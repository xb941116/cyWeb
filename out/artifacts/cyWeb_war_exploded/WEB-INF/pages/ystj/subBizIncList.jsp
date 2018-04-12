<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx"
       value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<script>
    var _CTX = '${ctx}';
</script>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>子商家收入统计</title>
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
<body class="">
<!--页面标头start-->
<div class="ctabs">
    <a href="#">子商家收入统计</a>
    <div class="pull-right"><button type="button" class="btn btn-mystyle btn-sm" onclick="if(window.opener){window.close()}else{history.go(-1)}">返回</button></div>
</div>
<!--页面标头end-->

<div class="container-fluid">
    <div class="info-center">
        <!--页面标头end-->
        <form class="ajax_form validate" method="post" action="" novalidate="novalidate">
            <!--操作栏start-->
            <div class="search-box row">
                <div class="col-lg-6">
                    <div class="pull-left ">
                        <input type="date" name="beginDate" id="beginDate" class=" form-control pull-left" style="width:220px; margin:0px 6px;"
                               value="${beginDate}" style="width:4.2rem;">
                        <input type="date" name="endDate" id="endDate" class=" form-control pull-left"style="width:220px; margin:0px 6px;"
                               value="${endDate}" style="width:4.2rem;">
                        <button class="btn btn-default" id="search" type="button"><span class="glyphicon glyphicon-search"></span> 查询
                        </button>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="btn-group pull-right">
                        <a href="javascript:void(0);" onclick="exportExcel()" class="btn btn-default"><span class="glyphicon glyphicon-export"></span>报表导出</a>
                    </div>
                </div>
            </div>
            <!--操作栏end-->
            <div class="clearfix">
            </div>

            <!--主体内容start-->
            <section  class="body-bg">
                <div class="selec-list clearfix">
                    <div style="height: 5px;">&nbsp;</div>
                    <table class="table table-bordered table-margin  table-header table-hover">
                        <thead>
                        <tr>
                            <td  width="21%" class="tr_bg1">商家</td>
                            <td width="15%" class="tr_bg2">网络收入</td>
                            <td width="15%" class="tr_bg2">投币收入</td>
                            <td width="15%" class="tr_bg2">刷卡收入</td>
                            <td width="15%" class="tr_bg2">总收入</td>
                            <td width="15%" class="tr_bg3">设备在线</td>
                        </tr>
                        </thead>
                        <c:forEach items="${pager.recordList}" var="biz">
                            <tr>
                                <td width="21%">${fn:substring(biz.name,0,7)}</td>
                                <td width="15%">${biz.netInc}</td>
                                <!--<td width="15%">${biz.netInc}</td>-->
                                <td width="15%">${biz.coinInc}</td>
                                <td width="15%">${biz.cardInc}</td>
                                <td width="15%">${biz.totalInc}</td>
                                <td width="15%">${biz.online}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <section class="fc">
                    <div class="page clearfix">
                        ${pager.formPageStr}
                    </div>
                </section>
            </section>
            <!--主体内容end-->
        </form>
    </div>
</div>
<input id="operaction" type="hidden" value="">
<input id="operactionall" type="hidden" value="">
<script>
    function exportExcel(){
        var beginDate = $('#beginDate').val();
        var endDate = $('#endDate').val();
        window.location.href = _CTX + "/stat/exportSubInc?&beginDate="+beginDate+"&endDate="+endDate;
    }

</script>
</body></html>