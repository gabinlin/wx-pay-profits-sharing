package top.gabin.tools;

import top.gabin.tools.request.ecommerce.refunds.RefundApplyRequest;
import top.gabin.tools.request.ecommerce.subsidies.SubsidiesCancelRequest;
import top.gabin.tools.request.ecommerce.subsidies.SubsidiesCreateRequest;
import top.gabin.tools.request.ecommerce.subsidies.SubsidiesRefundRequest;
import top.gabin.tools.request.pay.combine.CombineTransactionsJsRequest;
import top.gabin.tools.response.ecommerce.refunds.RefundApplyResponse;
import top.gabin.tools.response.ecommerce.subsidies.SubsidiesCancelResponse;
import top.gabin.tools.response.ecommerce.subsidies.SubsidiesCreateResponse;
import top.gabin.tools.response.ecommerce.subsidies.SubsidiesRefundResponse;

import java.util.Map;
import java.util.Optional;

public interface ProfitsSharingService {

    /**
     * <pre>
     * 合单下单-JS支付API.
     * 详见https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/combine/chapter3_2.shtml
     * 使用合单支付接口，用户只输入一次密码，即可完成多个订单的支付。目前最多一次可支持50笔订单进行合单支付。
     * 注意：
     * • 订单如果需要进行抽佣等，需要在合单中指定需要进行分账（profit_sharing为true）；指定后，交易资金进入二级商户账户，处于冻结状态，可在后续使用分账接口进行分账，利用分账完结进行资金解冻，实现抽佣和对二级商户的账期。
     * • 合单中同一个二级商户只允许有一笔子订单。
     * 接口地址：https://api.mch.weixin.qq.com/v3/combine-transactions/jsapi
     * </pre>
     *
     * @param request 微信订单号
     * @return String 预支付交易会话标识,数字和字母。微信生成的预支付会话标识，用于后续接口调用使用。
     */
    String combineTransactions(CombineTransactionsJsRequest request);

    /**
     * @param prePayId 预下单ID
     * @param appId    appID
     * @return 用于js调起支付的参数
     */
    Map<String, String> getStringStringMap(String prePayId, String appId);

    /**
     * <pre>
     * 请求补差API
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/subsidies/chapter3_1.shtml
     * 服务商下单的时候带上补差标识，微信订单支付成功并结算完成后，发起分账前，调用该口进行补差。
     * 注意：
     * • 电商平台下单时传入补差金额，详见【合单下单API】文档中补差字段说明。
     * • 在发起分账前，调用该接口进行补差。
     * • 补差金额需要和下单的时候传入的补差金额保持一致(发生用户退款时可以小于下单时的补差金额，须有对应的微信退款单号，任意一笔该订单的微信退款单)。
     * • 该接口支持重入，请求参数相同只会扣款一次，重入有效期180天。
     * • 系统异常（如返回SYSTEM_ERROR），请使用相同参数稍后重新调用，请务必用原参数来重入此接口，如更换金额重试，可能会导致重复扣款，系统会在1天后回退到原账户。
     * 接口地址：https://api.mch.weixin.qq.com/v3/ecommerce/subsidies/create
     * </pre>
     *
     * @param request 请求对象
     * @return .
     */
    Optional<SubsidiesCreateResponse> subsidiesCreate(SubsidiesCreateRequest request);

    /**
     * <pre>
     * 申退补差API
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/subsidies/chapter3_2.shtml
     * 订单发送退款的时候，可以对补贴成功的补差单发起回退。
     * 注意：
     * • 补差回退以原补差单位依据，支持多次回退，申请回退总金额不能超过补差金额。
     * • 此接口采用同步处理模式，即在接收到商户请求后，会实时返回处理结果。
     * • 补差回退的前置条件是订单发生退款。
     * • 系统异常（如返回SYSTEM_ERROR），请使用相同参数稍后重新调用，请务必用原商户补差回退单号和原参数来重入此接口。
     * 接口地址：https://api.mch.weixin.qq.com/v3/ecommerce/subsidies/refund
     * </pre>
     *
     * @param request 请求对象
     * @return .
     */
    Optional<SubsidiesRefundResponse> subsidiesRefund(SubsidiesRefundRequest request);

    /**
     * <pre>
     * 取消补差API
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/subsidies/chapter3_3.shtml
     * 对带有补差标识的订单，如果不需要补差，可在发起发起分账前，可调用这个接口进行取消补差。
     * 注意：
     * • 取消补差完成后，商户可以对未补差的订单进行分账。
     * • 订单补差取消的前置条件是订单发生退款。
     * </pre>
     *
     * @param request 请求对象
     * @return .
     */
    Optional<SubsidiesCancelResponse> subsidiesRefund(SubsidiesCancelRequest request);

    /**
     * <pre>
     * 退款API
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/refunds/chapter3_1.shtml
     * 当交易发生之后一段时间内，由于买家或者卖家的原因需要退款时，卖家可以通过退款接口将支付款退还给买家，微信支付将在收到退款请求并且验证成功之后，按照退款规则将支付款按原路退到买家帐号上。
     * 注意：
     *
     * • 交易时间超过一年的订单无法提交退款。
     * • 微信支付退款支持单笔交易分多次退款，多次退款需要提交原支付订单的商户订单号和设置不同的退款单号。申请退款总金额不能超过订单金额。 一笔退款失败后重新提交，请不要更换退款单号，请使用原商户退款单号。
     * • 请求频率限制：150qps，即每秒钟正常的申请退款请求次数不超过150次，错误或无效请求频率限制：6qps，即每秒钟异常或错误的退款申请请求不超过6次。
     * • 每个支付订单的部分退款次数不能超过50次。
     * 接口地址 https://api.mch.weixin.qq.com/v3/ecommerce/refunds/apply
     * </pre>
     *
     * @param request 请求对象
     * @return .
     */
    Optional<RefundApplyResponse> refundApply(RefundApplyRequest request);
}
