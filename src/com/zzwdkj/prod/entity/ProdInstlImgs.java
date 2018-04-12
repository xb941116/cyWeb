package com.zzwdkj.prod.entity;

import com.hckj.framework.entity.GenericEntity;

/**
* prod_instl_imgs, 产品_安装场地图片表
*
* @author guoxianwei  2016-09-12 12:51:01
*/
public class ProdInstlImgs extends GenericEntity{
    private String id;          //ID
    private String bizNo;          //商家编号
    private String prodNo;          //产品编号
    private String filePath;          //文件路径
    private String mobile;          //上传者手机号码
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public ProdInstlImgs() {
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
    public String getProdNo(){
        return this.prodNo;
    }

    public void setProdNo(String prodNo){
        this.prodNo=prodNo;
    }
    public String getFilePath(){
        return this.filePath;
    }

    public void setFilePath(String filePath){
        this.filePath=filePath;
    }
    public String getMobile(){
        return this.mobile;
    }

    public void setMobile(String mobile){
        this.mobile=mobile;
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