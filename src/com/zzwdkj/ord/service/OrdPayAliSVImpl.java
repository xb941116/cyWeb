package com.zzwdkj.ord.service;

import com.zzwdkj.ord.entity.OrdPayAli;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.ord.dao.OrdPayAliDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* ord_pay_ali, 订单_支付_支付宝支付记录表SVImpl
*
* @author guoxianwei  2016-09-07 15:01:46
*/
@Service("ordPayAliSV")
public class OrdPayAliSVImpl implements OrdPayAliSV {

    @Resource
    private OrdPayAliDAO ordPayAliDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询订单_支付_支付宝支付记录表 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<OrdPayAli> queryOrdPayAli(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return ordPayAliDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计订单_支付_支付宝支付记录表数量
    * @param name 名称
    * @return 订单_支付_支付宝支付记录表
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return ordPayAliDAO.count(params);
    }


    /**
    * 新增订单_支付_支付宝支付记录表
    *
    * @param ordPayAli
    */
    @Override
    public void create(OrdPayAli ordPayAli) {
        ordPayAli.setId(identifierSV.uniqueId());
        ordPayAli.setCrtime(new Date());
        ordPayAli.setUptime(new Date());
        ordPayAliDAO.insert(ordPayAli);
    }

    /**
    * 修改订单_支付_支付宝支付记录表
    *
    * @param ordPayAli
    */
    @Override
    public void update(OrdPayAli ordPayAli) {
        ordPayAli.setUptime(new Date());
        ordPayAliDAO.update(ordPayAli);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        ordPayAliDAO.delete(id);
    }

    /**
    * 加载订单_支付_支付宝支付记录表
    *
    * @param id 主键
    * @return 订单_支付_支付宝支付记录表
    */
    @Override
    public OrdPayAli load(String id) {
        return ordPayAliDAO.load(id);
    }

}
