package com.zzwdkj.gprs.service;

import com.zzwdkj.gprs.entity.GprsModel;

import java.util.List;
import java.util.Map;

/**
 * gprs_model, GPRS_模块表SV
 *
 * @author guoxianwei  2016-09-07 15:01:38
 */

public interface GprsModelSV {

    /**
     * 查询GPRS_模块表 ，带分页
     *
     * @param gprsNo   模块化号
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 结果集
     */
    List<GprsModel> queryGprsModel(String gprsNo, int rowStart, int rowSize);

    /**
     * 查询GPRS_模块表 ，带分页
     *
     * @param params   参数
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 结果集
     */
    List<GprsModel> queryGprsModel(Map<String, Object> params, int rowStart, int rowSize);

    /**
     * 查询GPRS_模块表 ，带分页
     *
     * @param params   参数
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 结果集
     */
    List<GprsModel> queryGprsModelForProdSet(Map<String, Object> params, int rowStart, int rowSize);

    /**
     * 新增GPRS_模块表
     *
     * @param gprsModel
     */
    void create(GprsModel gprsModel);

    /**
     * 修改GPRS_模块表
     *
     * @param gprsModel
     */
    void update(GprsModel gprsModel);

    /**
     * 删除一条记录
     *
     * @param id
     */
    void remove(String id);

    /**
     * 统计GPRS_模块表数量
     *
     * @param name 名称
     * @return GPRS_模块表数量
     */
    int count(String name);

    /**
     * 统计GPRS_模块表数量
     *
     * @param params 参数
     * @return GPRS_模块表数量
     */
    int count(Map<String, Object> params);


    /**
     * 统计GPRS_模块表数量
     *
     * @param params 商家编号
     * @return GPRS_模块表数量
     */
    int countGprsModelForProdSet(Map<String, Object> params);



    /**
     * 加载GPRS_模块表
     *
     * @param id 主键
     * @return GPRS_模块表
     */
    GprsModel load(String id);

    /**
     * 加载GPRS_模块表
     *
     * @param gprsNo 模块号
     * @return GPRS_模块表
     */
    GprsModel loadByGprsNo(String gprsNo);

    /**
     *
     * @param bizNo 商家编号
     * @param instlaState 位置设置状态
     *@param rowStart 起始位置
     * @param pageSize 分页大小   @return
     */
    List<GprsModel> queryInstlGprsModelByBizNo(String bizNo, String instlaState, int rowStart, int pageSize);

    /**
     * 获取数据个数
     * @param bizNo
     * @return
     */
    int countInstlGprsModelByBizNo(String bizNo, String instlaState);
}
