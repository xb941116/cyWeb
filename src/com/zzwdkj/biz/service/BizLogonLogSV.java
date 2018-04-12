package com.zzwdkj.biz.service;

import com.zzwdkj.biz.entity.BizLogonLog;

import java.util.List;

/**
* biz_logon_log, 商家_登录_日志SV
*
* @author guoxianwei  2016-09-07 15:01:29
*/

public interface BizLogonLogSV {

    /**
    * 查询商家_登录_日志 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<BizLogonLog> queryBizLogonLog(String name, int rowStart, int rowSize);

    /**
    * 新增商家_登录_日志
    *
    * @param bizLogonLog
    */
    void create(BizLogonLog bizLogonLog);

    /**
    * 修改商家_登录_日志
    *
    * @param bizLogonLog
    */
    void update(BizLogonLog bizLogonLog);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计商家_登录_日志数量
    * @param name 名称
    * @return 商家_登录_日志数量
    */
    int count(String name);

    /**
    * 加载商家_登录_日志
    *
    * @param id 主键
    * @return 商家_登录_日志
    */
    BizLogonLog load(String id);


}
