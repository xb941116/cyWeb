package com.zzwdkj.ord.service;

import com.zzwdkj.ord.entity.OrdPayWx;

import java.util.List;
import java.util.Map;

/**
* ord_pay_wx, 订单_支付_微信支付记录表SV
*
* @author guoxianwei  2016-09-07 15:01:55
*/

public interface OrdPayWxSV {

    /**
    * 查询订单_支付_微信支付记录表 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<OrdPayWx> queryOrdPayWx(String name, int rowStart, int rowSize);

    /**
     * 查询订单_支付_微信支付记录表 ，带分页(rowStart或rowSize的值为-1时候则全查)
     *
     * @param params 条件Map
     * @param rowStart 起始行
     * @param rowSize 分页大小
     * @return 结果集
     */
    List<OrdPayWx> queryOrdPayWx(Map<String, Object> params, int rowStart, int rowSize);


    /**
    * 新增订单_支付_微信支付记录表
    *
    * @param ordPayWx
    */
    void create(OrdPayWx ordPayWx);

    /**
    * 修改订单_支付_微信支付记录表
    *
    * @param ordPayWx
    */
    void update(OrdPayWx ordPayWx);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计订单_支付_微信支付记录表数量
    * @param name 名称
    * @return 订单_支付_微信支付记录表数量
    */
    int count(String name);

    /**
    * 加载订单_支付_微信支付记录表
    *
    * @param id 主键
    * @return 订单_支付_微信支付记录表
    */
    OrdPayWx load(String id);


    /**
     * 通过订单号获取 订单_支付_微信支付记录表
     * @param payno
     * @return
     */
    OrdPayWx loadByPayno(String payno);

}
