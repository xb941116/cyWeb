package com.zzwdkj.prod.service;

import com.zzwdkj.prod.entity.ProdInstlPosModel;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.common.Std;
import com.zzwdkj.prod.dao.ProdInstlPosModelDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* prod_instl_pos_model, 产品_安装位置模板表SVImpl
*
* @author guoxianwei  2017-01-17 17:06:22
*/
@Service("prodInstlPosModelSV")
public class ProdInstlPosModelSVImpl implements ProdInstlPosModelSV {

    @Resource
    private ProdInstlPosModelDAO prodInstlPosModelDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询产品_安装位置模板表 ，带分页
    *
    * @param bizNo     商家编号
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<ProdInstlPosModel> queryProdInstlPosModel(String bizNo, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        if (rowStart==-1||rowSize==-1){
            return prodInstlPosModelDAO.query(params);
        }else {
            return prodInstlPosModelDAO.query(params, rowStart, rowSize);
        }

    }

    /**
    * 统计产品_安装位置模板表数量
    * @param name 名称
    * @return 产品_安装位置模板表
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return prodInstlPosModelDAO.count(params);
    }


    /**
    * 新增产品_安装位置模板表
    *
    * @param prodInstlPosModel
    */
    @Override
    public void create(ProdInstlPosModel prodInstlPosModel) {
        prodInstlPosModel.setId(identifierSV.uniqueId());
        prodInstlPosModel.setCrtime(new Date());
        prodInstlPosModel.setUptime(new Date());
        prodInstlPosModelDAO.insert(prodInstlPosModel);
    }

    /**
    * 修改产品_安装位置模板表
    *
    * @param prodInstlPosModel
    */
    @Override
    public void update(ProdInstlPosModel prodInstlPosModel) {
        prodInstlPosModel.setUptime(new Date());
        prodInstlPosModelDAO.update(prodInstlPosModel);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        prodInstlPosModelDAO.delete(id);
    }

    /**
    * 加载产品_安装位置模板表
    *
    * @param id 主键
    * @return 产品_安装位置模板表
    */
    @Override
    public ProdInstlPosModel load(String id) {
        return prodInstlPosModelDAO.load(id);
    }

}
