<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../_layout_header2.jsp" %>
<body style="background-color: #ffffff;">
<header>
    <h1 class="header fc" style="background-color:#56A8E0">会员充值<a href="javascript:void(0);" onclick="javascript:history.go(-1);" class="back"><i class="icon iconfont">&#xe67f;</i></a>
    </h1>
</header>
<!-- 订单列表搜索 -->
<section class="mart2b2 clearfix">
    <div class="center-wrapper">
        <%--<div class="cw-top">
            <i>
                <a href="javascript:void(0);" class="upload">
                    <c:if test="${empty headimgurl}"><img id="headImg" src="${ctx}/static/img/bg.jpg" alt="" style="width: 100%;height: 100%"></c:if>
                    <c:if test="${not empty headimgurl}"><img id="headImg" src="${ctx}/${headimgurl}" style="width: 100%;height: 100%"></c:if>
                </a>
            </i>

            <h2>${mbr.name}</h2>
        </div>
        <div class="cw-income-list clearfix">
            <ul>
                <li>余额<br/><c:if test="${mbrWallet==null||mbrWallet.money==null}">0</c:if>${mbrWallet.money}</li>
                <li>&nbsp;<br/>&nbsp;</li>
                <li>积分<br/><c:if test="${mbrCoin==null||mbrCoin.coin==null}">0</c:if>${mbrCoin.coin}</li>
            </ul>
        </div>--%>

        <div class="cw-income-list clearfix">
            <ul>
                <li style="width: auto;padding-left: 20px;">
                    <span style=" font-size: 0.6rem;">您的钱包余额:</span>
                    <span style="color: #58b431; font-size: 0.6rem;font-weight: bolder;">
                            <c:if test="${mbrWallet==null||mbrWallet.money==null}">0</c:if>${mbrWallet.money}
                        </span>
                </li>
            </ul>
        </div>
        <!--  list -->
        <input type="hidden" name="total" id="total" value="1"/>
     <div style="margin-top:10px;margin-left:20px;font-size: 0.6rem;font-family: 宋体;text-align: left;">请选择充值金额:</div>   <div class="reg_money">
            <div class="ord_border ord_check" onclick="changeTotal(1,this)">¥1</div>
            <div class="ord_border" onclick="changeTotal(10,this)">¥10</div>
            <div class="ord_border" onclick="changeTotal(50,this)">¥50</div>
            <div class="ord_border" onclick="changeTotal(100,this)">¥100</div>
            <div class="ord_border" onclick="changeTotal(300,this)">¥300</div>
            <div class="ord_border" onclick="changeTotal(500,this)">¥500</div>
            <div class="ord_border" style="width: 82%;" onclick="changeTotal(0,this)">
                <input id="autoTotal" name="autoTotal" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();"
                       onblur="autoTotal()" type="text" style="width: 95%;" placeholder="请输入充值金额">
            </div>
        </div>

        <button type="button" id="btn" class="btn" onclick="dopay()" style="background-color:#56A8E0;margin-top:10px">确认支付</button>

        <h2 style="font-weight:bolder;font-size: 0.6rem;color: #f08300;padding-left: 5%;margin-top:10px">最新活动</h2>
        <div style="text-align: left; float: left;padding-left: 8%;">
            <c:forEach items="${bizGiftList}" var="gift" varStatus="state">
                <div style="width: 95%; text-align: left; float: left;margin-top: 5px;padding-left: 10px;">
                    充值<span style="color:#58b431; font-weight: bolder;">${gift.coin}</span>元送<span style="color:#58b431; font-weight: bolder;">${gift.money}</span>元
                    (<c:if test="${gift.totalMbr==0}">不限次数</c:if><c:if test="${gift.totalMbr!=0}">活动限参与<span style="font-weight: bolder;">${gift.totalMbr}</span>次</c:if>)
                </div>
            </c:forEach>
        </div>


    </div>
</section>
<script type="text/javascript">

    function autoTotal() {
        $("#total").val($("#autoTotal").val());
    }

    function changeTotal(money, e) {
        $("div .reg_money").find("div").removeClass("ord_check");
        $(e).addClass("ord_check");
        $("#total").val(money);
    }

    function dopay() {
        /* if (confirm("充值后的钱只能用于支付
        的产品设备，请核实您要使用的产品是否支持钱包支付！")){

         }else {
         return;
         }*/
        /*alert("功能测试中，暂未开放……");
         return ;*/
        $("#btn").attr("disabled", "disabled");
        var total = $("#total").val();
        if (total == "" || total == 0) {
            total = parseInt($("#autoTotal").val()) + parseInt(total);
            if (total == 0) {
                alert("请选择或者输入有效的金额！");
            }
        }
        $.ajax({
            type: "POST",
            url: "${ctx}/weixinJSBridge/rgPay.json",
            data: {
                "commodityName": $("#commodityName").html(),//商品名称
                "bizNo": "${bizNo}",//商家编号
                "money": total //支付的总金额
            },
            cache: false,
            error: function () {
                alert("网络错误，请稍后重试！");
                return false;
            },
            success: function (data) {
                if (parseInt(data.agent) < 5) {
                    alert("您的微信版本低于5.0无法使用微信支付。");
                    return;
                }
                WeixinJSBridge.invoke('getBrandWCPayRequest', {
                    "appId": data.appId, //公众号名称，由商户传入
                    "nonceStr": data.nonceStr, //随机串
                    "package": data.packageValue, //商品包信息
                    "signType": data.signType, //微信签名方式:
                    "timeStamp": data.timeStamp, //时间戳，自 1970 年以来的秒数
                    "paySign": data.paySign //微信签名
                }, function (res) {
                    /* 对于支付结果，res对象的err_msg值主要有3种，含义如下：(当然，err_msg的值不只这3种)
                     1、get_brand_wcpay_request:ok   支付成功后，微信服务器返回的值
                     2、get_brand_wcpay_request:cancel   用户手动关闭支付控件，取消支付，微信服务器返回的值
                     3、get_brand_wcpay_request:fail   支付失败，微信服务器返回的值

                     -可以根据返回的值，来判断支付的结果。
                     -注意：res对象的err_msg属性名称，是有下划线的，与chooseWXPay支付里面的errMsg是不一样的。而且，值也是不同的。
                     */
                    //alert(res.err_msg);
                    if (res.err_msg == 'get_brand_wcpay_request:ok') {
                        alert("充值成功！");
                        $("#btn").removeAttr("disabled");
                        window.location.href = window.location.href;
                    } else if (res.err_msg == "get_brand_wcpay_request:cancel") {
                        $("#btn").removeAttr("disabled");
                        window.location.href = data.errorUrl;
                    } else {
                        $("#btn").removeAttr("disabled");
                        window.location.href = data.errorUrl;
                    }
                });
            }
        });
    }
</script>
</body>
</html>
