package com.zzwdkj.base.ctrl;


import com.alibaba.druid.stat.TableStat;
import com.zzwdkj.base.entity.BaseDict;
import com.zzwdkj.base.service.BaseDictSV;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.biz.entity.Biz;
import com.zzwdkj.common.BaseConfig;
import com.zzwdkj.common.ExtMsg;
import com.zzwdkj.common.Pager;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huangshentao on 2017/3/1.
 */
@Controller
@RequestMapping("/dict")
public class DictCtrl {

    @Resource
    private BaseDictSV baseDictSV;
    @Resource
    private IdentifierSV identifierSV;

    @RequestMapping("/list")
    public String list(Pager<BaseDict> pager) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("type", BaseDict.DictType.PROD_TYPE.getKey());
        params.put("is_parent", "0");
        pager.setAction("/dict/list");
        List<BaseDict> dictList = baseDictSV.queryBaseDict(params, pager.getRowStart(), pager.getPageSize());
        pager.setParams(params);
        pager.setRecordList(dictList);
        pager.setTotalRow(baseDictSV.count(params));
        return "sjxx/typeMaintain/prodTypeList";
    }

    @RequestMapping("/add")
    public String add() {
        return "sjxx/typeMaintain/prodTypeEdit";
    }

    @RequestMapping("/edit")
    public String edit(String id, Model model) {
        BaseDict dict = baseDictSV.load(id);
        model.addAttribute("baseDict", dict);
        return "sjxx/typeMaintain/prodTypeEdit";
    }

    @RequestMapping("/insertOrUpdate")
    public ExtMsg insertOrUpdate(BaseDict baseDict, HttpServletRequest request, ModelMap modelMap) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        if (StringUtils.isNotEmpty(baseDict.getId())) {
            baseDict.setUpdateby(biz.getAcct());
            baseDictSV.update(baseDict);
            modelMap.clear();
        } else {
            baseDict.setType(BaseDict.DictType.PROD_TYPE.getKey());
            baseDict.setDescr("商品类型");
            baseDict.setId(identifierSV.getUniqueId());
            baseDict.setCreateby(biz.getAcct());
            baseDict.setIs_parent(0);
            baseDictSV.insert(baseDict);
            modelMap.clear();
        }
        return ExtMsg.success();
    }

    @RequestMapping("/remove")
    public ExtMsg remove(String id) {
        if (StringUtils.isNotEmpty(id)) {
            baseDictSV.remove(id);
        }
        return ExtMsg.success();
    }
}
