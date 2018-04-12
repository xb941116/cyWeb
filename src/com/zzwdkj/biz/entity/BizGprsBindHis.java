package com.zzwdkj.biz.entity;

import com.hckj.framework.entity.GenericEntity;

/**
 * biz_gprs_bind_his, 商家_GPRS绑定历史表
 *
 * @author guoxianwei  2016-09-07 15:01:29
 */
public class BizGprsBindHis extends GenericEntity {
    private String id;          //ID
    private String bizNo;          //商家编号
    private String gprsNo;          //GPRS模块号
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public BizGprsBindHis() {
    }

    public BizGprsBindHis(String bizNo, String gprsNo) {
        this.bizNo = bizNo;
        this.gprsNo = gprsNo;
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