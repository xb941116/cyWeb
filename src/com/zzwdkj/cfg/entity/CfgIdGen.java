package com.zzwdkj.cfg.entity;

import com.hckj.framework.entity.GenericEntity;

/**
* cfg_id_gen, 系统_主键生成策略表
*
* @author guoxianwei  2016-09-20 16:37:51
*/
public class CfgIdGen extends GenericEntity{
    private String id;          //ID
    private String tbl;          //表名
    private Integer val;          //当前值
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public CfgIdGen() {
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id=id;
    }
    public String getTbl(){
        return this.tbl;
    }

    public void setTbl(String tbl){
        this.tbl=tbl;
    }
    public Integer getVal(){
        return this.val;
    }

    public void setVal(Integer val){
        this.val=val;
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