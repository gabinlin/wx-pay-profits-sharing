package top.gabin.tools.service;

import top.gabin.tools.constant.AccountType;
import top.gabin.tools.request.ecommerce.applyments.ApplymentsModifySettlementRequest;
import top.gabin.tools.request.ecommerce.applyments.ApplymentsRequest;
import top.gabin.tools.request.ecommerce.applyments.ApplymentsSettlementStatusRequest;
import top.gabin.tools.request.ecommerce.fund.WithdrawExceptionLogRequest;
import top.gabin.tools.request.ecommerce.fund.WithdrawForPlatformRequest;
import top.gabin.tools.request.ecommerce.fund.WithdrawForSubMchRequest;
import top.gabin.tools.request.ecommerce.fund.WithdrawStatusForSubMchRequest1;
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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProfitsSharingService {

    /**
     * <pre>
     * 二级商户进件API
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_1.shtml
     * 电商平台，可使用该接口，帮助其二级商户进件成为微信支付商户。
     * 接口说明
     * 适用对象：电商平台
     * 请求URL：https://api.mch.weixin.qq.com/v3/ecommerce/applyments/
     * 请求方式：POST
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     * </pre>
     *
     * @param request 请求对象
     * @return .
     */
    Optional<ApplymentsResponse> applyments(ApplymentsRequest request);

    /**
     * <pre>
     * 查询申请状态API
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_2.shtml
     *
     * 电商平台通过查询申请状态API查询二级商户入驻申请结果。
     *
     * 注意：
     * ● 查询申请状态API可按以下两种不同方式查询：
     *     1、通过申请单ID查询申请状态；
     *     2、通过业务申编号查询申请状态
     * ● 两种不同查询方式返回结果相同
     *
     * 1、通过申请单ID查询申请状态
     * 接口说明
     * 适用对象：电商平台
     * 请求URL：https://api.mch.weixin.qq.com/v3/ecommerce/applyments/{applyment_id}
     * 请求方式：GET
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     * </pre>
     *
     * @param applymentId 提交申请返回的ID
     * @return .
     */
    Optional<ApplymentsStatusResponse> queryApplymentsStatus(String applymentId);

    /**
     * <pre>
     * 查询申请状态API
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_2.shtml
     *
     * 电商平台通过查询申请状态API查询二级商户入驻申请结果。
     *
     * 注意：
     * ● 查询申请状态API可按以下两种不同方式查询：
     *     1、通过申请单ID查询申请状态；
     *     2、通过业务申编号查询申请状态
     * ● 两种不同查询方式返回结果相同
     *
     * 2、通过业务申请编号查询申请状态
     * 接口说明
     * 适用对象：电商平台
     * 请求URL：https://api.mch.weixin.qq.com/v3/ecommerce/applyments/out-request-no/{out_request_no}
     * 请求方式：GET
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     * </pre>
     *
     * @param outRequestNo 业务编号
     * @return .
     */
    Optional<ApplymentsStatusResponse> queryApplymentsStatusByOutNo(String outRequestNo);

    /**
     * <pre>
     * 下载平台证书API
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_3.shtml
     * 由于证书有效期限制和交易安全的原因，微信支付会不定期的更换平台证书。微信支付提供了一系列接口，帮助商户后台系统实现平滑的证书更换。
     *
     * 更换指引：
     * ● 建议开发者使用中控服务器（即统一管理和分发，注意证书的保密和安全性）统一下载和管理微信支付平台证书。其他业务逻辑服务器通过该中控服务器进行报文的验签和解密。
     * ● 在微信支付更换平台证书之前，待更换的证书会提前24小时加入商户的平台证书列表。中控服务器需要定时查询商户的平台证书列表，并及时下载新的平台证书。
     * ● 在微信支付更换平台证书期间，商户收到的应答请求和回调通知中会同时存在不同的证书序列号，商户要能正确处理这种情况。
     * ● 获取平台证书的接口频率限制规则: 单个商户号 1000次/s (查单接口为600次/s)。
     *
     * 最佳实践：
     * 在中控服务器上调用；
     * 定时调用，间隔应小于12小时；
     * 与本地证书序列表对比，如果发现有新增证书序列号，则需要新换的证书。老证书会在1天内失效，应及时清理；
     * 获取到证书后，分发到各业务接口服务器。
     * 接口说明
     * 适用对象：电商平台
     * 请求URL：https://api.mch.weixin.qq.com/v3/certificates
     * 请求方式：GET
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     * </pre>
     *
     * @return .
     */
    List<X509Certificate> downloadCertificates();

    /**
     * <pre>
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_4.shtml
     * 修改结算帐号API
     * 普通服务商（支付机构、银行不可用），可使用本接口修改其进件、已签约的特约商户-结算账户信息。
     *
     * 注意：
     * • 本接口无需传银行开户名称参数。
     * 若账户类型为“经营者个人银行卡”，则系统自动拉取特约商户的经营者姓名为开户名称。
     * 若账户类型为“对公银行账户”，则系统自动拉取特约商户的公司名称为开户名称。
     * 接口说明
     * 适用对象：普通服务商
     * 请求URL：https://api.mch.weixin.qq.com/v3/apply4sub/sub_merchants/{sub_mchid}/modify-settlement
     * 请求方式：POST
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     * </pre>
     *
     * @param request 请求对象
     * @return .
     */
    Optional<ApplymentsModifySettlementResponse> modifySettlement(ApplymentsModifySettlementRequest request);

    /**
     * <pre>
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_5.shtml
     * 查询结算账户API
     * 最新更新时间：2019.09.09 版本说明
     *
     * 普通服务商（支付机构、银行不可用），可使用本接口查询其进件、已签约的特约商户-结算账户信息（敏感信息掩码）。 该接口可用于核实是否成功修改结算账户信息、及查询系统汇款验证结果。
     *
     * 接口说明
     * 适用对象：普通服务商
     * 请求URL：https://api.mch.weixin.qq.com/v3/apply4sub/sub_merchants/{sub_mchid}/settlement
     * 请求方式：GET
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     * </pre>
     *
     * @param subMchid 二级商户ID
     * @return .
     */
    Optional<ApplymentsSettlementStatusResponse> querySettlement(String subMchid);

    /**
     * <pre>
     * 合单下单-APP支付API.
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/combine/chapter3_1.shtml
     * 使用合单支付接口，用户只输入一次密码，即可完成多个订单的支付。目前最多一次可支持50笔订单进行合单支付。
     * 注意：
     * • 订单如果需要进行抽佣等，需要在合单中指定需要进行分账（profit_sharing为true）；指定后，交易资金进入二级商户账户，处于冻结状态，可在后续使用分账接口进行分账，利用分账完结进行资金解冻，实现抽佣和对二级商户的账期。
     * • 合单中同一个二级商户只允许有一笔子订单。
     *
     * 适用对象：电商平台 服务商 直连商户
     * 请求URL：https://api.mch.weixin.qq.com/v3/combine-transactions/app
     * 请求方式：POST
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     * </pre>
     *
     * @param request 请求对象
     * @return 预支付交易会话标识, 数字和字母。微信生成的预支付会话标识，用于后续接口调用使用。
     */
    Optional<String> combineTransactions(CombineTransactionsAppRequest request);

    /**
     * <pre>
     * 合单下单-JS支付API.
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/combine/chapter3_2.shtml
     * 使用合单支付接口，用户只输入一次密码，即可完成多个订单的支付。目前最多一次可支持50笔订单进行合单支付。
     * 注意：
     * • 订单如果需要进行抽佣等，需要在合单中指定需要进行分账（profit_sharing为true）；指定后，交易资金进入二级商户账户，处于冻结状态，可在后续使用分账接口进行分账，利用分账完结进行资金解冻，实现抽佣和对二级商户的账期。
     * • 合单中同一个二级商户只允许有一笔子订单。
     * 适用对象：电商平台 服务商 直连商户
     * 请求URL：https://api.mch.weixin.qq.com/v3/combine-transactions/jsapi
     * 请求方式：POST
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     * </pre>
     *
     * @param request 请求对象
     * @return 预支付交易会话标识, 数字和字母。微信生成的预支付会话标识，用于后续接口调用使用。
     */
    Optional<String> combineTransactions(CombineTransactionsJsRequest request);

    /**
     * <pre>
     * 合单查询订单API
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/combine/chapter3_3.shtml
     * 电商平台通过合单查询订单API查询订单状态，完成下一步的业务逻辑。
     * 注意：
     * • 需要调用查询接口的情况：
     * 1、当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知。
     * 2、调用支付接口后，返回系统错误或未知交易状态情况。
     * 3、调用刷卡支付API，返回USERPAYING的状态。
     * 4、调用关单或撤销接口API之前，需确认支付状态。
     * 适用对象：电商平台 服务商 直连商户
     * 请求URL：https://api.mch.weixin.qq.com/v3/combine-transactions/out-trade-no/{combine_out_trade_no}
     * 请求方式：GET
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     * </pre>
     *
     * @param combineOutTradeNo 合单支付总订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一 。
     * @return 订单详情
     */
    Optional<CombineTransactionsStatusResponse> combineTransactionsStatus(String combineOutTradeNo);

    /**
     * <pre>
     * 合单关闭订单API
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/combine/chapter3_4.shtml
     * 合单支付订单只能使用此合单关单api完成关单。
     *
     * 适用对象：电商平台 服务商 直连商户
     * 请求URL：https://api.mch.weixin.qq.com/v3/combine-transactions/out-trade-no/{combine_out_trade_no}/close
     * 请求方式：POST
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     * </pre>
     * <p>
     * 没有数据，返回状态码204（一般情况）
     * <p>
     * 状态码	错误码	描述	解决方案
     * 202	USERPAYING	用户支付中，需要输入密码	等待5秒，然后调用被扫订单结果查询API，查询当前订单的不同状态，决定下一步的操作
     * 403	TRADE_ERROR	交易错误	因业务原因交易失败，请查看接口返回的详细信息
     * 500	SYSTEMERROR	系统错误	系统异常，请用相同参数重新调用
     * 401	SIGN_ERROR	签名错误	请检查签名参数和方法是否都符合签名算法要求
     * 403	RULELIMIT	业务规则限制	因业务规则限制请求频率，请查看接口返回的详细信息
     * 400	PARAM_ERROR	参数错误	请根据接口返回的详细信息检查请求参数
     * 403	OUT_TRADE_NO_USED	商户订单号重复	请核实商户订单号是否重复提交
     * 404	ORDERNOTEXIST	订单不存在	请检查订单是否发起过交易
     * 400	ORDER_CLOSED	订单已关闭	当前订单已关闭，请重新下单
     * 500	OPENID_MISMATCH	openid和appid不匹配	请确认openid和appid是否匹配
     * 403	NOTENOUGH	余额不足	用户帐号余额不足，请用户充值或更换支付卡后再支付
     * 403	NOAUTH	商户无权限	请商户前往申请此接口相关权限
     * 400	MCH_NOT_EXISTS	商户号不存在	请检查商户号是否正确
     * 500	INVALID_TRANSACTIONID	订单号非法	请检查微信支付订单号是否正确
     * 400	INVALID_REQUEST	无效请求	请根据接口返回的详细信息检查
     * 429	FREQUENCY_LIMITED	频率超限	请降低请求接口频率
     * 500	BANKERROR	银行系统异常	银行系统异常，请用相同参数重新调用
     * 400	APPID_MCHID_NOT_MATCH	appid和mch_id不匹配	请确认appid和mch_id是否匹配
     * 403	ACCOUNTERROR	账号异常	用户账号异常，无需更多操作
     *
     * @param request 请求对象
     */
    void combineTransactionsClose(CombineTransactionsCloseRequest request);

    /**
     * @param prePayId 预下单ID
     * @param appId    appID
     * @return 用于app调起支付的参数
     */
    Map<String, String> getAppPayParams(String prePayId, String appId);

    /**
     * @param prePayId 预下单ID
     * @param appId    appID
     * @return 用于js调起支付的参数
     */
    Map<String, String> getJsPayParams(String prePayId, String appId);

    /**
     * @param prePayId 预下单ID
     * @param appId    appID
     * @return 用于小程序调起支付的参数
     */
    Map<String, String> getSmallPayParams(String prePayId, String appId);

    /**
     * @param timeStamp 时间戳
     * @param nonce     随机串
     * @param body      请求实体
     * @param signed    已签名字符串
     * @param serialNo  证书序列号
     * @return 验签结果
     */
    boolean verifyNotifySign(String timeStamp, String nonce, String body, String signed, String serialNo);

    /**
     * 对通知的数据体进行解析
     *
     * @param notifyContent 消息通知内容
     * @return 支付信息
     */
    Optional<CombineTransactionsNotifyRequest1> parsePayNotify(String notifyContent);


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
     *
     * 适用对象：电商平台
     * 请求URL：https://api.mch.weixin.qq.com/v3/ecommerce/subsidies/create
     * 请求方式：POST
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     * </pre>
     *
     * @param request 请求对象
     * @return .
     */
    Optional<SubsidiesCreateResponse> subsidiesCreate(SubsidiesCreateRequest request);

    /**
     * <pre>
     * 请求补差回退API
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/subsidies/chapter3_2.shtml
     * 订单发送退款的时候，可以对补贴成功的补差单发起回退。
     * 注意：
     * • 补差回退以原补差单位依据，支持多次回退，申请回退总金额不能超过补差金额。
     * • 此接口采用同步处理模式，即在接收到商户请求后，会实时返回处理结果。
     * • 补差回退的前置条件是订单发生退款。
     * • 系统异常（如返回SYSTEM_ERROR），请使用相同参数稍后重新调用，请务必用原商户补差回退单号和原参数来重入此接口。
     *
     * 适用对象：电商平台
     * 请求URL：https://api.mch.weixin.qq.com/v3/ecommerce/subsidies/return
     * 请求方式：POST
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
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
     *
     * 适用对象：电商平台
     * 请求URL：https://api.mch.weixin.qq.com/v3/ecommerce/subsidies/cancel
     * 请求方式：POST
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     * </pre>
     *
     * @param request 请求对象
     * @return .
     */
    Optional<SubsidiesCancelResponse> subsidiesCancel(SubsidiesCancelRequest request);


    /**
     * <pre>
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/profitsharing/chapter3_1.shtml
     * 请求分账API
     * 最新更新时间：2020.2.27 版本说明
     *
     * 微信订单支付成功后，由电商平台发起分账请求，将结算后的资金分给分账接收方。
     *
     * 注意：
     * • 微信订单支付成功后，服务商代特约商户发起分账请求，将结算后的钱分到分账接收方。
     * • 对同一笔订单最多能发起20次分账请求，每次请求最多分给5个接收方。
     * • 此接口采用异步处理模式，即在接收到商户请求后，会先受理请求再异步处理，最终的分账结果可以通过查询分账接口获取。
     * 接口说明
     * 适用对象：电商平台
     * 请求URL：https://api.mch.weixin.qq.com/v3/ecommerce/profitsharing/orders
     * 请求方式：POST
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     *
     * path 指该参数需在请求URL传参
     * query 指该参数需在请求JSON传参
     * </pre>
     *
     * @param request 请求对象
     * @return .
     */
    Optional<ProfitSharingApplyResponse> applyProfitSharing(ProfitSharingApplyRequest request);

    /**
     * @return 平台的商户ID
     */
    Optional<String> getPlatformId();

    /**
     * <pre>
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/profitsharing/chapter3_2.shtml
     *
     * 返回上一层文档首页 / 电商收付通 / 分账 / 查询分账结果API
     * 查询分账结果API
     * 最新更新时间：2020.04.01 版本说明
     *
     * 发起分账请求后，可调用此接口查询分账结果 ;发起分账完结请求后，可调用此接口查询分账完结的结果
     * 接口说明
     * 适用对象：电商平台
     * 请求URL：https://api.mch.weixin.qq.com/v3/ecommerce/profitsharing/orders
     * 请求方式：GET
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     *
     * path 指该参数需在请求URL传参
     * query 指该参数需在请求JSON传参
     * </pre>
     *
     * @param request 请求对象
     * @return .
     */
    Optional<ProfitSharingQueryApplyResponse> queryProfitSharingStatus(ProfitSharingQueryApplyRequest request);

    /**
     * <pre>
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/profitsharing/chapter3_3.shtml
     * 请求分账回退API
     * 最新更新时间：2019.09.11 版本说明
     *
     * 订单已经分账，在退款时，可以先调此接口，将已分账的资金从分账接收方的账户回退给分账方，再发起退款。
     *
     * 注意：
     * • 分账回退以原分账单为依据，支持多次回退，申请回退总金额不能超过原分账单分给该接收方的金额。
     * • 此接口采用同步处理模式，即在接收到商户请求后，会实时返回处理结果。
     * • 此功能需要接收方在商户平台开启同意分账回退后，才能使用。
     * • 对同一笔分账单最多能发起20次分账回退请求。
     * 接口说明
     * 适用对象：电商平台
     * 请求URL：https://api.mch.weixin.qq.com/v3/ecommerce/profitsharing/returnorders
     * 请求方式：POST
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     *
     * path 指该参数需在请求URL传参
     * query 指该参数需在请求JSON传参
     * </pre>
     *
     * @param request 请求对象
     * @return .
     */
    Optional<ProfitSharingRefundResponse> refundProfitSharing(ProfitSharingRefundRequest request);

    /**
     * <pre>
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/profitsharing/chapter3_4.shtml
     * 查询分账回退结果API
     * 最新更新时间：2019.09.11 版本说明
     *
     * 商户需要核实回退结果，可调用此接口查询回退结果;如果分账回退接口返回状态为处理中，可调用此接口查询回退结果
     * 接口说明
     * 适用对象：电商平台
     * 请求URL：https://api.mch.weixin.qq.com/v3/ecommerce/profitsharing/returnorders
     * 请求方式：GET
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     *
     * path 指该参数需在请求URL传参
     * query 指该参数需在请求JSON传参
     * </pre>
     *
     * @param request 请求对象
     * @return .
     */
    Optional<ProfitSharingQueryRefundResponse> queryRefundProfitSharingStatus(ProfitSharingQueryRefundRequest request);

    /**
     * <pre>
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/profitsharing/chapter3_5.shtml
     * 完结分账API
     * 最新更新时间：2019.09.11 版本说明
     *
     * 不需要进行分账的订单，可直接调用本接口将订单的金额全部解冻给特约商户。
     *
     * 注意：
     * • 调用分账接口后，需要解冻剩余资金时，调用本接口将剩余的分账金额全部解冻给特约商户。
     * 接口说明
     * 适用对象：电商平台
     * 请求URL：https://api.mch.weixin.qq.com/v3/ecommerce/profitsharing/finish-order
     * 请求方式：POST
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     *
     * path 指该参数需在请求URL传参
     * query 指该参数需在请求JSON传参
     * </pre>
     *
     * @param request 请求对象
     * @return .
     */
    Optional<ProfitSharingFinishResponse> finishProfitSharing(ProfitSharingFinishRequest request);

    /**
     * <pre>
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/profitsharing/chapter3_7.shtml
     * 添加分账接收方API
     * 最新更新时间：2020.03.05 版本说明
     *
     * 1. 电商平台可通过此接口添加分账接收方，建立分账接收方列表。后续通过发起分账请求，将电商平台下的二级商户结算后的资金，分给分账接收方列表中具体的分账接收方。
     * 2. 添加的分账接收方统一都在电商平台维度进行管理，其他二级商户，均可向该分账接收方列表中的接收方进行分账，避免在二级商户维度重复维护。
     *
     * 接口说明
     * 适用对象：电商平台
     * 请求URL：https://api.mch.weixin.qq.com/v3/ecommerce/profitsharing/receivers/add
     * 请求方式：POST
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     *
     * path 指该参数需在请求URL传参
     * query 指该参数需在请求JSON传参
     * </pre>
     *
     * @param request 请求对象
     * @return .
     */
    Optional<ProfitSharingAddReceiverResponse> addReceiver(ProfitSharingAddReceiverRequest request);

    /**
     * <pre>
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/profitsharing/chapter3_8.shtml
     * 删除分账接收方API
     * 最新更新时间：2020.03.05 版本说明
     *
     * 电商平台发起删除分账接收方请求。删除后，不支持将电商平台下二级商户结算后的资金，分到该分账接收方。
     *
     * 接口说明
     * 适用对象：电商平台
     * 请求URL：https://api.mch.weixin.qq.com/v3/ecommerce/profitsharing/receivers/delete
     * 请求方式：POST
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     *
     * path 指该参数需在请求URL传参
     * query 指该参数需在请求JSON传参
     * </pre>
     *
     * @param request 请求对象
     * @return .
     */
    Optional<ProfitSharingRemoveReceiverResponse> removeReceiver(ProfitSharingRemoveReceiverRequest request);

    /**
     * <pre>
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/profitsharing/chapter3_6.shtml
     *
     * 返回上一层文档首页 / 电商收付通 / 分账 / 分账动账通知API
     * 分账动账通知API
     * 最新更新时间：2020.03.23 版本说明
     *
     * 1、此功能仅针对分账接收方。
     * 2、分账动账金额变动后，微信会把相关变动结果发送给需要实时关注的分账接收方。
     *
     * 注意：
     * 对后台通知交互时，如果微信收到应答不是成功或超时，微信认为通知失败，微信会通过一定的策略定期重新发起通知，尽可能提高通知的成功率，但微信不保证通知最终能成功
     *
     * • 同样的通知可能会多次发送给商户系统。商户系统必须能够正确处理重复的通知。 推荐的做法是，当商户系统收到通知进行处理时，先检查对应业务数据的状态，并判断该通知是否已经处理。如果未处理，则再进行处理；如果已处理，则直接返回结果成功。在对业务数据进行状态检查和处理之前，要采用数据锁进行并发控制，以避免函数重入造成的数据混乱。
     * • 如果在所有通知频率(4小时)后没有收到微信侧回调，商户应调用查询订单接口确认订单状态。
     *
     * 特别提醒：商户系统对于开启结果通知的内容一定要做签名验证，并校验通知的信息是否与商户侧的信息一致，防止数据泄漏导致出现“假通知”，造成资金损失。
     * 接口说明
     * 适用对象：直联商户电商服务商 服务商
     * 请求URL：该链接是通过[商户配置]提交service_notify_url设置，必须为https协议。如果链接无法访问，商户将无法接收到微信通知。 通知url必须为直接可访问的url，不能携带参数。示例： “https://pay.weixin.qq.com/wxpay/pay.action”
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     * 通知规则
     * 用户支付完成后，微信会把相关支付结果和用户信息发送给清算机构，清算机构需要接收处理后返回应答成功，然后继续给异步通知到下游从业机构。
     * 对后台通知交互时，如果微信收到应答不是成功或超时，微信认为通知失败，微信会通过一定的策略定期重新发起通知，尽可能提高通知的成功率，但微信不保证通知最终能成功。（通知频率为15s/15s/30s/3m/10m/20m/30m/30m/30m/60m/3h/3h/3h/6h/6h - 总计 24h4m）
     * 通知报文
     * 支付结果通知是以POST 方法访问商户设置的通知url，通知的数据以JSON 格式通过请求主体（BODY）传输。通知的数据包括了加密的支付结果详情。
     *
     * 下面详细描述对通知数据进行解密的流程：
     * 1、用商户平台上设置的APIv3密钥【微信商户平台—&gt;账户设置—&gt;API安全—&gt;设置APIv3密钥】，记为key。
     * 2、针对resource.algorithm中描述的算法（目前为AEAD_AES_256_GCM），取得对应的参数nonce和associated_data。
     * 3、使用key、nonce和associated_data，对数据密文resource.ciphertext进行解密，得到JSON形式的资源对象。
     *
     * 注： AEAD_AES_256_GCM算法的接口细节，请参考rfc5116。微信支付使用的密钥key长度为32个字节，随机串nonce长度12个字节，associated_data长度小于16个字节并可能为空。
     * </pre>
     *
     * @param request 请求对象
     * @return .
     */
    Optional<ProfitSharingNotifyRequest1> parseProfitsSharingNotify(ProfitSharingNotifyRequest request);

    /**
     * <pre>
     * 退款申请API
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/refunds/chapter3_1.shtml
     * 当交易发生之后一段时间内，由于买家或者卖家的原因需要退款时，卖家可以通过退款接口将支付款退还给买家，微信支付将在收到退款请求并且验证成功之后，按照退款规则将支付款按原路退到买家帐号上。
     *
     * 注意：
     * • 交易时间超过一年的订单无法提交退款。
     * • 微信支付退款支持单笔交易分多次退款，多次退款需要提交原支付订单的商户订单号和设置不同的退款单号。申请退款总金额不能超过订单金额。 一笔退款失败后重新提交，请不要更换退款单号，请使用原商户退款单号。
     * • 请求频率限制：150qps，即每秒钟正常的申请退款请求次数不超过150次，错误或无效请求频率限制：6qps，即每秒钟异常或错误的退款申请请求不超过6次。
     * • 每个支付订单的部分退款次数不能超过50次。
     *
     * 适用对象：电商平台
     * 请求URL：https://api.mch.weixin.qq.com/v3/ecommerce/refunds/apply
     * 请求方式：POST
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     * </pre>
     *
     * @param request 请求对象
     * @return .
     */
    Optional<RefundApplyResponse> refundApply(RefundApplyRequest request);

    /**
     * <pre>
     * 查询退款API
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/refunds/chapter3_2.shtml
     * 提交退款申请后，通过调用该接口查询退款状态。退款有一定延时，用零钱支付的退款20分钟内到账，银行卡支付的退款3个工作日后重新查询退款状态。
     *
     * 注意：
     * ● 退款查询API可按以下两种不同方式查询：
     *     1、通过微信支付退款单号查询退款；
     *     2、通过商户退款单号查询退款。
     * ● 两种不同查询方式返回结果相同
     *
     * 适用对象：电商平台
     * 请求URL：https://api.mch.weixin.qq.com/v3/ecommerce/refunds/id/{refund_id}
     * 请求方式：POST
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     * </pre>
     *
     * @param subMchid 二级商户
     * @param refundId 退款记录ID
     * @return .
     */
    Optional<RefundQueryStatusResponse> refundQueryById(String subMchid, String refundId);

    /**
     * <pre>
     * 查询退款API
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/refunds/chapter3_2.shtml
     * 提交退款申请后，通过调用该接口查询退款状态。退款有一定延时，用零钱支付的退款20分钟内到账，银行卡支付的退款3个工作日后重新查询退款状态。
     *
     * 注意：
     * ● 退款查询API可按以下两种不同方式查询：
     *     1、通过微信支付退款单号查询退款；
     *     2、通过商户退款单号查询退款。
     * ● 两种不同查询方式返回结果相同
     *
     * 适用对象：电商平台
     * 请求URL：https://api.mch.weixin.qq.com/v3/ecommerce/refunds/out-refund-no/{out_refund_no}
     * 请求方式：GET
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     * </pre>
     *
     * @param subMchid    二级商户
     * @param outRefundNo 退款记录单号
     * @return .
     */
    Optional<RefundQueryStatusResponse> refundQueryByNumber(String subMchid, String outRefundNo);

    /**
     * 对通知的数据体进行解析
     *
     * @param request 请求对象
     * @return 退款信息
     */
    Optional<RefundNotifyRequest1> parseRefundNotify(RefundNotifyRequest request);

    /**
     * <pre>
     * 查询二级商户账户实时余额API
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/amount/chapter3_1.shtml
     *
     * 电商服务商通过此接口可以查询二级商户账户余额信息。
     *
     * 注意：
     * • 电商平台可利用分账实现对二级商户的账期，不建议电商平台利用限制二级商户提现进行账期控制，特殊情况下商户可直接到微信支付进行提现，造成账期控制无效。
     * 接口说明
     * 适用对象：电商平台
     * 请求URL：https://api.mch.weixin.qq.com/v3/ecommerce/fund/balance/{sub_mchid}
     * 请求方式：GET
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     *
     * </pre>
     *
     * @param subMchid 电商平台二级商户号，由微信支付生成并下发。
     * @return .
     */
    Optional<AmountOnlineOfSubMchResponse> queryOnlineAmount(String subMchid);

    /**
     * <pre>
     * 查询二级商户账户日终余额API
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/amount/chapter3_2.shtml
     *
     * 电商服务商通过该接口可以查询二级商户指定日期当天24点的账户余额。
     *
     * 注意：
     * • 可查询90天内的日终余额。
     * 接口说明
     * 适用对象：电商平台
     * 请求URL：https://api.mch.weixin.qq.com/v3/ecommerce/fund/enddaybalance/{sub_mchid}
     * 请求方式：GET
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     *
     * </pre>
     *
     * @param subMchid 电商平台二级商户号，由微信支付生成并下发。
     * @param date     指定查询商户日终余额的日期 示例值：2019-08-17
     * @return .
     */
    Optional<AmountDayEndOfSubMchResponse> queryDayEndAmount(String subMchid, Date date);

    /**
     * <pre>
     * 查询电商平台账户实时余额API
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/amount/chapter3_3.shtml
     *
     * 电商平台可通过此接口可以查询本商户号的账号余额情况。
     *
     * 接口说明
     * 适用对象：电商平台
     * 请求URL：https://api.mch.weixin.qq.com/v3/merchant/fund/balance/{account_type}
     * 请求方式：GET
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     *
     * </pre>
     *
     * @param accountType 账户类型
     * @return .
     */
    Optional<AmountOnlineOfPlatformResponse> queryOnlineAmount(AccountType accountType);

    /**
     * <pre>
     * 查询电商平台账户日终余额API
     * 通过此接口可以查询本商户号指定日期当天24点的账户余额。。
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/amount/chapter3_4.shtml
     *
     * 注意：
     * • 可查询90天内的日终余额。
     * 接口说明
     * 适用对象：电商平台
     * 请求URL：https://api.mch.weixin.qq.com/v3/merchant/fund/dayendbalance/{account_type}
     * 请求方式：GET
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     *
     * </pre>
     *
     * @param accountType 账户类型
     * @param date        指定查询商户日终余额的日期 示例值：2019-08-17
     * @return .
     */
    Optional<AmountDayEndOfPlatformResponse> queryDayEndAmount(AccountType accountType, Date date);

    /**
     * <pre>
     * 账户余额提现API
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/fund/chapter3_2.shtml
     *
     * 电商平台通过余额提现API帮助二级商户发起账户余额提现申请，完成账户余额提现。
     *
     * 注意：
     * • 相同的“商户提现单号”+“二级商户商户号”可以提供20天内防重。
     * 接口说明
     * 适用对象：电商平台
     * 请求URL：https://api.mch.weixin.qq.com/v3/ecommerce/fund/withdraw
     * 请求方式：POST
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     * </pre>
     *
     * @param request 请求对象
     * @return .
     */
    Optional<WithdrawForSubMchResponse> withdraw(WithdrawForSubMchRequest request);

    /**
     * <pre>
     * 二级商户查询提现状态API
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/fund/chapter3_3.shtml
     *
     * 电商平台通过查询提现状态API查询二级商户提现单的提现结果。
     *
     * 注意：
     * • 支持查询一年内提现结果。
     *
     * 两种查询方式返回结果相同。
     *
     * 1、微信支付提现单号查询
     * 接口说明
     * 适用对象：电商平台
     * 请求URL：https://api.mch.weixin.qq.com/v3/ecommerce/fund/withdraw/{withdraw_id}
     * 请求方式：GET
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     *
     * </pre>
     *
     * @param subMchId   二级商户号
     * @param withdrawId 提现记录ID
     * @return .
     */
    Optional<WithdrawStatusForSubMchResponse> queryWithdrawStatus(String subMchId, String withdrawId);

    /**
     * <pre>
     * 二级商户查询提现状态API
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/fund/chapter3_3.shtml
     *
     * 电商平台通过查询提现状态API查询二级商户提现单的提现结果。
     *
     * 注意：
     * • 支持查询一年内提现结果。
     *
     * 两种查询方式返回结果相同。
     *
     * 2、商户提现单号查询
     * 接口说明
     * 适用对象：电商平台
     * 请求URL：https://api.mch.weixin.qq.com/v3/ecommerce/fund/withdraw/out-request-no/{out_request_no}
     * 请求方式：GET
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     *
     * </pre>
     *
     * @param subMchId     二级商户ID
     * @param outRequestNo 业务编号
     * @return .
     */
    Optional<WithdrawStatusForSubMchResponse> queryWithdrawStatusByOutNo(String subMchId, String outRequestNo);

    /**
     * <pre>
     * 电商平台提现API
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/fund/chapter3_5.shtml
     *
     * 电商平台通过该接口可将其平台的收入进行提现
     *
     * 注意：
     * • 只能在电商平台指定账户的可用余额中进行提现。
     * • 发起提现后如果微信支付正确返回了微信支付提现单号，查询状态需要隔日早上8点后进行。
     * • 查询结果可能存在延迟，提现发起后查询无单据并不代表没有发起提现，应以隔日查询结果为准判断单据是否存在。
     * • 查询结果中状态为INIT时并不代表一定未受理成功，需要等待7日后确定单据最终状态或者原单（所有请求参数保持不变）重入请求。
     * 接口说明
     * 适用对象：电商平台
     * 请求URL：https://api.mch.weixin.qq.com/v3/merchant/fund/withdraw
     * 请求方式：POST
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     *
     * </pre>
     *
     * @param request 请求对象
     * @return .
     */
    Optional<WithdrawForPlatformResponse> withdraw(WithdrawForPlatformRequest request);

    /**
     * <pre>
     * 电商平台查询提现状态API
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/fund/chapter3_6.shtml
     *
     * 电商平台通过该接口查询其提现结果
     *
     * 注意：
     * • 发起提现后如果微信支付正确返回了微信支付提现单号，查询状态需要隔日早上8点后进行。
     * • 查询结果可能存在延迟，提现发起后查询无单据并不代表没有发起提现，应以隔日查询结果为准判断单据是否存在。
     * • 查询结果中状态为INIT时并不代表一定未受理成功，需要等待7日后确定单据最终状态或者原单（所有请求参数保持不变）重入请求。
     * • 可查询90天内的提现数据，时间以微信支付提现单创建时间为准。
     *
     * 接口说明
     * 适用对象：电商平台
     * 请求URL：https://api.mch.weixin.qq.com/v3/merchant/fund/withdraw/out-request-no/{out_request_no}
     * 请求方式：GET
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     * </pre>
     *
     * @param outRequestNo 业务编号
     * @return .
     */
    Optional<WithdrawStatusForPlatformResponse> queryWithdrawStatus(String outRequestNo);

    /**
     * <pre>
     * 按日下载提现异常文件API
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/fund/chapter3_4.shtml
     *
     * 电商服务商按日查询并下载提现状态为异常的提现单，提现异常包括提现失败和银行退票。
     *
     * 注意：
     * • 每日09:00开始可以下载前一日的提现异常文件，支持下载90天内提现状态变为提现异常的提现单文件。
     * • 日期（date）字段为提现状态变为提现异常的日期。建议输入日期为昨日，每天定时（早上9点后）查询昨日是否有状态为异常的提现单。
     * • 电商服务商查询的结果包括电商服务商发起的提现和给电商二级商户发起的提现。
     *
     * • 同一笔提现的相同数据可能出现在不同日期。即提现单A的失败数据，可能同时出现在1日和3日。
     * • 历史数据如果有某一日遗漏，则系统会补入当天的账单中。例如：今日为2019-11-15日，发现2019-11-12日某数据遗漏，则该数据会补入2019-11-15日。
     * 接口说明
     * 适用对象：电商平台
     * 请求URL：https://api.mch.weixin.qq.com/v3/merchant/fund/withdraw/bill-type/{bill_type}
     * 请求方式：GET
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     * </pre>
     *
     * @param request 请求对象
     * @return 下载文件的地址
     */
    Optional<String> downloadWithdrawExceptionFile(WithdrawExceptionLogRequest request);

    /**
     * <pre>
     * 申请交易账单API
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/bill/chapter3_1.shtml
     *
     * 微信支付按天提供交易账单文件，商户可以通过该接口获取账单文件的下载地址。文件内包含交易相关的金额、时间、营销等信息，供商户核对订单、退款、银行到账等情况。
     *
     * 注意：
     * • 微信侧未成功下单的交易不会出现在对账单中。支付成功后撤销的交易会出现在对账单中，跟原支付单订单号一致；
     * • 对账单中涉及金额的字段单位为“元”；
     * • 对账单接口只能下载三个月以内的账单。
     * • 小微商户不单独提供对账单下载，如有需要，可在调取“下载对账单”API接口时不传sub_mch_id，获取服务商下全量电商二级商户（包括小微商户和非小微商户）的对账单。
     * 接口说明
     * 适用对象：电商平台 服务商 直连商户
     * 请求URL：https://api.mch.weixin.qq.com/v3/bill/tradebill
     * 请求方式：GET
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     * </pre>
     *
     * @param request 请求对象
     * @return 下载文件的地址
     */
    Optional<String> downloadTradeBill(BillOfTradeRequest request);

    /**
     * <pre>
     * 申请资金账单API
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/bill/chapter3_2.shtml
     *
     * 微信支付按天提供微信支付账户的资金流水账单文件，商户可以通过该接口获取账单文件的下载地址。文件内包含该账户资金操作相关的业务单号、收支金额、记账时间等信息，供商户进行核对。
     *
     * 注意：
     * • 资金账单中的数据反映的是商户微信支付账户资金变动情况；
     * • 对账单中涉及金额的字段单位为“元”。
     * 接口说明
     * 适用对象：电商平台 服务商 直连商户
     * 请求URL：https://api.mch.weixin.qq.com/v3/bill/fundflowbill
     * 请求方式：GET
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     * </pre>
     *
     * @param request 请求对象
     * @return 下载文件的地址
     */
    Optional<String> downloadTradeBill(BillOfFundFlowRequest request);

    /**
     * <pre>
     * 下载账单API
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/bill/chapter3_3.shtml
     * 下载账单API为通用接口，交易/资金账单都可以通过该接口获取到对应的账单。
     *
     * 注意：
     * • 账单文件的下载地址的有效时间为30s。
     * • 强烈建议商户将实际账单文件的哈希值和之前从接口获取到的哈希值进行比对，以确认数据的完整性。
     * 接口说明
     * 适用对象：电商平台 服务商 直连商户
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     *
     * </pre>
     *
     * @param downloadUrl 下载地址
     * @return .
     */
    InputStream downloadBillFile(String downloadUrl);

    /**
     * <pre>
     * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/tool/chapter3_1.shtml
     * 图片上传API
     * 最新更新时间：2019.09.09 版本说明
     * 部分微信支付业务指定商户需要使用图片上传 API来上报图片信息，从而获得必传参数的值：图片MediaID 。
     *
     * 接口说明
     * 适用对象：电商平台
     * 请求URL：https://api.mch.weixin.qq.com/v3/merchant/media/upload
     * 请求主体类型：multipart/form-data
     * 请求方式：POST
     * 接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
     *
     * path 指该参数需在请求URL传参
     * query 指该参数需在请求JSON传参
     * </pre>
     *
     * @param file 文件
     * @return .
     */
    Optional<ImageUploadResponse> uploadImage(File file) throws Exception;
}
