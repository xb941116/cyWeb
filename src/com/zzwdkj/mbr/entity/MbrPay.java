package com.zzwdkj.mbr.entity;

import com.hckj.framework.entity.GenericEntity;

/**
* mbr_pay, 客户_支付记录表
*
* @author guoxianwei  2016-09-07 15:01:43
*/
public class MbrPay extends GenericEntity{
    private String id;          //
    private String ordno;          //订单号
    private String memberId;          //客户ID
    private java.math.BigDecimal money;          //应支付金额
    private java.math.BigDecimal realMoney;          //支付金额
    private java.util.Date time;          //支付时间
    private Integer payWay;          //支付方式(1支付宝;2微信;3钱包;4刷卡)
    private String payno;          //支付凭证号
    private java.util.Date crtime;          //创建时间

    public MbrPay() {
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id=id;
    }
    public String getOrdno(){
        return this.ordno;
    }

    public void setOrdno(String ordno){
        this.ordno=ordno;
    }
    public String getMemberId(){
        return this.memberId;
    }

    public void setMemberId(String memberId){
        this.memberId=memberId;
    }
    public java.math.BigDecimal getMoney(){
        return this.money;
    }

    public void setMoney(java.math.BigDecimal money){
        this.money=money;
    }
    public java.math.BigDecimal getRealMoney(){
        return this.realMoney;
    }

    public void setRealMoney(java.math.BigDecimal realMoney){
        this.realMoney=realMoney;
    }
    public java.util.Date getTime(){
        return this.time;
    }

    public void setTime(java.util.Date time){
        this.time=time;
    }
    public Integer getPayWay(){
        return this.payWay;
    }

    public void setPayWay(Integer payWay){
        this.payWay=payWay;
    }
    public String getPayno(){
        return this.payno;
    }

    public void setPayno(String payno){
        this.payno=payno;
    }
    public java.util.Date getCrtime(){
        return this.crtime;
    }

    public void setCrtime(java.util.Date crtime){
        this.crtime=crtime;
    }
}