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

    <title>统计报表</title>
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
    <a href="#">统计报表</a>
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
                        <input type="hidden" name="devType" id="dev_type" value="">
                        <span>商品：</span>
                        <div class="btn-group">
                            <select id="devType" name="devType" style="width:145px" class="form-control pull-left" data-original-title="" title="">
                                <option value=""  <c:if test="${empty  devType}">selected</c:if>>全部商品</option>
                                <c:forEach var="dict" items="${dictList}">
                                    <option value="${dict.code}" <c:if test="${dict.code eq devType}">selected="selected"</c:if>>${dict.val}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <input type="date" name="beginDate" id="beginDate" class=" form-control pull-left" style="width:220px; margin:0px 6px;"
                               value="${pager.params.beginDate}" style="width:4.2rem;">
                        <input type="date" name="endDate" id="endDate" class=" form-control pull-left"style="width:220px; margin:0px 6px;"
                               value="${pager.params.endDate}" style="width:4.2rem;">
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
                            <td width="16%">产品编号</td>
                            <td width="16%">模块编号</td>
                            <td width="16%">微信收入</td>
                            <td width="16%">钱包收入</td>
                            <td width="16%">刷卡收入</td>
                            <td width="16%">投币收入</td>
                        </tr>
                        </thead>
                        <c:forEach items="${pager.recordList}" var="prod">
                        <tr onclick="getAddr('${prod.prodNo}')">
                            <td width="16%" id="prod_online">${prod.prodNo}</td>
                            <td width="16%" id="online"><c:if test="${empty prod.gprsNo }">模块移除</c:if>${prod.gprsNo}</td>
                            <td width="16%" id="wx">${prod.wxInc}</td>
                            <td width="16%" id="wlt">${prod.wltInc}</td>
                            <td width="16%" id="card">${prod.cardInc}</td>
                            <td width="16%" id="coin">${prod.coinInc}</td>
                        </tr>
                        </c:forEach>
                        <tbody>
                            <td width="16%">合计：</td>
                            <td width="16%"></td>
                            <td width="16%">${totalWxInc}</td>
                            <td width="16%">${totalWltInc}</td>
                            <td width="16%">${totalCardInc}</td>
                            <td width="16%">${totalCoinInc}</td>
                        </tbody>
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

    $().ready(function () {
        $("#devType").on('select', function () {

        });
    });

    function exportExcel(){
        var devType = $('#devType').val();
        var flag = $('a.bgactive').attr('flag');
        if (flag==undefined){
            flag="";
        }
        var beginDate = $('#beginDate').val();
        var endDate = $('#endDate').val();
        window.location.href = _CTX + "/stat/exportXsl?flag=" + flag + "&devType=" + devType + "&beginDate="+beginDate+"&endDate="+endDate;
    }


    function queryProd() {
        var beginDate=$("#beginDate").val();
        var endDate=$("#endDate").val();
        if(beginDate!=''&&endDate!=''&&beginDate>endDate){
            alert("结束时间应该大于开始时间！");
            return false;
        }
        var devType = $('#devType').val();
        $('#dev_type').val(devType);
        $('#theForm').submit();
    }

    function getAddr(prodNo) {
        location.href=_CTX+"/stat/loadProdInstlPosByProdNo?prodNo="+prodNo;
    }

</script>
</body></html>