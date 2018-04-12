<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">订单列表 <a href="${ctx}/navCtrl/home" class="back"><i class="icon iconfont">&#xe67f;</i></a></h1>
	
       
    
</header>
<c:set var="now" value="<%=new java.util.Date()%>"/>
<!-- 订单列表搜索 -->
<section class="mart2b2 clearfix">
    <form name="theForm" id="theForm" action="${ctx}/ord/list" method="post">
        <div class="order-ss">
            <ul>
                <li>
                    <label>下单时间：</label>
                    <input type="date" placeholder="请选择开始日期" id="beginTime" name="beginTime"
                           value="${pager.params.beginTime}" class="input-style" style="width:4.2rem"/> -
                    <input type="date" placeholder="请选择截止日期" id="endTime" name="endTime"
                           value="${pager.params.endTime}" class="input-style" style="width:4.2rem"/>
                </li>
                <li>
                    <input type="text" id="ordNo" name="ordNo"
                           value="${pager.params.ordNo}" placeholder="请输入订单号"
                           class="input-style"  style="width:4.2rem;">
                    <input type="text" id="gprsNo" name="gprsNo"
                           value="${pager.params.gprsNo}" placeholder="请输入模块号"
                           class="input-style"  style="width:4.2rem;">
                    <input type="submit"  style="padding: 0.28rem 0.1rem; line-height: 1.42857143;"  value="&nbsp;&nbsp;&nbsp;搜 索&nbsp;&nbsp;&nbsp;" class="input-style ri bgactive">&nbsp;&nbsp;
                    <input type="button" value="&nbsp;&nbsp;&nbsp;清 除&nbsp;&nbsp;&nbsp;" onclick="formReset()" class="input-style ri bgactive">
                </li>
            </ul>
        </div>
    </form>
    <div class="body-bg">

    <div class="selec-list clearfix">
        <div style="height: 5px;">&nbsp;</div>
        <table class="selec-list-table">
            <tr style=" padding-t: 20px;">
                <td width="25%" class="tr_bg1">订单号</td>
                <td width="15%" class="tr_bg2">模块号</td>
                <td width="25%" class="tr_bg2">商品名称</td>
                <td width="15%" class="tr_bg2">订单状态</td>
                <td width="20%" class="tr_bg3">订单日期</td>
            </tr>
            <c:forEach var="ord" items="${pager.recordList}">
                <tr onclick="ordDetail('${ord.ordno}')">
                    <td width="25%">${ord.ordno}</td>
                    <td width="15%">${ord.gprsNo}</td>
                    <td width="25%">${ord.prodName}</td>
                    <td width="15%">${ord.stateStr}</td>
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
        window.location.href = _CTX + "/ord/view?ordno=" + ordno;
    }

    function formReset()
    {
        $('#beginTime').val('');
        $('#endTime').val('');
        $('#ordNo').val('');
    }
    $(document).ready(function(){
            $("tr:even").css("background","#FFFFFF");
        });
</script>
</body>
</html>