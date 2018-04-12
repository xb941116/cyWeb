package com.zzwdkj.sys.service;

import com.zzwdkj.sys.entity.SysRes;

import java.util.List;

/**
 * sys_res, 权限_系统资源SV
 *
 * @author guoxianwei  2016-09-07 15:02:12
 */

public interface SysResSV {

    /**
     * 查询权限_系统资源 ，带分页
     *
     * @param name     名称
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 结果集
     */
    List<SysRes> querySysRes(String name, int rowStart, int rowSize);

    /**
     * 新增权限_系统资源
     *
     * @param sysRes
     */
    void create(SysRes sysRes);

    /**
     * 修改权限_系统资源
     *
     * @param sysRes
     */
    void update(SysRes sysRes);

    /**
     * 删除一条记录
     *
     * @param id
     */
    void remove(String id);

    /**
     * 统计权限_系统资源数量
     *
     * @param name 名称
     * @return 权限_系统资源数量
     */
    int count(String name);

    /**
     * 加载权限_系统资源
     *
     * @param id 主键
     * @return 权限_系统资源
     */
    SysRes load(String id);

    /**
     * 查询用户资源
     *
     * @param acct 账号
     * @return 资源
     */
    List<SysRes> querySysResByAcct(String acct);

    /**
     * 查询用户资源
     *
     * @param acct 账号
     * @param parSysCode 父资源Code
     * @return 资源
     */
    List<SysRes> querySysRes(String acct,String parSysCode);
}
