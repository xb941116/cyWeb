package com.zzwdkj.gprs.service;

import com.zzwdkj.common.Std;
import com.zzwdkj.gprs.entity.GprsOtp;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
* gprs_otp, gprs模块otp计数表SV
*
* @author guoxianwei  2017-02-20 11:49:23
*/

public interface GprsOtpSV {

    /**
    * 查询gprs模块otp计数表 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<GprsOtp> queryGprsOtp(String name, int rowStart, int rowSize);

    /**
    * 新增gprs模块otp计数表
    *
    * @param gprsOtp
    */
    void create(GprsOtp gprsOtp);

    /**
    * 修改gprs模块otp计数表
    *
    * @param gprsOtp
    */
    void update(GprsOtp gprsOtp);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计gprs模块otp计数表数量
    * @param name 名称
    * @return gprs模块otp计数表数量
    */
    int count(String name);

    /**
    * 加载gprs模块otp计数表
    *
    * @param id 主键
    * @return gprs模块otp计数表
    */
    GprsOtp load(String id);

    /**
     * 模块otp次数+1
     * @param gprsNo GPRS模块编号
     * @return
     */
    int updateMovingFactor(String gprsNo);

    /**
     * 重置模块OTP次数
     * @param gprsNo GPRS模块编号
     * @return
     */
    int updateReset(String gprsNo);

    /**
     * 通过gprsNo获取模块otp计数表
     * @param gprsNo
     * @return
     */
    GprsOtp loadByGprsNo(String gprsNo);


    /**
     * 修改otp次数
     * @param gprsNo
     * @param movingFactor
     */
    int updateOtpMovingFactor(String gprsNo, Long movingFactor);
}
