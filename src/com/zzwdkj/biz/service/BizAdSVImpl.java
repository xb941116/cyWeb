package com.zzwdkj.biz.service;

import com.zzwdkj.biz.entity.BizAd;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.common.Std;
import com.zzwdkj.biz.dao.BizAdDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* biz_ad, 商家广告表SVImpl
*
* @author xizhuangchui  2017-04-22 12:12:22
*/
@Service("bizAdSV")
public class BizAdSVImpl implements BizAdSV {

    @Resource
    private BizAdDAO bizAdDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询商家广告表 ，带分页
    *
    * @param bizNo     商家编号
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<BizAd> queryBizAd(String bizNo, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        if (bizNo==null||bizNo.equals("")){
            return null;
        }
        params.put("bizNo", bizNo);
        return bizAdDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计商家广告表数量
    * @param bizNo 商家编号
    * @return 商家广告表
    */
    @Override
    public int count(String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        return bizAdDAO.count(params);
    }


    /**
    * 新增商家广告表
    *
    * @param bizAd
    */
    @Override
    public void create(BizAd bizAd) {
        bizAd.setId(identifierSV.uniqueId());
        bizAd.setCrtime(new Date());
        bizAd.setUptime(new Date());
        bizAdDAO.insert(bizAd);
    }

    /**
    * 修改商家广告表
    *
    * @param bizAd
    */
    @Override
    public void update(BizAd bizAd) {
        bizAd.setUptime(new Date());
        bizAdDAO.update(bizAd);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        bizAdDAO.delete(id);
    }

    /**
    * 加载商家广告表
    *
    * @param id 主键
    * @return 商家广告表
    */
    @Override
    public BizAd load(String id) {
        return bizAdDAO.load(id);
    }

    /**
     * 通过商家编号获取 广告表
     *
     * @param bizNo 商家编号
     * @param adType 广告类型
     * @param checkTime 是否校验结束时间
     * @return
     */
    @Override
    public List<BizAd> queryBizAdByBizNoAndType(String bizNo,Integer adType,boolean checkTime) {
        if (bizNo==null||bizNo.equals("")){
            return null;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo",bizNo);
        params.put("adType",adType);
        if (checkTime){
            params.put("checkTime",checkTime);
        }
        params.put("orderField","ad_seat");
        params.put("orderSeq","asc");
        return bizAdDAO.query(params);
    }

    @Override
    public void createPaySucess(BizAd bizAd) {

        bizAd.setId(identifierSV.uniqueId());
        bizAd.setCrtime(new Date());
        bizAd.setUptime(new Date());
        bizAd.setAdSeat(1);
        bizAdDAO.insert(bizAd);
        bizAd.setId(identifierSV.uniqueId());
        bizAd.setAdSeat(2);
        bizAdDAO.insert(bizAd);
    }

    @Override
    public List<BizAd> queryBizAdByparams(Map<String, Object> params, int rowStart, int rowSize) {
        if (rowStart==-1||rowSize==-1){
            return bizAdDAO.query(params);
        }else {
            return bizAdDAO.query(params,rowStart,rowSize);
        }

    }

}
