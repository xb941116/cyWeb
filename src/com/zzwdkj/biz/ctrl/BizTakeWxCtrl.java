package com.zzwdkj.biz.ctrl;

import com.zzwdkj.common.ExtMsg;
import com.zzwdkj.common.GsonUtil;
import com.zzwdkj.base.BaseCtrl;
import com.hckj.framework.web.query.ExtJsp;
import com.zzwdkj.biz.entity.BizTakeWx;
import com.zzwdkj.biz.service.BizTakeWxSV;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import javax.annotation.Resource;
import java.util.List;

/**
* biz_take_wx, 商家_提现_微信自动转账Ctrl
*
* @author guoxianwei  2016-11-15 09:27:26
*/
@Controller
@RequestMapping("/biz/bizTakeWx")
public class BizTakeWxCtrl extends BaseCtrl {

@Resource
private BizTakeWxSV bizTakeWxSV;

        /**
        * 查询
        */
        @RequestMapping("query")
        public void query(ExtJsp<BizTakeWx> page, String name) {
            List<BizTakeWx> bizTakeWxList = bizTakeWxSV.queryBizTakeWx(name, page.getStart(), page.getLimit());
            page.setRecordList(bizTakeWxList);
            page.setTotalRow( bizTakeWxSV.count(name));
        }

        /**
        * 新增页面
        */
        @RequestMapping("/add")
        public String add(Model model) {
           return "bizTakeWx/BizTakeWxAdd";
        }

        /**
        * 编辑页面
        */
        @RequestMapping("/edit")
        public String edit(String id, Model model) {
            BizTakeWx bizTakeWx = bizTakeWxSV.load(id);
            model.addAttribute("bizTakeWx", bizTakeWx);
            return "bizTakeWx/BizTakeWxEdit";
        }
                /**
                * 创建
                *
                * @param bizTakeWx 商家_提现_微信自动转账
                */
                @RequestMapping("create")
                public ExtMsg create(BizTakeWx bizTakeWx) {
                    bizTakeWxSV.create( bizTakeWx);
                    return ExtMsg.success("创建成功");
                }

                /**
                * 更新
                *
                * @param bizTakeWx 商家_提现_微信自动转账
                */
                @RequestMapping("update")
                public ExtMsg update(BizTakeWx bizTakeWx) {
                    try {
                        bizTakeWxSV.update(bizTakeWx);
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
                    BizTakeWx  bizTakeWx = GsonUtil.fromJson(data, BizTakeWx.class);
                    bizTakeWxSV.remove(bizTakeWx.getId());
                    return ExtMsg.success("删除成功");
                }
                }
