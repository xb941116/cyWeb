package com.zzwdkj.biz.ctrl;

import com.zzwdkj.common.ExtMsg;
import com.zzwdkj.common.GsonUtil;
import com.zzwdkj.common.Pager;
import com.zzwdkj.common.Std;
import com.zzwdkj.base.BaseCtrl;
import com.zzwdkj.biz.entity.BizVip;
import com.zzwdkj.biz.service.BizVipSV;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;

/**
 * biz_vip, 商家VIP功能表Ctrl
 *
 * @author xizhuangchui  2017-04-22 12:12:22
 *
 */
@Controller
@RequestMapping("/biz/bizVip")
public class BizVipCtrl extends BaseCtrl {

    @Resource
    private BizVipSV bizVipSV;

    /**
     * 查询
     */
    @RequestMapping("query")
    public String query(Pager<BizVip> pager, String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        List<BizVip> bizVipList = bizVipSV.queryBizVip(bizNo, pager.getRowStart(), pager.getPageSize());
        pager.setRecordList(bizVipList);
        pager.setTotalRow(bizVipSV.count(bizNo));
        pager.setAction("/biz/bizVip/query");
        pager.setParams(params);
        return "sjxx/adManage/bizVip";
    }

    /**
     * 新增页面
     */
    @RequestMapping("/add")
    public String add(Model model) {
        return "bizVip/BizVipAdd";
    }

    /**
     * 编辑页面
     */
    @RequestMapping("/edit")
    public String edit(String id, Model model) {
        BizVip bizVip = bizVipSV.load(id);
        model.addAttribute("bizVip", bizVip);
        return "bizVip/BizVipEdit";
    }

    /**
     * 创建
     *
     * @param bizVip 商家VIP功能表
     */
    @RequestMapping("createPaySucess")
    public ModelAndView createPaySucess(BizVip bizVip, Integer year) {
        Map<String, Object> map = new HashMap<String, Object>();
        Date date=new Date();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo",bizVip.getBizNo());
        params.put("vipType", Std.VipType.AD.getKey());
        List<BizVip> bizVipList= bizVipSV.queryBizAdByparams(params,0,1);
        if (bizVipList==null||bizVipList.size()==0){
            bizVip.setStartTime(date);
            bizVip.setEndTime(DateUtils.addYears(date,year));
            bizVip.setVipType(Std.VipType.AD.getKey());
            bizVipSV.createPaySucess(bizVip);
        }else {
            BizVip bizVip_up=new BizVip();
            if (year!=null&&year>=0){//加年数
                if (date.after(bizVipList.get(0).getEndTime())){//当前时间大于结束时间
                    bizVip_up.setStartTime(date);
                    bizVip_up.setEndTime(DateUtils.addYears(date,year));
                }else {//当前时间小于结束时间
                    bizVip_up.setEndTime(DateUtils.addYears(bizVipList.get(0).getEndTime(),year));
                }
            }else if (year!=null&&year<0){//减年数
                if (date.after(bizVipList.get(0).getEndTime())){//当前时间大于结束时间

                }else {//当前时间小于结束时间
                    if (DateUtils.addYears(bizVipList.get(0).getEndTime(),year).after(bizVipList.get(0).getStartTime())){
                        //减少后年数的结束时间 在 开始时间之前
                        bizVip_up.setEndTime(DateUtils.addYears(bizVipList.get(0).getEndTime(),year));
                        if (bizVipList.get(0).getStartTime().after(DateUtils.addYears(bizVipList.get(0).getEndTime(),year))){
                            bizVip_up.setEndTime(date);
                        }
                    }else {
                        bizVip_up.setEndTime(bizVipList.get(0).getStartTime());
                    }
                }
            }
            bizVip_up.setId(bizVipList.get(0).getId());
            bizVipSV.update(bizVip_up);
        }

        return new ModelAndView("redirect:/biz/bizVip/query?bizNo="+bizVip.getBizNo(),map);
    }

    /**
     * 更新
     *
     * @param bizVip 商家VIP功能表
     */
    @RequestMapping("update")
    public ExtMsg update(BizVip bizVip) {
        try {
            bizVipSV.update(bizVip);
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
        BizVip bizVip = GsonUtil.fromJson(data, BizVip.class);
        bizVipSV.remove(bizVip.getId());
        return ExtMsg.success("删除成功");
    }
}
