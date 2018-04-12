package com.zzwdkj.biz.ctrl;

import com.alibaba.fastjson.JSON;
import com.zzwdkj.biz.entity.Biz;
import com.zzwdkj.common.*;
import com.zzwdkj.base.BaseCtrl;
import com.zzwdkj.biz.entity.BizAd;
import com.zzwdkj.biz.service.BizAdSV;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * biz_ad, 商家广告表Ctrl
 *
 * @author xizhuangchui  2017-04-22 12:12:22
 */
@Controller
@RequestMapping("/biz/bizAd")
public class BizAdCtrl extends BaseCtrl {

    @Resource
    private BizAdSV bizAdSV;

    /**
     * 查询
     */
    @RequestMapping("query")
    public String query(Pager<BizAd> pager, String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizNo);
        System.out.println("-----------------"+bizNo);
        List<BizAd> bizAdList = bizAdSV.queryBizAd(bizNo, pager.getRowStart(), pager.getPageSize());
        pager.setRecordList(bizAdList);
        pager.setTotalRow(bizAdSV.count(bizNo));
        pager.setAction("/biz/bizAd/query");
        pager.setParams(params);
        return "sjxx/adManage/bizAd";
    }

    /**
     * 新增页面
     */
    @RequestMapping("/add")
    public String add(Model model) {
        return "bizAd/BizAdAdd";
    }

    /**
     * 编辑页面
     */
    @RequestMapping("/edit")
    public String edit(Integer adSeat, Model model, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo",biz.getBizNo());
        params.put("adSeat",adSeat);
        params.put("adType", Std.ADType.PAYSUCESS.getKey());
        List<BizAd> bizAdList= bizAdSV.queryBizAdByparams(params,0,1);
        model.addAttribute("bizAd", bizAdList.get(0));
        return "sjxx/adSet/bizAdEdit";
    }

    @RequestMapping("uploadLogo")
    public ExtMsg uploadLogo(String imgdata, String filename,String adSeat, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        String fileType = filename.substring(filename.lastIndexOf(".")+1);
        if("gif".equalsIgnoreCase(fileType) || "jpg".equalsIgnoreCase(fileType) || "jpeg".equalsIgnoreCase(fileType) || "png".equalsIgnoreCase(fileType)){
            String newName1 = biz.getBizNo()+"_"+adSeat+"." + fileType;//question+时
            String relativeFilePath1 = "/upload/biz/ad/paysucess/" + newName1;
            boolean ok = FileUtil.saveImgdataFile(request, imgdata, relativeFilePath1);
            if (ok) {
                return ExtMsg.success(relativeFilePath1);
            } else {
                return ExtMsg.fail("上传失败");
            }
        }else {
            return ExtMsg.fail("上传图片文件类型有误");
        }

    }

    /**
     * 创建
     *
     * @param year 商家广告表年数
     */
    @RequestMapping("createPaySucess")
    public ModelAndView createPaySucess(BizAd bizAd, Integer year) {
        Map<String, Object> map = new HashMap<String, Object>();
        Date date=new Date();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo",bizAd.getBizNo());
        params.put("adType", Std.ADType.PAYSUCESS.getKey());
        List<BizAd> bizAdList= bizAdSV.queryBizAdByparams(params,0,2);
        if (bizAdList==null||bizAdList.size()==0){
            bizAd.setStartTime(date);
            bizAd.setEndTime(DateUtils.addYears(date,year));
        }else {
            BizAd bizAd_up=new BizAd();
           if (year!=null&&year>=0){//加年数
               if (date.after(bizAdList.get(0).getEndTime())){//当前时间大于结束时间
                   bizAd_up.setStartTime(date);
                   bizAd_up.setEndTime(DateUtils.addYears(date,year));
               }else {//当前时间小于结束时间
                   bizAd_up.setEndTime(DateUtils.addYears(bizAdList.get(0).getEndTime(),year));
               }
           }else if (year!=null&&year<0){//减年数
               if (date.after(bizAdList.get(0).getEndTime())){//当前时间大于结束时间

               }else {//当前时间小于结束时间
                   if (DateUtils.addYears(bizAdList.get(0).getEndTime(),year).after(bizAdList.get(0).getStartTime())){
                       //减少后年数的结束时间 在 开始时间之前
                       bizAd_up.setEndTime(DateUtils.addYears(bizAdList.get(0).getEndTime(),year));
                       if (bizAdList.get(0).getStartTime().after(DateUtils.addYears(bizAdList.get(0).getEndTime(),year))){
                           bizAd_up.setEndTime(date);
                       }
                   }else {
                       bizAd_up.setEndTime(bizAdList.get(0).getStartTime());
                   }
               }
           }
            bizAd_up.setId(bizAdList.get(0).getId());
            bizAdSV.update(bizAd_up);
            bizAd_up.setId(bizAdList.get(1).getId());
            bizAdSV.update(bizAd_up);
            return new ModelAndView("redirect:/biz/bizAd/query?bizNo="+bizAd.getBizNo(),map);
        }
        bizAd.setAdType(Std.ADType.PAYSUCESS.getKey());
        bizAdSV.createPaySucess(bizAd);
        return new ModelAndView("redirect:/biz/bizAd/query?bizNo="+bizAd.getBizNo(),map);
    }

    /**
     * 更新
     *
     * @param bizAd 商家广告表
     */
    @RequestMapping("update")
    public ExtMsg update(BizAd bizAd, Model model, HttpServletRequest request) {
        bizAdSV.update(bizAd);
        return ExtMsg.success("更新成功！");
        //return "redirect:/biz/bizAd/edit?adSeat="+bizAd.getAdSeat();
    }

    /**
     * 删除
     *
     * @param data 主键
     */
    @RequestMapping("remove")
    public ExtMsg remove(String data) {
        BizAd bizAd = GsonUtil.fromJson(data, BizAd.class);
        bizAdSV.remove(bizAd.getId());
        return ExtMsg.success("删除成功");
    }
}
