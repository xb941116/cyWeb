package com.zzwdkj.ord.service;

import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.ord.dao.OrdPayWltDAO;
import com.zzwdkj.ord.entity.OrdPayWlt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* ord_pay_wlt, 订单_支付_钱包支付记录表SVImpl
*
* @author guoxianwei  2016-09-07 15:01:51
*/
@Service("ordPayWltSV")
public class OrdPayWltSVImpl implements OrdPayWltSV {

    @Resource
    private OrdPayWltDAO ordPayWltDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询订单_支付_钱包支付记录表 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<OrdPayWlt> queryOrdPayWlt(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return ordPayWltDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计订单_支付_钱包支付记录表数量
    * @param name 名称
    * @return 订单_支付_钱包支付记录表
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return ordPayWltDAO.count(params);
    }


    /**
    * 新增订单_支付_钱包支付记录表
    *
    * @param ordPayWlt
    */
    @Override
    public void create(OrdPayWlt ordPayWlt) {
        ordPayWlt.setId(identifierSV.uniqueId());
        ordPayWlt.setCrtime(new Date());
        ordPayWlt.setUptime(new Date());
        ordPayWltDAO.insert(ordPayWlt);
    }

    /**
    * 修改订单_支付_钱包支付记录表
    *
    * @param ordPayWlt
    */
    @Override
    public void update(OrdPayWlt ordPayWlt) {
        ordPayWlt.setUptime(new Date());
        ordPayWltDAO.update(ordPayWlt);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        ordPayWltDAO.delete(id);
    }

    /**
    * 加载订单_支付_钱包支付记录表
    *
    * @param id 主键
    * @return 订单_支付_钱包支付记录表
    */
    @Override
    public OrdPayWlt load(String id) {
        return ordPayWltDAO.load(id);
    }

}
