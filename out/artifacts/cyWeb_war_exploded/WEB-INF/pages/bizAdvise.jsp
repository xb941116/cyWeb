<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">申诉建议<a href="${ctx}/navCtrl/biz" class="back"><i class="icon iconfont">&#xe67f;</i></a>
    </h1>
</header>


<!-- 订单列表搜索 -->
<section class="mart2b2 clearfix">
    <div class="para-set clearfix">
        <textarea name="advise" id="advise" class="ssjy" placeholder="留下你的宝贵意见，让我们做的更好"></textarea>
    </div>
    <button type="button" class="btn">确认提交</button>

</section>

<!-- end -->

<%@include file="_layout_nav.jsp" %>
<script>
    $(document).ready(function () {
        $('.btn').on('click', function (e) {
            e.preventDefault();
            var advise = $('#advise').val();
            if (advise == '') {
                alert('请填写申诉建议');
                return;
            }
            $.post(_CTX + "/biz/bizAdviseAdd.json",
                    {advise:advise},
                    function (data) {
                        if (data) {
                            if (data.success) {
                                alert("谢谢，您的申诉建议已经提交成功！针对您的反馈我们会抓紧处理。");
                            } else {
                                alert(data.msg);
                            }
                        }
                    }, "json");
        });
    });
</script>
</body>
</html>