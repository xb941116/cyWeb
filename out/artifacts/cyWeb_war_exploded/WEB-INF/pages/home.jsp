<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">个人中心<!-- <a href="#" class="back"><i class="icon iconfont">&#xe67f;</i></a> -->
        <a href="#" class="username"><i class="icon iconfont">&#xe666;</i>&nbsp;${acct}</a>
        <a href="javascript:void(0);" onclick="loginOut()" class="tuichu">退出</a>
    </h1>
</header>
<!-- 订单列表搜索 -->
<section class="mart2b2 clearfix">
    <div class="center-wrapper">
        <div class="cw-top">
            <%--<i>
                <a href="#" class="upload">
                    <c:if test="${empty headImg}"><img id="headImg" src="${ctx}/static/img/bg.jpg" alt="" style="width: 100%;height: 100%"></c:if>
                    <c:if test="${not empty headImg}"><img id="headImg" src="${ctx}/${headImg}" style="width: 100%;height: 100%"></c:if>
                </a>
            </i>--%>
            <h2 style="color: #FEC161">总收入</h2>
            <p style="color: #FEC161">${totalInc}</p>
        </div>
        <!--  -->
        <div class="cw-income-list clearfix">
            <ul>
                <li style="color: #1d53ae;">日收入<br/>${dailyInc}</li>
                <li style="color: #ff7e20;">周收入<br/>${weekInc}</li>
                <li style="color: #358b07;">月收入<br/>${monthInc}</li>
                <li style="color: #1674cb;">充值收入<br/>${rgInc}</li>
            </ul>
        </div>
        <!--  list -->
        <div class="cw-admin-lsit">
            <ul>
                <c:forEach var="sysRes" items="${sysResList}" varStatus="sta">
                    <li
                    <c:if  test="${sta.index==0}"> style='background-color: #FEC161' </c:if>
                    <c:if  test="${sta.index==1}"> style='background-color: #76CAFE' </c:if>
                    <c:if  test="${sta.index==2}"> style='background-color: #76CAFE' </c:if>
                    <c:if  test="${sta.index==3}"> style='background-color: #FEC161' </c:if>
                    <c:if  test="${sta.index==4}"> style='background-color: #FEC161' </c:if>
                    <c:if  test="${sta.index==5}"> style='background-color: #76CAFE' </c:if>
                    ><a href="${ctx}${sysRes.uri}"><i class="icon iconfont">${sysRes.logo}</i>${sysRes.name}</a></li>
                </c:forEach>
            </ul>
        </div>
    </div>
</section>
<%@include file="_layout_nav.jsp" %>
<!-- end -->
<!-- 选择商家 弹出框 -->
<div class="alert-box clearfix">
    <h2>头像上传</h2>
    <dl class="clearfix">
        <dd><input type="file" alt="" id="logoFile" name="logoFile"></dd>
    </dl>
    <p class="clearfix">
        <button onclick="ajaxFileUpload()" class="btnReadSec">上传</button>
        <button class="close">取消</button>
    </p>
</div>
<!-- 选择商家 弹出框 end -->
<script src='${ctx}/static/js/fileupload_js/LocalResizeIMG.js'></script>
<script src='${ctx}/static/js/fileupload_js/patch/mobileBUGFix.mini.js'></script>
<script src='${ctx}/static/js/fileupload_js/dist/lrz.bundle.js'></script>
<script>

    var btnClose = $('.close');
    var alertBox = $('.alert-box');
    $('a.upload').on('touchstart click', function (e) {
        e.preventDefault();
        alertBox.css('display', 'block');
    });
    btnClose.on('touchstart click', function (e) {
        e.preventDefault();
        alertBox.hide();
    });

    function timer(time) {
        var btn = $("button.btnReadSec");
        btn.attr("disabled", "true");  //按钮禁止点击
        btn.html(time <= 0 ? "上传" : "上传中...");
        if (time <= 0) {
            btn.removeAttr("disabled");  //按钮禁止点击
        } else {

        }
    }
    function ajaxFileUpload() {
        if ($('#logoFile').val() == '') {
            alert("图片为空");
            return false;
        } else {
            var file = $('#logoFile').val();
            var mime = file.toLowerCase().substr(file.lastIndexOf("."));
            if (mime != ".jpg"
                    && mime != ".jpeg"
                    && mime != ".png"
                    && mime != ".gif") {
                $('#logoFile').val('')
                alert("图片格式不正确!");
                return false;
            }
        }
        timer(30);
        //以图片宽度为1000进行压缩
        //alert($("#file1")[0].files[0]);
        lrz($("#logoFile")[0].files[0], {
            width: 300
        }).then(function (rst) {
            //压缩后异步上传
            //alert(rst);
            $.ajax({
                url: '${ctx}/sys/sysAcct/uploadLogo.json',
                type: "POST",
                data: {
                    filename: rst.origin.name,
                    fileLength: rst.base64.length,
                    imgdata: rst.base64//压缩后的base值
                },
                dataType: "json",
                cache: true,
                async: false,
                success: function (data) {
                    if (data.success) {
                        var alertBox = $('.alert-box');
                        alertBox.hide();
                        alert('上传图片成功');
                        $("#headImg").attr('src', _CTX + data.msg);
                    } else {
                        alert(data.msg);
                    }
                    timer(0);
                },
                error: function () {
                    timer(0);
                    alert("上传出错,请稍后重新上传！");
                }
            });
        }).catch(function (err) {
            // 处理失败会执行
            timer(0);
            alert('上传失败,可能是网络问题，请稍后重新上传！');
        }).always(function () {
            //return false;
        });
        //
        return false;
    }

    function is_weixin(){
        var ua = navigator.userAgent.toLowerCase();
        if(ua.match(/MicroMessenger/i)=="micromessenger") {
            return true;
        } else {
            return false;
        }
    }
    function loginOut() {
        if (is_weixin()){
            window.location.href="${ctx}/loginCtrl/logout?isWeiXin=true";
        }else {
            window.location.href="${ctx}/loginCtrl/logout?isWeiXin=false";
        }
    }
</script>
</body>
</html>