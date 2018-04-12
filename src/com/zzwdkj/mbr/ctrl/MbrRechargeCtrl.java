package com.zzwdkj.mbr.ctrl;

import com.zzwdkj.common.ExtMsg;
import com.zzwdkj.common.GsonUtil;
import com.zzwdkj.base.BaseCtrl;
import com.hckj.framework.web.query.ExtJsp;
import com.zzwdkj.mbr.entity.MbrRecharge;
import com.zzwdkj.mbr.service.MbrRechargeSV;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import javax.annotation.Resource;
import java.util.List;

/**
* mbr_recharge, 充值记录表Ctrl
*
* @author guoxianwei  2016-11-11 15:55:31
*/
@Controller
@RequestMapping("/mbr/mbrRecharge")
public class MbrRechargeCtrl extends BaseCtrl {

@Resource
private MbrRechargeSV mbrRechargeSV;

        /**
        * 查询
        */
        @RequestMapping("query")
        public void query(ExtJsp<MbrRecharge> page, String name) {
            List<MbrRecharge> mbrRechargeList = mbrRechargeSV.queryMbrRecharge(name, page.getStart(), page.getLimit());
            page.setRecordList(mbrRechargeList);
            page.setTotalRow( mbrRechargeSV.count(name));
        }

        /**
        * 新增页面
        */
        @RequestMapping("/add")
        public String add(Model model) {
           return "mbrRecharge/MbrRechargeAdd";
        }

        /**
        * 编辑页面
        */
        @RequestMapping("/edit")
        public String edit(String id, Model model) {
            MbrRecharge mbrRecharge = mbrRechargeSV.load(id);
            model.addAttribute("mbrRecharge", mbrRecharge);
            return "mbrRecharge/MbrRechargeEdit";
        }
                /**
                * 创建
                *
                * @param mbrRecharge 充值记录表
                */
                @RequestMapping("create")
                public ExtMsg create(MbrRecharge mbrRecharge) {
                    mbrRechargeSV.create( mbrRecharge);
                    return ExtMsg.success("创建成功");
                }

                /**
                * 更新
                *
                * @param mbrRecharge 充值记录表
                */
                @RequestMapping("update")
                public ExtMsg update(MbrRecharge mbrRecharge) {
                    try {
                        mbrRechargeSV.update(mbrRecharge);
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
                    MbrRecharge mbrRecharge = GsonUtil.fromJson(data, MbrRecharge.class);
                    mbrRechargeSV.remove(mbrRecharge.getId());
                    return ExtMsg.success("删除成功");
                }
                }
