package com.zzwdkj.ord.service;

import com.zzwdkj.ord.entity.OrdItem;

import java.util.List;

/**
* ord_item, 订单_客户_订单商品项SV
*
* @author guoxianwei  2016-09-07 15:01:45
*/

public interface OrdItemSV {

    /**
    * 查询订单_客户_订单商品项 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<OrdItem> queryOrdItem(String name, int rowStart, int rowSize);

    /**
    * 新增订单_客户_订单商品项
    *
    * @param ordItem
    */
    void create(OrdItem ordItem);

    /**
    * 修改订单_客户_订单商品项
    *
    * @param ordItem
    */
    void update(OrdItem ordItem);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计订单_客户_订单商品项数量
    * @param name 名称
    * @return 订单_客户_订单商品项数量
    */
    int count(String name);

    /**
    * 加载订单_客户_订单商品项
    *
    * @param id 主键
    * @return 订单_客户_订单商品项
    */
    OrdItem load(String id);


}
