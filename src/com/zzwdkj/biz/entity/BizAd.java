package com.zzwdkj.biz.entity;

import com.hckj.framework.entity.GenericEntity;
import com.zzwdkj.common.DateUtil;

/**
* biz_ad, 商家广告表
*
* @author guoxianwei  2017-04-22 12:12:22
*/
public class BizAd extends GenericEntity{
    private String id;          //主键
    private String bizNo;          //商家编号
    private Integer adType;          //广告类型（1支付完成界面）
    private Integer adSeat;          //广告位置(1第一幅,2第二幅）
    private String title;          //标题
    private String content;          //内容
    private String srcUrl;          //超链接
    private String imgUrl;          //图片地址
    private Integer counts; //超链接被访问的次数
    private java.util.Date startTime;          //起始时间
    private java.util.Date endTime;          //结束时间
    private Integer state;          //开启状态（0关闭1开启）
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //修改时间

    private String bizName;
    private Integer yearLength;

    public BizAd() {
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
    public Integer getAdType(){
        return this.adType;
    }

    public void setAdType(Integer adType){
        this.adType=adType;
    }
    public Integer getAdSeat(){
        return this.adSeat;
    }

    public void setAdSeat(Integer adSeat){
        this.adSeat=adSeat;
    }
    public String getTitle(){
        return this.title;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public void setTitle(String title){
        this.title=title;
    }
    public String getContent(){
        return this.content;
    }

    public void setContent(String content){
        this.content=content;
    }
    public String getSrcUrl(){
        return this.srcUrl;
    }

    public void setSrcUrl(String srcUrl){
        this.srcUrl=srcUrl;
    }
    public String getImgUrl(){
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl){
        this.imgUrl=imgUrl;
    }
    public java.util.Date getStartTime(){
        return this.startTime;
    }

    public void setStartTime(java.util.Date startTime){
        this.startTime=startTime;
    }
    public java.util.Date getEndTime(){
        return this.endTime;
    }

    public void setEndTime(java.util.Date endTime){
        this.endTime=endTime;
    }
    public Integer getState(){
        return this.state;
    }

    public void setState(Integer state){
        this.state=state;
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

    public Integer getYearLength() {
        return DateUtil.getYear(this.endTime)-DateUtil.getYear(this.startTime);
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }
}