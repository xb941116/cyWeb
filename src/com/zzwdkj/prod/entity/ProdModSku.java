package com.zzwdkj.prod.entity;

import com.hckj.framework.entity.GenericEntity;

/**
* prod_mod_sku, 商品_模板_SKU
*
* @author guoxianwei  2016-09-12 12:51:12
*/
public class ProdModSku extends GenericEntity{
    private String id;          //ID
    private String bizNo;          //商家编号
    private String sku;          //SKU
    private String name;          //名称
    private String attrVals;          //属性集合
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public ProdModSku() {
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
    public String getSku(){
        return this.sku;
    }

    public void setSku(String sku){
        this.sku=sku;
    }
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name=name;
    }
    public String getAttrVals(){
        return this.attrVals;
    }

    public void setAttrVals(String attrVals){
        this.attrVals=attrVals;
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