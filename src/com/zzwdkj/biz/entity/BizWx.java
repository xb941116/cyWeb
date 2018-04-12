package com.zzwdkj.biz.entity;

import com.hckj.framework.entity.GenericEntity;

import java.math.BigDecimal;

/**
 * biz_wx, 商家_微信
 *
 * @author guoxianwei  2016-09-07 15:01:33
 */
public class BizWx extends GenericEntity {
    private String id;          //ID
    private String bizNo;          //商家编号
    private String templateId;          //微信消息模板
    private String appId;          //应用ID
    private String appSkey;          //应用密钥
    private String pubAcctId;          //公众号原始ID
    private String bizNum;          //商户号
    private String apiSkey;          //API密钥
    private String apiCert;          //API证书
    private String apiCertSkey;          //证书秘钥
    private java.math.BigDecimal giroFee;          //转账手续费率
    private java.math.BigDecimal giroQuota;          //转账限额
    private Integer focus;          //强制关注
    private Integer state;          //状态
    private String optor;          //操作人
    private String bizName;          //企业名称
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public BizWx() {
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

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSkey() {
        return this.appSkey;
    }

    public void setAppSkey(String appSkey) {
        this.appSkey = appSkey;
    }

    public BigDecimal getGiroFee() {
        return giroFee;
    }

    public void setGiroFee(BigDecimal giroFee) {
        this.giroFee = giroFee;
    }

    public BigDecimal getGiroQuota() {
        return giroQuota;
    }

    public void setGiroQuota(BigDecimal giroQuota) {
        this.giroQuota = giroQuota;
    }

    public String getPubAcctId() {
        return this.pubAcctId;
    }

    public void setPubAcctId(String pubAcctId) {
        this.pubAcctId = pubAcctId;
    }

    public String getBizNum() {
        return this.bizNum;
    }

    public void setBizNum(String bizNum) {
        this.bizNum = bizNum;
    }

    public String getApiSkey() {
        return this.apiSkey;
    }

    public void setApiSkey(String apiSkey) {
        this.apiSkey = apiSkey;
    }

    public String getApiCert() {
        return this.apiCert;
    }

    public void setApiCert(String apiCert) {
        this.apiCert = apiCert;
    }

    public String getApiCertSkey() {
        return apiCertSkey;
    }

    public void setApiCertSkey(String apiCertSkey) {
        this.apiCertSkey = apiCertSkey;
    }

    public Integer getFocus() {
        return this.focus;
    }

    public void setFocus(Integer focus) {
        this.focus = focus;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getOptor() {
        return this.optor;
    }

    public void setOptor(String optor) {
        this.optor = optor;
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

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }
}