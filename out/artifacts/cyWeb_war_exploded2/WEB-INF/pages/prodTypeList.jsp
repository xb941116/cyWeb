<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">商品类型列表<a href="${ctx}/navCtrl/prod" class="back"><i class="icon iconfont">
        &#xe67f;</i></a></h1>
</header>
<!-- 商品类型选择 -->
<section class="mart2b2 clearfix">
    <form name="theForm" id="theForm" action="${ctx}/dict/add" method="post">
        <div class="selec  clearfix">
            <input type="submit" class="input-style bgactive ri" value="新增" style="margin-left: 115px;float: left;">
        </div>
    </form>
    <section class="body-bg">
        <div class="selec-list clearfix">
            <div style="height: 5px;">&nbsp;</div>
            <table class="selec-list-table">
                <tr>
                    <td width="25%" class="tr_bg1">商品类型名称</td>
                    <td width="25%" class="tr_bg2">商品类型编号</td>
                    <td width="14%" class="tr_bg2">排序编号</td>
                    <td width="36%" class="tr_bg3">操作</td>
                </tr>
                <c:forEach var="baseDict" items="${pager.recordList}">
                    <tr>
                        <td width="15%">${baseDict.val}</td>
                        <td width="15%">${baseDict.code}</td>
                        <td width="14%">${baseDict.seqs}</td>
                        <td width="56%">
                            <a href="#" class="input-style" edit="${baseDict.id}">修改</a>
                            <a href="#" class="input-style" del="${baseDict.id}">删除</a>
                        </td>
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

<script>
    $(document).ready(function () {

        $("a[del]").click(function () {
            var id = $(this).attr("del");
            $.post(_CTX + "/dict/remove.json",
                {id: id},
                function (data) {
                    if (data) {
                        if (data.success) {
                            alert("删除成功")
                            window.location.href = _CTX + "/dict/list";
                        } else {
                            alert(data.msg);
                        }
                    }
                }, "json");

        });


        $("a[edit]").click(function () {
            var id = $(this).attr("edit");
            window.location.href = _CTX + "/dict/edit?id=" + id;
        });

    });
</script>
</body>
</html>