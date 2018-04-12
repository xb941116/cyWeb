<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">产品列表<a href="javascript:history.go(-1);" class="back"><i class="icon iconfont">
        &#xe67f;</i></a></h1>
	<script type="text/javascript">
        $(document).ready(function(){
            $("tr:even").css("background","#FFFFFF");
        });
    </script>
</header>
<!-- 商品类型选择 -->
<section class="mart2b2 clearfix">
    <form name="theForm" id="theForm" action="${ctx}/prod/onLineList" method="post">
        <div class="selec  clearfix">
            <label for="gprsNo">模块编号：</label>
            <input type="text" name="gprsNo" id="gprsNo" class="input-style" style="width:150px;">
            <input type="submit" class="input-style bgactive ri" value="搜索">
        </div>
    </form>
    <div class="body-bg">
        <div style="height: 5px;">&nbsp;</div>
        <section>
            <div class="selec-list clearfix">
                <table class="selec-list-table">
                    <tr>
                        <td width="20%" class="tr_bg1">模块编号</td>
                        <td width="40%" class="tr_bg2">安装位置</td>
                        <td width="20%" class="tr_bg2">上线时间</td>
                        <td width="20%" class="tr_bg3">在线时长</td>
                    </tr>
                    <c:forEach var="prodOnline" items="${pager.recordList}">
                        <tr>
                            <td width="20%">${prodOnline.gpno}</td>
                            <td width="40%">${prodOnline.provName}${prodOnline.cityName}${prodOnline.distName}-${prodOnline.addr}</td>
                            <td width="20%"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${prodOnline.curOnlineTime}"/></td>
                            <td width="20%">${prodOnline.onLineTimes}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </section>
        <section class="fc">
            <div class="page clearfix">
                ${pager.formPageStr}
            </div>
        </section>
    </div>
    <!-- end -->
</section>
<%@include file="_layout_nav.jsp" %>
</body>
</html>