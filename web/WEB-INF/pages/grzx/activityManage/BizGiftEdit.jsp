<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<script>
    var _CTX = '${ctx}';
</script>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>修改活动</title>
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
    <a href="#">修改活动</a>
    <div class="pull-right"><button type="button" class="btn btn-mystyle btn-sm" onclick="if(window.opener){window.close()}else{history.go(-1)}">返回</button></div>
</div>
<!--页面标头end-->

<!--主体内容start-->
<div class="container-fluid">
    <form method="post" class="ajax_form validate" novalidate="novalidate" id="form">
        <table class="table table-bordered  table-header table-hover margin-top">
            <tbody>
            <tr>
                <input type="hidden" id="id" name="id" value="${bizGift.id}" />
                <td align="right"><span class="C_ss">*</span>
                    达标额度:
                </td>
                <td>
                    <input name="coin" id="coin" type="text" class="form-control" value="${bizGift.coin}" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right" style="width: 150px;"><span class="C_ss">*</span>
                    赠送积分额度:
                </td>
                <td>
                    <input name="money" id="money" type="text" class="form-control" value="${bizGift.money}" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right" style="width:350px;"><span class="C_ss">*</span>
                    限制次数(单位:会员/次,设置为0即不限制次数):
                </td>
                <td>
                    <input name="totalMbr"id="totalMbr" type="text" class="form-control" value="${bizGift.totalMbr}" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right"><span class="C_ss">*</span>
                    活动总次数(设置为0即不限制次数):
                </td>
                <td>
                    <input name="totalAll"id="totalAll" type="text" class="form-control" value="${bizGift.totalAll}" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right"><span class="C_ss">*</span>
                    活动排序:
                </td>
                <td>
                    <input name="sort" type="text" class="form-control" value="${bizGift.sort}" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right">
                    开启状态:
                </td>
                <td>
                    <label class="margin-right"><input type="radio" value="0" name="state" data-original-title="" title=""  <c:if test="${bizGift.state==0}">checked="true" </c:if>>否</label>
                    <label class="margin-right"><input type="radio" value="1" name="state" data-original-title="" title=""  <c:if test="${bizGift.state==1 || bizGift.state==null}">checked="true" </c:if>>是</label>
                </td>
            </tr>
            <tr>
                <td align="right"><span class="C_ss">*</span>
                    活动说明:
                </td>
                <td>
                    <textarea name="content" style="width: 300px;height: 100px;" required="" data-msg-required="" data-original-title="" title="">${bizGift.content}</textarea>
                </td>
            </tr>
            <tfoot>
            <tr>
                <td align="right"></td>
                <td colspan="3">
                    <button type="submit" class="btn btn-primary">修改确认</button>
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
            var coin = $('#coin').val();
            var money = $('#money').val();
            var content = $('#content').val();
            var totalMbr = $('#totalMbr').val();
            var totalAll = $('#totalAll').val();
            if (coin == '' || money == '' || content == '' || totalMbr == '' || totalAll == '') {
                alert('信息请填写完整！');
                return;
            }

            $.post(_CTX + "/biz/bizGift/update.json",
                    $('#form').serialize(),
                    function (data) {
                        if (data) {
                            if (data.extMsg.success) {//extMsg
                                alert(data.extMsg.msg);
                                window.location.href = _CTX + "/biz/bizGift/query";
                            } else {
                                alert(data.extMsg.msg);
                            }
                        }
                    }, "json");
        });
    });
</script>

</body></html>