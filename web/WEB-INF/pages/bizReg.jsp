<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">商家添加<a href="${ctx}/biz/query" class="back"><i class="icon iconfont">&#xe67f;</i></a></h1>
</header>
<section class="mart2b2 clearfix">
    <div class="para-set clearfix">
        <ul>
            <li>
                <span>企业名称</span>
                <span class="red">*</span>
            </li>
            <li>
                <input type="text" id="bizName" name="bizName" class="style01" placeholder="重要提示：请输入完整的企业名称" value="">
            </li>
            <li>
                <span>登录主账号</span>
                <span class="red">*</span>
            </li>
            <li>
                <input type="text" id="acct" name="acct" class="style01" value="" placeholder="英文或英文、数字的组合">
            </li>
            <li>
                <span>登录密码</span>
                <span class="red">*</span>
            </li>
            <li>
                <input type="password" id="pwd" name="pwd" class="style01" value="" placeholder="密码长度不得少于6位">
            </li>
            <li>
                <span>确认密码</span>
                <span class="red">*</span>
            </li>
            <li>
                <input type="password" id="pwd2" name="pwd2" class="style01" value="">
            </li>
            <li>
                <span>最低可提现额度</span>
                <span class="red">*</span>
            </li>
            <li>
                <input type="number" id="takeSet" name="takeSet" class="style01" value="">
            </li>
            <li>
                <span>商家等级</span>
                <span class="red">*</span>
            </li>
            <li>
                <span class="red">&nbsp;</span>
                <label for="grade1" class="ri extClass2">代理商<input type="radio" id="grade1" name="grade" value="3"></label>
                <label for="grade2" class="ri extClass2">商家<input type="radio" id="grade2" name="grade" value="1" checked ="true"></label>
            </li>
            <li>
                <span>权限分配</span>
            </li>
            <li class="clearfix">
                <c:forEach var="sysRes" items="${sysResList}">
                    <label  class="extClass2"><input type="checkbox" name="res_list" value="${sysRes.code}" <c:if test="${sysRes.admin==1}">disabled</c:if>
                                                           <c:if test="${sysRes.checked && sysRes.admin ==0 }">checked="true" </c:if> >${sysRes.name}</label>
                </c:forEach>
            </li>
        </ul>
    </div>
    <button class="btn btnReadSec" <c:if test="${grade!=3}">disabled="disabled"</c:if>>提 交</button>
</section>
<%@include file="_layout_nav.jsp" %>

<script>
    $(document).ready(function () {
        var _CTX = '${ctx}';
        //提交注册请求
        $('button.btnReadSec').click(function (e) {
            e.preventDefault();
            var acct = $("#acct").val();
            var bizName = $("#bizName").val();
            var pwd = $("#pwd").val();
            var pwd2 = $("#pwd2").val();
            var takeSet = $("#takeSet").val();
            if (acct == '') {
                alert('请输入登录账户');
                $("#acct").focus();
                return;
            }
            if (acct != '') {
                var nickReg = /^(?=.*[a-zA-Z])[a-zA-Z0-9]+/;
                if (!nickReg.test(acct)) {
                    alert("登录账户格只能输入字母或字母与数字的组合！");
                    return;
                    $("#acct").focus();
                } else {
                    var nickLength = acct.length;
                    if (nickLength > 20) {
                        alert("登录账户长度不能超过20位！");
                        return;
                        $("#acct").focus();
                    }
                }
            }
            if (bizName == '') {
                alert('请输入企业名称');
                $("#bizName").focus();
                return;
            }
            if (pwd == '' || pwd2 == '') {
                $("#pwd").focus();
                return;
            }
            if (pwd.length < 6) {
                alert('密码长度最短6位，且建议数字和字母组合！');
                return;
            }
            if (pwd != pwd2) {
                alert("两次输入密码不一致");
                $("#pwd").focus();
                return;
            }
            if (takeSet == '') {
                alert('请输入提现额度');
                $("#takeSet").focus();
                return;
            }
            var resList = $("input[name='res_list']:checked");
            var res = "";
            if (resList && resList.length > 0) {
                resList.each(function () {
                    res = res + "@" + $(this).val();
                });
            }
            var grade = $('input[name="grade"]:checked').val();
            $.post(_CTX + "/regCtrl/crBiz.json",
                    {
                        acct: acct, pwd: pwd, bizName: bizName, grade: grade, res: res
                    },
                    function (data) {
                        if (data) {
                            if (data.success) {
                                alert("商家添加成功！");
                            } else {
                                alert(data.msg);
                            }
                        }
                    }, "json");
        });
    })
</script>
</body>
</html>