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
    <title>盈收排行榜</title>
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
    <a href="#">盈收排行榜</a>
    <div class="pull-right"><button type="button" class="btn btn-mystyle btn-sm" onclick="if(window.opener){window.close()}else{history.go(-1)}">返回</button></div>
</div>
<!--页面标头end-->

<div class="container-fluid">
    <div class="info-center">

        <form method="post">
            <!--操作栏start-->
            <div class="search-box row">
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
                        <td width="20%" class="tr_bg1">产品编号</td>
                        <td width="20%" class="tr_bg2">设备编号</td>
                        <td width="30%" class="tr_bg2">位置</td>
                        <td width="30%" class="tr_bg3">收入</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="prodCoinRpt" items="${pager.recordList}">
                        <tr onclick="getAddr('${prodCoinRpt.prodNo}')">
                            <td width="20%"> ${prodCoinRpt.prodNo}</td>
                            <td width="20%"><c:if test="${empty prodCoinRpt.gprsNo }">模块移除</c:if> ${prodCoinRpt.gprsNo}</td>
                            <td width="30%" style="line-height: 1.0rem;padding: 10px 0px 10px 0px;">${prodCoinRpt.provName}${prodCoinRpt.cityName}${prodCoinRpt.distName}${prodCoinRpt.addr}</td>
                            <td width="30%"><c:if test="${prodCoinRpt.money==null}">0</c:if>${prodCoinRpt.money}元</td>
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
    function getAddr(prodNo) {
        location.href=_CTX+"/stat/loadProdInstlPosByProdNo?prodNo="+prodNo;
    }
</script>
</body></html>