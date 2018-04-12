<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016年09月27日16:03:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
  <base href="<%=basePath%>">
  <meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"
        name="viewport" id="viewport"/>
    <title>扫描二维码</title>
</head>
<body>
</body>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
  /*
   * 注意：
   * 1. 所有的JS接口只能在公众号绑定的域名下调用，公众号开发者需要先登录微信公众平台进入“公众号设置”的“功能设置”里填写“JS接口安全域名”。
   * 2. 如果发现在 Android 不能分享自定义内容，请到官网下载最新的包覆盖安装，Android 自定义分享接口需升级至 6.0.2.58 版本及以上。
   * 3. 常见问题及完整 JS-SDK 文档地址：http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html
   *
   * 开发中遇到问题详见文档“附录5-常见错误及解决办法”解决，如仍未能解决可通过以下渠道反馈：
   * 邮箱地址：weixin-open@qq.com
   * 邮件主题：【微信JS-SDK反馈】具体问题
   * 邮件内容说明：用简明的语言描述问题所在，并交代清楚遇到该问题的场景，可附上截屏图片，微信团队会尽快处理你的反馈。
   */
  wx.config({
    debug: false,
    appId: '${appId}',
    timestamp: '${timestamp}',
    nonceStr: '${nonceStr}',
    signature: '${signature}',
    jsApiList: [
      // 所有要调用的 API 都要加到这个列表中
      'scanQRCode'
    ]
  });
  wx.ready(function () {
    // 在这里调用 API
    //调起微信扫一扫接口
    wx.scanQRCode({
      needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
      scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
      success: function (res) {
        var res = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
        //alert("已经成功扫描到设备信息，点击继续！");
        checkChannel(res);
      }
    });
  });
  wx.error(function(res){
    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。

  });

  function checkChannel(res){
    /*if(res.length!=14){
      alert("未知设备，请重试！");
      location.reload();
    }else{
      location.href="";
    }*/
    var gprsNos=res.split("gprsNo=");
    if (gprsNos.length>1){
      //location.href="<%=basePath%>custWeiXin/authorize?state=3&gprsNo="+gprsNos[1];
      if ("${way}"=="1"){
        location.href=res;
      }else if("${way}"=="3") {//充值扫一扫
        location.href="<%=basePath%>custWeiXin/toReCharge?gprsNo="+gprsNos[1]+"&bizNo="+"${bizNo}";
      }
    }else {
      alert("未知设备，请重试！");
    }


  }
</script>
<body>
<%--正在启动摄像头...--%>
</body>
</html>
