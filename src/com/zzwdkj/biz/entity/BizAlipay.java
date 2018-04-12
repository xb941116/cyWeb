package com.zzwdkj.biz.entity;

import com.hckj.framework.entity.GenericEntity;

/**
* biz_alipay, 商家_支付宝
*
* @author guoxianwei  2016-09-07 15:01:27
*/
public class BizAlipay extends GenericEntity{
    private String id;          //ID
    private String bizNo;          //商家编号
    private String partnerId;          //合作者身份ID
    private String bizSkey;          //商户的私钥
    private String alipayPkey;          //支付宝公钥
    private Integer state;          //状态（0 未启用；1已启用）
    private String optor;          //操作人
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public BizAlipay() {
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
    public String getPartnerId(){
        return this.partnerId;
    }

    public void setPartnerId(String partnerId){
        this.partnerId=partnerId;
    }
    public String getBizSkey(){
        return this.bizSkey;
    }

    public void setBizSkey(String bizSkey){
        this.bizSkey=bizSkey;
    }
    public String getAlipayPkey(){
        return this.alipayPkey;
    }

    public void setAlipayPkey(String alipayPkey){
        this.alipayPkey=alipayPkey;
    }
    public Integer getState(){
        return this.state;
    }

    public void setState(Integer state){
        this.state=state;
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