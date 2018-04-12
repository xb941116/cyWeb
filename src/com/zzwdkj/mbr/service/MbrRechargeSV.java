package com.zzwdkj.mbr.service;

import com.zzwdkj.mbr.entity.MbrRecharge;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * mbr_recharge, 充值记录表SV
 *
 * @author guoxianwei  2016-11-11 15:55:31
 */

public interface MbrRechargeSV {

    /**
     * 查询充值记录表 ，带分页
     *
     * @param name     名称
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 结果集
     */
    List<MbrRecharge> queryMbrRecharge(String name, int rowStart, int rowSize);

    /**
     * 新增充值记录表
     *
     * @param mbrRecharge
     */
    void create(MbrRecharge mbrRecharge);

    /**
     * 修改充值记录表
     *
     * @param mbrRecharge
     */
    void update(MbrRecharge mbrRecharge);

    /**
     * 删除一条记录
     *
     * @param id
     */
    void remove(String id);

    /**
     * 统计充值记录表数量
     *
     * @param name 名称
     * @return 充值记录表数量
     */
    int count(String name);

    /**
     * 加载充值记录表
     *
     * @param id 主键
     * @return 充值记录表
     */
    MbrRecharge load(String id);


    /**
     * 通过支付单号获取 充值记录
     *
     * @param payno
     */
    MbrRecharge loadByPayno(String payno);


    /**
     * 查询充值记录
     *
     * @param params
     * @param startRow 起始行（不含起始行）
     * @param pageSize 每页行数
     * @return 结果集
     */
    List<MbrRecharge> queryMbrRecharge(Map<String, Object> params, int startRow, int pageSize);

    /**
     * 充值记录的总数
     *
     * @param params
     * @return
     */
    int countMbrRecharge(Map<String, Object> params);


    /**
     * 查询充值记录
     *
     * @param openid
     * @param startRow 起始行（不含起始行）
     * @param pageSize 每页行数
     * @return 结果集
     */
    List<MbrRecharge> queryMbrReCgHisByOpenid(String openid, int startRow, int pageSize);

    /**
     * 充值记录的总数
     *
     * @param openid
     * @return
     */
    int countMbrReCgHisByOpenid(String openid);


    /**
     * 查询充值记录
     *
     * @param openid
     * @param startRow 起始行（不含起始行）
     * @param pageSize 每页行数
     * @return 结果集
     */
    List<MbrRecharge> queryMbrReCgHisByOpenidAndBizNo(String openid, String bizNo, int startRow, int pageSize);

    /**
     * 充值记录的总数
     *
     * @param openid
     * @return
     */
    int countMbrReCgHisByOpenidAndBizNo(String openid, String bizNo);

    /**
     * 加载充值收入
     *
     * @param bizNo 商家编号
     * @return 收入
     */
    BigDecimal queryStaIncByBizNo(String bizNo);

}
