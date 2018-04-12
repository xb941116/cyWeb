package com.zzwdkj.prod.entity;

import com.hckj.framework.entity.GenericEntity;

import java.util.Date;

/**
 * prod_sp_args, 产品_特定参数表
 *
 * @author guoxianwei  2016-09-29 17:00:56
 */
public class ProdSpArgs extends GenericEntity {
    private String id;          //
    private String bizNo;          //商家编号
    private String prodNo;          //产品编号
    private String code;          //编码
    private String name;          //属性名称
    private String type;          //值类型（1文本型；2单选框；3复选框）
    private String unit;          //值单位
    private String val;            //值
    private String maxVal;          //最大值
    private String minVal;          //最小值
    private String remark;          //备注
    private Integer sort;          //序号
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间


    public ProdSpArgs() {
    }

    public ProdSpArgs(String bizNo, String prodNo, String name, String code, String type, String unit,String val, String maxVal, String minVal, Integer sort) {
        this.bizNo = bizNo;
        this.prodNo = prodNo;
        this.name = name;
        this.code = code;
        this.type = type;
        this.unit = unit;
        this.val = val;
        this.maxVal = maxVal;
        this.minVal = minVal;
        this.sort = sort;
        this.crtime = new Date();
        this.uptime = new Date();
    }

    /**
     * 状态枚举
     */
    public static enum ArgsEnum {
        AAA(1,  "10","200", "0", "1", "厘/秒", "清水费率"),
        BBB(2,  "13","200", "0", "1", "厘/秒", "泡沫费率"),
        CCC(3,  "7","200", "0", "1", "厘/秒", "吸尘费率"),
        DDD(4,  "8","200", "0", "1", "厘/秒", "洗手费率"),
        EEE(5,  "11","200", "0", "1", "厘/秒", "臭氧费率"),
        FFF(6,  "14","250", "0", "1", "分/秒", "玻水费率"),
        GGG(7,  "0","200", "0", "1", "厘/秒", "暂停费率"),
        HHH(8,  "6","60", "1", "1", "元", "单刷扣费"),
        III(9,  "6","60", "1", "1", "元", "最少投币"),
        JJJ(10, "3","20", "1", "1", "分钟", "除臭时间"),
        KKK(11, "13","250", "1", "1", "秒", "玻水注时间"),
        LLL(12, "15","250", "1", "1", "秒", "添原液时间"),
        MMM(13, "5","60", "0", "1", "分", "自添水时间"),
        NNN(14, "0","59", "0", "1", "秒", "自添水时间"),
        OOO(15, "10","60", "0", "1", "分钟", "余清等时间"),
        PPP(16, "5","20", "0", "1", "分钟", "车灯延时间"),
        QQQ(17, "20","60", "0", "1", "分钟", "总使用时间"),
        RRR(18, "18","23", "0", "1", "时", "广告灯开时间"),
        SSS(19, "30","59", "0", "1", "分", "广告灯开时间"),
        TTT(20, "6","23", "0", "1", "时", "广告灯关时间"),
        UUU(21, "0","59", "0", "1", "分", "广告灯关时间"),
        VVV(22, "3","10", "0", "1", "", "防冻温度"),
        WWW(23, "1","1", "0", "2", "", "余额清除"),
        XXX(24, "10","60", "0", "1", "秒", "排水时间"),
        YYY(25, "1","1", "0", "2", "", "余额退还");

        private Integer key;
        private String val;
        private String desc;
        private String maxVal;
        private String minVal;
        private String unit;
        private String type;
        private String name;

        ArgsEnum(Integer key,String val, String maxVal, String minVal, String type, String unit, String desc) {
            this.key = key;
            this.val = val;
            this.maxVal = maxVal;
            this.minVal = minVal;
            this.type = type;
            this.unit = unit;
            this.desc = desc;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        public String getMaxVal() {
            return maxVal;
        }

        public void setMaxVal(String maxVal) {
            this.maxVal = maxVal;
        }

        public String getMinVal() {
            return minVal;
        }

        public void setMinVal(String minVal) {
            this.minVal = minVal;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return this.name();
        }

        public void setName(String name) {
            this.name = name;
        }

        public static ArgsEnum valueOf(Integer key) {
            for (ArgsEnum st : ArgsEnum.values()) {
                if (key != null && st.key.equals(key)) {
                    return st;
                }
            }
            return null;
        }
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBizNo() {
        return this.bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public String getProdNo() {
        return this.prodNo;
    }

    public void setProdNo(String prodNo) {
        this.prodNo = prodNo;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMaxVal() {
        return this.maxVal;
    }

    public void setMaxVal(String maxVal) {
        this.maxVal = maxVal;
    }

    public String getMinVal() {
        return this.minVal;
    }

    public void setMinVal(String minVal) {
        this.minVal = minVal;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public java.util.Date getCrtime() {
        return this.crtime;
    }

    public void setCrtime(java.util.Date crtime) {
        this.crtime = crtime;
    }

    public java.util.Date getUptime() {
        return this.uptime;
    }

    public void setUptime(java.util.Date uptime) {
        this.uptime = uptime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}