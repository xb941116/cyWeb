package com.zzwdkj.prod.service;

import com.zzwdkj.common.Std;
import com.zzwdkj.prod.entity.ProdInstlPosModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
* prod_instl_pos_model, 产品_安装位置模板表SV
*
* @author guoxianwei  2017-01-17 17:06:22
*/

public interface ProdInstlPosModelSV {

    /**
    * 查询产品_安装位置模板表 ，带分页
    *
    * @param bizNo 商家编号
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<ProdInstlPosModel> queryProdInstlPosModel(String bizNo, int rowStart, int rowSize);

    /**
    * 新增产品_安装位置模板表
    *
    * @param prodInstlPosModel
    */
    void create(ProdInstlPosModel prodInstlPosModel);

    /**
    * 修改产品_安装位置模板表
    *
    * @param prodInstlPosModel
    */
    void update(ProdInstlPosModel prodInstlPosModel);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计产品_安装位置模板表数量
    * @param name 名称
    * @return 产品_安装位置模板表数量
    */
    int count(String name);

    /**
    * 加载产品_安装位置模板表
    *
    * @param id 主键
    * @return 产品_安装位置模板表
    */
    ProdInstlPosModel load(String id);


}
