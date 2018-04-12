package com.zzwdkj.biz.service;

import com.zzwdkj.biz.dao.BizWxFocusDAO;
import com.zzwdkj.biz.entity.BizWx;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.biz.dao.BizWxDAO;
import com.zzwdkj.biz.entity.BizWxFocus;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * biz_wx, 商家_微信SVImpl
 *
 * @author guoxianwei  2016-09-07 15:01:33
 */
@Service("bizWxSV")
public class BizWxSVImpl implements BizWxSV {

    @Resource
    private BizWxDAO bizWxDAO;
    @Resource
    private BizWxFocusDAO bizWxFocusDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
     * 查询商家_微信 ，带分页
     *
     * @param name     菜单名称
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 菜单资源
     */
    @Override
    public List<BizWx> queryBizWx(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return bizWxDAO.query(params, rowStart, rowSize);
    }

    /**
     * 统计商家_微信数量
     *
     * @param name 名称
     * @return 商家_微信
     */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return bizWxDAO.count(params);
    }


    /**
     * 新增商家_微信
     *
     * @param bizWx
     */
    @Override
    public void createOrUpdate(BizWx bizWx) {
        if (StringUtils.isNotEmpty(bizWx.getId())) {
            bizWx.setUptime(new Date());
            bizWxDAO.update(bizWx);
        } else {
            bizWx.setId(identifierSV.uniqueId());
            bizWx.setCrtime(new Date());
            bizWx.setUptime(new Date());
            bizWxDAO.insert(bizWx);
        }
        if (bizWx.getFocus()==null || bizWx.getFocus() == 1) {
            BizWxFocus bizWxFocus = loadBizWxFocusByBizNo(bizWx.getBizNo());
            if (bizWxFocus != null) {
                bizWxFocus.setAppId(bizWx.getAppId());
                bizWxFocus.setAppSkey(bizWx.getAppSkey());
                bizWxFocus.setPubAcctId(bizWx.getPubAcctId());
                bizWxFocus.setName(bizWx.getBizName());
                bizWxFocus.setUptime(new Date());
                bizWxFocus.setOptor(bizWx.getOptor());
                bizWxFocusDAO.update(bizWxFocus);
            } else {
                 bizWxFocus = new BizWxFocus();
                        bizWxFocus.setId(identifierSV.uniqueId());
                bizWxFocus.setBizNo(bizWx.getBizNo());
                bizWxFocus.setAppId(bizWx.getAppId());
                bizWxFocus.setAppSkey(bizWx.getAppSkey());
                bizWxFocus.setPubAcctId(bizWx.getPubAcctId());
                bizWxFocus.setName(bizWx.getBizName());
                bizWxFocus.setUptime(new Date());
                bizWxFocus.setCrtime(new Date());
                bizWxFocus.setOptor(bizWx.getOptor());
                bizWxFocusDAO.insert(bizWxFocus);
            }
        }
    }

    /**
     * 删除一条记录
     *
     * @param id
     */
    @Override
    public void remove(String id) {
        bizWxDAO.delete(id);
    }

    /**
     * 加载商家_微信
     *
     * @param id 主键
     * @return 商家_微信
     */
    @Override
    public BizWx load(String id) {
        return bizWxDAO.load(id);
    }

    /**
     * 通过商家编号获取 商家_微信
     *
     * @param bizNo 商家编号
     * @return
     */
    @Override
    public BizWx loadByBizNo(String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        List<BizWx> bizWxList = bizWxDAO.query(params);
        if (bizWxList != null && bizWxList.size() == 1&&bizNo!=null&&!bizNo.equals("")) {
            return bizWxList.get(0);
        } else {
            return null;
        }
    }

    /**
     * 通过订单号获取收款方的 商家_微信
     *
     * @param ordNo 订单号
     * @return
     */
    @Override
    public BizWx loadByOrdNo(String ordNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ordno", ordNo);
        return bizWxDAO.queryUnique("loadByOrdNo", params);
    }

    /**
     * 通过设备号获取 商家_微信
     *
     * @param gprsNo 设备号
     * @return
     */
    @Override
    public BizWx loadByGprsNo(String gprsNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("gprsNo", gprsNo);
        List<BizWx> bizWxList = bizWxDAO.query("loadByGprsNo", params);
        if (bizWxList != null && bizWxList.size() == 1) {
            return bizWxList.get(0);
        } else {
            return null;
        }
    }

    /**
     * 删除微信API证书
     *
     * @param bizNo 商家编号
     */
    @Override
    public void removeCertSkey(String bizNo, String acct) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        params.put("acct", acct);
        bizWxDAO.update("removeCertSkey", params);
    }

    /**
     * 通过商户号获取 商家_微信
     *
     * @param bizNum 商户号
     * @param bizNo 商家编号
     * @return
     */
    @Override
    public BizWx loadByBizNumAndBizNo(String bizNum,String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNum", bizNum);
        params.put("bizNo", bizNo);
        return bizWxDAO.queryUnique("loadByBizNumAndBizNo", params);
    }

    /**
     * 加载微信强制关注信息
     *
     * @param bizNo 商家编号
     * @return 结果
     */
    @Override
    public BizWxFocus loadBizWxFocusByBizNo(String bizNo) {
        return bizWxFocusDAO.queryUnique("loadBizWxFocusByBizNo", bizNo);
    }

}
