package com.zzwdkj.biz.service;

import com.zzwdkj.biz.entity.BizTakeBank;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.biz.dao.BizTakeBankDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* biz_take_bank, 商家_提现_银行卡_记录SVImpl
*
* @author guoxianwei  2016-09-07 15:01:32
*/
@Service("bizTakeBankSV")
public class BizTakeBankSVImpl implements BizTakeBankSV {

    @Resource
    private BizTakeBankDAO bizTakeBankDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询商家_提现_银行卡_记录 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<BizTakeBank> queryBizTakeBank(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return bizTakeBankDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计商家_提现_银行卡_记录数量
    * @param name 名称
    * @return 商家_提现_银行卡_记录
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return bizTakeBankDAO.count(params);
    }


    /**
    * 新增商家_提现_银行卡_记录
    *
    * @param bizTakeBank
    */
    @Override
    public void create(BizTakeBank bizTakeBank) {
        bizTakeBank.setId(identifierSV.uniqueId());
        bizTakeBank.setCrtime(new Date());
        bizTakeBank.setUptime(new Date());
        bizTakeBankDAO.insert(bizTakeBank);
    }

    /**
    * 修改商家_提现_银行卡_记录
    *
    * @param bizTakeBank
    */
    @Override
    public void update(BizTakeBank bizTakeBank) {
        bizTakeBank.setUptime(new Date());
        bizTakeBankDAO.update(bizTakeBank);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        bizTakeBankDAO.delete(id);
    }

    /**
    * 加载商家_提现_银行卡_记录
    *
    * @param id 主键
    * @return 商家_提现_银行卡_记录
    */
    @Override
    public BizTakeBank load(String id) {
        return bizTakeBankDAO.load(id);
    }

    /**
     * 获取商家_提现_银行卡_记录 通过申请单号
     *
     * @param takeNo 申请单号
     * @return
     */
    @Override
    public BizTakeBank loadByReqno(String takeNo) {
        if (takeNo==null||takeNo.equals("")){
            return null;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("takeNo",takeNo);
        List<BizTakeBank> bizTakeBankList=bizTakeBankDAO.query(params);
        if (bizTakeBankList!=null&&bizTakeBankList.size()==1){
            return bizTakeBankList.get(0);
        }else {
            return null;
        }
    }

}
