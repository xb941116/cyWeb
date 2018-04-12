package com.zzwdkj.prod.entity;

import com.hckj.framework.entity.GenericEntity;

/**
* prod_sp_arg_vals, 产品_特定参数值表
*
* @author guoxianwei  2016-09-29 17:00:55
*/
public class ProdSpArgVals extends GenericEntity{
    private String id;          //ID
    private String bizNo;          //商家编号
    private String prodNo;          //产品编号
    private String paramId;          //参数ID
    private String valEn;          //值英文
    private String valCn;          //值中文
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public ProdSpArgVals() {
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
    public String getParamId(){
        return this.paramId;
    }

    public void setParamId(String paramId){
        this.paramId=paramId;
    }
    public String getValEn(){
        return this.valEn;
    }

    public void setValEn(String valEn){
        this.valEn=valEn;
    }
    public String getValCn(){
        return this.valCn;
    }

    public void setValCn(String valCn){
        this.valCn=valCn;
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