package com.zzwdkj.prod.service;

import com.zzwdkj.ord.entity.Ord;
import com.zzwdkj.prod.entity.ProdCoinRpt;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * prod_coin_rpt, 产品_投币上报表SV
 *
 * @author guoxianwei  2016-09-07 15:01:58
 */

public interface ProdCoinRptSV {

    /*
    * 刷卡收入折线图
    * */
    List<ProdCoinRpt> queryStaEverySk(String biz_no);
    /*
    * 投币收入折线图
    * */
    List<ProdCoinRpt> queryStaEveryTb(String biz_no);

    /**
     * 查询产品_投币上报表 ，带分页
     *
     * @param name     名称
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 结果集
     */
    List<ProdCoinRpt> queryProdCoinRpt(String name, int rowStart, int rowSize);

    /**
     * 新增产品_投币上报表
     *
     * @param prodCoinRpt
     */
    void create(ProdCoinRpt prodCoinRpt);

    /**
     * 修改产品_投币上报表
     *
     * @param prodCoinRpt
     */
    void update(ProdCoinRpt prodCoinRpt);

    /**
     * 删除一条记录
     *
     * @param id
     */
    void remove(String id);

    /**
     * 统计产品_投币上报表数量
     *
     * @param name 名称
     * @return 产品_投币上报表数量
     */
    int count(String name);

    /**
     * 加载产品_投币上报表
     *
     * @param id 主键
     * @return 产品_投币上报表
     */
    ProdCoinRpt load(String id);

    /**
     * 统计投币收入
     *
     * @param bizNo 商家编号
     * @return 收入
     */
    BigDecimal queryStaCoinInc(String devType, String bizNo);


    /**
     * 统计投币收入
     *
     * @param prodNo 产品编号
     * @return 收入
     */
    BigDecimal queryStaCoinIncByProdNo(String prodNo);

    /**
     * 统计刷卡收入
     *
     * @param bizNo 商家编号
     * @return 收入
     */
    BigDecimal queryStaCardInc(String type, String bizNo);

    /**
     * 统计刷卡收入
     *
     * @param prodNo 产品编号
     * @return 收入
     */
    BigDecimal queryStaCardIncByProdNo(String prodNo);

    /**
     * 统计时间段内投币收入
     *
     * @param bizNo     商家编号
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 收入
     */
    BigDecimal queryStaPeriodCoinInc(String type, String bizNo, String beginDate, String endDate);

    /**
     * 统计时间段内投币收入
     *
     * @param gprsNo    产品编号
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 收入
     */
    BigDecimal queryStaPeriodCoinIncByProdNo(String gprsNo, String beginDate, String endDate);

    /**
     * 统计时间段内刷卡收入
     *
     * @param bizNo     商家编号
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 收入
     */
    BigDecimal queryStaPeriodCardInc(String type, String bizNo, String beginDate, String endDate);

    /**
     * 统计时间段内刷卡收入,按支付方式输出值
     *
     * @param bizNo     商家编号
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 收入
     */
    ProdCoinRpt loadStaPeriodTotalIncWithPerPayWay(String type, String bizNo, String beginDate, String endDate);


    /**
     * 统计时间段内刷卡收入,按支付方式输出值
     *
     * @param prodNo    产品编号
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 收入
     */
    ProdCoinRpt loadStaPeriodIncWithPerPayWayByProdNo(String prodNo, String beginDate, String endDate);

    /**
     * 统计刷卡收入,按支付方式输出值
     *
     * @param type  产品类型
     * @param bizNo 商家编号
     * @return 收入
     */
    ProdCoinRpt loadStaTotalIncWithPerPayWay(String type, String bizNo);

    /**
     * 统计刷卡收入,按支付方式输出值
     *
     * @param prodNo 产品编号
     * @return 收入
     */
    ProdCoinRpt loadStaIncWithPerPayWayByProdNo(String prodNo);

    /**
     * 统计日收入,按支付方式输出值
     *
     * @param prodNo 产品编号
     * @return 收入
     */
    ProdCoinRpt loadStaDayIncWithPerPayWayByProdNo(String prodNo);

    /**
     * 统计刷卡日收入,按支付方式输出值
     *
     * @param bizNo 商家编号
     * @return 收入
     */
    ProdCoinRpt loadStaDayTotalIncWithPerPayWay(String type, String bizNo);

    /**
     * 统计周收入,按支付方式输出值
     *
     * @param prodNo 产品编号
     * @return 收入
     */
    ProdCoinRpt loadStaWeekIncWithPerPayWayByProdNo(String prodNo);

    /**
     * 统计刷卡周收入,按支付方式输出值
     *
     * @param type  产品类型
     * @param bizNo 商家编号
     * @return 收入
     */
    ProdCoinRpt loadStaWeekTotalIncWithPerPayWay(String type, String bizNo);

    /**
     * 统计刷卡月收入,按支付方式输出值
     *
     * @param prodNo 产品编号
     * @return 收入
     */
    ProdCoinRpt loadStaMonthIncWithPerPayWayByProdNo(String prodNo);

    /**
     * 统计刷卡月收入
     * @param type  产品类型
     * @param bizNo 商家编号
     * @return 收入
     */
    ProdCoinRpt loadStaMonthTotalIncWithPerPayWay(String type, String bizNo);

    /**
     * 统计时间段内刷卡收入
     *
     * @param gprsNo    产品编号
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 收入
     */
    BigDecimal queryStaPeriodCardIncByProdNo(String gprsNo, String beginDate, String endDate);


    /**
     * 统计投币日收入
     *
     * @param bizNo 商家编号
     * @return 收入
     */
    BigDecimal queryStaDayCoinInc(String type, String bizNo);

    /**
     * 统计投币日收入
     *
     * @param gprsNo 产品编号
     * @return 收入
     */
    BigDecimal queryStaDayCoinIncByProdNo(String gprsNo);

    /**
     * 统计投币周收入
     *
     * @param bizNo 商家编号
     * @return 收入
     */
    BigDecimal queryStaWeekCoinInc(String type, String bizNo);

    /**
     * 统计投币周收入
     *
     * @param gprsNo 产品编号
     * @return 收入
     */
    BigDecimal queryStaWeekCoinIncByProdNo(String gprsNo);

    /**
     * 统计投币月收入
     *
     * @param bizNo 商家编号
     * @return 收入
     */
    BigDecimal queryStaMonthCoinInc(String type, String bizNo);

    /**
     * 统计投币月收入
     *
     * @param prodNo 产品编号
     * @return 收入
     */
    BigDecimal queryStaMonthCoinIncByProdNo(String prodNo);

    /**
     * 统计刷卡日收入
     *
     * @param bizNo 商家编号
     * @return 收入
     */
    BigDecimal queryStaDayCardInc(String type, String bizNo);

    /**
     * 统计刷卡日收入
     *
     * @param prodNo 产品编号
     * @return 收入
     */
    BigDecimal queryStaDayCardIncByProdNo(String prodNo);

    /**
     * 统计刷卡周收入
     *
     * @param bizNo 商家编号
     * @return 收入
     */
    BigDecimal queryStaWeekCardInc(String type, String bizNo);

    /**
     * 统计刷卡周收入
     *
     * @param prodNo 产品编号
     * @return 收入
     */
    BigDecimal queryStaWeekCardIncByProdNo(String prodNo);

    /**
     * 统计刷卡月收入
     *
     * @param bizNo 商家编号
     * @return 收入
     */
    BigDecimal queryStaMonthCardInc(String type, String bizNo);

    /**
     * 统计刷卡月收入
     *
     * @param prodNo 产品编号
     * @return 收入
     */
    BigDecimal queryStaMonthCardIncByProdNo(String prodNo);

    /**
     * 统计收入排行
     *
     * @param bizNo 商家编号
     * @return 收入排行
     */
    List<ProdCoinRpt> queryStaIncSort(String type, String bizNo, int rowStart, int rowSize);

    /**
     * 统计收入排行行数
     *
     * @param bizNo 商家编号
     * @return 收入排行
     */
    int countStaIncSort(String bizNo);

    /**
     * 更加商品编号统计收入
     *
     * @param prodNo 商品编号
     * @return 收入
     */
    BigDecimal queryStaIncByProdNo(String prodNo);
}
