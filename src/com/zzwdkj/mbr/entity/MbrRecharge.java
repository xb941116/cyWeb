package com.zzwdkj.mbr.entity;

import com.hckj.framework.entity.GenericEntity;
import com.zzwdkj.common.Std;

import java.math.BigDecimal;

/**
* mbr_recharge, 充值记录表
*
* @author guoxianwei  2016-11-11 15:55:31
*/
public class MbrRecharge extends GenericEntity{
    private String id;          //
    private String memberId;          //会员ID
    private String bizNo;            //商家编号
    private String giftId;          //活动ID
    private BigDecimal money;          //充值金额
    private Integer payWay;          //充值方式(1支付宝;2微信;3网银)
    private Integer radio;          //兑换比例
    private String payno;          //充值凭证号
    private Integer state;          //@@充值状态(0失败； 1成功；2处理中; 3退款）
    private Integer giveMoney;          //活动赠送额度
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间

    private String stateStr;
    private String payWayStr;

    public MbrRecharge() {
    }

    /**
     * 状态枚举
     */
    public static enum St {
        FAIL(0, "充值失败"), SUCCESS(1, "充值成功"), PROC(2, "处理中"), REFUND(3, "已退款");

        private Integer key;

        private String val;

        St(Integer key, String val) {
            this.key = key;
            this.val = val;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        public static St valueOf(Integer key) {
            for (St st : St.values()) {
                if (key != null && st.key.equals(key)) {
                    return st;
                }
            }
            return null;
        }
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id=id;
    }
    public String getMemberId(){
        return this.memberId;
    }

    public void setMemberId(String memberId){
        this.memberId=memberId;
    }

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public BigDecimal getMoney(){
        return this.money;
    }

    public void setMoney(BigDecimal money){
        this.money=money;
    }
    public Integer getPayWay(){
        return this.payWay;
    }

    public void setPayWay(Integer payWay){
        this.payWay=payWay;
    }
    public Integer getRadio(){
        return this.radio;
    }

    public void setRadio(Integer radio){
        this.radio=radio;
    }
    public String getPayno(){
        return this.payno;
    }

    public void setPayno(String payno){
        this.payno=payno;
    }
    public Integer getState(){
        return this.state;
    }

    public void setState(Integer state){
        this.state=state;
    }
    public Integer getGiveMoney(){
        return this.giveMoney;
    }

    public void setGiveMoney(Integer giveMoney){
        this.giveMoney=giveMoney;
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

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public String getStateStr() {
        if (state != null) {
            stateStr = St.valueOf(state).getVal();
        }
        return stateStr;
    }

    public String getPayWayStr() {
        if (payWay != null) {
            payWayStr = Std.PayWay.valueOf(payWay).getVal();
        }
        return payWayStr;
    }
}