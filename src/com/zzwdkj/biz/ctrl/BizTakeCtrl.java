package com.zzwdkj.biz.ctrl;

import com.zzwdkj.base.BaseCtrl;
import com.zzwdkj.biz.entity.*;
import com.zzwdkj.biz.service.*;
import com.zzwdkj.common.*;
import com.zzwdkj.common.weixin.WeiXinUtil;
import com.zzwdkj.ord.service.OrdSV;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * biz_take, 商家_提现Ctrl
 *
 * @author guoxianwei  2016-09-07 15:01:31
 */
@Controller
@RequestMapping("/biz/bizTake")
public class BizTakeCtrl extends BaseCtrl {

    @Resource
    private BizTakeSV bizTakeSV;
    @Resource
    private BizSV bizSV;
    @Resource
    private BizWltSV bizWltSV;
    @Resource
    private BizTakeWwltSV bizTakeWwltSV;
    @Resource
    private BizTakeBankSV bizTakeBankSV;
    @Resource
    private BizTakeWxSV bizTakeWxSV;
    @Resource
    private BizWxSV bizWxSV;
    @Resource
    private OrdSV ordSV;

    /**
     * 银行卡提现列表
     *
     * @return
     */
    @RequestMapping("/list")
    public String list(Pager pager, Integer takeWay, HttpSession httpSession) {
        Biz biz = (Biz) httpSession.getAttribute(BaseConfig.SESSION_VAR);
        if (biz.getGrade() == 3) {//代理
            Map<String, Object> params = new HashMap<String, Object>();
            if (takeWay==null){
                params.put("takeWay", 1);
            }else if (takeWay==1){
                params.put("takeWay", 1);
            }else if (takeWay==2){
                params.put("takeWayWX", "1");
            }else {
                params.put("takeWay", 1);
            }
            params.put("parBizNo", biz.getBizNo());
            params.put("orderField", "biz_take.crtime");
            params.put("orderSeq", "asc");
            pager.setAction("/biz/bizTake/list");
            List<BizTake> bizTakeList = bizTakeSV.queryJoinBiz(params, pager.getRowStart(), pager.getPageSize());
            pager.setRecordList(bizTakeList);
            pager.setTotalRow(bizTakeSV.countJoinBiz(params));
            params.put("takeWay", takeWay == null ? 1 : takeWay);
            pager.setParams(params);
            return "/grzx/bizTake/mbrTakePayList";
        } else {
            Map<String, Object> params = new HashMap<String, Object>();
            if (takeWay==null){
                params.put("takeWay", 1);
            }else if (takeWay==1){
                params.put("takeWay", 1);
            }else if (takeWay==2){
                params.put("takeWayWX", "1");
            }else {
                params.put("takeWay", 1);
            }
            params.put("bizNo", biz.getBizNo());
            params.put("orderField", "biz_take.crtime");
            params.put("orderSeq", "asc");
            pager.setAction("/biz/bizTake/list");
            List<BizTake> bizTakeList = bizTakeSV.queryJoinBiz(params, pager.getRowStart(), pager.getPageSize());
            pager.setRecordList(bizTakeList);
            pager.setTotalRow(bizTakeSV.countJoinBiz(params));
            params.put("takeWay", takeWay == null ? 1 : takeWay);
            pager.setParams(params);
            return "/grzx/bizTake/mbrTakeList";
        }

    }

    /**
     * 新增页面
     */
    @RequestMapping("/add")
    public String add(Model model, HttpSession httpSession) {
        Biz biz = (Biz) httpSession.getAttribute(BaseConfig.SESSION_VAR);
        BizWlt bizWlt = bizSV.loadBizWlt(biz.getBizNo());
        BigDecimal autoMoney=ordSV.queryAutoMoney(biz.getBizNo());
        if (bizWlt != null) {
            model.addAttribute("autoMoney", autoMoney);
            model.addAttribute("takeMoney", bizWlt.getTake() == null ? "0" : bizWlt.getTake().subtract(autoMoney));
            model.addAttribute("takeSet", biz.getTakeSet());
        }else {
            model.addAttribute("takeMoney", "0");
        }
        return "/mbrTakeAdd";
    }



    /**
     * 绑定
     */
    @RequestMapping("/bindWx")
    public ModelAndView bindWx(HttpSession httpSession) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(httpSession.getAttribute("user_openid")==null||httpSession.getAttribute("user_openid").equals("")){
            map.clear();
            map.put("message", "请在微信公众号内进行绑定！");
            return new ModelAndView("weixin/message", map);
        }
        map= WeiXinUtil.getUserInfo(httpSession);//获取微信用户的信息
        Biz biz=null;
        try {
            biz= (Biz) httpSession.getAttribute(BaseConfig.SESSION_VAR);
            if(biz==null||biz.getId()==null){
                map.put("message", "页面失效请重新登陆！");
                return new ModelAndView("weixin/message", map);
            }
        } catch (Exception e) {
            map.put("message", "页面失效请重新登陆！");
            return new ModelAndView("weixin/message", map);
        }
        BizTakeWx bizTakeWx=bizTakeWxSV.loadByBizNo(biz.getBizNo());
        if(bizTakeWx!=null&&biz.getId()!=null){
            map.put("real",1);
            map.put("bizTakeWx",bizTakeWx);
        }else {
            map.put("real",0);
        }
        return new ModelAndView("weixin/bindWx",map);
    }

    /**
     * 绑定自动提款微信
     * @param httpSession
     * @return
     */
    @RequestMapping("/createBizTakeWx")
    public ModelAndView createBizTakeWx(HttpSession httpSession,BizTakeWx bizTakeWx_update){
        Biz biz =null;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            biz =(Biz) httpSession.getAttribute(BaseConfig.SESSION_VAR);
            if(biz==null||biz.getId()==null){
                map.put("message", "页面失效请重新登陆！");
                return new ModelAndView("weixin/message", map);
            }
        } catch (Exception e) {
            map.put("message", "页面失效请重新登陆！");
            return new ModelAndView("weixin/message", map);
        }
        if(httpSession.getAttribute("user_openid")==null||httpSession.getAttribute("user_openid").equals("")){
            map.clear();
            map.put("message", "请在微信公众号内进行绑定！");
            return new ModelAndView("weixin/message", map);
        }
        BizTakeWx bizTakeWx=bizTakeWxSV.loadByBizNo(biz.getBizNo());
        map= WeiXinUtil.getUserInfo(httpSession);//获取微信用户的信息
        if (bizTakeWx!=null && bizTakeWx.getId()!=null){
            /*
            bizTakeWx_update.setId(bizTakeWx.getId());
            bizTakeWx_update.setOpenid(httpSession.getAttribute("user_openid").toString());
            bizTakeWx_update.setNick(map.get("nickname").toString());
            bizTakeWx_update.setState(1);
            bizTakeWxSV.update(bizTakeWx_update);
            */
            if (!bizTakeWx.getOpenid().equals(httpSession.getAttribute("user_openid").toString())){
                map.clear();
                map.put("message", "请用商家当前绑定的微信号进行解绑操作！");
                return new ModelAndView("weixin/message", map);
            }else {
                bizTakeWxSV.remove(bizTakeWx.getId());
            }
        }else {
            bizTakeWx=new BizTakeWx();
            bizTakeWx.setId(bizTakeWx.getId());
            bizTakeWx.setBizNo(biz.getBizNo());
            bizTakeWx.setOpenid(httpSession.getAttribute("user_openid").toString());
            String nickname="";
            if (map.get("nickname")!=null){
                nickname=WeiXinUtil.removeNonBmpUnicode(map.get("nickname").toString());
            }
            bizTakeWx.setNick(nickname);
            bizTakeWx.setState(1);
            bizTakeWxSV.create(bizTakeWx);
        }
        return new ModelAndView("redirect:/biz/bizTake/bindWx",map);
    }

    /**
     * 更改BizTakeWx 开启状态
     */
    @RequestMapping("/updateBizTakeWx")
    public ModelAndView edit(HttpSession httpSession,Integer state) {
        Biz biz =null;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            biz =(Biz) httpSession.getAttribute(BaseConfig.SESSION_VAR);
            if(biz==null||biz.getId()==null){
                map.put("message", "页面失效请重新登陆！");
                return new ModelAndView("weixin/message", map);
            }
        } catch (Exception e) {
            map.put("message", "页面失效请重新登陆！");
            return new ModelAndView("weixin/message", map);
        }
        if(httpSession.getAttribute("user_openid")==null||httpSession.getAttribute("user_openid").equals("")){
            map.clear();
            map.put("message", "请在微信公众号内进行绑定！");
            return new ModelAndView("weixin/message", map);
        }
        BizTakeWx bizTakeWx=bizTakeWxSV.loadByBizNo(biz.getBizNo());
        BizTakeWx bizTakeWx_update=new BizTakeWx();
        bizTakeWx_update.setId(bizTakeWx.getId());
        bizTakeWx_update.setState(state);
        bizTakeWxSV.update(bizTakeWx_update);
        return new ModelAndView("redirect:/biz/bizTake/bindWx",map);
    }


    /**
     * 编辑页面
     */
    @RequestMapping("/edit")
    public String edit(String id, Model model) {
        BizTake bizTake = bizTakeSV.load(id);
        BizTakeBank bizTakeBank = bizTakeBankSV.loadByReqno(bizTake.getReqno());
        BizTakeWwlt bizTakeWwlt = bizTakeWwltSV.loadByReqno(bizTake.getReqno());
        model.addAttribute("bizTake", bizTake);
        model.addAttribute("bizTakeBank", bizTakeBank);
        model.addAttribute("bizTakeWwlt", bizTakeWwlt);
        return "/mbrTakeEdit";
    }


    /**
     * 编辑页面
     */
    @RequestMapping("/editTakePay")
    public String editTakePay(String id, Model model) {
        BizTake bizTake = bizTakeSV.load(id);
        BizTakeBank bizTakeBank = bizTakeBankSV.loadByReqno(bizTake.getReqno());
        BizTakeWwlt bizTakeWwlt = bizTakeWwltSV.loadByReqno(bizTake.getReqno());
        Biz biz = bizSV.loadBiz(bizTake.getBizId());
        model.addAttribute("bizTake", bizTake);
        model.addAttribute("bizTakeBank", bizTakeBank);
        model.addAttribute("bizTakeWwlt", bizTakeWwlt);
        model.addAttribute("biz", biz);
        return "/grzx/bizTake/mbrTakePay";
    }

    /**
     * 创建
     *
     * @param bizTake 商家_提现
     */
    @RequestMapping("/create")
    public ExtMsg create(BizTake bizTake, BizTakeBank bizTakeBank, BizTakeWwlt bizTakeWwlt, HttpSession httpSession) {
        Biz biz = (Biz) httpSession.getAttribute(BaseConfig.SESSION_VAR);
        if (biz == null || biz.getId() == null) {
            return ExtMsg.fail("请刷新页面重试！");
        }
        /*BizTakeWx bizTakeWx =bizTakeWxSV.loadByBizNo(biz.getBizNo());
        if (bizTakeWx.getState()!=1){
            return ExtMsg.fail("已开启自动提现，无法手动提现！若想手动请先关闭自动提现！");
        }*/
        if (biz.getTakeSet() != null) {
            if (bizTake.getAmount().compareTo(biz.getTakeSet()) == -1) {
                return ExtMsg.fail("最低提现金额不能小于！" + biz.getTakeSet());
            }
        }

        BizWlt bizWlt = bizWltSV.loadByBizId(biz.getBizNo());
        if (bizWlt.getTake().compareTo(bizTake.getAmount()) == 0 ||
                bizWlt.getTake().compareTo(bizTake.getAmount()) == 1) {
            bizTake.setBizId(biz.getId());
            bizTake.setBizNo(biz.getBizNo());
            bizTake.setParBizNo(biz.getParBizNo());
            /*
            BizWx bizWx_transfers=bizWxSV.loadByBizNo(biz.getParBizNo());
            bizTake.setMtcCost(bizTake.getAmount().multiply(bizWx_transfers.getGiroQuota()));//手续费
            bizTake.setRealAmount(bizTake.getAmount().subtract(bizTake.getMtcCost()));//实际转账金额
            */
            if (StringUtils.isNotEmpty(biz.getParBizNo())) {
                bizTakeSV.create(bizTake, bizTakeBank, bizTakeWwlt, biz);
            } else {
                return ExtMsg.fail("无上级商家，不能提现！");
            }
        } else {
            return ExtMsg.fail("您提现金额大于可提现余额！请核实后再进行提现！");
        }
        return ExtMsg.success("创建成功!");
    }

    /**
     * 更新提现的信息
     *
     * @param bizTake 商家_提现
     */
    @RequestMapping("update")
    public ExtMsg update(BizTake bizTake, BizTakeBank bizTakeBank, BizTakeWwlt bizTakeWwlt, HttpSession httpSession) {
        try {
            Biz biz = (Biz) httpSession.getAttribute(BaseConfig.SESSION_VAR);
            BizTake bizTake_load = bizTakeSV.load(bizTake.getId());
            //不是自己的提现单子无权修改。
            if (!bizTake_load.getBizId().equals(biz.getId())) {
                return ExtMsg.fail("您没有权限进行修改！");
            }
            if (bizTake_load.getState() != 0) {
                return ExtMsg.fail("提款单进行中或者已经结束，无法进行修改！");
            }
            BizTakeBank bizTakeBank_load = bizTakeBankSV.loadByReqno(bizTake_load.getReqno());
            BizTakeWwlt bizTakeWwlt_load = bizTakeWwltSV.loadByReqno(bizTake_load.getReqno());
            BizTake bizTake_update = new BizTake();
            if (bizTake_load != null && bizTake_load.getId() != null) {
                bizTake_update.setId(bizTake_load.getId());

                bizTake_update.setDoneDate(new Date());
                if (bizTakeBank_load != null) {
                    bizTakeBank.setId(bizTakeBank_load.getId());
                    bizTakeSV.update(bizTake_update, bizTakeBank, null, biz);

                }
                if (bizTakeWwlt_load != null) {
                    bizTakeWwlt.setId(bizTakeWwlt_load.getId());
                    bizTakeSV.update(bizTake_update, null, bizTakeWwlt, biz);
                }

            } else {
                return ExtMsg.fail("请刷新页面重试！");
            }
        } catch (Exception e) {
            return ExtMsg.fail(e.getMessage());
        }

        return ExtMsg.success("更新成功");
    }


    /**
     * 更新提现状态
     *
     * @param id 商家_提现id
     */
    @RequestMapping("updateState")
    public ExtMsg updateState(String id,String tsno,String mtcCost,String realAmount, HttpSession httpSession) {
        Biz biz_par = (Biz) httpSession.getAttribute(BaseConfig.SESSION_VAR);
        BizTake bizTake_load = bizTakeSV.load(id);
        Biz biz = bizSV.loadBiz(bizTake_load.getBizId());
        //不是改单子的上层代理商无权修改。
        if (!biz.getParBizNo().equals(biz_par.getBizNo())) {
            return ExtMsg.fail("您没有权限操作！");
        }
        if (bizTake_load.getState() != 0) {
            return ExtMsg.fail("提款单进行中或者已经结束，无法进行修改！");
        }
        BizTake bizTake_update = new BizTake();
        if (bizTake_load != null && bizTake_load.getId() != null) {
            bizTake_update.setTsno(tsno);
            bizTake_update.setDoneDate(new Date());
            bizTake_update.setId(bizTake_load.getId());
            bizTake_update.setState(Std.TakeSt.SUCCESS.getKey());
            bizTake_update.setMtcCost(new BigDecimal(mtcCost));
            bizTake_update.setRealAmount(new BigDecimal(realAmount));
        } else {
            return ExtMsg.fail("请刷新页面重试！");
        }
        try {
            bizTakeSV.update(bizTake_update);
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
        BizTake bizTake = GsonUtil.fromJson(data, BizTake.class);
        bizTakeSV.remove(bizTake.getId());
        return ExtMsg.success("删除成功");
    }
}
