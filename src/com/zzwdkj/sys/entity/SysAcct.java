package com.zzwdkj.sys.entity;

import com.hckj.framework.entity.GenericEntity;

/**
 * sys_acct, 权限_系统账号
 *
 * @author guoxianwei  2016-09-07 15:02:11
 */
public class SysAcct extends GenericEntity {
    private String id;          //ID
    private String bizNo;          //商家编码
    private String acct;          //账号
    private String name;          //姓名
    private String pwd;          //密码
    private String email;          //邮箱
    private String mobile;          //手机
    private String tel;          //电话
    private Integer sex;          //@性别
    private Integer state;          //@@状态(0 待审核；1正常；2禁用）
    private Integer mainAcct;          //是否主账号(0否；1是）
    private Integer admin;          //是否管理员(0否；1是）
    private String img;          //头像
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间
    private String stateStr;          //@@状态(0 待审核；1正常；2禁用）

    private String res; //账户资源



    public SysAcct() {
    }

    public SysAcct(String acct,String bizNo, String name, String mobile, String pwd, Integer mainAcct) {
        this.acct = acct;
        this.bizNo = bizNo;
        this.name = name;
        this.mainAcct = mainAcct;
        this.mobile = mobile;
        this.pwd = pwd;
        this.admin = 0;
    }

    /**
     * 状态枚举 @@状态(0 待审核；1正常；2禁用）
     */
    public static enum St {
        WAIT_AUDIT(0, "待审核"), NORMAL(1, "正常"), DISABLED(2, "禁用");

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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return this.pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Integer getSex() {
        return this.sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getMainAcct() {
        return this.mainAcct;
    }

    public void setMainAcct(Integer mainAcct) {
        this.mainAcct = mainAcct;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public String getStateStr() {
        if(state!=null){
            stateStr = St.valueOf(state).getVal();
        }
        return stateStr;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }
}