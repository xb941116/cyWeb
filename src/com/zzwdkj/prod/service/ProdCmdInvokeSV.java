package com.zzwdkj.prod.service;

import com.zzwdkj.prod.entity.ProdCmdInvoke;

import java.util.List;

/**
* prod_cmd_invoke, 产品_高级命令下发表SV
*
* @author guoxianwei  2016-09-07 15:01:58
*/

public interface ProdCmdInvokeSV {

    /**
    * 查询产品_高级命令下发表 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<ProdCmdInvoke> queryProdCmdInvoke(String name, int rowStart, int rowSize);

    /**
    * 新增产品_高级命令下发表
    *
    * @param prodCmdInvoke
    */
    void create(ProdCmdInvoke prodCmdInvoke);

    /**
    * 修改产品_高级命令下发表
    *
    * @param prodCmdInvoke
    */
    void update(ProdCmdInvoke prodCmdInvoke);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计产品_高级命令下发表数量
    * @param name 名称
    * @return 产品_高级命令下发表数量
    */
    int count(String name);

    /**
    * 加载产品_高级命令下发表
    *
    * @param id 主键
    * @return 产品_高级命令下发表
    */
    ProdCmdInvoke load(String id);


}
