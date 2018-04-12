<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">
        商家权限修改<a href="${ctx}/biz/query" class="back"><i class="icon iconfont">&#xe67f;</i></a>
    </h1>
</header>

<!-- 订单列表搜索 -->
<section class="mart2b2 clearfix">
    <form id="theForm" method="post">
        <div class="para-set clearfix">
            <ul>
                <li>
                    <span>商家名称</span>
                    <span class="red">*</span>
                </li>
                <li>
                    <span>${bizName}</span>
                </li>
                <li>
                    <span>商家主账号</span>
                    <span class="red">*</span>
                </li>
                <li>
                    <span>${sysAcct.acct}</span>
                </li>
                <li>
                    <span>权限分配</span>
                </li>
                <li class="clearfix">
                    <c:forEach var="sysRes" items="${sysResList}">
                        <label for="" class="extClass2"><input type="checkbox" name="res_list" value="${sysRes.code}"
                                             <c:if test="${sysRes.checked}">checked="true" </c:if> >${sysRes.name}</label>
                    </c:forEach>
                </li>
                <li>
                    <span>备注</span>
                </li>
                <li>
                    <textarea name="remark" id="remark"></textarea>
                </li>
            </ul>
        </div>
        <input type="hidden" id="res" name="res" value="">
        <input type="hidden" id="bizNo" name="bizNo" value="${sysAcct.bizNo}">
        <input type="hidden" id="id" name="id" value="${sysAcct.id}">
        <button type="button" class="btn">确认提交</button>
    </form>
</section>

<!-- end -->
<%@include file="_layout_nav.jsp" %>
<script>
    $(document).ready(function () {
        $('.btn').on('click', function (e) {
            e.preventDefault();
             var resList = $("input[name='res_list']:checked");
            var res = "";
            if (resList && resList.length > 0) {
                resList.each(function () {
                    res = res + "@" + $(this).val();
                });
            }
            $('#res').val(res);
            $.post(_CTX + "/sys/sysAcct/resetRight.json",
                    $('#theForm').serialize(),
                    function (data) {
                        if (data) {
                            if (data.success) {
                                alert(data.msg);
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