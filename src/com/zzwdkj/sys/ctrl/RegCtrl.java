package com.zzwdkj.sys.ctrl;

import com.zzwdkj.biz.entity.Biz;
import com.zzwdkj.biz.service.BizSV;
import com.zzwdkj.common.BaseConfig;
import com.zzwdkj.common.ExtMsg;
import com.zzwdkj.common.MD5Util;
//import com.zzwdkj.sys.entity.ProjectFile;
import com.zzwdkj.sys.entity.SysAcct;
import com.zzwdkj.sys.entity.SysRes;
//import com.zzwdkj.sys.service.ProjectFileSV;
import com.zzwdkj.sys.service.SysAcctSV;
import com.zzwdkj.sys.service.SysResSV;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guoxianwei 2016/9/8.
 */
@Controller
@RequestMapping("/regCtrl")
public class RegCtrl {

    @Resource
    private BizSV bizSV;
    @Resource
    private SysResSV sysResSV;
    @Resource
    private SysAcctSV sysAcctSV;
    //@Resource
    //private ProjectFileSV projectFileSV;

    @RequestMapping("/bizReg")
    public String bizReg(Model model, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        model.addAttribute("grade", biz.getGrade());
        if (biz != null) {

            List<SysRes> sysResList = sysResSV.querySysResByAcct(biz.getAcct());
            if (sysResList != null && !sysResList.isEmpty()) {
                for (SysRes sysRes : sysResList) {
                    sysRes.setChecked(true);
                }
                model.addAttribute("sysResList", sysResList);
            }
        }
        return "sjxx/sellerManage/bizReg";
    }

    @RequestMapping("/crBiz")
    public ExtMsg crBiz(String acct, String bizName, String pwd, Integer grade, Integer takeSet, String res, HttpServletRequest request) {
        if (acct != null && !acct.isEmpty()) {
            if (!acct.matches("^(?=.*[a-zA-Z])[a-zA-Z0-9]+")) {
                return ExtMsg.fail("登录账号只能输入字母或字母与数字的组合");
            }
        }
        if (sysAcctSV.loadSysAcctByAcct(acct) != null) {
            return ExtMsg.fail("登录账号已注册过！");
        }
        if (grade == null) grade = 1;
        Biz biz = new Biz(bizName, null, null, null, null, grade);
        biz.setTakeSet(takeSet != null ? BigDecimal.valueOf(takeSet) : BigDecimal.ZERO);
        Biz biz2 = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        biz.setParBizNo(biz2.getBizNo());
        bizSV.createBiz(biz);
        pwd = MD5Util.MD5(pwd);
        SysAcct sysAcct = new SysAcct(acct, biz.getBizNo(), null, null, pwd, 1);
        sysAcct.setRes(res);
        sysAcctSV.createOrUpdateSysAcct(sysAcct);
        return ExtMsg.success();
    }

    //加载地图
    @RequestMapping("/toMap")
    public String toMap() {
        return "/baiduAPI";
    }

    @RequestMapping("/regSucc")
    public String regSucc() {
        return "/bizRegSucc";
    }

    //案例
    @RequestMapping("/cases")
    public String cases() {
        return "/case";
    }


    //案例
    @RequestMapping("/toDetails")
    public String toDetails() {
        return "/casesDetails";
    }


    //关于我们
    @RequestMapping("/adoutUser")
    public String adoutUser() {
        return "/adoutUser";
    }

    //后台登录
    @RequestMapping("/login")
    public String login() {
        return "/bizLogin";
    }

    //选择广告
    @RequestMapping("/selAd")
    public String selAd() {
        return "/selAd";
    }

    //广告提交
    @RequestMapping("/submitAd")
    public String submitAd() {
        return "/submitAd";
    }

    //加载支付页面
    @RequestMapping("/toAdPay")
    public String toAdPay() {
        return "/toAdPay";
    }

    //广告支付
    @RequestMapping("/adPay")
    public String adPay() {
        return "/adPay";
    }

    //支付回调
    @RequestMapping("/confirmPay")
    public String confirmPay() {
        return "/adEdit";
    }

    /**
     * 创建客户项目需求
     * @param yusuan
     * @param xuqiu
     * @param name
     * @param phone
     * @return
     */
    @RequestMapping("/create")
    public ExtMsg create(String yusuan, String xuqiu,String name,String phone) {
        //ProjectFile project = new ProjectFile(yusuan,xuqiu,name,Integer.parseInt(phone),new Date(),0);
       // projectFileSV.addCreate(project);
        return ExtMsg.success("提交成功！客户代表将在在三个工作日内联系您");
    }

    @RequestMapping("/ceshi")
    public ExtMsg ceshi(String yusuan, String xuqiu,String name,String phone) {
        //ProjectFile project = new ProjectFile(yusuan,xuqiu,name,Integer.parseInt(phone),new Date(),0);
        // projectFileSV.addCreate(project);
        return ExtMsg.success("测试gitgit");
    }
}
