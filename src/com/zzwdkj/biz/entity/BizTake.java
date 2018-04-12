package com.zzwdkj.biz.entity;

import com.hckj.framework.entity.GenericEntity;
import com.zzwdkj.base.BaseEnum;
import com.zzwdkj.common.Std;

/**
 * biz_take, 商家_提现
 *
 * @author guoxianwei  2016-09-07 15:01:31
 */
public class BizTake extends GenericEntity {
    private String id;          //主键
    private String reqno;          //申请单号
    private String bizId;          //商家ID
    private String bizNo;          //商家编号
    private String parBizNo;          //打款商家编号
    private String tsno;          //打款凭证号
    private java.math.BigDecimal amount;          //提现金额
    private java.math.BigDecimal realAmount;          //实提金额
    private java.math.BigDecimal mtcCost;          //手续费
    private Integer takeWay;          //提现方式(1银行卡；2微信零钱包；3自动转账到微信）
    private Integer state;          //状态（0 等待转账；1提现成功；2 已打款待到账；3提现失败；4无效申请单5 待转账）
    private String expln;          //说明
    private java.util.Date doneDate;          //响应成功时间
    private String optor;          //操作人
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间


    private String takeWayStr;
    private String stateStr;

    /**
     * 支付方式
     */
    public static enum TakeWay implements BaseEnum {
        Bank(1, "银行卡"), WX(2, "微信钱包"),AUTOWX(3,"自动转账");

        private Integer key;

        private String val;

        TakeWay(Integer key, String val) {
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

        public static TakeWay valueOf(Integer key) {
            for (TakeWay st : TakeWay.values()) {
                if (key != null && st.key.equals(key)) {
                    return st;
                }
            }
            return null;
        }
    }

    public BizTake() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReqno() {
        return this.reqno;
    }

    public void setReqno(String reqno) {
        this.reqno = reqno;
    }

    public String getBizId() {
        return this.bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getTsno() {
        return this.tsno;
    }

    public void setTsno(String tsno) {
        this.tsno = tsno;
    }

    public java.math.BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(java.math.BigDecimal amount) {
        this.amount = amount;
    }

    public java.math.BigDecimal getRealAmount() {
        return this.realAmount;
    }

    public void setRealAmount(java.math.BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public java.math.BigDecimal getMtcCost() {
        return this.mtcCost;
    }

    public void setMtcCost(java.math.BigDecimal mtcCost) {
        this.mtcCost = mtcCost;
    }

    public Integer getTakeWay() {
        return this.takeWay;
    }

    public void setTakeWay(Integer takeWay) {
        this.takeWay = takeWay;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getExpln() {
        return this.expln;
    }

    public void setExpln(String expln) {
        this.expln = expln;
    }

    public java.util.Date getDoneDate() {
        return this.doneDate;
    }

    public void setDoneDate(java.util.Date doneDate) {
        this.doneDate = doneDate;
    }

    public String getOptor() {
        return this.optor;
    }

    public void setOptor(String optor) {
        this.optor = optor;
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

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public String getParBizNo() {
        return parBizNo;
    }

    public void setParBizNo(String parBizNo) {
        this.parBizNo = parBizNo;
    }

    public String getStateStr() {
        if (state != null) {
            stateStr = Std.TakeSt.valueOf(state).getVal();
        }
        return stateStr;
    }

    public String getTakeWayStr() {
        if (takeWay != null) {
            takeWayStr = TakeWay.valueOf(takeWay).getVal();
        }
        return takeWayStr;
    }
}