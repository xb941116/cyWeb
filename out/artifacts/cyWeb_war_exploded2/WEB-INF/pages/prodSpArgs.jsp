<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">产品参数设置<a href="javascript:history.go(-1);" class="back"><i class="icon iconfont">&#xe67f;</i></a>
    </h1>
</header>
<section class="mart2b2 clearfix">
    <form id="form" method="post">
        <c:if test="${gprsType==1}">
            <%--洗车机--%>
            <div class="para-set clearfix">
                <ul>
                    <li>
                        <c:if test="${argsEnumArray!=null && fn:length(argsEnumArray)>0}">
                            <span style="color: #ff0000">默认产品参数</span>
                        </c:if>
                        <c:if test="${prodSpArgsList!=null && fn:length(prodSpArgsList)>0}">
                            <span style="color: #ff0000">产品参数获取时间：<fmt:formatDate value="${crtime}"
                                                                                  pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate> </span>
                        </c:if>
                    </li>
                    <li>
                        <c:if test="${argsEnumArray!=null && fn:length(argsEnumArray)>0}">
                        <c:forEach var="argsEnum" items="${argsEnumArray}" varStatus="status">
                        <c:if test="${argsEnum.type==1}">
                            <span>${argsEnum.desc}</span>
                            <input type="text" name="${argsEnum.name}" class="argsText" value="${argsEnum.val}"
                                   onblur="validInput(this);" minVal="${argsEnum.minVal}" maxVal="${argsEnum.maxVal}"
                                   placeholder="${argsEnum.minVal}-${argsEnum.maxVal}"> ${argsEnum.unit}
                            &nbsp;
                        </c:if>
                        <c:if test="${argsEnum.type==2}">
                            <label for="" class="extClass"><input type="checkbox" name="${argsEnum.name}" value="1"
                                                                  checked>${argsEnum.desc}
                            </label>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </c:if>
                        <c:if test="${status.count%2==0}">
                    </li>
                    <li>
                        </c:if>
                        </c:forEach>
                        </c:if>
                        <c:if test="${prodSpArgsList!=null && fn:length(prodSpArgsList)>0}">
                        <c:forEach var="argsEnum" items="${prodSpArgsList}" varStatus="status">
                        <c:if test="${argsEnum.type==1}">
                                <span>
                                        ${argsEnum.name}</span>
                            <input type="text" name="${argsEnum.code}" class="argsText" value="${argsEnum.val}"
                                   onblur="validInput(this);" minVal="${argsEnum.minVal}" maxVal="${argsEnum.maxVal}"
                                   placeholder="${argsEnum.minVal}-${argsEnum.maxVal}"> ${argsEnum.unit}
                            &nbsp;
                        </c:if>
                        <c:if test="${argsEnum.type==2}">
                            <label for="" class="extClass"><input type="checkbox" name="${argsEnum.code}" value="1"
                                                                  checked>${argsEnum.name}
                            </label>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </c:if>
                        <c:if test="${status.count%2==0}">
                    </li>
                    <li>
                        </c:if>
                        </c:forEach>
                        </c:if>
                    </li>
                </ul>
            </div>
        </c:if>
        <c:if test="${gprsType==6}">
            <%--充电桩--%>
            <div class="para-set clearfix">
                <ul>
                    <li>
                        <span style="color: #ff0000">默认产品参数</span>
                    </li>
                    <li>
                        <span>刷卡扣费设置(单位角)</span>
                        <br>
                        刷卡扣费:<input name="cardMoney" id="cardMoney" value="" placeholder="1-255">
                        <button class="btnReadSec ri" name="cardMoneySet" id="cardMoneySet" type="button">设置</button>
                        <button class="btnReadSec ri" name="cardMoneySel" id="cardMoneySel" type="button"
                                style="margin-right: 10px;">查询
                        </button>
                    </li>
                    <li>
                        <span>充电时间设置(分钟)</span>
                        <br>&emsp;&emsp;&nbsp;第一功率刷卡充电时间(10的倍数):<input name="oneCardTime" id="oneCardTime" value=""
                                                                       placeholder="第一功率刷卡充电时间(10的倍数)">
                        <br>&emsp;&emsp;&nbsp;第二功率刷卡充电时间(10的倍数):<input name="twoCardTime" id="twoCardTime" value=""
                                                                       placeholder="第二功率刷卡充电时间(10的倍数)">
                        <br>第一功率投币/微信充电时间(10的倍数):<input name="oneWXTime" id="oneWXTime" value=""
                                                        placeholder="第一功率投币/微信充电时间(10的倍数)">
                        <br>第二功率投币/微信充电时间(10的倍数):<input name="twoWXTime" id="twoWXTime" value=""
                                                        placeholder="第二功率投币/微信充电时间(10的倍数)">
                        <br>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                        充满延时时间:<input name="totalTime" id="totalTime" value="" placeholder="充满延时时间">
                        <br>
                        <button class="btnReadSec ri" name="timeSet" id="timeSet" type="button">设置</button>
                        <button class="btnReadSec ri" name="timeSel" id="timeSel" type="button"
                                style="margin-right: 10px;">查询
                        </button>
                        <br>
                    </li>
                    <li>
                        <span>功率参数设置</span>
                        <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;第一功率(0-800W):<input name="oneWatt" id="oneWatt" value=""
                                                                                    placeholder="第一功率(0-800W)">
                        <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;最大功率系数(1-6):<input name="cv" id="cv" value=""
                                                                                         placeholder="最大功率系数(1-6)">
                        <br>充足自停功率(0-100W):<input name="twoWatt" id="twoWatt" value="" placeholder="充足自停功率(0-100W)">
                        <br>
                        <button class="btnReadSec ri" name="wattSet" id="wattSet" type="button">设置</button>
                        <button class="btnReadSec ri" name="wattSel" id="wattSel" type="button"
                                style="margin-right: 10px;">查询
                        </button>
                        <br>
                    </li>
                </ul>
            </div>
        </c:if>

        <c:if test="${gprsType==3}">
            <%--昌原充电桩--%>
            <div class="para-set clearfix">
                <ul>
                    <li>
                        <span style="color: #ff0000">默认产品参数</span>
                    </li>
                    <li>
                        <span>机器型号</span>
                        <br>
                        型号查询:<input name="machineType" id="machineType" value="">
                        <button class="btnReadSec ri" name="machineTypeSel" id="machineTypeSel" type="button"
                                style="margin-right: 10px;">查询
                        </button>
                    </li>
                    <li>
                        <span>功率参数(分钟)及费率参数(分)设置</span>
                        <br>&emsp;&emsp;&nbsp;大功率段充电时间:<input name="largePowerTime" id="largePowerTime" value=""
                                                              placeholder="大功率段充电时间">
                        <br>&emsp;&emsp;&nbsp;中功率段充电时间:<input name="middenPowerTime" id="middenPowerTime" value=""
                                                              placeholder="中功率段充电时间">
                        <br>&emsp;&emsp;&nbsp;小功率段充电时间:<input name="smallPowerTime" id="smallPowerTime" value=""
                                                              placeholder="小功率段充电时间">
                        <br>&emsp;&emsp;&nbsp;下限功率:<input name="limitPower" id="limitPower" value="" placeholder="下限功率">
                        <br>&emsp;&emsp;&nbsp;预扣金额:<input name="preMoney" id="preMoney" value="" placeholder="预扣金额">
                        <br>&emsp;&emsp;&nbsp;喇叭音量:<input name="voice" id="voice" value="" placeholder="喇叭音量">
                        <br>
                        <button class="btnReadSec ri" name="cyTimeSet" id="cyTimeSet" type="button">设置</button>
                        <button class="btnReadSec ri" name="cyTimeSel" id="cyTimeSel" type="button"
                                style="margin-right: 10px;">查询
                        </button>
                        <br>
                    </li>
                    <li>
                        <span>状态、剩余时间或金额</span>
                        <br>&emsp;&emsp;&nbsp;第一路:<input style="width: 800px" name="one" id="one" value="" placeholder="第一路">
                        <br>&emsp;&emsp;&nbsp;第二路:<input style="width: 800px" name="two" id="two" value="" placeholder="第二路">
                        <br>&emsp;&emsp;&nbsp;第三路:<input style="width: 800px" name="three" id="three" value="" placeholder="第三路">
                        <br>&emsp;&emsp;&nbsp;第四路:<input style="width: 800px" name="four" id="four" value="" placeholder="第四路">
                        <br>&emsp;&emsp;&nbsp;第五路:<input style="width: 800px" name="five" id="five" value="" placeholder="第五路">
                        <br>&emsp;&emsp;&nbsp;第六路:<input style="width: 800px" name="six" id="six" value="" placeholder="第六路">
                        <br>&emsp;&emsp;&nbsp;第七路:<input style="width: 800px" name="seven" id="seven" value="" placeholder="第七路">
                        <br>&emsp;&emsp;&nbsp;第八路:<input style="width: 800px" name="eight" id="eight" value="" placeholder="第八路">
                        <br>&emsp;&emsp;&nbsp;第九路:<input style="width: 800px" name="nine" id="nine" value="" placeholder="第九路">
                        <br>&emsp;&emsp;&nbsp;第十路:<input style="width: 800px" name="ten" id="ten" value="" placeholder="第十路">
                        <button class="btnReadSec ri" name="cyChannelState" id="cyChannelState" type="button"
                                style="margin-right: 10px;">查询
                        </button>
                    </li>
                    <li>
                        <span>刷卡总额和投币总额</span>
                        <br>&emsp;&emsp;&nbsp;刷卡总额:<input name="cyCardMoney" id="cyCardMoney" value=""
                                                          placeholder="刷卡总额">
                        <br>&emsp;&emsp;&nbsp;投币总额:<input name="cyTBMoney" id="cyTBMoney" value="" placeholder="投币总额">
                        <br>&emsp;&emsp;&nbsp;微信总额:<input name="cyWXMoney" id="cyWXMoney" value="" placeholder="微信总额">
                        <button class="btnReadSec ri" name="cyMoneySel" id="cyMoneySel" type="button"
                                style="margin-right: 10px;">查询
                        </button>
                    </li>
                </ul>
            </div>
        </c:if>
        <input type="hidden" name="all" value="${all}">
        <input type="hidden" name="gprsNoList" id="gprsNoList" value="${gprsNoList}">
        <button type="button" class="btn">确认提交</button>
    </form>
</section>
<script>
    var hander = null;
    var gprsNoList = $("#gprsNoList").val();
    function timer(time) {
        var btn = $("button.btn");
        btn.attr("disabled", "true");  //按钮禁止点击
        btn.html(time <= 0 ? "确认提交" : ("处理中" + (time) + "秒"));
        if (time <= 0) {
            btn.removeAttr("disabled");  //按钮禁止点击
            clearInterval(hander);
            hander = null;
        } else {
            hander = setInterval(function () {
                if (time <= 0) {
                    clearInterval(hander); //清除倒计时
                    btn.html("确认提交");
                    btn.removeAttr("disabled");
                    return false;
                } else {
                    btn.html("处理中" + (time--) + "秒");
                }
            }, 1000);
        }
    }
    $(document).ready(function () {
        $('.btn').on('click', function (e) {
            e.preventDefault();
            var args = $("input[type='checkbox']");
            args.each(function () {
                if ($(this).get(0).checked) {
                    $(this).val("1");
                } else {
                    $(this).val("0");
                }
            });
            timer(30);
            $.post(_CTX + "/prod/prodSpArgsRest.json",
                $('#form').serialize(),
                function (data) {
                    if (data) {
                        if (data.success) {
                            alert(data.msg);
                        } else {
                            alert(data.msg);
                        }
                    }
                    timer(0);
                }, "json");
        });

        $('#cardMoneySet').on('click', function () {
            var cardMoney = $("#cardMoney").val();
            if (cardMoney < 1 || cardMoney > 255) {
                alert("刷卡扣费取值范围在1-255之间");
                return;
            }
            timer(30);
            $.post(_CTX + "/prod/cardMoneySet.json",
                $('#form').serialize(),
                function (data) {
                    if (data) {
                        if (data.success) {
                            alert(data.msg);
                        } else {
                            alert(data.msg);
                        }
                    }
                    timer(0);
                }, "json");
        });
        $('#cardMoneySel').on('click', function () {
            if (gprsNoList.split("_").length > 2) {
                alert("不支持多个查询！");
                return;
            }
            var gprsNo = gprsNoList.split("_")[1];

            $.post(_CTX + "/gprs/model/senddCmd.json",
                {gprsNo: gprsNo, cmd: "GS04"},
                function (data) {
                    if (data) {
                        if (data.success) {
                            alert(data.msg);
                            setTimeout(loadCardMoney, 4000);
                        } else {
                            alert(data.msg);
                        }
                        $("button.btn1").css({
                            'background-color': '#064679'
                        });
                        $("button.btn1").removeAttr("disabled");
                    }
                }, "json");
        });
        $('#wattSet').on('click', function () {
            var oneWatt = $("#oneWatt").val();
            var cv = $("#cv").val();
            var twoWatt = $("#twoWatt").val();
            if (oneWatt < 0 || oneWatt > 800) {
                alert("第一功率在0-800之间");
                return;
            }
            if (cv < 1 || cv > 6) {
                alert("最大功率系数在1-6之间");
                return;
            }
            if (twoWatt < 1 || twoWatt > 100) {
                alert("充足自停功率在0-100之间");
                return;
            }
            timer(30);
            $.post(_CTX + "/prod/wattSet.json",
                $('#form').serialize(),
                function (data) {
                    if (data) {
                        if (data.success) {
                            alert(data.msg);
                        } else {
                            alert(data.msg);
                        }
                    }
                    timer(0);
                }, "json");
        });
        $('#wattSel').on('click', function () {
            if (gprsNoList.split("_").length > 2) {
                alert("不支持多个查询！");
                return;
            }
            var gprsNo = gprsNoList.split("_")[1];
            $.post(_CTX + "/gprs/model/senddCmd.json",
                {gprsNo: gprsNo, cmd: "GS08"},
                function (data) {
                    if (data) {
                        if (data.success) {
                            alert(data.msg);
                            setTimeout(loadWatt, 4000);
                        } else {
                            alert(data.msg);
                        }
                        $("button.btn1").css({
                            'background-color': '#064679'
                        });
                        $("button.btn1").removeAttr("disabled");
                    }
                }, "json");
        });
        $('#timeSet').on('click', function () {
            var oneCardTime = $("#oneCardTime").val();
            var twoCardTime = $("#twoCardTime").val();
            var oneWXTime = $("#oneWXTime").val();
            var twoWXTime = $("#twoWXTime").val();
            var totalTime = $("#totalTime").val();
            if (oneCardTime % 10 != 0 || twoCardTime % 10 != 0 || oneWXTime % 10 != 0 || twoWXTime % 10 != 0) {
                alert("除了充满延时时间外，其余参数都必须是10的倍数");
                return;
            }
            if (oneCardTime < 1 || twoCardTime < 1 || oneWXTime < 1 || twoWXTime < 1 || oneCardTime > 999 || twoCardTime > 999 || oneWXTime > 999 || twoWXTime > 999) {
                alert("除了充满延时时间外，其余参数都必须在1-999之间");
                return;
            }
            if (totalTime > 200 || totalTime < 0) {
                alert("充满延时时间在0-200之间");
                return;
            }
            timer(30);
            $.post(_CTX + "/prod/timeSet.json",
                $('#form').serialize(),
                function (data) {
                    if (data) {
                        if (data.success) {
                            alert(data.msg);
                        } else {
                            alert(data.msg);
                        }
                    }
                    timer(0);
                }, "json");
        });
        $('#timeSel').on('click', function () {
            if (gprsNoList.split("_").length > 2) {
                alert("不支持多个查询！");
                return;
            }
            var gprsNo = gprsNoList.split("_")[1];
            $.post(_CTX + "/gprs/model/senddCmd.json",
                {gprsNo: gprsNo, cmd: "GS06"},
                function (data) {
                    if (data) {
                        if (data.success) {
                            alert(data.msg);
                            setTimeout(loadTime, 4000);
                        } else {
                            alert(data.msg);
                        }
                        $("button.btn1").css({
                            'background-color': '#064679'
                        });
                        $("button.btn1").removeAttr("disabled");
                    }
                }, "json");
        });


        /*查询机器型号*/
        $('#machineTypeSel').on('click', function () {
            if (gprsNoList.split("_").length > 2) {
                alert("不支持多个查询！");
                return;
            }
            var gprsNo = gprsNoList.split("_")[1];
            $.post(_CTX + "/gprs/model/senddCmd.json",
                {gprsNo: gprsNo, cmd: "CY40"},
                function (data) {
                    if (data) {
                        if (data.success) {
                            alert(data.msg);
                            setTimeout(loadMachineType, 4000);
                        } else {
                            alert(data.msg);
                        }
                        $("button.btn1").css({
                            'background-color': '#064679'
                        });
                        $("button.btn1").removeAttr("disabled");
                    }
                }, "json");
        });


        /*修改从机的大中小功率时间、下限功率、峪口金额、喇叭音量大小*/
        $('#cyTimeSet').on('click', function () {
            var largePowerTime = $("#largePowerTime").val();
            var middenPowerTime = $("#middenPowerTime").val();
            var smallPowerTime = $("#smallPowerTime").val();
            var limitPower = $("#limitPower").val();
            var preMoney = $("#preMoney").val();
            var voice = $("#voice").val();
            timer(30);
            $.post(_CTX + "/prod/cyTimeSet.json",
                $('#form').serialize(),
                function (data) {
                    if (data) {
                        if (data.success) {
                            alert(data.msg);
                        } else {
                            alert(data.msg);
                        }
                    }
                    timer(0);
                }, "json");
        });
        $('#cyTimeSel').on('click', function () {
            if (gprsNoList.split("_").length > 2) {
                alert("不支持多个查询！");
                return;
            }
            var gprsNo = gprsNoList.split("_")[1];
            $.post(_CTX + "/gprs/model/senddCmd.json",
                {gprsNo: gprsNo, cmd: "CY45"},
                function (data) {
                    if (data) {
                        if (data.success) {
                            alert(data.msg);
                            setTimeout(loadArgs, 4000);
                        } else {
                            alert(data.msg);
                        }
                        $("button.btn1").css({
                            'background-color': '#064679'
                        });
                        $("button.btn1").removeAttr("disabled");
                    }
                }, "json");
        });
        /*查询十路机的工作状态*/
        $('#cyChannelState').on('click',function () {
            if (gprsNoList.split("_").length>2){
                alert("不支持多个查询！");
                return;
            }
            var gprsNo=gprsNoList.split("_")[1];
            $.post(_CTX + "/gprs/model/senddCmd.json",
                {gprsNo: gprsNo, cmd: "CY44"},
                function (data) {
                    if (data) {
                        if (data.success) {
                            alert(data.msg);
                            setTimeout(channelState, 4000);
                        } else {
                            alert(data.msg);
                        }
                        $("button.btn1").css({
                            'background-color': '#064679'
                        });
                        $("button.btn1").removeAttr("disabled");
                    }
                }, "json");
        });
        /*查询刷卡、投币和微信收入*/
        $('#cyMoneySel').on('click',function () {
            if (gprsNoList.split("_").length>2){
                alert("不支持多个查询！");
                return;
            }
            var gprsNo=gprsNoList.split("_")[1];
            $.post(_CTX + "/gprs/model/senddCmd.json",
                {gprsNo: gprsNo, cmd: "CY43"},
                function (data) {
                    if (data) {
                        if (data.success) {
                            alert(data.msg);
                            setTimeout(moneySel, 4000);
                        } else {
                            alert(data.msg);
                        }
                        $("button.btn1").css({
                            'background-color': '#064679'
                        });
                        $("button.btn1").removeAttr("disabled");
                    }
                }, "json");
        });

    });

    function validInput(obj) {
        var val = $(obj).val();
        var minVal = parseInt($(obj).attr('minVal'), 0);
        var maxVal = parseInt($(obj).attr('maxVal'), 0);
        if (val != null && val != '') {
            if (val < minVal || val > maxVal) {
                alert("取值范围：" + minVal + "-" + maxVal);
                $(obj).val('');
                $(obj).focus();
                return;
            }
        } else {
            $(obj).focus();
        }
    }


    function loadCardMoney() {
        var gprsNo = gprsNoList.split("_")[1];
        $.getJSON(_CTX + "/gprs/model/advanceArgsResp.json?gprsNo=" + gprsNo + "&cmd=HS04",
            function (result) {
                $('#cardMoney').val(result);
            });
    }

    function loadTime() {
        var gprsNo = gprsNoList.split("_")[1];
        $.getJSON(_CTX + "/gprs/model/advanceArgsResp.json?gprsNo=" + gprsNo + "&cmd=HS06",
            function (result) {
                $("#oneCardTime").val(result.split("_")[0]);
                $("#twoCardTime").val(result.split("_")[1]);
                $("#oneWXTime").val(result.split("_")[2]);
                $("#twoWXTime").val(result.split("_")[3]);
                $("#totalTime").val(result.split("_")[4]);
            });
    }


    function loadWatt() {
        var gprsNo = gprsNoList.split("_")[1];
        $.getJSON(_CTX + "/gprs/model/advanceArgsResp.json?gprsNo=" + gprsNo + "&cmd=HS08",
            function (result) {
                $("#oneWatt").val(result.split("_")[0]);
                $("#cv").val(result.split("_")[1]);
                $("#twoWatt").val(result.split("_")[2]);
            });
    }

    function loadMachineType() {
        var gprsNo = gprsNoList.split("_")[1];
        $.getJSON(_CTX + "/gprs/model/advanceArgsResp.json?gprsNo=" + gprsNo + "&cmd=CYR40",
            function (result) {
                $('#machineType').val(result);
            });
    }

    function loadArgs() {
        var gprsNo = gprsNoList.split("_")[1];
        $.getJSON(_CTX + "/gprs/model/advanceArgsResp.json?gprsNo=" + gprsNo + "&cmd=CYR45",
            function (result) {
                $("#largePowerTime").val(result.split("_")[0]);
                $("#middenPowerTime").val(result.split("_")[1]);
                $("#smallPowerTime").val(result.split("_")[2]);
                $("#limitPower").val(result.split("_")[3]);
                $("#preMoney").val(result.split("_")[4]);
                $("#voice").val(result.split("_")[5]);
            });
    }
    function channelState() {
        var gprsNo = gprsNoList.split("_")[1];
        $.getJSON(_CTX + "/gprs/model/advanceArgsResp.json?gprsNo=" + gprsNo + "&cmd=CYR44",
            function (result) {
                $("#one").val(result.split("_")[0]);
                $("#two").val(result.split("_")[1]);
                $("#three").val(result.split("_")[2]);
                $("#four").val(result.split("_")[3]);
                $("#five").val(result.split("_")[4]);
                $("#six").val(result.split("_")[5]);
                $("#seven").val(result.split("_")[6]);
                $("#eight").val(result.split("_")[7]);
                $("#nine").val(result.split("_")[8]);
                $("#ten").val(result.split("_")[9]);
            });
    }
    function moneySel() {
        var gprsNo = gprsNoList.split("_")[1];
        $.getJSON(_CTX + "/gprs/model/advanceArgsResp.json?gprsNo=" + gprsNo + "&cmd=CYR43",
            function (result) {
                $("#cyCardMoney").val(result.split("_")[0]);
                $("#cyTBMoney").val(result.split("_")[1]);
                $("#cyWXMoney").val(result.split("_")[2]);
            });
    }



</script>
</body>
</html>