<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<nav>
    <div class="nav">
        <ul>
            <li><a href="${ctx}/navCtrl/home" class="active"><i class="icon iconfont">&#xe664;</i>首页</a></li>
            <li><a href="${ctx}/navCtrl/prod"><i class="icon iconfont">&#xe62d;</i>设备管理</a></li>
            <li><a href="${ctx}/navCtrl/stat"><i class="icon iconfont">&#xe624;</i>盈收统计</a></li>
            <li><a href="${ctx}/navCtrl/biz"><i class="icon iconfont">&#xe662;</i>商家信息</a></li>
        </ul>
    </div>
</nav>
<script src="${ctx}/static/js/jquery-1.9.1.min.js"></script>

<!-- nav end-->
<script>
    var navActive = $('.nav li');
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
    }
</script>