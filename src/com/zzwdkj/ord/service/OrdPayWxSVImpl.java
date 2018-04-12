package com.zzwdkj.ord.service;

import com.zzwdkj.ord.entity.OrdPayWx;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.ord.dao.OrdPayWxDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* ord_pay_wx, 订单_支付_微信支付记录表SVImpl
*
* @author guoxianwei  2016-09-07 15:01:55
*/
@Service("ordPayWxSV")
public class OrdPayWxSVImpl implements OrdPayWxSV {

    @Resource
    private OrdPayWxDAO ordPayWxDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询订单_支付_微信支付记录表 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<OrdPayWx> queryOrdPayWx(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return ordPayWxDAO.query(params, rowStart, rowSize);
    }

    /**
     * 查询订单_支付_微信支付记录表 ，带分页(rowStart或rowSize的值为-1时候则全查)
     *
     * @param params 条件Map
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 菜单资源
     */
    @Override
    public List<OrdPayWx> queryOrdPayWx(Map<String, Object> params,int rowStart, int rowSize) {
        if (rowStart==-1||rowSize==-1){
            return ordPayWxDAO.query(params);
        }else {
            return ordPayWxDAO.query(params, rowStart, rowSize);
        }
    }

    /**
    * 统计订单_支付_微信支付记录表数量
    * @param name 名称
    * @return 订单_支付_微信支付记录表
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return ordPayWxDAO.count(params);
    }


    /**
    * 新增订单_支付_微信支付记录表
    *
    * @param ordPayWx
    */
    @Override
    public void create(OrdPayWx ordPayWx) {
        ordPayWx.setId(identifierSV.uniqueId());
        ordPayWx.setCrtime(new Date());
        ordPayWx.setUptime(new Date());
        ordPayWxDAO.insert(ordPayWx);
    }

    /**
    * 修改订单_支付_微信支付记录表
    *
    * @param ordPayWx
    */
    @Override
    public void update(OrdPayWx ordPayWx) {
        ordPayWx.setUptime(new Date());
        ordPayWxDAO.update(ordPayWx);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        ordPayWxDAO.delete(id);
    }

    /**
    * 加载订单_支付_微信支付记录表
    *
    * @param id 主键
    * @return 订单_支付_微信支付记录表
    */
    @Override
    public OrdPayWx load(String id) {
        return ordPayWxDAO.load(id);
    }

    /**
     * 通过订单号获取 订单_支付_微信支付记录表
     *
     * @param payno
     * @return
     */
    @Override
    public OrdPayWx loadByPayno(String payno) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ordno", payno);
        return ordPayWxDAO.queryUnique("loadOrdPayWxByOrdNo",params);
    }

}
