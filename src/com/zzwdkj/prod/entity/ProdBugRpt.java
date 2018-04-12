package com.zzwdkj.prod.entity;

import com.hckj.framework.entity.GenericEntity;

import java.util.Date;

/**
* prod_bug_rpt, 产品_故障_报告
*
* @author guoxianwei  2016-09-30 11:02:44
*/
public class ProdBugRpt extends GenericEntity{
    private String id;          //ID
    private String bizNo;          //商家编号
    private String prodNo;          //产品编号
    private String result;          //故障结果
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public ProdBugRpt() {
    }

    public ProdBugRpt(String bizNo,String prodNo,String result) {
        this.bizNo = bizNo;
        this.prodNo = prodNo;
        this.result = result;
        this.crtime = new Date();
        this.uptime = new Date();
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id=id;
    }
    public String getBizNo(){
        return this.bizNo;
    }

    public void setBizNo(String bizNo){
        this.bizNo=bizNo;
    }
    public String getProdNo(){
        return this.prodNo;
    }

    public void setProdNo(String prodNo){
        this.prodNo=prodNo;
    }
    public String getResult(){
        return this.result;
    }

    public void setResult(String result){
        this.result=result;
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