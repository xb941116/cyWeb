<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<script>
    var _CTX = '${ctx}';
</script>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>申诉建议</title>
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
    <a href="#">申诉建议</a>
    <div class="pull-right"><button type="button" class="btn btn-mystyle btn-sm" onclick="if(window.opener){window.close()}else{history.go(-1)}">返回</button></div>
</div>
<!--页面标头end-->

<!--主体内容start-->
<div class="container-fluid">
    <form method="post" class="ajax_form validate" novalidate="novalidate" id="form">
        <table class="table table-bordered  table-header table-hover margin-top">
            <tbody>
            <tr>
                <td align="right" style="width: 100px;"><span class="C_ss">*</span>
                    申诉建议:
                </td>
                <td >
                    <textarea name="advise" id="advise" rows="10" cols="200" required="" placeholder="留下你的宝贵意见，让我们做的更好！" data-msg-required="" data-original-title="" title=""></textarea>
                </td>
            </tr>
            <tfoot>
            <tr>
                <td align="right"></td>
                <td colspan="3">
                    <button type="submit" class="btn btn-primary">确认提交</button>
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
            var advise = $('#advise').val();
            if (advise == '') {
                alert('请填写申诉建议');
                return;
            }
            $.post(_CTX + "/biz/bizAdviseAdd.json",
                    {advise:advise},
                    function (data) {
                        if (data) {
                            if (data.success) {
                                alert("谢谢，您的申诉建议已经提交成功！针对您的反馈我们会抓紧处理。");
                                history.go(0);
                            } else {
                                alert(data.msg);
                            }
                        }
                    }, "json");
        });
    });
</script>

</body></html>