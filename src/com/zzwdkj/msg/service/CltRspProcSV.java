package com.zzwdkj.msg.service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author guoxianwei 2016/9/29.
 */
public interface CltRspProcSV {
    /**
     * 接收设备端响应支付状态
     *
     * @param ordno 订单号
     * @param gprsNo 模块号
     * @param result 洗车结果
     * @param channel 通道（充电回路）
     * @param totalTime 时间
     */
    void resvCltPaySt(String ordno,String gprsNo, String result,Integer channel,Integer totalTime);

    /**
     * 索要剩余金额
     *
     * @param gprsNo 模块号
     * @param result 剩余金额
     */
    void reqRemainMoneyRslt(String gprsNo, String result);

    /**
     * 索要收入
     *
     * @param gprsNo 模块号
     * @param result 收入
     */
    void reqEarnRslt(String gprsNo, String result);
    /**
     * 索要收入
     *
     * @param gprsNo 模块号
     * @param cardMoney 刷卡收入
     * @param TBMoney 投币收入
     */
    void reqEarnRsltHS02(String gprsNo, BigDecimal cardMoney,BigDecimal TBMoney);

    /**
     *
     * 清除收入记录
     *
     * @param gprsNo 模块号
     * @param result 收入
     */
    void clearEarnHisRslt(String gprsNo, String result);

    /**
     * 索要设定参数
     *
     * @param gprsNo 模块号
     * @param args   参数
     */
    void reqProdArgsRslt(String gprsNo, String args);

    /**
     * 索要程序版本
     *
     * @param gprsNo  模块号
     * @param version 版本号
     */
    void reqProgramVersionRslt(String gprsNo, String version);

    /**
     * 时间设置
     *
     * @param gprsNo 模块号
     * @param result 设置后时间
     */
    void restTimeRslt(String gprsNo, String result);

    /**
     * 参数设置
     *
     * @param gprsNo 模块号
     */
    void restProdArgsRslt(String gprsNo);

    /**
     * 恢复出厂设置
     *
     * @param gprsNo 模块号
     */
    void restFactoryArgsRslt(String gprsNo);

    /**
     * 索要故障状态命令
     *
     * @param gprsNo 模块号
     * @param result 结果 abcde 0 正常，1故障 (a：使用期限到    b：缺水    c：缺洗车液    d：缺玻璃水    e：低温) ；
     */
    void reqBugCmdRslt(String gprsNo, String result);

    /**
     * 设备掉线
     * @param gprsNo 模块号
     */
    void resvGprsOutLine(String gprsNo);
}
