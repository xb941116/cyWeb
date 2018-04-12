package com.zzwdkj.biz.entity;

import com.hckj.framework.entity.GenericEntity;
import com.zzwdkj.common.Std;

/**
* biz_gift, 满赠活动
*
* @author guoxianwei  2016-11-11 17:52:57
*/
public class BizGift extends GenericEntity{
    private String id;          //ID
    private String bizNo;          //商家编号
    private String content;          //活动说明
    private Integer coin;          //达标额度
    private Integer money;          //赠送积分额度（修改人：苏方宁；时间：12.1）
    private Integer totalMbr;          //每个会员限制次数（设置为0则不限制）
    private Integer totalAll;          //活动总次数（若设置为则0不限次数）
    private Integer stockAll;          //活动剩余总次数
    private Integer state;          //@@状态（0 未开启； 1 开启）
    private Integer sort;          //排序
    private java.util.Date startDate;          //到期时间
    private java.util.Date overDate;          //到期时间
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    private  String stateStr;

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public BizGift() {
    }
    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCoin(){
        return this.coin;
    }

    public Integer getTotalMbr() {
        return totalMbr;
    }

    public void setTotalMbr(Integer totalMbr) {
        this.totalMbr = totalMbr;
    }

    public Integer getTotalAll() {
        return totalAll;
    }

    public void setTotalAll(Integer totalAll) {
        this.totalAll = totalAll;
    }

    public Integer getStockAll() {
        return stockAll;
    }

    public void setStockAll(Integer stockAll) {
        this.stockAll = stockAll;
    }

    public Integer getState(){
        return this.state;
    }

    public void setState(Integer state){
        this.state=state;
    }
    public java.util.Date getStartDate(){
        return this.startDate;
    }

    public void setStartDate(java.util.Date startDate){
        this.startDate=startDate;
    }
    public java.util.Date getOverDate(){
        return this.overDate;
    }

    public void setOverDate(java.util.Date overDate){
        this.overDate=overDate;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }


    public String getStateStr() {
        if (state != null) {
            stateStr = Std.YN.valueOf(state).getVal();
        }
        return stateStr;
    }
}