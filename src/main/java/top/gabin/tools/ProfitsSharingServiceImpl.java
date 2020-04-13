package top.gabin.tools;

import top.gabin.tools.request.pay.combine.CombineTransactionsJsRequest;
import top.gabin.tools.response.pay.combine.CombineTransactionsJsResponse;
import top.gabin.tools.utils.HttpUtils;
import top.gabin.tools.utils.RSASignUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProfitsSharingServiceImpl implements ProfitsSharingService {

    private String mchId = "1449025802"; // 商户号
    private String mchSerialNo = "SLHKqLh6buZEBQKKqLh6teSEBQKKqLh6"; // 商户证书序列号
    // 你的商户私钥
    private String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDS9se1wbq51SXw49bZgzIdj/ShiV618b/vNDztFhK/iW1nD1zMxo72YD9yHRX8jVWnrByDwL18107HK2nYbHu8t3Bcxd8k3/tpZgosbDOoXExDuJG02cwSx4jbCK6x5WWvucGO+tjoMaB1pPEurK6R8AVykcEzB6eo0bGSjx6ZUDh3pUESMXTKkXoQO/fJofq1BibWUq5KpAdeNL+yjFUUZg05PH2K7XRFp8nwe9tDQ3HrvUx1HhyNP/IFxU/XSR/D7uZsYuYOehiBX76mQzYD8PocnS0ehjpcyho7R/jmGuD4O6BGdWxCthrNrrRd6g030/TvfRictiuEJAMSQqf/AgMBAAECggEAHa61OMCSSjVQSk10XFRWR8yKafQPDGCAVeKus9kIOETYzMhfkTxavxWZt6+Z+VfVdmsD9BG5V4hfwCw+j0HsQwg4WgVJOUH+eLzvr4Jl3klmPZ0Jez2ttfK3McJN+h/Bp/Dl5/0paboZzpOvj5aiVUxFJ/KUEV8BWwJuDqXuczmRsG/JwXCDnLsrIEMmyBXDgvGSEiu/L6mjfIMNpwBPGkTiiJGRlBSWIAWdQr/jNw/po0zb+jlCVGWoPcivWGAafXJQX66aAk4JMiNCuLjdkH+xen/xSWU/QEQ9nWwLRJh6l9shvPWY3bfqQKPYkDGyyLdIUzxpIQBpkn+SZp5TSQKBgQD7KOtBC0bcVPpOVu333BC/pxMzqA2HPMyflQuUr7Di6HXZulxb2qEuXfafRMgVo7puLu/TDozZa83L7W1nEf5nbjClVEgVMbhT1uNgkIRuJ8CJu50wnDWUBsmMB4+Dn6kUG7YD/Psp4M1xzhWQSXvyxFXzAf6+DzrISK0FymJeVQKBgQDXB462cxKLbot8la1rQIjw2lX6p/WMBASuqOnPFlkDjIZuQbYfXchWxJRXVLCUikPzBLjWvJHbjrhmBTeIyxTyib+JThqaMzyMf0Y77/GiUJdUBx7PFHFyhDaj+jSmOxi7ceSZF37RNn539D2S6E7Qusj3OYPlaQrSV1WmLxBZAwKBgQDh9lSBdnXQMRvpc0gxoOnoo5Yg+WcCbu7h/CQpJ1ALNX0h4ArMEQzGPH9vl2A0J9PI4a2eww5xZg4HFJtDCetKftaBSCx59PuTYle7PwoGWPlecU7gtwl1Hg4iT4MMto5VqwC84dPOP5RWeUTpRVOgfIefVAIuWGFYZBpWhViu6QKBgQDLU3gdCX6VnbgD3DyZV/KlXK9ETyGefgY3ab18djNBadWL2FLwIevYMBXc5lX6fyt1Vhe55aE+LRwsS+6RSQbLuHkGynXZLW2ppIezEVY5F1+gswLs6PXFRUOtll/Gd8cRJ8bzBAaEqbS4lJjMmyI7uQNi0l3nxYXYE4EHnSUmJQKBgHqcrvr0P2fl01Yma4CxU/CVppAWpN2xPFrIaOgIhMRwAIB/VJxMIcA35hjf5IDMFeevGFtqUCMHtxvFeDWsQlFw4WVR3aaATnQypNC3lukH4kgko7v6IsVj0NoaVOJd2kpNmivlcQGNGX1fg+wfkuF2vksKWfsJVZjM8GUoqMiu";
    // 你的微信支付平台证书
    private String certificate = "-----BEGIN CERTIFICATE-----\n" +
            "MIID9jCCAt6gAwIBAgIULl0WVna8Zk1TKJIqIdJAnSZNk/owDQYJKoZIhvcNAQEL\n" +
            "BQAwXjELMAkGA1UEBhMCQ04xEzARBgNVBAoTClRlbnBheS5jb20xHTAbBgNVBAsT\n" +
            "FFRlbnBheS5jb20gQ0EgQ2VudGVyMRswGQYDVQQDExJUZW5wYXkuY29tIFJvb3Qg\n" +
            "Q0EwHhcNMTkwNDEwMTAwNDIyWhcNMjAwNDA5MTAwNDIyWjCBhzETMBEGA1UEAwwK\n" +
            "MTQ0OTAyNTgwMjEbMBkGA1UECgwS5b6u5L+h5ZWG5oi357O757ufMTMwMQYDVQQL\n" +
            "DCrljqbpl6jluILllYbml4XojZ/nvZHnu5znp5HmioDmnInpmZDlhazlj7gxCzAJ\n" +
            "BgNVBAYMAkNOMREwDwYDVQQHDAhTaGVuWmhlbjCCASIwDQYJKoZIhvcNAQEBBQAD\n" +
            "ggEPADCCAQoCggEBAKXojjCh/zkF5etMxc2tJIwMfpyFt8ssKtcPQL+2/8JaRhVr\n" +
            "SLqeW3KRXqd/P/s1eg2ZsCvYoLSzXsdyk6pVU+u77TSEhbGzq40/3mXYEjGppFv3\n" +
            "a4su+hg5ddadk/ICwNaKfiJtA4yNsjG4iIT5+IdH2xLFs1sFs5kpPBRo4mQ9R94C\n" +
            "zjcPsRkCXfFZDA6p+iP+ZlOH0/fHHAOn+RhO8SgPGppGGLZaacXgFF5v/euQR0h9\n" +
            "wwpsR3cTE8dNt5fUWXSynoXjMTj9kIYR3313zYX+TftIJkdxCBBIdJbMh5PBw2du\n" +
            "4T8K55oqe+AjdRbdS69gZjveb15XYDTGOGB2oAMCAwEAAaOBgTB/MAkGA1UdEwQC\n" +
            "MAAwCwYDVR0PBAQDAgTwMGUGA1UdHwReMFwwWqBYoFaGVGh0dHA6Ly9ldmNhLml0\n" +
            "cnVzLmNvbS5jbi9wdWJsaWMvaXRydXNjcmw/Q0E9MUJENDIyMEU1MERCQzA0QjA2\n" +
            "QUQzOTc1NDk4NDZDMDFDM0U4RUJEMjANBgkqhkiG9w0BAQsFAAOCAQEAUZh79ZRV\n" +
            "YZnAKwyH9STDZoBtDaqGI/ji4I2b03koUxdMjFj9E6Ufvs144Sn10PogwjSM07sb\n" +
            "eXLanzEWWE2V1ZjKsyNJe6kK9v4E8tKuNmMTyJpNgVHQNXLQh4aZry6pWnCNDEjz\n" +
            "tzv/zrCsqdVpAOjSI8dH9iFr1Tuvo0GBkrUKj8B1HmfC0QLbAZ/OyS51RveytTIr\n" +
            "qLwC9uPGrQhKWauHZgFTKx/4ee1J8/pkmKoIM3xleFYuBgr+Us4JzUR5nwvEwhPK\n" +
            "/Ob90Tl1jy+/KxZ2NEYfLw7Kp+3tfstF1ZeurFYR6dUxdGl7fbP9gsdM7aM4t93e\n" +
            "u3qYEAnjcWVdVA==\n" +
            "-----END CERTIFICATE-----\n";
    private HttpUtils httpUtils;

    public ProfitsSharingServiceImpl() {
        httpUtils = new HttpUtils(mchId, mchSerialNo, privateKey, certificate);

    }

    @Override
    public String combineTransactions(CombineTransactionsJsRequest request) {
        Optional<CombineTransactionsJsResponse> responseOptional = getResponse(CombineTransactionsJsResponse.class, request,
                "https://api.mch.weixin.qq.com/v3/combine-transactions/jsapi");
        return responseOptional.map(CombineTransactionsJsResponse::getPrepayId).orElse(null);
    }


    @Override
    public Map<String, String> getStringStringMap(String prePayId, String appId) {
        Map<String, String> params = new HashMap<>();
        String signType = "RSA";
        params.put("appId", appId);
        long timeStamp = System.currentTimeMillis();
        String nonceStr = timeStamp + "_gabin";
//        params.put("timeStamp", timeStamp + "");
        params.put("timeStamp", "1414561699");
//        params.put("nonceStr", nonceStr);
        params.put("nonceStr", "5K8264ILTKCH16CQ2502SI8ZNMTM67VS");
        params.put("signType", signType);
        String packageStr = "prepay_id=" + prePayId;
        params.put("package", packageStr);

        String singSource = String.format("%s\n%s\n%s\n%s",
                appId, timeStamp, nonceStr, "prepay_id=" + prePayId);
        String sign = RSASignUtil.sign(privateKey, singSource);
        params.put("paySign", sign);
        return params;
    }

    private <T> Optional<T> getResponse(Class<T> classZ, CombineTransactionsJsRequest request, String url) {
        return Optional.ofNullable(httpUtils.post(classZ, request, url));
    }
}
