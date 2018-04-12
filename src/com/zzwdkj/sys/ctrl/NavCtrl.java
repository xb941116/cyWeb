package com.zzwdkj.sys.ctrl;

import com.zzwdkj.biz.entity.Biz;
import com.zzwdkj.biz.entity.BizAd;
import com.zzwdkj.biz.service.BizAdSV;
import com.zzwdkj.biz.service.BizSV;
import com.zzwdkj.common.BaseConfig;
import com.zzwdkj.common.Pager;
import com.zzwdkj.common.Std;
import com.zzwdkj.mbr.service.MbrRechargeSV;
import com.zzwdkj.ord.entity.Ord;
import com.zzwdkj.ord.service.OrdSV;
import com.zzwdkj.prod.entity.ProdCoinRpt;
import com.zzwdkj.prod.service.ProdCoinRptSV;
import com.zzwdkj.prod.service.ProdSV;
import com.zzwdkj.sys.entity.SysRes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guoxianwei 2016/9/12.
 */
@Controller
@RequestMapping("/navCtrl")
public class NavCtrl {

    @Resource
    private OrdSV ordSV;
    @Resource
    private ProdSV prodSV;
    @Resource
    private ProdCoinRptSV prodCoinRptSV;
    @Resource
    private MbrRechargeSV mbrRechargeSV;
    @Resource
    private BizSV bizSV;
    @Resource
    private BizAdSV bizAdSV;

    @RequestMapping("/home")
    public String home(Model model, String wxBizNo, HttpServletRequest request, HttpSession httpSession) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        model.addAttribute("acct", biz.getAcct());
        /*BigDecimal totalInc = BigDecimal.ZERO;
        ProdCoinRpt prodCoinRpt = prodCoinRptSV.loadStaTotalIncWithPerPayWay(null, biz.getBizNo());
        Ord ord = ordSV.loadStaTotalIncWithPerPayWay(null,biz.getBizNo());
        BigDecimal rgInc = mbrRechargeSV.queryStaIncByBizNo(biz.getBizNo());
        model.addAttribute("totalInc", totalInc.add(prodCoinRpt.getCardInc()).add(prodCoinRpt.getCoinInc()).add(ord.getWxInc()).add(rgInc));
        BigDecimal dailyInc = ordSV.queryStaDailyIncByPayWay(null, Std.PayWay.WX, biz.getBizNo());
        BigDecimal weekInc = ordSV.queryStaWeekIncByPayWay(null, Std.PayWay.WX, biz.getBizNo());
        BigDecimal monthInc = ordSV.queryStaMonthIncByPayWay(null, Std.PayWay.WX,biz.getBizNo());
        ProdCoinRpt prodCoinRptDaily = prodCoinRptSV.loadStaDayTotalIncWithPerPayWay(null, biz.getBizNo());
        ProdCoinRpt prodCoinRptWeek = prodCoinRptSV.loadStaWeekTotalIncWithPerPayWay(null, biz.getBizNo());
        ProdCoinRpt prodCoinRptMonth = prodCoinRptSV.loadStaMonthTotalIncWithPerPayWay(null, biz.getBizNo());
        model.addAttribute("dailyInc", dailyInc.add(prodCoinRptDaily.getCardInc()).add(prodCoinRptDaily.getCoinInc()));
        model.addAttribute("weekInc", weekInc.add(prodCoinRptWeek.getCardInc()).add(prodCoinRptWeek.getCoinInc()));
        model.addAttribute("monthInc", monthInc.add(prodCoinRptMonth.getCardInc()).add(prodCoinRptMonth.getCoinInc()));
        model.addAttribute("rgInc", rgInc);*/
        List<SysRes> sysResList = getUnderSysRes("100", biz.getSysResList());
        model.addAttribute("sysResList", sysResList);
        model.addAttribute("headImg", biz.getHeadImg());
        if (wxBizNo != null && !wxBizNo.equals("")) {
            Biz biz_wx = bizSV.loadBizByBizNo(wxBizNo);
            httpSession.setAttribute("wxBizNoLogo", biz_wx.getLogo());
            httpSession.setAttribute("wxBizNoName", biz_wx.getName());
        }
        return "grzx/home";
    }

    @RequestMapping("/income")
    public String income(Model model,HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        BigDecimal totalInc = BigDecimal.ZERO;
        BigDecimal rgInc = mbrRechargeSV.queryStaIncByBizNo(biz.getBizNo());
        BigDecimal dailyInc = ordSV.queryStaDailyIncByPayWay(null, Std.PayWay.WX, biz.getBizNo());
        BigDecimal weekInc = ordSV.queryStaWeekIncByPayWay(null, Std.PayWay.WX, biz.getBizNo());
        BigDecimal monthInc = ordSV.queryStaMonthIncByPayWay(null, Std.PayWay.WX,biz.getBizNo());
        Ord ord = ordSV.loadStaTotalIncWithPerPayWay(null,biz.getBizNo());
        ProdCoinRpt prodCoinRpt = prodCoinRptSV.loadStaTotalIncWithPerPayWay(null, biz.getBizNo());
        ProdCoinRpt prodCoinRptDaily = prodCoinRptSV.loadStaDayTotalIncWithPerPayWay(null, biz.getBizNo());
        ProdCoinRpt prodCoinRptWeek = prodCoinRptSV.loadStaWeekTotalIncWithPerPayWay(null, biz.getBizNo());
        ProdCoinRpt prodCoinRptMonth = prodCoinRptSV.loadStaMonthTotalIncWithPerPayWay(null, biz.getBizNo());
        List<SysRes> sysResList = getUnderSysRes("100", biz.getSysResList());

        model.addAttribute("sysResList", sysResList);
        model.addAttribute("headImg", biz.getHeadImg());
        model.addAttribute("totalInc", totalInc.add(prodCoinRpt.getCardInc()).add(prodCoinRpt.getCoinInc()).add(ord.getWxInc()).add(rgInc));
        model.addAttribute("dailyInc", dailyInc.add(prodCoinRptDaily.getCardInc()).add(prodCoinRptDaily.getCoinInc()));
        model.addAttribute("weekInc", weekInc.add(prodCoinRptWeek.getCardInc()).add(prodCoinRptWeek.getCoinInc()));
        model.addAttribute("monthInc", monthInc.add(prodCoinRptMonth.getCardInc()).add(prodCoinRptMonth.getCoinInc()));
        model.addAttribute("rgInc", rgInc);
        return "grzx/totalIncome";
    }

    @RequestMapping("/equipment")
    public String equipment(HttpServletRequest request, Model model) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        int online = prodSV.statProdOnLineCount(biz.getBizNo());
        int offline = prodSV.statProdOffLineCount(biz.getBizNo());
        model.addAttribute("online", online);
        model.addAttribute("offline", offline);
        return "sbgl/equipment";
    }

    @RequestMapping("/prod")
    public String prod(HttpServletRequest request, Model model) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        List<SysRes> sysResList = getUnderSysRes("200", biz.getSysResList());
        model.addAttribute("sysResList", sysResList);
        return "sbgl/manageCenter";
    }


    @RequestMapping("/biz")
    public String biz(HttpServletRequest request, Model model) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        List<SysRes> sysResList = getUnderSysRes("400", biz.getSysResList());
        List<BizAd> bizAdList =bizAdSV.queryBizAdByBizNoAndType(biz.getBizNo(), Std.ADType.PAYSUCESS.getKey(),true);
        if (bizAdList==null||bizAdList.size()==0){
            model.addAttribute("isShowAd",false);
        }else {
            model.addAttribute("isShowAd",true);
        }
        model.addAttribute("sysResList", sysResList);
        model.addAttribute("isAdmin", biz.isAdmin());
        return "sjxx/biz";
    }

    @RequestMapping("/stat")
    public String stat() {
       /* Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        ProdCoinRpt prodCoinRpt = prodCoinRptSV.loadStaTotalIncWithPerPayWay(null, biz.getBizNo());
        BigDecimal netInc = ordSV.queryStaNetInc(biz.getBizNo());
        Ord ord = ordSV.loadStaTotalIncWithPerPayWay(null, biz.getBizNo());
        List<Ord> list = ordSV.queryEveryQb(biz.getBizNo());//钱包
        //List<OrdPayWx> wxlist = ordPayWxSV.queryOrdPayWx(biz.getBizNo());
        List<Ord> wxlist = ordSV.queryEveryWx(biz.getBizNo());//微信
        List<ProdCoinRpt> tblist = prodCoinRptSV.queryStaEveryTb(biz.getBizNo());//投币
        List<ProdCoinRpt> sklist = prodCoinRptSV.queryStaEverySk(biz.getBizNo());//刷卡
        BigDecimal totalWltInc = ord.getWltInc();
        BigDecimal totalWxInc = ord.getWxInc();
        model.addAttribute("cardInc", prodCoinRpt.getCardInc());
        model.addAttribute("coinInc", prodCoinRpt.getCoinInc());
        model.addAttribute("totalWltInc", totalWltInc);
        model.addAttribute("totalWxInc", totalWxInc);
        model.addAttribute("netInc", netInc);
        model.addAttribute("list",list);//发送钱包list集合
        // model.addAttribute("wxlist",wxlist);//发送微信list集合
        model.addAttribute("wxlist",wxlist);//发送微信list集合
        model.addAttribute("tblist",tblist);//发送投币list集合
        model.addAttribute("sklist",sklist);//发送刷卡list集合
        boolean pagination = false;
        if (pager != null && (pager.getCurPage() > 1 || pager.getAction() != null)) {
            pagination = true;
        }
        model.addAttribute("bizName", biz.getName());
        model.addAttribute("pagination", pagination);

        Map<String, Object> params = new HashMap<String, Object>();

        int dd = 10;
        model.addAttribute("dd",dd);
        params.put("pagination", pagination);
        params.put("list",list);//放入params
        pager.setAction(request.getContextPath() + "/navCtrl/stat");
        List<ProdCoinRpt> prodCoinRptList = prodCoinRptSV.queryStaIncSort(null, biz.getBizNo(), pager.getRowStart(), pager.getPageSize());
        pager.setRecordList(prodCoinRptList);
        pager.setTotalRow(prodCoinRptSV.countStaIncSort(biz.getBizNo()));*/
        return "ystj/statList";
    }

    @RequestMapping("/statPropor")
    public String statPropor(Pager pager, HttpServletRequest request, Model model) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        ProdCoinRpt prodCoinRpt = prodCoinRptSV.loadStaTotalIncWithPerPayWay(null, biz.getBizNo());
        BigDecimal netInc = ordSV.queryStaNetInc(biz.getBizNo());
        Ord ord = ordSV.loadStaTotalIncWithPerPayWay(null, biz.getBizNo());
        List<Ord> list = ordSV.queryEveryQb(biz.getBizNo());//钱包
        //List<OrdPayWx> wxlist = ordPayWxSV.queryOrdPayWx(biz.getBizNo());
        List<Ord> wxlist = ordSV.queryEveryWx(biz.getBizNo());//微信
        List<ProdCoinRpt> tblist = prodCoinRptSV.queryStaEveryTb(biz.getBizNo());//投币
        List<ProdCoinRpt> sklist = prodCoinRptSV.queryStaEverySk(biz.getBizNo());//刷卡
        List<Ord> coinlist = ordSV.queryEveryCoin(biz.getBizNo());
        BigDecimal totalWltInc = ord.getWltInc();
        BigDecimal totalWxInc = ord.getWxInc();
        model.addAttribute("cardInc", prodCoinRpt.getCardInc());
        model.addAttribute("coinInc", prodCoinRpt.getCoinInc());
        model.addAttribute("totalWltInc", totalWltInc);
        model.addAttribute("totalWxInc", totalWxInc);
        model.addAttribute("netInc", netInc);
        model.addAttribute("list",list);//发送钱包list集合
        // model.addAttribute("wxlist",wxlist);//发送微信list集合
        model.addAttribute("wxlist",wxlist);//发送微信list集合
        model.addAttribute("tblist",tblist);//发送投币list集合
        model.addAttribute("sklist",sklist);//发送刷卡list集合
        model.addAttribute("coinlist",coinlist);//发送积分list集合
        boolean pagination = false;
        if (pager != null && (pager.getCurPage() > 1 || pager.getAction() != null)) {
            pagination = true;
        }
        model.addAttribute("bizName", biz.getName());
        model.addAttribute("pagination", pagination);

        Map<String, Object> params = new HashMap<String, Object>();

        int dd = 10;
        model.addAttribute("dd",dd);
        params.put("pagination", pagination);
        params.put("list",list);//放入params
        pager.setAction(request.getContextPath() + "/navCtrl/stat");
        List<ProdCoinRpt> prodCoinRptList = prodCoinRptSV.queryStaIncSort(null, biz.getBizNo(), pager.getRowStart(), pager.getPageSize());
        pager.setRecordList(prodCoinRptList);
        pager.setTotalRow(prodCoinRptSV.countStaIncSort(biz.getBizNo()));
        return "ystj/stat";
    }

    @RequestMapping("/statRanking")
    public String statRanking(Pager pager, HttpServletRequest request, Model model) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        boolean pagination = false;
        if (pager != null && (pager.getCurPage() > 1 || pager.getAction() != null)) {
            pagination = true;
        }
        model.addAttribute("bizName", biz.getName());
        model.addAttribute("pagination", pagination);

        Map<String, Object> params = new HashMap<String, Object>();

        int dd = 10;
        model.addAttribute("dd",dd);
        params.put("pagination", pagination);
        pager.setAction(request.getContextPath() + "/navCtrl/stat");
        List<ProdCoinRpt> prodCoinRptList = prodCoinRptSV.queryStaIncSort(null, biz.getBizNo(), pager.getRowStart(), pager.getPageSize());
        pager.setRecordList(prodCoinRptList);
        pager.setTotalRow(prodCoinRptSV.countStaIncSort(biz.getBizNo()));
        return "ystj/statRanking";
    }

    protected List<SysRes> getUnderSysRes(String parSysCode, List<SysRes> sysResList) {
        if (sysResList != null && !sysResList.isEmpty()) {
            List<SysRes> sysResList1 = new ArrayList<SysRes>();
            for (SysRes sysRes : sysResList) {
                if (sysRes.getPcode() != null && sysRes.getPcode().equals(parSysCode)) {
                    sysResList1.add(sysRes);
                }
            }
            return sysResList1;
        }
        return null;
    }


}
