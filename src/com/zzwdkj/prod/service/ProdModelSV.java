package com.zzwdkj.prod.service;

import com.zzwdkj.prod.entity.ProdModel;

import java.util.List;

/**
* prod_model, 商品_模板表SV
*
* @author guoxianwei  2016-09-07 15:02:08
*/

public interface ProdModelSV {

    /**
    * 查询商品_模板表 ，带分页
    *
    * @param bizNo 商家编号
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<ProdModel> queryProdModel(String bizNo, int rowStart, int rowSize);

    /**
    * 新增商品_模板表
    *
    * @param prodModel
    */
    void create(ProdModel prodModel);

    /**
    * 修改商品_模板表
    *
    * @param prodModel
    */
    void update(ProdModel prodModel);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计商品_模板表数量
    * @param name 名称
    * @return 商品_模板表数量
    */
    int count(String name);

    /**
    * 加载商品_模板表
    *
    * @param id 主键
    * @return 商品_模板表
    */
    ProdModel load(String id);


}
