package com.zzwdkj.prod.entity;

import com.hckj.framework.entity.GenericEntity;

/**
 * prod_coin_rpt_log,
 *
 * @author guoxianwei  2017-02-19 21:00:59
 */
public class ProdCoinRptLog extends GenericEntity{
    private String id;          //
    private String bizNo;          //产品编号
    private Integer type;          //类型（1刷卡投币上报2BUG上报）
    private Integer times;          //GPRS模块号
    private java.util.Date crtime;          //
    private java.util.Date uptime;          //产品收入上报日志

    public ProdCoinRptLog() {
    }

    /**
     * 状态枚举
     */
    public static enum St {
        CoinRpt(1, "刷卡投币上报"), BugRpt(2, "BUG上报");

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

        public static ProdCoinRptLog.St valueOf(Integer key) {
            for (ProdCoinRptLog.St st : ProdCoinRptLog.St.values()) {
                if (key != null && st.key.equals(key)) {
                    return st;
                }
            }
            return null;
        }
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
    public Integer getTimes(){
        return this.times;
    }

    public void setTimes(Integer times){
        this.times=times;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}