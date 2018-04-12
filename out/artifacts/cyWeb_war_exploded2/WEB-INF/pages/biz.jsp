<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
<h1 class="header fc">商家管理 </h1>
</header>
 

<section>
  <div class="admin-list mart2b2">
     <ul>
         <c:forEach var="sysRes" items="${sysResList}" varStatus="sta">
             <li
                     <c:if  test="${sta.index==0}"> style='min-height: 7.4rem;background-color: #FEC161' </c:if>
                     <c:if  test="${sta.index==1}"> style='min-height: 7.4rem;background-color: #76CAFE' </c:if>
                     <c:if  test="${sta.index==2}"> style='min-height: 7.4rem;background-color: #76CAFE' </c:if>
                     <c:if  test="${sta.index==3}"> style='min-height: 7.4rem;background-color: #FEC161' </c:if>
                     <c:if  test="${sta.index==4}"> style='min-height: 7.4rem;background-color: #FEC161' </c:if>
                     <c:if  test="${sta.index==5}"> style='min-height: 7.4rem;background-color: #76CAFE' </c:if>
                     <c:if  test="${sta.index==6}"> style='min-height: 7.4rem;background-color: #76CAFE' </c:if>
                     <c:if  test="${sta.index==7}"> style='min-height: 7.4rem;background-color: #FEC161' </c:if>
             ><a href="${ctx}${sysRes.uri}" style="margin-top: 2rem;"><i class="icon iconfont">${sysRes.logo}</i>${sysRes.name}</a></li>
         </c:forEach>
<%--         <li>
            <a href="${ctx}/biz/view"><i class="icon iconfont">&#xe681;</i>基本信息</a>
         </li>
         <li>
            <a href="${ctx}/biz/pwdReset"><i class="icon iconfont">&#xe671;</i>密码修改</a>
         </li>
         <li>
            <a href="${ctx}/biz/advise"><i class="icon iconfont">&#xe60b;</i>申诉建议</a>
         </li>
         <li>
            <a href="${ctx}/biz/paySet"><i class="icon iconfont">&#xe631;</i>支付设置</a>
         </li>
         <li>
            <a href="${ctx}/biz/subUserList"><i class="icon iconfont">&#xe667;</i>子账号管理</a>
         </li>
         <li>
            <a href="${ctx}/biz/view"><i class="icon iconfont">&#xe666;</i>会员管理</a>
         </li>--%>
         <%--<li>--%>
            <%--<a href="${ctx}/biz/view"><i class="icon iconfont">&#xe682;</i>营销活动</a>--%>
         <%--</li>--%>
         <c:if test="${isAdmin}">
             <li style='min-height: 7.4rem;background-color: #FEC161'>
                 <a href="${ctx}/biz/queryAd" style="margin-top: 2rem;"><i class="icon iconfont">&#xe67a;</i>广告管理</a>
             </li>
         </c:if>
         <c:if test="${isShowAd}">
             <li  style='min-height: 7.4rem;background-color: #76CAFE'>
                 <a href="${ctx}/biz/adSet" style="margin-top: 2rem;"><i class="icon iconfont">&#xe67a;</i>广告设置</a>
             </li>
         </c:if>

     </ul>
  </div>
</section>


<%@include file="_layout_nav.jsp" %>


</body>
</html>