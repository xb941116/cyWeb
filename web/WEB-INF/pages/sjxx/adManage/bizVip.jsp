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

    <title>${pager.params.bizNo}广告屏蔽</title>
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
    <a href="#">${pager.params.bizNo}广告屏蔽</a>
    <div class="pull-right"><button type="button" class="btn btn-mystyle btn-sm" onclick="if(window.opener){window.close()}else{history.go(-1)}">返回</button></div>
</div>
<!--页面标头end-->

<div class="container-fluid">
    <div class="info-center">
        <!--页面标头end-->
        <form class="ajax_form validate" method="post" action="${ctx}/biz/bizVip/createPaySucess" id="theForm" novalidate="novalidate">
            <input type="hidden" name="bizNo" value="${pager.params.bizNo}">
            <!--操作栏start-->
            <div class="search-box row">
                <div class="col-lg-6">
                </div>
                <div class="col-lg-4">
                    <div class="btn-group pull-right">
                        <input type="text" name="year" id="year" class="form-control pull-left" placeholder="请输入时间(单位年)" style="width:220px; margin:0px 6px;">
                        <a href="javascript:void(0)" onclick="toSubmit()" class="btn btn-default add"><span class="glyphicon glyphicon-plus"></span>添加</a>
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
                            <td width="15%">商家编号</td>
                            <td width="15%">时间（年）</td>
                            <td width="20%">起始时间</td>
                            <td width="20%">结束时间</td>
                            <td width="20%">创建时间</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="biz" items="${pager.recordList}">
                            <tr>
                                <td width="15%">${biz.bizNo}</td>
                                <td width="15%">${biz.yearLength}</td>
                                <td width="20"><fmt:formatDate value="${biz.startTime}" pattern="yyyy-MM-dd"/></td>
                                <td width="20%"><fmt:formatDate value="${biz.endTime}" pattern="yyyy-MM-dd"/></td>
                                <td width="20%"><fmt:formatDate value="${biz.crtime}" pattern="yyyy-MM-dd"/> </td>
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
<script src='${ctx}/static/js/fileupload_js/LocalResizeIMG.js'></script>
<script src='${ctx}/static/js/fileupload_js/patch/mobileBUGFix.mini.js'></script>
<script src='${ctx}/static/js/fileupload_js/dist/lrz.bundle.js'></script>
<script>
    function toSubmit() {

        var year = /^(-|\+)?\d+$/;
        if(!year.test($("#year").val()))
        {
            alert('请输入有效的年份！');
            return false;
        }

        //$("#theForm").submit();
    }
</script>
</body></html>