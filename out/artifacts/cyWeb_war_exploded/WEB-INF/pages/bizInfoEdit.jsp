<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">商家信息修改<a href="${ctx}/biz/view" class="back"><i class="icon iconfont">&#xe67f;</i></a>
    </h1>
</header>

<!-- 订单列表搜索 -->
<section class="mart2b2 clearfix">
    <form action="${ctx}/biz/update" id="theForm" name="form" method="post" enctype="multipart/form-data">
        <div class="para-set clearfix">
            <ul>
                <li>
                    <span>商家名称</span>
                    <input type="text" placeholder="请输入商家名称" id="name" name="name" value="${biz.name}" class="ri">
                </li>
                <li>
                    <span>网址</span>
                    <input type="text" placeholder="请输入网址" name="webSite" value="${biz.webSite}" class="ri">
                </li>
                <li>
                    <span>手机</span>
                    <input type="text" placeholder="请输入手机号" name="mobile" value="${biz.mobile}" class="ri">
                </li>
                <li>
                    <span>邮箱</span>
                    <input type="text" placeholder="请输入邮箱" name="email" value="${biz.email}" class="ri">
                </li>
                <li>
                    <span>地址</span>
                    <input type="text" placeholder="请输入地址" name="addr" value="${biz.addr}" class="ri">
                </li>
                <li><span>服务电话</span>
                    <input type="text" placeholder="请输入服务电话" name="tel" value="${biz.tel}" class="ri">
                </li>
                <li><span>API秘钥</span>
                    <input type="text" placeholder="API秘钥" id="bizKey" name="bizKey" maxlength="64" value="${biz.bizKey}" class="ri">
                </li>
                <%--<li><span>商家LOGO</span>
                    <input type="file" placeholder="" name="file" value="" class="ri">
                </li>--%>
            </ul>
        </div>
        <input type="hidden" name="id" value="${biz.id}">
        <button type="button" class="btn">确认保存</button>
    </form>
</section>
<%@include file="_layout_nav.jsp" %>
<script>
    $(document).ready(function () {
        $('.btn').on('click', function (e) {
            e.preventDefault();
            var name = $('#name').val();
            if (name == '') {
                alert('请填写商家名称');
                return;
            }
            if (bizKey.length<32){
                alert('API秘钥长度至少为32位！');
                return;
            }
            $('#theForm').submit();
        });
    });
</script>

<!-- end -->
</body>
</html>