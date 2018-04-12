package com.zzwdkj.ord.entity;

import com.hckj.framework.entity.GenericEntity;

/**
* ord_pay_return, 订单_退单表
*
* @author guoxianwei  2016-09-07 15:01:46
*/
public class OrdPayReturn extends GenericEntity{
    private String id;          //ID
    private String backno;          //退单号
    private String ordno;          //订单号
    private String memberId;          //客户ID
    private String reason;          //退单原因
    private Integer state;          //状态(1退货申请;2商品返库;3向客户返款;4退货完成)
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public OrdPayReturn() {
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id=id;
    }
    public String getBackno(){
        return this.backno;
    }

    public void setBackno(String backno){
        this.backno=backno;
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
    public String getReason(){
        return this.reason;
    }

    public void setReason(String reason){
        this.reason=reason;
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