package com.zzwdkj.mbr.ctrl;

import com.zzwdkj.base.BaseCtrl;
import com.zzwdkj.biz.entity.Biz;
import com.zzwdkj.common.*;
import com.zzwdkj.mbr.entity.Mbr;
import com.zzwdkj.mbr.entity.MbrRecharge;
import com.zzwdkj.mbr.service.MbrRechargeSV;
import com.zzwdkj.mbr.service.MbrSV;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * mbr, 会员Ctrl
 *
 * @author guoxianwei  2016-09-07 15:01:39
 */
@Controller
@RequestMapping("/mbr")
public class MbrCtrl extends BaseCtrl {

    @Resource
    private MbrSV mbrSV;
    @Resource
    private MbrRechargeSV mbrRechargeSV;

    /**
     * 查询
     */
    @RequestMapping("/query")
    public String query(Pager pager,String mobile, String name,  HttpSession httpSession ) {
        Biz biz = (Biz) httpSession.getAttribute(BaseConfig.SESSION_VAR);
        Map<String, Object> params = new HashMap<String, Object>();
        pager.setAction("/mbr/query");
        params.put("name",name);
        params.put("mobile",mobile);
        params.put("bizNo",biz.getBizNo());
        List<Mbr> bizGiftList = mbrSV.queryMbrJoinWalletByMap(params, pager.getRowStart(), pager.getPageSize());
        pager.setRecordList(bizGiftList);
        pager.setTotalRow(mbrSV.countByMap(params));
        pager.setParams(params);
        return "grzx/memberManage/memberList";
    }


    /**
     * 充值记录查询
     *@param pager
     */
    @RequestMapping("/reChargeHis")
    public ModelAndView reChargeHis(Pager pager,String memberId) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(memberId==null||memberId.equals("")){
            map.clear();
            map.put("message", "参数异常，请重新进入！");
            return new ModelAndView("weixin/message", map);
        }
        List<MbrRecharge> mbrRechargeList = null;
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("memberId", memberId);
            pager.setAction("/mbr/reChargeHis");
            pager.setParams(params);
            mbrRechargeList = mbrRechargeSV.queryMbrRecharge(params, pager.getRowStart(), pager.getPageSize());
            pager.setRecordList(mbrRechargeList);
            pager.setTotalRow(mbrRechargeSV.countMbrRecharge(params));
        } catch (Exception e) {
            map.put("message","页面失效、请重新进入！");
            return new ModelAndView("weixin/message",map);
        }
        map.put("mbrRechargeList",mbrRechargeList);
        return new ModelAndView("grzx/memberManage/mbrReChargeList",map);
    }

    /**
     * 新增页面
     */
    @RequestMapping("/add")
    public String add(Model model) {
        return "mbr/MbrAdd";
    }



    /**
     * 创建
     *
     * @param mbr 会员
     */
    @RequestMapping("create")
    public ExtMsg create(Mbr mbr) {
        mbrSV.create(mbr);
        return ExtMsg.success("创建成功");
    }


    /**
     * 删除
     *
     * @param data 主键
     */
    @RequestMapping("remove")
    public ExtMsg remove(String data) {
        Mbr mbr = GsonUtil.fromJson(data, Mbr.class);
        mbrSV.remove(mbr.getId());
        return ExtMsg.success("删除成功");
    }

}
