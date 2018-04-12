package com.zzwdkj.gprs.msg.service;

import com.alibaba.fastjson.JSON;
import com.zzwdkj.gprs.ChargeCmd;
import com.zzwdkj.gprs.SessionManager;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 接收消息服务
 *
 * @author guoxianwei 2015-01-07 15:35
 */
@Service("msgRecvSV")
public class MsgRecvSVImpl implements MsgRecvSV {

    private final static Logger LOGGER = Logger.getLogger(MsgRecvSVImpl.class);

    /**
     * 接收服务器命令（除去支付方面的所有接收均用这个方法处理）
     *
     * @param params 参数 cmd:AS02, gprsNo:00000000
     */
    @Override
    public void resvServerCmd(Map<String, Object> params) {
        String cmd = (String) params.get("cmd");
        try {
            ChargeCmd command = ChargeCmd.valueOf(cmd);
            String gprsNo = (String) params.get("gprsNo");
            String gprsNoList = (String) params.get("gprsNoList");
            String arg = (String) params.get("arg");
            LOGGER.info("-------------------resvServerCmd params -------------------:" + params.toString());
            switch (command) {
                case WS11:
                    heartReset(gprsNoList, arg);
                    break;
                case WS24:
                    resetDefault(gprsNoList);
                    break;
                case WS25:
                    restart(gprsNoList);
                    break;
                case WS29:
                    String type = (String) params.get("type");
                    reqEarn(gprsNo, type);
                    break;
                case WS50:
                    insertCoinWth(gprsNoList, arg);
                    break;
                case WS51:
                    insertCoinInterval(gprsNoList, arg);
                    break;
                case WS61:
                    insertTotalTimePerCoin(gprsNoList, arg);
                    break;
                case WS30:
                    doWS30(params);
                    break;
                case CY40:
                    doCY40(params);
                    break;
                case CY41:
                    doCY41(params);
                    break;
                case CY43:
                    doCY43(params);
                    break;
                case CY44:
                    doCY44(params);
                    break;
                case CY45:
                    doCY45(params);
                    break;
                default:
                    LOGGER.info("Unhandled command: " + command);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("Illegal argument", e);
        }
    }

    private void doCY44(Map<String, Object> params) {
        String gprsNo = (String) params.get("gprsNo");
        IoSession session = SessionManager.getSession(gprsNo);
        if (session != null && session.isConnected()) {
            StringBuilder sb = new StringBuilder();
            sb.append("2A44").
                    append("00006E23");
            LOGGER.info("doCY44 cmd: " + sb.toString());
            session.write(hexStringToBytes(sb.toString()));
        }
    }

    /**
     * 查询刷卡、投币和微信收入
     * @param params
     */
    private void doCY43(Map<String, Object> params) {
        String gprsNo = (String) params.get("gprsNo");
        IoSession session = SessionManager.getSession(gprsNo);
        if (session != null && session.isConnected()) {
            StringBuilder sb = new StringBuilder();
            sb.append("2A43").
                    append("00006D23");
            LOGGER.info("doCY43 cmd: " + sb.toString());
            session.write(hexStringToBytes(sb.toString()));
        }
    }

    /**
     * 查询大中小功率、最低功率、预付费金额、喇叭音量参数
     *
     * @param params 模块相关信息
     */
    private void doCY45(Map<String, Object> params) {
        String gprsNo = (String) params.get("gprsNo");
        IoSession session = SessionManager.getSession(gprsNo);
        if (session != null && session.isConnected()) {
            StringBuilder sb = new StringBuilder();
            sb.append("2A45").
                    append("00006F23");
            LOGGER.info("doCY45 cmd: " + sb.toString());
            session.write(hexStringToBytes(sb.toString()));
        }
    }

    /**
     * 设置大中小功率、最低功率、预付费金额、喇叭音量参数
     *
     * @param params 设置参数
     */
    private void doCY41(Map<String, Object> params) {
        Map<String, Object>  argsMap = new HashMap<String,Object>();
        argsMap = (Map<String, Object>) JSON.parse((String)params.get("arg"));
        String gprsNo = (String) argsMap.get("gprsNo");
        String largePowerTime = (String) argsMap.get("largePowerTime");
        String middenPowerTime = (String) argsMap.get("middenPowerTime");
        String smallPowerTime = (String) argsMap.get("smallPowerTime");
        String limitPower = (String) argsMap.get("limitPower");
        String preMoney = (String) argsMap.get("preMoney");
        String voice = (String) argsMap.get("voice");
        String length = (String) argsMap.get("length");
        String checkBit = (String) argsMap.get("checkBit");

        IoSession session = SessionManager.getSession(gprsNo);
        if (session != null && session.isConnected()) {
            StringBuilder sb = new StringBuilder();
            sb.append("2A41").
                    append(length).
                    append(largePowerTime).
                    append(middenPowerTime).
                    append(smallPowerTime).
                    append(limitPower).
                    append(preMoney).
                    append(voice).
                    append(checkBit).
                    append("23");
            LOGGER.info("doCY41 cmd: " + sb.toString());
            session.write(hexStringToBytes(sb.toString()));
        }
    }

    private void doCY40(Map<String, Object> params) {
        String gprsNo = (String) params.get("gprsNo");
        IoSession session = SessionManager.getSession(gprsNo);
        if (session != null && session.isConnected()) {
            StringBuilder sb = new StringBuilder("2A4000006A23");
            LOGGER.info("doCY40 cmd: " + sb.toString());
            session.write(hexStringToBytes(sb.toString()));
        }
    }

    private void doWS30(Map<String, Object> params) {
        String gprsNo = (String) params.get("gprsNo");
        IoSession session = SessionManager.getSession(gprsNo);
        if (session != null && session.isConnected()) {
            StringBuilder sb = new StringBuilder("{");
            sb.append(gprsNo)
                    .append("WS30").
                    append("}");
            LOGGER.info("doWS30 cmd: " + sb.toString());
            session.write(sb.toString());
        }
    }


    /**
     * 接收服务器高级命令
     *
     * @param params 参数
     */
    @Override
    public void resvServerAdvanceCmd(Map<String, Object> params) {
        LOGGER.info("-------------------resvServerAdvanceCmd params -------------------:" + params.toString());
        String gprsNo = (String) params.get("gprsNo");
        String args = (String) params.get("args");
        IoSession session = SessionManager.getSession(gprsNo);
        if (session != null) {
            LOGGER.info("高级命令，报文：" + args);
            session.write(args);
        }
    }

    /**
     * 接收支付状态
     *
     * @param params 支付参数  gprsNo:00000000;money:6
     */
    @Override
    public void resvPaySt(Map<String, Object> params) {
        LOGGER.info("-------------------resvPaySt params -------------------:" + params.toString());
        String gprsNo = (String) params.get("gprsNo");
        String money = (String) params.get("money");
        if (gprsNo.startsWith("3")) {
            doCY42(gprsNo,money);
        } else {
            doAS01(gprsNo, money);
        }
    }

    /**
     *
     * @param gprsNo
     * @param money
     */
    public void doCY42(String gprsNo,String money) {
        //获取设备对应的消息通道
        IoSession session = SessionManager.getSession(gprsNo);
        if (session != null && session.isConnected()) {
            String relmoney = Integer.toHexString(Integer.parseInt(money.substring(0,money.length()-1)));
            relmoney=StringUtils.leftPad(relmoney,4,"0");
            String length = shiToHex(String.valueOf(relmoney.length() / 2));
            String checkBit = add("2A" + "42" + length + relmoney);
            System.out.println(length + "=" + relmoney + "=" + checkBit);
            StringBuilder sb = new StringBuilder("2A").append("42").append(length).append(relmoney).append(checkBit).append("23");
            LOGGER.info("doCY42 cmd: " + sb.toString());
            session.write(hexStringToBytes(sb.toString()));
        }
    }


    /**
     * 索要收入
     *
     * @param gprsNo 模块号
     * @param type   操作类型 0：总收入清零  1：索要总收入
     */
    protected void reqEarn(String gprsNo, String type) {
        if (isSelfProd(gprsNo)) {
            doWS29(gprsNo, type);
        } else {
            doAS03(gprsNo, type);
        }
    }


    /**
     * 响应支付查询
     *
     * @param gprsNo 模块号
     * @param money  交易金额
     */
    protected void doAS01(String gprsNo, String money) {
        IoSession session = SessionManager.getSession(gprsNo);
        if (session != null && session.isConnected()) {
            if (StringUtils.isNotEmpty(money)) {
                int m = Integer.parseInt(money);
                if (m <= 0) {
                    return;
                }
            }
            StringBuilder sb = new StringBuilder("");
            sb.append(gprsNo).
                    append(isSelfProd(gprsNo) ? "WS28" : "AS01").
                    append(money);
            LOGGER.info("doAS01 cmd:" + procArgs(sb.toString()));
            session.write(procArgs(sb.toString()));
        }
    }


    /**
     * 索要收入
     *
     * @param gprsNo 模块号
     * @param type   操作类型 1：总收入清零
     */
    protected void doWS29(String gprsNo, String type) {
        IoSession session = SessionManager.getSession(gprsNo);
        if (session != null && session.isConnected()) {
            StringBuilder sb = new StringBuilder("{");
            if (type != null && "1".equals(type)) {
                sb.append(gprsNo).
                        append("WS29").
                        append(type).
                        append(type).
                        append("}");
            } else {
                sb.append(gprsNo).
                        append("WS29").
                        append("}");
            }
            LOGGER.info("WS29 cmd :" + sb.toString());
            session.write(sb.toString());
        }
    }

    /**
     * 索要收入
     *
     * @param gprsNo 模块号
     * @param type   操作类型 0：总收入清零  1：索要总收入
     */
    protected void doAS03(String gprsNo, String type) {
        IoSession session = SessionManager.getSession(gprsNo);
        if (session != null && session.isConnected()) {
            StringBuilder sb = new StringBuilder("[");
            sb.append(gprsNo).
                    append("AS03").
                    append(type).
                    append(type).
                    append("]");
            LOGGER.info("doAS03 cmd :" + sb.toString());
            session.write(sb.toString());
        }
    }


    /**
     * 心跳时间设置
     *
     * @param gprsNoList 模块号
     * @param arg
     */
    public void heartReset(String gprsNoList, String arg) {
        if (gprsNoList != null) {
            String[] gprsNoArray = gprsNoList.split("_");
            if (gprsNoArray.length > 0) {
                for (String gprsNo : gprsNoArray) {
                    if (StringUtils.isNotEmpty(gprsNo)) {
                        IoSession session = SessionManager.getSession(gprsNo);
                        if (session != null && session.isConnected()) {
                            StringBuilder sb = new StringBuilder("{");
                            sb.append(gprsNo).
                                    append("WS11").
                                    append(arg).
                                    append("}");
                            LOGGER.info("heartReset cmd :" + sb.toString());
                            session.write(sb.toString());
                        }
                    }
                }
            }
        }
    }

    /**
     * 恢复默认值
     *
     * @param gprsNoList 模块号
     */
    public void resetDefault(String gprsNoList) {
        if (gprsNoList != null) {
            String[] gprsNoArray = gprsNoList.split("_");
            if (gprsNoArray.length > 0) {
                for (String gprsNo : gprsNoArray) {
                    if (StringUtils.isNotEmpty(gprsNo)) {
                        IoSession session = SessionManager.getSession(gprsNo);
                        if (session != null && session.isConnected()) {
                            StringBuilder sb = new StringBuilder("{");
                            sb.append(gprsNo).
                                    append("WS24").
                                    append("}");
                            LOGGER.info("resetDefault ,cmd :" + sb.toString());
                            session.write(sb.toString());
                        }
                    }
                }
            }
        }
    }

    /**
     * 重启模块
     *
     * @param gprsNoList 模块号
     */
    public void restart(String gprsNoList) {
        if (gprsNoList != null) {
            String[] gprsNoArray = gprsNoList.split("_");
            if (gprsNoArray.length > 0) {
                for (String gprsNo : gprsNoArray) {
                    if (StringUtils.isNotEmpty(gprsNo)) {
                        IoSession session = SessionManager.getSession(gprsNo);
                        if (session != null && session.isConnected()) {
                            LOGGER.info("restart");
                            StringBuilder sb = new StringBuilder("{");
                            sb.append(gprsNo).
                                    append("WS25").
                                    append("}");
                            LOGGER.info("restart, cmd" + sb.toString());
                            session.write(sb.toString());
                        }
                    }
                }
            }
        }
    }

    /**
     * 投币脉冲宽度
     *
     * @param gprsNoList 模块号
     * @param arg
     */
    public void insertCoinWth(String gprsNoList, String arg) {
        if (gprsNoList != null) {
            String[] gprsNoArray = gprsNoList.split("_");
            if (gprsNoArray.length > 0) {
                for (String gprsNo : gprsNoArray) {
                    if (StringUtils.isNotEmpty(gprsNo)) {
                        IoSession session = SessionManager.getSession(gprsNo);
                        if (session != null && session.isConnected()) {
                            StringBuilder sb = new StringBuilder("{");
                            sb.append(gprsNo).
                                    append("WS50").
                                    append(arg).
                                    append("}");
                            LOGGER.info("insertCoinWth cmd :" + sb.toString());
                            session.write(sb.toString());
                        }
                    }
                }
            }
        }
    }

    /**
     * 投币脉冲间隔
     *
     * @param gprsNoList 模块号
     * @param arg
     */
    public void insertCoinInterval(String gprsNoList, String arg) {
        if (gprsNoList != null) {
            String[] gprsNoArray = gprsNoList.split("_");
            if (gprsNoArray.length > 0) {
                for (String gprsNo : gprsNoArray) {
                    if (StringUtils.isNotEmpty(gprsNo)) {
                        IoSession session = SessionManager.getSession(gprsNo);
                        if (session != null && session.isConnected()) {
                            StringBuilder sb = new StringBuilder("{");
                            sb.append(gprsNo).
                                    append("WS51").
                                    append(arg).
                                    append("}");
                            LOGGER.info("insertCoinInterval, cmd :" + sb.toString());
                            session.write(sb.toString());
                        }
                    }
                }
            }
        }
    }

    /**
     * 单位投币脉冲对应继电器吸合时间
     *
     * @param gprsNoList 模块号
     * @param arg        参数
     */
    public void insertTotalTimePerCoin(String gprsNoList, String arg) {
        if (gprsNoList != null) {
            String[] gprsNoArray = gprsNoList.split("_");
            if (gprsNoArray.length > 0) {
                for (String gprsNo : gprsNoArray) {
                    if (StringUtils.isNotEmpty(gprsNo)) {
                        IoSession session = SessionManager.getSession(gprsNo);
                        if (session != null && session.isConnected()) {
                            StringBuilder sb = new StringBuilder("{");
                            sb.append(gprsNo).
                                    append("WS61").
                                    append(arg).
                                    append("}");
                            LOGGER.info("insertTotalTimePerCoin, cmd :" + sb.toString());
                            session.write(sb.toString());
                        }
                    }
                }
            }
        }
    }

    public static String procArgs(String args) {
        if (isSelfProd(args)) {
            return "{" + args + "}";
        } else {
            return "[" + args + "]";
        }
    }

    public static boolean isSelfProd(String gprsNo) {
        boolean isSelf = false;
        if (gprsNo != null && gprsNo.startsWith("0")) {
            isSelf = true;
        }
        return isSelf;
    }

    public static String checkCode(String args) {
        byte[] bys = args.getBytes();
        int checkBit = 0;
        for (int i = 0; i < bys.length; i++) {
            int c = (int) bys[i];
            if (c >= 48) {
                checkBit += c - 48;

            } else if (c < 48) {
                checkBit += (256 - (48 - c));
            }
            if (checkBit > 255) {
                checkBit -= 256;
            }
        }
        checkBit = checkBit % 10;
        return String.valueOf(checkBit);
    }

    /**
     * 二进制字符串转字节数组
     *
     * @param hexString 二进制字符串
     * @return 字节数组
     */
    public static byte[] hexStringToBytes(String hexString) {
        hexString = hexString.replaceAll(" ", ""); // 去空格
        if ((hexString == null) || (hexString.equals(""))) {
            return null;
        }
        hexString = hexString.toUpperCase(); // 字符串中的所有字母都被转化为大写字母
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; ++i) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[(pos + 1)]));
        }
        return d;
    }

    /**
     * char转字节数
     *
     * @param c char
     * @return 字节数
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
    /**
     * @param s 要转化的十进制数据
     * @return 标准十六进制数据
     */
    public String shiToHex(String s) {
        String hexString = Integer.toHexString(Integer.parseInt(s));
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
            return hexString;
        }
        return hexString;
    }

    /**
     * 得到校验字
     *
     * @param s 二进制字符串
     * @return 校验字
     */
    public String add(String s) {
        byte[] bytes = hexStringToBytes(s);
        char c = 0;
        for (byte b : bytes) {
            c += b;
        }
        return Integer.toHexString(c);
    }
}
