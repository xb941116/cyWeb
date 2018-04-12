<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">
        申请提现<a href="${ctx}/biz/bizTake/list" class="back"><i class="icon iconfont">&#xe67f;</i></a>
    </h1>
</header>

<!-- 订单列表搜索 -->
<section class="mart2b2 clearfix">
    <form id="form" action="${ctx}/biz/bizTake/create">
        <div class="para-set clearfix">
            <ul>
                <li>
                    <span>提现金额</span>
                    <span class="red">手动可提现${takeMoney}(待自动转账${autoMoney}）*</span>
                </li>
                <li>
                    <input id="amount" name="amount" type="text" class="style01">
                </li>
                <li>
                    <span>提现方式</span>
                    <span class="red">*</span>
                    <label for="wx" class="ri">微信钱包<input type="radio" id="wx" name="celec0" onclick="checkTakeWay(2)"></label>
                    <label for="bank" class="ri">银行卡<input id="bank" type="radio" name="celec0" onclick="checkTakeWay(1)"></label>
                    <input type="hidden" id="takeWay" name="takeWay"/>
                </li>
                <li id="hd1" style="display: none;">
                    <span>真实姓名</span>
                    <span class="red">*</span>
                </li>
                <li id="hd2" style="display: none;">
                    <input id="name" name="name" type="text" class="style01">
                </li>
                <li id="hd3_1" style="display: none;">
                    <span>银行卡号</span>
                    <span class="red">*</span>
                </li>
                <li id="hd4_1" style="display: none;">
                    <input id="bankNo" name="bankNo" type="text" class="style01">
                </li>
                <li id="hd5_1" style="display: none;">
                    <span>开户行地址</span>
                    <span class="red">*</span>
                </li>
                <li id="hd6_1" style="display: none;">
                    <input id="bankAddr" name="bankAddr" type="text" class="style01">
                </li>

                <li id="hd3_2" style="display: none;">
                    <span>微信账号</span>
                    <span class="red">*</span>
                </li>
                <li id="hd4_2" style="display: none;">
                    <input id="wxAcct" name="wxAcct" type="text" class="style01">
                </li>
                <li id="hd5_2" style="display: none;">
                    <span>昵称</span>
                    <span class="red">*</span>
                </li>
                <li id="hd6_2" style="display: none;">
                    <input id="nick" name="nick" type="text" class="style01">
                </li>
                <li>
                    <span>备注说明</span>
                </li>
                <li>
                    <textarea name="expln" id="expln"></textarea>
                </li>
            </ul>
        </div>
        <input id="takeSet" name="takeSet" type="hidden" value="${takeSet}">
        <button type="button" class="btn">确认提交</button>

    </form>
</section>

<!-- end -->
<%@include file="_layout_nav.jsp" %>

</body>
<script>
    function checkTakeWay(way) {
        if (way == 1) {
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
        } else if (way == 2) {
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
        } else {
            alert("刷新页面，请重新尝试！")
        }
    }


    $(document).ready(function () {

        $('.btn').on('click', function (e) {
            e.preventDefault();
            var amount = $('#amount').val();
            var takeWay = $('#takeWay').val();
            var name = $('#name').val();
            var bankNo = $('#bankNo').val();
            var bankAddr = $('#bankAddr').val();
            var takeSet = $('#takeSet').val();
            var wxAcct = $('#wxAcct').val();
            var nick = $('#nick').val();
            if (takeWay == '' || takeWay == null) {
                alert("请选择提款方式！");
                return;
            } else if (takeWay == 1) {
                if (amount == '' || takeWay == '' || name == '' || bankNo == '' || bankAddr == '') {
                    alert('信息请填写完整！');
                    return;
                }
            } else if (takeWay == 2) {
                if (amount == '' || takeWay == '' || name == '' || wxAcct == '' || nick == '') {
                    alert('信息请填写完整！');
                    return;
                }
            } else {
                alert("请刷新页面，重新尝试！");
                return;
            }
            if (takeSet != null && takeSet != '') {
                amount = parseInt(amount);
                if (amount < parseInt(takeSet)) {
                    alert("最低提现金额不能小于" + parseInt(takeSet));
                    return;
                }
            }

            $.post(_CTX + "/biz/bizTake/create.json",
                    $('#form').serialize(),
                    function (data) {
                        if (data) {
                            if (data.extMsg.success) {
                                window.location.href = _CTX + "/biz/bizTake/list?takeWay=" + takeWay;
                            } else {
                                alert(data.extMsg.msg);
                            }
                        }
                    }, "json");
        });
    });
</script>
</html>