package com.zzwdkj.msg.service;

import com.zzwdkj.gprs.ChargeCmd;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author guoxianwei 2016/9/29.
 */

@Service("msgResvCltSV")
public class MsgResvCltSVImpl implements MsgResvCltSV {

    private final static Logger LOGGER = Logger.getLogger(MsgResvCltSVImpl.class);

    @Resource
    private CltRspProcSV cltRspProcSV;

    /**
     * 接收客户端响应命令（除去支付方面的所有接收均用这个方法处理）
     *
     * @param params 参数 cmd:BS02, gprsNo:00000000
     */
    @Override
    public void resvCltCmd(Map<String, Object> params) {
        String cmd = (String) params.get("cmd");
        try {
            ChargeCmd command = ChargeCmd.valueOf(cmd);
            switch (command) {
                case WD29:
                    reqEarnRslt(params);
                    break;
                case WD20:
                    reqProgramVersionRslt(params);
                    break;
                default:
                    LOGGER.warn("Unhandled command: " + cmd);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warn("Illegal argument", e);
        }
    }


    /**
     * 接收设备端响应支付状态
     *
     * @param params 支付参数  gprsNo:00000000;money:6
     */
    @Override
    public void resvCltPaySt(Map<String, Object> params) {
        LOGGER.warn("-------------resvCltPaySt------------------" + params.toString());
        String gprsNo = (String) params.get("gprsNo");
        String result = (String) params.get("result");
        Integer channel = (Integer) params.get("loop");
        String ordno = (String) params.get("ordno");
        Integer totalTime = (Integer) params.get("totalTime");
        cltRspProcSV.resvCltPaySt(ordno,gprsNo, result,channel,totalTime);

    }




    /**
     * 索要收入
     *
     * @param params 响应参数
     */
    protected void reqEarnRslt(Map<String, Object> params) {
        String gprsNo = (String) params.get("gprsNo");
        String result = (String) params.get("result");
        cltRspProcSV.reqEarnRslt(gprsNo, result);
    }


    /**
     * 索要程序版本
     *
     * @param params 响应参数
     */
    protected void reqProgramVersionRslt(Map<String, Object> params) {
        String gprsNo = (String) params.get("gprsNo");
        String result = (String) params.get("result");
        cltRspProcSV.reqProgramVersionRslt(gprsNo, result);
    }




}
