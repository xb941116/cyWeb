<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<script>
    var _CTX = '${ctx}';
</script>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>商家权限修改</title>
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
<body>
<!--页面标头start-->
<div class="ctabs">
    <a href="#">商家权限修改</a>
    <div class="pull-right"><button type="button" class="btn btn-mystyle btn-sm" onclick="if(window.opener){window.close()}else{history.go(-1)}">返回</button></div>
</div>
<!--页面标头end-->

<!--主体内容start-->
<div class="container-fluid">
    <form method="post" class="ajax_form validate" novalidate="novalidate" id="theForm">
        <input type="hidden" id="res" name="res" value="">
        <input type="hidden" id="bizNo" name="bizNo" value="${sysAcct.bizNo}">
        <input type="hidden" id="id" name="id" value="${sysAcct.id}">
        <table class="table table-bordered  table-header table-hover margin-top">
            <tbody>
            <tr>
                <td align="right">
                    商家名称:
                </td>
                <td>
                    <span >${bizName}</span>
                </td>
            </tr>
            <tr>
                <td align="right" style="width: 150px;">
                    商家主账号:
                </td>
                <td>
                    <span class="ri"  style="overflow: hidden; max-width: 60%;">${sysAcct.acct}</span>
                </td>
            </tr>
            <tr>
                <td align="right"><span class="C_ss">*</span>
                    权限分配:
                </td>
                <td>
                    <c:forEach var="sysRes" items="${sysResList}">
                        <label for="" class="extClass2">
                            <input type="checkbox" name="res_list" value="${sysRes.code}" <c:if test="${sysRes.checked}">checked="true" </c:if> >${sysRes.name}
                        </label>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td align="right">
                    备注:
                </td>
                <td>
                    <textarea name="remark" style="width: 600px;height: 100px;" required="" data-msg-required="" data-original-title="" title=""></textarea>
                </td>
            </tr>
            <tfoot>
            <tr>
                <td align="right"></td>
                <td colspan="3">
                    <button type="submit" id="ab" class="btn btn-primary">确认提交</button>
                </td>
            </tr>
            </tfoot>
        </table>
        <!--主体内容end-->
    </form>
</div>
<script>
    $(document).ready(function () {
        $('#ab').on('click', function (e) {
            e.preventDefault();
            var resList = $("input[name='res_list']:checked");
            var res = "";
            if (resList && resList.length > 0) {
                resList.each(function () {
                    res = res + "@" + $(this).val();
                });
            }
            $('#res').val(res);
            $.post(_CTX + "/sys/sysAcct/resetRight.json",
                    $('#theForm').serialize(),
                    function (data) {
                        if (data) {
                            if (data.success) {
                                alert(data.msg);
                                window.location.href = _CTX + "/biz/query";
                            } else {
                                alert(data.msg);
                            }
                        }
                    }, "json");
        });
    });
</script>
</body></html>