<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">商家基本信息<a href="${ctx}/navCtrl/biz" class="back"><i class="icon iconfont">&#xe67f;</i></a>
        <a href="${ctx}/biz/edit" class="editors">编辑</a>
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
            <li>
                <span>商家名称</span>
                <span class="ri">${biz.name}</span>
            </li>
            <li>
                <span>网址</span>
                <span class="ri">${biz.webSite}</span>
            </li>
            <li>
                <span>联系人手机</span>
                <span class="ri">${biz.mobile}</span>
            </li>
            <li>
                <span>邮箱</span>
                <span class="ri">${biz.email}</span>
            </li>
            <li>
                <span>地址</span>
                <span class="ri">${biz.addr}</span>
            </li>
            <li><span>商家LOGO</span>
                <img src="${ctx}/${biz.logo}"  width="50px" height="50px" class="ri">
            </li>
            <li><span>服务电话</span>
                <span class="ri">${biz.tel}</span>
            </li>
            <li><span>AIP秘钥</span>
                <span class="ri">${biz.bizKey}</span>
            </li>
        </ul>
    </div>

</section>

<!-- end -->
<%@include file="_layout_nav.jsp" %>

</body>
</html>