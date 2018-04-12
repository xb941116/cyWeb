package com.zzwdkj.mbr.service;

import com.zzwdkj.common.Std;
import com.zzwdkj.mbr.entity.MbrCoin;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
* mbr_coin, 会员积分表SV
*
* @author guoxianwei  2016-11-11 15:55:27
*/
@Service("mbrCoinSv")
public interface MbrCoinSV {

    /**
    * 查询会员积分表 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<MbrCoin> queryMbrCoin(String name, int rowStart, int rowSize);

    /**
    * 新增会员积分表
    *
    * @param mbrCoin
    */
    void create(MbrCoin mbrCoin);

    /**
    * 修改会员积分表
    *
    * @param mbrCoin
    */
    void update(MbrCoin mbrCoin);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计会员积分表数量
    * @param name 名称
    * @return 会员积分表数量
    */
    int count(String name);

    /**
    * 加载会员积分表
    *
    * @param id 主键
    * @return 会员积分表
    */
    MbrCoin load(String id);

    /**
     * 会员消费                  （此处修改；时间：12.7；修改人：sfn）
     * @param memberId
     * @param coin
     * @return
     *
     */

    int updateCoinSub(String memberId, BigDecimal coin);


    /**
     * 通过用户id获取积分表
     * @param memberId 会员id
     * @return
     */
    MbrCoin loadByMemberId(String memberId);
}
