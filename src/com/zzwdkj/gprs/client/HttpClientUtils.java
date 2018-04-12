package com.zzwdkj.gprs.client;

import com.zzwdkj.gprs.Constant;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 调用WEB服务的REST API工具类
 *
 * @author guoxianwei 2016-04-02 16:05
 */
public final class HttpClientUtils {

    private final static Logger LOGGER = Logger.getLogger(HttpClientUtils.class);

    private final static PoolingHttpClientConnectionManager connectionManager;

    private static String web_server_host = "";

    static {

        connectionManager = new PoolingHttpClientConnectionManager();
        // 将最大连接数增加到200
        connectionManager.setMaxTotal(200);
        // 将每个路由基础的连接增加到20
        connectionManager.setDefaultMaxPerRoute(20);
        //将目标主机的最大连接数增加到50
        connectionManager.setDefaultMaxPerRoute(50);
        Properties properties = new Properties();
        InputStream in = HttpClientUtils.class.getResourceAsStream("../../../../application.properties");

        try {
            properties.load(in);
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        web_server_host = properties.getProperty("web_server_host").toString();
    }

    public static CloseableHttpClient getHttpClient() {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .build();
        return httpClient;
    }

    /**
     * 获取设备编号
     *
     * @param gprs    设备模块号
     * @param devtype 设备类型
     */
    public static String fetchDevno(String gprs, String devtype) {
        CloseableHttpClient httpclient = getHttpClient();
        CloseableHttpResponse response = null;
        String result = null;
        try {
            // 创建httpget.
            HttpGet httpget = new HttpGet(web_server_host  + "?gprs=" + gprs + "&devtype=" + devtype);
            System.out.println("executing request " + httpget.getURI());
            // 执行get请求.
            response = httpclient.execute(httpget);
            // 获取响应实体
            HttpEntity entity = response.getEntity();
            // 打印响应状态
            System.out.println(response.getStatusLine());
            if (entity != null) {
                result = EntityUtils.toString(entity, "UTF-8");
//                AppMsg appMsg = GsonUtil.fromJson(result, AppMsg.class);
//                if (appMsg.getResult() != null) {
//                    result = String.valueOf(appMsg.getResult().get("devno"));
//                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                if (response != null)
                    response.close();
                //httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    public static void main(String[] args) {
        for(;;){
            HttpClientUtils.fetchDevno("10000000000", "01");
        }
    }
}
