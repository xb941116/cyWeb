<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<script src="${ctx}/static/js/echarts.common.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/series-label.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<body>
<header>
    <h1 class="header fc">营收统计</h1>
</header>
<section class="mart2b2 body-bg">
    <!-- 营收统计 -->
    <section>
        <div class="selec-list clearfix" style="padding-bottom: 0px;">
            <div class="conl">
                <ul>
                    <li><a href="#" style="font-size: 0.6rem;" class="input-style  <c:if test="${pagination==false}">bgactive</c:if>" >营收比例</a></li>
                    <li><a href="#"  style="font-size: 0.6rem;" class="input-style <c:if test="${pagination==true}">bgactive</c:if>">营收排行榜</a></li>
                    <li><a href="${ctx}/stat/statSubBizInc" style="font-size: 0.6rem;" class="input-style">子商家营收统计</a></li>
                </ul>
            </div>
        </div>
        	
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
        <div class="content main-tab-1"  <c:if test="${!pagination}">style="display:none"</c:if> >
            <div style="height: 5px;">&nbsp;</div>
            <table class="selec-list-table body-bg" >
                <tr>
                    <td width="20%" class="tr_bg1">产品编号</td>
                    <td width="20%" class="tr_bg2">设备编号</td>
                    <td width="30%" class="tr_bg2">位置</td>
                    <td width="30%" class="tr_bg3">收入</td>
                </tr>
                <c:forEach var="prodCoinRpt" items="${pager.recordList}">
                    <tr onclick="getAddr('${prodCoinRpt.prodNo}')">
                        <td width="20%"> ${prodCoinRpt.prodNo}</td>
                        <td width="20%"><c:if test="${empty prodCoinRpt.gprsNo }">模块移除</c:if> ${prodCoinRpt.gprsNo}</td>
                        <td width="30%" style="line-height: 1.0rem;padding: 10px 0px 10px 0px;">${prodCoinRpt.provName}${prodCoinRpt.cityName}${prodCoinRpt.distName}${prodCoinRpt.addr}</td>
                        <td width="30%"><c:if test="${prodCoinRpt.money==null}">0</c:if>${prodCoinRpt.money}元</td>
                    </tr>
                </c:forEach>
            </table>
            <section class="fc">
                <div class="page clearfix">
                    ${pager.formPageStr}
                </div>
            </section>
        </div>
        <!-- end -->
    </section>
    <%@include file="_layout_nav.jsp" %>
</section>
</body>
<script>
    function getAddr(prodNo) {
        location.href=_CTX+"/stat/loadProdInstlPosByProdNo?prodNo="+prodNo;
    }
</script>
</html>