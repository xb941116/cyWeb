package com.zzwdkj.prod.entity;

import com.hckj.framework.entity.GenericEntity;

/**
* prod_gprs_bind, 产品_GPRS_绑定表
*
* @author guoxianwei  2016-09-20 16:38:00
*/
public class ProdGprsBind extends GenericEntity{
    private String id;          //ID
    private String bizNo;          //商家编号
    private String prodNo;          //产品编号
    private String gprsNo;          //GPRS模块号
    private Integer bind;          //是否绑定
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public ProdGprsBind() {
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
    public String getGprsNo(){
        return this.gprsNo;
    }

    public void setGprsNo(String gprsNo){
        this.gprsNo=gprsNo;
    }
    public Integer getBind(){
        return this.bind;
    }

    public void setBind(Integer bind){
        this.bind=bind;
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