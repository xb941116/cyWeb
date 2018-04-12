package com.zzwdkj.gprs.ctrl;

import com.zzwdkj.msg.service.MsgSV;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/gprs/test")
public class GprsTestCtrl {

    @Resource
    private MsgSV msgSV;

    @RequestMapping("payNotice")
    public void payNotice(String ordno,String gprsNo, String money) {
        msgSV.payNotice(ordno,gprsNo, money);
    }

    /**
     * 索要剩余金额
     *
     * @param gprsNo 模块号
     */
    @RequestMapping("reqRemainMoney")
    public void reqRemainMoney(String gprsNo) {
        msgSV.reqRemainMoney(gprsNo);
    }

    /**
     * 索要收入
     *
     * @param gprsNo 模块号
     */
    @RequestMapping("reqEarn")
    public void reqEarn(String gprsNo) {
        msgSV.reqEarn(gprsNo);
    }

    /**
     * 清除收入记录
     *
     * @param gprsNo 模块号
     */
    @RequestMapping("clearEarnHis")
    public void clearEarnHis(String gprsNo) {
        msgSV.clearEarnHis(gprsNo);
    }

    /**
     * 索要设定参数
     *
     * @param gprsNo 模块号
     */
    @RequestMapping("reqProdArgs")
    public void reqProdArgs(String gprsNo) {
        msgSV.reqProdArgs(gprsNo);
    }

    /**
     * 索要程序版本
     *
     * @param gprsNo 模块号
     */
    @RequestMapping("reqProgramVersion")
    public void reqProgramVersion(String gprsNo) {
        msgSV.reqProgramVersion(gprsNo);
    }

    /**
     * 时间设置
     *
     * @param datetime 参数datetime : 20160927214500
     */
    @RequestMapping("restTime")
    public void restTime(String gprsNo, String datetime) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("gprsNo", gprsNo);
        params.put("dateTime", datetime);
        msgSV.restTime(params);
    }

    /**
     * 参数设置
     *
     * @param args 参数
     *             gprsNo:00000000;args:010013007008011014000006006003013015005000010005020018030006000003001001
     */
    @RequestMapping("restProdArgs")
    public void restProdArgs(String gprsNo, String args) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("gprsNo", gprsNo);
        params.put("args", args);
        msgSV.restProdArgs(params);
    }

    /**
     * 恢复出厂设置
     *
     * @param arg 参数
     *            gprsNo:00000000;
     *            params: 05   恢复5元费率参数 ；6   恢复6元费率参数
     */
    @RequestMapping("restFactoryArgs")
    public void restFactoryArgs(String gprsNo, String arg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("gprsNo", gprsNo);
        params.put("arg", arg);
        msgSV.restFactoryArgs(params);
    }

    /**
     * 索要故障状态命令
     *
     * @param gprsNo 模块号
     */
    @RequestMapping("reqBugCmd")
    public void reqBugCmd(String gprsNo) {
        msgSV.reqBugCmd(gprsNo);
    }

}
