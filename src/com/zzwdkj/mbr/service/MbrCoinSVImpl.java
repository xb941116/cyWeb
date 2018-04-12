package com.zzwdkj.mbr.service;

import com.zzwdkj.mbr.entity.MbrCoin;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.common.Std;
import com.zzwdkj.mbr.dao.MbrCoinDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
* mbr_coin, 会员积分表SVImpl
*
* @author guoxianwei  2016-11-11 15:55:27
*/
@Service("mbrCoinSV")
public class MbrCoinSVImpl implements MbrCoinSV {

    @Resource
    private MbrCoinDAO mbrCoinDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询会员积分表 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<MbrCoin> queryMbrCoin(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return mbrCoinDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计会员积分表数量
    * @param name 名称
    * @return 会员积分表
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return mbrCoinDAO.count(params);
    }


    /**
    * 新增会员积分表
    *
    * @param mbrCoin
    */
    @Override
    public void create(MbrCoin mbrCoin) {
        mbrCoin.setId(identifierSV.uniqueId());
        mbrCoin.setCrtime(new Date());
        mbrCoin.setUptime(new Date());
        mbrCoinDAO.insert(mbrCoin);
    }

    /**
    * 修改会员积分表
    *
    * @param mbrCoin
    */
    @Override
    public void update(MbrCoin mbrCoin) {
        mbrCoin.setUptime(new Date());
        mbrCoinDAO.update(mbrCoin);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        mbrCoinDAO.delete(id);
    }

    /**
    * 加载会员积分表
    *
    * @param id 主键
    * @return 会员积分表
    */
    @Override
    public MbrCoin load(String id) {
        return mbrCoinDAO.load(id);
    }

    /**
     * 通过用户id获取积分表
     *
     * @param memberId 会员id
     * @return
     */
    @Override
    public MbrCoin loadByMemberId(String memberId) {
        return mbrCoinDAO.queryUnique("loadByMemberId",memberId);
    }

    /**
     * 会员积分消费             （此处修改；时间：12.7）
     *
     * @param memberId
     * @param coin
     * @return
     *
     */

    @Override
    public int updateCoinSub(String memberId, BigDecimal coin) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("memberId", memberId);
        params.put("coin", coin);
        return mbrCoinDAO.update("updateCoinSub",params);
    }

}
