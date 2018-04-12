package com.zzwdkj.mbr.entity;

import com.hckj.framework.entity.GenericEntity;

/**
* mbr_oauth, 第三方账号关联表
*
* @author guoxianwei  2016-11-11 15:55:28
*/
public class MbrOauth extends GenericEntity{
    private String id;          //ID
    private String memberId;          //会员ID
    private String openid;          //第三方关联ID
    private Integer tpb;          //第三方平台(1微信；2 QQ；)加入社区名称值从设备编码获取（通过用户使用设备获取本用户的）
    private Integer state;          //绑定状态(1正常；0失效）
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public MbrOauth() {
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id=id;
    }
    public String getMemberId(){
        return this.memberId;
    }

    public void setMemberId(String memberId){
        this.memberId=memberId;
    }
    public String getOpenid(){
        return this.openid;
    }

    public void setOpenid(String openid){
        this.openid=openid;
    }
    public Integer getTpb(){
        return this.tpb;
    }

    public void setTpb(Integer tpb){
        this.tpb=tpb;
    }
    public Integer getState(){
        return this.state;
    }

    public void setState(Integer state){
        this.state=state;
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