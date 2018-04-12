package com.zzwdkj.prod.service;

import com.zzwdkj.prod.entity.ProdModAttrVal;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.prod.dao.ProdModAttrValDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* prod_mod_attr_val, 商品_模板_属性值SVImpl
*
* @author guoxianwei  2016-09-07 15:02:04
*/
@Service("prodModAttrValSV")
public class ProdModAttrValSVImpl implements ProdModAttrValSV {

    @Resource
    private ProdModAttrValDAO prodModAttrValDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询商品_模板_属性值 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<ProdModAttrVal> queryProdModAttrVal(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return prodModAttrValDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计商品_模板_属性值数量
    * @param name 名称
    * @return 商品_模板_属性值
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return prodModAttrValDAO.count(params);
    }


    /**
    * 新增商品_模板_属性值
    *
    * @param prodModAttrVal
    */
    @Override
    public void create(ProdModAttrVal prodModAttrVal) {
        prodModAttrVal.setId(identifierSV.uniqueId());
        prodModAttrVal.setCrtime(new Date());
        prodModAttrVal.setUptime(new Date());
        prodModAttrValDAO.insert(prodModAttrVal);
    }

    /**
    * 修改商品_模板_属性值
    *
    * @param prodModAttrVal
    */
    @Override
    public void update(ProdModAttrVal prodModAttrVal) {
        prodModAttrVal.setUptime(new Date());
        prodModAttrValDAO.update(prodModAttrVal);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        prodModAttrValDAO.delete(id);
    }

    /**
    * 加载商品_模板_属性值
    *
    * @param id 主键
    * @return 商品_模板_属性值
    */
    @Override
    public ProdModAttrVal load(String id) {
        return prodModAttrValDAO.load(id);
    }

}
