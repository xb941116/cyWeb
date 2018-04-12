package com.zzwdkj.mbr.service;

import com.zzwdkj.mbr.entity.MbrPrizen;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.common.Std;
import com.zzwdkj.mbr.dao.MbrPrizenDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* mbr_prizen, 会员奖励记录表SVImpl
*
* @author guoxianwei  2016-11-11 15:55:30
*/
@Service("mbrPrizenSV")
public class MbrPrizenSVImpl implements MbrPrizenSV {

    @Resource
    private MbrPrizenDAO mbrPrizenDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询会员奖励记录表 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<MbrPrizen> queryMbrPrizen(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return mbrPrizenDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计会员奖励记录表数量
    * @param name 名称
    * @return 会员奖励记录表
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return mbrPrizenDAO.count(params);
    }


    /**
    * 新增会员奖励记录表
    *
    * @param mbrPrizen
    */
    @Override
    public void create(MbrPrizen mbrPrizen) {
        mbrPrizen.setId(identifierSV.uniqueId());
        mbrPrizen.setCrtime(new Date());
        mbrPrizenDAO.insert(mbrPrizen);
    }

    /**
    * 修改会员奖励记录表
    *
    * @param mbrPrizen
    */
    @Override
    public void update(MbrPrizen mbrPrizen) {
        mbrPrizenDAO.update(mbrPrizen);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        mbrPrizenDAO.delete(id);
    }

    /**
    * 加载会员奖励记录表
    *
    * @param id 主键
    * @return 会员奖励记录表
    */
    @Override
    public MbrPrizen load(String id) {
        return mbrPrizenDAO.load(id);
    }

}
