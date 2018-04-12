package com.zzwdkj.sys.ctrl;

import com.zzwdkj.biz.entity.Biz;
import com.zzwdkj.biz.service.BizSV;
import com.zzwdkj.common.BaseConfig;
import com.zzwdkj.common.ExtMsg;
import com.zzwdkj.common.MD5Util;
import com.zzwdkj.sys.entity.SysAcct;
import com.zzwdkj.sys.entity.SysRes;
import com.zzwdkj.sys.service.SysAcctSV;
import com.zzwdkj.sys.service.SysResSV;
import org.apache.commons.lang.StringUtils;
import org.patchca.background.SingleColorBackgroundFactory;
import org.patchca.color.ColorFactory;
import org.patchca.filter.predefined.*;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.util.*;

/**
 * @author guoxianwei 2016/9/8.
 */
@Controller
@RequestMapping("/loginCtrl")
public class LoginCtrl {

    @Resource
    private SysAcctSV sysAcctSV;
    @Resource
    private SysResSV sysResSV;
    @Resource
    private BizSV bizSV;

    @RequestMapping("login")
    public ExtMsg login(String acct, String pwd, String code, HttpServletRequest request) {
        if (StringUtils.isEmpty(code)) {
            return ExtMsg.fail("请输入验证码");
        } else {
            String token = (String) request.getSession().getAttribute("captchaToken");
            if (!code.equalsIgnoreCase(token)) {
                return ExtMsg.fail("验证码输入错误");
            }
        }
        if (acct != null && pwd != null) {
            String token = (String) request.getSession().getAttribute("captchaToken");
            SysAcct sysAcct = sysAcctSV.loadSysAcctByAcct(acct);
            if (sysAcct != null && (acct.equals(sysAcct.getAcct()) || acct.equals(sysAcct.getBizNo()))) {
                String md5 = MD5Util.MD5(pwd);
                if (md5.equals(sysAcct.getPwd())) {
                    Biz biz = bizSV.loadBizByBizNo(sysAcct.getBizNo());
                    biz.setAcct(sysAcct.getAcct());
                    biz.setAcctName(sysAcct.getName());
                    biz.setMainAcct(sysAcct.getMainAcct());
                    biz.setAdmin(sysAcct.getAdmin());
                    biz.setHeadImg(sysAcct.getImg());
                    java.util.List<SysRes> sysResList = null;
                    if(biz.isAdmin()){
                        sysResList = sysResSV.querySysRes(null,0,100);
                    }else {
                        sysResList = sysResSV.querySysResByAcct(acct);
                    }
                    biz.setSysResList(sysResList);
                    request.getSession().setAttribute(BaseConfig.SESSION_VAR, biz);
                    return ExtMsg.success();
                } else {
                    return ExtMsg.fail("登录账号或密码错误");
                }
            } else {
                return ExtMsg.fail("登录账号或密码错误");
            }
        } else {
            return ExtMsg.fail("登录账号或密码错误");
        }
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request,boolean isWeiXin) {
        HttpSession session = request.getSession();
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        if (session == null) {
            return "redirect:/index.jsp";
        } else {
            session.invalidate();
            if ( isWeiXin){
                return "redirect:/custWeiXin/authorizeUrl?state="+biz.getBizNo()+"_1";
            }else {
                return "redirect:/index.jsp";
            }
        }
    }

    private static ConfigurableCaptchaService cs = new ConfigurableCaptchaService();

    private static Random random = new Random();

    static {
        cs.setColorFactory(new ColorFactory() {
            @Override
            public Color getColor(int x) {
                int[] c = new int[3];
                int i = random.nextInt(c.length);
                for (int fi = 0; fi < c.length; fi++) {
                    if (fi == i) {
                        c[fi] = random.nextInt(71);
                    } else {
                        c[fi] = random.nextInt(256);
                    }
                }
                return new Color(255, 255, 255);
            }
        });
        SingleColorBackgroundFactory backgroundFactory = new SingleColorBackgroundFactory(new Color(9, 122, 221));
        RandomWordFactory wf = new RandomWordFactory();
        wf.setCharacters("234567890");
        wf.setMaxLength(4);
        wf.setMinLength(4);
        cs.setWordFactory(wf);
        cs.setBackgroundFactory(backgroundFactory);
    }

    @RequestMapping("/code")
    public void crimg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        switch (random.nextInt(2)) {
            case 0:
                cs.setFilterFactory(new DoubleRippleFilterFactory());
                break;
            case 1:
                cs.setFilterFactory(new WobbleRippleFilterFactory());
                break;
            case 2:
                cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
                break;
            case 3:
                cs.setFilterFactory(new MarbleRippleFilterFactory());
                break;
            case 4:
                cs.setFilterFactory(new DiffuseRippleFilterFactory());
                break;
        }
        HttpSession session = request.getSession(false);
        if (session == null) {
            session = request.getSession();
        }
        setResponseHeaders(response);
        String token = EncoderHelper.getChallangeAndWriteImage(cs, "png", response.getOutputStream());
        session.setAttribute("captchaToken", token);
    }

    protected void setResponseHeaders(HttpServletResponse response) {
        response.setContentType("image/png");
        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        long time = System.currentTimeMillis();
        response.setDateHeader("Last-Modified", time);
        response.setDateHeader("Date", time);
        response.setDateHeader("Expires", time);
    }
}
