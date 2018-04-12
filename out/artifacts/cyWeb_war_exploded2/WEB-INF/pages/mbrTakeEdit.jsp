<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
<h1 class="header fc">
    申请提现<a href="${ctx}/biz/bizTake/list?takeWay=${bizTake.takeWay}" class="back"><i class="icon iconfont">&#xe67f;</i></a>
</h1>
</header>

<!-- 订单列表搜索 -->
<section class="mart2b2 clearfix">
<form id="form" >
    <div class="para-set clearfix">
          <ul>
              <li>
                  <span>状态</span>
                  <span class="red">*</span>
                  <label  class="ri">${bizTake.stateStr}</label>
              </li>
             <li>
                 <input type="hidden" id="id" name="id" value="${bizTake.id}" />
                <span>提现金额</span>
                <span class="red">*</span>
                 <label  class="ri">${bizTake.amount}</label>
             </li>
            <%-- <li>
               <input id="amount" name="amount" readonly="readonly"  value="${bizTake.amount}"  type="text" class="style01">
             </li>--%>
              <li>
                 <span>提现方式</span>
                 <span class="red">*</span>
                  <c:if test="${bizTake.takeWay==2}">
                    <label  class="ri">微信钱包</label>
                  </c:if>
                  <c:if test="${bizTake.takeWay==1}">
                    <label  class="ri">银行卡</label>
                  </c:if>
                  <c:if test="${bizTake.takeWay==3}">
                      <label  class="ri">自动转账</label>
                  </c:if>
                  <input type="hidden" id="takeWay" name="takeWay" value="${bizTake.takeWay}"  />
             </li>
              <li id="hd1" style="display: none;">
                  <span>真实姓名</span>
                  <span class="red">*</span>
              </li>
              <li  id="hd2" style="display: none;">
                  <input id="name" name="name" value="${bizTakeBank.name}${bizTakeWwlt.name}"   type="text" class="style01">
              </li>
              <li  id="hd3_1" style="display: none;">
                  <span>银行卡号</span>
                  <span  class="red">*</span>
              </li>
              <li  id="hd4_1" style="display: none;">
                  <input id="bankNo" name="bankNo" value="${bizTakeBank.bankNo}" type="text" class="style01">
              </li>
              <li  id="hd5_1" style="display: none;">
                  <span>开户行地址</span>
                  <span class="red">*</span>
              </li>
              <li  id="hd6_1" style="display: none;">
                  <input id="bankAddr" name="bankAddr" value="${bizTakeBank.bankAddr}" type="text" class="style01">
              </li>

              <li  id="hd3_2" style="display: none;">
                  <span>微信账号</span>
                  <span  class="red">*</span>
              </li>
              <li  id="hd4_2" style="display: none;">
                  <input id="wxAcct" name="wxAcct" value="${bizTakeWwlt.wxAcct}" type="text" class="style01">
              </li>
              <li  id="hd5_2" style="display: none;">
                  <span>昵称</span>
                  <span class="red">*</span>
              </li>
              <li  id="hd6_2" style="display: none;">
                  <input id="nick" name="nick" value="${bizTakeWwlt.nick}" type="text" class="style01">
              </li>
              <c:if test="${bizTake.state==1}">
                  <li>
                      <span>打款凭证号</span>
                      <label class="ri" style="width: 70%">${bizTake.tsno}</label>
                  </li>
                  <li>
                      <span>手续费</span>
                      <label class="ri" style="width: 70%">${bizTake.mtcCost}</label>

                  </li>
                  <li>
                      <span>实提金额</span>
                      <label class="ri" style="width: 70%">${bizTake.realAmount}</label>
                  </li>
              </c:if>
             <li>
                <span>备注说明</span>
             </li>
             <li>
                <textarea name="expln" id="expln">${bizTake.expln}</textarea>
             </li>
          </ul>
      </div>
    <c:if test="${bizTake.state==null || bizTake.state==0}">
        <button type="button" class="btn">修改确认</button>
    </c:if>

</form>
</section>

<!-- end -->
<%@include file="_layout_nav.jsp" %>

</body>
<script>
    function  checkTakeWay(way) {
        if (way==1){
            $("#takeWay").val(way);
            $("#hd1").show();
            $("#hd2").show();
            $("#hd3_1").show();
            $("#hd4_1").show();
            $("#hd5_1").show();
            $("#hd6_1").show();
            $("#hd3_2").hide();
            $("#hd4_2").hide();
            $("#hd5_2").hide();
            $("#hd6_2").hide();
        }else if (way==2){
            $("#takeWay").val(way);
            $("#hd1").show();
            $("#hd2").show();
            $("#hd3_1").hide();
            $("#hd4_1").hide();
            $("#hd5_1").hide();
            $("#hd6_1").hide();
            $("#hd3_2").show();
            $("#hd4_2").show();
            $("#hd5_2").show();
            $("#hd6_2").show();
        }else if (way==3){
            $("#takeWay").val(way);
            $("#hd3_1").hide();
            $("#hd4_1").hide();
            $("#hd5_1").hide();
            $("#hd6_1").hide();
            $("#hd5_2").show();
            $("#hd6_2").show();
        }else {
            alert("刷新页面，请重新尝试！")
        }
    }



    $(document).ready(function () {
        var  tWay="${bizTake.takeWay}";
        if(tWay=="1"){
            checkTakeWay(tWay);
        }else if(tWay=="2"||tWay=="3"){
            checkTakeWay(tWay);
        }else {
            alert("页面已经失效，请重新进入该页面！");
        }

        $('.btn').on('click', function (e) {
            e.preventDefault();
            var amount = $('#amount').val();
            var takeWay = $('#takeWay').val();
            var name = $('#name').val();
            var bankNo = $('#bankNo').val();
            var bankAddr = $('#bankAddr').val();
            var wxAcct = $('#wxAcct').val();
            var nick = $('#nick').val();
            if (takeWay==''||takeWay==null){
                alert("请选择提款方式！");
                return;
            }else if(takeWay==1){
                if (amount == '' || takeWay == '' || name == '' || bankNo == ''|| bankAddr == '') {
                    alert('信息请填写完整！');
                    return;
                }
            }else if (takeWay==2){
                if (amount == '' || takeWay == '' || name == '' || wxAcct == ''|| nick == '') {
                    alert('信息请填写完整！');
                    return;
                }
            }else {
                alert("请刷新页面，重新尝试！");
                return;
            }

            $.post(_CTX + "/biz/bizTake/update.json",
                    $('#form').serialize(),
                    function (data) {
                        if (data) {
                            if (data.extMsg.success) {
                                alert(data.extMsg.msg);
                                window.location.href = _CTX + "/biz/bizTake/list?takeWay="+takeWay;
                            } else {
                                alert(data.extMsg.msg);
                            }
                        }
                    }, "json");
        });
    });
</script>
</html>