package com.zzwdkj.common.weixin;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zzwdkj.Test.Test;
import com.zzwdkj.common.BaseConfig;
import com.zzwdkj.common.MD5Util;
import com.zzwdkj.common.RedisUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * Created by xizhuangchui on 2016年09月29日16:50:13
 */
public class WeiXinUtil {

    /**
     * 获取预支付单号prepay_id
     */
    public static final String UNI_URL = BaseConfig.getSysProperties().getProperty("wx.uniurl").toString();
    public static final String _proname= "_"+BaseConfig.getSysProperties().getProperty("wx.proname").toString();
    //public static final String APPID_JS = BaseConfig.getSysProperties().getProperty("wx.TokenAppid").toString();
    //public static final String SECRET_JS = BaseConfig.getSysProperties().getProperty("wx.TokenSecret").toString();



    /**
     * 微信公众号APPID
     */
    public static String getAPPID(HttpSession httpSession) {
        return httpSession.getAttribute("APPID").toString();
    }

    /**
     * 微信公众号SECRET
     */
    public static String getSECRET(HttpSession httpSession) {
        return httpSession.getAttribute("SECRET").toString();
    }

    /**
     * 微信公众号绑定的商户号
     */
    public static String getMchId(HttpSession httpSession) {
        return httpSession.getAttribute("MCH_ID").toString();
    }

    /**
     * 支付密钥，商户平台 > API安全 > 密钥管理 中进行设置
     */
    public static String getApiKey(HttpSession httpSession) {
        return httpSession.getAttribute("API_KEY").toString();
    }

    /**
     *  获取token
     * @return
     */
    public static String getToken(HttpSession httpSession){

        String turl = String.format("%s?grant_type=client_credential&appid=%s&secret=%s", "https://api.weixin.qq.com/cgi-bin/token",
                getAPPID(httpSession), getSECRET(httpSession));
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(turl);
        JsonParser jsonparer = new JsonParser();// 初始化解析json格式的对象
        String result = null;
        try {
            HttpResponse res = client.execute(get);
            String responseContent = null; // 响应内容
            HttpEntity entity = res.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
            JsonObject json = jsonparer.parse(responseContent)
                                .getAsJsonObject();
            // 将json字符串转换为json对象
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            {
                if (json.get("errcode") != null)
                {// 错误时微信会返回错误码等信息，{"errcode":40013,"errmsg":"invalid appid"}
                }
                else
                {// 正常情况下{"access_token":"ACCESS_TOKEN","expires_in":7200}
                    result = json.get("access_token").getAsString();
                    RedisUtil.setex("user_access_token_"+getAPPID(httpSession)+_proname ,7200, result);

                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            // 关闭连接 ,释放资源
            client.getConnectionManager().shutdown();
            return result;
        }
    }

    public  static String httpPostXml(String urlStr,String xmlInfo) {
        String sTotalString="";
        try {
            URL url = new URL(urlStr);
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            con.setRequestProperty("Pragma:", "no-cache");
            con.setRequestProperty("Cache-Control", "no-cache");
            con.setRequestProperty("Content-Type", "text/xml");

            OutputStreamWriter out = new OutputStreamWriter(con
                    .getOutputStream(),"UTF-8");
            //System.out.println("urlStr=" + urlStr);
            //System.out.println("xmlInfo=" + xmlInfo);
            out.write(new String(xmlInfo.getBytes("UTF-8")));
            out.flush();
            out.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(con
                    .getInputStream()));
            String line = "";

            for (line = br.readLine(); line != null; line = br.readLine()) {
                sTotalString+=line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sTotalString;
    }

    /**
     * 获取POST请求返回值
     * @param postUrl
     * @param parameters
     * @return String
     * @throws IOException
     */
    public static String httpPost(String postUrl,String parameters) throws IOException {

        /**
         * 首先要和URL下的URLConnection对话。 URLConnection可以很容易的从URL得到。比如： // Using
         *  java.net.URL and //java.net.URLConnection
         */
        URL url = new URL(postUrl);
        URLConnection connection = url.openConnection();
        /**
         * 然后把连接设为输出模式。URLConnection通常作为输入来使用，比如下载一个Web页。
         * 通过把URLConnection设为输出，你可以把数据向你个Web页传送。下面是如何做：
         */
        connection.setDoOutput(true);
        /**
         * 最后，为了得到OutputStream，简单起见，把它约束在Writer并且放入POST信息中，例如： ...
         */
        OutputStreamWriter out = new OutputStreamWriter(connection
                .getOutputStream(), "UTF-8");
        out.write(parameters); //post的关键所在！"username=kevin&password=*********"
        // remember to clean up
        out.flush();
        out.close();
        /**
         * 这样就可以发送一个看起来象这样的POST：
         * POST /jobsearch/jobsearch.cgi HTTP 1.0 ACCEPT:
         * text/plain Content-type: application/x-www-form-urlencoded
         * Content-length: 99 username=bob password=someword
         */
        // 一旦发送成功，用以下方法就可以得到服务器的回应：
        String sCurrentLine;
        String sTotalString;
        sCurrentLine = "";
        sTotalString = "";
        InputStream l_urlStream;
        l_urlStream = connection.getInputStream();
        // 传说中的三层包装阿！
        BufferedReader l_reader = new BufferedReader(new InputStreamReader(
                l_urlStream,"UTF-8"));
        while ((sCurrentLine = l_reader.readLine()) != null) {
            sTotalString +=sCurrentLine ;

        }
        //System.out.println(sTotalString);
        return sTotalString;
    }

    /**
     * 获取POST请求返回值
     * @param postUrl
     * @param parameters
     * @return JsonObject
     * @throws IOException
     */
    public static JsonObject httpPost2(String postUrl, String parameters) throws IOException {

        /**
         * 首先要和URL下的URLConnection对话。 URLConnection可以很容易的从URL得到。比如： // Using
         *  java.net.URL and //java.net.URLConnection
         */
        URL url = new URL(postUrl);
        URLConnection connection = url.openConnection();
        /**
         * 然后把连接设为输出模式。URLConnection通常作为输入来使用，比如下载一个Web页。
         * 通过把URLConnection设为输出，你可以把数据向你个Web页传送。下面是如何做：
         */
        connection.setDoOutput(true);
        /**
         * 最后，为了得到OutputStream，简单起见，把它约束在Writer并且放入POST信息中，例如： ...
         */
        OutputStreamWriter out = new OutputStreamWriter(connection
                .getOutputStream(), "UTF-8");
        out.write(parameters); //post的关键所在！"username=kevin&password=*********"
        // remember to clean up
        out.flush();
        out.close();
        /**
         * 这样就可以发送一个看起来象这样的POST：
         * POST /jobsearch/jobsearch.cgi HTTP 1.0 ACCEPT:
         * text/plain Content-type: application/x-www-form-urlencoded
         * Content-length: 99 username=bob password=someword
         */
        // 一旦发送成功，用以下方法就可以得到服务器的回应：
        String sCurrentLine;
        String sTotalString;
        sCurrentLine = "";
        sTotalString = "";
        InputStream l_urlStream;
        l_urlStream = connection.getInputStream();
        // 传说中的三层包装阿！
        BufferedReader l_reader = new BufferedReader(new InputStreamReader(
                l_urlStream,"UTF-8"));
        while ((sCurrentLine = l_reader.readLine()) != null) {
            sTotalString += sCurrentLine;

        }
        //System.out.println(sTotalString);
        JsonParser jsonparer = new JsonParser();// 初始化解析json格式的对象
        JsonObject json = jsonparer.parse(sTotalString)
                .getAsJsonObject();
        return json;
    }
    /**
     * 通过code换取网页授权access_token
     * @param code
     */
    public static void getRefreshToken (String code,HttpSession httpSession){
        JsonParser jsonparer = new JsonParser();// 初始化解析json格式的对象
        try {
            //通过code换取网页授权access_token
            String responseContent=httpPost("https://api.weixin.qq.com/sns/oauth2/access_token",
                    "appid="+getAPPID(httpSession)+"&secret="+getSECRET(httpSession)+"&code="+code+"&grant_type=authorization_code");
            JsonObject json = jsonparer.parse(responseContent)
                    .getAsJsonObject();
            // 将json字符串转换为json对象

            if (json.get("errcode") != null&&!json.get("errcode").equals("0")) {
                // 错误时微信会返回错误码等信息，{"errcode":40029,"errmsg":"invalid code"}
            }
            else{
                // 正常情况
                RedisUtil.set("user_refresh_token_"+getAPPID(httpSession)+_proname,json.get("refresh_token").getAsString());
                httpSession.setAttribute("user_openid",json.get("openid").getAsString());
                //RedisUtil.set("user_access_token" , json.get("access_token").getAsString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 刷新access_token
     */
    public static void refreshToken(HttpSession httpSession){
        //刷新access_token
        JsonParser jsonparer = new JsonParser();// 初始化解析json格式的对象
        try {
            String reftoken=httpPost("https://api.weixin.qq.com/sns/oauth2/refresh_token",
                    "appid="+getAPPID(httpSession)+"&grant_type=refresh_token&refresh_token="+RedisUtil.get("user_refresh_token_"+getAPPID(httpSession)+_proname));
            JsonObject json = jsonparer.parse(reftoken)
                    .getAsJsonObject();
            // 将json字符串转换为json对象

            if (json.get("errcode") != null&&!json.get("errcode").equals("0")) {
                // 错误时微信会返回错误码等信息，{"errcode":40029,"errmsg":"invalid code"}
            }
            else{
                // 正常情况
                RedisUtil.set("user_refresh_token_"+getAPPID(httpSession)+_proname , json.get("refresh_token").getAsString());
                httpSession.setAttribute("user_openid",json.get("openid").getAsString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 拉取用户信息(需scope为 snsapi_userinfo)
     */
    public static Map<String,Object> getUserInfo(HttpSession httpSession){
        if (RedisUtil.get("user_access_token_"+getAPPID(httpSession)+_proname)==null||RedisUtil.get("user_access_token_"+getAPPID(httpSession)+_proname).equals("")){
            getToken(httpSession);
        }

        JsonParser jsonparer = new JsonParser();// 初始化解析json格式的对象
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String reftoken=httpPost("https://api.weixin.qq.com/cgi-bin/user/info",
                    "access_token=" + RedisUtil.get("user_access_token_"+getAPPID(httpSession)+_proname)+"&openid="+httpSession.getAttribute("user_openid")+"&lang=zh_CN");
            JsonObject json = jsonparer.parse(reftoken)
                    .getAsJsonObject();
            // 将json字符串转换为json对象

            if (json.get("errcode") != null&&!json.get("errcode").equals("0")) {
                // 错误时微信会返回错误码等信息，{"errcode":40029,"errmsg":"invalid code"}
            }
            else{
                // 正常情况
                map.put("subscribe",json.get("subscribe").getAsString());//用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息
                map.put("openid",json.get("openid").getAsString());//用户的标识，对当前公众号唯一
                if (json.get("nickname")!=null){
                    map.put("nickname",json.get("nickname").getAsString());//用户的昵称
                }
                if (json.get("sex")!=null){
                    map.put("sex",json.get("sex").getAsString());//用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
                }
                if (json.get("province")!=null){
                    map.put("province",json.get("province").getAsString());//用户所在省份
                }
                if (json.get("city")!=null){
                    map.put("city",json.get("city").getAsString());//用户所在城市
                }
                if (json.get("country")!=null){
                    map.put("country",json.get("country").getAsString());//用户所在国家
                }
                if (json.get("language")!=null){
                    map.put("language",json.get("language").getAsString());//用户的语言，简体中文为zh_CN
                }
                if (json.get("headimgurl")!=null){
                    map.put("headimgurl",json.get("headimgurl").getAsString());//用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
                }
                if (json.get("subscribe_time")!=null){
                    map.put("subscribe_time",json.get("subscribe_time").getAsString());//用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
                }
                if (json.get("groupid")!=null){
                    map.put("groupid",json.get("groupid").getAsString());//用户所在的分组ID
                }
                if (json.get("remark")!=null){
                    map.put("remark",json.get("remark").toString());//公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
                }
                //httpSession.setAttribute("user_unionid",json.get("unionid").getAsString());//只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
                //map.put("unionid", json.get("unionid").getAsString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 获取二维码图片Ticket
     * @return
     */
    public  static  Map<String,Object> getQrcodeTicket(HttpSession httpSession){
        if (RedisUtil.get("user_access_token_"+getAPPID(httpSession)+_proname)==null||RedisUtil.get("user_access_token_"+getAPPID(httpSession)+_proname).equals("")){
            getToken(httpSession);
        }

        JsonParser jsonparer = new JsonParser();// 初始化解析json格式的对象
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String reftoken=httpPost("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + RedisUtil.get("user_access_token_"+getAPPID(httpSession)+_proname),
                    "{\"expire_seconds\": 604800, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": 123}}}");
            JsonObject json = jsonparer.parse(reftoken)
                    .getAsJsonObject();
            // 将json字符串转换为json对象

            if (json.get("ticket") != null) {
                map.put("QrcodeTicket",json.get("ticket").getAsString());
                //RedisUtil.setex("QrcodeTicket",604800,json.get("ticket").getAsString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     *生成JS接口调用签名
     * @param request
     * @return
     */
    public static Map<String, Object> getSignPackage(HttpServletRequest request,HttpSession httpSession){
        Map<String, Object> signPackage = new HashMap<String, Object>();
        String jsapiTicket=getJsApiTicket(httpSession);
        String proname= BaseConfig.getSysProperties().getProperty("wx.proname").toString();
        if (proname.equals("null")){
            proname="";
        }else {
            proname="/"+proname;
        }
        String url= "http://" + request.getServerName()+proname //服务器地址 "/MHLPro"
                + request.getServletPath();      //请求页面或其他地址
        if (request.getQueryString()!=null){
            url+= "?" + (request.getQueryString()); //参数
        }
        System.out.println("js接口签名url"+url);
        Long timestamp=new Date().getTime();
        String nonceStr=createNonceStr(16);
        String rawString="jsapi_ticket="+jsapiTicket+"&noncestr="+nonceStr+"&timestamp="+timestamp+"&url="+url+"";
        String signature= DigestUtils.shaHex(rawString);
        signPackage.put("appId", getAPPID(httpSession));
        signPackage.put("nonceStr", nonceStr);
        signPackage.put("timestamp", timestamp);
        signPackage.put("url", url);
        //signPackage.put("rawString",rawString);
        signPackage.put("signature", signature);
        return signPackage;
    }

    /**
     * 通过access_token 采用http GET方式请求获得jsapi_ticket（有效期7200秒，开发者必须在自己的服务全局缓存jsapi_ticket）
     * @return jsapi_ticket
     */
    private static String getJsApiTicket(HttpSession httpSession) {

        if (RedisUtil.get("user_access_token_"+getAPPID(httpSession)+_proname)==null||RedisUtil.get("user_access_token_"+getAPPID(httpSession)+_proname).equals("")){
            getToken(httpSession);
        }

        JsonParser jsonparer = new JsonParser();// 初始化解析json格式的对象
        String jsapi_ticket=RedisUtil.get("user_jsapi_ticket_"+getAPPID(httpSession)+_proname);
        if (jsapi_ticket==null||jsapi_ticket.equals("")){
            try {
                //通过code换取网页授权access_token
                String responseContent=httpPost("https://api.weixin.qq.com/cgi-bin/ticket/getticket",
                        "access_token="+RedisUtil.get("user_access_token_"+getAPPID(httpSession)+_proname)+"&type=jsapi");
                JsonObject json = jsonparer.parse(responseContent)
                        .getAsJsonObject();
                // 将json字符串转换为json对象
                if (json.get("errcode") != null&&!json.get("errcode").getAsString().equals("0")) {
                    // 错误时微信会返回错误码等信息，{"errcode":40029,"errmsg":"invalid code"}
                }
                else{
                    // 正常情况
                    jsapi_ticket=json.get("ticket").getAsString();
                    RedisUtil.setex("user_jsapi_ticket_"+getAPPID(httpSession)+_proname, 7000, jsapi_ticket);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return jsapi_ticket;
    }

    /**
     * 生成随机字符串
     * @return
     */
    public static String createNonceStr(Integer length) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String str = "";
        for (int i = 0; i < length; i++) {
            str += chars.charAt((int) (Math.random() * (chars.length() - 1)));
        }
        return str;
    }

    /**
     * 从Request对象中获得客户端IP，处理了HTTP代理服务器和Nginx的反向代理截取了ip
     * @param request
     * @return ip
     */
    public static String getLocalIp(HttpServletRequest request) {
        /*String remoteAddr = request.getRemoteAddr();
        String forwarded = request.getHeader("X-Forwarded-For");
        String realIp = request.getHeader("X-Real-IP");

        String ip = null;
        if (realIp == null) {
            if (forwarded == null) {
                ip = remoteAddr;
            } else {
                ip = remoteAddr + "/" + forwarded.split(",")[0];
            }
        } else {
            if (realIp.equals(forwarded)) {
                ip = realIp;
            } else {
                if(forwarded != null){
                    forwarded = forwarded.split(",")[0];
                }
                ip = realIp + "/" + forwarded;
            }
        }
        return ip;*/
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.split(",")[0];
    }

    public static String maptoXml(Map map) {
        Document document = DocumentHelper.createDocument();
        Element nodeElement = document.addElement("node");
        for (Object obj : map.keySet()) {
            Element keyElement = nodeElement.addElement("key");
            keyElement.addAttribute("label", String.valueOf(obj));
            keyElement.setText(String.valueOf(map.get(obj)));
        }
        return doc2String(document);
    }
    /**
     *Document 转成String
     * @param document
     * @return
     */
    public static String doc2String(Document document) {
        String s = "";
        try {
            // 使用输出流来进行转化
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            // 使用UTF-8编码
            OutputFormat format = new OutputFormat("   ", true, "UTF-8");
            XMLWriter writer = new XMLWriter(out, format);
            writer.write(document);
            s = out.toString("UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return s;
    }


    /**
     * 用来演示提交XML格式数据的例子
     */

    public static void PostXMLClient(String[] args) throws Exception {
        File input = new File("text.xml");
        PostMethod post = new PostMethod("https://api.mch.weixin.qq.com/pay/unifiedorder");
        // 设置请求的内容直接从文件中读取
        post.setRequestBody(new FileInputStream(input));
        if (input.length() < Integer.MAX_VALUE)
            post.setRequestContentLength(input.length());
        else
            post.setRequestContentLength(EntityEnclosingMethod.CONTENT_LENGTH_CHUNKED);
        // 指定请求内容的类型
        post.setRequestHeader("Content-type", "text/xml; charset=UTF-8");
        org.apache.commons.httpclient.HttpClient httpclient = new org.apache.commons.httpclient.HttpClient();
        int result = httpclient.executeMethod(post);
        //System.out.println("Response status code: " + result);
        //System.out.println("Response body: ");
        //System.out.println(post.getResponseBodyAsString());
        post.releaseConnection();
    }



    /**
     * 获取32位随机字符串
     *
     * 作者: xizhuangchui 
     * 日期：2016年09月29日16:50:28
     * @return
     */
    public static String getNonceStr() {
        Random random = new Random();
        return MD5Util.MD5Encode(String.valueOf(random.nextInt(10000)), "UTF-8");
    }

    /**
     * 时间戳
     *
     * 作者: xizhuangchui 
     * 日期：2016年09月29日16:50:37
     * @return
     */
    public static String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }






    /**
     * 解压到指定目录
     * @param zipPath
     * @param descDir
     * @author 席庄捶
     */
    public static void unZipFiles(String zipPath,String descDir)throws IOException{
        unZipFiles(new File(zipPath), descDir);
    }
    /**
     * 解压文件到指定目录
     * @param zipFile
     * @param descDir
     * @author 席庄捶
     */
    @SuppressWarnings("rawtypes")
    public static void unZipFiles(File zipFile,String descDir)throws IOException{
        File pathFile = new File(descDir);
        if(!pathFile.exists()){
            pathFile.mkdirs();
        }
        ZipFile zip = new ZipFile(zipFile);
        for(Enumeration entries = zip.entries(); entries.hasMoreElements();){
            ZipEntry entry = (ZipEntry)entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream in = zip.getInputStream(entry);
            String outPath = (descDir+zipEntryName).replaceAll("\\*", "/");;
            //判断路径是否存在,不存在则创建文件路径
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
            if(!file.exists()){
                file.mkdirs();
            }
            //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
            if(new File(outPath).isDirectory()){
                continue;
            }
            //输出文件路径信息
            System.out.println(outPath);

            OutputStream out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[1024];
            int len;
            while((len=in.read(buf1))>0){
                out.write(buf1,0,len);
            }
            in.close();
            out.close();
        }
        System.out.println("******************解压完毕********************");
    }


    public static InputStream readZipContext( String fileName,String zipPath) throws Exception{
        System.out.println("读取zip文件"+zipPath);
        ZipFile zf=new ZipFile(zipPath);

        InputStream in=new BufferedInputStream(new FileInputStream(zipPath));
        ZipInputStream zin=new ZipInputStream(in);
        //ZipEntry 类用于表示 ZIP 文件条目。
        ZipEntry ze;
        while((ze=zin.getNextEntry())!=null){
            if(ze.isDirectory()){
                //为空的文件夹什么都不做
            }else{

                System.err.println("file:"+ze.getName()+"\nsize:"+ze.getSize()+"bytes");
                if(ze.getSize()>0&&fileName.equals(ze.getName().toString())){
                    try {
                        return zf.getInputStream(ze);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }
        }
        return null;
    }


    /**
     * 获取项目根目录
     * @return
     */
    public static String getRootPath() {
        String classPath = WeiXinUtil.class.getClassLoader().getResource("").getPath();
        String rootPath = "";
        //windows下
        if("\\".equals(File.separator)){
            rootPath = classPath.substring(1,classPath.indexOf("/WEB-INF/classes"));
            rootPath = rootPath.replace("/", "\\");
        }
        //linux下
        if("/".equals(File.separator)){
            rootPath = classPath.substring(0,classPath.indexOf("/WEB-INF/classes"));
            rootPath = rootPath.replace("\\", "/");
        }
        return rootPath;
    }

    //-----------------------------------经纬度

    /** 地球半径  （米）*/
    private static final double EARTH_RADIUS = 6371000;

    /**
     * 获取范围内的经纬度(依次为左上角、右上角、左下角、右下角)
     * @param lat 维度
     * @param lng 经度
     * @param distance 范围距离(米)
     * @return
     */
    public static Location[] getRectangle4Point(double lat, double lng,double distance ) {
        /** 左上角 */
        Location left_top = null;
        /** 右上角 */
        Location right_top = null;
        /** 左下角 */
        Location left_bottom = null;
        /** 右下角 */
        Location right_bottom = null;
        // float dlng = 2 * asin(sin(distance / (2 * EARTH_RADIUS)) / cos(lat));
        // float dlng = degrees(dlng) // 弧度转换成角度

        double dlng = 2 * Math.asin(Math.sin(distance/(2*EARTH_RADIUS))/Math.cos(Math.toRadians(lat)));
        dlng = Math.toDegrees(dlng);

        // dlat = distance / EARTH_RADIUS
        // dlng = degrees(dlat) # 弧度转换成角度
        double dlat = distance / EARTH_RADIUS;
        dlat = Math.toDegrees(dlat); // # 弧度转换成角度

        // left-top : (lat + dlat, lng - dlng)
        // right-top : (lat + dlat, lng + dlng)
        // left-bottom : (lat - dlat, lng - dlng)
        // right-bottom: (lat - dlat, lng + dlng)
        left_top = new Location(lat + dlat, lng - dlng);
        right_top = new Location(lat + dlat, lng + dlng);
        left_bottom = new Location(lat - dlat, lng - dlng);
        right_bottom = new Location(lat - dlat, lng + dlng);
        Location[] locations = new Location[4];
        locations[0] = left_top;
        locations[1] = right_top;
        locations[2] = left_bottom;
        locations[3] = right_bottom;
        return  locations;
    }

    public static double hav(double theta) {
        double s = Math.sin(theta / 2);
        return s * s;
    }

    /**
     * 两点距离(米)
     * @param lat0 维度
     * @param lng0 经度
     * @param lat1 维度
     * @param lng1 经度
     * @return
     */
    public static double getDistance(double lat0, double lng0, double lat1,
                                     double lng1) {
        // from math import sin, asin, cos, radians, fabs, sqrt

        // def hav(theta):
        // s = sin(theta / 2)
        // return s * s

        // def get_distance_hav(lat0, lng0, lat1, lng1):
        // "用haversine公式计算球面两点间的距离。"
        // # 经纬度转换成弧度
        // lat0 = radians(lat0)
        // lat1 = radians(lat1)
        // lng0 = radians(lng0)
        // lng1 = radians(lng1)

        // dlng = fabs(lng0 - lng1)
        // dlat = fabs(lat0 - lat1)
        // h = hav(dlat) + cos(lat0) * cos(lat1) * hav(dlng)
        // distance = 2 * EARTH_RADIUS * asin(sqrt(h))

        // return distance
        lat0 = Math.toRadians(lat0);
        lat1 = Math.toRadians(lat1);
        lng0 = Math.toRadians(lng0);
        lng1 = Math.toRadians(lng1);

        double dlng = Math.abs(lng0 - lng1);
        double dlat = Math.abs(lat0 - lat1);
        double h = hav(dlat) + Math.cos(lat0) * Math.cos(lat1) * hav(dlng);
        double distance = 2 * EARTH_RADIUS * Math.asin(Math.sqrt(h));

        return distance;
    }


    public static String removeNonBmpUnicode(String str) {
        if (str == null) {
            return null;
        }
        str = str.replaceAll("[^\\u0000-\\uFFFF]", "");
        byte[] b_text=str.getBytes();
        for (int i = 0; i < b_text.length; i++)
        {
            if((b_text[i] & 0xF8)== 0xF0){
                for (int j = 0; j < 4; j++) {
                    b_text[i+j]=0x30;
                }
                i+=3;
            }
        }
        str=new String(b_text);
        str=str.replace("0000","");
        return str;
    }
}
