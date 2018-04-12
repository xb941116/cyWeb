<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">订单列表 <a href="${ctx}/navCtrl/home" class="back"><i class="icon iconfont">&#xe67f;</i></a></h1>
	<script type="text/javascript">
        $(document).ready(function(){
            $("tr:even").css("background","#FFFFFF");
        });
    </script>
</header>
<c:set var="now" value="<%=new java.util.Date()%>"/>
<!-- 订单列表搜索 -->
<section class="mart2b2 clearfix">
    <form name="theForm" id="theForm" action="${ctx}/ord/bizlist" method="post">
        <div class="order-ss">
            <ul>
                <li>
                    <label>下单时间：</label>
                    <input type="date" placeholder="请选择开始日期" id="beginTime" name="beginTime"
                           value="${pager.params.beginTime}" class="input-style" style="width:3.6rem"/> -
                    <input type="date" placeholder="请选择截止日期" id="endTime" name="endTime"
                           value="${pager.params.endTime}" class="input-style" style="width:3.6rem"/>
                    <input type="text" id="ordNo" name="ordNo"
                           value="${pager.params.ordNo}" placeholder="请输入订单号"
                           class="input-style"  style="width:3.6rem;">
                </li>
                <li>
                    <label>所属商家：</label>
                    <select name="bizNo" id="bizNo" class="input-style" style="width:3.6rem">
                        <option value="">请选择商家</option>
                        <c:forEach var="biz" items="${bizList}">
                            <option <c:if test="${pager.params.bizNo==biz.bizNo}"> selected="selected" </c:if>  value="${biz.bizNo}">${biz.name}</option>
                        </c:forEach>
                    </select>&nbsp;&nbsp;
                    <input type="text" id="gprsNo" name="gprsNo"
                           value="${pager.params.gprsNo}" placeholder="请输入模块号"
                           class="input-style"  style="width:3.6rem;">
                    <input type="submit" value="搜索" class="input-style ri bgactive">&nbsp;&nbsp;
                </li>
            </ul>
        </div>
    </form>
    <div class="body-bg">

    <div style="height: 5px;">&nbsp;</div>
    <div class="selec-list clearfix">
        <table class="selec-list-table">
            <tr>
                <td width="15%" class="tr_bg1">模块号</td>
                <td width="25%" class="tr_bg2">商品名称</td>
                <td width="40%" class="tr_bg2">商家名称</td>
                <td width="20%" class="tr_bg3">订单日期</td>
            </tr>
            <c:forEach var="ord" items="${pager.recordList}">
                <tr onclick="ordDetail('${ord.ordno}')">
                    <td width="15%">${ord.gprsNo}</td>
                    <td width="25%">${ord.prodName}</td>
                    <td width="40%">${fn:substring(ord.bizName,0,7)}</td>
                    <td width="20%"><fmt:formatDate pattern="yyyy/MM/dd" value="${ord.crtime}"/></td>
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
<script type="text/javascript">

    function ordDetail(ordno){
        window.location.href = _CTX + "/ord/viewBizOrd?ordno=" + ordno;
    }

    function formReset()
    {
        $('#beginTime').val('');
        $('#endTime').val('');
        $('#ordNo').val('');
    }
</script>
</body>
</html>