package com.zzwdkj.biz.entity;

import com.hckj.framework.entity.GenericEntity;

/**
* biz_wx_focus, 商家_微信_强制关注
*
* @author guoxianwei  2016-09-07 15:01:34
*/
public class BizWxFocus extends GenericEntity{
    private String id;          //ID
    private String bizNo;          //商家编号
    private String name;          //名称
    private String appId;          //应用ID
    private String appSkey;          //应用密钥
    private String pubAcctId;          //公众号原始ID
    private String optor;          //操作人
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public BizWxFocus() {
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
    public String getAppId(){
        return this.appId;
    }

    public void setAppId(String appId){
        this.appId=appId;
    }
    public String getAppSkey(){
        return this.appSkey;
    }

    public void setAppSkey(String appSkey){
        this.appSkey=appSkey;
    }
    public String getPubAcctId(){
        return this.pubAcctId;
    }

    public void setPubAcctId(String pubAcctId){
        this.pubAcctId=pubAcctId;
    }
    public String getOptor(){
        return this.optor;
    }

    public void setOptor(String optor){
        this.optor=optor;
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