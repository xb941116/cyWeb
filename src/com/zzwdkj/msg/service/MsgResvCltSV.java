package com.zzwdkj.msg.service;

import java.util.Map;

/**
 * @author guoxianwei 2016/9/29.
 */
public interface MsgResvCltSV {

    /**
     * 接收客户端响应命令（除去支付方面的所有接收均用这个方法处理）
     *
     * @param params 参数 cmd:AS02, gprsNo:00000000
     */
    void resvCltCmd(Map<String, Object> params);

    /**
     * 接收设备端响应支付状态
     *
     * @param params 支付参数  gprsNo:00000000;money:6
     */
    void resvCltPaySt(Map<String, Object> params);

}
