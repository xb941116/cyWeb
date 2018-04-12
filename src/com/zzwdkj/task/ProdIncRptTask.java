package com.zzwdkj.task;

import com.zzwdkj.common.BaseConfig;
import com.zzwdkj.prod.entity.ProdOnlLog;
import com.zzwdkj.prod.service.ProdSV;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */


public class ProdIncRptTask implements BaseTask {

    @Resource
    private ProdSV prodSV;


    @Override
    public int doTask() {
        List<ProdOnlLog> prodOnlLogList = prodSV.queryAllOnlineProd();
        if (prodOnlLogList != null && !prodOnlLogList.isEmpty()) {
            for (ProdOnlLog prodOnlLog : prodOnlLogList) {
                reqEarn(prodOnlLog.getGpno());
            }
        }
        return 0;
    }

    public void reqEarn(String gprsNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        if (BaseConfig.isSelfProd(gprsNo)) {
            params.put("gprsNo", gprsNo);
            params.put("type", "0");
            params.put("cmd", "WS29");
        }else if(gprsNo.startsWith("3")){
            params.put("gprsNo", gprsNo);
            params.put("cmd", "CY43");
        }
    }
}
