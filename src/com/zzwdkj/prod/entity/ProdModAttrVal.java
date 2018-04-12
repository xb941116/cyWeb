package com.zzwdkj.prod.entity;

import com.hckj.framework.entity.GenericEntity;

/**
* prod_mod_attr_val, 商品_模板_属性值
*
* @author guoxianwei  2016-09-12 12:51:11
*/
public class ProdModAttrVal extends GenericEntity{
    private String id;          //ID
    private String bizNo;          //商家编号
    private String attrCode;          //属性编码
    private String valEn;          //值英文
    private String valCn;          //值中文
    private String unit;          //值单位
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public ProdModAttrVal() {
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
    public String getAttrCode(){
        return this.attrCode;
    }

    public void setAttrCode(String attrCode){
        this.attrCode=attrCode;
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
    public String getUnit(){
        return this.unit;
    }

    public void setUnit(String unit){
        this.unit=unit;
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