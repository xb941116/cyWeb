<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../_layout_header.jsp" %>
<body style="background-color: #ffffff;">
<header>
    <h1 class="header fc">支付</h1>
</header>

<section class="mart2b2">
    <!-- 商品类型选择 -->
    <!-- pay -->
    <div class="payed">
        <div class="ord_logo">
            <img src="../static/img/weixin/logo_byc.png" alt=""  style="width: 100%;height: 100%" >
        </div>
        <div>
            <div style="font-weight: bold;text-align:left;font-size: 15px;font-family: 新宋体"> <span style="font-size:18px">第一步</span>：请确保充电器连接正常；</div>
            <div style="font-weight: bold;text-align:left;font-size: 15px;font-family: 新宋体"> <span style="font-size:18px">第二步</span>：扫码前请按下对应按钮；</div>
            <div style="font-weight: bold;text-align:left;font-size: 15px;font-family: 新宋体"> <span style="font-size:18px">第三步</span>：<span style="color:red;font-size:18px">务必</span>在听到“请刷卡或投币”时点击支付。</div>
        </div>
	
            
            
          
        <div style="float: left;width:100%;border-bottom: 2px solid #dddddd;border-top: 2px solid #dddddd;line-height: 1.4rem; ">
            <span style="float: left;margin-left: 10px; font-size:0.6rem">钱包余额：<span style="color: #58b431;font-weight: bolder; font-size:0.6rem">￥${mbrWallet.money}</span></span>
           
            
        </div>
		<div style="float: left;width:100%;border-bottom: 2px solid #dddddd;line-height: 1.4rem; ">
            
            <span style="float: left;margin-left: 10px; font-size:0.6rem">积分余额：<span style="color: #58b431;font-weight: bolder; font-size:0.6rem">￥${mbrCoin.coin}</span></span>
            <span style="float:right;margin-right: 10px;color:#58b431;font-size:20px" onclick="toRecg()">我要充值</span>
        </div>
       
        <%--<div style="clear: both;height: 0.3rem;"></div>--%>
	<div style="font-weight: bold;text-align:left;font-size: 15px;font-family: 新宋体;text-align: left;">请您选择支付金额:</div>
        <div class="ord_money">
            <input type="hidden" id="totalPay" value="${moneyOptionList[0][1]}" name="totalPay"/>
            <c:forEach items="${moneyOptionList}" var="moneyOption" varStatus="sta">
                <c:if test="${not empty moneyOption[1]}">
                    <div style="margin-left: 5px;margin-right: 5px;"
                            <c:if test="${sta.index==0}"> class="ord_border ord_check" </c:if>
                            <c:if test="${sta.index!=0}"> class="ord_border" </c:if>
                         onclick="cgMoney('${moneyOption[1]}',this)">
                        <c:if test="${empty moneyOption[0]&& empty moneyOption[1]}">暂无名称</c:if>${moneyOption[0]} <c:if test="${not empty moneyOption[1]&& empty moneyOption[0]}">${name}${sta.index+1}</c:if><br>
                        <span style="font-weight: bolder;"><c:if test="${empty moneyOption[1]}">暂无价格</c:if><c:if test="${not empty moneyOption[1]}">${moneyOption[1]}元</c:if></span>
                    </div>
                </c:if>
            </c:forEach>
        </div>
        <br>
        <div style="clear: both;height: 15px;">&nbsp;</div>
        <a href="#" payWay="2" id="btn" class="btn" style="padding: 0px; border:none;margin-right: 10px;" >
            <img src="${ctx}/static/img/weixin/wx.png" alt="" style="width: 120px;height: 34px;">
        </a>
        <a href="#" payWay="3" id="btn_wallet" class="btn" style="padding: 0px; border:none;margin-left: 10px;" >
            <img src="${ctx}/static/img/weixin/qb.png" alt="" style="width: 120px;height: 34px;">
        </a>
    </div>
    <br/>
    <div style="font-weight: bold;font-size: 15px;font-family: 新宋体">
        <span style="color:red;font-size:18px">温馨提示</span>：注册会员充值“便易充钱包”，即可享受充电<span style="color:red;font-size:18px">五折</span>优惠或免费活动。
    </div>
    <!-- payend -->
    <!-- end -->
</section>
<script>
    
    $(document).ready(function () {
	$(".img_d").click(function(){
	$(".img_d").removeClass("ord_check");
 	$(this).addClass("ord_check");
        })
    });
    var clickSta=false;
    function cgMoney(moneyValue,e) {
        if (moneyValue==null||moneyValue==""){
            return;
        }
        $("#totalPay").val(moneyValue);
        $("div .ord_money").find("div").removeClass("ord_check");
        $(e).addClass("ord_check");
    }

    function toRecg() {
        window.location.href =_CTX+"/custWeiXin/toReCharge?bizNo=${bizNo}&gprsNo=${gprsNo}&rgState=false";
    }
  

    $(document).ready(function () {
        var otherMoneyState="${otherMoneyState}";
        //微信支付
        $('#btn').on('click', function (e) {

            var totalPay=0;
            totalPay=$("#totalPay").val();
            if (totalPay==0||totalPay==''){
                alert("获取商品金额网络超时，请稍后再试。");
                return false;
            }

            if(clickSta){
                return;
            }else {
                $("#btn_text").val("加载中…");
                $("#btn").attr("disabled","disabled");
                $("#btn_wallet").attr("disabled","disabled");
                clickSta=true;
            }
            e.preventDefault();
            

            $.post(_CTX + "/ord/create.json",
                    {
                        "gprsNo":"${gprsNo}",//00000000
                        "mobile":"${mobile}",
                        "acct":"${nickname}",
                        "totalPay":totalPay,
           
                        "perCt":1,
                        "payWay":2
                    },
                    function (data) {
                        if (data) {
                            if (data.success) {
                                dopay(data.msg,totalPay);//微信支付修改处 加第几路
                            } else {
                                alert(data.msg);
                            }
                        }
                    }, "json");
        });

        //钱包支付
        $('#btn_wallet').on('click', function (e) {
            var mbrName="${mbr.id}";
            var isRegister=${isRegister};
            if (mbrName==""||mbrName==null){
                if (!isRegister){
                    alert("该设备不支持钱包支付！");
                }else if(isRegister){
                    window.location.href =_CTX+"/custWeiXin/toRegister?bizNo=${bizNo}&gprsNo=${gprsNo}";
                }

                return;
            }
            var totalPay=0;
            if (otherMoneyState=="1"){
                totalPay=$("#totalPay").val();
            }else if(otherMoneyState=="2"){
                var totalPayArray=$("#totalPay").val().match(/(\d+\.\d+)|(\d+)/g);
                totalPay=totalPayArray[totalPayArray.length-1];
            }else {
                totalPay=$("#totalPay").val();
            }
            if (totalPay==0){
                alert("获取商品金额网络超时，请稍后再试。");
                return false;
            }

            if(clickSta){
                return;
            }else {
                $("#span_wallet").val("加载中…");
                $("#btn_wallet").attr("disabled","disabled");
                $("#btn").attr("disabled","disabled");
                clickSta=true;
            }
            e.preventDefault();
            

            $.post(_CTX + "/ord/create.json",
                    {
                        "gprsNo":"${gprsNo}",//00000000
                        "mobile":"${mobile}",
                        "acct":"${nickname}",
                        "totalPay":totalPay,
                        
                        "perCt":1,
                        "payWay":3
                    },
                    function (data) {
                        if (data) {
                            if (data.success) {  //钱包支付修改处 加第几路
                                //alert(data.msg);
                                window.location.href = _CTX + "/weixinJSBridge/paysuccess?ordno="+data.msg;
                                //alert("支付成功！");
                            } else {
                                $("#btn_text").val("微信支付");
                                $("#span_wallet").val("钱包支付");
                                clickSta=false;
                                $("#btn").removeAttr("disabled");
                                $("#btn_wallet").removeAttr("disabled");
                                alert(data.msg);
                            }
                        }
                    }, "json");
        });

        if($("div.ord_border").length%2==1){
            $("div.ord_border:last") .css("width","90%");
        }
    });

    function is_weixin(){
        var ua = navigator.userAgent.toLowerCase();
        if(ua.match(/MicroMessenger/i)=="micromessenger") {
            return true;
        } else {
            return false;
        }
    }

    function dopay(payno,totalPay) {
        if (!is_weixin()){
            alert("请在微信公众号内进行支付操作！");
            return false;
        }
        $.ajax({
            type : "POST",
            url : _CTX+"/weixinJSBridge/gopay.json",
            data : {
                "commodityName" : "${name}",//商品名称
                "money" : totalPay, //支付的总金额
                "payno" : payno //订单号
		
            },
            cache : false,
            error : function() {
                alert("请点击微信公众号内的“扫一扫”进行扫码！");
                return false;
            },
            success : function(data) {
                if (parseInt(data.agent) < 5) {
                    alert("您的微信版本低于5.0无法使用微信支付。");
                    return;
                }
                WeixinJSBridge.invoke('getBrandWCPayRequest',{
                    "appId" : data.appId, //公众号名称，由商户传入
                    "nonceStr" : data.nonceStr, //随机串
                    "package" : data.packageValue, //商品包信息
                    "signType" : data.signType, //微信签名方式:
                    "timeStamp" : data.timeStamp, //时间戳，自 1970 年以来的秒数
                    "paySign" : data.paySign //微信签名
                },function(res) {
                    /* 对于支付结果，res对象的err_msg值主要有3种，含义如下：(当然，err_msg的值不只这3种)
                     1、get_brand_wcpay_request:ok   支付成功后，微信服务器返回的值
                     2、get_brand_wcpay_request:cancel   用户手动关闭支付控件，取消支付，微信服务器返回的值
                     3、get_brand_wcpay_request:fail   支付失败，微信服务器返回的值

                     -可以根据返回的值，来判断支付的结果。
                     -注意：res对象的err_msg属性名称，是有下划线的，与chooseWXPay支付里面的errMsg是不一样的。而且，值也是不同的。
                     */
                    //alert(res.err_msg);
                    if (res.err_msg == 'get_brand_wcpay_request:ok') {
                        $("#btn_text").val("微信支付");
                        $("#span_wallet").val("钱包支付");
                        clickSta=false;
                        $("#btn").removeAttr("disabled");
                        $("#btn_wallet").removeAttr("disabled");
                        //alert("支付成功！");
                        window.location.href = data.sendUrl;
                    } else if (res.err_msg == "get_brand_wcpay_request:cancel") {
                        $("#btn_text").val("微信支付");
                        $("#span_wallet").val("钱包支付");
                        clickSta=false;
                        $("#btn").removeAttr("disabled");
                        $("#btn_wallet").removeAttr("disabled");
                        alert("您已手动取消该订单支付。");

                    } else {
                        $("#btn_text").val("微信支付");
                        $("#span_wallet").val("钱包支付");
                        clickSta=false;
                        $("#btn").removeAttr("disabled");
                        $("#btn_wallet").removeAttr("disabled");
                        alert("订单支付失败。");
                    }
                });
            }
        });
    }
</script>
</body>
</html>