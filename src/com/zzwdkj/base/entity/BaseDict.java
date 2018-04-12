package com.zzwdkj.base.entity;

import java.io.Serializable;

/**
 * base_dict,
 *
 * @author guoxianwei  2017-03-07 16:46:28
 */
public class BaseDict implements Serializable {

    private String id;          //主键
    private String type;          //类别
    private String code;          //字典编码
    private String val;          //字典值
    private Integer seqs;          //排序
    private Integer is_parent;          //父节点
    private String descr;          //描述
    private String createby;          //创建人员(登陆账户)
    private String updateby;          //修改人员
    private java.util.Date createtime;          //创建时间
    private java.util.Date updatetime;          //修改时间

    public BaseDict() {
    }

    public static enum DictType {

        PROD_TYPE("70012", "商品类型");

        private String key;
        private String val;

        public String getKey() {
            return this.key;
        }

        public String getVal() {
            return val;
        }

        DictType(String key, String val) {
            this.key = key;
            this.val = val;
        }

    }
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVal() {
        return this.val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public Integer getSeqs() {
        return this.seqs;
    }

    public void setSeqs(Integer seqs) {
        this.seqs = seqs;
    }

    public Integer getIs_parent() {
        return this.is_parent;
    }

    public void setIs_parent(Integer is_parent) {
        this.is_parent = is_parent;
    }

    public String getCreateby() {
        return this.createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public String getUpdateby() {
        return this.updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby;
    }

    public java.util.Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(java.util.Date createtime) {
        this.createtime = createtime;
    }

    public java.util.Date getUpdatetime() {
        return this.updatetime;
    }

    public void setUpdatetime(java.util.Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}