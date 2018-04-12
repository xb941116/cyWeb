<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/byc/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/byc/default.css">
<script type="text/javascript" src="${ctx}/static/js/byc/jquery.js"></script>
<script type="text/javascript" src="${ctx}/static/js/byc/bootstrap.min.js"></script>

<nav class="nav navbar-default navbar-mystyle navbar-fixed-top">
    <div class="navbar-header">
        <h1 class="cname">控制台</h1>
        <ul class="nav navbar-nav pull-left">
            <li class="li-border mnc" id="Mntop1" data-id="30">
                <a href="${ctx}/navCtrl/home" class="mystyle-color">首页</a>
            </li>
            <li class="li-border mnc" id="Mntop2" data-id="29">
                <a href="${ctx}/navCtrl/prod" class="mystyle-color">设备管理</a>
            </li>
            <li class="li-border mnc" id="Mntop3" data-id="17">
                <a href="${ctx}/navCtrl/stat)" class="mystyle-color">盈收统计</a>
            </li>
            <li class="li-border mnc" id="Mntop4" data-id="51">
                <a href="${ctx}/navCtrl/biz" class="mystyle-color">商家信息</a>
            </li>
            <li class="li-border mnc" id="Mntop5" data-id="61">
                <a href="javascript:void(0)" onfocus="this.blur();" class="mystyle-color">区域管理</a>
            </li>
        </ul>
        <ul class="nav navbar-nav pull-right">
            <li class="dropdown li-border"><a href="#" class="dropdown-toggle mystyle-color" data-toggle="dropdown">${acct}<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="${ctx}/biz/pwdReset" target="main">修改密码</a></li>
                    <li><a href="javascript:void(0);" onclick="loginOut()">安全退出</a></li>
                </ul>
            </li>
            <li class="li-border"><a href="${ctx}/loginCtrl/logout" class="mystyle-color"><span class="glyphicon glyphicon-off"></span></a></li>
        </ul>
    </div>
</nav>
<script src="${ctx}/static/js/jquery-1.9.1.min.js"></script>

<!-- nav end-->
<script>
    /*var navActive = $('.nav');
     var _index = 0;
     navActive.on('click', function () {
     _index = $(this).index();
     localStorage.setItem("nav_index", _index);
     })
     navActive.removeClass('active');
     window.onload = function () {
     if (localStorage.nav_index) {
     _index = parseInt(localStorage.nav_index);
     tabNav();
     } else {
     navActive.eq(_index).addClass('active').siblings().removeClass('active');
     }
     }
     function tabNav() {
     navActive.eq(_index).addClass('active').siblings().removeClass('active');
     localStorage.removeItem();
     }*/
</script>
