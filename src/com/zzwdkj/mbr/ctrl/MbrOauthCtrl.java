package com.zzwdkj.mbr.ctrl;

import com.zzwdkj.common.ExtMsg;
import com.zzwdkj.common.GsonUtil;
import com.zzwdkj.base.BaseCtrl;
import com.hckj.framework.web.query.ExtJsp;
import com.zzwdkj.mbr.entity.MbrOauth;
import com.zzwdkj.mbr.service.MbrOauthSV;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import javax.annotation.Resource;
import java.util.List;

/**
* mbr_oauth, 第三方账号关联表Ctrl
*
* @author guoxianwei  2016-11-11 15:55:28
*/
@Controller
@RequestMapping("/mbr/mbrOauth")
public class MbrOauthCtrl extends BaseCtrl {

@Resource
private MbrOauthSV mbrOauthSV;

    /**
     * 查询
     */
    @RequestMapping("query")
    public void query(ExtJsp<MbrOauth> page, String name) {
        List<MbrOauth> mbrOauthList = mbrOauthSV.queryMbrOauth(name, page.getStart(), page.getLimit());
        page.setRecordList(mbrOauthList);
        page.setTotalRow( mbrOauthSV.count(name));
    }

    /**
     * 新增页面
     */
    @RequestMapping("/add")
    public String add(Model model) {
        return "mbrOauth/MbrOauthAdd";
    }

    /**
     * 编辑页面
     */
    @RequestMapping("/edit")
    public String edit(String id, Model model) {
        MbrOauth mbrOauth = mbrOauthSV.load(id);
        model.addAttribute("mbrOauth", mbrOauth);
        return "mbrOauth/MbrOauthEdit";
    }

    /**
     * 创建
     *
     * @param mbrOauth 第三方账号关联表
     */
    @RequestMapping("create")
    public ExtMsg create(MbrOauth mbrOauth) {
        mbrOauthSV.create( mbrOauth);
        return ExtMsg.success("创建成功");
    }

    /**
     * 更新
     *
     * @param mbrOauth 第三方账号关联表
     */
    @RequestMapping("update")
    public ExtMsg update(MbrOauth mbrOauth) {
        try {
            mbrOauthSV.update(mbrOauth);
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
        MbrOauth  mbrOauth = GsonUtil.fromJson(data, MbrOauth.class);
        mbrOauthSV.remove(mbrOauth.getId());
        return ExtMsg.success("删除成功");
    }


}
