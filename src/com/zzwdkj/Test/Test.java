package com.zzwdkj.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.zzwdkj.common.BaseConfig;
import com.zzwdkj.msg.service.CltRspProcSV;
import com.zzwdkj.prod.entity.Prod;
import com.zzwdkj.prod.entity.ProdModel;
import com.zzwdkj.prod.service.ProdModelSV;
import com.zzwdkj.prod.service.ProdSV;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guoxianwei 2016/9/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/framework/spring-back.xml"})
public class Test {

    @Resource
    private CltRspProcSV cltRspProcSV;
    @Resource
    private ProdSV prodSV;
    @Resource
    private ProdModelSV prodModelSV;

    @org.junit.Test
    public void testEarnRslt() {
        int coin_inc = 0000;
        int card_inc = 9000;
        int net_inc = 7000;
        for (int i = 0; i < 1000; i = i + 50) {
            StringBuilder money = new StringBuilder("000090007000");
            money.append(coin_inc).append(card_inc).append(net_inc);
            cltRspProcSV.reqEarnRslt("10000101", money.toString());
            if (coin_inc >= 9000) coin_inc = 0;
            if (card_inc >= 9000) card_inc = 0;
            if (net_inc >= 9000) net_inc = 0;
            coin_inc += i;
            card_inc += i;
            net_inc += i;
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @org.junit.Test
    public void testEarnRsltSp() {
       /* int coin_inc = 0000;
        int card_inc = 9000;
        int net_inc = 7000;
        StringBuilder money = new StringBuilder("000090007000");
        cltRspProcSV.reqEarnRslt("10000101", money.toString());*/

    }

    /***
     * 修改价格体系
     */
    @org.junit.Test
    public void updatePrice() {
        Map<String,Object> map=new HashMap<String,Object>();

        List<Prod> prodList =prodSV.queryProd("",0,1000);
        map.clear();
        for (Prod prod:prodList){
            if(prod.getGprsNo()==null||prod.getGprsNo().equals("")){
                continue;
            }
            String[][] moneyOptionList_new = new String[6][3];
            String[][]  moneyOptionList_old= JSON.parseObject(prod.getOtherMoneyOption(),new TypeReference<String[][]>(){});
            for (int i=0;i<6;i++){
                moneyOptionList_new[i][0]=moneyOptionList_old[i][0];
                moneyOptionList_new[i][1]=moneyOptionList_old[i][1];
                if (moneyOptionList_old[i][1]!=null&&!moneyOptionList_old[i][1].equals("")
                        &&(prod.getGprsNo().startsWith("0")||prod.getGprsNo().startsWith("2"))){
                    moneyOptionList_new[i][2]= String.valueOf(Double.valueOf(moneyOptionList_old[i][1]).intValue()*prod.getProdCv());
                }else {
                    moneyOptionList_new[i][2]="";
                }
            }
            if (map.get(JSONArray.toJSONString(moneyOptionList_new))==null){
                map.put(JSONArray.toJSONString(moneyOptionList_new),prod.getOtherMoneyOption());
                System.out.println("update prod m set  m.OTHER_MONEY_OPTION='"+JSONArray.toJSONString(moneyOptionList_new)+"'  " +
                        " where m.OTHER_MONEY_OPTION='"+JSONArray.toJSONString(moneyOptionList_old)+"' and m.PROD_CV="+prod.getProdCv()+";");
            }
        }
        System.out.println("-----------------------------size"+map.size());


        List<ProdModel> prodModelList =prodModelSV.queryProdModel("",0,1000);
        map.clear();
        for (ProdModel prodModel:prodModelList){
            String[][] moneyOptionList_new = new String[6][3];
            String[][]  moneyOptionList_old= JSON.parseObject(prodModel.getOtherMoneyOption(),new TypeReference<String[][]>(){});
            for (int i=0;i<6;i++){
                moneyOptionList_new[i][0]=moneyOptionList_old[i][0];
                moneyOptionList_new[i][1]=moneyOptionList_old[i][1];
                if (moneyOptionList_old[i][1]!=null&&!moneyOptionList_old[i][1].equals("")){
                    moneyOptionList_new[i][2]= String.valueOf(Double.valueOf(moneyOptionList_old[i][1]).intValue()*prodModel.getProdCv());
                }else {
                    moneyOptionList_new[i][2]="";
                }
            }

            if (map.get(JSONArray.toJSONString(moneyOptionList_new))==null){
                map.put(JSONArray.toJSONString(moneyOptionList_new),prodModel.getOtherMoneyOption());
                System.out.println("update prod_model m set  m.OTHER_MONEY_OPTION='"+JSONArray.toJSONString(moneyOptionList_new)+"'  " +
                        " where m.OTHER_MONEY_OPTION='"+JSONArray.toJSONString(moneyOptionList_old)+"' and m.PROD_CV="+prodModel.getProdCv()+";");
            }
        }
        System.out.println("-----------------------------size"+map.size());
    }

    /**
     * 发送清零命令
     */
    @org.junit.Test
    public void clearMoney() {
        Map<String, Object> params_prod = new HashMap<String, Object>();
        Map<String, Object> params = new HashMap<String, Object>();
        params_prod.put("bizNo", "B100025");
        List<Prod> prods = prodSV.queryProd(params_prod, 0, 0);
        for (Prod prod : prods) {
            params.clear();
            if(prod.getGprsNo().startsWith("6")){
                params.put("gprsNo", prod.getGprsNo());
                params.put("cmd", "GS03");
            } else if (prod.getGprsNo().startsWith("1")){
                params.put("gprsNo", prod.getGprsNo());
                params.put("type", "0");
                params.put("cmd", "AS03");
            }
            System.out.println(params.get("gprsNo")+"|"+params.get("cmd"));
        }
    }
}
