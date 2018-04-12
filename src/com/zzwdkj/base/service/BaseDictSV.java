package com.zzwdkj.base.service;


import com.zzwdkj.base.entity.BaseDict;

import java.util.List;
import java.util.Map;

/**
* base_dict, SV
*
* @author guoxianwei  2017-03-01 14:27:28
*/

public interface BaseDictSV {

    /**
    * 查询
    *
    * @param params 参数
    * @return 结果集
    */
    List<BaseDict> queryBaseDict(Map<String, Object> params,int rowStart,int rowSize);

    /**
    * 新增
    *
    * @param baseDict
    */
    void insert(BaseDict baseDict);

    /**
    * 修改
    *
    * @param baseDict
    */
    void update(BaseDict baseDict);

    /**
    * 删除一条记录
    *
    * @param id 主键
    */
    void remove(String id);

    /**
    * 统计数量
    * @param params 参数
    * @return 数量
    */
    int count(Map<String, Object> params);

    /**
    * 加载
    *
    * @param id 主键
    * @return 
    */
    BaseDict load(String id);

    /**
     * 根据类型获取数据字典
     * @param type 类型
     * @return
     */
    List<BaseDict> queryBaseDictByType(String type);

}
