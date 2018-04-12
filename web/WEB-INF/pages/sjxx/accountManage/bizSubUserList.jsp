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

    <title>子账号管理</title>
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
    <a href="#">子账号管理</a>
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
                        <input type="text" name="acct" class="form-control pull-left" placeholder="请输入账号" style="width:220px; margin:0px 6px;" data-original-title="" title="">
                        <button class="btn btn-default" id="search" type="button"><span class="glyphicon glyphicon-search"></span> 查询
                        </button>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="btn-group pull-right">
                        <a href="${ctx}/sys/sysAcct/add" class="btn btn-default add"><span class="glyphicon glyphicon-plus"></span>添加子账号</a>
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
                            <td width="20%"  class="tr_bg2">账号</td>
                            <td width="15%"  class="tr_bg2">姓名</td>
                            <td width="15%"  class="tr_bg2">状态</td>
                            <td width="15%"  class="tr_bg3">是否主账户</td>
                            <td width="30%"  class="tr_bg3">操作</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="sysUsr" items="${pager.recordList}">
                            <tr>
                                <td width="20%">${sysUsr.acct}</td>
                                <td width="15%">${sysUsr.name}</td>
                                <td width="15%">${sysUsr.stateStr}</td>
                                <td width="15%"><c:if test="${sysUsr.mainAcct==1}"><span style="color: #ff0000">主账户</span></c:if><c:if test="${sysUsr.mainAcct==0}">子账户</c:if></td>
                                <td width="35%">
                                    <a href="${ctx}/sys/sysAcct/edit?id=${sysUsr.id}">修改</a>
                                    &nbsp;&nbsp;&nbsp;
                                    <c:if test="${sysUsr.mainAcct==0}"><a href="#" class="remove" id="${sysUsr.id}">删除</a></c:if>
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
    $(document).ready(function () {
        $('a.remove').on('click', function (e) {
            e.preventDefault();
            if (confirm("确定要删除吗？")) {
                var id = $(this).attr('id');
                $.post(_CTX + "/sys/sysAcct/remove.json",
                        {id: id},
                        function (data) {
                            if (data) {
                                if (data.success) {
                                    alert("删除成功！");
                                    window.location.href = _CTX + "/biz/subUserList"
                                } else {
                                    alert(data.msg);
                                }
                            }
                        }, "json");
            }

        });
    });
</script>
</body></html>