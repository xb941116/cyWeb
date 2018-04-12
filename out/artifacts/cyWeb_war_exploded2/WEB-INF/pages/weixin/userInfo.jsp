<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@include file="../_layout_header.jsp" %>

<body style="background-color: #CCCCCC;">

<header>
    
<h1 class="header fc" style="background-color:#A1D8FD">个人中心<!-- <a href="#" class="back">
<i class="icon iconfont">&#xe67f;</i></a> -->
        
<%--<a href="#" class="username"><i class="icon iconfont">&#xe666;</i>&nbsp;${acct}</a>
      
  <a href="${ctx}/loginCtrl/logout" class="tuichu">退出</a>
  
--%>
  </h1></header>

<!-- 订单列表搜索 -->
<section class="mart2b2 clearfix">
   
 <div class="center-wrapper">
      
  <div class="cw-top" style=" height: 8rem;background-color:#56A8E0;margin-top:0px">
           
 <i>
                <a href="<c:if test="${ismbr==null||ismbr==0}">javascript:void(0);</c:if><c:if test="${ismbr!=null&&ismbr==1}">${ctx}/custWeiXin/edit?id=${mbr.id}&bizNo=${bizNo}</c:if>" class="upload">
           
         <c:if test="${empty headimgurl}"><img id="headImg" src="${ctx}/static/img/bg.jpg" alt="" style="width: 100%;height: 100% ;"></c:if>
   
                 <c:if test="${not empty headimgurl}"><img id="headImg" src="${headimgurl}" style="width: 100%;height: 100%"></c:if>
                </a>
   
         </i>

            <h2 <c:if test="${ismbr==null||ismbr==0}"> style="color:red;"</c:if>>${mbr.name}<c:if test="${ismbr==null||ismbr==0}">${nickname}【非会员】</c:if><c:if test="${ismbr!=null&&ismbr==1}">【会员】</c:if></h2>

<%--            <p>${totalInc}</p>--%>
        </div>
   

     <!--  -->
       
 <div >
    
        
<ul style="background-color: #ffffff;">
            <li style="padding-top:10px;border-bottom: 2px soild red;padding-left:10px;padding-bottom:10px;font-size: 15px;border-bottom: 1px solid #CCCCCC;" width="400px">余额<span style="float:right;padding-right:10px"><c:if test="${mbrWallet==null||mbrWallet.money==null}">0</c:if>${mbrWallet.money}</li>
             
            <li style="padding-top:10px;padding-left:10px;padding-bottom:10px;font-size: 15px;" width="400px">积分<span style="float:right;padding-right:10px"><c:if test="${mbrCoin==null||mbrCoin.coin==null}">0</c:if>${mbrCoin.coin}</li>
     
       <ul>
	</div>
        <!--  list --><div style="height:15px"></div>     
   <div>
            
<ul style="background-color: #ffffff;">
  
              <li style='border-bottom:1px solid #CCCCCC;border-top:1px solid #CCCCCC;height:50px ' ><a href="${ctx}/custWeiXin/toRegister?bizNo=${bizNo}&gprsNo=${gprsNo}"><img src="../../../static/img/zc.png" height="30" width="30" style="margin-left:10px;vertical-align:middle"><span style="font-size: 16px;margin-left:15px;vertical-align:middle">会员注册</span></a></li>

                <li style='border-bottom:1px solid #CCCCCC;border-bottom:1px solid #CCCCCC;height:50px'><a href="javascript:void(0);" onclick="chackUrl()"><img src="../../../static/img/cz.png" height="30" width="30" style="margin-left:10px;vertical-align:middle"><span style="font-size: 16px;margin-left:15px;vertical-align:middle">钱包充值</span></a></li>

                <li style='border-bottom:1px solid #CCCCCC;border-bottom:1px solid #CCCCCC;height:50px'><a href="${ctx}/custWeiXin/reChargeHis?bizNo=${bizNo}&gprsNo=${gprsNo}"><img src="../../../static/img/czjl.png" height="30" width="30" style="margin-left:10px;vertical-align:middle"><span style="font-size: 16px;margin-left:15px;vertical-align:middle">充值记录</span></a></li>

                <li style='border-bottom:1px solid #CCCCCC;border-bottom:1px solid #CCCCCC;height:50px'><a href="${ctx}/custWeiXin/mbrOrdList?bizNo=${bizNo}&gprsNo=${gprsNo}"><img src="../../../static/img/xj.png" height="30" width="30" style="margin-left:10px;vertical-align:middle"><span style="font-size: 16px;margin-left:15px;vertical-align:middle">消费记录</span></a></li>

                <li style='border-bottom:1px solid #CCCCCC;border-bottom:1px solid #CCCCCC;height:50px'><a href="${ctx}/custWeiXin/queryBizGift?bizNo=${bizNo}&gprsNo=${gprsNo}"><img src="../../../static/img/yh.png" height="30" width="30" style="margin-left:10px;vertical-align:middle"><span style="font-size: 16px;margin-left:15px;vertical-align:middle">优惠活动</span></a></li>
 
               <li style='border-bottom:1px solid #CCCCCC;border-bottom:1px solid #CCCCCC;height:50px'><a href="${ctx}/custWeiXin/loadPayMsg?bizNo=${bizNo}&gprsNo=${gprsNo}"><img src="../../../static/img/xz.png" height="30" width="30" style="margin-left:10px;vertical-align:middle"><span style="font-size: 16px;margin-left:15px;vertical-align:middle">信息查询</span></a></li>
 
           </ul>
        
</div>
    
</div>

</section>


<script>

    function chackUrl() {
        var rgState="${rgState}";
        var isMbr="${ismbr==null||ismbr==0}";
        if (isMbr==""||isMbr==null||isMbr!="true"||isMbr!=true){
            window.location.href="${ctx}/custWeiXin/toRegister?bizNo=${bizNo}&gprsNo=${gprsNo}";
        }
        if (rgState=="true"||rgState==true){
            window.location.href="${ctx}/custWeiXin/toReCharge?bizNo=${bizNo}&rgState=${rgState}&way=3&gprsNo=${gprsNo}";
        }else {
            if ("${gprsNo}"==""&&confirm("稍后请扫一扫您使用设备上的二维码来进行充值！")){
                window.location.href="${ctx}/custWeiXin/scanQRCode?bizNo=${bizNo}&way=3&gprsNo=${gprsNo}";
            }else {
                window.location.href="${ctx}/custWeiXin/toReCharge?bizNo=${bizNo}&rgState=${rgState}&way=3&gprsNo=${gprsNo}";
            }
        }


    }
</script>
</body>
</html>
