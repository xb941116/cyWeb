package com.zzwdkj.msg.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author guoxianwei 2016/9/23.
 */
public interface MsgSV {
    /**
     * 付款通知
     *
     * @param ordno 订单号
     * @param gprsNo 模块号
     * @param money  付款金额 单位分
     */
    void payNotice(String ordno,String gprsNo, String money);

    /**
     * 服务端向设备发送命令
     *
     * @param cmd    命令代码
     * @param gprsNo 模块号
     * @param arg    参数
     */
    void sendServerCmd(String cmd, String gprsNo, String arg);

    /**
     * 索要剩余金额
     *
     * @param gprsNo 模块号
     */
    void reqRemainMoney(String gprsNo);

    /**
     * 索要收入
     *
     * @param gprsNo 模块号
     */
    void reqEarn(String gprsNo);

    /**
     * 清除收入记录
     *
     * @param gprsNo 模块号
     */
    void clearEarnHis(String gprsNo);

    /**
     * 索要设定参数
     *
     * @param gprsNo 模块号
     */
    void reqProdArgs(String gprsNo);

    /**
     * 索要程序版本
     *
     * @param gprsNo 模块号
     */
    void reqProgramVersion(String gprsNo);

    /**
     * 时间设置
     *
     * @param params 参数
     *               gprsNo:00000000;datime:20160101080000
     */
    void restTime(Map<String, Object> params);

    /**
     * 参数设置
     *
     * @param params 参数
     *               gprsNo:00000000;params:
     */
    void restProdArgs(Map<String, Object> params);

    /**
     * 恢复出厂设置
     *
     * @param params 参数
     *               gprsNo:00000000;
     *               params: 05   恢复5元费率参数 ；6   恢复6元费率参数
     */
    void restFactoryArgs(Map<String, Object> params);

    /**
     * 索要故障状态命令
     *
     * @param gprsNo 模块号
     */
    void reqBugCmd(String gprsNo);

    /**
     * 心跳时间设置
     *
     * @param gprsNoList 模块号
     */
    void heartReset(String gprsNoList, String arg);

    /**
     * 恢复默认值
     *
     * @param gprsNoList 模块号
     */
    void resetDefault(String gprsNoList);

    /**
     * 重启模块
     *
     * @param gprsNoList 模块号
     */
    void restart(String gprsNoList);

    /**
     * 投币脉冲宽度
     *
     * @param gprsNoList 模块号
     */
    void insertCoinWth(String gprsNoList, String arg);

    /**
     * 投币脉冲间隔
     *
     * @param gprsNoList 模块号
     */
    void insertCoinInterval(String gprsNoList, String arg);

    /**
     * 单位投币脉冲对应继电器吸合时间
     *
     * @param gprsNoList 模块号
     * @param arg        参数
     */
    void insertTotalTimePerCoin(String gprsNoList, String arg);

    void sendCmd(String cmd, String gprsNo, String arg);
}
