<%--
  Created by IntelliJ IDEA.
  User: xizhuangchui
  Date: 2016/11/15
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">充值记录 <a href="${ctx}/navCtrl/home" class="back"><i class="icon iconfont">&#xe67f;</i></a></h1>
	<script type="text/javascript">
        $(document).ready(function(){
            $("tr:odd").css("background","#FFFFFF");
        });
    </script>
</header>

<!-- 商品类型选择 -->

<section class="mart2b2">
    <div class="selec-list clearfix">
       <%-- <div class="conl clearfix">
            <ul class="clearfix">
                <li><a href="${ctx}/biz/bizTake/list?takeWay=1" <c:if test="${pager.params.takeWay==1}"> class="input-style bgactive" </c:if><c:if
                        test="${pager.params.takeWay!=1}"> class="input-style" </c:if>  >银行卡</a></li>
                <li><a href="${ctx}/biz/bizTake/list?takeWay=2" <c:if test="${pager.params.takeWay==2}"> class="input-style bgactive" </c:if><c:if
                        test="${pager.params.takeWay!=2}"> class="input-style" </c:if> >微信钱包</a></li>
                <li><a href="${ctx}/biz/bizTake/add" class="input-style">申请提现</a></li>
            </ul>
        </div>--%>
           <div style="height: 5px;">&nbsp;</div>
        <table class="selec-list-table">
            <tr>
                <td width="20%" style="font-weight: bold;background-color: #b9b9b9; border:solid 1px #ffffff;line-height:1.0rem;border-radius:15px 0 0 15px;">方式</td>
                <td width="20%" style="font-weight: bold;background-color: #b9b9b9; border:solid 1px #ffffff;line-height:1.0rem;">充值金额</td>
                <td width="20%" style="font-weight: bold;background-color: #b9b9b9; border:solid 1px #ffffff;line-height:1.0rem;">赠送金额</td>
                <td width="20%" style="font-weight: bold;background-color: #b9b9b9; border:solid 1px #ffffff;line-height:1.0rem;">日期</td>
                <td width="20%" style="font-weight: bold;background-color: #b9b9b9; border:solid 1px #ffffff;line-height:1.0rem;border-radius:0 15px 15px 0;">状态</td>
            </tr>
            <c:forEach items="${pager.recordList}" var="mbrRecharge">
                <tr>
                    <td width="20%">${mbrRecharge.payWayStr}</td>
                    <td width="20%">${mbrRecharge.money}</td>
                    <td width="20%">${mbrRecharge.giveMoney}</td>
                    <td width="20%"><fmt:formatDate pattern="yyyy/MM/dd" value="${mbrRecharge.crtime}"/></td>
                    <td width="20%">${mbrRecharge.stateStr}</td>
                </tr>
            </c:forEach>
        </table>
        <section class="fc">
            <div class="page clearfix">
                ${pager.formPageStr}
            </div>
        </section>
    </div>


</section>
<%@include file="../_layout_nav.jsp" %>

<!-- end -->
</body>
</html>