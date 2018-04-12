package com.zzwdkj.gprs.ctrl;

import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.common.ExtMsg;
import com.zzwdkj.gprs.entity.GprsModel;
import com.zzwdkj.gprs.entity.GprsOtp;
import com.zzwdkj.gprs.service.GprsModelSV;
import com.zzwdkj.gprs.service.GprsOtpSV;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Administrator on 2016/10/2.
 */
@Controller
@RequestMapping("/gprs")
public class GprsCtrl {
    @Resource
    private GprsModelSV gprsModelSV;
    @Resource
    private GprsOtpSV gprsOtpSV;
    @Resource
    private IdentifierSV identifierSV;

    @RequestMapping("genCyGprsNo")
    public ExtMsg genGprsNo(String type) {
        if (StringUtils.isNotEmpty(type)) {
            GprsModel gprsModel = null;
            if ("m".equalsIgnoreCase(type)) {
                gprsModel = new GprsModel(identifierSV.gprsNo("0"));
                gprsModelSV.create(gprsModel);
            }
            else if("n".equalsIgnoreCase(type)){
                gprsModel = new GprsModel(identifierSV.gprsNo("3"));
                gprsModelSV.create(gprsModel);
            }
            if(gprsModel!=null){
                return ExtMsg.success(gprsModel.getGprsNo());
            }else {
                return ExtMsg.success("fail");
            }
        } else {
            return ExtMsg.success("fail");
        }

    }

}
