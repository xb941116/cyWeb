<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>订单详情</title>
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
    <a href="#">订单详情</a>
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
                    模块编号:
                </td>
                <td>
                    <%--<input name="title" type="text" class="form-control" value="as" style="width: 300px;" required="" data-original-title="" title="">--%>
                    <span >${ord.gprsNo}</span>
                </td>
            </tr>
            <tr>
                <td align="right" style="width: 150px;">
                    产品安装位置:
                </td>
                <td>
                    <span class="ri"  style="overflow: hidden; max-width: 60%;">${pos}</span>
                </td>
            </tr>
            <tr>
                <td align="right">
                    订单号:
                </td>
                <td>
                    <%--<input name="title" type="text" class="form-control" value="as" style="width: 300px;" required="" data-original-title="" title="">--%>
                    <span >${ord.ordno}</span>
                </td>
            </tr>
            <tr>
                <td align="right">
                    订单金额:
                </td>
                <td>
                    <%--<input name="title" type="text" class="form-control" value="as" style="width: 300px;" required="" data-original-title="" title="">--%>
                    <span >${ord.prodPrice}</span>
                </td>
            </tr>
            <tr>
                <td align="right">
                    支付人账户:
                </td>
                <td>
                    <%--<input name="title" type="text" class="form-control" value="as" style="width: 300px;" required="" data-original-title="" title="">--%>
                    <span >${acct}</span>
                </td>
            </tr>
            <tr>
                <td align="right">
                    支付方式:
                </td>
                <td>
                    <%--<input name="title" type="text" class="form-control" value="as" style="width: 300px;" required="" data-original-title="" title="">--%>
                    <span >${ord.payWayStr}</span>
                </td>
            </tr>
            <tr>
                <td align="right">
                    订单状态:
                </td>
                <td>
                    <%--<input name="title" type="text" class="form-control" value="as" style="width: 300px;" required="" data-original-title="" title="">--%>
                    <span >${ord.stateStr}</span>
                </td>
            </tr>
            <tr>
                <td align="right">
                    下单时间:
                </td>
                <td>
                    <%--<input name="title" type="text" class="form-control" value="as" style="width: 300px;" required="" data-original-title="" title="">--%>
                    <span class="ri"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${ord.crtime}"/></span>
                </td>
            </tr>
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