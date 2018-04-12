package com.zzwdkj.prod.entity;

import com.hckj.framework.entity.GenericEntity;

/**
* prod_base_args, 商品_基本属性
*
* @author guoxianwei  2016-09-20 16:37:58
*/
public class ProdBaseArgs extends GenericEntity{
    private String id;          //ID
    private String bizNo;          //商家编号
    private Integer local;          //本地保存
    private Integer ext;          //扩展口
    private Integer coinRt;          //出笔速度
    private Integer coinIntl;          //出币间隔
    private Integer extPulseRt;          //扩展口脉冲速度
    private Integer extPulseIntl;          //扩展口脉冲间隔
    private Integer used;          //是否下发
    private String optor;          //操作人
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public ProdBaseArgs() {
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
    public Integer getLocal(){
        return this.local;
    }

    public void setLocal(Integer local){
        this.local=local;
    }
    public Integer getExt(){
        return this.ext;
    }

    public void setExt(Integer ext){
        this.ext=ext;
    }
    public Integer getCoinRt(){
        return this.coinRt;
    }

    public void setCoinRt(Integer coinRt){
        this.coinRt=coinRt;
    }
    public Integer getCoinIntl(){
        return this.coinIntl;
    }

    public void setCoinIntl(Integer coinIntl){
        this.coinIntl=coinIntl;
    }
    public Integer getExtPulseRt(){
        return this.extPulseRt;
    }

    public void setExtPulseRt(Integer extPulseRt){
        this.extPulseRt=extPulseRt;
    }
    public Integer getExtPulseIntl(){
        return this.extPulseIntl;
    }

    public void setExtPulseIntl(Integer extPulseIntl){
        this.extPulseIntl=extPulseIntl;
    }
    public Integer getUsed(){
        return this.used;
    }

    public void setUsed(Integer used){
        this.used=used;
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
}