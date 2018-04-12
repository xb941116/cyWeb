package com.zzwdkj.prod.service;

import com.zzwdkj.prod.entity.ProdCmdInvoke;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.prod.dao.ProdCmdInvokeDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* prod_cmd_invoke, 产品_高级命令下发表SVImpl
*
* @author guoxianwei  2016-09-07 15:01:58
*/
@Service("prodCmdInvokeSV")
public class ProdCmdInvokeSVImpl implements ProdCmdInvokeSV {

    @Resource
    private ProdCmdInvokeDAO prodCmdInvokeDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询产品_高级命令下发表 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<ProdCmdInvoke> queryProdCmdInvoke(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return prodCmdInvokeDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计产品_高级命令下发表数量
    * @param name 名称
    * @return 产品_高级命令下发表
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return prodCmdInvokeDAO.count(params);
    }


    /**
    * 新增产品_高级命令下发表
    *
    * @param prodCmdInvoke
    */
    @Override
    public void create(ProdCmdInvoke prodCmdInvoke) {
        prodCmdInvoke.setId(identifierSV.uniqueId());
        prodCmdInvoke.setCrtime(new Date());
        prodCmdInvoke.setUptime(new Date());
        prodCmdInvokeDAO.insert(prodCmdInvoke);
    }

    /**
    * 修改产品_高级命令下发表
    *
    * @param prodCmdInvoke
    */
    @Override
    public void update(ProdCmdInvoke prodCmdInvoke) {
        prodCmdInvoke.setUptime(new Date());
        prodCmdInvokeDAO.update(prodCmdInvoke);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        prodCmdInvokeDAO.delete(id);
    }

    /**
    * 加载产品_高级命令下发表
    *
    * @param id 主键
    * @return 产品_高级命令下发表
    */
    @Override
    public ProdCmdInvoke load(String id) {
        return prodCmdInvokeDAO.load(id);
    }

}
