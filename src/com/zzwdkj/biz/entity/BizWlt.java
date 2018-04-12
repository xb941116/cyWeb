package com.zzwdkj.biz.entity;

import com.hckj.framework.entity.GenericEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * plat_wlt, 商家_钱包
 *
 * @author guoxianwei  2016-09-07 15:01:55
 */
public class BizWlt extends GenericEntity {
    private String id;          //主键
    private String bizNo;          //商家编号
    private java.math.BigDecimal cashPool;          //商家现金池
    private java.math.BigDecimal totalOut;          //商家总支出
    private java.math.BigDecimal take;          //可提金额
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public BizWlt() {
    }

    public BizWlt(String bizNo) {
        this.bizNo = bizNo;
        this.cashPool = BigDecimal.ZERO;
        this.totalOut = BigDecimal.ZERO;
        this.take = BigDecimal.ZERO;
        this.crtime = new Date();
        this.uptime = new Date();
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

    public java.math.BigDecimal getCashPool() {
        return this.cashPool;
    }

    public void setCashPool(java.math.BigDecimal cashPool) {
        this.cashPool = cashPool;
    }

    public java.math.BigDecimal getTotalOut() {
        return this.totalOut;
    }

    public void setTotalOut(java.math.BigDecimal totalOut) {
        this.totalOut = totalOut;
    }

    public BigDecimal getTake() {
        return take;
    }

    public void setTake(BigDecimal take) {
        this.take = take;
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