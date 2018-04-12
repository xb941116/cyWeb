package com.zzwdkj.mbr.entity;

import com.hckj.framework.entity.GenericEntity;

/**
* mbr_prizen, 会员奖励记录表
*
* @author guoxianwei  2016-11-11 15:55:30
*/
public class MbrPrizen extends GenericEntity{
    private String id;          //
    private String memberId;          //会员ID
    private Integer money;          //奖励额度
    private String reason;          //奖励原因
    private java.util.Date crtime;          //奖励时间

    public MbrPrizen() {
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
    public Integer getMoney(){
        return this.money;
    }

    public void setMoney(Integer money){
        this.money=money;
    }
    public String getReason(){
        return this.reason;
    }

    public void setReason(String reason){
        this.reason=reason;
    }
    public java.util.Date getCrtime(){
        return this.crtime;
    }

    public void setCrtime(java.util.Date crtime){
        this.crtime=crtime;
    }
}