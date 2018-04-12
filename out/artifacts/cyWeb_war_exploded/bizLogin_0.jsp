<%--
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx"
       value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<script>
    var _CTX = '${ctx}';
</script>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
    <title>登录</title>
    <link rel="stylesheet" href="${ctx}/static/loginfont/demo.css?v=1">
    <link rel="stylesheet" href="${ctx}/static/loginfont/iconfont.css?v=1">
    <link rel="stylesheet" href="${ctx}/static/css/common.css?v=1">
    <link rel="stylesheet" href="${ctx}/static/css/login_reg.css?v=1">
    <script src="${ctx}/static/js/jquery-1.9.1.min.js"></script>
    <script src="${ctx}/static/js/scale.js"></script>
</head>
<body style="background-color: #ffffff;background-image: url('static/img/version1/sy_bg1.png');background-size: 100% 100%;">
<form id="form"
      action=""
      method="post"
      validator="true">
    <div class="fill-wrapper">
        <div class="login_logo">
            <c:if test="${param.bizNo!=null}">
                <img id="logo" src="${ctx}/upload/biz/logo/${param.bizNo}.png" style="height:100%; width: 100%;" alt="">
            </c:if>
            <c:if test="${param.bizNo==null}">
                <img id="logo" src="${ctx}/static/img/logo6.png" style="height:100%;width: 100%;" alt="">
            </c:if>
        </div>

        <div class="fill-main">
            <ul>
                <li><i class="icon iconfont">&#xe602;</i><input type="text" id="acct" name="acct" placeholder="请输入账号"></li>
                <li><i class="icon iconfont">&#xe61d;</i><input type="password" id="pwd" name="pwd" placeholder="请输入密码"></li>
                <li><i class="icon iconfont">&#xe631;</i><input type="number" id="vcode" name="code" placeholder="请输入验证码">
                    <img src="${ctx}/loginCtrl/code" id="code" alt="" onclick="change(this)">
                </li>
            </ul>
        </div>
        <button id="logon" class="btn" style="background-color: #ebf4f9;color: #026a9f;">登&nbsp;&nbsp;&nbsp;&nbsp;录</button>
        &lt;%&ndash;<p><a href="${ctx}/reset" class="lost-password">忘记密码</a></p>&ndash;%&gt;
    </div>
    <div><a   href="http://www.miitbeian.gov.cn"  style="align:center; font-size:15px;padding-left: 600px; ">豫ICP备16028260号</a></div>
</form>
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
</body>
</html>--%>
