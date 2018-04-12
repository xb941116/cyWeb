<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">
        添加活动<a href="${ctx}/navCtrl/home" class="back"><i class="icon iconfont">&#xe67f;</i></a>
    </h1>
</header>

<!-- 订单列表搜索 -->
<section class="mart2b2 clearfix">
    <form id="form" >
        <div class="para-set clearfix">
            <ul>
                <li  id="hd5_1" >
                    <span>达标额度</span>
                    <span class="red">*</span>
                </li>
                <li  id="hd6_1" >
                    <input id="coin" name="coin" value="" type="text" class="style01">
                </li>

                <li  id="hd3_2" >
                    <span>赠送的积分额度</span>
                    <span  class="red">*</span>
                </li>
                <li  id="hd4_2" >
                    <input id="money" name="money" value="" type="text" class="style01">
                </li>

                <li>
                    <span>限制次数（会员/次）</span>
                    <span  class="red">*</span>
                </li>
                <li>
                    <input id="totalMbr" name="totalMbr" value="" type="text" class="style01">
                </li>

                <li>
                    <span>活动总次数</span>
                    <span  class="red">*</span>
                </li>
                <li>
                    <input id="totalAll" name="totalAll" value="" type="text" class="style01">
                </li>
                <li>
                    <span>活动排序</span>
                    <span  class="red">*</span>
                </li>
                <li>
                    <input id="sort" name="sort" value="" type="text" class="style01">
                </li>
                <li>
                    <span>开启状态</span>
                    <label for="focus2" class="ri">否<input type="radio" id="focus2" name="state" value="0"></label>
                    <label for="focus1" class="ri">是<input type="radio" id="focus1" name="state" value="1" checked="true" ></label>
                </li>
                <li>
                    <span>活动说明</span>
                </li>
                <li>
                    <textarea name="content" id="content">${bizGift.content}</textarea>
                </li>
            </ul>
        </div>

        <button type="button" class="btn">添加</button>

    </form>
</section>

<!-- end -->
<%@include file="../_layout_nav.jsp" %>

</body>
<script>

    $(document).ready(function () {

        $('.btn').on('click', function (e) {
            e.preventDefault();
            var coin = $('#coin').val();
            var money = $('#money').val();
            var content = $('#content').val();
            var totalMbr = $('#totalMbr').val();
            var totalAll = $('#totalAll').val();
            if (coin == ''|| money == ''|| content == ''|| totalMbr == ''|| totalAll == '') {
                alert('信息请填写完整！');
                return;
            }

            $.post(_CTX + "/biz/bizGift/create.json",
                    $('#form').serialize(),
                    function (data) {
                        if (data) {
                            if (data.extMsg.success) {
                                alert(data.extMsg.msg);
                                window.location.href = _CTX + "/biz/bizGift/query";
                            } else {
                                alert(data.extMsg.msg);
                            }
                        }
                    }, "json");
        });
    });
</script>
</html>