package com.zzwdkj.biz.service;

import com.zzwdkj.biz.dao.BizDAO;
import com.zzwdkj.biz.dao.BizGprsBindHisDAO;
import com.zzwdkj.biz.entity.Biz;
import com.zzwdkj.biz.entity.BizGprsBind;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.biz.entity.BizGprsBindHis;
import com.zzwdkj.biz.dao.BizGprsBindDAO;
import com.zzwdkj.gprs.dao.GprsModelDAO;
import com.zzwdkj.prod.dao.ProdDAO;
import com.zzwdkj.prod.dao.ProdOnlLogDAO;
import com.zzwdkj.prod.entity.ProdGprsBindHis;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * biz_gprs_bind, 商家_GPRS绑定表SVImpl
 *
 * @author guoxianwei  2016-09-07 15:01:28
 */
@Service("bizGprsBindSV")
public class BizGprsBindSVImpl implements BizGprsBindSV {
    @Resource
    private BizDAO bizDAO;
    @Resource
    private BizGprsBindDAO bizGprsBindDAO;
    @Resource
    private BizGprsBindHisDAO bizGprsBindHisDAO;
    @Resource
    private GprsModelDAO gprsModelDAO;
    @Resource
    private ProdOnlLogDAO prodOnlLogDAO;
    @Resource
    private ProdDAO prodDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
     * 查询商家_GPRS绑定表 ，带分页
     *
     * @param params   参数
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 结果集
     */
    @Override
    public List<BizGprsBind> queryBizGprsBind(Map<String, Object> params, int rowStart, int rowSize) {
        return bizGprsBindDAO.query(params, rowStart, rowSize);
    }

    /**
     * 统计商家_GPRS绑定表数量
     *
     * @param params 参数
     * @return 商家_GPRS绑定表数量
     */
    @Override
    public int countBizGprsBind(Map<String, Object> params) {
        return bizGprsBindDAO.count(params);
    }

    /**
     * 新增商家_GPRS绑定表
     *
     * @param bizGprsBind
     */
    @Override
    public void create(BizGprsBind bizGprsBind) {
        bizGprsBind.setId(identifierSV.uniqueId());
        bizGprsBind.setCrtime(new Date());
        bizGprsBind.setUptime(new Date());
        bizGprsBindDAO.insert(bizGprsBind);
    }

    /**
     * 修改商家_GPRS绑定表
     *
     * @param bizGprsBind
     */
    @Override
    public void update(BizGprsBind bizGprsBind) {
        bizGprsBind.setUptime(new Date());
        bizGprsBindDAO.update(bizGprsBind);
    }

    /**
     * 删除一条记录
     *
     * @param id
     */
    @Override
    public void remove(String id) {
        bizGprsBindDAO.delete(id);
    }

    /**
     * 加载商家_GPRS绑定表
     *
     * @param id 主键
     * @return 商家_GPRS绑定表
     */
    @Override
    public BizGprsBind load(String id) {
        return bizGprsBindDAO.load(id);
    }

    /**
     * 绑定
     *
     * @param bizNo  商家编号
     * @param gprsNo 模块编号
     */
    @Override
    public void bind(String bizNo, String gprsNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        params.put("gprsNo", gprsNo);
        List<BizGprsBind> bizGprsBindList = bizGprsBindDAO.query(params);
        if (bizGprsBindList != null && !bizGprsBindList.isEmpty()) {
            BizGprsBind bizGprsBind = bizGprsBindList.get(0);
            bizGprsBind.setBind(1);
            bizGprsBind.setUptime(new Date());
            bizGprsBindDAO.update(bizGprsBind);
        } else {
            BizGprsBind bizGprsBind = new BizGprsBind(bizNo, gprsNo, 1);
            bizGprsBind.setId(identifierSV.uniqueId());
            bizGprsBind.setCrtime(new Date());
            bizGprsBind.setUptime(new Date());
            bizGprsBindDAO.insert(bizGprsBind);
        }
        gprsModelDAO.update("updateGprsModelBizBind", params);
        BizGprsBindHis bizGprsBindHis = new BizGprsBindHis(bizNo, gprsNo);
        bizGprsBindHis.setId(identifierSV.uniqueId());
        bizGprsBindHis.setCrtime(new Date());
        bizGprsBindHis.setUptime(new Date());
        bizGprsBindHisDAO.insert(bizGprsBindHis);
    }

    /**
     * 解绑
     *
     * @param bizNo  商家编号
     * @param gprsNo 模块编号
     */
    @Override
    public void unbind(String bizNo, String gprsNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        params.put("gprsNo", gprsNo);
        List<BizGprsBind> bizGprsBindList = bizGprsBindDAO.query(params);
        if (bizGprsBindList != null && !bizGprsBindList.isEmpty()) {
            prodOnlLogDAO.delete("deleteProdOnlLog",params);
            bizGprsBindDAO.delete(bizGprsBindList.get(0).getId());
            Biz biz = bizDAO.queryUnique("loadBizByBizNo", bizNo);
            if (biz != null && StringUtils.isNotEmpty(biz.getParBizNo())) {
                bind(biz.getParBizNo(), gprsNo);
                prodDAO.update("updateProdBizUnBind", params);
            } else {
                gprsModelDAO.update("updateGprsModelBizUnBind", params);
                prodDAO.update("updateProdBizUnBind", params);
            }

        }
    }

    /**
     * 模块移动
     *
     * @param bizNo  商家编号
     * @param gprsNo 模块编号
     */
    @Override
    public void moveModel(String bizNo, String gprsNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bind", 1);
        params.put("gprsNo", gprsNo);
        List<BizGprsBind> bizGprsBindList = bizGprsBindDAO.query(params);
        if (bizGprsBindList != null && !bizGprsBindList.isEmpty()) {
            bizGprsBindDAO.delete(bizGprsBindList.get(0).getId());
            params.put("bizNo", bizGprsBindList.get(0).getBizNo());
            prodDAO.update("updateProdBizUnBind", params);
            params.put("bizNo", bizNo);
            params.put("bind", 0);
            List<BizGprsBind> bizGprsBindList2 = bizGprsBindDAO.query(params);
            if (bizGprsBindList2 != null && !bizGprsBindList2.isEmpty()) {
                BizGprsBind bizGprsBind2 = new BizGprsBind();
                bizGprsBind2.setId(bizGprsBindList2.get(0).getId());
                bizGprsBind2.setBind(1);
                bizGprsBind2.setUptime(new Date());
                bizGprsBindDAO.update(bizGprsBind2);
            } else {
                BizGprsBind bizGprsBind2 = new BizGprsBind(bizNo, gprsNo, 1);
                bizGprsBind2.setId(identifierSV.uniqueId());
                bizGprsBind2.setCrtime(new Date());
                bizGprsBind2.setUptime(new Date());
                bizGprsBindDAO.insert(bizGprsBind2);
            }
            BizGprsBindHis bizGprsBindHis = new BizGprsBindHis(bizNo, gprsNo);
            bizGprsBindHis.setId(identifierSV.uniqueId());
            bizGprsBindHis.setCrtime(new Date());
            bizGprsBindHis.setUptime(new Date());
            bizGprsBindHisDAO.insert(bizGprsBindHis);
        }
    }

    /**
     * 加载商家模块绑定
     *
     * @param bizNo  商家编号
     * @param gprsNo 商家编号
     * @return 结果
     */
    @Override
    public BizGprsBind loadBizGprsBind(String bizNo, String gprsNo) {
        if (StringUtils.isNotEmpty(bizNo) && StringUtils.isNotEmpty(gprsNo)) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("bizNo", bizNo);
            params.put("gprsNo", gprsNo);
            params.put("bind", 1);
            List<BizGprsBind> bizGprsBindList = bizGprsBindDAO.query(params);
            if (bizGprsBindList != null && !bizGprsBindList.isEmpty()) {
                return bizGprsBindList.get(0);
            }
        }
        return null;
    }

    /**
     * 查询商家模块绑定
     *
     * @param bizNo 商家编号
     * @return 结果集
     */
    @Override
    public List<BizGprsBind> queryBizGprsBind(String bizNo) {
        if (StringUtils.isNotEmpty(bizNo)) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("bizNo", bizNo);
            params.put("bind", 1);
            List<BizGprsBind> bizGprsBindList = bizGprsBindDAO.query(params);
            return bizGprsBindList;
        }
        return null;
    }

    /**
     * 统计商家模块绑定
     *
     * @param bizNo 商家编号
     * @return 数量
     */
    @Override
    public int countBizGprsBind(String bizNo) {
        if (StringUtils.isNotEmpty(bizNo)) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("bizNo", bizNo);
            params.put("bind", 1);
            return bizGprsBindDAO.count(params);
        }
        return 0;
    }

    /**
     * 统计商家模块绑定
     *
     * @param bizNo  商家编号
     * @param gprsNo 商家编号
     * @return 数量
     */
    @Override
    public int countBizGprsBind(String bizNo, String gprsNo) {
        if (StringUtils.isNotEmpty(bizNo) && StringUtils.isNotEmpty(gprsNo)) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("bizNo", bizNo);
            params.put("gprsNo", gprsNo);
            params.put("bind", 1);
            return bizGprsBindDAO.count(params);
        }
        return 0;
    }
}
