package com.zzwdkj.prod.service;

import com.zzwdkj.prod.entity.ProdBaseArgs;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.prod.dao.ProdBaseArgsDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* prod_base_args, 商品_基本属性SVImpl
*
* @author guoxianwei  2016-09-07 15:01:57
*/
@Service("prodBaseArgsSV")
public class ProdBaseArgsSVImpl implements ProdBaseArgsSV {

    @Resource
    private ProdBaseArgsDAO prodBaseArgsDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询商品_基本属性 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<ProdBaseArgs> queryProdBaseArgs(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return prodBaseArgsDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计商品_基本属性数量
    * @param name 名称
    * @return 商品_基本属性
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return prodBaseArgsDAO.count(params);
    }


    /**
    * 新增商品_基本属性
    *
    * @param prodBaseArgs
    */
    @Override
    public void create(ProdBaseArgs prodBaseArgs) {
        prodBaseArgs.setId(identifierSV.uniqueId());
        prodBaseArgs.setCrtime(new Date());
        prodBaseArgs.setUptime(new Date());
        prodBaseArgsDAO.insert(prodBaseArgs);
    }

    /**
    * 修改商品_基本属性
    *
    * @param prodBaseArgs
    */
    @Override
    public void update(ProdBaseArgs prodBaseArgs) {
        prodBaseArgs.setUptime(new Date());
        prodBaseArgsDAO.update(prodBaseArgs);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        prodBaseArgsDAO.delete(id);
    }

    /**
    * 加载商品_基本属性
    *
    * @param id 主键
    * @return 商品_基本属性
    */
    @Override
    public ProdBaseArgs load(String id) {
        return prodBaseArgsDAO.load(id);
    }

}
