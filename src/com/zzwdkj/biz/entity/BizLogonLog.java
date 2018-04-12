package com.zzwdkj.biz.entity;

import com.hckj.framework.entity.GenericEntity;

/**
* biz_logon_log, 商家_登录_日志
*
* @author guoxianwei  2016-09-07 15:01:29
*/
public class BizLogonLog extends GenericEntity{
    private String id;          //主键ID
    private String bizId;          //商家ID
    private String ip;          //登录IP
    private String city;          //所在地市
    private String device;          //终端
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public BizLogonLog() {
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id=id;
    }
    public String getBizId(){
        return this.bizId;
    }

    public void setBizId(String bizId){
        this.bizId=bizId;
    }
    public String getIp(){
        return this.ip;
    }

    public void setIp(String ip){
        this.ip=ip;
    }
    public String getCity(){
        return this.city;
    }

    public void setCity(String city){
        this.city=city;
    }
    public String getDevice(){
        return this.device;
    }

    public void setDevice(String device){
        this.device=device;
    }
    public java.util.Date getCrtime(){
        return this.crtime;
    }

    public void setCrtime(java.util.Date crtime){
        this.crtime=crtime;
    }
    public java.util.Date getUptime(){
        return this.uptime;
    }

    public void setUptime(java.util.Date uptime){
        this.uptime=uptime;
    }
}