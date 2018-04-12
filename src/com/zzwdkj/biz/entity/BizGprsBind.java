package com.zzwdkj.biz.entity;

import com.hckj.framework.entity.GenericEntity;

/**
 * biz_gprs_bind, 商家_GPRS绑定表
 *
 * @author guoxianwei  2016-09-07 15:01:28
 */
public class BizGprsBind extends GenericEntity {
    private String id;          //ID
    private String bizNo;          //商家编号
    private String bizName;          //商家名称
    private String gprsNo;          //GPRS模块号
    private Integer prodSet;
    private Integer bind;          //是否绑定(0未绑定；1绑定）
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间
    private String bindStr;          //是否绑定(0未绑定；1绑定）
    private String prodSetStr;

    public BizGprsBind() {
    }

    public BizGprsBind(String bizNo, String gprsNo, Integer bind) {
        this.bizNo = bizNo;
        this.gprsNo = gprsNo;
        this.bind = bind;
        this.prodSet = 0;
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

    public Integer getBind() {
        return this.bind;
    }

    public void setBind(Integer bind) {
        this.bind = bind;
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

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public Integer getProdSet() {
        return prodSet;
    }

    public void setProdSet(Integer prodSet) {
        this.prodSet = prodSet;
    }

    public String getBindStr() {
        if (bind != null)
            bindStr = bind == 1 ? "已绑定" : "未绑定";
        return bindStr;
    }

    public String getProdSetStr() {
        if (prodSet != null) {
            prodSetStr = prodSet == 1 ? "已经营" : "未经营";
        } else {
            prodSetStr = "未经营";
        }
        return prodSetStr;
    }
}