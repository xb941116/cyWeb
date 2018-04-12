package com.zzwdkj.gprs.entity;

import com.hckj.framework.entity.GenericEntity;

/**
* gprs_otp, gprs模块otp计数表
*
* @author guoxianwei  2017-02-20 11:49:23
*/
public class GprsOtp extends GenericEntity{
    private String id;          //主键ID
    private String gprsNo;          //GPRS模块号
    private Long movingFactor;          //计数
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public GprsOtp() {
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id=id;
    }
    public String getGprsNo(){
        return this.gprsNo;
    }

    public void setGprsNo(String gprsNo){
        this.gprsNo=gprsNo;
    }
    public Long getMovingFactor(){
        return this.movingFactor;
    }

    public void setMovingFactor(Long movingFactor){
        this.movingFactor=movingFactor;
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