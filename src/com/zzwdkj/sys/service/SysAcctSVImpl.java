package com.zzwdkj.sys.service;

import com.zzwdkj.sys.dao.SysAcctResDAO;
import com.zzwdkj.sys.dao.SysResDAO;
import com.zzwdkj.sys.entity.SysAcct;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.sys.dao.SysAcctDAO;
import com.zzwdkj.sys.entity.SysAcctRes;
import com.zzwdkj.sys.entity.SysRes;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * sys_acct, 权限_系统账号SVImpl
 *
 * @author guoxianwei  2016-09-07 15:02:11
 */
@Service("sysAcctSV")
public class SysAcctSVImpl implements SysAcctSV {

    @Resource
    private SysAcctDAO sysAcctDAO;
    @Resource
    private SysResDAO sysResDAO;
    @Resource
    private SysAcctResDAO sysAcctResDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
     * 查询权限_系统账号 ，带分页
     *
     * @param params   参数
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 菜单资源
     */
    @Override
    public List<SysAcct> querySysAcct(Map<String, Object> params, int rowStart, int rowSize) {
        return sysAcctDAO.query(params, rowStart, rowSize);
    }

    /**
     * 统计权限_系统账号数量
     *
     * @param params 参数
     * @return 权限_系统账号
     */
    @Override
    public int countSysAcct(Map<String, Object> params) {
        return sysAcctDAO.count(params);
    }


    /**
     * 新增权限_系统账号
     *
     * @param sysAcct
     */
    @Override
    public void createOrUpdateSysAcct(SysAcct sysAcct) {
        if (StringUtils.isNotEmpty(sysAcct.getId())) {
            sysAcct.setUptime(new Date());
            sysAcctDAO.update(sysAcct);
            sysAcctResDAO.delete("deleteSysAcctRes", sysAcct.getAcct());
        } else {
            sysAcct.setId(identifierSV.uniqueId());
            sysAcct.setState(1);
            sysAcct.setCrtime(new Date());
            sysAcct.setUptime(new Date());
            sysAcctDAO.insert(sysAcct);
        }
        if (StringUtils.isNotEmpty(sysAcct.getRes())) {
            String res = sysAcct.getRes();
            String[] resArray = res.split("@");
            for (String sysRes : resArray) {
                if (StringUtils.isNotEmpty(sysRes)) {
                    SysAcctRes sysAcctRes = new SysAcctRes(sysAcct.getBizNo(), sysAcct.getAcct(), sysRes);
                    sysAcctRes.setId(identifierSV.uniqueId());
                    sysAcctResDAO.insert(sysAcctRes);
                }
            }
        }

    }

    /**
     * 重置权限
     *
     * @param sysAcct
     */
    @Override
    public void resetRight(SysAcct sysAcct) {
        if (StringUtils.isNotEmpty(sysAcct.getId())) {
            sysAcctResDAO.delete("deleteSysAcctRes", sysAcct.getAcct());
        }
        if (StringUtils.isNotEmpty(sysAcct.getRes())) {
            String res = sysAcct.getRes();
            String[] resArray = res.split("@");
            for (String sysRes : resArray) {
                if (StringUtils.isNotEmpty(sysRes)) {
                    SysAcctRes sysAcctRes = new SysAcctRes(sysAcct.getBizNo(), sysAcct.getAcct(), sysRes);
                    sysAcctRes.setId(identifierSV.uniqueId());
                    sysAcctResDAO.insert(sysAcctRes);
                }
            }
        }
    }

    /**
     * 删除一条记录
     *
     * @param id
     */
    @Override
    public void removeSysAcct(String id) {
        sysAcctDAO.delete(id);
    }

    /**
     * 加载权限_系统账号
     *
     * @param id 主键
     * @return 权限_系统账号
     */
    @Override
    public SysAcct loadSysAcct(String id) {
        return sysAcctDAO.load(id);
    }

    /**
     * 加载权限_系统账号
     *
     * @param acct 账号
     * @return 权限_系统账号
     */
    @Override
    public SysAcct loadSysAcctByAcct(String acct) {
        return sysAcctDAO.queryUnique("loadSysAcctByAcct", acct);
    }

    /**
     * 加载权限_主系统账号
     *
     * @param bizNo 商家编号
     * @return 权限_系统账号
     */
    @Override
    public SysAcct loadMainSysAcct(String bizNo) {
        return sysAcctDAO.queryUnique("loadMainSysAcct", bizNo);
    }

    /**
     * 密码修改
     *
     * @param bizNo  商家编号
     * @param acct   账户
     * @param oldPwd 老密码
     * @param newPwd 新密码
     */
    @Override
    public void upPwd(String bizNo, String acct, String oldPwd, String newPwd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        params.put("acct", acct);
        params.put("oldPwd", oldPwd);
        params.put("newPwd", newPwd);
        sysAcctDAO.update("updateSysAcctPwd", params);
    }

    /**
     * 更新头像
     *
     * @param acct    账户
     * @param headImg 头像
     */
    @Override
    public void upHeadImg(String acct, String headImg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("acct", acct);
        params.put("headImg", headImg);
        sysAcctDAO.update("updateSysAcctHeadImg", params);
    }
}
