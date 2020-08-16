package top.gabin.tools.auth;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wechat.pay.contrib.apache.httpclient.Credentials;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.CertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.util.AesUtil;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 在原有CertificatesVerifier基础上，增加自动更新证书功能
 */
@Slf4j
public class AutoUpdateInCloudCertificatesVerifier implements Verifier {

    private boolean cloud;

    private final String UPDATE_TIME_CACHE_KEY = "UPDATE_TIME_CACHE_KEY";
    private final String NEW_CREDENTIALS_CACHE_KEY = "NEW_CREDENTIALS_CACHE_KEY";

    private CacheService cacheService;

    //证书下载地址
    private static final String CertDownloadPath = "https://api.mch.weixin.qq.com/v3/certificates";

    //上次更新时间
    private volatile Instant instant;

    //证书更新间隔时间，单位为分钟
    private final int minutesInterval;

    private Verifier verifier;

    private final Credentials credentials;

    private final byte[] apiV3Key;

    private final ReentrantLock lock = new ReentrantLock();

    private List<X509Certificate> certificateList;

    public List<X509Certificate> getLastCertificateList() {
        List<X509Certificate> certificateList = getCertificateList();
        if (certificateList.isEmpty()) {
            //构造时更新证书
            try {
                autoUpdateCert();
                setInstant(Instant.now());
            } catch (IOException | GeneralSecurityException e) {
                throw new RuntimeException(e);
            }
        }
        return getCertificateList();
    }

    private List<X509Certificate> getCertificateList() {
        if (cloud) return cacheService.getList(NEW_CREDENTIALS_CACHE_KEY);
        else {
            return certificateList;
        }
    }

    //时间间隔枚举，支持一小时、六小时以及十二小时
    public enum TimeInterval {
        OneHour(60),
//        SixHours(60 * 6),
//        TwelveHours(60 * 12)
        ;

        private final int minutes;

        TimeInterval(int minutes) {
            this.minutes = minutes;
        }

        public int getMinutes() {
            return minutes;
        }
    }

    public AutoUpdateInCloudCertificatesVerifier(Credentials credentials, byte[] apiV3Key, CacheService cacheService) {
        this(credentials, apiV3Key, TimeInterval.OneHour.getMinutes(), cacheService);
    }

    public AutoUpdateInCloudCertificatesVerifier(Credentials credentials, byte[] apiV3Key, int minutesInterval, CacheService cacheService) {
        this.credentials = credentials;
        this.apiV3Key = apiV3Key;
        this.minutesInterval = minutesInterval;
        if (cacheService != null) {
            setCacheService(cacheService);
        }
        //构造时更新证书
        try {
            autoUpdateCert();
            setInstant(Instant.now());
        } catch (IOException | GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    public Instant getInstant() {
        if (!cloud) {
            return instant;
        }
        return cacheService.get(UPDATE_TIME_CACHE_KEY, Instant.class);
    }

    public void setInstant(Instant instant) {
        if (cloud) {
            cacheService.cache(UPDATE_TIME_CACHE_KEY, instant);
        } else {
            this.instant = instant;
        }
    }

    public void setCacheService(CacheService cacheService) {
        this.cacheService = cacheService;
        cloud = true;
        verifier = new CloudCertificatesVerifier(cacheService);
    }

    @Override
    public boolean verify(String serialNumber, byte[] message, String signature) {
        if (invalidInstant()) {
            if (lock.tryLock()) {
                try {
                    autoUpdateCert();
                    //更新时间
                    setInstant(Instant.now());
                } catch (GeneralSecurityException | IOException e) {
                    log.warn("Auto update cert failed, exception = " + e);
                } finally {
                    lock.unlock();
                }
            }
        }
        return verifier.verify(serialNumber, message, signature);
    }

    private boolean invalidInstant() {
        Instant instant = getInstant();
        return instant == null || Duration.between(instant, Instant.now()).toMinutes() >= minutesInterval;
    }

    private void autoUpdateCert() throws IOException, GeneralSecurityException {
        CloseableHttpClient httpClient = WechatPayHttpClientBuilder.create()
                .withCredentials(credentials)
                // 先只校验更新时间，更新时间不符合条件的时候，当做首次下载证书
                .withValidator(invalidInstant() ? (response) -> true : new WechatPay2Validator(verifier))
                .build();

        HttpGet httpGet = new HttpGet(CertDownloadPath);
        httpGet.addHeader("Accept", "application/json");

        CloseableHttpResponse response = httpClient.execute(httpGet);
        int statusCode = response.getStatusLine().getStatusCode();
        String body = EntityUtils.toString(response.getEntity());
        if (statusCode == 200) {
            List<X509Certificate> newCertList = deserializeToCerts(apiV3Key, body);
            if (newCertList.isEmpty()) {
                log.warn("Cert list is empty");
                return;
            }
            if (cloud) {
                cacheService.cache(NEW_CREDENTIALS_CACHE_KEY, newCertList);
                Map<BigInteger, X509Certificate> certificateMap = new HashMap<>();
                for (X509Certificate x509Certificate : newCertList) {
                    certificateMap.put(x509Certificate.getSerialNumber(), x509Certificate);
                }
                cacheService.cache(CloudCertificatesVerifier.CACHE_KEY, certificateMap);
            } else {
                certificateList = newCertList;
                this.verifier = new CertificatesVerifier(newCertList);
            }
        } else {
            log.warn("Auto update cert failed, statusCode = " + statusCode + ",body = " + body);
        }
    }


    /**
     * 反序列化证书并解密
     */
    private List<X509Certificate> deserializeToCerts(byte[] apiV3Key, String body)
            throws GeneralSecurityException, IOException {
        AesUtil decryptor = new AesUtil(apiV3Key);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataNode = mapper.readTree(body).get("data");
        List<X509Certificate> newCertList = new ArrayList<>();
        if (dataNode != null) {
            for (int i = 0, count = dataNode.size(); i < count; i++) {
                JsonNode encryptCertificateNode = dataNode.get(i).get("encrypt_certificate");
                //解密
                String cert = decryptor.decryptToString(
                        encryptCertificateNode.get("associated_data").toString().replaceAll("\"", "")
                                .getBytes(StandardCharsets.UTF_8),
                        encryptCertificateNode.get("nonce").toString().replaceAll("\"", "")
                                .getBytes(StandardCharsets.UTF_8),
                        encryptCertificateNode.get("ciphertext").toString().replaceAll("\"", ""));

                X509Certificate x509Cert = PemUtil
                        .loadCertificate(new ByteArrayInputStream(cert.getBytes(StandardCharsets.UTF_8)));
                try {
                    x509Cert.checkValidity();
                } catch (CertificateExpiredException | CertificateNotYetValidException e) {
                    continue;
                }
                newCertList.add(x509Cert);
            }
        }
        return newCertList;
    }
}