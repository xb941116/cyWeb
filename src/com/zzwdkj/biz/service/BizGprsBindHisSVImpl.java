package com.zzwdkj.biz.service;

import com.zzwdkj.biz.entity.BizGprsBindHis;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.biz.dao.BizGprsBindHisDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* biz_gprs_bind_his, 商家_GPRS绑定历史表SVImpl
*
* @author guoxianwei  2016-09-07 15:01:29
*/
@Service("bizGprsBindHisSV")
public class BizGprsBindHisSVImpl implements BizGprsBindHisSV {

    @Resource
    private BizGprsBindHisDAO bizGprsBindHisDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询商家_GPRS绑定历史表 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<BizGprsBindHis> queryBizGprsBindHis(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return bizGprsBindHisDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计商家_GPRS绑定历史表数量
    * @param name 名称
    * @return 商家_GPRS绑定历史表
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return bizGprsBindHisDAO.count(params);
    }


    /**
    * 新增商家_GPRS绑定历史表
    *
    * @param bizGprsBindHis
    */
    @Override
    public void create(BizGprsBindHis bizGprsBindHis) {
        bizGprsBindHis.setId(identifierSV.uniqueId());
        bizGprsBindHis.setCrtime(new Date());
        bizGprsBindHis.setUptime(new Date());
        bizGprsBindHisDAO.insert(bizGprsBindHis);
    }

    /**
    * 修改商家_GPRS绑定历史表
    *
    * @param bizGprsBindHis
    */
    @Override
    public void update(BizGprsBindHis bizGprsBindHis) {
        bizGprsBindHis.setUptime(new Date());
        bizGprsBindHisDAO.update(bizGprsBindHis);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        bizGprsBindHisDAO.delete(id);
    }

    /**
    * 加载商家_GPRS绑定历史表
    *
    * @param id 主键
    * @return 商家_GPRS绑定历史表
    */
    @Override
    public BizGprsBindHis load(String id) {
        return bizGprsBindHisDAO.load(id);
    }

}
