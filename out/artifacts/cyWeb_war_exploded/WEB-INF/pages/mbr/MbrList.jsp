<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">
        会员管理 <a href="${ctx}/navCtrl/home" class="back"><i class="icon iconfont">&#xe67f;</i></a>
    </h1>
	<script type="text/javascript">
        $(document).ready(function(){
            $("tr:even").css("background","#FFFFFF");
        });
    </script>
</header>


<section class="mart2b2">
    <form name="theForm" id="theForm" action="${ctx}/mbr/query" method="post">
        <div class="selec  clearfix">
            <label for="name">手机号：</label>
            <input type="text" name="mobile" id="mobile" class="input-style" value="${pager.params.mobile}" style="width:25%;">
            <label for="name">姓名：</label>
            <input type="text" name="name" id="name" class="input-style" value="${pager.params.name}" style="width:25%;">
            <input type="submit" class="input-style bgactive ri" value="搜索">
        </div>
    </form>

    <div class="selec-list clearfix body-bg">
        <div style="height: 5px;">&nbsp;</div>
       <%-- <div class="conl clearfix">
            <ul class="clearfix" style="text-align: right;">
                <li><a href="${ctx}/biz/bizGift/add" class="input-style">添加活动</a></li>
            </ul>
        </div>--%>
        <table class="selec-list-table">
            <tr>
                <td width="20%" style="font-weight: bold; background-color: #b9b9b9; border:solid 1px #ffffff;line-height:1.0rem;border-radius:15px 0 0 15px;">手机号</td>
                <td width="20%" style="font-weight: bold;background-color: #b9b9b9; border:solid 1px #ffffff;line-height:1.0rem;">会员昵称</td>
                <td width="20%" style="font-weight: bold;background-color: #b9b9b9; border:solid 1px #ffffff;line-height:1.0rem;">微信昵称</td>
                <td width="20%" style="font-weight: bold;background-color: #b9b9b9; border:solid 1px #ffffff;line-height:1.0rem;">余额</td>
                <td width="20%" style="font-weight: bold;background-color: #b9b9b9; border:solid 1px #ffffff;line-height:1.0rem;border-radius:0 15px 15px 0;">创建时间</td>
            </tr>
            <c:forEach items="${pager.recordList}" var="mbr">
                <tr onclick="loadGift('${mbr.id}');">
                    <td width="20%">${mbr.mobile}</td>
                    <td width="20%">${mbr.name}</td>
                    <td width="20%">${mbr.nick}</td>
                    <td width="20%">${mbr.money}</td>
                    <td width="20%"><fmt:formatDate pattern="yyyy/MM/dd" value="${mbr.crtime}"/></td>
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
        location.href = _CTX + "/mbr/reChargeHis?memberId=" + id;
    }
</script>
</html>