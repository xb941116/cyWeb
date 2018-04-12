<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<c:set var="now" value="<%=new java.util.Date()%>"/>
<body>
<header>
    <h1 class="header fc">商品模板<a href="${ctx}/prod/prodList" class="back"><i class="icon iconfont">&#xe67f;</i></a></h1>
</header>

<section class="mart2b2 clearfix">
    <form id="form" method="post">
        <div class="para-set clearfix">
            <ul>
                <li><span>模板名称</span><input type="text" id="prodMdlName" name="prodMdlName" value="${prodModel.prodMdlName}" class="ri" placeholder="模板名称"></li>
                <li>
                    <span>商品类型</span>
                    <select id="type" name="type" class="ri" style="width: 6.1rem;">
                        <c:forEach var="dict" items="${dictList}">
                            <option value="${dict.code}" <c:if test="${dict.code eq prodModel.type}">selected="selected"</c:if>>${dict.val}</option>
                        </c:forEach>
                     </select>
                </li>
                <li><span>基本价格</span><input type="number" id="price" name="price" value="${prodModel.price}" class="ri" placeholder="基本价格"></li>
                <li style="display: none;"><span>脉冲系数</span><input type="number" id="prodCv" name="prodCv" value="<c:if test="${prodModel.prodCv==null}">1</c:if><c:if test="${prodModel.prodCv!=null}">${prodModel.prodCv}</c:if>" class="ri" placeholder="脉冲系数"></li>

                <%-- <li><span>可选价格状态</span>
                    <select id="otherMoneyState" name="otherMoneyState" class="ri"  style="width: 6.1rem;">
                        <option value="0" <c:if test="${0==prodModel.otherMoneyState}">selected="selected"</c:if>>关闭</option>
                        <option value="1" <c:if test="${1== prodModel.otherMoneyState}">selected="selected"</c:if>>类型1(仅价格)</option>
&lt;%&ndash;
                        <option value="2" <c:if test="${2== prodModel.otherMoneyState}">selected="selected"</c:if>>类型2(说明+价格)</option>
&ndash;%&gt;
                    </select>
                </li>--%>
                <li>
                    <input type="hidden" name="otherMoneyState" value="1" />
                    <span>可选价格（金额单位为元）</span>
<%--
                    <input type="text" id="otherMoneyOption" name="otherMoneyOption" value="${prodModel.otherMoneyOption}" class="ri" placeholder="可选价格(用逗号隔开)。例如：1,2">
--%>
                    <div class="ord_money_add">
<%--
                        <input type="hidden" id="totalPay" value="${moneyOptionList[0][2]}" name="totalPay"/>
--%>
                        <c:forEach items="${moneyOptionList}" var="moneyOption" varStatus="sta">
                            <div class="ord_border">
                                <input type="text" name="nameTeam" value="${moneyOption[0]}"  maxlength="10" placeholder="名称" /><br>
                                <input type="text" name="moneyTeam" value="${moneyOption[1]}"  onkeyup="value=value.replace(/[^\-?\d.]/g,'')" placeholder="金额" />
                                <input type="text" name="cvTeam" value="${moneyOption[2]}" maxlength="2" onkeyup="value=value.replace(/[^0-9]/g,'')" placeholder="脉冲" />
                            </div>
                        </c:forEach>
                    </div>
                </li>
                <li style="display: none;"><span>单笔数量</span><input type="number" id="perCt" name="perCt" value="${prodModel.perCt}" class="ri" placeholder="单笔数量"></li>
                <li><span>使用时长(单位:分钟)</span><input type="number" id="runTime" name="runTime" value="${prodModel.runTime}" class="ri" placeholder="使用时长(单位:分钟)"></li>
                <li style="display: none;"><span>赠送数量</span><input type="number" id="giftCt" name="giftCt" value="${prodModel.giftCt}" class="ri" placeholder="赠送数量"></li>
                <li style="display: none;"><span>首次免单</span>
                    <label for="firstFree1">
                        <input type="radio" id="firstFree1" name="firstFree" value="0" <c:if test="${prodModel.firstFree==0}">checked="checked"</c:if> >否
                    </label>
                    <label for="firstFree0">
                        <input type="radio" id="firstFree0" name="firstFree" value="1" <c:if test="${prodModel.firstFree==1}">checked="checked"</c:if>  >是
                    </label>
                </li>

                <li><span>商品图</span>
                    <input type="text" id="logo" name="logo" value="${prodModel.logo}" class="" placeholder="" style="width:50%;">
                    <label for="logoBtn">
                        <button class="ri" id="logoBtn">点击上传</button>
                    </label>
                </li>
            </ul>
        </div>
        <input type="hidden" id="id" name="id" value="${prodModel.id}" >
        <button type="button" class="btn">确认提交</button>
    </form>
</section>
<!-- 选择商家 弹出框 -->
<div class="alert-box clearfix">
    <h2>商品图片上传</h2>
    <dl class="clearfix">
        <dd><input type="file" alt="" id="logoFile" name="logoFile"></dd>
    </dl>
    <p class="clearfix">
        <button onclick="ajaxFileUpload()" class="btnReadSec">上传</button>
        <button class="close">取消</button>
    </p>
</div>
<!-- 选择商家 弹出框 end -->
<script src='${ctx}/static/js/fileupload_js/LocalResizeIMG.js'></script>
<script src='${ctx}/static/js/fileupload_js/patch/mobileBUGFix.mini.js'></script>
<script src='${ctx}/static/js/fileupload_js/dist/lrz.bundle.js'></script>
<script>
    var hander = null;
    function timer(time) {
        var btn = $("button.btnReadSec");
        btn.attr("disabled", "true");  //按钮禁止点击
        btn.html(time <= 0 ? "上传" : ("" + (time) + "秒"));
        if (time <= 0) {
            btn.removeAttr("disabled");  //按钮禁止点击
            clearInterval(hander);
            hander = null;
        } else {
            hander = setInterval(function () {
                if (time <= 0) {
                    clearInterval(hander); //清除倒计时
                    btn.html("上传");
                    btn.removeAttr("disabled");
                    return false;
                } else {
                    btn.html("" + (time--) + "秒");
                }
            }, 1000);
        }
    }
    function ajaxFileUpload() {
        if ($('#logoFile').val() == '') {
            alert("图片为空");
            return false;
        } else {
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
                url: '${ctx}/prod/uploadLogo.json',
                type: "POST",
                data: {
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
                        alertBox.hide();
                        alert('上传图片成功');
                        $("#logo").val(data.msg);
                    } else {
                        alert(data.msg);
                    }
                    timer(0);
                },
                error: function () {
                    timer(0);
                    alert("上传失败,可能是网络问题，请重新上传！");
                }
            });
        }).catch(function (err) {
            // 处理失败会执行
            timer(0);
            alert('上传失败,可能是网络问题，请重新上传！');
        }).always(function () {
            //return false;
        });
        //
        return false;
    }
</script>
<script>
    $(document).ready(function () {
        var btnClose = $('.close');
        var alertBox = $('.alert-box');
        $('#logoBtn').on('touchstart click', function (e) {
            e.preventDefault();
            alertBox.css('display', 'block');
        });
        btnClose.on('touchstart click', function (e) {
            e.preventDefault();
            alertBox.hide();
        });
        var addList = $('.append-bank');
        $('.btn').on('click', function (e) {
            e.preventDefault();
            var prodMdlName = $('#prodMdlName').val();
            var price = $('#price').val();
            var perCt = $('#perCt').val();
            var giftCt = $('#giftCt').val();
            var prodCv = $('#prodCv').val();
            var otherMoneyState = $('#otherMoneyState').val();
/*
            var otherMoneyOption = $('#otherMoneyOption').val();
*/
            var myreg = /^(\d+\\.\d+)|(\d+)$/;
           /*
            if(otherMoneyState=="1"&&!myreg.test($("#otherMoneyOption").val().replace(/,/g,"").replace(/，/g,""))) {
                alert("除类型2之外，可选价格里面只能有逗号和数字！");
                return ;
            }
            */
            if (price == '' || prodMdlName == '' || prodCv == '' ) {
                alert('请填写名称、价格、脉冲系数');
                return;
            }
            $.post(_CTX + "/prod/prodModel/crOrUp.json",
                    $('#form').serialize(),
                    function (data) {
                        if (data) {
                            if (data.success) {
                                window.location.href = _CTX + "/prod/prodSalePro";
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