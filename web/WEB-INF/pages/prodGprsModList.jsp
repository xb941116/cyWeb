<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">模块维护 <a href="${ctx}/navCtrl/prod" class="back"><i class="icon iconfont">&#xe67f;</i></a></h1>
	<script type="text/javascript">
        $(document).ready(function(){
            $("tr:even").css("background","#FFFFFF");
        });
    </script>
</header>
<section class="mart2b2 clearfix">
    <!-- 商品类型选择 -->
    <form name="theForm" id="theForm" action="${ctx}/prod/prodGprsMod" method="post">
        <div class="selec  clearfix">
            <label for="gprsNo">绑定商家：</label>
            <input type="text" name="bizName" id="bizName" class="input-style" placeholder="" value="${pager.params.bizName}" style="width: 3.8rem">
            <label for="gprsNo">&nbsp;模块编号：</label>
            <input type="text" name="gprsNo" id="gprsNo" class="input-style" placeholder="" value="${pager.params.gprsNo}" style="width: 3.8rem">
            <br>
            <br>
            <label>模块类型：</label>
            <select name="gprsType" id="gprsType" class="input-style" style="width: 3.8rem">
                <option value="0">电子投币</option>
                <option value="1">洗车机</option>
                <option value="2">动态口令</option>
                <option value="6">充电站</option>
                <option value="3">昌原充电站</option>
            </select>
            <label>在线状态：</label>
            <select name="onlineState" id="onlineState" class="input-style" style="width: 3.8rem">
                <option value="">请选择</option>
                <option value="0">离线</option>
                <option value="1">在线</option>
            </select>
            <br>
            <br>
            <div style="text-align: center;">
                <input type="submit" class="input-style bgactive " value="搜索">
            </div>
        </div>
    </form>
    <section>
        <div class="selec-list clearfix">
            <div class="conl" class="conl" style="background-color: #ffffff;">
                <ul>
                    <li>
                        <label>是否应用全部设备</label>
                        <select name="all" id="all" class="input-style" style="width: 3rem">
                            <option value="0">否</option>
                            <option value="1">是</option>
                        </select>
                    </li>
                    <li style="width: 1.0rem"></li>
                    <li><a href="#" class="input-style" onclick="setProdModArgs()">模块参数设置</a></li>
                    <li><a href="#" class="input-style" onclick="setProdArgs()">产品参数设置</a></li>
                </ul>
            </div>
            <div class="body-bg">
            <div style="height: 5px;background-color: #ffffff;">&nbsp;</div>

                <table class="selec-list-table">
                    <tr>
                        <td width="10%" class="tr_bg1"><label for="chk_all"><input type="checkbox" id="chk_all"></label></td>
                        <td width="15%" class="tr_bg2">编号</td>
                        <td width="40%" class="tr_bg2">绑定商家</td>
                        <td width="10%" class="tr_bg2">版本</td>
                        <td width="15%" class="tr_bg2">是否在线</td>
                        <td width="10%" class="tr_bg3">位置</td>
                    </tr>
                    <c:forEach var="model" items="${pager.recordList}">
                        <tr>
                            <td width="10%">
                                <label for="chk_list">
                                    <input type="checkbox" value="${model.gprsNo}" id="chk_list" name="chk_list">
                                    <input type="hidden" value="${model.online}" id="online">
                                </label>
                            </td>
                            <td width="15%">${model.gprsNo}</td>
                            <td width="40%">${fn:substring(model.bizName,0,7)}</td>
                            <td width="10%">${model.mo}</td>
                            <td width="15%">${model.onlineStr}</td>
                            <td width="10%">
                                <c:if test="${not empty model.pos}">
                                    <a href="#" pos="${model.pos}">详情</a>
                                </c:if>
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
        </div>
    </section>
</section>
<!-- end -->
<%@include file="_layout_nav.jsp" %>
<div class="alert-box clearfix" style="width: 260px;min-height: 130px; height:150px;margin: -100px 0 0 -130px;">
    <h2>设备安装位置<i class="ri close">x&nbsp;&nbsp;</i></h2>
    <dl class="clearfix" style=" padding:0rem 0;margin: 30px auto;">
        <%--<dt>文件:</dt>--%>
        <dd>
            <span id="pos"></span>
        </dd>
    </dl>

</div>
<script>

    function setProdModArgs() {
        var all = $('#all').val();
        var gprsList = $("input[name='chk_list']:checked");
        var online = $("input[name='chk_list']:checked").parent().find('#online').val();
        var gprsNoList = "";
        if (all == 0) {
            if (gprsList.length == 0) {
                alert("请选择产品");
                return;
            }

            /*for (var i=0;i<gprsList.length;i++){
                if ($(gprsList[i]).val().substring(0,1)!="2"&&$(gprsList[i]).parent().find('#online').val()!=1){
                    alert($(gprsList[i]).val()+"设备不在线不能设置模块参数");
                    return;
                }
            }
            if (gprsList.length == 1 && (online == 0 || online == null || online == '')) {
                alert("设备不在线不能设置模块参数");
                return;*/
            gprsList.each(function () {
                gprsNoList = gprsNoList + "_" + $(this).val();
            });

        }
        window.location.href = _CTX + "/prod/prodGprsArgsSet?all=" + all + "&gprsNoList=" + gprsNoList;
    }

    function setProdArgs() {
        var all = $('#all').val();
        var gprsType = $('#gprsType').val();
        var gprsList = $("input[name='chk_list']:checked");
        var online = $("input[name='chk_list']:checked").parent().find('#online').val();
        var gprsNoList = "";
        var ale = false;
        if (all == 0) {
            if (gprsList.length == 0) {
                alert("请选择产品");
                return;
            }
            /*if (gprsList.length == 1 && (online == 0 || online == null || online == '')) {
                alert("设备不在线不能设置模块参数");
                return;
            }*/
            gprsList.each(function () {
                if ($(this).val().substring(0, 1) == '3') {
                    gprsNoList = gprsNoList + "_" + $(this).val();
                } else if ($(this).val().substring(0, 1) == '0') {
                    ale = ale&&true;
                }
            });
        }
        if (ale && gprsList.length == 1) {
            alert("该设备不支持产品参数设置");
        } else if (ale && gprsList.length > 1) {
            alert("提示：设备不支持参数设置");
            //window.location.href = _CTX + "/prod/prodSpArgsSet?all=" + all + "&gprsNoList=" + gprsNoList;
        } else {
            window.location.href = _CTX + "/prod/prodSpArgsSet?all=" + all + "&gprsNoList=" + gprsNoList+"&gprsType="+gprsType;
        }
    }

    $(document).ready(function () {

        $("#chk_all").click(function () {
            $("input[name='chk_list']").prop("checked", $(this).prop("checked"));
        });

        $('#all').on('change', function (e) {
            var val = $(this).val();
            if (val == 1) {
                $("input[type='checkbox']").attr("disabled", "disabled");
            } else {
                $("input[type='checkbox']").removeAttr("disabled");
            }
        });

        var btnClose = $('.close');
        var alertBox = $('.alert-box');
        btnClose.on('touchstart click', function (e) {
            e.preventDefault();
            alertBox.hide();
        });
        $("a[pos]").click(function () {
            var pos = $(this).attr("pos");
            $('#pos').html(pos);
            alertBox.css('display', 'block');
        });

        $("#gprsType").val("${pager.params.gprsType}");
        $("#onlineState").val("${pager.params.onlineState}");
    });
</script>
</body>
</html>