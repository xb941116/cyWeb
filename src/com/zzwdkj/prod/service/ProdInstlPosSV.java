package com.zzwdkj.prod.service;

import com.zzwdkj.common.Std;
import com.zzwdkj.prod.entity.Prod;
import com.zzwdkj.prod.entity.ProdInstlPos;
import com.zzwdkj.prod.entity.ProdInstlPosModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
* prod_instl_pos, 产品_安装位置表SV
*
* @author guoxianwei  2017-01-17 17:06:22
*/

public interface ProdInstlPosSV {

    /**
    * 查询产品_安装位置表 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<ProdInstlPos> queryProdInstlPos(String name, int rowStart, int rowSize);

    /**
    * 新增产品_安装位置表
    *
    * @param prodInstlPos
    */
    void create(ProdInstlPos prodInstlPos);

    /**
    * 修改产品_安装位置表
    *
    * @param prodInstlPos
    */
    void update(ProdInstlPos prodInstlPos);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计产品_安装位置表数量
    * @param name 名称
    * @return 产品_安装位置表数量
    */
    int count(String name);

    /**
    * 加载产品_安装位置表
    *
    * @param id 主键
    * @return 产品_安装位置表
    */
    ProdInstlPos load(String id);


    /**
     * 创建安装位置
     * @param prodNo
     * @param prodInstlPosModel
     */
    void createProdInstlPos(String prodNo, ProdInstlPosModel prodInstlPosModel);

    /**
     * 查询某个产品绑定位置个数
     * @param prodNo
     * @return
     */
    int countByProdNo(String prodNo);

    /**
     * 更新产品位置
     * @param prodNo
     * @param prodInstlPosModel
     */
    void updateByProdNo(String prodNo, ProdInstlPosModel prodInstlPosModel);

    /**
     * 通过当前经纬度获取范围内的产品(rowStart或rowSize有值为-1则全查)
     * @param params 参数MAP（参数有 minLat:最小维度 maxLat：最大维度 minLng：最小经度 maxLng：最大经度）
     * @param rowStart 起始位置
     * @param rowSize 分页大小
     * @return
     */
    List<ProdInstlPos> queryProdByDisTance(Map<String, Object> params, int rowStart, int rowSize);

    /**
     * 通过产品编号查找产品位置
     * @param prodNo
     * @return
     */
    ProdInstlPos loadProdInstlPosByProdNo(String prodNo);
}
