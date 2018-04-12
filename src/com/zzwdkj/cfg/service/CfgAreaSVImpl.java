package com.zzwdkj.cfg.service;


import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.cfg.dao.CfgAreaDAO;
import com.zzwdkj.cfg.entity.CfgArea;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * cfg_area, 区域字典表SVImpl
 *
 * @author guoxianwei  2016-10-18 17:18:57
 */
@Service("cfgAreaSV")
public class CfgAreaSVImpl implements CfgAreaSV {

    @Resource
    private CfgAreaDAO cfgAreaDAO;
    @Resource
    private IdentifierSV identifierSV;

    /**
     * 查询运营城市
     *
     * @param topno     上级代码
     * @param areaLevel 区域级别
     * @param rowStart  起始行
     * @param rowSize   行大小
     * @return 运营城市集合
     */
    @Override
    public List<CfgArea> queryArea(String topno, String areaLevel, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("topno", topno);
        params.put("areaLevel", areaLevel);
        return cfgAreaDAO.query(params, rowStart, rowSize);
    }

}
