<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<script>
    var _CTX = '${ctx}';
</script>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>商家添加</title>
    <script type="text/javascript">var webroot="", res="/res/",themespath="/src/admin/view/themes/";</script>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/byc/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/byc/default.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/byc/ui.css">
    <script type="text/javascript" src="${ctx}/static/js/byc/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/byc/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/byc/send.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/byc/header.js"></script>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
</head>
<style>
    .C_ss{
        color:red;
    }
</style>
<body>
<!--页面标头start-->
<div class="ctabs">
    <a href="#">商家添加</a>
    <div class="pull-right"><button type="button" class="btn btn-mystyle btn-sm" onclick="if(window.opener){window.close()}else{history.go(-1)}">返回</button></div>
</div>
<!--页面标头end-->

<!--主体内容start-->
<div class="container-fluid">
    <form method="post" class="ajax_form validate" novalidate="novalidate" id="theForm">
        <table class="table table-bordered  table-header table-hover margin-top">
            <tbody>
            <tr>
                <td align="right"  style="width: 150px;"><span class="C_ss">*</span>
                    企业名称:
                </td>
                <td>
                    <input name="bizName" id="bizName" type="text" placeholder="重要提示：请输入完整的企业名称"
                           class="form-control" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right"><span class="C_ss">*</span>
                    登录主账号:
                </td>
                <td>
                    <input name="acct" id="acct" type="text" placeholder="英文或英文、数字的组合" class="form-control" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right">
                    登录密码:
                </td>
                <td>
                    <input name="pwd" id="pwd" type="password" placeholder="密码长度不得少于6位" class="form-control" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right"><span class="C_ss">*</span>
                    确认密码:
                </td>
                <td>
                    <input name="pwd2" id="pwd2" type="password"  class="form-control" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right">
                    最低可提现额度:
                </td>
                <td>
                    <input name="takeSet" id="takeSet" type="text" class="form-control" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right"><span class="C_ss">*</span>
                    商家等级:
                </td>
                <td>
                    <label class="margin-right"><input type="radio" value="3" name="grade" data-original-title="" title="" >代理商</label>
                    <label class="margin-right"><input type="radio" value="1" name="grade" data-original-title="" title="" checked ="true" >商家</label>
                </td>
            </tr>
            <tr>
                <td align="right"><span class="C_ss">*</span>
                    权限分配:
                </td>
                <td>
                    <c:forEach var="sysRes" items="${sysResList}">
                        <label  class="extClass2"><input type="checkbox" name="res_list" value="${sysRes.code}" <c:if test="${sysRes.admin==1}">disabled</c:if>
                                                         <c:if test="${sysRes.checked && sysRes.admin ==0 }">checked="true" </c:if> >${sysRes.name}</label>
                    </c:forEach>
                </td>
            </tr>

            <tfoot>
            <tr>
                <td align="right"></td>
                <td colspan="3">
                    <button type="submit" id="ab" class="btn btn-primary" <c:if test="${grade!=3}">disabled="disabled"</c:if>>确认提交</button>
                </td>
            </tr>
            </tfoot>
        </table>
        <!--主体内容end-->

    </form>
</div>
<script>
    $(document).ready(function () {
        $('#ab').on('click', function (e) {
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
                                window.location.href = _CTX + "/biz/query";
                            } else {
                                alert(data.msg);
                            }
                        }
                    }, "json");
        });
    });
</script>

</body></html>