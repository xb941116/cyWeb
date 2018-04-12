package com.zzwdkj.mbr.ctrl;

import com.zzwdkj.common.ExtMsg;
import com.zzwdkj.common.GsonUtil;
import com.zzwdkj.base.BaseCtrl;
import com.hckj.framework.web.query.ExtJsp;
import com.zzwdkj.mbr.entity.MbrCoin;
import com.zzwdkj.mbr.service.MbrCoinSV;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import javax.annotation.Resource;
import java.util.List;

/**
* mbr_coin, 会员积分表Ctrl
*
* @author guoxianwei  2016-11-11 15:55:27
*/
@Controller
@RequestMapping("/mbr/mbrCoin")
public class MbrCoinCtrl extends BaseCtrl {

@Resource
private MbrCoinSV mbrCoinSV;

        /**
        * 查询
        */
        @RequestMapping("query")
        public void query(ExtJsp<MbrCoin> page, String name) {
            List<MbrCoin> mbrCoinList = mbrCoinSV.queryMbrCoin(name, page.getStart(), page.getLimit());
            page.setRecordList(mbrCoinList);
            page.setTotalRow( mbrCoinSV.count(name));
        }

        /**
        * 新增页面
        */
        @RequestMapping("/add")
        public String add(Model model) {
           return "mbrCoin/MbrCoinAdd";
        }

        /**
        * 编辑页面
        */
        @RequestMapping("/edit")
        public String edit(String id, Model model) {
            MbrCoin mbrCoin = mbrCoinSV.load(id);
            model.addAttribute("mbrCoin", mbrCoin);
            return "mbrCoin/MbrCoinEdit";
        }
                /**
                * 创建
                *
                * @param mbrCoin 会员积分表
                */
                @RequestMapping("create")
                public ExtMsg create(MbrCoin mbrCoin) {
                    mbrCoinSV.create( mbrCoin);
                    return ExtMsg.success("创建成功");
                }

                /**
                * 更新
                *
                * @param mbrCoin 会员积分表
                */
                @RequestMapping("update")
                public ExtMsg update(MbrCoin mbrCoin) {
                    try {
                        mbrCoinSV.update(mbrCoin);
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
                    MbrCoin  mbrCoin = GsonUtil.fromJson(data, MbrCoin.class);
                    mbrCoinSV.remove(mbrCoin.getId());
                    return ExtMsg.success("删除成功");
                }
            }
