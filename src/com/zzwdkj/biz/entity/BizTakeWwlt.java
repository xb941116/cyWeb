package com.zzwdkj.biz.entity;

import com.hckj.framework.entity.GenericEntity;

/**
* biz_take_wwlt, 商家_提现_微信零钱包_记录
*
* @author guoxianwei  2016-09-30 17:52:45
*/
public class BizTakeWwlt extends GenericEntity{
    private String id;          //ID
    private String takeNo;          //申请单号
    private String wxAcct;          //微信账号
    private String nick;          //昵称
    private String name;          //姓名
    private java.math.BigDecimal money;          //提现金额
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public BizTakeWwlt() {
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id=id;
    }
    public String getTakeNo(){
        return this.takeNo;
    }

    public void setTakeNo(String takeNo){
        this.takeNo=takeNo;
    }
    public String getWxAcct(){
        return this.wxAcct;
    }

    public void setWxAcct(String wxAcct){
        this.wxAcct=wxAcct;
    }
    public String getNick(){
        return this.nick;
    }

    public void setNick(String nick){
        this.nick=nick;
    }
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name=name;
    }
    public java.math.BigDecimal getMoney(){
        return this.money;
    }

    public void setMoney(java.math.BigDecimal money){
        this.money=money;
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