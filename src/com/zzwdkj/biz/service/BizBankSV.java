package com.zzwdkj.biz.service;

import com.zzwdkj.biz.entity.BizBank;

import java.util.List;

/**
* biz_bank, 商家_银行卡SV
*
* @author guoxianwei  2016-09-07 15:01:27
*/

public interface BizBankSV {

    /**
    * 查询商家_银行卡 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<BizBank> queryBizBank(String name, int rowStart, int rowSize);

    /**
    * 新增商家_银行卡
    *
    * @param bizBank
    */
    void create(BizBank bizBank);

    /**
    * 修改商家_银行卡
    *
    * @param bizBank
    */
    void update(BizBank bizBank);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计商家_银行卡数量
    * @param name 名称
    * @return 商家_银行卡数量
    */
    int count(String name);

    /**
    * 加载商家_银行卡
    *
    * @param id 主键
    * @return 商家_银行卡
    */
    BizBank load(String id);


}
