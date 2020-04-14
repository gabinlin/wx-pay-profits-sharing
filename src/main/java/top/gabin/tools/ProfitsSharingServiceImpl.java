package top.gabin.tools;

import com.wechat.pay.contrib.apache.httpclient.util.AesUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.gabin.tools.config.ProfitsSharingConfig;
import top.gabin.tools.constant.AccountType;
import top.gabin.tools.request.ecommerce.applyments.*;
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
import top.gabin.tools.response.ecommerce.amount.AmountDayEndOfPlatformResponse;
import top.gabin.tools.response.ecommerce.amount.AmountDayEndOfSubMchResponse;
import top.gabin.tools.response.ecommerce.amount.AmountOnlineOfPlatformResponse;
import top.gabin.tools.response.ecommerce.amount.AmountOnlineOfSubMchResponse;
import top.gabin.tools.response.ecommerce.applyments.*;
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
import top.gabin.tools.response.pay.combine.CombineTransactionsJsResponse;
import top.gabin.tools.response.pay.combine.CombineTransactionsStatusResponse;
import top.gabin.tools.utils.HttpUtils;
import top.gabin.tools.utils.JsonUtils;
import top.gabin.tools.utils.RSASignUtil;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProfitsSharingServiceImpl implements ProfitsSharingService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ProfitsSharingConfig config;
    private HttpUtils httpUtils;


    public ProfitsSharingServiceImpl(ProfitsSharingConfig config) {
        this.config = config;
        httpUtils = new HttpUtils(config.getMchId(), config.getMchSerialNo(), config.getPrivateKey(), config.getCertificate());
    }

    private String getPrivateKey() {
        return config.getPrivateKey();
    }

    private String getPublicKey() {
        return config.getPublicKey();
    }

    @Override
    public Optional<ApplymentsResponse> applyments(ApplymentsRequest request) {
        return post(ApplymentsResponse.class, request, "https://api.mch.weixin.qq.com/v3/ecommerce/applyments");
    }

    @Override
    public Optional<ApplymentsStatusResponse> queryApplymentsStatus(ApplymentsStatusRequest request) {
        return get(ApplymentsStatusResponse.class, String.format("https://api.mch.weixin.qq.com/v3/ecommerce/applyments/%s",
                request.getApplymentId()));
    }

    @Override
    public Optional<ApplymentsStatusResponse> queryApplymentsStatus(ApplymentsStatusRequest1 request) {
        return get(ApplymentsStatusResponse.class, String.format("https://api.mch.weixin.qq.com/v3/ecommerce/applyments/out-request-no/%s",
                request.getOutRequestNo()));
    }

    @Override
    public Optional<ApplymentsDownCertificatesResponse> downloadCertificates() {
        return get(ApplymentsDownCertificatesResponse.class, "https://api.mch.weixin.qq.com/v3/certificates");
    }

    @Override
    public Optional<ApplymentsModifySettlementResponse> modifySettlement(ApplymentsModifySettlementRequest request) {
        return post(ApplymentsModifySettlementResponse.class, request,
                String.format("https://api.mch.weixin.qq.com/v3/apply4sub/sub_merchants/%s/modify-settlement",
                        request.getSubMchid()));
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
        post(String.class, request, url);
    }

    @Override
    public Map<String, String> getAppPayParams(String prePayId, String appId) {
        Map<String, String> params = getParams();
        params.put("appId", appId);
        long timeStamp = System.currentTimeMillis();
        String nonceStr = timeStamp + "_app";
        params.put("timeStamp", timeStamp + "");
        params.put("nonceStr", nonceStr);
        String packageStr = "prepay_id=" + prePayId;
        params.put("package", packageStr);
        params.put("signType", "RSA");
        String singSource = String.format("%s\n%s\n%s\n%s",
                appId,
                timeStamp,
                nonceStr,
                "prepay_id=" + prePayId);
        String sign = RSASignUtil.sign(getPrivateKey(), singSource);
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
        String singSource = String.format("%s\n%s\n%s\n%s",
                appId,
                timeStamp,
                nonceStr,
                packageVal);
        String sign = RSASignUtil.sign(getPrivateKey(), singSource);
        params.put("paySign", sign);
        return params;
    }

    private Map<String, String> getParams() {
        return new HashMap<>();
    }

    @Override
    public Map<String, String> getSmallPayParams(String prePayId, String appId) {
        Map<String, String> params = getParams();
        params.put("appId", appId);
        long timeStamp = System.currentTimeMillis();
        String nonceStr = timeStamp + "_small_app";
        params.put("timeStamp", timeStamp + "");
        params.put("nonceStr", nonceStr);
        String packageVal = "prepay_id=" + prePayId;
        params.put("package", packageVal);
        params.put("signType", "RSA");
        String singSource = String.format("%s\n%s\n%s\n%s",
                appId,
                timeStamp,
                nonceStr,
                prePayId);
        String sign = RSASignUtil.sign(getPrivateKey(), singSource);
        params.put("paySign", sign);
        return params;
    }

    @Override
    public boolean verifyNotifySign(String timeStamp, String nonce, String body, String signed) {
        StringBuilder beforeSign = new StringBuilder(timeStamp + "\n");
        beforeSign.append(nonce + "\n");
        beforeSign.append(body);
        return RSASignUtil.verifySign(getPublicKey(), beforeSign.toString(), signed);
    }

    @Override
    public Optional<CombineTransactionsNotifyRequest1> parsePayNotify(CombineTransactionsNotifyRequest request) {
        if (request != null) {
            CombineTransactionsNotifyRequest.Resource resource = request.getResource();
            AesUtil aesUtil = getAesUtil();
            try {
                String json = aesUtil.decryptToString(resource.getAssociatedData().getBytes(), resource.getNonce().getBytes(), resource.getCiphertext());
                CombineTransactionsNotifyRequest1 request1 = JsonUtils.json2Bean(CombineTransactionsNotifyRequest1.class, json);
                return Optional.ofNullable(request1);
            } catch (GeneralSecurityException e) {
                logger.error(e.getMessage(), e);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return Optional.empty();
    }

    // TODO 待优化
    private AesUtil getAesUtil() {
        return new AesUtil(config.getApiKey().getBytes());
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
            AesUtil aesUtil = getAesUtil();
            try {
                String json = aesUtil.decryptToString(resource.getAssociatedData().getBytes(), resource.getNonce().getBytes(), resource.getCiphertext());
                ProfitSharingNotifyRequest1 request1 = JsonUtils.json2Bean(ProfitSharingNotifyRequest1.class, json);
                return Optional.ofNullable(request1);
            } catch (GeneralSecurityException e) {
                logger.error(e.getMessage(), e);
            } catch (IOException e) {
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
            AesUtil aesUtil = getAesUtil();
            try {
                String json = aesUtil.decryptToString(resource.getAssociatedData().getBytes(), resource.getNonce().getBytes(), resource.getCiphertext());
                RefundNotifyRequest1 request1 = JsonUtils.json2Bean(RefundNotifyRequest1.class, json);
                return Optional.ofNullable(request1);
            } catch (GeneralSecurityException e) {
                logger.error(e.getMessage(), e);
            } catch (IOException e) {
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
        String url = String.format("https://api.mch.weixin.qq.com/v3/ecommerce/fund/enddaybalance/{sub_mchid}?date=%s",
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
        query.put("tar_type", request.getTarType());
        Optional<BillOfFundFlowResponse> response = get(BillOfFundFlowResponse.class, "https://api.mch.weixin.qq.com/v3/bill/fundflowbill", query);
        return response.map(BillOfFundFlowResponse::getDownloadUrl);
    }

    @Override
    public File downloadBillFile(String downloadUrl) {
        // TODO
        return null;
    }

    private <T> Optional<T> post(Class<T> classZ, Object request, String url) {
        return Optional.ofNullable(httpUtils.post(classZ, request, url));
    }

    private <T> Optional<T> get(Class<T> classZ, String url) {
        return Optional.ofNullable(httpUtils.get(classZ, url));
    }

    private <T> Optional<T> get(Class<T> classZ, String url, Map<String, String> query) {
        if (query != null && !query.isEmpty()) {
            if (!url.contains("?")) {
                url += "?";
            }
            String queryStr = query.keySet().stream().map(key -> key + "=" + query.get(key)).collect(Collectors.joining("&"));
            url += queryStr;
        }
        return Optional.ofNullable(httpUtils.get(classZ, url));
    }

    private String getFormatDate(Date date) {
        return DateFormatUtils.format(date, "yyyy-MM-dd");
    }


}
