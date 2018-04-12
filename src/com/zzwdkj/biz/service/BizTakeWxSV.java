package com.zzwdkj.biz.service;

import com.zzwdkj.common.Std;
import com.zzwdkj.biz.entity.BizTakeWx;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
* biz_take_wx, 商家_提现_微信自动转账SV
*
* @author guoxianwei  2016-11-15 09:27:26
*/

public interface BizTakeWxSV {

    /**
    * 查询商家_提现_微信自动转账 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<BizTakeWx> queryBizTakeWx(String name, int rowStart, int rowSize);

    /**
    * 新增商家_提现_微信自动转账
    *
    * @param bizTakeWx
    */
    void create(BizTakeWx bizTakeWx);

    /**
    * 修改商家_提现_微信自动转账
    *
    * @param bizTakeWx
    */
    void update(BizTakeWx bizTakeWx);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计商家_提现_微信自动转账数量
    * @param name 名称
    * @return 商家_提现_微信自动转账数量
    */
    int count(String name);

    /**
    * 加载商家_提现_微信自动转账
    *
    * @param id 主键
    * @return 商家_提现_微信自动转账
    */
    BizTakeWx load(String id);


    /**
     * 通过商家编号获取 商家_提现_微信自动转账
     * @param bizNo
     * @return
     */
    BizTakeWx loadByBizNo(String bizNo);
}
