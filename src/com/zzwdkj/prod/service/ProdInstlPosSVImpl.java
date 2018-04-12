package com.zzwdkj.prod.service;

import com.zzwdkj.prod.entity.Prod;
import com.zzwdkj.prod.entity.ProdInstlPos;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.common.Std;
import com.zzwdkj.prod.dao.ProdInstlPosDAO;
import com.zzwdkj.prod.entity.ProdInstlPosModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* prod_instl_pos, 产品_安装位置表SVImpl
*
* @author guoxianwei  2017-01-17 17:06:22
*/
@Service("prodInstlPosSV")
public class ProdInstlPosSVImpl implements ProdInstlPosSV {

    @Resource
    private ProdInstlPosDAO prodInstlPosDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询产品_安装位置表 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<ProdInstlPos> queryProdInstlPos(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return prodInstlPosDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计产品_安装位置表数量
    * @param name 名称
    * @return 产品_安装位置表
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return prodInstlPosDAO.count(params);
    }


    /**
    * 新增产品_安装位置表
    *
    * @param prodInstlPos
    */
    @Override
    public void create(ProdInstlPos prodInstlPos) {
        prodInstlPos.setId(identifierSV.uniqueId());
        prodInstlPos.setCrtime(new Date());
        prodInstlPos.setUptime(new Date());
        prodInstlPosDAO.insert(prodInstlPos);
    }

    /**
    * 修改产品_安装位置表
    *
    * @param prodInstlPos
    */
    @Override
    public void update(ProdInstlPos prodInstlPos) {
        prodInstlPos.setUptime(new Date());
        prodInstlPosDAO.update(prodInstlPos);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        prodInstlPosDAO.delete(id);
    }

    /**
    * 加载产品_安装位置表
    *
    * @param id 主键
    * @return 产品_安装位置表
    */
    @Override
    public ProdInstlPos load(String id) {
        return prodInstlPosDAO.load(id);
    }

    /**
     * 创建安装位置
     *
     * @param prodNo
     * @param prodInstlPosModel
     */
    @Override
    public void createProdInstlPos(String prodNo, ProdInstlPosModel prodInstlPosModel) {
        ProdInstlPos prodInstlPos=new ProdInstlPos();
        prodInstlPos.setId(identifierSV.uniqueId());
        prodInstlPos.setBizNo(prodInstlPosModel.getBizNo());
        prodInstlPos.setProdNo(prodNo);
        prodInstlPos.setPos(prodInstlPosModel.getPos());
        prodInstlPos.setAddr(prodInstlPosModel.getAddr());
        prodInstlPos.setProv(prodInstlPosModel.getProv());
        prodInstlPos.setCity(prodInstlPosModel.getCity());
        prodInstlPos.setDist(prodInstlPosModel.getDist());
        prodInstlPos.setProvName(prodInstlPosModel.getProvName());
        prodInstlPos.setCityName(prodInstlPosModel.getCityName());
        prodInstlPos.setDistName(prodInstlPosModel.getDistName());
        prodInstlPos.setLat(prodInstlPosModel.getLat());
        prodInstlPos.setLng(prodInstlPosModel.getLng());
        prodInstlPos.setManager(prodInstlPosModel.getManager());
        prodInstlPos.setTel(prodInstlPosModel.getTel());
        prodInstlPos.setRemark(prodInstlPosModel.getRemark());
        prodInstlPos.setCrtime(new Date());
        prodInstlPos.setUptime(new Date());
        prodInstlPosDAO.insert(prodInstlPos);
    }

    /**
     * 查询某个产品绑定的个数
     *
     * @param prodNo
     * @return
     */
    @Override
    public int countByProdNo(String prodNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("prodNo", prodNo);
        return prodInstlPosDAO.count(params);
    }

    /**
     * 更新产品位置
     *
     * @param prodNo
     * @param prodInstlPosModel
     */
    @Override
    public void updateByProdNo(String prodNo, ProdInstlPosModel prodInstlPosModel) {
        ProdInstlPos prodInstlPos=new ProdInstlPos();
        prodInstlPos.setBizNo(prodInstlPosModel.getBizNo());
        prodInstlPos.setProdNo(prodNo);
        prodInstlPos.setPos(prodInstlPosModel.getPos());
        prodInstlPos.setAddr(prodInstlPosModel.getAddr());
        prodInstlPos.setProv(prodInstlPosModel.getProv());
        prodInstlPos.setCity(prodInstlPosModel.getCity());
        prodInstlPos.setDist(prodInstlPosModel.getDist());
        prodInstlPos.setProvName(prodInstlPosModel.getProvName());
        prodInstlPos.setCityName(prodInstlPosModel.getCityName());
        prodInstlPos.setDistName(prodInstlPosModel.getDistName());
        prodInstlPos.setLat(prodInstlPosModel.getLat());
        prodInstlPos.setLng(prodInstlPosModel.getLng());
        prodInstlPos.setManager(prodInstlPosModel.getManager());
        prodInstlPos.setTel(prodInstlPosModel.getTel());
        prodInstlPos.setRemark(prodInstlPosModel.getRemark());
        prodInstlPos.setUptime(new Date());
        prodInstlPosDAO.update("updateByProdNo",prodInstlPos);
    }


    /**
     * 通过当前经纬度获取范围内的产品(rowStart或rowSize有值为-1则全查)
     *
     * @param params   参数MAP（参数有 minLat:最小维度 maxLat：最大维度 minLng：最小经度 maxLng：最大经度）
     * @param rowStart 起始位置
     * @param rowSize  分页大小
     * @return
     */
    @Override
    public List<ProdInstlPos> queryProdByDisTance(Map<String, Object> params, int rowStart, int rowSize) {

        if (rowStart==-1||rowSize==-1){
            return prodInstlPosDAO.query("queryProdByDisTance",params);
        }else {
            return prodInstlPosDAO.query("queryProdByDisTance",params,rowStart,rowSize);
        }
    }

    /**
     * 通过产品编号查找位置
     * @param prodNo
     * @return
     */
    @Override
    public ProdInstlPos loadProdInstlPosByProdNo(String prodNo) {
        return prodInstlPosDAO.queryUnique("loadProdInstlPosByProdNo",prodNo);
    }

}
