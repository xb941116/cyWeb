package com.zzwdkj.biz.service;

import com.zzwdkj.biz.entity.Biz;
import com.zzwdkj.biz.entity.BizTake;
import com.zzwdkj.biz.entity.BizTakeBank;
import com.zzwdkj.biz.entity.BizTakeWwlt;

import java.util.List;
import java.util.Map;

/**
 * biz_take, 商家_提现SV
 *
 * @author guoxianwei  2016-09-07 15:01:31
 */

public interface BizTakeSV {

    /**
     * 查询商家_提现 ，带分页
     *
     * @param name     名称
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 结果集
     */
    List<BizTake> queryBizTake(String name, int rowStart, int rowSize);

    /**
     * 新增商家_提现
     *
     * @param bizTake
     */
    void create(BizTake bizTake);

    /**
     * 修改商家_提现
     *
     * @param bizTake
     */
    void update(BizTake bizTake);

    /**
     * 删除一条记录
     *
     * @param id
     */
    void remove(String id);

    /**
     * 统计商家_提现数量
     *
     * @param name 名称
     * @return 商家_提现数量
     */
    int count(String name);

    /**
     * 加载商家_提现
     *
     * @param id 主键
     * @return 商家_提现
     */
    BizTake load(String id);


    /**
     * 查询
     *
     * @param params   条件MAP
     * @param rowStart 起始位置
     * @param pageSize 分页大小
     * @return
     */
    List<BizTake> query(Map<String, Object> params, int rowStart, int pageSize);

    /**
     * 查询个数
     *
     * @param params 条件MAP
     * @return
     */
    int count(Map<String, Object> params);

    /**
     * 创建提款记录
     *
     * @param bizTake
     * @param bizTakeBank
     * @param bizTakeWwlt
     * @param biz
     */
    String create(BizTake bizTake, BizTakeBank bizTakeBank, BizTakeWwlt bizTakeWwlt, Biz biz);

    /**
     * 修改提款记录
     *
     * @param bizTake
     * @param bizTakeBank
     * @param bizTakeWwlt
     * @param biz
     */
    void update(BizTake bizTake, BizTakeBank bizTakeBank, BizTakeWwlt bizTakeWwlt, Biz biz);

    /**
     * 查询提现列表
     *
     * @param params
     * @param rowStart
     * @param pageSize
     * @return
     */
    List<BizTake> queryJoinBiz(Map<String, Object> params, int rowStart, int pageSize);

    /**
     * 查询提现列表
     *
     * @param params
     * @return
     */
    int countJoinBiz(Map<String, Object> params);

    /**
     * 自动转账成功
     * @param reqno
     * @param wxAcct
     */
    void updateTransfersSuccess(String reqno, String wxAcct);

    /**
     * 自动转账失败
     * @param partner_trade_no
     * @param expln
     */
    void updateTransfersFail(String partner_trade_no,String expln);

    /**
     * 自动转账
     */
    void createTransfersTask( List<Map<String,Object>>  takeOrdList, Integer i);

    /**
     * 通过申请单号获取  BizTake
     * @param reqno 申请单号
     * @return
     */
    BizTake loadByReqno(String reqno);
}
