package com.zzwdkj.prod.entity;

import com.hckj.framework.entity.GenericEntity;

/**
 * prod_model, 产品_销售__模板表
 *
 * @author guoxianwei  2016-09-21 17:30:04
 */
public class ProdModel extends GenericEntity {
    private String id;          //ID
    private String bizNo;          //商家编号
    private String bizName;          //商家名称
    private String prodMdlNo;          //商品模板编号
    private String prodMdlName;          //商品模板名称
    private String logo;          //图标
    private String sku;          //SKU
    private Integer type;          //商品类型
    private java.math.BigDecimal price;          //单笔价格
    private Integer prodCv;          //脉冲系数
    private Integer otherMoneyState;          //是否开启备选金额
    private String otherMoneyOption;          //备选金额。格式为:3,2,1
    private Integer perCt;          //单笔数量
    private Integer giftCt;          //赠送数量
    private Integer firstFree;          //是否首次免单
    private String spoId;          //优惠活动
    private Integer runTime;          //使用时长（单位分钟）
    private Integer state;          //状态(0不启用；1启用）
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public ProdModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBizNo() {
        return this.bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public String getBizName() {
        return this.bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public String getProdMdlNo() {
        return this.prodMdlNo;
    }

    public void setProdMdlNo(String prodMdlNo) {
        this.prodMdlNo = prodMdlNo;
    }

    public String getProdMdlName() {
        return this.prodMdlName;
    }

    public void setProdMdlName(String prodMdlName) {
        this.prodMdlName = prodMdlName;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSku() {
        return this.sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public java.math.BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(java.math.BigDecimal price) {
        this.price = price;
    }

    public Integer getProdCv() {
        return prodCv;
    }

    public void setProdCv(Integer prodCv) {
        this.prodCv = prodCv;
    }

    public Integer getOtherMoneyState() {
        return otherMoneyState;
    }

    public void setOtherMoneyState(Integer otherMoneyState) {
        this.otherMoneyState = otherMoneyState;
    }

    public String getOtherMoneyOption() {
        return otherMoneyOption;
    }

    public void setOtherMoneyOption(String otherMoneyOption) {
        this.otherMoneyOption = otherMoneyOption;
    }

    public Integer getPerCt() {
        return this.perCt;
    }

    public void setPerCt(Integer perCt) {
        this.perCt = perCt;
    }

    public Integer getGiftCt() {
        return this.giftCt;
    }

    public void setGiftCt(Integer giftCt) {
        this.giftCt = giftCt;
    }

    public Integer getFirstFree() {
        return this.firstFree;
    }

    public void setFirstFree(Integer firstFree) {
        this.firstFree = firstFree;
    }

    public String getSpoId() {
        return this.spoId;
    }

    public void setSpoId(String spoId) {
        this.spoId = spoId;
    }

    public Integer getRunTime() {
        return runTime;
    }

    public void setRunTime(Integer runTime) {
        this.runTime = runTime;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public java.util.Date getCrtime() {
        return this.crtime;
    }

    public void setCrtime(java.util.Date crtime) {
        this.crtime = crtime;
    }

    public java.util.Date getUptime() {
        return this.uptime;
    }

    public void setUptime(java.util.Date uptime) {
        this.uptime = uptime;
    }
}