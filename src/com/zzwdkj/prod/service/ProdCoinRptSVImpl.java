package com.zzwdkj.prod.service;

import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.common.DateUtil;
import com.zzwdkj.ord.entity.Ord;
import com.zzwdkj.prod.dao.ProdCoinRptDAO;
import com.zzwdkj.prod.entity.ProdCoinRpt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * prod_coin_rpt, 产品_投币上报表SVImpl
 *
 * @author guoxianwei  2016-09-07 15:01:58
 */
@Service("prodCoinRptSV")
public class ProdCoinRptSVImpl implements ProdCoinRptSV {

    @Resource
    private ProdCoinRptDAO prodCoinRptDAO;
    @Resource
    private IdentifierSV identifierSV;
    /*
    * 投币收入折线图
    * */
    public  List<ProdCoinRpt> queryStaEveryTb(String biz_no){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("biz_no",biz_no);
        return prodCoinRptDAO.query("queryStaEveryTb",params);
    }
    /*
    * 刷卡收入折线图
    * */
    public  List<ProdCoinRpt> queryStaEverySk(String biz_no){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("biz_no",biz_no);
        return prodCoinRptDAO.query("queryStaEverySk",params);
    }

    /**
     * 查询产品_投币上报表 ，带分页
     *
     * @param name     菜单名称
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 菜单资源
     */
    @Override
    public List<ProdCoinRpt> queryProdCoinRpt(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return prodCoinRptDAO.query(params, rowStart, rowSize);
    }

    /**
     * 统计产品_投币上报表数量
     *
     * @param name 名称
     * @return 产品_投币上报表
     */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);

        return prodCoinRptDAO.count(params);
    }


    /**
     * 新增产品_投币上报表
     *
     * @param prodCoinRpt
     */
    @Override
    public void create(ProdCoinRpt prodCoinRpt) {
        prodCoinRpt.setId(identifierSV.uniqueId());
        prodCoinRpt.setCrtime(new Date());
        prodCoinRptDAO.insert(prodCoinRpt);
    }

    /**
     * 修改产品_投币上报表
     *
     * @param prodCoinRpt
     */
    @Override
    public void update(ProdCoinRpt prodCoinRpt) {
        prodCoinRptDAO.update(prodCoinRpt);
    }

    /**
     * 删除一条记录
     *
     * @param id
     */
    @Override
    public void remove(String id) {
        prodCoinRptDAO.delete(id);
    }

    /**
     * 加载产品_投币上报表
     *
     * @param id 主键
     * @return 产品_投币上报表
     */
    @Override
    public ProdCoinRpt load(String id) {
        return prodCoinRptDAO.load(id);
    }


    /**
     * 统计投币收入
     *
     * @param bizNo 商家编号
     * @return 收入
     */
    @Override
    public BigDecimal queryStaCoinInc(String devType, String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("type", devType);
        params.put("bizNo", bizNo);
        ProdCoinRpt prodCoinRpt = prodCoinRptDAO.queryUnique("queryStaCoinInc", params);
        if (prodCoinRpt != null && prodCoinRpt.getMoney() != null) {
            return prodCoinRpt.getMoney();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计投币收入
     *
     * @param prodNo 模块编号
     * @return 收入
     */
    @Override
    public BigDecimal queryStaCoinIncByProdNo(String prodNo) {
        ProdCoinRpt prodCoinRpt = prodCoinRptDAO.queryUnique("queryStaCoinIncByProdNo", prodNo);
        if (prodCoinRpt != null && prodCoinRpt.getMoney() != null) {
            return prodCoinRpt.getMoney();
        }
        return BigDecimal.ZERO;
    }


    /**
     * 统计刷卡收入
     *
     * @param bizNo 商家编号
     * @return 收入
     */
    @Override
    public BigDecimal queryStaCardInc(String type, String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        params.put("type", type);
        ProdCoinRpt prodCoinRpt = prodCoinRptDAO.queryUnique("queryStaCardInc", params);
        if (prodCoinRpt != null && prodCoinRpt.getMoney() != null) {
            return prodCoinRpt.getMoney();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计刷卡收入
     *
     * @param prodNo 模块编号
     * @return 收入
     */
    @Override
    public BigDecimal queryStaCardIncByProdNo(String prodNo) {
        ProdCoinRpt prodCoinRpt = prodCoinRptDAO.queryUnique("queryStaCardIncByProdNo", prodNo);
        if (prodCoinRpt != null && prodCoinRpt.getMoney() != null) {
            return prodCoinRpt.getMoney();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计时间段内投币收入
     *
     * @param bizNo     商家编号
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 收入
     */
    @Override
    public BigDecimal queryStaPeriodCoinInc(String type, String bizNo, String beginDate, String endDate) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("type", type);
        params.put("bizNo", bizNo);
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        ProdCoinRpt prodCoinRpt = prodCoinRptDAO.queryUnique("queryStaPeriodCoinInc", params);
        if (prodCoinRpt != null && prodCoinRpt.getMoney() != null) {
            return prodCoinRpt.getMoney();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计时间段内投币收入
     *
     * @param prodNo    模块编号
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 收入
     */
    @Override
    public BigDecimal queryStaPeriodCoinIncByProdNo(String prodNo, String beginDate, String endDate) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("prodNo", prodNo);
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        ProdCoinRpt prodCoinRpt = prodCoinRptDAO.queryUnique("queryStaPeriodCoinIncByProdNo", params);
        if (prodCoinRpt != null && prodCoinRpt.getMoney() != null) {
            return prodCoinRpt.getMoney();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计时间段内刷卡收入
     *
     * @param bizNo     商家编号
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 收入
     */
    @Override
    public BigDecimal queryStaPeriodCardInc(String type, String bizNo, String beginDate, String endDate) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("type", type);
        params.put("bizNo", bizNo);
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        ProdCoinRpt prodCoinRpt = prodCoinRptDAO.queryUnique("queryStaPeriodCardInc", params);
        if (prodCoinRpt != null && prodCoinRpt.getMoney() != null) {
            return prodCoinRpt.getMoney();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计时间段内刷卡收入
     *
     * @param bizNo     商家编号
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 收入
     */
    @Override
    public ProdCoinRpt loadStaPeriodTotalIncWithPerPayWay(String type, String bizNo, String beginDate, String endDate) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        params.put("type", type);
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        ProdCoinRpt prodCoinRpt = prodCoinRptDAO.queryUnique("loadStaPeriodTotalIncWithPerPayWay", params);
        if (prodCoinRpt != null) {
            prodCoinRpt.setCardInc(prodCoinRpt.getCardInc() == null ? BigDecimal.ZERO : prodCoinRpt.getCardInc());
            prodCoinRpt.setCoinInc(prodCoinRpt.getCoinInc() == null ? BigDecimal.ZERO : prodCoinRpt.getCoinInc());
        }else {
            prodCoinRpt = new ProdCoinRpt();
        }
        return prodCoinRpt;
    }

    /**
     * 统计时间段内刷卡收入,按支付方式输出值
     *
     * @param prodNo    产品编号
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 收入
     */
    @Override
    public ProdCoinRpt loadStaPeriodIncWithPerPayWayByProdNo(String prodNo, String beginDate, String endDate) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("prodNo", prodNo);
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        ProdCoinRpt prodCoinRpt = prodCoinRptDAO.queryUnique("loadStaPeriodIncWithPerPayWayByProdNo", params);
        if (prodCoinRpt != null) {
            prodCoinRpt.setCardInc(prodCoinRpt.getCardInc() == null ? BigDecimal.ZERO : prodCoinRpt.getCardInc());
            prodCoinRpt.setCoinInc(prodCoinRpt.getCoinInc() == null ? BigDecimal.ZERO : prodCoinRpt.getCoinInc());
        }else {
            prodCoinRpt = new ProdCoinRpt();
        }
        return prodCoinRpt;
    }

    /**
     * 统计刷卡收入,按支付方式输出值
     *
     * @param type   设备类型
     * @param bizNo 商家编号
     * @return 收入
     */
    @Override
    public ProdCoinRpt loadStaTotalIncWithPerPayWay(String type, String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        params.put("type", type);
        ProdCoinRpt prodCoinRpt = prodCoinRptDAO.queryUnique("loadStaTotalIncWithPerPayWay", params);
        if (prodCoinRpt != null) {
            prodCoinRpt.setCardInc(prodCoinRpt.getCardInc() == null ? BigDecimal.ZERO : prodCoinRpt.getCardInc());
            prodCoinRpt.setCoinInc(prodCoinRpt.getCoinInc() == null ? BigDecimal.ZERO : prodCoinRpt.getCoinInc());
        }else {
            prodCoinRpt = new ProdCoinRpt();
        }
        return prodCoinRpt;
    }

    /**
     * 统计刷卡收入,按支付方式输出值
     *
     * @param prodNo 产品编号
     * @return 收入
     */
    @Override
    public ProdCoinRpt loadStaIncWithPerPayWayByProdNo(String prodNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("prodNo", prodNo);
        ProdCoinRpt prodCoinRpt = prodCoinRptDAO.queryUnique("loadStaIncWithPerPayWayByProdNo", params);
        if (prodCoinRpt != null) {
            prodCoinRpt.setCardInc(prodCoinRpt.getCardInc() == null ? BigDecimal.ZERO : prodCoinRpt.getCardInc());
            prodCoinRpt.setCoinInc(prodCoinRpt.getCoinInc() == null ? BigDecimal.ZERO : prodCoinRpt.getCoinInc());
        }else {
            prodCoinRpt = new ProdCoinRpt();
        }
        return prodCoinRpt;
    }

    /**
     * 统计日收入,按支付方式输出值
     *
     * @param prodNo 产品编号
     * @return 收入
     */
    @Override
    public ProdCoinRpt loadStaDayIncWithPerPayWayByProdNo(String prodNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("prodNo", prodNo);
        ProdCoinRpt prodCoinRpt = prodCoinRptDAO.queryUnique("loadStaDayIncWithPerPayWayByProdNo", params);
        if (prodCoinRpt != null) {
            prodCoinRpt.setCardInc(prodCoinRpt.getCardInc() == null ? BigDecimal.ZERO : prodCoinRpt.getCardInc());
            prodCoinRpt.setCoinInc(prodCoinRpt.getCoinInc() == null ? BigDecimal.ZERO : prodCoinRpt.getCoinInc());
        }else {
            prodCoinRpt = new ProdCoinRpt();
        }
        return prodCoinRpt;
    }

    /**
     * 统计刷卡日收入,按支付方式输出值
     *
     * @param type  设备类型
     * @param bizNo 商家编号
     * @return 收入
     */
    @Override
    public ProdCoinRpt loadStaDayTotalIncWithPerPayWay(String type, String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        params.put("type", type);
        ProdCoinRpt prodCoinRpt = prodCoinRptDAO.queryUnique("loadStaDayTotalIncWithPerPayWay", params);
        if (prodCoinRpt != null) {
            prodCoinRpt.setCardInc(prodCoinRpt.getCardInc() == null ? BigDecimal.ZERO : prodCoinRpt.getCardInc());
            prodCoinRpt.setCoinInc(prodCoinRpt.getCoinInc() == null ? BigDecimal.ZERO : prodCoinRpt.getCoinInc());
        }else {
            prodCoinRpt = new ProdCoinRpt();
        }
        return prodCoinRpt;
    }

    /**
     * 统计周收入,按支付方式输出值
     *
     * @param prodNo 产品编号
     * @return 收入
     */
    @Override
    public ProdCoinRpt loadStaWeekIncWithPerPayWayByProdNo(String prodNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("prodNo", prodNo);
        params.put("mondayOfThisWeek", DateUtil.getMondayOfThisWeek());
        ProdCoinRpt prodCoinRpt = prodCoinRptDAO.queryUnique("loadStaWeekIncWithPerPayWayByProdNo", params);
        if (prodCoinRpt != null) {
            prodCoinRpt.setCardInc(prodCoinRpt.getCardInc() == null ? BigDecimal.ZERO : prodCoinRpt.getCardInc());
            prodCoinRpt.setCoinInc(prodCoinRpt.getCoinInc() == null ? BigDecimal.ZERO : prodCoinRpt.getCoinInc());
        }else {
            prodCoinRpt = new ProdCoinRpt();
        }
        return prodCoinRpt;
    }

    /**
     * 统计刷卡周收入,按支付方式输出值
     *
     * @param type
     * @param bizNo 商家编号
     * @return 收入
     */
    @Override
    public ProdCoinRpt loadStaWeekTotalIncWithPerPayWay(String type, String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        params.put("type", type);
        params.put("mondayOfThisWeek", DateUtil.getMondayOfThisWeek());
        ProdCoinRpt prodCoinRpt = prodCoinRptDAO.queryUnique("loadStaWeekTotalIncWithPerPayWay", params);
        if (prodCoinRpt != null) {
            prodCoinRpt.setCardInc(prodCoinRpt.getCardInc() == null ? BigDecimal.ZERO : prodCoinRpt.getCardInc());
            prodCoinRpt.setCoinInc(prodCoinRpt.getCoinInc() == null ? BigDecimal.ZERO : prodCoinRpt.getCoinInc());
        }else {
            prodCoinRpt = new ProdCoinRpt();
        }
        return prodCoinRpt;
    }

    /**
     * 统计刷卡月收入,按支付方式输出值
     *
     * @param prodNo 产品编号
     * @return 收入
     */
    @Override
    public ProdCoinRpt loadStaMonthIncWithPerPayWayByProdNo(String prodNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("prodNo", prodNo);
        ProdCoinRpt prodCoinRpt = prodCoinRptDAO.queryUnique("loadStaMonthIncWithPerPayWayByProdNo", params);
        if (prodCoinRpt != null) {
            prodCoinRpt.setCardInc(prodCoinRpt.getCardInc() == null ? BigDecimal.ZERO : prodCoinRpt.getCardInc());
            prodCoinRpt.setCoinInc(prodCoinRpt.getCoinInc() == null ? BigDecimal.ZERO : prodCoinRpt.getCoinInc());
        }else {
            prodCoinRpt = new ProdCoinRpt();
        }
        return prodCoinRpt;
    }

    /**
     * 统计刷卡月收入
     *
     * @param type  产品类型
     * @param bizNo 商家编号
     * @return 收入
     */
    @Override
    public ProdCoinRpt loadStaMonthTotalIncWithPerPayWay(String type, String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("type", type);
        params.put("bizNo", bizNo);
        ProdCoinRpt prodCoinRpt = prodCoinRptDAO.queryUnique("loadStaMonthTotalIncWithPerPayWay", params);
        if (prodCoinRpt != null) {
            prodCoinRpt.setCardInc(prodCoinRpt.getCardInc() == null ? BigDecimal.ZERO : prodCoinRpt.getCardInc());
            prodCoinRpt.setCoinInc(prodCoinRpt.getCoinInc() == null ? BigDecimal.ZERO : prodCoinRpt.getCoinInc());
        }else {
            prodCoinRpt = new ProdCoinRpt();
        }
        return prodCoinRpt;
    }

    /**
     * 统计时间段内刷卡收入
     *
     * @param prodNo    模块编号
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 收入
     */
    @Override
    public BigDecimal queryStaPeriodCardIncByProdNo(String prodNo, String beginDate, String endDate) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("prodNo", prodNo);
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        ProdCoinRpt prodCoinRpt = prodCoinRptDAO.queryUnique("queryStaPeriodCardIncByProdNo", params);
        if (prodCoinRpt != null && prodCoinRpt.getMoney() != null) {
            return prodCoinRpt.getMoney();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计投币日收入
     *
     * @param bizNo 商家编号
     * @return 收入
     */
    @Override
    public BigDecimal queryStaDayCoinInc(String type, String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("type", type);
        params.put("bizNo", bizNo);
        ProdCoinRpt prodCoinRpt = prodCoinRptDAO.queryUnique("queryStaDayCoinInc", params);
        if (prodCoinRpt != null && prodCoinRpt.getMoney() != null) {
            return prodCoinRpt.getMoney();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计投币日收入
     *
     * @param prodNo 模块编号
     * @return 收入
     */
    @Override
    public BigDecimal queryStaDayCoinIncByProdNo(String prodNo) {
        ProdCoinRpt prodCoinRpt = prodCoinRptDAO.queryUnique("queryStaDayCoinIncByProdNo", prodNo);
        if (prodCoinRpt != null && prodCoinRpt.getMoney() != null) {
            return prodCoinRpt.getMoney();
        }
        return BigDecimal.ZERO;
    }


    /**
     * 统计投币周收入
     *
     * @param bizNo 商家编号
     * @return 收入
     */
    @Override
    public BigDecimal queryStaWeekCoinInc(String type, String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("type", type);
        params.put("bizNo", bizNo);
        params.put("mondayOfThisWeek", DateUtil.getMondayOfThisWeek());
        ProdCoinRpt prodCoinRpt = prodCoinRptDAO.queryUnique("queryStaWeekCoinInc", params);
        if (prodCoinRpt != null && prodCoinRpt.getMoney() != null) {
            return prodCoinRpt.getMoney();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计投币周收入
     *
     * @param prodNo 模块编号
     * @return 收入
     */
    @Override
    public BigDecimal queryStaWeekCoinIncByProdNo(String prodNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("prodNo", prodNo);
        params.put("mondayOfThisWeek", DateUtil.getMondayOfThisWeek());
        ProdCoinRpt prodCoinRpt = prodCoinRptDAO.queryUnique("queryStaWeekCoinIncByProdNo", params);
        if (prodCoinRpt != null && prodCoinRpt.getMoney() != null) {
            return prodCoinRpt.getMoney();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计投币月收入
     *
     * @param bizNo 商家编号
     * @return 收入
     */
    @Override
    public BigDecimal queryStaMonthCoinInc(String type, String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("type", type);
        params.put("bizNo", bizNo);
        ProdCoinRpt prodCoinRpt = prodCoinRptDAO.queryUnique("queryStaMonthCoinInc", params);
        if (prodCoinRpt != null && prodCoinRpt.getMoney() != null) {
            return prodCoinRpt.getMoney();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计投币月收入
     *
     * @param prodNo 模块编号
     * @return 收入
     */
    @Override
    public BigDecimal queryStaMonthCoinIncByProdNo(String prodNo) {
        ProdCoinRpt prodCoinRpt = prodCoinRptDAO.queryUnique("queryStaMonthCoinIncByProdNo", prodNo);
        if (prodCoinRpt != null && prodCoinRpt.getMoney() != null) {
            return prodCoinRpt.getMoney();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计刷卡日收入
     *
     * @param bizNo 商家编号
     * @return 收入
     */
    @Override
    public BigDecimal queryStaDayCardInc(String type, String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("type", type);
        params.put("bizNo", bizNo);
        ProdCoinRpt prodCoinRpt = prodCoinRptDAO.queryUnique("queryStaDayCardInc", params);
        if (prodCoinRpt != null && prodCoinRpt.getMoney() != null) {
            return prodCoinRpt.getMoney();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计刷卡日收入
     *
     * @param prodNo 模块编号
     * @return 收入
     */
    @Override
    public BigDecimal queryStaDayCardIncByProdNo(String prodNo) {
        ProdCoinRpt prodCoinRpt = prodCoinRptDAO.queryUnique("queryStaDayCardIncByProdNo", prodNo);
        if (prodCoinRpt != null && prodCoinRpt.getMoney() != null) {
            return prodCoinRpt.getMoney();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计刷卡周收入
     *
     * @param bizNo 商家编号
     * @return 收入
     */
    @Override
    public BigDecimal queryStaWeekCardInc(String type, String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("type", type);
        params.put("bizNo", bizNo);
        params.put("mondayOfThisWeek", DateUtil.getMondayOfThisWeek());
        ProdCoinRpt prodCoinRpt = prodCoinRptDAO.queryUnique("queryStaWeekCardInc", params);
        if (prodCoinRpt != null && prodCoinRpt.getMoney() != null) {
            return prodCoinRpt.getMoney();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计刷卡周收入
     *
     * @param prodNo 模块编号
     * @return 收入
     */
    @Override
    public BigDecimal queryStaWeekCardIncByProdNo(String prodNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("prodNo", prodNo);
        params.put("mondayOfThisWeek", DateUtil.getMondayOfThisWeek());
        ProdCoinRpt prodCoinRpt = prodCoinRptDAO.queryUnique("queryStaWeekCardIncByProdNo", params);
        if (prodCoinRpt != null && prodCoinRpt.getMoney() != null) {
            return prodCoinRpt.getMoney();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计刷卡月收入
     *
     * @param bizNo 商家编号
     * @return 收入
     */
    @Override
    public BigDecimal queryStaMonthCardInc(String type, String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("type", type);
        params.put("bizNo", bizNo);
        ProdCoinRpt prodCoinRpt = prodCoinRptDAO.queryUnique("queryStaMonthCardInc", params);
        if (prodCoinRpt != null && prodCoinRpt.getMoney() != null) {
            return prodCoinRpt.getMoney();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计刷卡月收入
     *
     * @param prodNo 模块编号
     * @return 收入
     */
    @Override
    public BigDecimal queryStaMonthCardIncByProdNo(String prodNo) {
        ProdCoinRpt prodCoinRpt = prodCoinRptDAO.queryUnique("queryStaMonthCardIncByProdNo", prodNo);
        if (prodCoinRpt != null && prodCoinRpt.getMoney() != null) {
            return prodCoinRpt.getMoney();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计收入排行
     *
     * @param bizNo 商家编号
     * @return 收入排行
     */
    @Override
    public List<ProdCoinRpt> queryStaIncSort(String type, String bizNo, int rowStart, int rowSize) {
        return prodCoinRptDAO.query("queryStaIncSort", bizNo, rowStart, rowSize);
    }

    /**
     * 统计收入排行行数
     *
     * @param bizNo 商家编号
     * @return 收入排行
     */
    @Override
    public int countStaIncSort(String bizNo) {
        int count = prodCoinRptDAO.count("countStaIncSort", bizNo);
        return count;
    }

    /**
     * 更加商品编号统计收入
     *
     * @param prodNo 商品编号
     * @return 收入
     */
    @Override
    public BigDecimal queryStaIncByProdNo(String prodNo) {
        ProdCoinRpt prodCoinRpt = prodCoinRptDAO.queryUnique("queryStaIncByProdNo", prodNo);
        if (prodCoinRpt != null && prodCoinRpt.getMoney() != null) {
            return prodCoinRpt.getMoney();
        }
        return BigDecimal.ZERO;
    }
}
