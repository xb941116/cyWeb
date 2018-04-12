package com.zzwdkj.sys.entity;

import com.hckj.framework.entity.GenericEntity;

import java.util.Date;

/**
 * sys_acct_res, 权限_账号权限
 *
 * @author guoxianwei  2016-09-12 12:57:42
 */
public class SysAcctRes extends GenericEntity {
    private String id;          //ID
    private String bizNo;          //商家编码
    private String acct;          //账号
    private String resCode;          //资源编码
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public SysAcctRes() {
    }

    public SysAcctRes(String bizNo,String acct,String resCode) {
        this.bizNo = bizNo;
        this.acct = acct;
        this.resCode = resCode;
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

    public String getAcct() {
        return this.acct;
    }

    public void setAcct(String acct) {
        this.acct = acct;
    }

    public String getResCode() {
        return this.resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }


    public Date getCrtime() {
        return crtime;
    }

    public void setCrtime(Date crtime) {
        this.crtime = crtime;
    }

    public Date getUptime() {
        return uptime;
    }

    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }
}