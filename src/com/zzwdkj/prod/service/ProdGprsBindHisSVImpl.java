package com.zzwdkj.prod.service;

import com.zzwdkj.prod.entity.ProdGprsBindHis;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.prod.dao.ProdGprsBindHisDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* prod_gprs_bind_his, 产品_GPRS绑定历史表SVImpl
*
* @author guoxianwei  2016-09-07 15:01:59
*/
@Service("prodGprsBindHisSV")
public class ProdGprsBindHisSVImpl implements ProdGprsBindHisSV {

    @Resource
    private ProdGprsBindHisDAO prodGprsBindHisDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询产品_GPRS绑定历史表 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<ProdGprsBindHis> queryProdGprsBindHis(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return prodGprsBindHisDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计产品_GPRS绑定历史表数量
    * @param name 名称
    * @return 产品_GPRS绑定历史表
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return prodGprsBindHisDAO.count(params);
    }


    /**
    * 新增产品_GPRS绑定历史表
    *
    * @param prodGprsBindHis
    */
    @Override
    public void create(ProdGprsBindHis prodGprsBindHis) {
        prodGprsBindHis.setId(identifierSV.uniqueId());
        prodGprsBindHis.setCrtime(new Date());
        prodGprsBindHis.setUptime(new Date());
        prodGprsBindHisDAO.insert(prodGprsBindHis);
    }

    /**
    * 修改产品_GPRS绑定历史表
    *
    * @param prodGprsBindHis
    */
    @Override
    public void update(ProdGprsBindHis prodGprsBindHis) {
        prodGprsBindHis.setUptime(new Date());
        prodGprsBindHisDAO.update(prodGprsBindHis);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        prodGprsBindHisDAO.delete(id);
    }

    /**
    * 加载产品_GPRS绑定历史表
    *
    * @param id 主键
    * @return 产品_GPRS绑定历史表
    */
    @Override
    public ProdGprsBindHis load(String id) {
        return prodGprsBindHisDAO.load(id);
    }

}
