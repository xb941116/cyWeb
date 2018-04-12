package com.zzwdkj.mbr.ctrl;

import com.zzwdkj.common.ExtMsg;
import com.zzwdkj.common.GsonUtil;
import com.zzwdkj.base.BaseCtrl;
import com.hckj.framework.web.query.ExtJsp;
import com.zzwdkj.mbr.entity.MbrWalletChged;
import com.zzwdkj.mbr.service.MbrWalletChgedSV;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import javax.annotation.Resource;
import java.util.List;

/**
* mbr_wallet_chged, 会员钱包变更表Ctrl
*
* @author guoxianwei  2016-11-11 15:55:32
*/
@Controller
@RequestMapping("/mbr/mbrWalletChged")
public class MbrWalletChgedCtrl extends BaseCtrl {

@Resource
private MbrWalletChgedSV mbrWalletChgedSV;

        /**
        * 查询
        */
        @RequestMapping("query")
        public void query(ExtJsp<MbrWalletChged> page, String name) {
            List<MbrWalletChged> mbrWalletChgedList = mbrWalletChgedSV.queryMbrWalletChged(name, page.getStart(), page.getLimit());
            page.setRecordList(mbrWalletChgedList);
            page.setTotalRow( mbrWalletChgedSV.count(name));
        }

        /**
        * 新增页面
        */
        @RequestMapping("/add")
        public String add(Model model) {
           return "mbrWalletChged/MbrWalletChgedAdd";
        }

        /**
        * 编辑页面
        */
        @RequestMapping("/edit")
        public String edit(String id, Model model) {
            MbrWalletChged mbrWalletChged = mbrWalletChgedSV.load(id);
            model.addAttribute("mbrWalletChged", mbrWalletChged);
            return "mbrWalletChged/MbrWalletChgedEdit";
        }
                /**
                * 创建
                *
                * @param mbrWalletChged 会员钱包变更表
                */
                @RequestMapping("create")
                public ExtMsg create(MbrWalletChged mbrWalletChged) {
                    mbrWalletChgedSV.create( mbrWalletChged);
                    return ExtMsg.success("创建成功");
                }

                /**
                * 更新
                *
                * @param mbrWalletChged 会员钱包变更表
                */
                @RequestMapping("update")
                public ExtMsg update(MbrWalletChged mbrWalletChged) {
                    try {
                        mbrWalletChgedSV.update(mbrWalletChged);
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
                    MbrWalletChged  mbrWalletChged = GsonUtil.fromJson(data, MbrWalletChged.class);
                    mbrWalletChgedSV.remove(mbrWalletChged.getId());
                    return ExtMsg.success("删除成功");
                }
                }
