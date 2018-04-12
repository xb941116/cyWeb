package com.zzwdkj.weixinpay.ctrl;

/**
 * Created by xizhuangchui on 2016/11/17.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;

import com.zzwdkj.common.MD5Util;
import com.zzwdkj.common.weixin.WeiXinUtil;
import com.zzwdkj.common.weixin.XMLUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jdom.JDOMException;


/**
 * 申请退款
 *
 * 作者: 席庄捶
 * 日期：2016年11月20日 上午11:11:10
 */
public class RefundCtrl {

    /**
     * 申请退款，所需参数
     */
    private final static String appid = "";//公众号APPID
    private final static String mch_id = "";//公众号对应的商户号
    private final static String refund_fee_type = "CNY"; //货币类型，默认人民币
    private final static String op_user_id = ""; //操作员帐号, 默认为商户号
    private final static String transaction_id = ""; //微信生成的订单号，在支付通知中有返回
    private final static String out_refund_no = "";//自定义的单号，不重复，唯一，商户系统内部的退款单号，商户系统内部唯一
    private final static String total_fee = "";//订单总金额，单位为分
    private final static String refund_fee = "";//退款总金额
    private static String nonce_str = WeiXinUtil.getNonceStr();//随机字符串，不长于32位
    private static String key = "";//支付密钥，商户平台获取


    /**
     * 测试申请退款。
     * 申请退款需要证书文件，请到商户平台中去下载，放到项目的某目录下。我这里是放在了src目录下。
     * win7系统下，需要先导入证书。详情请看官网文档说明：https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_3     表4.2：证书文件说明、apiclient_cert.p12
     *
     * 作者: 席庄捶
     * 日期：2016年11月20日 下午1:26:57
     * @param args
     * @throws IOException
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     * @throws CertificateException
     * @throws KeyManagementException
     * @throws UnrecoverableKeyException
     * @throws JDOMException
     */
    @SuppressWarnings("unchecked")
    public  void refund() throws Exception {
        /**
         * 以下代码使用微信提供的代码
         */

        KeyStore keyStore  = KeyStore.getInstance("PKCS12");
        InputStream instream =  RefundCtrl.class.getResourceAsStream("/apiclient_cert.p12");//指定证书文件
        try {
            keyStore.load(instream, mch_id.toCharArray());//密码默认是商户号
        } finally {
            instream.close();
        }

        // 信任自己的CA和自签名证书，密码默认为商户号
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, mch_id.toCharArray()).build();
        // 只允许TLSv1协议
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        try {
            //指定申请退款接口
            HttpPost httppost = new HttpPost("https://api.mch.weixin.qq.com/secapi/pay/refund");
            System.out.println("执行请求操作：" + httppost.getRequestLine());

            //设置请求报文
            StringEntity myEntity = new StringEntity(getXmlInfo(), "UTF-8");
            httppost.setEntity(myEntity);

            //请求退款接口
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();

                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());

                StringBuffer result = new StringBuffer();
                if (entity != null) {
                    //System.out.println("请求退款接口，返回的内容的长度: " + entity.getContentLength());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                    String text;
                    while ((text = bufferedReader.readLine()) != null) {
                        System.out.println(text);
                        result.append(text);
                    }
                }
                EntityUtils.consume(entity);

                System.out.println("申请退款结果：\n" + result);

                Map<String, String> map = XMLUtil.doXMLParse(result.toString());
                if(map.get("return_code").equals("SUCCESS")){
                    System.out.println("申请退款成功！");
                    //这里可以进行其他的业务逻辑...

                }else{
                    System.out.println("申请退款失败！");
                    //这里可以进行其他的业务逻辑...

                }
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

    /**
     * 生成xml格式退款请求报文
     *
     * 作者: 席庄捶
     * 日期：2016年11月20日 下午1:10:42
     * @return
     */
    private static String getXmlInfo() {
        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        parameters.put("appid", appid); //appid
        parameters.put("mch_id", mch_id); //微信商户号
        parameters.put("nonce_str", nonce_str); //随机字符串

        /**
         * 这里有2种选择，二选一即可：
         * 1、transaction_id：微信订单号
         * 2、out_trade_no：商户订单号
         */
        parameters.put("transaction_id", transaction_id); //这里使用微信订单号，优先使用它
        parameters.put("out_refund_no", out_refund_no); //商户退款单号
        parameters.put("total_fee", total_fee); //总金额
        parameters.put("refund_fee", refund_fee); //退款金额
        parameters.put("refund_fee_type", refund_fee_type); //货比种类
        parameters.put("op_user_id", op_user_id); //操作员

        //创建签名，算法与支付的算法一样
        String sign = createSign("UTF-8", parameters);
        parameters.put("sign", sign);

        String data = getRequestXml(parameters);
        return data;
    }

    /**
     * 生成退款申请的请求报文
     *
     * 作者: 席庄捶
     * 日期：2016年11月20日 下午1:57:11
     * @param parameters
     * @return
     */
    public static String getRequestXml(SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set<Entry<Object, Object>> es = parameters.entrySet();
        Iterator<Entry<Object, Object>> it = es.iterator();
        while (it.hasNext()) {
            Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) it.next();
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
     * 生成签名
     *
     * 作者: 席庄捶
     * 日期：2016年11月20日 下午1:57:05
     * @param characterEncoding
     * @param parameters
     * @return
     */
    public static String createSign(String characterEncoding, SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        Set<Entry<Object, Object>> es = parameters.entrySet();
        Iterator<Entry<Object, Object>> it = es.iterator();
        while (it.hasNext()) {
            Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            /** 如果参数为key或者sign，则不参与加密签名 */
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        /** 支付密钥必须参与加密，放在字符串最后面 */
        sb.append("key=" + key);
        /** 记得最后一定要转换为大写 */
        String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
        return sign;
    }

}

