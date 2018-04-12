package com.zzwdkj.gprs.ctrl;

import com.zzwdkj.base.BaseCtrl;
import com.zzwdkj.biz.entity.Biz;
import com.zzwdkj.common.BaseConfig;
import com.zzwdkj.common.ExtMsg;
import com.zzwdkj.common.RedisUtil;
import com.zzwdkj.gprs.ChargeCmd;
import com.zzwdkj.gprs.entity.GprsModel;
import com.zzwdkj.gprs.service.GprsModelSV;
import com.zzwdkj.gprs.service.GprsOtpSV;
import com.zzwdkj.msg.service.MsgSV;
import com.zzwdkj.prod.entity.Prod;
import com.zzwdkj.prod.entity.ProdOnlLog;
import com.zzwdkj.prod.service.ProdSV;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/gprs/model")
public class GprsModelCtrl extends BaseCtrl {
    @Resource
    private ProdSV prodSV;
    @Resource
    private MsgSV msgSV;
    @Resource
    private GprsOtpSV gprsOtpSV;
    @Resource
    private GprsModelSV gprsModelSV;

    /**
     * 恢复出厂设置
     *
     * @param all 是否应用全部产品
     */
    @RequestMapping("resetModArgs")
    public ExtMsg restFactoryArgs(String cmd, String gprsNoList, Integer all, String arg, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        StringBuilder result = new StringBuilder("");
        if (all != null && all == 1) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.clear();
            params.put("bizNo", biz.getBizNo());
            List<Prod> prodList = prodSV.queryProdOnline(params, 0, 2000);
            if (prodList != null && !prodList.isEmpty()) {
                gprsNoList = "";
                for (Prod prod : prodList) {
                    gprsNoList = gprsNoList + prod.getGprsNo() + "_";
                }
                proc(cmd, gprsNoList, arg);
            }
        } else {
            if (gprsNoList != null) {
                proc(cmd, gprsNoList, arg);
            }
        }
        return ExtMsg.success("设置成功");
    }


    /**
     * 设置otp次数
     *
     * @param
     */
    @RequestMapping("restMovingFactor")
    public ExtMsg restMovingFactor(String gprsNoList, Long movingFactor, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        String allGprs = "_";
        if (!biz.isAdmin()) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("bizNo", biz.getBizNo());
            List<GprsModel> gprsModelList = gprsModelSV.queryGprsModel(params, -1, -1);
            for (GprsModel gprsModel : gprsModelList) {
                allGprs += gprsModel.getGprsNo() + "_";
            }
        }
        String[] strings = gprsNoList.split("_");
        for (int i = 0; i < strings.length; i++) {
            if (strings[i] != null && !strings[i].equals("")) {
                if (strings[i].startsWith("2") && biz.isAdmin()) {
                    gprsOtpSV.updateOtpMovingFactor(strings[i], movingFactor);
                } else if (strings[i].startsWith("2") && allGprs.indexOf("_" + strings[i] + "_") > -1) {
                    gprsOtpSV.updateOtpMovingFactor(strings[i], movingFactor);
                } else if (!strings[i].startsWith("2")) {
                    return ExtMsg.fail(strings[i] + "设备不是OTP设备!");
                } else {
                    return ExtMsg.fail("您没有权限重置" + strings[i] + "!");
                }
            }
        }
        return ExtMsg.success("设置成功");
    }

    @RequestMapping("senddCmd")
    public ExtMsg senddCmd(String cmd, String gprsNo, String arg, HttpServletRequest request) {
        Prod prod=prodSV.loadByGprsNo(gprsNo);
        ProdOnlLog prodOnlLog = prodSV.loadProdOnline(prod.getProdNo());
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        //&& prodOnlLog.getBizNo().equalsIgnoreCase(biz.getBizNo())
        if (prodOnlLog != null&& (prodOnlLog.getBizNo().equalsIgnoreCase(biz.getBizNo())|| biz.isAdmin()) ) {
            msgSV.sendCmd(cmd, gprsNo, arg);
            return ExtMsg.success("发送成功");
        } else {
            return ExtMsg.fail("无法获取设备在线状态");
        }
    }

    @RequestMapping("advancedCmd")
    public ExtMsg advancedCmd(String cmd, String gprsNo, String arg, HttpServletRequest request) {
        Prod prod=prodSV.loadByGprsNo(gprsNo);
        ProdOnlLog prodOnlLog = prodSV.loadProdOnline(prod.getProdNo());
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        if ("ydkj".equals(biz.getAcct())) {
            msgSV.sendServerCmd(cmd, gprsNo, arg);
            return ExtMsg.success("发送成功");
        } else {
            if (prodOnlLog != null && prodOnlLog.getBizNo().equalsIgnoreCase(biz.getBizNo())) {
                msgSV.sendServerCmd(cmd, gprsNo, arg);
                return ExtMsg.success("发送成功");
            } else {
                return ExtMsg.fail("无权限操作设备");
            }
        }

    }

    @RequestMapping("baseArgsResp")
    public Map<String, String> baseArgsResp(String cmd, String gprsNoList) {
        Map<String, String> result = new HashMap<String, String>();
        if (gprsNoList != null) {
            String gprsNo = gprsNoList.substring(gprsNoList.indexOf("_") + 1);
            String heart = RedisUtil.get(gprsNo + "WD11");
            result.put("heart", heart);
            String insertCoinWth = RedisUtil.get(gprsNo + "WD50");
            result.put("insertCoinWth", insertCoinWth);
            String insertCoinInterval = RedisUtil.get(gprsNo + "WD51");
            result.put("insertCoinInterval", insertCoinInterval);
            String totalTimePerCoin = RedisUtil.get(gprsNo + "WD61");
            result.put("totalTimePerCoin", totalTimePerCoin);
        }
        return result;
    }

    @RequestMapping("advanceArgsResp")
    public Map<String, String> advanceArgsResp(String gprsNo, String cmd) {
        Map<String, String> result = new HashMap<String, String>();
        if (gprsNo != null) {
            System.out.println("获得的存储参数是："+RedisUtil.get(gprsNo + cmd));
            String resp = RedisUtil.get(gprsNo + cmd);
            result.put("resp", resp);
        }
        return result;
    }

    public void proc(String cmd, String gprsNoList, String arg) {
        ChargeCmd command = ChargeCmd.valueOf(cmd);
        switch (command) {
            case WS11:
                msgSV.heartReset(gprsNoList, arg);
                break;
            case WS24:
                msgSV.resetDefault(gprsNoList);
                break;
            case WS25:
                msgSV.restart(gprsNoList);
                break;
            case WS50:
                msgSV.insertCoinWth(gprsNoList, arg);
                break;
            case WS51:
                msgSV.insertCoinInterval(gprsNoList, arg);
                break;
            case WS61:
                msgSV.insertTotalTimePerCoin(gprsNoList, arg);
                break;
            default:
                break;
        }
    }
}
