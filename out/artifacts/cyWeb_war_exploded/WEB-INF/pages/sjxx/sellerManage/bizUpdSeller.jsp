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
    <form method="post" class="ajax_form validate" novalidate="novalidate" id="form">
        <input type="hidden" id="bizNo"  value="${bizNo}">
        <table class="table table-bordered  table-header table-hover margin-top">
            <tbody>
            <tr>
                <td align="right"><span class="C_ss">*</span>
                    密码:
                </td>
                <td>
                    <input name="pwd" id="pwd" type="password" class="form-control" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right" style="width: 150px;"><span class="C_ss">*</span>
                    确认密码:
                </td>
                <td>
                    <input name="pwd2" id="pwd2" type="password" class="form-control" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right" style="width:350px;"><span class="C_ss">*</span>
                    商家类型:
                </td>
                <td>
                    <select id="grade" name="grade" style="width:145px" class="form-control pull-left" data-original-title="" title="">
                        <option value="">请选择</option>
                        <option value="1">商家</option>
                        <option value="3">代理商</option>
                    </select>
                </td>
            </tr>
            <c:if test="${pager.params.isAdmin!=null&&pager.params.isAdmin==true}">
            <tr>
                <td align="right" style="width: 150px;"><span class="C_ss">*</span>
                    LOGO:
                </td>
                <td>
                    <input name="logoFile" id="logoFile" type="file" class="form-control" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            </c:if>
            <tfoot>
            <tr>
                <td align="right"></td>
                <input type="hidden" id="id" name="id" value="${baseDict.id}">
                <td colspan="3">
                    <button type="submit" id="tj"class="btn btn-primary">提交</button>
                </td>
            </tr>
            </tfoot>
        </table>
        <!--主体内容end-->

    </form>
</div>
<script>
    $(document).ready(function () {
        $('#tj').on('click', function (e) {
            e.preventDefault();
            var pwd = $("#pwd").val();
            var pwd2 = $("#pwd2").val();
            var takeSet = $("#takeSet").val();
            var grade = $("#grade").val();
            if(pwd!=null && pwd!='' && pwd!=pwd2){
                alert("两次输入密码不一致");
                return;
            }
            var bizNo = $("#bizNo").val();
            $.post(_CTX + "/biz/upBizInfo.json",
                    {bizNo: bizNo, pwd: pwd, grade: grade, takeSet: takeSet, logo: null},
                    function (data) {
                        if (data) {
                            if (data.success) {
                                alert("修改成功");
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