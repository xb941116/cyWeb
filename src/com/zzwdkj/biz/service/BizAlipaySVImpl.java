package com.zzwdkj.biz.service;

import com.zzwdkj.biz.entity.BizAlipay;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.biz.dao.BizAlipayDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* biz_alipay, 商家_支付宝SVImpl
*
* @author guoxianwei  2016-09-07 15:01:27
*/
@Service("bizAlipaySV")
public class BizAlipaySVImpl implements BizAlipaySV {

    @Resource
    private BizAlipayDAO bizAlipayDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询商家_支付宝 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<BizAlipay> queryBizAlipay(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return bizAlipayDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计商家_支付宝数量
    * @param name 名称
    * @return 商家_支付宝
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return bizAlipayDAO.count(params);
    }


    /**
    * 新增商家_支付宝
    *
    * @param bizAlipay
    */
    @Override
    public void create(BizAlipay bizAlipay) {
        bizAlipay.setId(identifierSV.uniqueId());
        bizAlipay.setCrtime(new Date());
        bizAlipay.setUptime(new Date());
        bizAlipayDAO.insert(bizAlipay);
    }

    /**
    * 修改商家_支付宝
    *
    * @param bizAlipay
    */
    @Override
    public void update(BizAlipay bizAlipay) {
        bizAlipay.setUptime(new Date());
        bizAlipayDAO.update(bizAlipay);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        bizAlipayDAO.delete(id);
    }

    /**
    * 加载商家_支付宝
    *
    * @param id 主键
    * @return 商家_支付宝
    */
    @Override
    public BizAlipay load(String id) {
        return bizAlipayDAO.load(id);
    }

}
