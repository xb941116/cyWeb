package com.zzwdkj.biz.service;

import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.biz.dao.BizWltDAO;
import com.zzwdkj.biz.entity.BizWlt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* plat_wlt, 商家_钱包SVImpl
*
* @author guoxianwei  2016-09-07 15:01:55
*/
@Service("BizWltSV")
public class BizWltSVImpl implements BizWltSV {

    @Resource
    private BizWltDAO bizWltDAO;
    @Resource
    private IdentifierSV identifierSV;


    /**
    * 加载商家_钱包
    *
    * @param id 主键
    * @return 商家_钱包
    */
    @Override
    public BizWlt load(String id) {
        return bizWltDAO.load(id);
    }

    /**
     * 加载商家钱包
     *
     * @param bizNo 商家编号
     * @return 商家_钱包
     */
    @Override
    public BizWlt loadByBizNo(String bizNo) {
        return bizWltDAO.queryUnique("loadBizWltByBizNo",bizNo);
    }
    /**
     * 通过商家编号获取 商家_钱包
     *
     * @param bizNo
     * @return
     */
    @Override
    public BizWlt loadByBizId(String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo",bizNo);
        List<BizWlt> bizWltList=bizWltDAO.query(params);
        if (bizWltList!=null&&bizWltList.get(0)!=null){
            return bizWltList.get(0);
        }else {
            return null;
        }
    }

}
