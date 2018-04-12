package com.zzwdkj.biz.service;

import com.zzwdkj.common.Std;
import com.zzwdkj.biz.entity.BizVip;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
* biz_vip, 商家VIP功能表SV
*
* @author xizhuangchui  2017-04-22 12:12:22
*/

public interface BizVipSV {

    /**
    * 查询商家VIP功能表 ，带分页
    *
    * @param bizNo 商家编号
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<BizVip> queryBizVip(String bizNo, int rowStart, int rowSize);

    /**
    * 新增商家VIP功能表
    *
    * @param bizVip
    */
    void create(BizVip bizVip);

    /**
    * 修改商家VIP功能表
    *
    * @param bizVip
    */
    void update(BizVip bizVip);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计商家VIP功能表数量
    * @param name 名称
    * @return 商家VIP功能表数量
    */
    int count(String name);

    /**
    * 加载商家VIP功能表
    *
    * @param id 主键
    * @return 商家VIP功能表
    */
    BizVip load(String id);


    /**
     * 通过 商家编号获取 VIP功能表
     * @param bizNo 商家编号
     * @param vipType 类型
     * @param checkTime 是否校验结束时间
     * @return
     */
    BizVip loadByBizNoAndType(String bizNo, Integer vipType,boolean checkTime);

    /**
     * 通过条件查询 如果rowStart或者rowSize为-1则全查
     * @param params
     * @param rowStart
     * @param rowSize
     * @return
     */
    List<BizVip> queryBizAdByparams(Map<String, Object> params, int rowStart, int rowSize);

    /**
     * 创建支付完成界面 免广告功能
     * @param bizVip
     */
    void createPaySucess(BizVip bizVip);
}
