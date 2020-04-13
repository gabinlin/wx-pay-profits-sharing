package top.gabin.tools.utils;

import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import top.gabin.tools.request.ecommerce.AbstractRequest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

public class HttpUtils {

    private String mchId;       // 商户号
    private String mchSerialNo; // 商户证书序列号
    private String privateKey;  // 你的商户私钥
    private String certificate; // 你的微信支付平台证书
    private CloseableHttpClient httpClient;

    public HttpUtils(String mchId, String mchSerialNo, String privateKey, String certificate) {
        this.mchId = mchId;
        this.mchSerialNo = mchSerialNo;
        this.privateKey = privateKey;
        this.certificate = certificate;
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException  {
        PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(
                new ByteArrayInputStream(privateKey.getBytes("utf-8")));
//        X509Certificate wechatpayCertificate = PemUtil.loadCertificate(
//                new ByteArrayInputStream(certificate.getBytes("utf-8")));

        ArrayList<X509Certificate> listCertificates = new ArrayList<>();
//        listCertificates.add(wechatpayCertificate);

        httpClient = WechatPayHttpClientBuilder.create()
                .withMerchant(mchId, mchSerialNo, merchantPrivateKey)
                .withWechatpay(listCertificates)
                .build();
    }

    private <T> T request(Class<T> responseClass, HttpUriRequest request) {
        request.addHeader("Content-Type", "application/json");
        request.addHeader("Accept", "application/json");
        try {
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            String responseText = EntityUtils.toString(entity, "utf-8");
            EntityUtils.consume(entity);
            if (responseClass.isAssignableFrom(String.class)) {
                return (T) responseText;
            }
            System.out.println(responseText);
            return JsonUtils.json2Bean(responseClass, responseText);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T get(Class<T> responseClass, String url) {
        return request(responseClass, new HttpGet(url));
    }

    public <T> T post(Class<T> responseClass, Object requestObj, String url) {
        HttpPost httpPost = new HttpPost(url);
        String jsonData = "";
        //增加一个判断是否有Declared方法，要不JsonUtils.bean2Json(requestBody)会报异常
        int methodsNum = requestObj.getClass().getDeclaredMethods().length;
        if (requestObj instanceof String) {
            jsonData = requestObj.toString();
        } else if (methodsNum > 0) {
            jsonData = JsonUtils.bean2Json(requestObj);
        }
        if (jsonData != null && !jsonData.equals("{}")) {
            StringEntity reqEntity = new StringEntity(
                    jsonData, ContentType.create("application/json", "utf-8"));
            httpPost.setEntity(reqEntity);
        }
        if (jsonData != null && !jsonData.equals("{}")) {
            httpPost.setEntity(new StringEntity(jsonData, "utf-8"));
        }
        return request(responseClass, httpPost);
    }

    public <T> T post(Class<T> responseClass, AbstractRequest requestObj) {
        return post(responseClass, requestObj, requestObj.getUrl());
    }
}
