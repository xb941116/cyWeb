<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">订单详情<a href="javascript:void(0);" onclick="javascript:history(-1);" class="back"><i class="icon iconfont">&#xe67f;</i></a>
    </h1>
</header>

<!-- 订单列表搜索 -->
<section class="mart2b2 clearfix">
    <div class="para-set clearfix">
        <ul>
            <li>
                <span>商品名称</span>
                <span class="ri">${ord.prodName}</span>
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
                <span>支付人</span>
                <span class="ri">${acct}</span>
            </li>
            <li>
                <span>支付方式</span>
                <span class="ri" >${ord.payWayStr}</span>
            </li>
            <li>
                <span>订单状态</span>
                <span class="ri">${ord.stateStr}<c:if test="${ord.state==1||ord.state==3}">(次日退款)</c:if></span>
            </li>
            <li>
                <span>设备响应</span>
                <span class="ri"><c:if test="${ord.succ==null||ord.succ==0}">失败</c:if><c:if test="${ord.succ==1}">成功</c:if></span>
            </li>
            <li>
                <span>下单时间</span>
                <span class="ri"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${ord.crtime}"/></span>
            </li>
            <c:if test="${ord.otpCode!=null}">
                <li style="text-align: center">
                    <span style="text-align: center;color: #000000;font-size:18px; font-weight: bold;">设备验证码</span>
                </li>
            </c:if>
        </ul>

    </div>
    <c:if test="${ord.otpCode!=null}">
        <div style="text-align: center;">
            <span  style="color: red;font-weight:bold;font-size: 40px; ">${ord.otpCode}</span>
            <br>
            <span  style="color: black;font-weight:bold;">&nbsp;&nbsp;&nbsp;请通过按键将此验证码输入，即可使用设备！</span>
        </div>
    </c:if>
</section>
<!-- end -->
<!-- nav end-->
<script>
    var navActive = $('.nav li');
    var _index = 0;
    navActive.on('click', function () {
        _index = $(this).index();
        localStorage.setItem("nav_index", _index);
    })
    navActive.removeClass('active');
    window.onload = function () {
        if (localStorage.nav_index) {
            _index = parseInt(localStorage.nav_index);
            tabNav();
        } else {
            navActive.eq(_index).addClass('active').siblings().removeClass('active');
        }
    }
    function tabNav() {
        navActive.eq(_index).addClass('active').siblings().removeClass('active');
        localStorage.removeItem();
    }
</script>
</body>
</html>