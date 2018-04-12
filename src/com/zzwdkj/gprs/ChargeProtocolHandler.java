package com.zzwdkj.gprs;

import com.zzwdkj.common.RedisUtil;
import com.zzwdkj.gprs.msg.service.MsgRecvSVImpl;
import com.zzwdkj.msg.service.CltRspProcSV;
import com.zzwdkj.msg.service.CltRspProcSVImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * GPRS模块和服务器充电模块核心处理
 *
 * @author guoxianwei 2015-08-17 12:13
 */
public class ChargeProtocolHandler extends IoHandlerAdapter {

    private final static Logger LOGGER = Logger.getLogger(ChargeProtocolHandler.class);

    @Resource
    private CltRspProcSV cltRspProcSV;

   /* CltRspProcSVImpl cltRspProcSVImpl = (CltRspProcSVImpl) SpringContextUtil.getBean("cltRspProcSV");*/

    /**
     * 要求：每条命令都以回车换行符结束
     */
    @Override
    public void messageReceived(IoSession session, Object message) {
        //session.write(hexStringToBytes("1111"));
        String theMessage = "";
        if (message instanceof String) {
            theMessage = (String) message;
        } else {
            try {
                CharsetDecoder cd = Charset.forName("UTF-8").newDecoder();
                theMessage = ((IoBuffer) message).getString(cd);
            } catch (CharacterCodingException e) {
                e.printStackTrace();
            }
        }

        LOGGER.info("received: " + message);
        if (theMessage != null) {
            theMessage = theMessage.replaceAll("\\{", "[").replaceAll("}", "]").replaceAll("$", "").replaceAll("\\[", "");
            String[] theMessages = theMessage.split("]");
            if (theMessages.length >= 1) {
                for (String aMessage : theMessages) {
                    if (aMessage.length() < 12) {
                        continue;
                    }
                    String theCommand = "";
                    if (aMessage.startsWith("2A") || aMessage.startsWith("2a")) {
                        aMessage = session.getAttribute("gprsNo").toString() + aMessage.substring(0, 2) + "CYR" + aMessage.substring(2);
                        theCommand = aMessage.substring(10, 15);
                        System.out.println("昌原命令：" + theCommand);
                    } else {
                        theCommand = aMessage.substring(8, 12);
                    }
                    try {
                        ChargeCmd command = ChargeCmd.valueOf(theCommand);
                        switch (command) {
                            case WD20:
                                procWD20(session, aMessage);
                                break;
                            case WD28:
                                procBS01(aMessage);
                                break;
                            case WD29:
                                procWD29(session, aMessage);
                                break;
                            case WD11:
                                procWD11(aMessage);
                                break;
                            case WD50:
                                procWD50(aMessage);
                                break;
                            case WD51:
                                procWD51(aMessage);
                                break;
                            case WD61:
                                procW61(aMessage);
                                break;
                            case WD302:
                                WD302(session, aMessage);
                                break;
                            case CYR40:
                                CYR40(session, aMessage);
                                break;
                            case CYR41:
                                CYR41(session, aMessage);
                                break;
                            case CYR42:
                                CYR42(session, aMessage);
                                break;
                            case CYR43:
                                CYR43(session, aMessage);
                                break;
                            case CYR44:
                                CYR44(session, aMessage);
                                break;
                            case CYR45:
                                CYR45(session, aMessage);
                            default:
                                LOGGER.info("Unhandled command: " + command);
                                procDefault(aMessage);
                                break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        LOGGER.debug("Illegal argument", e);
                    }
                }
            }
        }
    }

    private void CYR45(IoSession session, String aMessage) {
        String gprsNo = aMessage.substring(0, 8);
        String command = aMessage.substring(10, 15);
        String largePowerTime = getDeviceIdByHex(aMessage.substring(17, 21));
        String middenPowerTime = getDeviceIdByHex(aMessage.substring(21, 25));
        String smallPowerTime = getDeviceIdByHex(aMessage.substring(25, 29));
        String limitPower = getDeviceIdByHex(aMessage.substring(29, 31));
        String preMoney = getDeviceIdByHex(aMessage.substring(31, 35));
        String voice = getDeviceIdByHex(aMessage.substring(35, 37));
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cmd", command);
        params.put("gprsNo", gprsNo);
        params.put("largePowerTime", largePowerTime);
        params.put("middenPowerTime", middenPowerTime);
        params.put("smallPowerTime", smallPowerTime);
        params.put("limitPower", limitPower);
        params.put("preMoney", preMoney);
        params.put("voice", voice);
        String msg = (largePowerTime + "_" + middenPowerTime + "_" + smallPowerTime + "_" + limitPower + "_" + preMoney + "_" + voice);
        RedisUtil.setex(gprsNo + command, 30, msg);
    }

    private void CYR44(IoSession session, String aMessage) {
        String gprsNo = aMessage.substring(0, 8);
        String command = aMessage.substring(10, 15);
        //获取10路机的工作状态
        String message = aMessage.substring(17, 21);
        String runState = hexString2binaryString(message);
        String[] state = runState.split("");
        state = reverseArray(state);
        Map<String, String> map = new HashMap<String, String>();
        Map<String, String> countMap = new HashMap<String, String>();
        Map<String, String> residueMap = new HashMap<String, String>();
        int i, j = 1;
        for (i = 1; i < 11; i++) {
            if ("1".equals(state[i - 1]) && i <= 10) {
                map.put("第" + i + "路", "正在工作");
                //获取预付费或计次状态
                String preMoneyOrPreCount = hexString2binaryString(aMessage.substring(21, 25));
                String[] moneyOrCOunt = runState.split("");
                moneyOrCOunt = reverseArray(moneyOrCOunt);
                for (j = 1; j < 11; j++) {
                    if ("正在工作".equals(map.get("第"+j+"路"))) {
                        if ("1".equals(moneyOrCOunt[j])) {
                            countMap.put("第" + j + "路", "预付费");
                            //获取第j路的金额
                            residueMap.put("第" + j + "路现在金额", getDeviceIdByHex(aMessage.substring(4 * j + 21, 4 * j + 21 + 4)));
                        } else {
                            countMap.put("第" + j + "路", "计次");
                            residueMap.put("第" + j + "路剩余时间", getDeviceIdByHex(aMessage.substring(4 * j + 21, 4 * j + 21 + 4)));
                        }
                    }
                }
            } else {
                if (i <= 10) {
                    map.put("第" + i + "路", "空闲");
                }
            }
        }
        String[] args = new String[11];
        for (String key : map.keySet()) {
            System.out.println("key= " + key + " and value= " + map.get(key));
        }
        for (String key : countMap.keySet()) {
            System.out.println("countKey= " + key + " and value= " + countMap.get(key));
        }
        for (String key : residueMap.keySet()) {
            System.out.println("residueKey= " + key + " and value= " + residueMap.get(key));
        }
        for (i = j = 1; i < 11; i++, j++) {
            if (i == j) {
                if ("预付费".equals(countMap.get("第" + j + "路"))) {
                    args[i] = "工作状态是：" + map.get("第" + i + "路") + ",  " + "工作方式是：" + countMap.get("第" + j + "路") + ",  " + "剩余金额是：" + residueMap.get("第" + j + "路现在金额");
                } else if ("计次".equals(countMap.get("第" + j + "路"))) {
                    args[i] = "工作状态是：" + map.get("第" + i + "路") + ",  " + "工作方式是：" + countMap.get("第" + j + "路") + ",  " + "剩余时间是：" + residueMap.get("第" + j + "路剩余时间");
                } else {
                    args[i] = "空闲状态";
                }
            }
        }
        RedisUtil.setex(gprsNo + command, 30, args[1] + "_" + args[2] + "_" + args[3] + "_" + args[4] + "_" + args[5] + "_" + args[6] + "_" + args[7] + "_" + args[8] + "_" + args[9] + "_" + args[10]);
    }

    private void CYR43(IoSession session, String aMessage) {
        LOGGER.info("proc version CYR43 resp :" + aMessage);
        try {
            String gprsNo = aMessage.substring(0, 8);
            String command = aMessage.substring(10, 15);
            String cardMoney = String.valueOf(Double.parseDouble(getDeviceIdByHex(aMessage.substring(17, 23))) / 100.0);
            String tbMoney = String.valueOf(Double.parseDouble(getDeviceIdByHex(aMessage.substring(23, 29))) / 100.0);
            String wxMoney = String.valueOf(Double.parseDouble(getDeviceIdByHex(aMessage.substring(29, 35))) / 100.0);
            String msg = cardMoney + "_" + tbMoney + "_" + wxMoney;
            RedisUtil.setex(gprsNo + command, 30, cardMoney + "_" + tbMoney + "_" + wxMoney);
            cltRspProcSV.reqEarnRsltHS02(gprsNo, new BigDecimal(cardMoney), new BigDecimal(tbMoney));
            if (Double.parseDouble(cardMoney) > 160000 || Double.parseDouble(tbMoney) > 160000 || Double.parseDouble(wxMoney) > 160000) {
                StringBuilder sb = new StringBuilder("[");
                sb.append("2A46").
                        append("00007023");
                System.out.println("清除统计命令-------------------" + sb);
                session.write(hexStringToBytes(sb.toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("proc version CYR43 resp error :" + e.getMessage());
        }
    }

    private void CYR42(IoSession session, String aMessage) {
        LOGGER.info("proc version CYR42 resp :" + aMessage);
        try {
            String gprsNo = aMessage.substring(0, 8);
            String command = aMessage.substring(10, 15);
            String state = "";
            if ("2a42004f4b23".equals(aMessage.substring(8, 10) + aMessage.substring(13))) {
                state = "2";
            } else if ("2a4200455223".equals(aMessage.substring(8, 10) + aMessage.substring(13))) {
                state = "0";
            }
            RedisUtil.setex(gprsNo + command, 30, aMessage.substring(15, aMessage.length()));
            cltRspProcSV.resvCltPaySt(null, gprsNo, state, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("proc version CYR42 resp error :" + e.getMessage());
        }
    }

    private void CYR41(IoSession session, String aMessage) {
        LOGGER.info("proc version CYR41 resp :" + aMessage);
        try {
            String gprsNo = aMessage.substring(0, 8);
            String command = aMessage.substring(10, 15);
            String result = "";
            if ("2a41004f4b23".equals(aMessage.substring(8, 10) + aMessage.substring(13))) {
                result = "参数设置成功！";
            } else {
                result = "参数设置失败";
            }
            RedisUtil.setex(gprsNo + command, 30, result);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("proc version CYR41 resp error :" + e.getMessage());
        }
    }

    private void CYR40(IoSession session, String aMessage) {
        LOGGER.info("proc version CYR40 resp :" + aMessage);
        try {

            String gprsNo = aMessage.substring(0, 8);
            String command = aMessage.substring(10, 15);
            String message = aMessage.substring(8, 10) + aMessage.substring(13, aMessage.length() - 4);
            String loop = aMessage.substring(17, 19);
            String returnCheckBit = aMessage.substring(aMessage.length() - 4, aMessage.length() - 2);
            String checkBit = add(message);
            if (returnCheckBit.equals(checkBit)) {
                if ("01".equals(loop)) {
                    loop = "10路机";
                }

                RedisUtil.setex(gprsNo + command, 30, loop);
                System.out.println("初始获得的：" + RedisUtil.get(gprsNo + command));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("proc version CYR40 resp error :" + e.getMessage());
        }
    }

    private void WD302(IoSession session, String aMessage) {
        LOGGER.info("proc version WD302 resp :" + aMessage);
        String gprsNo = aMessage.substring(0, 8);
        String command = aMessage.substring(8, 13);
        String loop = aMessage.substring(13, 18);
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("cmd", command);
            params.put("gprsNo", gprsNo);
            params.put("time", loop);
            RedisUtil.setex(gprsNo + command, 30, loop);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("proc version WD302 resp error:" + e.getMessage());
        }
    }


    private void procWD20(IoSession session, String message) {
        LOGGER.info("proc version WD20 resp :" + message);
        String command = message.substring(8, 12);
        String gprsNo = message.substring(0, 8);
        String result = message.substring(12, 17);
        SessionManager.putSession(gprsNo, session);
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("cmd", command);
            params.put("gprsNo", gprsNo);
            params.put("result", result);
            cltRspProcSV.reqProgramVersionRslt(gprsNo, result);

            RedisUtil.setex(gprsNo + command, 30, message.substring(12, message.length()));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("proc version BS05 resp error:" + e.getMessage());
        }
    }

    private void procDefault(String message) {
        LOGGER.info("proc advance cmd:" + message);
        if (message.length() >= 12) {
            String command = message.substring(8, 12);
            String gprsNo = message.substring(0, 8);
            LOGGER.info("proc advance cmd args cmd=" + command + "gprsNo=" + gprsNo);
            RedisUtil.setex(gprsNo + "ADCMD", 30, message);
        }
    }

    private void procWD11(String message) {
        LOGGER.info("proc heart cmd:" + message);
        String command = message.substring(8, 12);
        String gprsNo = message.substring(0, 8);
        String result = message.substring(12, 15);
        RedisUtil.setex(gprsNo + command, 30, String.valueOf(Integer.valueOf(result)));
        LOGGER.info("---------WD11 value------" + RedisUtil.get(gprsNo + command));
    }

    private void procWD50(String message) {
        LOGGER.info("proc insert coin wth cmd:" + message);
        String command = message.substring(8, 12);
        String gprsNo = message.substring(0, 8);
        String result = message.substring(12, 15);
        RedisUtil.setex(gprsNo + command, 30, String.valueOf(Integer.valueOf(result)));
        LOGGER.info("---------WD50 value------" + RedisUtil.get(gprsNo + command));
    }

    private void procW61(String message) {
        LOGGER.info("proc insert totalTimesPerCoin wth cmd:" + message);
        String command = message.substring(8, 12);
        String gprsNo = message.substring(0, 8);
        String result = message.substring(12, 17);
        RedisUtil.setex(gprsNo + command, 30, String.valueOf(Integer.valueOf(result)));
        LOGGER.info("---------WD61 value------" + RedisUtil.get(gprsNo + command));
    }

    private void procWD51(String message) {
        LOGGER.info("proc insert coin interval cmd:" + message);
        String command = message.substring(8, 12);
        String gprsNo = message.substring(0, 8);
        String result = message.substring(12, 15);
        RedisUtil.setex(gprsNo + command, 30, String.valueOf(Integer.valueOf(result)));
        LOGGER.info("---------WD51 gprsNo------" + gprsNo + ":" + command);
        LOGGER.info("---------WD51 value------" + RedisUtil.get(gprsNo + command));
    }

    /**
     * 支付响应  0：系统故障，支付失败
     * 1：正在使用，不能支付（投币或刷卡方式付费)
     * 2：支付成功（清零也回复该指令)
     */
    public void procBS01(String message) {
        LOGGER.info("proc pay resp:" + message);
        String command = message.substring(8, 12);
        String gprsNo = message.substring(0, 8);
        String result = message.substring(12, 13);
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("cmd", command);
            params.put("gprsNo", gprsNo);
            String r = "";
            if (MsgRecvSVImpl.isSelfProd(gprsNo)) {
                if ("0".equals(result)) {
                    r = "1";
                } else if ("1".equals(result)) {
                    r = "2";
                }
                params.put("result", r);
            } else {
                params.put("result", result);
            }
            cltRspProcSV.resvCltPaySt(null, gprsNo, result, null, null);
            RedisUtil.setex(gprsNo + command, 30, message.substring(12, message.length()));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("proc pay resp error :" + e.getMessage());
        }
    }

    /**
     * 索要回应剩余金额
     */
    public void procBS02(String message) {
        LOGGER.info("proc remain money resp:" + message);
        String command = message.substring(8, 12);
        String gprsNo = message.substring(0, 8);
        String result = message.substring(12, 16);
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("cmd", command);
            params.put("gprsNo", gprsNo);
            params.put("result", result);
            cltRspProcSV.reqRemainMoneyRslt(gprsNo, result);
            RedisUtil.setex(gprsNo + command, 30, message.substring(12, message.length()));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("proc remain money resp :" + e.getMessage());
        }
    }

    /**
     * 索要收入
     */
    public void procWD29(IoSession session, String message) {
        LOGGER.info("proc earn WD29 resp:" + message);
        String command = message.substring(8, 12);
        String gprsNo = message.substring(0, 8);
        String result = message.substring(12, 17);
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("cmd", command);
            params.put("gprsNo", gprsNo);
            params.put("result", result);
            cltRspProcSV.reqEarnRslt(gprsNo, result);
            int coin_inc = Integer.parseInt(result);
            if (coin_inc >= 9000) {
                StringBuilder sb = new StringBuilder("{");
                sb.append(gprsNo).
                        append("WS2911").
                        append("}");
                session.write(sb.toString());
            }
            RedisUtil.setex(gprsNo + command, 30, message.substring(12, message.length()));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("proc earn BS03 resp error :" + e.getMessage());
        }
    }


    /**
     * 索要设定参数
     *
     * @param message
     */
    public void procBS04(String message) {
        LOGGER.info("proc prod args BS04 resp :" + message);
        String command = message.substring(8, 12);
        String gprsNo = message.substring(0, 8);
        String result = message.substring(12, message.length() - 1);
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("cmd", command);
            params.put("gprsNo", gprsNo);
            params.put("result", result);
            cltRspProcSV.reqProdArgsRslt(gprsNo, result);
            RedisUtil.setex(gprsNo + command, 30, message.substring(12, message.length()));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("proc prod args BS04 resp error :" + e.getMessage());
        }
    }


    /**
     * 索要程序版本
     *
     * @param message [00012001BS0511002]
     */
    public void procBS05(IoSession session, String message) {
        LOGGER.info("proc version BS05 resp :" + message);
        String command = message.substring(8, 12);
        String gprsNo = message.substring(0, 8);
        String result = message.substring(12, 16);
        SessionManager.putSession(gprsNo, session);
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("cmd", command);
            params.put("gprsNo", gprsNo);
            params.put("result", result);
            cltRspProcSV.reqProgramVersionRslt(gprsNo, result);
            RedisUtil.setex(gprsNo + command, 30, message.substring(12, message.length()));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("proc version BS05 resp error:" + e.getMessage());
        }
    }

    /**
     * 时间设置
     *
     * @param message [00012001BS06201601010800009]
     */
    public void procBS06(String message) {
        LOGGER.info("proc time set BS06 resp :" + message);
//        String command = message.substring(8, 12);
//        String gprsNo = message.substring(0, 8);
//        String result = message.substring(12, 26);
    }

    /**
     * 参数设置
     *
     * @param message
     */
    public void procBS07(String message) {
        LOGGER.info("心跳包，报文" + message);
//        String command = message.substring(8, 12);
//        String gprsNo = message.substring(0, 8);
    }

    /**
     * 恢复出厂设置
     *
     * @param message
     */
    public void procBS08(String message) {
        LOGGER.info("proc reset BS08 resp :" + message);
//        String command = message.substring(8, 12);
//        String gprsNo = message.substring(0, 8);
    }

    /**
     * 索要故障状状态
     *
     * @param message
     */
    public void procBS09(String message) {
        LOGGER.info("proc bug rpt BS09 resp :" + message);
        String command = message.substring(8, 12);
        String gprsNo = message.substring(0, 8);
        String result = message.substring(12, 17);
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("cmd", command);
            params.put("gprsNo", gprsNo);
            params.put("result", result);
            cltRspProcSV.reqBugCmdRslt(gprsNo, result);
            RedisUtil.setex(gprsNo + command, 30, message.substring(12, message.length()));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("proc bug rpt BS09 resp error :" + e.getMessage());
        }
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.printf("-----------------------1----------------------");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.printf("-----------------------2----------------------");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        LOGGER.info("  -   session idle  - " + status.toString());
        if (session != null) {
            session.close(true);
        }

    }

    /**
     * 索要程序版本
     *
     * @param gprsNo 模块
     */
    protected void doAS05(IoSession session, String gprsNo) {
        if (session != null && session.isConnected()) {
            StringBuilder sb = new StringBuilder("[");
            sb.append(gprsNo).
                    append("AS05").
                    append("]");
            LOGGER.info("do get version AS05 cmd:" + sb.toString());
            session.write(sb.toString());
        }
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        Object gprsNo = session.getAttribute("gprsNo");
        LOGGER.info("session closed");
        if (gprsNo != null) {
            IoSession session1 = SessionManager.getSession(gprsNo.toString());
            if (session.getId() == session1.getId()) {
                try {
                    HashMap e = new HashMap();
                    e.put("cmd", "BS97");
                    e.put("gprsNo", gprsNo);
                    cltRspProcSV.resvGprsOutLine((String) gprsNo);
                    LOGGER.info("session " + gprsNo + " closed; message sended");
                } catch (Exception var5) {
                    var5.printStackTrace();
                    LOGGER.info("session closed error :" + var5.getMessage());
                }

                LOGGER.info("session " + gprsNo + " closed");
                SessionManager.removeSession(gprsNo.toString());
            } else {
                LOGGER.info("old session connected state is : " + session1.isConnected());
                LOGGER.info("old session closing state is : " + session1.isClosing());
                LOGGER.info("old session bothidle state is : " + session1.isBothIdle());
                LOGGER.info("new session connected state is : " + session.isConnected());
                LOGGER.info("new session closing state is : " + session.isClosing());
                LOGGER.info("new session bothidle state is : " + session.isBothIdle());
            }
        }
        session.close(true);

    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) {
        LOGGER.error("exceptionCaught.", cause);
        Object gprsNo = session.getAttribute("gprsNo");
        if (gprsNo != null) {
            LOGGER.info("session " + gprsNo + " exception");
        }
//        try {
//            Map<String, Object> params = new HashMap<String, Object>();
//            params.put("cmd", "BS97");
//            params.put("gprsNo", gprsNo);
//        } catch (Exception e) {
//            e.printStackTrace();
//            LOGGER.info("连接关闭发�?�发生异常：" + e.getMessage());
//        }
//        if (gprsNo != null)
//            SessionManager.removeSession(gprsNo.toString());
//        session.close(true);
    }

    /**
     * 把字节转化成字符串
     *
     * @param b 要转化的字节
     * @return 转化后的字符串
     */
    public static String toHex(byte b) {
        String result = Integer.toHexString(b & 0xFF);
        if (result.length() == 1) {
            result = '0' + result;
        }
        return result;
    }

    public static String StringToHex(String aMessage) {
        byte[] result = aMessage.getBytes();
        StringBuilder sb = new StringBuilder();
        for (byte b : result) {
            sb.append(toHex(b));
            System.out.println(sb.toString());
        }
        return sb.toString();
    }

    public static String hexString(String str) {
        String ret = "";
        byte[] b;
        try {
            b = str.getBytes("GB2312");
            for (int i = b.length - 1; i >= 0; i--) {
                String hex = Integer.toHexString(b[i] & 0xFF);
                if (hex.length() == 1) {
                    hex = '0' + hex;
                }
                ret += hex.toUpperCase();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ret;
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
     * 十六进制字符串转为10进制
     *
     * @param hex
     * @return
     */
    public static String getDeviceIdByHex(String hex) {
        String s = String.valueOf(Long.parseLong(hex, 16));
        if (s.equals("0")) {
            s = "0";
        }
        return s;
    }


    /**
     * 十六进制字符串转为二进制字符串
     *
     * @param hexString 十六进制字符串
     * @return 二进制字符串
     */
    public static String hexString2binaryString(String hexString) {
        if (hexString == null || hexString.length() % 2 != 0)
            return null;
        String bString = "", tmp;
        for (int i = 0; i < hexString.length(); i++) {
            tmp = "0000"
                    + Integer.toBinaryString(Integer.parseInt(hexString
                    .substring(i, i + 1), 16));
            bString += tmp.substring(tmp.length() - 4);
        }
        return bString;
    }


    /*
     *
     * 实现 数组翻转
     * 例如：{'a','b','c','d'}变成{'d','c','b','a'}
     */
    private static String[] reverseArray(String[] Array) {
        ArrayList<String> array_list = new ArrayList<String>();
        for (int i = 0; i < Array.length; i++) {
            array_list.add(Array[Array.length - i - 1]);
        }
        Array = array_list.toArray(Array);
        return Array;
    }

    /**
     * ASCII码字符串转数字字符串
     *
     * @param content ASCII字符串
     * @return 字符串
     */
    public static String AsciiStringToString(String content) {
        String result = "";
        int length = content.length() / 2;
        for (int i = 0; i < length; i++) {
            String c = content.substring(i * 2, i * 2 + 2);
            int a = hexStringToAlgorism(c);
            char b = (char) a;
            String d = String.valueOf(b);
            result += d;
        }
        return result;
    }

    /**
     * 十六进制字符串装十进制
     *
     * @param hex 十六进制字符串
     * @return 十进制数值
     */
    public static int hexStringToAlgorism(String hex) {
        hex = hex.toUpperCase();
        int max = hex.length();
        int result = 0;
        for (int i = max; i > 0; i--) {
            char c = hex.charAt(i - 1);
            int algorism = 0;
            if (c >= '0' && c <= '9') {
                algorism = c - '0';
            } else {
                algorism = c - 55;
            }
            result += Math.pow(16, max - i) * algorism;
        }
        return result;
    }


}
