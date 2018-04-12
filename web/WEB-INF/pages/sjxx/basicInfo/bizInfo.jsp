<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>商家基本信息</title>
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
    <a href="#">商家基本信息</a>
    <div class="pull-right"><button type="button" class="btn btn-mystyle btn-sm" onclick="if(window.opener){window.close()}else{history.go(-1)}">返回</button></div>
</div>
<!--页面标头end-->

<!--主体内容start-->
<div class="container-fluid">
        <div class="info-center">

    <form method="post" class="ajax_form validate" novalidate="novalidate">
        <!--操作栏start-->
        <div class="search-box row">
            <div class="col-lg-6">
            </div>
            <div class="col-lg-6">
                <div class="btn-group pull-right">
                    <a href="${ctx}/biz/edit" class="btn btn-default edit"><span class="glyphicon glyphicon-edit"></span>编辑</a>
                </div>
            </div>
        </div>
        <table class="table table-bordered  table-header table-hover margin-top">
            <tbody>
            <tr>
                <td align="right" style="width: 150px;">
                    商家名称:
                </td>
                <td>
                    <span class="ri"  style="overflow: hidden; max-width: 60%;">${biz.name}</span>
                </td>
            </tr>
            <tr>
                <td align="right">
                    网址:
                </td>
                <td>
                    <span>${biz.webSite}</span>
                </td>
            </tr>
            <tr>
                <td align="right">
                    联系人手机:
                </td>
                <td>
                    <span >${biz.mobile}</span>
                </td>
            </tr>
            <tr>
                <td align="right">
                    邮箱:
                </td>
                <td>
                    <span >${biz.email}</span>
                </td>
            </tr>
            <tr>
                <td align="right">
                    地址:
                </td>
                <td>
                    <span >${biz.addr}</span>
                </td>
            </tr>
            <tr>
                <td align="right">
                    商家LOGO:
                </td>
                <td>
                    <span ><img src="${ctx}/${biz.logo}"  width="50px" height="50px" class="ri"></span>
                </td>
            </tr>
            <tr>
                <td align="right">
                    服务电话:
                </td>
                <td>
                    <span >${biz.tel}</span>
                </td>
            </tr>
            <tr>
                <td align="right">
                    AIP秘钥:
                </td>
                <td>
                    <span>${biz.bizKey}</span>
                </td>
            </tr>
        </table>
        <!--主体内容end-->
    </form>
    </div>
</div>
</body></html>