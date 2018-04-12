package com.zzwdkj.biz.service;

import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.biz.dao.BizAdviseDAO;
import com.zzwdkj.biz.dao.BizDAO;
import com.zzwdkj.biz.dao.BizWltDAO;
import com.zzwdkj.biz.entity.Biz;
import com.zzwdkj.biz.entity.BizAdvise;
import com.zzwdkj.biz.entity.BizWlt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * biz, 商家SVImpl
 *
 * @author guoxianwei  2016-09-07 15:01:26
 */
@Service("bizSV")
public class BizSVImpl implements BizSV {

    @Resource
    private BizDAO bizDAO;
    @Resource
    private BizWltDAO bizWltDAO;
    @Resource
    private BizAdviseDAO bizAdviseDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
     * 查询商家 ，带分页
     *
     * @param params   参数
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 菜单资源
     */
    @Override
    public List<Biz> queryBiz(Map<String, Object> params, int rowStart, int rowSize) {
        if(rowStart==-1||rowSize==-1){
            return bizDAO.query(params);
        }else {
            return bizDAO.query(params, rowStart, rowSize);
        }
    }

    /**
     * 查询商家 ，带分页
     *
     * @param params   参数
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 菜单资源
     */
    @Override
    public List<Biz> queryBizJoinAcct(Map<String, Object> params, int rowStart, int rowSize) {
        if(rowStart==-1||rowSize==-1){
            return bizDAO.query("queryBizJoinAcct",params);
        }else {
            return bizDAO.query("queryBizJoinAcct",params, rowStart, rowSize);
        }
    }

    /**
     * 查询下级商家 ，带分页
     *
     * @param bizNo    商家编号
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 结果集
     */
    @Override
    public List<Biz> queryBizUnder(String bizNo, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        if(rowStart==-1||rowSize==-1){
            return bizDAO.query("queryBizUnder", params);
        }else {
            return bizDAO.query("queryBizUnder", params, rowStart, rowSize);
        }
    }

    /**
     * 统计商家数量
     *
     * @param params 参数
     * @return 商家
     */
    @Override
    public int countBiz(Map<String, Object> params) {
        return bizDAO.count(params);
    }

    /**
     * 统计商家数量
     *
     * @param params 参数
     * @return 商家
     */
    @Override
    public int countBizJoinAcct(Map<String, Object> params) {
        return bizDAO.count("countBizJoinAcct",params);
    }


    /**
     * 新增商家
     *
     * @param biz 商家
     */
    @Override
    public void createBiz(Biz biz) {
        biz.setId(identifierSV.uniqueId());
        biz.setBizNo(identifierSV.bizCode());
        biz.setState(0);
        biz.setCrtime(new Date());
        biz.setUptime(new Date());
        bizDAO.insert(biz);
        BizWlt bizWlt = new BizWlt(biz.getBizNo());
        bizWlt.setId(biz.getId());
        bizWltDAO.insert(bizWlt);
    }

    /**
     * 修改商家
     *
     * @param biz 商家
     */
    @Override
    public void updateBiz(Biz biz) {
        biz.setUptime(new Date());
        bizDAO.update(biz);
    }

    /**
     * 删除一条记录
     *
     * @param id 主键
     */
    @Override
    public void removeBiz(String id) {
        bizDAO.delete(id);
    }

    /**
     * 加载商家
     *
     * @param id 主键
     * @return 商家
     */
    @Override
    public Biz loadBiz(String id) {
        return bizDAO.load(id);
    }

    /**
     * 加载商家
     *
     * @param mobile 联系人手机
     * @return 商家
     */
    @Override
    public Biz loadBizByMobile(String mobile) {
        return bizDAO.queryUnique("loadBizByMobile", mobile);
    }

    /**
     * 加载商家
     *
     * @param name 企业名称
     * @return 商家
     */
    @Override
    public Biz loadBizByName(String name) {
        return bizDAO.queryUnique("loadBizByName", name);
    }

    /**
     * 加载商家
     *
     * @param email 企业邮箱
     * @return 商家
     */
    @Override
    public Biz loadBizByEmail(String email) {
        return bizDAO.queryUnique("loadBizByEmail", email);
    }

    /**
     * 加载商家
     *
     * @param bizNo 商家编号
     * @return 商家
     */
    @Override
    public Biz loadBizByBizNo(String bizNo) {
        return bizDAO.queryUnique("loadBizByBizNo", bizNo);
    }

    /**
     * 添加商家投诉意见
     *
     * @param bizAdvise 意见
     */
    @Override
    public void createBizAdvise(BizAdvise bizAdvise) {
        bizAdvise.setId(identifierSV.uniqueId());
        bizAdviseDAO.insert(bizAdvise);
    }

    /**
     * 加载商家钱包
     *
     * @param bizNo 商户编号
     * @return 钱包
     */
    @Override
    public BizWlt loadBizWlt(String bizNo) {
        return bizWltDAO.queryUnique("loadBizWltByBizNo", bizNo);
    }
}
