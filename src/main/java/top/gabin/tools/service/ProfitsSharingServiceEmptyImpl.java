package top.gabin.tools.service;

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
import top.gabin.tools.request.pay.combine.CombineTransactionsAppRequest;
import top.gabin.tools.request.pay.combine.CombineTransactionsCloseRequest;
import top.gabin.tools.request.pay.combine.CombineTransactionsJsRequest;
import top.gabin.tools.request.pay.combine.CombineTransactionsNotifyRequest1;
import top.gabin.tools.response.ecommerce.amount.AmountDayEndOfPlatformResponse;
import top.gabin.tools.response.ecommerce.amount.AmountDayEndOfSubMchResponse;
import top.gabin.tools.response.ecommerce.amount.AmountOnlineOfPlatformResponse;
import top.gabin.tools.response.ecommerce.amount.AmountOnlineOfSubMchResponse;
import top.gabin.tools.response.ecommerce.applyments.ApplymentsModifySettlementResponse;
import top.gabin.tools.response.ecommerce.applyments.ApplymentsResponse;
import top.gabin.tools.response.ecommerce.applyments.ApplymentsSettlementStatusResponse;
import top.gabin.tools.response.ecommerce.applyments.ApplymentsStatusResponse;
import top.gabin.tools.response.ecommerce.fund.WithdrawForPlatformResponse;
import top.gabin.tools.response.ecommerce.fund.WithdrawForSubMchResponse;
import top.gabin.tools.response.ecommerce.fund.WithdrawStatusForPlatformResponse;
import top.gabin.tools.response.ecommerce.fund.WithdrawStatusForSubMchResponse;
import top.gabin.tools.response.ecommerce.profitsharing.*;
import top.gabin.tools.response.ecommerce.refunds.RefundApplyResponse;
import top.gabin.tools.response.ecommerce.refunds.RefundQueryStatusResponse;
import top.gabin.tools.response.ecommerce.subsidies.SubsidiesCancelResponse;
import top.gabin.tools.response.ecommerce.subsidies.SubsidiesCreateResponse;
import top.gabin.tools.response.ecommerce.subsidies.SubsidiesRefundResponse;
import top.gabin.tools.response.pay.combine.CombineTransactionsStatusResponse;
import top.gabin.tools.response.tool.ImageUploadResponse;

import java.io.File;
import java.io.InputStream;
import java.security.cert.X509Certificate;
import java.util.*;

public class ProfitsSharingServiceEmptyImpl implements ProfitsSharingService {
    @Override
    public Optional<ApplymentsResponse> applyments(ApplymentsRequest request) {
        return Optional.empty();
    }

    @Override
    public Optional<ApplymentsStatusResponse> queryApplymentsStatus(String applymentId) {
        return Optional.empty();
    }

    @Override
    public Optional<ApplymentsStatusResponse> queryApplymentsStatusByOutNo(String outRequestNo) {
        return Optional.empty();
    }

    @Override
    public List<X509Certificate> downloadCertificates() {
        return Collections.emptyList();
    }

    @Override
    public Optional<ApplymentsModifySettlementResponse> modifySettlement(ApplymentsModifySettlementRequest request) {
        return Optional.empty();
    }

    @Override
    public Optional<ApplymentsSettlementStatusResponse> querySettlement(String subMchid) {
        return Optional.empty();
    }

    @Override
    public Optional<String> combineTransactions(CombineTransactionsAppRequest request) {
        return Optional.empty();
    }

    @Override
    public Optional<String> combineTransactions(CombineTransactionsJsRequest request) {
        return Optional.empty();
    }

    @Override
    public Optional<CombineTransactionsStatusResponse> combineTransactionsStatus(String combineOutTradeNo) {
        return Optional.empty();
    }

    @Override
    public void combineTransactionsClose(CombineTransactionsCloseRequest request) {

    }

    @Override
    public Map<String, String> getAppPayParams(String prePayId, String appId) {
        return null;
    }

    @Override
    public Map<String, String> getJsPayParams(String prePayId, String appId) {
        return null;
    }

    @Override
    public Map<String, String> getSmallPayParams(String prePayId, String appId) {
        return null;
    }

    @Override
    public boolean verifyNotifySign(String timeStamp, String nonce, String body, String signed, String serialNo) {
        return false;
    }

    @Override
    public Optional<CombineTransactionsNotifyRequest1> parsePayNotify(String notifyContent) {
        return Optional.empty();
    }

    @Override
    public Optional<SubsidiesCreateResponse> subsidiesCreate(SubsidiesCreateRequest request) {
        return Optional.empty();
    }

    @Override
    public Optional<SubsidiesRefundResponse> subsidiesRefund(SubsidiesRefundRequest request) {
        return Optional.empty();
    }

    @Override
    public Optional<SubsidiesCancelResponse> subsidiesCancel(SubsidiesCancelRequest request) {
        return Optional.empty();
    }

    @Override
    public Optional<ProfitSharingApplyResponse> applyProfitSharing(ProfitSharingApplyRequest request) {
        return Optional.empty();
    }

    @Override
    public Optional<String> getPlatformId() {
        return Optional.empty();
    }

    @Override
    public Optional<ProfitSharingQueryApplyResponse> queryProfitSharingStatus(ProfitSharingQueryApplyRequest request) {
        return Optional.empty();
    }

    @Override
    public Optional<ProfitSharingRefundResponse> refundProfitSharing(ProfitSharingRefundRequest request) {
        return Optional.empty();
    }

    @Override
    public Optional<ProfitSharingQueryRefundResponse> queryRefundProfitSharingStatus(ProfitSharingQueryRefundRequest request) {
        return Optional.empty();
    }

    @Override
    public Optional<ProfitSharingFinishResponse> finishProfitSharing(ProfitSharingFinishRequest request) {
        return Optional.empty();
    }

    @Override
    public Optional<ProfitSharingAddReceiverResponse> addReceiver(ProfitSharingAddReceiverRequest request) {
        return Optional.empty();
    }

    @Override
    public Optional<ProfitSharingRemoveReceiverResponse> removeReceiver(ProfitSharingRemoveReceiverRequest request) {
        return Optional.empty();
    }

    @Override
    public Optional<ProfitSharingNotifyRequest1> parseProfitsSharingNotify(ProfitSharingNotifyRequest request) {
        return Optional.empty();
    }

    @Override
    public Optional<RefundApplyResponse> refundApply(RefundApplyRequest request) {
        return Optional.empty();
    }

    @Override
    public Optional<RefundQueryStatusResponse> refundQueryById(String subMchid, String refundId) {
        return Optional.empty();
    }

    @Override
    public Optional<RefundQueryStatusResponse> refundQueryByNumber(String subMchid, String outRefundNo) {
        return Optional.empty();
    }

    @Override
    public Optional<RefundNotifyRequest1> parseRefundNotify(RefundNotifyRequest request) {
        return Optional.empty();
    }

    @Override
    public Optional<AmountOnlineOfSubMchResponse> queryOnlineAmount(String subMchid) {
        return Optional.empty();
    }

    @Override
    public Optional<AmountDayEndOfSubMchResponse> queryDayEndAmount(String subMchid, Date date) {
        return Optional.empty();
    }

    @Override
    public Optional<AmountOnlineOfPlatformResponse> queryOnlineAmount(AccountType accountType) {
        return Optional.empty();
    }

    @Override
    public Optional<AmountDayEndOfPlatformResponse> queryDayEndAmount(AccountType accountType, Date date) {
        return Optional.empty();
    }

    @Override
    public Optional<WithdrawForSubMchResponse> withdraw(WithdrawForSubMchRequest request) {
        return Optional.empty();
    }

    @Override
    public Optional<WithdrawStatusForSubMchResponse> queryWithdrawStatusByOutNo(String subMchId, String outRequestNo) {
        return Optional.empty();
    }

    @Override
    public Optional<WithdrawForPlatformResponse> withdraw(WithdrawForPlatformRequest request) {
        return Optional.empty();
    }

    @Override
    public Optional<WithdrawStatusForSubMchResponse> queryWithdrawStatus(String subMchId, String withdrawId) {
        return Optional.empty();
    }

    @Override
    public Optional<WithdrawStatusForPlatformResponse> queryWithdrawStatus(String outRequestNo) {
        return Optional.empty();
    }

    @Override
    public Optional<String> downloadWithdrawExceptionFile(WithdrawExceptionLogRequest request) {
        return Optional.empty();
    }

    @Override
    public Optional<String> downloadTradeBill(BillOfTradeRequest request) {
        return Optional.empty();
    }

    @Override
    public Optional<String> downloadTradeBill(BillOfFundFlowRequest request) {
        return Optional.empty();
    }

    @Override
    public InputStream downloadBillFile(String downloadUrl) {
        return null;
    }

    @Override
    public Optional<ImageUploadResponse> uploadImage(File file) {
        return Optional.empty();
    }
}
