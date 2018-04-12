<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">产品列表<a href="${ctx}/navCtrl/prod" class="back"><i class="icon iconfont">
        &#xe67f;</i></a></h1>
	<script type="text/javascript">
        $(document).ready(function(){
            $("tr:even").css("background","#FFFFFF");
        });
    </script>
</header>
<!-- 商品类型选择 -->
<section class="mart2b2 clearfix">
    <div class="conl clearfix">
        <ul class="clearfix">
            <li><a href="${ctx}/prod/prodList"  class="input-style bgactive" >产品查询</a></li>
           <%-- <li><a href="${ctx}/prod/prodBugRptQuery"  class="input-style"  >故障查询</a></li>--%>
        </ul>
    </div>
    <form name="theForm" id="theForm" action="${ctx}/prod/prodList" method="post">
        <div class="selec  clearfix">
            <label for="prodSet">属性设置：</label>
            <select name="prodSet" id="prodSet" class="input-style" style="width: 3.8rem">
                <option value="">请选择</option>
                <option value="1">已设置&nbsp;&nbsp;&nbsp;</option>
                <option value="0">未设置&nbsp;&nbsp;&nbsp;</option>
            </select>
            <label for="gprsNo">模块编号：</label>
            <input type="text" name="gprsNo" id="gprsNo" value="${pager.params.gprsNo}" class="input-style" style="width:3.8rem;">
            <br>
            <br>
            <label style="float: left;padding-top: 5px;">模块类型：&nbsp;</label>
            <select name="gprsType" id="gprsType" class="input-style" style="width: 3.8rem;float: left;">
                <option value="">请选择</option>
                <option value="0">电子投币</option>
                <option value="1">洗车机</option>
                <option value="2">动态口令</option>
                <option value="6">充电站</option>
                <option value="3">昌原充电站</option>
            </select>

            <input type="submit" class="input-style bgactive ri" value="搜索" style="margin-left: 115px;float: left;">

        </div>
    </form>
    <section class="body-bg">
        <div class="selec-list clearfix">
            <div style="height: 5px;">&nbsp;</div>
            <table class="selec-list-table">
                <tr>
                    <td width="20%" class="tr_bg1">产品编号</td>
                    <td width="20%" class="tr_bg2">模块编号</td>
                    <td width="20%" class="tr_bg2">商品属性</td>
                    <td width="40%" class="tr_bg3">操作</td>
                </tr>
                <c:forEach var="prod" items="${pager.recordList}">
                    <tr>
                        <td width="15%">${prod.prodNo}</td>
                        <td width="15%">${prod.gprsNo}</td>
                        <td width="14%">${prod.prodSetStr}</td>
                        <td width="56%">
                            <input type="hidden" id="gprsNo2" value="${prod.gprsNo}">
                            <input type="hidden" id="bizNo" value="${prod.bizNo}">
                            <c:if test="${prod.prodSet==1}">
                                <a href="#" class="input-style" qr="${prod.qrGened}" id="qrGened">二维码下载</a>
                                <a href="#" class="input-style" edit="${prod.id}">修改商品属性</a>
                                <%--<c:if test="${ fn:startsWith(prod.gprsNo,'1')||fn:startsWith(prod.gprsNo,'6') }">
                                    <a href="#" class="input-style" stsr="${prod.id}" gprsno="${prod.gprsNo}">状态查询</a>
                                </c:if>--%>
                            </c:if>
                            <c:if test="${prod.prodSet==null || prod.prodSet==0 }">
                                <a href="#" class="input-style" prodSet="${prod.gprsNo}">设置商品属性</a>
                            </c:if>
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

<div class="alert-box clearfix" style="width: 260px;margin: -100px 0 0 -130px;">
    <h2>二维码</h2>
    <dl class="clearfix" style=" padding:0rem 0;margin: 0px auto;">
        <%--<dt>文件:</dt>--%>
        <dd>
            <input type="image" alt="" src="" id="qrFile" name="qrFile" style="width:200px; height: 200px">
            <input type="hidden" id="fileName" name="fileName" value="">
        </dd>

    </dl>
    <p class="clearfix">
        <button class="btnReadSec">下载</button>
        <button class="close">关闭</button>
    </p>
</div>
<script>
    $(document).ready(function () {
        $("#prodSet").val("${pager.params.prodSet}");
        $("#gprsType").val("${pager.params.gprsType}");
        var btnClose = $('.close');
        var alertBox = $('.alert-box');
        btnClose.on('touchstart click', function (e) {
            e.preventDefault();
            alertBox.hide();
        });
        $("a[qr]").click(function () {
            var qr = $(this).attr("qr");
            var gprsNo = $(this).parent().find("#gprsNo2").val();
            var bizNo = $(this).parent().find("#bizNo").val();
            if (qr == 1) {
                $('#qrFile').attr("src", _CTX + "/upload/prod/qr/" + bizNo + "/" + gprsNo + ".png");
                $('#fileName').val(gprsNo);
                alertBox.css('display', 'block');
            } else if (qr == 0) {
                $.post(_CTX + "/prod/qrGen.json",
                        {gprsNo: gprsNo},
                        function (data) {
                            if (data) {
                                if (data.success) {
                                    $('#qrFile').attr("src", _CTX + "/upload/prod/qr/" + bizNo + "/" + gprsNo + ".png");
                                    $('#fileName').val(gprsNo);
                                    alertBox.css('display', 'block');
                                } else {
                                    alert(data.msg);
                                }
                            }
                        }, "json");
            }
        });

        $("a[stsr]").click(function () {
            var prodId = $(this).attr("stsr");
            var gprsNo = $(this).attr("gprsno");
           /* if(gprsNo.substring(0,1)=="6"){
                $.post(_CTX + "/prod/prodBugRpt.json",
                    {prodId: prodId},
                    function (data) {
                        alert(data);
                    }, "json");
            }else {
                setTimeout(loadSt(prodId), 4000);
            }*/
            setTimeout(loadSt(prodId), 1000);
        });

        function loadSt(prodId) {
            window.location.href = _CTX + "/prod/prodBugRpt?prodId=" + prodId;
        }

        $("a[edit]").click(function () {
            var prodId = $(this).attr("edit");
            window.location.href = _CTX + "/prod/prodEdit?prodId=" + prodId;
        });

        $("a[prodSet]").click(function () {
            var gprsNo = $(this).attr("prodSet");
            window.location.href = _CTX + "/prod/prodSalePro?gprsNo=" + gprsNo;
        });

        $("button.btnReadSec").click(function () {
            var gprsNo = $('#fileName').val();
            window.location.href = _CTX + "/prod/qrGenDown?gprsNo=" + gprsNo;
        });

    });
</script>
</body>
</html>