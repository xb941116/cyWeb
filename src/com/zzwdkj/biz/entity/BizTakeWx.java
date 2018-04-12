package com.zzwdkj.biz.entity;

import com.hckj.framework.entity.GenericEntity;

import java.util.Date;

/**
* biz_take_wx, 商家_提现_微信自动转账
*
* @author guoxianwei  2016-11-15 09:27:26
*/
public class BizTakeWx extends GenericEntity{
    private String id;          //ID
    private String bizNo;          //商家编号
    private String nick;          //昵称
    private String openid;          //微信openid
    private Integer state;          //自动转账开启状态(0关闭，1开启)
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public BizTakeWx() {
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

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getOpenid(){
        return this.openid;
    }

    public void setOpenid(String openid){
        this.openid=openid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCrtime() {
        return crtime;
    }

    public void setCrtime(Date crtime) {
        this.crtime = crtime;
    }

    public java.util.Date getUptime(){
        return this.uptime;
    }

    public void setUptime(java.util.Date uptime){
        this.uptime=uptime;
    }
}