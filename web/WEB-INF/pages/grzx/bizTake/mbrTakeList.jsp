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
    <title>申请提现</title>
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
    <a href="#">申请提现</a>
    <div class="pull-right"><button type="button" class="btn btn-mystyle btn-sm" onclick="if(window.opener){window.close()}else{history.go(-1)}">返回</button></div>
</div>
<!--页面标头end-->

<div class="container-fluid">

    <div class="panel panel-tabs table-margin">
        <ul class="nav nav-tabs">
            <li>
            <li <c:if test="${pager.params.takeWay==1}">class="active" </c:if> <c:if test="${pager.params.takeWay!=1}">class="" </c:if> >
                <a href="${ctx}/biz/bizTake/list?takeWay=1">银行卡</a></li>
            <li <c:if test="${pager.params.takeWay==2}">class="hf_edit active"</c:if> <c:if test="${pager.params.takeWay!=2}">class="hf_edit" </c:if> >
                <a href="${ctx}/biz/bizTake/list?takeWay=2">微信</a></li>
            <li><a href="${ctx}/biz/bizTake/add" class="input-style">手动提现</a></li>
            <li><a href="${ctx}/biz/bizTake/bindWx" class="input-style">自动提现</a></li>
        </ul>
    </div>
    <div class="info-center">

        <form method="post">

            <div class="clearfix"></div>


            <!--主体内容start-->
            <div class="table-margin">

                <table class="table table-bordered  table-header table-hover">
                    <thead>
                    <tr>
                        <td width="100">订单号</td>
                        <td width="100">提现金额</td>
                        <td width="100">日期</td>
                        <td width="120">状态</td>
                        <td width="160">操作</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pager.recordList}" var="bizTake">
                        <tr onclick="loadToke('${bizTake.id}');">
                            <td width="20%">${bizTake.reqno}</td>
                            <td width="20%">${bizTake.amount}</td>
                            <td width="20%"><fmt:formatDate pattern="yyyy/MM/dd" value="${bizTake.crtime}"/></td>
                            <td width="20%">${bizTake.stateStr}</td>
                            <td width="20%"><a href="#" onclick="loadToke('${bizTake.id}');" >查看详情</a></td>
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
<script type="text/javascript">

    function loadToke(id) {
        location.href = _CTX + "/biz/bizTake/editTakePay?id=" + id;
    }
</script>
</body></html>