<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">
        自动提现<a href="${ctx}/biz/bizTake/list" class="back"><i class="icon iconfont">&#xe67f;</i></a>
    </h1>
</header>

<!-- 订单列表搜索 -->
<section class="mart2b2 clearfix">
        <div class="para-set clearfix">
            <ul>
                <li id="hd3_2">
                    <span>已绑定微信的昵称</span>
                    <span class="red">*</span>
                </li>
                <li id="hd4_2" >
                    <label  class="ri">${bizTakeWx.nick}</label>
<%--
                    <input id="oldnick" name="oldnick" type="text" value="${bizTakeWx.nick}" readonly="readonly" class="style01">
--%>
                </li>
                <li id="hd5_2" style="display: none;">
                    <span>当前微信的昵称</span>
                    <span class="red">*</span>
                </li>
                <li id="hd6_2" style="display: none;">
                    <input id="nick" name="nick" value="${nickname}"  type="text" class="style01">
                </li>
            </ul>
        </div>
   <%-- <c:if test="${real==1}">
            <c:if test="${state==1}"><button type="button" class="btn" onclick="openBizWx(0)">关闭自动转账</button></c:if>
            <c:if test="${state==0}"><button type="button" class="btn" onclick="openBizWx(1)">开启自动转账</button></c:if>
    </c:if>--%>
        <button type="button" class="btn" onclick="bindBizWx()"><c:if test="${real==1}">解除</c:if>绑定</button>
</section>

<!-- end -->
<%@include file="../_layout_nav.jsp" %>

</body>
<script>
function bindBizWx() {
    if (confirm("您确定要操作吗？")) {
        location.href = _CTX + "/biz/bizTake/createBizTakeWx";
    }
}

function openBizWx(state) {
    if (confirm("您确定要操作吗？")) {
        location.href = _CTX + "/biz/bizTake/updateBizTakeWx?state="+state;
    }
}
</script>
</html>