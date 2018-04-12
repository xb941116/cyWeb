package com.zzwdkj.biz.service;

import com.zzwdkj.biz.entity.BizLogonLog;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.biz.dao.BizLogonLogDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* biz_logon_log, 商家_登录_日志SVImpl
*
* @author guoxianwei  2016-09-07 15:01:29
*/
@Service("bizLogonLogSV")
public class BizLogonLogSVImpl implements BizLogonLogSV {

    @Resource
    private BizLogonLogDAO bizLogonLogDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询商家_登录_日志 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<BizLogonLog> queryBizLogonLog(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return bizLogonLogDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计商家_登录_日志数量
    * @param name 名称
    * @return 商家_登录_日志
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return bizLogonLogDAO.count(params);
    }


    /**
    * 新增商家_登录_日志
    *
    * @param bizLogonLog
    */
    @Override
    public void create(BizLogonLog bizLogonLog) {
        bizLogonLog.setId(identifierSV.uniqueId());
        bizLogonLog.setCrtime(new Date());
        bizLogonLog.setUptime(new Date());
        bizLogonLogDAO.insert(bizLogonLog);
    }

    /**
    * 修改商家_登录_日志
    *
    * @param bizLogonLog
    */
    @Override
    public void update(BizLogonLog bizLogonLog) {
        bizLogonLog.setUptime(new Date());
        bizLogonLogDAO.update(bizLogonLog);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        bizLogonLogDAO.delete(id);
    }

    /**
    * 加载商家_登录_日志
    *
    * @param id 主键
    * @return 商家_登录_日志
    */
    @Override
    public BizLogonLog load(String id) {
        return bizLogonLogDAO.load(id);
    }

}
