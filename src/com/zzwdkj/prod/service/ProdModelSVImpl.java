package com.zzwdkj.prod.service;

import com.zzwdkj.prod.entity.ProdModel;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.prod.dao.ProdModelDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * prod_model, 商品_模板表SVImpl
 *
 * @author guoxianwei  2016-09-07 15:02:08
 */
@Service("prodModelSV")
public class ProdModelSVImpl implements ProdModelSV {

    @Resource
    private ProdModelDAO prodModelDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
     * 查询商品_模板表 ，带分页
     *
     * @param bizNo    商家编号
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 菜单资源
     */
    @Override
    public List<ProdModel> queryProdModel(String bizNo, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        return prodModelDAO.query(params, rowStart, rowSize);
    }

    /**
     * 统计商品_模板表数量
     *
     * @param bizNo 商家编号
     * @return 商品_模板表
     */
    @Override
    public int count(String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        return prodModelDAO.count(params);
    }


    /**
     * 新增商品_模板表
     *
     * @param prodModel
     */
    @Override
    public void create(ProdModel prodModel) {
        prodModel.setId(identifierSV.uniqueId());
        prodModel.setCrtime(new Date());
        prodModel.setUptime(new Date());
        prodModel.setState(1);
        prodModelDAO.insert(prodModel);
    }

    /**
     * 修改商品_模板表
     *
     * @param prodModel
     */
    @Override
    public void update(ProdModel prodModel) {
        prodModel.setUptime(new Date());
        prodModelDAO.update(prodModel);
    }

    /**
     * 删除一条记录
     *
     * @param id
     */
    @Override
    public void remove(String id) {
        prodModelDAO.delete(id);
    }

    /**
     * 加载商品_模板表
     *
     * @param id 主键
     * @return 商品_模板表
     */
    @Override
    public ProdModel load(String id) {
        return prodModelDAO.load(id);
    }

}
