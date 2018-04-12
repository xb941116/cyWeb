package com.zzwdkj.ord.service;

import com.zzwdkj.biz.dao.BizWltDAO;
import com.zzwdkj.biz.entity.BizWx;
import com.zzwdkj.biz.service.BizWxSV;
import com.zzwdkj.common.*;
import com.zzwdkj.common.weixin.*;
import com.zzwdkj.gprs.service.GprsOtpSV;
import com.zzwdkj.mbr.dao.MbrCoinChgedDAO;
import com.zzwdkj.mbr.dao.MbrWalletChgedDAO;
import com.zzwdkj.mbr.entity.MbrCoin;
import com.zzwdkj.mbr.entity.MbrCoinChged;
import com.zzwdkj.mbr.entity.MbrWallet;
import com.zzwdkj.mbr.entity.MbrWalletChged;
import com.zzwdkj.mbr.service.MbrCoinSV;
import com.zzwdkj.mbr.service.MbrSV;
import com.zzwdkj.mbr.service.MbrWalletSV;
import com.zzwdkj.ord.dao.*;
import com.zzwdkj.ord.entity.*;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.prod.service.ProdSV;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.security.*;
import java.util.*;

/**
 * ord, 订单_订单表SVImpl
 *
 * @author guoxianwei  2016-09-07 15:01:44
 */
@Service("ordSV")
public class OrdSVImpl implements OrdSV {
    private final static Logger LOGGER = Logger.getLogger(OrdSVImpl.class);
    @Resource
    private OrdDAO ordDAO;
    @Resource
    private MbrWalletChgedDAO mbrWalletChgedDAO;
    @Resource
    private OrdPayWxDAO ordPayWxDAO;
    @Resource
    private OrdPayWltDAO ordPayWltDAO;
    @Resource
    private OrdPayAliDAO ordPayAliDAO;
    @Resource
    private BizWltDAO bizWltDAO;
    @Resource
    private GprsOtpSV gprsOtpSV;
    @Resource
    private MbrSV mbrSV;
    @Resource
    private MbrWalletSV mbrWalletSV;
    @Resource
    private IdentifierSV identifierSV;
    @Resource
    private BizWxSV bizWxSV;
    @Resource
    private ProdSV prodSV;
    @Resource
    private MbrCoinSV mbrCoinSV;
    @Resource
    private MbrCoinChgedDAO mbrCoinChgedDAO;
    @Resource
    private OrdPayCoinDAO ordPayCoinDAO;


    /**
     * 查询订单_订单表 ，本月每天数据
     *
     * @param
     * @param  bizNo 商家编号
     * @return 结果集
     */
    public List<Ord> queryEveryQb(String bizNo){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        List<Ord> listOrd  = ordDAO.query("queryStaEveryInc",params);
        return listOrd;
    }
    public List<Ord> queryEveryWx(String bizNo){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        List<Ord> listWx  = ordDAO.query("queryStaEveryWx",params);
        return listWx;
    }

    public List<Ord> queryEveryCoin(String bizNo){
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("bizNo",bizNo);
        List<Ord> coinlist = ordDAO.query("queryStaEveryCoin",params);
        return  coinlist;
    }


    /**
     * 查询订单_订单表 ，带分页
     *
     * @param params   参数
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 菜单资源
     */
    @Override
    public List<Ord> queryOrd(Map<String, Object> params, int rowStart, int rowSize) {
        return ordDAO.query(params, rowStart, rowSize);
    }

    /**
     * 统计订单_订单表数量
     *
     * @param params 参数
     * @return 订单_订单表
     */
    @Override
    public int count(Map<String, Object> params) {
        return ordDAO.count(params);
    }


    /**
     * 新增订单_订单表
     *
     * @param ord
     */
    @Override
    public void create(Ord ord) {
        ord.setId(identifierSV.uniqueId());
        ord.setOrdno(identifierSV.payNo());
        ord.setState(0);
        ord.setCrtime(new Date());
        ord.setUptime(new Date());
        ordDAO.insert(ord);


    }

    /**
     * 修改订单_订单表
     *
     * @param ord
     */
    @Override
    public void update(Ord ord) {
        ord.setUptime(new Date());
        ordDAO.update(ord);
    }

    /**
     * 删除一条记录
     *
     * @param id
     */
    @Override
    public void remove(String id) {
        ordDAO.delete(id);
    }

    /**
     * 加载订单_订单表
     *
     * @param id 主键
     * @return 订单_订单表
     */
    @Override
    public Ord load(String id) {
        return ordDAO.load(id);
    }

    /**
     * 获取订单——订单表 通过订单号
     *
     * @param ordno 订单号
     * @return
     */
    @Override
    public Ord loadByOrdNo(String ordno) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ordno", ordno);
        List<Ord> ordList = ordDAO.query("loadByOrdNo", params);
        if (ordList != null && ordList.size() == 1) {
            return ordList.get(0);
        } else {
            return null;
        }
    }

    /**
     * 更改订单支付状态
     *
     * @param ordno 订单号
     */
    @Override
    public void updateOrdPaySt(String ordno, Std.PayWay payWay) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ordno", ordno);
        params.put("state", 1);
        ordDAO.update("updateOrdPaySt", params);

        Ord ord = this.loadByOrdNo(ordno);
        if (Std.PayWay.WX.getKey().equals(payWay.getKey())) {//微信
            ordPayWxDAO.update("updateOrdPaySt", params);
        }

        if (Std.PayWay.WLT.getKey().equals(payWay.getKey())) {//钱包
            ordPayWltDAO.update("updateOrdPaySt", params);
        }


        //发送支付成功提示
    }

    /**
     * 创建微信支付订单
     *
     * @param ord      订单
     * @param ordPayWx 微信支付
     */
    @Override
    public void createWxOrd(Ord ord, OrdPayWx ordPayWx) {
        ord.setId(identifierSV.uniqueId());
        ord.setOrdno(identifierSV.payNo());
        ord.setState(0);
        ord.setCrtime(new Date());
        ord.setUptime(new Date());
        ordDAO.insert(ord);
        ordPayWx.setId(identifierSV.uniqueId());
        ordPayWx.setPayno(ord.getOrdno());
        ordPayWx.setState(2);
        ordPayWxDAO.insert(ordPayWx);
    }

    /**
     * 统计日收入
     *
     * @param bizNo 商家编号
     * @return 日收入微信
     * 修改时间：18-03-15   修改人；苏方宁
     */
    @Override
    public BigDecimal queryStaDailyInc(String bizNo) {
        Ord ord = ordDAO.queryUnique("queryStaDailyInc", bizNo);
        if (ord != null && ord.getProdPrice() != null) {
            return ord.getProdPrice();
        }
        return BigDecimal.ZERO;
    }
    /**
     * 统计日收入
     *
     * @param bizNo 商家编号
     * @return 日收入钱包
     * 修改时间：18-03-15   修改人；苏方宁
     */
    @Override
    public BigDecimal queryStaDailyWltInc(String bizNo) {
        Ord ord = ordDAO.queryUnique("queryStaDailyWltInc", bizNo);
        if (ord != null && ord.getWallet() != null) {
            return ord.getWallet();
        }
        return BigDecimal.ZERO;
    }
    /**
     * 统计日收入
     *
     * @param bizNo 商家编号
     * @return 日收入积分
     * 修改时间：18-03-15   修改人；苏方宁
     */
    @Override
    public BigDecimal queryStaDailyCoinsInc(String bizNo) {
        Ord ord = ordDAO.queryUnique("queryStaDailyCoinsInc", bizNo);
        if (ord != null && ord.getCoins() != null) {
            return ord.getCoins();
        }
        return BigDecimal.ZERO;
    }
    /**
     * 统计日收入
     *
     * @param payWay
     * @param bizNo  商家编号
     * @return 日收入
     */
    @Override
    public BigDecimal queryStaDailyIncByPayWay(String type, Std.PayWay payWay, String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("type", type);
        params.put("bizNo", bizNo);
        params.put("payWay", payWay.getKey());
        Ord ord = ordDAO.queryUnique("queryStaDailyIncByPayWay", params);
        if (ord != null && ord.getProdPrice() != null) {
            return ord.getProdPrice();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计日收入
     *
     * @param prodNo 模块编号
     * @return 日收入
     */
    @Override
    public BigDecimal queryStaDailyIncByProdNo(String prodNo) {
        Ord ord = ordDAO.queryUnique("queryStaDailyIncByProdNo", prodNo);
        if (ord != null && ord.getProdPrice() != null) {
            return ord.getProdPrice();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计日收入
     *
     * @param payWay 支付方式
     * @param prodNo 产品编号
     * @return 日收入
     */
    @Override
    public BigDecimal queryStaDailyIncByProdNoAndPayWay(Std.PayWay payWay, String prodNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("prodNo", prodNo);
        params.put("payWay", payWay.getKey());
        Ord ord = ordDAO.queryUnique("queryStaDailyIncByProdNoAndPayWay", params);
        if (ord != null && ord.getProdPrice() != null) {
            return ord.getProdPrice();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计周收入
     *
     * @param bizNo 商家编号
     * @return 周收入微信
     * 修改时间：18-03-15   修改人；苏方宁
     */
    @Override
    public BigDecimal queryStaWeekInc(String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        params.put("mondayOfWeek", DateUtil.getMondayOfThisWeek());
        Ord ord = ordDAO.queryUnique("queryStaWeekInc", params);
        if (ord != null && ord.getProdPrice() != null) {
            return ord.getProdPrice();
        }
        return BigDecimal.ZERO;
    }
    /**
     * 统计周收入
     *
     * @param bizNo 商家编号
     * @return 周收入钱包
     * 修改时间：18-03-15   修改人；苏方宁
     */
    @Override
    public BigDecimal queryStaWeekWltInc(String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        params.put("mondayOfWeek", DateUtil.getMondayOfThisWeek());
        Ord ord = ordDAO.queryUnique("queryStaWeekWltInc", params);
        if (ord != null && ord.getWallet() != null) {
            return ord.getWallet();
        }
        return BigDecimal.ZERO;
    }
    /**
     * 统计周收入
     *
     * @param bizNo 商家编号
     * @return 周收入积分
     * 修改时间：18-03-15   修改人；苏方宁
     */
    @Override
    public BigDecimal queryStaWeekCoinsInc(String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        params.put("mondayOfWeek", DateUtil.getMondayOfThisWeek());
        Ord ord = ordDAO.queryUnique("queryStaWeekCoinsInc", params);
        if (ord != null && ord.getCoins() != null) {
            return ord.getCoins();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计周收入
     *
     * @param payWay 支付方式
     * @param bizNo  商家编号
     * @return 周收入
     */
    @Override
    public BigDecimal queryStaWeekIncByPayWay(String type, Std.PayWay payWay, String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("type", type);
        params.put("bizNo", bizNo);
        params.put("payWay", payWay.getKey());
        params.put("mondayOfWeek", DateUtil.getMondayOfThisWeek());
        Ord ord = ordDAO.queryUnique("queryStaWeekIncByPayWay", params);
        if (ord != null && ord.getProdPrice() != null) {
            return ord.getProdPrice();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计周收入
     *
     * @param prodNo 模块编号
     * @return 周收入
     */
    @Override
    public BigDecimal queryStaWeekIncByProdNo(String prodNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("prodNo", prodNo);
        params.put("mondayOfWeek", DateUtil.getMondayOfThisWeek());
        Ord ord = ordDAO.queryUnique("queryStaWeekIncByProdNo", params);
        if (ord != null && ord.getProdPrice() != null) {
            return ord.getProdPrice();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计月收入
     *
     * @param bizNo 商家编号
     * @return 月收入微信
     */
    @Override
    public BigDecimal queryStaMonthInc(String bizNo) {
        Ord ord = ordDAO.queryUnique("queryStaMonthInc", bizNo);
        if (ord != null && ord.getProdPrice() != null) {
            return ord.getProdPrice();
        }
        return BigDecimal.ZERO;
    }
    /**
     * 统计月收入
     *
     * @param bizNo 商家编号
     * @return 月收入钱包
     */
    @Override
    public BigDecimal queryStaMonthWltInc(String bizNo) {
        Ord ord = ordDAO.queryUnique("queryStaMonthWltInc", bizNo);
        if (ord != null && ord.getWallet() != null) {
            return ord.getWallet();
        }
        return BigDecimal.ZERO;
    }
    /**
     * 统计月收入
     *
     * @param bizNo 商家编号
     * @return 月收入积分
     */
    @Override
    public BigDecimal queryStaMonthCoinsInc(String bizNo) {
        Ord ord = ordDAO.queryUnique("queryStaMonthCoinsInc", bizNo);
        if (ord != null && ord.getCoins() != null) {
            return ord.getCoins();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计月收入
     *
     * @param payWay 支付方式
     * @param bizNo  商家编号
     * @return 月收入
     */
    @Override
    public BigDecimal queryStaMonthIncByPayWay(String type, Std.PayWay payWay, String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("payWay", payWay.getKey());
        params.put("bizNo", bizNo);
        params.put("type", type);
        Ord ord = ordDAO.queryUnique("queryStaMonthIncByPayWay", params);
        if (ord != null && ord.getProdPrice() != null) {
            return ord.getProdPrice();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计月收入
     *
     * @param prodNo 模块编号
     * @return 月收入
     */
    @Override
    public BigDecimal queryStaMonthIncByProdNo(String prodNo) {
        Ord ord = ordDAO.queryUnique("queryStaMonthIncByProdNo", prodNo);
        if (ord != null && ord.getProdPrice() != null) {
            return ord.getProdPrice();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计周收入
     *
     * @param payWay 支付方式
     * @param prodNo 模块编号
     * @return 周收入
     */
    @Override
    public BigDecimal queryStaWeekIncByProdNoAndPayWay(Std.PayWay payWay, String prodNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("prodNo", prodNo);
        params.put("payWay", payWay.getKey());
        params.put("mondayOfWeek", DateUtil.getMondayOfThisWeek());
        Ord ord = ordDAO.queryUnique("queryStaWeekIncByProdNoAndPayWay", params);
        if (ord != null && ord.getProdPrice() != null) {
            return ord.getProdPrice();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计日收入
     *
     * @param prodNo 产品编号
     * @return 日收入
     */
    @Override
    public Ord loadStaDailyIncWithPerPayWayByProdNo(String prodNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("prodNo", prodNo);
        Ord ord = ordDAO.queryUnique("loadStaDailyIncWithPerPayWayByProdNo", params);
        if (ord != null) {
            ord.setWxInc(ord.getWxInc() == null ? BigDecimal.ZERO : ord.getWxInc());
            ord.setWltInc(ord.getWltInc() == null ? BigDecimal.ZERO : ord.getWltInc());
            ord.setAliInc(ord.getAliInc() == null ? BigDecimal.ZERO : ord.getAliInc());
        }else {
            ord = new Ord();
        }
        return ord;
    }

    /**
     * 统计日总收入 ，按支付方式输出值
     *
     * @param type
     * @param bizNo 商家编号
     * @return 日收入
     */
    @Override
    public Ord loadStaDailyTotalIncWithPerPayWay(String type, String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        params.put("type", type);
        Ord ord = ordDAO.queryUnique("loadStaDailyTotalIncWithPerPayWay", params);
        if (ord != null) {
            ord.setWxInc(ord.getWxInc() == null ? BigDecimal.ZERO : ord.getWxInc());
            ord.setWltInc(ord.getWltInc() == null ? BigDecimal.ZERO : ord.getWltInc());
            ord.setAliInc(ord.getAliInc() == null ? BigDecimal.ZERO : ord.getAliInc());
        }else {
            ord = new Ord();
        }
        return ord;
    }

    /**
     * 统计日总收入 ，按支付方式输出值
     *
     * @param type
     * @param bizNo 商家编号
     * @return 日收入
     */
    @Override
    public Ord loadStaDailyTotalIncWithPerPayWayTransfers(String type, String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        params.put("type", type);
        Ord ord = ordDAO.queryUnique("loadStaDailyTotalIncWithPerPayWayTransfers", params);
        if (ord != null) {
            ord.setWxInc(ord.getWxInc() == null ? BigDecimal.ZERO : ord.getWxInc());
            ord.setWltInc(ord.getWltInc() == null ? BigDecimal.ZERO : ord.getWltInc());
            ord.setAliInc(ord.getAliInc() == null ? BigDecimal.ZERO : ord.getAliInc());
        }else {
            ord = new Ord();
        }
        return ord;
    }

    /**
     * 统计周收入 ，按支付方式输出值
     *
     * @param prodNo 产品编号
     * @return 周收入
     */
    @Override
    public Ord loadStaWeekIncWithPerPayWayByProdNo(String prodNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("prodNo", prodNo);
        params.put("mondayOfWeek", DateUtil.getMondayOfThisWeek());
        Ord ord = ordDAO.queryUnique("loadStaWeekIncWithPerPayWayByProdNo", params);
        if (ord != null) {
            ord.setWxInc(ord.getWxInc() == null ? BigDecimal.ZERO : ord.getWxInc());
            ord.setWltInc(ord.getWltInc() == null ? BigDecimal.ZERO : ord.getWltInc());
            ord.setAliInc(ord.getAliInc() == null ? BigDecimal.ZERO : ord.getAliInc());
        }else {
            ord = new Ord();
        }
        return ord;
    }

    /**
     * 统计月收入 ，按支付方式输出值
     *
     * @param prodNo 产品编号
     * @return 月收入
     */
    @Override
    public Ord loadStaMonthIncWithPerPayWayByProdNo(String prodNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("prodNo", prodNo);
        params.put("mondayOfWeek", DateUtil.getMondayOfThisWeek());
        Ord ord = ordDAO.queryUnique("loadStaMonthIncWithPerPayWayByProdNo", params);
        if (ord != null) {
            ord.setWxInc(ord.getWxInc() == null ? BigDecimal.ZERO : ord.getWxInc());
            ord.setWltInc(ord.getWltInc() == null ? BigDecimal.ZERO : ord.getWltInc());
            ord.setAliInc(ord.getAliInc() == null ? BigDecimal.ZERO : ord.getAliInc());
        }else {
            ord = new Ord();
        }
        return ord;
    }

    /**
     * 统计一段时间内收入 ，按支付方式输出值
     *
     * @param prodNo    产品编号
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 时间段内收入
     */
    @Override
    public Ord loadStaPeriodIncWithPerPayWayByProdNo(String prodNo, String beginDate, String endDate) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("prodNo", prodNo);
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        Ord ord = ordDAO.queryUnique("loadStaPeriodIncWithPerPayWayByProdNo", params);
        if (ord != null) {
            ord.setWxInc(ord.getWxInc() == null ? BigDecimal.ZERO : ord.getWxInc());
            ord.setWltInc(ord.getWltInc() == null ? BigDecimal.ZERO : ord.getWltInc());
            ord.setAliInc(ord.getAliInc() == null ? BigDecimal.ZERO : ord.getAliInc());
        }else {
            ord = new Ord();
        }
        return ord;
    }

    /**
     * 统计一段时间内总收入 ，按支付方式输出值
     *
     * @param type      设备类型
     * @param bizNo     商家编号
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 时间段内收入
     */
    @Override
    public Ord loadStaPeriodTotalIncWithPerPayWay(String type, String bizNo, String beginDate, String endDate) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        params.put("type", type);
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        Ord ord = ordDAO.queryUnique("loadStaPeriodTotalIncWithPerPayWay", params);
        if (ord != null) {
            ord.setWxInc(ord.getWxInc() == null ? BigDecimal.ZERO : ord.getWxInc());
            ord.setWltInc(ord.getWltInc() == null ? BigDecimal.ZERO : ord.getWltInc());
            ord.setAliInc(ord.getAliInc() == null ? BigDecimal.ZERO : ord.getAliInc());
        }else {
            ord = new Ord();
        }
        return ord;
    }

    /**
     * 统计网络收入 ，按支付方式输出值
     *
     * @param prodNo 产品编号
     * @return 收入
     */
    @Override
    public Ord loadStaNetIncPerPayWayByProdNo(String prodNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("prodNo", prodNo);
        Ord ord = ordDAO.queryUnique("loadStaNetIncPerPayWayByProdNo", params);
        if (ord != null) {
            ord.setWxInc(ord.getWxInc() == null ? BigDecimal.ZERO : ord.getWxInc());
            ord.setWltInc(ord.getWltInc() == null ? BigDecimal.ZERO : ord.getWltInc());
            ord.setAliInc(ord.getAliInc() == null ? BigDecimal.ZERO : ord.getAliInc());
        }else {
            ord = new Ord();
        }
        return ord;
    }

    /**
     * 统计总收入，按支付方式输出值
     *
     * @param type  设备类型
     * @param bizNo 商家编号
     * @return 总收入
     */
    @Override
    public Ord loadStaTotalIncWithPerPayWay(String type, String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        params.put("type", type);
        Ord ord = ordDAO.queryUnique("loadStaTotalIncWithPerPayWay", params);
        if (ord != null) {
            ord.setWxInc(ord.getWxInc() == null ? BigDecimal.ZERO : ord.getWxInc());
            ord.setWltInc(ord.getWltInc() == null ? BigDecimal.ZERO : ord.getWltInc());
            ord.setAliInc(ord.getAliInc() == null ? BigDecimal.ZERO : ord.getAliInc());
        }else {
            ord = new Ord();
        }
        return ord;
    }

    /**
     * 统计月收入，按支付方式输出值
     *
     * @param type  设备类型
     * @param bizNo 商家编号
     * @return 月收入
     */
    @Override
    public Ord loadStaMonthTotalIncWithPerPayWay(String type, String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        params.put("type", type);
        Ord ord = ordDAO.queryUnique("loadStaMonthTotalIncWithPerPayWay", params);
        if (ord != null) {
            ord.setWxInc(ord.getWxInc() == null ? BigDecimal.ZERO : ord.getWxInc());
            ord.setWltInc(ord.getWltInc() == null ? BigDecimal.ZERO : ord.getWltInc());
            ord.setAliInc(ord.getAliInc() == null ? BigDecimal.ZERO : ord.getAliInc());
        }else {
            ord = new Ord();
        }
        return ord;
    }

    /**
     * 统计周收入，按支付方式输出值
     *
     * @param type  设备类型
     * @param bizNo 商家编号
     * @return 周收入
     */
    @Override
    public Ord loadStaWeekTotalIncWithPerPayWay(String type, String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        params.put("type", type);
        params.put("mondayOfWeek", DateUtil.getMondayOfThisWeek());
        Ord ord = ordDAO.queryUnique("loadStaWeekTotalIncWithPerPayWay", params);
        if (ord != null) {
            ord.setWxInc(ord.getWxInc() == null ? BigDecimal.ZERO : ord.getWxInc());
            ord.setWltInc(ord.getWltInc() == null ? BigDecimal.ZERO : ord.getWltInc());
            ord.setAliInc(ord.getAliInc() == null ? BigDecimal.ZERO : ord.getAliInc());
        }else {
            ord = new Ord();
        }
        return ord;
    }

    /**
     * 统计月收入
     *
     * @param payWay 支付方式
     * @param prodNo 模块编号
     * @return 月收入
     */
    @Override
    public BigDecimal queryStaMonthIncByProdNoAndPayWay(Std.PayWay payWay, String prodNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("prodNo", prodNo);
        params.put("payWay", payWay.getKey());
        Ord ord = ordDAO.queryUnique("queryStaMonthIncByProdNoAndPayWay", params);
        if (ord != null && ord.getProdPrice() != null) {
            return ord.getProdPrice();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计网络收入
     *
     * @param bizNo 商家编号
     * @return 收入微信
     */
/*修改人苏方宁  时间18-03-13*/
    @Override
    public BigDecimal queryStaNetInc(String bizNo) {
        Ord ord = ordDAO.queryUnique("queryStaNetInc", bizNo);
        if (ord != null && ord.getProdPrice() != null) {
            return ord.getProdPrice();
        }
        return BigDecimal.ZERO;
    }
    /**
     * 统计网络收入
     *
     * @param bizNo 商家编号
     * @return 收入钱包
     */
/*修改人苏方宁  时间18-03-13*/
    @Override
    public BigDecimal queryStaWltInc(String bizNo) {
        Ord ord = ordDAO.queryUnique("queryStaWltInc", bizNo);
        if (ord != null && ord.getWallet() != null) {
            return ord.getWallet();
        }
        return BigDecimal.ZERO;
    }
    /**
     * 统计网络收入
     *
     * @param bizNo 商家编号
     * @return 收入积分
     */
/*修改人苏方宁  时间18-03-13*/
    @Override
    public BigDecimal queryStaCoinsInc(String bizNo) {
        Ord ord = ordDAO.queryUnique("queryStaCoinsInc", bizNo);
        if (ord != null && ord.getCoins() != null) {
            return ord.getCoins();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计网络收入
     *
     * @param prodNo 模块编号
     * @return 收入
     */
    @Override
    public BigDecimal queryStaNetIncByProdNo(String prodNo) {
        Ord ord = ordDAO.queryUnique("queryStaNetIncByProdNo", prodNo);
        if (ord != null && ord.getProdPrice() != null) {
            return ord.getProdPrice();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计网络收入
     *
     * @param payWay 支付方式
     * @param prodNo 模块编号
     * @return 收入
     */
    @Override
    public BigDecimal queryStaNetIncByProdNoAndPayWay(Std.PayWay payWay, String prodNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("payWay", payWay.getKey());
        params.put("prodNo", prodNo);
        Ord ord = ordDAO.queryUnique("queryStaNetIncByProdNoAndPayWay", params);
        if (ord != null && ord.getProdPrice() != null) {
            return ord.getProdPrice();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计一段时间内收入
     *
     * @param bizNo     商家编号
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 时间段内收入微信
     */
    @Override
    public BigDecimal queryStaPeriodInc(String bizNo, String beginDate, String endDate) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        Ord ord = ordDAO.queryUnique("queryStaPeriodInc", params);
        if (ord != null && ord.getProdPrice() != null) {
            return ord.getProdPrice();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计一段时间内收入
     *
     * @param bizNo     商家编号
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 时间段内收入钱包
     */
    @Override
    public BigDecimal queryStaPeriodWltInc(String bizNo, String beginDate, String endDate) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        Ord ord = ordDAO.queryUnique("queryStaPeriodWltInc", params);
        if (ord != null && ord.getWallet() != null) {
            return ord.getWallet();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计一段时间内收入
     *
     * @param bizNo     商家编号
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 时间段内收入积分
     */
    @Override
    public BigDecimal queryStaPeriodCoinsInc(String bizNo, String beginDate, String endDate) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        Ord ord = ordDAO.queryUnique("queryStaPeriodCoinsInc", params);
        if (ord != null && ord.getCoins() != null) {
            return ord.getCoins();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计一段时间内收入
     *
     * @param payWay    支付方式
     * @param bizNo     商家编号
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 时间段内收入
     */
    @Override
    public BigDecimal queryStaPeriodIncByPayWay(String type, Std.PayWay payWay, String bizNo, String beginDate, String endDate) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("type", type);
        params.put("payWay", payWay.getKey());
        params.put("bizNo", bizNo);
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        Ord ord = ordDAO.queryUnique("queryStaPeriodIncByPayWay", params);
        if (ord != null && ord.getProdPrice() != null) {
            return ord.getProdPrice();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计一段时间内收入
     *
     * @param payWay 支付方式
     * @param bizNo  商家编号
     * @return 时间段内收入
     */
    @Override
    public BigDecimal queryStaIncByPayWay(String type, Std.PayWay payWay, String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("type", type);
        params.put("payWay", payWay.getKey());
        params.put("bizNo", bizNo);
        Ord ord = ordDAO.queryUnique("queryStaIncByPayWay", params);
        if (ord != null && ord.getProdPrice() != null) {
            return ord.getProdPrice();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计一段时间内收入
     *
     * @param prodNo    模块编号
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 时间段内收入
     */
    @Override
    public BigDecimal queryStaPeriodIncByProdNo(String prodNo, String beginDate, String endDate) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("prodNo", prodNo);
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        Ord ord = ordDAO.queryUnique("queryStaPeriodIncByProdNo", params);
        if (ord != null && ord.getProdPrice() != null) {
            return ord.getProdPrice();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 统计一段时间内收入
     *
     * @param payWay    支付方式
     * @param prodNo    模块编号
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 时间段内收入
     */
    @Override
    public BigDecimal queryStaPeriodIncByProdNoAndPayWay(Std.PayWay payWay, String prodNo, String beginDate, String endDate) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("payWay", payWay.getKey());
        params.put("prodNo", prodNo);
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        Ord ord = ordDAO.queryUnique("queryStaPeriodIncByProdNoAndPayWay", params);
        if (ord != null && ord.getProdPrice() != null) {
            return ord.getProdPrice();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 加载订单支付
     *
     * @param ordno 订单号
     * @return 微信支付
     */
    @Override
    public OrdPayWx loadOrdPayWxByOrdNo(String ordno) {
        return ordPayWxDAO.queryUnique("loadOrdPayWxByOrdNo", ordno);
    }

    /**
     * 加载订单支付
     *
     * @param ordno
     * @return 支付宝支付
     */
    @Override
    public OrdPayAli loadOrdPayAliByOrdNo(String ordno) {
        return ordPayAliDAO.queryUnique("loadOrdPayAliByOrdNo", ordno);
    }
    /**
     * 加载订单支付
     *
     * @param ordno
     * @return 钱包支付
     */
    public OrdPayWlt loadOrdPayWltByOrdNo(String ordno){
        return ordPayWltDAO.queryUnique("loadOrdPayWltByOrdNo", ordno);
    }
    /**
     * 将所有待自动转账的订单状态改为结算中（1）
     *
     * @return
     */
    @Override
    public int updateAllTransfersOrd() {
        Map<String, Object> params = new HashMap<String, Object>();
        return ordDAO.update("updateAllTransfersOrd", params);
    }

    /**
     * 查询每个待转账商家的金额、openid。
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> queryTakeOrd() {
        Map<String, Object> params = new HashMap<String, Object>();
        return ordDAO.query("queryTakeOrd", params);
    }

    /**
     * 钱包支付退款
     */
    public void createRefundTaskWallet(List<Map<String, Object>> ordList_wallet,Integer i) {

        String memberId = String.valueOf(ordList_wallet.get(i).get("MEMBER_ID"));//会员编号
        String wallet = String.valueOf(ordList_wallet.get(i).get("WALLET"));//消费金额(修改时间：12.7)
        String coins = String.valueOf(ordList_wallet.get(i).get("COINS"));//消费的积分(修改时间：12.9)
        String out_trade_no = String.valueOf(ordList_wallet.get(i).get("ORDNO"));//订单号
        String bizNo = String.valueOf(ordList_wallet.get(i).get("BIZ_NO"));//商家编号
        String openid = String.valueOf(ordList_wallet.get(i).get("ORD_OPENID"));//openid

        LOGGER.info("-------------------开始钱包支付退款：：ORDNO:"+out_trade_no+",wallet"+wallet+",memberId"+memberId+",coins"+coins);//(修改人：sfn；修改时间：12.7)
        if(memberId.equals("null")||wallet.equals("null")||out_trade_no.equals("null")){//(修改时间：12.7)
            LOGGER.debug("---------------参数有为空的，不进行退款！");
            return;
        }
        BigDecimal total_fee = new BigDecimal(wallet);//订单总金额，单位为分 (修改时间：12.7)
        BigDecimal refund_fee = total_fee;//退款总金额，单位为分
        BigDecimal total_coin = new BigDecimal(coins);//订单总积分（修改人：sfn；修改时间：12.9）
        BigDecimal refund_coin = total_coin;//退还总积分
        //退款
        refund_coin(memberId,out_trade_no,total_coin,
                refund_coin,"支付失败退还");

        refund_wallet(memberId, out_trade_no, total_fee,
                refund_fee,"支付失败退款");
        BizWx bizWx_refund = bizWxSV.loadByBizNo(bizNo);
        //转账，所需参数
        if (bizWx_refund == null) {
            LOGGER.debug("---------------无法获取商家对应的微信信息！");
            return;
        }
        String appid = bizWx_refund.getAppId();//公众号APPID
        String secret = bizWx_refund.getAppSkey();//公众号secret
        String templates = bizWx_refund.getTemplateId();//模板ID
        LOGGER.info("----------------------------积分支付退还结束");
        LOGGER.info("----------------------------钱包支付退款结束");
    }

    /**
     * 积分支付退还                 (修改时间：12.7；修改人：sfn)

     public void createRefundTaskCoins(List<Map<String, Object>> ordList_coins,Integer i) {

     String memberId = String.valueOf(ordList_coins.get(i).get("MEMBER_ID"));//会员编号
     String coins = String.valueOf(ordList_coins.get(i).get("COINS"));//总积分(修改时间：12.7)
     String out_trade_no = String.valueOf(ordList_coins.get(i).get("ORDNO"));//订单号
     String bizNo = String.valueOf(ordList_coins.get(i).get("BIZ_NO"));//商家编号
     String openid = String.valueOf(ordList_coins.get(i).get("ORD_OPENID"));//openid

     LOGGER.info("-------------------开始积分支付退还：：ORDNO:"+out_trade_no+",prod_price"+coins+",memberId"+memberId);//(修改时间：12.7)
     if(memberId.equals("null")||coins.equals("null")||out_trade_no.equals("null")){//(修改时间：12.7)
     LOGGER.debug("---------------参数有为空的，不进行退还！");
     return;
     }
     Integer total_fee = (new BigDecimal(coins)).intValue();//订单总积分，单位为分 (修改时间：12.7)
     Integer refund_fee = total_fee;//退还总积分，单位为分
     //退还积分
     refund_wallet(memberId, out_trade_no, total_fee,
     refund_fee, "支付失败退还");
     BizWx bizWx_refund = bizWxSV.loadByBizNo(bizNo);
     //转账，所需参数
     if (bizWx_refund == null) {
     LOGGER.debug("---------------无法获取商家对应的微信信息！");
     return;
     }
     String appid = bizWx_refund.getAppId();//公众号APPID
     String secret = bizWx_refund.getAppSkey();//公众号secret
     String templates = bizWx_refund.getTemplateId();//模板ID
     LOGGER.info("----------------------------积分支付退还结束");
     }
     */


    /**
     * 微信支付退款
     */
    @Override
    public void createRefundTaskWx(List<Map<String, Object>> ordList_wx ,Integer i) {


        String bizNum = String.valueOf(ordList_wx.get(i).get("bizNum"));//订单的收款微信商户号
        String prod_price = String.valueOf(ordList_wx.get(i).get("PROD_PRICE"));//总金额
        String out_trade_no = String.valueOf(ordList_wx.get(i).get("ORDNO"));//订单号
        String openid = String.valueOf(ordList_wx.get(i).get("ORD_OPENID"));//openid
        String resvBizNo =  String.valueOf(ordList_wx.get(i).get("resvBizNo"));//订单的收款商家编号

        LOGGER.info("-------------------开始微信支付退款：ORDNO:"+out_trade_no+",prod_price"+prod_price+",bizNum"+bizNum+",resvBizNo"+resvBizNo);
        if(bizNum.equals("null")||prod_price.equals("null")||out_trade_no.equals("null")||resvBizNo.equals("null")){
            LOGGER.debug("---------------参数有为空的，不进行退款！");
            return;
        }

        String out_refund_no = identifierSV.payNo();//退款单号
        Integer total_fee = (new BigDecimal(prod_price).multiply(new BigDecimal(100))).intValue();//订单总金额，单位为分
        Integer refund_fee = total_fee;//退款总金额，单位为分
        String op_user_id = bizNum;//操作员帐号, 默认为商户号
        String refund_fee_type = "CNY"; //货币类型，默认人民币

        if (ordList_wx.get(i).get("BIZ_NO") == null) {
            return;
        }
        BizWx bizWx_refund = bizWxSV.loadByBizNumAndBizNo(bizNum, resvBizNo);
        //转账，所需参数
        if (bizWx_refund == null) {
            return;
        }
        String appid = bizWx_refund.getAppId();//公众号APPID
        String secret = bizWx_refund.getAppSkey();//公众号secret
        String mch_id = bizWx_refund.getBizNum();//公众号对应的商户号
        String key = bizWx_refund.getApiSkey();//支付密钥，商户平台获取
        String zipPath = bizWx_refund.getApiCert();//证书地址
        String templates = bizWx_refund.getTemplateId();//模板ID

        //退款到钱包
       /* if (ordList_wx.get(i).get("MEMBER_ID") != null && !(ordList_wx.get(i).get("MEMBER_ID").equals(""))) {

            String memberId = ordList_wx.get(i).get("MEMBER_ID").toString();//会员编号

            refund_wallet(memberId, out_trade_no, total_fee / 100,
                    refund_fee / 100, "支付失败退款");

            return;
        }*/


        //微信退款
        try {
            refund_wx(zipPath, appid, mch_id, out_trade_no, out_refund_no, total_fee,
                    refund_fee, refund_fee_type, op_user_id, key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LOGGER.info("----------------------微信支付退款结束：ORDNO:"+out_trade_no+",prod_price"+prod_price+",bizNum"+bizNum);
    }

    /**
     * 钱包退款
     *
     * @param memberId     会员id
     * @param out_trade_no 订单号
     * @param total_fee    订单总金额
     * @param refund_fee   退款总金额
     * @param reason       原因
     */
    private void refund_wallet(String memberId, String out_trade_no, BigDecimal total_fee, BigDecimal refund_fee, String reason) {

        mbrSV.createWalletRefund(memberId, refund_fee, reason);
        this.updateStateByOrdno(out_trade_no, Ord.St.BKMONEY.getKey());//更新订单为已退款


    }

    /**
     * 积分退还                  //修改；时间：12.7 sfn
     *
     * @param memberId     会员id
     * @param out_trade_no 订单号
     * @param total_coin    订单总积分
     * @param refund_coin  退还总积分
     * @param reason       原因
     */
    private void refund_coin(String memberId, String out_trade_no, BigDecimal total_coin, BigDecimal refund_coin, String reason) {

        mbrSV.createCoinRefund(memberId,refund_coin, reason);


    }



    /**
     * 更新自动提款成功
     *
     * @param bizNo
     */
    @Override
    public void updateTransfersOrdSucess(String bizNo) {
        Map<String, Object> params_ord = new HashMap<String, Object>();
        params_ord.put("bizNo", bizNo);
        ordDAO.update("updateTransfersOrdSucess", params_ord);
    }

    /**
     * 自动提现总金额
     *
     * @param bizNo 商家编号
     * @return
     */
    @Override
    public BigDecimal queryAutoMoney(String bizNo) {
        Map<String, Object> params_ord = new HashMap<String, Object>();
        params_ord.put("bizNo", bizNo);
        params_ord.put("transfersIs", 1);
        params_ord.put("transfersStateAuto", 1);
        params_ord.put("succ", 1);
        return new BigDecimal(ordDAO.count("queryAutoMoney", params_ord));
    }

    /**
     * 通过订单号更新
     *
     * @param ordno
     */
    @Override
    public void updateStateByOrdno(String ordno, Integer state) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ordno", ordno);
        params.put("state", state);
        ordDAO.update("updateOrdPaySt", params);
    }

    /**
     * 创建钱包支付订单
     *
     * @param ord
     * @param mbrWalletChged
     * @param mbrWallet
     * @param ordPayWlt
     */
    @Override
    public String createWalletOrd(Ord ord, OrdPayWlt ordPayWlt, MbrWallet mbrWallet, MbrWalletChged mbrWalletChged,OrdPayCoin ordPayCoin,MbrCoin mbrCoin,MbrCoinChged mbrCoinChged) {//修改；时间：12.8 sfn

        int rcont = mbrWalletSV.updateMoneySub(ord.getMemberId(), ordPayWlt.getMoney());
        System.out.println("------------------------------------------------------RCONT--------------------------------------------------------------"+ rcont);
        System.out.println("------------------------------------------------------RCONT--------------------------------------------------------------"+ ord.getWallet());

        int jcont = mbrCoinSV.updateCoinSub(ord.getMemberId(), ordPayCoin.getCoin());//修改；时间：12.8 sfn
        System.out.println("------------------------------------------------------RCONT--------------------------------------------------------------"+ jcont);
        System.out.println("------------------------------------------------------RCONT--------------------------------------------------------------"+ ord.getCoins());


        if (rcont > 0 && jcont>0) {
            ord.setId(identifierSV.uniqueId());
            ord.setOrdno(identifierSV.payNo());
            ord.setState(1);
            ord.setWallet(ordPayWlt.getMoney());
            ord.setCoins(ordPayCoin.getCoin());
            System.out.println("------------------------------------------------------RCONT--------------------------------------------------------------"+ ord.getWallet());
            System.out.println("------------------------------------------------------RCONT--------------------------------------------------------------"+ ord.getCoins());
            ord.setCrtime(new Date());
            ord.setUptime(new Date());
            ordDAO.insert(ord);

            ordPayWlt.setId(identifierSV.uniqueId());
            ordPayWlt.setPayno(ord.getOrdno());
            ordPayWlt.setState(1);
            ordPayWltDAO.insert(ordPayWlt);
            mbrWalletChged.setId(identifierSV.uniqueId());
            mbrWalletChgedDAO.insert(mbrWalletChged);

            ordPayCoin.setId(ordPayWlt.getId());//修改；时间：12.8 sfn
            ordPayCoin.setPayno(ord.getOrdno());    //修改；时间：12.8 sfn
            ordPayCoin.setState(1);    //修改；时间：12.8 sfn
            ordPayCoinDAO.insert(ordPayCoin);    //修改；时间：12.8 sfn
            mbrCoinChged.setId(identifierSV.uniqueId());//修改；时间：12.8 sfn
            mbrCoinChgedDAO.insert(mbrCoinChged);    //修改；时间：12.8 sfn
            return null;
        } else if (rcont>0){
            ord.setId(identifierSV.uniqueId());
            ord.setOrdno(identifierSV.payNo());
            ord.setWallet(ordPayWlt.getMoney());
            ord.setCoins(ordPayCoin.getCoin());
            ord.setState(1);
            ord.setCrtime(new Date());
            ord.setUptime(new Date());
            ordDAO.insert(ord);
            System.out.println("------------------------------------------------------RCONT1--------------------------------------------------------------"+ ord.getWallet());
            System.out.println("------------------------------------------------------RCONT2--------------------------------------------------------------"+ ord.getCoins());

            ordPayWlt.setId(identifierSV.uniqueId());
            ordPayWlt.setPayno(ord.getOrdno());
            ordPayWlt.setState(1);
            ordPayWltDAO.insert(ordPayWlt);
            mbrWalletChged.setId(identifierSV.uniqueId());
            mbrWalletChgedDAO.insert(mbrWalletChged);
            return null;
        }else{

            return "钱包余额不足";
        }
    }

    /**
     * 创建积分支付订单        //修改；时间：12.7 sfn
     *
     * @param ord
     * @param mbrCoinChged
     * @param mbrCoin
     * @param ordPayCoin

     @Override
     public String createCoinOrd(Ord ord, OrdPayCoin ordPayCoin, MbrCoin mbrCoin, MbrCoinChged mbrCoinChged) {

     int rcont = mbrCoinSV.updateCoinSub(ord.getMemberId(), ord.getCoins());//修改；时间：12.7 sfn
     if (rcont > 0) {
     ord.setId(identifierSV.uniqueId());
     ord.setOrdno(identifierSV.payNo());
     ord.setState(1);
     ord.setCrtime(new Date());
     ord.setUptime(new Date());


     ordDAO.insert(ord);
     ordPayCoin.setId(identifierSV.uniqueId());
     ordPayCoin.setPayno(ord.getOrdno());
     ordPayCoin.setState(1);
     ordPayCoinDAO.insert(ordPayCoin);
     mbrCoinChged.setId(identifierSV.uniqueId());
     mbrCoinChgedDAO.insert(mbrCoinChged);
     return null;
     } else {
     return "钱包余额不足";
     }

     }
     */


    /**
     * 查询订单，关联OrdPayWx
     *
     * @param params
     * @param rowStart
     * @param pageSize
     * @return
     */
    @Override
    public List<Ord> queryOrdJoinOrdPayWx(Map<String, Object> params, int rowStart, int pageSize) {
        return ordDAO.query("queryOrdJoinOrdPayWx", params, rowStart, pageSize);
    }

    /**
     * 统计个数，关联OrdPayWx
     *
     * @param params
     * @return
     */
    @Override
    public int countOrdJoinOrdPayWx(Map<String, Object> params) {
        return ordDAO.count("countOrdJoinOrdPayWx", params);
    }

    /**
     * 统计商品收入
     *
     * @param prodNo 商品编号
     * @return 收入
     */
    @Override
    public BigDecimal queryStaIncByProdNo(String prodNo) {
        Ord ord = ordDAO.queryUnique("queryStaIncByProdNo", prodNo);
        if (ord != null && ord.getProdPrice() != null) {
            return ord.getProdPrice();
        }
        return BigDecimal.ZERO;
    }


    /**
     * 通过GPRS编号统计营收
     * @param gprsNo GPRS编号
     * @param bizNo 商家编号
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    @Override
    public Ord loadStaPeriodIncWithPerPayWayByGprsNo(String gprsNo, String bizNo, String beginDate, String endDate) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("gprsNo", gprsNo);
        params.put("bizNo", bizNo);
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        Ord ord = ordDAO.queryUnique("loadStaPeriodIncWithPerPayWayByGprsNo", params);
        if (ord != null) {
            ord.setWxInc(ord.getWxInc() == null ? BigDecimal.ZERO : ord.getWxInc());
            ord.setWltInc(ord.getWltInc() == null ? BigDecimal.ZERO : ord.getWltInc());
            ord.setAliInc(ord.getAliInc() == null ? BigDecimal.ZERO : ord.getAliInc());
        }else {
            ord = new Ord();
        }
        return ord;
    }

    /**
     * 查询退款订单
     *
     * @param params_ord_wallet
     * @return
     */
    @Override
    public List<Map<String, Object>> queryRefundOrd(Map<String, Object> params_ord_wallet) {
        return ordDAO.query("queryFailOrd", params_ord_wallet);
    }
    /**
     * 查询积分退还订单
     *
     * @param params_ord_coin（这个地方需要修改）
     * @return
     */

    /**
     * 查询商家当天的订单
     *
     * @param bizNo
     * @return
     */
    @Override
    public List<Map<String, Object>> quryTodayMapOrd(String bizNo) {
        return ordDAO.query("quryTodayMapOrd", bizNo);
    }

    /**
     * 通过订单号获取订单信息
     *
     * @param ordNo
     * @param bizNo
     * @return
     */
    @Override
    public Map<String, Object> loadMapOrdByOrdNo(String ordNo,String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ordNo",ordNo);
        params.put("bizNo", bizNo);
        return ordDAO.queryUnique("loadMapOrdByOrdNo", params);
    }

    /**
     * 获取用户的全部订单
     *
     * @param params
     * @param rowStart
     * @param pageSize
     * @return
     */
    @Override
    public List<Ord> queryMbrAllOrd(Map<String, Object> params, int rowStart, int pageSize) {
        return ordDAO.query("queryMbrAllOrd",params,rowStart,pageSize);
    }

    /**
     * 获取用户的全部订单数量
     *
     * @param params
     * @return
     */
    @Override
    public int countMbrAllOrd(Map<String, Object> params) {
        return ordDAO.count("countMbrAllOrd",params);
    }

    /**
     * * 申请退款。
     * 申请退款需要证书文件，请到商户平台中去下载，放到项目的某目录下。我这里是放在了src目录下。
     * <p/>
     * 作者: 席庄捶
     * 日期：2016年11月20日 下午1:26:57
     *
     * @param appid           公众号APPID
     * @param mch_id          公众号对应的商户号
     * @param out_refund_no   订单号
     * @param total_fee       订单总金额，单位为分
     * @param refund_fee      退款总金额，单位为分
     * @param refund_fee_type 货币类型，默认人民币
     * @param op_user_id      操作员帐号, 默认为商户号
     * @param key             支付密钥，商户平台获取
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void refund_wx(String zipPath, String appid, String mch_id, String out_trade_no, String out_refund_no, Integer total_fee,
                          Integer refund_fee, String refund_fee_type, String op_user_id, String key) throws Exception {
        /**
         * 以下代码使用微信提供的代码
         */

        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        zipPath = WeiXinUtil.getRootPath() + zipPath;
        LOGGER.info("退款-----------------证书path:" + zipPath);
        InputStream instream = WeiXinUtil.readZipContext("apiclient_cert.p12", zipPath);
        //InputStream instream =  RefundCtrl.class.getResourceAsStream("/apiclient_cert.p12");//指定证书文件
        try {
            keyStore.load(instream, mch_id.toCharArray());//密码默认是商户号
        } finally {
            instream.close();
        }

        // 信任自己的CA和自签名证书，密码默认为商户号
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, mch_id.toCharArray()).build();
        // 只允许TLSv1协议
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        try {
            //指定申请退款接口
            HttpPost httppost = new HttpPost("https://api.mch.weixin.qq.com/secapi/pay/refund");
            LOGGER.info("执行请求操作：" + httppost.getRequestLine());

            //设置请求报文
            StringEntity myEntity = new StringEntity(getXmlInfo(appid, mch_id, out_trade_no, out_refund_no, total_fee,
                    refund_fee, refund_fee_type, op_user_id, key), "UTF-8");
            httppost.setEntity(myEntity);

            //请求退款接口
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();

                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());

                StringBuffer result = new StringBuffer();
                if (entity != null) {
                    //System.out.println("请求退款接口，返回的内容的长度: " + entity.getContentLength());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                    String text;
                    while ((text = bufferedReader.readLine()) != null) {
                        System.out.println(text);
                        result.append(text);
                    }
                }
                EntityUtils.consume(entity);

                LOGGER.info("申请退款结果：\n" + result);

                Map<String, String> map = XMLUtil.doXMLParse(result.toString());
                if (map.get("return_code").equals("SUCCESS")) {
                    LOGGER.info("申请退款成功！");
                    //这里可以进行其他的业务逻辑...
                    this.updateStateByOrdno(out_trade_no, Ord.St.BKMONEY.getKey());//更新订单为已退款

                } else {
                    LOGGER.debug("申请退款失败！");
                    //这里可以进行其他的业务逻辑...

                }
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

    /**
     * 生成xml格式退款请求报文 退款
     * <p/>
     * 作者: 席庄捶
     * 日期：2016年11月21日 下午1:10:42
     *
     * @return
     */
    private static String getXmlInfo(String appid, String mch_id, String out_trade_no, String out_refund_no, Integer total_fee,
                                     Integer refund_fee, String refund_fee_type, String op_user_id, String key) {
        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        parameters.put("appid", appid); //appid
        parameters.put("mch_id", mch_id); //微信商户号
        parameters.put("nonce_str", WeiXinUtil.getNonceStr()); //随机字符串

        /**
         * 这里有2种选择，二选一即可：
         * 1、transaction_id：微信订单号
         * 2、out_trade_no：商户订单号
         */
        //parameters.put("transaction_id", transaction_id); //这里使用微信订单号，优先使用它
        parameters.put("out_trade_no", out_trade_no); //商户侧传给微信的订单号
        parameters.put("out_refund_no", out_refund_no); //商户系统内部的退款单号
        parameters.put("total_fee", total_fee); //总金额
        parameters.put("refund_fee", refund_fee); //退款金额
        parameters.put("refund_fee_type", refund_fee_type); //货比种类
        parameters.put("op_user_id", op_user_id); //操作员

        //创建签名，算法与支付的算法一样
        String sign = createSign("UTF-8", parameters, key);
        parameters.put("sign", sign);

        String data = getRequestXml(parameters);
        return data;
    }

    /**
     * 生成退款申请的请求报文 退款
     * <p/>
     * 作者: 席庄捶
     * 日期：2016年11月21日 下午1:57:11
     *
     * @param parameters
     * @return
     */
    public static String getRequestXml(SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set<Map.Entry<Object, Object>> es = parameters.entrySet();
        Iterator<Map.Entry<Object, Object>> it = es.iterator();
        while (it.hasNext()) {
            Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) it.next();
            String k = (String) entry.getKey();
            String v = entry.getValue() + "";
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
            } else {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 生成签名 退款
     * <p/>
     * 作者: 席庄捶
     * 日期：2016年11月21日 下午1:57:05
     *
     * @param characterEncoding
     * @param parameters
     * @return
     */
    public static String createSign(String characterEncoding, SortedMap<Object, Object> parameters, String key) {
        StringBuffer sb = new StringBuffer();
        Set<Map.Entry<Object, Object>> es = parameters.entrySet();
        Iterator<Map.Entry<Object, Object>> it = es.iterator();
        while (it.hasNext()) {
            Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            /** 如果参数为key或者sign，则不参与加密签名 */
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        /** 支付密钥必须参与加密，放在字符串最后面 */
        sb.append("key=" + key);
        /** 记得最后一定要转换为大写 */
        String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
        return sign;
    }
}
