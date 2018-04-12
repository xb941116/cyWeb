package com.zzwdkj.mbr.entity;

import com.hckj.framework.entity.GenericEntity;

/**
 * mbr_wallet, 会员钱包
 *
 * @author guoxianwei  2016-11-11 15:55:31
 */
public class MbrWallet extends GenericEntity {
    private String id;          //ID
    private String memberId;          //会员ID
    private java.math.BigDecimal money;          //余额
    private Integer state;          //状态（0 锁定；1 正常）
    private String paypwd;          //支付密码
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public MbrWallet() {
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

    public String getPaypwd() {
        return this.paypwd;
    }

    public void setPaypwd(String paypwd) {
        this.paypwd = paypwd;
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