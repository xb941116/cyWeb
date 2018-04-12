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
                <a href="${ctx}/navCtrl/stat" class="mystyle-color">盈收统计</a>
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
<script language="javascript">
    $(function () {
        $(".mnc").click(function () {
            /*var a_id = $(this).attr("id");
            if(a_id=='Mntop1'){
                alert(1);
                $(".mnc").removeClass("active");
                $(this).attr("class","active");
            }
            if(a_id=='Mntop2'){
                alert(2);
                $(".mnc").removeClass("active");
                $(this).attr("class","active");
            }
            if(a_id=='Mntop3'){
                alert(3);
                $(".mnc").removeClass("active");
                $(this).attr("class","active");
            }
            if(a_id=='Mntop4'){
                $(".mnc").removeClass("active");
                $(this).attr("class","active");
            }
            if(a_id=='Mntop5'){
                $(".mnc").removeClass("active");
                $(this).attr("class","active");
            }*/
            /*if($(this).hasClass("active"))
                return false;
            $(this).addClass("active").siblings().removeClass("active");
            var mainmenu = $("div.sBox[data-pid='" + $(this).attr("data-id") + "']");
            mainmenu.show();
            $("div.sBox[data-pid!='" + $(this).attr("data-id") + "']").hide();
            $(".navContent:visible").hide();
            $("div.sBox .subNav span.glyphicon-chevron-up").removeClass("glyphicon-chevron-up").addClass("glyphicon-chevron-down");
            $("div.sBox .subNav.sublist-up").removeClass("sublist-up").addClass("sublist-down");
            $curmenu=mainmenu.eq(0).find(".subNav");
            $curmenu.find("span.glyphicon-chevron-down").removeClass("glyphicon-chevron-down").addClass("glyphicon-chevron-up");
            $curmenu.removeClass("sublist-down").addClass("sublist-up");
            $curmenu.next(".navContent").show();*/
            // mainmenu.find(".subNav").eq(0).trigger("click");
        });
        /*左侧导航栏显示隐藏功能*/
        $(".subNav").click(function () {
            /*显示*/
            if($(this).hasClass("sublist-down")){
                $(".subNav.sublist-up").removeClass("sublist-up").addClass("sublist-down");
                $(".navContent:visible").hide(300);
                $("span.glyphicon-chevron-up").removeClass("glyphicon-chevron-up").addClass("glyphicon-chevron-down");
                $(this).find("span.glyphicon-chevron-down").removeClass("glyphicon-chevron-down").addClass("glyphicon-chevron-up");
                $(this).removeClass("sublist-down").addClass("sublist-up");
                $(this).next(".navContent").show(300);
            }else{
                $(this).find("span:first-child").removeClass("glyphicon-chevron-up").addClass("glyphicon-chevron-down");
                $(this).removeClass("sublist-up").addClass("sublist-down");
                $(this).next(".navContent").hide();
            }
        })
        /*左侧导航栏缩进功能*/
        $(".left-main .sidebar-fold").click(function () {
            if ($(this).parent().attr('class') == "left-main left-full") {
                $(this).parent().removeClass("left-full");
                $(this).parent().addClass("left-off");

                $(this).parent().parent().find(".right-product").removeClass("right-full");
                $(this).parent().parent().find(".right-product").addClass("right-off");

            } else {
                $(this).parent().removeClass("left-off");
                $(this).parent().addClass("left-full");

                $(this).parent().parent().find(".right-product").removeClass("right-off");
                $(this).parent().parent().find(".right-product").addClass("right-full");

            }
        })

        /*左侧菜单点击*/
        $(".sBox ul li").click(function () {
            $(".sBox ul li").removeClass("active");
            $(this).addClass("active");
        });
        /*左侧鼠标移入提示功能*/
        $(".sBox ul li").mouseenter(function () {
            if ($(this).find("span:last-child").css("display") == "none") {
                $(this).find("div").show();
            }
        }).mouseleave(function () {
            $(this).find("div").hide();
        });

        $(".mnc:eq(0)").trigger("click");
        //    $(".sBox").find(".subNav").eq(0).trigger("click");
    })
</script>
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
