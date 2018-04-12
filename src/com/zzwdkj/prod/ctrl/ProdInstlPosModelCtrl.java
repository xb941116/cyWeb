package com.zzwdkj.prod.ctrl;

import com.zzwdkj.cfg.entity.CfgArea;
import com.zzwdkj.cfg.service.CfgAreaSV;
import com.zzwdkj.common.ExtMsg;
import com.zzwdkj.base.BaseCtrl;
import com.hckj.framework.web.query.ExtJsp;
import com.zzwdkj.prod.entity.ProdInstlPosModel;
import com.zzwdkj.prod.service.ProdInstlPosModelSV;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import java.util.List;

/**
 * prod_instl_pos_model, 产品_安装位置模板表Ctrl
 *
 * @author guoxianwei  2017-01-17 17:06:22
 */
@Controller
@RequestMapping("/prod/prodInstlPosModel")
public class ProdInstlPosModelCtrl extends BaseCtrl {

    @Resource
    private ProdInstlPosModelSV prodInstlPosModelSV;
    @Resource
    private CfgAreaSV cfgAreaSV;

    /**
     * 查询
     */
    @RequestMapping("/query")
    public void query(ExtJsp<ProdInstlPosModel> page, String name) {
        List<ProdInstlPosModel> prodInstlPosModelList = prodInstlPosModelSV.queryProdInstlPosModel(name, page.getStart(), page.getLimit());
        page.setRecordList(prodInstlPosModelList);
        page.setTotalRow(prodInstlPosModelSV.count(name));
    }

    /**
     * 新增页面
     */
    @RequestMapping("/add")
    public String add(Model model,String latitude,String longitude) {
        List<CfgArea> cfgAreaList = cfgAreaSV.queryArea(null, "1", 0, 100);
        model.addAttribute("cfgAreaList", cfgAreaList);
        model.addAttribute("latitude", latitude);
        model.addAttribute("longitude", longitude);
        return "/prodPosRpt";
    }

    /**
     * 编辑页面
     */
    @RequestMapping("/edit")
    public String edit(String id,String latitude,String longitude, Model model) {
        ProdInstlPosModel prodInstlPosModel = prodInstlPosModelSV.load(id);
        model.addAttribute("prodInstlPosModel", prodInstlPosModel);
        List<CfgArea> cfgAreaList = cfgAreaSV.queryArea(null, "1", 0, 100);
        model.addAttribute("cfgAreaList", cfgAreaList);
        model.addAttribute("latitude", latitude);
        model.addAttribute("longitude", longitude);
        return "/prodPosRpt";
    }

    /**
     * 创建
     *
     * @param prodInstlPosModel 产品_安装位置模板表
     */
    @RequestMapping("create")
    public ExtMsg create(ProdInstlPosModel prodInstlPosModel) {
        prodInstlPosModelSV.create(prodInstlPosModel);
        return ExtMsg.success("创建成功");
    }

    /**
     * 更新
     *
     * @param prodInstlPosModel 产品_安装位置模板表
     */
    @RequestMapping("update")
    public ExtMsg update(ProdInstlPosModel prodInstlPosModel) {
        try {
            prodInstlPosModelSV.update(prodInstlPosModel);
        } catch (Exception e) {
            return ExtMsg.fail(e.getMessage());
        }
        return ExtMsg.success("更新成功");
    }

    /**
     * 删除
     *
     * @param id 主键
     */
    @RequestMapping("remove")
    public ExtMsg remove(String id) {
        try {
            prodInstlPosModelSV.remove(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ExtMsg.fail("删除失败");
        }
        return ExtMsg.success("删除成功");
    }
}

