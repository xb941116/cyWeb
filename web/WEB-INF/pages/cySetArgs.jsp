<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header2.jsp" %>
<body>
<header>
    <h1 class="header fc">GPRS参数设置<a href="javascript:history.go(-1);" class="back"><i class="icon iconfont">&#xe67f;</i></a></h1>
</header>
<section class="mart2b2 clearfix">
    <div class="selec-list clearfix">
        <div class="conl">
            <ul>
                <li><a href="#" class="input-style bgactive">基本设置</a></li>
                <li><a href="#" class="input-style">高级设置</a></li>
            </ul>
        </div>
        <section class="content main-tab-0">
            <div class="para-set clearfix">
                <ul>
                    <li>
                        <input type="hidden" id="all" name="all" value="${all}">
                        <input type="hidden" id="gprsNoList" name="gprsNoList" value="${gprsNoList}">
                        <button class="btnReadSec" id="resetDefault" style="top: 0.0rem" onclick="resetDefault()">恢复默认</button>
                        <button class="btnReadSec" id="restart" style="top: 0.0rem" onclick="restart()">设备重启</button>
                    </li>
                    <li><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;心跳时间设置</span>
                        <input type="number" id="heart" placeholder="10到50之间数字">
                        <button class="btnReadSec ri" id="heartReset" onclick="heartReset()">设置</button>
                    </li>
                    <li><span>投币信号脉冲宽度</span><input type="number" id="insertWidth" placeholder="0到250之间数字">
                        <button class="btnReadSec ri" id="insertCoinWth" onclick="insertCoinWth()">设置</button>
                    </li>
                    <li><span>投币信号脉冲间隔</span><input type="number" id="insertInterval" placeholder="0到250之间数字">
                        <button class="btnReadSec ri" id="insertCoinInterval" onclick="insertCoinInterval()">设置</button>
                    </li>
                    <li><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;opt次数设置</span><input type="number" value="${movingFactor}" id="movingFactor" placeholder="1到99999999之间数字">
                        <button class="btnReadSec ri" id="movingFactorReset" onclick="otpNumReset()">设置</button>
                    </li>
                    <li><span>&nbsp;&nbsp;&nbsp;继电器吸合时间</span><input type="number" id="totalTimePerCoin" placeholder="单位秒">
                        <button class="btnReadSec ri" id="totalTimePerCoinBtn" onclick="totalTimePerCoinReset()">设置</button>
                    </li>

                </ul>
            </div>
        </section>
        <section class="content main-tab-1" style="display:none">
            <div class="para-set clearfix">
                <ul>
                    <li style="text-align: left;"><span>模块编号(例如00000000)</span><input type="text" id="gprsNo" class="ri" placeholder="请填写模块编号"></li>
                    <li style="text-align: left;"><span>命令(例如[00000000AS0311])</span><input type="text" id="cmd" class="ri" placeholder="请填写命令"></li>
                    <li><span>消息体</span><input type="text" id="arg" class="ri" placeholder="消息体"></li>
                    <li style="text-align: left;"><span>回应命令字(例如BS03)</span><input type="text" id="reCmd" class="ri" placeholder="请填写回应命令字"></li>
                    <li style="text-align: left;"><span>命令响应</span>
                        <textarea id="resp" class="ri" rows="6" cols="6"></textarea>
                    </li>
                </ul>
            </div>
            <button type="button" class="btn1" onclick="advancedCmd()">下发</button>
        </section>
    </div>
</section>
<script>

    setTimeout(loadSt, 2000);

    function loadSt() {
        var st = 0;
        $.getJSON(_CTX + "/gprs/model/baseArgsResp.json?gprsNoList=${gprsNoList}",
            function (result) {
                var val = "";
                $('#heart').val(result.heart);
                $('#insertWidth').val(result.insertCoinWth);
                $('#insertInterval').val(result.insertCoinInterval);
                $('#totalTimePerCoin').val(result.totalTimePerCoin);
                st = 1;
            });
        return st;
    }

    function resetDefault() {
        $("#resetDefault").css({
            'background-color': '#cccccc'
        });
        $("#resetDefault").attr("disabled", "disabled");
        proc("WS24", "", "#resetDefault");
    }
    function restart() {
        $("#restart").css({
            'background-color': '#cccccc'
        });
        $("#restart").attr("disabled", "disabled");
        proc("WS25", "", "#restart");
    }
    function heartReset() {
        var heart = $('#heart').val();
        if (heart != null && heart != "" && heart != 0) {
            $("#heartReset").css({
                'background-color': '#cccccc'
            });
            heart = parseInt(heart);
            if(heart>50 || heart <10){
                alert("心跳间隔应在10至50之间");
                return;
            }
            proc("WS11", heart, "#heartReset");
        } else {
            alert("请填写心跳间隔时间");
        }
    }
    function insertCoinWth() {
        var insertWidth = $('#insertWidth').val();
        if (insertWidth != null && insertWidth != "" && insertWidth > 0) {
            $("#insertCoinWth").css({
                'background-color': '#cccccc'
            });
            proc("WS50", insertWidth, "#insertCoinWth");
        } else {
            alert("请填写投币信号脉冲宽度，且值大于0");
        }
    }
    function insertCoinInterval() {
        var insertInterval = $('#insertInterval').val();
        if (insertInterval != null && insertInterval != "" && insertInterval > 0) {
            $("#insertCoinInterval").css({
                'background-color': '#cccccc'
            });
            proc("WS51", insertInterval, "#insertCoinInterval");
        } else {
            alert("请填写投币信号脉冲间隔，且值大于0");
        }
    }

    function totalTimePerCoinReset() {
        var totalTimePerCoin = $('#totalTimePerCoin').val();
        if (totalTimePerCoin != null && totalTimePerCoin != "" && totalTimePerCoin >= 0) {
            $("#totalTimePerCoin").css({
                'background-color': '#cccccc'
            });
            proc("WS61", totalTimePerCoin, "#totalTimePerCoin");
        } else {
            alert("青填写单位投币脉冲对应继电器吸合时间，且值大于等于0");
        }
    }


    function otpNumReset() {
        var movingFactor = $('#movingFactor').val();
        var gprsNoList = $('#gprsNoList').val();
        if (movingFactor<1||movingFactor>99999999){
            alert("opt次数必须是1到99999999之间数字");
            return ;
        }
        $.post(_CTX + "/gprs/model/restMovingFactor.json",
            {gprsNoList: gprsNoList, movingFactor: movingFactor},
            function (data) {
                if (data) {
                    if (data.success) {
                        alert(data.msg);
                    } else {
                        alert(data.msg);
                    }
                    /*$(btn).css({
                     'background-color': '#F96A0E'
                     });
                     $(btn).removeAttr("disabled");*/
                }
            }, "json");
    }

    function proc(cmd, arg, btn) {
        var gprsNoList = $('#gprsNoList').val();
        var all = $('#all').val();
        $.post(_CTX + "/gprs/model/resetModArgs.json",
            {all: all, gprsNoList: gprsNoList, cmd: cmd, arg: arg},
            function (data) {
                if (data) {
                    if (data.success) {
                        alert(data.msg);
                    } else {
                        alert(data.msg);
                    }
                    $(btn).css({
                        'background-color': '#F96A0E'
                    });
                    $(btn).removeAttr("disabled");
                }
            }, "json");
    }


</script>
</body>
</html>