package com.zzwdkj.biz.service;

import com.zzwdkj.biz.entity.BizTakeWwlt;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.biz.dao.BizTakeWwltDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* biz_take_wwlt, 商家_提现_微信零钱包_记录SVImpl
*
* @author guoxianwei  2016-09-07 15:01:33
*/
@Service("bizTakeWwltSV")
public class BizTakeWwltSVImpl implements BizTakeWwltSV {

    @Resource
    private BizTakeWwltDAO bizTakeWwltDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询商家_提现_微信零钱包_记录 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<BizTakeWwlt> queryBizTakeWwlt(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return bizTakeWwltDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计商家_提现_微信零钱包_记录数量
    * @param name 名称
    * @return 商家_提现_微信零钱包_记录
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return bizTakeWwltDAO.count(params);
    }


    /**
    * 新增商家_提现_微信零钱包_记录
    *
    * @param bizTakeWwlt
    */
    @Override
    public void create(BizTakeWwlt bizTakeWwlt) {
        bizTakeWwlt.setId(identifierSV.uniqueId());
        bizTakeWwlt.setCrtime(new Date());
        bizTakeWwlt.setUptime(new Date());
        bizTakeWwltDAO.insert(bizTakeWwlt);
    }

    /**
    * 修改商家_提现_微信零钱包_记录
    *
    * @param bizTakeWwlt
    */
    @Override
    public void update(BizTakeWwlt bizTakeWwlt) {
        bizTakeWwlt.setUptime(new Date());
        bizTakeWwltDAO.update(bizTakeWwlt);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        bizTakeWwltDAO.delete(id);
    }

    /**
    * 加载商家_提现_微信零钱包_记录
    *
    * @param id 主键
    * @return 商家_提现_微信零钱包_记录
    */
    @Override
    public BizTakeWwlt load(String id) {
        return bizTakeWwltDAO.load(id);
    }

    /**
     * 获取商家_提现_微信零钱包_记录 通过申请单号
     *
     * @param takeNo 申请单号
     * @return
     */
    @Override
    public BizTakeWwlt loadByReqno(String takeNo) {
        if (takeNo==null||takeNo.equals("")){
            return null;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("takeNo",takeNo);
        List<BizTakeWwlt> bizTakeWwltList=bizTakeWwltDAO.query(params);
        if (bizTakeWwltList!=null&&bizTakeWwltList.size()==1){
            return bizTakeWwltList.get(0);
        }else {
            return null;
        }
    }

}
