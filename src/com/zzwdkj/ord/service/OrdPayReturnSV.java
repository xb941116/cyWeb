package com.zzwdkj.ord.service;

import com.zzwdkj.ord.entity.OrdPayReturn;

import java.util.List;

/**
* ord_pay_return, 订单_退单表SV
*
* @author guoxianwei  2016-09-07 15:01:46
*/

public interface OrdPayReturnSV {

    /**
    * 查询订单_退单表 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<OrdPayReturn> queryOrdPayReturn(String name, int rowStart, int rowSize);

    /**
    * 新增订单_退单表
    *
    * @param ordPayReturn
    */
    void create(OrdPayReturn ordPayReturn);

    /**
    * 修改订单_退单表
    *
    * @param ordPayReturn
    */
    void update(OrdPayReturn ordPayReturn);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计订单_退单表数量
    * @param name 名称
    * @return 订单_退单表数量
    */
    int count(String name);

    /**
    * 加载订单_退单表
    *
    * @param id 主键
    * @return 订单_退单表
    */
    OrdPayReturn load(String id);


}
