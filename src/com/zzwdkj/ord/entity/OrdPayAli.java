package com.zzwdkj.ord.entity;

import com.hckj.framework.entity.GenericEntity;

/**
* ord_pay_ali, 订单_支付_支付宝支付记录表
*
* @author guoxianwei  2016-09-30 12:25:05
*/
public class OrdPayAli extends GenericEntity{
    private String id;          //
    private String acct;          //支付宝账号
    private java.math.BigDecimal money;          //支付金额
    private Integer state;          //@@支付状态(0失败； 1成功；2处理中）
    private String payno;          //支付凭证号
    private String resvAcct;          //收款账号
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public OrdPayAli() {
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id=id;
    }
    public String getAcct(){
        return this.acct;
    }

    public void setAcct(String acct){
        this.acct=acct;
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