<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
<h1 class="header fc">支付宝配置信息<a href="${ctx}/biz/paySet" class="back"><i class="icon iconfont">&#xe67f;</i></a>
</h1>
</header>
 

<!-- 订单列表搜索 -->
<section class="mart2b2 clearfix">

<div class="para-set clearfix">
      <ul>
         <li>
            <span>合作身份者ID</span>
            <span class="red">*</span>
         </li>
         <li>
           <input type="text" class="style01">
         </li>
         <li>
            <span>商户的私钥</span>
            <span class="red">*</span>
         </li>
         <li>
            <textarea name="" id=""></textarea>
         </li>
         <li>
            <span>支付宝的公用</span>
            <span class="red">*</span>
         </li>
         <li>
            <textarea name="" id=""></textarea>
         </li>
         <li>
             <span>是否启用</span>
             <label for="" class="ri">是<input type="radio" name="celec0"></label>
             <label for="" class="ri">否<input type="radio" name="celec0"></label>
         </li>
      </ul>
  </div>

  <button type="button" class="btn">确认提交</button>
  

</section>
<%@include file="_layout_nav.jsp" %>

<!-- end -->
</body>
</html>