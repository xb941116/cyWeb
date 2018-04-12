package com.zzwdkj.weixinpay.ctrl;

import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.biz.entity.*;
import com.zzwdkj.biz.service.*;
import com.zzwdkj.common.*;
import com.zzwdkj.common.weixin.WeiXinUtil;
import com.zzwdkj.common.weixin.XMLUtil;
import com.zzwdkj.mbr.entity.Mbr;
import com.zzwdkj.mbr.entity.MbrRecharge;
import com.zzwdkj.mbr.service.MbrRechargeSV;
import com.zzwdkj.mbr.service.MbrSV;
import com.zzwdkj.msg.service.MsgSV;
import com.zzwdkj.ord.entity.Ord;
import com.zzwdkj.ord.service.OrdPayWxSV;
import com.zzwdkj.ord.service.OrdSV;
import com.zzwdkj.prod.entity.Prod;
import com.zzwdkj.prod.service.ProdSV;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.Map.Entry;

/**
 * 微信支付-调用微信浏览器自带的对象发起支付
 * <p/>
 * 作者: xizhuangchui
 * 日期：2016年09月22日14:35:01
 */
@Controller
@RequestMapping("/weixinJSBridge")
public class WeixinJSBridgeCtrl {

    private static Logger log = LoggerFactory.getLogger(WeixinJSBridgeCtrl.class);

    @Resource
    private BizWxSV bizWxSV;
    @Resource
    private BizSV bizSV;
    @Resource
    private OrdSV ordSV;
    @Resource
    private MsgSV msgSV;
    @Resource
    private MbrSV mbrSV;
    @Resource
    private ProdSV prodSV;
    @Resource
    private BizAdSV bizAdSV;
    @Resource
    private BizVipSV bizVipSV;
    @Resource
    private MbrRechargeSV mbrRechargeSV;

    /**
     * 充电支付的回调方法，微信服务器调用
     */
    private static final String NOTIFY_URL = "weixinJSBridge/pay";
    private static final String RGPAY_URL = "weixinJSBridge/rgPaySuccess";


    /**
     * 测试微信号的openId，这里固定写成我的微信openid，你们到时候自己编码获取
     */
   /* private static final String openId = "";*/


    /**
     * 获取预支付订单
     *
     * @param httpSession
     * @param request
     * @param payno         订单号
     * @param commodityName 商品名称
     * @param totleMoney    总金额（单位：分）
     * @param rgPayState    是否是充值支付
     * @return
     */
    public ModelAndView getWeixinParam(HttpSession httpSession, HttpServletRequest request, String payno, String commodityName, Integer totleMoney, Integer rgPayState) {

        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        log.info("basePath=" + basePath);
        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        /** 公众号APPID */
        parameters.put("appid", WeiXinUtil.getAPPID(httpSession));
        /** 商户号 */
        parameters.put("mch_id", WeiXinUtil.getMchId(httpSession));
        /** 随机字符串 */
        parameters.put("nonce_str", WeiXinUtil.getNonceStr());
        /** 商品名称 */
        parameters.put("body", commodityName != null ? commodityName : "微信支付");

        /** 当前时间 yyyyMMddHHmmss */
        //String currTime = TenpayUtil.getCurrTime();
        /** 8位日期 */
        //String strTime = currTime.substring(8, currTime.length());
        /** 四位随机数 */
        //String strRandom = TenpayUtil.buildRandom(4) + "";
        /** 订单号 */
        parameters.put("out_trade_no", payno);

        /** 订单金额以分为单位，只能为整数 */
        parameters.put("total_fee", totleMoney);
        /** 客户端本地ip */
        parameters.put("spbill_create_ip", WeiXinUtil.getLocalIp(request));
        /** 支付回调地址 */
        if (rgPayState == 1) {
            parameters.put("notify_url", basePath + RGPAY_URL);
        } else if (rgPayState == 0) {
            parameters.put("notify_url", basePath + NOTIFY_URL);
        }

        /** 支付方式为JSAPI支付 */
        parameters.put("trade_type", "JSAPI");
        /** 用户微信的openid，当trade_type为JSAPI的时候，该属性字段必须设置 */
        String openid = httpSession.getAttribute("user_openid").toString();
        parameters.put("openid", openid);

        /** MD5进行签名，必须为UTF-8编码，注意上面几个参数名称的大小写 */
        String sign = createSign("UTF-8", parameters, payno, rgPayState);
        parameters.put("sign", sign);

        /** 生成xml结构的数据，用于统一下单接口的请求 */
        String requestXML = getRequestXml(parameters);
        log.info("requestXML：" + requestXML);
        /** 开始请求统一下单接口，获取预支付prepay_id */
        HttpClient client = new HttpClient();
        PostMethod myPost = new PostMethod(WeiXinUtil.UNI_URL);
        client.getParams().setSoTimeout(300 * 1000);
        String result = null;
        try {
            myPost.setRequestEntity(new StringRequestEntity(requestXML, "text/xml", "utf-8"));
            int statusCode = client.executeMethod(myPost);
            if (statusCode == HttpStatus.SC_OK) {
                BufferedInputStream bis = new BufferedInputStream(myPost.getResponseBodyAsStream());
                byte[] bytes = new byte[1024];
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int count = 0;
                while ((count = bis.read(bytes)) != -1) {
                    bos.write(bytes, 0, count);
                }
                byte[] strByte = bos.toByteArray();
                result = new String(strByte, 0, strByte.length, "utf-8");
                bos.close();
                bis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /** 需要释放掉、关闭连接 */
        myPost.releaseConnection();
        client.getHttpConnectionManager().closeIdleConnections(0);

        log.info("请求统一支付接口的返回结果:");
        log.info(result);
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        try {
            /** 解析微信返回的信息，以Map形式存储便于取值 */
            Map<String, String> map = XMLUtil.doXMLParse(result);

            SortedMap<Object, Object> params = new TreeMap<Object, Object>();

            params.put("appId", WeiXinUtil.getAPPID(httpSession));
            paramsMap.put("appId", WeiXinUtil.getAPPID(httpSession));
            params.put("timeStamp", new Date().getTime() + "");
            paramsMap.put("timeStamp", new Date().getTime() + "");
            String nonceStr_S = WeiXinUtil.getNonceStr();
            params.put("nonceStr", nonceStr_S);
            paramsMap.put("nonceStr", nonceStr_S);
            /**
             * 获取预支付单号prepay_id后，需要将它参与签名。
             * 微信支付最新接口中，要求package的值的固定格式为prepay_id=...
             */
            params.put("package", "prepay_id=" + map.get("prepay_id"));
            paramsMap.put("package", "prepay_id=" + map.get("prepay_id"));

            /** 微信支付新版本签名算法使用MD5，不是SHA1 */
            params.put("signType", "MD5");
            paramsMap.put("signType", "MD5");
            /**
             * 获取预支付prepay_id之后，需要再次进行签名，参与签名的参数有：appId、timeStamp、nonceStr、package、signType.
             * 主意上面参数名称的大小写.
             * 该签名用于前端js中WeixinJSBridge.invoke中的paySign的参数值
             */
            String paySign = createSign("UTF-8", params, payno, rgPayState);
            params.put("paySign", paySign);
            paramsMap.put("paySign", paySign);

            /** 预支付单号，前端ajax回调获取。由于js中package为关键字，所以，这里使用packageValue作为key。 */
            params.put("packageValue", "prepay_id=" + map.get("prepay_id"));
            paramsMap.put("packageValue", "prepay_id=" + map.get("prepay_id"));

            /** 付款成功后，微信会同步请求我们自定义的成功通知页面，通知用户支付成功 */
            params.put("sendUrl", basePath + "weixinJSBridge/paysuccess?ordno=" + payno);
            paramsMap.put("sendUrl", basePath + "weixinJSBridge/paysuccess?ordno=" + payno);
            params.put("errorUrl", basePath + "weixinJSBridge/rgPayError?state=3");
            paramsMap.put("errorUrl", basePath + "weixinJSBridge/rgPayError?state=3");
            /** 获取用户的微信客户端版本号，用于前端支付之前进行版本判断，微信版本低于5.0无法使用微信支付 */
            String userAgent = request.getHeader("user-agent");
            char agent = userAgent.charAt(userAgent.indexOf("MicroMessenger") + 15);
            params.put("agent", new String(new char[]{agent}));
            paramsMap.put("agent", new String(new char[]{agent}));
            paramsMap.put("totleMoney", totleMoney);
            //String json = JSONArray.toJSONString(params);
            //System.out.println("-----------------" + json);
            return new ModelAndView("", paramsMap);


        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ModelAndView("", paramsMap);
    }

    /**
     * 调用统一下单接口获取预支付单号prepay_id，生成订单数据 以及 微信支付需要的签名等信息 传输到前端，进行调用JSAPI支付接口
     * 微信支付
     * 作者: xizhuangchui 日期：2016年09月29日16:49:51
     *
     * @param commodityName 商品名称
     * @param channelName
     * @param money         钱数
     * @param payno         订单号
     * @param channel
     * @param chargeType
     * @param httpSession
     * @param request
     * @return
     */

    @RequestMapping("/gopay")
    public ModelAndView Gopay(HttpSession httpSession, HttpServletRequest request, String commodityName, String channelName, String payno, String money, String devno, String channel, String chargeType) {

        Ord ord = ordSV.loadByOrdNo(payno);
        BigDecimal payMoney = ord.getProdPrice();
        BigDecimal Money100 = new BigDecimal(100);
        Integer totleMoney = payMoney.multiply(Money100).intValue();
        return getWeixinParam(httpSession, request, payno, commodityName, totleMoney, 0);
    }

    /**
     * 调用统一下单接口获取预支付单号prepay_id，生成订单数据 以及 微信支付需要的签名等信息 传输到前端，进行调用JSAPI支付接口
     * 微信充值支付
     * 作者: xizhuangchui 日期：2016年11月17日14:32:50
     *
     * @param commodityName 商品名称
     * @param money         钱数
     * @param httpSession
     * @param request
     * @return
     */
    @RequestMapping("/rgPay")
    public ModelAndView rgPay(HttpServletRequest request, String bizNo, String money, String commodityName, HttpSession httpSession) {
        if (httpSession.getAttribute("user_openid") == null || httpSession.getAttribute("user_openid").equals("")) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.clear();
            map.put("message", "页面已经失效，请重新进入！");
            return new ModelAndView("weixin/message", map);
        }
        Mbr mbr = mbrSV.loadByOpenidAndBizNo(httpSession.getAttribute("user_openid").toString(), bizNo);
        if (mbr != null && mbr.getId() != null) {
            BigDecimal payMoney = new BigDecimal(money);
            BigDecimal Money100 = new BigDecimal(100);
            Integer totleMoney = payMoney.multiply(Money100).intValue();
            String payno = null;
            payno = mbrSV.createRecharge(mbr, payMoney, Std.PayWay.WX);
            return getWeixinParam(httpSession, request, payno, commodityName, totleMoney, 1);
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.clear();
            map.put("message", "您还没有注册为会员，无法进行充值！");
            return new ModelAndView("mbr/register", map);
        }
    }


    /**
     * sign签名
     * <p/>
     * 作者: xizhuangchui 日期：2015年11月16日 上午9:31:24
     *
     * @param characterEncoding
     * @param parameters
     * @param payno             订单号
     * @param rgPayState        是否是充值支付
     * @return
     */
    public String createSign(String characterEncoding, SortedMap<Object, Object> parameters, String payno, Integer rgPayState) {
        StringBuffer sb = new StringBuffer();
        Set<Entry<Object, Object>> es = parameters.entrySet();
        Iterator<Entry<Object, Object>> it = es.iterator();
        while (it.hasNext()) {
            Entry<Object, Object> entry = (Entry<Object, Object>) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            /** 如果参数为key或者sign，则不参与加密签名 */
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        /** 支付密钥必须参与加密，放在字符串最后面 */
        if (rgPayState == 1) {
            MbrRecharge mbrRecharge = mbrRechargeSV.loadByPayno(payno);
            Mbr mbr = mbrSV.load(mbrRecharge.getMemberId());
            BizWx bizWx = bizWxSV.loadByBizNo(mbr.getBizNo());
            sb.append("key=" + bizWx.getApiSkey());
        } else {
            Ord ord = ordSV.loadByOrdNo(payno);
            BizWx bizWx = bizWxSV.loadByBizNo(ord.getBizNo());
            if (bizWx == null || 0 == bizWx.getState()) {
                Biz biz = bizSV.loadBizByBizNo(ord.getBizNo());
                bizWx = bizWxSV.loadByBizNo(biz.getParBizNo());
            }
            sb.append("key=" + bizWx.getApiSkey());
        }

        /** 记得最后一定要转换为大写 */
        String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
        return sign;
    }

    /**
     * 将请求参数转换为xml格式的string
     * <p/>
     * 作者: xizhuangchui 日期：2016年09月29日16:49:41
     *
     * @param parameters
     * @return
     */
    public static String getRequestXml(SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set<Entry<Object, Object>> es = parameters.entrySet();
        Iterator<Entry<Object, Object>> it = es.iterator();
        while (it.hasNext()) {
            Entry<Object, Object> entry = (Entry<Object, Object>) it.next();
            String k = (String) entry.getKey();
            String v = entry.getValue() + "";
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
            } else {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 获取支付回调信息
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws JDOMException
     */
    public Map<Object, Object> getResultMap(HttpServletRequest request,
                                            HttpServletResponse response) throws IOException, JDOMException {
        log.info("微信支付成功调用回调URL");
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        log.info("~~~~~~~~~~~~~~~~付款成功~~~~~~~~~");

        outSteam.close();
        inStream.close();

        /** 支付成功后，微信回调返回的信息 */
        String result = new String(outSteam.toByteArray(), "utf-8");
        log.info("微信返回的订单支付信息:" + result);
        Map<Object, Object> map = XMLUtil.doXMLParse(result);
        return map;
    }

    /**
     * 转换成TreeMap用于验签
     *
     * @param map Map<Object, Object>
     * @return
     */
    public SortedMap<Object, Object> getResultSortedMap(Map<Object, Object> map) {
        // 用于验签
        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        for (Object keyValue : map.keySet()) {
            /** 输出返回的订单支付信息 */
            log.info(keyValue + "=" + map.get(keyValue));
            //System.out.println(keyValue + "=" + map.get(keyValue));
            if (!"sign".equals(keyValue)) {
                parameters.put(keyValue, map.get(keyValue));
            }
        }
        return parameters;
    }

    /**
     * 付款成功回调处理
     * <p/>
     * 作者: xizhuangchui 日期：2016年09月29日16:49:33
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws JDOMException
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "pay")
    public void notify_success(HttpServletRequest request,
                               HttpServletResponse response) throws IOException, JDOMException {
        Map<Object, Object> map = new HashMap<Object, Object>();
        try {
            map = getResultMap(request, response);//获取回调函数
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        }
        SortedMap<Object, Object> parameters = getResultSortedMap(map);

        //订单号
        String out_trade_no = parameters.get("out_trade_no").toString();
        //System.out.println("订单号：--------------------------"+out_trade_no);


        if (map.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
            // 先进行校验，是否是微信服务器返回的信息
            String checkSign = createSign("UTF-8", parameters, out_trade_no, 0);
            log.info("对服务器返回的结果进行签名：" + checkSign);
            log.info("服务器返回的结果签名：" + map.get("sign"));
            if (checkSign.equals(map.get("sign"))) {// 如果签名和服务器返回的签名一致，说明数据没有被篡改过
                log.info("签名校验成功，信息合法，未被篡改过");

                /** 告诉微信服务器，我收到信息了，不要再调用回调方法了 */
                /**如果不返回SUCCESS的信息给微信服务器，则微信服务器会在一定时间内，多次调用该回调方法，如果最终还未收到回馈，微信默认该订单支付失败*/
                /** 微信默认会调用8次该回调地址 */
                response.getWriter().write(setXML("SUCCESS", ""));
                log.info("-------------" + setXML("SUCCESS", ""));

                /*
                支付成功响应代码
                */
                System.out.println("---------------支付成功回调-------------");
                Ord ord = ordSV.loadByOrdNo(out_trade_no);
                Prod prod=prodSV.loadByGprsNo(ord.getGprsNo());
                log.debug("回调成功");
                if (ord != null && ord.getState() == 0) {
                    ordSV.updateOrdPaySt(out_trade_no, Std.PayWay.WX);
                    log.debug("发送指令");
                    if (prod.getProdCv()==null){
                        prod.setProdCv(1);
                    }
                    //如果是OTP支付模式则不发送指令
                    if (!ord.getGprsNo().substring(0,1).equals("2")){
                        if (ord.getGprsNo().startsWith("0")){
                            msgSV.payNotice(ord.getOrdno(),ord.getGprsNo(), BaseConfig.getPayMoney(ord.getGprsNo(),out_trade_no, ord.getPulse().intValue()));
                        }else {
                            msgSV.payNotice(ord.getOrdno(),ord.getGprsNo(), BaseConfig.getPayMoney(ord.getGprsNo(),out_trade_no, ord.getProdPrice().intValue()));
                        }
                    }
                }

                //log.info("我去掉了发送SUCCESS给微信服务器");

            }
        }
    }


    /**
     * 充值支付成功回调处理
     * <p/>
     * 作者: xizhuangchui 日期：2015年11月16日 上午9:25:29
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws JDOMException
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "rgPaySuccess")
    public void rgPay_success(HttpServletRequest request,
                              HttpServletResponse response) throws IOException, JDOMException {
        Map<Object, Object> map = new HashMap<Object, Object>();
        try {
            map = getResultMap(request, response);//获取回调函数
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        }
        SortedMap<Object, Object> parameters = getResultSortedMap(map);
        //订单号
        String out_trade_no = parameters.get("out_trade_no").toString();
        System.out.println("充值单号：--------------------------" + out_trade_no);
        if (map.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
            // 先进行校验，是否是微信服务器返回的信息
            String checkSign = createSign("UTF-8", parameters, out_trade_no, 1);
            log.info("对服务器返回的结果进行签名：" + checkSign);
            log.info("服务器返回的结果签名：" + map.get("sign"));
            if (checkSign.equals(map.get("sign"))) {// 如果签名和服务器返回的签名一致，说明数据没有被篡改过
                log.info("签名校验成功，信息合法，未被篡改过");

                /** 告诉微信服务器，我收到信息了，不要再调用回调方法了 */
                /**如果不返回SUCCESS的信息给微信服务器，则微信服务器会在一定时间内，多次调用该回调方法，如果最终还未收到回馈，微信默认该订单支付失败*/
                /** 微信默认会调用8次该回调地址 */
                response.getWriter().write(setXML("SUCCESS", ""));
                log.info("-------------" + setXML("SUCCESS", ""));

                 /*
                支付成功响应代码
                */
                mbrSV.upReCg(out_trade_no, Std.YN.YES);
                System.out.println("---------------充值成功回调-------------");


                //log.info("我去掉了发送SUCCESS给微信服务器");

            }
        }
    }


    @RequestMapping("rgPayError")
    public ModelAndView rgPay_error(String pno) {
        mbrSV.upReCg(pno, Std.YN.NO);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", "支付失败！");
        return new ModelAndView("weixin/message", map);
    }

    /**
     * 发送xml格式数据到微信服务器 告知微信服务器回调信息已经收到。
     * <p/>
     * 作者: xizhuangchui 日期：2015年11月16日 上午9:27:33
     *
     * @param return_code
     * @param return_msg
     * @return
     */
    public static String setXML(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code
                + "]]></return_code><return_msg><![CDATA[" + return_msg
                + "]]></return_msg></xml>";
    }

    /**
     * 支付成功请求的地址URL，告知用户已经支付成功
     * <p/>
     * 作者: xizhuangchui 日期：2016年09月29日16:49:19
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("paysuccess")
    public ModelAndView paysuccess(HttpServletRequest request,
                                   HttpServletResponse response,String ordno) {
        Ord ord=ordSV.loadByOrdNo(ordno);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ord",ord);
        List<BizAd> bizAdList =bizAdSV.queryBizAdByBizNoAndType(ord.getBizNo(),Std.ADType.PAYSUCESS.getKey(),true);
        if (bizAdList==null||bizAdList.size()==0){
            bizAdList =bizAdSV.queryBizAdByBizNoAndType("B100000",Std.ADType.PAYSUCESS.getKey(),false);
        }
        map.put("bizAdList",bizAdList);
        BizVip bizVip=bizVipSV.loadByBizNoAndType(ord.getBizNo(), Std.VipType.AD.getKey(),true);
        map.put("bizVip",bizVip);
        ModelAndView mav = new ModelAndView("weixin/paysuccess",map);
        return mav;
    }


}
