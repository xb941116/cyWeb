package com.zzwdkj.ord.entity;

import com.hckj.framework.entity.GenericEntity;

/**
* ord_item, 订单_客户_订单商品项
*
* @author guoxianwei  2016-09-07 15:01:45
*/
public class OrdItem extends GenericEntity{
    private String id;          //ID
    private String ordno;          //订单号
    private String prodName;          //商品名称
    private java.math.BigDecimal originalPrice;          //原价
    private java.math.BigDecimal price;          //价格
    private Integer amount;          //购买数量
    private Integer gift;          //是否赠品(0否；1是）
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public OrdItem() {
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
    public String getProdName(){
        return this.prodName;
    }

    public void setProdName(String prodName){
        this.prodName=prodName;
    }
    public java.math.BigDecimal getOriginalPrice(){
        return this.originalPrice;
    }

    public void setOriginalPrice(java.math.BigDecimal originalPrice){
        this.originalPrice=originalPrice;
    }
    public java.math.BigDecimal getPrice(){
        return this.price;
    }

    public void setPrice(java.math.BigDecimal price){
        this.price=price;
    }
    public Integer getAmount(){
        return this.amount;
    }

    public void setAmount(Integer amount){
        this.amount=amount;
    }
    public Integer getGift(){
        return this.gift;
    }

    public void setGift(Integer gift){
        this.gift=gift;
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