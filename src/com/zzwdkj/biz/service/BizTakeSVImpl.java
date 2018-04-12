package com.zzwdkj.biz.service;

import com.zzwdkj.biz.dao.BizTakeBankDAO;
import com.zzwdkj.biz.dao.BizTakeWwltDAO;
import com.zzwdkj.biz.dao.BizWltDAO;
import com.zzwdkj.biz.entity.*;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.biz.dao.BizTakeDAO;
import com.zzwdkj.common.BaseConfig;
import com.zzwdkj.common.MD5Util;
import com.zzwdkj.common.Std;
import com.zzwdkj.common.weixin.WeiXinUtil;
import com.zzwdkj.common.weixin.XMLUtil;
import com.zzwdkj.ord.service.OrdPayWxSV;
import com.zzwdkj.ord.service.OrdSV;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.security.KeyStore;
import java.util.*;
import java.util.concurrent.TimeoutException;

/**
 * biz_take, 商家_提现SVImpl
 *
 * @author guoxianwei  2016-09-07 15:01:31
 */
@Service("bizTakeSV")
public class BizTakeSVImpl implements BizTakeSV {

    @Resource
    private BizTakeDAO bizTakeDAO;
    @Resource
    private BizTakeBankDAO bizTakeBankDAO;
    @Resource
    private BizTakeWwltDAO bizTakeWwltDAO;
    @Resource
    private BizWltDAO bizWltDAO;
    @Resource
    private IdentifierSV identifierSV;


    @Resource
    private OrdSV ordSV;
    @Resource
    private BizSV bizSV;
    @Resource
    private BizWxSV bizWxSV;
    @Resource
    private OrdPayWxSV ordPayWxSV;
    @Resource
    private BizTakeWxSV bizTakeWxSV;

    private final static Logger LOGGER = Logger.getLogger(BizTakeSVImpl.class);

    /**
     * 查询商家_提现 ，带分页
     *
     * @param name     菜单名称
     * @param rowStart 起始行
     * @param rowSize  分页大小
     * @return 菜单资源
     */
    @Override
    public List<BizTake> queryBizTake(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return bizTakeDAO.query(params, rowStart, rowSize);
    }

    /**
     * 统计商家_提现数量
     *
     * @param name 名称
     * @return 商家_提现
     */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return bizTakeDAO.count(params);
    }


    /**
     * 新增商家_提现
     *
     * @param bizTake
     */
    @Override
    public void create(BizTake bizTake) {
        bizTake.setId(identifierSV.uniqueId());
        bizTake.setCrtime(new Date());
        bizTake.setUptime(new Date());
        bizTakeDAO.insert(bizTake);
    }

    /**
     * 修改商家_提现
     *
     * @param bizTake
     */
    @Override
    public void update(BizTake bizTake) {
        bizTake.setUptime(new Date());
        bizTakeDAO.update(bizTake);
    }

    /**
     * 删除一条记录
     *
     * @param id
     */
    @Override
    public void remove(String id) {
        bizTakeDAO.delete(id);
    }

    /**
     * 加载商家_提现
     *
     * @param id 主键
     * @return 商家_提现
     */
    @Override
    public BizTake load(String id) {
        return bizTakeDAO.load(id);
    }

    /**
     * 查询
     *
     * @param params   条件MAP
     * @param rowStart 起始位置
     * @param pageSize 分页大小
     * @return
     */
    @Override
    public List<BizTake> query(Map<String, Object> params, int rowStart, int pageSize) {
        if (rowStart == -1 || pageSize == -1) {
            return bizTakeDAO.query(params);
        } else {
            return bizTakeDAO.query(params, rowStart, pageSize);
        }
    }

    /**
     * 查询个数
     *
     * @param params 条件MAP
     * @return
     */
    @Override
    public int count(Map<String, Object> params) {
        return bizTakeDAO.count(params);
    }

    /**
     * 创建提款记录
     *
     * @param bizTake
     * @param bizTakeBank
     * @param bizTakeWwlt
     * @param biz
     */
    @Override
    public String create(BizTake bizTake, BizTakeBank bizTakeBank, BizTakeWwlt bizTakeWwlt, Biz biz) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", biz.getBizNo());
        params.put("take", bizTake.getAmount());
        if (bizTake.getTakeWay() == 3){//自动提现
            params.put("autoMoney",0);
        }else {//手动提现
            BigDecimal autoMoney=ordSV.queryAutoMoney(biz.getBizNo());//自动提现总金额
            params.put("autoMoney",autoMoney);
            bizTake.setReqno(identifierSV.payNo());
        }
        int rtcount = bizWltDAO.update("updateTake", params);//自动提现扣钱
        if (rtcount > 0) {
            bizTake.setId(identifierSV.uniqueId());
            bizTake.setState(Std.TakeSt.OVERING.getKey());
            bizTake.setOptor(biz.getName());
            bizTake.setCrtime(new Date());
            bizTake.setUptime(new Date());
            if (bizTake.getTakeWay() == 1) {
                bizTakeBank.setId(identifierSV.uniqueId());
                bizTakeBank.setTakeNo(bizTake.getReqno());
                bizTakeBank.setMoney(bizTake.getAmount());
                bizTakeBank.setCrtime(new Date());
                bizTakeBank.setUptime(new Date());
                bizTakeBankDAO.insert(bizTakeBank);
            } else if (bizTake.getTakeWay() == 2) {
                bizTakeWwlt.setId(identifierSV.uniqueId());
                bizTakeWwlt.setTakeNo(bizTake.getReqno());
                bizTakeWwlt.setMoney(bizTake.getAmount());
                bizTakeWwlt.setCrtime(new Date());
                bizTakeWwlt.setUptime(new Date());
                bizTakeWwltDAO.insert(bizTakeWwlt);
            }  else if (bizTake.getTakeWay() == 3) {
                bizTake.setOptor(biz.getName()+"(自动)");
                bizTake.setExpln("自动转账到绑定的微信！");
                bizTakeWwlt.setId(identifierSV.uniqueId());
                bizTakeWwlt.setTakeNo(bizTake.getReqno());
                bizTakeWwlt.setMoney(bizTake.getAmount());
                bizTakeWwlt.setCrtime(new Date());
                bizTakeWwlt.setUptime(new Date());
                bizTakeWwltDAO.insert(bizTakeWwlt);
            }else {
                throw new RuntimeException("提款方式错误！");
            }
            bizTakeDAO.insert(bizTake);
        } else {
            return  "已经手动提现，自动提现可提现额度不够！";
        }

        return null;

    }

    /**
     * 修改提款记录
     *
     * @param bizTake
     * @param bizTakeBank
     * @param bizTakeWwlt
     * @param biz
     */
    @Override
    public void update(BizTake bizTake, BizTakeBank bizTakeBank, BizTakeWwlt bizTakeWwlt, Biz biz) {

        bizTake.setUptime(new Date());
        bizTakeDAO.update(bizTake);

        if (bizTakeBank != null) {
            bizTakeBank.setUptime(new Date());
            bizTakeBankDAO.update(bizTakeBank);
        }

        if (bizTakeWwlt != null) {
            bizTakeWwlt.setUptime(new Date());
            bizTakeWwltDAO.update(bizTakeWwlt);
        }

    }

    /**
     * 查询提现列表
     *
     * @param params
     * @param rowStart
     * @param pageSize
     * @return
     */
    @Override
    public List<BizTake> queryJoinBiz(Map<String, Object> params, int rowStart, int pageSize) {
        if (rowStart == -1 || pageSize == -1) {
            return bizTakeDAO.query("queryJoinBiz", params);
        } else {
            return bizTakeDAO.query("queryJoinBiz", params, rowStart, pageSize);
        }
    }

    /**
     * 查询提现列表
     *
     * @param params
     * @return
     */
    @Override
    public int countJoinBiz(Map<String, Object> params) {
        return bizTakeDAO.count("countJoinBiz", params);
    }

    /**
     * 自动转账成功
     *
     * @param reqno
     * @param wxAcct
     */
    @Override
    public void updateTransfersSuccess(String reqno, String wxAcct) {
        BizTake bizTake=new BizTake();
        bizTake.setReqno(reqno);
        bizTake.setTsno(wxAcct);
        bizTake.setState(1);
        bizTake.setDoneDate(new Date());
        bizTakeDAO.update("updateByReqno",bizTake);
        bizTake=this.loadByReqno(reqno);
        ordSV.updateTransfersOrdSucess(bizTake.getBizNo());
        /*BizTakeWwlt bizTakeWwlt=new BizTakeWwlt();
        bizTakeWwlt.setTakeNo(reqno);
        bizTakeWwlt.setWxAcct(wxAcct);
        bizTakeWwltDAO.update("updateByTakeNo",bizTakeWwlt);*/

    }

    /**
     * 自动转账失败
     * @param partner_trade_no
     * @param expln
     */
    @Override
    public void updateTransfersFail(String partner_trade_no,String expln ) {

        BizTake bizTake = this.loadByReqno(partner_trade_no);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", bizTake.getBizNo());
        params.put("take", bizTake.getAmount());
        int rtcount = bizWltDAO.update("updateTakeAddTake", params);
        if (rtcount>0){
            BizTake bizTake_update=new BizTake();
            bizTake_update.setReqno(partner_trade_no);
            bizTake_update.setState(3);
            bizTake_update.setExpln(expln);
            bizTakeDAO.update("updateByReqno",bizTake_update);
        }

    }

    /**
     * 自动转账
     */
    @Override
    public void createTransfersTask(List<Map<String,Object>>  takeOrdList,Integer i) {

        String bizNo=String.valueOf(takeOrdList.get(i).get("bizNo"));//收款人
        String resvBizNo=String.valueOf(takeOrdList.get(i).get("resvBizNo"));//打款人
        String bizNum=String.valueOf(takeOrdList.get(i).get("bizNum"));//打款商户号
        String openid=String.valueOf(takeOrdList.get(i).get("openid"));//收款人openid
        String nick=String.valueOf(takeOrdList.get(i).get("nick"));//收款人微信昵称
        String money=String.valueOf(takeOrdList.get(i).get("money"));//金额
        LOGGER.info("---------------开始转账:bizNo:"+bizNo+",resvBizNo"+resvBizNo+",money"+money+",bizNum"+bizNum+",openid"+openid+",nick"+nick);
        if(bizNo.equals("null")||resvBizNo.equals("null")||bizNum.equals("null")||
                openid.equals("null")||nick.equals("null")||money.equals("null")){

            LOGGER.debug("---------------参数有为空的，不进行转账！");
            return;
        }

        if (bizNum!=null&&openid!=null){
            String partner_trade_no=identifierSV.payNo();

            BizWx bizWx_transfers=bizWxSV.loadByBizNumAndBizNo(bizNum,resvBizNo);
            if (bizWx_transfers==null||bizWx_transfers.getId()==null){
                LOGGER.debug("---------------无法获取转账限额！");
                return;
            }
            //如果转账的钱数小于限额。则不转账
            BigDecimal transfers_money=new BigDecimal(money);
            /*
            if(transfers_money.compareTo(bizWx_transfers.getGiroQuota())==-1){
                LOGGER.debug("---------------转账的钱数小于限额,不进行转账！");
                return;
            }
            */

            //转账，所需参数
            String appid=bizWx_transfers.getAppId();
            String mch_id=bizWx_transfers.getBizNum();
            String key=bizWx_transfers.getApiSkey();
            String parBizNo=bizWx_transfers.getBizNo();//打款人
            //转账
            BizTakeBank bizTakeBank=new BizTakeBank();
            BizTakeWwlt bizTakeWwlt=new BizTakeWwlt();
            bizTakeWwlt.setNick(nick);
            BizTake bizTake=new BizTake();
            Biz biz=bizSV.loadBizByBizNo(bizNo);//设备所属商家
            bizTake.setTakeWay(3);
            bizTake.setReqno(partner_trade_no);
            bizTake.setAmount(transfers_money);//转账金额
            bizTake.setMtcCost((transfers_money.multiply(bizWx_transfers.getGiroFee())).setScale(2,BigDecimal.ROUND_UP));//手续费
            bizTake.setRealAmount(transfers_money.subtract(bizTake.getMtcCost()));//实际转账金额
            bizTake.setBizId(biz.getId());
            bizTake.setBizNo(biz.getBizNo());
            bizTake.setParBizNo(parBizNo);
            String msg=this.create(bizTake, bizTakeBank, bizTakeWwlt, biz);
            if (msg==null){
                try {
                    this.transfers(null,bizWx_transfers.getApiCert(),openid,bizTake.getRealAmount(),partner_trade_no,appid,mch_id,key);
                } catch (Exception e) {
                    this.updateTransfersFail(partner_trade_no,e.getMessage());//失败退可提现
                    LOGGER.debug("-----------------转账异常:"+e.getMessage());

                    e.printStackTrace();
                }
            }else {
                //this.updateTransfersFail(partner_trade_no,msg);//失败退可提现
                BizTake bizTake_update=new BizTake();
                bizTake_update.setReqno(partner_trade_no);
                bizTake_update.setState(3);
                bizTake_update.setExpln(msg);
                bizTakeDAO.update("updateByReqno",bizTake_update);
                LOGGER.debug("-----------------创建转账异常:"+msg);
            }

        }

        LOGGER.info("-------------------------转账结束:bizNo:"+bizNo+",resvBizNo"+resvBizNo+",money"+money);
    }

    /**
     * 通过申请单号获取  BizTake
     *
     * @param reqno 申请单号
     * @return
     */
    @Override
    public BizTake loadByReqno(String reqno) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("reqno",reqno);
        return bizTakeDAO.queryUnique("loadByReqno",params);
    }

    /**
     * 转账。
     * 转账需要证书文件，请到商户平台中去下载，放到项目的某目录下。我这里是放在了src目录下。
     * win7系统下，需要先导入证书。详情请看官网文档说明：https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_3     表4.2：证书文件说明、apiclient_cert.p12
     * <p>
     * @param request
     * @return
     * @throws Exception
     */
    public void transfers(HttpServletRequest request,String zipPath, String openid, BigDecimal payMoney, String partner_trade_no,
                          String appid, String mch_id, String key) throws Exception {

        BigDecimal Money100 = new BigDecimal(100);
        Integer totleMoney = payMoney.multiply(Money100).intValue();
        /**
         * 以下代码使用微信提供的代码
         */

        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        zipPath=WeiXinUtil.getRootPath()+zipPath;
        System.out.println("转账-----------------证书path:"+zipPath);
        InputStream instream =WeiXinUtil.readZipContext("apiclient_cert.p12",zipPath);
        //InputStream instream = TransfersTaskCtrl.class.getResourceAsStream("/apiclient_cert.p12");//指定证书文件
        try {
            keyStore.load(instream, mch_id.toCharArray());//密码默认是商户号
        } finally {
            instream.close();
        }

        // 信任自己的CA和自签名证书，密码默认为商户号
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, mch_id.toCharArray()).build();
        // 只允许TLSv1协议
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        try {
            //指定转账接口
            HttpPost httppost = new HttpPost("https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers");
            System.out.println("执行请求操作：" + httppost.getRequestLine());

            //设置请求报文
            String ip= BaseConfig.getSysProperties().getProperty("wx.ip").toString();//获得本机IP.因为转账是服务器端主动的。所以原来调用接口的机器Ip地址（WeiXinUtil.getLocalIp(request)）就换成获取本机IP了。
            StringEntity myEntity = new StringEntity(getXmlInfo(ip,openid,totleMoney,partner_trade_no,appid,mch_id,key), "UTF-8");
            httppost.setEntity(myEntity);

            //请求退款接口
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();

                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());

                StringBuffer result = new StringBuffer();
                if (entity != null) {
                    //System.out.println("请求退款接口，返回的内容的长度: " + entity.getContentLength());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                    String text;
                    while ((text = bufferedReader.readLine()) != null) {
                        System.out.println(text);
                        result.append(text);
                    }
                }
                EntityUtils.consume(entity);

                System.out.println("转账结果：\n" + result);

                Map<String, String> map = XMLUtil.doXMLParse(result.toString());
                if (map.get("result_code").equals("SUCCESS")) {
                    System.out.println("转账成功！");
                    //这里可以进行其他的业务逻辑...
                    //String reqno=map.get("partner_trade_no");//商家订单号
                    String wxAcct=map.get("payment_no").toString();//微信单号
                    System.out.println("-------商家订单号------"+partner_trade_no);
                    System.out.println("-------微信转账单号------"+wxAcct);
                    this.updateTransfersSuccess(partner_trade_no,wxAcct);
                    System.out.println("-------转账成功------");
                } else {
                    System.out.println("转账失败！"+map.get("err_code_des"));
                    //这里可以进行其他的业务逻辑...
                    this.updateTransfersFail(partner_trade_no,map.get("err_code_des"));//失败退可提现
                    System.out.println("-------转账失败------");
                }
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }

    }

    /**
     * 生成xml格式退款请求报文 转账
     * <p>
     * 作者: 席庄捶
     * 日期：2016年09月22日
     *
     * @return
     */
    private String getXmlInfo(String spbill_create_ip,String openid,Integer totleMoney,String partner_trade_no,
                              String appid,String mch_id,String key) {
        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        parameters.put("mch_appid", appid); //appid
        parameters.put("mchid", mch_id); //微信商户号
        parameters.put("nonce_str", WeiXinUtil.getNonceStr()); //随机字符串

        parameters.put("partner_trade_no", partner_trade_no); //商户订单号
        parameters.put("openid", openid); //用户openid
        parameters.put("check_name", "NO_CHECK"); //NO_CHECK：不校验真实姓名 FORCE_CHECK：强校验真实姓名（未实名认证的用户会校验失败，无法转账）OPTION_CHECK：针对已实名认证的用户才校验真实姓名（未实名认证用户不校验，可以转账成功）
        parameters.put("amount", totleMoney); //总金额
        parameters.put("desc", "自动转账打款"); //企业付款操作说明信息。必填。
        parameters.put("spbill_create_ip", spbill_create_ip); //调用接口的机器Ip地址

        //创建签名，算法与支付的算法一样
        String sign = createSignTransfers("UTF-8", parameters,key);
        parameters.put("sign", sign);

        String data = getRequestXmlTransfers(parameters);
        return data;
    }

    /**
     * 生成退款申请的请求报文 转账
     * <p>
     * 作者: 席庄捶
     * 日期：2016年09月22日
     *
     * @param parameters
     * @return
     */
    public  String getRequestXmlTransfers(SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set<Map.Entry<Object, Object>> es = parameters.entrySet();
        Iterator<Map.Entry<Object, Object>> it = es.iterator();
        while (it.hasNext()) {
            Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) it.next();
            String k = (String) entry.getKey();
            String v = entry.getValue() + "";
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
            } else {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 生成签名 转账
     * <p>
     * 作者: 席庄捶
     * 日期：2016年09月22日
     *
     * @param characterEncoding
     * @param parameters
     * @return
     */
    public  String createSignTransfers(String characterEncoding, SortedMap<Object, Object> parameters,
                                       String key) {
        StringBuffer sb = new StringBuffer();
        Set<Map.Entry<Object, Object>> es = parameters.entrySet();
        Iterator<Map.Entry<Object, Object>> it = es.iterator();
        while (it.hasNext()) {
            Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            /** 如果参数为key或者sign，则不参与加密签名 */
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        /** 支付密钥必须参与加密，放在字符串最后面 */
        sb.append("key=" + key);
        /** 记得最后一定要转换为大写 */
        String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
        return sign;
    }

}
