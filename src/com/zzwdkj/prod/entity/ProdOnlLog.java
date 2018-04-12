package com.zzwdkj.prod.entity;

import com.hckj.framework.entity.GenericEntity;
import com.zzwdkj.common.DateUtil;

import java.util.Date;

/**
 * prod_onl_log, 产品_在线记录表
 *
 * @author guoxianwei  2016-09-12 12:51:17
 */
public class ProdOnlLog extends GenericEntity {
    private String id;          //ID
    private String bizNo;          //商家编号
    private String prodNo;          //产品编号
    private String gpno;          //模块编号
    private java.util.Date firstOnlineTime;          //首次上线时间
    private java.util.Date curOnlineTime;          //本次上线时间
    private java.util.Date offLineTime;          //掉线时间
    private Integer state;          //状态(0 掉线；1在线）
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间
    private String offLineTimes;          //掉线时长
    private String onLineTimes;          //在线时长

    private String pos;          //掉线时长
    private String addr;          //掉线时长
    private String provName;          //掉线时长
    private String cityName;          //掉线时长
    private String distName;          //掉线时长
    private String gprsNo;
    public ProdOnlLog() {
    }

    public ProdOnlLog(String bizNo, String prodNo, String gpno) {
        this.bizNo = bizNo;
        this.prodNo = prodNo;
        this.gpno = gpno;
        this.crtime = new Date();
        this.uptime = crtime;
        this.firstOnlineTime = crtime;
        this.curOnlineTime = crtime;
        this.state = 1;
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

    public String getProdNo() {
        return this.prodNo;
    }

    public void setProdNo(String prodNo) {
        this.prodNo = prodNo;
    }

    public String getGpno() {
        return this.gpno;
    }

    public void setGpno(String gpno) {
        this.gpno = gpno;
    }

    public java.util.Date getFirstOnlineTime() {
        return this.firstOnlineTime;
    }

    public void setFirstOnlineTime(java.util.Date firstOnlineTime) {
        this.firstOnlineTime = firstOnlineTime;
    }

    public java.util.Date getCurOnlineTime() {
        return this.curOnlineTime;
    }

    public void setCurOnlineTime(java.util.Date curOnlineTime) {
        this.curOnlineTime = curOnlineTime;
    }

    public java.util.Date getOffLineTime() {
        return this.offLineTime;
    }

    public void setOffLineTime(java.util.Date offLineTime) {
        this.offLineTime = offLineTime;
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

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
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

    public String getOffLineTimes() {
        if(offLineTime!=null){
            Date curDate = new Date();
           long times  = DateUtil.minuteDifference(curDate,this.offLineTime);
            if(times<1){
                offLineTimes = "刚刚";
            }else {
                offLineTimes = times+ "分钟";
            }

            times  = DateUtil.hourDifference(curDate,this.offLineTime);
            if(times>1){
                offLineTimes = times+ "小时";
            }

            times  = DateUtil.daysDifference(curDate, this.offLineTime);
            if(times>1){
                offLineTimes = times+ "天";
            }
        }
        return offLineTimes;
    }

    public String getOnLineTimes() {
        if(curOnlineTime!=null){
            Date curDate = new Date();
            long times  = DateUtil.minuteDifference(curDate,this.curOnlineTime);
            if(times<1){
                onLineTimes = "刚刚";
            }else {
                onLineTimes = times+ "分钟";
            }

            times  = DateUtil.hourDifference(curDate,this.curOnlineTime);
            if(times>1){
                onLineTimes = times+ "小时";
            }

            times  = DateUtil.daysDifference(curDate, this.curOnlineTime);
            if(times>1){
                onLineTimes = times+ "天";
            }
        }
        return onLineTimes;
    }

    public String getGprsNo() {
        return gprsNo;
    }

    public void setGprsNo(String gprsNo) {
        this.gprsNo = gprsNo;
    }
}