package com.zzwdkj.ord.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hckj.framework.entity.GenericEntity;
import com.zzwdkj.common.Std;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;

/**
 * ord, 订单_订单表
 *
 * @author guoxianwei  2016-09-07 15:01:44
 */
public class Ord extends GenericEntity {
    private String id;          //ID
    private String memberId;          //客户ID
    private String ordOpenid;          //客户openid
    private String bizNo;          //商家编号
    private String prodNo;          //设备编号
    private String gprsNo;          //模块编号
    private String ordno;          //订单号
    private Integer devType;     //设备类型
    private Integer channel;//通道（充电回路）
    private Integer totalTime;//使用时间
    private String prodName;        //订单名称
    private java.math.BigDecimal perPrice;          //商品单价
    private Integer prodQt;          //购买数量
    private java.math.BigDecimal prodPrice;          //商品总金额
    private java.math.BigDecimal wallet; //消费钱包金额 （此处修改添加的新字段；时间：12.7）
    private java.math.BigDecimal coins;//消费的积分额度 （此处修改添加的新字段；时间：12.7）
    private Integer giveQt;          //赠送数量
    private Integer pulse;          //脉冲数量
    private Integer payWay;          //支付方式(1支付宝;2微信;3钱包;4刷卡)
    private String otpCode;          //otp码
    private Integer transfersIs;          //是否是自动转账（0否，1是）
    private Integer transfersState;          //自动转账状态（0待转账，1结算中, 2转账成功，3转账失败）
    private Integer state;          //状态(0等待付款；1 已付款；2交易完成；3 交易超时关闭 ；4 已退款）
    private Integer succ;          //交易是否成功
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date crtime;          //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date uptime;          //更新时间
    private String bizName;          //商家编号

    private String stateStr;
    private String payWayStr;

    private BigDecimal wxInc = BigDecimal.ZERO;
    private BigDecimal wltInc = BigDecimal.ZERO;
    private BigDecimal aliInc = BigDecimal.ZERO;

    public Ord() {
    }

    public Ord(String bizNo, String prodNo, String prodName, String gprsNo, BigDecimal perPrice, Integer prodQt, BigDecimal prodPrice, Integer payWay) {
        this.bizNo = bizNo;
        this.prodNo = prodNo;
        this.prodName = prodName;
        this.gprsNo = gprsNo;
        this.perPrice = perPrice;
        this.prodQt = prodQt;
        this.prodPrice = prodPrice;
        this.payWay = payWay;
    }
    //（此处修改添加的新字段；时间：12.9）
    public Ord(String bizNo, String bizName, String prodNo, String prodName, String gprsNo, BigDecimal perPrice,
               Integer prodQt, BigDecimal prodPrice, Integer payWay, Integer pulse) {
        this.bizNo = bizNo;
        this.bizName = bizName;
        this.prodNo = prodNo;
        this.prodName = prodName;
        this.gprsNo = gprsNo;
        this.perPrice = perPrice;
        this.prodQt = prodQt;
        this.prodPrice = prodPrice;
        this.payWay = payWay;
        this.pulse = pulse;
    }


    /**
     * 状态枚举 自动转账状态（0待转账，1结算中, 2转账成功，3转账失败）
     */
    public static enum Transfers {
        WAIT_PAY(0, "待转账"), PAYED(1, "结算中"), DONE(2, "转账成功"), CANCEL(3, "转账失败");

        private Integer key;

        private String val;

        Transfers(Integer key, String val) {
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

        public static Transfers valueOf(Integer key) {
            for (Transfers st : Transfers.values()) {
                if (key != null && st.key.equals(key)) {
                    return st;
                }
            }
            return null;
        }
    }


    /**
     * 状态枚举 状态(0等待付款；1 已付款；2交易完成；3 设备未运行 4 已退款）
     */
    public static enum St {
        WAIT_PAY(0, "等待付款"), PAYED(1, "已付款"), DONE(2, "交易完成"), CANCEL(3, "设备未运行"), BKMONEY(4, "已退款");

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


    public java.math.BigDecimal getCoins() {
        return coins;
    }

    public void setCoins(BigDecimal coins) {
        this.coins = coins;
    }

    public java.math.BigDecimal getWallet() {
        return wallet;
    }

    public void setWallet(BigDecimal wallet) {
        this.wallet = wallet;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return this.memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
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

    public String getGprsNo() {
        return this.gprsNo;
    }

    public void setGprsNo(String gprsNo) {
        this.gprsNo = gprsNo;
    }

    public String getOrdno() {
        return this.ordno;
    }

    public void setOrdno(String ordno) {
        this.ordno = ordno;
    }

    public java.math.BigDecimal getPerPrice() {
        return this.perPrice;
    }

    public void setPerPrice(java.math.BigDecimal perPrice) {
        this.perPrice = perPrice;
    }

    public Integer getProdQt() {
        return this.prodQt;
    }

    public void setProdQt(Integer prodQt) {
        this.prodQt = prodQt;
    }

    public java.math.BigDecimal getProdPrice() {
        return this.prodPrice;
    }

    public void setProdPrice(java.math.BigDecimal prodPrice) {
        this.prodPrice = prodPrice;
    }

    public Integer getGiveQt() {
        return this.giveQt;
    }

    public void setGiveQt(Integer giveQt) {
        this.giveQt = giveQt;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public Integer getPayWay() {
        return this.payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public Integer getTransfersIs() {
        return transfersIs;
    }

    public void setTransfersIs(Integer transfersIs) {
        this.transfersIs = transfersIs;
    }

    public Integer getTransfersState() {
        return transfersState;
    }

    public void setTransfersState(Integer transfersState) {
        this.transfersState = transfersState;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public java.util.Date getCrtime() {
        return this.crtime;
    }

    public void setCrtime(java.util.Date crtime) {
        this.crtime = crtime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public java.util.Date getUptime() {
        return this.uptime;
    }

    public void setUptime(java.util.Date uptime) {
        this.uptime = uptime;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Integer getSucc() {
        return succ;
    }

    public void setSucc(Integer succ) {
        this.succ = succ;
    }

    public Integer getDevType() {
        return devType;
    }

    public void setDevType(Integer devType) {
        this.devType = devType;
    }

    public String getStateStr() {
        if (state != null) {
            stateStr = St.valueOf(state).getVal();
        }
        return stateStr;
    }

    public String getPayWayStr() {
        if (payWay != null) {
            payWayStr = Std.PayWay.valueOf(payWay).getVal();
        }
        return payWayStr;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
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

    public BigDecimal getAliInc() {
        return aliInc;
    }

    public void setAliInc(BigDecimal aliInc) {
        this.aliInc = aliInc;
    }

    public Integer getPulse() {
        return pulse;
    }

    public void setPulse(Integer pulse) {
        this.pulse = pulse;
    }

    public String getOrdOpenid() {
        return ordOpenid;
    }

    public void setOrdOpenid(String ordOpenid) {
        this.ordOpenid = ordOpenid;
    }
}