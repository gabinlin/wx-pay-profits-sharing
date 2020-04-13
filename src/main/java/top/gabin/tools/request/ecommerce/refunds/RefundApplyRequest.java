package top.gabin.tools.request.ecommerce.refunds;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * <pre>
 * 当交易发生之后一段时间内，由于买家或者卖家的原因需要退款时，卖家可以通过退款接口将支付款退还给买家，微信支付将在收到退款请求并且验证成功之后，按照退款规则将支付款按原路退到买家帐号上。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/refunds/chapter3_1.shtml
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RefundApplyRequest {
	/**
	 * <pre>
	 * 字段名：二级商户号
	 * 变量名：sub_mchid
	 * 是否必填：是
	 * 类型：string(32)
	 * 描述：
	 *  微信支付分配二级商户的商户号。
	 *  示例值： 1900000109 
	 * </pre>
	 */
	@JsonProperty(value = "sub_mchid")
	private String subMchid;

	/**
	 * <pre>
	 * 字段名：电商平台APPID
	 * 变量名：sp_appid
	 * 是否必填：是
	 * 类型：string(32)
	 * 描述：
	 *  电商平台在微信公众平台申请服务号对应的APPID，申请商户功能的时候微信支付会配置绑定关系。 
	 *  示例值：wx8888888888888888 
	 * </pre>
	 */
	@JsonProperty(value = "sp_appid")
	private String spAppid;

	/**
	 * <pre>
	 * 字段名：二级商户APPID
	 * 变量名：sub_appid
	 * 是否必填：否
	 * 类型：string(32)
	 * 描述：
	 *  二级商户在微信申请公众号成功后分配的帐号ID，需要电商平台侧配置绑定关系才能传参。 
	 *  示例值：wx8888888888888888 
	 * </pre>
	 */
	@JsonProperty(value = "sub_appid")
	private String subAppid;

	/**
	 * <pre>
	 * 字段名：微信订单号
	 * 变量名：transaction_id
	 * 是否必填：否
	 * 类型：string(32)
	 * 描述：
	 *  原支付交易对应的微信订单号。 
	 *  示例值：1217752501201407033233368018 
	 * </pre>
	 */
	@JsonProperty(value = "transaction_id")
	private String transactionId;

	/**
	 * <pre>
	 * 字段名：商户订单号
	 * 变量名：out_trade_no
	 * 是否必填：否
	 * 类型：string(32)
	 * 描述：
	 *  原支付交易对应的商户订单号。
	 *  示例值：1217752501201407033233368018 
	 * </pre>
	 */
	@JsonProperty(value = "out_trade_no")
	private String outTradeNo;

	/**
	 * <pre>
	 * 字段名：商户退款单号
	 * 变量名：out_refund_no
	 * 是否必填：是
	 * 类型：string(64)
	 * 描述：
	 *  商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@，同一退款单号多次请求只退一笔。 
	 *  示例值：1217752501201407033233368018 
	 * </pre>
	 */
	@JsonProperty(value = "out_refund_no")
	private String outRefundNo;

	/**
	 * <pre>
	 * 字段名：退款原因
	 * 变量名：reason
	 * 是否必填：否
	 * 类型：string(80)
	 * 描述：
	 *  若商户传入，会在下发给用户的退款消息中体现退款原因。 
	 *  注意：若订单退款金额≤1元，且属于部分退款，则不会在退款消息中体现退款原因 
	 *  示例值：商品已售完 
	 * </pre>
	 */
	@JsonProperty(value = "reason")
	private String reason;

	/**
	 * <pre>
	 * 字段名：+订单金额
	 * 变量名：amount
	 * 是否必填：是
	 * 类型：object
	 * 描述：订单金额信息 
	 * </pre>
	 */
	@JsonProperty(value = "amount")
	private Amount amount;

	/**
	 * <pre>
	 * 字段名：退款结果回调url
	 * 变量名：notify_url
	 * 是否必填：否
	 * 类型：string(256)
	 * 描述：
	 *  异步接收微信支付退款结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。 如果参数中传了notify_url，则商户平台上配置的回调地址将不会生效，优先回调当前传的地址。 
	 *  示例值：https://weixin.qq.com 
	 * </pre>
	 */
	@JsonProperty(value = "notify_url")
	private String notifyUrl;

	public String getSubMchid() {
		return this.subMchid;
	}

	public void setSubMchid(String subMchid) {
		this.subMchid = subMchid;
	}

	public String getSpAppid() {
		return this.spAppid;
	}

	public void setSpAppid(String spAppid) {
		this.spAppid = spAppid;
	}

	public String getSubAppid() {
		return this.subAppid;
	}

	public void setSubAppid(String subAppid) {
		this.subAppid = subAppid;
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

	public String getOutRefundNo() {
		return this.outRefundNo;
	}

	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Amount getAmount() {
		return this.amount;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
	}

	public String getNotifyUrl() {
		return this.notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
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
		private String refund;

		/**
		 * <pre>
		 * 字段名：原订单金额
		 * 变量名：total
		 * 是否必填：是
		 * 类型：int(64)
		 * 描述：
		 *  原支付交易的订单总金额，币种的最小单位，只能为整数。 
		 *  示例值：888 
		 * </pre>
		 */
		@JsonProperty(value = "total")
		private String total;

		/**
		 * <pre>
		 * 字段名：退款币种
		 * 变量名：currency
		 * 是否必填：否
		 * 类型：string(18)
		 * 描述：
		 *  符合ISO 4217标准的三位字母代码，目前只支持人民币：CNY。 
		 *  示例值：CNY 
		 * </pre>
		 */
		@JsonProperty(value = "currency")
		private String currency;

		public String getRefund() {
			return this.refund;
		}

		public void setRefund(String refund) {
			this.refund = refund;
		}

		public String getTotal() {
			return this.total;
		}

		public void setTotal(String total) {
			this.total = total;
		}

		public String getCurrency() {
			return this.currency;
		}

		public void setCurrency(String currency) {
			this.currency = currency;
		}

	}

}