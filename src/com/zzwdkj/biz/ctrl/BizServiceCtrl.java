package com.zzwdkj.biz.ctrl;

import com.alibaba.fastjson.JSON;
import com.zzwdkj.base.BaseCtrl;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.biz.entity.Biz;
import com.zzwdkj.biz.service.BizSV;
import com.zzwdkj.biz.service.BizWxFocusSV;
import com.zzwdkj.biz.service.BizWxSV;
import com.zzwdkj.common.*;
import com.zzwdkj.ord.service.OrdSV;
import com.zzwdkj.prod.entity.Prod;
import com.zzwdkj.prod.entity.ProdOnlLog;
import com.zzwdkj.prod.service.ProdSV;
import com.zzwdkj.sys.service.SysAcctSV;
import com.zzwdkj.sys.service.SysResSV;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
 * biz, 商家Ctrl
 *
 * @author guoxianwei  2016-09-07 15:01:26
 */
@Controller
@RequestMapping("/bizService")
public class BizServiceCtrl extends BaseCtrl {

    @Resource
    private BizSV bizSV;
    @Resource
    private BizWxSV bizWxSV;
    @Resource
    private OrdSV ordSV;
    @Resource
    private ProdSV prodSV;
    @Resource
    private BizWxFocusSV bizWxFocusSV;
    @Resource
    private SysResSV sysResSV;
    @Resource
    private SysAcctSV sysAcctSV;
    @Resource
    private IdentifierSV identifierSV;

    /**
     * 获取授权code
     * @param bizNo
     * @param key 第三方授权码
     * @return
     */
    @ResponseBody
    @RequestMapping("getCode")
    public String getCode(String bizNo,String key) {
        if (bizNo==null||bizNo.equals("")){
            return "{\"errcode\": 1, \"errmsg\": \"BIZNO IS NO WRONG\"}";
        }
        if (key==null||key.equals("")){
            return "{\"errcode\": 1, \"errmsg\": \"KEY IS NO WRONG\"}";
        }
        Biz biz= bizSV.loadBizByBizNo(bizNo);
        String code="";
        if (biz.getBizKey()!=null&&biz.getBizKey().equals(key)){
            code= PushUtil.createCode(bizNo);
            return "{\"errcode\": 0, \"errmsg\": \"SUCCESS\", \"code\": \""+code+"\"}";
        }else {
            return "{\"errcode\": 1, \"errmsg\": \"KEY IS NO WRONG\"}";
        }
    }


    /**
     * 获取某个设备的上下线信息
     * @param bizNo
     * @param sign
     * @return
     */
    @ResponseBody
    @RequestMapping("loadGprsState")
    public String loadGprsState(String bizNo,String gprsNo,String sign) {
        String code=PushUtil.checkCode(bizNo);
        if (code==null){
            return "{\"errcode\": 1, \"errmsg\": \"CODE IS NO VALID\"}";
        }
        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        parameters.put("bizNo",bizNo);
        parameters.put("gprsNo",gprsNo);
        if (PushUtil.createSign("UTF-8",parameters,code).equals(sign)){
            Prod prod=prodSV.loadByGprsNo(gprsNo);
            if (prod==null||prod.getId()==null){
                return "{\"errcode\": 1, \"errmsg\": \"GPRSNO IS NO WRONG\"}";
            }
            if (!prod.getBizNo().equals(bizNo)){
                return "{\"errcode\": 1, \"errmsg\": \"PERMISSION IS NO WRONG\"}";
            }
            ProdOnlLog prodOnlLog=prodSV.loadProdOnline(prod.getProdNo());
            if (prodOnlLog==null||prodOnlLog.getId()==null){
                return "{\"errcode\": 0, \"errmsg\": \"SUCCESS\", \"online\": 0}";
            }
            //状态(0 掉线；1在线）
            return "{\"errcode\": 0, \"errmsg\": \"SUCCESS\", \"online\": "+prodOnlLog.getState()+"}";
        }else {
            return "{\"errcode\": 1, \"errmsg\": \"SIGN IS NO WRONG\"}";
        }
    }


    /**
     * 获取商家所有设备的上下线信息
     * @param bizNo
     * @param sign
     * @return
     */
    @ResponseBody
    @RequestMapping("queryAllGprsState")
    public String queryGprsState(String bizNo,String sign) {
        String code=PushUtil.checkCode(bizNo);
        if (code==null){
            return "{\"errcode\": 1, \"errmsg\": \"CODE IS NO VALID\"}";
        }
        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        parameters.put("bizNo",bizNo);
        if (PushUtil.createSign("UTF-8",parameters,code).equals(sign)){
            List<Map<String,Object>> prodOnlLogList=prodSV.queryProdOnlineState(bizNo);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("errcode",0);
            map.put("errmsg","SUCCESS");
            map.put("resultList",prodOnlLogList);
            return JSON.toJSONString(map);
        }else {
            return "{\"errcode\": 1, \"errmsg\": \"SIGN IS NO WRONG\"}";
        }
    }

    /**
     * 获取订单信息
     * @param bizNo
     * @param sign
     * @return
     */
    @ResponseBody
    @RequestMapping("loadOrd")
    public String loadOrd(String bizNo,String ordNo,String sign) {
        String code=PushUtil.checkCode(bizNo);
        if (code==null){
            return "{\"errcode\": 1, \"errmsg\": \"CODE IS NO VALID\"}";
        }
        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        parameters.put("bizNo",bizNo);
        parameters.put("ordNo",ordNo);
        if (PushUtil.createSign("UTF-8",parameters,code).equals(sign)){
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("errcode",0);
            map.put("errmsg","SUCCESS");
            map.put("resultData",ordSV.loadMapOrdByOrdNo(ordNo,bizNo));
            return JSON.toJSONString(map);
        }else {
            return "{\"errcode\": 1, \"errmsg\": \"SIGN IS NO WRONG\"}";
        }
    }

    /**
     * 获取所有订单
     * @param bizNo
     * @param sign
     * @return
     */
    @ResponseBody
    @RequestMapping("queryAllOrd")
    public String queryAllOrd(String bizNo,String sign) {
        String code=PushUtil.checkCode(bizNo);
        if (code==null){
            return "{\"errcode\": 1, \"errmsg\": \"CODE IS NO VALID\"}";
        }
        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        parameters.put("bizNo",bizNo);
        if (PushUtil.createSign("UTF-8",parameters,code).equals(sign)){
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("errcode",0);
            map.put("errmsg","SUCCESS");
            map.put("resultList",ordSV.quryTodayMapOrd(bizNo));
            return JSON.toJSONString(map);
        }else {
            return "{\"errcode\": 1, \"errmsg\": \"SIGN IS NO WRONG\"}";
        }
    }
}
