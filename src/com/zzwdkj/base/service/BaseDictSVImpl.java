package com.zzwdkj.base.service;


import com.zzwdkj.base.dao.BaseDictDAO;
import com.zzwdkj.base.entity.BaseDict;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * base_dict, SVImpl
 *
 * @author guoxianwei  2017-03-01 14:27:28
 */
@Service("baseDictSV")
public class BaseDictSVImpl implements BaseDictSV {

    @Resource
    private BaseDictDAO baseDictDAO;


    /**
     * 查询
     *
     * @param params 参数
     * @return 结果集
     */
    @Override
    public List<BaseDict> queryBaseDict(Map<String, Object> params, int rowStart, int rowSize) {
        return baseDictDAO.query(params, rowStart, rowSize);
    }


    /**
     * 统计数量
     *
     * @param params 参数
     * @return
     */
    @Override
    public int count(Map<String, Object> params) {
        return baseDictDAO.count(params);
    }


    /**
     * 新增
     *
     * @param baseDict
     */
    @Override
    public void insert(BaseDict baseDict) {

        baseDictDAO.insert(baseDict);
    }

    /**
     * 修改
     *
     * @param baseDict
     */
    @Override
    public void update(BaseDict baseDict) {
        baseDict.setUpdatetime(new Date());
        baseDictDAO.update(baseDict);
    }

    /**
     * 删除一条记录
     *
     * @param id 主键
     */
    @Override
    public void remove(String id) {
        baseDictDAO.delete(id);
    }

    /**
     * 加载
     *
     * @param id 主键
     * @return
     */
    @Override
    public BaseDict load(String id) {
        return baseDictDAO.load(id);
    }

    /**
     * 根据类型获取数据字典
     *
     * @param dictType 类型
     * @return
     */
    @Override
    public List<BaseDict> queryBaseDictByType(String dictType) {
        return baseDictDAO.query("selectBaseDictByDictType", dictType);
    }
}
