<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>产品位置</title>
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
    <a href="#">产品位置</a>
    <div class="pull-right"><button type="button" class="btn btn-mystyle btn-sm" onclick="if(window.opener){window.close()}else{history.go(-1)}">返回</button></div>
</div>
<!--页面标头end-->

<!--主体内容start-->
<div class="container-fluid">
    <form method="post" class="ajax_form validate" novalidate="novalidate">
        <table class="table table-bordered  table-header table-hover margin-top">
            <tbody>
            <tr>
                <td align="right">
                    产品编号:
                </td>
                <td>
                    <span >${prodInstlPos.prodNo}</span>
                </td>
            </tr>
            <tr>
                <td align="right" style="width: 150px;">
                    设备编号:
                </td>
                <td>
                    <span class="ri"  style="overflow: hidden; max-width: 60%;"><c:if test="${empty prodInstlPos.gprsNo }">模块移除</c:if>${prodInstlPos.gprsNo}</span>
                </td>
            </tr>
            <tr>
                <td align="right">
                    产品名称:
                </td>
                <td>
                    <span >${prodInstlPos.prodName}</span>
                </td>
            </tr>
            <tr>
                <td align="right">
                    产品类型:
                </td>
                <td>
                    <span >${prodInstlPos.typeStr}</span>
                </td>
            </tr>
            <tr>
                <td align="right">
                    位&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;置:
                </td>
                <td>
                    <span > ${prodInstlPos.provName}${prodInstlPos.cityName}${prodInstlPos.distName}${prodInstlPos.addr}</span>
                </td>
            </tr>
        </table>
        <!--主体内容end-->
    </form>
</div>
<input id="operaction" type="hidden" value="">
<input id="operactionall" type="hidden" value="">

</body></html>