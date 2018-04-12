package com.zzwdkj.biz.ctrl;

import com.alibaba.fastjson.JSON;
import com.zzwdkj.base.BaseCtrl;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.biz.entity.Biz;
import com.zzwdkj.biz.entity.BizAdvise;
import com.zzwdkj.biz.entity.BizWx;
import com.zzwdkj.biz.entity.BizWxFocus;
import com.zzwdkj.biz.service.BizSV;
import com.zzwdkj.biz.service.BizWxFocusSV;
import com.zzwdkj.biz.service.BizWxSV;
import com.zzwdkj.common.*;
import com.zzwdkj.common.weixin.WeiXinUtil;
import com.zzwdkj.sys.entity.SysAcct;
import com.zzwdkj.sys.entity.SysRes;
import com.zzwdkj.sys.service.SysAcctSV;
import com.zzwdkj.sys.service.SysResSV;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * biz, 商家Ctrl
 *
 * @author guoxianwei  2016-09-07 15:01:26
 */
@Controller
@RequestMapping("/biz")
public class BizCtrl extends BaseCtrl {

    @Resource
    private BizSV bizSV;
    @Resource
    private BizWxSV bizWxSV;
    @Resource
    private BizWxFocusSV bizWxFocusSV;
    @Resource
    private SysResSV sysResSV;
    @Resource
    private SysAcctSV sysAcctSV;
    @Resource
    private IdentifierSV identifierSV;

    /**
     * 商家管理
     */
    @RequestMapping("query")
    public String query(Pager<Biz> pager, String name, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("likeName", name);
        if (!biz.isMainAcct()) {
            params.put("acct", biz.getAcct());
        }
        if (!biz.isAdmin()){

            params.put("parBizNo", biz.getBizNo());
        }
        pager.setAction("/biz/query");
        List<Biz> bizList = bizSV.queryBizJoinAcct(params, pager.getRowStart(), pager.getPageSize());
        params.put("isAdmin",biz.isAdmin());
        pager.setParams(params);
        pager.setRecordList(bizList);
        pager.setTotalRow(bizSV.countBizJoinAcct(params));
        return "sjxx/sellerManage/bizList";
    }

    /**
     * 基本信息
     */
    @RequestMapping("/view")
    public String view(Model model, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        biz = bizSV.loadBiz(biz.getId());
        model.addAttribute("biz", biz);
        return "sjxx/basicInfo/bizInfo";
    }

    /**
     * 建议
     */
    @RequestMapping("/advise")
    public String advise(Model model) {
        return "sjxx/suggest/bizAdvise";
    }

    /**
     * 支付设置
     */
    @RequestMapping("/paySet")
    public String paySet(Model model) {
        return "bizPaySet";
    }
    /**
     * 广告设置
     */
    @RequestMapping("/adSet")
    public String adSet(Model model) {
        return "sjxx/adSet/bizAdSet";
    }


    /**
     * 密码重置
     */
    @RequestMapping("/pwdReset")
    public String pwdReset(Model model) {
        return "sjxx/pwdUpd/bizPwdReset";
    }

    /**
     * 子账户管理
     */
    @RequestMapping("/subUserList")
    public String subUserList(Pager<SysAcct> pager, String acct, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("acct", acct);
        if (!biz.isMainAcct()) {
            params.put("acct", biz.getAcct());
        }
        pager.setAction("/biz/subUserList");
        pager.setParams(params);
        params.put("bizNo", biz.getBizNo());
        List<SysAcct> ordList = sysAcctSV.querySysAcct(params, pager.getRowStart(), pager.getPageSize());
        pager.setRecordList(ordList);
        pager.setTotalRow(sysAcctSV.countSysAcct(params));
        return "sjxx/accountManage/bizSubUserList";
    }

    /**
     * 子账户添加
     */
    @RequestMapping("/subUserAdd")
    public String subUserAdd(Model model) {
        return "bizSubUserAdd";
    }

    /**
     * 会员管理
     */
    @RequestMapping("/mbrList")
    public String mbrList(Model model) {
        return "mbrList";
    }


    /**
     * 支付设置-alipay
     */
    @RequestMapping("/payAlipay")
    public String payAlipay(Model model) {
        return "bizPayAlipay";
    }

    /**
     * 支付设置-微信
     */
    @RequestMapping("/payWx")
    public String payWx(Model model, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        BizWx bizWx = bizWxSV.loadByBizNo(biz.getBizNo());
        List<String> templates = new ArrayList<String>();
        if (bizWx != null && bizWx.getAppId() != null) {
            bizWx.setBizName(biz.getName());
            Map map_tlp = JSON.parseObject(bizWx.getTemplateId(),Map.class);

            if (map_tlp!=null){
                for (int i=0;i<map_tlp.size();i++){
                    templates.add(map_tlp.get((i+1)+"").toString());
                }
            }
            bizWx.setTemplateId(JSON.toJSONString(map_tlp));
        }


        model.addAttribute("templates",templates);
        model.addAttribute("bizWx", bizWx);
        return "sjxx/paySet/bizPayWx";
    }
    /**
     * 支付设置-微信强制关注
     */
    @RequestMapping("/payWxFocus")
    public String payWxFocus(Model model, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        BizWxFocus bizWxFocus = bizWxSV.loadBizWxFocusByBizNo(biz.getBizNo());
        if (bizWxFocus != null && bizWxFocus.getAppId() != null) {
            bizWxFocus.setName(biz.getName());
        }
        model.addAttribute("bizWxFocus", bizWxFocus);
        return "bizPayWxFocus";
    }

    /**
     * 支付设置-微信
     */
    @RequestMapping("/removeCertSkey")
    public ExtMsg removeCertSkey(HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        bizWxSV.removeCertSkey(biz.getBizNo(), biz.getAcct());
        return ExtMsg.success();
    }

    /**
     * 编辑页面
     */
    @RequestMapping("/edit")
    public String edit(Model model, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        biz = bizSV.loadBiz(biz.getId());
        model.addAttribute("biz", biz);
        return "sjxx/basicInfo/bizInfoEdit";
    }

    /**
     * 更新
     *
     * @param biz 商家
     */
    @RequestMapping("/update")
    public String update(Biz biz, MultipartFile file, HttpServletRequest request) {
        try {
          /*  if (file != null) {
                Biz biz2 = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
                FileUtil.saveFile(request, file, "/upload/biz/log/" + biz2.getBizNo() + ".jpg");
                biz.setLogo("/upload/biz/log/" + biz2.getBizNo() + ".jpg");
            }*/
            bizSV.updateBiz(biz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "sjxx/basicInfo/bizInfo";
    }

    /**
     * 删除
     *
     * @param data 主键
     */
    @RequestMapping("remove")
    public ExtMsg remove(String data) {
        Biz biz = GsonUtil.fromJson(data, Biz.class);
        bizSV.removeBiz(biz.getId());
        return ExtMsg.success("删除成功");
    }

    /**
     * 申诉意见创建
     */
    @RequestMapping("bizAdviseAdd")
    public ExtMsg bizAdviseAdd(String advise, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        BizAdvise bizAdvise = new BizAdvise(biz.getBizNo(), biz.getAcct(), advise);
        bizSV.createBizAdvise(bizAdvise);
        return ExtMsg.success();
    }

    /**
     * 修改当前账户密码
     */
    @RequestMapping("bizPwdUp")
    public ExtMsg bizPwdUp(String newPwd, String oldPwd, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        SysAcct sysAcct = sysAcctSV.loadSysAcctByAcct(biz.getAcct());
        if (sysAcct.getPwd().equals(MD5Util.MD5(oldPwd))) {
            sysAcctSV.upPwd(biz.getBizNo(), biz.getAcct(), MD5Util.MD5(oldPwd), MD5Util.MD5(newPwd));
        } else {
            return ExtMsg.fail("旧密码不正确");
        }
        return ExtMsg.success();
    }

    /**
     * 微信支付设置
     */
    @RequestMapping("crOrUpBizPayWx")
    public String crOrUpBizPayWx(BizWx bizWx,String[] templates, MultipartFile file,MultipartFile file2, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        if (file != null&&!file.isEmpty()) {
            String fname=System.currentTimeMillis()+WeiXinUtil.createNonceStr(8);
            FileUtil.saveFile(request, file, "/upload/biz/cert/zip/" + fname + ".zip");
            bizWx.setApiCert("/upload/biz/cert/zip/" + fname + ".zip");
        }
        if (file2 != null&&!file2.isEmpty()) {
            String fname=file2.getOriginalFilename().split("\\.")[0];
            FileUtil.saveFile(request, file2, "/" + fname + ".txt");
            bizWx.setApiCertSkey("/" + fname + ".txt");
        }
        /*
        //API证书秘钥（txt文本的名称） txt文件
        BizWx bizWx_old=bizWxSV.load(bizWx.getId());
        if(StringUtils.isNotEmpty(bizWx.getId())&&!bizWx_old.getApiCertSkey().equals(bizWx.getApiCertSkey())){
            try {
                String basePath = request.getSession().getServletContext()
                        .getRealPath("/");
                String[] keys=bizWx.getApiCertSkey().replaceAll(".txt","").split("_");
                FileOutputStream fos = new FileOutputStream(basePath+bizWx.getApiCertSkey().replaceAll(".txt","")+".txt");
                fos.write(keys[keys.length-1].getBytes(), 0, keys[keys.length-1].getBytes().length);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        */

        if(templates!=null){
            Map<String ,Object> map_tlp=new HashMap<String ,Object>();
            for (int i=0;i<templates.length;i++){
                map_tlp.put((i+1)+"",templates[i]);
            }
            bizWx.setTemplateId(JSON.toJSONString(map_tlp));
        }
        bizWx.setBizNo(biz.getBizNo());
        bizWx.setOptor(biz.getAcct());
        bizWxSV.createOrUpdate(bizWx);
        return "redirect:/biz/payWx";
    }

    /**
     * 微信强制关注配置
     */
    @RequestMapping("crOrUpBizPayWxFocus")
    public String crOrUpBizPayWxFocus(BizWxFocus bizWxFocus, Model model, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        bizWxFocusSV.update(bizWxFocus);
        return "redirect:/biz/payWxFocus";
    }

    @RequestMapping("toUpdSeller")
    public String toUpdSeller(String bizNo,Model model,HttpServletRequest request){
        model.addAttribute("bizNo", bizNo);
        return "sjxx/sellerManage/bizUpdSeller";
    }
    /**
     * 管理子商家信息
     *
     * @param bizNo   商家编号
     * @param pwd     密码
     * @param takeSet 提现额度
     */
    @RequestMapping("upBizInfo")
    public ExtMsg upBizInfo(String bizNo, String pwd, Integer takeSet,Integer grade,String logo, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        if (takeSet != null) {
            Biz biz1 = bizSV.loadBizByBizNo(bizNo);
            if (biz1 != null && biz.getBizNo().equals(biz1.getParBizNo())) {
                Biz biz2 = new Biz();
                biz2.setId(biz1.getId());
                biz2.setTakeSet(BigDecimal.valueOf(takeSet));
                bizSV.updateBiz(biz2);
            }
        }

        if (grade!=null){
            Biz biz1 = bizSV.loadBizByBizNo(bizNo);
            if (biz1 != null && biz.getBizNo().equals(biz1.getParBizNo())) {
                Biz biz2 = new Biz();
                biz2.setId(biz1.getId());
                biz2.setGrade(grade);
                bizSV.updateBiz(biz2);
            }
        }
        if (StringUtils.isNotEmpty(pwd)) {
            Biz biz1 = bizSV.loadBizByBizNo(bizNo);
            SysAcct sysAcct = sysAcctSV.loadMainSysAcct(bizNo);
            if ((biz1 != null && sysAcct != null && biz.getBizNo().equals(biz1.getParBizNo()))||biz.isAdmin()) {
                sysAcctSV.upPwd(bizNo, sysAcct.getAcct(), sysAcct.getPwd(), MD5Util.MD5(pwd));
            }
        }

        //修改商家LOGO（网站登录的LOGO）
        if (logo!=null&&logo!=""&&biz.isAdmin()){
            Biz biz1 = bizSV.loadBizByBizNo(bizNo);
            Biz biz2 = new Biz();
            biz2.setId(biz1.getId());
            biz2.setLogo(logo);
            bizSV.updateBiz(biz2);
        }

        return ExtMsg.success();
    }


    /**
     * 修改子商家LOGO（只能是ydkj账号才有权上传）
     * @param imgdata
     * @param filename
     * @param request
     * @return
     */
    @RequestMapping("uploadLogo")
    public ExtMsg uploadLogo(String imgdata, String filename, String bizNo,HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        if (!biz.getAcct().equals("ydkj")){
            return ExtMsg.fail("该账号无权限操作！");
        }
        //String newName1 = bizNo + filename.substring(filename.lastIndexOf("."));//question+时
        String relativeFilePath1 = "/upload/biz/logo/" + bizNo+".png";
        boolean ok = FileUtil.saveImgdataFile(request, imgdata, relativeFilePath1);
        if (ok) {
            return ExtMsg.success(relativeFilePath1);
        } else {
            return ExtMsg.fail("上传失败");
        }
    }

    /**
     * 商家主账户权限
     */
    @RequestMapping("bizRight")
    public String bizRight(String bizNo, Model model, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        Biz biz1 = bizSV.loadBizByBizNo(bizNo);
        if (biz1 != null && biz.getBizNo().equals(biz1.getParBizNo())) {
            SysAcct sysAcct = sysAcctSV.loadMainSysAcct(bizNo);
            List<SysRes> sysResList = null;
            if (biz.isAdmin()) {
                sysResList = sysResSV.querySysRes(null, 0, 100);
            } else {
                sysResList = sysResSV.querySysResByAcct(biz.getAcct());
            }
            if (sysResList != null && !sysResList.isEmpty()) {
                List<SysRes> acctSysResList = sysResSV.querySysResByAcct(sysAcct.getAcct());
                if (acctSysResList != null && !acctSysResList.isEmpty()) {
                    Map<String, String> resMap = new HashMap<String, String>();
                    for (SysRes sysRes : acctSysResList) {
                        resMap.put(sysRes.getCode(), sysRes.getCode());
                    }
                    for (SysRes sysRes : sysResList) {
                        if (resMap.containsKey(sysRes.getCode())) {
                            sysRes.setChecked(true);
                        }
                    }
                }
            }
            model.addAttribute("bizName", biz1.getName());
            model.addAttribute("sysAcct", sysAcct);
            model.addAttribute("sysResList", sysResList);
        }
        return "sjxx/sellerManage/bizRightReset";
    }



    /**
     * 商家广告管理
     */
    @RequestMapping("queryAd")
    public String queryAd(Pager<Biz> pager, String name, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("likeName", name);
        if (!biz.isMainAcct()) {
            params.put("acct", biz.getAcct());
        }
        pager.setAction("/biz/queryAd");
        List<Biz> bizList = bizSV.queryBiz(params, pager.getRowStart(), pager.getPageSize());
        params.put("isAdmin",biz.isAdmin());
        pager.setParams(params);
        pager.setRecordList(bizList);
        pager.setTotalRow(bizSV.countBiz(params));
        return "sjxx/adManage/bizAdList";
    }
}
