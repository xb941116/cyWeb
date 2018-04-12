<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
<h1 class="header fc">支付配置<a href="${ctx}/navCtrl/biz" class="back"><i class="icon iconfont">&#xe67f;</i></a>
</h1>
</header>
 <!-- 订单列表搜索 -->
<section class="mart2b2 clearfix">
<!--      <div class="center-wrapper">
          <div class="cw-top">
              <i><img src="img/bg.jpg" alt=""></i>
              <h2>wedngkj</h2>
              <p>2伟鼎科技</p>
          </div>
     </div> -->

<div class="para-set clearfix">
      <ul>
        <%-- <li>
            <a href="${ctx}/biz/payAlipay">
                 <span>支付宝支付配置</span>
                  <span class="ri"><img src="${ctx}/static/img/right.png" alt="" ></span>
            </a>
         </li> --%>
         <li>
            <a href="${ctx}/biz/payWx">
                 <span>微信支付配置</span>
                 <span class="ri"><img src="${ctx}/static/img/right.png" alt=""></span>
            </a>
         </li>
         <%--<li>
            <a href="${ctx}/biz/payWxFocus">
                 <span>微信强制关注配置</span>
                 <span class="ri"><img src="${ctx}/static/img/right.png" alt=""></span>
            </a>
         </li>--%>
      </ul>
  </div>

</section>
<%@include file="_layout_nav.jsp" %>

<!-- end -->
</body>
</html>