package com.zzwdkj.biz.entity;

import com.hckj.framework.entity.GenericEntity;
import com.zzwdkj.common.DateUtil;

/**
* biz_vip, 商家VIP功能表
*
* @author guoxianwei  2017-04-22 12:12:22
*/
public class BizVip extends GenericEntity{
    private String id;          //主键
    private String bizNo;          //商家编号
    private Integer vipType;          //vip功能（1免广告）
    private Integer state;          //状态（1开启0关闭）
    private java.util.Date startTime;          //起始时间
    private java.util.Date endTime;          //结束时间
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //修改时间

    private Integer yearLength;
    private String bizName;

    public BizVip() {
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id=id;
    }
    public String getBizNo(){
        return this.bizNo;
    }

    public void setBizNo(String bizNo){
        this.bizNo=bizNo;
    }
    public Integer getVipType(){
        return this.vipType;
    }

    public void setVipType(Integer vipType){
        this.vipType=vipType;
    }
    public Integer getState(){
        return this.state;
    }

    public void setState(Integer state){
        this.state=state;
    }
    public java.util.Date getStartTime(){
        return this.startTime;
    }

    public void setStartTime(java.util.Date startTime){
        this.startTime=startTime;
    }
    public java.util.Date getEndTime(){
        return this.endTime;
    }

    public void setEndTime(java.util.Date endTime){
        this.endTime=endTime;
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

    public Integer getYearLength() {
        return DateUtil.getYear(this.endTime)-DateUtil.getYear(this.startTime);
    }



    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }
}