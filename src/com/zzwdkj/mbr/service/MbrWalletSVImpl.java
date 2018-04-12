package com.zzwdkj.mbr.service;

import com.zzwdkj.mbr.entity.MbrWallet;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.common.Std;
import com.zzwdkj.mbr.dao.MbrWalletDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
* mbr_wallet, 会员钱包SVImpl
*
* @author guoxianwei  2016-11-11 15:55:31
*/
@Service("mbrWalletSV")
public class MbrWalletSVImpl implements MbrWalletSV {

    @Resource
    private MbrWalletDAO mbrWalletDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询会员钱包 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<MbrWallet> queryMbrWallet(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return mbrWalletDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计会员钱包数量
    * @param name 名称
    * @return 会员钱包
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return mbrWalletDAO.count(params);
    }


    /**
    * 新增会员钱包
    *
    * @param mbrWallet
    */
    @Override
    public void create(MbrWallet mbrWallet) {
        mbrWallet.setId(identifierSV.uniqueId());
        mbrWallet.setCrtime(new Date());
        mbrWallet.setUptime(new Date());
        mbrWalletDAO.insert(mbrWallet);
    }

    /**
    * 修改会员钱包
    *
    * @param mbrWallet
    */
    @Override
    public void update(MbrWallet mbrWallet) {
        mbrWallet.setUptime(new Date());
        mbrWalletDAO.update(mbrWallet);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        mbrWalletDAO.delete(id);
    }

    /**
    * 加载会员钱包
    *
    * @param id 主键
    * @return 会员钱包
    */
    @Override
    public MbrWallet load(String id) {
        return mbrWalletDAO.load(id);
    }

    /**
     * 通过会员ID获取钱包
     *
     * @param memberId
     * @return
     */
    @Override
    public MbrWallet loadByMemberId(String memberId) {
        return mbrWalletDAO.queryUnique("loadByMemberId",memberId);
    }

    /**
     * 会员消费
     *
     * @param memberId
     * @param money
     * @return
     */
    @Override
    public int updateMoneySub(String memberId, BigDecimal money) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("memberId", memberId);
        params.put("money", money);
        return mbrWalletDAO.update("updateMoneySub",params);
    }

}
