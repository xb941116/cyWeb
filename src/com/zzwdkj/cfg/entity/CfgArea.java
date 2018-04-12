package com.zzwdkj.cfg.entity;

import com.hckj.framework.entity.GenericEntity;

/**
 * cfg_area, 区域字典表
 *
 * @author guoxianwei  2016-10-18 17:18:57
 */
public class CfgArea extends GenericEntity {
    private String id;          //
    private String no;          //
    private String areaName;          //
    private String topno;          //
    private String areaCode;          //
    private String areaLevel;          //
    private String typeName;          //

    public CfgArea() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo() {
        return this.no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getAreaName() {
        return this.areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getTopno() {
        return this.topno;
    }

    public void setTopno(String topno) {
        this.topno = topno;
    }

    public String getAreaCode() {
        return this.areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaLevel() {
        return this.areaLevel;
    }

    public void setAreaLevel(String areaLevel) {
        this.areaLevel = areaLevel;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}