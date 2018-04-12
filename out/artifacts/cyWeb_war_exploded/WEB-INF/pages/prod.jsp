<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<header>
    <h1 class="header fc" style="background-color: #53ade7;">管理中心</h1>
</header>


<!-- 订单列表搜索 -->
<section class="mart2b2 clearfix">
    <div class="center-wrapper">
        <div class="cw-top2">
            <ul>
                <li>当前在线设备<br/><br/><span class="online" style="font-size: 1.0rem; ">${online}</span></li>
                <li>当前离线设备<br/><br/><span class="offline" style="font-size: 1.0rem; ">${offline}</span></li>
            </ul>
        </div>

        <!--  list -->

        <div class="prod-admin-lsit clearfix">
            <ul>
                <c:forEach var="sysRes" items="${sysResList}" varStatus="sta">
                    <li
                            <c:if  test="${sta.index==0}"> style='min-height: 7.4rem;background-color: #FEC161' </c:if>
                            <c:if  test="${sta.index==1}"> style='min-height: 7.4rem;background-color: #76CAFE' </c:if>
                            <c:if  test="${sta.index==2}"> style='min-height: 7.4rem;background-color: #76CAFE' </c:if>
                            <c:if  test="${sta.index==3}"> style='min-height: 7.4rem;background-color: #FEC161' </c:if>
                            <c:if  test="${sta.index==4}"> style='min-height: 7.4rem;background-color: #019fa8' </c:if>
                    ><a href="${ctx}${sysRes.uri}" style="margin-top: 2rem;"><i class="icon iconfont">${sysRes.logo}</i>${sysRes.name}</a></li>
                </c:forEach>
                <%--                <li><a href="${ctx}/prod/prodList"><i class="icon iconfont">&#xe60e;</i>产品列表</a></li>
                                <li><a href="${ctx}/biz/bizGprsBind/query"><i class="icon iconfont">&#xe64d;</i>商家绑定</a></li>
                                <li><a href="${ctx}/prod/prodPosRpt"><i class="icon iconfont">&#xe649;</i>位置上报</a></li>
                                <li><a href="${ctx}/prod/prodGprsMod"><i class="icon iconfont">&#xe617;</i>模块查询</a></li>
                                <li><a href="${ctx}/prod/prodGprsArgs"><i class="icon iconfont">&#xe827;</i>GPRS参数设置</a></li>
                                &lt;%&ndash;<li><a href="${ctx}/prod/prodList"><i class="icon iconfont">&#xe72a;</i>对外接口</a></li>&ndash;%&gt;
                                <li><a href="${ctx}/prod/prodSpArgs"><i class="icon iconfont">&#xe632;</i>特定参数设置</a></li>--%>
            </ul>
        </div>
    </div>
</section>

<!-- end -->
<%@include file="_layout_nav.jsp" %>

<script>
    $().ready(function () {
        var offline = $('span.offline');
        $('.offline').on('touchstart click', function (e) {
            var count = offline.text();
            if (count > 0) {
                window.location.href = _CTX + "/prod/offLineList";
            }
        });

        var online = $('span.online');
        $('.online').on('touchstart click', function (e) {
            var count = online.text();
            if (count > 0) {
                window.location.href = _CTX + "/prod/onLineList";
            }
        });

    });
</script>
</body>
</html>