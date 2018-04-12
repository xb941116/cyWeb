package com.zzwdkj.sys.service;

import com.zzwdkj.sys.entity.SysAcctRes;

import java.util.List;

/**
* sys_acct_res, 权限_账号权限SV
*
* @author guoxianwei  2016-09-07 15:02:12
*/

public interface SysAcctResSV {

    /**
    * 查询权限_账号权限 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<SysAcctRes> querySysAcctRes(String name, int rowStart, int rowSize);

    /**
    * 新增权限_账号权限
    *
    * @param sysAcctRes
    */
    void create(SysAcctRes sysAcctRes);

    /**
    * 修改权限_账号权限
    *
    * @param sysAcctRes
    */
    void update(SysAcctRes sysAcctRes);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计权限_账号权限数量
    * @param name 名称
    * @return 权限_账号权限数量
    */
    int count(String name);

    /**
    * 加载权限_账号权限
    *
    * @param id 主键
    * @return 权限_账号权限
    */
    SysAcctRes load(String id);


}
