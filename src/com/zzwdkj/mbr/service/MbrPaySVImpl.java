package com.zzwdkj.mbr.service;

import com.zzwdkj.mbr.entity.MbrPay;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.mbr.dao.MbrPayDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* mbr_pay, 客户_支付记录表SVImpl
*
* @author guoxianwei  2016-09-07 15:01:43
*/
@Service("mbrPaySV")
public class MbrPaySVImpl implements MbrPaySV {

    @Resource
    private MbrPayDAO mbrPayDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询客户_支付记录表 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<MbrPay> queryMbrPay(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return mbrPayDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计客户_支付记录表数量
    * @param name 名称
    * @return 客户_支付记录表
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return mbrPayDAO.count(params);
    }


    /**
    * 新增客户_支付记录表
    *
    * @param mbrPay
    */
    @Override
    public void create(MbrPay mbrPay) {
        mbrPay.setId(identifierSV.uniqueId());
        mbrPay.setCrtime(new Date());
        mbrPayDAO.insert(mbrPay);
    }

    /**
    * 修改客户_支付记录表
    *
    * @param mbrPay
    */
    @Override
    public void update(MbrPay mbrPay) {
        mbrPayDAO.update(mbrPay);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        mbrPayDAO.delete(id);
    }

    /**
    * 加载客户_支付记录表
    *
    * @param id 主键
    * @return 客户_支付记录表
    */
    @Override
    public MbrPay load(String id) {
        return mbrPayDAO.load(id);
    }

}
