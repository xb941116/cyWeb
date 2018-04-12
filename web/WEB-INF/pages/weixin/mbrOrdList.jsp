<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">消费列表 <a  href="javascript:void(0);" onclick="javascript:history.go(-1);" class="back"><i class="icon iconfont">&#xe67f;</i></a></h1>
	<script type="text/javascript">
        $(document).ready(function(){
            $("tr:even").css("background","#FFFFFF");
        });
	</script>
</header>
<c:set var="now" value="<%=new java.util.Date()%>"/>
<!-- 订单列表搜索 -->
<section class="mart2b2 clearfix">
   <%-- <form name="theForm" id="theForm" action="${ctx}/custWeiXin/mbrOrdList" method="post">
        <div class="order-ss">
            <ul>
                <li>
                    <label>下单时间：</label>
                    <input type="date" placeholder="请选择开始日期" id="beginTime" name="beginTime"
                           value="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}"/>" class="input-style" style="width:90px;"/>-
                    <input type="date" placeholder="请选择截止日期" id="endTime" name="endTime"
                           value="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}"/>" class="input-style" style="width:90px;"/>
                </li>
                <li>
                   <input type="text" id="ordNo" name="ordNo"
                                                          value="" placeholder="请输入订单号"
                                                          class="input-style"  style="width:90px;">
                    <input type="text" id="gprsNo" name="gprsNo"
                                                         value="" placeholder="请输入模块号"
                                                         class="input-style"  style="width:90px;">
                    <input type="submit" value="搜索" class="input-style ri bgactive">
                    <input type="button" value="清 除" onclick="formReset()" class="input-style ri bgactive">
                </li>
            </ul>
        </div>
    </form>--%>
       <div style="height: 5px;">&nbsp;</div>
    <div class="selec-list clearfix body-bg">
        <table class="selec-list-table">
            <tr>
                <td width="25%" class="tr_bg1">订单号</td>
                <td width="25%" class="tr_bg2">商品名称</td>
                <td width="15%" class="tr_bg2">总价</td>
                <td width="15%" class="tr_bg2">状态</td>
                <td width="20%" class="tr_bg3">日期</td>
            </tr>
            <c:forEach var="ord" items="${pager.recordList}">
                <tr onclick="ordDetail('${ord.ordno}')">
                    <td width="25%">${ord.ordno}</td>
                    <td width="25%">${ord.prodName}</td>
                    <td width="15%">${ord.prodPrice}</td>
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
</section>
<!-- nav end-->
<script>
    var navActive = $('.nav li');
    var _index = 0;
    navActive.on('click', function () {
        _index = $(this).index();
        localStorage.setItem("nav_index", _index);
    })
    navActive.removeClass('active');
    window.onload = function () {
        if (localStorage.nav_index) {
            _index = parseInt(localStorage.nav_index);
            tabNav();
        } else {
            navActive.eq(_index).addClass('active').siblings().removeClass('active');
        }
    }
    function tabNav() {
        navActive.eq(_index).addClass('active').siblings().removeClass('active');
        localStorage.removeItem();
    }
</script>
<!-- end -->
<script type="text/javascript">

    function ordDetail(ordno){
        window.location.href = _CTX + "/custWeiXin/view?ordno=" + ordno;
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