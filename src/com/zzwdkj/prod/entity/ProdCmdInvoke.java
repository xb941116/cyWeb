package com.zzwdkj.prod.entity;

import com.hckj.framework.entity.GenericEntity;

/**
* prod_cmd_invoke, 产品_高级命令下发表
*
* @author guoxianwei  2016-09-20 16:37:59
*/
public class ProdCmdInvoke extends GenericEntity{
    private String id;          //ID
    private String bizNo;          //商家编号
    private String prodNo;          //产品编号
    private String code;          //命令代码
    private String cmd;          //命令
    private String param;          //参数
    private String name;          //名称
    private String respCode;          //响应码
    private String optor;          //操作人
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    public ProdCmdInvoke() {
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
    public String getCode(){
        return this.code;
    }

    public void setCode(String code){
        this.code=code;
    }
    public String getCmd(){
        return this.cmd;
    }

    public void setCmd(String cmd){
        this.cmd=cmd;
    }
    public String getParam(){
        return this.param;
    }

    public void setParam(String param){
        this.param=param;
    }
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name=name;
    }
    public String getRespCode(){
        return this.respCode;
    }

    public void setRespCode(String respCode){
        this.respCode=respCode;
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