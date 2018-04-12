package com.zzwdkj.task.ctrl;


import com.zzwdkj.biz.entity.BizTake;
import com.zzwdkj.biz.entity.BizWx;
import com.zzwdkj.biz.service.BizTakeSV;
import com.zzwdkj.biz.service.BizWxSV;
import com.zzwdkj.common.BaseConfig;
import com.zzwdkj.common.Std;
import com.zzwdkj.gprs.msg.service.MsgRecvSV;
import com.zzwdkj.ord.entity.Ord;
import com.zzwdkj.ord.service.OrdSV;
import com.zzwdkj.prod.entity.ProdOnlLog;
import com.zzwdkj.prod.service.ProdSV;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xizhuangchui on 2016/11/21.
 */

@Component
public class TransfersTaskCtrl {
    @Resource
    private BizTakeSV bizTakeSV;
    @Resource
    private OrdSV ordSV;
    @Resource
    private ProdSV prodSV;
    @Resource
    private BizWxSV bizWxSV;
    @Resource
    private MsgRecvSV msgRecvSV;

    private final static Logger LOGGER = Logger.getLogger(TransfersTaskCtrl.class);

    /**
     * 自动转账
     */
    public void transfersTask() {
        LOGGER.info("---------------开始自动转账-------------");
        BigDecimal checkMoney = new BigDecimal(20);
        //自动转账
        ordSV.updateAllTransfersOrd();//开始转账，更改状态
        List<Map<String, Object>> takeOrdList = ordSV.queryTakeOrd();
        if (takeOrdList == null || takeOrdList.size() < 1) {
            return;
        }
        for (int i = 0; i < takeOrdList.size(); i++) {
            try {
                Map<String, Object> params = new HashMap<String, Object>();
                String bizNo = String.valueOf(takeOrdList.get(i).get("bizNo"));//收款人
                String resvBizNo = String.valueOf(takeOrdList.get(i).get("resvBizNo"));//打款人
                String bizNum = String.valueOf(takeOrdList.get(i).get("bizNum"));//打款商户号
                String openid = String.valueOf(takeOrdList.get(i).get("openid"));//收款人openid
                String nick = String.valueOf(takeOrdList.get(i).get("nick"));//收款人微信昵称
                String money = String.valueOf(takeOrdList.get(i).get("money"));//金额
                LOGGER.info("---------------准备转账:bizNo:" + bizNo + ",resvBizNo" + resvBizNo + ",money" + money + ",bizNum" + bizNum + ",openid" + openid + ",nick" + nick);
                if (bizNo.equals("null") || resvBizNo.equals("null") || bizNum.equals("null") ||
                        openid.equals("null") || nick.equals("null") || money.equals("null")) {

                    LOGGER.debug("---------------参数有为空的，不进行转账！");
                    continue;
                }

                params.put("bizNo", bizNo);
                List<BizTake> bizTakeList = bizTakeSV.query(params, -1, -1);
                BigDecimal errorMoney = BigDecimal.ZERO;
                for (BizTake bizTake : bizTakeList) {
                    if (bizTake.getState() == Std.TakeSt.SUCCESS.getKey()) {
                        break;
                    } else if (bizTake.getState() == Std.TakeSt.FAIL.getKey()) {
                        errorMoney = errorMoney.add(bizTake.getAmount());
                        break;
                    }
                }
                LOGGER.info("--------------查询" + bizNo + "失败总金额：" + errorMoney);
                Ord ord = ordSV.loadStaDailyTotalIncWithPerPayWayTransfers(null, bizNo);
                LOGGER.info("--------------查询" + bizNo + "昨日营收总金额：" + ord.getWxInc());
                BigDecimal allMoney = errorMoney.add(ord.getWxInc());
                LOGGER.info("--------------查询" + bizNo + "转账总金额：" + money);
                /*if (allMoney.compareTo(new BigDecimal(money)) == -1) {
                    //continue;
                    LOGGER.debug("--------------转账金额大于昨日营收金额则不进行转账");
                    /*if ((new BigDecimal(money).subtract(allMoney)).compareTo(checkMoney)==-1){
                        LOGGER.info("--------------转账金额减去昨日营收金额和转账失败金额小于20则进行转账！");
                        bizTakeSV.createTransfersTask( t 33akeOrdList,i);
                    }
                } else {
                    bizTakeSV.createTransfersTask(takeOrdList, i);
                }*/
                if (checkMoney.compareTo(new BigDecimal(money)) == -1) {
                    bizTakeSV.createTransfersTask(takeOrdList, i);
                    LOGGER.debug("--------------转账成功");
                }else{
                    LOGGER.debug("--------------转账金额小于20不进行转账");
                }


            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.debug(e.getMessage());
            }
        }
        LOGGER.info("---------------完成自动转账-------------");
    }

    /**
     * 钱包支付退款
     */
    public void refundTaskWallet() {
        LOGGER.info("---------------钱包退款开始-------------");
        Map<String, Object> params_ord_wallet = new HashMap<String, Object>();
        params_ord_wallet.put("payWay", Std.PayWay.WLT.getKey());
        List<Map<String, Object>> ordList_wallet = ordSV.queryRefundOrd(params_ord_wallet);//ordList_wallet就是要退钱的集合值
        for (int i = 0; i < ordList_wallet.size(); i++) {
            try {
                ordSV.createRefundTaskWallet(ordList_wallet, i);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.debug(e.getMessage());
            }
            //ordSV.createRefundTask_wx();
        }

        LOGGER.info("---------------积分退还完成-------------");
        LOGGER.info("---------------钱包退款完成-------------");

    }



    /**
     * 积分支付退款

    public void refundTaskCoin() {
        LOGGER.info("---------------积分退还开始-------------");
        Map<String, Object> params_ord_coin = new HashMap<String, Object>();
        params_ord_coin.put("payWay", Std.PayWay.COINS.getKey());
        List<Map<String, Object>> ordList_coin = ordSV.queryRefundOrd(params_ord_coin);
        for (int i = 0; i < ordList_coin.size(); i++) {
            try {
                ordSV.createRefundTaskCoins(ordList_coin, i);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.debug(e.getMessage());
            }
            //ordSV.createRefundTask_wx();
        }

        LOGGER.info("---------------积分退还完成-------------");
    }
     */

    /**
     * 微信支付退款
     */
    public void refundTaskWx() {
        LOGGER.info("---------------微信退款开始-------------");
        Map<String, Object> params_ord_wx = new HashMap<String, Object>();
        params_ord_wx.put("payWay", Std.PayWay.WX.getKey());
        List<Map<String, Object>> ordList_wx = ordSV.queryRefundOrd(params_ord_wx);
        for (int i = 0; i < ordList_wx.size(); i++) {
            //ordSV.createRefundTask_wallet();
            try {
                ordSV.createRefundTaskWx(ordList_wx, i);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.debug(e.getMessage());
            }
        }

        LOGGER.info("---------------微信退款完成-------------");
    }


    public void uploadData() {
        List<ProdOnlLog> prodOnlLogList = prodSV.queryAllOnlineProd();
        if (prodOnlLogList != null && !prodOnlLogList.isEmpty()) {
            for (ProdOnlLog prodOnlLog : prodOnlLogList) {
                reqEarn(prodOnlLog.getGpno());
            }
        }
    }

    public void reqEarn(String gprsNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        if (BaseConfig.isSelfProd(gprsNo)) {
            params.put("gprsNo", gprsNo);
            params.put("type", "0");
            params.put("cmd", "WS29");
        } else if (gprsNo.startsWith("3")) {
            params.put("gprsNo", gprsNo);
            params.put("cmd", "CY43");
        }
        msgRecvSV.resvServerCmd(params);

    }
}
