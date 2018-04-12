package com.zzwdkj.biz.service;

import com.zzwdkj.biz.entity.BizBank;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.biz.dao.BizBankDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* biz_bank, 商家_银行卡SVImpl
*
* @author guoxianwei  2016-09-07 15:01:27
*/
@Service("bizBankSV")
public class BizBankSVImpl implements BizBankSV {

    @Resource
    private BizBankDAO bizBankDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询商家_银行卡 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<BizBank> queryBizBank(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return bizBankDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计商家_银行卡数量
    * @param name 名称
    * @return 商家_银行卡
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return bizBankDAO.count(params);
    }


    /**
    * 新增商家_银行卡
    *
    * @param bizBank
    */
    @Override
    public void create(BizBank bizBank) {
        bizBank.setId(identifierSV.uniqueId());
        bizBank.setCrtime(new Date());
        bizBank.setUptime(new Date());
        bizBankDAO.insert(bizBank);
    }

    /**
    * 修改商家_银行卡
    *
    * @param bizBank
    */
    @Override
    public void update(BizBank bizBank) {
        bizBank.setUptime(new Date());
        bizBankDAO.update(bizBank);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        bizBankDAO.delete(id);
    }

    /**
    * 加载商家_银行卡
    *
    * @param id 主键
    * @return 商家_银行卡
    */
    @Override
    public BizBank load(String id) {
        return bizBankDAO.load(id);
    }

}
