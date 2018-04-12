<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx"
       value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
  <title><c:if test="${sessionScope.wxBizNoLogo!=null&&sessionScope.wxBizNoLogo!=''}">${sessionScope.wxBizNoName}</c:if><c:if test="${sessionScope.wxBizNoLogo==null||sessionScope.wxBizNoLogo==''}">便易充</c:if>智能充电</title>
  <script src="${ctx}/static/js/jquery-1.9.1.min.js"></script>
  <script>
    var _CTX = '${ctx}';
  </script> 
   
</head>
    <!--<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=vzbZHgsR8ApclNp5HQq4fswj"></script> -->
   <!--<script type="text/javascript"
          src="http://api.map.baidu.com/api?type=quick&ak=8FcT81aeeGnSSC1GeOeHMlOAe6bK4som&v=1.0"></script>-->
  <script type="text/javascript"
          src="http://api.map.baidu.com/api?v=2.0&ak=8FcT81aeeGnSSC1GeOeHMlOAe6bK4som"></script>
 <!--<script type="text/javascript"
          src="http://api.map.baidu.com/api?v=2.0&ak=MAHzcSq32CwqPeteRa4847SOCGI7MuMy"></script>-->
		  
		<!--<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=l1uysHZFe8dhWsxq1qPNiaX1bFZUyh21"></script>-->
<body>
<style type="text/css">
    body, html {
        width: 100%;
        height: 100%;
        margin: 0;
        font-family: "微软雅黑";
    }

    #allmap {
        width: 100%;
        height: 100%;
        z-index:0;
        margin-top: -85px;
    }

    p {
        margin-left: 5px;
        font-size: 14px;
    }
    .sp1_s {
        display: inline-block;
        background: url(/static/img/weixin/us_cursor_1.gif) no-repeat;
        overflow: hidden;
    }
    .sp1_1 {
      width: 19px;
      height: 25px;
      background-position: 0px -21px;
      margin-top: 3px;
    }
    .sp1_2 {
      width: 19px;
      height: 25px;
      background-position: -23px -21px;
      margin-top: 3px;
    }
    .sp1_3 {
        width: 19px;
        height: 25px;
         background-position: -46px -21px;
         margin-top: 3px;
    }
    .sp1_4 {
        width: 19px;
        height: 25px;
         background-position: -69px -21px;
         margin-top: 3px;
    }
    .sp1_5 {
        width: 19px;
        height: 25px;
        background-position: -92px -21px;
        margin-top: 3px;
    }
    .sp1_6 {
      width: 19px;
      height: 25px;
      background-position: -115px -21px;
      margin-top: 3px;
    }

    .clearfix {
      zoom: 1;
    }

    .clearfix:after {
      content: ' ';
      display: block;
      clear: both;
      visibility: hidden;
      line-height: 0;
      height: 0;
    }
    #ig{
        width: 170px;
        height: 80px;
        z-index:1;
        position:relative;
        top:480px;
        left:22px;
    }
	 #wd{
        width: 70px;
        height: 37px;
        z-index:1;
        position:relative;
        top:50px;
        left:-12px;
    }
	 #kf{
        width: 50px;
        height: 50px;
        z-index:1;
        position:relative;
        top:467px;
        left:60px;
    }

</style>

<section class="mart2b2 clearfix">
	<a href="http://www.bianyichong.cn/custWeiXin/authorizeUrl?state=B100000_5"><img id="wd" src="../static/img/weixin/wode.png"/></a><!--个人中心-->
    <a href="http://www.bianyichong.cn/custWeiXin/scanQRCode?bizNo=B100000"><img id="ig" src="../static/img/weixin/saoma.png"/></a>
	<a href="tel:13253590033"><img id="kf" src="../static/img/weixin/kefu.png"/></a><!--拨打售后电话-->
    <div id="allmap"></div>
</section>

</body>

<script>
  $("#allmap").height(document.body.clientHeight);
</script>
</html>
<script type="text/javascript">

	// 百度地图API功能	
	var map = new BMap.Map("allmap");
	var point = new BMap.Point(${longitude},${latitude});//接收传来的经纬度参数GPS定位
	map.centerAndZoom(point, 15);

	//创建自定义函数
	function addMarker(point,Icon,content,title){
	  var marker = new BMap.Marker(point);//创建标注
	  
	  var opts = {
				width : 150,     // 信息窗口宽度
				height: 80,     // 信息窗口高度
				 title : title, // 信息窗口标题
				 enableMessage:true,//设置允许信息窗发送短息
				 message:content
				
			   }
	   marker.setIcon(Icon);//替换图片
	   map.addOverlay(marker);// 将标注添加到地图中
	   
	   var infoWindow = new BMap.InfoWindow(content,opts);
	   marker.addEventListener("click", function(){          
	   map.openInfoWindow(infoWindow,point); //开启信息窗口
	 });
	
	}

	/*var geolocation = new BMap.Geolocation();
	geolocation.getCurrentPosition(function(r){
		if(this.getStatus() == BMAP_STATUS_SUCCESS){
			var mk = new BMap.Marker(r.point);
			map.addOverlay(mk);
			map.panTo(r.point);
			alert('您的位置：'+r.point.lng+','+r.point.lat);
		}
		else {
			alert('failed'+this.getStatus());
		}        
	},{enableHighAccuracy: true})*///根据浏览器获取当前经纬度值定位当前位置
	

	 //当前位置
     var myIcon = new BMap.Icon("/static/img/weixin/us_cursor_1.gif", new BMap.Size(21, 23),{

       //offset: new BMap.Size(10, 25), // 指定定位位置
       imageOffset: new BMap.Size(-23*3, -21) // 设置图片偏移-21
     });
     //图标一
     var oneIcon = new BMap.Icon("/static/img/weixin/us_cursor_1.gif", new BMap.Size(21, 23), {
       //offset: new BMap.Size(10, 25), // 指定定位位置
       imageOffset: new BMap.Size(-23*2, -21) // 设置图片偏移-21
      });

	  //图标一
     var twoIcon = new BMap.Icon("/static/img/weixin/us_cursor_1.gif", new BMap.Size(21, 23), {
       //offset: new BMap.Size(10, 25), // 指定定位位置
       imageOffset: new BMap.Size(0, -21) // 设置图片偏移-21
      });
	 addMarker(point,myIcon,"当前位置","");

    
	var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
	
	//var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL}); //右上角，仅包含平移和缩放按钮
	/*缩放控件type有四种类型:
	BMAP_NAVIGATION_CONTROL_SMALL：仅包含平移和缩放按钮；BMAP_NAVIGATION_CONTROL_PAN:仅包含平移按钮；BMAP_NAVIGATION_CONTROL_ZOOM：仅包含缩放按钮*/
	
	//添加控件和比例尺
	
	map.addControl(top_left_control);        
		   
	//map.addControl(top_right_navigation);   //右上角，仅包含平移和缩放按钮
	
	  // 添加带有定位的导航控件
    var navigationControl = new BMap.NavigationControl({
    // 靠左上角位置
    anchor: BMAP_ANCHOR_TOP_RIGHT,
    // LARGE类型
    type: BMAP_NAVIGATION_CONTROL_LARGE,
    // 启用显示定位
    enableGeolocation: true
    });
    map.addControl(navigationControl);
    // 添加定位控件
    var geolocationControl = new BMap.GeolocationControl();
    geolocationControl.addEventListener("locationSuccess", function(e){
    // 定位成功事件
    var address = '';
    address += e.addressComponent.province;
    address += e.addressComponent.city;
    address += e.addressComponent.district;
    address += e.addressComponent.street;
    address += e.addressComponent.streetNumber;
    alert("当前定位地址为：" + address);
  });
  geolocationControl.addEventListener("locationError",function(e){
    // 定位失败事件
    alert(e.message);
  });
  map.addControl(geolocationControl);


	
 var distance=100000;
  $(function () {
    $.ajax( {
      type : "POST",
      url : "${ctx}/custWeiXin/queryGeo.json",
      data: {
        "lng" : "${longitude}",
        "lat" : "${latitude}",
        "bizNo" : "${bizNo}",
        "distance" : distance
      },success : function(data) {
	      for(var i=0;i<data.length;i++){
		    var mypoint = new BMap.Point(data[i].lng,data[i].lat);  // 创建标注
		    map.addOverlay(mypoint);              // 将标注添加到地图中
		    if (data[i].onlineState==1){
               addMarker(mypoint,"","地址:"+data[i].addr,data[i].prodName+"(在线)");
		    }else {
			   addMarker(mypoint,"oneIcon","地址:"+data[i].addr,data[i].prodName+"(离线)");
            }
	      }  	
	    }

	  });

   });
	

		// 编写自定义函数,创建标注
	/*function adddMarker(point){
	  var marker = new BMap.Marker(point);
	  map.addOverlay(marker);
	}
	// 随机向地图添加25个标注
	var bounds = map.getBounds();
	var sw = bounds.getSouthWest();
	var ne = bounds.getNorthEast();
	var lngSpan = Math.abs(sw.lng - ne.lng);
	var latSpan = Math.abs(ne.lat - sw.lat);
	for (var i = 0; i < 25; i ++) {
		var point = new BMap.Point(sw.lng + lngSpan * (Math.random() * 0.7), ne.lat - latSpan * (Math.random() * 0.7));
		adddMarker(point);
	}*/

	// 随机向地图添加25个标注
	/*var bounds = map.getBounds();
	var sw = bounds.getSouthWest();
	var ne = bounds.getNorthEast();
	var lngSpan = Math.abs(sw.lng - ne.lng);
	var latSpan = Math.abs(ne.lat - sw.lat);
	for (var i = 0; i < 50; i ++) {
		var point = new BMap.Point(sw.lng + lngSpan * (Math.random() * 0.7), ne.lat - latSpan * (Math.random() * 0.7));
		addMarker(point);
	}*/
	

	var data_info = [[113.7036504649,34.8215955824,"地址：郑州市金水区龙湖外环西路维也纳森林"],
					 [113.7035109900,34.8218422137,"地址：郑州市金水区龙湖外环西路维也纳森林"],
					 [113.7033554219,34.8223310699,"地址：郑州市金水区龙湖外环西路维也纳森林"],
					 [113.7028511666,34.8223618986,"地址：郑州市金水区龙湖外环西路维也纳森林"],
					 [113.7021055125,34.8224103437,"地址：郑州市金水区龙湖外环西路维也纳森林"],

					 [113.7033554219,34.8225424664,"地址：郑州市金水区龙湖外环西路维也纳森林"],
					 [113.7027385138,34.8227450541,"地址：郑州市金水区龙湖外环西路维也纳森林"],
					 [113.7045999669,34.8220976524,"地址：郑州市金水区龙湖外环西路维也纳森林"],
					 [113.7048896455,34.8216352196,"地址：郑州市金水区龙湖外环西路维也纳森林"],
					 [113.7039133214,34.8229080047,"地址：郑州市金水区龙湖外环西路维也纳森林"],

					 [113.7048413657,34.8230753591,"地址：郑州市金水区龙湖外环西路维也纳森林"],
					 [113.7038435840,34.8236566927,"地址：郑州市金水区龙湖外环西路维也纳森林"],
					 [113.7046428822,34.8224984255,"地址：郑州市金水区龙湖外环西路维也纳森林"],
					 [113.7041439914,34.8221945428,"地址：郑州市金水区龙湖外环西路维也纳森林"],
					 [113.7038382195,34.8223707068,"地址：郑州市金水区龙湖外环西路维也纳森林"],
					 [113.7047126197,34.8212608673,"地址：郑州市金水区龙湖外环西路维也纳森林"],

					 [113.5852338416,34.7945884966,"地址：郑州市中原区南流小区"],
					 [113.5852138416,34.7942684966,"地址：郑州市中原区南流小区"],
					 [113.5852038416,34.7939884966,"地址：郑州市中原区南流小区"],
					 [113.5843538416,34.7943084966,"地址：郑州市中原区南流小区"],
					 [113.5852538416,34.7928284966,"地址：郑州市中原区南流小区"],

					 [113.7171954897,34.7163396244,"地址：郑州市管城回族区金穗小区"],
					 [113.7172554897,34.7167296244,"地址：郑州市管城回族区金穗小区"],
					 [113.7175954897,34.7163496244,"地址：郑州市管城回族区金穗小区"],
					 [113.7175554897,34.7172796244,"地址：郑州市管城回族区金穗小区"],
					

					 [113.6725614714,34.7453130559,"地址：郑州市二七区一马路欢乐湖购物广场附近"],
					 [113.6946782475,34.8042977636,"地址：郑州市金水区北林路街道北林路院校社区南方向"],
					 [113.6347228808,34.7232247088,"地址：郑州市二七区嵩山南路绿城花园"],
					 [113.6355831386,34.7229588407,"地址：郑州市二七区嵩山南路绿城花园"],
					 

					 [113.7555261176,34.7672285003,"地址：郑州视光眼科医院附近"],
					 [113.7554510157,34.7672064650,"地址：郑州视光眼科医院附近"],

					 [113.6384598991,34.7745678049,"地址：河南省郑州市中原区北站路5号院"],
					 [113.6382009083,34.7747453692,"地址：河南省郑州市中原区北站路5号院"],

					 [113.6493966070,34.8030163126,"地址：郑州市金水区和众公寓"],
					 [113.6492088524,34.8031528726,"地址：郑州市金水区和众公寓"],
					 [113.6491873947,34.8033995610,"地址：郑州市金水区和众公寓"],
					 [113.6498633114,34.8035977923,"地址：郑州市金水区和众公寓"],
					 [113.6494717089,34.8035933872,"地址：郑州市金水区和众公寓"],
					 [113.6487636057,34.8036638693,"地址：郑州市金水区和众公寓"],

					 [113.7514520392,34.7207721102,"地址：郑州市管城回族区百度花园"],

					 [113.6896637399,34.8433242875,"地址：郑州市金水区佳帆阳光水岸"],
					 [113.6892537399,34.8437642875,"地址：郑州市金水区佳帆阳光水岸"],
					 [113.6898337399,34.8434442875,"地址：郑州市金水区佳帆阳光水岸"],
					 [113.6895473903,34.8434430917,"地址：郑州市金水区佳帆阳光水岸"],
					 [113.6896493143,34.8437336879,"地址：郑州市金水区佳帆阳光水岸"],

					 [113.6811924604,34.7501584647,"地址：郑州市管城回族区德济路9号院"],
					 [113.6814338592,34.7501540568,"地址：郑州市管城回族区德济路9号院"],
					 
					 [113.7043100595,34.7746980769,"地址：河南省郑州市金水区经一路5"],
					 [113.6814338592,34.7501540568,"地址：河南省郑州市金水区经一路5"],

					 [113.7006283558,34.7932856643,"地址：郑州市金水区经三路中银小区"],
					 [113.7002421177,34.7934795129,"地址：郑州市金水区经三路中银小区"],
					 [113.7010628737,34.7933737774,"地址：郑州市金水区经三路中银小区"],

					 [113.6383598991,34.7713678049,"地址：郑州市二七区嵩山路都市丽茵"],
					 [113.6386300617,34.7713389428,"地址：郑州市二七区嵩山路都市丽茵"],

					 [113.6389547225,34.7636399267,"地址：郑州市二七区久久思达"],
					 [113.6390694030,34.7635053096,"地址：郑州市二七区久久思达"],
					 [113.6386456140,34.7636243059,"地址：郑州市二七区久久思达"],
					 [113.6382057317,34.7635758259,"地址：郑州市二七区久久思达"],

					 [113.6106095747,34.7435464828,"地址：郑州市中原区富邦公寓"],
					 [113.6110074163,34.7435702909,"地址：郑州市中原区富邦公寓"],

					 [113.6927333719,34.7498612012,"地址：郑州市管城回族区城南路199号院"],
					 [113.6390694030,34.7635053096,"地址：郑州市管城回族区城南路199号院"],
					 [113.6966563202,34.7893065569,"地址：郑州市金水区丰产路91号院"],
					 [113.6965176208,34.7893135574,"地址：郑州市金水区丰产路91号院"],
					 [113.6967912061,34.7893488043,"地址：郑州市金水区丰产路91号院"],
					 [113.6966356380,34.7892650929,"地址：郑州市金水区丰产路91号院"],

					 [113.6642292604,34.7768913494,"地址：郑州市金水区新新公寓"],
					 [113.6644154517,34.7769001866,"地址：郑州市金水区新新公寓"],
					 [113.6638629166,34.7770059439,"地址：郑州市金水区新新公寓"],
					 [113.6634927718,34.7770323832,"地址：郑州市金水区新新公寓"],

					 [113.6636000601,34.7772923690,"地址：郑州市金水区新新公寓"],
					 [113.6633157460,34.7771910187,"地址：郑州市金水区新新公寓"],
					 [113.6633050171,34.7770015373,"地址：郑州市金水区新新公寓"],
					 [113.6639648405,34.7773496539,"地址：郑州市金水区新新公寓"],

					 [113.6826865310,34.7493173221,"地址：郑州市管城回族区南关街132号院"],
					 [113.6831036086,34.7495551564,"地址：郑州市管城回族区南关街132号院"],
					 [113.6830499645,34.7498152278,"地址：郑州市管城回族区南关街132号院"],

					 [113.6494764676,34.8156496354,"地址：郑州市金水区金桂苑"],
					 [113.6492259914,34.8155932367,"地址：郑州市金水区金桂苑"],

					 [113.6471640492,34.7953990769,"地址：郑州市金水区憧憬家园"],
					 [113.6473893547,34.7955752995,"地址：郑州市金水区憧憬家园"],
					 [113.6472016001,34.7956281663,"地址：郑州市金水区憧憬家园"],

					 [113.7606892392,34.4238070193,"地址：郑州市新郑市碧海蓝天"],
					 [113.7608301157,34.4237837078,"地址：郑州市新郑市碧海蓝天"],
					 [113.7610446924,34.4238146851,"地址：郑州市新郑市碧海蓝天"],
					 [113.7611466164,34.4237837078,"地址：郑州市新郑市碧海蓝天"],

					 [113.6153963251,34.8505566850,"地址：郑州市惠济区珠江荣景"],
					 [113.6150503956,34.8506652429,"地址：郑州市惠济区珠江荣景"],
					 [113.6143530213,34.8507356844,"地址：郑州市惠济区珠江荣景"],
					 [113.6146373354,34.8507929181,"地址：郑州市惠济区珠江荣景"],

					 [113.6333234851,34.7186141453,"地址：郑州市二七区长江中路珠江怡景"],
					 [113.6334916199,34.7186457379,"地址：郑州市二七区长江中路珠江怡景"],
					 [113.6313915479,34.7533581487,"地址：郑州市中原区中原中路郑铝家属院"],
					 [113.6312995425,34.7533848685,"地址：郑州市中原区中原中路郑铝家属院"],

					 [113.7529347484,34.7870121262,"地址：郑州市金水区百子湾路永基美邻"],
					 [113.7520755303,34.7869388283,"地址：郑州市金水区百子湾路永基美邻"],

					 [113.7248496122,34.7349661094,"地址：郑州市管城回族区石化路泰来怡居"],
					 [113.7247570228,34.7349944515,"地址：郑州市管城回族区石化路泰来怡居"],
					 [113.6346006312,34.8321225824,"地址：郑州市惠济区银河街协和制药厂"],

					 [113.5253897064,34.7932861745,"地址：郑州市郑州市中原区光大印染厂"],
					 [113.5252858093,34.7932427535,"地址：郑州市郑州市中原区光大印染厂"],
					 [113.5253930977,34.7931854795,"地址：郑州市郑州市中原区光大印染厂"],
					 [113.6853865310,34.7478173221,"地址：郑州市管城回族区烟厂后街9号院"],

					 [113.6480982763,34.7213035867,"地址：郑州市二七区浪漫之都"],
					 [113.6481863742,34.7210710875,"地址：郑州市二七区浪漫之都"],
					 [113.7131365328,34.7718789179,"地址：郑州市金水区广发花园"],
					 [113.7137522509,34.7718497275,"地址：郑州市金水区广发花园"],

					 [113.6518007850,34.7312942539,"地址：郑州市政云街"],
					 [113.6515631018,34.7312383449,"地址：郑州市政云街"],
					 [113.6518604376,34.7313622216,"地址：郑州市政云街"],
					 [113.6520428279,34.7314856732,"地址：郑州市政云街"],


					 [113.6136256896,34.7241155597,"地址：郑州市中原区茜城花园"],
					 [113.6284173134,34.7236262022,"地址：郑州市二七区警苑小区"],
					 [113.6865261073,34.7480696484,"地址：郑州市管城回族区烟厂后街4号院"],
					 [113.6313915479,34.7533581487,"地址：郑州市中原区黄问路83号"],

					 [113.6046195623,34.7524630318,"地址：郑州市中原区罗庄小区"],
					 [113.6047746790,34.7524050256,"地址：郑州市中原区罗庄小区"],
					 [113.6048336876,34.7525725232,"地址：郑州市中原区罗庄小区"],
					 [113.6045708311,34.7525416684,"地址：郑州市中原区罗庄小区"],

					 [113.6047800434,34.7523036453,"地址：郑州市中原区罗庄小区"],
					 [113.6050697220,34.7523345002,"地址：郑州市中原区罗庄小区"],
					 [113.6047854079,34.7523345002,"地址：郑州市中原区罗庄小区"],
					 [113.6047478569,34.7525504841,"地址：郑州市中原区罗庄小区"],

					 [113.6575661890,34.7749454146,"地址：郑州市金水区中亨花园"],
					 [113.6571994245,34.7748085722,"地址：郑州市金水区中亨花园"],
					 [113.6569580257,34.7747380655,"地址：郑州市金水区中亨花园"],
					 [113.6567702711,34.7750333121,"地址：郑州市金水区中亨花园"],

					 [113.5531377950,34.8306587166,"地址：郑州市中原区翰林国际城"],
					 [113.5535839147,34.8304295371,"地址：郑州市中原区翰林国际城"],
					 [113.5532674140,34.8304339408,"地址：郑州市中原区翰林国际城"],
					 [113.5539272374,34.8305043997,"地址：郑州市中原区翰林国际城"],

					 [113.6943819628,34.8179251070,"地址：郑州市金水区国泰一品庄园"],
					 [113.6942741146,34.8174972094,"地址：郑州市金水区国泰一品庄园"],
					 [113.6944350471,34.8169510669,"地址：郑州市金水区国泰一品庄园"],
					 [113.6943819628,34.8179251070,"地址：郑州市金水区国泰一品庄园"],

					 [113.6102218461,34.7653405598,"地址：郑州市中原区秦岭国际"],
					 [113.6103888729,34.7651666627,"地址：郑州市中原区秦岭国际"],
					 [113.6245015479,34.7610681487,"地址：郑州市中原区郑州总工会"],
					 [113.6242131463,34.7606089417,"地址：郑州市中原区郑州总工会"],

					 [113.5681624976,34.8074558977,"地址：郑州市中原区银发工业园"],
					 [113.5686228945,34.8074897296,"地址：郑州市中原区银发工业园"],
					 [113.6494031569,34.6679238058,"地址：郑州市二七区鑫苑鑫家"],
					 [113.6487467052,34.6678916441,"地址：郑州市二七区鑫苑鑫家"],

					 [113.6094618461,34.7666105598,"地址：郑州市中原区六合幸福门"],
					 [113.6091550568,34.7665593121,"地址：郑州市中原区六合幸福门"],
					 [113.6090584973,34.7666738962,"地址：郑州市中原区六合幸福门"],
					 [113.6092355230,34.7663874358,"地址：郑州市中原区六合幸福门"],

					 [113.6091657856,34.7665989758,"地址：郑州市中原区六合幸福门"],
					 [113.6097075918,34.7665416838,"地址：郑州市中原区六合幸福门"],
					 [113.6095573881,34.7664359138,"地址：郑州市中原区六合幸福门"],
					 [113.6092355230,34.7663874358,"地址：郑州市中原区六合幸福门"],

					 [113.5377403245,34.9182027333,"地址：郑州市管城回族区裕隆钢铁物流园 "],
					 [113.5362942132,34.9155020504,"地址：郑州市管城回族区裕隆钢铁物流园"],
					 [113.6582200475,34.7535715307,"地址：郑州市惠济区思念果岭"],
					 [113.6583076243,34.7535453569,"地址：郑州市惠济区思念果岭"],

					 [113.7971078578,34.7345313820,"地址：郑州市二七区长城宾馆"],
					 [113.7968764284,34.7342745190,"地址：郑州市二七区长城宾馆"],
					 [113.6001291072,34.7614525261,"地址：郑州市中原区阳光世纪城"],
					 [113.5998965103,34.7613929794,"地址：郑州市中原区阳光世纪城"],

					 [113.5999823410,34.7612607587,"地址：郑州市中原区阳光世纪城"],
					 [113.6888657062,34.6125311458,"地址：郑州市新郑市泰山建筑"],
					 [113.7519876446,34.8774202029,"地址：郑州市惠济区大河印象"],
					 [113.7515849000,34.8776333609,"地址：郑州市惠济区大河印象"],

					 [113.6784538026,34.8755222388,"地址：郑州市惠济区永威迎宾府"],
					 [113.6789639225,34.8750335200,"地址：郑州市惠济区永威迎宾府"],
					 [113.6797793141,34.8750159148,"地址：郑州市惠济区永威迎宾府"],
					 [113.6809165707,34.8754032284,"地址：郑州市惠济区永威迎宾府"],

					 [113.7177678547,34.7589828403,"地址：郑州市金水区聂庄安置房"],
					 [113.7181516667,34.7589892145,"地址：郑州市金水区聂庄安置房"],
					 [113.7177278777,34.7590553267,"地址：郑州市金水区聂庄安置房"],
					 [113.7180121919,34.7590905865,"地址：郑州市金水区聂庄安置房"]
					 
					];
	/*var opts1 = {
				width : 150,     // 信息窗口宽度
				height: 80,     // 信息窗口高度
				title : "智能充电桩(在线)" , // 信息窗口标题
				enableMessage:true//设置允许信息窗发送短息
			   };
	for(var i=0;i<data_info.length;i++){
		var youpoint = new BMap.Marker(new BMap.Point(data_info[i][0],data_info[i][1]));  // 创建标注
		var content = data_info[i][2];
		map.addOverlay(marker);               // 将标注添加到地图中
		addClickHandler(content,youpoint);
	}
	function addClickHandler(content,marker){
		marker.addEventListener("click",function(e){
			openInfo(content,e)}
	   
	  
		);
	}
	function openInfo(content,e){
		var p = e.target;
		var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
		var infoWindow = new BMap.InfoWindow(content,opts1);  // 创建信息窗口对象 
		map.openInfoWindow(infoWindow,point); //开启信息窗口
	}*/





	var opts1 = {
				width : 150,     // 信息窗口宽度
				height: 80,     // 信息窗口高度
				title : "智能充电桩(在线)" , // 信息窗口标题
				enableMessage:true//设置允许信息窗发送短息
			   };


	function addClickHandler(content,marker,Icon){
		marker.setIcon(Icon);//替换图片
		marker.addEventListener("click",function(e){
			openInfo(content,e)}
	   
	  
		);
	}

	function openInfo(content,e){
		var p = e.target;
		var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
		var infoWindow = new BMap.InfoWindow(content,opts1);  // 创建信息窗口对象 
		map.openInfoWindow(infoWindow,point); //开启信息窗口
	}

	for(var i=0;i<data_info.length;i++){
		var youpoint = new BMap.Marker(new BMap.Point(data_info[i][0],data_info[i][1]));  // 创建标注
		var content = data_info[i][2];
		map.addOverlay(youpoint);               // 将标注添加到地图中
		addClickHandler(content,youpoint,"");
	}
	
</script>

