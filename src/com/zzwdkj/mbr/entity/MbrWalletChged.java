package com.zzwdkj.mbr.entity;

import com.hckj.framework.entity.GenericEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
* mbr_wallet_chged, 会员钱包变更表
*
* @author guoxianwei  2016-11-11 15:55:32
*/
public class MbrWalletChged extends GenericEntity{
    private String id;          //ID
    private String memberId;          //会员ID
    private Integer chgType;          //@变更类型(0 减；1增)
    private java.math.BigDecimal money;          //变更额度
    private String remark;          //说明
    private java.util.Date crtime;          //创建时间

    public MbrWalletChged() {
    }

    public MbrWalletChged(String memberId, int chgType, BigDecimal money, String remark) {

        this.memberId=memberId;
        this.chgType=chgType;
        this.money=money;
        this.remark=remark;
        this.crtime = new Date();
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
    public Integer getChgType(){
        return this.chgType;
    }

    public void setChgType(Integer chgType){
        this.chgType=chgType;
    }
    public java.math.BigDecimal getMoney(){
        return this.money;
    }

    public void setMoney(java.math.BigDecimal money){
        this.money=money;
    }
    public String getRemark(){
        return this.remark;
    }

    public void setRemark(String remark){
        this.remark=remark;
    }
    public java.util.Date getCrtime(){
        return this.crtime;
    }

    public void setCrtime(java.util.Date crtime){
        this.crtime=crtime;
    }
}