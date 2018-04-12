package com.zzwdkj.gprs.service;

import com.zzwdkj.gprs.entity.GprsModel;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.gprs.dao.GprsModelDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * gprs_model, GPRS_模块表SVImpl
 *
 * @author guoxianwei  2016-09-07 15:01:38
 */
@Service("gprsModelSV")
public class GprsModelSVImpl implements GprsModelSV {

    @Resource
    private GprsModelDAO gprsModelDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
     * 查询GPRS_模块表 ，带分页
     *
     * @param name     菜单名称
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 菜单资源
     */
    @Override
    public List<GprsModel> queryGprsModel(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return gprsModelDAO.query(params, rowStart, rowSize);
    }

    /**
     * 查询GPRS_模块表 ，带分页
     *
     * @param params   参数
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 结果集
     */
    @Override
    public List<GprsModel> queryGprsModel(Map<String, Object> params, int rowStart, int rowSize) {
        if (rowStart==-1||rowSize==-1){
            return gprsModelDAO.query("queryGprsModel",params);
        }else {
            return gprsModelDAO.query("queryGprsModel",params, rowStart, rowSize);
        }
    }

    /**
     * 统计GPRS_模块表数量
     *
     * @param name 名称
     * @return GPRS_模块表
     */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return gprsModelDAO.count(params);
    }

    /**
     * 统计GPRS_模块表数量
     *
     * @param params 参数
     * @return GPRS_模块表数量
     */
    @Override
    public int count(Map<String, Object> params) {
        return gprsModelDAO.count("countGprsModel",params);
    }

    /**
     * 新增GPRS_模块表
     *
     * @param gprsModel
     */
    @Override
    public void create(GprsModel gprsModel) {
        gprsModel.setId(identifierSV.uniqueId());
        gprsModel.setCrtime(new Date());
        gprsModel.setUptime(new Date());
        gprsModel.setBind(0);
        gprsModel.setState(0);
        gprsModelDAO.insert(gprsModel);
    }

    /**
     * 修改GPRS_模块表
     *
     * @param gprsModel
     */
    @Override
    public void update(GprsModel gprsModel) {
        gprsModel.setUptime(new Date());
        gprsModelDAO.update(gprsModel);
    }

    /**
     * 删除一条记录
     *
     * @param id
     */
    @Override
    public void remove(String id) {
        gprsModelDAO.delete(id);
    }

    /**
     * 加载GPRS_模块表
     *
     * @param id 主键
     * @return GPRS_模块表
     */
    @Override
    public GprsModel load(String id) {
        return gprsModelDAO.load(id);
    }

    /**
     * 加载GPRS_模块表
     *
     * @param gprsNo 模块号
     * @return GPRS_模块表
     */
    @Override
    public GprsModel loadByGprsNo(String gprsNo) {
        return gprsModelDAO.queryUnique("queryGprsByGprsNo",gprsNo);
    }

    /**
     * @param bizNo    商家编号
     * @param instlaState 位置设置状态
     *@param rowStart 起始位置
     * @param pageSize 分页大小   @return
     */
    @Override
    public List<GprsModel> queryInstlGprsModelByBizNo(String bizNo, String instlaState, int rowStart, int pageSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        if (instlaState!=null&&!instlaState.equals("")){
            params.put("instlaState", instlaState);
        }
        if (rowStart==-1||pageSize==-1){
            return gprsModelDAO.query("queryInstlGprsModelByBizNo",params);
        }else {
            return gprsModelDAO.query("queryInstlGprsModelByBizNo",params, rowStart, pageSize);
        }
    }

    /**
     * 获取数据个数
     *
     * @param bizNo
     * @return
     */
    @Override
    public int countInstlGprsModelByBizNo(String bizNo, String instlaState) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        if (instlaState!=null&&!instlaState.equals("")){
            params.put("instlaState", instlaState);
        }
        return gprsModelDAO.count("countInstlGprsModelByBizNo",params);
    }

    /**
     * 查询GPRS_模块表 ，带分页
     *
     * @param params    参数
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 结果集
     */
    @Override
    public List<GprsModel> queryGprsModelForProdSet(Map<String, Object> params, int rowStart, int rowSize) {
        return gprsModelDAO.query("queryGprsModelForProdSet",params, rowStart, rowSize);
    }

    /**
     * 统计GPRS_模块表数量
     *
     * @param params 参数
     * @return GPRS_模块表数量
     */
    @Override
    public int countGprsModelForProdSet(Map<String, Object> params) {
        return gprsModelDAO.count("countGprsModelForProdSet",params);
    }
}
