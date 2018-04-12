<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">修改密码<a href="${ctx}/navCtrl/biz" class="back"><i class="icon iconfont">&#xe67f;</i></a>
    </h1>
</header>
<!-- 订单列表搜索 -->
<section class="mart2b2 clearfix">
    <!--      <div class="center-wrapper">
              <div class="cw-top">
                  <i><img src="img/bg.jpg" alt=""></i>
                  <h2>wedngkj</h2>
                  <p>2伟鼎科技</p>
              </div>
         </div> -->
    <div class="para-set clearfix">
        <ul>
            <%--         <li>
                        <span>验证码</span>
                        <input type="text" class="ri" placeholder="请输入验证码">
                        <button class="ri">获取验证码</button>
                     </li>--%>
            <li>
                <span>旧密码</span>
                <input type="password" id="oldPwd" name="oldPwd" class="ri" placeholder="请输入旧密码">
            </li>
            <li>
                <span>新密码</span>
                <input type="password" id="newPwd" name="newPwd" class="ri" placeholder="请输入新密码">
            </li>
            <li>
                <span>确认新密码</span>
                <input type="password" id="newPwd2" name="newPwd2" class="ri" placeholder="请再一次输入新密码">
            </li>
        </ul>
    </div>
    <button type="button" class="btn">确认修改</button>

</section>
<%@include file="_layout_nav.jsp" %>

<!-- end -->
<script>
    $(document).ready(function () {
        $('.btn').on('click', function (e) {
            e.preventDefault();
            var oldPwd = $('#oldPwd').val();
            var newPwd = $('#newPwd').val();
            var newPwd2 = $('#newPwd2').val();
            if (oldPwd == '') {
                alert('请填写旧密码');
                return;
            }
            if (newPwd == '') {
                alert('请填写新密码');
                return;
            }
            if (newPwd.length < 6) {
                alert('密码长度最短6位，且建议数字和字母组合！');
                return;
            }
            if (newPwd != newPwd2) {
                alert('两次输入密码不一致');
                return;
            }
            $.post(_CTX + "/biz/bizPwdUp.json",
                    {oldPwd: oldPwd, newPwd: newPwd},
                    function (data) {
                        if (data) {
                            if (data.success) {
                                alert("您的密码已经修改成功，可重新登录。");
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