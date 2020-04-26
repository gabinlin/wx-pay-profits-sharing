package top.gabin.tools.config;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;

public class ProfitsSharingConfig {
    private String apiKey;
    private String mchId;                   // 商户号
    private String mchSerialNo;             // 商户证书序列号
    private PrivateKey privateKey;          // 你的商户私钥
    private X509Certificate certificate;    // 商户证书，在这里几乎没用，证书基本上都是用下载的

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getMchSerialNo() {
        return mchSerialNo;
    }

    public void setMchSerialNo(String mchSerialNo) {
        this.mchSerialNo = mchSerialNo;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public X509Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(X509Certificate certificate) {
        this.certificate = certificate;
    }
}
