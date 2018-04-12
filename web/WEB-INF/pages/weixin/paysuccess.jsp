<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc" style="background-color:#56A8E0">交易结果
    </h1>
</header>

<!-- 订单列表搜索 -->
<section class="mart2b2 clearfix">
    

    <c:if test="${ord.otpCode!=null}">
        <div style="text-align: center;">
            <span  style="color: red;font-weight:bold;font-size: 40px; ">${ord.otpCode}</span>
            <br>
            <span  style="color: black;font-weight:bold;">&nbsp;&nbsp;&nbsp;请通过按键将此验证码输入，即可使用设备！</span>
        </div>
    </c:if>


    <c:if test="${bizVip==null||bizVip.state==0}">
        <div>
            <c:forEach items="${bizAdList}" var="bizAd">
                <span>
                <a href="${bizAd.srcUrl}"><img name="adImg" style="width: 100%;" src="${bizAd.imgUrl}"></a>
                </span>
            </c:forEach>
        </div>
    </c:if>
    <div class="para-set clearfix">
        <ul>
            <c:if test="${ord.otpCode==null}">
                <li>
                    <span>订单号</span>
                    <span class="ri">${ord.ordno}</span>
                </li>
                <li>
                    <span>支付方式</span>
                    <span class="ri" >${ord.payWayStr}</span>
                </li>
            </c:if>
            <li>
                <span>订单金额</span>
                <span class="ri">${ord.prodPrice}</span>
            </li>
            <%----%>
            <%-- <li>
                 <span>订单状态</span>
                 <span class="ri">${ord.stateStr}</span>
             </li>
             --%>
            <c:if test="${ord.otpCode!=null}">
                <li style="text-align: center">
                    <span style="text-align: center;color: #000000;font-size:18px; font-weight: bold;">设备验证码</span>
                </li>
            </c:if>
        </ul>
    </div>
    <button type="button" id="btn"  onclick="javascript:window.location.href='${ctx}/custWeiXin/userInfo?bizNo=${ord.bizNo}'"  class="btn" style="background-color:#56A8E0;margin-top:10px">完&nbsp;成</button>
    <div style="margin-left: 10px;font-size:12px；margin-top:10px"><span style="color:red">*</span>温馨提示:</div>
    <div style="margin-left: 10px;font-size: 10px;font-family: 新宋体">
        若支付成功未充电，系统将在24小时内退费，详情请查看消费记录并认真查看支付流程。
    </div>
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
    $(document).ready(function () {
        var divWidth = $("img[name=adImg]").width();
        $("img[name=adImg]").css({'height':divWidth/3});
    });
</script>
</body>
</html>