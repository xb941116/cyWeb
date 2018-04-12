<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">子账号管理 <a href="${ctx}/navCtrl/biz" class="back"><i class="icon iconfont">&#xe67f;</i></a>
    </h1>
</header>

<section class="mart2b2">

    <!-- 商品类型选择 -->
    <section>
        <form name="theForm" id="theForm" action="${ctx}/biz/subUserList" method="post">
            <div class="selec clearfix">
                <label for="acct">根据账号查找</label>
                <input name="acct" id="acct" class="input-style" placeholder="账号">
                <input type="submit" class="input-style bgactive " value="搜索">
                <a href="${ctx}/sys/sysAcct/add" class="input-style bgactive ri">添加子账号</a>
            </div>
        </form>
    </section>
    <section class="body-bg">
        <div style="height: 5px;">&nbsp;</div>
        <div class="content main-tab-0">
            <table class="selec-list-table">
                <tr>
                    <td width="20%" class="tr_bg1">账号</td>
                    <td width="15%" class="tr_bg2">姓名</td>
                    <td width="15%" class="tr_bg2">状态</td>
                    <td width="15%" class="tr_bg2">是否主账户</td>
                    <td width="35%" class="tr_bg3">操作</td>
                </tr>
                <c:forEach var="sysUsr" items="${pager.recordList}">
                    <tr>
                        <td width="20%">${sysUsr.acct}</td>
                        <td width="15%">${sysUsr.name}</td>
                        <td width="15%">${sysUsr.stateStr}</td>
                        <td width="15%"><c:if test="${sysUsr.mainAcct==1}"><span style="color: #ff0000">主账户</span></c:if><c:if test="${sysUsr.mainAcct==0}">子账户</c:if></td>
                        <td width="35%">
                            <a href="${ctx}/sys/sysAcct/edit?id=${sysUsr.id}">修改</a>
                            &nbsp;&nbsp;&nbsp;
                            <c:if test="${sysUsr.mainAcct==0}"><a href="#" class="remove" id="${sysUsr.id}">删除</a></c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <section class="fc">
                <div class="page clearfix">
                    ${pager.formPageStr}
                </div>
            </section>
        </div>
    </section>
</section>
<%@include file="_layout_nav.jsp" %>
<script>
    $(document).ready(function () {
        $('a.remove').on('click', function (e) {
            e.preventDefault();
            if (confirm("确定要删除吗？")) {
                var id = $(this).attr('id');
                $.post(_CTX + "/sys/sysAcct/remove.json",
                        {id: id},
                        function (data) {
                            if (data) {
                                if (data.success) {
                                    alert("删除成功！");
                                    window.location.href = _CTX + "/biz/subUserList"
                                } else {
                                    alert(data.msg);
                                }
                            }
                        }, "json");
            }

        });
    });
</script>
<!-- end -->
</body>
</html>