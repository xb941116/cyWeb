package com.zzwdkj.mbr.entity;

import com.hckj.framework.entity.GenericEntity;

import java.math.BigDecimal;

/**
* mbr_coin, 会员积分表
*
* @author guoxianwei  2016-11-11 15:55:27
*/
public class MbrCoin extends GenericEntity{
    private String id;          //ID
    private String memberId;          //会员ID
    private java.math.BigDecimal coin;          //积分
    private Integer state;          //状态（0 锁定；1 正常）
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public MbrCoin() {
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
    public java.math.BigDecimal getCoin(){
        return this.coin;
    }

    public void setCoin(BigDecimal coin){
        this.coin=coin;
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