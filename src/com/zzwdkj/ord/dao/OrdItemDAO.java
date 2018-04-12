package com.zzwdkj.ord.dao;

import org.springframework.stereotype.Repository;
import com.hckj.framework.ext.mybatis.dao.BaseMybatisDAO;
import com.zzwdkj.ord.entity.OrdItem;

/**
* ordItem, 订单_客户_订单商品项
*
* @author guoxianwei 2016-09-07 15:01:45
*/
@Repository
public class OrdItemDAO extends BaseMybatisDAO<OrdItem, String> {
}