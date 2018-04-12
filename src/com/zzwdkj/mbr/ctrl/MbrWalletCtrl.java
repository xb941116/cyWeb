package com.zzwdkj.mbr.ctrl;

import com.zzwdkj.common.ExtMsg;
import com.zzwdkj.common.GsonUtil;
import com.zzwdkj.base.BaseCtrl;
import com.hckj.framework.web.query.ExtJsp;
import com.zzwdkj.mbr.entity.MbrWallet;
import com.zzwdkj.mbr.service.MbrWalletSV;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import javax.annotation.Resource;
import java.util.List;

/**
* mbr_wallet, 会员钱包Ctrl
*
* @author guoxianwei  2016-11-11 15:55:31
*/
@Controller
@RequestMapping("/mbr/mbrWallet")
public class MbrWalletCtrl extends BaseCtrl {

@Resource
private MbrWalletSV mbrWalletSV;

        /**
        * 查询
        */
        @RequestMapping("query")
        public void query(ExtJsp<MbrWallet> page, String name) {
            List<MbrWallet> mbrWalletList = mbrWalletSV.queryMbrWallet(name, page.getStart(), page.getLimit());
            page.setRecordList(mbrWalletList);
            page.setTotalRow( mbrWalletSV.count(name));
        }

        /**
        * 新增页面
        */
        @RequestMapping("/add")
        public String add(Model model) {
           return "mbrWallet/MbrWalletAdd";
        }

        /**
        * 编辑页面
        */
        @RequestMapping("/edit")
        public String edit(String id, Model model) {
            MbrWallet mbrWallet = mbrWalletSV.load(id);
            model.addAttribute("mbrWallet", mbrWallet);
            return "mbrWallet/MbrWalletEdit";
        }
                /**
                * 创建
                *
                * @param mbrWallet 会员钱包
                */
                @RequestMapping("create")
                public ExtMsg create(MbrWallet mbrWallet) {
                    mbrWalletSV.create( mbrWallet);
                    return ExtMsg.success("创建成功");
                }

                /**
                * 更新
                *
                * @param mbrWallet 会员钱包
                */
                @RequestMapping("update")
                public ExtMsg update(MbrWallet mbrWallet) {
                    try {
                        mbrWalletSV.update(mbrWallet);
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
                    MbrWallet  mbrWallet = GsonUtil.fromJson(data, MbrWallet.class);
                    mbrWalletSV.remove(mbrWallet.getId());
                    return ExtMsg.success("删除成功");
                }
                }
