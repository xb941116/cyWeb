package com.zzwdkj.prod.entity;

import com.hckj.framework.entity.GenericEntity;

import java.util.Date;

/**
 * prod_gprs_bind_his, 产品_GPRS_绑定历史表
 *
 * @author guoxianwei  2016-09-20 16:38:01
 */
public class ProdGprsBindHis extends GenericEntity {
    private String id;          //ID
    private String bizNo;          //商家编号
    private String prodNo;          //产品编号
    private String gprsNo;          //GPRS模块号
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public ProdGprsBindHis() {
    }

    public ProdGprsBindHis(String bizNo, String prodNo, String gprsNo) {
        this.bizNo = bizNo;
        this.prodNo = prodNo;
        this.gprsNo = gprsNo;
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