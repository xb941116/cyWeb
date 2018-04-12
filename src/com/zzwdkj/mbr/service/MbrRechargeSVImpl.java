package com.zzwdkj.mbr.service;

import com.zzwdkj.mbr.entity.MbrRecharge;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.mbr.dao.MbrRechargeDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * mbr_recharge, 充值记录表SVImpl
 *
 * @author guoxianwei  2016-11-11 15:55:31
 */
@Service("mbrRechargeSV")
public class MbrRechargeSVImpl implements MbrRechargeSV {

    @Resource
    private MbrRechargeDAO mbrRechargeDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
     * 查询充值记录表 ，带分页
     *
     * @param name     菜单名称
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 菜单资源
     */
    @Override
    public List<MbrRecharge> queryMbrRecharge(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return mbrRechargeDAO.query(params, rowStart, rowSize);
    }

    /**
     * 统计充值记录表数量
     *
     * @param name 名称
     * @return 充值记录表
     */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return mbrRechargeDAO.count(params);
    }


    /**
     * 新增充值记录表
     *
     * @param mbrRecharge
     */
    @Override
    public void create(MbrRecharge mbrRecharge) {
        mbrRecharge.setId(identifierSV.uniqueId());
        mbrRecharge.setCrtime(new Date());
        mbrRecharge.setUptime(new Date());
        mbrRechargeDAO.insert(mbrRecharge);
    }

    /**
     * 修改充值记录表
     *
     * @param mbrRecharge
     */
    @Override
    public void update(MbrRecharge mbrRecharge) {
        mbrRecharge.setUptime(new Date());
        mbrRechargeDAO.update(mbrRecharge);
    }

    /**
     * 删除一条记录
     *
     * @param id
     */
    @Override
    public void remove(String id) {
        mbrRechargeDAO.delete(id);
    }

    /**
     * 加载充值记录表
     *
     * @param id 主键
     * @return 充值记录表
     */
    @Override
    public MbrRecharge load(String id) {
        return mbrRechargeDAO.load(id);
    }

    /**
     * 通过支付单号获取 充值记录
     *
     * @param payno
     */
    @Override
    public MbrRecharge loadByPayno(String payno) {
        return mbrRechargeDAO.queryUnique("loadByPayno", payno);
    }

    /**
     * 查询充值记录
     *
     * @param params
     * @param startRow 起始行（不含起始行）
     * @param pageSize 每页行数
     * @return 结果集
     */
    @Override
    public List<MbrRecharge> queryMbrRecharge(Map<String, Object> params, int startRow, int pageSize) {
        if (startRow == -1 || pageSize == -1) {
            return mbrRechargeDAO.query(params);
        } else {
            return mbrRechargeDAO.query(params, startRow, pageSize);
        }
    }

    /**
     * 充值记录的总数
     *
     * @param params
     * @return
     */
    @Override
    public int countMbrRecharge(Map<String, Object> params) {
        return mbrRechargeDAO.count(params);
    }


    /**
     * 查询充值记录
     *
     * @param openid
     * @param startRow 起始行（不含起始行）
     * @param pageSize 每页行数
     * @return 结果集
     */
    @Override
    public List<MbrRecharge> queryMbrReCgHisByOpenid(String openid, int startRow, int pageSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("openid", openid);
        List<MbrRecharge> mbrRechargeList = mbrRechargeDAO.query("queryMbrReCgHisByOpenid", params, startRow, pageSize);
        return mbrRechargeList;
    }


    /**
     * 查询充值记录
     *
     * @param openid
     * @param bizNo
     * @param startRow 起始行（不含起始行）
     * @param pageSize 每页行数
     * @return 结果集
     */
    @Override
    public List<MbrRecharge> queryMbrReCgHisByOpenidAndBizNo(String openid, String bizNo, int startRow, int pageSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("openid", openid);
        params.put("bizNo", bizNo);
        List<MbrRecharge> mbrRechargeList = mbrRechargeDAO.query("queryMbrReCgHisByOpenidAndBizNo", params, startRow, pageSize);
        return mbrRechargeList;
    }

    /**
     * 充值记录的总数
     *
     * @param openid
     * @return
     */
    @Override
    public int countMbrReCgHisByOpenid(String openid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("openid", openid);
        return mbrRechargeDAO.count("countMbrReCgHisByOpenid", params);
    }

    /**
     * 充值记录的总数
     *
     * @param openid
     * @param bizNo
     * @return
     */
    @Override
    public int countMbrReCgHisByOpenidAndBizNo(String openid, String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("openid", openid);
        params.put("bizNo", bizNo);
        return mbrRechargeDAO.count("countMbrReCgHisByOpenidAndBizNo", params);
    }

    /**
     * 加载充值收入
     *
     * @param bizNo 商家编号
     * @return 收入
     */
    @Override
    public BigDecimal queryStaIncByBizNo(String bizNo) {
        MbrRecharge recharge = mbrRechargeDAO.queryUnique("loadMbrRechargeStaIncByBizNo", bizNo);
        if (recharge != null) {
            return recharge.getMoney();
        }
        return BigDecimal.ZERO;
    }
}
