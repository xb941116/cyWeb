<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">会员信息修改<a href="javascript:void(0);" class="back"><i class="icon iconfont">&#xe67f;</i></a>
    </h1>
</header>

<!-- 订单列表搜索 -->
<section class="mart2b2 clearfix">
    <form action="${ctx}/custWeiXin/update" id="theForm" name="form" method="post" enctype="multipart/form-data">
        <div class="para-set clearfix">
            <ul>
                <li>
                    <span>昵称</span>
                    <input type="text" placeholder="请输入会员昵称" id="name" name="name" value="${mbr.name}" class="ri">
                </li>
                <li>
                    <span>手机</span>
                    <input   id="mobile" type="text" placeholder="请输入手机号" name="mobile" value="${mbr.mobile}" class="ri">
                </li>
                <li>
                    <span>邮箱</span>
                    <input  id="email" type="text" placeholder="请输入邮箱" name="email" value="${mbr.email}" class="ri">
                </li>
                <%--<li><span>商家LOGO</span>
                    <input type="file" placeholder="" name="file" value="" class="ri">
                </li>--%>
            </ul>
        </div>
        <input type="hidden" name="id" value="${mbr.id}">
        <input type="hidden" name="bizNo" value="${bizNo}">
        <button type="button" class="btn">确认保存</button>
    </form>
</section>
<%@include file="../_layout_nav.jsp" %>
<script>
    $(document).ready(function () {
        $('.btn').on('click', function (e) {
            e.preventDefault();
            var name = $('#name').val();
            var mobile = $('#mobile').val();
            if (name == ''||mobile == '') {
                alert('请填写会员名称');
                return;
            }
            if (name.length>10){
                alert('会员名称请填写在十个字以内！');
                return;
            }
            var myreg = /^((1)+\d{10})$/;
            if(!myreg.test(mobile))
            {
                alert('请输入有效的手机号码！');
                return;
            }
            $('#theForm').submit();
        });
    });
</script>

<!-- end -->
</body>
</html>