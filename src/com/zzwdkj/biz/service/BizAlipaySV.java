package com.zzwdkj.biz.service;

import com.zzwdkj.biz.entity.BizAlipay;

import java.util.List;

/**
* biz_alipay, 商家_支付宝SV
*
* @author guoxianwei  2016-09-07 15:01:27
*/

public interface BizAlipaySV {

    /**
    * 查询商家_支付宝 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<BizAlipay> queryBizAlipay(String name, int rowStart, int rowSize);

    /**
    * 新增商家_支付宝
    *
    * @param bizAlipay
    */
    void create(BizAlipay bizAlipay);

    /**
    * 修改商家_支付宝
    *
    * @param bizAlipay
    */
    void update(BizAlipay bizAlipay);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计商家_支付宝数量
    * @param name 名称
    * @return 商家_支付宝数量
    */
    int count(String name);

    /**
    * 加载商家_支付宝
    *
    * @param id 主键
    * @return 商家_支付宝
    */
    BizAlipay load(String id);


}
