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

    <title>设置商品属性</title>
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
    <a href="#">设置商品属性</a>
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
                    <div class="pull-left ">
                        <input type="text" name="gprsNo" class="form-control pull-left" placeholder="请输入模块编号" style="width:220px; margin:0px 6px;" data-original-title="" title="">
                        <span>属性设置：</span>
                        <div class="btn-group">
                            <select id="prodSet" name="prodSet" style="width:145px" class="form-control pull-left" data-original-title="" title="">
                                <option value="">全部</option>
                                <option value="1">已设置</option>
                                <option value="0">未设置</option>
                            </select>
                        </div>
                        <button class="btn btn-default" id="search" type="button"><span class="glyphicon glyphicon-search"></span> 查询
                        </button>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="btn-group pull-right">
                        <a href="${ctx}/prod/prodModel/add" class="btn btn-default add"><span class="glyphicon glyphicon-plus"></span>添加商品模板</a>
                    </div>
                </div>
            </div>
            <!--操作栏end-->
            <div class="clearfix">
            </div>

            <%-- <section>
                 <div class="selec-input clearfix">
                     <h2>商品模板</h2>
                     <ul style="background-color: #eaeaea;">
                         <c:forEach var="prodModel" items="${prodModelList}">
                             <li>
                                 <label for="">
                                     <input type="radio" name="prodModId" value="${prodModel.id}">${prodModel.prodMdlName}
                                 </label>
                                 <label>
                                     <a href="#" edit="${prodModel.id}">修改</a>
                                 </label>
                                 <label>
                                     <a href="#" del="${prodModel.id}">删除</a>
                                 </label>
                             </li>
                         </c:forEach>
                         <c:if test="${prodModelList!=null && fn:length(prodModelList)%2!=0}">
                             <li><label for="">&nbsp;</label></li>
                         </c:if>
                     </ul>
                 </div>
             </section>--%>

            <!--主体内容start-->
            <section  class="body-bg">
                <div class="selec-list clearfix">
                    <%--<div class="conl">
                        <ul>
                            <li><a href="${ctx}/prod/prodModel/add" class="input-style bgactive">添加商品模板</a></li>
                           &lt;%&ndash; <li><a href="#" class="input-style" onclick="setProdMod()">设置商品属性</a></li>&ndash;%&gt;
                        </ul>
                    </div>--%>
                    <div style="height: 5px;">&nbsp;</div>
                    <table class="table table-bordered table-margin  table-header table-hover">
                        <thead>
                        <tr>
                            <td width="10%"><input title="" value="id[]" class="chkall" type="checkbox" data-original-title="选中/取消"></td>
                            <td width="20%"  class="tr_bg2">编号</td>
                            <td width="25%"  class="tr_bg2">名称</td>
                            <td width="20%"  class="tr_bg2">状态</td>
                            <td width="25%"  class="tr_bg3">购买日期</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="model" items="${pager.recordList}">
                            <tr>
                                <td width="10%"><input value="${model.gprsNo}" name="id[]" type="checkbox" data-original-title="" title=""></td>
                               <%-- <td width="10%"><label for="chk_list"><input type="checkbox" value="${model.gprsNo}" id="chk_list" name="chk_list"></label></td>--%>
                                <td width="20%">${model.gprsNo}</td>
                                <td width="25%">${model.prodName}</td>
                                <td width="20%">
                                    <c:if test="${not empty model.prodSet && '1' eq model.prodSet }">已设置</c:if>
                                    <c:if test="${empty model.prodSet || '0' eq model.prodSet }">未设置</c:if>
                                </td>
                                <td width="25%"><fmt:formatDate pattern="yyyy-MM-dd" value="${model.crtime}"/></td>
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
</body></html>