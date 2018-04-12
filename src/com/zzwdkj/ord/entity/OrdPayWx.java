package com.zzwdkj.ord.entity;

import com.hckj.framework.entity.GenericEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ord_pay_wx, 订单_支付_微信支付记录表
 *
 * @author guoxianwei  2016-09-30 12:25:03
 */
public class OrdPayWx extends GenericEntity {
    private String id;          //ID
    private String acct;          //微信账号
    private String openid;          //微信openid
    private java.math.BigDecimal money;          //支付金额
    private Integer state;          //@@支付状态(0失败； 1成功；2处理中）
    private String payno;          //支付凭证号
    private String resvAcct;          //收款的商家微信商户账号
    private String resvBizNo;          //收款的商家编号
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public OrdPayWx() {
    }

    public OrdPayWx(String acct,String openid, BigDecimal money, String payno, String resvAcct,String resvBizNo) {
        this.acct = acct;
        this.openid = openid;
        this.money = money;
        this.payno = payno;
        this.resvAcct = resvAcct;
        this.resvBizNo = resvBizNo;
        this.crtime = new Date();
        this.uptime = new Date();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAcct() {
        return this.acct;
    }

    public void setAcct(String acct) {
        this.acct = acct;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public java.math.BigDecimal getMoney() {
        return this.money;
    }

    public void setMoney(java.math.BigDecimal money) {
        this.money = money;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getPayno() {
        return this.payno;
    }

    public void setPayno(String payno) {
        this.payno = payno;
    }

    public String getResvAcct() {
        return this.resvAcct;
    }

    public void setResvAcct(String resvAcct) {
        this.resvAcct = resvAcct;
    }

    public String getResvBizNo() {
        return resvBizNo;
    }

    public void setResvBizNo(String resvBizNo) {
        this.resvBizNo = resvBizNo;
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
}