package com.zzwdkj.biz.service;

import com.zzwdkj.biz.entity.BizVip;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.common.Std;
import com.zzwdkj.biz.dao.BizVipDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* biz_vip, 商家VIP功能表SVImpl
*
* @author xizhuangchui  2017-04-22 12:12:22
*/
@Service("bizVipSV")
public class BizVipSVImpl implements BizVipSV {

    @Resource
    private BizVipDAO bizVipDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询商家VIP功能表 ，带分页
    *
    * @param bizNo     商家编号
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<BizVip> queryBizVip(String bizNo, int rowStart, int rowSize) {
        if (bizNo==null||bizNo.equals("")){
            return null;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        return bizVipDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计商家VIP功能表数量
    * @param name 名称
    * @return 商家VIP功能表
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return bizVipDAO.count(params);
    }


    /**
    * 新增商家VIP功能表
    *
    * @param bizVip
    */
    @Override
    public void create(BizVip bizVip) {
        bizVip.setId(identifierSV.uniqueId());
        bizVip.setCrtime(new Date());
        bizVip.setUptime(new Date());
        bizVipDAO.insert(bizVip);
    }

    /**
    * 修改商家VIP功能表
    *
    * @param bizVip
    */
    @Override
    public void update(BizVip bizVip) {
        bizVip.setUptime(new Date());
        bizVipDAO.update(bizVip);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        bizVipDAO.delete(id);
    }

    /**
    * 加载商家VIP功能表
    *
    * @param id 主键
    * @return 商家VIP功能表
    */
    @Override
    public BizVip load(String id) {
        return bizVipDAO.load(id);
    }

    /**
     * 通过 商家编号获取 VIP功能表
     *
     * @param bizNo 商家编号
     * @param vipType 类型
     * @param checkTime 是否校验结束时间
     * @return
     */
    @Override
    public BizVip loadByBizNoAndType(String bizNo, Integer vipType,boolean checkTime) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        params.put("vipType", vipType);
        if (checkTime){
            params.put("checkTime",checkTime);
        }
        return bizVipDAO.queryUnique("loadByBizNoAndType",params);
    }

    /**
     * 通过条件查询 如果rowStart或者rowSize为-1则全查
     * @param params
     * @param rowStart
     * @param rowSize
     * @return
     */
    @Override
    public List<BizVip> queryBizAdByparams(Map<String, Object> params, int rowStart, int rowSize) {
        if (rowStart==-1||rowSize==-1){
            return bizVipDAO.query(params);
        }else {
            return bizVipDAO.query(params,rowStart,rowSize);
        }
    }


    /**
     * 创建支付完成界面 免广告功能
     * @param bizVip
     */
    @Override
    public void createPaySucess(BizVip bizVip) {
        bizVip.setId(identifierSV.uniqueId());
        bizVip.setCrtime(new Date());
        bizVip.setUptime(new Date());
        bizVipDAO.insert(bizVip);
    }

}
