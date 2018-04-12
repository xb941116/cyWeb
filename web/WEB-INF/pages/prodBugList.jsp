<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">故障列表<a href="${ctx}/navCtrl/prod" class="back"><i class="icon iconfont">
        &#xe67f;</i></a></h1>
</header>
<!-- 商品类型选择 -->
<section class="mart2b2 clearfix">
    <div class="conl clearfix">
        <ul class="clearfix">
            <li><a href="${ctx}/prod/prodList"  class="input-style" >产品查询</a></li>
            <li><a href="${ctx}/prod/prodBugRptQuery"  class="input-style bgactive"  >故障查询</a></li>
        </ul>
    </div>
    <form name="theForm" id="theForm" action="${ctx}/prod/prodBugRptQuery" method="post">
        <div class="selec  clearfix">
            <div style="text-align: center;width: 100%;float: left;">
            <label style="float: left;line-height: 1.2rem;margin-left: 10px;">模块类型：&nbsp;</label>
            <select name="gprsType" id="gprsType" class="input-style" style="width: 3.8rem;float: left;">
                <option value="">请选择</option>
                <option value="0">电子投币</option>
                <option value="1">洗车机</option>
                <option value="2">动态口令</option>
                <option value="6">充电站</option>
                <option value="3">昌原充电站</option>
            </select>
            <label style="float: left;line-height: 1.2rem;margin-left: 10px;" for="gprsNo">模块编号：</label>
            <input type="text" name="gprsNo" id="gprsNo" value="${pager.params.gprsNo}" class="input-style" style="width:100px;float: left;">
            </div>
            <div style="text-align: center;width: 100%;float: left;margin-top: 10px;">
                <input type="submit" class="input-style bgactive le" value="故障查询" style="display:inline-block;margin-left: 30%;">
                <button type="button" id="send" onclick="queryBug()" class="input-style bgactive ri" style="display:inline-block;margin-right: 30%;">&nbsp;&nbsp;&nbsp;实时上报&nbsp;&nbsp;&nbsp;</button>
            </div>

        </div>
    </form>
    <section class="body-bg">
        <div class="selec-list clearfix">
            <div style="height: 5px;">&nbsp;</div>
            <table class="selec-list-table">
                <tr>
                    <td width="23%" class="tr_bg1">产品编号</td>
                    <td width="23%" class="tr_bg2">模块编号</td>
                    <td width="23%" class="tr_bg2">商品类型</td>
                    <td width="30%" class="tr_bg3">操作</td>
                </tr>
                <c:forEach var="prod" items="${pager.recordList}">
                    <tr>
                        <td width="23%">${prod.prodNo}</td>
                        <td width="23%">${prod.gprsNo}</td>
                        <td width="23%">${prod.typeStr}</td>
                        <td width="30%">
                            <input type="hidden" id="gprsNo2" value="${prod.gprsNo}">
                            <input type="hidden" id="bizNo" value="${prod.bizNo}">
                            <a href="#" class="input-style" stsr="${prod.id}" gprsno="${prod.gprsNo}">详情查询</a>
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
            setTimeout(loadSt(prodId), 10);
        });

        function loadSt(prodId) {
            window.location.href = _CTX + "/prod/prodBugRpt?prodId=" + prodId;
        }

    });


    var hander = null;
    function timer(time) {
        var btn = $("#send");
        btn.attr("disabled", "true");  //按钮禁止点击
        btn.html(time <= 0 ? "实时上报" : ("上报中" + (time) + ""));
        if (time <= 0) {
            btn.removeAttr("disabled");  //按钮禁止点击
            clearInterval(hander);
            hander = null;
        } else {
            hander = setInterval(function () {
                if (time <= 0) {
                    clearInterval(hander); //清除倒计时
                    btn.html("实时上报");
                    btn.removeAttr("disabled");
                    location.href=_CTX+"/prod/prodBugRptQuery";
                    return false;
                } else {
                    btn.html("上报中" + (time--) + "");
                }
            }, 1000);
        }
    }
    function queryBug() {
        timer(10);
        $.ajax({
            type: "GET",
            url: _CTX+"/prod/prodBugRptQueryMQ.json",
            dataType: "json",
            success: function(data){
                if (data.extMsg.success){
                    /*location.href=_CTX+"/prod/prodBugRptQuery";*/
                }else{
                    alert(data.extMsg.msg);
                    timer(0);
                }
            },
            error:function(data){
                timer(0);
                alert("请检查网络，刷新后再尝试！");
            }
        });
    }
</script>
</body>
</html>