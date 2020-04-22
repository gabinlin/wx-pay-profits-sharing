package top.gabin.tools.service;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.gabin.tools.config.ProfitsSharingConfig;
import top.gabin.tools.constant.AccountType;
import top.gabin.tools.request.ecommerce.applyments.*;
import top.gabin.tools.request.ecommerce.fund.WithdrawForSubMchRequest;
import top.gabin.tools.request.ecommerce.fund.WithdrawStatusForSubMchRequest;
import top.gabin.tools.request.ecommerce.profitsharing.ProfitSharingApplyRequest;
import top.gabin.tools.request.ecommerce.refunds.RefundApplyRequest;
import top.gabin.tools.response.ecommerce.amount.AmountOnlineOfSubMchResponse;
import top.gabin.tools.response.ecommerce.applyments.ApplymentsResponse;
import top.gabin.tools.response.ecommerce.applyments.ApplymentsStatusResponse;
import top.gabin.tools.response.ecommerce.fund.WithdrawForSubMchResponse;
import top.gabin.tools.response.ecommerce.fund.WithdrawStatusForSubMchResponse;
import top.gabin.tools.response.pay.combine.CombineTransactionsStatusResponse;
import top.gabin.tools.response.tool.ImageUploadResponse;
import top.gabin.tools.utils.JsonUtils;

import java.io.*;
import java.security.cert.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ProfitsSharingServiceTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    ProfitsSharingService profitsSharingService;
    X509Certificate certificate;
    String apiKey;


    @Before
    public void before() throws IOException {
        ProfitsSharingConfig config = new ProfitsSharingConfig();
        config.setMchId(FileUtils.readFileToString(new File("/Users/linjiabin/cert/wxPayForEco/mchId.txt"), "UTF-8").trim());
        apiKey = FileUtils.readFileToString(new File("/Users/linjiabin/cert/wxPayForEco/api3Key.txt"), "UTF-8").trim();
        config.setApiKey(apiKey);
        certificate = getCertificate("/Users/linjiabin/cert/wxPayForEco/apiclient_cert.pem");
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
    private static X509Certificate getCertificate(String filename) throws IOException {
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

    //    @Test
    public void testApplyments() {

    }

    @Test
    public void testQueryApplyments() {
        ApplymentsStatusRequest request = new ApplymentsStatusRequest();
        request.setApplymentId("2000002140061723");
        Optional<ApplymentsStatusResponse> applymentsStatusResponse = profitsSharingService.queryApplymentsStatus(request);
        applymentsStatusResponse.ifPresent(response -> logger.info(JsonUtils.bean2Json(response)));
    }

    @Test
    public void testQueryApplyments1() {
        ApplymentsStatusRequest1 request = new ApplymentsStatusRequest1();
        request.setOutRequestNo("20200420054724");
        Optional<ApplymentsStatusResponse> applymentsStatusResponse = profitsSharingService.queryApplymentsStatus(request);
        applymentsStatusResponse.ifPresent(response -> logger.info(JsonUtils.bean2Json(response)));
    }

    @Test
    public void testDownCert() {
        List<X509Certificate> certificateList = profitsSharingService.downloadCertificates();
        logger.info("证书数量：" + certificateList.size());
    }

    @Test
    public void testModifySettlement() {
        ApplymentsModifySettlementRequest request = new ApplymentsModifySettlementRequest();
        request.setAccountNumber("");
        request.setAccountBank("");
        request.setAccountType("");
        request.setBankName("");
        request.setBankBranchId("");
        request.setBankAddressCode("");
        request.setSubMchid("");
        profitsSharingService.modifySettlement(request).ifPresent(this::logger);
    }

    @Test
    public void testQuerySettlement() {
        ApplymentsSettlementStatusRequest request = new ApplymentsSettlementStatusRequest();
        request.setSubMchid("1587487911");
        profitsSharingService.querySettlement(request).ifPresent(this::logger);
    }

    private void logger(Object obj) {
        logger.info(JsonUtils.bean2Json(obj));
    }

    //    @Test
    public void testUploadImage() throws Exception {
        Optional<ImageUploadResponse> imageUploadResponse = profitsSharingService.uploadImage(new File("/Users/linjiabin/Downloads/IMG_1116.jpeg"));
        imageUploadResponse.ifPresent(response -> logger.info(JsonUtils.bean2Json(response)));
    }

    @Test
    public void testPay() {
//        CombineTransactionsJsRequest request = new CombineTransactionsJsRequest();
//        request.setSubOrders();
//        profitsSharingService.combineTransactions(request);
    }

    @Test
    public void testQueryPay() {
        Optional<CombineTransactionsStatusResponse> combineTransactionsStatusResponse =
                profitsSharingService.combineTransactionsStatus("C_JS_11042020042115183415642");
        combineTransactionsStatusResponse.ifPresent(response -> logger.info(JsonUtils.bean2Json(response)));
    }

    @Test
    public void testProfitsSharing() {
        ProfitSharingApplyRequest sharingApplyRequest = new ProfitSharingApplyRequest();
        sharingApplyRequest.setTransactionId("4349500102202004213293235146");
        List<ProfitSharingApplyRequest.Receivers> receiversList = new ArrayList<>();
        ProfitSharingApplyRequest.Receivers receivers = new ProfitSharingApplyRequest.Receivers();
        receivers.setAmount(1);
        receivers.setReceiverMchid("1586045221");
        receivers.setDescription("平台抽成");
        receiversList.add(receivers);
        sharingApplyRequest.setReceivers(receiversList);
        sharingApplyRequest.setSubMchid("1587487911");
        sharingApplyRequest.setOutOrderNo("232323232323223232332342");
        sharingApplyRequest.setFinish(true);
        profitsSharingService.applyProfitSharing(sharingApplyRequest);
    }

    @Test
    public void testQueryOnlineBalance() {
        Optional<AmountOnlineOfSubMchResponse> response = profitsSharingService.queryOnlineAmount("1587487911");
        response.ifPresent(amountOnlineOfSubMchResponse -> logger.info(JsonUtils.bean2Json(amountOnlineOfSubMchResponse)));
    }

    @Test
    public void testQueryOnlineBalanceForPlatform() {
        profitsSharingService.queryOnlineAmount(AccountType.BASIC).ifPresent(response -> logger.info(JsonUtils.bean2Json(response)));
        profitsSharingService.queryOnlineAmount(AccountType.FEES).ifPresent(response -> logger.info(JsonUtils.bean2Json(response)));
//        profitsSharingService.queryOnlineAmount(AccountType.OPERATION).ifPresent(response -> logger.info(JsonUtils.bean2Json(response)));
    }

    @Test
    public void testQueryDayEndBalance() {
        Optional<AmountOnlineOfSubMchResponse> response = profitsSharingService.queryOnlineAmount("1587487911");
        response.ifPresent(amountOnlineOfSubMchResponse -> logger.info(JsonUtils.bean2Json(amountOnlineOfSubMchResponse)));
    }

    @Test
    public void testQueryDayEndBalanceForPlatform() {
        profitsSharingService.queryDayEndAmount(AccountType.BASIC, new Date()).ifPresent(response -> logger.info(JsonUtils.bean2Json(response)));
    }

//    @Test
    public void testWithdraw() {
        WithdrawForSubMchRequest request = new WithdrawForSubMchRequest();
        request.setAmount(1);
        request.setOutRequestNo("20200421163501");
        request.setBankMemo("我要提现");
        request.setSubMchid("1587487911");
        request.setRemark("我要提现");
        Optional<WithdrawForSubMchResponse> withdraw = profitsSharingService.withdraw(request);
        withdraw.ifPresent(response -> logger.info(JsonUtils.bean2Json(response)));
    }

    @Test
    public void testQueryWithdraw() {
        WithdrawStatusForSubMchRequest request = new WithdrawStatusForSubMchRequest();
        request.setSubMchid("1587487911");
        request.setWithdrawId("209000120133995202004211781163170");
        Optional<WithdrawStatusForSubMchResponse> responseOptional = profitsSharingService.queryWithdrawStatus(request);
        responseOptional.ifPresent(response -> logger.info(JsonUtils.bean2Json(response)));
    }

    @Test
    public void testRefund() {
        RefundApplyRequest request = new RefundApplyRequest();
        request.setTransactionId("4303500104202004210206956407");
//        request.setOutTradeNo("");
        request.setSubMchid("1587487911");
        request.setSpAppid("wxb2bb0b1a0dcd2eed");
        request.setReason("退款");
        request.setNotifyUrl("https://cab.gabin.top/refund/wx/eco");
        RefundApplyRequest.Amount amount = new RefundApplyRequest.Amount();
        amount.setCurrency("CNY");
        amount.setTotal(2);
        amount.setRefund(2);
        request.setAmount(amount);
        request.setOutRefundNo(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
        profitsSharingService.refundApply(request).ifPresent(refundApplyResponse -> logger.info(JsonUtils.bean2Json(refundApplyResponse)));
    }


}
