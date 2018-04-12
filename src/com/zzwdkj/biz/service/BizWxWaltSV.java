package com.zzwdkj.biz.service;

import com.zzwdkj.biz.entity.BizWxWalt;

import java.util.List;

/**
* biz_wx_walt, 商家_微信零钱包SV
*
* @author guoxianwei  2016-09-07 15:01:34
*/

public interface BizWxWaltSV {

    /**
    * 查询商家_微信零钱包 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<BizWxWalt> queryBizWxWalt(String name, int rowStart, int rowSize);

    /**
    * 新增商家_微信零钱包
    *
    * @param bizWxWalt
    */
    void create(BizWxWalt bizWxWalt);

    /**
    * 修改商家_微信零钱包
    *
    * @param bizWxWalt
    */
    void update(BizWxWalt bizWxWalt);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计商家_微信零钱包数量
    * @param name 名称
    * @return 商家_微信零钱包数量
    */
    int count(String name);

    /**
    * 加载商家_微信零钱包
    *
    * @param id 主键
    * @return 商家_微信零钱包
    */
    BizWxWalt load(String id);


}
