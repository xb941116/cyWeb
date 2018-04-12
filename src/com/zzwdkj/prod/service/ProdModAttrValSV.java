package com.zzwdkj.prod.service;

import com.zzwdkj.prod.entity.ProdModAttrVal;

import java.util.List;

/**
* prod_mod_attr_val, 商品_模板_属性值SV
*
* @author guoxianwei  2016-09-07 15:02:04
*/

public interface ProdModAttrValSV {

    /**
    * 查询商品_模板_属性值 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<ProdModAttrVal> queryProdModAttrVal(String name, int rowStart, int rowSize);

    /**
    * 新增商品_模板_属性值
    *
    * @param prodModAttrVal
    */
    void create(ProdModAttrVal prodModAttrVal);

    /**
    * 修改商品_模板_属性值
    *
    * @param prodModAttrVal
    */
    void update(ProdModAttrVal prodModAttrVal);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计商品_模板_属性值数量
    * @param name 名称
    * @return 商品_模板_属性值数量
    */
    int count(String name);

    /**
    * 加载商品_模板_属性值
    *
    * @param id 主键
    * @return 商品_模板_属性值
    */
    ProdModAttrVal load(String id);


}
