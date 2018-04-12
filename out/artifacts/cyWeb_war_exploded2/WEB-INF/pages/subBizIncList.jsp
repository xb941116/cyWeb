<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">子商家收入统计 <a href="${ctx}/navCtrl/stat" class="back"><i class="icon iconfont">&#xe67f;</i></a></h1>
</header>

<!-- 商品类型选择 -->

<section class="mart2b2">
    <form name="theForm" id="theForm" action="${ctx}/stat/statSubBizInc" method="post">
        <!-- 商品类型选择 -->
        <section>
            <div class="selec">
                <label for="beginDate">查询时间：</label>
                <input type="date" name="beginDate" id="beginDate" class="input-style" placeholder="" value="${beginDate}" style="width:4.2rem;">
                <input type="date" name="endDate" id="endDate" class="input-style" placeholder="" value="${endDate}" style="width:4.2rem;">
                <input type="submit" class="input-style bgactive ri" value="搜索">
            </div>
        </section>
    </form>
    <div class="selec-list clearfix body-bg">
        <div class="conl">
            <ul>
                <li><a href="${ctx}/stat/statSubBizInc?flag=day" flag="day"
                        <c:if test="${flag == 'day'}"> class="input-style bgactive" </c:if><c:if
                        test="${flag != 'day'}"> class="input-style" </c:if>
                        >当日</a></li>
                <li style="width: 1.0rem"></li>
                <li><a href="${ctx}/stat/statSubBizInc?flag=week" flag="week"
                        <c:if test="${flag == 'week'}"> class="input-style bgactive" </c:if><c:if
                        test="${flag != 'week'}"> class="input-style" </c:if>
                        >当周</a></li>
                <li style="width: 1.0rem"></li>
                <li><a href="${ctx}/stat/statSubBizInc?flag=month" flag="month"
                        <c:if test="${flag == 'month'}"> class="input-style bgactive" </c:if><c:if
                        test="${flag != 'month'}"> class="input-style" </c:if>
                        >当月</a></li>
                <li style="width: 1.0rem"></li>
                <li><a href="${ctx}/stat/statSubBizInc?flag=" flag=""  <c:if test="${empty flag}"> class="input-style bgactive" </c:if><c:if
                        test="${not empty flag}"> class="input-style" </c:if>  >全部</a></li>
                <li>&nbsp;
                    <button id="export" class="input-style2" onclick="exportExcel()">报表导出</button>
                </li>
            </ul>
        </div>
        <div style="height: 5px;">&nbsp;</div>
        <table class="selec-list-table">
            <tr>
                <td  width="21%" class="tr_bg1">商家</td>
				 <td width="15%" class="tr_bg2">网络收入</td>
               <!-- <td width="15%" class="tr_bg2">微信收入</td>
				<td width="15%" class="tr_bg2">钱包收入</td>-->
                <td width="15%" class="tr_bg2">投币收入</td>
                <td width="15%" class="tr_bg2">刷卡收入</td>
                <td width="15%" class="tr_bg2">总收入</td>
                <td width="15%" class="tr_bg3">设备在线</td>
            </tr>
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
        <section class="fc">
            <div class="page clearfix">
                ${pager.formPageStr}
            </div>
        </section>
    </div>


</section>
<%@include file="_layout_nav.jsp" %>
<script>

    function exportExcel(){
        var flag = $('a.bgactive').attr('flag');
        var beginDate = $('#beginDate').val();
        var endDate = $('#endDate').val();
        window.location.href = _CTX + "/stat/exportSubInc?flag=" + flag + "&beginDate="+beginDate+"&endDate="+endDate;
    }

</script>
<!-- end -->
</body>
</html>