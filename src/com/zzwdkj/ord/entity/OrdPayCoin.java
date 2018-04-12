package com.zzwdkj.ord.entity;

import com.hckj.framework.entity.GenericEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ord_pay_coin, 订单_支付_积分支付记录表
 * Created by sufangning on 2017/12/6.
 */
public class OrdPayCoin extends GenericEntity {
        private String id;          //ID
        private String coinid;          //积分ID
        private java.math.BigDecimal coin;          //支付积分
        private Integer state;          //@@支付状态(0失败； 1成功；2处理中）
        private String payno;          //支付凭证号
        private String resvAcct;          //收款账号
        private java.util.Date crtime;          //创建时间
        private java.util.Date uptime;          //更新时间

        public OrdPayCoin() {
        }

        public  OrdPayCoin(String coinid, BigDecimal coin, String payno, String resvAcct) {
            this.coinid=coinid;
            this.coin=coin;
            this.payno=payno;
            this.resvAcct=resvAcct;
            this.crtime = new Date();
            this.uptime = new Date();
        }

        public String getId(){
            return this.id;
        }

        public void setId(String id){
            this.id=id;
        }
        public String getCoinid(){
            return this.coinid;
        }

        public void setCoinid(String coinid){
            this.coinid=coinid;
        }
        public java.math.BigDecimal getCoin(){
            return this.coin;
        }

        public void setCoin(java.math.BigDecimal coin){
            this.coin=coin;
        }
        public Integer getState(){
            return this.state;
        }

        public void setState(Integer state){
            this.state=state;
        }
        public String getPayno(){
            return this.payno;
        }

        public void setPayno(String payno){
            this.payno=payno;
        }
        public String getResvAcct(){
            return this.resvAcct;
        }

        public void setResvAcct(String resvAcct){
            this.resvAcct=resvAcct;
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
