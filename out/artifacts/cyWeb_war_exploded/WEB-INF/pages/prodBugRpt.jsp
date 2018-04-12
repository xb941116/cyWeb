<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">产品故障状态<a href="javascript:history.go(-1);" class="back"><i class="icon iconfont">&#xe67f;</i></a>
    </h1>
</header>

<!-- 订单列表搜索 -->
<section class="mart2b2 clearfix">
    <div class="para-set clearfix">
        <ul>
            <li><span>模块编号</span>
                <span class="ri">${gprsNo}</span>
            </li>
            <li  style="line-height: 2rem;"><span>产品安装位置</span>
                <span class="ri" style="line-height: 1rem;">${pos}</span>
            </li>
            <li>
                <span>是否到使用期限</span>
                <span class="ri" id="key0"></span>
            </li>
            <li>
                <span>是否缺水</span>
                <span class="ri" id="key1"></span>
            </li>
            <li>
                <span>是否缺洗车液</span>
                <span class="ri" id="key2"></span>
            </li>
            <li>
                <span>是否缺玻璃水</span>
                <span class="ri" id="key3"></span>
            </li>
            <li>
                <span>是否低温</span>
                <span class="ri" id="key4"></span>
            </li>
        </ul>
    </div>
</section>
<!-- end -->
<%@include file="_layout_nav.jsp" %>
<script>
    $().ready(function () {

        var online = '${online}';
        if (online) {
            if (loadSt() == 0) {
                setTimeout(loadSt, 2000);
            }
        }else{
            alert("设备不在线，无法获取设备状态");
        }
        function loadSt() {
            var st = 0;
            $.getJSON(_CTX + "/prod/prodStSearch.json?prodId=${prodId}",
                    function (result) {
                        var val = "";
                        $.each(result, function (i, field) {
                            val = field == 1 ? "<font color='red'>是</font>" : "<font color='green'>否</font>";
                            $('#key' + i).html(val);
                            st = 1;
                        });
                    });
            return st;
        }
    });
</script>
</body>
</html>