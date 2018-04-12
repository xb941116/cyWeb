package com.zzwdkj.mbr.service;

import com.zzwdkj.common.Std;
import com.zzwdkj.mbr.entity.MbrCoinChged;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
* mbr_coin_chged, 会员积分变更表SV
*
* @author guoxianwei  2016-11-11 15:55:28
*/

public interface MbrCoinChgedSV {

    /**
    * 查询会员积分变更表 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<MbrCoinChged> queryMbrCoinChged(String name, int rowStart, int rowSize);

    /**
    * 新增会员积分变更表
    *
    * @param mbrCoinChged
    */
    void create(MbrCoinChged mbrCoinChged);

    /**
    * 修改会员积分变更表
    *
    * @param mbrCoinChged
    */
    void update(MbrCoinChged mbrCoinChged);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计会员积分变更表数量
    * @param name 名称
    * @return 会员积分变更表数量
    */
    int count(String name);

    /**
    * 加载会员积分变更表
    *
    * @param id 主键
    * @return 会员积分变更表
    */
    MbrCoinChged load(String id);


}
