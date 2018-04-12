<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/7/30
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../_layout_header.jsp" %>
<body style="background-color: #ffffff;">
<header>
    <h1 class="header fc">提示信息<a href="javascript:void(0);" onclick="javascript:history.go(-1);" class="back"><i class="icon iconfont">&#xe67f;</i></a>
    </h1>
</header>

<!-- 订单列表搜索 -->
<section class="mart2b2 clearfix" >
    <div style="text-align: center;">
        <span><img src="${ctx}/static/img/weixin/msg.png"></span>
        <br>
        <span  style="color: #03b6a3; font-size: 0.8rem;">${message}</span>
    </div>
    <%--<button type="button" id="btn" style="width: 20%;margin-top: 20%;" onclick="javascript:history.go(-1);"  class="btn" >返&nbsp;回</button>--%>
</section>
<!-- end -->
<!-- nav end-->
</body>
</html>