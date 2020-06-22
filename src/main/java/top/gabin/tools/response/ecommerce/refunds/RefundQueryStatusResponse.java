package top.gabin.tools.response.ecommerce.refunds;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import top.gabin.tools.response.AbstractResponse;

import java.util.List;


/**
 * <pre>
 * 提交退款申请后，通过调用该接口查询退款状态。该查询服务提供两种查询方式（两种查询方式返回结果一致）： 方式1：通过微信支付退款单号查询退款； 方式2：通过商户退款单号查询退款。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/refunds/chapter3_2.shtml
 * 状态码	错误码	描述	解决方案
 * 500	SYSTEM_ERROR	接口返回错误	请不要更换商户退款单号，请使用相同参数再次调用API。
 * 404	RESOURCE_NOT_EXISTS	订单不存在	请检查订单号是否正确且是否已支付，未支付的订单不能发起退款
 * 400	PARAM_ERROR	参数错误	请求参数错误，请重新检查再调用退款申请
 * 429	FREQUENCY_LIMITED	频率限制	该笔退款未受理，请降低频率后重试
 * 403	NO_AUTH	没有退款权限	此状态代表退款申请失败，请检查是否有退这笔订单的权限
 * 401	SIGN_ERROR	签名错误	请检查签名参数和方法是否都符合签名算法要求
 * 400	INVALID_REQUEST	请求参数符合参数格式，但不符合业务规则	此状态代表退款申请失败，商户可根据具体的错误提示做相应的处理。
 * 400	MCH_NOT_EXISTS	商户号不存在	请检查商户号是否正确
 * 403	REQUEST_BLOCKED	请求受阻	此状态代表退款申请失败，商户可根据具体的错误提示做相应的处理。
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RefundQueryStatusResponse extends AbstractResponse {
	/**
	 * <pre>
	 * 字段名：微信退款单号
	 * 变量名：refund_id
	 * 是否必填：是
	 * 类型：string(32)
	 * 描述：
	 *  微信支付退款订单号。
	 *  示例值：1217752501201407033233368018 
	 * </pre>
	 */
	@JsonProperty(value = "refund_id")
	private String refundId;

	/**
	 * <pre>
	 * 字段名：商户退款单号
	 * 变量名：out_refund_no
	 * 是否必填：是
	 * 类型：string(64)
	 * 描述：
	 *  商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔。 
	 *  示例值：1217752501201407033233368018 
	 * </pre>
	 */
	@JsonProperty(value = "out_refund_no")
	private String outRefundNo;

	/**
	 * <pre>
	 * 字段名：微信订单号
	 * 变量名：transaction_id
	 * 是否必填：是
	 * 类型：string(32)
	 * 描述：
	 *  微信支付交易订单号。 
	 *  示例值： 1217752501201407033233368018 
	 * </pre>
	 */
	@JsonProperty(value = "transaction_id")
	private String transactionId;

	/**
	 * <pre>
	 * 字段名：商户订单号
	 * 变量名：out_trade_no
	 * 是否必填：是
	 * 类型：string(32)
	 * 描述：
	 *  返回的原交易订单号。 
	 *  示例值： 1217752501201407033233368018 
	 * </pre>
	 */
	@JsonProperty(value = "out_trade_no")
	private String outTradeNo;

	/**
	 * <pre>
	 * 字段名：退款渠道
	 * 变量名：channel
	 * 是否必填：否
	 * 类型：string(16)
	 * 描述：
	 *  ORIGINAL：原路退款
	 *  BALANCE：退回到余额
	 *  OTHER_BALANCE：原账户异常退到其他余额账户
	 *  OTHER_BANKCARD：原银行卡异常退到其他银行卡 
	 *  示例值： ORIGINAL 
	 * </pre>
	 */
	@JsonProperty(value = "channel")
	private String channel;

	/**
	 * <pre>
	 * 字段名：退款入账账户
	 * 变量名：user_received_account
	 * 是否必填：否
	 * 类型：string(64)
	 * 描述：
	 *  取当前退款单的退款入账方。
	 *  退回银行卡：{银行名称}{卡类型}{卡尾号}
	 *  退回支付用户零钱：支付用户零钱
	 *  退还商户：商户基本账户、商户结算银行账户
	 *  退回支付用户零钱通：支付用户零钱通 
	 *  示例值： 招商银行信用卡0403 
	 * </pre>
	 */
	@JsonProperty(value = "user_received_account")
	private String userReceivedAccount;

	/**
	 * <pre>
	 * 字段名：退款成功时间
	 * 变量名：success_time
	 * 是否必填：否
	 * 类型：string(64)
	 * 描述：
	 *  退款成功时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日13点29分35秒。
	 *  示例值： 2018-06-08T10:34:56+08:00 
	 * </pre>
	 */
	@JsonProperty(value = "success_time")
	private String successTime;

	/**
	 * <pre>
	 * 字段名：退款创建时间
	 * 变量名：create_time
	 * 是否必填：是
	 * 类型：string(64)
	 * 描述：
	 *  1、退款受理时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日13点29分35秒。
	 *  2、当退款状态为退款成功时返回此字段。
	 *  示例值：2018-06-08T10:34:56+08:00 
	 * </pre>
	 */
	@JsonProperty(value = "create_time")
	private String createTime;

	/**
	 * <pre>
	 * 字段名：退款状态
	 * 变量名：status
	 * 是否必填：是
	 * 类型：string(16)
	 * 描述：
	 *  退款状态，枚举值：
	 *  SUCCESS：退款成功
	 *  REFUNDCLOSE：退款关闭 
	 *  PROCESSING：退款处理中
	 *  ABNORMAL：退款异常，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，可前往【服务商平台—>交易中心】，手动处理此笔退款 
	 *  示例值：SUCCESS 
	 * </pre>
	 */
	@JsonProperty(value = "status")
	private String status;

	/**
	 * <pre>
	 * 字段名：+退款金额信息
	 * 变量名：amount
	 * 是否必填：是
	 * 类型：object
	 * 描述：订单退款金额信息 
	 * </pre>
	 */
	@JsonProperty(value = "amount")
	private Amount amount;

	/**
	 * <pre>
	 * 字段名：+营销详情
	 * 变量名：promotion_detail
	 * 是否必填：否
	 * 类型：array
	 * 描述：优惠退款信息 
	 * </pre>
	 */
	@JsonProperty(value = "promotion_detail")
	private List<PromotionDetail> promotionDetail;

	public String getRefundId() {
		return this.refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public String getOutRefundNo() {
		return this.outRefundNo;
	}

	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}

	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getOutTradeNo() {
		return this.outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getUserReceivedAccount() {
		return this.userReceivedAccount;
	}

	public void setUserReceivedAccount(String userReceivedAccount) {
		this.userReceivedAccount = userReceivedAccount;
	}

	public String getSuccessTime() {
		return this.successTime;
	}

	public void setSuccessTime(String successTime) {
		this.successTime = successTime;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Amount getAmount() {
		return this.amount;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
	}

	public List<PromotionDetail> getPromotionDetail() {
		return this.promotionDetail;
	}

	public void setPromotionDetail(List<PromotionDetail> promotionDetail) {
		this.promotionDetail = promotionDetail;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Amount {
		/**
		 * <pre>
		 * 字段名：退款金额
		 * 变量名：refund
		 * 是否必填：是
		 * 类型：int(64)
		 * 描述：
		 *  退款金额，币种的最小单位，只能为整数，不能超过原订单支付金额。 
		 *  示例值：888 
		 * </pre>
		 */
		@JsonProperty(value = "refund")
		private Integer refund;

		/**
		 * <pre>
		 * 字段名：用户退款金额
		 * 变量名：payer_refund
		 * 是否必填：是
		 * 类型：int(64)
		 * 描述：
		 *  退款给用户的金额，不包含所有优惠券金额。 
		 *  示例值：888 
		 * </pre>
		 */
		@JsonProperty(value = "payer_refund")
		private Integer payerRefund;

		/**
		 * <pre>
		 * 字段名：优惠退款金额
		 * 变量名：discount_refund
		 * 是否必填：否
		 * 类型：int(64)
		 * 描述：
		 *  优惠券的退款金额，原支付单的优惠按比例退款。 
		 *  示例值：888 
		 * </pre>
		 */
		@JsonProperty(value = "discount_refund")
		private Integer discountRefund;

		/**
		 * <pre>
		 * 字段名：退款币种
		 * 变量名：currency
		 * 是否必填：否
		 * 类型：string(18)
		 * 描述：
		 *  符合ISO 4217标准的三位字母代码，目前只支持人民币：CNY 。
		 *  示例值： CNY 
		 * </pre>
		 */
		@JsonProperty(value = "currency")
		private String currency;

		public Integer getRefund() {
			return this.refund;
		}

		public void setRefund(Integer refund) {
			this.refund = refund;
		}

		public Integer getPayerRefund() {
			return this.payerRefund;
		}

		public void setPayerRefund(Integer payerRefund) {
			this.payerRefund = payerRefund;
		}

		public Integer getDiscountRefund() {
			return this.discountRefund;
		}

		public void setDiscountRefund(Integer discountRefund) {
			this.discountRefund = discountRefund;
		}

		public String getCurrency() {
			return this.currency;
		}

		public void setCurrency(String currency) {
			this.currency = currency;
		}

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class PromotionDetail {
		/**
		 * <pre>
		 * 字段名：券ID
		 * 变量名：promotion_id
		 * 是否必填：是
		 * 类型：string(32)
		 * 描述：
		 *  券或者立减优惠id 。
		 *  示例值：109519 
		 * </pre>
		 */
		@JsonProperty(value = "promotion_id")
		private String promotionId;

		/**
		 * <pre>
		 * 字段名：优惠范围
		 * 变量名：scope
		 * 是否必填：是
		 * 类型：string(32)
		 * 描述：
		 *  枚举值：
		 *  GLOBAL：全场代金券
		 *  SINGLE：单品优惠 
		 *  示例值：SINGLE 
		 * </pre>
		 */
		@JsonProperty(value = "scope")
		private String scope;

		/**
		 * <pre>
		 * 字段名：优惠类型
		 * 变量名：type
		 * 是否必填：是
		 * 类型：string(32)
		 * 描述：
		 *  枚举值：
		 *  COUPON：充值型代金券，商户需要预先充值营销经费
		 *  DISCOUNT：免充值型优惠券，商户不需要预先充值营销经费 
		 *  示例值：DISCOUNT 
		 * </pre>
		 */
		@JsonProperty(value = "type")
		private String type;

		/**
		 * <pre>
		 * 字段名：优惠券面额
		 * 变量名：amount
		 * 是否必填：是
		 * 类型：int64
		 * 描述：
		 *  用户享受优惠的金额（优惠券面额=微信出资金额+商家出资金额+其他出资方金额 ）。 
		 *  示例值：5 
		 * </pre>
		 */
		@JsonProperty(value = "amount")
		private Integer amount;

		/**
		 * <pre>
		 * 字段名：优惠退款金额
		 * 变量名：refund_amount
		 * 是否必填：是
		 * 类型：int64
		 * 描述：
		 *  代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见《代金券或立减优惠》。
		 *  示例值：100 
		 * </pre>
		 */
		@JsonProperty(value = "refund_amount")
		private Integer refundAmount;

		public String getPromotionId() {
			return this.promotionId;
		}

		public void setPromotionId(String promotionId) {
			this.promotionId = promotionId;
		}

		public String getScope() {
			return this.scope;
		}

		public void setScope(String scope) {
			this.scope = scope;
		}

		public String getType() {
			return this.type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public Integer getAmount() {
			return this.amount;
		}

		public void setAmount(Integer amount) {
			this.amount = amount;
		}

		public Integer getRefundAmount() {
			return this.refundAmount;
		}

		public void setRefundAmount(Integer refundAmount) {
			this.refundAmount = refundAmount;
		}

	}

}