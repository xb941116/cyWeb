<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx"
       value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<script>
    var _CTX = '${ctx}';
</script>
<html><head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
    <title>后台管理系统</title>
    <script type="text/javascript">var webroot="", res="/res/",themespath="/src/admin/view/themes/";</script>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/byc/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/byc/default.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/byc/ui.css">
    <script src="${ctx}/static/js/jquery-1.9.1.min.js"></script>
    <script src="${ctx}/static/js/scale.js"></script>
</head>
<body>
<div class="container-fluid p-login">
    <div class="p-login-bg">
        <div class="p-login-box">
            <div class="p-login-tit"><b>便易充网络科技有限公司</b><em></em></div>
            <form id="form" action=""method="post"validator="true" >
                <div class="form-group">
                    <input id="acct" name="acct" type="text" placeholder="请输入用户名" class="form-control" required="" data-original-title="" title="">
                </div>
                <div class="form-group">
                    <input id="pwd" name="pwd" type="password" placeholder="请输入密码" class="form-control" autocomplete="off" required="" data-original-title="" title="">
                </div>
                <div class="form-group">
                    <input name="code" id="vcode" type="text" placeholder="请输入验证码" maxlength="4" class="form-control p-login-yzm" required="" data-original-title="" title="">
                    <img src="${ctx}/loginCtrl/code" alt="点击刷新验证码！" height="34" style="cursor:pointer;" id="codes" onclick="change(this)">
                </div>
                <div class="form-group">
                    <button id="logon" class="btn btn-lg btn-primary btn-block">登录</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    var _CTX = '${ctx}';
    function change(srcObj) {
        srcObj.src = _CTX + "/loginCtrl/code?" + Math.random();
    }

    $().ready(function () {
        $('button#logon').click(function (e) {
            e.preventDefault();
            var acct = $('#acct').val();
            var pwd = $('#pwd').val();
            var vcode = $('#vcode').val();
            if (acct == null || acct == '') {
                alert("请输入账号");
                $('acct').focus();
                return;
            }
            if (pwd == null || pwd == '') {
                alert("请输入密码");
                $('pwd').focus();
                return;
            }
            if (vcode == null || vcode == '') {
                alert("请输入验证码");
                $('vcode').focus();
                return;
            }
            $(this).attr('disabled', 'disabled').html('<i></i>登录中...');
            $.post(_CTX + "/loginCtrl/login.json",
                    $('#form').serialize(),
                    function (data) {
                        if (data) {
                            if (data.success) {
                                $('button#logon').removeAttr('disabled').html('登录');
                                window.location.href = _CTX + "/navCtrl/home?wxBizNo=${param.bizNo}";
                            } else {
                                $('button#logon').removeAttr('disabled').html('登录');
                                alert(data.msg);
                                $('#code').attr("src", _CTX + "/loginCtrl/code?" + Math.random());
                            }
                        }
                    }, "json");

        });

        $("#logo").error(function(){
            $(this).attr('src',"${ctx}/static/img/logo6.png");
        });
    });
</script>
<script type="text/javascript" src="${ctx}/static/js/byc/jquery.validate.min.js"></script><script type="text/javascript" src="${ctx}/static/js/byc/validate.js"></script>
<script type="text/javascript">
    if (top.location != self.location) {
        top.location.href = self.location;
    }
    function checkCode(bk){
        $("#vcodeimg").click();
        $("input[name='verify']").val("").focus();

    }
</script>
<input id="operaction" type="hidden" value="">
<input id="operactionall" type="hidden" value="">

</body></html>