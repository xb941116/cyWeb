package com.zzwdkj.msg.service;

import com.zzwdkj.common.BaseConfig;
import com.zzwdkj.common.RandomUtil;
import com.zzwdkj.gprs.msg.service.MsgRecvSV;
import com.zzwdkj.gprs.msg.service.MsgRecvSVImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author guoxianwei 2016/9/23.
 */
@Service("msgSV")
public class MsgSVImpl implements MsgSV {

    private final static Logger LOGGER = Logger.getLogger(MsgSVImpl.class);
    @Resource
    private MsgRecvSV msgRecvSV;

    /**
     * 付款通知
     *
     * @param ordno  订单号
     * @param gprsNo 模块号
     * @param money  付款金额  单位分
     */
    @Override
    public void payNotice(String ordno, String gprsNo, String money) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ordno", ordno);
        params.put("gprsNo", gprsNo);
        params.put("money", money);
        LOGGER.debug("payNotice-------->" + params.toString());
        msgRecvSV.resvPaySt(params);
    }

    /**
     * 索要剩余金额
     *
     * @param gprsNo 模块号
     */
    @Override
    public void reqRemainMoney(String gprsNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("gprsNo", gprsNo);
        params.put("cmd", "AS02");
        msgRecvSV.resvServerCmd(params);

    }

    /**
     * 索要收入
     *
     * @param gprsNo 模块号
     */
    @Override
    public void reqEarn(String gprsNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        if (BaseConfig.isSelfProd(gprsNo)) {
            params.put("gprsNo", gprsNo);
            params.put("type", "0");
            params.put("cmd", "WS29");
        } else if (gprsNo.startsWith("3")) {
            params.put("gprsNo", gprsNo);
            params.put("cmd", "CY43");
        } else {
            params.put("gprsNo", gprsNo);
            params.put("type", "1");
            params.put("cmd", "AS03");
        }
        msgRecvSV.resvServerCmd(params);

    }

    /**
     * 清除收入记录
     *
     * @param gprsNo 模块号
     */
    @Override
    public void clearEarnHis(String gprsNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("gprsNo", gprsNo);
        params.put("type", "0");
        params.put("cmd", "AS03");
        msgRecvSV.resvServerCmd(params);

    }

    /**
     * 索要设定参数
     *
     * @param gprsNo 模块号
     */
    @Override
    public void reqProdArgs(String gprsNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("gprsNo", gprsNo);
        params.put("cmd", "AS04");
        msgRecvSV.resvServerCmd(params);

    }

    /**
     * 索要程序版本
     *
     * @param gprsNo 模块号
     */
    @Override
    public void reqProgramVersion(String gprsNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("gprsNo", gprsNo);
        params.put("cmd", "AS05");
        msgRecvSV.resvServerCmd(params);

    }

    /**
     * 时间设置
     *
     * @param params 参数
     *               gprsNo:00000000;datime:20160101080000
     */
    @Override
    public void restTime(Map<String, Object> params) {
        params.put("cmd", "AS06");
        msgRecvSV.resvServerCmd(params);

    }

    /**
     * 参数设置
     *
     * @param params 参数
     *               gprsNo:00000000;params: 010013007008011014000006006003013015005000010005020018030006000003001001
     */
    @Override
    public void restProdArgs(Map<String, Object> params) {
        params.put("cmd", "AS07");
        msgRecvSV.resvServerCmd(params);

    }

    /**
     * 恢复出厂设置
     *
     * @param params 参数
     *               gprsNo:00000000;
     *               params: 05   恢复5元费率参数 ；6   恢复6元费率参数
     */
    @Override
    public void restFactoryArgs(Map<String, Object> params) {
        params.put("cmd", "AS08");
        msgRecvSV.resvServerCmd(params);

    }

    /**
     * 索要故障状态命令
     *
     * @param gprsNo 模块号
     */
    @Override
    public void reqBugCmd(String gprsNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("gprsNo", gprsNo);
        params.put("cmd", "AS09");
        msgRecvSV.resvServerCmd(params);

    }

    /**
     * 心跳时间设置
     *
     * @param gprsNoList 模块号
     * @param arg
     */
    @Override
    public void heartReset(String gprsNoList, String arg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cmd", "WS11");
        params.put("gprsNoList", gprsNoList);
        if (StringUtils.isNotEmpty(arg)) {
            arg = StringUtils.leftPad(arg, 3, "0");
            arg = arg + RandomUtil.checkCode(arg);
        }
        params.put("arg", arg);
        msgRecvSV.resvServerCmd(params);

    }

    /**
     * 恢复默认值
     *
     * @param gprsNoList 模块号
     */
    @Override
    public void resetDefault(String gprsNoList) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cmd", "WS24");
        params.put("gprsNoList", gprsNoList);
        msgRecvSV.resvServerCmd(params);

    }

    /**
     * 重启模块
     *
     * @param gprsNoList 模块号
     */
    @Override
    public void restart(String gprsNoList) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cmd", "WS25");
        params.put("gprsNoList", gprsNoList);
        msgRecvSV.resvServerCmd(params);

    }

    /**
     * 投币脉冲宽度
     *
     * @param gprsNoList 模块号
     * @param arg
     */
    @Override
    public void insertCoinWth(String gprsNoList, String arg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cmd", "WS50");
        params.put("gprsNoList", gprsNoList);
        if (StringUtils.isNotEmpty(arg)) {
            arg = StringUtils.leftPad(arg, 3, "0");
            arg = arg + RandomUtil.checkCode(arg);
        }
        params.put("arg", arg);
        msgRecvSV.resvServerCmd(params);

    }

    /**
     * 投币脉冲间隔
     *
     * @param gprsNoList 模块号
     * @param arg
     */
    @Override
    public void insertCoinInterval(String gprsNoList, String arg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cmd", "WS51");
        params.put("gprsNoList", gprsNoList);
        if (StringUtils.isNotEmpty(arg)) {
            arg = StringUtils.leftPad(arg, 3, "0");
            arg = arg + RandomUtil.checkCode(arg);
        }
        params.put("arg", arg);
        msgRecvSV.resvServerCmd(params);

    }

    /**
     * 单位投币脉冲对应继电器吸合时间
     *
     * @param gprsNoList 模块号
     * @param arg        参数
     */
    @Override
    public void insertTotalTimePerCoin(String gprsNoList, String arg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cmd", "WS61");
        params.put("gprsNoList", gprsNoList);
        if (StringUtils.isNotEmpty(arg)) {
            int times = Integer.parseInt(arg);
            arg = StringUtils.leftPad(String.valueOf(times), 5, "0");
            arg = arg + RandomUtil.checkCode(arg);
        }
        params.put("arg", arg);
        msgRecvSV.resvServerCmd(params);

    }

    @Override
    public void sendCmd(String cmd, String gprsNo, String arg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cmd", cmd);
        params.put("gprsNo", gprsNo);
        params.put("arg", arg);
        msgRecvSV.resvServerCmd(params);


    }

    /**
     * 服务端向设备发送命令
     *
     * @param cmd    命令代码
     * @param gprsNo 模块号
     * @param arg    参数
     */
    @Override
    public void sendServerCmd(String cmd, String gprsNo, String arg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("gprsNo", gprsNo);
        params.put("args", arg);
        msgRecvSV.resvServerAdvanceCmd(params);
    }
}
