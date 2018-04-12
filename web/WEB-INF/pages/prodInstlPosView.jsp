<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">产品位置<a href="javascript:void(0);" onclick="javascript:history(-1);" class="back"><i class="icon iconfont">&#xe67f;</i></a>
    </h1>
</header>

<!-- 订单列表搜索 -->
<section class="mart2b2 clearfix">
    <div class="para-set clearfix">
        <ul>
            <li>
                <span>产品编号</span>
                <span class="ri">${prodInstlPos.prodNo}</span>
            </li>
            <li>
                <span>设备编号</span>
                <span class="ri"><c:if test="${empty prodInstlPos.gprsNo }">模块移除</c:if>${prodInstlPos.gprsNo}</span>
            </li>
            <li>
                <span>产品名称</span>
                <span class="ri">${prodInstlPos.prodName}</span>
            </li>
            <li>
                <span>产品类型</span>
                <span class="ri">${prodInstlPos.typeStr}</span>
            </li>
            <li>
                <span>位&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;置</span>
                <span class="ri" style="max-width: 70%;line-height: 25px;padding: 12px;">
                    <%--<c:if test="${empty prodCoinRpt.provName&&prodCoinRpt.addr }">未设置位置</c:if>--%>
                    ${prodInstlPos.provName}${prodInstlPos.cityName}${prodInstlPos.distName}${prodInstlPos.addr}
                </span>
            </li>
        </ul>

    </div>

</section>
<!-- end -->
<!-- nav end-->
</body>
</html>