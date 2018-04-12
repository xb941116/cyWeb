package com.zzwdkj.biz.entity;

import com.hckj.framework.entity.GenericEntity;

/**
* biz_wx_walt, 商家_微信零钱包
*
* @author guoxianwei  2016-09-07 15:01:34
*/
public class BizWxWalt extends GenericEntity{
    private String id;          //ID
    private String bizId;          //商家ID
    private String bizAcct;          //银行卡号
    private String nick;          //银行卡名称
    private String name;          //开户行地址
    private Integer bind;          //绑定状态
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public BizWxWalt() {
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id=id;
    }
    public String getBizId(){
        return this.bizId;
    }

    public void setBizId(String bizId){
        this.bizId=bizId;
    }
    public String getBizAcct(){
        return this.bizAcct;
    }

    public void setBizAcct(String bizAcct){
        this.bizAcct=bizAcct;
    }
    public String getNick(){
        return this.nick;
    }

    public void setNick(String nick){
        this.nick=nick;
    }
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name=name;
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