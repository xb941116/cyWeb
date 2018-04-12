<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">参数设置<a href="${ctx}/navCtrl/prod" class="back"><i class="icon iconfont">&#xe67f;</i></a></h1>
</header>


<!-- 商品类型选择 -->
<section>
    <form name="theForm" id="theForm" action="${ctx}/prod/prodArgsList" method="post">
        <div class="selec mart2b2 clearfix">
            <label for="gprsNo">模块编号：</label>
            <input type="text" id="gprsNo" name="gprsNo" placeholder="模块编号" class="input-style">
            <input type="submit" class="input-style bgactive ri" value="搜索">
        </div>
    </form>
</section>
<section>
    <div class="selec-list clearfix">
        <div class="conl" style="background-color: #ffffff;">
            <ul>
                <li>
                    <label>是否应用全部设备</label>
                    <select name="all" id="all" class="input-style" style="width: 3rem">
                        <option value="0">否</option>
                        <option value="1">是</option>
                    </select>
                </li>
                <li style="width: 1.0rem"></li>
                <li><a href="#" class="input-style" onclick="setProdModArgs()">模块参数设置</a></li>
                <li><a href="#" class="input-style" onclick="setProdArgs()">产品参数设置</a></li>
            </ul>
        </div>
        <div class="body-bg">
        <div style="height: 5px;background-color: #ffffff;">&nbsp;</div>

            <table class="selec-list-table">
                <tr>
                    <td width="10%" class="tr_bg1"><label for="chk_all"><input type="checkbox" id="chk_all"></label></td>
                    <td width="20%" class="tr_bg2">模块编号</td>
                    <td width="30%" class="tr_bg2">产品名称</td>
                    <td width="20%" class="tr_bg2">状态</td>
                    <td width="20%" class="tr_bg3">创建日期</td>
                </tr>
                <c:forEach var="prod" items="${pager.recordList}">
                    <tr>
                        <td width="10%"><label for="chk_list"><input type="checkbox" value="${prod.gprsNo}" id="chk_list" name="chk_list"></label></td>
                        <td width="20%">${prod.gprsNo}</td>
                        <td width="30%">${prod.prodName}</td>
                        <td width="20%">${prod.stateStr}</td>
                        <td width="20%"><fmt:formatDate pattern="yyyy-MM-dd" value="${prod.crtime}"/></td>
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
<%@include file="_layout_nav.jsp" %>
<script>
    $(document).ready(function () {

        $("#chk_all").click(function () {
            $("input[name='chk_list']").prop("checked", $(this).prop("checked"));
        });

        $('#all').on('change', function (e) {
            var val = $(this).val();
            if (val == 1) {
                $("input[type='checkbox']").attr("disabled", "disabled");
            } else {
                $("input[type='checkbox']").removeAttr("disabled");
            }
        });
    });

    function setProdModArgs() {
        var all = $('#all').val();
        var gprsList = $("input[name='chk_list']:checked");
        var gprsNoList = "";
        if (all == 0) {
            if (gprsList.length == 0) {
                alert("请选择产品");
                return;
            }
            gprsList.each(function () {
                gprsNoList = gprsNoList + "_" + $(this).val();
            });
        }
        window.location.href = _CTX + "/prod/prodGprsArgsSet?all="+all+"&gprsNoList="+gprsNoList;
    }

    function setProdArgs() {
        var all = $('#all').val();
        var gprsList = $("input[name='chk_list']:checked");
        var gprsNoList = "";
        if (all == 0) {
            if (gprsList.length == 0) {
                alert("请选择产品");
                return;
            }
            gprsList.each(function () {
                gprsNoList = gprsNoList + "_" + $(this).val();
            });
        }
        window.location.href = _CTX + "/prod/prodSpArgsSet?all="+all+"&gprsNoList="+gprsNoList;
    }
</script>
</body>
</html>