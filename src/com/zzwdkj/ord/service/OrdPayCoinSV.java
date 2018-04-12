package com.zzwdkj.ord.service;

import com.zzwdkj.ord.entity.OrdPayCoin;

import java.util.List;

/**
 * ord_pay_coin, 订单_支付_积分支付记录表SV
 * Created by sufangning on 2017/12/7.新添加的接口
 */
public interface OrdPayCoinSV {

    /**
     * 查询订单_支付_积分支付记录表 ，带分页
     *
     * @param name 名称
     * @param rowStart 起始行
     * @param rowSize 分页大小
     * @return 结果集
     */
    List<OrdPayCoin> queryOrdPayCoin(String name, int rowStart, int rowSize);

    /**
     * 新增订单_支付_积分支付记录表
     *
     * @param ordPayCoin
     */
    void create(OrdPayCoin ordPayCoin);

    /**
     * 修改订单_支付_积分支付记录表
     *
     * @param ordPayCoin
     */
    void update(OrdPayCoin ordPayCoin);

    /**
     * 删除一条记录
     *
     * @param id
     */
    void remove(String id);

    /**
     * 统计订单_支付_积分支付记录表数量
     * @param name 名称
     * @return 订单_支付_积分支付记录表数量
     */
    int count(String name);

    /**
     * 加载订单_支付_积分支付记录表
     *
     * @param id 主键
     * @return 订单_支付_积分支付记录表
     */
    OrdPayCoin load(String id);
}
