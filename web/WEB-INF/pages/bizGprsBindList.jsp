<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">商家绑定<a href="${ctx}/navCtrl/prod" class="back"><i class="icon iconfont">
        &#xe67f;</i></a></h1>
	<script type="text/javascript">
        $(document).ready(function(){
            $("tr:even").css("background","#FFFFFF");
        });
    </script>
</header>
<!-- 商品类型选择 -->
<section class="mart2b2 clearfix">
    <form name="theForm" id="theForm" action="${ctx}/biz/bizGprsBind/query" method="post">
        <div class="selec mart2b2 clearfix">
            <c:if test="${admin}">
                <label>是否绑定</label>
                <select name="bind" id="bind" class="input-style">
                    <option value="0">未绑定</option>
                    <option value="1">已绑定</option>
                </select>
            </c:if>
            <c:if test="${!admin}">
                <label>是否经营</label>
                <select name="prodSet" id="prodSet" class="input-style">
                    <option value="0">未经营</option>
                    <option value="1">已经营</option>
                </select>
            </c:if>
            <label>模块编号</label>
            <input type="text" id="gprsNo" name="gprsNo" placeholder="模块编号" value="${pager.params.gprsNo}" class="input-style" style="width: 110px">
            <input type="submit" class="input-style bgactive ri" value="搜索">
        </div>
    </form>

    <div class="selec-list clearfix body-bg">
        <div class="conl">
            <ul>
                <li>
                    <label>请选择商家</label>
                    <select name="bizNo" id="bizNo" class="input-style" style="width: 4rem">
                        <c:forEach var="biz" items="${bizList}">
                            <option value="${biz.bizNo}">${biz.name}</option>
                        </c:forEach>
                    </select>
                </li>
                <li style="width: 1.0rem"></li>
                <c:if test="${admin}">
                    <li><button type="button" class="btn2" onclick="bindBiz(this)">商家绑定</button></li>
                    <li><button type="button" class="btn2" onclick="unbindBiz(this)">商家解绑</button></li>
                    <li><button type="button" class="btn2" onclick="moveModel(this)">模块移动</button></li>
                </c:if>
                <c:if test="${!admin}">
                    <li><a href="#" class="input-style" onclick="unbindBiz(this)">商家解绑</a></li>
                    <c:if test="${grade==3}">
                        <li><a href="#" class="input-style" onclick="moveModel(this)">模块移动</a></li>
                    </c:if>
                </c:if>
            </ul>
        </div>
        <div style="height: 5px;">&nbsp;</div>
        <table class="selec-list-table">
            <tr>
                <td width="12%" align="center"  class="tr_bg1"><label for="chk_all"><input type="checkbox" id="chk_all">全选</label></td>
                <td width="15%" class="tr_bg2">编号</td>
                <td width="35%" class="tr_bg2">绑定商家</td>
                <c:if test="${admin}">
                    <td width="18%" class="tr_bg2">是否绑定</td>
                </c:if>
                <c:if test="${!admin}">
                    <td width="18%" class="tr_bg2">是否经营</td>
                </c:if>
                <td width="20%" class="tr_bg3">操作日期</td>
            </tr>
            <c:forEach var="gprs" items="${pager.recordList}">
                <tr>
                    <td width="12%"><label for="chk_list"><input type="checkbox" value="${gprs.gprsNo}" id="chk_list" name="chk_list"></label></td>
                    <td width="15%">${gprs.gprsNo}</td>
                    <td width="35%" style="width: 100px;">${fn:substring(gprs.bizName,0,7)}</td>
                    <c:if test="${admin}">
                        <td width="18%">${gprs.bindStr}</td>
                    </c:if>
                    <c:if test="${!admin}">
                        <td width="18%">${gprs.prodSetStr}</td>
                    </c:if>
                    <td width="20%"><fmt:formatDate pattern="yyyy-MM-dd" value="${gprs.uptime}"/></td>
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

<!-- end -->
<%@include file="_layout_nav.jsp" %>

<script>
    $(document).ready(function () {
        var btnClose = $('.close');
        var alertBox = $('.alert-box');
        btnClose.on('touchstart click', function (e) {
            e.preventDefault();
            alertBox.hide();
        });

        $("#chk_all").click(function () {
            $("input[name='chk_list']").prop("checked", $(this).prop("checked"));
        });
        var bind="${pager.params.bind}";
        if(bind!="")  {
            $("#bind").val(bind);
        }
    });

    function bindBiz(btn) {
        var bizNo = $("#bizNo").val();
        if (bizNo && bizNo.length > 0) {
            var gprsList = $("input[name='chk_list']:checked");
            if (gprsList && gprsList.length > 0) {
                var gprsNoList = "";
                gprsList.each(function () {
                    gprsNoList = gprsNoList + "_" + $(this).val();
                });
                $(btn).attr("disabled", "true");  //按钮禁止点击
                $(btn).html("绑定中...");
                $.post(_CTX + "/biz/bizGprsBind/bind.json",
                        {bizNo: bizNo, gprsNoList: gprsNoList},
                        function (data) {
                            $(btn).removeAttr("disabled");  //按钮禁止点击
                            $(btn).html("商家绑定");
                            if (data) {
                                if (data.success) {
                                    alert("模块绑定成功");
                                } else {
                                    alert("部分模块未绑定：" + data.msg + ",原因可能是模块已经绑定过");
                                }
                                window.location.href = _CTX + "/biz/bizGprsBind/query";
                            }
                        }, "json");
            } else {
                alert("请选择模块");
            }
        } else {
            alert("请选择商家");
        }

    }

    function unbindBiz(btn) {
        var gprsList = $("input[name='chk_list']:checked");
        if (gprsList && gprsList.length > 0) {
            var r=confirm("解绑后模块将移动到上级商家，确定要解绑吗？");
            if(r==true){
                var gprsNoList = "";
                gprsList.each(function () {
                    gprsNoList = gprsNoList + "_" + $(this).val();
                });
                $(btn).attr("disabled", "true");  //按钮禁止点击
                $(btn).html("解绑中...");
                $.post(_CTX + "/biz/bizGprsBind/unbind.json",
                        {gprsNoList: gprsNoList},
                        function (data) {
                            $(btn).removeAttr("disabled");  //按钮禁止点击
                            $(btn).html("商家解绑");
                            if (data) {
                                if (data.success) {
                                    alert("解除绑定成功");
                                } else {
                                    alert("部分模块未解除绑定：" + data.msg + ",原因可能是模块不隶属于本商家");
                                }
                                window.location.href = _CTX + "/biz/bizGprsBind/query";
                            }
                        }, "json");
            }
        } else {
            alert("请选择模块");
        }
    }

    function moveModel(btn) {
        var bizNo = $("#bizNo").val();
        var gprsList = $("input[name='chk_list']:checked");
        if (gprsList && gprsList.length > 0) {
            var gprsNoList = "";
            gprsList.each(function () {
                gprsNoList = gprsNoList + "_" + $(this).val();
            });
            $(btn).attr("disabled", "true");  //按钮禁止点击
            $(btn).html("移动中...");
            $.post(_CTX + "/biz/bizGprsBind/moveModel.json",
                    {bizNo:bizNo,gprsNoList: gprsNoList},
                    function (data) {
                        $(btn).removeAttr("disabled");  //按钮禁止点击
                        $(btn).html("模块移动");
                        if (data) {
                            if (data.success) {
                                alert(data.msg);
                            } else {
                                alert("部分模块未移动：" + data.msg + ",原因可能是模块不隶属于本商家");
                            }
                            window.location.href = _CTX + "/biz/bizGprsBind/query";
                        }
                    }, "json");
        } else {
            alert("请选择模块");
        }
    }
</script>
</body>
</html>