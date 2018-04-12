<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<c:set var="now" value="<%=new java.util.Date()%>"/>
<body>
<header>
    <h1 class="header fc">商品类型修改<a href="${ctx}/dict/list" class="back"><i class="icon iconfont">&#xe67f;</i></a></h1>
</header>
<section class="mart2b2 clearfix">
    <form id="form" method="post">
        <div class="para-set clearfix">
            <ul>
                <li><span>名称</span><input type="text" id="code" name="code" value="${baseDict.val}" class="ri" placeholder="名称"></li>
                <li>
                    <span>编码</span></span><input type="text" id="val" name="val" value="${baseDict.code}" class="ri" placeholder="编码">
                </li>
                <li><span>排序号</span><input type="number" id="seqs" name="seqs" value="${baseDict.seqs}" class="ri" placeholder="排序号"></li>
            </ul>
        </div>
        <input type="hidden" id="id" name="id" value="${baseDict.id}">
        <button type="button" class="btn">确认提交</button>
    </form>
</section>
<script>
    $(document).ready(function () {
        $('.btn').on('click', function (e) {
            e.preventDefault();
            var code = $('#code').val();
            var val = $('#val').val();
            var seqs = $('#seqs').val();
            if (code == '' || val == '' || seqs == '') {
                alert('请填写名称、编码、排序号');
                return;
            }
            $.post(_CTX + "/dict/insertOrUpdate.json",
                $('#form').serialize(),
                function (data) {
                    if (data) {
                        if (data.success) {
                            alert("操作成功");
                            window.location.href = _CTX + "/dict/list";
                        } else {
                            alert(data.msg);
                        }
                    }
                }, "json");
        });
    });
</script>
</body>
</html>