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
    <title>订单列表</title>
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
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
</head>
<body>
<c:set var="now" value="<%=new java.util.Date()%>"/>
<!--页面标头start-->
<div class="ctabs">
    <a href="#">订单列表</a>
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
                        <input type="text" name="ordNo" class="form-control pull-left" placeholder="请输入订单号" style="width:220px; margin:0px 6px;">
                        <input type="text" name="gprsNo" class="form-control pull-left" placeholder="请输入模块号" style="width:220px; margin:0px 6px;">
                        <span>所属商家：</span>
                        <div class="btn-group">
                            <select id="bind" name="bind" style="width:145px" class="form-control pull-left" data-original-title="" title="">
                                <option value="">请选择商家</option>
                                <c:forEach var="biz" items="${bizList}">
                                    <option <c:if test="${pager.params.bizNo==biz.bizNo}"> selected="selected" </c:if>  value="${biz.bizNo}">${biz.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <input type="date" name="beginTime" id="beginTime" class=" form-control pull-left" style="width:220px; margin:0px 6px;"
                               value="${pager.params.beginTime}" style="width:4.2rem;">
                        <input type="date" name="endTime" id="endTime" class=" form-control pull-left"style="width:220px; margin:0px 6px;"
                               value="${pager.params.endTime}" style="width:4.2rem;">
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
                        <td width="100">模块号</td>
                        <td width="100">商品名称</td>
                        <td width="100">商家名称</td>
                        <td width="160">订单日期</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="ord" items="${pager.recordList}">
                        <tr onclick="ordDetail('${ord.ordno}')">
                            <td width="15%">${ord.gprsNo}</td>
                            <td width="25%">${ord.prodName}</td>
                            <td width="40%">${fn:substring(ord.bizName,0,7)}</td>
                            <td width="20%"><fmt:formatDate pattern="yyyy/MM/dd" value="${ord.crtime}"/></td>
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

    function ordDetail(ordno){
        window.location.href = _CTX + "/ord/viewBizOrd?ordno=" + ordno;
    }

    function formReset()
    {
        $('#beginTime').val('');
        $('#endTime').val('');
        $('#ordNo').val('');
    }
</script>
</body></html>