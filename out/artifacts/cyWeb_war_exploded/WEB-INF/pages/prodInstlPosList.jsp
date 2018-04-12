<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">位置上报 <a href="${ctx}/navCtrl/home" class="back"><i class="icon iconfont">&#xe67f;</i></a></h1>
	<script type="text/javascript">
        $(document).ready(function(){
            $("tr:even").css("background","#FFFFFF");
        });
    </script>
</header>

<section class="mart2b2">
    <form name="theForm" id="theForm" action="${ctx}/prod/prodInstlPos" method="post">
        <!-- 商品类型选择 -->
        <section>
            <div class="selec">
                <label for="gprsNo">模块编号：</label>
                <input type="text" name="gprsNo" id="gprsNo" style="width: 25%;" class="input-style" placeholder="" value="${pager.params.gprsNo}">
                <label for="instlaState">设置状态：</label>
                <select name="instlaState" id="instlaState" class="input-style">
                    <option value="">全部</option>
                    <option value="1">已设置</option>
                    <option value="0">未设置</option>
                </select>
                <input type="submit" class="input-style bgactive ri" value="搜索">
            </div>
        </section>
    </form>
    <section>
        <div class="selec-input clearfix">
            <h2>安装地址模板</h2>
            <ul style="overflow-y: auto;max-height: 110px;background-color: #eaeaea;">
                <c:forEach var="prodInstlPosModel" items="${prodInstlPosModelList}">
                    <li>
                        <label for="">
                            <input type="radio" name="prodModId" value="${prodInstlPosModel.id}">${prodInstlPosModel.title}
                        </label>
                        <label>
                            <a href="#" edit="${prodInstlPosModel.id}">修改</a>
                        </label>
                        <label>
                            <a href="#" del="${prodInstlPosModel.id}">删除</a>
                        </label>
                    </li>
                </c:forEach>
                <c:if test="${prodInstlPosModelList!=null && fn:length(prodInstlPosModelList)%2!=0}">
                    <li><label for="">&nbsp;</label></li>
                </c:if>
            </ul>
        </div>
    </section>
    <section>
        <div class="selec-list clearfix">
            <div class="conl" style="background-color: #ffffff;">
                <ul>
                    <li><a href="${ctx}/custWeiXin/getLocation?wayFlag=2" class="input-style" >添加安装地址模板</a></li>
                    <li><a href="#" class="input-style" onclick="setProdMod()">设置模块安装地址</a></li>
                </ul>
            </div>
            <div class="body-bg">
            <div style="height: 5px;background-color: #ffffff;">&nbsp;</div>

                <table class="selec-list-table">
                    <tr>
                        <td width="10%" class="tr_bg1"><label for="chk_all"><input type="checkbox" id="chk_all"></label></td>
                        <td width="20%" class="tr_bg2">编号</td>
                        <td width="20%" class="tr_bg2">状态</td>
                        <td width="25%" class="tr_bg3">购买日期</td>
                    </tr>
                    <c:forEach var="model" items="${pager.recordList}">
                        <tr>
                            <td width="10%"><label for="chk_list"><input type="checkbox" value="${model.gprsNo}" id="chk_list" name="chk_list"></label></td>
                            <td width="20%">${model.gprsNo}</td>
                            <td width="20%">
                                <c:if test="${model.instlaState==0}">未设置</c:if>
                                <c:if test="${model.instlaState==1}">已设置</c:if>
                            </td>
                            <td width="25%"><fmt:formatDate pattern="yyyy-MM-dd" value="${model.crtime}"/></td>
                        </tr>
                    </c:forEach>
                </table>
                <section class="fc">
                    <div class="page clearfix">
                        ${pager.formPageStr}
                    </div>
                </section>
            </div>
        </div>
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
            //window.location.href = _CTX + "/custWeiXin/getLocation?wayFlag=3_" + id;
            window.location.href = _CTX + "/prod/prodInstlPosModel/edit?id=" + id;

        });

        $('a[del]').on('touchstart click', function (e) {
            e.preventDefault();
            var id = $(this).attr('del');
            if (confirm("确定要删除吗？")) {
                $.post(_CTX + "/prod/prodInstlPosModel/remove.json",
                        {id: id},
                        function (data) {
                            if (data) {
                                if (data.success) {
                                    window.location.href = _CTX + "/prod/prodInstlPos";
                                } else {
                                    alert(data.msg);
                                }
                            }
                        }, "json");
            }
        });
        $("#instlaState").val("${pager.params.instlaState}");
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
                $.post(_CTX + "/prod/createInstlPos.json",
                        {ModelId: prodModId, gprsNoList: gprsNoList},
                        function (data) {
                            if (data) {
                                if (data.success) {
                                    window.location.href = _CTX + "/prod/prodInstlPos";
                                } else {
                                    alert(data.msg);
                                }
                            }
                        }, "json");
            } else {
                alert("请选择模块");
            }
        } else {
            alert("请选择位置模板");
        }

    }
</script>
</body>
</html>