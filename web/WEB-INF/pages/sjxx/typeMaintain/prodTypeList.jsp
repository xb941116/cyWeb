<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx"
       value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<script>
    var _CTX = '${ctx}';
</script>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>商品类型列表</title>
    <script type="text/javascript">var webroot="", res="/res/",themespath="/src/admin/view/themes/";</script>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/byc/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/byc/default.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/byc/ui.css">
    <link href="${ctx}/static/css/byc/WdatePicker.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${ctx}/static/js/byc/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/byc/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/byc/send.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/byc/header.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/byc/WdatePicker.js"></script>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
</head>
<body class="">
<!--页面标头start-->
<div class="ctabs">
    <a href="#">商品类型列表</a>
    <div class="pull-right"><button type="button" class="btn btn-mystyle btn-sm" onclick="if(window.opener){window.close()}else{history.go(-1)}">返回</button></div>
</div>
<!--页面标头end-->

<div class="container-fluid">
    <div class="info-center">
        <!--页面标头end-->
        <form class="ajax_form validate" method="post" action="" novalidate="novalidate">
            <!--操作栏start-->
            <div class="search-box row">
                <div class="col-lg-6">
                </div>
                <div class="col-lg-6">
                    <div class="btn-group pull-right">
                        <a href="${ctx}/dict/add" class="btn btn-default add"><span class="glyphicon glyphicon-plus"></span>添加</a>
                    </div>
                </div>
            </div>
            <!--操作栏end-->
            <div class="clearfix">
            </div>

            <!--主体内容start-->
            <section  class="body-bg">
                <div class="selec-list clearfix">
                    <div style="height: 5px;">&nbsp;</div>
                    <table class="table table-bordered table-margin  table-header table-hover">
                        <thead>
                        <tr>
                            <td width="10%"  class="tr_bg2">商品类型名称</td>
                            <td width="20%"  class="tr_bg2">商品类型编号</td>
                            <td width="20%"  class="tr_bg2">排序编号</td>
                            <td width="10%"  class="tr_bg2">操作</td>
                        </tr>
                        </thead>
                        <tbody><c:forEach var="baseDict" items="${pager.recordList}">
                            <tr>
                                <td width="15%">${baseDict.val}</td>
                                <td width="15%">${baseDict.code}</td>
                                <td width="14%">${baseDict.seqs}</td>
                                <td width="10%">
                                    <a href="#" class="input-style" edit="${baseDict.id}">修改</a>
                                    <a href="#" class="input-style" del="${baseDict.id}">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <section class="fc">
                    <div class="page clearfix">
                        ${pager.formPageStr}
                    </div>
                </section>
            </section>
            <!--主体内容end-->
        </form>
    </div>
</div>
<input id="operaction" type="hidden" value="">
<input id="operactionall" type="hidden" value="">
<script>
    $(document).ready(function () {

        $("a[del]").click(function () {
            var id = $(this).attr("del");
            $.post(_CTX + "/dict/remove.json",
                    {id: id},
                    function (data) {
                        if (data) {
                            if (data.success) {
                                alert("删除成功")
                                window.location.href = _CTX + "/dict/list";
                            } else {
                                alert(data.msg);
                            }
                        }
                    }, "json");

        });


        $("a[edit]").click(function () {
            var id = $(this).attr("edit");
            window.location.href = _CTX + "/dict/edit?id=" + id;
        });

    });
</script>
</body></html>