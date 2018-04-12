package com.zzwdkj.cfg.service;

import com.zzwdkj.cfg.entity.CfgArea;

import java.util.List;

/**
 * cfg_area, 区域字典表SV
 *
 * @author guoxianwei  2016-10-18 17:18:57
 */

public interface CfgAreaSV {


    /**
     * 查询运营城市
     *
     * @param topno     上级代码
     * @param areaLevel 区域级别
     * @param rowStart  起始行
     * @param rowSize   行大小
     * @return 运营城市集合
     */
    List<CfgArea> queryArea(String topno, String areaLevel, int rowStart, int rowSize);
}
