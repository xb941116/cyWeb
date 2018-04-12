package com.zzwdkj.cfg.ctrl;

import com.zzwdkj.base.BaseCtrl;
import com.zzwdkj.cfg.entity.CfgArea;
import com.zzwdkj.cfg.service.CfgAreaSV;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * cfg_area, 区域字典表Ctrl
 *
 * @author guoxianwei  2016-10-18 17:18:57
 */
@Controller
@RequestMapping("/cfg/cfgArea")
public class CfgAreaCtrl extends BaseCtrl {

    @Resource
    private CfgAreaSV cfgAreaSV;

    /**
     * 查询
     */
    @RequestMapping("queryArea")
    public List<CfgArea> queryArea(String topno, String areaLevel) {
        List<CfgArea> cfgAreaList = cfgAreaSV.queryArea(topno, areaLevel, 0, 100);
        return cfgAreaList;
    }

}
