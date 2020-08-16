package top.gabin.tools.config;

import lombok.Data;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;

@Data
public class ProfitsSharingConfig {
    private String apiKey;
    private String mchId;                   // 商户号
    private String mchSerialNo;             // 商户证书序列号
    private PrivateKey privateKey;          // 你的商户私钥
    private X509Certificate certificate;    // 商户证书，在这里几乎没用，证书基本上都是用下载的
}
