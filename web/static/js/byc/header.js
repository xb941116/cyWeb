////*Spp.page.bind页面事件*///
(function () {
    var editor = null;
    Spp.page.bind = {
        init: function () {
            this.pageBase();
            this.form();
            this.dialog();
            this.ajax();
            this.excelTo();
            this.search();
            this.editor();
            this.upfile();
            this.dataPicker();
            this.area();
            this.other();
            this.operaction();
        },
        //基础页面事件//
        pageBase: function () {
            /*返回按钮*/
            $(".back").click(function () {
                Spp.page.action.close();
            })

            /*复选框全选设置*/
            $(".chkall").on("click", function () {
                var checked = this.checked;
                $("input:enabled[name='" + $(this).val() + "']").each(function (i, v) {
                    v.checked = checked;
                })
            });
            $(".chklike").on("click", function () {
                var checked = this.checked;
                $("input:enabled:visible[name^='" + $(this).val() + "']").each(function (i, v) {
                    v.checked = checked;
                })

            });
            $("select[data-selected]").each(function (i, v) {
                if ($(v).attr("data-selected") != '') {
                    $(v).val($(v).attr("data-selected"));
                    $(v).trigger("change");
                }
            })


            if ($(".ctabs h4").length == 1) {
                /*标题*/
                var title = location.href.getQueryValue("title").decodeURI() || $("div.subNavBox li.active span.sub-title", parent.window.document).html();
                if (title && title != '') {
                    $(document).attr("title", title);//修改title值
                    $(".ctabs h4 a").html(title);
                }
            }
            $(".nav-tabs").each(function (i, v) {
                var content = $(v).next(".tab-content");
                $(v).find("a[href='#']").click(function () {
                    var tab = $(this).parent("li");
                    tab.addClass("active").siblings().removeClass("active");
                    content.find(".tab-pane").eq(tab.index()).addClass("active").siblings(".tab-pane").removeClass("active");
                });
                if ($(v).find("li.active").length > 0)
                    $(v).find("li.active a").click();
                else
                    $(v).find("li:eq(0) a[href='#']").click();
            })

            $("input[type='password']").each(function (i, v) {
                $(v).attr("type", "text");
                $(v).one("focus", function () {
                    $(this).attr("type", "password");
                })
            })

        },
        ///FORM提交
        form: function () {
            var $this = this;
            $("form.validate").find("input,select,textarea").tooltip({
                placement: 'auto top',
                "template": '<div class="tooltip" role="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>'
            });
            $("form.ajax_form").attr("novalidate", "novalidate").on("submit", function () {
                if ($(this).hasClass("validate")) {
                    if (!$.validator) {
                        Spp.dialog.alert("页面未加载验证模块");
                        return false;
                    }
                    var vali = $(this).validate();
                    if (!vali.form())
                        return false;
                }
                var data = $(this).serializeArray();
                Spp.page.action.submit($(this).attr("action") || location.href, data);
                return false;
            })
        },
        ///弹出窗口
        dialog: function () {
            var $this = this;
            $this.refresh = 0;
            $("a.dialog").on("click", function () {
                    if ($(this).attr("data-rel")) {
                        var data = $(this).attr("data-rel").parseJSON();
                        var pdiv = $("#" + data.id), vform = $("form", pdiv), vali = vform.validate();
                        if (pdiv == null || vform == null)return false;
                        vform[0].reset();
                        vali.resetForm();
                        vform.find("input,select,textarea").removeAttr("data-original-title").removeClass("vali-error");
                        Spp.page.bindData(pdiv, data.data);
                        var action = data.action ? data.action : "add";
                        var actionText = data.actionText ? data.actionText : (action.indexOf("add") !== -1 ? "添加" : "保存")
                        var close = function () {
                            if ($this.refresh == 1) {
                                location.reload();
                            }
                        }
                        var params = {
                            id: pdiv.attr("id"),
                            title: data.title,
                            closeFunction: close,
                            bottom: {
                                action: actionText,
                                callback: function () {
                                    vform.trigger("submit");
                                }
                            }
                        }
                        Spp.dialog.div(params);
                        function callBack(bk) {
                            if (bk.code == 0) {
                                Spp.dialog.alert(bk.msg, function () {
                                    Spp.dialog.div(params);
                                });
                            }
                            else {
                                $this.refresh = 1
                                switch (bk.data.type) {
                                    case "alert":
                                        Spp.dialog.alert(bk.msg);
                                        break;
                                    case "back":
                                        Spp.dialog.msg("success", bk.msg, close());
                                        break;
                                    case "save":
                                        Spp.dialog.msg("success", bk.msg, function () {
                                            location.reload();
                                            /*
                                             if (location.href.getQueryValue("backurl") == "") {
                                             location.href = location.href.addQueryValue("backurl", document.referrer)
                                             }*/
                                        });
                                        break;
                                    case "add":
                                        Spp.dialog.confirm(bk.msg, function () {
                                            vform[0].reset();
                                            vali.resetForm();
                                            Spp.dialog.div(params);
                                        }, close);
                                        break;
                                }
                            }
                        }

                        vform.unbind("submit").bind("submit", function () {
                            if ($(vform).hasClass("validate") && !vali.form())
                                return false;
                            var url = $(this).attr("action").replace("action", action);
                            Spp.page.action.submit(url, $(this).serialize(), callBack);
                            return false;
                        });
                    }
                    else {
                        var url = $(this).attr("href"),
                            height = parseInt(url.getQueryValue("h")) || 150, width = parseInt(url.getQueryValue("w")) || 400, title = url.getQueryValue("title");
                        if ($(this).hasClass("dialog-modal")) {
                            Spp.dialog.url(url);
                            return false;
                        } else {
                            url = url.removeQueryValue("w").removeQueryValue("h").removeQueryValue("title").addQueryValue("title", title.encodeURI());
                            var iTop = (window.screen.availHeight - 30 - height) / 2;       //获得窗口的垂直位置;
                            var iLeft = (window.screen.availWidth - 10 - width) / 2;           //获得窗口的水平位置;
                            var target = parent.window.name + "1";
                            url = url + '&tmd=' + Math.random();
                            var win = window.open(url, target, 'height=' + height + ',width=' + width + ',top=' + iTop + ',left=' + iLeft + ',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no')
                            if (win == null)
                                alert("您的浏览器启用弹出窗口过滤功能！/n/n请暂时先关闭此功能！");
                            return false;
                        }
                        // Spp.dialog.url($(this).attr("href"))
                    }
                    return false;
                }
            );
        },
        ///AJAX按钮绑定
        ajax: function () {
            var $this = this;
            $(".ajax").click(function () {
                if ($(this).hasClass("disabled"))
                    return false;
                var url = $(this).attr("href");
                var field = $(this).attr("data-field")
                var data = {field: field};
                if (!field || field == '') {
                    if ($(this).attr("class").indexOf("del") != -1) {
                        var did = $(".chkall", $(this).parents("form")).val();
                        if ($("input[name='" + did + "']:checked").size() <= 0) {
                            Spp.dialog.alert("请至少选择一项或多项进行操作！");
                            return false;
                        }
                    }
                    var $form = $(this).parents("form");
                    if ($form.length > 0)
                        data = $form.serialize();
                }
                if ($(this).attr("data-msg") == "")
                    Spp.page.action.submit(url, data);
                else {
                    var msg = $(this).attr("data-msg");
                    if (typeof(msg) == "undefined")
                        msg = $(this).hasClass("del") ? "该操作将删除此信息及关联信息且不可恢复！您确定要删除该信息吗？" : "您确定要执行此操作吗？";
                    Spp.dialog.confirm(msg, function () {
                        Spp.page.action.submit(url, data);
                    });
                }
                return false;
            });
        },
        ///excel导出
        excelTo: function () {
            $(".dc").click(function () {
                var excel = $("#excelTo");
                if (!excel[0]) {
                    var $total = $(this).attr("data-total") || 0;

                    var $url = $(this).attr("href") || $(this).attr("data-url") || location.href;
                    var $size = $(this).attr("data-size") || 1000;
                    if ($total <= 0) {
                        Spp.dialog.alert("暂无导出数据");
                        return false;
                    }
                    $url = $url.addQueryValue("pagesize", $size);
                    excel = $("<div id='excelTo' style='width:350px;'></div>");
                    for (var i = 1; i <= Math.ceil($total / $size); i++) {

                        var don = $('<a href="' + $url.addQueryValue("page", i) + '" class="btn btn-link" style="width: 50%;"></a>');
                        if ((i * $size) > $total)
                            don.html(((i - 1) * $size + 1) + "~" + $total + "条记录");
                        else
                            don.html(((i - 1) * $size + 1) + "~" + i * $size + "条记录")
                        excel.append(don);
                    }
                    $("body").append(excel);
                }
                Spp.dialog.div("excelTo", "导出excel")
                return false;
            });
        },
        ///查询绑定
        search: function () {
            var $this = this;
            var baseurl = $("form:eq(0)").attr("action") || $("div.subNavBox li.active a", parent.window.document).attr("href") || location.href;
            $(".search-box .dropdown-menu").find("a").attr("href", function () {
                if (typeof($(this).attr("href")) == "undefined") {
                    return $(this).attr("sid") == "" ? baseurl.removeQueryValue("sid") : location.href.addQueryValue("sid", $(this).attr("sid"));
                }
            });
            $("#search").on("click", function () {
                location.href = Spp.page.bindUrl(baseurl, $(".search-box"));
            });

            $(".search-box input:text").bind("keypress", function (e) {
                if (e.keyCode == 13) {
                    $("#search").trigger("click");
                    return false;
                }
            })
            Spp.page.bindData($(".search-box"), location.href.getQueryJson());
            $("a[sid='" + location.href.getQueryValue("sid") + "']", $(".search-box")).parent().attr("class", "active");
            ///高级查询
            $("#searchall").on("click", function () {
                var pdiv = $("#searchpn");
                Spp.page.bindData(pdiv, location.href.getQueryJson());
                Spp.dialog.div({
                    id: "searchpn",
                    title: "高级搜索",
                    bottom: {
                        action: "查询",
                        callback: function () {
                            location.href = Spp.page.bindUrl(baseurl, $("#searchpn"));
                        }
                    }
                });
            });
        },
        ///编辑器绑定
        editor: function () {
            if ($(".editor").length > 0) {
                if (!window.KindEditor) {
                    Spp.dialog.alert("页面未加载编辑器");
                    return false;
                }

                editor = KindEditor.create('.editor', {
                    uploadJson: webroot + '/webservice/upload/upfile.html',
                    fileManagerJson: webroot + '/webservice/upload/getlist.html',
                    allowFileManager: true,//图片浏览
                    imageTabIndex: 1,
                    afterBlur: function () {
                        this.sync();
                    }

                });
            }
        },
        //图片上传绑定
        upfile: function () {
            if ($(".uploader").length > 0)
                Spp.page.loadJs("ui/uploader.js", function () {
                    $(".uploader").uploader();
                });
        },
        dataPicker: function () {
            if ($(".wdate").length > 0) {
                $(".wdate").focus(function () {
                    var params = {
                        dateFmt: 'yyyy-MM-dd'
                    }
                    $.extend(params, $(this).attr("data-picker") && $(this).attr("data-picker") != '' ? $(this).attr("data-picker").parseJSON() : "");
                    var $nm = $(this).attr("name");
                    if ($nm.indexOf("s") == 0) {
                        var $e = "e" + $nm.substring(1);
                        if ($("[name='" + $e + "']").size() > 0) {
                            $.extend(params, {
                                maxDate: '#F{$dp.$D(\'' + $e + '\')}',
                                startDate: new Date().dateAdd("d", -7).format("yyyy-MM-dd")
                            });
                        }
                    }
                    if ($nm.indexOf("e") == 0) {
                        var $s = "s" + $nm.substring(1);
                        if ($("[name='" + $s + "']").size() > 0) {
                            $.extend(params, {minDate: '#F{$dp.$D(\'' + $s + '\');}'})
                        }
                    }
                    WdatePicker(params);

                });
            }
        },
        ///省市县联动
        area: function () {
            function bind(obj, pid) {
                var text = obj.attr("data-text");
                if (pid !== "") {
                    $.post(webroot + "/webservice/area.html", {"pid": pid}, function (data) {
                        $(data).each(function (i, v) {
                            var $option = $("<option value='" + v.id + "'>" + v.title + "</option>");
                            if (text == v.title) {
                                $option.attr("selected", true);
                                //input.val(v.title);
                                obj.removeAttr("data-text")
                            }
                            obj.append($option); //添加一项option
                        });
                        obj.change();
                    })
                } else {
                    obj.change();
                }

            }

            $(".area").change(function () {
                var name = $(this).attr("name").trimEnd("id");
                var input = $(this).next("input[name='" + name + "']");
                if (input.length <= 0)
                    input = $('<input  name="' + name + '" type="hidden"  />').insertAfter($(this));
                if ($(this).val() != "")
                    input.val($(this).find("option:selected").text());
                else
                    input.val("");
                var index = $(this).attr("data-index")._toNumber();
                if (typeof($(this).attr("data-gruop")) == "undefined")
                    var obj = $(".area[data-index='" + (index + 1) + "']");
                else
                    var obj = $(".area[data-gruop='" + $(this).attr("data-gruop") + "'][data-index='" + (index + 1) + "']");
                if (obj[0]) {
                    obj.find("option[value!='']").remove();
                    bind(obj, $(this).val());
                }
            });
            $(".area[data-index='0']").each(function (i, v) {
                bind($(v), 0);
            })
        },
        other: function () {
            $(".imgview").each(function () {
                $(this).popover({
                    content: '<img src="' + $(this).attr('href') + '" width="200" height="200">',
                    html: true,
                    placement: 'auto top',
                    trigger: 'hover'
                });
            })
        },
        ///页面权限
        operaction: function () {
            if (!$("#operactionall")[0])
                return false;
            $.each($("#operactionall").val().split(','), function (i, v) {
                if ($("#operaction").val().split(",").indexOf(v) == -1) {
                    var obj = $("." + v);
                    if (obj.length <= 0)
                        return true;
                    var parent = obj.parents("div.panelBar");
                    if (parent) {
                        obj.hide();
                        obj.next("a.aline").hide();
                        if ($(".panelBar a:visible").length <= 0)
                            $(".panelBar").hide();
                    }
                    parent = obj.parents("table.datalist");
                    if (parent) {
                        if (obj.is("th")) {
                            $("tr", parent).find("td:eq(" + obj.index() + ")").hide();
                            obj.hide();
                        }
                        else {
                            obj.hide();
                            $td = obj.eq(0).parent();
                            if ($td.is("td") && parent.find("a:visible").length <= 0) {
                                $("tr", parent).find("td:eq(" + $td.index() + ")").hide();
                                $("tr", parent).find("th:eq(" + $td.index() + ")").hide();

                            }
                        }
                    }
                }
            });
        }
    };
    Spp.page.action = {
        ///弹出窗口关闭事件
        submit: function (url, data, func) {
            var $this = this;
            $.ajax({
                url: url,
                type: "post",
                dataType: "json",
                data: data,
                beforeSend: function () {
                    Spp.dialog.loading("正在执行操作....");
                },
                success: function (bk) {
                    if (func) {
                        func(bk);
                        return false;
                    }
                    var data = bk.data;
                    /* if (!data.url && location.href.getQueryValue("backurl") != "")
                     data.url = location.href.getQueryValue("backurl");*/
                    var evt = data.url ? "location.href = '" + data.url + "'" : null;
                    evt = data.act && data.act != "" ? "window[data.act](bk)" : evt;
                    switch (data.type) {
                        case "error":
                            Spp.dialog.alert(bk.msg, function () {
                                eval(evt)
                            });
                            break;
                        case "add":
                            if (location.href.getQueryValue("backurl") != "") {
                                evt = 'location.href = document.location.href.sliceBefore("#")';
                            } else {
                                evt = 'location.href = location.href.addQueryValue("backurl",document.referrer)';
                            }
                            Spp.dialog.msg("success", bk.msg, function () {
                                eval(evt);
                            });
                            break;
                        case "save":
                            if (!data.url && !evt) {
                                evt = 'location.reload()';
                            }
                            Spp.dialog.msg("success", bk.msg, function () {
                                eval(evt);
                            });
                            break;
                        case "back":
                            if (!data.url && !evt && document.referrer)
                                evt = 'location.href=document.referrer';
                            Spp.dialog.msg("success", bk.msg, function () {
                                eval(evt)
                            });
                            break;
                        case "dialogclose":
                            if (evt && data.url)
                                evt = "window.openner.location.href = '" + data.url + "'";
                            if (!evt)
                                evt = 'window.openner..location.reload()';
                            Spp.dialog.msg("success", bk.msg, function () {
                                eval(evt);
                            });
                            break;
                        case "msg":
                        default:
                            Spp.dialog.msg("success", bk.msg, function () {
                                eval(evt);
                            })
                            break;

                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert(XMLHttpRequest.responseText);
                    this; // 调用本次AJAX请求时传递的options参数
                }
            });
        },
        close: function (url, evt) {
            if (location.href.getQueryValue("backurl") != "")
                url = location.href.getQueryValue("backurl");
            if (!url && url != "" && document.referrer)
                url = document.referrer;
            if (evt)
                eval(evt)
            else
                location.href = url;
        }
    }
    $(function () {
        Spp.page.bind.init();
    });
})(jQuery);
