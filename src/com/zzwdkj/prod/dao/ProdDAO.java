package com.zzwdkj.prod.dao;

import org.springframework.stereotype.Repository;
import com.hckj.framework.ext.mybatis.dao.BaseMybatisDAO;
import com.zzwdkj.prod.entity.Prod;

/**
* prod, 产品表
*
* @author guoxianwei 2016-09-07 15:01:56
*/
@Repository
public class ProdDAO extends BaseMybatisDAO<Prod, String> {
}