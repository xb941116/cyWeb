<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx"
       value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<script>
    var _CTX = '${ctx}';
</script>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>后台管理系统</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/byc/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/byc/default.css">
    <script type="text/javascript" src="${ctx}/static/js/byc/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/byc/bootstrap.min.js"></script>

</head>

<body scroll="no" style="overflow: hidden">

<%@include file="../_layout_nav.jsp" %>
<div class="down-main">
    <div class="left-main left-full">
        <div class="sidebar-fold"><span class="glyphicon glyphicon-menu-hamburger"></span></div>
        <div class="subNavBox">
            <div class="sBox" data-pid="30" data-id="36" style="display: block;">
                <div class="subNav sublist-up"><span class="title-icon glyphicon glyphicon-chevron-up"></span><span class="sublist-title">管理中心</span></div>
                <ul class="navContent" style="display: block;">
                    <li>
                        <div class="showtitle" style="display: none;"><img src=""></div>
                        <a href="${ctx}/navCtrl/equipment" target="main"><span class="sublist-icon glyphicon glyphicon-minus"></span><span class="sub-title"><i class="icon iconfont"></i>设备</span></a>
                    </li>
                    <c:forEach var="sysRes" items="${sysResList}" varStatus="sta">
                    <li>
                        <div class="showtitle" style="display: none;"><img src=""></div>
                        <a href="${ctx}${sysRes.uri}" target="main"><span class="sublist-icon glyphicon glyphicon-minus"></span><span class="sub-title"><i class="icon iconfont"></i>${sysRes.name}</span></a>
                    </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    <div class="right-product right-full">
        <iframe id="main" name="main" frameborder="0" scrolling="yes" src="" style="z-index: 2; width: 100%; height: 871px; overflow: hidden;"></iframe>
    </div>
</div>
<script>
    $("#main").height($("#main").parent().height());
</script>

</body></html>