<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">
        优惠活动<a  href="javascript:void(0);" onclick="javascript:history.go(-1);" class="back"><i class="icon iconfont">&#xe67f;</i></a>
    </h1>
</header>

<!-- 优惠活动 -->
<section class="mart2b2 clearfix">
        <div class="para-set clearfix body-bg">
            <ul>
               <%-- <li style="text-align: center;">
                    <span style="text-align: center;font-weight: bold;" >优惠活动</span>
                </li>--%>
                <c:forEach items="${bizGiftList}" var="gift">
                    <li>
                        <span>
                            &nbsp;&nbsp;充值<span style="color:#58b431; ">${gift.coin}</span>元送<span style="color:#58b431; ">${gift.money}</span>元
                            (<c:if test="${gift.totalMbr==0}">不限参与次数</c:if><c:if test="${gift.totalMbr!=0}">限参与${gift.totalMbr}次</c:if>)
                        </span>
                    </li>
                </c:forEach>
            </ul>
        </div>
</section>

<!-- end -->

</body>
</html>