package com.zzwdkj.biz.ctrl;

import com.zzwdkj.base.BaseCtrl;
import com.zzwdkj.biz.entity.Biz;
import com.zzwdkj.biz.entity.BizGprsBind;
import com.zzwdkj.biz.service.BizGprsBindSV;
import com.zzwdkj.biz.service.BizSV;
import com.zzwdkj.common.BaseConfig;
import com.zzwdkj.common.ExtMsg;
import com.zzwdkj.common.GsonUtil;
import com.zzwdkj.common.Pager;
import com.zzwdkj.gprs.entity.GprsModel;
import com.zzwdkj.gprs.service.GprsModelSV;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * biz_gprs_bind, 商家_GPRS绑定表Ctrl
 *
 * @author guoxianwei  2016-09-07 15:01:28
 */
@Controller
@RequestMapping("/biz/bizGprsBind")
public class BizGprsBindCtrl extends BaseCtrl {

    @Resource
    private BizGprsBindSV bizGprsBindSV;
    @Resource
    private GprsModelSV gprsModelSV;
    @Resource
    private BizSV bizSV;

    /**
     * 查询
     */
    @RequestMapping("query")
    public String query(Pager pager, String gprsNo, Model model, Integer bind, Integer prodSet, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        Map<String, Object> params = new HashMap<String, Object>();
        pager.setAction("/biz/bizGprsBind/query");
        bind = bind == null ? 1 : bind;
        if (biz.isAdmin()) {
            params.put("gprsNo", gprsNo);
            params.put("bind", bind);
            pager.setParams(params);
            List<GprsModel> gprsModelList = gprsModelSV.queryGprsModel(params, pager.getRowStart(), pager.getPageSize());
            pager.setRecordList(gprsModelList);
            pager.setTotalRow(gprsModelSV.count(params));
            //商家列表
            List<Biz> bizList = bizSV.queryBiz(new HashMap<String, Object>(), -1, -1);
            model.addAttribute("bizList", bizList);
        } else {
            params.put("bizNo", biz.getBizNo());
            params.put("gprsNo", gprsNo);
            params.put("bind", bind);
            params.put("prodSet", prodSet);
            pager.setParams(params);
            List<BizGprsBind> bizGprsBindList = bizGprsBindSV.queryBizGprsBind(params, pager.getRowStart(), pager.getPageSize());
            if (bizGprsBindList != null && !bizGprsBindList.isEmpty()) {
                for (BizGprsBind bizGprsBind : bizGprsBindList) {
                    bizGprsBind.setBizName(biz.getName());
                }
            }
            pager.setRecordList(bizGprsBindList);
            pager.setTotalRow(bizGprsBindSV.countBizGprsBind(params));
            //商家列表
            List<Biz> bizList = bizSV.queryBizUnder(biz.getBizNo(), -1, -1);
            model.addAttribute("bizList", bizList);
        }



        model.addAttribute("admin", biz.isAdmin());
        model.addAttribute("grade", biz.getGrade());
        return "sbgl/sellerBinding/bizGprsBindList";
    }

    /**
     * 模块商家绑定
     *
     * @param bizNo      商家
     * @param gprsNoList Gprs模块号
     */
    @RequestMapping("bind")
    public ExtMsg bind(String bizNo, String gprsNoList, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        StringBuilder stringBuilder = new StringBuilder("");
        if (bizNo != null && gprsNoList != null) {
            String[] gprsNoArray = gprsNoList.split("_");
            if (biz.isAdmin()) {
                for (String gprsNo : gprsNoArray) {
                    if (StringUtils.isNotEmpty(gprsNo)) {
                        int count = bizGprsBindSV.countBizGprsBind(bizNo, gprsNo);
                        GprsModel gprsModel = gprsModelSV.loadByGprsNo(gprsNo);
                        if (count > 0 || (gprsModel != null && gprsModel.getBind() == 1)) {
                            stringBuilder.append(gprsNo).append("_");
                        } else {
                            bizGprsBindSV.bind(bizNo, gprsNo);
                        }
                    }
                }
            } else {
                for (String gprsNo : gprsNoArray) {
                    if (StringUtils.isNotEmpty(gprsNo)) {
                        int count = bizGprsBindSV.countBizGprsBind(bizNo, gprsNo);
                        GprsModel gprsModel = gprsModelSV.loadByGprsNo(gprsNo);
                        if (count > 0 || (gprsModel != null && gprsModel.getBind() == 1)) {
                            stringBuilder.append(gprsNo).append("_");
                        } else {
                            List<Biz> bizList = bizSV.queryBizUnder(biz.getBizNo(), 0, 20);
                            if (bizList != null && !bizList.isEmpty()) {
                                for (Biz biz1 : bizList) {
                                    if (biz1.getBizNo().equals(bizNo)) {
                                        bizGprsBindSV.bind(bizNo, gprsNo);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (stringBuilder.toString().length() > 1) {
            return ExtMsg.fail(stringBuilder.toString());
        } else {
            return ExtMsg.success("绑定成功");
        }
    }

    /**
     * 模块商家解除绑定
     *
     * @param gprsNoList Gprs模块号
     */
    @RequestMapping("unbind")
    public ExtMsg bizUnBind(String gprsNoList, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        StringBuilder stringBuilder = new StringBuilder("");
        if (gprsNoList != null) {
            String[] gprsNoArray = gprsNoList.split("_");
            for (String gprsNo : gprsNoArray) {
                if (StringUtils.isNotEmpty(gprsNo)) {
                    int count = bizGprsBindSV.countBizGprsBind(biz.getBizNo(), gprsNo);
                    GprsModel gprsModel = gprsModelSV.loadByGprsNo(gprsNo);
                    if (count > 0 && (gprsModel != null && gprsModel.getBind() == 1)) {
                        bizGprsBindSV.unbind(biz.getBizNo(), gprsNo);
                    } else {
                        stringBuilder.append(gprsNo).append("_");
                    }
                }
            }
        }
        if (stringBuilder.toString().length() > 1) {
            return ExtMsg.fail(stringBuilder.toString());
        } else {
            return ExtMsg.success("解除绑定成功");
        }
    }


    /**
     * 模块移动
     *
     * @param bizNo      商家
     * @param gprsNoList Gprs模块号
     */
    @RequestMapping("moveModel")
    public ExtMsg moveModel(String bizNo, String gprsNoList, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        StringBuilder stringBuilder = new StringBuilder("");
        if (biz.isAdmin() || biz.getGrade() == 3) {
            if (bizNo != null && gprsNoList != null && !biz.getBizNo().equals(bizNo)) {
                String[] gprsNoArray = gprsNoList.split("_");
                for (String gprsNo : gprsNoArray) {
                    if (StringUtils.isNotEmpty(gprsNo)) {
                        int count = bizGprsBindSV.countBizGprsBind(biz.getBizNo(), gprsNo);
                        GprsModel gprsModel = gprsModelSV.loadByGprsNo(gprsNo);
                        if (count > 0 && gprsModel != null && gprsModel.getBind() == 1) {
                            Biz biz1 = bizSV.loadBizByBizNo(bizNo);
                            if (biz1.getParBizNo() != null && biz1.getParBizNo().equals(biz.getBizNo())) {
                                bizGprsBindSV.moveModel(bizNo, gprsNo);
                            } else {
                                return ExtMsg.success("非法的移动");
                            }
                        } else {
                            stringBuilder.append(gprsNo).append("_");
                        }
                    }
                }
            } else {
                return ExtMsg.success("无效的移动");
            }
        } else {
            return ExtMsg.success("只有代理商或超级管理员有权限移动模块");
        }
        if (stringBuilder.toString().length() > 1) {
            return ExtMsg.fail(stringBuilder.toString());
        } else {
            return ExtMsg.success("模块移动成功");
        }
    }

    /**
     * 新增页面
     */
    @RequestMapping("/add")
    public String add(Model model) {
        return "bizGprsBind/BizGprsBindAdd";
    }

    /**
     * 编辑页面
     */
    @RequestMapping("/edit")
    public String edit(String id, Model model) {
        BizGprsBind bizGprsBind = bizGprsBindSV.load(id);
        model.addAttribute("bizGprsBind", bizGprsBind);
        return "bizGprsBind/BizGprsBindEdit";
    }

    /**
     * 创建
     *
     * @param bizGprsBind 商家_GPRS绑定表
     */
    @RequestMapping("create")
    public ExtMsg create(BizGprsBind bizGprsBind) {
        bizGprsBindSV.create(bizGprsBind);
        return ExtMsg.success("创建成功");
    }

    /**
     * 更新
     *
     * @param bizGprsBind 商家_GPRS绑定表
     */
    @RequestMapping("update")
    public ExtMsg update(BizGprsBind bizGprsBind) {
        try {
            bizGprsBindSV.update(bizGprsBind);
        } catch (Exception e) {
            return ExtMsg.fail(e.getMessage());
        }
        return ExtMsg.success("更新成功");
    }

    /**
     * 删除
     *
     * @param data 主键
     */
    @RequestMapping("remove")
    public ExtMsg remove(String data) {
        BizGprsBind bizGprsBind = GsonUtil.fromJson(data, BizGprsBind.class);
        bizGprsBindSV.remove(bizGprsBind.getId());
        return ExtMsg.success("删除成功");
    }
}
