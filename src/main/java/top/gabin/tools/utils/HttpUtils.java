package top.gabin.tools.utils;

import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.gabin.tools.auth.AutoUpdateInCloudCertificatesVerifier;
import top.gabin.tools.auth.CacheService;

import java.io.IOException;
import java.io.InputStream;
import java.security.PrivateKey;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.stream.Collectors;

public class HttpUtils {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String mchId;           // 商户号
    private String mchSerialNo;     // 商户证书序列号
    private PrivateKey privateKey;  // 你的商户私钥
    private String apiKey;
    private CloseableHttpClient httpClient;
    private volatile AutoUpdateInCloudCertificatesVerifier verifier;

    public HttpUtils(String mchId, String mchSerialNo, PrivateKey privateKey, String apiKey, CacheService cacheService) {
        this.mchId = mchId;
        this.mchSerialNo = mchSerialNo;
        this.privateKey = privateKey;
        this.apiKey = apiKey;
        try {
            init(cacheService);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AutoUpdateInCloudCertificatesVerifier getVerifier() {
        return verifier;
    }

    private void init(CacheService cacheService) throws IOException {
        // 不需要传入微信支付证书，将会自动更新
        verifier = new AutoUpdateInCloudCertificatesVerifier(
                new WechatPay2Credentials(mchId, new PrivateKeySigner(mchSerialNo, privateKey)),
                apiKey.getBytes("utf-8"), cacheService);
        WechatPayHttpClientBuilder builder = WechatPayHttpClientBuilder.create()
                .withMerchant(mchId, mchSerialNo, privateKey)
                .withValidator(new WechatPay2Validator(verifier));
        httpClient = builder.build();
    }

    public List<X509Certificate> getLastCertificateList() {
        List<X509Certificate> certificateList = verifier.getLastCertificateList().stream().filter(certificate -> {
            try {
                certificate.checkValidity();
                return true;
            } catch (CertificateExpiredException | CertificateNotYetValidException e) {
                e.printStackTrace();
                return false;
            }
        }).collect(Collectors.toList());
        logger.info("可用证书数量："  + certificateList.size());
        return certificateList;
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

    public <T> T post(Class<T> responseClass, Object requestObj, String url, X509Certificate certificate) {
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
        httpPost.addHeader("Wechatpay-Serial", certificate.getSerialNumber().toString(16).toUpperCase());
        return request(responseClass, httpPost);
    }

    public InputStream download(String downloadUrl) {
        HttpGet httpGet = new HttpGet(downloadUrl);
        httpGet.addHeader("Accept", "application/json");

        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            int statusCode = response.getStatusLine().getStatusCode();
            String body = EntityUtils.toString(response.getEntity());
            if (statusCode == 200) {
                return response.getEntity().getContent();
            } else {
                throw new IOException("request failed");
            }
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
