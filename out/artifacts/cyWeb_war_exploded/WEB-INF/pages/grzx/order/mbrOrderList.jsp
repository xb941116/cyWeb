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
    <title>订单管理</title>
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
    <a href="#">订单管理</a>
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
                        <input type="date" name="beginTime" id="beginTime" class=" form-control pull-left" style="width:220px; margin:0px 6px;"
                               value="${pager.params.beginTime}" style="width:4.2rem;">
                        <input type="date" name="endTime" id="endTime" class=" form-control pull-left"style="width:220px; margin:0px 6px;"
                               value="${pager.params.endTime}" style="width:4.2rem;"><%--
                        <input id="sdate" name="sdate" class="wdate form-control" style="width:220px; margin:0px 6px;" type="text">&nbsp;&nbsp;到--%>
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
                        <td width="100">订单号</td>
                        <td width="100">模块号</td>
                        <td width="100">商品名称</td>
                        <td width="120">订单状态</td>
                        <td width="160">订单日期</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="ord" items="${pager.recordList}">
                        <tr onclick="ordDetail('${ord.ordno}')">
                            <td width="25%">${ord.ordno}</td>
                            <td width="15%">${ord.gprsNo}</td>
                            <td width="25%">${ord.prodName}</td>
                            <td width="15%">${ord.stateStr}</td>
                            <td width="20%"><fmt:formatDate pattern="yyyy/MM/dd" value="${ord.crtime}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <ul class="pagination"><li class="disabled"><span>上一页</span></li>
                    <li class="active"><span>1</span></li><li><a href="javascript:pageForm.curPage.value=2;pageForm.submit();">2</a></li>
                    <li><a href="?page=32">32</a></li>
                    <li><a href="?page=2">下一页</a></li>
                </ul>
                <%--<section class="fc">
                    <div class="page clearfixs">
                        ${pager.formPageStr}
                    </div>
                </section>--%>
            </div>
            <!--主体内容end-->
            <span id="about">


            </span>

        </form>
    </div>
</div>
<input id="operaction" type="hidden" value="">
<input id="operactionall" type="hidden" value="">
<script type="text/javascript">

    function ordDetail(ordno){
        window.location.href = _CTX + "/ord/view?ordno=" + ordno;
    }

    function formReset()
    {
        $('#beginTime').val('');
        $('#endTime').val('');
        $('#ordNo').val('');
    }
    $(document).ready(function(){
        $("tr:even").css("background","#FFFFFF");
    });
</script>
</body></html>