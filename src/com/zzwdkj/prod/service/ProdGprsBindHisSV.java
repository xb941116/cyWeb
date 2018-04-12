package com.zzwdkj.prod.service;

import com.zzwdkj.prod.entity.ProdGprsBindHis;

import java.util.List;

/**
* prod_gprs_bind_his, 产品_GPRS绑定历史表SV
*
* @author guoxianwei  2016-09-07 15:01:59
*/

public interface ProdGprsBindHisSV {

    /**
    * 查询产品_GPRS绑定历史表 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<ProdGprsBindHis> queryProdGprsBindHis(String name, int rowStart, int rowSize);

    /**
    * 新增产品_GPRS绑定历史表
    *
    * @param prodGprsBindHis
    */
    void create(ProdGprsBindHis prodGprsBindHis);

    /**
    * 修改产品_GPRS绑定历史表
    *
    * @param prodGprsBindHis
    */
    void update(ProdGprsBindHis prodGprsBindHis);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计产品_GPRS绑定历史表数量
    * @param name 名称
    * @return 产品_GPRS绑定历史表数量
    */
    int count(String name);

    /**
    * 加载产品_GPRS绑定历史表
    *
    * @param id 主键
    * @return 产品_GPRS绑定历史表
    */
    ProdGprsBindHis load(String id);


}
