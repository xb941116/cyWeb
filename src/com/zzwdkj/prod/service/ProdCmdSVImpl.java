package com.zzwdkj.prod.service;

import com.zzwdkj.prod.entity.ProdCmd;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.prod.dao.ProdCmdDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* prod_cmd, 产品_常用高级命令表SVImpl
*
* @author guoxianwei  2016-09-07 15:01:56
*/
@Service("prodCmdSV")
public class ProdCmdSVImpl implements ProdCmdSV {

    @Resource
    private ProdCmdDAO prodCmdDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询产品_常用高级命令表 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<ProdCmd> queryProdCmd(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return prodCmdDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计产品_常用高级命令表数量
    * @param name 名称
    * @return 产品_常用高级命令表
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return prodCmdDAO.count(params);
    }


    /**
    * 新增产品_常用高级命令表
    *
    * @param prodCmd
    */
    @Override
    public void create(ProdCmd prodCmd) {
        prodCmd.setId(identifierSV.uniqueId());
        prodCmd.setCrtime(new Date());
        prodCmd.setUptime(new Date());
        prodCmdDAO.insert(prodCmd);
    }

    /**
    * 修改产品_常用高级命令表
    *
    * @param prodCmd
    */
    @Override
    public void update(ProdCmd prodCmd) {
        prodCmd.setUptime(new Date());
        prodCmdDAO.update(prodCmd);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        prodCmdDAO.delete(id);
    }

    /**
    * 加载产品_常用高级命令表
    *
    * @param id 主键
    * @return 产品_常用高级命令表
    */
    @Override
    public ProdCmd load(String id) {
        return prodCmdDAO.load(id);
    }

}
