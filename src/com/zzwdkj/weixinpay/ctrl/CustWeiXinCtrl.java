package com.zzwdkj.weixinpay.ctrl;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.JsonObject;
import com.zzwdkj.biz.entity.Biz;
import com.zzwdkj.biz.entity.BizGift;
import com.zzwdkj.biz.entity.BizWx;
import com.zzwdkj.biz.entity.BizWxFocus;
import com.zzwdkj.biz.service.BizGiftSV;
import com.zzwdkj.biz.service.BizSV;
import com.zzwdkj.biz.service.BizWxSV;
import com.zzwdkj.common.*;
import com.zzwdkj.common.weixin.Location;
import com.zzwdkj.common.weixin.WeiXinUtil;
import com.zzwdkj.gprs.msg.service.MsgRecvSV;
import com.zzwdkj.mbr.entity.*;
import com.zzwdkj.mbr.service.*;
import com.zzwdkj.ord.entity.Ord;
import com.zzwdkj.ord.entity.OrdPayAli;
import com.zzwdkj.ord.entity.OrdPayWlt;
import com.zzwdkj.ord.entity.OrdPayWx;
import com.zzwdkj.ord.service.OrdPayWltSV;
import com.zzwdkj.ord.service.OrdPayWxSV;
import com.zzwdkj.ord.service.OrdSV;
import com.zzwdkj.prod.entity.Prod;
import com.zzwdkj.prod.entity.ProdInstlPos;
import com.zzwdkj.prod.service.ProdInstlPosSV;
import com.zzwdkj.prod.service.ProdSV;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

/**
 * 客户管理模块Ctrl
 *
 * @author guoxianwei 2015-06-22 15:29
 */
@Controller
@RequestMapping("/custWeiXin")
public class CustWeiXinCtrl {

    private static String TOKEN = "jiexika_com";

    @Resource
    private BizWxSV bizWxSV;
    @Resource
    private BizSV bizSV;
    @Resource
    private ProdSV prodSV;
    @Resource
    private ProdInstlPosSV prodInstlPosSV;
    @Resource
    private MbrOauthSV mbrOauthSV;
    @Resource
    private MbrSV mbrSV;
    @Resource
    private MbrWalletSV mbrWalletSV;
    @Resource
    private BizGiftSV bizGiftSV;
    @Resource
    private MbrCoinSV mbrCoinSV;
    @Resource
    private MbrRechargeSV mbrRechargeSV;
    @Resource
    private OrdSV ordSV;
    @Resource
    private OrdPayWxSV ordPayWxSV;
    @Resource
    private OrdPayWltSV ordPayWltSV;
    @Resource
    private MsgRecvSV msgRecvSV;

    /**
     * 微信验证
     *
     * @param request
     * @param response
     */
    @RequestMapping("/WechatCallbackApi")
    public void WechatCallbackApi(HttpServletRequest request, HttpServletResponse response) {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");

        String[] str = {TOKEN, timestamp, nonce};
        Arrays.sort(str); // 字典序排序
        String bigStr = str[0] + str[1] + str[2];
        // SHA1加密
        // String digest = new SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase();

        String digest = DigestUtils.shaHex(bigStr);

        // 确认请求来至微信
        if (digest.equals(signature)) {
            try {
                response.getWriter().print(echostr);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping("/getUnionID")
    public ModelAndView getUnionID() {
        Map<String, Object> map = new HashMap<String, Object>();
        return new ModelAndView("", map);
    }


    /**
     * 用户授权
     *
     * @param gprsNo
     * @return
     */
    @RequestMapping("/authorizeFocus")
    public ModelAndView authorizeFocus(String gprsNo, HttpSession httpSession) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (gprsNo == null || gprsNo.equals("")) {
            map.put("message", "暂不识别您扫描的二维码！");
            return new ModelAndView("weixin/message", map);
        }
        Prod prod = prodSV.loadByGprsNo(gprsNo);
        if (prod == null || prod.getId() == null) {
            map.put("message", "该产品不存在，请联系商家！");
            return new ModelAndView("weixin/message", map);
        }
        BizWxFocus bizWx = bizWxSV.loadBizWxFocusByBizNo(prod.getBizNo());
        if (bizWx == null || bizWx.getAppId() == null) {
            map.put("message", "商家未绑定微信支付信息，请联系商家！");
            return new ModelAndView("weixin/message", map);
        }
        httpSession.setAttribute("APPID", bizWx.getAppId());
        httpSession.setAttribute("SECRET", bizWx.getAppSkey());
        return new ModelAndView("redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + bizWx.getAppId() + "&redirect_uri=http%3A%2F%2F" + BaseConfig.getSysProperties().getProperty("wx.squrl").toString() + "%2FcustWeiXin%2FoptionsFocus&response_type=code&scope=snsapi_userinfo&state=" + gprsNo + "#wechat_redirect");
    }


    /**
     * 授权，并跳转到响应的栏目菜单
     *
     * @param code  授权后返回的code
     * @param state
     * @return
     */
    @RequestMapping("/optionsFocus")
    public ModelAndView optionsFocus(String code, String state, HttpSession httpSession) {
        String gprsNo = state;
        Map<String, Object> map = new HashMap<String, Object>();
        if (gprsNo == null || gprsNo.equals("")) {
            map.put("message", "暂不识别您扫描的二维码！");
            return new ModelAndView("weixin/message", map);
        }
        Prod prod = prodSV.loadByGprsNo(gprsNo);
        if (prod == null || prod.getId() == null) {
            map.put("message", "该产品不存在，请联系商家！");
            return new ModelAndView("weixin/message", map);
        }
        BizWx bizWx = bizWxSV.loadByBizNo(prod.getBizNo());
        if (bizWx == null || 0 == bizWx.getState()) {
            Biz biz = bizSV.loadBizByBizNo(prod.getBizNo());
            bizWx = bizWxSV.loadByBizNo(biz.getParBizNo());
        }
        if (bizWx == null || bizWx.getAppId() == null) {
            map.put("message", "商家未绑定微信支付信息，请联系商家！");
            return new ModelAndView("weixin/message", map);
        }
        httpSession.setAttribute("APPID", bizWx.getAppId());
        httpSession.setAttribute("SECRET", bizWx.getAppSkey());

        ModelMap mmap = new ModelMap();
        WeiXinUtil.getToken(httpSession);//获取access_token
        WeiXinUtil.getRefreshToken(code, httpSession);//通过code换取网页授权access_token.获取openID refresh_token
        WeiXinUtil.refreshToken(httpSession); //刷新access_token
        mmap.addAttribute("gprsNo", gprsNo);
        mmap.addAttribute("focus", bizWx.getFocus());
        Integer focus = bizWx.getFocus();
        map = WeiXinUtil.getUserInfo(httpSession);//获取微信用户的信息
        System.out.println("----------------------用户关注状态为" + map.get("subscribe").toString());
        if (map.get("subscribe").toString().equals("0") && focus != null && focus == 1) {
            map.put("ticket", WeiXinUtil.getQrcodeTicket(httpSession).get("QrcodeTicket"));
            return new ModelAndView("weixin/subscribe", map);
        }
        return new ModelAndView("redirect:/custWeiXin/authorize", mmap);
    }

    /**
     * 用户授权
     *
     * @param gprsNo
     * @return
     */
    @RequestMapping("/authorize")
    public ModelAndView authorize(String gprsNo, HttpSession httpSession) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (gprsNo == null || gprsNo.equals("")) {
            map.put("message", "暂不识别您扫描的二维码！");
            return new ModelAndView("weixin/message", map);
        }
        Prod prod = prodSV.loadByGprsNo(gprsNo);
        if (prod == null || prod.getId() == null) {
            map.put("message", "该产品不存在，请联系商家！");
            return new ModelAndView("weixin/message", map);
        }
        BizWx bizWx = bizWxSV.loadByBizNo(prod.getBizNo());
        if (bizWx == null || 0 == bizWx.getState()) {
            Biz biz = bizSV.loadBizByBizNo(prod.getBizNo());
            bizWx = bizWxSV.loadByBizNo(biz.getParBizNo());
        }
        if (bizWx == null || bizWx.getAppId() == null) {
            map.put("message", "商家未绑定微信支付信息，请联系商家！");
            return new ModelAndView("weixin/message", map);
        }
        httpSession.setAttribute("APPID", bizWx.getAppId());
        httpSession.setAttribute("SECRET", bizWx.getAppSkey());
        httpSession.setAttribute("MCH_ID", bizWx.getBizNum());
        httpSession.setAttribute("API_KEY", bizWx.getApiSkey());
        return new ModelAndView("redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + bizWx.getAppId() + "&redirect_uri=http%3A%2F%2F" + BaseConfig.getSysProperties().getProperty("wx.squrl").toString() + "%2FcustWeiXin%2Foptions&response_type=code&scope=snsapi_userinfo&state=" + gprsNo + "#wechat_redirect");
    }


    /**
     * 授权，并跳转到响应的栏目菜单
     *
     * @param code  授权后返回的code
     * @param state
     * @return
     */
    @RequestMapping("/options")
    public ModelAndView options(String code, String state, HttpSession httpSession) {
        String gprsNo = state;
        Map<String, Object> map = new HashMap<String, Object>();
        if (gprsNo == null || gprsNo.equals("")) {
            map.put("message", "暂不识别您扫描的二维码！");
            return new ModelAndView("weixin/message", map);
        }
        Prod prod = prodSV.loadByGprsNo(gprsNo);
        if (prod == null || prod.getId() == null) {
            map.put("message", "该产品不存在，请联系商家！");
            return new ModelAndView("weixin/message", map);
        }
        BizWx bizWx = bizWxSV.loadByBizNo(prod.getBizNo());
        if (bizWx == null || 0 == bizWx.getState()) {
            Biz biz = bizSV.loadBizByBizNo(prod.getBizNo());
            bizWx = bizWxSV.loadByBizNo(biz.getParBizNo());
        }
        if (bizWx == null || bizWx.getAppId() == null) {
            map.put("message", "商家未绑定微信支付信息，请联系商家！");
            return new ModelAndView("weixin/message", map);
        }
        httpSession.setAttribute("APPID", bizWx.getAppId());
        httpSession.setAttribute("SECRET", bizWx.getAppSkey());
        httpSession.setAttribute("MCH_ID", bizWx.getBizNum());
        httpSession.setAttribute("API_KEY", bizWx.getApiSkey());

        ModelMap mmap = new ModelMap();
        WeiXinUtil.getToken(httpSession);//获取access_token
        WeiXinUtil.getRefreshToken(code, httpSession);//通过code换取网页授权access_token.获取openID refresh_token
        WeiXinUtil.refreshToken(httpSession); //刷新access_token
        mmap.addAttribute("gprsNo", gprsNo);
        mmap.addAttribute("focus", bizWx.getFocus());
        return new ModelAndView("redirect:/custWeiXin/optionsWay", mmap);
    }


    /**
     * 跳转
     *
     * @param gprsNo
     * @return
     */
    @RequestMapping("/optionsWay")
    public ModelAndView optionsWay(String gprsNo, Integer focus, HttpSession httpSession) {
        Map<String, Object> map = new HashMap<String, Object>();
        String openid = "";
        //session失效
        if (httpSession.getAttribute("user_openid") == null || httpSession.getAttribute("user_openid").equals("")) {
            map.clear();
            map.put("message", "页面已经失效，请重新进入！");
            return new ModelAndView("weixin/message", map);
        } else {
            openid = httpSession.getAttribute("user_openid").toString();//获取unionid
        }

        map = WeiXinUtil.getUserInfo(httpSession);//获取微信用户的信息
        /*System.out.println("----------------------用户关注状态为"+map.get("subscribe").toString());
        if (map.get("subscribe").toString().equals("0")&&focus!=null&&focus==1){
            map.put("ticket", WeiXinUtil.getQrcodeTicket(httpSession).get("QrcodeTicket"));
            return new ModelAndView("weixin/subscribe",map);
        }*/
        ModelMap mmap = new ModelMap();
        if (gprsNo.length() == 8) { //“微支付”
            mmap.addAttribute("gprsNo", gprsNo);
            mmap.addAttribute("nickname", map.get("nickname"));
            mmap.addAttribute("authorizeState", 1);
            return new ModelAndView("redirect:/ord/ord", mmap);
        } else {
            map.put("message", "暂不识别该二维码！");
            return new ModelAndView("weixin/message", map);
        }
    }


    /**
     * 用户授权
     *
     * @param state
     * @return
     */
    @RequestMapping("/authorizeUrl")
    public ModelAndView authorizeUrl(String state, HttpSession httpSession) {
        String bizNo = state.split("_")[0];
        Map<String, Object> map = new HashMap<String, Object>();
        if (bizNo == null || bizNo.equals("")) {
            map.put("message", "链接参数错误！");
            return new ModelAndView("weixin/message", map);
        }
        //通过商家编号获取微信绑定信息
        BizWx bizWx = bizWxSV.loadByBizNo(bizNo);
        if (bizWx == null || 0 == bizWx.getState() || bizWx.getAppId() == null) {
            map.put("message", "商家未绑定微信支付信息，请联系商家！");
            return new ModelAndView("weixin/message", map);
        }
        httpSession.setAttribute("APPID", bizWx.getAppId());
        httpSession.setAttribute("SECRET", bizWx.getAppSkey());
        httpSession.setAttribute("MCH_ID", bizWx.getBizNum());
        httpSession.setAttribute("API_KEY", bizWx.getApiSkey());
        return new ModelAndView("redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + bizWx.getAppId() + "&redirect_uri=http%3A%2F%2F" + BaseConfig.getSysProperties().getProperty("wx.squrl").toString() + "%2FcustWeiXin%2FoptionsUrl&response_type=code&scope=snsapi_userinfo&state=" + state + "#wechat_redirect");
    }


    /**
     * 授权，并跳转到响应的栏目菜单
     *
     * @param code  授权后返回的code
     * @param state
     * @return
     */
    @RequestMapping("/optionsUrl")
    public ModelAndView optionsUrl(String code, String state, HttpSession httpSession) {
        System.out.println("------------------跳转参数：" + state);
        String bizNo = state.split("_")[0];
        state = state.split("_")[1];
        System.out.println(bizNo + "------------------" + state);
        Map<String, Object> map = new HashMap<String, Object>();
        if (bizNo == null || bizNo.equals("")) {
            map.put("message", "链接参数错误！");
            return new ModelAndView("weixin/message", map);
        }
        //通过商家编号获取微信绑定信息
        BizWx bizWx = bizWxSV.loadByBizNo(bizNo);
        if (bizWx == null || 0 == bizWx.getState() || bizWx.getAppId() == null) {
            map.put("message", "商家未绑定微信支付信息，请联系商家！");
            return new ModelAndView("weixin/message", map);
        }
        httpSession.setAttribute("APPID", bizWx.getAppId());
        httpSession.setAttribute("SECRET", bizWx.getAppSkey());
        httpSession.setAttribute("MCH_ID", bizWx.getBizNum());
        httpSession.setAttribute("API_KEY", bizWx.getApiSkey());

        ModelMap mmap = new ModelMap();
        WeiXinUtil.getToken(httpSession);//获取access_token
        WeiXinUtil.getRefreshToken(code, httpSession);//通过code换取网页授权access_token.获取openID refresh_token
        WeiXinUtil.refreshToken(httpSession); //刷新access_token
        mmap.addAttribute("bizNo", bizNo);
        mmap.addAllAttributes(map);
        Biz biz_wx = bizSV.loadBizByBizNo(bizNo);
        httpSession.setAttribute("wxBizNoLogo", biz_wx.getLogo());
        httpSession.setAttribute("wxBizNoName", biz_wx.getName());
        if (state.equals("1")) {//管理中心
            return new ModelAndView("redirect:/", mmap);
        } else if (state.equals("2")) {//会员注册
            return new ModelAndView("redirect:/custWeiXin/toRegister", mmap);
        } else if (state.equals("3")) {//会员充值
            mmap.addAttribute("way", 3);
            if (mbrRechargeSV.countMbrReCgHisByOpenidAndBizNo(httpSession.getAttribute("user_openid").toString(), bizNo) > 0) {
                mmap.addAttribute("rgState", true);
                return new ModelAndView("redirect:/custWeiXin/toReCharge", mmap);
            } else {
                mmap.addAttribute("rgState", false);
                return new ModelAndView("redirect:/custWeiXin/scanQRCode", mmap);
            }


        } else if (state.equals("31")) {//会员充值
            mmap.addAttribute("way", 3);
            mmap.addAttribute("rgState", true);
            return new ModelAndView("redirect:/custWeiXin/toReCharge", mmap);
        } else if (state.equals("4")) {//消费记录
            return new ModelAndView("redirect:/custWeiXin/mbrOrdList", mmap);

        } else if (state.equals("5")) {//个人中心
            return new ModelAndView("redirect:/custWeiXin/userInfo", mmap);

        } else if (state.equals("6")) {//充值记录
            return new ModelAndView("redirect:/custWeiXin/reChargeHis", mmap);

        } else if (state.equals("7")) {//附近（获取经纬度，并用百度地图展示设备位置）
            mmap.addAttribute("wayFlag", "1");
            return new ModelAndView("redirect:/custWeiXin/getLocation", mmap);

        } else if (state.equals("8")) {//查询用户使用中的设备状态
            return new ModelAndView("redirect:/custWeiXin/loadPayMsg", mmap);

        } else if (state.equals("9")) {//添加位置模板（获取经纬度，并用百度地图展示）
            mmap.addAttribute("wayFlag", "2");
            return new ModelAndView("redirect:/custWeiXin/getLocation", mmap);

        } else if (state.equals("10")) {//优惠活动
            return new ModelAndView("redirect:/custWeiXin/queryBizGift", mmap);

        } else {
            map.put("message", "商家未绑定微信支付信息，请联系商家！");
            return new ModelAndView("weixin/message", map);
        }
    }


    /**
     * 个人中心
     *
     * @return
     */

    @RequestMapping("/userInfo")
    public ModelAndView userInfo(HttpSession httpSession, String bizNo) {
        Map<String, Object> map = new HashMap<String, Object>();
        String openid = "";
        //session失效
        if (httpSession.getAttribute("user_openid") == null || httpSession.getAttribute("user_openid").equals("")) {
            map.clear();
            map.put("message", "页面已经失效，请重新进入！");
            return new ModelAndView("weixin/message", map);
        } else {
            openid = httpSession.getAttribute("user_openid").toString();//获取unionid
        }
        Mbr mbr = mbrSV.loadByOpenidAndBizNo(openid, bizNo);
        if (mbr != null && mbr.getId() != null) {
            map = WeiXinUtil.getUserInfo(httpSession);//获取微信用户的信息
            map.put("ismbr", 1);
            map.put("mbr", mbr);
            MbrWallet mbrWallet = mbrWalletSV.loadByMemberId(mbr.getId());
            MbrCoin mbrCoin = mbrCoinSV.loadByMemberId(mbr.getId());
            map.put("mbrWallet", mbrWallet);
            map.put("mbrCoin", mbrCoin);
            //List<BizGift> bizGiftList=bizGiftSV.queryBizGiftListByBizNo(bizNo,1);
            //map.put("bizGiftList",bizGiftList);
            map.put("bizNo", bizNo);
            //Biz biz=bizSV.loadBizByBizNo(bizNo);
            //map.put("biz",biz);
            if (mbrRechargeSV.countMbrReCgHisByOpenidAndBizNo(httpSession.getAttribute("user_openid").toString(), bizNo) > 0) {
                map.put("rgState", true);//充值过
            } else {
                map.put("rgState", false);
            }

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("memberId", mbr.getId());
            params.put("bizNo", bizNo);
            List<Ord> mbrOrdList = ordSV.queryOrdJoinOrdPayWx(params, 0, 1);
            if (mbrOrdList != null && mbrOrdList.size() > 0) {
                map.put("gprsNo", mbrOrdList.get(0).getGprsNo());
            }

        } else {
            map = WeiXinUtil.getUserInfo(httpSession);//获取微信用户的信息
            map.put("ismbr", 0);
            map.put("bizNo", bizNo);

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("openid", openid);
            params.put("bizNo", bizNo);
            List<Ord> openidList = ordSV.queryOrdJoinOrdPayWx(params, 0, 1);
            if (openidList != null && openidList.size() > 0) {
                map.put("gprsNo", openidList.get(0).getGprsNo());
            }
        }

        return new ModelAndView("/weixin/userInfo", map);
    }

    /**
     * 会员注册
     *
     * @param bizNo 商家编号
     * @return
     */
    @RequestMapping("/toRegister")
    public ModelAndView toRegister(HttpSession httpSession, String bizNo, String gprsNo) {
        Map<String, Object> map = new HashMap<String, Object>();
        String openid = "";
        //session失效
        if (httpSession.getAttribute("user_openid") == null || httpSession.getAttribute("user_openid").equals("")) {
            map.clear();
            map.put("message", "页面已经失效，请重新进入！");
            return new ModelAndView("weixin/message", map);
        } else {
            openid = httpSession.getAttribute("user_openid").toString();//获取unionid
        }
        Mbr mbr = mbrSV.loadByOpenidAndBizNo(openid, bizNo);
        if (mbr != null && mbr.getId() != null) {
            map.put("message", "请勿重复进行注册，您已经注册过会员了！");
            return new ModelAndView("redirect:/custWeiXin/authorizeUrl?state=" + bizNo + "_5", map);
        }
        map = WeiXinUtil.getUserInfo(httpSession);
        map.put("bizNo", bizNo);
        Biz biz = bizSV.loadBizByBizNo(bizNo);
        map.put("biz", biz);
        map.put("gprsNo", gprsNo);
        return new ModelAndView("/weixin/mbrRegister", map);
    }

    /**
     * 会员充值
     *
     * @param bizNo   商家编号
     * @param rgState 是否充值过
     * @return
     */
    @RequestMapping("/toReCharge")
    public ModelAndView toReCharge(HttpSession httpSession, String bizNo, String gprsNo, boolean rgState) {
        Map<String, Object> map = new HashMap<String, Object>();
        String openid = "";
        //session失效
        if (httpSession.getAttribute("user_openid") == null || httpSession.getAttribute("user_openid").equals("")) {
            map.clear();
            map.put("message", "页面已经失效，请重新进入！");
            return new ModelAndView("weixin/message", map);
        } else {
            openid = httpSession.getAttribute("user_openid").toString();//获取unionid
        }

        Mbr mbr = mbrSV.loadByOpenidAndBizNo(openid, bizNo);

        if (rgState != true) {
            //如果gprsNo为空
            if (gprsNo == null || gprsNo == "") {
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("openid", openid);
                params.put("bizNo", bizNo);
                List<Ord> openidList = ordSV.queryOrdJoinOrdPayWx(params, 0, 1);
                if (openidList != null && openidList.size() > 0) {
                    gprsNo = openidList.get(0).getGprsNo();
                } else {
                    ModelMap mmap = new ModelMap();
                    mmap.addAttribute("bizNo", bizNo);
                    return new ModelAndView("redirect:/custWeiXin/userInfo", mmap);
                }
            }
            Prod prod = prodSV.loadByGprsNo(gprsNo);
            BizWx bizWx = bizWxSV.loadByBizNo(prod.getBizNo());
            if (!bizNo.equals(prod.getBizNo()) || bizWx == null || bizWx.getId() == null) {
                map.put("message", "该商家的设备不支持会员充值！");
                return new ModelAndView("weixin/message", map);
            }
        }

        map = WeiXinUtil.getUserInfo(httpSession);

        if (mbr != null && mbr.getId() != null) {
            map.put("mbr", mbr);
            MbrWallet mbrWallet = mbrWalletSV.loadByMemberId(mbr.getId());
            MbrCoin mbrCoin = mbrCoinSV.loadByMemberId(mbr.getId());
            map.put("mbrWallet", mbrWallet);
            map.put("mbrCoin", mbrCoin);
            List<BizGift> bizGiftList = bizGiftSV.queryBizGiftListByBizNo(bizNo, 1);
            map.put("bizGiftList", bizGiftList);
            map.put("bizNo", bizNo);
            Biz biz = bizSV.loadBizByBizNo(bizNo);
            map.put("biz", biz);
            return new ModelAndView("weixin/mbrReCharge", map);
        } else {
            map.put("message", "您还没有注册成为会员，请先注册再进入！");
            return new ModelAndView("weixin/message", map);
            //return new ModelAndView("redirect:/custWeiXin/mbrRegister", mmap);
        }
    }


    /**
     * 扫描二维码 通过商家的编号
     *
     * @param request
     * @param httpSession
     * @param bizNo       商家的编号
     * @return
     */
    @RequestMapping("/scanQRCode")
    public ModelAndView scanQRCode(HttpServletRequest request, HttpSession httpSession, String bizNo, String way) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (bizNo == null || bizNo.equals("")) {
            map.put("message", "您微信公众号配置的扫描链接参数错误！");
            return new ModelAndView("weixin/message", map);
        }
        //通过商家编号获取微信绑定信息
        BizWx bizWx = bizWxSV.loadByBizNo(bizNo);
        if (bizWx == null || 0 == bizWx.getState() || bizWx.getAppId() == null) {
            map.put("message", "商家未绑定微信支付信息，请联系商家！");
            return new ModelAndView("weixin/message", map);
        }
        httpSession.setAttribute("APPID", bizWx.getAppId());
        httpSession.setAttribute("SECRET", bizWx.getAppSkey());
        httpSession.setAttribute("MCH_ID", bizWx.getBizNum());
        httpSession.setAttribute("API_KEY", bizWx.getApiSkey());

        //获取JS调用参数
        WeiXinUtil.getToken(httpSession);
        map = WeiXinUtil.getSignPackage(request, httpSession); //获取微信签名
        map.put("way", (way == null || way.equals("")) ? 1 : way);
        map.put("bizNo", bizNo);
        return new ModelAndView("weixin/scanQRCode", map);
    }


    /**
     * 微信解除与平台账号的绑定
     *
     * @return
     */
    @RequestMapping("/updateBind")
    public ModelAndView updateBind(HttpSession httpSession) {

        try {
            Map<String, Object> map = new HashMap<String, Object>();
            String openid = "";
            //session失效
            if (httpSession.getAttribute("user_openid") == null || httpSession.getAttribute("user_openid").equals("")) {
                map.clear();
                map.put("message", "页面已经失效，请重新进入！");
                return new ModelAndView("weixin/message", map);
            } else {
                openid = httpSession.getAttribute("user_openid").toString();//获取unionid
            }
            List<MbrOauth> mbrOauthList = mbrOauthSV.queryMbrOauthByOpenid(openid);
            MbrOauth mbrOauth = mbrOauthList.get(0);
            mbrOauth.setUptime(new Date());
            mbrOauth.setState(0);
            mbrOauthSV.update(mbrOauth);//解除绑定
            map = WeiXinUtil.getUserInfo(httpSession);//获取微信用户的信息
            return new ModelAndView("weixin/register", map);
        } catch (Exception e) {
            ModelMap map = new ModelMap();
            map.put("message", 1);
            return new ModelAndView("redirect:/mbrWeiXin/userInfo", map);
        }

    }


    /**
     * 检查手机号是否注册过
     *
     * @param mobile 手机号
     * @return
     */
    @RequestMapping("/verifyMbr")
    public ModelAndView verifyMbr(String mobile) {
        Map<String, Object> map = new HashMap<String, Object>();
        Mbr mbr = mbrSV.loadByMobile(mobile);
        if (mbr != null && mbr.getId() != null) {
            map.put("vState", 1);//该手机已经注册
            map.put("nick", mbr.getNick());
        } else {
            map.put("vState", 0);//该手机未注册
        }
        return new ModelAndView("", map);
    }

    /**
     * 微信客户注册
     *
     * @param valCode  验证码
     * @param mobile   手机号码
     * @param nickname 昵称
     * @param name     名称
     * @param bizNo    商家编号
     * @param email    绑定邮箱
     */
    @RequestMapping("/mbrRegister")
    public ModelAndView regist(String valCode, String mobile, String nickname, String name, String bizNo, String email, String gprsNo, HttpSession httpSession) {
        String msg = null;
        Map<String, Object> map = new HashMap<String, Object>();
        //注册
        String openid = "";
        //session失效
        if (httpSession.getAttribute("user_openid") == null || httpSession.getAttribute("user_openid").equals("")) {
            map.clear();
            map.put("message", "页面已经失效，请重新进入！");
            return new ModelAndView("weixin/message", map);
        } else {
            openid = httpSession.getAttribute("user_openid").toString();//获取unionid
        }
        nickname = WeiXinUtil.removeNonBmpUnicode(nickname);
        name = WeiXinUtil.removeNonBmpUnicode(name);
        msg = mbrSV.createMbr(mobile, bizNo, nickname, name, Std.TPB.WX, openid);
        if (msg != null && !msg.equals("")) {
            map.put("message", msg);
            map.put("mobile", mobile);
            map.put("nickname", nickname);
            map.put("name", name);
            map.put("bizNo", bizNo);
            return new ModelAndView("weixin/mbrRegister", map);
        }

        map.put("message", "注册成功！");
        ModelMap mmap = new ModelMap();
        mmap.addAttribute("gprsNo", gprsNo);
        mmap.addAttribute("bizNo", bizNo);
        mmap.addAttribute("way", 3);
        mmap.addAttribute("rgState", false);
        return new ModelAndView("redirect:/custWeiXin/toReCharge", mmap);
        //return new ModelAndView("redirect:/custWeiXin/authorizeUrl?state="+bizNo+"_31",map);
    }

    /**
     * 编辑页面
     */
    @RequestMapping("/edit")
    public String edit(String id, String bizNo, Model model) {
        Mbr mbr = mbrSV.load(id);
        model.addAttribute("mbr", mbr);
        model.addAttribute("bizNo", bizNo);
        return "mbr/mbrInfoEdit";
    }


    /**
     * 更新
     *
     * @param mbr 会员
     */
    @RequestMapping("update")
    public String update(Mbr mbr, String bizNo) {
        try {
            mbr.setName(WeiXinUtil.removeNonBmpUnicode(mbr.getName()));
            mbrSV.update(mbr);
        } catch (Exception e) {
            return "redirect:/custWeiXin/userInfo?bizNo=" + bizNo;
        }
        return "redirect:/custWeiXin/userInfo?bizNo=" + bizNo;
    }


    /**
     * 充值记录查询
     *
     * @param pager
     */
    @RequestMapping("/reChargeHis")
    public ModelAndView reChargeHis(Pager pager, HttpSession httpSession, String bizNo) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (httpSession.getAttribute("user_openid") == null || httpSession.getAttribute("user_openid").equals("")) {
            map.clear();
            map.put("message", "页面已经失效，请重新进入！");
            return new ModelAndView("weixin/message", map);
        }
        List<MbrRecharge> mbrRechargeList = null;
        try {
            String openid = httpSession.getAttribute("user_openid").toString();
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("openid", openid);
            params.put("bizNo", bizNo);
            pager.setAction("/custWeiXin/reChargeHis");
            pager.setParams(params);
            mbrRechargeList = mbrRechargeSV.queryMbrReCgHisByOpenid(openid, pager.getRowStart(), pager.getPageSize());
            pager.setRecordList(mbrRechargeList);
            pager.setTotalRow(mbrRechargeSV.countMbrReCgHisByOpenid(openid));
        } catch (Exception e) {
            map.put("message", "页面失效、请重新从微信进入！");
            return new ModelAndView("weixin/message", map);
        }
        map.put("mbrRechargeList", mbrRechargeList);
        return new ModelAndView("weixin/mbrReChargeList", map);
    }

    /**
     * 消费记录查询
     *
     * @return
     */
    @RequestMapping("/mbrOrdList")
    public String mbrOrdList(Pager pager, String ordNo, String gprsNo, String bizNo, String beginTime, String endTime, HttpSession httpSession, Model model, HttpServletRequest request) {


        /*httpSession.setAttribute("user_openid","ocwe7v3pwQ2dfvUQJPIrlEQKTGIc");*/
       /* bizNo="B100025";*/
        Map<String, Object> params = new HashMap();
        if(httpSession.getAttribute("user_openid") != null && !httpSession.getAttribute("user_openid").equals("")) {
            String openid = httpSession.getAttribute("user_openid").toString();
            System.out.printf("--------------------------------"+openid);
            pager.setAction("/custWeiXin/mbrOrdList");
            params.put("ordOpenid", openid);
            List<Ord> ordListAll = this.ordSV.queryMbrAllOrd(params, pager.getRowStart(), pager.getPageSize());
            System.out.printf("--------------------------------"+ JSONArray.toJSONString(ordListAll));
            int countAll = this.ordSV.countMbrAllOrd(params);
            pager.setRecordList(ordListAll);
            pager.setTotalRow(countAll);
            pager.setParams(params);
            return "weixin/mbrOrdList";
        } else {
            model.addAttribute("message", "页面已经失效，请重新进入！");
            return "weixin/message";
        }



    }

    /**
     * 查看订单详情
     *
     * @param ordno 订单号
     */
    @RequestMapping("/view")
    public String view(String ordno, Model model) {
        Ord ord = ordSV.loadByOrdNo(ordno);
        if (ord != null) {
            if (Std.PayWay.WX.getKey().equals(ord.getPayWay())) {
                OrdPayWx ordPayWx = ordSV.loadOrdPayWxByOrdNo(ordno);
                model.addAttribute("acct", ordPayWx.getAcct());
            } else if (Std.PayWay.WLT.getKey().equals(ord.getPayWay())) {
                OrdPayWlt ordPayWlt = ordSV.loadOrdPayWltByOrdNo(ordno);
                model.addAttribute("acct", ordPayWlt.getName());
            }
            ProdInstlPos prodInstlPos = prodSV.loadProdInstlPos(ord.getProdNo());
            if (prodInstlPos != null) {
                model.addAttribute("pos", prodInstlPos.getProvName() + prodInstlPos.getCityName() + prodInstlPos.getDistName() + prodInstlPos.getAddr());
            }
            model.addAttribute("ord", ord);
        }
        return "weixin/mbrOrdDetail";
    }


    /**
     * 查询优惠活动
     *
     * @param bizNo       商家编号
     * @param httpSession
     * @param model
     * @return
     */
    @RequestMapping("/queryBizGift")
    public String queryBizGift(String bizNo, HttpSession httpSession, Model model) {
        List<BizGift> bizGiftList = new ArrayList<BizGift>();
        String openid = "";
        //session失效
        if (httpSession.getAttribute("user_openid") == null || httpSession.getAttribute("user_openid").equals("")) {
            model.addAttribute("message", "页面已经失效，请重新进入！");
            return "weixin/message";
        } else {
            openid = httpSession.getAttribute("user_openid").toString();//获取unionid
        }
        Mbr mbr = mbrSV.loadByOpenidAndBizNo(openid, bizNo);
        if (mbr != null && mbr.getId() != null) {
            bizGiftList = bizGiftSV.queryBizGiftListByBizNo(bizNo, 1);
        }
        model.addAttribute("bizGiftList", bizGiftList);
        return "weixin/mbrBizGiftList";
    }


    /**
     * 查询用户使用中的设备状态
     *
     * @param httpSession
     * @param bizNo       商家编号
     * @return
     */
    @RequestMapping("/loadPayMsg")
    public String loadPayMsg(HttpSession httpSession, Model model, String bizNo) {
        //httpSession.setAttribute("user_openid","oJRIpwthLsHrm1r7CNMQjQ0OArpE");
        Map<String, Object> params = new HashMap<String, Object>();
        String openid = "";
        //session失效
        if (httpSession.getAttribute("user_openid") == null || httpSession.getAttribute("user_openid").equals("")) {
            model.addAttribute("message", "页面已经失效，请重新进入！");
            return "weixin/message";
        } else {
            openid = httpSession.getAttribute("user_openid").toString();//获取unionid
        }


        List<Ord> ordList = null;
        Mbr mbr = mbrSV.loadByOpenidAndBizNo(openid, bizNo);
        params.put("succ", 1);
        params.put("state", 2);

        params.put("openid", openid);
        List<Ord> openidList = ordSV.queryOrdJoinOrdPayWx(params, 0, 5);
        int openidCount = ordSV.countOrdJoinOrdPayWx(params);

        if (mbr != null && mbr.getId() != null) {
            params.remove("openid");
            params.put("memberId", mbr.getId());
            List<Ord> mbrOrdList = ordSV.queryOrdJoinOrdPayWx(params, 0, 5);
            int mbrOrdCount = ordSV.countOrdJoinOrdPayWx(params);

            if ((mbrOrdList == null || mbrOrdList.size() == 0) && (openidList != null || openidList.size() != 0)) {
                ordList = openidList;
            } else if ((openidList == null || openidList.size() == 0) && (mbrOrdList != null || mbrOrdList.size() != 0)) {
                ordList = mbrOrdList;
            } else if ((openidList != null || openidList.size() != 0) && (mbrOrdList != null || mbrOrdList.size() != 0)) {
                if (mbrOrdList.get(0).getCrtime().getTime() > openidList.get(0).getCrtime().getTime()) {
                    ordList = mbrOrdList;
                } else {
                    ordList = openidList;
                }
            }

        } else {
            ordList = openidList;
        }


        Date date = new Date();
        Map<String, Object> params2 = new HashMap<String, Object>();
        if (ordList != null && ordList.size() > 0 && ordList.get(0).getGprsNo().startsWith("6")) {
            Prod prod = prodSV.loadByGprsNo(ordList.get(0).getGprsNo());
            long subTime = DateUtil.minuteDifference(date, ordList.get(0).getCrtime());//获取时间差（分钟）
            Integer maxTime = ordList.get(0).getTotalTime();//总使用时间
            int subTime_int = Integer.parseInt(String.valueOf(subTime));//转换类型
            Integer usedTime = subTime_int;//已经使用时间
            if (maxTime == null || maxTime < usedTime) {
                usedTime = maxTime;
            }
            model.addAttribute("prod", prod);
            model.addAttribute("maxTime", maxTime);
            model.addAttribute("usedTime", usedTime);
            model.addAttribute("useing", true);
            return "weixin/loadPayMsg";
        }
        /*else if (ordList != null && ordList.size() > 0) {
            params2.clear();
            params2.put("cmd", "WS30");
            params2.put("gprsNo", ordList.get(0).getGprsNo());
            msgRecvSV.resvPaySt(params2);
            for (int i = 4; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
                if (RedisUtil.get(ordList.get(0).getGprsNo() + "WD302") != null) {
                    model.addAttribute("useing", true);
                    Prod prod = prodSV.loadByGprsNo(ordList.get(0).getGprsNo());
                    model.addAttribute("prod", prod);
                    model.addAttribute("overplusTime", Integer.parseInt(RedisUtil.get(ordList.get(0).getGprsNo() + "WD302")) / 60);
                    return "weixin/loadPayMsg";
                }
            }
        }*/

        if (ordList != null && ordList.size() > 0) {
            model.addAttribute("useing", true);
            Prod prod = prodSV.loadByGprsNo(ordList.get(0).getGprsNo());
            int runtime = prod.getRunTime();
            //初始化maxTime和usedTime
            Integer maxTime = 0;//总使用时间
            Integer usedTime = runtime;//已经使用时间
            for (int i = 0; i < ordList.size(); i++) {
                long subTime = DateUtil.minuteDifference(date, ordList.get(i).getCrtime());//获取时间差（分钟）

                int subTime_int = Integer.parseInt(String.valueOf(subTime));//转换类型
                //如果和最近的一笔订单的产品一样，且与现在时间的时间差小于总的使用时间。
                BigDecimal cv = BigDecimal.ZERO;
                if (prod.getGprsNo().startsWith("0") || prod.getGprsNo().startsWith("3")) {
                    cv = new BigDecimal(ordList.get(i).getPulse());//脉冲数量
                } else {
                    cv = ordList.get(i).getProdPrice().divide(prod.getPrice(), 10, ROUND_HALF_DOWN);
                }
                if (prod.getProdNo().equals(ordList.get(i).getProdNo()) && subTime_int < (maxTime + new BigDecimal(runtime).multiply(cv).intValue())) {
                    maxTime = maxTime + runtime * (cv).intValue();
                    usedTime = subTime_int;
                } else {
                    break;
                }
            }
            model.addAttribute("prod", prod);
            model.addAttribute("maxTime", maxTime);
            model.addAttribute("usedTime", usedTime);
        } else {
            model.addAttribute("useing", false);
        }

        return "weixin/loadPayMsg";
    }

    /**
     * 获取用户经纬度
     *
     * @param request
     * @return
     */
    @RequestMapping("/getLocation")
    public ModelAndView getLocation(HttpServletRequest request, HttpSession httpSession, String wayFlag, String bizNo) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (httpSession.getAttribute("user_openid") == null || httpSession.getAttribute("user_openid").equals("")) {
            map.put("message", "请在微信公众号内操作！");
            return new ModelAndView("weixin/message", map);
        }
        map = WeiXinUtil.getSignPackage(request, httpSession); //获取微信签名
        map.put("wayFlag", wayFlag);
        map.put("bizNo", bizNo);
        return new ModelAndView("weixin/getLocation", map);
    }

    /**
     * 进入查询附近设备的页面
     *
     * @param latitude  纬度
     * @param longitude 经度
     * @param accuracy  精准度
     * @return
     */
    @RequestMapping("/nearby")
    public ModelAndView nearby(String latitude, String longitude, String accuracy) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("latitude", latitude);
        map.put("longitude", longitude);
        map.put("accuracy", accuracy);
        return new ModelAndView("weixin/nearby", map);
    }

    /**
     * 获取附近设备
     *
     * @param lat      维度
     * @param lng      经度
     * @param distance 距离（米）
     * @return
     */
    @RequestMapping("/queryGeo")
    public ModelAndView queryGeo(double lat, double lng, double distance, String bizNo, HttpSession httpSession) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> params = new HashMap<String, Object>();
        String openid = "";
        //session失效
        if (httpSession.getAttribute("user_openid") == null || httpSession.getAttribute("user_openid").equals("")) {
            map.clear();
            map.put("message", "页面已经失效，请重新进入！");
            return new ModelAndView("weixin/message", map);
        } else {
            openid = httpSession.getAttribute("user_openid").toString();//获取unionid
        }
        Mbr mbr = mbrSV.loadByOpenidAndBizNo(openid, bizNo);

        if (mbr != null && mbr.getId() != null) {
            params.put("bizNo", mbr.getBizNo());
        } else {
            Map<String, Object> params_ord = new HashMap<String, Object>();
            params_ord.put("openid", openid);
            List<Ord> ordList = ordSV.queryOrdJoinOrdPayWx(params_ord, 0, 1);
            if (ordList != null && ordList.size() != 0) {
                params.put("bizNo", ordList.get(0).getBizNo());
            } else {
                params.put("bizNo", bizNo);
            }
        }
        String jsonStr = null;
        try {
            Location[] locations = WeiXinUtil.getRectangle4Point(lat, lng, distance);
            params.put("minLat", locations[2].getLatitude());
            params.put("maxLat", locations[0].getLatitude());
            params.put("minLng", locations[0].getLongitude());
            params.put("maxLng", locations[1].getLongitude());
            List<ProdInstlPos> prodInstlPosList = prodInstlPosSV.queryProdByDisTance(params, -1, -1);
            map.put("prodInstlPosList", prodInstlPosList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("", map);
    }


    /**
     * 转发位置信息到不同应用
     *
     * @param wayFlag   标识
     * @param latitude  纬度
     * @param longitude 经度
     * @return
     */
    @RequestMapping("/locationInfo")
    public ModelAndView locationInfo(String wayFlag, String latitude, String longitude, String bizNo) {
        Map<String, Object> map = new HashMap<String, Object>();
        JsonObject jsonObject_zh = new JsonObject();
        //转换坐标
        try {
            jsonObject_zh = WeiXinUtil.httpPost2(
                    "http://api.map.baidu.com/geoconv/v1/?coords=" + longitude + "," + latitude
                            + "&ak=8FcT81aeeGnSSC1GeOeHMlOAe6bK4som&from=3&to=5&output=json", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        longitude = jsonObject_zh.get("result").getAsJsonArray().get(0).getAsJsonObject().get("x").getAsString();//经度
        latitude = jsonObject_zh.get("result").getAsJsonArray().get(0).getAsJsonObject().get("y").getAsString();//纬度

        //解析坐标
        JsonObject jsonObject = new JsonObject();
        try {
            jsonObject = WeiXinUtil.httpPost2(
                    "http://api.map.baidu.com/geocoder?location=" + latitude + "," + longitude
                            + "&output=json&coord_type=gcj02&key=8FcT81aeeGnSSC1GeOeHMlOAe6bK4som&src=ydkj", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.put("bizNo", bizNo);
        if (jsonObject != null && jsonObject.get("result") != null && jsonObject.get("result").getAsJsonObject().get("formatted_address") != null) {
            map.put("address", jsonObject.get("result").getAsJsonObject().get("formatted_address").getAsString());
        }
        if (jsonObject != null && jsonObject.get("result") != null && jsonObject.get("result").getAsJsonObject().get("addressComponent") != null) {
            map.put("province", jsonObject.get("result").getAsJsonObject().get("addressComponent").getAsJsonObject().get("province").getAsString());
            map.put("city", jsonObject.get("result").getAsJsonObject().get("addressComponent").getAsJsonObject().get("city").getAsString());
            map.put("district", jsonObject.get("result").getAsJsonObject().get("addressComponent").getAsJsonObject().get("district").getAsString());
            map.put("street", jsonObject.get("result").getAsJsonObject().get("addressComponent").getAsJsonObject().get("street").getAsString());
            map.put("street_number", jsonObject.get("result").getAsJsonObject().get("addressComponent").getAsJsonObject().get("street_number").getAsString());
        }
        map.put("latitude", latitude);
        map.put("longitude", longitude);
        if ("1".equals(wayFlag)) {//附近
            return new ModelAndView("weixin/nearby", map);
        } else if ("2".equals(wayFlag)) {//添加设备模板
            ModelMap mmap = new ModelMap();
            mmap.addAttribute("latitude", latitude);
            mmap.addAttribute("longitude", longitude);
            /*
            mmap.addAttribute("address", jsonObject.get("result").getAsJsonObject().get("formatted_address").getAsString());
            mmap.addAttribute("province", jsonObject.get("result").getAsJsonObject().get("addressComponent").getAsJsonObject().get("province").getAsString());
            mmap.addAttribute("city", jsonObject.get("result").getAsJsonObject().get("addressComponent").getAsJsonObject().get("city").getAsString());
            mmap.addAttribute("district", jsonObject.get("result").getAsJsonObject().get("addressComponent").getAsJsonObject().get("district").getAsString());
            mmap.addAttribute("street", jsonObject.get("result").getAsJsonObject().get("addressComponent").getAsJsonObject().get("street").getAsString());
            mmap.addAttribute("street_number", jsonObject.get("result").getAsJsonObject().get("addressComponent").getAsJsonObject().get("street_number").getAsString());
            */
            return new ModelAndView("redirect:/prod/prodInstlPosModel/add", mmap);
        } else if (wayFlag.indexOf("3_") > -1) {//修改位置模板
            String id = wayFlag.split("_")[1];
            ModelMap mmap = new ModelMap();
            mmap.addAttribute("id", id);
            mmap.addAttribute("latitude", latitude);
            mmap.addAttribute("longitude", longitude);
            /*
            mmap.addAttribute("address", jsonObject.get("result").getAsJsonObject().get("formatted_address").getAsString());
            mmap.addAttribute("province", jsonObject.get("result").getAsJsonObject().get("addressComponent").getAsJsonObject().get("province").getAsString());
            mmap.addAttribute("city", jsonObject.get("result").getAsJsonObject().get("addressComponent").getAsJsonObject().get("city").getAsString());
            mmap.addAttribute("district", jsonObject.get("result").getAsJsonObject().get("addressComponent").getAsJsonObject().get("district").getAsString());
            mmap.addAttribute("street", jsonObject.get("result").getAsJsonObject().get("addressComponent").getAsJsonObject().get("street").getAsString());
            mmap.addAttribute("street_number", jsonObject.get("result").getAsJsonObject().get("addressComponent").getAsJsonObject().get("street_number").getAsString());
            */
            return new ModelAndView("redirect:/prod/prodInstlPosModel/edit", mmap);
        } else {
            map.clear();
            map.put("message", "非正常途径打开该页面！");
            return new ModelAndView("weixin/message", map);
        }
    }


}
