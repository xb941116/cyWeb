package com.zzwdkj.biz.service;

import com.zzwdkj.biz.entity.BizWxFocus;

import java.util.List;

/**
* biz_wx_focus, 商家_微信_强制关注SV
*
* @author guoxianwei  2016-09-07 15:01:34
*/

public interface BizWxFocusSV {

    /**
    * 查询商家_微信_强制关注 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<BizWxFocus> queryBizWxFocus(String name, int rowStart, int rowSize);

    /**
    * 新增商家_微信_强制关注
    *
    * @param bizWxFocus
    */
    void create(BizWxFocus bizWxFocus);

    /**
    * 修改商家_微信_强制关注
    *
    * @param bizWxFocus
    */
    void update(BizWxFocus bizWxFocus);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计商家_微信_强制关注数量
    * @param name 名称
    * @return 商家_微信_强制关注数量
    */
    int count(String name);

    /**
    * 加载商家_微信_强制关注
    *
    * @param id 主键
    * @return 商家_微信_强制关注
    */
    BizWxFocus load(String id);


}
