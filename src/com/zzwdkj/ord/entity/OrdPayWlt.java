package com.zzwdkj.ord.entity;

import com.hckj.framework.entity.GenericEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
* ord_pay_wlt, 订单_支付_钱包支付记录表
*
* @author guoxianwei  2016-09-30 12:25:04
*/
public class OrdPayWlt extends GenericEntity{
    private String id;          //ID
    private String walletid;          //支付钱包
    private String name; //会员姓名
    private java.math.BigDecimal money;          //支付金额
    private Integer state;          //@@支付状态(0失败； 1成功；2处理中）
    private String payno;          //支付凭证号
    private String resvAcct;          //收款账号
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public OrdPayWlt() {
    }

    public OrdPayWlt(String walletid, BigDecimal money, String payno, String resvAcct) {
        this.walletid=walletid;
        this.money=money;
        this.payno=payno;
        this.resvAcct=resvAcct;
        this.crtime = new Date();
        this.uptime = new Date();
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id=id;
    }
    public String getWalletid(){
        return this.walletid;
    }

    public void setWalletid(String walletid){
        this.walletid=walletid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public java.math.BigDecimal getMoney(){
        return this.money;
    }

    public void setMoney(java.math.BigDecimal money){
        this.money=money;
    }
    public Integer getState(){
        return this.state;
    }

    public void setState(Integer state){
        this.state=state;
    }
    public String getPayno(){
        return this.payno;
    }

    public void setPayno(String payno){
        this.payno=payno;
    }
    public String getResvAcct(){
        return this.resvAcct;
    }

    public void setResvAcct(String resvAcct){
        this.resvAcct=resvAcct;
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