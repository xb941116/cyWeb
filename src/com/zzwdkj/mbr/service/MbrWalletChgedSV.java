package com.zzwdkj.mbr.service;

import com.zzwdkj.common.Std;
import com.zzwdkj.mbr.entity.MbrWalletChged;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
* mbr_wallet_chged, 会员钱包变更表SV
*
* @author guoxianwei  2016-11-11 15:55:32
*/

public interface MbrWalletChgedSV {

    /**
    * 查询会员钱包变更表 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<MbrWalletChged> queryMbrWalletChged(String name, int rowStart, int rowSize);

    /**
    * 新增会员钱包变更表
    *
    * @param mbrWalletChged
    */
    void create(MbrWalletChged mbrWalletChged);

    /**
    * 修改会员钱包变更表
    *
    * @param mbrWalletChged
    */
    void update(MbrWalletChged mbrWalletChged);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计会员钱包变更表数量
    * @param name 名称
    * @return 会员钱包变更表数量
    */
    int count(String name);

    /**
    * 加载会员钱包变更表
    *
    * @param id 主键
    * @return 会员钱包变更表
    */
    MbrWalletChged load(String id);


}
