<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>申请提现</title>
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
    <a href="#">申请提现</a>
    <div class="pull-right"><button type="button" class="btn btn-mystyle btn-sm" onclick="if(window.opener){window.close()}else{history.go(-1)}">返回</button></div>
</div>
<!--页面标头end-->

<!--主体内容start-->
<div class="container-fluid">
    <form method="post" class="ajax_form validate" novalidate="novalidate">
        <table class="table table-bordered  table-header table-hover margin-top">
            <tbody>
            <tr>
                <td align="right">
                    提现商家:
                </td>
                <td>
                    <%--<input name="title" type="text" class="form-control" value="as" style="width: 300px;" required="" data-original-title="" title="">--%>
                    <span >${biz.name}</span>
                </td>
            </tr>
            <tr>
                <td align="right" style="width: 150px;">
                    状态:
                </td>
                <td>
                    <span class="ri"  style="overflow: hidden; max-width: 60%;">${bizTake.stateStr}</span>
                </td>
            </tr>
            <tr>
                <td align="right">
                    提现金额:
                </td>
                <td>
                    <%--<input name="title" type="text" class="form-control" value="as" style="width: 300px;" required="" data-original-title="" title="">--%>
                    <span >${bizTake.amount}</span>
                </td>
            </tr>
            <tr>
                <td align="right">
                    提现方式:
                </td>
                <td>
                    <%--<input name="title" type="text" class="form-control" value="as" style="width: 300px;" required="" data-original-title="" title="">--%>
                        <c:if test="${bizTake.takeWay==2}">
                            <span >微信钱包</span>
                        </c:if>
                        <c:if test="${bizTake.takeWay==1}">
                            <span >银行卡</span>
                        </c:if>
                        <c:if test="${bizTake.takeWay==3}">
                            <span>自动转账</span>
                        </c:if>
                        <input type="hidden" id="takeWay" name="takeWay" value="${bizTake.takeWay}"/>
                </td>
            </tr>
            <tr>
                <td align="right">
                    真实姓名:
                </td>
                <td>
                    <%--<input name="title" type="text" class="form-control" value="as" style="width: 300px;" required="" data-original-title="" title="">--%>
                    <span >${bizTakeBank.name}${bizTakeWwlt.name}</span>
                </td>
            </tr>
            <tr>
                <td align="right">
                    手机号码:
                </td>
                <td>
                    <%--<input name="title" type="text" class="form-control" value="as" style="width: 300px;" required="" data-original-title="" title="">--%>
                    <span >${biz.mobile}</span>
                </td>
            </tr>
            <tr>
                <td align="right">
                    银行卡号:
                </td>
                <td>
                    <%--<input name="title" type="text" class="form-control" value="as" style="width: 300px;" required="" data-original-title="" title="">--%>
                    <span >${bizTakeBank.bankNo}</span>
                </td>
            </tr>
            <tr>
                <td align="right">
                    开户行地址:
                </td>
                <td>
                    <%--<input name="title" type="text" class="form-control" value="as" style="width: 300px;" required="" data-original-title="" title="">--%>
                    <span class="ri">${bizTakeBank.bankAddr}</span>
                </td>
            </tr>
            <tr>
                <td align="right">
                    微信账号:
                </td>
                <td>
                    <%--<input name="title" type="text" class="form-control" value="as" style="width: 300px;" required="" data-original-title="" title="">--%>
                    <span class="ri">${bizTakeWwlt.wxAcct}</span>
                </td>
            </tr>
            <tr>
                <td align="right">
                    昵称:
                </td>
                <td>
                    <%--<input name="title" type="text" class="form-control" value="as" style="width: 300px;" required="" data-original-title="" title="">--%>
                    <span class="ri">${bizTakeWwlt.nick}</span>
                </td>
            </tr>
            <c:if test="${bizTake.state==null || bizTake.state==0|| bizTake.state==5}">
            <tr>
                <td align="right"><span class="red">*</span>
                    打款凭证号:
                </td>
                <td>
                        <input name="tsno" type="text" class="form-control" value="tsno" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right"><span class="red">*</span>
                    手续费:
                </td>
                <td>
                    <input name="mtcCost" type="text" class="form-control" value="mtcCost" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            <tr>
                <td align="right"><span class="red">*</span>
                    实提金额:
                </td>
                <td>
                    <input name="realAmount" type="text" class="form-control" value="realAmount" style="width: 300px;" required data-original-title="" title="">
                </td>
            </tr>
            </c:if>
            <c:if test="${bizTake.state==1}">
            <tr>
                <td align="right"><span class="red">*</span>
                    打款凭证号:
                </td>
                <td>
                    <span class="ri">${bizTake.tsno}</span>
                </td>
            </tr>
            <tr>
                <td align="right"><span class="red">*</span>
                    手续费:
                </td>
                <td>
                    <span class="ri">${bizTake.mtcCost}</span>
                </td>
            </tr>
            <tr>
                <td align="right"><span class="red">*</span>
                    实提金额:
                </td>
                <td>
                    <span class="ri">${bizTake.realAmount}</span>
                </td>
            </tr>
            </c:if>
            <td align="right"><span class="red">*</span>
                备注说明:
            </td>
            <td>
                <span class="ri">${bizTake.expln}</span>
            </td>
            <c:if test="${bizTake.state==null || bizTake.state==0|| bizTake.state==5}">
            <tfoot>
            <tr>
                <td>
                    <button id="saveinfo" type="submit" class="btn btn-primary">付款确认</button>
                </td>
            </tr>
            </tfoot>
            <input type="hidden" name="id" id="id" class="style01" value="${bizTake.id}">
            </c:if>
        </table>
        <!--主体内容end-->

    </form>
</div>
<script>
    function chk_inputvlaue() {
        if($("input[name='input_type']:checked").val()==1){
            $("#default_value").show();
        }else{
            $("#default_value").hide();
        }
    }
    $("input[name='input_type']").click(function () {
        chk_inputvlaue();
    })
    chk_inputvlaue();
</script>
<input id="operaction" type="hidden" value="">
<input id="operactionall" type="hidden" value="">

</body></html>