package com.zzwdkj.mbr.service;

import com.zzwdkj.mbr.entity.MbrPay;

import java.util.List;

/**
* mbr_pay, 客户_支付记录表SV
*
* @author guoxianwei  2016-09-07 15:01:43
*/

public interface MbrPaySV {

    /**
    * 查询客户_支付记录表 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<MbrPay> queryMbrPay(String name, int rowStart, int rowSize);

    /**
    * 新增客户_支付记录表
    *
    * @param mbrPay
    */
    void create(MbrPay mbrPay);

    /**
    * 修改客户_支付记录表
    *
    * @param mbrPay
    */
    void update(MbrPay mbrPay);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计客户_支付记录表数量
    * @param name 名称
    * @return 客户_支付记录表数量
    */
    int count(String name);

    /**
    * 加载客户_支付记录表
    *
    * @param id 主键
    * @return 客户_支付记录表
    */
    MbrPay load(String id);


}
