package com.zzwdkj.common;

import com.zzwdkj.base.BaseEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 静态数据枚举类集中存放类Std
 *
 * @author guoxianwei 2015-06-18 10:56
 */
public class Std {

    private static Map<String, BaseEnum[]> enumMap = new HashMap<String, BaseEnum[]>();

    static {
        enumMap.put(ResType.class.getSimpleName(), ResType.values());
        enumMap.put(Sex.class.getSimpleName(), Sex.values());
        enumMap.put(YN.class.getSimpleName(), YN.values());
        enumMap.put(ONOFF.class.getSimpleName(), ONOFF.values());
    }

    /**
     * 供前台调用Std枚举值
     *
     * @param name 枚举名称
     * @return 枚举值map
     */
    public static List<Map<String, Object>> getStd(String name) {
        BaseEnum[] bes = enumMap.get(name);
        List<Map<String, Object>> resultList = null;
        if (bes != null) {
            resultList = new ArrayList<Map<String, Object>>();
            for (BaseEnum b : bes) {
                Map<String, Object> result = new HashMap<String, Object>();
                result.put("key", b.getKey());
                result.put("val", b.getVal());
                resultList.add(result);
            }
        }
        return resultList;
    }

    /**
     * 交易支出类型
     */
    public static enum InOutType implements BaseEnum {

        OUT(0, "支出"), IN(1, "收入");

        private Integer key;
        private String val;

        InOutType(Integer key, String val) {
            this.key = key;
            this.val = val;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        public static InOutType valueOf(Integer key) {
            for (InOutType st : InOutType.values()) {
                if (key != null && st.key.equals(key)) {
                    return st;
                }
            }
            return null;
        }
    }

    /**
     * vip类型
     */
    public static enum VipType implements BaseEnum {

        AD(1, "免广告");

        private Integer key;
        private String val;

        VipType(Integer key, String val) {
            this.key = key;
            this.val = val;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        public static VipType valueOf(Integer key) {
            for (VipType st : VipType.values()) {
                if (key != null && st.key.equals(key)) {
                    return st;
                }
            }
            return null;
        }
    }

    /**
     * 广告类型
     */
    public static enum ADType implements BaseEnum {

        PAYSUCESS(1, "支付完成界面");

        private Integer key;
        private String val;

        ADType(Integer key, String val) {
            this.key = key;
            this.val = val;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        public static ADType valueOf(Integer key) {
            for (ADType st : ADType.values()) {
                if (key != null && st.key.equals(key)) {
                    return st;
                }
            }
            return null;
        }
    }


    /**
     * 证件类型
     */
    public static enum CardType implements BaseEnum {
        IDCARD(1, "身份证");

        private Integer key;

        private String val;

        CardType(Integer key, String val) {
            this.key = key;
            this.val = val;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        public static CardType valueOf(Integer key) {
            for (CardType st : CardType.values()) {
                if (key != null && st.key.equals(key)) {
                    return st;
                }
            }
            return null;
        }
    }

    /**
     * 支付方式
     */
    public static enum PayWay implements BaseEnum {

        ALI(1, "支付宝"), WX(2, "微信"), WLT(3, "钱包"), BANK(4, "网银"), COIN(5, "投币"), CARD(6, "刷卡"),COINS(7,"积分");//(修改人：苏方宁；时间：12.3)

        private Integer key;

        private String val;

        PayWay(Integer key, String val) {
            this.key = key;
            this.val = val;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        public static PayWay valueOf(Integer key) {
            for (PayWay st : PayWay.values()) {
                if (key != null && st.key.equals(key)) {
                    return st;
                }
            }
            return null;
        }
    }

    /**
     * 支付状态枚举
     */
    public static enum PaySt implements BaseEnum {
        FAIL(0, "支付失败"), SUCCESS(1, "支付成功"), PAY_PROC(2, "待支付"), REFUND(3, "退款"), REF_PROC(4, "退款中");

        private Integer key;

        private String val;

        PaySt(Integer key, String val) {
            this.key = key;
            this.val = val;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        public static PaySt valueOf(Integer key) {
            for (PaySt st : PaySt.values()) {
                if (key != null && st.key.equals(key)) {
                    return st;
                }
            }
            return null;
        }
    }


    /**
     * 支付状态枚举
     */
    public static enum TakeSt implements BaseEnum {
        PROC(0, "等待转账"), SUCCESS(1, "提现成功"), PAYING(2, "待到账"), FAIL(3, "提现失败"), ERROR(4, "无效申请单"), OVERING(5, "待结算");

        private Integer key;

        private String val;

        TakeSt(Integer key, String val) {
            this.key = key;
            this.val = val;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        public static TakeSt valueOf(Integer key) {
            for (TakeSt st : TakeSt.values()) {
                if (key != null && st.key.equals(key)) {
                    return st;
                }
            }
            return null;
        }
    }

    /**
     * 是否类枚举
     */
    public static enum YN implements BaseEnum {
        YES(1, "是"), NO(0, "否");

        private Integer key;

        private String val;

        YN(Integer key, String val) {
            this.key = key;
            this.val = val;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        public static YN valueOf(Integer key) {
            for (YN st : YN.values()) {
                if (key != null && st.key.equals(key)) {
                    return st;
                }
            }
            return null;
        }
    }

    /**
     * 启用禁用类枚举
     */
    public static enum ONOFF implements BaseEnum {
        ON(1, "启用"), OFF(0, "禁用");

        private Integer key;

        private String val;

        ONOFF(Integer key, String val) {
            this.key = key;
            this.val = val;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        public static ONOFF valueOf(Integer key) {
            for (ONOFF st : ONOFF.values()) {
                if (key != null && st.key.equals(key)) {
                    return st;
                }
            }
            return null;
        }
    }

    /**
     * 性别
     */
    public static enum Sex implements BaseEnum {
        MALE(1, "男"), FEMALE(2, "女"), UNKNOWN(2, "未知");

        private Integer key;

        private String val;


        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        Sex(Integer key, String val) {
            this.key = key;
            this.val = val;
        }

        public static Sex valueOf(Integer key) {
            for (Sex st : Sex.values()) {
                if (key != null && st.key.equals(key)) {
                    return st;
                }
            }
            return null;
        }
    }

    /**
     * 资源类型
     */
    public static enum ResType implements BaseEnum {
        MENU(1, "菜单"), BTN(2, "按钮");

        private Integer key;

        private String val;

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        ResType(Integer key, String val) {
            this.key = key;
            this.val = val;
        }

        public static ResType valueOf(Integer key) {
            for (ResType st : ResType.values()) {
                if (key != null && st.key.equals(key)) {
                    return st;
                }
            }
            return null;
        }
    }

    /**
     * 变更类型
     */
    public static enum ChgType implements BaseEnum {
        SUB(0, "减"), ADD(1, "增");

        private Integer key;

        private String val;

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        ChgType(Integer key, String val) {
            this.key = key;
            this.val = val;
        }

        public static ChgType valueOf(Integer key) {
            for (ChgType st : ChgType.values()) {
                if (key != null && st.key.equals(key)) {
                    return st;
                }
            }
            return null;
        }
    }

    /**
     * 第三方平台(1微信；2 QQ；)
     */
    public static enum TPB implements BaseEnum {
        WX(1, "微信"), QQ(2, "QQ");

        private Integer key;

        private String val;

        TPB(Integer key, String val) {
            this.key = key;
            this.val = val;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        public static TPB valueOf(Integer key) {
            for (TPB st : TPB.values()) {
                if (key != null && st.key.equals(key)) {
                    return st;
                }
            }
            return null;
        }
    }


    /**
     * 分享方式
     */
    public static enum ShareWay implements BaseEnum {
        WX(1, "微信"), SINA(2, "新浪"), TWB(3, "腾讯微博"), QQ(3, "QQ");
        private Integer key;

        private String val;

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        ShareWay(Integer key, String val) {
            this.key = key;
            this.val = val;
        }

        public static ShareWay valueOf(Integer key) {
            for (ShareWay st : ShareWay.values()) {
                if (key != null && st.key.equals(key)) {
                    return st;
                }
            }
            return null;
        }
    }

    /**
     * 统计周期
     */
    public static enum Period implements BaseEnum {
        DAY(1, "日"), WEEK(2, "周"), MONTH(3, "月"), YEAR(3, "年");
        private Integer key;

        private String val;

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        Period(Integer key, String val) {
            this.key = key;
            this.val = val;
        }

        public static Period valueOf(Integer key) {
            for (Period st : Period.values()) {
                if (key != null && st.key.equals(key)) {
                    return st;
                }
            }
            return null;
        }
    }

}
