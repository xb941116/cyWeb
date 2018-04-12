package com.zzwdkj.prod.service;

import com.zzwdkj.prod.entity.ProdModAttr;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.prod.dao.ProdModAttrDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* prod_mod_attr, 商品_模板_属性表SVImpl
*
* @author guoxianwei  2016-09-07 15:02:03
*/
@Service("prodModAttrSV")
public class ProdModAttrSVImpl implements ProdModAttrSV {

    @Resource
    private ProdModAttrDAO prodModAttrDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询商品_模板_属性表 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<ProdModAttr> queryProdModAttr(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return prodModAttrDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计商品_模板_属性表数量
    * @param name 名称
    * @return 商品_模板_属性表
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return prodModAttrDAO.count(params);
    }


    /**
    * 新增商品_模板_属性表
    *
    * @param prodModAttr
    */
    @Override
    public void create(ProdModAttr prodModAttr) {
        prodModAttr.setId(identifierSV.uniqueId());
        prodModAttr.setCrtime(new Date());
        prodModAttr.setUptime(new Date());
        prodModAttrDAO.insert(prodModAttr);
    }

    /**
    * 修改商品_模板_属性表
    *
    * @param prodModAttr
    */
    @Override
    public void update(ProdModAttr prodModAttr) {
        prodModAttr.setUptime(new Date());
        prodModAttrDAO.update(prodModAttr);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        prodModAttrDAO.delete(id);
    }

    /**
    * 加载商品_模板_属性表
    *
    * @param id 主键
    * @return 商品_模板_属性表
    */
    @Override
    public ProdModAttr load(String id) {
        return prodModAttrDAO.load(id);
    }

}
