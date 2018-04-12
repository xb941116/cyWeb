package com.zzwdkj.gprs.msg.service;

import java.util.Map;

/**
 * 消息接收服务
 *
 * @author guoxianwei 2015-01-07 15:28
 */
public interface MsgRecvSV {


    /**
     * 接收服务器命令（除去支付方面的所有接收均用这个方法处理）
     *
     * @param params 参数 cmd:AS02, gprsNo:00000000
     */
    void resvServerCmd(Map<String, Object> params);


    /**
     * 接收服务器高级命令
     *
     * @param params 参数
     */
    void resvServerAdvanceCmd(Map<String, Object> params);

    /**
     * 接收支付状态
     *
     * @param params 支付参数  gprsNo:00000000;money:6
     */
    void resvPaySt(Map<String, Object> params);

}
