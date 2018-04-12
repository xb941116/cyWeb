<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx"
       value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
    <title><c:if test="${sessionScope.wxBizNoLogo!=null&&sessionScope.wxBizNoLogo!=''}">${sessionScope.wxBizNoName}</c:if><c:if test="${sessionScope.wxBizNoLogo==null||sessionScope.wxBizNoLogo==''}">便易充</c:if>管理后台</title>
    <link rel="stylesheet" href="${ctx}/static/font/demo.css?v=1">
    <link rel="stylesheet" href="${ctx}/static/font/iconfont.css?v=1">
    <link rel="stylesheet" href="${ctx}/static/centerfont/demo.css?v=1">
    <link rel="stylesheet" href="${ctx}/static/centerfont/iconfont.css?v=1">
    <link rel="stylesheet" href="${ctx}/static/adminfont/demo.css?v=1">
    <link rel="stylesheet" href="${ctx}/static/adminfont/iconfont.css?v=1">
    <link rel="stylesheet" href="${ctx}/static/css/common.css?v=1">
    <link rel="stylesheet" href="${ctx}/static/css/style.css?v=1">
    <script src="${ctx}/static/js/jquery-1.9.1.min.js"></script>
    <script src="${ctx}/static/js/scale.js"></script>
    <script src="${ctx}/static/js/common.js?v=1"></script>
    <script src="${ctx}/static/js/my2.js?v=1"></script>
    <script>
        var _CTX = '${ctx}';
    </script>
</head>
