<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">商家广告管理<a href="${ctx}/navCtrl/biz" class="back"><i class="icon iconfont">
        &#xe67f;</i></a></h1>
	<script type="text/javascript">
        $(document).ready(function(){
            $("tr:even").css("background","#FFFFFF");
        });
    </script>
</header>
<!-- 商品类型选择 -->
<section class="mart2b2 clearfix">
    <form name="theForm" id="theForm" action="${ctx}/biz/queryAd" method="post">
        <div class="selec  clearfix">
            <label for="name">商家名称：</label>
            <input type="text" name="name" id="name" class="input-style" value="${pager.params.likeName}" style="width:150px;">
            <input type="submit" class="input-style bgactive ri" value="搜索">
        </div>
    </form>
    <section class="body-bg">
        <div style="height: 5px;">&nbsp;</div>
        <div class="selec-list clearfix">
            <table class="selec-list-table">
                <tr>
                    <td width="35%" class="tr_bg1">商家名称</td>
                    <td width="15%"class="tr_bg2">商家类型</td>
                    <c:if test="${pager.params.isAdmin!=null&&pager.params.isAdmin==true}">
                        <td width="15%" class="tr_bg2">商家编号</td>
                        <td width="35%" class="tr_bg3">操作</td>
                    </c:if>
                    <c:if test="${pager.params.isAdmin==null||pager.params.isAdmin==false}">
                        <td width="50%"  class="tr_bg3">操作</td>
                    </c:if>
                </tr>
                <c:forEach var="biz" items="${pager.recordList}">
                    <tr>
                        <td width="35%">${biz.name}</td>
                        <td width="15%"><c:if test="${biz.grade==3}">代理商</c:if><c:if test="${biz.grade!=3}">商家</c:if></td>

                        <c:if test="${pager.params.isAdmin!=null&&pager.params.isAdmin==true}">
                            <td width="15%">
                                    ${biz.bizNo}
                            </td>
                            <td width="35%">
                                <a href="#" class="input-style" edit="${biz.bizNo}">广告管理</a>
                                <a href="#" class="input-style" right="${biz.bizNo}">广告屏蔽</a>
                            </td>
                        </c:if>
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
    <!-- end -->
</section>
<%@include file="_layout_nav.jsp" %>

<script src='${ctx}/static/js/fileupload_js/LocalResizeIMG.js'></script>
<script src='${ctx}/static/js/fileupload_js/patch/mobileBUGFix.mini.js'></script>
<script src='${ctx}/static/js/fileupload_js/dist/lrz.bundle.js'></script>
<script>
    $(document).ready(function () {
        $("a[edit]").click(function (e) {
            var bizNo = $(this).attr("edit");
            window.location.href = _CTX + "/biz/bizAd/query?bizNo=" + bizNo;
        });

        $("a[right]").click(function () {
            var bizNo = $(this).attr("right");
            window.location.href = _CTX + "/biz/bizVip/query?bizNo=" + bizNo;
        });

    });

</script>
</body>
</html>