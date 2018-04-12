<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../_layout_header.jsp" %>
<script src="${ctx}/static/js/jquery.knob.js"></script>
<style>
    body{
        background: #ffffff;
    }
</style>
<body>
<header>
    <h1 class="header fc">
        信息查询<a  href="javascript:void(0);" onclick="javascript:history.go(-1);" class="back"><i class="icon iconfont">&#xe67f;</i></a>
    </h1>
</header>

<section class="mart2b2 clearfix">
    <div id="knobwrapper">
        <ul>
           <%-- <li style="text-align: center;margin-top: 30px; ">
                <span style="text-align: center;font-weight: bold;font-size: 14px;" >&nbsp;${prod.prodName}&nbsp;</span>
            </li>
            <li style="text-align: center;margin-bottom: 30px;">
                <span style="text-align: center;" >&nbsp;${prod.bizName}&nbsp;</span>
            </li>--%>
            <c:if test="${overplusTime==null}">
                <li style="text-align: center;height: 50px;">
                    &nbsp;
                </li>
                <li style="text-align: center;">
                    <input id="knob2" class="knob2" data-width="150" data-displayinput="true" data-displayprevious="true" readonly  data-min="0" data-max="${maxTime}" value="${usedTime}" >
                </li>

                <li style="text-align: center; color: #03b6a3;">
                    <c:if test="${useing==true&&maxTime!=0}">
                        <span id="spanMsg" style="text-align: center;font-weight: bold;color: #03b6a3;font-size: 0.7rem;" >总共长：${maxTime} 分钟（已经使用：<c:if test="${maxTime>usedTime}">${usedTime}</c:if><c:if test="${maxTime<usedTime}">${maxTime}</c:if> 分钟）</span>
                    </c:if>

                    <c:if test="${useing==true&&maxTime==0}">
                        <span style="text-align: center;color: #03b6a3;font-size: 0.7rem;" >该设备不支持信息查询</span>
                    </c:if>
                    <c:if test="${useing==false}">
                        <span style="text-align: center;font-weight: bold;color: #03b6a3;font-size: 0.7rem;" >您还未使用设备</span>
                    </c:if>
                </li>
            </c:if>
            <c:if test="${overplusTime!=null}">
                <li style="text-align: center;">
                    <span id="spanMsg2" style="text-align: center;font-weight: bold;color: #03b6a3;font-size: 0.7rem;" >剩余时间：${overplusTime} 分钟</span>
                </li>
            </c:if>
        </ul>
    </div>
</section>

<!-- end -->

</body>
<script>
    $(function() {
        $(".knob2").knob({
            'release':function(e){
                $('#img').animate({width:e});
            }
        });
    });

    function timeCg() {
        var maxTime="${maxTime}";
        if (maxTime=="0"){
            return;
        }
        var usedTime=parseInt($("#knob2").val()); ;
        $('.knob2').val(usedTime+1).trigger('change');
        $('#spanMsg').html("总共时长：${maxTime} 分钟（已经使用："+(usedTime+1)+" 分钟）");

    }
    setInterval("timeCg();",60000);
</script>
</html>