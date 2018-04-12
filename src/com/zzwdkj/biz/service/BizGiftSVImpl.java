package com.zzwdkj.biz.service;

import com.zzwdkj.biz.entity.BizGift;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.common.Std;
import com.zzwdkj.biz.dao.BizGiftDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* biz_gift, 满赠活动SVImpl
*
* @author guoxianwei  2016-11-11 17:52:57
*/
@Service("bizGiftSV")
public class BizGiftSVImpl implements BizGiftSV {

    @Resource
    private BizGiftDAO bizGiftDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询满赠活动 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<BizGift> queryBizGift(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return bizGiftDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计满赠活动数量
    * @param name 名称
    * @return 满赠活动
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return bizGiftDAO.count(params);
    }


    /**
    * 新增满赠活动
    *
    * @param bizGift
    */
    @Override
    public void create(BizGift bizGift) {
        bizGift.setId(identifierSV.uniqueId());
        Date date=new Date();
        bizGift.setStartDate(date);
        bizGift.setCrtime(date);
        bizGift.setUptime(date);
        bizGiftDAO.insert(bizGift);
    }

    /**
    * 修改满赠活动
    *
    * @param bizGift
    */
    @Override
    public void update(BizGift bizGift) {
        bizGift.setUptime(new Date());
        bizGiftDAO.update(bizGift);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        bizGiftDAO.delete(id);
    }

    /**
    * 加载满赠活动
    *
    * @param id 主键
    * @return 满赠活动
    */
    @Override
    public BizGift load(String id) {
        return bizGiftDAO.load(id);
    }

    /**
     * 查询商家的活动
     *
     * @param bizNo
     * @param  state
     */
    @Override
    public List<BizGift>  queryBizGiftListByBizNo(String bizNo,Integer state) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        params.put("state", state);
        if (bizNo==null||bizNo.equals("")){
            return null;
        }
        return bizGiftDAO.query("queryOrderBySort",params);
    }

    /**
     * 查询充值的钱数是否达到商家的活动
     *
     * @param bizNo
     * @param coin
     * @return
     */
    @Override
    public List<BizGift> queryByCoinAndBizNo(String bizNo, Integer coin) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        params.put("coin", coin);
        return bizGiftDAO.query("queryByCoinAndBizNo",params);
    }

    /**
     * 查询(rowStart或者pageSize为-1则全查)
     *
     * @param params
     * @param rowStart
     * @param pageSize
     * @return
     */
    @Override
    public List<BizGift> queryBizGiftByMap(Map<String, Object> params, int rowStart, int pageSize) {
        if(rowStart==-1||pageSize==-1){
            return bizGiftDAO.query(params);
        }else {
            return bizGiftDAO.query(params,rowStart,pageSize);
        }

    }

    /**
     * 根据条件查询个数
     *
     * @param params
     * @return
     */
    @Override
    public int countBizGiftByMap(Map<String, Object> params) {
        return bizGiftDAO.count(params);
    }

    /**
     * 获取某个会员参加某个活动的次数
     *
     * @param memberId 会员ID
     * @param giftId   活动ID
     * @return
     */
    @Override
    public Integer queryBizGiftTotalByMemberId(String memberId, String giftId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("memberId",memberId);
        params.put("giftId",giftId);
        return bizGiftDAO.count("queryBizGiftTotalByMemberId",params);
    }

    /**
     * 根据活动ID查询该活动已经参加的人数
     *
     * @param giftId 活动ID
     * @return
     */
    @Override
    public Integer queryBizGiftTotalByALL(String giftId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("giftId",giftId);
        return bizGiftDAO.count("queryBizGiftTotalByALL",params);
    }

    /**
     * 更新活动总库存
     * @param id  活动ID
     * @return
     */
    @Override
    public int updateStockAll(String id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id",id);
        return  bizGiftDAO.update("updateStockAll",params);
    }

}
