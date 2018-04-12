package com.zzwdkj.sys.entity;

import com.hckj.framework.entity.GenericEntity;

/**
 * sys_res, 权限_系统资源
 *
 * @author guoxianwei  2016-09-07 15:02:12
 */
public class SysRes extends GenericEntity {
    private String id;          //ID
    private String logo;          //图标
    private String name;          //菜单名称
    private String code;          //菜单编码
    private String uri;          //URI
    private Integer type;          //@菜单类型（1菜单；2按钮）
    private String pcode;          //父菜单
    private Integer sort;          //排序
    private Integer state;          //@@状态（0 无效；1 正常）
    private Integer admin;           //是否管理员菜单 0否；1是
    private String remark;          //备注
    private java.util.Date crtime;          //CRTIME
    private java.util.Date uptime;          //UPTIME
    private boolean checked;

    public SysRes() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPcode() {
        return this.pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}