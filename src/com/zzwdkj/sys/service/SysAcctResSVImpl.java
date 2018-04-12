package com.zzwdkj.sys.service;

import com.zzwdkj.sys.entity.SysAcctRes;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.sys.dao.SysAcctResDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* sys_acct_res, 权限_账号权限SVImpl
*
* @author guoxianwei  2016-09-07 15:02:12
*/
@Service("sysAcctResSV")
public class SysAcctResSVImpl implements SysAcctResSV {

    @Resource
    private SysAcctResDAO sysAcctResDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询权限_账号权限 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<SysAcctRes> querySysAcctRes(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return sysAcctResDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计权限_账号权限数量
    * @param name 名称
    * @return 权限_账号权限
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return sysAcctResDAO.count(params);
    }


    /**
    * 新增权限_账号权限
    *
    * @param sysAcctRes
    */
    @Override
    public void create(SysAcctRes sysAcctRes) {
        sysAcctRes.setId(identifierSV.uniqueId());
        sysAcctRes.setCrtime(new Date());
        sysAcctRes.setUptime(new Date());
        sysAcctResDAO.insert(sysAcctRes);
    }

    /**
    * 修改权限_账号权限
    *
    * @param sysAcctRes
    */
    @Override
    public void update(SysAcctRes sysAcctRes) {
        sysAcctRes.setUptime(new Date());
        sysAcctResDAO.update(sysAcctRes);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        sysAcctResDAO.delete(id);
    }

    /**
    * 加载权限_账号权限
    *
    * @param id 主键
    * @return 权限_账号权限
    */
    @Override
    public SysAcctRes load(String id) {
        return sysAcctResDAO.load(id);
    }

}
