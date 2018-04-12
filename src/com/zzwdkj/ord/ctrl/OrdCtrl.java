package com.zzwdkj.ord.ctrl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zzwdkj.base.BaseCtrl;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.biz.entity.Biz;
import com.zzwdkj.biz.entity.BizTakeWx;
import com.zzwdkj.biz.entity.BizWx;
import com.zzwdkj.biz.service.BizSV;
import com.zzwdkj.biz.service.BizTakeWxSV;
import com.zzwdkj.biz.service.BizWxSV;
import com.zzwdkj.common.BaseConfig;
import com.zzwdkj.common.ExtMsg;
import com.zzwdkj.common.Pager;
import com.zzwdkj.common.Std;
import com.zzwdkj.common.weixin.WeiXinUtil;
import com.zzwdkj.mbr.dao.MbrCoinDAO;
import com.zzwdkj.mbr.entity.*;
import com.zzwdkj.mbr.service.MbrCoinSV;
import com.zzwdkj.mbr.service.MbrSV;
import com.zzwdkj.mbr.service.MbrWalletSV;
import com.zzwdkj.msg.service.MsgSV;
import com.zzwdkj.ord.entity.*;
import com.zzwdkj.ord.service.OrdSV;
import com.zzwdkj.prod.entity.Prod;
import com.zzwdkj.prod.entity.ProdInstlPos;
import com.zzwdkj.prod.entity.ProdOnlLog;
import com.zzwdkj.prod.service.ProdSV;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ord, 订单_订单表Ctrl
 *
 * @author guoxianwei  2016-09-07 15:01:44
 */
@Controller
@RequestMapping("/ord")
public class OrdCtrl extends BaseCtrl {

    private final static Logger LOGGER = Logger.getLogger(OrdCtrl.class);

    @Resource
    private OrdSV ordSV;
    @Resource
    private ProdSV prodSV;
    @Resource
    private BizSV bizSV;
    @Resource
    private BizWxSV bizWxSV;
    @Resource
    private BizTakeWxSV bizTakeWxSV;
    @Resource
    private MbrSV mbrSV;
    @Resource
    private MbrWalletSV mbrWalletSV;
    @Resource
    private MsgSV msgSV;
    @Resource
    private MbrCoinSV mbrCoinSV;
    @Resource
    private IdentifierSV identifierSV;
    @Resource
    private MbrCoinDAO mbrCoinDAO;

    @RequestMapping("list")
    public String list(Pager pager, String ordNo, String gprsNo, String beginTime, String endTime, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ordno", ordNo);
        params.put("gprsNo", gprsNo);
        if (StringUtils.isEmpty(ordNo) && StringUtils.isEmpty(gprsNo)) {
            params.put("beginTime", beginTime);
            params.put("endTime", endTime);
        }
        pager.setAction("/ord/list");
        pager.setParams(params);
        params.put("bizNo", biz.getBizNo());
        List<Ord> ordList = ordSV.queryOrd(params, pager.getRowStart(), pager.getPageSize());
        pager.setRecordList(ordList);
        pager.setTotalRow(ordSV.count(params));
        return "grzx/order/mbrOrderList";
    }

    @RequestMapping("bizlist")
    public String bizlist(Pager pager, String bizNo, String ordNo, String gprsNo, String beginTime, String endTime, Model model) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ordno", ordNo);
        params.put("gprsNo", gprsNo);
        params.put("bizNo", bizNo);
        //if (StringUtils.isEmpty(ordNo) && StringUtils.isEmpty(gprsNo)) {
            params.put("beginTime", beginTime);
            params.put("endTime", endTime);
        //}
        pager.setAction("/ord/bizlist");
        pager.setParams(params);
        List<Ord> ordList = ordSV.queryOrd(params, pager.getRowStart(), pager.getPageSize());
        pager.setRecordList(ordList);
        List<Biz> bizList = bizSV.queryBiz(null, 0, 50);
        model.addAttribute("bizList", bizList);
        pager.setTotalRow(ordSV.count(params));
        return "sjxx/sellerOrder/bizOrdList";
    }

    @RequestMapping("ord")
    public String ord(String gprsNo, String nickname,Integer authorizeState, Model model, HttpSession httpSession) {


        if (httpSession.getAttribute("user_openid") == null || httpSession.getAttribute("user_openid").equals("")||
                authorizeState==null||authorizeState!=1) {
            return "forward:/custWeiXin/authorize";
        }

        String opentid = httpSession.getAttribute("user_openid").toString();//获取用户openid

        Map<String, Object> map = new HashMap<String, Object>();
        if (gprsNo != null) {
            Prod prod = prodSV.loadByGprsNo(gprsNo);//获取设备信息
            if (prod != null) {

                BizWx bizWx = bizWxSV.loadByBizNo(prod.getBizNo());
                //是否商家有微信公众号
                if (bizWx == null || 0 == bizWx.getState()) {//没有则获取上级商家的微信公众号信息
                    Biz biz = bizSV.loadBizByBizNo(prod.getBizNo());
                    bizWx = bizWxSV.loadByBizNo(biz.getParBizNo());
                    model.addAttribute("isRegister", false);
                } else {//有则获取该支付用户是否是商家的会员
                    model.addAttribute("isRegister", true);
                    //获取会员信息，以及会员的钱包和积分
                    Mbr mbr = mbrSV.loadByOpenidAndBizNo(opentid, prod.getBizNo());
                    if (mbr != null && mbr.getId() != null) {//是会员
                        map.put("mbr", mbr);
                        MbrWallet mbrWallet = mbrWalletSV.loadByMemberId(mbr.getId());
                        MbrCoin mbrCoin = mbrCoinSV.loadByMemberId(mbr.getId());
                        if (mbrWallet != null && mbrWallet.getId() != null) {
                            map.put("mbrWallet", mbrWallet);
                            if(mbrCoin != null && mbrCoin.getId() != null) {
                                map.put("mbrCoin", mbrCoin);
                            }
                        }
                    }
                    model.addAllAttributes(map);
                }

                //判断商家是否绑定微信公众号
                if (bizWx == null || bizWx.getAppId() == null) {
                    model.addAttribute("message", "商家未绑定微信支付信息，请联系商家！");
                    return "weixin/message";
                }

                //判断是否关注微信公众号
                try {
                    map = WeiXinUtil.getUserInfo(httpSession);//获取微信用户的信息
                } catch (Exception e) {
                    return "forward:/custWeiXin/authorize";
                }
                LOGGER.debug(map);
                if (map.get("subscribe") == null) {
                    return "forward:/custWeiXin/authorize";
                }
                if (map.get("subscribe").toString().equals("0") && bizWx.getFocus() == 1) {
                    String ticket = WeiXinUtil.getQrcodeTicket(httpSession).get("QrcodeTicket").toString();
                    model.addAttribute("ticket", ticket);
                    System.out.println("----------------ticket:" + ticket);
                    return "weixin/subscribe";
                }


                model.addAttribute("gprsNo", prod.getGprsNo());
                model.addAttribute("logo", prod.getLogo());
                model.addAttribute("perPrice", prod.getPrice());
                model.addAttribute("otherMoneyState", prod.getOtherMoneyState());
                String[][] moneyOptionList = new String[6][3];
                if (prod.getOtherMoneyState() == 1 && prod.getOtherMoneyOption() != null) {
                    moneyOptionList=JSON.parseObject(prod.getOtherMoneyOption(),new TypeReference<String[][]>(){});
                }
                /*else if (prod.getOtherMoneyState() == 2 && prod.getOtherMoneyOption() != null) {
                    String[] optionStr = prod.getOtherMoneyOption().replaceAll("，", ",").trim().split(",");
                    for (int i = 0; i < optionStr.length; i++) {
                        if (!optionStr[i].equals("")) {
                            moneyOptionList[i]=(optionStr[i]);
                            moneyOptionList[i]=(optionStr[i]);
                        }
                    }
                }*/

                model.addAttribute("moneyOptionList", moneyOptionList);
                model.addAttribute("name", prod.getProdName());
                model.addAttribute("typeStr", prod.getTypeStr());
                model.addAttribute("bizName", prod.getBizName());
                model.addAttribute("nickname", WeiXinUtil.removeNonBmpUnicode(nickname));
                model.addAttribute("bizNo", prod.getBizNo());
                Biz biz_wx = bizSV.loadBizByBizNo(prod.getBizNo());
                httpSession.setAttribute("wxBizNoLogo",biz_wx.getLogo());
                httpSession.setAttribute("wxBizNoName",biz_wx.getName());
                return "/mbr/ord";
            }
        }
        return null;
    }

    @RequestMapping("create")
    public ExtMsg create(String acct, String gprsNo, Integer perCt, Integer payWay, String totalPay,
                         HttpSession httpSession) {
        if (gprsNo == null || payWay == null || perCt == null || totalPay == null || totalPay.equals("")) {
            return ExtMsg.fail("无效参数");
        }
        Prod prod = prodSV.loadByGprsNo(gprsNo);//获取模块号
        if (prod != null && prod.getState() != null && prod.getState() == 1) {//设备状态为1就是启用
            ProdOnlLog onlLog = prodSV.loadProdOnline(prod.getProdNo());//获取在线设备的编号
            if (prod == null ||((onlLog==null||onlLog.getState()==0)&&!gprsNo.substring(0, 1).equals("2"))){
                return ExtMsg.fail("设备不在线，无法进行支付。");
            }
            perCt = perCt == null ? 1 : perCt;
            BigDecimal prodPrice = new BigDecimal(totalPay);

            //获取脉冲数量
            Integer pulse=1;
            boolean pulseState=false;//是否获取到
            String[][] moneyOptionList = new String[6][3];//产品金额集合
            if (prod.getOtherMoneyState() == 1 && prod.getOtherMoneyOption() != null) {
                moneyOptionList=JSON.parseObject(prod.getOtherMoneyOption(),new TypeReference<String[][]>(){});
            }else {
                return ExtMsg.fail("设备参数异常，无法进行支付。");
            }
            //遍历金额找出金额对应的脉冲数
            for (String[] strings:moneyOptionList){
             if (strings[1]!=null&&!strings[1].equals("")&&strings[1].equals(totalPay)) {
                 pulseState=true;
                 pulse=(strings[2]!=null&&!strings[2].equals(""))?Integer.parseInt(strings[2]):1;
             }
            }
            if (!pulseState&&(prod.getGprsNo().startsWith("2")||prod.getGprsNo().startsWith("0"))){
                return ExtMsg.fail("创建订单异常，请重新操作。");
            }

            //创建订单
            Ord ord = new Ord(prod.getBizNo(), prod.getBizName(), prod.getProdNo(), prod.getProdName(), gprsNo, prod.getPrice(),  perCt, prodPrice, payWay,pulse);//（修改人：苏方宁；12.8）
            System.out.println("------------------------------------------------------订单--------------------------------------------------------------"+ ord);

            ord.setSucc(0);//待结算
            ord.setOrdOpenid(httpSession.getAttribute("user_openid").toString());
            ord.setDevType(prod.getType());
            Std.PayWay payWay1 = Std.PayWay.valueOf(payWay);//获取支付方式
            String resvAcct = null;
            String resvBizNo = null;
            BizWx bizWx = bizWxSV.loadByBizNo(prod.getBizNo());
            if (bizWx != null && 1 == bizWx.getState()) {
                resvAcct = bizWx.getBizNum();//商户号
                resvBizNo = bizWx.getBizNo();//商家编号
                ord.setTransfersIs(0);//是否自动转账（0不自动转账）
                ord.setTransfersState(0);//转账状态（0代转账）
            } else {
                Biz biz = bizSV.loadBizByBizNo(prod.getBizNo());//加载商家
                bizWx = bizWxSV.loadByBizNo(biz.getParBizNo());//获取商家微信
                if (bizWx != null && 1 == bizWx.getState()) {
                    resvAcct = bizWx.getBizNum();
                    resvBizNo = bizWx.getBizNo();
                }
                BizTakeWx bizTakeWx = bizTakeWxSV.loadByBizNo(prod.getBizNo());//微信自动转账
                if (bizTakeWx != null && bizTakeWx.getState() == 1) {//状态开启
                    ord.setTransfersIs(1);//是否自动转账（1自动转账）
                    ord.setTransfersState(0);//转账状态（0代转账）
                } else {
                    ord.setTransfersIs(0);
                    ord.setTransfersState(0);
                }
            }
            //微信支付
            if (Std.PayWay.WX.getKey().equals(payWay1.getKey()) && StringUtils.isNotEmpty(resvAcct)) {
                OrdPayWx ordPayWx = new OrdPayWx(acct, httpSession.getAttribute("user_openid").toString(), prodPrice, ord.getOrdno(), resvAcct, resvBizNo);
                ordSV.createWxOrd(ord, ordPayWx);
            }

            //钱包支付
            if (Std.PayWay.WLT.getKey().equals(payWay1.getKey()) && StringUtils.isNotEmpty(resvAcct)) {
                Mbr mbr = mbrSV.loadByOpenidAndBizNo(httpSession.getAttribute("user_openid").toString(), prod.getBizNo());
                if (mbr != null && mbr.getId() != null) {
                    System.out.println("------------------------------------------------------会员号--------------------------------------------------------------"+ mbr);
                    MbrWallet mbrWallet = mbrWalletSV.loadByMemberId(mbr.getId());//获取会员钱包（修改人：苏方宁；12.2）
                    MbrCoin mbrCoin = mbrCoinSV.loadByMemberId(mbr.getId());
                    System.out.println("------------------------------------------------------会员钱包--------------------------------------------------------------"+ mbrWallet);
                    System.out.println("------------------------------------------------------会员ID--------------------------------------------------------------"+ mbrWallet.getId());
                    BigDecimal wallet = null;
                    BigDecimal coins = null;
                    // MbrCoinChged mbrCoinChged = new MbrCoinChged(mbr.getId(), 0, prodPrice, "积分支付");
                    BigDecimal half = new BigDecimal(2);//（修改人：苏方宁；12.2）(设置half为2为下面价格除以2准备)
                    BigDecimal moiety =  prodPrice.divide(half);
                     if (mbrWallet != null && mbrWallet.getId() != null && mbrCoin != null&& mbrCoin.getId() != null ) {//第一个判断条件（如果钱包和积分都不为空执行）
                         if ((moiety.compareTo(mbrCoin.getCoin()) == 1 && mbrCoin.getCoin().compareTo(new BigDecimal(0)) == 1) || mbrCoin.getCoin().compareTo(new BigDecimal(0)) == 0) {
                             System.out.println("------------------------------------------------------会员积分--------------------------------------------------------------" + mbrCoin.getCoin());

                             System.out.println("------------------------------------------------------会员ID--------------------------------------------------------------" + mbrCoin.getCoin());
                             System.out.println("------------------------------------------------------会员积分ID--------------------------------------------------------------" + mbrCoin.getId());
                             coins = mbrCoin.getCoin();//消费的积分（修改人：苏方宁；12.8）
                             System.out.println("------------------------------------------------------支付积分--------------------------------------------------------------" + coins);
                             wallet = prodPrice.subtract(coins);//消费的钱包额度（修改人：苏方宁；12.8）
                             MbrCoinChged mbrCoinChged = new MbrCoinChged(mbr.getId(), 0, coins, "积分支付");//用支付积分产生一条会员积分变更记录（mbrCoin.getCoin()剩余的积分）（修改人：苏方宁；12.2）
                             MbrWalletChged mbrWalletChged = new MbrWalletChged(mbr.getId(), 0, wallet, "钱包支付");//用支付金额产生一条会员钱包变更记录(prodPrice获取的金额总钱数，prodPrice.subtract(mbrCoin.getCoin())总金额-积分额度)
                             OrdPayWlt ordPayWlt = new OrdPayWlt(mbrWallet.getId(), wallet, ord.getOrdno(), prod.getBizNo());//会员钱包支付记录  需要改动（修改人：苏方宁；12.2）
                             ordPayWlt.setName(mbr.getName());
                             System.out.println("------------------------------------------------------会员名--------------------------------------------------------------" + mbr.getName());
                             System.out.println("------------------------------------------------------会员名--------------------------------------------------------------" + ordPayWlt.getName());
                             OrdPayCoin ordPayCoin = new OrdPayCoin(mbrWallet.getId(), coins, ord.getOrdno(), prod.getBizNo());//会员积分支付记录（修改人：苏方宁；12.7）
                             ord.setMemberId(mbr.getId());
                             ord.setWallet(wallet);
                             ord.setCoins(coins);
                             System.out.println("------------------------------------------------------支付积分1111--------------------------------------------------------------" + coins);
                             System.out.println("------------------------------------------------------支付积分2222--------------------------------------------------------------" + ord.getCoins());
                             String msg = ordSV.createWalletOrd(ord, ordPayWlt, mbrWallet, mbrWalletChged, ordPayCoin, mbrCoin, mbrCoinChged);
                             System.out.println("------------------------------------------------------支付记录--------------------------------------------------------------" + ord);

                             System.out.println("------------------------------------------------------支付记录--------------------------------------------------------------" + msg);

                             if (msg != null) {
                                 return ExtMsg.fail(msg);
                             } else {
                                 ord = ordSV.loadByOrdNo(ord.getOrdno());
                                 LOGGER.debug("钱包支付成功");
                                 //如果是OTP支付模式则不发送指令
                                 if (ord != null && ord.getState() == 1 && !ord.getGprsNo().substring(0, 1).equals("2")) {
                                     LOGGER.debug("发送指令");
                                     if (ord.getGprsNo().startsWith("0")) {
                                         msgSV.payNotice(ord.getOrdno(), ord.getGprsNo(), BaseConfig.getPayMoney(ord.getGprsNo(), ord.getOrdno(), ord.getPulse().intValue()));
                                     } else {
                                         msgSV.payNotice(ord.getOrdno(), ord.getGprsNo(), BaseConfig.getPayMoney(ord.getGprsNo(), ord.getOrdno(), ord.getProdPrice().intValue()));
                                     }
                                     //msgSV.payNotice(ord.getOrdno(), ord.getGprsNo(), BaseConfig.getPayMoney(ord.getGprsNo(), ord.getOrdno(), (ord.getProdPrice().multiply(new BigDecimal(prod.getProdCv()))).intValue()));
                                 }

                             }

                         } else {
                             wallet = moiety;//消费的积分额度（修改人：苏方宁；12.8）
                             coins = moiety;//消费的钱包金额（修改人：苏方宁；12.8）
                             System.out.println("------------------------------------------------------支付积分--------------------------------------------------------------" + coins);
                             MbrWalletChged mbrWalletChged = new MbrWalletChged(mbr.getId(), 0, wallet, "钱包支付"); //用支付积分产生一条会员积分变更记录（修改人：苏方宁；12.2）
                             MbrCoinChged mbrCoinChged = new MbrCoinChged(mbr.getId(), 0, coins, "积分支付");//用支付积分产生一条会员积分变更记录（mbrCoin.getCoin()剩余的积分）（修改人：苏方宁；12.2）
                             System.out.println("------------------------------------------------------钱包变更--------------------------------------------------------------" + mbrWalletChged);
                             System.out.println("------------------------------------------------------积分变更记录--------------------------------------------------------------" + mbrCoinChged);

                             OrdPayWlt ordPayWlt = new OrdPayWlt(mbrWallet.getId(), wallet, ord.getOrdno(), prod.getBizNo());//会员钱包支付记录  需要改动（修改人：苏方宁；12.8）
                             ordPayWlt.setName(mbr.getName());
                             System.out.println("------------------------------------------------------会员名--------------------------------------------------------------" + mbr.getName());
                             System.out.println("------------------------------------------------------会员名--------------------------------------------------------------" + ordPayWlt.getName());
                             OrdPayCoin ordPayCoin = new OrdPayCoin(mbrWallet.getId(), coins, ord.getOrdno(), prod.getBizNo());//会员积分支付记录（修改人：苏方宁；12.8）

                             System.out.println("------------------------------------------------------支付钱包记录--------------------------------------------------------------" + ordPayWlt);
                             System.out.println("------------------------------------------------------支付积分记录--------------------------------------------------------------" + ordPayCoin);
                             ord.setMemberId(mbr.getId());
                             System.out.println("------------------------------------------------------支付钱包记录--------------------------------------------------------------" + wallet);
                             System.out.println("------------------------------------------------------支付积分记录--------------------------------------------------------------" + mbr.getId());
                             ord.setWallet(wallet);
                             ord.setCoins(coins);
                             String msg = ordSV.createWalletOrd(ord, ordPayWlt, mbrWallet, mbrWalletChged, ordPayCoin, mbrCoin, mbrCoinChged);
                             System.out.println("------------------------------------------------------支付记录--------------------------------------------------------------" + msg);
                             System.out.println("------------------------------------------------------ORD--------------------------------------------------------------" + ord);
                             System.out.println("------------------------------------------------------ORD--------------------------------------------------------------" + ordPayWlt);

                             if (msg != null) {
                                 return ExtMsg.fail(msg);
                             } else {
                                 ord = ordSV.loadByOrdNo(ord.getOrdno());
                                 LOGGER.debug("钱包支付成功");
                                 //如果是OTP支付模式则不发送指令
                                 if (ord != null && ord.getState() == 1 && !ord.getGprsNo().substring(0, 1).equals("2")) {
                                     LOGGER.debug("发送指令");
                                     if (ord.getGprsNo().startsWith("0")) {
                                         msgSV.payNotice(ord.getOrdno(), ord.getGprsNo(), BaseConfig.getPayMoney(ord.getGprsNo(), ord.getOrdno(), ord.getPulse().intValue()));
                                     } else {
                                         msgSV.payNotice(ord.getOrdno(), ord.getGprsNo(), BaseConfig.getPayMoney(ord.getGprsNo(), ord.getOrdno(), ord.getProdPrice().intValue()));
                                     }
                                     //msgSV.payNotice(ord.getOrdno(), ord.getGprsNo(), BaseConfig.getPayMoney(ord.getGprsNo(), ord.getOrdno(), (ord.getProdPrice().multiply(new BigDecimal(prod.getProdCv()))).intValue()));
                                 }

                             }
                         }

                    }else if(mbrWallet != null && mbrWallet.getId() != null){
                        wallet = prodPrice;//（修改人：苏方宁；12.8）
                        coins = new BigDecimal(0);
                        System.out.println("------------------------------------------------------支付钱11111-------------------------------------------------------------"+ wallet);
                        System.out.println("------------------------------------------------------huiyuan钱11111-------------------------------------------------------------"+ mbrWallet.getMoney());

                        MbrWalletChged mbrWalletChged = new MbrWalletChged(mbr.getId(), 0, wallet, "钱包支付");//用支付金额产生一条会员钱包变更记录（修改人：苏方宁；12.8）
                         System.out.println("------------------------------------------------------钱包变更记录--------------------------------------------------------------" + mbrWalletChged);

                         MbrCoinChged mbrCoinChged = new MbrCoinChged(mbr.getId(), 0,coins, "积分支付");//用支付积分产生一条会员积分变更记录
                         System.out.println("------------------------------------------------------积分变更记录--------------------------------------------------------------" + mbrCoinChged);

                         OrdPayWlt ordPayWlt = new OrdPayWlt(mbrWallet.getId(), wallet, ord.getOrdno(), prod.getBizNo());//会员钱包支付记录（修改人：苏方宁；12.8）
                         System.out.println("------------------------------------------------------支付钱包记录--------------------------------------------------------------" + ordPayWlt);
                         ordPayWlt.setName(mbr.getName());
                         System.out.println("------------------------------------------------------会员名--------------------------------------------------------------" + mbr.getName());
                         System.out.println("------------------------------------------------------会员名--------------------------------------------------------------" + ordPayWlt.getName());
                         OrdPayCoin ordPayCoin   = new OrdPayCoin(mbrWallet.getId(), coins, ord.getOrdno(), prod.getBizNo());//会员积分支付记录（修改人：苏方宁；12.8）
                         System.out.println("------------------------------------------------------支付积分记录--------------------------------------------------------------" + ordPayCoin);

                         ord.setMemberId(mbr.getId());
                         ord.setWallet(wallet);
                         ord.setCoins(coins);
                        String msg = ordSV.createWalletOrd(ord, ordPayWlt, mbrWallet, mbrWalletChged,ordPayCoin,mbrCoin,mbrCoinChged);//修改人：苏方宁；12.8）
                         System.out.println("------------------------------------------------------订单记录--------------------------------------------------------------" + ord);

                         if (msg != null) {
                            return ExtMsg.fail(msg);
                        } else {
                            ord = ordSV.loadByOrdNo(ord.getOrdno());
                            LOGGER.debug("钱包支付成功");
                            //如果是OTP支付模式则不发送指令
                            if (ord != null && ord.getState() == 1 && !ord.getGprsNo().substring(0, 1).equals("2")) {
                                LOGGER.debug("发送指令");
                                if (ord.getGprsNo().startsWith("0")){
                                    msgSV.payNotice(ord.getOrdno(),ord.getGprsNo(), BaseConfig.getPayMoney(ord.getGprsNo(),ord.getOrdno(), ord.getPulse().intValue()));
                                }else {
                                    msgSV.payNotice(ord.getOrdno(),ord.getGprsNo(), BaseConfig.getPayMoney(ord.getGprsNo(),ord.getOrdno(), ord.getProdPrice().intValue()));
                                }
                                //msgSV.payNotice(ord.getOrdno(), ord.getGprsNo(), BaseConfig.getPayMoney(ord.getGprsNo(), ord.getOrdno(), (ord.getProdPrice().multiply(new BigDecimal(prod.getProdCv()))).intValue()));
                            }

                        }
                    } else {
                        System.out.println("------------------------------------------------------支付积分--------------------------------------------------------------");

                        return ExtMsg.fail("钱包余额不足，请选择其它支付方式！");
                    }
                } else {
                    return ExtMsg.fail("该设备无法使用钱包支付！");
                }
            }
            return ExtMsg.success(ord.getOrdno());
        } else {
            return ExtMsg.fail("产品暂时停用，无法支付。");
        }
    }

    /**
     * 查看订单详情
     *
     * @param ordno 订单号
     */
    @RequestMapping("view")
    public String view(String ordno, Model model) {
        Ord ord = ordSV.loadByOrdNo(ordno);
        if (ord != null) {
            if (Std.PayWay.WX.getKey().equals(ord.getPayWay())) {
                OrdPayWx ordPayWx = ordSV.loadOrdPayWxByOrdNo(ordno);
                model.addAttribute("acct", ordPayWx.getAcct());
            } else if (Std.PayWay.WLT.getKey().equals(ord.getPayWay())) {
                OrdPayWlt ordPayWlt = ordSV.loadOrdPayWltByOrdNo(ordno);
                model.addAttribute("acct", ordPayWlt.getName());//修改时间：12.20修改人：sfn
                System.out.println("------------------------------------------------------会员名--------------------------------------------------------------" +ordPayWlt.getName());

            }
            ProdInstlPos prodInstlPos = prodSV.loadProdInstlPos(ord.getProdNo());
            if (prodInstlPos != null) {
                model.addAttribute("pos", prodInstlPos.getProvName() + prodInstlPos.getCityName() + prodInstlPos.getDistName() + prodInstlPos.getAddr());
            }
            model.addAttribute("ord", ord);
        }
        return "grzx/order/mbrOrdDetail";
    }

    /**
     * 查看订单详情
     *
     * @param ordno 订单号
     */
    @RequestMapping("viewBizOrd")
    public String viewBizOrd(String ordno, Model model) {
        Ord ord = ordSV.loadByOrdNo(ordno);
        if (ord != null) {
            if (Std.PayWay.WX.getKey().equals(ord.getPayWay())) {
                OrdPayWx ordPayWx = ordSV.loadOrdPayWxByOrdNo(ordno);
                model.addAttribute("acct", ordPayWx.getAcct());
            } else if (Std.PayWay.WLT.getKey().equals(ord.getPayWay())) {
                OrdPayWlt ordPayWlt = ordSV.loadOrdPayWltByOrdNo(ordno);
                model.addAttribute("acct", ordPayWlt.getName());//修改时间：12.20修改人：sfn
                System.out.println("------------------------------------------------------会员名--------------------------------------------------------------" +ordPayWlt.getName());

            }
            ProdInstlPos prodInstlPos = prodSV.loadProdInstlPos(ord.getProdNo());
            if (prodInstlPos != null) {
                model.addAttribute("pos", prodInstlPos.getProvName() + prodInstlPos.getCityName() + prodInstlPos.getDistName() + prodInstlPos.getAddr());
            }
            model.addAttribute("ord", ord);
        }
        return "sjxx/sellerOrder/bizOrdDetail";
    }

}
