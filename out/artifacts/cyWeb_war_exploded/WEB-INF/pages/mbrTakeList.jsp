<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">申请提现 <a href="${ctx}/navCtrl/home" class="back"><i class="icon iconfont">&#xe67f;</i></a></h1>
</header>

<!-- 商品类型选择 -->

<section class="mart2b2">
        <div class="conl clearfix">
            <ul class="clearfix">
                <li><a href="${ctx}/biz/bizTake/list?takeWay=1" <c:if test="${pager.params.takeWay==1}"> class="input-style bgactive" </c:if><c:if
                        test="${pager.params.takeWay!=1}"> class="input-style" </c:if>  >银行卡</a></li>
                <li><a href="${ctx}/biz/bizTake/list?takeWay=2" <c:if test="${pager.params.takeWay==2}"> class="input-style bgactive" </c:if><c:if
                        test="${pager.params.takeWay!=2}"> class="input-style" </c:if> >微信</a></li>
                <li><a href="${ctx}/biz/bizTake/add" class="input-style">手动提现</a></li>
                <li><a href="${ctx}/biz/bizTake/bindWx" class="input-style">自动提现</a></li>
            </ul>
        </div>
        <div style="height: 5px;">&nbsp;</div>
        <div  class="body-bg">
            <div class="selec-list clearfix">
                <div style="height: 5px;">&nbsp;</div>
                <table class="selec-list-table">
                    <tr>
                        <td width="20%" class="tr_bg1">订单号</td>
                        <td width="20%" class="tr_bg2">提现方式</td>
                        <td width="20%" class="tr_bg2">提现金额</td>
                        <td width="20%" class="tr_bg2">日期</td>
                        <td width="20%" class="tr_bg3">状态</td>
                    </tr>
                    <c:forEach items="${pager.recordList}" var="bizTake">
                        <tr onclick="loadToke('${bizTake.id}');">
                            <td width="20%">${bizTake.reqno}</td>
                            <td width="20%">${bizTake.takeWayStr}</td>
                            <td width="20%">${bizTake.amount}</td>
                            <td width="20%"><fmt:formatDate pattern="yyyy/MM/dd" value="${bizTake.crtime}"/></td>
                            <td width="20%">${bizTake.stateStr}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <section class="fc">
                <div class="page clearfix">
                    ${pager.formPageStr}
                </div>
            </section>
        </div>


</section>
<%@include file="_layout_nav.jsp" %>

<!-- end -->
</body>
<script>
    function loadToke(id) {
        location.href = _CTX + "/biz/bizTake/edit?id=" + id;
    }
</script>
</html>