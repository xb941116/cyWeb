package com.zzwdkj.prod.service;

import com.zzwdkj.prod.entity.*;

import java.util.List;
import java.util.Map;

/**
 * prod, 产品表SV
 *
 * @author guoxianwei  2016-09-07 15:01:56
 */

public interface ProdSV {

    /**
     * 查询产品表 ，带分页
     *
     * @param state    状态
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 结果集
     */
    List<Prod> queryProd(String state, int rowStart, int rowSize);

    /**
     * 统计产品表数量
     *
     * @param state 状态
     * @return 产品表数量
     */
    int count(String state);

    /**
     * 查询产品表 ，带分页
     *
     * @param params   参数
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 结果集
     */
    List<Prod> queryProd(Map params, int rowStart, int rowSize);

    /**
     * 查询离线产品表 ，带分页
     *
     * @param params   参数
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 结果集
     */
    List<ProdOnlLog> queryProdOffLine(Map params, int rowStart, int rowSize);
    /**
     * 查询在线产品表 ，带分页
     *
     * @param params   参数
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 结果集
     */
    List<ProdOnlLog> queryProdOnLine(Map<String, Object> params, int rowStart, int rowSize);
    /**
     * 统计离线产品表数量
     *
     * @param params 参数
     * @return 产品表数量
     */
    int countProdOffLine(Map params);

    /**
     * 统计在线产品表数量
     *
     * @param params 参数
     * @return 产品表数量
     */
    int countProdOnLine(Map params);
    /**
     * 统计产品表数量
     *
     * @param params 参数
     * @return 产品表数量
     */
    int count(Map params);

    /**
     * 统计产品表数量
     *
     * @param params 参数
     * @return 产品表数量
     */
    int countProd(Map params);

    /**
     * 新增产品表
     *
     * @param prod
     */
    void create(Prod prod);

    /**
     * 修改产品表
     *
     * @param prod
     */
    void update(Prod prod);

    /**
     * 删除一条记录
     *
     * @param id
     */
    void remove(String id);

    /**
     * 加载产品表
     *
     * @param id 主键
     * @return 产品表
     */
    Prod load(String id);

    /**
     * 加载产品表
     *
     * @param gprsNo 模块号
     * @return 产品表
     */
    Prod loadByGprsNo(String gprsNo);

    /**
     * 统计产品表数量
     *
     * @param gprsNo 模块号
     * @return 产品表数量
     */
    int countByGprsNo(String gprsNo);

    /**
     * 创建产品
     *
     * @param gprsNo    模块号
     * @param prodModel 商品模板
     */
    void createProd(String gprsNo, ProdModel prodModel);

    /**
     * 修改产品属性
     *
     * @param gprsNo    模块号
     * @param prodModel 商品模板
     */
    void updateProd(String gprsNo, ProdModel prodModel);
    /**
     * 更改产品是否生成二维码标示
     *
     * @param bizNo  商家编号
     * @param gprsNo 模块号
     * @param gened  是否标示（0否；1是）
     */
    void updateProdQrSt(String bizNo, String gprsNo, Integer gened);

    /**
     * 统计设备在线数量
     *
     * @param bizNo 商家编号
     * @return 数量
     */
    int statProdOnLineCount(String bizNo);

    /**
     * 统计设备离线数量
     *
     * @param bizNo 商家编号
     * @return 数量
     */
    int statProdOffLineCount(String bizNo);

    /**
     * 查询在线产品
     *
     * @param params   参数
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 结果集
     */
    List<Prod> queryProdOnline(Map<String, Object> params, int rowStart, int rowSize);

    /**
     * 查询在线产品
     *
     * @param bizNo 商家编号
     * @return 结果集
     */
    List<Prod> queryProdOnlineByBizNo(String bizNo);

    /**
     * 统计设备在线数量
     *
     * @param params 参数
     * @return 数量
     */
    int countProdOnline(Map<String, Object> params);

    /**
     * 加载产品特定参数
     *
     * @param prodNo 产品编号
     * @return 参数
     */
    List<ProdSpArgs> queryProdSpArgs(String prodNo);

    /**
     * 加载产品安装位置
     *
     * @param prodNo 产品编号
     * @return 安装位置
     */
    ProdInstlPos loadProdInstlPos(String prodNo);

    /**
     * 删除产品问题报告
     *
     * @param prodNo 产品编号
     */
    void removeProdBugRpt(String prodNo);

    /**
     * 加载产品问题报告
     *
     * @param prodNo 产品编号
     * @return 产品问题报告
     */
    ProdBugRpt loadProdBugRpt(String prodNo);

    /**
     * 创建设备安装位置
     *
     * @param prodInstlPos 位置
     */
    void createProdInstlPos(ProdInstlPos prodInstlPos);

    /**
     * 查询所有在线设备
     *
     * @return 在线设备
     */
    List<ProdOnlLog> queryAllOnlineProd();

    /**
     * 根据产品号加载在线设备
     *
     * @return 在线设备
     */
    ProdOnlLog loadProdOnline(String prodNo);

    /**
     * 创建上报记录
     *
     * @param bizNo 商家编号
     * @param type 类别
     */
    void createProdCoinRptLog(String bizNo, Integer type);

    /**
     * 统计上报次数
     *
     * @param bizNo 商家编号
     * @param type 类型
     * @return 次数
     */
    int countCurrentDayProdCoinRptLogTimes(String bizNo, Integer type);

    /**
     * 查询带有安装地址的商品列表
     *
     * @param bizNo    商品编号
     * @param rowStart 起始行
     * @param pageSize 页数
     * @return 结果集
     */
    List<Prod> queryProdWithInstlPos(String bizNo, int rowStart, int pageSize);

    /**
     * 根据商家编号统计商品数量
     * @param bizNo 商家编号
     * @return 数量
     */
    int countProdByBizNo(String bizNo);

    /**
     * 通过订单表的商家编号找到商家所有的产品列表
     *
     * @param params
     * @return
     */
    List<Prod> queryProdByOrdBizNo(Map<String, Object> params, int rowStart, int pageSize);

    /**
     * 通过订单表的商家编号找到商家所有的产品列表个数
     * @param params
     * @return
     */
    int countProdByOrdBizNo(Map<String, Object> params);

    /**
     * 查询故障设备列表
     * @param params
     * @param rowStart
     * @param pageSize
     * @return
     */
    List<Prod> queryProdBugRpt(Map<String, Object> params, int rowStart, int pageSize);

    /**
     * 查询故障设备总数量
     * @param params
     * @return
     */
    int countProdBugRpt(Map<String, Object> params);

    /**
     *
     * @param bizNo
     * @param gprsType
     * @return
     */
    List<Prod> queryOnlineProdByGprsType(String bizNo, String gprsType);

    /**
     * 获取商家的所有设备的上下线状态
     * @param bizNo
     * @return
     */
    List<Map<String,Object>> queryProdOnlineState(String bizNo);
}
