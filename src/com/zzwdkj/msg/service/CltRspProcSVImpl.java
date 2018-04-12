package com.zzwdkj.msg.service;

import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.biz.dao.BizWltDAO;
import com.zzwdkj.biz.entity.BizWx;
import com.zzwdkj.biz.service.BizWxSV;
import com.zzwdkj.common.BaseConfig;
import com.zzwdkj.common.Std;
import com.zzwdkj.gprs.dao.GprsModelDAO;
import com.zzwdkj.gprs.entity.GprsModel;
import com.zzwdkj.ord.dao.OrdDAO;
import com.zzwdkj.ord.entity.Ord;
import com.zzwdkj.prod.dao.*;
import com.zzwdkj.prod.entity.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guoxianwei 2016/9/29.
 */
@Service("cltRspProcSV")
public class CltRspProcSVImpl implements CltRspProcSV {

    private final static Logger LOGGER = Logger.getLogger(CltRspProcSVImpl.class);

    public static final int MAX_VAL_COMMON = 9000;
    public static final int MAX_VAL_SP = 900;

    @Resource
    private OrdDAO ordDAO;
    @Resource
    private BizWltDAO bizWltDAO;
    @Resource
    private BizWxSV bizWxSV;
    @Resource
    private ProdGprsBindDAO prodGprsBindDAO;
    @Resource
    private ProdDAO prodDAO;
    @Resource
    private ProdCoinRptDAO prodCoinRptDAO;
    @Resource
    private ProdSpArgsDAO prodSpArgsDAO;
    @Resource
    private GprsModelDAO gprsModelDAO;
    @Resource
    private ProdBugRptDAO prodBugRptDAO;
    @Resource
    private ProdOnlLogDAO prodOnlLogDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
     * 接收设备端响应支付状态
     *
     * @param ordno     订单号
     * @param gprsNo    模块号
     * @param result    洗车结果
     * @param channel   通道（充电回路）
     * @param totalTime 时间
     */
    @Override
    public void resvCltPaySt(String ordno, String gprsNo, String result, Integer channel, Integer totalTime) {
       /* result
        0：系统故障，支付失败
        1：正在使用，不能支付（投币或刷卡方式付费）
        2：支付成功（清零也回复该指令）
        */
        if ("0".equals(result)) {
            updateOrdSt(ordno, gprsNo, 3, 0, channel, totalTime);
        } else if ("1".equals(result)) {
            updateOrdSt(ordno, gprsNo, 3, 0, channel, totalTime);
        } else if ("2".equals(result)) {
            updateOrdSt(ordno, gprsNo, 2, 1, channel, totalTime);
        }
    }

    /**
     * 更新订单状态
     *
     * @param ordno   订单号
     * @param gprsNo  模块号
     * @param state   状态
     * @param succ    交易是否成功
     * @param channel 通道（充电回路）
     */
    private void updateOrdSt(String ordno, String gprsNo, Integer state, Integer succ, Integer channel, Integer totalTime) {
        LOGGER.debug("更新订单状态: gprsNo = " + gprsNo + " state=" + state + " succ=" + succ);
        Ord ord = ordDAO.queryUnique("queryOrdLasted", gprsNo);
        if (ord != null) {
            //获取用户是否绑定了微信公众号
            if (state.intValue() == 2 && succ.intValue() == 1) {//设备响应正常
                BizWx bizWx = bizWxSV.loadByBizNo(ord.getBizNo());
                if (ord.getState() == 2 && ord.getSucc() == 1) {//订单已经处理过。
                    //订单已经处理过，不进行加钱（防止重复回复进行处理）。
                } else if (bizWx == null || bizWx.getId() == null) {//没有绑定微信公众号，则增加可提现金额
                    Map<String, Object> params_tk = new HashMap<String, Object>();
                    params_tk.put("bizNo", ord.getBizNo());
                    params_tk.put("take", ord.getProdPrice());
                    bizWltDAO.update("updateBizWltTakeAdd", params_tk);
                }
            }

            Map<String, Object> params = new HashMap<String, Object>();
            if (ordno != null) {
                params.put("ordno", ordno);
            } else {
                params.put("ordno", ord.getOrdno());
            }
            params.put("state", state);
            params.put("succ", succ);
            params.put("channel", channel);
            params.put("totalTime", totalTime);
            ordDAO.update("updateOrdSt", params);

        }
    }

    /**
     * 索要剩余金额
     *
     * @param gprsNo 模块号
     */
    @Override
    public void reqRemainMoneyRslt(String gprsNo, String result) {
        LOGGER.debug("索要剩余金额返回结果: gprsNo = " + gprsNo + " result=" + result);
    }


    /**
     * 索要收入
     *
     * @param gprsNo 模块号
     * @param result 结果
     */
    @Override
    public void reqEarnRslt(String gprsNo, String result) {
        LOGGER.debug("索要收入返回结果: gprsNo = " + gprsNo + " result=" + result);
        //电子投币，只能获取投币收入
        if (BaseConfig.isSelfProd(gprsNo)) {
            if (gprsNo != null && !"".equals(gprsNo) && result != null) {
                Prod prod = prodDAO.queryUnique("loadByGprsNo", gprsNo);
                if (prod != null) {
                    BigDecimal coin_inc = BigDecimal.valueOf(Long.parseLong((result)));
                    ProdCoinRpt prodCoinRpt = getProdCoinRpt(coin_inc, prod, Std.PayWay.COIN, MAX_VAL_COMMON);
                    prodCoinRpt.setId(identifierSV.uniqueId());
                    prodCoinRpt.setDevType(prod.getType());
                    prodCoinRpt.setTotalMoney(coin_inc);
                    prodCoinRptDAO.insert(prodCoinRpt);
                }
            }
            //洗车机收入，投币，刷卡，网络收入
        } else {
            if (gprsNo != null && !"".equals(gprsNo) && result != null && result.length() == 12) {
                Prod prod = this.prodDAO.queryUnique("loadByGprsNo", gprsNo);
                if (prod != null) {
                    BigDecimal coin_inc = BigDecimal.valueOf(Long.parseLong(result.substring(0, 4)));
                    BigDecimal card_inc = BigDecimal.valueOf(Long.parseLong(result.substring(4, 8)));
                    BigDecimal net_inc = BigDecimal.valueOf(Long.parseLong(result.substring(8, 12)));
                    ProdCoinRpt prodCoinRpt = getProdCoinRpt(coin_inc, prod, Std.PayWay.COIN, MAX_VAL_COMMON);
                    prodCoinRpt.setId(this.identifierSV.uniqueId());
                    prodCoinRpt.setDevType(prod.getType());
                    prodCoinRpt.setTotalMoney(coin_inc);
                    this.prodCoinRptDAO.insert(prodCoinRpt);
                    prodCoinRpt = getProdCoinRpt(card_inc, prod, Std.PayWay.CARD, MAX_VAL_COMMON);
                    prodCoinRpt.setId(this.identifierSV.uniqueId());
                    prodCoinRpt.setDevType(prod.getType());
                    prodCoinRpt.setTotalMoney(card_inc);
                    this.prodCoinRptDAO.insert(prodCoinRpt);
                    prodCoinRpt = getProdCoinRpt(net_inc, prod, Std.PayWay.BANK, MAX_VAL_COMMON);
                    prodCoinRpt.setId(this.identifierSV.uniqueId());
                    prodCoinRpt.setDevType(prod.getType());
                    prodCoinRpt.setTotalMoney(net_inc);
                    this.prodCoinRptDAO.insert(prodCoinRpt);
                }
            }
        }

    }

    /**
     * @param current_inc 本次上报收入
     * @param prod        商品
     * @param payWay      支付方式
     * @param maxVal      最大限制，超过在ProdCoinRpt记录上标记IsErrorRpt=1
     * @return 待保存的ProdCoinRpt
     */
    private ProdCoinRpt getProdCoinRpt(BigDecimal current_inc, Prod prod, Std.PayWay payWay, int maxVal) {
        ProdCoinRpt prodCoinRpt;
        if (current_inc.compareTo(BigDecimal.ZERO) == 1) {
            BigDecimal lastedRptMoney = getLastedRptMoney(prod.getBizNo(), prod.getProdNo(), payWay);
            //上次收入小于等于本次收入时，取相差值入库
            if (lastedRptMoney.compareTo(current_inc) < 1) {
                BigDecimal current_day_money = current_inc.subtract(lastedRptMoney);
                prodCoinRpt = new ProdCoinRpt(prod.getBizNo(), prod.getProdNo(), payWay.getKey(), current_day_money);
                if (isErrorRpt(lastedRptMoney.intValue(), current_inc.intValue(), maxVal)) {
                    prodCoinRpt.setIsErrorRpt(1);
                }
            } else {
                //直接入库
                prodCoinRpt = new ProdCoinRpt(prod.getBizNo(), prod.getProdNo(), payWay.getKey(), current_inc);
            }
        } else {
            prodCoinRpt = new ProdCoinRpt(prod.getBizNo(), prod.getProdNo(), payWay.getKey(), BigDecimal.ZERO);
        }
        return prodCoinRpt;
    }

    private boolean isErrorRpt(int lastedRptMoney, int currentRptMoney, int maxVal) {
        if (lastedRptMoney >= maxVal && currentRptMoney >= maxVal) {
            return true;
        }
        return false;
    }

    private BigDecimal getLastedRptMoney(String bizNo, String prodNo, Std.PayWay payWay) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("prodNo", prodNo);
        params.put("payWay", payWay.getKey());
        params.put("bizNo", bizNo);
        ProdCoinRpt lastedRpt = prodCoinRptDAO.queryUnique("loadLastedProdCoinRpt", params);
        if (lastedRpt != null) {
            return lastedRpt.getTotalMoney() == null ? BigDecimal.ZERO : lastedRpt.getTotalMoney();
        } else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 索要收入
     *
     * @param gprsNo   模块号
     * @param card_inc 刷卡收入
     * @param coin_inc 投币收入
     */
    @Override
    public void reqEarnRsltHS02(String gprsNo, BigDecimal card_inc, BigDecimal coin_inc) {
        LOGGER.debug("索要收入返回结果: gprsNo = " + gprsNo + "刷卡=" + card_inc + "投币" + coin_inc);
        ProdCoinRpt prodCoinRpt;
        Prod prod = this.prodDAO.queryUnique("loadByGprsNo", gprsNo);
        if (prod != null) {
            prodCoinRpt = getProdCoinRpt(card_inc, prod, Std.PayWay.CARD, MAX_VAL_SP);
            prodCoinRpt.setId(this.identifierSV.uniqueId());
            prodCoinRpt.setDevType(prod.getType());
            prodCoinRpt.setTotalMoney(card_inc);
            this.prodCoinRptDAO.insert(prodCoinRpt);

            prodCoinRpt = getProdCoinRpt(coin_inc, prod, Std.PayWay.COIN, MAX_VAL_SP);
            prodCoinRpt.setId(this.identifierSV.uniqueId());
            prodCoinRpt.setDevType(prod.getType());
            prodCoinRpt.setTotalMoney(coin_inc);
            this.prodCoinRptDAO.insert(prodCoinRpt);
        }
    }

    /**
     * 清除收入记录
     *
     * @param gprsNo 模块号
     * @param result 收入
     */
    @Override
    public void clearEarnHisRslt(String gprsNo, String result) {
        LOGGER.debug("清除收入记录返回结果: gprsNo = " + gprsNo + " result=" + result);
    }

    /**
     * 索要设定参数
     *
     * @param gprsNo 模块号
     * @param args   参数
     */
    @Override
    public void reqProdArgsRslt(String gprsNo, String args) {
        LOGGER.debug("索要设定参数返回结果: gprsNo = " + gprsNo + " args=" + args);
        if (StringUtils.isNotEmpty(gprsNo) && StringUtils.isNotEmpty(args)) {
            int length = args.length();
            if (length % 3 == 0) {
                Prod prod = prodDAO.queryUnique("loadByGprsNo", gprsNo);
                prodSpArgsDAO.delete("deleteProdSpArgs", prod.getProdNo());
                int idx = 1;
                for (int i = 0; i < length; i = i + 3) {
                    String arg = String.valueOf(Integer.parseInt(StringUtils.mid(args, i, 3)));
                    ProdSpArgs.ArgsEnum argsEnum = ProdSpArgs.ArgsEnum.valueOf(idx);
                    ProdSpArgs prodSpArgs = new ProdSpArgs(prod.getBizNo(), prod.getProdNo(), argsEnum.getDesc(), argsEnum.name(), argsEnum.getType(), argsEnum.getUnit(), arg, argsEnum.getMaxVal(), argsEnum.getMinVal(), idx);
                    prodSpArgs.setId(identifierSV.uniqueId());
                    prodSpArgsDAO.insert(prodSpArgs);
                    idx++;
                }
            }
        }
    }

    /**
     * 索要程序版本
     *
     * @param gprsNo 模块号
     */
    @Override
    public void reqProgramVersionRslt(String gprsNo, String version) {
        LOGGER.debug("索要程序版本返回结果: gprsNo = " + gprsNo + " version=" + version);

        if (gprsNo != null && !"".equals(gprsNo)) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("gprsNo", gprsNo);
            params.put("version", version);
            gprsModelDAO.update("updateGprsModelVersion", params);

            Prod prod = prodDAO.queryUnique("loadByGprsNo", gprsNo);
            if (prod != null) {
                params.put("prodNo", prod.getProdNo());
                params.put("bizNo", prod.getBizNo());
                ProdOnlLog prodOnlLog = prodOnlLogDAO.queryUnique("loadProdOnlLog", params);
                if (prodOnlLog != null) {
                    ProdOnlLog prodOnlLog2 = new ProdOnlLog();
                    prodOnlLog2.setId(prodOnlLog.getId());
                    prodOnlLog2.setState(1);
                    prodOnlLog2.setCurOnlineTime(new Date());
                    prodOnlLog2.setUptime(new Date());
                    prodOnlLogDAO.update(prodOnlLog2);
                } else {
                    prodOnlLog = new ProdOnlLog(prod.getBizNo(), prod.getProdNo(), prod.getGprsNo());
                    prodOnlLog.setId(identifierSV.uniqueId());
                    prodOnlLogDAO.insert(prodOnlLog);
                }
            }
        }

    }

    /**
     * 时间设置
     *
     * @param gprsNo 模块号
     * @param result 设置后时间
     */
    @Override
    public void restTimeRslt(String gprsNo, String result) {
        LOGGER.debug("时间设置返回结果: gprsNo = " + gprsNo + " datetime=" + result);

    }

    /**
     * 参数设置
     *
     * @param gprsNo 模块号
     */
    @Override
    public void restProdArgsRslt(String gprsNo) {
        LOGGER.debug("参数设置返回结果: gprsNo = " + gprsNo);

    }

    /**
     * 恢复出厂设置
     *
     * @param gprsNo 模块号
     */
    @Override
    public void restFactoryArgsRslt(String gprsNo) {
        LOGGER.debug("恢复出厂设置返回结果: gprsNo = " + gprsNo);

    }

    /**
     * 索要故障状态命令
     *
     * @param gprsNo 模块号
     * @param result 结果 abcde 0 正常，1故障 (a：使用期限到    b：缺水    c：缺洗车液    d：缺玻璃水    e：低温) ；
     */
    @Override
    public void reqBugCmdRslt(String gprsNo, String result) {
        LOGGER.debug("索要故障状态命令返回结果: gprsNo = " + gprsNo + " result=" + result);
        Prod prodGprsBind = prodDAO.queryUnique("loadByGprsNo", gprsNo);
        prodBugRptDAO.delete("deleteProdBugRpt", prodGprsBind.getProdNo());
        ProdBugRpt bugRpt = new ProdBugRpt(prodGprsBind.getBizNo(), prodGprsBind.getProdNo(), result);
        bugRpt.setId(identifierSV.uniqueId());
        prodBugRptDAO.insert(bugRpt);
    }

    /**
     * 设备掉线
     *
     * @param gprsNo 模块号
     */
    @Override
    public void resvGprsOutLine(String gprsNo) {
        Prod prodGprsBind = prodDAO.queryUnique("loadByGprsNo", gprsNo);
        if (prodGprsBind != null) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("gprsNo", gprsNo);
            prodOnlLogDAO.update("updateProdOutLine", params);
        }
    }
}
