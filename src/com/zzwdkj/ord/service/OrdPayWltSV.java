package com.zzwdkj.ord.service;

import com.zzwdkj.ord.entity.OrdPayWlt;

import java.util.List;

/**
* ord_pay_wlt, 订单_支付_钱包支付记录表SV
*
* @author guoxianwei  2016-09-07 15:01:51
*/

public interface OrdPayWltSV {

    /**
    * 查询订单_支付_钱包支付记录表 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<OrdPayWlt> queryOrdPayWlt(String name, int rowStart, int rowSize);

    /**
    * 新增订单_支付_钱包支付记录表
    *
    * @param ordPayWlt
    */
    void create(OrdPayWlt ordPayWlt);

    /**
    * 修改订单_支付_钱包支付记录表
    *
    * @param ordPayWlt
    */
    void update(OrdPayWlt ordPayWlt);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计订单_支付_钱包支付记录表数量
    * @param name 名称
    * @return 订单_支付_钱包支付记录表数量
    */
    int count(String name);

    /**
    * 加载订单_支付_钱包支付记录表
    *
    * @param id 主键
    * @return 订单_支付_钱包支付记录表
    */
    OrdPayWlt load(String id);


}
