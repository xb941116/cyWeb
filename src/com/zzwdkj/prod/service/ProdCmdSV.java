package com.zzwdkj.prod.service;

import com.zzwdkj.prod.entity.ProdCmd;

import java.util.List;

/**
* prod_cmd, 产品_常用高级命令表SV
*
* @author guoxianwei  2016-09-07 15:01:56
*/

public interface ProdCmdSV {

    /**
    * 查询产品_常用高级命令表 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<ProdCmd> queryProdCmd(String name, int rowStart, int rowSize);

    /**
    * 新增产品_常用高级命令表
    *
    * @param prodCmd
    */
    void create(ProdCmd prodCmd);

    /**
    * 修改产品_常用高级命令表
    *
    * @param prodCmd
    */
    void update(ProdCmd prodCmd);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计产品_常用高级命令表数量
    * @param name 名称
    * @return 产品_常用高级命令表数量
    */
    int count(String name);

    /**
    * 加载产品_常用高级命令表
    *
    * @param id 主键
    * @return 产品_常用高级命令表
    */
    ProdCmd load(String id);


}
