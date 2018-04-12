package com.zzwdkj.gprs.service;

import com.zzwdkj.gprs.entity.GprsOtp;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.common.Std;
import com.zzwdkj.gprs.dao.GprsOtpDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* gprs_otp, gprs模块otp计数表SVImpl
*
* @author guoxianwei  2017-02-20 11:49:23
*/
@Service("gprsOtpSV")
public class GprsOtpSVImpl implements GprsOtpSV {

    @Resource
    private GprsOtpDAO gprsOtpDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询gprs模块otp计数表 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<GprsOtp> queryGprsOtp(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return gprsOtpDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计gprs模块otp计数表数量
    * @param name 名称
    * @return gprs模块otp计数表
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return gprsOtpDAO.count(params);
    }


    /**
    * 新增gprs模块otp计数表
    *
    * @param gprsOtp
    */
    @Override
    public void create(GprsOtp gprsOtp) {
        gprsOtp.setId(identifierSV.uniqueId());
        gprsOtp.setCrtime(new Date());
        gprsOtp.setUptime(new Date());
        gprsOtpDAO.insert(gprsOtp);
    }

    /**
    * 修改gprs模块otp计数表
    *
    * @param gprsOtp
    */
    @Override
    public void update(GprsOtp gprsOtp) {
        gprsOtp.setUptime(new Date());
        gprsOtpDAO.update(gprsOtp);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        gprsOtpDAO.delete(id);
    }

    /**
    * 加载gprs模块otp计数表
    *
    * @param id 主键
    * @return gprs模块otp计数表
    */
    @Override
    public GprsOtp load(String id) {
        return gprsOtpDAO.load(id);
    }

    /**
     * 模块otp次数+1
     *
     * @param gprsNo GPRS模块编号
     * @return
     */
    @Override
    public int updateMovingFactor(String gprsNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("gprsNo", gprsNo);
        return gprsOtpDAO.update("updateMovingFactor",params);
    }

    /**
     * 重置模块OTP次数
     *
     * @param gprsNo GPRS模块编号
     * @return
     */
    @Override
    public int updateReset(String gprsNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("gprsNo", gprsNo);
        return gprsOtpDAO.update("updateReset",params);
    }

    /**
     * 通过gprsNo获取模块otp计数表
     *
     * @param gprsNo
     * @return
     */
    @Override
    public GprsOtp loadByGprsNo(String gprsNo) {
        return gprsOtpDAO.queryUnique("loadByGprsNo",gprsNo);
    }

    /**
     * 修改otp次数
     *
     * @param gprsNo
     * @param movingFactor
     */
    @Override
    public int updateOtpMovingFactor(String gprsNo, Long movingFactor) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("gprsNo", gprsNo);
        params.put("movingFactor", movingFactor);
        return gprsOtpDAO.update("updateOtpMovingFactor",params);
    }

}
