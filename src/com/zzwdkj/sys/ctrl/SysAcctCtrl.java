package com.zzwdkj.sys.ctrl;

import com.zzwdkj.base.BaseCtrl;
import com.hckj.framework.web.query.ExtJsp;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.biz.entity.Biz;
import com.zzwdkj.common.*;
import com.zzwdkj.sys.entity.SysAcct;
import com.zzwdkj.sys.entity.SysRes;
import com.zzwdkj.sys.service.SysAcctSV;
import com.zzwdkj.sys.service.SysResSV;
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
 * sys_acct, 权限_系统账号Ctrl
 *
 * @author guoxianwei  2016-09-07 15:02:11
 */
@Controller
@RequestMapping("/sys/sysAcct")
public class SysAcctCtrl extends BaseCtrl {

    @Resource
    private SysAcctSV sysAcctSV;
    @Resource
    private SysResSV sysResSV;
    @Resource
    private IdentifierSV identifierSV;

    /**
     * 查询
     */
    @RequestMapping("query")
    public void query(ExtJsp<SysAcct> page, String name) {
        List<SysAcct> sysAcctList = sysAcctSV.querySysAcct(null, page.getStart(), page.getLimit());
        page.setRecordList(sysAcctList);
        page.setTotalRow(sysAcctSV.countSysAcct(null));
    }

    /**
     * 新增页面
     */
    @RequestMapping("/add")
    public String add(Model model, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);

        List<SysRes> sysResList = null;
        if (biz.isAdmin()) {
            sysResList = sysResSV.querySysRes(null, 0, 100);
        } else {
            SysAcct sysAcct2 = sysAcctSV.loadMainSysAcct(biz.getBizNo());
            sysResList = sysResSV.querySysResByAcct(sysAcct2.getAcct());
        }
        model.addAttribute("sysResList", sysResList);
        return "sjxx/accountManage/bizSubUserAdd";
    }

    /**
     * 编辑页面
     */
    @RequestMapping("/edit")
    public String edit(String id, Model model, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);

        SysAcct sysAcct = sysAcctSV.loadSysAcct(id);
        model.addAttribute("sysAcct", sysAcct);
        List<SysRes> sysResList = null;
        if (biz.isAdmin()) {
            sysResList = sysResSV.querySysRes(null, 0, 100);
        } else {
            SysAcct sysAcct2 = sysAcctSV.loadMainSysAcct(sysAcct.getBizNo());
            sysResList = sysResSV.querySysResByAcct(sysAcct2.getAcct());
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
        model.addAttribute("sysResList", sysResList);
        return "sjxx/accountManage/bizSubUserAdd";
    }

    /**
     * 更新
     *
     * @param sysAcct 权限_系统账号
     */
    @RequestMapping("crOrUpSubUsr")
    public ExtMsg crOrUpSubUsr(SysAcct sysAcct, ModelMap modelMap, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        if (StringUtils.isNotEmpty(sysAcct.getId()) && !"null".equals(sysAcct.getId())) {
            sysAcct.setBizNo(biz.getBizNo());
            sysAcctSV.createOrUpdateSysAcct(sysAcct);
            modelMap.clear();
            return ExtMsg.success("更新成功");
        } else {
            if (sysAcctSV.loadSysAcctByAcct(sysAcct.getAcct()) != null) {
                modelMap.clear();
                return ExtMsg.fail("登录账号已注册过！");
            }
            sysAcct.setAdmin(0);
            sysAcct.setMainAcct(0);
            sysAcct.setBizNo(biz.getBizNo());
            sysAcct.setPwd(MD5Util.MD5(sysAcct.getPwd()));
            sysAcctSV.createOrUpdateSysAcct(sysAcct);
            modelMap.clear();
            return ExtMsg.success("新增成功");
        }
    }

    /**
     * 更新
     *
     * @param sysAcct 权限_系统账号
     */
    @RequestMapping("resetRight")
    public ExtMsg resetRight(SysAcct sysAcct, ModelMap modelMap) {
        SysAcct sysAcct1 = sysAcctSV.loadSysAcct(sysAcct.getId());
        sysAcct1.setRes(sysAcct.getRes());
        sysAcctSV.resetRight(sysAcct1);
        modelMap.clear();
        return ExtMsg.success("授权成功");

    }

    @RequestMapping("uploadLogo")
    public ExtMsg uploadLogo(String imgdata, String filename, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        String fileType = filename.substring(filename.lastIndexOf(".")+1);
        if("gif".equalsIgnoreCase(fileType) || "jpg".equalsIgnoreCase(fileType) || "jpeg".equalsIgnoreCase(fileType) || "png".equalsIgnoreCase(fileType)){
            String newName1 = identifierSV.getUniqueId() + fileType;//question+时
            String relativeFilePath1 = "/upload/biz/acct/" + newName1;
            boolean ok = FileUtil.saveImgdataFile(request, imgdata, relativeFilePath1);
            if (ok) {
                sysAcctSV.upHeadImg(biz.getAcct(), relativeFilePath1);
                biz.setHeadImg(relativeFilePath1);
                return ExtMsg.success(relativeFilePath1);
            } else {
                return ExtMsg.fail("上传失败");
            }
        }else {
            return ExtMsg.fail("上传头像文件类型有误");
        }

    }

    /**
     * 删除
     *
     * @param id 主键
     */
    @RequestMapping("remove")
    public ExtMsg remove(String id) {
        SysAcct sysAcct = sysAcctSV.loadSysAcct(id);
        if (sysAcct != null && sysAcct.getMainAcct() == 0 && sysAcct.getAdmin() == 0) {
            sysAcctSV.removeSysAcct(sysAcct.getId());
        }
        return ExtMsg.success("删除成功");
    }
}
