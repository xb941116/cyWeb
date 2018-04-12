package com.zzwdkj.mbr.ctrl;

import com.zzwdkj.common.ExtMsg;
import com.zzwdkj.common.GsonUtil;
import com.zzwdkj.base.BaseCtrl;
import com.hckj.framework.web.query.ExtJsp;
import com.zzwdkj.mbr.entity.MbrPrizen;
import com.zzwdkj.mbr.service.MbrPrizenSV;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import javax.annotation.Resource;
import java.util.List;

/**
* mbr_prizen, 会员奖励记录表Ctrl
*
* @author guoxianwei  2016-11-11 15:55:30
*/
@Controller
@RequestMapping("/mbr/mbrPrizen")
public class MbrPrizenCtrl extends BaseCtrl {

@Resource
private MbrPrizenSV mbrPrizenSV;

        /**
        * 查询
        */
        @RequestMapping("query")
        public void query(ExtJsp<MbrPrizen> page, String name) {
            List<MbrPrizen> mbrPrizenList = mbrPrizenSV.queryMbrPrizen(name, page.getStart(), page.getLimit());
            page.setRecordList(mbrPrizenList);
            page.setTotalRow( mbrPrizenSV.count(name));
        }

        /**
        * 新增页面
        */
        @RequestMapping("/add")
        public String add(Model model) {
           return "mbrPrizen/MbrPrizenAdd";
        }

        /**
        * 编辑页面
        */
        @RequestMapping("/edit")
        public String edit(String id, Model model) {
            MbrPrizen mbrPrizen = mbrPrizenSV.load(id);
            model.addAttribute("mbrPrizen", mbrPrizen);
            return "mbrPrizen/MbrPrizenEdit";
        }
                /**
                * 创建
                *
                * @param mbrPrizen 会员奖励记录表
                */
                @RequestMapping("create")
                public ExtMsg create(MbrPrizen mbrPrizen) {
                    mbrPrizenSV.create( mbrPrizen);
                    return ExtMsg.success("创建成功");
                }

                /**
                * 更新
                *
                * @param mbrPrizen 会员奖励记录表
                */
                @RequestMapping("update")
                public ExtMsg update(MbrPrizen mbrPrizen) {
                    try {
                        mbrPrizenSV.update(mbrPrizen);
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
                    MbrPrizen  mbrPrizen = GsonUtil.fromJson(data, MbrPrizen.class);
                    mbrPrizenSV.remove(mbrPrizen.getId());
                    return ExtMsg.success("删除成功");
                }
                }
