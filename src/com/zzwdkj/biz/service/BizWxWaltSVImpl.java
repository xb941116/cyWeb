package com.zzwdkj.biz.service;

import com.zzwdkj.biz.entity.BizWxWalt;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.biz.dao.BizWxWaltDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* biz_wx_walt, 商家_微信零钱包SVImpl
*
* @author guoxianwei  2016-09-07 15:01:34
*/
@Service("bizWxWaltSV")
public class BizWxWaltSVImpl implements BizWxWaltSV {

    @Resource
    private BizWxWaltDAO bizWxWaltDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询商家_微信零钱包 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<BizWxWalt> queryBizWxWalt(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return bizWxWaltDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计商家_微信零钱包数量
    * @param name 名称
    * @return 商家_微信零钱包
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return bizWxWaltDAO.count(params);
    }


    /**
    * 新增商家_微信零钱包
    *
    * @param bizWxWalt
    */
    @Override
    public void create(BizWxWalt bizWxWalt) {
        bizWxWalt.setId(identifierSV.uniqueId());
        bizWxWalt.setCrtime(new Date());
        bizWxWalt.setUptime(new Date());
        bizWxWaltDAO.insert(bizWxWalt);
    }

    /**
    * 修改商家_微信零钱包
    *
    * @param bizWxWalt
    */
    @Override
    public void update(BizWxWalt bizWxWalt) {
        bizWxWalt.setUptime(new Date());
        bizWxWaltDAO.update(bizWxWalt);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        bizWxWaltDAO.delete(id);
    }

    /**
    * 加载商家_微信零钱包
    *
    * @param id 主键
    * @return 商家_微信零钱包
    */
    @Override
    public BizWxWalt load(String id) {
        return bizWxWaltDAO.load(id);
    }

}
