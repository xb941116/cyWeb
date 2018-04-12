package com.zzwdkj.sys.service;

import com.zzwdkj.sys.entity.SysAcct;

import java.util.List;
import java.util.Map;

/**
 * sys_acct, 权限_系统账号SV
 *
 * @author guoxianwei  2016-09-07 15:02:11
 */

public interface SysAcctSV {

    /**
     * 查询权限_系统账号 ，带分页
     *
     * @param params   参数
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 结果集
     */
    List<SysAcct> querySysAcct(Map<String, Object> params, int rowStart, int rowSize);

    /**
     * 新增权限_系统账号
     *
     * @param sysAcct
     */
    void createOrUpdateSysAcct(SysAcct sysAcct);


    /**
     * 重置权限
     *
     * @param sysAcct
     */
    void resetRight(SysAcct sysAcct);

    /**
     * 删除一条记录
     *
     * @param id
     */
    void removeSysAcct(String id);

    /**
     * 统计权限_系统账号数量
     *
     * @param params 参数
     * @return 权限_系统账号数量
     */
    int countSysAcct(Map<String, Object> params);

    /**
     * 加载权限_系统账号
     *
     * @param id 主键
     * @return 权限_系统账号
     */
    SysAcct loadSysAcct(String id);


    /**
     * 加载权限_系统账号
     *
     * @param acct 账号
     * @return 权限_系统账号
     */
    SysAcct loadSysAcctByAcct(String acct);


    /**
     * 加载权限_主系统账号
     *
     * @param bizNo 商家编号
     * @return 权限_系统账号
     */
    SysAcct loadMainSysAcct(String bizNo);

    /**
     * 密码修改
     *
     * @param bizNo  商家编号
     * @param acct   账户
     * @param oldPwd 老密码
     * @param newPwd 新密码
     */
    void upPwd(String bizNo, String acct, String oldPwd, String newPwd);

    /**
     * 更新头像
     *
     * @param acct   账户
     * @param headImg 头像
     */
    void upHeadImg(String acct, String headImg);


}
