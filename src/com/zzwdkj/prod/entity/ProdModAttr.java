package com.zzwdkj.prod.entity;

import com.hckj.framework.entity.GenericEntity;

/**
* prod_mod_attr, 商品_模板_属性表
*
* @author guoxianwei  2016-09-12 12:51:06
*/
public class ProdModAttr extends GenericEntity{
    private String id;          //
    private String bizNo;          //商家编号
    private String name;          //属性名称
    private String code;          //属性值
    private String type;          //值类型（1文本型；2单选框；3复选框）
    private Integer sort;          //序号
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public ProdModAttr() {
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
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name=name;
    }
    public String getCode(){
        return this.code;
    }

    public void setCode(String code){
        this.code=code;
    }
    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type=type;
    }
    public Integer getSort(){
        return this.sort;
    }

    public void setSort(Integer sort){
        this.sort=sort;
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