package com.zzwdkj.ord.service;

import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.ord.dao.OrdPayCoinDAO;
import com.zzwdkj.ord.entity.OrdPayCoin;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/7.
 * 修改时间：2017/12/7.；修改人：sfn   新添加的实现类
 */

@Service("ordPayCoinSV")
public class OrdPayCoinSVImpl implements OrdPayCoinSV {

    @Resource
    private OrdPayCoinDAO ordPayCoinDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
     * 查询订单_支付_积分支付记录表 ，带分页
     *
     * @param name     菜单名称
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 菜单资源
     */
    @Override
    public List<OrdPayCoin> queryOrdPayCoin(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return ordPayCoinDAO.query(params, rowStart, rowSize);
    }

    /**
     * 统计订单_支付_积分支付记录表数量
     * @param name 名称
     * @return 订单_支付_积分支付记录表
     */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return ordPayCoinDAO.count(params);
    }


    /**
     * 新增订单_支付__积分支付记录表
     *
     * @param ordPayCoin
     */
    @Override
    public void create(OrdPayCoin ordPayCoin) {
        ordPayCoin.setId(identifierSV.uniqueId());
        ordPayCoin.setCrtime(new Date());
        ordPayCoin.setUptime(new Date());
        ordPayCoinDAO.insert(ordPayCoin);
    }

    /**
     * 修改订单_支付_积分支付记录表
     *
     * @param ordPayCoin
     */
    @Override
    public void update(OrdPayCoin ordPayCoin) {
        ordPayCoin.setUptime(new Date());
        ordPayCoinDAO.update(ordPayCoin);
    }

    /**
     * 删除一条记录
     *
     * @param id
     */
    @Override
    public void remove(String id) {
        ordPayCoinDAO.delete(id);
    }

    /**
     * 加载订单_支付_积分支付记录表
     *
     * @param id 主键
     * @return 订单_支付_积分支付记录表
     */
    @Override
    public OrdPayCoin load(String id) {
        return ordPayCoinDAO.load(id);
    }

}
