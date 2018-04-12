<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<script>
    var _CTX = '${ctx}';
</script>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加子账号</title>
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
    <c:if test="${not empty sysAcct.acct}">
        <a href="#">修改子账号</a>
    </c:if>
    <c:if test="${empty sysAcct.acct}">
    <a href="#">添加子账号</a>
    </c:if>
    <div class="pull-right"><button type="button" class="btn btn-mystyle btn-sm" onclick="if(window.opener){window.close()}else{history.go(-1)}">返回</button></div>
</div>
<!--页面标头end-->

<!--主体内容start-->
<div class="container-fluid">
    <form method="post" class="ajax_form validate" novalidate="novalidate" id="theForm">
        <input type="hidden" id="res" name="res" value="">
        <input type="hidden" id="id" name="id" value="${sysAcct.id}">
        <table class="table table-bordered  table-header table-hover margin-top">
            <tbody>
            <tr>
                <td align="right"  style="width: 150px;"><span class="C_ss">*</span>
                    账号:
                </td>
                <td>
                    <input name="acct" id="acct" type="text" value="${sysAcct.acct}" <c:if test="${not empty sysAcct.acct}">readonly</c:if>
                           class="form-control" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right"><span class="C_ss">*</span>
                    名称:
                </td>
                <td>
                    <input name="name" id="name" type="text" value="${sysAcct.name}" class="form-control" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right">
                    手机:
                </td>
                <td>
                    <input name="mobile" id="mobile" type="text" value="${sysAcct.mobile}" class="form-control" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right">
                    邮箱:
                </td>
                <td>
                    <input name="email" id="email" type="text" value="${sysAcct.email}" class="form-control" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <c:if test="${empty sysAcct.id}">
            <tr>
                <td align="right"><span class="C_ss">*</span>
                    密码:
                </td>
                <td>
                    <input name="pwd" id="pwd" type="password"  class="form-control" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right"><span class="C_ss">*</span>
                    重复密码:
                </td>
                <td>
                    <input name="pwd2" id="pwd2" type="password"  class="form-control" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            </c:if>
            <tr>
                <td align="right"><span class="C_ss">*</span>
                    是否启用:
                </td>
                <td>
                    <label class="margin-right"><input type="radio" value="0" name="state" data-original-title="" title=""  <c:if test="${sysAcct.state==0}">checked="true" </c:if> >否</label>
                    <label class="margin-right"><input type="radio" value="1" name="state" data-original-title="" title=""  <c:if test="${sysAcct.state==1}">checked="true" </c:if> >是</label>
                </td>
            </tr>
            <tr>
                <td align="right"><span class="C_ss">*</span>
                    权限分配:
                </td>
                <td>
                    <c:forEach var="sysRes" items="${sysResList}">
                        <label for="" class="extClass2"><input type="checkbox" name="res_list" value="${sysRes.code}"
                                                               <c:if test="${sysRes.admin==1}">disabled</c:if>
                                                               <c:if test="${sysRes.checked && sysRes.admin ==0 }">checked="true" </c:if> >${sysRes.name}</label>
                    </c:forEach>
                </td>
            </tr>

            <tfoot>
            <tr>
                <td align="right"></td>
                <td colspan="3">
                    <button type="submit" id="ab" class="btn btn-primary">确认提交</button>
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
                                window.location.href = _CTX + "/biz/subUserList";
                            } else {
                                alert(data.msg);
                            }
                        }
                    }, "json");
        });
    });
</script>

</body></html>