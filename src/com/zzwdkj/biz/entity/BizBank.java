package com.zzwdkj.biz.entity;

import com.hckj.framework.entity.GenericEntity;

/**
* biz_bank, 商家_银行卡
*
* @author guoxianwei  2016-09-07 15:01:27
*/
public class BizBank extends GenericEntity{
    private String id;          //ID
    private String bizId;          //商家ID
    private String bankNo;          //银行卡号
    private String bankName;          //银行卡名称
    private String bankAddr;          //开户行地址
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public BizBank() {
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id=id;
    }
    public String getBizId(){
        return this.bizId;
    }

    public void setBizId(String bizId){
        this.bizId=bizId;
    }
    public String getBankNo(){
        return this.bankNo;
    }

    public void setBankNo(String bankNo){
        this.bankNo=bankNo;
    }
    public String getBankName(){
        return this.bankName;
    }

    public void setBankName(String bankName){
        this.bankName=bankName;
    }
    public String getBankAddr(){
        return this.bankAddr;
    }

    public void setBankAddr(String bankAddr){
        this.bankAddr=bankAddr;
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