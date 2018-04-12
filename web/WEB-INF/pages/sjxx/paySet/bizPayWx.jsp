<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<script>
    var _CTX = '${ctx}';
</script>

<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>微信支付配置信息</title>
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
        <input type="hidden" id="id" name="id" value="${bizWx.id}">
        <table class="table table-bordered  table-header table-hover margin-top">
            <tbody>
            <tr>
                <td align="right"><span class="C_ss">*</span>
                    应用ID:
                </td>
                <td>
                    <input name="appId" id="appId" type="text" value="${bizWx.appId}" class="form-control" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right" style="width: 150px;"><span class="C_ss">*</span>
                    应用秘钥:
                </td>
                <td>
                    <input name="appSkey" id="appSkey" type="text" value="${bizWx.appSkey}"class="form-control" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right" style="width:350px;"><span class="C_ss">*</span>
                    公众号原始ID:
                </td>
                <td>
                    <input name="pubAcctId"id="pubAcctId" type="text" value="${bizWx.pubAcctId}" class="form-control" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right" style="width:350px;"><span class="C_ss">*</span>
                    手续费:
                </td>
                <td>
                    <input name="giroFee"id="giroFee" type="text" value="${bizWx.giroFee}" class="form-control" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right" style="width:350px;"><span class="C_ss">*</span>
                    转账限额:
                </td>
                <td>
                    <input name="giroQuota"id="giroQuota" type="text" value="${bizWx.giroQuota}" class="form-control" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right" style="width:350px;"><span class="C_ss">*</span>
                    商户号:
                </td>
                <td>
                    <input name="bizNum"id="bizNum" type="text" value="${bizWx.bizNum}" class="form-control" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right" style="width:350px;"><span class="C_ss">*</span>
                    企业名称:
                </td>
                <td>
                    <input name="bizName"id="bizName" type="text" value="${bizWx.bizName}" class="form-control" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>

            <tr>
                <td align="right" style="width:350px;"><span class="C_ss">*</span>
                    API秘钥:
                </td>
                <td>
                    <input name="apiSkey"id="apiSkey" type="text" value="${bizWx.apiSkey}" class="form-control" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>

            <tr>
                <td align="right" style="width:350px;">
                    API证书:
                    <span class="C_ss">上传格式：*.zip</span>
                </td>
                <td>
                    <input type="file" class="style01" id="apiCert" name="file" accept="aplication/zip" value="" <c:if test="${not empty bizWx.apiCert}">style="width: 80%" </c:if>>
                </td>
            </tr>
            <tr>
                <td align="right" style="width:350px;">
                    微信接入验证文件:
                    <span class="C_ss">上传格式：*.txt</span>
                </td>
                <td>
                    <input type="file" class="style01" id="apiCertSkey" name="file2" accept="text/plain" value="" <c:if test="${not empty bizWx.apiCertSkey}">style="width: 80%" </c:if>>
                </td>
            </tr>
            <tr>
                <td align="right" style="width:350px;"><span class="C_ss">*</span>
                    强制关注:
                </td>
                <td>
                    <label class="margin-right"><input type="radio" value="0" name="focus" data-original-title="" title=""  <c:if test="${bizWx.focus==0}">checked="true" </c:if>>否</label>
                    <label class="margin-right"><input type="radio" value="1" name="focus" data-original-title="" title=""  <c:if test="${bizWx.focus==1 || bizWx.focus==null}">checked="true" </c:if>>是</label>
                </td>
            </tr>
            <tfoot>
            <tr>
                <td align="right"></td>
                <td colspan="3">
                    <button type="submit" id="aa" class="btn btn-primary">确认修改</button>
                </td>
            </tr>
            </tfoot>
        </table>
        <!--主体内容end-->

    </form>
</div>
<script>
    $(document).ready(function () {
        $('a.remove').on('click', function (e) {
            e.preventDefault();
            if (confirm("确定要删除吗？")) {
                $.post(_CTX + "/biz/removeCertSkey.json",
                        {},
                        function (data) {
                            if (data) {
                                if (data.success) {
                                    alert("删除成功");
                                    $('em.cert').css('display', 'none');
                                } else {
                                    alert(data.msg);
                                }
                            }
                        }, "json");
            }
        });

        $('#aa').on('click', function (e) {
            e.preventDefault();
            var appId = $('#appId').val();
            var appSkey = $('#appSkey').val();
            var bizNum = $('#bizNum').val();
            var apiSkey = $('#apiSkey').val();
            if (appId == '' || appSkey == '' || bizNum == '' || apiSkey == '') {
                alert('请填写应用ID、应用秘钥、商户号、API秘钥');
                return;
            }
            $.post(_CTX + "/biz/crOrUpBizPayWx.json",
                    $('#theForm').serialize(),
                    function (data) {
                        if (data) {
                            if (data.extMsg.success) {//extMsg
                                alert(data.extMsg.msg);
                                alert("修改成功！");
                                window.location.href = _CTX + "/biz/bizGift/query";
                            } else {
                                alert(data.extMsg.msg);
                            }
                        }
                    }, "json");
            /* var apiCertSkey = $('#apiCertSkey').val();
             if(apiCertSkey == ''||apiCertSkey.length>35){
             alert("请输入正确的API证书秘钥（txt文本的名称）！");
             return;
             }*/

        });
    });
</script>

</body></html>