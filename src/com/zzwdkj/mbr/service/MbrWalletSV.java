package com.zzwdkj.mbr.service;

import com.zzwdkj.common.Std;
import com.zzwdkj.mbr.entity.MbrWallet;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
* mbr_wallet, 会员钱包SV
*
* @author guoxianwei  2016-11-11 15:55:31
*/

public interface MbrWalletSV {

    /**
    * 查询会员钱包 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<MbrWallet> queryMbrWallet(String name, int rowStart, int rowSize);

    /**
    * 新增会员钱包
    *
    * @param mbrWallet
    */
    void create(MbrWallet mbrWallet);

    /**
    * 修改会员钱包
    *
    * @param mbrWallet
    */
    void update(MbrWallet mbrWallet);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计会员钱包数量
    * @param name 名称
    * @return 会员钱包数量
    */
    int count(String name);

    /**
    * 加载会员钱包
    *
    * @param id 主键
    * @return 会员钱包
    */
    MbrWallet load(String id);


    /**
     * 通过会员ID获取钱包
     * @param memberId
     * @return
     */
    MbrWallet loadByMemberId(String memberId);

    /**
     * 会员消费
     * @param memberId
     * @param money
     * @return
     */
    int updateMoneySub(String memberId, BigDecimal money);
}
