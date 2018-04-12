package com.zzwdkj.biz.service;

import com.zzwdkj.common.Std;
import com.zzwdkj.biz.entity.BizGift;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
* biz_gift, 满赠活动SV
*
* @author guoxianwei  2016-11-11 17:52:57
*/

public interface BizGiftSV {

    /**
    * 查询满赠活动 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<BizGift> queryBizGift(String name, int rowStart, int rowSize);

    /**
    * 新增满赠活动
    *
    * @param bizGift
    */
    void create(BizGift bizGift);

    /**
    * 修改满赠活动
    *
    * @param bizGift
    */
    void update(BizGift bizGift);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计满赠活动数量
    * @param name 名称
    * @return 满赠活动数量
    */
    int count(String name);

    /**
    * 加载满赠活动
    *
    * @param id 主键
    * @return 满赠活动
    */
    BizGift load(String id);


    /**
     * 查询商家的活动
     * @param bizNo
     * @param state
     */
    List<BizGift>  queryBizGiftListByBizNo(String bizNo,Integer state);

    /**
     *查询充值的钱数是否达到商家的活动
     * @param bizNo
     * @param coin
     * @return
     */
    List<BizGift> queryByCoinAndBizNo(String bizNo, Integer coin);

    /**
     * 查询
     * @param params
     * @param rowStart
     * @param pageSize
     * @return
     */
    List<BizGift> queryBizGiftByMap(Map<String, Object> params, int rowStart, int pageSize);

    /**
     * 根据条件查询个数
     * @param params
     * @return
     */
    int countBizGiftByMap(Map<String, Object> params);

    /**
     * 获取某个会员参加某个活动的次数
     * @param memberId 会员ID
     * @param giftId 活动ID
     * @return
     */
    Integer queryBizGiftTotalByMemberId(String memberId, String giftId);

    /**
     * 根据活动ID查询该活动已经参加的人数
     * @param giftId 活动ID
     * @return
     */
    Integer queryBizGiftTotalByALL(String giftId);

    /**
     * 更新活动总库存
     * @param id  活动ID
     * @return
     */
    int updateStockAll(String id);

}
