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

    <title>产品列表</title>
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
    <a href="#">产品列表</a>
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
                        <span>模块类型：</span>
                        <div class="btn-group">
                            <select id="gprsType" name="gprsType" style="width:145px" class="form-control pull-left" data-original-title="" title="">
                                <option value="">全部</option>
                                <option value="0">电子投币</option>
                                <option value="1">洗车机</option>
                                <option value="2">动态口令</option>
                                <option value="6">充电站</option>
                                <option value="3">昌原充电站</option>
                            </select>
                        </div>
                        <button class="btn btn-default" id="search" type="button"><span class="glyphicon glyphicon-search"></span> 查询
                        </button>
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
                            <td width="10%"  class="tr_bg2">产品编号</td>
                            <td width="20%"  class="tr_bg2">模块编号</td>
                            <td width="20%"  class="tr_bg2">商品属性</td>
                            <td width="30%"  class="tr_bg2">操作</td>
                        </tr>
                        </thead>
                        <tbody><c:forEach var="prod" items="${pager.recordList}">
                            <tr>
                                <td width="15%">${prod.prodNo}</td>
                                <td width="15%">${prod.gprsNo}</td>
                                <td width="14%">${prod.prodSetStr}</td>
                                <td width="56%">
                                    <input type="hidden" id="gprsNo2" value="${prod.gprsNo}">
                                    <input type="hidden" id="bizNo" value="${prod.bizNo}">
                                    <c:if test="${prod.prodSet==1}">
                                        <a href="${ctx}/upload/prod/qr/${prod.bizNo}/${prod.gprsNo}.png"
                                           target="_blank" class="margin-small-right imgview" data-original-title="" title="">查看二维码
                                            <%--<img src="${ctx}/upload/prod/qr/${prod.bizNo}/${prod.gprsNo}.png" style="width: 30px;">--%>
                                        </a>
                                        <a href="#" class="input-style" edit="${prod.id}">修改商品属性</a>
                                    </c:if>
                                    <c:if test="${prod.prodSet==null || prod.prodSet==0 }">
                                        <a href="#" class="input-style" prodSet="${prod.gprsNo}">设置商品属性</a>
                                    </c:if>
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
<%--<div class="alert-box clearfix" style="width: 260px;margin: -100px 0 0 -130px;">
    <h2>二维码</h2>
    <dl class="clearfix" style=" padding:0rem 0;margin: 0px auto;">
        &lt;%&ndash;<dt>文件:</dt>&ndash;%&gt;
        <dd>
            <input type="image" alt="" src="" id="qrFile" name="qrFile" style="width:200px; height: 200px">
            <input type="hidden" id="fileName" name="fileName" value="">
        </dd>

    </dl>
    <p class="clearfix">
        <button class="btnReadSec">下载</button>
        <button class="close">关闭</button>
    </p>
</div>--%>
<script>
    $(document).ready(function () {
        function loadSt(prodId) {
            window.location.href = _CTX + "/prod/prodBugRpt?prodId=" + prodId;
        }
        function edSeller(prodId){
            alert(prodId);
            window.location.href = _CTX + "/prod/prodEdit?prodId=" + prodId;
        }

        $("a[edit]").click(function () {
            var prodId = $(this).attr("edit");
            window.location.href = _CTX + "/prod/prodEdit?prodId=" + prodId;
        });

        $("a[prodSet]").click(function () {
            var gprsNo = $(this).attr("prodSet");
            window.location.href = _CTX + "/prod/prodSalePro?gprsNo=" + gprsNo;
        });

        $("button.btnReadSec").click(function () {
            var gprsNo = $('#fileName').val();
            window.location.href = _CTX + "/prod/qrGenDown?gprsNo=" + gprsNo;
        });

    });
</script>
</body></html>