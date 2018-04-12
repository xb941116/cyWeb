<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<script>
    var _CTX = '${ctx}';
</script>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>商家信息修改</title>
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
    <a href="#">商家信息修改</a>
    <div class="pull-right"><button type="button" class="btn btn-mystyle btn-sm" onclick="if(window.opener){window.close()}else{history.go(-1)}">返回</button></div>
</div>
<!--页面标头end-->

<!--主体内容start-->
<div class="container-fluid">
    <form method="post" class="ajax_form validate" novalidate="novalidate" id="theForm">
        <input type="hidden" name="id" value="${biz.id}">
        <table class="table table-bordered  table-header table-hover margin-top">
            <tbody>
            <tr>
                <td align="right"><span class="C_ss">*</span>
                    商家名称:
                </td>
                <td>
                    <input name="name" id="name" type="text" class="form-control" value="${biz.name}" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right" style="width: 150px;">
                    网址:
                </td>
                <td>
                    <input name="webSite" id="webSite" type="text" class="form-control" value="${biz.webSite}" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right" style="width:350px;">
                    手机:
                </td>
                <td>
                    <input name="mobile"id="mobile" type="text" class="form-control" value="${biz.mobile}" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right">
                    邮箱:
                </td>
                <td>
                    <input name="email"id="email" type="text" class="form-control" value="${biz.email}" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right">
                    地址:
                </td>
                <td>
                    <input name="addr" type="text" class="form-control" value="${biz.addr}" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right">
                    服务电话:
                </td>
                <td>
                    <input name="tel" type="text" class="form-control" value="${biz.tel}" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right">
                    API秘钥:
                </td>
                <td>
                    <input id="bizKey" name="bizKey" type="text" class="form-control" value="${biz.bizKey}" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tfoot>
            <tr>
                <td align="right"></td>
                <td colspan="3">
                    <button type="submit" class="btn btn-primary">确认保存</button>
                </td>
            </tr>
            </tfoot>
        </table>
        <!--主体内容end-->

    </form>
</div>
<script>
    $(document).ready(function () {
        $('.btn').on('click', function (e) {
            e.preventDefault();
            var name = $('#name').val();
            if (name == '') {
                alert('请填写商家名称');
                return;
            }
            if (bizKey.length<32){
                alert('API秘钥长度至少为32位！');
                return;
            }
            //$('#theForm').submit();
            $.post(_CTX + "/biz/update.json",
                    $('#theForm').serialize(),
                    function (data) {
                        if (data) {
                            if (data.extMsg.success) {//extMsg
                                alert(data.extMsg.msg);
                                window.location.href = _CTX + "/biz/edit";
                            } else {
                                alert(data.extMsg.msg);
                            }
                        }
                    }, "json");
        });
    });
</script>

</body></html>