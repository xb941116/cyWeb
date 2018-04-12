package com.zzwdkj.prod.service;

import com.zzwdkj.prod.entity.ProdModAttr;

import java.util.List;

/**
* prod_mod_attr, 商品_模板_属性表SV
*
* @author guoxianwei  2016-09-07 15:02:03
*/

public interface ProdModAttrSV {

    /**
    * 查询商品_模板_属性表 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<ProdModAttr> queryProdModAttr(String name, int rowStart, int rowSize);

    /**
    * 新增商品_模板_属性表
    *
    * @param prodModAttr
    */
    void create(ProdModAttr prodModAttr);

    /**
    * 修改商品_模板_属性表
    *
    * @param prodModAttr
    */
    void update(ProdModAttr prodModAttr);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计商品_模板_属性表数量
    * @param name 名称
    * @return 商品_模板_属性表数量
    */
    int count(String name);

    /**
    * 加载商品_模板_属性表
    *
    * @param id 主键
    * @return 商品_模板_属性表
    */
    ProdModAttr load(String id);


}
