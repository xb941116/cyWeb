<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<script>
    var _CTX = '${ctx}';
</script>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>修改密码</title>
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
    <a href="#">修改密码</a>
    <div class="pull-right"><button type="button" class="btn btn-mystyle btn-sm" onclick="if(window.opener){window.close()}else{history.go(-1)}">返回</button></div>
</div>
<!--页面标头end-->

<!--主体内容start-->
<div class="container-fluid">
    <form method="post" class="ajax_form validate" novalidate="novalidate" id="theForm">
        <table class="table table-bordered  table-header table-hover margin-top">
            <tbody>
            <tr>
                <td align="right"><span class="C_ss">*</span>
                    旧密码:
                </td>
                <td>
                    <input name="oldPwd" id="oldPwd" type="password" class="form-control" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right" style="width: 150px;"><span class="C_ss">*</span>
                    新密码:
                </td>
                <td>
                    <input name="newPwd" id="newPwd" type="password" class="form-control" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right" style="width:350px;"><span class="C_ss">*</span>
                    确认新密码:
                </td>
                <td>
                    <input name="newPwd2"id="newPwd2" type="password" class="form-control" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tfoot>
            <tr>
                <td align="right"></td>
                <td colspan="3">
                    <button type="submit" class="btn btn-primary">确认修改</button>
                </td>
            </tr>
            </tfoot>
        </table>
        <!--主体内容end-->

    </form>
</div>
<script>
    $(document).ready(function () {
        $('.btn').on('click', function (e) {
            e.preventDefault();
            var oldPwd = $('#oldPwd').val();
            var newPwd = $('#newPwd').val();
            var newPwd2 = $('#newPwd2').val();
            if (oldPwd == '') {
                alert('请填写旧密码');
                return;
            }
            if (newPwd == '') {
                alert('请填写新密码');
                return;
            }
            if (newPwd.length < 6) {
                alert('密码长度最短6位，且建议数字和字母组合！');
                return;
            }
            if (newPwd != newPwd2) {
                alert('两次输入密码不一致');
                return;
            }
            $.post(_CTX + "/biz/bizPwdUp.json",
                    {oldPwd: oldPwd, newPwd: newPwd},
                    function (data) {
                        if (data) {
                            if (data.success) {
                                alert("您的密码已经修改成功。");
                                window.location.href = _CTX + "/loginCtrl/logout";
                            } else {
                                alert(data.msg);
                            }
                        }
                    }, "json");
        });
    });
</script>

</body></html>