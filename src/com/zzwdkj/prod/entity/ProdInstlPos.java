package com.zzwdkj.prod.entity;

import com.hckj.framework.entity.GenericEntity;
import com.zzwdkj.common.Std;

/**
* prod_instl_pos, 产品_安装位置表
*
* @author guoxianwei  2016-09-12 12:51:04
*/
public class ProdInstlPos extends GenericEntity{
    private String id;          //ID
    private String bizNo;          //商家编号
    private String prodNo;          //产品编号
    private String pos;          //详细位置
    private String addr;          //位置安装人员补充填写（包括小区，单元，楼号）
    private String prov;          //所在省份
    private String city;          //所在地市
    private String dist;          //所在区县
    private String provName;          //所在省份名称
    private String cityName;          //所在地市名称
    private String distName;          //所在区县名称
    private String lng;          //经度值
    private String lat;          //维度值
    private String manager;          //设备负责人
    private String tel;          //负责人电话
    private String remark;          //备注
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    private String prodName; //产品名称
    private String bizName;//商家编号
    private String gprsNo;//gprs模块编号
    private Integer onlineState;//状态(0 掉线；1在线）
    private Integer type;          //商品类型
    private String typeStr;  //商品类型

    public ProdInstlPos() {
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
    public String getPos(){
        return this.pos;
    }

    public void setPos(String pos){
        this.pos=pos;
    }
    public String getAddr(){
        return this.addr;
    }

    public void setAddr(String addr){
        this.addr=addr;
    }
    public String getProv(){
        return this.prov;
    }

    public void setProv(String prov){
        this.prov=prov;
    }
    public String getCity(){
        return this.city;
    }

    public void setCity(String city){
        this.city=city;
    }
    public String getDist(){
        return this.dist;
    }

    public void setDist(String dist){
        this.dist=dist;
    }
    public String getProvName(){
        return this.provName;
    }

    public void setProvName(String provName){
        this.provName=provName;
    }
    public String getCityName(){
        return this.cityName;
    }

    public void setCityName(String cityName){
        this.cityName=cityName;
    }
    public String getDistName(){
        return this.distName;
    }

    public void setDistName(String distName){
        this.distName=distName;
    }
    public String getLng(){
        return this.lng;
    }

    public void setLng(String lng){
        this.lng=lng;
    }
    public String getLat(){
        return this.lat;
    }

    public void setLat(String lat){
        this.lat=lat;
    }
    public String getManager(){
        return this.manager;
    }

    public void setManager(String manager){
        this.manager=manager;
    }
    public String getTel(){
        return this.tel;
    }

    public void setTel(String tel){
        this.tel=tel;
    }
    public String getRemark(){
        return this.remark;
    }

    public void setRemark(String remark){
        this.remark=remark;
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

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public String getGprsNo() {
        return gprsNo;
    }

    public void setGprsNo(String gprsNo) {
        this.gprsNo = gprsNo;
    }

    public Integer getOnlineState() {
        return onlineState;
    }

    public void setOnlineState(Integer onlineState) {
        this.onlineState = onlineState;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }
}