package com.zzwdkj.biz.service;

import com.zzwdkj.biz.entity.BizTakeWwlt;

import java.util.List;

/**
* biz_take_wwlt, 商家_提现_微信零钱包_记录SV
*
* @author guoxianwei  2016-09-07 15:01:33
*/

public interface BizTakeWwltSV {

    /**
    * 查询商家_提现_微信零钱包_记录 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<BizTakeWwlt> queryBizTakeWwlt(String name, int rowStart, int rowSize);

    /**
    * 新增商家_提现_微信零钱包_记录
    *
    * @param bizTakeWwlt
    */
    void create(BizTakeWwlt bizTakeWwlt);

    /**
    * 修改商家_提现_微信零钱包_记录
    *
    * @param bizTakeWwlt
    */
    void update(BizTakeWwlt bizTakeWwlt);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计商家_提现_微信零钱包_记录数量
    * @param name 名称
    * @return 商家_提现_微信零钱包_记录数量
    */
    int count(String name);

    /**
    * 加载商家_提现_微信零钱包_记录
    *
    * @param id 主键
    * @return 商家_提现_微信零钱包_记录
    */
    BizTakeWwlt load(String id);


    /**
     * 获取商家_提现_微信零钱包_记录 通过申请单号
     * @param takeNo 申请单号
     * @return
     */
    BizTakeWwlt loadByReqno(String takeNo);
}
