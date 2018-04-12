<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">位置上报<a href="${ctx}/navCtrl/prod" class="back"><i class="icon iconfont">&#xe67f;</i></a></h1>
</header>

<section class="mart2b2 clearfix">
<form id="form" method="post">
    <div class="para-set clearfix">
        <ul>
            <%--<li>  <a href="#" class="qrcode">二维码扫描</a></li>--%>
            <input type="hidden" name="id" value="${prodInstlPosModel.id}"/>
            <c:if test="${not empty latitude}">

                <input type="hidden" name="lat" value="${latitude}"/>
                <input type="hidden" name="lng" value="${longitude}"/>

            </c:if>
            <c:if test="${empty latitude}">

                <input type="hidden" name="lat" value="${prodInstlPosModel.lat}"/>
                <input type="hidden" name="lng" value="${prodInstlPosModel.lng}"/>

            </c:if>
            <li><span>重新获取经纬度</span><a href="/custWeiXin/getLocation?wayFlag=3_${prodInstlPosModel.id}"><input type="button" class="input-style ri" style="text-align: center;" value="重新获取"></a></li>
            <li><span>模板标题</span><input type="text" id="title" name="title" value="${prodInstlPosModel.title}" style="width: 8.6rem;" class="ri" placeholder="请输入模板标题"></li>
            <li><span>安装地址</span><input type="text" id="addr" name="addr" value="${prodInstlPosModel.addr}" style="width: 8.6rem;" class="ri" placeholder="请输入安装地址"></li>
            <li><span>省份</span>
                <select name="prov" id="prov" class="ri">
                    <c:forEach var="prov" items="${cfgAreaList}">
                        <option value="${prov.no}">${prov.areaName}</option>
                    </c:forEach>
                </select>
                <input type="hidden" class="input" id="provName" name="provName" size="20"
                       maxlength="40" value="${prodInstlPosModel.provName}">
            </li>
            <li><span>地市</span>
                <select name="city" id="city" class="ri">
                </select>
                <input type="hidden" class="input" id="cityName" name="cityName" size="20"
                       maxlength="40" value="${prodInstlPosModel.cityName}">
            </li>
            <li><span>区县</span>
                <select name="dist" id="dist" class="ri">
                </select>
                <input type="hidden" class="input" id="distName" name="distName" size="20"
                       maxlength="40" value="${prodInstlPosModel.distName}">
            </li>
<%--
            <li><span>经纬度值</span><input type="text" class="ri" placeholder="请设置经纬度值"></li>
--%>

        </ul>
    </div>
    <button type="button" class="btn">确认提交</button>
</form>
</section>
<script>
    $.ajaxSettings.async = false;
    $(function () {
        $('.btn').on('click', function (e) {
            e.preventDefault();
            var addr = $('#addr').val();
            if ( addr == '') {
                alert('请填写地址');
                return;
            }
            var provName = $("#prov").find("option:selected").text();
            var cityName = $("#city").find("option:selected").text();
            var distName = $("#dist").find("option:selected").text();
            $('#provName').val(provName);
            $('#cityName').val(cityName);
            $('#distName').val(distName);
            $.post(_CTX + "/prod/prodInstlPosModel.json",
                    $('#form').serialize(),
                    function (data) {
                        if (data) {
                            if (data.success) {
                                alert(data.msg);
                            } else {
                                alert(data.msg);
                            }
                        }
                    }, "json");
        });
        $("#prov").change(function () {
            $("#city").empty();
            loadArea(2,null);
        });
        $("#city").change(function () {
            $("#dist").empty();
            loadArea(3,null);

        });

       // window.setTimeout(loadArea(2, null), 200);

    });

    function loadArea(level, areaCode) {
        if (level == 2) {
            var topno = $("#prov").val();
            $("#city").empty();
            $("#dist").empty();
            $.getJSON(_CTX + "/cfg/cfgArea/queryArea.json", {"topno": topno, "areaLevel": "2"}, function (data) {
                var no = null;
                $.each(data, function (i, field) {
                    var selected = field.no == "${pbaVillage.city}" ? "selected" : "";
                    $("#city").append("<option value='" + field.no + "' " + selected + ">" + field.areaName + "</option>");
                    if (i == 0) {
                        no = field.no;
                    }
                    if (selected == 'selected') {
                        no = field.no;
                    }
                });
                loadArea(3, no);
            });
        } else if (level == 3) {
            var topno = $("#city").val();
            if (areaCode != null) topno = areaCode;
            $("#dist").empty();
            $.getJSON(_CTX + "/cfg/cfgArea/queryArea.json", {"topno": topno, "areaLevel": "3"}, function (data) {
                $.each(data, function (i, field) {
                    var selected = field.no == "${pbaVillage.dist}" ? "selected" : "";
                    $("#dist").append("<option value='" + field.no + "'  " + selected + ">" + field.areaName + "</option>");
                });
            });
        }
    }
    $(function () {
        var provNo="${prodInstlPosModel.prov}";
        var cityNo="${prodInstlPosModel.city}";
        var distNo="${prodInstlPosModel.dist}";
        $("#city").empty();
        $("#dist").empty();
        if (provNo!=""&&provNo!=null){
            $("#prov").val("${prodInstlPosModel.prov}");
            loadArea(2,"${prodInstlPosModel.prov}");
        }else {
            loadArea(2,null);
        }
        if (cityNo!=""&&cityNo!=null){
            $("#city").val("${prodInstlPosModel.city}");
            loadArea(3,"${prodInstlPosModel.city}");
        }else {
            loadArea(3,null);
        }
        if (distNo!=""&&distNo!=null){
            $("#dist").val("${prodInstlPosModel.dist}");
        }
    });
</script>
</body>
</html>