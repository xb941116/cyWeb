package com.zzwdkj.ord.service;

import com.zzwdkj.ord.entity.OrdPayReturn;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.ord.dao.OrdPayReturnDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* ord_pay_return, 订单_退单表SVImpl
*
* @author guoxianwei  2016-09-07 15:01:46
*/
@Service("ordPayReturnSV")
public class OrdPayReturnSVImpl implements OrdPayReturnSV {

    @Resource
    private OrdPayReturnDAO ordPayReturnDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询订单_退单表 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<OrdPayReturn> queryOrdPayReturn(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return ordPayReturnDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计订单_退单表数量
    * @param name 名称
    * @return 订单_退单表
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return ordPayReturnDAO.count(params);
    }


    /**
    * 新增订单_退单表
    *
    * @param ordPayReturn
    */
    @Override
    public void create(OrdPayReturn ordPayReturn) {
        ordPayReturn.setId(identifierSV.uniqueId());
        ordPayReturn.setCrtime(new Date());
        ordPayReturn.setUptime(new Date());
        ordPayReturnDAO.insert(ordPayReturn);
    }

    /**
    * 修改订单_退单表
    *
    * @param ordPayReturn
    */
    @Override
    public void update(OrdPayReturn ordPayReturn) {
        ordPayReturn.setUptime(new Date());
        ordPayReturnDAO.update(ordPayReturn);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        ordPayReturnDAO.delete(id);
    }

    /**
    * 加载订单_退单表
    *
    * @param id 主键
    * @return 订单_退单表
    */
    @Override
    public OrdPayReturn load(String id) {
        return ordPayReturnDAO.load(id);
    }

}
