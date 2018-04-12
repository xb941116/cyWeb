package com.zzwdkj.biz.service;

import com.zzwdkj.biz.entity.BizWx;
import com.zzwdkj.biz.entity.BizWxFocus;

import java.util.List;

/**
* biz_wx, 商家_微信SV
*
* @author guoxianwei  2016-09-07 15:01:33
*/

public interface BizWxSV {

    /**
    * 查询商家_微信 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<BizWx> queryBizWx(String name, int rowStart, int rowSize);

    /**
    * 新增商家_微信
    *
    * @param bizWx
    */
    void createOrUpdate(BizWx bizWx);


    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计商家_微信数量
    * @param name 名称
    * @return 商家_微信数量
    */
    int count(String name);

    /**
    * 加载商家_微信
    *
    * @param id 主键
    * @return 商家_微信
    */
    BizWx load(String id);

    /**
     * 通过商家编号获取 商家_微信
     * @param bizNo 商家编号
     * @return 微信设置
     */
    BizWx loadByBizNo(String bizNo);


    /**
     * 通过订单号获取收款方的 商家_微信
     * @param ordNo 订单号
     * @return 微信设置
     */
    BizWx loadByOrdNo(String ordNo);

    /**
     * 通过设备号获取 商家_微信
     * @param gprsNo 设备号
     * @return 微信设置
     */
    BizWx loadByGprsNo(String gprsNo);

    /**
     * 删除微信API证书
     * @param bizNo 商家编号
     * @param acct  账户
     */
    void removeCertSkey(String bizNo,String acct);

    /**
     * 通过商户号获取 商家_微信
     * @param bizNum 商户号
     * @param bizNo 商家编号
     * @return
     */
    BizWx loadByBizNumAndBizNo(String bizNum,String bizNo);

    /**
     * 获取 关注微信 信息
     * @param bizNo
     * @return
     */
    BizWxFocus loadBizWxFocusByBizNo(String bizNo);
}
