package com.zzwdkj.biz.entity;

import com.hckj.framework.entity.GenericEntity;

import java.util.Date;

/**
 * biz_advise, 商家_申诉建议
 *
 * @author guoxianwei  2016-10-10 11:38:07
 */
public class BizAdvise extends GenericEntity {
    private String id;          //ID
    private String bizNo;          //商家编号
    private String acct;          //反馈账户
    private String advise;          //反馈意见
    private Integer state;          //状态
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间
    private String stateStr;          //状态

    public BizAdvise(String bizNo, String acct, String advise) {
        this.bizNo = bizNo;
        this.acct = acct;
        this.advise = advise;
        this.crtime = new Date();
        this.uptime = new Date();
        this.state = 0;
    }

    /**
     * 状态枚举 状态(1等待付款；2 已付款；3交易完成；4 交易超时关闭）
     */
    public static enum St {
        WAIT_PROC(0, "待处理"), PROCED(1, "已处理");

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

    public String getAcct() {
        return this.acct;
    }

    public void setAcct(String acct) {
        this.acct = acct;
    }

    public String getAdvise() {
        return this.advise;
    }

    public void setAdvise(String advise) {
        this.advise = advise;
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

    public String getStateStr() {
        if (state != null)
            stateStr = St.valueOf(state).getVal();
        return stateStr;
    }
}