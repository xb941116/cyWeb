package com.zzwdkj.mbr.ctrl;

import com.zzwdkj.common.ExtMsg;
import com.zzwdkj.common.GsonUtil;
import com.zzwdkj.base.BaseCtrl;
import com.hckj.framework.web.query.ExtJsp;
import com.zzwdkj.mbr.entity.MbrCoinChged;
import com.zzwdkj.mbr.service.MbrCoinChgedSV;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import javax.annotation.Resource;
import java.util.List;

/**
* mbr_coin_chged, 会员积分变更表Ctrl
*
* @author guoxianwei  2016-11-11 15:55:28
*/
@Controller
@RequestMapping("/mbr/mbrCoinChged")
public class MbrCoinChgedCtrl extends BaseCtrl {

@Resource
private MbrCoinChgedSV mbrCoinChgedSV;

        /**
        * 查询
        */
        @RequestMapping("query")
        public void query(ExtJsp<MbrCoinChged> page, String name) {
            List<MbrCoinChged> mbrCoinChgedList = mbrCoinChgedSV.queryMbrCoinChged(name, page.getStart(), page.getLimit());
            page.setRecordList(mbrCoinChgedList);
            page.setTotalRow( mbrCoinChgedSV.count(name));
        }

        /**
        * 新增页面
        */
        @RequestMapping("/add")
        public String add(Model model) {
           return "mbrCoinChged/MbrCoinChgedAdd";
        }

        /**
        * 编辑页面
        */
        @RequestMapping("/edit")
        public String edit(String id, Model model) {
            MbrCoinChged mbrCoinChged = mbrCoinChgedSV.load(id);
            model.addAttribute("mbrCoinChged", mbrCoinChged);
            return "mbrCoinChged/MbrCoinChgedEdit";
        }
                /**
                * 创建
                *
                * @param mbrCoinChged 会员积分变更表
                */
                @RequestMapping("create")
                public ExtMsg create(MbrCoinChged mbrCoinChged) {
                    mbrCoinChgedSV.create( mbrCoinChged);
                    return ExtMsg.success("创建成功");
                }

                /**
                * 更新
                *
                * @param mbrCoinChged 会员积分变更表
                */
                @RequestMapping("update")
                public ExtMsg update(MbrCoinChged mbrCoinChged) {
                    try {
                        mbrCoinChgedSV.update(mbrCoinChged);
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
                    MbrCoinChged  mbrCoinChged = GsonUtil.fromJson(data, MbrCoinChged.class);
                    mbrCoinChgedSV.remove(mbrCoinChged.getId());
                    return ExtMsg.success("删除成功");
                }
                }
