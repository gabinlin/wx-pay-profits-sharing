package top.gabin.tools.utils;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtilsTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String mchId = "1449025802"; // 商户号
    private String mchSerialNo = "SLHKqLh6buZEBQKKqLh6teSEBQKKqLh6"; // 商户证书序列号
    // 你的商户私钥
    private String privateKey = "-----BEGIN PRIVATE KEY-----\n" +
            "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCl6I4wof85BeXr\n" +
            "TMXNrSSMDH6chbfLLCrXD0C/tv/CWkYVa0i6nltykV6nfz/7NXoNmbAr2KC0s17H\n" +
            "cpOqVVPru+00hIWxs6uNP95l2BIxqaRb92uLLvoYOXXWnZPyAsDWin4ibQOMjbIx\n" +
            "uIiE+fiHR9sSxbNbBbOZKTwUaOJkPUfeAs43D7EZAl3xWQwOqfoj/mZTh9P3xxwD\n" +
            "p/kYTvEoDxqaRhi2WmnF4BReb/3rkEdIfcMKbEd3ExPHTbeX1Fl0sp6F4zE4/ZCG\n" +
            "Ed99d82F/k37SCZHcQgQSHSWzIeTwcNnbuE/CueaKnvgI3UW3UuvYGY73m9eV2A0\n" +
            "xjhgdqADAgMBAAECggEBAIbhKbgf+35AbUt+ftFXkf7JqaVWkLV8kteEbv9tp0A6\n" +
            "Y//F2LrfQzlBNdK2gS2ZrECiUbdTxlMiAEvlumcgN7nObmnj001E5JNQ+V2S7Dez\n" +
            "0wtxet0MtAY9sK9qLHz0ABJrRWB3gAskp1oEy/hPwN06bcA1Ojslx30dKNru6h7S\n" +
            "4/GpoS3w8tt1cWmTApsYVjR3eY7cAv9hyxj6vjv81gW93F0IrWhZsliRB3vSjBaE\n" +
            "7/wvbLQ4slUJ17wJfh1HGlvZI1CkYPUx9206cNI/KoMTNOt45fki0FAg1QIGU1qL\n" +
            "blxWlSO8XVQTJSNiU8WeuYxckzy3n0iMi5wpPf2fzAECgYEA1SDKvIxEA0YFQUY4\n" +
            "0ZwuVd61nN4M7pdxeJmy1+93M7PmBJwJ0ZxrZ6Kdzy/45Ey0X1036i652WObbRi3\n" +
            "TjrggfwrsfzK9rkLrGF4qjl7lkxIv0vpMShnqhovY5Ts9fbmnWk+Gxw+0FUiZskQ\n" +
            "6TFxDxsBUV3Qqem2tAkPa0p/UPMCgYEAx0glrolYDWY+s/eFL53dp0jBQzOR345I\n" +
            "rDP2P35af3qFyhAzT1J7Gn8v+SpXskEkI5Hx2tbnmOhH7RKs40Hd5SQFxluL66et\n" +
            "GkSlq+cKh8gYnP2QsJ056GcGSKcG+xVnovUYZZsaMVQsjfxv6uC4qAAL5xS86oJo\n" +
            "m/wanYr7uLECgYB6ReDqQVK3yhEzvLTWVNMkgqwQ/jfPHmWEOjGnvwPVTs2VMWxU\n" +
            "rHfWMi51cmFJoVQOLi3pFbucI5BFC9wGbrLlACaVa6GJ4On4kMcoaegkd0l5LnTv\n" +
            "te2bYoBzkjpMdsUh5AI0jYTgAyfEbnBcSPhDIUwlQTx4btRZ/6Sv+kKGzwKBgHFV\n" +
            "vth8k+9K7u47HyvthFnXLtKhSZzytrH4+1sw6RcG/3/jpsq+BfUT0JzMUAO4uGzi\n" +
            "W8Ix5pU3xXA25sx4cVIYIpClD/Z97hy6Xd5eD8cZLaZbLybCxGQ/83ruQzIZAk/T\n" +
            "RITEqSjUzmIR5zSViW9CV4KccLSOZiAQSLLAkFiBAoGBAKH7XLBco2+CTuBsRfVn\n" +
            "0BMmQufU4uFsLpmUdK6sRnrFth1wqMshwDTLEmE01EVbNTh1IeJDZ4TkTX6dhVfc\n" +
            "wGx5GTCaMKfJtIIU45fSFh0XgA6G+3ID8+G7rshIgpKPUKScgk42MaQZtrsYSVf+\n" +
            "7vEp1PIHFrLY5+9cjkoFufCS\n" +
            "-----END PRIVATE KEY-----";
    // 你的微信支付平台证书
    private String certificate =  "-----BEGIN CERTIFICATE-----\n" +
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

    @Before
    public void init() {
        httpUtils = new HttpUtils(mchId, mchSerialNo, privateKey, certificate);
    }

    @Test
    public void test1() {

    }
}
