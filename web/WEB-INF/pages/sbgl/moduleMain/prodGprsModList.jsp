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

    <title>模块维护</title>
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
    <a href="#">模块维护</a>
    <div class="pull-right"><button type="button" class="btn btn-mystyle btn-sm" onclick="if(window.opener){window.close()}else{history.go(-1)}">返回</button></div>
</div>
<!--页面标头end-->

<div class="container-fluid">
    <div class="info-center">
        <!--页面标头end-->
        <form class="ajax_form validate" method="post" action="" novalidate="novalidate">
            <!--操作栏start-->
            <div class="search-box row">
                <div class="col-lg-8">
                    <div class="pull-left ">
                        <input type="text" name="bizName" class="form-control pull-left" placeholder="请输入绑定商家" style="width:220px; margin:0px 6px;" data-original-title="" title="">
                        <input type="text" name="gprsNo" class="form-control pull-left" placeholder="请输入模块编号" style="width:220px; margin:0px 6px;" data-original-title="" title="">
                        <span>模块类型：</span>
                        <div class="btn-group">
                            <select id="gprsType" name="gprsType" style="width:145px" class="form-control pull-left" data-original-title="" title="">
                                <option value="0">电子投币</option>
                                <option value="1">洗车机</option>
                                <option value="2">动态口令</option>
                                <option value="6">充电站</option>
                                <option value="3">昌原充电站</option>
                            </select>
                        </div>
                        <span>在线状态：</span>
                        <div class="btn-group">
                            <select id="onlineState" name="onlineState" style="width:145px" class="form-control pull-left" data-original-title="" title="">
                                <option value="">全部</option>
                                <option value="1">在线</option>
                                <option value="0">离线</option>
                            </select>
                        </div>
                        <button class="btn btn-default" id="search" type="button"><span class="glyphicon glyphicon-search"></span> 查询
                        </button>
                    </div>
                </div>
                <div class="col-lg-4">
                    <span>是否应用全部设备：</span>
                    <div class="btn-group">
                        <select id="all" name="all" style="width:145px" class="form-control pull-left" data-original-title="" title="">
                            <option value="1">是</option>
                            <option value="0">否</option>
                        </select>
                    </div>
                    <div class="btn-group pull-right">
                        <a href="javascript:void(0)" onclick="setProdModArgs()" class="btn btn-default add"></span>模块参数设置</a>
                    </div>
                    <div class="btn-group pull-right">
                        <a href="javascript:void(0)" onclick="setProdArgs()" class="btn btn-default add"></span>产品参数设置</a>
                    </div>
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
                            <td width="40%"  class="tr_bg2">绑定商家</td>
                            <td width="10%"  class="tr_bg3">版本</td>
                            <td width="10%"  class="tr_bg3">是否在线</td>
                            <td width="15%"  class="tr_bg3">位置</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="model" items="${pager.recordList}">
                            <tr>
                                <td width="2%">
                                    <label for="chk_list">
                                        <input type="checkbox" value="${model.gprsNo}" id="chk_list" name="chk_list">
                                        <input type="hidden" value="${model.online}" id="online">
                                    </label>
                                </td>
                                <td width="15%">${model.gprsNo}</td>
                                <td width="40%">${fn:substring(model.bizName,0,7)}</td>
                                <td width="10%">${model.mo}</td>
                                <td width="10%">${model.onlineStr}</td>
                                <td width="15%">
                                    <c:if test="${not empty model.pos}">
                                        <a href="#" pos="${model.pos}">详情</a>
                                    </c:if>
                                </td>
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
    function setProdModArgs() {
        var all = $('#all').val();
        var gprsList = $("input[name='chk_list']:checked");
        var online = $("input[name='chk_list']:checked").parent().find('#online').val();
        var gprsNoList = "";
        if (all == 0) {
            if (gprsList.length == 0) {
                alert("请选择产品");
                return;
            }
        }
        window.location.href = _CTX + "/prod/prodGprsArgsSet?all=" + all + "&gprsNoList=" + gprsNoList;
    }

    function setProdArgs() {
        var all = $('#all').val();
        var gprsType = $('#gprsType').val();
        var gprsList = $("input[name='chk_list']:checked");
        var online = $("input[name='chk_list']:checked").parent().find('#online').val();
        var gprsNoList = "";
        var ale = false;
        if (all == 0) {
            if (gprsList.length == 0) {
                alert("请选择产品");
                return;
            }
            /*if (gprsList.length == 1 && (online == 0 || online == null || online == '')) {
             alert("设备不在线不能设置模块参数");
             return;
             }*/
            gprsList.each(function () {
                if ($(this).val().substring(0, 1) == '3') {
                    gprsNoList = gprsNoList + "_" + $(this).val();
                } else if ($(this).val().substring(0, 1) == '0') {
                    ale = ale&&true;
                }
            });
        }
        if (ale && gprsList.length == 1) {
            alert("该设备不支持产品参数设置");
        } else if (ale && gprsList.length > 1) {
            alert("提示：设备不支持参数设置");
            //window.location.href = _CTX + "/prod/prodSpArgsSet?all=" + all + "&gprsNoList=" + gprsNoList;
        } else {
            window.location.href = _CTX + "/prod/prodSpArgsSet?all=" + all + "&gprsNoList=" + gprsNoList+"&gprsType="+gprsType;
        }
    }

    $(document).ready(function () {

        $("#chk_all").click(function () {
            $("input[name='chk_list']").prop("checked", $(this).prop("checked"));
        });

        $('#all').on('change', function (e) {
            var val = $(this).val();
            if (val == 1) {
                $("input[type='checkbox']").attr("disabled", "disabled");
            } else {
                $("input[type='checkbox']").removeAttr("disabled");
            }
        });

        var btnClose = $('.close');
        var alertBox = $('.alert-box');
        btnClose.on('touchstart click', function (e) {
            e.preventDefault();
            alertBox.hide();
        });
        $("a[pos]").click(function () {
            var pos = $(this).attr("pos");
            $('#pos').html(pos);
            alertBox.css('display', 'block');
        });

        $("#gprsType").val("${pager.params.gprsType}");
        $("#onlineState").val("${pager.params.onlineState}");
    });
</script>
</body></html>