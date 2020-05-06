package top.gabin.tools.service;

import com.wechat.pay.contrib.apache.httpclient.util.AesUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.gabin.tools.auth.CacheService;
import top.gabin.tools.config.ProfitsSharingConfig;
import top.gabin.tools.constant.AccountType;
import top.gabin.tools.request.ecommerce.applyments.ApplymentsModifySettlementRequest;
import top.gabin.tools.request.ecommerce.applyments.ApplymentsRequest;
import top.gabin.tools.request.ecommerce.applyments.ApplymentsSettlementStatusRequest;
import top.gabin.tools.request.ecommerce.fund.*;
import top.gabin.tools.request.ecommerce.profitsharing.*;
import top.gabin.tools.request.ecommerce.refunds.RefundApplyRequest;
import top.gabin.tools.request.ecommerce.refunds.RefundNotifyRequest;
import top.gabin.tools.request.ecommerce.refunds.RefundNotifyRequest1;
import top.gabin.tools.request.ecommerce.subsidies.SubsidiesCancelRequest;
import top.gabin.tools.request.ecommerce.subsidies.SubsidiesCreateRequest;
import top.gabin.tools.request.ecommerce.subsidies.SubsidiesRefundRequest;
import top.gabin.tools.request.pay.bill.BillOfFundFlowRequest;
import top.gabin.tools.request.pay.bill.BillOfTradeRequest;
import top.gabin.tools.request.pay.combine.*;
import top.gabin.tools.response.AbstractResponse;
import top.gabin.tools.response.ecommerce.amount.AmountDayEndOfPlatformResponse;
import top.gabin.tools.response.ecommerce.amount.AmountDayEndOfSubMchResponse;
import top.gabin.tools.response.ecommerce.amount.AmountOnlineOfPlatformResponse;
import top.gabin.tools.response.ecommerce.amount.AmountOnlineOfSubMchResponse;
import top.gabin.tools.response.ecommerce.applyments.ApplymentsModifySettlementResponse;
import top.gabin.tools.response.ecommerce.applyments.ApplymentsResponse;
import top.gabin.tools.response.ecommerce.applyments.ApplymentsSettlementStatusResponse;
import top.gabin.tools.response.ecommerce.applyments.ApplymentsStatusResponse;
import top.gabin.tools.response.ecommerce.fund.*;
import top.gabin.tools.response.ecommerce.profitsharing.*;
import top.gabin.tools.response.ecommerce.refunds.RefundApplyResponse;
import top.gabin.tools.response.ecommerce.refunds.RefundQueryStatusResponse;
import top.gabin.tools.response.ecommerce.subsidies.SubsidiesCancelResponse;
import top.gabin.tools.response.ecommerce.subsidies.SubsidiesCreateResponse;
import top.gabin.tools.response.ecommerce.subsidies.SubsidiesRefundResponse;
import top.gabin.tools.response.pay.bill.BillOfFundFlowResponse;
import top.gabin.tools.response.pay.bill.BillOfTradeResponse;
import top.gabin.tools.response.pay.combine.CombineTransactionsAppResponse;
import top.gabin.tools.response.pay.combine.CombineTransactionsCloseResponse;
import top.gabin.tools.response.pay.combine.CombineTransactionsJsResponse;
import top.gabin.tools.response.pay.combine.CombineTransactionsStatusResponse;
import top.gabin.tools.response.tool.ImageUploadResponse;
import top.gabin.tools.utils.HttpUtils;
import top.gabin.tools.utils.JsonUtils;

import javax.crypto.Cipher;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.stream.Collectors;

public class ProfitsSharingServiceImpl implements ProfitsSharingService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ProfitsSharingConfig config;
    private final HttpUtils httpUtils;
    private final AesUtil aesUtil;


    public ProfitsSharingServiceImpl(ProfitsSharingConfig config, CacheService cacheService) {
        this.config = config;
        httpUtils = new HttpUtils(config, cacheService);
        aesUtil = new AesUtil(config.getApiKey().getBytes());
    }

    private PrivateKey getPrivateKey() {
        return config.getPrivateKey();
    }

    @Override
    public Optional<ApplymentsResponse> applyments(ApplymentsRequest request) {
        Optional<X509Certificate> x509Certificate = getFirstCertificate();
        if (x509Certificate.isPresent()) {
            X509Certificate certificate = x509Certificate.get();
            ApplymentsRequest.AccountInfo accountInfo = request.getAccountInfo();
            if (accountInfo != null) {
                accountInfo.setAccountName(rsaEncryptOAEP(accountInfo.getAccountName(), certificate));
                accountInfo.setAccountNumber(rsaEncryptOAEP(accountInfo.getAccountNumber(), certificate));
            }
            ApplymentsRequest.ContactInfo contactInfo = request.getContactInfo();
            if (contactInfo != null) {
                contactInfo.setMobilePhone(rsaEncryptOAEP(contactInfo.getMobilePhone(), certificate));
                contactInfo.setContactEmail(rsaEncryptOAEP(contactInfo.getContactEmail(), certificate));
                contactInfo.setContactName(rsaEncryptOAEP(contactInfo.getContactName(), certificate));
                contactInfo.setContactIdCardNumber(rsaEncryptOAEP(contactInfo.getContactIdCardNumber(), certificate));
            }
            ApplymentsRequest.IdCardInfo idCardInfo = request.getIdCardInfo();
            if (idCardInfo != null) {
                idCardInfo.setIdCardNumber(rsaEncryptOAEP(idCardInfo.getIdCardNumber(), certificate));
                idCardInfo.setIdCardName(rsaEncryptOAEP(idCardInfo.getIdCardName(), certificate));
            }
            return post(ApplymentsResponse.class, request, "https://api.mch.weixin.qq.com/v3/ecommerce/applyments/", certificate);
        }
        return Optional.empty();
    }

    private String rsaEncryptOAEP(String message, X509Certificate x509Cert) {
        if (StringUtils.isBlank(message)) {
            return message;
        }
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-1AndMGF1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, x509Cert.getPublicKey());

            byte[] data = message.getBytes("utf-8");
            byte[] cipherdata = cipher.doFinal(data);
            return Base64.getEncoder().encodeToString(cipherdata);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Optional<ApplymentsStatusResponse> queryApplymentsStatus(String applymentId) {
        return get(ApplymentsStatusResponse.class, String.format("https://api.mch.weixin.qq.com/v3/ecommerce/applyments/%s",
                applymentId));
    }

    @Override
    public Optional<ApplymentsStatusResponse> queryApplymentsStatusByOutNo(String outRequestNo) {
        return get(ApplymentsStatusResponse.class, String.format("https://api.mch.weixin.qq.com/v3/ecommerce/applyments/out-request-no/%s",
                outRequestNo));
    }

    @Override
    public List<X509Certificate> downloadCertificates() {
        return httpUtils.getLastCertificateList(); // 和内置的工具统一出口
    }

    @Override
    public Optional<ApplymentsModifySettlementResponse> modifySettlement(ApplymentsModifySettlementRequest request) {
        Optional<X509Certificate> x509Certificate = getFirstCertificate();
        if (x509Certificate.isPresent()) {
            X509Certificate certificate = x509Certificate.get();
            String accountNumber = request.getAccountNumber();
            request.setAccountNumber(rsaEncryptOAEP(accountNumber, certificate));
            return post(ApplymentsModifySettlementResponse.class, request,
                    String.format("https://api.mch.weixin.qq.com/v3/apply4sub/sub_merchants/%s/modify-settlement",
                            request.getSubMchid()), certificate);
        }
        return Optional.empty();
    }

    private Optional<X509Certificate> getFirstCertificate() {
        List<X509Certificate> lastCertificateList = downloadCertificates();
        return lastCertificateList.stream().findFirst();
    }

    @Override
    public Optional<ApplymentsSettlementStatusResponse> querySettlement(ApplymentsSettlementStatusRequest request) {
        return get(ApplymentsSettlementStatusResponse.class,
                String.format("https://api.mch.weixin.qq.com/v3/apply4sub/sub_merchants/%s/settlement",
                        request.getSubMchid()));
    }

    @Override
    public Optional<String> combineTransactions(CombineTransactionsAppRequest request) {
        Optional<CombineTransactionsAppResponse> responseOptional = post(CombineTransactionsAppResponse.class, request,
                "https://api.mch.weixin.qq.com/v3/combine-transactions/app");
        return responseOptional.map(CombineTransactionsAppResponse::getPrepayId);
    }

    @Override
    public Optional<String> combineTransactions(CombineTransactionsJsRequest request) {
        Optional<CombineTransactionsJsResponse> responseOptional = post(CombineTransactionsJsResponse.class, request,
                "https://api.mch.weixin.qq.com/v3/combine-transactions/jsapi");
        return responseOptional.map(CombineTransactionsJsResponse::getPrepayId);
    }

    @Override
    public Optional<CombineTransactionsStatusResponse> combineTransactionsStatus(String combineOutTradeNo) {
        String url = String.format("https://api.mch.weixin.qq.com/v3/combine-transactions/out-trade-no/%s", combineOutTradeNo);
        return get(CombineTransactionsStatusResponse.class, url);
    }

    @Override
    public void combineTransactionsClose(CombineTransactionsCloseRequest request) {
        String url = String.format("https://api.mch.weixin.qq.com/v3/combine-transactions/out-trade-no/%s/close", request.getCombineOutTradeNo());
        post(CombineTransactionsCloseResponse.class, request, url);
    }

    @Override
    public Map<String, String> getAppPayParams(String prePayId, String appId) {
        Map<String, String> params = getParams();
        params.put("appId", appId);
        long timeStamp = System.currentTimeMillis();
        String nonceStr = timeStamp + "_app";
        params.put("timeStamp", timeStamp + "");
        params.put("nonceStr", nonceStr);
        params.put("package", "Sign=WXPay");
        params.put("signType", "RSA");
        String singSource = String.format("%s\n%s\n%s\n%s\n",
                appId,
                timeStamp,
                nonceStr,
                prePayId);
        String sign = sign(singSource);
        params.put("paySign", sign);
        return params;
    }

    @Override
    public Map<String, String> getJsPayParams(String prePayId, String appId) {
        Map<String, String> params = getParams();
        params.put("appId", appId);
        long timeStamp = System.currentTimeMillis();
        String nonceStr = timeStamp + "_js";
        params.put("timeStamp", timeStamp + "");
        params.put("nonceStr", nonceStr);
        String packageVal = "prepay_id=" + prePayId;
        params.put("package", packageVal);
        String signType = "RSA";
        params.put("signType", signType);
        String singSource = String.format("%s\n%s\n%s\n%s\n",
                appId,
                timeStamp,
                nonceStr,
                packageVal);
        String sign = sign(singSource);
        params.put("paySign", sign);
        return params;
    }

    private Map<String, String> getParams() {
        return new HashMap<>();
    }

    @Override
    public Map<String, String> getSmallPayParams(String prePayId, String appId) {
        return getJsPayParams(prePayId, appId);
    }

    @Override
    public boolean verifyNotifySign(String timeStamp, String nonce, String body, String signed, String serialNo) {
        String beforeSign = timeStamp + "\n" + nonce + "\n" + body + "\n";
        try {
            return httpUtils.getVerifier().verify(serialNo, beforeSign.getBytes("utf-8"), signed);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<CombineTransactionsNotifyRequest1> parsePayNotify(String notifyContent) {
        if (StringUtils.isNoneBlank(notifyContent)) {
            CombineTransactionsNotifyRequest request = JsonUtils.json2Bean(CombineTransactionsNotifyRequest.class, notifyContent);
            CombineTransactionsNotifyRequest.Resource resource = request.getResource();
            try {
                String json = aesUtil.decryptToString(resource.getAssociatedData().getBytes(), resource.getNonce().getBytes(), resource.getCiphertext());
                CombineTransactionsNotifyRequest1 request1 = JsonUtils.json2Bean(CombineTransactionsNotifyRequest1.class, json);
                return Optional.ofNullable(request1);
            } catch (GeneralSecurityException | IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<SubsidiesCreateResponse> subsidiesCreate(SubsidiesCreateRequest request) {
        return post(SubsidiesCreateResponse.class, request,
                "https://api.mch.weixin.qq.com/v3/ecommerce/subsidies/create");
    }

    @Override
    public Optional<SubsidiesRefundResponse> subsidiesRefund(SubsidiesRefundRequest request) {
        return post(SubsidiesRefundResponse.class, request,
                "https://api.mch.weixin.qq.com/v3/ecommerce/subsidies/return");
    }

    @Override
    public Optional<SubsidiesCancelResponse> subsidiesCancel(SubsidiesCancelRequest request) {
        return post(SubsidiesCancelResponse.class, request,
                "https://api.mch.weixin.qq.com/v3/ecommerce/subsidies/cancel");
    }

    @Override
    public Optional<ProfitSharingApplyResponse> applyProfitSharing(ProfitSharingApplyRequest request) {
        return post(ProfitSharingApplyResponse.class, request, "https://api.mch.weixin.qq.com/v3/ecommerce/profitsharing/orders");
    }

    @Override
    public Optional<String> getPlatformId() {
        return Optional.ofNullable(config.getMchId());
    }

    @Override
    public Optional<ProfitSharingQueryApplyResponse> queryProfitSharingStatus(ProfitSharingQueryApplyRequest request) {
        Map<String, String> params = getParams();
        params.put("sub_mchid", request.getSubMchid());
        params.put("transaction_id", request.getTransactionId());
        params.put("out_order_no", request.getOutOrderNo());
        return get(ProfitSharingQueryApplyResponse.class, "https://api.mch.weixin.qq.com/v3/ecommerce/profitsharing/orders", params);
    }

    @Override
    public Optional<ProfitSharingRefundResponse> refundProfitSharing(ProfitSharingRefundRequest request) {
        return post(ProfitSharingRefundResponse.class, request, "https://api.mch.weixin.qq.com/v3/ecommerce/profitsharing/returnorders");
    }

    @Override
    public Optional<ProfitSharingQueryRefundResponse> queryRefundProfitSharingStatus(ProfitSharingQueryRefundRequest request) {
        Map<String, String> params = getParams();
        params.put("sub_mchid", request.getSubMchid());
        params.put("order_id", request.getOrderId());
        params.put("out_order_no", request.getOutOrderNo());
        params.put("out_return_no", request.getOutReturnNo());
        return get(ProfitSharingQueryRefundResponse.class, "https://api.mch.weixin.qq.com/v3/ecommerce/profitsharing/returnorders", params);
    }

    @Override
    public Optional<ProfitSharingFinishResponse> finishProfitSharing(ProfitSharingFinishRequest request) {
        return post(ProfitSharingFinishResponse.class, request, "https://api.mch.weixin.qq.com/v3/ecommerce/profitsharing/finish-order");
    }

    @Override
    public Optional<ProfitSharingAddReceiverResponse> addReceiver(ProfitSharingAddReceiverRequest request) {
        return post(ProfitSharingAddReceiverResponse.class, request, "https://api.mch.weixin.qq.com/v3/ecommerce/profitsharing/receivers/add");
    }

    @Override
    public Optional<ProfitSharingRemoveReceiverResponse> removeReceiver(ProfitSharingRemoveReceiverRequest request) {
        return post(ProfitSharingRemoveReceiverResponse.class, request, "https://api.mch.weixin.qq.com/v3/ecommerce/profitsharing/receivers/delete");
    }

    @Override
    public Optional<ProfitSharingNotifyRequest1> parseProfitsSharingNotify(ProfitSharingNotifyRequest request) {
        if (request != null) {
            ProfitSharingNotifyRequest.Resource resource = request.getResource();
            try {
                String json = aesUtil.decryptToString(resource.getAssociatedData().getBytes(), resource.getNonce().getBytes(), resource.getCiphertext());
                ProfitSharingNotifyRequest1 request1 = JsonUtils.json2Bean(ProfitSharingNotifyRequest1.class, json);
                return Optional.ofNullable(request1);
            } catch (GeneralSecurityException | IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<RefundApplyResponse> refundApply(RefundApplyRequest request) {
        return post(RefundApplyResponse.class, request,
                "https://api.mch.weixin.qq.com/v3/ecommerce/refunds/apply");
    }

    @Override
    public Optional<RefundQueryStatusResponse> refundQueryById(String subMchid, String refundId) {
        String url = String.format("https://api.mch.weixin.qq.com/v3/ecommerce/refunds/%s/%s", subMchid, refundId);
        return post(RefundQueryStatusResponse.class, new Object(), url);
    }

    @Override
    public Optional<RefundQueryStatusResponse> refundQueryByNumber(String subMchid, String outRefundNo) {
        String url = String.format("https://api.mch.weixin.qq.com/v3/ecommerce/refunds/%s/%s", subMchid, outRefundNo);
        return post(RefundQueryStatusResponse.class, new Object(), url);
    }

    @Override
    public Optional<RefundNotifyRequest1> parseRefundNotify(RefundNotifyRequest request) {
        if (request != null) {
            RefundNotifyRequest.Resource resource = request.getResource();
            try {
                String json = aesUtil.decryptToString(resource.getAssociatedData().getBytes(), resource.getNonce().getBytes(), resource.getCiphertext());
                RefundNotifyRequest1 request1 = JsonUtils.json2Bean(RefundNotifyRequest1.class, json);
                return Optional.ofNullable(request1);
            } catch (GeneralSecurityException | IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<AmountOnlineOfSubMchResponse> queryOnlineAmount(String subMchid) {
        return get(AmountOnlineOfSubMchResponse.class,
                String.format("https://api.mch.weixin.qq.com/v3/ecommerce/fund/balance/%s", subMchid));
    }

    @Override
    public Optional<AmountDayEndOfSubMchResponse> queryDayEndAmount(String subMchid, Date date) {
        // 这边文档写的是在body json数据体中，可是。。。按照标准，应该get请求直接包含在query参数中。。
        String url = String.format("https://api.mch.weixin.qq.com/v3/ecommerce/fund/enddaybalance/%s?date=%s",
                subMchid, getFormatDate(date));
        return get(AmountDayEndOfSubMchResponse.class, url);
    }

    @Override
    public Optional<AmountOnlineOfPlatformResponse> queryOnlineAmount(AccountType accountType) {
        String url = String.format("https://api.mch.weixin.qq.com/v3/merchant/fund/balance/%s", accountType);
        return get(AmountOnlineOfPlatformResponse.class, url);
    }

    @Override
    public Optional<AmountDayEndOfPlatformResponse> queryDayEndAmount(AccountType accountType, Date date) {
        String url = String.format("https://api.mch.weixin.qq.com/v3/merchant/fund/dayendbalance/%s?date=%s",
                accountType, getFormatDate(date));
        return get(AmountDayEndOfPlatformResponse.class, url);
    }

    @Override
    public Optional<WithdrawForSubMchResponse> withdraw(WithdrawForSubMchRequest request) {
        return post(WithdrawForSubMchResponse.class, request, "https://api.mch.weixin.qq.com/v3/ecommerce/fund/withdraw");
    }

    @Override
    public Optional<WithdrawStatusForSubMchResponse> queryWithdrawStatus(WithdrawStatusForSubMchRequest request) {
        return get(WithdrawStatusForSubMchResponse.class,
                String.format("https://api.mch.weixin.qq.com/v3/ecommerce/fund/withdraw/%s?sub_mchid=%s",
                        request.getWithdrawId(),
                        request.getSubMchid()));
    }

    @Override
    public Optional<WithdrawStatusForSubMchResponse> queryWithdrawStatus(WithdrawStatusForSubMchRequest1 request) {
        return get(WithdrawStatusForSubMchResponse.class,
                String.format("https://api.mch.weixin.qq.com/v3/ecommerce/fund/withdraw/out-request-no/%s?sub_mchid=%s",
                        request.getOutRequestNo(),
                        request.getSubMchid()));
    }

    @Override
    public Optional<WithdrawForPlatformResponse> withdraw(WithdrawForPlatformRequest request) {
        return post(WithdrawForPlatformResponse.class, request, "https://api.mch.weixin.qq.com/v3/merchant/fund/withdraw");
    }

    @Override
    public Optional<WithdrawStatusForPlatformResponse> queryWithdrawStatus(WithdrawStatusForPlatformRequest request) {
        return get(WithdrawStatusForPlatformResponse.class,
                String.format("https://api.mch.weixin.qq.com/v3/merchant/fund/withdraw/out-request-no/%s",
                        request.getOutRequestNo()));
    }

    @Override
    public Optional<String> downloadWithdrawExceptionFile(WithdrawExceptionLogRequest request) {
        Optional<WithdrawExceptionLogResponse> responseOptional = get(WithdrawExceptionLogResponse.class,
                String.format("https://api.mch.weixin.qq.com/v3/merchant/fund/withdraw/bill-type/%s?bill_date=%s&tar_type=%s",
                        request.getBillType(),
                        request.getBillDate(),
                        request.getTarType())
        );
        return responseOptional.map(WithdrawExceptionLogResponse::getDownloadUrl);
    }

    @Override
    public Optional<String> downloadTradeBill(BillOfTradeRequest request) {
        Map<String, String> query = new HashMap<>();
        query.put("bill_date", request.getBillDate());
        query.put("sub_mchid", request.getSubMchid());
        query.put("bill_type", request.getBillType());
        query.put("tar_type", request.getTarType());
        Optional<BillOfTradeResponse> response = get(BillOfTradeResponse.class, "https://api.mch.weixin.qq.com/v3/bill/tradebill", query);
        return response.map(BillOfTradeResponse::getDownloadUrl);
    }

    @Override
    public Optional<String> downloadTradeBill(BillOfFundFlowRequest request) {
        Map<String, String> query = new HashMap<>();
        query.put("bill_date", request.getBillDate());
        query.put("account_type", request.getAccountType());
        String tarType = request.getTarType();
        if (StringUtils.isNoneBlank(tarType)) {
            query.put("tar_type", tarType);
        }
        Optional<BillOfFundFlowResponse> response = get(BillOfFundFlowResponse.class, "https://api.mch.weixin.qq.com/v3/bill/fundflowbill", query);
        return response.map(BillOfFundFlowResponse::getDownloadUrl);
    }

    @Override
    public InputStream downloadBillFile(String downloadUrl) {
        return httpUtils.download(downloadUrl);
    }

    @Override
    public Optional<ImageUploadResponse> uploadImage(File file) throws Exception {
        // 商户号
        String mchid = config.getMchId();
        // 证书序列号
        String serial_no = config.getMchSerialNo();
        // 时间戳
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        // 随机数
        String nonce_str = UUID.randomUUID().toString();
        // 图片文件
        String filename = file.getName();//文件名
        String fileSha256 = DigestUtils.sha256Hex(new FileInputStream(file));//文件sha256

        //计算签名
        String sb = "POST" + "\n" +
                "/v3/merchant/media/upload" + "\n" +
                timestamp + "\n" +
                nonce_str + "\n" +
                "{\"filename\":\"" + filename + "\",\"sha256\":\"" + fileSha256 + "\"}" + "\n";
        String sign = sign(sb);

        // 拼装http头的Authorization内容
        String authorization = "WECHATPAY2-SHA256-RSA2048 mchid=\"" + mchid + "\",nonce_str=\"" + nonce_str + "\",signature=\"" + sign + "\",timestamp=\"" + timestamp + "\",serial_no=\"" + serial_no + "\"";
        //接口URL
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String url = "https://api.mch.weixin.qq.com/v3/merchant/media/upload";
        HttpPost httpPost = new HttpPost(url);

        // 设置头部
        httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("Content-Type", "multipart/form-data");
        httpPost.addHeader("Authorization", authorization);
        // 创建MultipartEntityBuilder
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.RFC6532);
        // 设置boundary
        multipartEntityBuilder.setBoundary("boundary");
        multipartEntityBuilder.setCharset(StandardCharsets.UTF_8);
        // 设置meta内容
        multipartEntityBuilder.addTextBody("meta", "{\"filename\":\"" + filename + "\",\"sha256\":\"" + fileSha256 + "\"}", ContentType.APPLICATION_JSON);
        // 设置图片内容
        multipartEntityBuilder.addBinaryBody("file", file, ContentType.create("image/jpg"), filename);
        // 放入内容
        httpPost.setEntity(multipartEntityBuilder.build());
        // 获取返回内容
        CloseableHttpResponse response = httpclient.execute(httpPost);
        HttpEntity httpEntity = response.getEntity();
        String responseText = new String(InputStreamTOByte(httpEntity.getContent()));
        // 验证微信支付返回签名
        String timestampHeader = getHeaderString(response, "Wechatpay-Timestamp");
        String nonceHeader = getHeaderString(response, "Wechatpay-Nonce");
        String singedHear = getHeaderString(response, "Wechatpay-Signature");
        if (timestampHeader != null && nonceHeader != null && singedHear != null) {
            String serialHeader = getHeaderString(response, "Wechatpay-Serial");
            if (verifyNotifySign(timestampHeader, nonceHeader, responseText, singedHear, serialHeader)) {
                EntityUtils.consume(httpEntity);
                response.close();
                ImageUploadResponse imageUploadResponse = JsonUtils.json2Bean(ImageUploadResponse.class, responseText);
                if (imageUploadResponse == null) {
                    return Optional.empty();
                }
                imageUploadResponse.setHttpStatusCode(response.getStatusLine().getStatusCode());
                return Optional.of(imageUploadResponse);
            } else {
                logger.info("签名验证失败");
            }
        }
        return Optional.empty();
    }

    private String getHeaderString(CloseableHttpResponse response, String name) {
        Header firstHeader = response.getFirstHeader(name);
        if (firstHeader != null) {
            return firstHeader.getValue();
        }
        return null;
    }

    private String sign(String message) {
        try {
            Signature sign = Signature.getInstance("SHA256withRSA");
            sign.initSign(getPrivateKey());
            sign.update(message.getBytes());
            return Base64.getEncoder().encodeToString(sign.sign());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public static byte[] InputStreamTOByte(InputStream in) throws IOException {

        int BUFFER_SIZE = 4096;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count;

        while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
            outStream.write(data, 0, count);

        byte[] outByte = outStream.toByteArray();
        outStream.close();

        return outByte;
    }


    private <T extends AbstractResponse> Optional<T> post(Class<T> classZ, Object request, String url) {
        T post = httpUtils.post(classZ, request, url);
        if (post == null) {
            return Optional.empty();
        }
        return Optional.of(post);
    }

    private <T extends AbstractResponse> Optional<T> post(Class<T> classZ, Object request, String url, X509Certificate certificate) {
        T post = httpUtils.post(classZ, request, url, certificate);
        if (post == null) {
            return Optional.empty();
        }
        return Optional.of(post);
    }

    private <T extends AbstractResponse> Optional<T> get(Class<T> classZ, String url) {
        T value = httpUtils.get(classZ, url);
        if (value == null) {
            return Optional.empty();
        }
        return Optional.of(value);
    }

    private <T extends AbstractResponse> Optional<T> get(Class<T> classZ, String url, Map<String, String> query) {
        if (query != null && !query.isEmpty()) {
            if (!url.contains("?")) {
                url += "?";
            }
            String queryStr = query.keySet().stream().map(key -> key + "=" + query.get(key)).collect(Collectors.joining("&"));
            url += queryStr;
        }
        return get(classZ, url);
    }

    private String getFormatDate(Date date) {
        return DateFormatUtils.format(date, "yyyy-MM-dd");
    }

}
