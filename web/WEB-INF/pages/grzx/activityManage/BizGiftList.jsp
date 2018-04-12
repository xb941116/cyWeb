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
    <title>活动管理</title>
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
    <a href="#">活动管理</a>
    <div class="pull-right"><button type="button" class="btn btn-mystyle btn-sm" onclick="if(window.opener){window.close()}else{history.go(-1)}">返回</button></div>
</div>
<!--页面标头end-->

<div class="container-fluid">
    <div class="info-center">
        <div class="search-box row">
            <div class="col-lg-6">
                </div>
            <div class="col-lg-6">
                <div class="btn-group pull-right">
                    <a href="${ctx}/biz/bizGift/add" class="btn btn-default add"><span class="glyphicon glyphicon-plus"></span>添加活动</a>
                </div>
            </div>
            </div>
            <!--主体内容start-->

            <div class="table-margin">

                <table class="table table-bordered  table-header table-hover">
                    <thead>
                    <tr>
                        <td width="100">达标额度</td>
                        <td width="100">赠送积分额度</td>
                        <td width="100">活动排序</td>
                        <td width="120">创建日期</td>
                        <td width="160">是否开启</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pager.recordList}" var="bizGift">
                        <tr onclick="loadGift('${bizGift.id}');">
                            <td width="20%">${bizGift.coin}</td>
                            <td width="20%">${bizGift.money}</td>
                            <td width="20%">${bizGift.sort}</td>
                            <td width="20%"><fmt:formatDate pattern="yyyy/MM/dd" value="${bizGift.crtime}"/></td>
                            <td width="20%">${bizGift.stateStr}</td>
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
    </div>
</div>
<script>
    function loadGift(id) {
        location.href = _CTX + "/biz/bizGift/edit?id=" + id;
    }
</script>
</body></html>