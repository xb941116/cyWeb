package com.zzwdkj.mbr.entity;

import com.hckj.framework.entity.GenericEntity;

import java.math.BigDecimal;

/**
* mbr, 会员
*
* @author guoxianwei  2016-09-07 17:50:58
*/
public class Mbr extends GenericEntity{
    private String id;          //主键
    private String bizNo;          //商家编号
    private String name;          //姓名
    private String nick;          //昵称
    private String headImg;          //头像
    private String mobile;          //手机号码
    private String pwd;          //登录密码
    private String payPwd;          //支付密码
    private String email;          //常用邮箱
    private String local;          //归属地
    private Integer state;          //状态(0 新注册；1已激活；2被禁用）
    private String optor;          //操作人
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间


    private java.math.BigDecimal money;          //余额

    public Mbr() {
    }

    /**
     * 状态枚举
     */
    public static enum St {
        NEWRG(0, "新注册"),NORMAL(1, "已激活"), DISABLE(2, "被禁用");

        private Integer key;

        private String val;

        St(Integer key, String val) {
            this.key = key;
            this.val = val;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        public static St valueOf(Integer key) {
            for (St st : St.values()) {
                if (key != null && st.key.equals(key)) {
                    return st;
                }
            }
            return null;
        }
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

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
    public String getNick(){
        return this.nick;
    }

    public void setNick(String nick){
        this.nick=nick;
    }
    public String getHeadImg(){
        return this.headImg;
    }

    public void setHeadImg(String headImg){
        this.headImg=headImg;
    }
    public String getMobile(){
        return this.mobile;
    }

    public void setMobile(String mobile){
        this.mobile=mobile;
    }
    public String getPwd(){
        return this.pwd;
    }

    public void setPwd(String pwd){
        this.pwd=pwd;
    }
    public String getPayPwd(){
        return this.payPwd;
    }

    public void setPayPwd(String payPwd){
        this.payPwd=payPwd;
    }
    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email=email;
    }
    public String getLocal(){
        return this.local;
    }

    public void setLocal(String local){
        this.local=local;
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

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}