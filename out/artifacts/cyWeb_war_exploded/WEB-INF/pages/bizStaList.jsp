<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<header>
    <h1 class="header fc">统计报表 <a href="${ctx}/navCtrl/home" class="back"><i class="icon iconfont">&#xe67f;</i></a></h1>
	<script type="text/javascript">
        $(document).ready(function(){
            $("tr:even").css("background","#FFFFFF");
        });
    </script>
</header>

<!-- 商品类型选择 -->
<section class="mart2b2">
    <form name="theForm" id="theForm" action="${ctx}/stat/rpt" method="post">
        <!-- 商品类型选择 -->
        <section>
            <div class="selec">
                <ul>
                    <label for="beginDate">查询时间：</label>
                    <input type="hidden" name="devType" id="dev_type" value="">
                    <input type="date" name="beginDate" id="beginDate" class="input-style" placeholder="" value="${pager.params.beginDate}" style="width:4.2rem;">
                    <input type="date" name="endDate" id="endDate" class="input-style" placeholder="" value="${pager.params.endDate}" style="width:4.2rem;">
                    <a  href="javascript:void(0);" class="input-style bgactive ri" onclick="queryProd()"> 搜 索 </a>
                </ul>
            </div>
        </section>
    </form>
    <div class="selec-list clearfix">
        <div class="conl clearfix" style="background-color: #ffffff;">
            <ul class="clearfix">
                <li>
                    <select name="devType" id="devType" class="input-style2" style="width: 4.0rem;">
                        <option value=""  <c:if test="${empty  devType}">selected</c:if>>全部商品</option>
                        <c:forEach var="dict" items="${dictList}">
                            <option value="${dict.code}" <c:if test="${dict.code eq devType}">selected="selected"</c:if>>${dict.val}</option>
                        </c:forEach>
                    </select>
                </li>
                <li><a href="#" flag="day" onclick="query('day')" <c:if test="${flag == 'day'}"> class="input-style bgactive" </c:if><c:if
                        test="${flag != 'day'}"> class="input-style" </c:if>  >当日</a></li>
                <li><a href="#" flag="week" onclick="query('week')"  <c:if test="${flag=='week'}"> class="input-style bgactive" </c:if><c:if
                        test="${flag!='week'}"> class="input-style" </c:if> >当周</a></li>
                <li><a href="#" flag="month" onclick="query('month')"  <c:if test="${flag=='month'}"> class="input-style bgactive" </c:if><c:if
                        test="${flag!='month'}"> class="input-style" </c:if>  >当月</a></li>
                <li><a href="#" flag="" onclick="query('')"  <c:if test="${empty flag&&empty pager.params.beginDate&&empty pager.params.endDate}"> class="input-style bgactive" </c:if><c:if
                        test="${not empty flag||not empty pager.params.beginDate||not empty pager.params.endDate}"> class="input-style" </c:if>  >全部</a></li>
                <li>
                    <button id="send" class="input-style2" onclick="getBizCoinRpt()">实时收入</button>
                </li>
                <li>&nbsp;
                    <button id="export" class="input-style2" onclick="exportExcel()">报表导出</button>
                </li>
            </ul>
        </div>
        <div class="body-bg">
        <div style="height: 5px;">&nbsp;</div>

            <table class="selec-list-table">
                <tr>
                    <td width="16%" class="tr_bg1">产品编号</td>
                    <td width="16%" class="tr_bg2">模块编号</td>
                    <td width="16%" class="tr_bg2">微信收入</td>
                    <td width="16%" class="tr_bg2">钱包收入</td>
                    <td width="16%" class="tr_bg2">刷卡收入</td>
                    <td width="16%" class="tr_bg3">投币收入</td>
                </tr>
                <c:forEach items="${pager.recordList}" var="prod">
                    <tr onclick="getAddr('${prod.prodNo}')">
                        <td width="16%" id="prod_online">${prod.prodNo}</td>
                        <td width="16%" id="online"><c:if test="${empty prod.gprsNo }">模块移除</c:if>${prod.gprsNo}</td>
                        <td width="16%" id="wx">${prod.wxInc}</td>
                        <td width="16%" id="wlt">${prod.wltInc}</td>
                        <td width="16%" id="card">${prod.cardInc}</td>
                        <td width="16%" id="coin">${prod.coinInc}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td width="16%">合计：</td>
                    <td width="16%"></td>
                    <td width="16%">${totalWxInc}</td>
                    <td width="16%">${totalWltInc}</td>
                    <td width="16%">${totalCardInc}</td>
                    <td width="16%">${totalCoinInc}</td>
                </tr>
            </table>
            <section class="fc">
                <div class="page clearfix">
                    ${pager.formPageStr}
                </div>
            </section>
        </div>
    </div>
</section>
<%@include file="_layout_nav.jsp" %>
<script>

    $().ready(function () {
        $("#devType").on('select', function () {

        });
    });

    function exportExcel(){
        var devType = $('#devType').val();
        var flag = $('a.bgactive').attr('flag');
        if (flag==undefined){
            flag="";
        }
        var beginDate = $('#beginDate').val();
        var endDate = $('#endDate').val();
       window.location.href = _CTX + "/stat/exportXsl?flag=" + flag + "&devType=" + devType + "&beginDate="+beginDate+"&endDate="+endDate;
    }

    function query(period) {
        var devType = $('#devType').val();
        $('#beginDate').val("");
        $('#endDate').val("");
        window.location.href = _CTX + "/stat/rpt?flag=" + period + "&devType=" + devType;
    }

    function queryProd() {
        var beginDate=$("#beginDate").val();
        var endDate=$("#endDate").val();
        if(beginDate!=''&&endDate!=''&&beginDate>endDate){
            alert("结束时间应该大于开始时间！");
            return false;
        }
        var devType = $('#devType').val();
        $('#dev_type').val(devType);
        $('#theForm').submit();
    }


    var hander = null;
    function timer(time) {
        var btn = $("#send");
        btn.attr("disabled", "true");  //按钮禁止点击
        btn.html(time <= 0 ? "实时收入" : ("上报中" + (time) + ""));
        if (time <= 0) {
            btn.removeAttr("disabled");  //按钮禁止点击
            clearInterval(hander);
            hander = null;
        } else {
            hander = setInterval(function () {
                if (time <= 0) {
                    clearInterval(hander); //清除倒计时
                    btn.html("实时收入");
                    btn.removeAttr("disabled");
                    location.href=_CTX+"/stat/rpt?flag=day";
                    return false;
                } else {
                    btn.html("上报中" + (time--) + "");
                }
            }, 1000);
        }
    }
    function getBizCoinRpt(){
        timer(10);
        $.ajax({
            type: "GET",
            url: _CTX+"/prod/getBizCoinRpt.json",
            dataType: "json",
            success: function(data){
                if (data.success){
                   /* alert("获取成功");*/
                    /*location.href=_CTX+"/stat/rpt?flag=day";*/
                }else{
                    alert(data.msg);
                    timer(0);
                }
            },
            error:function(data){
                timer(0);
                alert("请检查网络，刷新后再尝试！");
            }
        });
    }

    function getAddr(prodNo) {
        location.href=_CTX+"/stat/loadProdInstlPosByProdNo?prodNo="+prodNo;
    }

</script>
<!-- end -->
</body>

</html>