package com.zzwdkj.mbr.service;

import com.zzwdkj.mbr.entity.MbrWalletChged;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.common.Std;
import com.zzwdkj.mbr.dao.MbrWalletChgedDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* mbr_wallet_chged, 会员钱包变更表SVImpl
*
* @author guoxianwei  2016-11-11 15:55:32
*/
@Service("mbrWalletChgedSV")
public class MbrWalletChgedSVImpl implements MbrWalletChgedSV {

    @Resource
    private MbrWalletChgedDAO mbrWalletChgedDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询会员钱包变更表 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<MbrWalletChged> queryMbrWalletChged(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return mbrWalletChgedDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计会员钱包变更表数量
    * @param name 名称
    * @return 会员钱包变更表
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return mbrWalletChgedDAO.count(params);
    }


    /**
    * 新增会员钱包变更表
    *
    * @param mbrWalletChged
    */
    @Override
    public void create(MbrWalletChged mbrWalletChged) {
        mbrWalletChged.setId(identifierSV.uniqueId());
        mbrWalletChged.setCrtime(new Date());
        mbrWalletChgedDAO.insert(mbrWalletChged);
    }

    /**
    * 修改会员钱包变更表
    *
    * @param mbrWalletChged
    */
    @Override
    public void update(MbrWalletChged mbrWalletChged) {
        mbrWalletChgedDAO.update(mbrWalletChged);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        mbrWalletChgedDAO.delete(id);
    }

    /**
    * 加载会员钱包变更表
    *
    * @param id 主键
    * @return 会员钱包变更表
    */
    @Override
    public MbrWalletChged load(String id) {
        return mbrWalletChgedDAO.load(id);
    }

}
