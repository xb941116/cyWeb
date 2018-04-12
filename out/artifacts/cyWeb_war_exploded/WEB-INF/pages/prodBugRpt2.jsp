<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">产品故障状态<a href="javascript:history.go(-1);" class="back"><i class="icon iconfont">&#xe67f;</i></a>
    </h1>
</header>

<!-- 订单列表搜索 -->
<section class="mart2b2 clearfix">
    <div class="para-set clearfix">
        <ul>
            <c:if test="${msgState==true}">
                <c:forEach items="${msgStr}" var="msgS">
                    <li><span>通道${fn:split(msgS,":")[0]}:</span>
                        <span class="ri" <c:if test="${fn:contains(msgS,'有故障')}"> style="color: red;" </c:if> >${fn:split(msgS,":")[1]}</span>
                    </li>
                </c:forEach>
            </c:if>

            <c:if test="${msgState!=true}">
                <li><span>提示:</span>
                    <span class="ri">${msgStr}</span>
                </li>
            </c:if>
        </ul>
    </div>
</section>
<!-- end -->
<%@include file="_layout_nav.jsp" %>
</body>
</html>