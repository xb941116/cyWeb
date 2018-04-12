<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx"
       value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<script>
    var _CTX = '${ctx}';
</script>
<html><head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>会员管理</title>
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
<body>
<!--页面标头start-->
<div class="ctabs">
    <a href="#">会员管理</a>
    <div class="pull-right"><button type="button" class="btn btn-mystyle btn-sm" onclick="if(window.opener){window.close()}else{history.go(-1)}">返回</button></div>
</div>
<!--页面标头end-->

<div class="container-fluid">
    <div class="info-center">

        <form method="post">
            <!--操作栏start-->
            <div class="search-box row">
                <div class="col-lg-12">
                    <div class="pull-left">
                        <input type="text" name="mobile" class="form-control pull-left" placeholder="请输入手机号" style="width:220px; margin:0px 6px;">
                        <input type="text" name="name" class="form-control pull-left" placeholder="请输入姓名" style="width:220px; margin:0px 6px;">
                        <button class="btn btn-default" id="search" type="button"><span class="glyphicon glyphicon-search"></span>查询
                        </button>
                    </div>
                </div>
                <div class="col-lg-6">
                </div>
            </div>
            <!--操作栏end-->

            <div class="clearfix"></div>


            <!--主体内容start-->
            <div class="table-margin">

                <table class="table table-bordered  table-header table-hover">
                    <thead>
                    <tr>
                        <td width="100">手机号</td>
                        <td width="100">会员昵称</td>
                        <td width="100">微信昵称</td>
                        <td width="120">余额</td>
                        <td width="160">创建时间</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pager.recordList}" var="mbr">
                        <tr onclick="loadGift('${mbr.id}');">
                            <td width="20%">${mbr.mobile}</td>
                            <td width="20%">${mbr.name}</td>
                            <td width="20%">${mbr.nick}</td>
                            <td width="20%">${mbr.money}</td>
                            <td width="20%"><fmt:formatDate pattern="yyyy/MM/dd" value="${mbr.crtime}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <section class="fc">
                    <div class="page clearfixs">
                        ${pager.formPageStr}
                    </div>
                </section>
            </div>
            <!--主体内容end-->

        </form>
    </div>
</div>
<input id="operaction" type="hidden" value="">
<input id="operactionall" type="hidden" value="">

<script>
    function loadGift(id) {
        location.href = _CTX + "/mbr/reChargeHis?memberId=" + id;
    }
</script>
</body></html>