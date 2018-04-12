package com.zzwdkj.mbr.ctrl;

import com.zzwdkj.common.ExtMsg;
import com.zzwdkj.common.GsonUtil;
import com.zzwdkj.base.BaseCtrl;
import com.hckj.framework.web.query.ExtJsp;
import com.zzwdkj.mbr.entity.MbrPay;
import com.zzwdkj.mbr.service.MbrPaySV;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import javax.annotation.Resource;
import java.util.List;

/**
* mbr_pay, 客户_支付记录表Ctrl
*
* @author guoxianwei  2016-09-07 15:01:43
*/
@Controller
@RequestMapping("/mbr/mbrPay")
public class MbrPayCtrl extends BaseCtrl {

@Resource
private MbrPaySV mbrPaySV;

        /**
        * 查询
        */
        @RequestMapping("query")
        public void query(ExtJsp<MbrPay> page, String name) {
            List<MbrPay> mbrPayList = mbrPaySV.queryMbrPay(name, page.getStart(), page.getLimit());
            page.setRecordList(mbrPayList);
            page.setTotalRow( mbrPaySV.count(name));
        }

        /**
        * 新增页面
        */
        @RequestMapping("/add")
        public String add(Model model) {
           return "mbrPay/MbrPayAdd";
        }

        /**
        * 编辑页面
        */
        @RequestMapping("/edit")
        public String edit(String id, Model model) {
            MbrPay mbrPay = mbrPaySV.load(id);
            model.addAttribute("mbrPay", mbrPay);
            return "mbrPay/MbrPayEdit";
        }
                /**
                * 创建
                *
                * @param mbrPay 客户_支付记录表
                */
                @RequestMapping("create")
                public ExtMsg create(MbrPay mbrPay) {
                    mbrPaySV.create( mbrPay);
                    return ExtMsg.success("创建成功");
                }

                /**
                * 更新
                *
                * @param mbrPay 客户_支付记录表
                */
                @RequestMapping("update")
                public ExtMsg update(MbrPay mbrPay) {
                    try {
                        mbrPaySV.update(mbrPay);
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
                    MbrPay  mbrPay = GsonUtil.fromJson(data, MbrPay.class);
                    mbrPaySV.remove(mbrPay.getId());
                    return ExtMsg.success("删除成功");
                }
                }
