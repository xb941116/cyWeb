package com.zzwdkj.prod.service;

import com.zzwdkj.biz.dao.BizGprsBindDAO;
import com.zzwdkj.prod.dao.*;
import com.zzwdkj.prod.entity.*;
import com.zzwdkj.base.service.IdentifierSV;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * prod, 产品表SVImpl
 *
 * @author guoxianwei  2016-09-07 15:01:56
 */
@Service("prodSV")
public class ProdSVImpl implements ProdSV {

    @Resource
    private ProdDAO prodDAO;
    @Resource
    private ProdSpArgsDAO prodSpArgsDAO;
    @Resource
    private ProdInstlPosDAO prodInstlPosDAO;
    @Resource
    private ProdBugRptDAO prodBugRptDAO;
    @Resource
    private BizGprsBindDAO bizGprsBindDAO;
    @Resource
    private ProdOnlLogDAO prodOnlLogDAO;
    @Resource
    private ProdCoinRptLogDAO prodCoinRptLogDAO;
    @Resource
    private ProdGprsBindHisDAO prodGprsBindHisDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
     * 查询产品表 ，带分页
     *
     * @param state    状态
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 菜单资源
     */
    @Override
    public List<Prod> queryProd(String state, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("state", state);
        return prodDAO.query(params, rowStart, rowSize);
    }

    /**
     * 统计产品表数量
     *
     * @param state 状态
     * @return 产品表
     */
    @Override
    public int count(String state) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("state", state);
        return prodDAO.count(params);
    }

    /**
     * 查询产品表 ，带分页
     *
     * @param params   参数
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 结果集
     */
    @Override
    public List<Prod> queryProd(Map params, int rowStart, int rowSize) {
        if(rowStart==-1||rowSize==-1){

            return prodDAO.query("queryProd", params);
        }else {

            return prodDAO.query("queryProd", params, rowStart, rowSize);
        }
    }

    /**
     * 查询离线产品表 ，带分页
     *
     * @param params   参数
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 结果集
     */
    @Override
    public List<ProdOnlLog> queryProdOffLine(Map params, int rowStart, int rowSize) {
        return prodOnlLogDAO.query("queryProdOffLine", params, rowStart, rowSize);
    }

    /**
     * 查询在线产品表 ，带分页
     *
     * @param params   参数
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 结果集
     */
    @Override
    public List<ProdOnlLog> queryProdOnLine(Map<String, Object> params, int rowStart, int rowSize) {
        return prodOnlLogDAO.query("queryProdOnLine", params, rowStart, rowSize);
    }

    /**
     * 统计在线产品表数量
     *
     * @param params 参数
     * @return 产品表数量
     */
    @Override
    public int countProdOnLine(Map params) {
        return prodOnlLogDAO.count("countProdOnLine", params);
    }

    /**
     * 统计离线产品表数量
     *
     * @param params 参数
     * @return 产品表数量
     */
    @Override
    public int countProdOffLine(Map params) {
        return prodOnlLogDAO.count("countProdOffLine", params);
    }

    /**
     * 统计产品表数量
     *
     * @param params 参数
     * @return 产品表数量
     */
    @Override
    public int count(Map params) {
        return prodDAO.count(params);
    }

    /**
     * 统计产品表数量
     *
     * @param params 参数
     * @return 产品表数量
     */
    @Override
    public int countProd(Map params) {
        return prodDAO.count("countProd", params);
    }

    /**
     * 新增产品表
     *
     * @param prod
     */
    @Override
    public void create(Prod prod) {
        prod.setId(identifierSV.uniqueId());
        prod.setCrtime(new Date());
        prod.setUptime(new Date());
        prodDAO.insert(prod);
    }

    /**
     * 修改产品表
     *
     * @param prod
     */
    @Override
    public void update(Prod prod) {
        prod.setUptime(new Date());
        Prod prod_old =prodDAO.load(prod.getId());
        String prodNo=prod_old.getProdNo();
        if (prod_old.getType()!=prod.getType()){//设置模板时候更新设备类型时候重新生成产品编号
            //移除
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("bizNo", prod_old.getBizNo());
            params.put("gprsNo", prod_old.getGprsNo());
            prodDAO.update("updateProdBizUnBind", params);
            //生成新的
            prod_old.setId(identifierSV.uniqueId());
            prod_old.setProdNo(identifierSV.prodNo());
            prod_old.setCrtime(new Date());
            prodDAO.insert(prod_old);
            prod.setId(prod_old.getId());
            ProdOnlLog prodOnlLog=prodOnlLogDAO.queryUnique("loadByProdNo",prodNo);
            if (prodOnlLog!=null&&prodOnlLog.getId()!=null){
                prodOnlLog.setProdNo(prod_old.getProdNo());
                prodOnlLogDAO.update(prodOnlLog);
            }
        }
        prodDAO.update(prod);
    }

    /**
     * 删除一条记录
     *
     * @param id
     */
    @Override
    public void remove(String id) {
        prodDAO.delete(id);
    }

    /**
     * 加载产品表
     *
     * @param id 主键
     * @return 产品表
     */
    @Override
    public Prod load(String id) {
        return prodDAO.load(id);
    }

    /**
     * 加载产品表
     *
     * @param gprsNo 模块号
     * @return 产品表
     */
    @Override
    public Prod loadByGprsNo(String gprsNo) {
        return prodDAO.queryUnique("loadByGprsNo", gprsNo);
    }

    /**
     * 统计产品表数量
     *
     * @param gprsNo 模块号
     * @return 产品表数量
     */
    @Override
    public int countByGprsNo(String gprsNo) {
        if (StringUtils.isNotEmpty(gprsNo)) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("gprsNo", gprsNo);
            params.put("state", 1);
            return prodDAO.count(params);
        }
        return 0;
    }

    /**
     * 创建产品
     *
     * @param gprsNo    模块号
     * @param prodModel 商品模板
     */
    @Override
    public void createProd(String gprsNo, ProdModel prodModel) {
        Prod prod = new Prod();
        prod.setBizName(prodModel.getBizName());
        prod.setBizNo(prodModel.getBizNo());
        prod.setId(identifierSV.uniqueId());
        prod.setProdNo(identifierSV.prodNo());
        prod.setState(1);
        prod.setOtherMoneyState(prodModel.getOtherMoneyState());
        prod.setOtherMoneyOption(prodModel.getOtherMoneyOption());
        prod.setPerCt(prodModel.getPerCt());
        prod.setFirstFree(prodModel.getFirstFree());
        prod.setGiftCt(prodModel.getGiftCt());
        prod.setGprsNo(gprsNo);
        prod.setLogo(prodModel.getLogo());
        prod.setOnlinePay(1);
        prod.setPrice(prodModel.getPrice());
        prod.setProdCv(prodModel.getProdCv());
        prod.setPerCt(prodModel.getPerCt());
        prod.setType(prodModel.getType());
        prod.setProdName(prodModel.getProdMdlName());
        prod.setSpoId(prodModel.getSpoId());
        prod.setCrtime(new Date());
        prod.setUptime(new Date());
        prod.setRunTime(prodModel.getRunTime());
        prodDAO.insert(prod);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("gprsNo", gprsNo);
        params.put("bizNo", prod.getBizNo());
        params.put("prodSet", 1);
        bizGprsBindDAO.update("updateBizGprsBindProdSet", params);
        ProdGprsBindHis prodGprsBindHis = new ProdGprsBindHis(prod.getBizNo(), prod.getProdNo(), prod.getGprsNo());
        prodGprsBindHis.setId(prod.getId());
        prodGprsBindHisDAO.insert(prodGprsBindHis);

        //2开头的模块创建在线信息
        if (prod.getGprsNo().startsWith("2")){
            ProdOnlLog prodOnlLog = new ProdOnlLog(prod.getBizNo(), prod.getProdNo(), prod.getGprsNo());
            prodOnlLog.setState(1);
            prodOnlLog.setCurOnlineTime(new Date());
            prodOnlLog.setUptime(new Date());
            prodOnlLog.setId(identifierSV.uniqueId());
            prodOnlLogDAO.insert(prodOnlLog);
        }

    }

    /**
     * 修改产品属性
     *
     * @param gprsNo    模块号
     * @param prodModel 商品模板
     */
    @Override
    public void updateProd(String gprsNo, ProdModel prodModel) {
        Prod prod = new Prod();
        prod.setBizName(prodModel.getBizName());
        prod.setBizNo(prodModel.getBizNo());
        prod.setState(1);
        prod.setOtherMoneyState(prodModel.getOtherMoneyState());
        prod.setOtherMoneyOption(prodModel.getOtherMoneyOption());
        prod.setPerCt(prodModel.getPerCt());
        prod.setFirstFree(prodModel.getFirstFree());
        prod.setGiftCt(prodModel.getGiftCt());
        prod.setGprsNo(gprsNo);
        prod.setLogo(prodModel.getLogo());
        prod.setOnlinePay(1);
        prod.setPrice(prodModel.getPrice());
        prod.setProdCv(prodModel.getProdCv());
        prod.setPerCt(prodModel.getPerCt());
        prod.setType(prodModel.getType());
        prod.setProdName(prodModel.getProdMdlName());
        prod.setSpoId(prodModel.getSpoId());
        prod.setUptime(new Date());
        prod.setRunTime(prodModel.getRunTime());

        Prod prod_old=prodDAO.queryUnique("loadByGprsNo", gprsNo);
        String prodNo=prod_old.getProdNo();
        if (prod_old.getType()!=prodModel.getType()){//设置模板时候更新设备类型时候重新生成产品编号
            //移除
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("bizNo", prod_old.getBizNo());
            params.put("gprsNo", gprsNo);
            prodDAO.update("updateProdBizUnBind", params);
            //生成新的
            prod.setId(identifierSV.uniqueId());
            prod.setProdNo(identifierSV.prodNo());
            prod.setCrtime(new Date());
            prodDAO.insert(prod);
            ProdOnlLog prodOnlLog=prodOnlLogDAO.queryUnique("loadByProdNo",prodNo);
            if (prodOnlLog!=null&&prodOnlLog.getId()!=null){
                prodOnlLog.setProdNo(prod.getProdNo());
                prodOnlLogDAO.update(prodOnlLog);
            }
        }else {
            prodDAO.update("updateProdByGprsNo",prod);
        }
    }

    /**
     * 更改产品是否生成二维码标示
     *
     * @param bizNo  商家编号
     * @param gprsNo 模块号
     * @param gened  是否标示（0否；1是）
     */
    @Override
    public void updateProdQrSt(String bizNo, String gprsNo, Integer gened) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        params.put("gprsNo", gprsNo);
        params.put("qrGened", gened);
        prodDAO.update("updateProdQrSt", params);
    }

    /**
     * 统计设备在线数量
     *
     * @param bizNo 商家编号
     * @return 数量
     */
    @Override
    public int statProdOnLineCount(String bizNo) {
        return prodOnlLogDAO.count("statProdOnLineCount", bizNo);
    }

    /**
     * 统计设备离线数量
     *
     * @param bizNo 商家编号
     * @return 数量
     */
    @Override
    public int statProdOffLineCount(String bizNo) {
        return prodOnlLogDAO.count("statProdOffLineCount", bizNo);
    }

    /**
     * 查询在线产品
     *
     * @param params   参数
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 结果集
     */
    @Override
    public List<Prod> queryProdOnline(Map<String, Object> params, int rowStart, int rowSize) {
        return prodDAO.query("queryProdOnline", params, rowStart, rowSize);
    }

    /**
     * 查询在线产品
     *
     * @param bizNo 商家编号
     * @return 结果集
     */
    @Override
    public List<Prod> queryProdOnlineByBizNo(String bizNo) {
        return prodDAO.query("queryProdOnlineByBizNo", bizNo);
    }

    /**
     * 统计设备在线数量
     *
     * @param params 参数
     * @return 数量
     */
    @Override
    public int countProdOnline(Map<String, Object> params) {
        return prodDAO.count("countProdOnline", params);
    }

    /**
     * 加载产品特定参数
     *
     * @param prodNo 产品编号
     * @return 参数
     */
    @Override
    public List<ProdSpArgs> queryProdSpArgs(String prodNo) {
        return prodSpArgsDAO.query("queryProdSpArgs", prodNo);
    }

    /**
     * 加载产品安装位置
     *
     * @param prodNo 产品编号
     * @return 安装位置
     */
    @Override
    public ProdInstlPos loadProdInstlPos(String prodNo) {
        return prodInstlPosDAO.queryUnique("loadProdInstlPos", prodNo);
    }

    /**
     * 删除产品问题报告
     *
     * @param prodNo 产品编号
     */
    @Override
    public void removeProdBugRpt(String prodNo) {
        prodBugRptDAO.delete("deleteProdBugRpt", prodNo);
    }

    /**
     * 加载产品问题报告
     *
     * @param prodNo 产品编号
     * @return 产品问题报告
     */
    @Override
    public ProdBugRpt loadProdBugRpt(String prodNo) {
        return prodBugRptDAO.queryUnique("loadProdBugRpt", prodNo);
    }

    /**
     * 创建设备安装位置
     *
     * @param prodInstlPos 位置
     */
    @Override
    public void createProdInstlPos(ProdInstlPos prodInstlPos) {
        ProdInstlPos instlPos = loadProdInstlPos(prodInstlPos.getProdNo());
        if (instlPos != null) {
            prodInstlPos.setCrtime(new Date());
            prodInstlPos.setUptime(new Date());
            prodInstlPosDAO.update("updateProdInstlPos", prodInstlPos);
        } else {
            prodInstlPos.setId(identifierSV.uniqueId());
            prodInstlPos.setCrtime(new Date());
            prodInstlPos.setUptime(new Date());
            prodInstlPosDAO.insert(prodInstlPos);
        }
    }

    /**
     * 查询所有在线设备
     *
     * @return 在线设备
     */
    @Override
    public List<ProdOnlLog> queryAllOnlineProd() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("state", 1);
        return prodOnlLogDAO.query(params);
    }

    /**
     * 根据产品号加载在线设备
     *
     * @param prodNo
     * @return 在线设备
     */
    @Override
    public ProdOnlLog loadProdOnline(String prodNo) {
        return prodOnlLogDAO.queryUnique("loadProdOnlineByProdNo", prodNo);
    }

    /**
     * 创建上报记录
     *
     * @param bizNo 商家编号
     * @param  type 类别
     */
    @Override
    public void createProdCoinRptLog(String bizNo, Integer type) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        params.put("type", type);
        List<ProdCoinRptLog> prodCoinRptLogs = prodCoinRptLogDAO.query("queryCurrentDayRptByBizNo", params);
        if (prodCoinRptLogs.isEmpty()) {
            ProdCoinRptLog prodCoinRptLog = new ProdCoinRptLog();
            prodCoinRptLog.setId(identifierSV.uniqueId());
            prodCoinRptLog.setBizNo(bizNo);
            prodCoinRptLog.setTimes(1);
            prodCoinRptLog.setType(type);
            prodCoinRptLog.setCrtime(new Date());
            prodCoinRptLog.setUptime(new Date());
            prodCoinRptLogDAO.insert(prodCoinRptLog);
        } else {
            prodCoinRptLogDAO.update("updateProdCoinRptLogTimesAdd", params);
        }
    }

    /**
     * 统计上报次数
     *
     * @param bizNo 商家编号
     * @param type 类型
     * @return 次数
     */
    @Override
    public int countCurrentDayProdCoinRptLogTimes(String bizNo, Integer type) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        params.put("type", type);
        List<ProdCoinRptLog> prodCoinRptLogs = prodCoinRptLogDAO.query("queryCurrentDayRptByBizNo", params);
        if (prodCoinRptLogs.isEmpty()) {
            return 0;
        } else {
            return prodCoinRptLogs.get(0).getTimes();
        }
    }

    /**
     * 查询带有安装地址的商品列表
     *
     * @param bizNo    商品编号
     * @param rowStart 起始行
     * @param pageSize 页数
     * @return 结果集
     */
    @Override
    public List<Prod> queryProdWithInstlPos(String bizNo, int rowStart, int pageSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        return prodDAO.query("queryProdWithInstlPos", params, rowStart, pageSize);
    }

    /**
     * 根据商家编号统计商品数量
     *
     * @param bizNo 商家编号
     * @return 数量
     */
    @Override
    public int countProdByBizNo(String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        return prodDAO.count("countProd", params);
    }

    /**
     * 通过订单表的商家编号找到商家所有的产品列表
     *
     * @param params
     * @return
     */
    @Override
    public List<Prod> queryProdByOrdBizNo(Map<String, Object> params, int rowStart, int pageSize) {
        if (rowStart==-1||pageSize==-1){
            return prodDAO.query("queryProdByOrdBizNo",params);
        }else {
            return prodDAO.query("queryProdByOrdBizNo",params,rowStart,pageSize);
        }
    }

    /**
     * 通过订单表的商家编号找到商家所有的产品列表个数
     *
     * @param params
     * @return
     */
    @Override
    public int countProdByOrdBizNo(Map<String, Object> params) {
        return prodDAO.count("countProdByOrdBizNo", params);
    }

    /**
     * 查询故障设备列表
     *
     * @param params
     * @param rowStart
     * @param pageSize
     * @return
     */
    @Override
    public List<Prod> queryProdBugRpt(Map<String, Object> params, int rowStart, int pageSize) {
        if (rowStart==-1||pageSize==-1){
            return prodDAO.query("queryProdBugRpt",params);
        }else {
            return prodDAO.query("queryProdBugRpt",params,rowStart,pageSize);
        }
    }

    /**
     * 查询故障设备总数量
     *
     * @param params
     * @return
     */
    @Override
    public int countProdBugRpt(Map<String, Object> params) {
        return prodDAO.count("countProdBugRpt", params);
    }

    @Override
    public List<Prod> queryOnlineProdByGprsType(String bizNo, String gprsType) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        params.put("gprsType", gprsType);
        return prodDAO.query("queryOnlineProdByGprsType",params);
    }

    /**
     * 获取商家的所有设备的上下线状态
     *
     * @param bizNo
     * @return
     */
    @Override
    public List<Map<String,Object>> queryProdOnlineState(String bizNo) {
        return prodOnlLogDAO.query("queryProdOnlineState",bizNo);
    }
}
