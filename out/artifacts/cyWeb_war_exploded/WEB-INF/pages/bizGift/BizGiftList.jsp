<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">
        活动管理 <a href="${ctx}/navCtrl/home" class="back"><i class="icon iconfont">&#xe67f;</i></a>
    </h1>
</header>

<!-- 商品类型选择 -->

<section class="mart2b2">
    <div class="selec-list clearfix body-bg">
        <div class="conl clearfix">
            <ul class="clearfix" style="text-align: right;">
                <li><a href="${ctx}/biz/bizGift/add" class="input-style">添加活动</a></li>
            </ul>
        </div>
        <div style="height: 5px;">&nbsp;</div>
        <table class="selec-list-table">
            <tr>
                <td width="20%" class="tr_bg1">达标额度</td>
                <td width="20%" class="tr_bg2">赠送积分额度</td>
                <td width="20%" class="tr_bg2">活动排序</td>
                <td width="20%" class="tr_bg2">创建日期</td>
                <td width="20%" class="tr_bg3">是否开启</td>
            </tr>
            <c:forEach items="${pager.recordList}" var="bizGift">
                <tr onclick="loadGift('${bizGift.id}');">
                    <td width="20%">${bizGift.coin}</td>
                    <td width="20%">${bizGift.money}</td>
                    <td width="20%">${bizGift.sort}</td>
                    <td width="20%"><fmt:formatDate pattern="yyyy/MM/dd" value="${bizGift.crtime}"/></td>
                    <td width="20%">${bizGift.stateStr}</td>
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
<script>
    function loadGift(id) {
        location.href = _CTX + "/biz/bizGift/edit?id=" + id;
    }
</script>
</html>