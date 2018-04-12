package com.zzwdkj.biz.entity;

import com.hckj.framework.entity.GenericEntity;
import com.zzwdkj.sys.entity.SysRes;

import java.math.BigDecimal;
import java.util.List;

/**
 * biz, 商家
 *
 * @author guoxianwei  2016-09-07 15:01:26
 */
public class Biz extends GenericEntity {
    private String id;          //ID
    private String bizPushUrl;          //商家回调地址
    private String bizKey;          //商家获取数据key
    private String bizNo;          //商家编号
    private String name;          //商家名称
    private String parBizNo;          //父级商家编号
    private String parBizName;          //父级商家名称
    private Integer grade;          //商家级别(1 一级商家；2二级商家；3代理商家）
    private String mobile;          //手机
    private String tel;          //电话
    private String email;          //邮箱
    private String webSite;          //网址
    private String addr;          //地址
    private String logo;          //LOGO
    private Integer useWxLg;          //是否使用微信头像(0否；1是）
    private String wxLogo;          //微信头像
    private Integer state;          //@@状态（0待审核；1 正常；99  被禁用）
    private BigDecimal takeSet; //可低提现额度
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    private BigDecimal dailyInc;
    private BigDecimal weekInc;
    private BigDecimal monthInc;

    /*修改人：苏方宁  时间：18-03-15*/
    private BigDecimal netInc;//微信收入
    private BigDecimal wltInc;//钱包
    private BigDecimal coinsInc;//积分

    private BigDecimal cardInc;
    private BigDecimal coinInc;

    private BigDecimal totalInc;
    private String online;
    private String acct;
    private String acctName;
    private boolean mainAcct;
    private boolean admin;
    private String headImg;
    private List<SysRes> sysResList;

    public Biz() {
    }

    public Biz(String name, String mobile, String tel, String email, String addr, Integer grade) {
        this.name = name;
        this.mobile = mobile;
        this.tel = tel;
        this.email = email;
        this.addr = addr;
        this.grade = grade;
    }

    public BigDecimal getCoinsInc() {
        return coinsInc;
    }

    public void setCoinsInc(BigDecimal coinsInc) {
        this.coinsInc = coinsInc;
    }

    public BigDecimal getWltInc() {
        return wltInc;
    }

    public void setWltInc(BigDecimal wltInc) {
        this.wltInc = wltInc;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBizPushUrl() {
        return bizPushUrl;
    }

    public void setBizPushUrl(String bizPushUrl) {
        this.bizPushUrl = bizPushUrl;
    }

    public String getBizKey() {
        return bizKey;
    }

    public void setBizKey(String bizKey) {
        this.bizKey = bizKey;
    }

    public String getBizNo() {
        return this.bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParBizNo() {
        return this.parBizNo;
    }

    public void setParBizNo(String parBizNo) {
        this.parBizNo = parBizNo;
    }

    public String getParBizName() {
        return this.parBizName;
    }

    public void setParBizName(String parBizName) {
        this.parBizName = parBizName;
    }

    public Integer getGrade() {
        return this.grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebSite() {
        return this.webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getAddr() {
        return this.addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getUseWxLg() {
        return this.useWxLg;
    }

    public void setUseWxLg(Integer useWxLg) {
        this.useWxLg = useWxLg;
    }

    public String getWxLogo() {
        return this.wxLogo;
    }

    public void setWxLogo(String wxLogo) {
        this.wxLogo = wxLogo;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public BigDecimal getTakeSet() {
        return takeSet;
    }

    public void setTakeSet(BigDecimal takeSet) {
        this.takeSet = takeSet;
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

    public String getAcct() {
        return acct;
    }

    public void setAcct(String acct) {
        this.acct = acct;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public boolean isMainAcct() {
        return mainAcct;
    }

    public void setMainAcct(Integer mainAcct) {
        if (mainAcct != null && mainAcct == 1) {
            this.mainAcct = true;
        }
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        if (admin != null && admin == 1) {
            this.admin = true;
        }
    }

    public BigDecimal getNetInc() {
        return netInc;
    }

    public void setNetInc(BigDecimal netInc) {
        this.netInc = netInc;
    }

    public BigDecimal getCardInc() {
        return cardInc;
    }

    public void setCardInc(BigDecimal cardInc) {
        this.cardInc = cardInc;
    }

    public BigDecimal getCoinInc() {
        return coinInc;
    }

    public void setCoinInc(BigDecimal coinInc) {
        this.coinInc = coinInc;
    }

    public BigDecimal getDailyInc() {
        return dailyInc;
    }

    public void setDailyInc(BigDecimal dailyInc) {
        this.dailyInc = dailyInc;
    }

    public BigDecimal getWeekInc() {
        return weekInc;
    }

    public void setWeekInc(BigDecimal weekInc) {
        this.weekInc = weekInc;
    }

    public BigDecimal getMonthInc() {
        return monthInc;
    }

    public void setMonthInc(BigDecimal monthInc) {
        this.monthInc = monthInc;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public List<SysRes> getSysResList() {
        return sysResList;
    }

    public void setSysResList(List<SysRes> sysResList) {
        this.sysResList = sysResList;
    }

    public BigDecimal getTotalInc() {
        return totalInc;
    }

    public void setTotalInc(BigDecimal totalInc) {
        this.totalInc = totalInc;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }
}