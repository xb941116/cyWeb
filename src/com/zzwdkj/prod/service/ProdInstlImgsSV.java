package com.zzwdkj.prod.service;

import com.zzwdkj.prod.entity.ProdInstlImgs;

import java.util.List;

/**
* prod_instl_imgs, 产品_安装场地图片表SV
*
* @author guoxianwei  2016-09-07 15:02:00
*/

public interface ProdInstlImgsSV {

    /**
    * 查询产品_安装场地图片表 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<ProdInstlImgs> queryProdInstlImgs(String name, int rowStart, int rowSize);

    /**
    * 新增产品_安装场地图片表
    *
    * @param prodInstlImgs
    */
    void create(ProdInstlImgs prodInstlImgs);

    /**
    * 修改产品_安装场地图片表
    *
    * @param prodInstlImgs
    */
    void update(ProdInstlImgs prodInstlImgs);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计产品_安装场地图片表数量
    * @param name 名称
    * @return 产品_安装场地图片表数量
    */
    int count(String name);

    /**
    * 加载产品_安装场地图片表
    *
    * @param id 主键
    * @return 产品_安装场地图片表
    */
    ProdInstlImgs load(String id);


}
