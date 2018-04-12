<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">设置商品属性 <a href="${ctx}/navCtrl/home" class="back"><i class="icon iconfont">&#xe67f;</i></a></h1>
</header>

<section class="mart2b2">
    <form name="theForm" id="theForm" action="${ctx}/prod/prodSalePro" method="post">
        <!-- 商品类型选择 -->
        <section>
            <div class="selec">
                <label for="gprsNo">模块编号：</label>
                <input type="text" name="gprsNo" id="gprsNo" class="input-style"  style="width:3.6rem;" placeholder="" value="">
                <label for="prodSet">属性设置：</label>
                <select name="prodSet" id="prodSet" class="input-style">
                    <option value="">请选择</option>
                    <option value="1">已设置&nbsp;&nbsp;&nbsp;</option>
                    <option value="0">未设置&nbsp;&nbsp;&nbsp;</option>
                </select>
                <input type="submit" class="input-style bgactive ri" value="搜索">
            </div>
        </section>
    </form>
    <section>
        <div class="selec-input clearfix">
            <h2>商品模板</h2>
            <ul style="background-color: #eaeaea;">
                <c:forEach var="prodModel" items="${prodModelList}">
                    <li>
                        <label for="">
                            <input type="radio" name="prodModId" value="${prodModel.id}">${prodModel.prodMdlName}
                        </label>
                        <label>
                            <a href="#" edit="${prodModel.id}">修改</a>
                        </label>
                        <label>
                            <a href="#" del="${prodModel.id}">删除</a>
                        </label>
                    </li>
                </c:forEach>
                <c:if test="${prodModelList!=null && fn:length(prodModelList)%2!=0}">
                    <li><label for="">&nbsp;</label></li>
                </c:if>
            </ul>
        </div>
    </section>
    <section  class="body-bg">
        <div class="selec-list clearfix">
            <div class="conl">
                <ul>
                    <li><a href="${ctx}/prod/prodModel/add" class="input-style bgactive">添加商品模板</a></li>
                    <li><a href="#" class="input-style" onclick="setProdMod()">设置商品属性</a></li>
                </ul>
            </div>
            <div style="height: 5px;">&nbsp;</div>
            <table class="selec-list-table">
                <tr>
                    <td width="10%"  class="tr_bg1"><label for="chk_all"><input type="checkbox" id="chk_all"></label></td>
                    <td width="20%"  class="tr_bg2">编号</td>
                    <td width="25%"  class="tr_bg2">名称</td>
                    <td width="20%"  class="tr_bg2">状态</td>
                    <td width="25%"  class="tr_bg3">购买日期</td>
                </tr>
                <c:forEach var="model" items="${pager.recordList}">
                    <tr>
                        <td width="10%"><label for="chk_list"><input type="checkbox" value="${model.gprsNo}" id="chk_list" name="chk_list"></label></td>
                        <td width="20%">${model.gprsNo}</td>
                        <td width="25%">${model.prodName}</td>
                        <td width="20%">
                            <c:if test="${not empty model.prodSet && '1' eq model.prodSet }">已设置</c:if>
                            <c:if test="${empty model.prodSet || '0' eq model.prodSet }">未设置</c:if>
                        </td>
                        <td width="25%"><fmt:formatDate pattern="yyyy-MM-dd" value="${model.crtime}"/></td>
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
        $("#chk_all").click(function () {
            $("input[name='chk_list']").prop("checked", $(this).prop("checked"));
        });

        $('a[edit]').on('touchstart click', function (e) {
            e.preventDefault();
            var id = $(this).attr('edit');
            window.location.href = _CTX + "/prod/prodModel/edit?id=" + id;
        });

        $('a[del]').on('touchstart click', function (e) {
            e.preventDefault();
            var id = $(this).attr('del');
            if (confirm("确定要删除吗？")) {
                $.post(_CTX + "/prod/prodModel/remove.json",
                        {id: id},
                        function (data) {
                            if (data) {
                                if (data.success) {
                                    window.location.href = _CTX + "/prod/prodSalePro";
                                } else {
                                    alert(data.msg);
                                }
                            }
                        }, "json");
            }
        });
    });

    function setProdMod() {
        var prodModId = $("input[name='prodModId']:checked");
        if (prodModId && prodModId.length > 0) {
            var gprsList = $("input[name='chk_list']:checked");
            if (gprsList && gprsList.length > 0) {
                prodModId = prodModId.val();
                var gprsNoList = "";
                gprsList.each(function () {
                    gprsNoList = gprsNoList + "_" + $(this).val();
                });
                $.post(_CTX + "/prod/create.json",
                        {prodModId: prodModId, gprsNoList: gprsNoList},
                        function (data) {
                            if (data) {
                                if (data.success) {
                                    window.location.href = _CTX + "/prod/prodList";
                                } else {
                                    alert(data.msg);
                                }
                            }
                        }, "json");
            } else {
                alert("请选择模块");
            }
        } else {
            alert("请选择商品模板");
        }

    }
</script>
</body>
</html>