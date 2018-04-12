package com.zzwdkj.biz.entity;

import com.hckj.framework.entity.GenericEntity;

/**
* biz_take_bank, 商家_提现_银行卡_记录
*
* @author guoxianwei  2016-09-07 15:01:32
*/
public class BizTakeBank extends GenericEntity{
    private String id;          //ID
    private String takeNo;          //申请单号
    private String bankNo;          //银行卡号
    private String name;          //姓名
    private String bankAddr;          //开户行地址
    private java.math.BigDecimal money;          //提现金额
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public BizTakeBank() {
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
    public String getBankNo(){
        return this.bankNo;
    }

    public void setBankNo(String bankNo){
        this.bankNo=bankNo;
    }
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name=name;
    }
    public String getBankAddr(){
        return this.bankAddr;
    }

    public void setBankAddr(String bankAddr){
        this.bankAddr=bankAddr;
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