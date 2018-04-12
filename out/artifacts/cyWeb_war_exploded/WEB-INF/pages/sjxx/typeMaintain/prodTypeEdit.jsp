<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<script>
    var _CTX = '${ctx}';
</script>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>商品类型新增修改</title>
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
    <c:if test="${baseDict!=null}">
    <a href="#">商品类型修改</a>
    </c:if>
    <c:if test="${baseDict==null}">
        <a href="#">商品类型新增</a>
    </c:if>
    <div class="pull-right"><button type="button" class="btn btn-mystyle btn-sm" onclick="if(window.opener){window.close()}else{history.go(-1)}">返回</button></div>
</div>
<!--页面标头end-->

<!--主体内容start-->
<div class="container-fluid">
    <form method="post" class="ajax_form validate" novalidate="novalidate" id="form">
        <table class="table table-bordered  table-header table-hover margin-top">
            <tbody>
            <tr>
                <td align="right"><span class="C_ss">*</span>
                    名称:
                </td>
                <td>
                    <input name="code" id="code" type="text" value="${baseDict.val}" class="form-control" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right" style="width: 150px;"><span class="C_ss">*</span>
                    编码:
                </td>
                <td>
                    <input name="val" id="val" type="text"value="${baseDict.code}" class="form-control" style="width: 300px;" maxlength="4"
                           onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right" style="width:350px;"><span class="C_ss">*</span>
                    排序号:
                </td>
                <td>
                    <input name="seqs"id="seqs" type="text" value="${baseDict.seqs}" class="form-control" style="width: 300px;" maxlength="4"
                           onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" required data-original-title="" title="">
                </td>
            </tr>
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
            var code = $('#code').val();
            var val = $('#val').val();
            var seqs = $('#seqs').val();
            if (code == '' || val == '' || seqs == '') {
                alert('请填写名称、编码、排序号');
                return;
            }
            $.post(_CTX + "/dict/insertOrUpdate.json",
                    $('#form').serialize(),
                    function (data) {
                        if (data) {
                            if (data.success) {
                                alert("操作成功");
                                window.location.href = _CTX + "/dict/list";
                            } else {
                                alert(data.msg);
                            }
                        }
                    }, "json");
        });
    });
</script>

</body></html>