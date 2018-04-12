package com.zzwdkj.common;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zzwdkj.common.weixin.WeiXinUtil;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * 第三方推送或者回调 工具类
 * Created by Administrator on 2017/7/1.
 */
public class PushUtil {


    public static int KEY_SECONDS=7200;
    public static int BIZ_PV_TIMES=7200;
    public static int BIZ_PV_DATE=7200;
    /**
     * 生成签名
     *
     * 作者: 席庄捶
     * 日期：2017年7月1日14:58:41
     * @param characterEncoding
     * @param parameters
     * @return
     */
    public static String createSign(String characterEncoding, SortedMap<Object, Object> parameters,String code) {
        StringBuffer sb = new StringBuffer();
        Set<Map.Entry<Object, Object>> es = parameters.entrySet();
        Iterator<Map.Entry<Object, Object>> it = es.iterator();
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
        sb.append("key=" + code);
        /** 记得最后一定要转换为大写 */
        String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
        return sign;
    }

    /**
     * 推送
     * @param url
     * @param jsonParam
     */
    public static void getPush(String url,String jsonParam){
        JsonParser jsonparer = new JsonParser();// 初始化解析json格式的对象
        try {
            //通过code换取网页授权access_token
            String responseContent= WeiXinUtil.httpPost(url,jsonParam);
            JsonObject json = jsonparer.parse(responseContent)
                    .getAsJsonObject();
            // 将json字符串转换为json对象

            if (json.get("result_code") != null&&!json.get("result_code").equals("SUCCESS")) {
                // 返回错误码等信息
            } else{
                // 正常情况
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取授权code
     * @param bizNo
     * @return
     */
    public static String createCode(String bizNo){
        String key=WeiXinUtil.getNonceStr();
        //RedisUtil.del(bizNo+"_service_code");
        RedisUtil.setex(bizNo+"_service_code",KEY_SECONDS,key);
        return key;
    }

    /**
     * 获取key
     * @param bizNo
     * @return
     */
    public static String checkCode(String bizNo){
        String check_code=RedisUtil.get(bizNo+"_service_code");
        if (check_code!=null){
            return check_code; //正确
        }else {
            //return "{\"errcode\": 2, \"errmsg\": \"KEY IS NO VALID\"}";
            return null;//失效
        }
    }

    /**
     * 获取商家是否在允许访问次数之内
     * @param bizNo
     * @return
     */
    public  static  boolean checkPV(String bizNo){
        String pv_times=RedisUtil.get(bizNo+"_pv_tims");
        if (pv_times!=null){
            if (pv_times.compareTo(BIZ_PV_TIMES+"")<0){
                return true;
            }else {
                return false;
            }
        }else {
            RedisUtil.setex(bizNo+"_pv_tims",BIZ_PV_DATE,"1");
            return true;
        }
    }


}
