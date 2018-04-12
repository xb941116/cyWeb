<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../_layout_header.jsp" %>
<link rel="stylesheet" href="../css/demo.css"/>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=FB14a133b9bf40fec90d36c84fb4dd13"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/DistanceTool/1.2/src/DistanceTool_min.js"></script>
<script src="../js/vue.js"></script>

<body>
<header>
    <h1 class="header fc">
        注册会员
    </h1>
</header>

<!-- 订单列表搜索 -->
<section class="mart2b2 clearfix">
    <form id="form" action="${ctx}/custWeiXin/mbrRegister">

        <div class="para-set clearfix">

            <ul>
                <li>
                    <span>商家名称</span>
                </li>
                <li>
                    ${biz.name}
                </li>
                <li id="hd1" >
                    <input type="hidden" id="bizNo" name="bizNo" value="${bizNo}"/>
                    <input type="hidden" id="gprsNo" name="gprsNo" value="${gprsNo}"/>
                    <span>昵称</span>
                    <span class="red">*</span>
                </li>
                <li id="hd2" >
                    <input id="name" name="name" type="text" value="${name}" class="style01">
                </li>
                <li id="hd3_1" >
                    <span>手机号</span>
                    <span class="red">*</span>
                </li>
                <li id="hd4_1" >
                    <input id="mobile" name="mobile" type="text" value="${mobile}" class="style01">
                </li>
                <!--<li id="hd8_1">
                    
                    <input type="button" onclick="getPOI();" value="确定" />

                </li>-->
                <li id="hd5_1" >
                    <span>微信昵称</span>
                    <span class="red">*</span>
                </li>
                <li id="hd6_1" >
                    <input id="nick" name="nickname" readonly="readonly" type="text" value="${nickname}" class="style01">
                </li>
                <li id="hd7_1" >
                    <span class="red">${message}</span>
                </li>
            </ul>
        </div>
        <button type="button" id="send" onclick="toSubmit()" class="btn" style="width: 40%;float: left;margin-left: 5%;">注册</button>
        <button type="button" onclick="javascript:history.go(-1);" class="btn"  style="width: 40%;float: right;margin-right: 5%;">取消</button>
        
    </form>

</section>
<script>

    /* function reloadSelect() {
   $.ajax({
         type: "get",        //type：(string)请求方式，POST或GET
         dataType: "json",    //dataType：(string)预期返回的数据类型。xml,html,json,text等
         url: "http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location=34.799674425456,113.61658992643&output=json&pois=5&ak=8FcT81aeeGnSSC1GeOeHMlOAe6bK4som",  //url：(string)发送请求的地址，可以是服务器页面也可以是WebService动作。
         success: function (msg) {
             var str = $("mySelect");
             for (i in msg) {
                var app = new Option(msg[i].name,msg[i].name);
                 //str.append( "<option value='"+msg[i].name+"'>" + msg[i].name + "</option>");
                 str.options.add(app);
             }

         }
     });
 }*/

    var map= new BMap.Map("container");
    var mPoint= new BMap.Point(104.0831760000, 30.6608250000);
    map.centerAndZoom(mPoint, 16);
    function getPOI(){
        var ss=displayPOI(104.0831760000, 30.6608250000,1000);//根据需要自己传经纬度
    }
    var myGeo = new BMap.Geocoder();
    function displayPOI(lng,lat,r){//参数：经lat、纬度lng、半径r
        var mOption = {
            poiRadius : r,           //半径为r米内的POI,
            numPois : 1             //最多只有12个 系统决定的
        }
        var ponits_=[];//经纬度和地址信息
        myGeo.getLocation(mPoint,
                function mCallback(rs){
                    var allPois = rs.surroundingPois; //获取全部POI(半径R的范围 最多12个点)
                    console.log(allPois);
                    if(allPois==null || allPois==""){
                        return;
                    }
                    var disMile=[];//储存周围的点和指定点的距离
                    for(i=0;i<allPois.length;i++){//计算得到的POI坐标和指定坐标的距离
                        var pointA=new BMap.Point(allPois[i].point.lng,allPois[i].point.lat);
                        disMile.push({'lng':allPois[i].point.lng,'lat':allPois[i].point.lat,'distance':map.getDistance(pointA, mPoint)}) ;
                    }
                    var result=arrBubble(disMile);//disMile进行升序排列后的数组
                    if(result!=null){
                        if(result.length>=3){//获取最多3个点  可以自己设定  但是最多就12个点
                            var k1=0;//去掉重复
                            var k2=0;
                            var k3=0;
                            for(var i=0;i<allPois.length;i++){
                                if(result[0].lng==allPois[i].point.lng && result[0].lat==allPois[i].point.lat && k1==0){
                                    ponits_.push({'lng':result[0].lng,'lat':result[0].lat,'address':allPois[i].title});
                                    k1++;

                                }else if(result[1].lng==allPois[i].point.lng && result[1].lat==allPois[i].point.lat && k2==0){
                                    ponits_.push({'lng':result[1].lng,'lat':result[1].lat,'address':allPois[i].title});
                                    k2++;

                                }else if(result[2].lng==allPois[i].point.lng && result[2].lat==allPois[i].point.lat && k3==0){
                                    ponits_.push({'lng':result[2].lng,'lat':result[2].lat,'address':allPois[i].title});
                                    k3++;
                                }
                            }
                        }else if(result.length==2){
                            var k1=0;
                            var k2=0;
                            for(var i=0;i<allPois.length;i++){
                                if(result[0].lng==allPois[i].point.lng && result[0].lat==allPois[i].point.lat && k1==0){
                                    ponits_.push({'lng':result[0].lng,'lat':result[0].lat,'address':allPois[i].title});
                                    k1++;
                                }else if(result[1].lng==allPois[i].point.lng && result[1].lat==allPois[i].point.lat && k2==0){
                                    ponits_.push({'lng':result[1].lng,'lat':result[1].lat,'address':allPois[i].title});
                                    k2++;
                                }
                            }
                        }else if(result.length==1){
                            var k1=0;
                            for(var i=0;i<allPois.length;i++){
                                if(result[0].lng==allPois[i].point.lng && result[0].lat==allPois[i].point.lat && k1==0){
                                    ponits_.push({'lng':result[0].lng,'lat':result[0].lat,'address':allPois[i].title});
                                    k1++;
                                }
                            }
                        }
                    }
                    console.log(ponits_);
                    if(ponits_!=null){//这里的points_包含了所需的经纬度和地址信息  具体操作自己设置
                        for(var i=0;i<ponits_.length;i++){
                            var pt=new BMap.Point(ponits_[i].lng,ponits_[i].lat);
                            map.addOverlay(new BMap.Marker(pt));
                        }
                    }

                },mOption
        );
    }
    //排序
    function arrBubble(arr){
        for(var i=0;i<arr.length;i++){
            for(var j=0;j<arr.length-1;j++){
                if(arr[j+1].distance<arr[j].distance){
                    var temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }

            }
        }
        return arr;
    }










    function toSubmit() {
        var btn = $("#send");
        btn.attr("disabled", "true");  //按钮禁止点击

        var name=$("#name").val();
        if(name==''||name.length==0){
            alert("请输入昵称");
            btn.removeAttr("disabled");
            return false;
        }
        if (name.length>10){
            alert('昵称不能超过十个字！');
            btn.removeAttr("disabled");
            return false;
        }
        var myreg = /^((1)+\d{10})$/;
        if(!myreg.test($("#mobile").val()))
        {
            alert('请输入有效的手机号码！');
            btn.removeAttr("disabled");
            return false;
        }

        $("#form").submit();
    }
</script>
<!-- end -->

</body>
</html>