package com.zzwdkj.prod.service;

import com.zzwdkj.prod.entity.ProdInstlImgs;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.prod.dao.ProdInstlImgsDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* prod_instl_imgs, 产品_安装场地图片表SVImpl
*
* @author guoxianwei  2016-09-07 15:02:00
*/
@Service("prodInstlImgsSV")
public class ProdInstlImgsSVImpl implements ProdInstlImgsSV {

    @Resource
    private ProdInstlImgsDAO prodInstlImgsDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
    * 查询产品_安装场地图片表 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<ProdInstlImgs> queryProdInstlImgs(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return prodInstlImgsDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计产品_安装场地图片表数量
    * @param name 名称
    * @return 产品_安装场地图片表
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return prodInstlImgsDAO.count(params);
    }


    /**
    * 新增产品_安装场地图片表
    *
    * @param prodInstlImgs
    */
    @Override
    public void create(ProdInstlImgs prodInstlImgs) {
        prodInstlImgs.setId(identifierSV.uniqueId());
        prodInstlImgs.setCrtime(new Date());
        prodInstlImgsDAO.insert(prodInstlImgs);
    }

    /**
    * 修改产品_安装场地图片表
    *
    * @param prodInstlImgs
    */
    @Override
    public void update(ProdInstlImgs prodInstlImgs) {
        prodInstlImgsDAO.update(prodInstlImgs);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        prodInstlImgsDAO.delete(id);
    }

    /**
    * 加载产品_安装场地图片表
    *
    * @param id 主键
    * @return 产品_安装场地图片表
    */
    @Override
    public ProdInstlImgs load(String id) {
        return prodInstlImgsDAO.load(id);
    }

}
