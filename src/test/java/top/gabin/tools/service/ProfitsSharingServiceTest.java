package top.gabin.tools.service;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.gabin.tools.config.ProfitsSharingConfig;
import top.gabin.tools.request.ecommerce.applyments.ApplymentsRequest;
import top.gabin.tools.response.tool.ImageUploadResponse;
import top.gabin.tools.utils.JsonUtils;

import java.io.*;
import java.security.cert.*;
import java.util.Date;
import java.util.Optional;

public class ProfitsSharingServiceTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    ProfitsSharingService profitsSharingService;

    @Before
    public void before() throws IOException {
        ProfitsSharingConfig config = new ProfitsSharingConfig();
        config.setMchId(FileUtils.readFileToString(new File("/Users/linjiabin/cert/wxPayForEco/mchId.txt"), "UTF-8").trim());
        String apiKey = FileUtils.readFileToString(new File("/Users/linjiabin/cert/wxPayForEco/api3Key.txt"), "UTF-8");
        config.setApiKey(apiKey.trim());
        X509Certificate certificate = getCertificate("/Users/linjiabin/cert/wxPayForEco/apiclient_cert.pem");
        String serialNo = certificate.getSerialNumber().toString(16).toUpperCase();
        config.setMchSerialNo(serialNo);
        String privateKeyStr = FileUtils.readFileToString(new File("/Users/linjiabin/cert/wxPayForEco/apiclient_key.pem"), "UTF-8");
        config.setPrivateKey(privateKeyStr);
        profitsSharingService = new ProfitsSharingServiceImpl(config, null);
    }

    /**
     * 获取证书。
     *
     * @param filename 证书文件路径  (required)
     * @return X509证书
     */
    public static X509Certificate getCertificate(String filename) throws IOException {
        InputStream fis = new FileInputStream(filename);
        BufferedInputStream bis = new BufferedInputStream(fis);

        try {
            CertificateFactory cf = CertificateFactory.getInstance("X509");
            X509Certificate cert = (X509Certificate) cf.generateCertificate(bis);
            cert.checkValidity();
            return cert;
        } catch (CertificateExpiredException e) {
            throw new RuntimeException("证书已过期", e);
        } catch (CertificateNotYetValidException e) {
            throw new RuntimeException("证书尚未生效", e);
        } catch (CertificateException e) {
            throw new RuntimeException("无效的证书文件", e);
        } finally {
            bis.close();
        }
    }

    @Test
    public void testApplyments() {
        ApplymentsRequest request = new ApplymentsRequest();
        request.setOutRequestNo(DateFormatUtils.format(new Date(), "yyyyMMddhhmmss"));
        request.setOrganizationType("2401");
        request.setIdDocType("IDENTIFICATION_TYPE_MAINLAND_IDCARD");
        request.setIdCardInfo(new ApplymentsRequest.IdCardInfo());
        profitsSharingService.applyments(request);
    }

    @Test
    public void testUploadImage() throws Exception {
        Optional<ImageUploadResponse> imageUploadResponse = profitsSharingService.uploadImage(new File("/Users/linjiabin/Downloads/IMG_1116.jpeg"));
        imageUploadResponse.ifPresent(imageUploadResponse1 -> logger.info(JsonUtils.bean2Json(imageUploadResponse1)));
    }
}
