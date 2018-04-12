<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx"
       value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>
        <!DOCTYPE html>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>设备</title>
    <script type="text/javascript">var webroot="", res="/res/",themespath="/src/admin/view/themes/";</script>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/byc/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/byc/default.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/byc/ui.css">
    <script type="text/javascript" src="${ctx}/static/js/byc/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/byc/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/byc/send.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/byc/header.js"></script>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
</head>
<body>
<!--页面标头start-->
<div class="ctabs">
    <a href="#">设备</a>
    <div class="pull-right"><button type="button" class="btn btn-mystyle btn-sm" onclick="if(window.opener){window.close()}else{history.go(-1)}">返回</button></div>
</div>
<!--页面标头end-->

<!--主体内容start-->
<div class="container-fluid">
    <form method="post" class="ajax_form validate" novalidate="novalidate">
        <table class="table table-bordered  table-header table-hover margin-top">
            <tbody>
            <tr onclick="queryEquiOff('${online}')">
                <td align="right">
                    当前在线设备:
                </td>
                <td>
                    <span class="ri"  style="overflow: hidden; max-width: 60%;">${online}</span>
                </td>
            </tr>
            <tr onclick="queryEquiOn('${offline}')">
                <td align="right" style="width: 150px;">
                    当前离线设备:
                </td>
                <td>
                    <span class="ri"  style="overflow: hidden; max-width: 60%;">${offline}</span>
                </td>
            </tr>
        </table>
        <!--主体内容end-->
    </form>
</div>
<input id="operaction" type="hidden" value="">
<input id="operactionall" type="hidden" value="">
<script>
    var _CTX = '${ctx}';
    function queryEquiOff(count){
        if (count > 0) {
            window.location.href = _CTX + "/prod/offLineList";
        }
    }
    function queryEquiOn(count){
        if (count > 0) {
            window.location.href = _CTX + "/prod/onLineList";
        }
    }
</script>
</body></html>