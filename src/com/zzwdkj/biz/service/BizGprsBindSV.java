package com.zzwdkj.biz.service;

import com.zzwdkj.biz.entity.BizGprsBind;

import java.util.List;
import java.util.Map;

/**
 * biz_gprs_bind, 商家_GPRS绑定表SV
 *
 * @author guoxianwei  2016-09-07 15:01:28
 */

public interface BizGprsBindSV {

    /**
     * 查询商家_GPRS绑定表 ，带分页
     *
     * @param params     参数
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 结果集
     */
    List<BizGprsBind> queryBizGprsBind(Map<String,Object> params, int rowStart, int rowSize);

    /**
     * 统计商家_GPRS绑定表数量
     *
     * @param params 参数
     * @return 商家_GPRS绑定表数量
     */
    int countBizGprsBind(Map<String,Object> params);

    /**
     * 新增商家_GPRS绑定表
     *
     * @param bizGprsBind
     */
    void create(BizGprsBind bizGprsBind);

    /**
     * 修改商家_GPRS绑定表
     *
     * @param bizGprsBind
     */
    void update(BizGprsBind bizGprsBind);

    /**
     * 删除一条记录
     *
     * @param id
     */
    void remove(String id);

    /**
     * 加载商家_GPRS绑定表
     *
     * @param id 主键
     * @return 商家_GPRS绑定表
     */
    BizGprsBind load(String id);

    /**
     * 绑定
     *
     * @param bizNo  商家编号
     * @param gprsNo 模块编号
     */
    void bind(String bizNo, String gprsNo);

    /**
     * 解绑
     *
     * @param bizNo  商家编号
     * @param gprsNo 模块编号
     */
    void unbind(String bizNo, String gprsNo);

    /**
     * 模块移动
     * @param bizNo  商家编号
     * @param gprsNo 模块编号
     */
    void moveModel(String bizNo, String gprsNo);
    /**
     * 加载商家模块绑定
     *
     * @param  bizNo  商家编号
     * @param  gprsNo 商家编号
     * @return 结果
     */
    BizGprsBind loadBizGprsBind(String bizNo, String gprsNo);

    /**
     * 查询商家模块绑定
     * @param bizNo 商家编号
     * @return 结果集
     */
    List<BizGprsBind> queryBizGprsBind(String bizNo);

    /**
     * 统计商家模块绑定
     * @param bizNo 商家编号
     * @return 数量
     */
    int countBizGprsBind(String bizNo);

    /**
     *  统计商家模块绑定
     *
     * @param  bizNo  商家编号
     * @param  gprsNo 商家编号
     * @return 数量
     */
    int countBizGprsBind(String bizNo, String gprsNo);

}
