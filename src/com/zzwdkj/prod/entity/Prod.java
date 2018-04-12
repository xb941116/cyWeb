package com.zzwdkj.prod.entity;

import com.hckj.framework.entity.GenericEntity;
import com.zzwdkj.common.Std;

import java.math.BigDecimal;

/**
 * prod, 产品表
 *
 * @author guoxianwei  2016-09-21 17:30:03
 */
public class Prod extends GenericEntity {
    private String id;          //ID
    private String prodNo;          //产品编号
    private String prodName;          //产品名称
    private String bizNo;          //商家编号
    private String bizName;          //商家名称
    private String prodModNo;          //商品模板编号
    private String gprsNo;          //GPRS模块号
    private String logo;          //图标
    private Integer type;          //商品类型
    private java.math.BigDecimal price;          //单笔价格
    private Integer prodCv;          //脉冲系数
    private Integer otherMoneyState;          //是否开启备选金额
    private String otherMoneyOption;          //备选金额。格式为:3,2,1
    private Integer perCt;          //单笔数量
    private Integer giftCt;          //赠送数量
    private Integer firstFree;          //是否首次免单
    private String spoId;          //优惠活动
    private Integer runTime;          //使用时长（单位分钟）
    private Integer state;          //状态(0停用；1启用；）
    private Integer onlinePay;          //是否启用在线支付（0否；1是）
    private Integer qrGened;          //是否二维码生成（0否；1是）
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间
    private String stateStr;          //
    private Integer prodSet;
    private String prodSetStr;

    private BigDecimal netInc;
    private BigDecimal wxInc;
    private BigDecimal wltInc;
    private BigDecimal cardInc;
    private BigDecimal coinInc;
    private BigDecimal totalInc;
    private String typeStr;

    public Prod() {
    }

    /**
     * 状态枚举
     */
    public static enum St {
        DISABLE(0, "停用"), ENABLE(1, "启用");

        private Integer key;

        private String val;

        St(Integer key, String val) {
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

        public static St valueOf(Integer key) {
            for (St st : St.values()) {
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

    public String getProdNo() {
        return this.prodNo;
    }

    public void setProdNo(String prodNo) {
        this.prodNo = prodNo;
    }

    public String getProdName() {
        return this.prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getBizNo() {
        return this.bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public String getBizName() {
        return this.bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public String getProdModNo() {
        return this.prodModNo;
    }

    public void setProdModNo(String prodModNo) {
        this.prodModNo = prodModNo;
    }

    public String getGprsNo() {
        return this.gprsNo;
    }

    public void setGprsNo(String gprsNo) {
        this.gprsNo = gprsNo;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public java.math.BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(java.math.BigDecimal price) {
        this.price = price;
    }

    public Integer getProdCv() {
        return prodCv;
    }

    public void setProdCv(Integer prodCv) {
        this.prodCv = prodCv;
    }

    public Integer getOtherMoneyState() {
        return otherMoneyState;
    }

    public void setOtherMoneyState(Integer otherMoneyState) {
        this.otherMoneyState = otherMoneyState;
    }

    public String getOtherMoneyOption() {
        return otherMoneyOption;
    }

    public void setOtherMoneyOption(String otherMoneyOption) {
        this.otherMoneyOption = otherMoneyOption;
    }

    public Integer getPerCt() {
        return this.perCt;
    }

    public void setPerCt(Integer perCt) {
        this.perCt = perCt;
    }

    public Integer getGiftCt() {
        return this.giftCt;
    }

    public void setGiftCt(Integer giftCt) {
        this.giftCt = giftCt;
    }

    public Integer getFirstFree() {
        return this.firstFree;
    }

    public void setFirstFree(Integer firstFree) {
        this.firstFree = firstFree;
    }

    public String getSpoId() {
        return this.spoId;
    }

    public void setSpoId(String spoId) {
        this.spoId = spoId;
    }

    public Integer getRunTime() {
        return runTime;
    }

    public void setRunTime(Integer runTime) {
        this.runTime = runTime;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getOnlinePay() {
        return this.onlinePay;
    }

    public void setOnlinePay(Integer onlinePay) {
        this.onlinePay = onlinePay;
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

    public Integer getQrGened() {
        return qrGened;
    }

    public void setQrGened(Integer qrGened) {
        this.qrGened = qrGened;
    }

    public Integer getProdSet() {
        return prodSet;
    }

    public void setProdSet(Integer prodSet) {
        this.prodSet = prodSet;
    }

    public String getStateStr() {
        if (state != null) {
            stateStr = St.valueOf(state).getVal();
        }
        return stateStr;
    }

    public String getProdSetStr() {
        prodSetStr = (null == prodSet ||0 ==prodSet) ? "未设置" :"已设置";
        return prodSetStr;
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
    }

    public BigDecimal getNetInc() {
        return netInc;
    }

    public void setNetInc(BigDecimal netInc) {
        this.netInc = netInc;
    }

    public BigDecimal getCardInc() {
        return cardInc;
    }

    public void setCardInc(BigDecimal cardInc) {
        this.cardInc = cardInc;
    }

    public BigDecimal getCoinInc() {
        return coinInc;
    }

    public void setCoinInc(BigDecimal coinInc) {
        this.coinInc = coinInc;
    }

    public BigDecimal getTotalInc() {
        return totalInc;
    }

    public void setTotalInc(BigDecimal totalInc) {
        this.totalInc = totalInc;
    }

    public BigDecimal getWxInc() {
        return wxInc;
    }

    public void setWxInc(BigDecimal wxInc) {
        this.wxInc = wxInc;
    }

    public BigDecimal getWltInc() {
        return wltInc;
    }

    public void setWltInc(BigDecimal wltInc) {
        this.wltInc = wltInc;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }
}