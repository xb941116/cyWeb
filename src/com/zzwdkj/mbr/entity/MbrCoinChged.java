package com.zzwdkj.mbr.entity;

import com.hckj.framework.entity.GenericEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
* mbr_coin_chged, 会员积分变更表
*
* @author guoxianwei  2016-11-11 15:55:28
*/
public class MbrCoinChged extends GenericEntity{
    private String id;          //ID
    private String memberId;          //会员ID
    private Integer chgType;          //@变更类型(0 减；1增）
    private java.math.BigDecimal coin;          //变更积分
    private String remark;          //说明
    private java.util.Date crtime;          //创建时间

    public MbrCoinChged(){

    }

    public MbrCoinChged(String memberId, int chgType, BigDecimal coin, String remark) {//(修改人：苏方宁；时间：12.5)
        this.memberId=memberId;
        this.chgType=chgType;
        this.coin=coin;
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
    public java.math.BigDecimal getCoin(){
        return this.coin;
    }

    public void setCoin(java.math.BigDecimal coin){
        this.coin=coin;
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