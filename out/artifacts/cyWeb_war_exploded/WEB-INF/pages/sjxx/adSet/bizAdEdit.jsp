<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<script>
    var _CTX = '${ctx}';
</script>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>第${bizAd.adSeat}幅广告配置</title>
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
    <a href="#">微信支付配置信息</a>
    <div class="pull-right"><button type="button" class="btn btn-mystyle btn-sm" onclick="if(window.opener){window.close()}else{history.go(-1)}">返回</button></div>
</div>
<!--页面标头end-->
<!--主体内容start-->
<div class="container-fluid">
    <form method="post" action="" class="ajax_form validate" novalidate="novalidate" id="theForm">
        <table class="table table-bordered  table-header table-hover margin-top">
            <tbody>
            <tr>
                <td align="right"><span class="C_ss">*</span>
                    url链接:
                </td>
                <td>
                    <input name="srcUrl" id="srcUrl" type="text" value="${bizAd.srcUrl}" class="form-control" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right" style="width:350px;"><span class="C_ss">*</span>
                    图片:
                </td>
                <td>
                    <input type="file" class="style01" id="logoFile" name="logoFile"  value="" <c:if test="${not empty bizWx.apiCert}">style="width: 80%" </c:if>>
                </td>
            </tr>
            <tr>
                <td align="right" style="width:350px;">
                    效果:
                </td>
                <td>
                    <%--<input type="file" class="style01" id="apiCertSkey" name="file2" accept="text/plain" value="" <c:if test="${not empty bizWx.apiCertSkey}">style="width: 80%" </c:if>>--%>
                    <a href="${bizAd.srcUrl}"><img name="adImg" style="width: 100%;" src="${bizAd.imgUrl}"></a>
                </td>
            </tr>
            <tfoot>
            <tr>
                <td align="right"></td>
                <td colspan="3">
                    <button type="button" onclick="ajaxFileUpload()" class="btn btn-primary">确认修改</button>
                </td>
            </tr>
            </tfoot>
        </table>
        <!--主体内容end-->
        <input type="hidden"  class="style01" id="id" name="id" value="${bizAd.id}">
        <input type="hidden"  class="style01" id="adSeat" name="adSeat" value="${bizAd.adSeat}">
        <input type="hidden"  class="style01" id="imgUrl" name="imgUrl" value="${bizAd.imgUrl}">
    </form>
</div>
<!-- 选择商家 弹出框 end -->
<script src='${ctx}/static/js/fileupload_js/LocalResizeIMG.js'></script>
<script src='${ctx}/static/js/fileupload_js/patch/mobileBUGFix.mini.js'></script>
<script src='${ctx}/static/js/fileupload_js/dist/lrz.bundle.js'></script>
<!-- end -->

<script>
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
        if("${bizAd.imgUrl}"==$('#imgUrl').val()&&"${bizAd.imgUrl}"!=""){
            $('#theForm').submit();
        }else  if ($('#logoFile').val() == '') {
            alert("图片为空");
            return false;
        }else if($('#srcUrl').val() == ''){
            alert("链接为空");
            return false;
        }else {
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
                url: '${ctx}/biz/bizAd/uploadLogo.json',
                type: "POST",
                data: {
                    adSeat: "${bizAd.adSeat}",
                    filename: rst.origin.name,
                    fileLength: rst.base64.length,
                    imgdata: rst.base64//压缩后的base值
                },
                dataType: "json",
                cache: true,
                async: false,
                success: function (data) {
                    if (data.success) {
                       /* var alertBox = $('.alert-box');
                        alertBox.hide();*/
                        $("#imgUrl").val(data.msg);
                        $.post(_CTX + "/biz/bizAd/update.json",
                                $('#theForm').serialize(),
                                function (data) {
                                    if (data) {
                                        if (data.success) {
                                            alert(data.msg);
                                            window.location.href = _CTX + "/biz/adSet";
                                        } else {
                                            alert(data.msg);
                                        }
                                    }
                                }, "json");
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
    $(document).ready(function () {
        var divWidth = $("img[name=adImg]").width();
        $("img[name=adImg]").css({'height':divWidth/3});
    });
</script>
</body>
</html>
<!-- 订单列表搜索 -->
<%--<section class="mart2b2 clearfix" style="background-color: #ffffff;">
    <form id="theForm" action="${ctx}/biz/bizAd/update" method="post" enctype="multipart/form-data">
        <div class="para-set clearfix">
            <ul>
                <li>
                    <span>url链接</span>
                    <span class="red">*</span>
                </li>
                <li>
                    <input type="text" class="style01" id="srcUrl" name="srcUrl" value="${bizAd.srcUrl}">
                </li>
                <li>
                    <span>图片</span>
                    <span class="red">*</span>
                </li>
                <li>
                    <input type="file" alt="" id="logoFile" name="logoFile">                   &lt;%&ndash; <c:if test="${not empty bizAd.apiCert}">
                        <em class="cert">
                          <img src="${ctx}/static/img/winrar.jpg">&nbsp;&nbsp; <a href="#" class="remove">删除重传</a>
                        </em>
                    </c:if>&ndash;%&gt;
                </li>
                <li>
                    <span>效果</span>
                    <span class="red"></span>
                </li>
                <li>
                    <span>
                    <a href="${bizAd.srcUrl}"><img name="adImg" style="width: 100%;" src="${bizAd.imgUrl}"></a>
                    </span>
                </li>

            </ul>
        </div>
        <input type="hidden"  class="style01" id="id" name="id" value="${bizAd.id}">
        <input type="hidden"  class="style01" id="adSeat" name="adSeat" value="${bizAd.adSeat}">
        <input type="hidden"  class="style01" id="imgUrl" name="imgUrl" value="${bizAd.imgUrl}">
        <br>
        <button type="button" onclick="ajaxFileUpload()" class="btn">确认提交</button>
    </form>
</section>--%>

<%--<%@include file="../../_layout_nav.jsp" %>--%>

