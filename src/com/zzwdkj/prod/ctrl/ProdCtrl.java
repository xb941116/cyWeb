package com.zzwdkj.prod.ctrl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.hckj.framework.utils.Formatter;
import com.rabbitmq.tools.json.JSONUtil;
import com.zzwdkj.base.BaseCtrl;
import com.zzwdkj.base.entity.BaseDict;
import com.zzwdkj.base.service.BaseDictSV;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.biz.entity.Biz;
import com.zzwdkj.biz.service.BizGprsBindSV;
import com.zzwdkj.cfg.entity.CfgArea;
import com.zzwdkj.cfg.service.CfgAreaSV;
import com.zzwdkj.common.*;
import com.zzwdkj.gprs.entity.GprsModel;
import com.zzwdkj.gprs.entity.GprsOtp;
import com.zzwdkj.gprs.service.GprsModelSV;
import com.zzwdkj.gprs.service.GprsOtpSV;
import com.zzwdkj.msg.service.MsgSV;
import com.zzwdkj.prod.entity.*;
import com.zzwdkj.prod.service.ProdInstlPosModelSV;
import com.zzwdkj.prod.service.ProdInstlPosSV;
import com.zzwdkj.prod.service.ProdModelSV;
import com.zzwdkj.prod.service.ProdSV;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * prod, 产品表Ctrl
 *
 * @author guoxianwei  2016-09-07 15:01:56
 */
@Controller
@RequestMapping("/prod")
public class ProdCtrl extends BaseCtrl {

    @Resource
    private ProdSV prodSV;
    @Resource
    private GprsOtpSV gprsOtpSV;
    @Resource
    private MsgSV msgSV;
    @Resource
    private IdentifierSV identifierSV;
    @Resource
    private GprsModelSV gprsModelSV;
    @Resource
    private CfgAreaSV cfgAreaSV;
    @Resource
    private ProdModelSV prodModelSV;
    @Resource
    private ProdInstlPosSV prodInstlPosSV;
    @Resource
    private ProdInstlPosModelSV prodInstlPosModelSV;
    @Resource
    private BizGprsBindSV bizGprsBindSV;
    @Resource
    private BaseDictSV baseDictSV;

    /**
     * 设置模块安装位置
     *
     * @param pager
     * @param gprsNo
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("prodInstlPos")
    public String prodInstlPos(Pager pager, String gprsNo, String instlaState, Model model, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        //获取模块列表
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", biz.getBizNo());
        params.put("gprsNo", gprsNo);
        params.put("instlaState", instlaState);
        pager.setParams(params);
        pager.setAction("/prod/prodInstlPos");
        List<GprsModel> gprsModelList = gprsModelSV.queryInstlGprsModelByBizNo(biz.getBizNo(), instlaState, pager.getRowStart(), pager.getPageSize());
        pager.setRecordList(gprsModelList);
        pager.setTotalRow(gprsModelSV.countInstlGprsModelByBizNo(biz.getBizNo(), instlaState));
        //获取安装位置模板
        List<ProdInstlPosModel> prodInstlPosModelList = prodInstlPosModelSV.queryProdInstlPosModel(biz.getBizNo(), -1, -1);
        model.addAttribute("prodInstlPosModelList", prodInstlPosModelList);
        return "sbgl/placeReport/prodInstlPosList";
    }


    /**
     * 设置商品属性
     */
    @RequestMapping("prodSalePro")
    public String prodSalePro(Pager pager, String gprsNo, String prodSet, Model model, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", biz.getBizNo());
        params.put("gprsNo", gprsNo);
        params.put("prodSet", prodSet);
        pager.setParams(params);
        pager.setAction("/prod/prodSalePro");
        List<GprsModel> gprsModelList = gprsModelSV.queryGprsModelForProdSet(params, pager.getRowStart(), pager.getPageSize());
        pager.setRecordList(gprsModelList);
        pager.setTotalRow(gprsModelSV.countGprsModelForProdSet(params));
        List<ProdModel> prodModelList = prodModelSV.queryProdModel(biz.getBizNo(), 0, 20);
        model.addAttribute("prodModelList", prodModelList);
        return "/grzx/goodsType/prodSaleProList";
    }

    @RequestMapping("prodPosRpt")
    public String prodPosRpt(Model model) {
        List<CfgArea> cfgAreaList = cfgAreaSV.queryArea(null, "1", 0, 100);
        model.addAttribute("cfgAreaList", cfgAreaList);
        return "prodPosRpt";
    }

    @RequestMapping("prodGprsMod")
    public String prodGprsMod(Pager pager, String gprsNo, String bizName, Integer state, Integer onlineState, String gprsType, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("gprsNo", gprsNo);
        params.put("gprsType", (gprsType == null || gprsType.equals("")) ? 3 : gprsType);
        if (!biz.isAdmin()) {
            params.put("bizNo", biz.getBizNo());
        }
        params.put("bizName", bizName);
        params.put("onlineState", onlineState);
        pager.setAction("/prod/prodGprsMod");
        pager.setParams(params);
        List<GprsModel> gprsModelList = gprsModelSV.queryGprsModel(params, pager.getRowStart(), pager.getPageSize());
        pager.setRecordList(gprsModelList);
        pager.setTotalRow(gprsModelSV.count(params));
        return "sbgl/moduleMain/prodGprsModList";
    }


    @RequestMapping("prodList")
    public String prodList(Pager pager, String gprsNo, String prodSet, String gprsType, HttpServletRequest request) {

        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("prodSet", prodSet);
        params.put("bizNo", biz.getBizNo());
        params.put("gprsNo", gprsNo);
        params.put("gprsType", gprsType);
        pager.setAction("/prod/prodList");
        pager.setParams(params);
        List<Prod> prodList = prodSV.queryProd(params, pager.getRowStart(), pager.getPageSize());
        pager.setRecordList(prodList);
        pager.setTotalRow(prodSV.countProd(params));
        return "sbgl/product/prodList";
    }

    @RequestMapping("offLineList")
    public String offLineList(Pager pager, String gprsNo, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", biz.getBizNo());
        params.put("gprsNo", gprsNo);
        pager.setAction("/prod/offLineList");
        List<ProdOnlLog> prodOnlLogList = prodSV.queryProdOffLine(params, pager.getRowStart(), pager.getPageSize());
        pager.setRecordList(prodOnlLogList);
        pager.setTotalRow(prodSV.countProdOffLine(params));
        return "sbgl/prodOffLineList";
    }

    @RequestMapping("onLineList")
    public String onLineList(Pager pager, String gprsNo, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", biz.getBizNo());
        params.put("gprsNo", gprsNo);
        pager.setAction("/prod/onLineList");
        List<ProdOnlLog> prodOnlLogList = prodSV.queryProdOnLine(params, pager.getRowStart(), pager.getPageSize());
        pager.setRecordList(prodOnlLogList);
        pager.setTotalRow(prodSV.countProdOnLine(params));
        return "sbgl/prodOnLineList";
    }


    @RequestMapping("prodArgsList")
    public String prodArgsList(Pager pager, String gprsNo, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", biz.getBizNo());
        params.put("gprsNo", gprsNo);
        pager.setAction("/prod/prodArgsList");
        pager.setParams(params);
        List<Prod> prodList = prodSV.queryProdOnline(params, pager.getRowStart(), pager.getPageSize());
        pager.setRecordList(prodList);
        pager.setTotalRow(prodSV.countProdOnline(params));
        return "prodArgsList";
    }

    @RequestMapping("prodSpArgs")
    public String prodSpArgs() {
        return "prodSpArgs";
    }

    @RequestMapping("prodGprsBind")
    public String prodGprsBind(Pager pager, String gprsNo, Integer bind, ModelMap modelMap) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("gprsNo", gprsNo);
        params.put("bind", bind);
        pager.setParams(params);
        List<GprsModel> gprsModelList = gprsModelSV.queryGprsModel(params, pager.getRowStart(), pager.getPageSize());
        pager.setRecordList(gprsModelList);
        pager.setTotalRow(gprsModelSV.count(params));
        return "bizGprsBindList";
    }


    /**
     * 创建
     *
     * @param prodModId  商品模块Id
     * @param gprsNoList Gprs模块号
     */
    @RequestMapping("create")
    public ExtMsg create(String prodModId, String gprsNoList, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        ProdModel prodModel = prodModelSV.load(prodModId);
        if (prodModel != null) {
            String[] gprsNoArray = gprsNoList.split("_");
            for (String gprsNo : gprsNoArray) {
                if (gprsNo.startsWith("0")) {
                    String[][] moneyOptionList = JSON.parseObject(prodModel.getOtherMoneyOption(), new TypeReference<String[][]>() {
                    });
                    for (String[] strings : moneyOptionList) {
                        if (strings[2] == "" || strings[2].length() > 1) {
                            return ExtMsg.fail("模板的脉冲数大于该商品可设置的范围！");
                        }
                    }
                } else if (gprsNo.startsWith("2")) {
                    String[][] moneyOptionList = JSON.parseObject(prodModel.getOtherMoneyOption(), new TypeReference<String[][]>() {
                    });
                    for (String[] strings : moneyOptionList) {
                        if (strings[2] == "" || strings[2].length() > 2) {
                            return ExtMsg.fail("模板的脉冲数大于该商品可设置的范围！");
                        }
                    }
                }
                if (StringUtils.isNotEmpty(gprsNo)) {
                    int count = bizGprsBindSV.countBizGprsBind(biz.getBizNo(), gprsNo);
                    if (count > 0 && prodSV.countByGprsNo(gprsNo) == 0) {
                        prodSV.createProd(gprsNo, prodModel);
                    } else if (count > 0 && prodSV.countByGprsNo(gprsNo) > 0) {
                        prodSV.updateProd(gprsNo, prodModel);
                    }
                }
            }
        }
        return ExtMsg.success("创建成功");
    }

    /**
     * 创建位置
     *
     * @param ModelId    位置模板Id
     * @param gprsNoList Gprs模块号
     */
    @RequestMapping("createInstlPos")
    public ExtMsg createInstlPos(String ModelId, String gprsNoList, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        ProdInstlPosModel prodInstlPosModel = prodInstlPosModelSV.load(ModelId);
        if (prodInstlPosModel != null) {
            String[] gprsNoArray = gprsNoList.split("_");
            for (String gprsNo : gprsNoArray) {
                if (StringUtils.isNotEmpty(gprsNo)) {
                    int count = bizGprsBindSV.countBizGprsBind(biz.getBizNo(), gprsNo);
                    Prod prod = prodSV.loadByGprsNo(gprsNo);
                    if (count > 0 && prodInstlPosSV.countByProdNo(prod.getProdNo()) == 0) {
                        prodInstlPosSV.createProdInstlPos(prod.getProdNo(), prodInstlPosModel);
                    } else if (count > 0 && prodInstlPosSV.countByProdNo(prod.getProdNo()) == 1) {
                        prodInstlPosSV.updateByProdNo(prod.getProdNo(), prodInstlPosModel);
                    }
                }
            }
        }
        return ExtMsg.success("设置成功");
    }

    /**
     * 编辑商品属性
     *
     * @param prodId 产品Id
     */
    @RequestMapping("prodEdit")
    public String prodEdit(String prodId, Model model) {
        Prod prod = prodSV.load(prodId);
        model.addAttribute("prod", prod);
        List<BaseDict> dictList = baseDictSV.queryBaseDictByType(BaseDict.DictType.PROD_TYPE.getKey());
        model.addAttribute("dictList", dictList);

        String[][] moneyOptionList = new String[6][3];
        if (prod.getOtherMoneyState() == 1 && prod.getOtherMoneyOption() != null) {
            moneyOptionList = JSON.parseObject(prod.getOtherMoneyOption(), new TypeReference<String[][]>() {
            });
        }
        model.addAttribute("moneyOptionList", moneyOptionList);
        if (prod.getGprsNo().startsWith("0")) {
            model.addAttribute("maxPulse", 2);
        } else if (prod.getGprsNo().startsWith("2")) {
            model.addAttribute("maxPulse", 1);
        }
        return "prodEdit";
    }

    /**
     * 更新
     *
     * @param prod 产品表
     */
    @RequestMapping("prodUpdate")
    public ExtMsg prodUpdate(Prod prod, String[] nameTeam, String[] moneyTeam, String[] cvTeam, ModelMap modelMap) {
        if (prod.getRunTime() == null || prod.getRunTime().equals("")) {
            prod.setRunTime(0);
        }
        try {
            //可选金额
            String[][] moneyStr = new String[6][3];
            for (int i = 0; i < 6; i++) {
                moneyStr[i][0] = nameTeam[i];
                moneyStr[i][1] = moneyTeam[i];
                moneyStr[i][2] = cvTeam[i];
            }
            String otherMoneyOption = JSONArray.toJSONString(moneyStr);
            prod.setOtherMoneyOption(otherMoneyOption);
            prodSV.update(prod);
        } catch (Exception e) {
            modelMap.clear();
            return ExtMsg.fail(e.getMessage());
        }
        modelMap.clear();
        return ExtMsg.success("更新成功");
    }

    @RequestMapping("uploadLogo")
    public ExtMsg uploadLogo(String imgdata, String filename, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        String fileType = filename.substring(filename.lastIndexOf(".") + 1);
        if ("gif".equalsIgnoreCase(fileType) || "jpg".equalsIgnoreCase(fileType) || "jpeg".equalsIgnoreCase(fileType) || "png".equalsIgnoreCase(fileType)) {
            String newName1 = identifierSV.getUniqueId() + filename.substring(filename.lastIndexOf("."));//question+时
            String relativeFilePath1 = "/upload/prod/model/10" + biz.getBizNo() + "/" + Formatter.formatShortDate(System.currentTimeMillis()) + "/" + newName1;
            boolean ok = FileUtil.saveImgdataFile(request, imgdata, relativeFilePath1);
            if (ok) {
                return ExtMsg.success(relativeFilePath1);
            } else {
                return ExtMsg.fail("上传失败");
            }
        } else {
            return ExtMsg.fail("上传文件类型有误");
        }
    }

    @RequestMapping("uploadProdLogo")
    public ExtMsg uploadProdLogo(String imgdata, String filename, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        String fileType = filename.substring(filename.lastIndexOf(".") + 1);
        if ("gif".equalsIgnoreCase(fileType) || "jpg".equalsIgnoreCase(fileType) || "jpeg".equalsIgnoreCase(fileType) || "png".equalsIgnoreCase(fileType)) {
            String newName1 = identifierSV.getUniqueId() + filename.substring(filename.lastIndexOf("."));//question+时
            String relativeFilePath1 = "/upload/prod/10" + biz.getBizNo() + "/" + Formatter.formatShortDate(System.currentTimeMillis()) + "/" + newName1;
            boolean ok = FileUtil.saveImgdataFile(request, imgdata, relativeFilePath1);
            if (ok) {
                return ExtMsg.success(relativeFilePath1);
            } else {
                return ExtMsg.fail("上传失败");
            }
        } else {
            return ExtMsg.fail("上传文件类型有误");
        }
    }

    /**
     * 二维码生成
     *
     * @param gprsNo 模块编号
     * @return
     */
    @RequestMapping("qrGen")
    public ExtMsg qrGen(String gprsNo, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        String relativeFilePath = "/upload/prod/qr/" + biz.getBizNo();
        String imgPath = request.getSession().getServletContext().getRealPath("/") + relativeFilePath;
        try {
//            String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
//                    QRCodeUtil.encode(url+"/ord/ord?gprsNo=" + gprsNo, imgPath, gprsNo + ".png");
            String url = "http://www.bianyichong.net";
            QRCodeUtil.encode(url + "/ord/ord?gprsNo=" + gprsNo, imgPath, gprsNo + ".png");
            prodSV.updateProdQrSt(biz.getBizNo(), gprsNo, 1);
        } catch (Exception e) {
            e.printStackTrace();
            return ExtMsg.fail("生成失败");
        }
        return ExtMsg.success();
    }

    /**
     * 二维码下载
     *
     * @param gprsNo 模块编号
     */
    @RequestMapping("qrGenDown")
    public void qrGenDown(String gprsNo, HttpServletRequest request, HttpServletResponse response) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        String relativeFilePath = "/upload/prod/qr/" + biz.getBizNo();
        String imgPath = request.getSession().getServletContext().getRealPath("/") + relativeFilePath;
        String filename = imgPath + "/" + gprsNo + ".png";
        response.setHeader("content-disposition", "attachment;filename=" + gprsNo + ".png");
        FileInputStream in = null;
        ServletOutputStream out = null;
        try {
            in = new FileInputStream(filename);
            int len = 0;
            byte buffer[] = new byte[1024];
            out = response.getOutputStream();
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping("prodGprsArgsSet")
    public String prodGprsArgs(Integer all, String gprsNoList, Model model) {
        model.addAttribute("all", all);
        model.addAttribute("gprsNoList", gprsNoList);
        if (gprsNoList != null && gprsNoList.startsWith("_2")) {
            GprsOtp gprsOtp = gprsOtpSV.loadByGprsNo(gprsNoList.replace("_", ""));
            if (gprsOtp != null) {

                model.addAttribute("movingFactor", gprsOtp.getMovingFactor());
            }
            return "prodGprsArgs";
        }


        if (all != null && all == 0) {
            if (gprsNoList != null) {
                String[] gprsNoArray = gprsNoList.split("_");
                if (gprsNoArray.length == 2) {
                    msgSV.heartReset(gprsNoList, "");
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    msgSV.insertCoinInterval(gprsNoList, "");
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    msgSV.insertCoinWth(gprsNoList, "");
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    msgSV.insertTotalTimePerCoin(gprsNoList, "");

                }
            }
        }
        return "prodGprsArgs";
    }


    @RequestMapping("prodSpArgsSet")
    public String prodSpArgsSet(Integer all, String gprsNoList, String gprsType, Model model) {
        model.addAttribute("gprsType", gprsType);
        if (gprsType.equals("1")) {

            if (all != null && all == 1) {
                model.addAttribute("argsEnumArray", ProdSpArgs.ArgsEnum.values());
                model.addAttribute("all", all);
            } else {
                if (gprsNoList != null) {
                    String[] prodNoArray = gprsNoList.split("_");
                    if (prodNoArray.length == 2) {
                        msgSV.reqProdArgs(prodNoArray[1]);
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Prod prod = prodSV.loadByGprsNo(prodNoArray[1]);
                        List<ProdSpArgs> prodSpArgsList = prodSV.queryProdSpArgs(prod.getProdNo());
                        if (prodSpArgsList == null || prodSpArgsList.isEmpty()) {
                            model.addAttribute("argsEnumArray", ProdSpArgs.ArgsEnum.values());
                            //                        model.addAttribute("all", all);
                        } else {
                            model.addAttribute("crtime", prodSpArgsList.get(0).getCrtime());
                        }
                        model.addAttribute("prodSpArgsList", prodSpArgsList);
                    }
                }
                model.addAttribute("all", 0);
                model.addAttribute("gprsNoList", gprsNoList);
            }
        } else if (gprsType.equals("6")) {
            model.addAttribute("all", all);
            model.addAttribute("gprsNoList", gprsNoList);
        } else if (gprsType.equals("3")) {
            model.addAttribute("all", all);
            System.out.println("模块编号：" + gprsNoList);
            model.addAttribute("gprsNoList", gprsNoList);
        }
        return "prodSpArgs";
    }

    /**
     * 产品参数设置
     *
     * @param all 是否应用全部产品
     */
    @RequestMapping("prodSpArgsRest")
    public ExtMsg prodSpArgsRest(Integer all, String gprsNoList, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        Map<String, String[]> params = request.getParameterMap();
        if (params != null && params.size() > 0) {
            Iterator<String> keys = params.keySet().iterator();
            String name = "";
            StringBuilder result = new StringBuilder("");
            while (keys.hasNext()) {
                name = keys.next();
                if (!"all".equals(name) && !"gprsNoList".equals(name) && params.get(name) != null && !"null".equals(params.get(name))) {
                    String[] args = params.get(name);
                    if (args != null && args.length > 0) {
                        result.append(StringUtils.leftPad(args[0], 3, "0"));
                    } else {
                        return ExtMsg.fail("参数无效");
                    }
                    System.out.println(params.get(name)[0].toString());
                }
            }
            int length = result.length();
            int vc = 0;
            for (int i = 0; i < length; i++) {
                vc += Integer.parseInt(StringUtils.mid(result.toString(), i, 1));
            }
            String vcStr = String.valueOf(vc).substring(String.valueOf(vc).length() - 1);
            result = result.append(vcStr);
            if (all != null && all == 1) {
                Map<String, Object> params2 = new HashMap<String, Object>();
                params2.clear();
                params2.put("bizNo", biz.getBizNo());
                List<Prod> prodList = prodSV.queryProdOnline(params2, 0, 200);
                if (prodList != null && !prodList.isEmpty()) {
                    for (Prod prod : prodList) {
                        if (StringUtils.isNotEmpty(prod.getGprsNo()) && prod.getGprsNo().startsWith("1")) {
                            params2.put("gprsNo", prod.getGprsNo());
                            params2.put("args", result.toString());
                            msgSV.restProdArgs(params2);
                            params2.clear();
                        }
                    }
                }
            } else {
                if (gprsNoList != null) {
                    Map<String, Object> params2 = new HashMap<String, Object>();
                    String[] gprsNoArray = gprsNoList.split("_");
                    for (String gprsNo : gprsNoArray) {
                        if (StringUtils.isNotEmpty(gprsNo) && gprsNo.startsWith("1")) {
                            params2.put("gprsNo", gprsNo);
                            params2.put("args", result.toString());
                            msgSV.restProdArgs(params2);
                            params2.clear();
                        }
                    }
                }
            }
        }

        return ExtMsg.success("设置成功");
    }

    /**
     * 刷卡扣费参数设置
     *
     * @param all 是否应用全部产品
     */
    @RequestMapping("cardMoneySet")
    public ExtMsg cardMoneySet(Integer all, String gprsNoList, String gprsType, String cardMoney, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        if (all != null && all == 1) {
            Map<String, Object> params2 = new HashMap<String, Object>();
            params2.clear();
            params2.put("bizNo", biz.getBizNo());
            params2.put("gprsType", (gprsType == null || gprsType.equals("")) ? 0 : gprsType);
            List<GprsModel> gprsModelList = gprsModelSV.queryGprsModel(params2, -1, -1);
            if (gprsModelList != null && !gprsModelList.isEmpty()) {
                for (GprsModel gprsModel : gprsModelList) {
                    if (StringUtils.isNotEmpty(gprsModel.getGprsNo()) && gprsModel.getGprsNo().startsWith("6")) {
                        params2.put("cmd", "GS05");
                        params2.put("gprsNo", gprsModel.getGprsNo());
                        params2.put("money", StringUtils.leftPad(cardMoney, 3, "0") + RandomUtil.checkCode(cardMoney));
                        msgSV.sendCmd("GS05", gprsModel.getGprsNo(), params2.toString());

                        params2.clear();
                    }
                }
            }
        } else {
            if (gprsNoList != null) {
                Map<String, Object> params2 = new HashMap<String, Object>();
                String[] gprsNoArray = gprsNoList.split("_");
                for (String gprsNo : gprsNoArray) {
                    if (StringUtils.isNotEmpty(gprsNo) && gprsNo.startsWith("6")) {
                        params2.put("gprsNo", gprsNo);
                        params2.put("bizNo", biz.getBizNo());
                        /*List<GprsModel> gprsModelList = gprsModelSV.queryGprsModel(params2, -1,-1);
                        if (gprsModelList==null||gprsModelList.size()<1){
                            return ExtMsg.fail("您没有权限进行设置！");
                        }*/
                        params2.put("cmd", "GS05");
                        params2.put("money", StringUtils.leftPad(cardMoney, 3, "0") + RandomUtil.checkCode(cardMoney));
                        msgSV.sendCmd("GS05", gprsNo, params2.toString());

                        params2.clear();
                    }
                }
            }
        }

        return ExtMsg.success("设置成功");
    }


    /**
     * 充电时间设置
     *
     * @param all 是否应用全部产品
     */
    @RequestMapping("timeSet")
    public ExtMsg timeSet(Integer all, String gprsNoList, String gprsType, String oneCardTime,
                          String twoCardTime, String oneWXTime, String twoWXTime, String totalTime, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        if (all != null && all == 1) {
            Map<String, Object> params2 = new HashMap<String, Object>();
            params2.clear();
            params2.put("bizNo", biz.getBizNo());
            params2.put("gprsType", (gprsType == null || gprsType.equals("")) ? 0 : gprsType);
            List<GprsModel> gprsModelList = gprsModelSV.queryGprsModel(params2, -1, -1);
            if (gprsModelList != null && !gprsModelList.isEmpty()) {
                for (GprsModel gprsModel : gprsModelList) {
                    if (StringUtils.isNotEmpty(gprsModel.getGprsNo()) && gprsModel.getGprsNo().startsWith("6")) {
                        params2.put("gprsNo", gprsModel.getGprsNo());
                        params2.put("cmd", "GS07");
                        params2.put("oneCardTime", StringUtils.leftPad(oneCardTime, 3, "0"));
                        params2.put("twoCardTime", StringUtils.leftPad(twoCardTime, 3, "0"));
                        params2.put("oneWXTime", StringUtils.leftPad(oneWXTime, 3, "0"));
                        params2.put("twoWXTime", StringUtils.leftPad(twoWXTime, 3, "0"));
                        params2.put("totalTime", StringUtils.leftPad(totalTime, 3, "0") + RandomUtil.checkCode(oneCardTime + twoCardTime + oneWXTime + twoWXTime + totalTime));
                        msgSV.sendCmd("GS07", gprsModel.getGprsNo(), params2.toString());

                        params2.clear();
                    }
                }
            }
        } else {
            if (gprsNoList != null) {
                Map<String, Object> params2 = new HashMap<String, Object>();
                String[] gprsNoArray = gprsNoList.split("_");
                for (String gprsNo : gprsNoArray) {
                    if (StringUtils.isNotEmpty(gprsNo) && gprsNo.startsWith("6")) {
                        params2.put("gprsNo", gprsNo);
                        params2.put("bizNo", biz.getBizNo());
                        /*List<GprsModel> gprsModelList = gprsModelSV.queryGprsModel(params2, -1,-1);
                        if (gprsModelList==null||gprsModelList.size()<1){
                            return ExtMsg.fail("您没有权限进行设置！");
                        }*/
                        params2.put("cmd", "GS07");
                        params2.put("oneCardTime", StringUtils.leftPad(oneCardTime, 3, "0"));
                        params2.put("twoCardTime", StringUtils.leftPad(twoCardTime, 3, "0"));
                        params2.put("oneWXTime", StringUtils.leftPad(oneWXTime, 3, "0"));
                        params2.put("twoWXTime", StringUtils.leftPad(twoWXTime, 3, "0"));
                        params2.put("totalTime", StringUtils.leftPad(totalTime, 3, "0") + RandomUtil.checkCode(oneCardTime + twoCardTime + oneWXTime + twoWXTime + totalTime));
                        msgSV.sendCmd("GS07", gprsNo, params2.toString());

                        params2.clear();
                    }
                }
            }
        }

        return ExtMsg.success("设置成功");
    }


    /**
     * 功率参数设置
     *
     * @param all 是否应用全部产品
     */
    @RequestMapping("wattSet")
    public ExtMsg wattSet(Integer all, String gprsNoList, String gprsType, String oneWatt, String cv, String twoWatt, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        if (all != null && all == 1) {
            Map<String, Object> params2 = new HashMap<String, Object>();
            params2.clear();
            params2.put("bizNo", biz.getBizNo());
            params2.put("gprsType", (gprsType == null || gprsType.equals("")) ? 0 : gprsType);
            List<GprsModel> gprsModelList = gprsModelSV.queryGprsModel(params2, -1, -1);
            if (gprsModelList != null && !gprsModelList.isEmpty()) {
                for (GprsModel gprsModel : gprsModelList) {
                    if (StringUtils.isNotEmpty(gprsModel.getGprsNo()) && gprsModel.getGprsNo().startsWith("6")) {
                        params2.put("gprsNo", gprsModel.getGprsNo());
                        params2.put("cmd", "GS09");
                        params2.put("oneWatt", StringUtils.leftPad(oneWatt, 3, "0"));
                        params2.put("cv", cv);
                        params2.put("twoWatt", StringUtils.leftPad(twoWatt, 3, "0") + RandomUtil.checkCode(oneWatt + cv + twoWatt));
                        msgSV.sendCmd("GS09", gprsModel.getGprsNo(), params2.toString());

                        params2.clear();
                    }
                }
            }
        } else {
            if (gprsNoList != null) {
                Map<String, Object> params2 = new HashMap<String, Object>();
                String[] gprsNoArray = gprsNoList.split("_");
                for (String gprsNo : gprsNoArray) {
                    if (StringUtils.isNotEmpty(gprsNo) && gprsNo.startsWith("6")) {
                        params2.put("gprsNo", gprsNo);
                        params2.put("bizNo", biz.getBizNo());
                        List<GprsModel> gprsModelList = gprsModelSV.queryGprsModel(params2, -1, -1);
                       /* if (gprsModelList==null||gprsModelList.size()<1){
                            return ExtMsg.fail("您没有权限进行设置！");
                        }*/
                        params2.put("cmd", "GS09");
                        params2.put("oneWatt", StringUtils.leftPad(oneWatt, 3, "0"));
                        params2.put("cv", cv);
                        params2.put("twoWatt", StringUtils.leftPad(twoWatt, 3, "0") + RandomUtil.checkCode(oneWatt + cv + twoWatt));
                        msgSV.sendCmd("GS09", gprsNo, params2.toString());

                        params2.clear();
                    }
                }
            }
        }

        return ExtMsg.success("设置成功");
    }


    /**
     * 查询故障模块列表
     *
     * @param pager
     * @param gprsNo   模块编号
     * @param gprsType 模块类型
     * @param request
     * @return
     */
    @RequestMapping("prodBugRptQuery")
    public String prodBugRptQuery(Pager pager, String gprsNo, String gprsType, HttpServletRequest request) {

        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", biz.getBizNo());
        params.put("gprsNo", gprsNo);
        params.put("gprsType", gprsType);
        pager.setAction("/prod/prodBugRptQuery");
        pager.setParams(params);
        List<Prod> prodList = prodSV.queryProdBugRpt(params, pager.getRowStart(), pager.getPageSize());
        pager.setRecordList(prodList);
        pager.setTotalRow(prodSV.countProdBugRpt(params));
        return "prodBugList";
    }

    /**
     * 实时查询故障
     *
     * @param request
     * @return
     */
    @RequestMapping("prodBugRptQueryMQ")
    public ExtMsg prodBugRptQueryMQ(Pager pager, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        int countCurrentDayProdCoinRptLogTimes = prodSV.countCurrentDayProdCoinRptLogTimes(biz.getBizNo(), ProdCoinRptLog.St.BugRpt.getKey());
        if (countCurrentDayProdCoinRptLogTimes > 2) {
            return ExtMsg.fail("每日手动获取次数不能超过3次");
        } else {
            List<Prod> prodList1 = prodSV.queryOnlineProdByGprsType(biz.getBizNo(), "1");
            for (Prod prod : prodList1) {
                msgSV.reqBugCmd(prod.getGprsNo());
            }
            List<Prod> prodList6 = prodSV.queryOnlineProdByGprsType(biz.getBizNo(), "6");
            for (Prod prod : prodList6) {
                msgSV.sendCmd("GS01", prod.getGprsNo(), null);
            }
            prodSV.createProdCoinRptLog(biz.getBizNo(), ProdCoinRptLog.St.BugRpt.getKey());
        }
        return ExtMsg.success("成功！");

    }

    /**
     * 产品状态查询
     *
     * @param prodId 产品ID
     */
    @RequestMapping("prodBugRpt")
    public String prodBugRpt(String prodId, Model model) {
        Prod prod = prodSV.load(prodId);
        model.addAttribute("prodId", prodId);
        model.addAttribute("gprsNo", prod.getGprsNo());
        ProdInstlPos prodInstlPos = prodSV.loadProdInstlPos(prod.getProdNo());

        if (prod != null) {
            model.addAttribute("online", true);
            if (prod.getGprsNo().startsWith("6")) {
                String msg = "";
                ProdBugRpt prodBugRpt = prodSV.loadProdBugRpt(prod.getProdNo());
                String result = prodBugRpt.getResult();
                for (int i = 0; i < result.length() - 1; i += 2) {
                    msg += result.substring(i, i + 1) + ":";
                    String state = result.substring(i + 1, i + 2);
                    if (state != null && state.equals("0")) {
                        msg += "无故障;";
                    } else if (state != null && state.equals("1")) {
                        msg += "有故障;";
                    } else {
                        msg += "指令无法解析;";
                    }
                }
                String[] strings_msg = msg.split(";");
                model.addAttribute("msgState", true);
                model.addAttribute("msgStr", strings_msg);
                return "prodBugRpt2";

            }

            if (prodInstlPos != null) {
                model.addAttribute("pos", prodInstlPos.getProvName() + prodInstlPos.getCityName() + prodInstlPos.getDistName() + prodInstlPos.getAddr());
            }
        }

        return "prodBugRpt";
    }

    /**
     * 产品状态查询
     *
     * @param prodId 产品ID
     */
    @RequestMapping("prodStSearch")
    public Map<String, String[]> prodStSearch(String prodId) {
        Prod prod = prodSV.load(prodId);
        Map<String, String[]> params = new HashMap<String, String[]>();
        if (prod != null) {
            ProdBugRpt prodBugRpt = prodSV.loadProdBugRpt(prod.getProdNo());
            if (prodBugRpt != null) {
                String[] array = new String[5];
                String result = prodBugRpt.getResult();
                int length = result.length();
                for (int i = 0; i < length; i++) {
                    array[i] = StringUtils.mid(result, i, 1);
                }
                params.put("params", array);
            }
        }
        return params;
    }

    /**
     * 添加或修改位置模板
     *
     * @param prodInstlPosModel
     * @param modelMap
     * @param request
     * @return
     */

    @RequestMapping("prodInstlPosModel")
    public ExtMsg prodInstlPosModel(ProdInstlPosModel prodInstlPosModel, ModelMap modelMap, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        if (StringUtils.isEmpty(prodInstlPosModel.getId())) {
            prodInstlPosModel.setBizNo(biz.getBizNo());
            prodInstlPosModel.setManager(biz.getName());
            prodInstlPosModel.setTel(biz.getTel());
            prodInstlPosModelSV.create(prodInstlPosModel);
            modelMap.clear();
            return ExtMsg.success("位置模板添加成功!");
        } else {
            prodInstlPosModelSV.update(prodInstlPosModel);
            modelMap.clear();
            return ExtMsg.success("位置模板修改成功!");
        }

    }

    /**
     * 手动获取设备收入
     */
    @RequestMapping("getBizCoinRpt")
    public ExtMsg getBizCoinRpt(HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        int countCurrentDayProdCoinRptLogTimes = prodSV.countCurrentDayProdCoinRptLogTimes(biz.getBizNo(), ProdCoinRptLog.St.CoinRpt.getKey());
        if (countCurrentDayProdCoinRptLogTimes > 2) {
            return ExtMsg.fail("每日手动获取次数不能超过3次");
        } else {
            List<Prod> prods = prodSV.queryProdOnlineByBizNo(biz.getBizNo());
            for (Prod prod : prods) {
                msgSV.reqEarn(prod.getGprsNo());
            }
            prodSV.createProdCoinRptLog(biz.getBizNo(), ProdCoinRptLog.St.CoinRpt.getKey());
        }
        return ExtMsg.success();
    }

    /**
     * 充电时间设置
     *
     * @param all 是否应用全部产品
     */
    @RequestMapping("cyTimeSet")
    public ExtMsg cyTimeSet(Integer all, String gprsNoList, String gprsType, String largePowerTime,
                            String middenPowerTime, String smallPowerTime, String limitPower, String preMoney, String voice, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        if (all != null && all == 1) {
            Map<String, Object> params2 = new HashMap<String, Object>();
            params2.clear();
            params2.put("bizNo", biz.getBizNo());
            params2.put("gprsType", (gprsType == null || gprsType.equals("")) ? 0 : gprsType);
            List<GprsModel> gprsModelList = gprsModelSV.queryGprsModel(params2, -1, -1);
            if (gprsModelList != null && !gprsModelList.isEmpty()) {
                for (GprsModel gprsModel : gprsModelList) {
                    if (StringUtils.isNotEmpty(gprsModel.getGprsNo()) && gprsModel.getGprsNo().startsWith("3")) {
                        System.out.println("大功率时间" + largePowerTime);
                        largePowerTime = shiToHex2(largePowerTime);
                        middenPowerTime = shiToHex2(middenPowerTime);
                        smallPowerTime = shiToHex2(smallPowerTime);
                        limitPower = shiToHex(limitPower);
                        preMoney = shiToHex2(String.valueOf(Integer.parseInt(preMoney) * 100));
                        voice = shiToHex(voice);
                        String length = shiToHex(String.valueOf((largePowerTime.length() + middenPowerTime.length() + smallPowerTime.length() + limitPower.length() + preMoney.length() + voice.length()) / 2));
                        String checkBit = add("2A" + "41" + length + largePowerTime + middenPowerTime + smallPowerTime + limitPower + preMoney + voice);
                        params2.put("gprsNo", gprsModel.getGprsNo());
                        params2.put("cmd", "CY41");
                        params2.put("length", length);
                        params2.put("largePowerTime", largePowerTime);
                        params2.put("middenPowerTime", middenPowerTime);
                        params2.put("smallPowerTime", smallPowerTime);
                        params2.put("limitPower", limitPower);
                        params2.put("preMoney", preMoney);
                        params2.put("voice", voice);
                        params2.put("checkBit", checkBit);
                        System.out.println("设置参数：" + params2.toString());
                        msgSV.sendCmd("CY41", gprsModel.getGprsNo(), JSON.toJSONString(params2));
                        params2.clear();
                    }
                }
            }
        } else {
            if (gprsNoList != null) {
                Map<String, Object> params2 = new HashMap<String, Object>();
                String[] gprsNoArray = gprsNoList.split("_");
                for (String gprsNo : gprsNoArray) {
                    if (StringUtils.isNotEmpty(gprsNo) && gprsNo.startsWith("3")) {
                        largePowerTime = shiToHex2(largePowerTime);
                        middenPowerTime = shiToHex2(middenPowerTime);
                        smallPowerTime = shiToHex2(smallPowerTime);
                        limitPower = shiToHex(limitPower);
                        preMoney = shiToHex2(preMoney);
                        voice = shiToHex(voice);
                        String length = shiToHex(String.valueOf((largePowerTime.length() + middenPowerTime.length() + smallPowerTime.length() + limitPower.length() + preMoney.length() + voice.length()) / 2));
                        String checkBit = add("2A" + "41" + length + largePowerTime + middenPowerTime + smallPowerTime + limitPower + preMoney + voice);
                        params2.put("gprsNo", gprsNo);
                        params2.put("bizNo", biz.getBizNo());
                        params2.put("cmd", "CY41");
                        params2.put("length", length);
                        params2.put("checkBit", checkBit);
                        params2.put("largePowerTime", largePowerTime);
                        params2.put("middenPowerTime", middenPowerTime);
                        params2.put("smallPowerTime", smallPowerTime);
                        params2.put("limitPower", limitPower);
                        params2.put("preMoney", preMoney);
                        params2.put("voice", voice);
                        msgSV.sendCmd("CY41", gprsNo, JSON.toJSONString(params2));
                        params2.clear();
                    }
                }
            }
        }
        return ExtMsg.success("设置成功");
    }

    /**
     * @param s 要转化的十进制数据
     * @return 标准十六进制数据
     */
    public String shiToHex2(String s) {
        String hexString = Integer.toHexString(Integer.parseInt(s));
        if (hexString.length() < 4) {
            if (hexString.length() == 1) {
                hexString = "000" + hexString;
            } else if (hexString.length() == 2) {
                hexString = "00" + hexString;
            } else if (hexString.length() == 3) {
                hexString = "0" + hexString;
            }
        }
        return hexString;
    }

    /**
     * @param s 要转化的十进制数据
     * @return 标准十六进制数据
     */
    public String shiToHex(String s) {
        String hexString = Integer.toHexString(Integer.parseInt(s));
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
            return hexString;
        }
        return hexString;
    }


    /**
     * 得到校验字
     *
     * @param s 二进制字符串
     * @return 校验字
     */
    public String add(String s) {
        byte[] bytes = hexStringToBytes(s);
        char c = 0;
        for (byte b : bytes) {
            c += b;
        }
        String s1 = Integer.toHexString(c);
        if (s1.length() > 2) {
            s1 = s1.substring(s1.length() - 2);
        }
        return s1;
    }

    /**
     * 二进制字符串转字节数组
     *
     * @param hexString 二进制字符串
     * @return 字节数组
     */
    public static byte[] hexStringToBytes(String hexString) {
        hexString = hexString.replaceAll(" ", ""); // 去空格
        if ((hexString == null) || (hexString.equals(""))) {
            return null;
        }
        hexString = hexString.toUpperCase(); // 字符串中的所有字母都被转化为大写字母
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; ++i) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[(pos + 1)]));
        }
        return d;
    }

    /**
     * char转字节数
     *
     * @param c char
     * @return 字节数
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
