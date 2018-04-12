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

    <title>商家管理</title>
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
    <a href="#">商家管理</a>
    <div class="pull-right"><button type="button" class="btn btn-mystyle btn-sm" onclick="if(window.opener){window.close()}else{history.go(-1)}">返回</button></div>
</div>
<!--页面标头end-->
<%
    request.setCharacterEncoding("UTF-8");
%>
<div class="container-fluid">
    <div class="info-center">
        <!--页面标头end-->
        <form class="ajax_form validate" method="post" novalidate="novalidate">
            <!--操作栏start-->
            <div class="search-box row">
                <div class="col-lg-6">
                    <div class="pull-left ">
                        <input type="text" name="name" class="form-control pull-left" placeholder="请输入商家名称" style="width:220px; margin:0px 6px;" data-original-title="" title="">
                        <button class="btn btn-default" id="search" type="button"><span class="glyphicon glyphicon-search"></span> 查询
                        </button>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="btn-group pull-right">
                        <a href="${ctx}/regCtrl/bizReg" class="btn btn-default add"><span class="glyphicon glyphicon-plus"></span>商家添加</a>
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
                            <td width="35%" class="tr_bg1">商家名称</td>
                            <td width="15%" class="tr_bg2">商家类型</td>
                            <c:if test="${pager.params.isAdmin!=null&&pager.params.isAdmin==true}">
                                <td width="15%" class="tr_bg2">商家编号</td>
                                <td width="35%" class="tr_bg3">操作</td>
                            </c:if>
                            <c:if test="${pager.params.isAdmin==null||pager.params.isAdmin==false}">
                                <td width="50%" class="tr_bg3">操作</td>
                            </c:if>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="biz" items="${pager.recordList}">
                            <tr>
                                <td width="35%">${biz.name}<c:if test="${pager.params.isAdmin!=null&&pager.params.isAdmin==true}">(${biz.acct})</c:if></td>
                                <td width="15%"><c:if test="${biz.grade==3}">代理商</c:if><c:if test="${biz.grade!=3}">商家</c:if></td>

                                <c:if test="${pager.params.isAdmin!=null&&pager.params.isAdmin==true}">
                                    <td width="15%">
                                            ${biz.bizNo}
                                    </td>
                                    <td width="35%">
                                        <a href="javascript:void(0);" class="input-style" onclick="edit('${biz.bizNo}')" >信息修改</a>
                                        <a href="#" class="input-style" right="${biz.bizNo}">权限修改</a>
                                    </td>
                                </c:if>
                                <c:if test="${pager.params.isAdmin==null||pager.params.isAdmin==false}">
                                    <td width="50%">
                                        <a href="javascript:void(0);" class="input-style" onclick="edit('${biz.bizNo}')" >信息修改</a>
                                        <a href="#" class="input-style" right="${biz.bizNo}">权限修改</a>
                                    </td>
                                </c:if>

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

        $("a[edit]").click(function (e) {
            e.preventDefault();
            var bizNo = $(this).attr('edit');
            $('#bizNo').val(bizNo);
            var prodId =$("#bizNo").val();
            window.location.href = _CTX + "/biz/toUpdSeller?bizNo=" + prodId;
        });
        $(".btnReadSec").click(function () {
            if($('#logoFile').val() != ''&&$('#logoFile').val()!=null){
                ajaxFileUpload();
            }else {
                updateBiz(null);
            }
        });

        $("a[right]").click(function () {
            var bizNo = $(this).attr("right");
            window.location.href = _CTX + "/biz/bizRight?bizNo=" + bizNo;
        });

    });


    function edit(bizNo){
        $('#bizNo').val(bizNo);
        window.location.href = _CTX + "/biz/toUpdSeller?bizNo=" + bizNo;
    }

    /**
     * 修改数据
     */
    function updateBiz(logo) {
        var alertBox = $('.alert-box');
        var pwd = $("#pwd").val();
        var pwd2 = $("#pwd2").val();
        var takeSet = $("#takeSet").val();
        var grade = $("#grade").val();
        if(pwd!=null && pwd!='' && pwd!=pwd2){
            alert("两次输入密码不一致");
            return;
        }

        var bizNo = $("#bizNo").val();
        $.post(_CTX + "/biz/upBizInfo.json",
                {bizNo: bizNo, pwd: pwd, grade: grade, takeSet: takeSet, logo: logo},
                function (data) {
                    if (data) {
                        if (data.success) {
                            alertBox.css('display', 'none');
                            alert("修改成功");
                            location.reload();
                        } else {
                            alert(data.msg);
                        }
                    }
                }, "json");
    }
</script>
</body></html>