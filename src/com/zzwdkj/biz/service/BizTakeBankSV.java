package com.zzwdkj.biz.service;

import com.zzwdkj.biz.entity.BizTakeBank;

import java.util.List;

/**
* biz_take_bank, 商家_提现_银行卡_记录SV
*
* @author guoxianwei  2016-09-07 15:01:32
*/

public interface BizTakeBankSV {

    /**
    * 查询商家_提现_银行卡_记录 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<BizTakeBank> queryBizTakeBank(String name, int rowStart, int rowSize);

    /**
    * 新增商家_提现_银行卡_记录
    *
    * @param bizTakeBank
    */
    void create(BizTakeBank bizTakeBank);

    /**
    * 修改商家_提现_银行卡_记录
    *
    * @param bizTakeBank
    */
    void update(BizTakeBank bizTakeBank);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计商家_提现_银行卡_记录数量
    * @param name 名称
    * @return 商家_提现_银行卡_记录数量
    */
    int count(String name);

    /**
    * 加载商家_提现_银行卡_记录
    *
    * @param id 主键
    * @return 商家_提现_银行卡_记录
    */
    BizTakeBank load(String id);


    /**
     * 获取商家_提现_银行卡_记录 通过申请单号
     * @param takeNo 申请单号
     * @return
     */
    BizTakeBank loadByReqno(String takeNo);
}
