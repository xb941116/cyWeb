package com.zzwdkj.biz.service;

import com.zzwdkj.biz.entity.Biz;
import com.zzwdkj.biz.entity.BizAdvise;
import com.zzwdkj.biz.entity.BizWlt;

import java.util.List;
import java.util.Map;

/**
 * biz, 商家SV
 *
 * @author guoxianwei  2016-09-07 15:01:26
 */

public interface BizSV {

    /**
     * 查询商家 ，带分页
     *
     * @param params   参数
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 结果集
     */
    List<Biz> queryBiz(Map<String, Object> params, int rowStart, int rowSize);
    /**
     * 查询商家 ，带分页
     *
     * @param params   参数
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 结果集
     */
    List<Biz> queryBizJoinAcct(Map<String, Object> params, int rowStart, int rowSize);

    /**
     * 统计商家数量
     *
     * @param params 参数
     * @return 商家数量
     */
    int countBiz(Map<String, Object> params);

    /**
     * 统计商家数量
     *
     * @param params 参数
     * @return 商家数量
     */
    int countBizJoinAcct(Map<String, Object> params);

    /**
     * 查询下级商家 ，带分页
     *
     * @param bizNo    商家编号
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 结果集
     */
    List<Biz> queryBizUnder(String bizNo, int rowStart, int rowSize);

    /**
     * 新增商家
     *
     * @param biz
     */
    void createBiz(Biz biz);

    /**
     * 修改商家
     *
     * @param biz
     */
    void updateBiz(Biz biz);

    /**
     * 删除一条记录
     *
     * @param id
     */
    void removeBiz(String id);


    /**
     * 加载商家
     *
     * @param id 主键
     * @return 商家
     */
    Biz loadBiz(String id);

    /**
     * 加载商家
     *
     * @param mobile 联系人手机
     * @return 商家
     */
    Biz loadBizByMobile(String mobile);

    /**
     * 加载商家
     *
     * @param name 企业名称
     * @return 商家
     */
    Biz loadBizByName(String name);

    /**
     * 加载商家
     *
     * @param email 企业邮箱
     * @return 商家
     */
    Biz loadBizByEmail(String email);

    /**
     * 加载商家
     *
     * @param bizNo 商家编号
     * @return 商家
     */
    Biz loadBizByBizNo(String bizNo);

    /**
     * 添加商家投诉意见
     *
     * @param bizAdvise 意见
     */
    void createBizAdvise(BizAdvise bizAdvise);

    /**
     * 加载商家钱包
     *
     * @param bizNo 商户编号
     * @return 钱包
     */
    BizWlt loadBizWlt(String bizNo);
}
