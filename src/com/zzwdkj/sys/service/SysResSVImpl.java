package com.zzwdkj.sys.service;

import com.zzwdkj.sys.entity.SysRes;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.sys.dao.SysResDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * sys_res, 权限_系统资源SVImpl
 *
 * @author guoxianwei  2016-09-07 15:02:12
 */
@Service("sysResSV")
public class SysResSVImpl implements SysResSV {

    @Resource
    private SysResDAO sysResDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
     * 查询权限_系统资源 ，带分页
     *
     * @param name     菜单名称
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 菜单资源
     */
    @Override
    public List<SysRes> querySysRes(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return sysResDAO.query(params, rowStart, rowSize);
    }

    /**
     * 统计权限_系统资源数量
     *
     * @param name 名称
     * @return 权限_系统资源
     */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return sysResDAO.count(params);
    }


    /**
     * 新增权限_系统资源
     *
     * @param sysRes
     */
    @Override
    public void create(SysRes sysRes) {
        sysRes.setId(identifierSV.uniqueId());
        sysRes.setCrtime(new Date());
        sysRes.setUptime(new Date());
        sysResDAO.insert(sysRes);
    }

    /**
     * 修改权限_系统资源
     *
     * @param sysRes
     */
    @Override
    public void update(SysRes sysRes) {
        sysRes.setUptime(new Date());
        sysResDAO.update(sysRes);
    }

    /**
     * 删除一条记录
     *
     * @param id
     */
    @Override
    public void remove(String id) {
        sysResDAO.delete(id);
    }

    /**
     * 加载权限_系统资源
     *
     * @param id 主键
     * @return 权限_系统资源
     */
    @Override
    public SysRes load(String id) {
        return sysResDAO.load(id);
    }

    /**
     * 查询用户资源
     *
     * @param acct 账号
     * @return 资源
     */
    @Override
    public List<SysRes> querySysResByAcct(String acct) {
        return sysResDAO.query("querySysResByAcct", acct);
    }

    /**
     * 查询用户资源
     *
     * @param acct       账号
     * @param parSysCode 父资源Code
     * @return 资源
     */
    @Override
    public List<SysRes> querySysRes(String acct, String parSysCode) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("acct", acct);
        params.put("parSysCode", parSysCode);
        return sysResDAO.query("querySysRes", params);
    }
}
