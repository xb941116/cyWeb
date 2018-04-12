package com.zzwdkj.mbr.service;

import com.zzwdkj.mbr.entity.MbrCoinChged;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.common.Std;
import com.zzwdkj.mbr.dao.MbrCoinChgedDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* mbr_coin_chged, 会员积分变更表SVImpl
*
* @author guoxianwei  2016-11-11 15:55:28
*/
@Service("mbrCoinChgedSV")
public class MbrCoinChgedSVImpl implements MbrCoinChgedSV {

    @Resource
    private MbrCoinChgedDAO mbrCoinChgedDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询会员积分变更表 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<MbrCoinChged> queryMbrCoinChged(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return mbrCoinChgedDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计会员积分变更表数量
    * @param name 名称
    * @return 会员积分变更表
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return mbrCoinChgedDAO.count(params);
    }


    /**
    * 新增会员积分变更表
    *
    * @param mbrCoinChged
    */
    @Override
    public void create(MbrCoinChged mbrCoinChged) {
        mbrCoinChged.setId(identifierSV.uniqueId());
        mbrCoinChged.setCrtime(new Date());
        mbrCoinChgedDAO.insert(mbrCoinChged);
    }

    /**
    * 修改会员积分变更表
    *
    * @param mbrCoinChged
    */
    @Override
    public void update(MbrCoinChged mbrCoinChged) {
        mbrCoinChgedDAO.update(mbrCoinChged);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        mbrCoinChgedDAO.delete(id);
    }

    /**
    * 加载会员积分变更表
    *
    * @param id 主键
    * @return 会员积分变更表
    */
    @Override
    public MbrCoinChged load(String id) {
        return mbrCoinChgedDAO.load(id);
    }

}
