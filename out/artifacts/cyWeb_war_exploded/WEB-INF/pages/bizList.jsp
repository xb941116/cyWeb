<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">商家管理<a href="${ctx}/navCtrl/biz" class="back"><i class="icon iconfont">
        &#xe67f;</i></a></h1>
	<script type="text/javascript">
        $(document).ready(function(){
            $("tr:even").css("background","#FFFFFF");
        });
    </script>
</header>
<!-- 商品类型选择 -->
<section class="mart2b2 clearfix">
    <form name="theForm" id="theForm" action="${ctx}/biz/query" method="post">
        <div class="selec  clearfix">
            <label for="name">商家名称：</label>
            <input type="text" name="name" id="name" value="${pager.params.name}" class="input-style" style="width:150px;">
            <input type="submit" class="input-style bgactive ri" value="搜索">
        </div>
    </form>
    <section class="body-bg">
        <div class="selec-list clearfix">
            <div class="conl clearfix">
                <ul class="clearfix">
                    <li><a href="${ctx}/regCtrl/bizReg" class="input-style bgactive">商家添加</a></li>
                </ul>
            </div>
            <div style="height: 5px;">&nbsp;</div>
            <table class="selec-list-table">
                <tr>
                    <td width="35%" class="tr_bg1">商家名称</td>
                    <td width="15%" class="tr_bg2">商家类型</td>
                    <c:if test="${pager.params.isAdmin!=null&&pager.params.isAdmin==true}">
                        <td width="15%" class="tr_bg2">商家编号</td>
                        <td width="35%" class="tr_bg3">操作</td>
                    </c:if>
                    <c:if test="${pager.params.isAdmin==null||pager.params.isAdmin==false}">
                        <td width="50%" class="tr_bg3">操作</td>
                    </c:if>
                </tr>
                <c:forEach var="biz" items="${pager.recordList}">
                    <tr>
                        <td width="35%" style="line-height: 0.8rem; padding: 5px 0px 5px 0px;">${biz.name}<c:if test="${pager.params.isAdmin!=null&&pager.params.isAdmin==true}">(${biz.acct})</c:if></td>
                        <td width="15%"><c:if test="${biz.grade==3}">代理商</c:if><c:if test="${biz.grade!=3}">商家</c:if></td>

                        <c:if test="${pager.params.isAdmin!=null&&pager.params.isAdmin==true}">
                            <td width="15%">
                                    ${biz.bizNo}
                            </td>
                            <td width="35%">
                                <a href="#" class="input-style" edit="${biz.bizNo}">信息修改</a>
                                <a href="#" class="input-style" right="${biz.bizNo}">权限修改</a>
                            </td>
                        </c:if>
                        <c:if test="${pager.params.isAdmin==null||pager.params.isAdmin==false}">
                            <td width="50%">
                                <a href="#" class="input-style" edit="${biz.bizNo}">信息修改</a>
                                <a href="#" class="input-style" right="${biz.bizNo}">权限修改</a>
                            </td>
                        </c:if>

                    </tr>
                </c:forEach>
            </table>
        </div>
        <section class="fc">
            <div class="page clearfix">
                ${pager.formPageStr}
            </div>
        </section>
    </section>
    <!-- end -->
</section>
<%@include file="_layout_nav.jsp" %>

<div class="alert-box clearfix" style="width: 300px;margin: -100px 0 0 -130px;">
    <h2>商家信息修改</h2>
    <dl class="clearfix" style=" padding:0.3rem 0;margin: 10px auto;">
        <dt>密码:</dt>
        <dd><input type="password" id="pwd" name="pwd" class=""></dd>
        <dt>确认密码:</dt>
        <dd><input type="password" id="pwd2" name="pwd2" class=""></dd>
        <dt>商家类型:</dt>
        <dd>
            <select id="grade" name="grade" style=" height: 30px; width: 200px;    background-color: #f0f0f0;">
                <option value="">请选择</option>
                <option value="1">商家</option>
                <option  value="3">代理商</option>
            </select>
        </dd>
        <%--<dt>提现额度:</dt>
        <dd><input type="number" id="takeSet" name="takeSet" class=""></dd>--%>
        <c:if test="${pager.params.isAdmin!=null&&pager.params.isAdmin==true}">
            <dt>LOGO:</dt>
            <dd>
                <input type="file" alt="" id="logoFile" name="logoFile">
            </dd>
        </c:if>
    </dl>
    <p class="clearfix">
        <input type="hidden" id="bizNo" name="bizNo" class="">
        <button onclick="" class="btnReadSec">确定</button>
        <button class="close">关闭</button>
    </p>
</div>
<script src='${ctx}/static/js/fileupload_js/LocalResizeIMG.js'></script>
<script src='${ctx}/static/js/fileupload_js/patch/mobileBUGFix.mini.js'></script>
<script src='${ctx}/static/js/fileupload_js/dist/lrz.bundle.js'></script>
<script>
    $(document).ready(function () {
        var btnClose = $('.close');
        var alertBox = $('.alert-box');
        btnClose.on('touchstart click', function (e) {
            e.preventDefault();
            alertBox.hide();
        });

        $("a[edit]").click(function (e) {
            e.preventDefault();
            var bizNo = $(this).attr('edit');
            $('#bizNo').val(bizNo);
            alertBox.css('display', 'block');
        });

        $(".btnReadSec").click(function () {
            if($('#logoFile').val() != ''&&$('#logoFile').val()!=null){
                ajaxFileUpload();
            }else {
                updateBiz(null);
            }
        });

        $("a[right]").click(function () {
            var bizNo = $(this).attr("right");
            window.location.href = _CTX + "/biz/bizRight?bizNo=" + bizNo;
        });

    });



    /**
     * 上传图片
     * @param time
     */
    function timer(time) {
        var btn = $("button.btnReadSec");
        btn.attr("disabled", "true");  //按钮禁止点击
        btn.html(time <= 0 ? "上传" : "上传中...");
        if (time <= 0) {
            btn.removeAttr("disabled");  //按钮禁止点击
        } else {

        }
    }
    /**
     * 上传图片
     */
    function ajaxFileUpload() {

        if ($('#logoFile').val() == '') {
            alert("图片为空");
            return false;
        } else {
            var bizNo = $("#bizNo").val();
            var file = $('#logoFile').val();
            var mime = file.toLowerCase().substr(file.lastIndexOf("."));
            if (mime != ".jpg"
                    && mime != ".jpeg"
                    && mime != ".png"
                    && mime != ".gif") {
                $('#logoFile').val('')
                alert("图片格式不正确!");
                return false;
            }
        }
        timer(30);
        //以图片宽度为1000进行压缩
        //alert($("#file1")[0].files[0]);
        lrz($("#logoFile")[0].files[0], {
            width: 300
        }).then(function (rst) {
            //压缩后异步上传
            //alert(rst);
            $.ajax({
                url: '${ctx}/biz/uploadLogo.json',
                type: "POST",
                data: {
                    bizNo:bizNo,
                    filename: rst.origin.name,
                    fileLength: rst.base64.length,
                    imgdata: rst.base64//压缩后的base值
                },
                dataType: "json",
                cache: true,
                async: false,
                success: function (data) {
                    if (data.success) {
                        var alertBox = $('.alert-box');
                        alertBox.hide();//////
                        updateBiz(data.msg);
                    } else {
                        alert(data.msg);
                    }
                    timer(0);
                },
                error: function () {
                    timer(0);
                    alert("上传出错,请稍后重新上传！");
                }
            });
        }).catch(function (err) {
            // 处理失败会执行
            timer(0);
            alert('上传失败,可能是网络问题，请稍后重新上传！');
        }).always(function () {
            //return false;
        });
        //
        return false;
    }

    /**
     * 修改数据
     */
    function updateBiz(logo) {
        var alertBox = $('.alert-box');
        var pwd = $("#pwd").val();
        var pwd2 = $("#pwd2").val();
        var takeSet = $("#takeSet").val();
        var grade = $("#grade").val();
        if(pwd!=null && pwd!='' && pwd!=pwd2){
            alert("两次输入密码不一致");
            return;
        }

        var bizNo = $("#bizNo").val();
        $.post(_CTX + "/biz/upBizInfo.json",
                {bizNo: bizNo, pwd: pwd, grade: grade, takeSet: takeSet, logo: logo},
                function (data) {
                    if (data) {
                        if (data.success) {
                            alertBox.css('display', 'none');
                            alert("修改成功");
                            location.reload();
                        } else {
                            alert(data.msg);
                        }
                    }
                }, "json");
    }
</script>
</body>
</html>