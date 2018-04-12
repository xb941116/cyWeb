<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">
        添加子账号<a href="${ctx}/biz/subUserList" class="back"><i class="icon iconfont">&#xe67f;</i></a>
    </h1>
</header>

<!-- 订单列表搜索 -->
<section class="mart2b2 clearfix">
    <form id="theForm" method="post">
        <div class="para-set clearfix">
            <ul>
                <li>
                    <span>账号</span>
                    <span class="red">*</span>
                </li>
                <li>
                    <input type="text" id="acct" name="acct" class="style01" value="${sysAcct.acct}"
                           <c:if test="${not empty sysAcct.acct}">readonly</c:if> >
                </li>
                <li>
                    <span>名称</span>
                    <span class="red">*</span>
                </li>
                <li>
                    <input type="text" id="name" name="name" class="style01" value="${sysAcct.name}">
                </li>
                <li>
                    <span>手机</span>
                    <span class="red"></span>
                </li>
                <li>
                    <input type="text" id="mobile" name="mobile" class="style01" value="${sysAcct.mobile}">
                </li>
                <li>
                    <span>邮箱</span>
                    <span class="red"></span>
                </li>
                <li>
                    <input type="text" id="email" name="email" class="style01" value="${sysAcct.email}">
                </li>
                <c:if test="${empty sysAcct.id}">
                    <li>
                        <span>密码</span>
                        <span class="red">*</span>
                    </li>
                    <li>
                        <input type="password" id="pwd" name="pwd" class="style01">
                    </li>
                    <li>
                        <span>重复密码</span>
                    </li>
                    <li>
                        <input type="password" id="pwd2" name="pwd2" class="style01">
                    </li>
                </c:if>
                <li>
                    <span>是否启用</span>
                    <label for="state2" class="ri">否<input type="radio" id="state2" name="state" value="0"
                                                           <c:if test="${sysAcct.state==0}">checked="true" </c:if> ></label>
                    <label for="state1" class="ri">是<input type="radio" id="state1" name="state" value="1"
                                                           <c:if test="${sysAcct.state==1}">checked="true" </c:if> ></label>
                </li>
                <li>
                    <span>权限分配</span>
                </li>
                <li class="clearfix">
                    <c:forEach var="sysRes" items="${sysResList}">
                        <label for="" class="extClass2"><input type="checkbox" name="res_list" value="${sysRes.code}"
                                                               <c:if test="${sysRes.admin==1}">disabled</c:if>
                                                               <c:if test="${sysRes.checked && sysRes.admin ==0 }">checked="true" </c:if> >${sysRes.name}</label>
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
            var id = $('#id').val();
            var acct = $('#acct').val();
            var name = $('#name').val();
            if (acct == '' || name == '') {
                alert('请填写账户、名称');
                return;
            }
            var nickReg = /^(?=.*[a-zA-Z])[a-zA-Z0-9]+/;
            if (!nickReg.test(acct)) {
                alert("账户必须是英文字母或英文字母与数字组合！");
                return;
            }
            if (id == null || id == '') {
                var pwd = $('#pwd').val();
                var pwd2 = $('#pwd2').val();
                if (pwd.length < 6) {
                    alert('密码长度最短6位，且建议数字和字母组合！');
                    return;
                }
                if (pwd != pwd2) {
                    alert('两次输入密码不一致');
                    return;
                }
            }
            var resList = $("input[name='res_list']:checked");
            var res = "";
            if (resList && resList.length > 0) {
                resList.each(function () {
                    res = res + "@" + $(this).val();
                });
            }
            $('#res').val(res);
            $.post(_CTX + "/sys/sysAcct/crOrUpSubUsr.json",
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