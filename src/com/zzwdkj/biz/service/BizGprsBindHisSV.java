package com.zzwdkj.biz.service;

import com.zzwdkj.biz.entity.BizGprsBindHis;

import java.util.List;

/**
* biz_gprs_bind_his, 商家_GPRS绑定历史表SV
*
* @author guoxianwei  2016-09-07 15:01:29
*/

public interface BizGprsBindHisSV {

    /**
    * 查询商家_GPRS绑定历史表 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<BizGprsBindHis> queryBizGprsBindHis(String name, int rowStart, int rowSize);

    /**
    * 新增商家_GPRS绑定历史表
    *
    * @param bizGprsBindHis
    */
    void create(BizGprsBindHis bizGprsBindHis);

    /**
    * 修改商家_GPRS绑定历史表
    *
    * @param bizGprsBindHis
    */
    void update(BizGprsBindHis bizGprsBindHis);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计商家_GPRS绑定历史表数量
    * @param name 名称
    * @return 商家_GPRS绑定历史表数量
    */
    int count(String name);

    /**
    * 加载商家_GPRS绑定历史表
    *
    * @param id 主键
    * @return 商家_GPRS绑定历史表
    */
    BizGprsBindHis load(String id);


}
