package com.zzwdkj.biz.service;

import com.zzwdkj.biz.entity.BizWlt;

import java.util.List;

/**
* plat_wlt, 商家_钱包SV
*
* @author guoxianwei  2016-09-07 15:01:55
*/

public interface BizWltSV {

    /**
    * 加载商家_钱包
    *
    * @param id 主键
    * @return 商家_钱包
    */
    BizWlt load(String id);

    /**
     * 加载商家钱包
     * @param bizNo 商家编号
     * @return 商家_钱包
     */
    BizWlt loadByBizNo(String bizNo);
    /**
     * 通过商家编号获取 商家_钱包
     * @param bizNo
     * @return
     */
    BizWlt loadByBizId(String bizNo);
}
