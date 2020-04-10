package top.gabin.tools.utils;

import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.AutoUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

public class HttpUtils {

    /**
     * 获取私钥。
     *
     * @param filename 私钥文件路径  (required)
     * @return 私钥对象
     */
    private static PrivateKey getPrivateKey(String filename) throws IOException {

        String content = new String(Files.readAllBytes(Paths.get(filename)), "utf-8");
        try {
            String privateKey = content.replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s+", "");
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(
                    new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("当前Java环境不支持RSA", e);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            throw new RuntimeException("无效的密钥格式");
        }
    }

    private static HttpClient getClient() throws IOException {
        //不需要传入微信支付证书，将会自动更新
        String merchantId = "1328384201";
        String merchantSerialNumber = "SLHKqLh6buZEBQKKqLh6teSEBQKKqLh6";
        PrivateKey merchantPrivateKey = getPrivateKey("/Users/linjiabin/cert/wxPay3/cert.txt");
        String apiV3Key = "apiV3Key";
        PrivateKeySigner keySigner = new PrivateKeySigner(merchantSerialNumber, merchantPrivateKey);
        WechatPay2Credentials pay2Credentials = new WechatPay2Credentials(merchantId, keySigner);
        AutoUpdateCertificatesVerifier verifier =
                new AutoUpdateCertificatesVerifier(pay2Credentials, apiV3Key.getBytes("utf-8"));
        WechatPayHttpClientBuilder builder = WechatPayHttpClientBuilder.create()
                .withMerchant(merchantId, merchantSerialNumber, merchantPrivateKey)
                .withValidator(new WechatPay2Validator(verifier));
        HttpClient httpClient = builder.build();
        return httpClient;
    }

    private static <T> T request(Class<T> responseClass, HttpUriRequest request) {
        request.addHeader("Content-Type", "application/json");
        request.addHeader("Accept", "application/json");
        try {
            HttpResponse response = getClient().execute(request);
            HttpEntity entity = response.getEntity();
            String responseText = EntityUtils.toString(entity, "utf-8");
            EntityUtils.consume(entity);
            if (responseClass.isAssignableFrom(String.class)) {
                return (T) responseText;
            }
            return JsonUtils.json2Bean(responseClass, responseText);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T get(Class<T> responseClass, String url) {
        return request(responseClass, new HttpGet(url));
    }

    public static <T> T post(Class<T> responseClass, Object requestObj, String url) {
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
            httpPost.setEntity(new StringEntity(jsonData, "utf-8"));
        }
        if (jsonData != null && !jsonData.equals("{}")) {
            httpPost.setEntity(new StringEntity(jsonData, "utf-8"));
        }
        return request(responseClass, httpPost);
    }
}
