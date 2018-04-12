package com.zzwdkj.prod.ctrl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.zzwdkj.base.BaseCtrl;
import com.hckj.framework.web.query.ExtJsp;
import com.zzwdkj.base.entity.BaseDict;
import com.zzwdkj.base.service.BaseDictSV;
import com.zzwdkj.biz.entity.Biz;
import com.zzwdkj.common.BaseConfig;
import com.zzwdkj.common.ExtMsg;
import com.zzwdkj.prod.entity.ProdModel;
import com.zzwdkj.prod.service.ProdModelSV;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * prod_model, 商品_模板表Ctrl
 *
 * @author guoxianwei  2016-09-07 15:02:08
 */
@Controller
@RequestMapping("/prod/prodModel")
public class ProdModelCtrl extends BaseCtrl {

    @Resource
    private ProdModelSV prodModelSV;

    @Resource
    private BaseDictSV baseDictSV;
    /**
     * 查询
     */
    @RequestMapping("query")
    public void query(ExtJsp<ProdModel> page, String name) {
        List<ProdModel> prodModelList = prodModelSV.queryProdModel(name, page.getStart(), page.getLimit());
        page.setRecordList(prodModelList);
        page.setTotalRow(prodModelSV.count(name));
    }

    /**
     * 新增页面
     */
    @RequestMapping("/add")
    public String add(Model model) {
        List<BaseDict> dictList = baseDictSV.queryBaseDictByType(BaseDict.DictType.PROD_TYPE.getKey());
        model.addAttribute("dictList",dictList);
        String[][] moneyOptionList = new String[6][3];
        moneyOptionList=JSON.parseObject("[[\"\",\"\",\"\"],[\"\",\"\",\"\"],[\"\",\"\",\"\"],[\"\",\"\",\"\"],[\"\",\"\",\"\"],[\"\",\"\",\"\"]]",new TypeReference<String[][]>(){});
        model.addAttribute("moneyOptionList", moneyOptionList);
        return "prodModelAdd";
    }

    /**
     * 编辑页面
     */
    @RequestMapping("/edit")
    public String edit(String id, Model model) {
        ProdModel prodModel = prodModelSV.load(id);
        List<BaseDict> dictList = baseDictSV.queryBaseDictByType(BaseDict.DictType.PROD_TYPE.getKey());
        model.addAttribute("dictList",dictList);
        model.addAttribute("prodModel", prodModel);
        //价格
        String[][] moneyOptionList = new String[6][3];
        if (prodModel.getOtherMoneyState() == 1 && prodModel.getOtherMoneyOption() != null) {
            moneyOptionList=JSON.parseObject(prodModel.getOtherMoneyOption(),new TypeReference<String[][]>(){});
        }

        model.addAttribute("moneyOptionList", moneyOptionList);

        return "prodModelAdd";
    }

    /**
     * 创建
     *
     * @param prodModel 商品_模板表
     */
    @RequestMapping("crOrUp")
    public ExtMsg create(ProdModel prodModel,String[] nameTeam,String[] moneyTeam,String[] cvTeam, HttpServletRequest request, ModelMap modelMap) {
        if (prodModel.getRunTime()==null||prodModel.getRunTime().equals("")){
            prodModel.setRunTime(0);
        }
        //可选金额
        String[][] moneyStr=new String[6][3];
        for (int i=0;i<6;i++){
            moneyStr[i][0]=nameTeam[i];
            moneyStr[i][1]=moneyTeam[i];
            moneyStr[i][2]=cvTeam[i];
        }
        String otherMoneyOption=JSONArray.toJSONString(moneyStr);
        prodModel.setOtherMoneyOption(otherMoneyOption);

        if(StringUtils.isEmpty(prodModel.getId())){
            Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
            prodModel.setBizNo(biz.getBizNo());
            prodModel.setBizName(biz.getName());
            if (prodModel.getRunTime()==null){
                prodModel.setRunTime(0);
            }
            prodModelSV.create(prodModel);
            modelMap.clear();
            return ExtMsg.success("创建成功");
        }else {
            prodModelSV.update(prodModel);
            modelMap.clear();
            return ExtMsg.success("更新成功");
        }
    }

    /**
     * 删除
     *
     * @param id 主键
     */
    @RequestMapping("remove")
    public ExtMsg remove(String id) {
        ProdModel prodModel = prodModelSV.load(id);
        if(prodModel!=null){
            prodModelSV.remove(prodModel.getId());
        }
        return ExtMsg.success("删除成功");
    }
}
