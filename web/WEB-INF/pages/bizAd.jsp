<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">${pager.params.bizNo}广告管理<a href="${ctx}/navCtrl/biz" class="back"><i class="icon iconfont">
        &#xe67f;</i></a></h1>
</header>
<!-- 商品类型选择 -->
<section class="mart2b2 clearfix">
    <section>
        <div class="selec-list clearfix">
            <form name="theForm" id="theForm" action="${ctx}/biz/bizAd/createPaySucess" method="post">
                <input type="hidden" name="bizNo" value="${pager.params.bizNo}">
            <div class="conl clearfix">
                <ul class="clearfix">
                    <li>
                        <label for="year">时间（单位年）：</label>
                        <input type="text" name="year" id="year" class="input-style" style="width:150px;">
                        <input type="button" onclick="toSubmit()" class="input-style bgactive"  value="&nbsp;&nbsp;&nbsp;添加&nbsp;&nbsp;&nbsp;" />
                    </li>
                </ul>
            </div>
            </form>
            <table class="selec-list-table">
                <tr>
                    <td width="15%">商家编号</td>
                    <td width="10%">时间(年)</td>
                    <td width="10%">位置</td>
                    <td width="18%">起始时间</td>
                    <td width="18%">结束时间</td>
                    <td width="18%">创建时间</td>

                </tr>
                <c:forEach var="biz" items="${pager.recordList}">
                    <tr>
                        <td width="15%">${biz.bizNo}</td>
                        <td width="10%">${biz.yearLength}</td>
                        <td width="10%">${biz.adSeat}</td>
                        <td width="18%"><fmt:formatDate value="${biz.startTime}" pattern="yyyy-MM-dd"/></td>
                        <td width="18%"><fmt:formatDate value="${biz.endTime}" pattern="yyyy-MM-dd"/></td>
                        <td width="18%"><fmt:formatDate value="${biz.crtime}" pattern="yyyy-MM-dd"/> </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
<%--qqq
        <div>
            <c:forEach items="${pager.recordList}" var="bizAd">
                <span>
                <a href="${bizAd.srcUrl}"><img name="adImg" style="width: 100%;" src="${bizAd.imgUrl}"></a>
                </span>
            </c:forEach>
        </div>--%>
    </section>
    <section class="fc">
        <div class="page clearfix">
            ${pager.formPageStr}
        </div>
    </section>
    <!-- end -->
</section>
<%@include file="_layout_nav.jsp" %>

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

        $("#theForm").submit();
    }
</script>
</body>
</html>