package com.zzwdkj.ord.service;

import com.zzwdkj.ord.entity.OrdItem;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.ord.dao.OrdItemDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* ord_item, 订单_客户_订单商品项SVImpl
*
* @author guoxianwei  2016-09-07 15:01:45
*/
@Service("ordItemSV")
public class OrdItemSVImpl implements OrdItemSV {

    @Resource
    private OrdItemDAO ordItemDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询订单_客户_订单商品项 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<OrdItem> queryOrdItem(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return ordItemDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计订单_客户_订单商品项数量
    * @param name 名称
    * @return 订单_客户_订单商品项
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return ordItemDAO.count(params);
    }


    /**
    * 新增订单_客户_订单商品项
    *
    * @param ordItem
    */
    @Override
    public void create(OrdItem ordItem) {
        ordItem.setId(identifierSV.uniqueId());
        ordItem.setCrtime(new Date());
        ordItem.setUptime(new Date());
        ordItemDAO.insert(ordItem);
    }

    /**
    * 修改订单_客户_订单商品项
    *
    * @param ordItem
    */
    @Override
    public void update(OrdItem ordItem) {
        ordItem.setUptime(new Date());
        ordItemDAO.update(ordItem);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        ordItemDAO.delete(id);
    }

    /**
    * 加载订单_客户_订单商品项
    *
    * @param id 主键
    * @return 订单_客户_订单商品项
    */
    @Override
    public OrdItem load(String id) {
        return ordItemDAO.load(id);
    }

}
