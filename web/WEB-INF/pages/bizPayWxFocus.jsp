<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">微信强制关注配置<a href="${ctx}/biz/paySet" class="back"><i class="icon iconfont">&#xe67f;</i></a>
    </h1>
</header>
<!-- 订单列表搜索 -->
<section class="mart2b2 clearfix">
    <form id="theForm" action="${ctx}/biz/crOrUpBizPayWxFocus" method="post" enctype="multipart/form-data">
        <div class="para-set clearfix">
            <ul>
                <li>
                    <span>应用ID</span>
                    <span class="red">*</span>
                </li>
                <li>
                    <input type="hidden" class="style01" id="Name" name="bizName" value="${bizWxFocus.name}">
                    <input type="text" class="style01" id="appId" name="appId" value="${bizWxFocus.appId}">
                </li>
                <li>
                    <span>应用秘钥</span>
                    <span class="red">*</span>
                </li>
                <li>
                    <input type="text" class="style01" id="appSkey" name="appSkey" value="${bizWxFocus.appSkey}">
                </li>
                <li>
                    <span>公众号原始ID</span>
                </li>
                <li>
                    <input type="text" class="style01" id="pubAcctId" name="pubAcctId" value="${bizWxFocus.pubAcctId}">
                </li>
            </ul>
        </div>
        <input type="hidden" class="style01" id="id" name="id" value="${bizWxFocus.id}">
        <c:if test="${bizWxFocus==null||bizWxFocus.id==null}">
            <button type="button" disabled="disabled"  class="btn">请先完善微信支付配置</button>
        </c:if>
        <c:if test="${bizWxFocus!=null||bizWxFocus.id!=null}">
            <button type="button" class="btn">确认提交</button>
        </c:if>
    </form>
</section>

<!-- end -->
<%@include file="_layout_nav.jsp" %>
<script>
    $(document).ready(function () {

        $('.btn').on('click', function (e) {
            e.preventDefault();
            var appId = $('#appId').val();
            var appSkey = $('#appSkey').val();
            if (appId == '' || appSkey == '') {
                alert('请填写应用ID、应用秘钥');
                return;
            }
            $('#theForm').submit();
        });

    });
</script>

</body>
</html>