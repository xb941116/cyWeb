package com.zzwdkj.biz.service;

import com.zzwdkj.biz.entity.BizTakeWx;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.common.Std;
import com.zzwdkj.biz.dao.BizTakeWxDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* biz_take_wx, 商家_提现_微信自动转账SVImpl
*
* @author guoxianwei  2016-11-15 09:27:26
*/
@Service("bizTakeWxSV")
public class BizTakeWxSVImpl implements BizTakeWxSV {

    @Resource
    private BizTakeWxDAO bizTakeWxDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询商家_提现_微信自动转账 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<BizTakeWx> queryBizTakeWx(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return bizTakeWxDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计商家_提现_微信自动转账数量
    * @param name 名称
    * @return 商家_提现_微信自动转账
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return bizTakeWxDAO.count(params);
    }


    /**
    * 新增商家_提现_微信自动转账
    *
    * @param bizTakeWx
    */
    @Override
    public void create(BizTakeWx bizTakeWx) {
        bizTakeWx.setId(identifierSV.uniqueId());
        bizTakeWx.setCrtime(new Date());
        bizTakeWx.setUptime(new Date());
        bizTakeWxDAO.insert(bizTakeWx);
    }

    /**
    * 修改商家_提现_微信自动转账
    *
    * @param bizTakeWx
    */
    @Override
    public void update(BizTakeWx bizTakeWx) {
        bizTakeWx.setUptime(new Date());
        bizTakeWxDAO.update(bizTakeWx);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        bizTakeWxDAO.delete(id);
    }

    /**
    * 加载商家_提现_微信自动转账
    *
    * @param id 主键
    * @return 商家_提现_微信自动转账
    */
    @Override
    public BizTakeWx load(String id) {
        return bizTakeWxDAO.load(id);
    }

    /**
     * 通过商家编号获取 商家_提现_微信自动转账
     *
     * @param bizNo
     * @return
     */
    @Override
    public BizTakeWx loadByBizNo(String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        return bizTakeWxDAO.queryUnique("loadByBizNo",params);
    }

}
