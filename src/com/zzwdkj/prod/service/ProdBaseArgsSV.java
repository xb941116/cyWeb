package com.zzwdkj.prod.service;

import com.zzwdkj.prod.entity.ProdBaseArgs;

import java.util.List;

/**
* prod_base_args, 商品_基本属性SV
*
* @author guoxianwei  2016-09-07 15:01:57
*/

public interface ProdBaseArgsSV {

    /**
    * 查询商品_基本属性 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<ProdBaseArgs> queryProdBaseArgs(String name, int rowStart, int rowSize);

    /**
    * 新增商品_基本属性
    *
    * @param prodBaseArgs
    */
    void create(ProdBaseArgs prodBaseArgs);

    /**
    * 修改商品_基本属性
    *
    * @param prodBaseArgs
    */
    void update(ProdBaseArgs prodBaseArgs);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计商品_基本属性数量
    * @param name 名称
    * @return 商品_基本属性数量
    */
    int count(String name);

    /**
    * 加载商品_基本属性
    *
    * @param id 主键
    * @return 商品_基本属性
    */
    ProdBaseArgs load(String id);


}
