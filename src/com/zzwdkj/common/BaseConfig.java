package com.zzwdkj.common;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author guoxianwei 2015-07-31 10:40
 */
public class BaseConfig {

    public static final String SESSION_VAR = "SysAcct";

    public static final String MQ_CHANNEL_PAYMENT = "mhl.gprs.pay";
    public static final String MQ_CHANNEL_COMMAND = "mhl.gprs.cmd";

    public static final String MQ_ROUTING_KEY_SV_PAYMENT = "sv_pay";
    public static final String MQ_ROUTING_KEY_SV_COMMAND = "sv_cmd";

    public static String SYS_CODE = null;
    public static Properties properties = null;


    public static Properties getSysProperties() {
        if (properties == null) {
            properties = new Properties();
            InputStream in = BaseConfig.class.getResourceAsStream("../../../application.properties");
            try {
                properties.load(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }

    public static String getSysCode() {
        if (SYS_CODE != null) {
            return SYS_CODE;
        }
        Properties properties = getSysProperties();
        if (properties != null) {
            String sysCode = (String) properties.get("sys_code");
            SYS_CODE = sysCode == null ? "00" : sysCode;
        } else {
            SYS_CODE = "00";
        }
        return SYS_CODE;
    }

    public static boolean isSelfProd(String gprsNo) {
        boolean isSelf = false;
        if (gprsNo != null && gprsNo.startsWith("0")) {
            isSelf = true;
        }
        return isSelf;
    }

    public static String getPayMoney(String gprsNo,String ordNo, int my) {
        String money = "0";
        if (isSelfProd(gprsNo)) {
            money = String.valueOf(my);
            if (money != null && money.length() > 0) {
                money = StringUtils.leftPad(money, 2, "0");
            }
            money = money + BaseConfig.getSysCode() + ordNo.substring(ordNo.length() - 3);
            money = money + RandomUtil.checkCode(money);
        } else {
            money = String.valueOf(my * 100);
            if (money != null && money.length() > 0) {
                money = StringUtils.leftPad(money, 4, "0");
            }
            money = money + RandomUtil.checkCode(money);
        }
        return money;
    }
}
