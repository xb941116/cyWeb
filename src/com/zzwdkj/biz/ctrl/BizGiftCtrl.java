package com.zzwdkj.biz.ctrl;

import com.zzwdkj.biz.entity.Biz;
import com.zzwdkj.common.*;
import com.zzwdkj.base.BaseCtrl;
import com.zzwdkj.biz.entity.BizGift;
import com.zzwdkj.biz.service.BizGiftSV;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* biz_gift, 满赠活动Ctrl
*
* @author guoxianwei  2016-11-11 17:52:57
*/
@Controller
@RequestMapping("/biz/bizGift")
public class BizGiftCtrl extends BaseCtrl {

@Resource
private BizGiftSV bizGiftSV;

    /**
     * 查询
     */
    @RequestMapping("/query")//活动管理总查询
    public String query(Pager pager, String name, HttpSession httpSession ){
        Biz biz = (Biz) httpSession.getAttribute(BaseConfig.SESSION_VAR);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name",name);
        params.put("bizNo",biz.getBizNo());
        params.put("orderField","sort");
        params.put("orderSeq","asc");
        List<BizGift> bizGiftList = bizGiftSV.queryBizGiftByMap(params, pager.getRowStart(), pager.getPageSize());
        pager.setRecordList(bizGiftList);
        pager.setTotalRow(bizGiftSV.countBizGiftByMap(params));
        pager.setParams(params);
        return "grzx/activityManage/BizGiftList";
    }

    /**
     * 新增页面
     */
    @RequestMapping("/add")
    public String add(Model model) {
        return "grzx/activityManage/BizGiftAdd";
    }

    /**
     * 编辑页面
     */
    @RequestMapping("/edit")
    public String edit(String id, Model model) {
        BizGift bizGift = bizGiftSV.load(id);
        model.addAttribute("bizGift", bizGift);
        return "grzx/activityManage/BizGiftEdit";
    }



    /**
     * 创建
     *
     * @param bizGift 满赠活动
     */
    @RequestMapping("/create")
    public ExtMsg create(BizGift bizGift,HttpSession httpSession) {
        Biz biz = (Biz) httpSession.getAttribute(BaseConfig.SESSION_VAR);
        bizGift.setBizNo(biz.getBizNo());
        bizGift.setStockAll(bizGift.getTotalAll());
        if (bizGift.getTotalAll()==0){
            bizGift.setStockAll(99999999);
        }
        bizGiftSV.create( bizGift);
        return ExtMsg.success("创建成功");

    }

    /**
     * 更新
     *
     * @param bizGift_update 满赠活动
     */
    @RequestMapping("/update")
    public ExtMsg update(BizGift bizGift_update,HttpSession httpSession) {
        Biz biz = (Biz) httpSession.getAttribute(BaseConfig.SESSION_VAR);
        BizGift bizGift=bizGiftSV.load(bizGift_update.getId());
        if (!biz.getBizNo().equals(bizGift.getBizNo())){
            return ExtMsg.fail("您没有权限修改！");
        }
        try {
            bizGift_update.setStockAll(bizGift.getStockAll()+(bizGift_update.getTotalAll()-bizGift.getTotalAll()));
            if (bizGift_update.getTotalAll()==0){
                bizGift_update.setStockAll(99999999);
            }
            bizGiftSV.update(bizGift_update);
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
    @RequestMapping("/remove")
    public ExtMsg remove(String data) {
        BizGift  bizGift = GsonUtil.fromJson(data, BizGift.class);
        bizGiftSV.remove(bizGift.getId());
        return ExtMsg.success("删除成功");
    }


}
