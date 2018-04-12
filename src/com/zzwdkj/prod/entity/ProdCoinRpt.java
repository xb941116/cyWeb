package com.zzwdkj.prod.entity;

import com.hckj.framework.entity.GenericEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * prod_coin_rpt, 产品_投币上报表
 *
 * @author guoxianwei  2016-09-20 16:38:00
 */
public class ProdCoinRpt extends GenericEntity {
    private String id;          //ID
    private String bizNo;       //商家编号
    private String prodNo;          //产品编号
    private Integer devType;        //设备类型
    private java.math.BigDecimal money;          //金额
    private BigDecimal totalMoney;
    private Integer isErrorRpt=0;
    private Integer payWay;          //支付方式(1支付宝;2微信;3钱包;4网银;5投币;6刷卡)
    private java.util.Date crtime;          //上报时间
    private java.util.Date uptime;          //更新时间
    private String gprsNo;
    private String addr;
    private String pos;
    private String provName;          //掉线时长
    private String cityName;          //掉线时长
    private String distName;          //掉线时长

    private BigDecimal coinInc = BigDecimal.ZERO;
    private BigDecimal cardInc = BigDecimal.ZERO;


    public ProdCoinRpt() {
    }

    public ProdCoinRpt(String bizNo, String prodNo, Integer payWay, BigDecimal money) {
        this.bizNo = bizNo;
        this.prodNo = prodNo;
        this.payWay = payWay;
        this.money = money == null ? BigDecimal.ZERO : money;
        this.crtime = new Date();
        this.uptime = new Date();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public String getProdNo() {
        return this.prodNo;
    }

    public void setProdNo(String prodNo) {
        this.prodNo = prodNo;
    }

    public java.math.BigDecimal getMoney() {
        return this.money;
    }

    public void setMoney(java.math.BigDecimal money) {
        this.money = money;
    }

    public String getGprsNo() {
        return gprsNo;
    }

    public void setGprsNo(String gprsNo) {
        this.gprsNo = gprsNo;
    }

    public Integer getPayWay() {
        return this.payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
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

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getProvName() {
        return provName;
    }

    public void setProvName(String provName) {
        this.provName = provName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistName() {
        return distName;
    }

    public void setDistName(String distName) {
        this.distName = distName;
    }

    public Integer getDevType() {
        return devType;
    }

    public void setDevType(Integer devType) {
        this.devType = devType;
    }

    public BigDecimal getCoinInc() {
        return coinInc;
    }

    public void setCoinInc(BigDecimal coinInc) {
        this.coinInc = coinInc;
    }

    public BigDecimal getCardInc() {
        return cardInc;
    }

    public void setCardInc(BigDecimal cardInc) {
        this.cardInc = cardInc;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getIsErrorRpt() {
        return isErrorRpt;
    }

    public void setIsErrorRpt(Integer isErrorRpt) {
        this.isErrorRpt = isErrorRpt;
    }
}