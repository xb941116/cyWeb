<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">订单详情<a href="${ctx}/ord/list" class="back"><i class="icon iconfont">&#xe67f;</i></a>
    </h1>
</header>

<!-- 订单列表搜索 -->
<section class="mart2b2 clearfix">
    <div class="para-set clearfix">
        <ul>
            <li><span>模块编号</span>
                <span class="ri">${ord.gprsNo}</span>
            </li>
            <li style="overflow: hidden;line-height: 1rem;">
                <span>产品安装位置</span>
                <span class="ri"  style="overflow: hidden; max-width: 60%;">${pos}</span>
            </li>
            <li>
                <span>订单号</span>
                <span class="ri">${ord.ordno}</span>
            </li>
            <li>
                <span>订单金额</span>
                <span class="ri">${ord.prodPrice}</span>
            </li>
            <li>
                <span>支付人账户</span>
                <span class="ri">${acct}</span>
            </li>
            <li>
                <span>支付方式</span>
                <span class="ri" >${ord.payWayStr}</span>
            </li>
            <li>
                <span>订单状态</span>
                <span class="ri">${ord.stateStr}</span>
            </li>
            <li>
                <span>下单时间</span>
                <span class="ri"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${ord.crtime}"/></span>
            </li>
        </ul>
    </div>
</section>
<!-- end -->
<%@include file="_layout_nav.jsp" %>
</body>
</html>