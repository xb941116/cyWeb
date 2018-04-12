<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx"
       value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<script>
    var _CTX = '${ctx}';
</script>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>商家绑定</title>
    <script type="text/javascript">var webroot="", res="/res/",themespath="/src/admin/view/themes/";</script>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/byc/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/byc/default.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/byc/ui.css">
    <link href="${ctx}/static/css/byc/WdatePicker.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${ctx}/static/js/byc/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/byc/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/byc/send.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/byc/header.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/byc/WdatePicker.js"></script>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
</head>
<body class="">
<!--页面标头start-->
<div class="ctabs">
    <a href="#">商家绑定</a>
    <div class="pull-right"><button type="button" class="btn btn-mystyle btn-sm" onclick="if(window.opener){window.close()}else{history.go(-1)}">返回</button></div>
</div>
<!--页面标头end-->

<div class="container-fluid">
    <div class="info-center">
        <!--页面标头end-->
        <form class="ajax_form validate" method="post" action="" novalidate="novalidate">
            <!--操作栏start-->
            <div class="search-box row">
                <div class="col-lg-6">
                    <div class="pull-left ">
                        <input type="text" name="gprsNo" class="form-control pull-left" placeholder="请输入模块编号" style="width:220px; margin:0px 6px;" data-original-title="" title="">
                        <c:if test="${admin}">
                        <span>是否绑定：</span>
                        <div class="btn-group">
                            <select id="bind" name="bind" style="width:145px" class="form-control pull-left" data-original-title="" title="">
                                <option value="">全部</option>
                                <option value="1">已绑定</option>
                                <option value="0">未绑定</option>
                            </select>
                        </div>
                        </c:if>
                        <c:if test="${!admin}">
                            <span>是否经营：</span>
                            <div class="btn-group">
                                <select id="prodSet" name="prodSet" style="width:145px" class="form-control pull-left" data-original-title="" title="">
                                    <option value="">全部</option>
                                    <option value="1">已经营</option>
                                    <option value="0">未经营</option>
                                </select>
                            </div>
                        </c:if>
                        <button class="btn btn-default" id="search" type="button"><span class="glyphicon glyphicon-search"></span> 查询
                        </button>
                    </div>
                </div>
                <div class="col-lg-6">
                    <span>请选择商家：</span>
                    <div class="btn-group">
                        <select id="bizNo" name="bizNo" style="width:145px" class="form-control pull-left" data-original-title="" title="">
                            <c:forEach var="biz" items="${bizList}">
                                <option value="${biz.bizNo}">${biz.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <c:if test="${admin}">
                        <div class="btn-group pull-right">
                             <a href="javascript:void(0)" onclick="moveModel(this)" class="btn btn-default add"></span>模块移动</a>
                        </div>
                        <div class="btn-group pull-right">
                            <a href="javascript:void(0)" onclick="unbindBiz(this)" class="btn btn-default add"></span>商家解绑</a>
                        </div>
                        <div class="btn-group pull-right">
                            <a href="javascript:void(0)" onclick="bindBiz(this)" class="btn btn-default add"></span>商家绑定</a>
                        </div>
                    </c:if>
                    <c:if test="${!admin}">
                        <c:if test="${grade==3}">
                            <div class="btn-group pull-right">
                                <a href="javascript:void(0)" onclick="moveModel(this)" class="btn btn-default add"></span>模块移动</a>
                            </div>
                        </c:if>
                        <div class="btn-group pull-right">
                            <a href="javascript:void(0)" onclick="unbindBiz(this)" class="btn btn-default add"></span>商家解绑</a>
                        </div>
                    </c:if>
                </div>
            </div>
            <!--操作栏end-->
            <div class="clearfix">
            </div>

            <!--主体内容start-->
            <section  class="body-bg">
                <div class="selec-list clearfix">
                    <div style="height: 5px;">&nbsp;</div>
                    <table class="table table-bordered table-margin  table-header table-hover">
                        <thead>
                        <tr>
                            <td width="2%"><input title="" value="id[]" class="chkall" type="checkbox" data-original-title="选中/取消"></td>
                            <td width="20%"  class="tr_bg2">编号</td>
                            <td width="25%"  class="tr_bg2">绑定商家</td>
                            <c:if test="${admin}">
                            <td width="20%"  class="tr_bg2">是否绑定</td>
                            </c:if>
                            <c:if test="${!admin}">
                            <td width="20%"  class="tr_bg3">是否经营</td>
                            </c:if>
                            <td width="25%"  class="tr_bg3">操作日期</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="gprs" items="${pager.recordList}">
                            <tr>
                                <td width="2%"><input value="${gprs.gprsNo}" name="id[]" type="checkbox" data-original-title="" title=""></td>
                                <td width="20%">${gprs.gprsNo}</td>
                                <td width="20%">${fn:substring(gprs.bizName,0,7)}</td>
                                <c:if test="${admin}">
                                    <td width="20%">${gprs.bindStr}</td>
                                </c:if>
                                <c:if test="${!admin}">
                                    <td width="20%">${gprs.prodSetStr}</td>
                                </c:if>
                                <td width="25%"><fmt:formatDate pattern="yyyy-MM-dd" value="${gprs.uptime}"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <section class="fc">
                    <div class="page clearfix">
                        ${pager.formPageStr}
                    </div>
                </section>
            </section>
            <!--主体内容end-->
        </form>
    </div>
</div>
<input id="operaction" type="hidden" value="">
<input id="operactionall" type="hidden" value="">
<script>
    $(document).ready(function () {
        var btnClose = $('.close');
        var alertBox = $('.alert-box');
        btnClose.on('touchstart click', function (e) {
            e.preventDefault();
            alertBox.hide();
        });

        $("#chk_all").click(function () {
            $("input[name='chk_list']").prop("checked", $(this).prop("checked"));
        });
        var bind="${pager.params.bind}";
        if(bind!="")  {
            $("#bind").val(bind);
        }
    });

    function bindBiz(btn) {
        var bizNo = $("#bizNo").val();
        if (bizNo && bizNo.length > 0) {
            var gprsList = $("input[name='id[]']:checked");
            alert(gprsList.length);
            if (gprsList && gprsList.length > 0) {
                var gprsNoList = "";
                gprsList.each(function () {
                    gprsNoList = gprsNoList + "_" + $(this).val();
                });
                $(btn).attr("disabled", "true");  //按钮禁止点击
                $(btn).html("绑定中...");
                $.post(_CTX + "/biz/bizGprsBind/bind.json",
                        {bizNo: bizNo, gprsNoList: gprsNoList},
                        function (data) {
                            $(btn).removeAttr("disabled");  //按钮禁止点击
                            $(btn).html("商家绑定");
                            if (data) {
                                if (data.success) {
                                    alert("模块绑定成功");
                                } else {
                                    alert("部分模块未绑定：" + data.msg + ",原因可能是模块已经绑定过");
                                }
                                window.location.href = _CTX + "/biz/bizGprsBind/query";
                            }
                        }, "json");
            } else {
                alert("请选择模块");
            }
        } else {
            alert("请选择商家");
        }

    }

    function unbindBiz(btn) {
        var gprsList = $("input[name='id[]']:checked");
        if (gprsList && gprsList.length > 0) {
            var r=confirm("解绑后模块将移动到上级商家，确定要解绑吗？");
            if(r==true){
                var gprsNoList = "";
                gprsList.each(function () {
                    gprsNoList = gprsNoList + "_" + $(this).val();
                    alert(gprsList.val());
                });
                $(btn).attr("disabled", "true");  //按钮禁止点击
                $(btn).html("解绑中...");
                $.post(_CTX + "/biz/bizGprsBind/unbind.json",
                        {gprsNoList: gprsNoList},
                        function (data) {
                            $(btn).removeAttr("disabled");  //按钮禁止点击
                            $(btn).html("商家解绑");
                            if (data) {
                                if (data.success) {
                                    alert("解除绑定成功");
                                } else {
                                    alert("部分模块未解除绑定：" + data.msg + ",原因可能是模块不隶属于本商家");
                                }
                                window.location.href = _CTX + "/biz/bizGprsBind/query";
                            }
                        }, "json");
            }
        } else {
            alert("请选择模块");
        }
    }

    function moveModel(btn) {
        var bizNo = $("#bizNo").val();
        var gprsList = $("input[name='chk_list']:checked");
        if (gprsList && gprsList.length > 0) {
            var gprsNoList = "";
            gprsList.each(function () {
                gprsNoList = gprsNoList + "_" + $(this).val();
            });
            $(btn).attr("disabled", "true");  //按钮禁止点击
            $(btn).html("移动中...");
            $.post(_CTX + "/biz/bizGprsBind/moveModel.json",
                    {bizNo:bizNo,gprsNoList: gprsNoList},
                    function (data) {
                        $(btn).removeAttr("disabled");  //按钮禁止点击
                        $(btn).html("模块移动");
                        if (data) {
                            if (data.success) {
                                alert(data.msg);
                            } else {
                                alert("部分模块未移动：" + data.msg + ",原因可能是模块不隶属于本商家");
                            }
                            window.location.href = _CTX + "/biz/bizGprsBind/query";
                        }
                    }, "json");
        } else {
            alert("请选择模块");
        }
    }
</script>
</body></html>