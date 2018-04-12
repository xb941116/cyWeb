package com.zzwdkj.biz.service;

import com.zzwdkj.common.Std;
import com.zzwdkj.biz.entity.BizAd;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
* biz_ad, 商家广告表SV
*
* @author xizhuangchui  2017-04-22 12:12:22
*/

public interface BizAdSV {

    /**
    * 查询商家广告表 ，带分页
    *
    * @param bizNo 商家编号
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<BizAd> queryBizAd(String bizNo, int rowStart, int rowSize);

    /**
    * 新增商家广告表
    *
    * @param bizAd
    */
    void create(BizAd bizAd);
    /**
    * 修改商家广告表
    *
    * @param bizAd
    */
    void update(BizAd bizAd);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计商家广告表数量
    * @param bizNo 商家编号
    * @return 商家广告表数量
    */
    int count(String bizNo);

    /**
    * 加载商家广告表
    *
    * @param id 主键
    * @return 商家广告表
    */
    BizAd load(String id);

    /**
     * 通过商家编号获取 广告表
     * @param bizNo 商家编号
     * @param adType 广告类型
     * @param checkTime 是否校验结束时间
     * @return
     */
    List<BizAd> queryBizAdByBizNoAndType(String bizNo,Integer adType,boolean checkTime);

    /**
     * 创建
     * @param bizAd
     */
    void createPaySucess(BizAd bizAd);

    /**
     * 通过条件查询 若 rowStart或者rowSize为-1则全查
     * @param params
     * @param rowStart
     * @param rowSize
     * @return
     */
    List<BizAd> queryBizAdByparams(Map<String, Object> params, int rowStart, int rowSize);
}
