package com.zzwdkj.ord.service;

import com.zzwdkj.ord.entity.OrdPayAli;

import java.util.List;

/**
* ord_pay_ali, 订单_支付_支付宝支付记录表SV
*
* @author guoxianwei  2016-09-07 15:01:46
*/

public interface OrdPayAliSV {

    /**
    * 查询订单_支付_支付宝支付记录表 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<OrdPayAli> queryOrdPayAli(String name, int rowStart, int rowSize);

    /**
    * 新增订单_支付_支付宝支付记录表
    *
    * @param ordPayAli
    */
    void create(OrdPayAli ordPayAli);

    /**
    * 修改订单_支付_支付宝支付记录表
    *
    * @param ordPayAli
    */
    void update(OrdPayAli ordPayAli);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计订单_支付_支付宝支付记录表数量
    * @param name 名称
    * @return 订单_支付_支付宝支付记录表数量
    */
    int count(String name);

    /**
    * 加载订单_支付_支付宝支付记录表
    *
    * @param id 主键
    * @return 订单_支付_支付宝支付记录表
    */
    OrdPayAli load(String id);


}
