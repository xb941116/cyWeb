package com.zzwdkj.gprs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hckj.framework.entity.GenericEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * gprs_model, GPRS_模块表
 *
 * @author guoxianwei  2016-09-20 16:37:51
 */
public class GprsModel extends GenericEntity {
    private String id;          //ID
    private String name;          //名称
    private String gprsNo;          //GPRS模块号
    private String mo;          //模块版本号
    private String so;          //软件版本号
    private String sn;          //产品序列号
    private Integer bind;          //是否绑定(0;否；1是）
    private Integer state;          //状态(0未出售；1已出售；2 已激活）
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date factoryDate;          //出厂日期
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private java.util.Date crtime;          //创建时间chan
    private java.util.Date uptime;          //更新时间
    private String stateStr;
    private String bindStr;
    private String bizNo;          //商家编号
    private String bizName;
    private Integer online;
    private String onlineStr;
    private String pos;
    private String prodName;
    private String prodSet;
    private Integer instlaState;//模块设置位置状态

    public GprsModel() {
    }

    public GprsModel(String gprsNo) {
        this.gprsNo = gprsNo;
        this.bind = 0;
        this.state = 0;
        this.crtime = new Date();
        this.uptime = new Date();
    }

    /**
     * 状态枚举
     */
    public static enum St {
        NEWLY(0, "未出售"), SALED(1, "已出售"), ACTIVE(2, "已激活");

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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGprsNo() {
        return this.gprsNo;
    }

    public void setGprsNo(String gprsNo) {
        this.gprsNo = gprsNo;
    }

    public String getMo() {
        return this.mo;
    }

    public void setMo(String mo) {
        this.mo = mo;
    }

    public String getSo() {
        return this.so;
    }

    public void setSo(String so) {
        this.so = so;
    }

    public String getSn() {
        return this.sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Integer getBind() {
        return this.bind;
    }

    public void setBind(Integer bind) {
        this.bind = bind;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public java.util.Date getFactoryDate() {
        return this.factoryDate;
    }

    public void setFactoryDate(java.util.Date factoryDate) {
        this.factoryDate = factoryDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public java.util.Date getCrtime() {
        return this.crtime;
    }

    public void setCrtime(java.util.Date crtime) {
        this.crtime = crtime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public java.util.Date getUptime() {
        return this.uptime;
    }

    public void setUptime(java.util.Date uptime) {
        this.uptime = uptime;
    }

    public String getStateStr() {
        if (state != null) {
            stateStr = St.valueOf(state).getVal();
        }
        return stateStr;
    }

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getBindStr() {
        if (bind != null) {
            bindStr = bind == 1 ? "已绑定" : "未绑定";
        } else {
            bindStr = "未绑定";
        }
        return bindStr;
    }

    public String getOnlineStr() {
        if (online != null) {
            onlineStr = online == 1 ? "在线" : "离线";
        } else {
            if (gprsNo.startsWith("2")){
                onlineStr = "在线";
            }else {
                onlineStr = "离线";
            }
        }
        return onlineStr;
    }


    public Integer getInstlaState() {
        return instlaState;
    }

    public void setInstlaState(Integer instlaState) {
        this.instlaState = instlaState;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdSet() {
        return prodSet;
    }

    public void setProdSet(String prodSet) {
        this.prodSet = prodSet;
    }
}