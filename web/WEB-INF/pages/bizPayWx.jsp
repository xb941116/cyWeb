<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">微信支付配置信息<a href="${ctx}/biz/paySet" class="back"><i class="icon iconfont">&#xe67f;</i></a>
    </h1>
</header>
<!-- 订单列表搜索 -->
<section class="mart2b2 clearfix">
    <form id="theForm" action="${ctx}/biz/crOrUpBizPayWx" method="post" enctype="multipart/form-data">
        <div class="para-set clearfix">
            <ul>
                <li>
                    <span>应用ID</span>
                    <span class="red">*</span>
                </li>
                <li>
                    <input type="text" class="style01" id="appId" name="appId" value="${bizWx.appId}">
                </li>
                <li>
                    <span>应用秘钥</span>
                    <span class="red">*</span>
                </li>
                <li>
                    <input type="text" class="style01" id="appSkey" name="appSkey" value="${bizWx.appSkey}">
                </li>
                <li>
                    <span>公众号原始ID</span>
                </li>
                <li>
                    <input type="text" class="style01" id="pubAcctId" name="pubAcctId" value="${bizWx.pubAcctId}">
                </li>
                <li>
                    <span>手续费</span>
                    <span class="red">*</span>
                </li>
                <li>
                    <input type="text" class="style01" id="giroFee" name="giroFee" value="${bizWx.giroFee}">
                </li>
                <li>
                    <span>转账限额</span>
                    <span class="red">*</span>
                </li>
                <li>
                    <input type="text" class="style01" id="giroQuota" name="giroQuota" value="${bizWx.giroQuota}">
                </li>
                <li>
                    <span>商户号</span>
                    <span class="red">*</span>
                </li>
                <li>
                    <input type="text" class="style01" id="bizNum" name="bizNum" value="${bizWx.bizNum}">
                </li>
                <li>
                    <span>企业名称</span>
                    <span class="red">*</span>
                </li>
                <li>
                    <input type="text" class="style01" id="bizName" name="bizName" value="${bizWx.bizName}">
                </li>
                <li>
                    <span>API秘钥</span>
                    <span class="red">*</span>
                </li>
                <li>
                    <input type="text" class="style01" id="apiSkey" name="apiSkey" value="${bizWx.apiSkey}">
                </li>
                <li>
                    <span>API证书</span>
                    <span class="red">上传格式：*.zip</span>
                </li>
                <li>
                    <input type="file" class="style01" id="apiCert" name="file" accept="aplication/zip" value="" <c:if test="${not empty bizWx.apiCert}">style="width: 80%" </c:if>>
                   <%-- <c:if test="${not empty bizWx.apiCert}">
                        <em class="cert">
                          <img src="${ctx}/static/img/winrar.jpg">&nbsp;&nbsp; <a href="#" class="remove">删除重传</a>
                        </em>
                    </c:if>--%>
                </li>
                <li>
                    <span>微信接入验证文件</span>
                    <span class="red">上传格式：*.txt</span>
                </li>
                <li>
                    <input type="file" class="style01" id="apiCertSkey" name="file2" accept="text/plain" value="" <c:if test="${not empty bizWx.apiCertSkey}">style="width: 80%" </c:if>>
                </li>

                <%--<li>
                    <span>退款消息模板ID</span>
                </li>
                <li>
                    <input type="text" class="style01" id="templates" name="templates" value="${templates[0]}">
                </li>--%>

                <li>
                    <span>强制关注</span>
                    <label for="focus2" class="ri">否<input type="radio" id="focus2" name="focus" value="0"
                                                           <c:if test="${bizWx.focus==0}">checked="true" </c:if> ></label>
                    <label for="focus1" class="ri">是<input type="radio" id="focus1" name="focus" value="1"
                                                           <c:if test="${bizWx.focus==1 || bizWx.focus==null}">checked="true" </c:if> ></label>
                </li>
                <li  hidden="hidden">
                    <span>是否启用</span>
                    <label for="state2" class="ri">否<input type="radio" id="state2" name="state" value="0"
                                                           <c:if test="${bizWx.state==0}">checked="true" </c:if>></label>
                    <label for="state1" class="ri">是<input type="radio" id="state1" name="state" value="1"
                                                           <c:if test="${bizWx.state==1|| bizWx.state==null}">checked="true" </c:if>></label>
                </li>
            </ul>
        </div>
        <input type="hidden" class="style01" id="id" name="id" value="${bizWx.id}">
        <button type="button" class="btn">确认提交</button>
    </form>
</section>

<!-- end -->
<%@include file="_layout_nav.jsp" %>
<script>
    $(document).ready(function () {
        $('a.remove').on('click', function (e) {
            e.preventDefault();
            if (confirm("确定要删除吗？")) {
                $.post(_CTX + "/biz/removeCertSkey.json",
                        {},
                        function (data) {
                            if (data) {
                                if (data.success) {
                                    alert("删除成功");
                                    $('em.cert').css('display', 'none');
                                } else {
                                    alert(data.msg);
                                }
                            }
                        }, "json");
            }
        });

        $('.btn').on('click', function (e) {
            e.preventDefault();
            var appId = $('#appId').val();
            var appSkey = $('#appSkey').val();
            var bizNum = $('#bizNum').val();
            var apiSkey = $('#apiSkey').val();
            if (appId == '' || appSkey == '' || bizNum == '' || apiSkey == '') {
                alert('请填写应用ID、应用秘钥、商户号、API秘钥');
                return;
            }
           /* var apiCertSkey = $('#apiCertSkey').val();
            if(apiCertSkey == ''||apiCertSkey.length>35){
                alert("请输入正确的API证书秘钥（txt文本的名称）！");
                return;
            }*/
            alert("修改成功！");
            $('#theForm').submit();
        });
    });
</script>

</body>
</html>