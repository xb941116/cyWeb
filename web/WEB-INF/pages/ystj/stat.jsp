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
    <title>盈收统计</title>
    <script type="text/javascript">var webroot="", res="/res/",themespath="/src/admin/view/themes/";</script>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/byc/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/byc/default.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/byc/ui.css">
    <link href="${ctx}/static/css/byc/WdatePicker.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${ctx}/static/js/byc/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/byc/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/byc/send.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/byc/header.js"></script>
    <script src="https://code.highcharts.com/highcharts.js"></script>
    <script src="https://code.highcharts.com/modules/series-label.js"></script>
    <script src="https://code.highcharts.com/modules/exporting.js"></script>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
</head>
<body>
<!--页面标头start-->
<div class="ctabs">
    <a href="#">盈收统计</a>
    <div class="pull-right"><button type="button" class="btn btn-mystyle btn-sm" onclick="if(window.opener){window.close()}else{history.go(-1)}">返回</button></div>
</div>
<!--页面标头end-->

<div class="container-fluid">
    <div class="panel panel-tabs table-margin">
        <ul class="nav nav-tabs">
            <li <c:if test="${pagination==false}">class="active" </c:if> >
                <a href="#">盈收比例</a></li>

        </ul>

        <div class="info-center">

            <form method="post">

                <div class="clearfix"></div>


                <!--主体内容start-->
                <div class="table-margin">
                    <div class="content main-tab-0" <c:if test="${pagination}">style="display:none"</c:if> >
                        <!-- 图表 -->
                        <div id="main" style="width:1200px; height:350px; margin:1rem auto 0 auto;">
                        </div>

                        <script>
                            var chart = Highcharts.chart('main', {
                                chart: {
                                    type: 'line'
                                },
                                title: {
                                    text: '最近30天收入'
                                },
                                subtitle: {
                                    text: ''
                                },
                                xAxis: {
                                    categories: ['起始','一天', '二天', '三天', '四天', '五天', '六天', '七天', '八天', '九天', '十天', '十一天', '十二天', '十三天', '十四天', '十五天', '十六天', '十七天', '十八天', '十九天', '二十天', '二十一天', '二十二天', '二十三天', '二十四天', '二十五天', '二十六天', '二十七天', '二十八天', '二十九天', '今天']
                                },
                                yAxis: {
                                    title: {
                                        text: '金额 (元)'
                                    }
                                },
                                plotOptions: {
                                    line: {
                                        dataLabels: {
                                            enabled: true          // 开启数据标签
                                        },
                                        enableMouseTracking: false // 关闭鼠标跟踪，对应的提示框、点击事件会失效
                                    }
                                },
                                series: [{
                                    name: '钱包',
                                    data: [0.00<c:forEach var="list" items="${list}">,${list.prodPrice}</c:forEach>]
                                }, {
                                    name: '微信',
                                    data: [0.00<c:forEach var="wxlist" items="${wxlist}">,${wxlist.prodPrice}</c:forEach>]
                                }, {
                                    name: '刷卡',
                                    data: [0.00<c:forEach var="sklist" items="${sklist}">,${sklist.money}</c:forEach>]
                                }, {
                                    name: '投币',
                                    data: [0.00<c:forEach var="tblist" items="${tblist}">,${tblist.money}</c:forEach>]
                                }]
                            });
                        </script>
                        <!-- 图表 end -->
                    </div>
                </div>
                <!--主体内容end-->

            </form>
        </div>
    </div>
</div>
<input id="operaction" type="hidden" value="">
<input id="operactionall" type="hidden" value="">
<script type="text/javascript">
    function getAddr(prodNo) {
        location.href=_CTX+"/stat/loadProdInstlPosByProdNo?prodNo="+prodNo;
    }
</script>
</body></html>