package com.zzwdkj.mbr.service;

import com.zzwdkj.common.Std;
import com.zzwdkj.mbr.entity.MbrOauth;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
* mbr_oauth, 第三方账号关联表SV
*
* @author guoxianwei  2016-11-11 15:55:28
*/

public interface MbrOauthSV {

    /**
    * 查询第三方账号关联表 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<MbrOauth> queryMbrOauth(String name, int rowStart, int rowSize);

    /**
    * 新增第三方账号关联表
    *
    * @param mbrOauth
    */
    void create(MbrOauth mbrOauth);

    /**
    * 修改第三方账号关联表
    *
    * @param mbrOauth
    */
    void update(MbrOauth mbrOauth);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计第三方账号关联表数量
    * @param name 名称
    * @return 第三方账号关联表数量
    */
    int count(String name);

    /**
    * 加载第三方账号关联表
    *
    * @param id 主键
    * @return 第三方账号关联表
    */
    MbrOauth load(String id);

    /**
     * 通过openid查询第三方关联表
     * @param openid
     * @return
     */

    List<MbrOauth> queryMbrOauthByOpenid(String openid);

    /**
     *通过memberid查询第三方关联表
     * @param memberId
     * @param state
     * @return
     */
    List<MbrOauth> queryMbrOauthByMbrid(String memberId,Integer state);
}
