package com.zzwdkj.mbr.service;

import com.zzwdkj.mbr.entity.MbrOauth;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.common.Std;
import com.zzwdkj.mbr.dao.MbrOauthDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* mbr_oauth, 第三方账号关联表SVImpl
*
* @author guoxianwei  2016-11-11 15:55:28
*/
@Service("mbrOauthSV")
public class MbrOauthSVImpl implements MbrOauthSV {

    @Resource
    private MbrOauthDAO mbrOauthDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询第三方账号关联表 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<MbrOauth> queryMbrOauth(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return mbrOauthDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计第三方账号关联表数量
    * @param name 名称
    * @return 第三方账号关联表
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return mbrOauthDAO.count(params);
    }


    /**
    * 新增第三方账号关联表
    *
    * @param mbrOauth
    */
    @Override
    public void create(MbrOauth mbrOauth) {
        mbrOauth.setId(identifierSV.uniqueId());
        mbrOauth.setCrtime(new Date());
        mbrOauth.setUptime(new Date());
        mbrOauthDAO.insert(mbrOauth);
    }

    /**
    * 修改第三方账号关联表
    *
    * @param mbrOauth
    */
    @Override
    public void update(MbrOauth mbrOauth) {
        mbrOauth.setUptime(new Date());
        mbrOauthDAO.update(mbrOauth);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        mbrOauthDAO.delete(id);
    }

    /**
    * 加载第三方账号关联表
    *
    * @param id 主键
    * @return 第三方账号关联表
    */
    @Override
    public MbrOauth load(String id) {
        return mbrOauthDAO.load(id);
    }

    /**
     * 通过openid查询第三方关联表
     * @param openid
     * @return
     */
    @Override
    public List<MbrOauth> queryMbrOauthByOpenid(String openid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("openid", openid);
        return mbrOauthDAO.query(params);
    }

    /**
     * 通过memberid查询第三方关联表
     *
     * @param memberId
     * @param state
     * @return
     */
    @Override
    public List<MbrOauth> queryMbrOauthByMbrid(String memberId, Integer state) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("memberId", memberId);
        params.put("state", state);
        return mbrOauthDAO.query(params);
    }

}
