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
    <title>产品列表</title>
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
    <a href="#">产品列表</a>
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
                        <input type="text" name="ordNo" class="form-control pull-left" placeholder="请输入模块编号" style="width:220px; margin:0px 6px;">
                        <button class="btn btn-default" id="search" type="button"><span class="glyphicon glyphicon-search"></span> 快速查询
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
                        <td width="100">模块编号</td>
                        <td width="100">安装位置</td>
                        <td width="100">离线时间</td>
                        <td width="120">离线时长</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="prodOnline" items="${pager.recordList}">
                        <tr>
                            <td width="20%">${prodOnline.gpno}</td>
                            <td width="40%">${prodOnline.provName}${prodOnline.cityName}${prodOnline.distName}-${prodOnline.addr}</td>
                            <td width="20%"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${prodOnline.offLineTime}"/></td>
                            <td width="20%">${prodOnline.offLineTimes}</td>
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
</body></html>