package com.zzwdkj.ord.service;

import com.zzwdkj.common.Std;
import com.zzwdkj.mbr.entity.MbrCoin;
import com.zzwdkj.mbr.entity.MbrCoinChged;
import com.zzwdkj.mbr.entity.MbrWallet;
import com.zzwdkj.mbr.entity.MbrWalletChged;
import com.zzwdkj.ord.entity.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * ord, 订单_订单表SV
 *
 * @author guoxianwei  2016-09-07 15:01:44
 */

public interface OrdSV {


    /**
     * 查询订单_订单表 ，本月每天数据
     *钱包和微信、积分
     * @param
     * @param  bizNo 商家编号
     * @return 结果集
     */
    List<Ord> queryEveryQb(String bizNo);

    List<Ord> queryEveryWx(String bizNo);

    List<Ord> queryEveryCoin(String bizNo);

    /**
     * 查询订单_订单表 ，带分页
     *
     * @param params   参数
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 结果集
     */
    List<Ord> queryOrd(Map<String, Object> params, int rowStart, int rowSize);

    /**
     * 新增订单_订单表
     *
     * @param ord
     */
    void create(Ord ord);

    /**
     * 修改订单_订单表
     *
     * @param ord
     */
    void update(Ord ord);

    /**
     * 删除一条记录
     *
     * @param id
     */
    void remove(String id);

    /**
     * 统计订单_订单表数量
     *
     * @param params 参数
     * @return 订单_订单表数量
     */
    int count(Map<String, Object> params);

    /**
     * 加载订单_订单表
     *
     * @param id 主键
     * @return 订单_订单表
     */
    Ord load(String id);

    /**
     * 获取订单——订单表 通过订单号
     *
     * @param ordno 订单号
     * @return
     */
    Ord loadByOrdNo(String ordno);

    /**
     * 更改订单支付状态
     *
     * @param ordno  订单号
     * @param payWay 支付方式
     */
    void updateOrdPaySt(String ordno, Std.PayWay payWay);

    /**
     * 创建微信支付订单
     *
     * @param ord      订单
     * @param ordPayWx 微信支付
     */
    void createWxOrd(Ord ord, OrdPayWx ordPayWx);

    /**
     * 统计日收入
     *
     * @param bizNo 商家编号
     * @return 日收入微信
     */
    BigDecimal queryStaDailyInc(String bizNo);
    /**
     * 统计日收入
     *
     * @param bizNo 商家编号
     * @return 日收入钱包
     */
    BigDecimal queryStaDailyWltInc(String bizNo);
    /**
     * 统计日收入
     *
     * @param bizNo 商家编号
     * @return 日收入积分
     */
    BigDecimal queryStaDailyCoinsInc(String bizNo);
    /**
     * 统计日收入
     *
     * @param bizNo 商家编号
     * @return 日收入
     */
    BigDecimal queryStaDailyIncByPayWay(String type, Std.PayWay payWay, String bizNo);

    /**
     * 统计日收入
     *
     * @param gprsNo 模块编号
     * @return 日收入
     */
    BigDecimal queryStaDailyIncByProdNo(String gprsNo);

    /**
     * 统计日收入
     *
     * @param payWay 支付方式
     * @param prodNo 产品编号
     * @return 日收入
     */
    BigDecimal queryStaDailyIncByProdNoAndPayWay(Std.PayWay payWay, String prodNo);

    /**
     * 统计周收入
     *
     * @param bizNo 商家编号
     * @return 周收入微信
     */
    BigDecimal queryStaWeekInc(String bizNo);
    /**
     * 统计周收入
     *
     * @param bizNo 商家编号
     * @return 周收入钱包
     */
    BigDecimal queryStaWeekWltInc(String bizNo);
    /**
     * 统计周收入
     *
     * @param bizNo 商家编号
     * @return 周收入积分
     */
    BigDecimal queryStaWeekCoinsInc(String bizNo);
    /**
     * 统计周收入
     *
     * @param payWay 支付方式
     * @param bizNo  商家编号
     * @return 周收入
     */
    BigDecimal queryStaWeekIncByPayWay(String type, Std.PayWay payWay, String bizNo);

    /**
     * 统计周收入
     *
     * @param gprsNo 模块编号
     * @return 周收入
     */
    BigDecimal queryStaWeekIncByProdNo(String gprsNo);


    /**
     * 统计周收入
     *
     * @param payWay 支付方式
     * @param prodNo 产品编号
     * @return 周收入
     */
    BigDecimal queryStaWeekIncByProdNoAndPayWay(Std.PayWay payWay, String prodNo);

    /**
     * 统计日收入
     *
     * @param prodNo 产品编号
     * @return 日收入
     */
    Ord loadStaDailyIncWithPerPayWayByProdNo(String prodNo);

    /**
     * 统计日总收入 ，按支付方式输出值
     *
     * @param bizNo 商家编号
     * @return 日收入
     */
    Ord loadStaDailyTotalIncWithPerPayWay(String type, String bizNo);

    /**
     * 统计日总收入 ，按支付方式输出值
     *
     * @param bizNo 商家编号
     * @return 日收入
     */
    Ord loadStaDailyTotalIncWithPerPayWayTransfers(String type, String bizNo);

    /**
     * 统计周收入 ，按支付方式输出值
     *
     * @param prodNo 产品编号
     * @return 周收入
     */
    Ord loadStaWeekIncWithPerPayWayByProdNo(String prodNo);

    /**
     * 统计月收入 ，按支付方式输出值
     *
     * @param prodNo 产品编号
     * @return 月收入
     */
    Ord loadStaMonthIncWithPerPayWayByProdNo(String prodNo);

    /**
     * 统计一段时间内收入 ，按支付方式输出值
     *
     * @param prodNo    产品编号
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 时间段内收入
     */
    Ord loadStaPeriodIncWithPerPayWayByProdNo(String prodNo, String beginDate, String endDate);

    /**
     * 统计一段时间内总收入 ，按支付方式输出值
     *
     * @param bizNo     商家编号
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 时间段内收入
     */
    Ord loadStaPeriodTotalIncWithPerPayWay(String type, String bizNo, String beginDate, String endDate);

    /**
     * 统计网络收入 ，按支付方式输出值
     *
     * @param prodNo 产品编号
     * @return 收入
     */
    Ord loadStaNetIncPerPayWayByProdNo(String prodNo);

    /**
     * 统计总收入，按支付方式输出值
     *
     * @param type  设备类型
     * @param bizNo 商家编号
     * @return 总收入
     */
    Ord loadStaTotalIncWithPerPayWay(String type, String bizNo);

    /**
     * 统计月收入，按支付方式输出值
     *
     * @param type  设备类型
     * @param bizNo 商家编号
     * @return 月收入
     */
    Ord loadStaMonthTotalIncWithPerPayWay(String type, String bizNo);

    /**
     * 统计周收入，按支付方式输出值
     *
     * @param type  设备类型
     * @param bizNo  商家编号
     * @return 周收入
     */
    Ord loadStaWeekTotalIncWithPerPayWay(String type,  String bizNo);
    /**
     * 统计月收入
     *
     * @param bizNo 商家编号
     * @return 月收入微信
     */
    BigDecimal queryStaMonthInc(String bizNo);
    /**
     * 统计月收入
     *
     * @param bizNo 商家编号
     * @return 月收入钱包
     */
    BigDecimal queryStaMonthWltInc(String bizNo);
    /**
     * 统计月收入
     *
     * @param bizNo 商家编号
     * @return 月收入积分
     */
    BigDecimal queryStaMonthCoinsInc(String bizNo);
    /**
     * 统计月收入
     *
     * @param payWay 支付方式
     * @param bizNo  商家编号
     * @return 月收入
     */
    BigDecimal queryStaMonthIncByPayWay(String type, Std.PayWay payWay, String bizNo);

    /**
     * 统计月收入
     *
     * @param gprsNo 模块编号
     * @return 月收入
     */
    BigDecimal queryStaMonthIncByProdNo(String gprsNo);

    /**
     * 统计月收入
     *
     * @param payWay 支付方式
     * @param gprsNo 模块编号
     * @return 月收入
     */
    BigDecimal queryStaMonthIncByProdNoAndPayWay(Std.PayWay payWay, String gprsNo);


    /**
     * 统计网络收入
     *
     * @param bizNo 商家编号
     * @return 收入微信
     */
    BigDecimal queryStaNetInc(String bizNo);
    /**
     * 统计网络收入
     *
     * @param bizNo 商家编号
     * @return 收入钱包
     */
    BigDecimal queryStaWltInc(String bizNo);

    /**
     * 统计网络收入
     *
     * @param bizNo 商家编号
     * @return 收入积分
     */
    BigDecimal queryStaCoinsInc(String bizNo);
    /**
     * 统计网络收入
     *
     * @param gprsNo 模块编号
     * @return 收入
     */
    BigDecimal queryStaNetIncByProdNo(String gprsNo);

    /**
     * 统计网络收入
     *
     * @param payWay 支付方式
     * @param gprsNo 模块编号
     * @return 收入
     */
    BigDecimal queryStaNetIncByProdNoAndPayWay(Std.PayWay payWay, String gprsNo);

    /**
     * 统计一段时间内收入
     *
     * @param bizNo     商家编号
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 时间段内收入微信
     */
    BigDecimal queryStaPeriodInc(String bizNo, String beginDate, String endDate);
    /**
     * 统计一段时间内收入
     *
     * @param bizNo     商家编号
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 时间段内收入钱包
     */
    BigDecimal queryStaPeriodWltInc(String bizNo, String beginDate, String endDate);

    /**
     * 统计一段时间内收入
     *
     * @param bizNo     商家编号
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 时间段内收入积分
     */
    BigDecimal queryStaPeriodCoinsInc(String bizNo, String beginDate, String endDate);

    /**
     * 统计一段时间内收入
     *
     * @param payWay    支付方式
     * @param bizNo     商家编号
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 时间段内收入
     */
    BigDecimal queryStaPeriodIncByPayWay(String type, Std.PayWay payWay, String bizNo, String beginDate, String endDate);

    /**
     * 统计一段时间内收入
     *
     * @param payWay 支付方式
     * @param bizNo  商家编号
     * @return 时间段内收入
     */
    BigDecimal queryStaIncByPayWay(String type, Std.PayWay payWay, String bizNo);

    /**
     * 统计一段时间内收入
     *
     * @param gprsNo    模块编号
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 时间段内收入
     */
    BigDecimal queryStaPeriodIncByProdNo(String gprsNo, String beginDate, String endDate);


    /**
     * 统计一段时间内收入
     *
     * @param payWay    支付方式
     * @param gprsNo    模块编号
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 时间段内收入
     */
    BigDecimal queryStaPeriodIncByProdNoAndPayWay(Std.PayWay payWay, String gprsNo, String beginDate, String endDate);

    /**
     * 加载订单支付
     *
     * @param ordno 订单号
     * @return 微信支付
     */
    OrdPayWx loadOrdPayWxByOrdNo(String ordno);

    /**
     * 加载订单支付
     *
     * @param ordno
     * @return 支付宝支付
     */
    OrdPayAli loadOrdPayAliByOrdNo(String ordno);

    /**
     * 加载订单支付
     *
     * @param ordno
     * @return 钱包支付
     */
    OrdPayWlt loadOrdPayWltByOrdNo(String ordno);

    /**
     * 将所有待自动转账的订单状态改为结算中（1）
     *
     * @return
     */
    int updateAllTransfersOrd();


    /**
     * 查询每个待转账商家的金额、openid。
     *
     * @return
     */
    List<Map<String, Object>> queryTakeOrd();

    /**
     * 微信支付退款
     */
    void createRefundTaskWx(List<Map<String, Object>> ordList_wx ,Integer i);


    /**
     * 钱包支付退款
     */
    void createRefundTaskWallet(List<Map<String, Object>> ordList_wallet,Integer i);

    /**
     * 积分支付退还    （修改时间：12.7 ；修改人：sfn）
     *

    void createRefundTaskCoins(List<Map<String, Object>> ordList_coins,Integer i);

     */

    /**
    /**
     * 更新自动提款成功
     *
     * @param bizNo
     */
    void updateTransfersOrdSucess(String bizNo);

    /**
     * 自动提现总金额
     *
     * @param bizNo 商家编号
     * @return
     */
    BigDecimal queryAutoMoney(String bizNo);

    /**
     * 通过订单号更新状态
     *
     * @param ordno 订单号
     * @param state 状态
     */
    void updateStateByOrdno(String ordno, Integer state);

    /**
     * 创建钱包支付订单
     *
     * @param ord
     * @param mbrWalletChged
     * @param mbrWallet
     * @param ordPayWlt
     */
    String createWalletOrd(Ord ord, OrdPayWlt ordPayWlt, MbrWallet mbrWallet, MbrWalletChged mbrWalletChged,OrdPayCoin ordPayCoin, MbrCoin mbrCoin,MbrCoinChged mbrCoinChged);//（修改时间：12.8 ；修改人：sfn）

    /**
     * 创建积分支付订单       （修改时间：12.7 ；修改人：sfn）
     *
     * @param ord
     * @param mbrCoinChged
     * @param mbrCoin
     * @param ordPayCoin

    String createCoinOrd(Ord ord, OrdPayCoin ordPayCoin, MbrCoin mbrCoin, MbrCoinChged mbrCoinChged);
     */


    /**
     * 查询订单，关联OrdPayWx
     *
     * @param params
     * @param rowStart
     * @param pageSize
     * @return
     */
    List<Ord> queryOrdJoinOrdPayWx(Map<String, Object> params, int rowStart, int pageSize);

    /**
     * 统计个数，关联OrdPayWx
     *
     * @param params
     * @return
     */
    int countOrdJoinOrdPayWx(Map<String, Object> params);

    /**
     * 统计商品收入
     *
     * @param prodNo 商品编号
     * @return 收入
     */
    BigDecimal queryStaIncByProdNo(String prodNo);

    /**
     * 通过GPRS编号统计营收
     * @param gprsNo GPRS编号
     * @param bizNo 商家编号
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    Ord loadStaPeriodIncWithPerPayWayByGprsNo(String gprsNo, String bizNo, String beginDate, String endDate);

    /**
     * 查询钱包退款订单
     * @param params_ord_wallet
     * @return
     */
    List<Map<String,Object>> queryRefundOrd(Map<String, Object> params_ord_wallet);

    /**
     * 查询商家当天的订单
     * @param bizNo
     * @return
     */
    List<Map<String,Object>> quryTodayMapOrd(String bizNo);

    /**
     * 通过订单号获取订单信息
     * @param ordNo
     * @param bizNo
     * @return
     */
    Map<String,Object> loadMapOrdByOrdNo(String ordNo,String bizNo);

    /**
     * 获取用户的全部订单
     * @param params
     * @param rowStart
     * @param pageSize
     * @return
     */
    List<Ord> queryMbrAllOrd(Map<String, Object> params, int rowStart, int pageSize);

    /**
     * 获取用户的全部订单数量
     * @param params
     * @return
     */
    int countMbrAllOrd(Map<String, Object> params);

}
