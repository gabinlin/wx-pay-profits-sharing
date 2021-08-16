package top.gabin.tools.response.ecommerce.refunds;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.gabin.tools.response.AbstractResponse;
import java.util.List;


/**
 * <pre>
 * 当交易发生之后一段时间内，由于买家或者卖家的原因需要退款时，卖家可以通过退款接口将支付款退还给买家，微信支付将在收到退款请求并且验证成功之后，按照退款规则将支付款按原路退到买家账号上。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/refunds/chapter3_1.shtml
 * 状态码	错误码	描述	解决方案
 * 500	SYSTEM_ERROR	接口返回错误	请不要更换商户退款单号，请使用相同参数再次调用API。
 * 404	RESOURCE_NOT_EXISTS	订单不存在	请检查订单号是否正确且是否已支付，未支付的订单不能发起退款
 * 400	PARAM_ERROR	参数错误	请求参数错误，请重新检查再调用退款申请
 * 429	FREQUENCY_LIMITED	频率限制	该笔退款未受理，请降低频率后重试
 * 403	NOT_ENOUGH	余额不足	此状态代表退款申请失败，商户可根据具体的错误提示做相应的处理。
 * 403	USER_ACCOUNT_ABNORMAL	退款请求失败	此状态代表退款申请失败，商户可自行处理退款。
 * 403	NO_AUTH	没有退款权限	此状态代表退款申请失败，请检查是否有退这笔订单的权限
 * 401	SIGN_ERROR	签名错误	请检查签名参数和方法是否都符合签名算法要求
 * 400	INVALID_REQUEST	请求参数符合参数格式，但不符合业务规则	此状态代表退款申请失败，商户可根据具体的错误提示做相应的处理。
 * 400	MCH_NOT_EXISTS	商户号不存在	请检查商户号是否正确
 * 403	REQUEST_BLOCKED	请求受阻	此状态代表退款申请失败，商户可根据具体的错误提示做相应的处理。
 * </pre>
 */
@Data
@EqualsAndHashCode
@JsonIgnoreProperties()
public class RefundApplyResponse extends AbstractResponse {
	/**
	 * <pre>
	 * 字段名：微信退款单号
	 * 变量名：refund_id
	 * 是否必填：是
	 * 类型：string[1,32]
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
	 * 类型：string[1,64]
	 * 描述：
	 *  商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔。 
	 *  示例值：1217752501201407033233368018 
	 * </pre>
	 */
	@JsonProperty(value = "out_refund_no")
	private String outRefundNo;

	/**
	 * <pre>
	 * 字段名：退款创建时间
	 * 变量名：create_time
	 * 是否必填：是
	 * 类型：string[1,64]
	 * 描述：
	 *  退款受理时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日13点29分35秒。 
	 *  示例值：2018-06-08T10:34:56+08:00 
	 * </pre>
	 */
	@JsonProperty(value = "create_time")
	private String createTime;

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
	 * 字段名：+优惠退款详情
	 * 变量名：promotion_detail
	 * 是否必填：否
	 * 类型：array
	 * 描述：
	 *  优惠退款功能信息，discount_refund>0时，返回该字段 
	 *  示例值：见示例 
	 * </pre>
	 */
	@JsonProperty(value = "promotion_detail")
	private List<PromotionDetail> promotionDetail;

	@EqualsAndHashCode
	@Data
	@JsonIgnoreProperties()
	public static class Amount {
		/**
		 * <pre>
		 * 字段名：退款金额
		 * 变量名：refund
		 * 是否必填：是
		 * 类型：int
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
		 * 类型：int
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
		 * 是否必填：是
		 * 类型：int
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
		 * 是否必填：是
		 * 类型：string[1,18]
		 * 描述：
		 *  符合ISO 4217标准的三位字母代码，目前只支持人民币：CNY 。
		 *  示例值：CNY 
		 * </pre>
		 */
		@JsonProperty(value = "currency")
		private String currency;

	}

	@EqualsAndHashCode
	@Data
	@JsonIgnoreProperties()
	public static class PromotionDetail {
		/**
		 * <pre>
		 * 字段名：券ID
		 * 变量名：promotion_id
		 * 是否必填：是
		 * 类型：string[1,32]
		 * 描述：
		 *  券或者立减优惠id。 
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
		 * 类型：string[1,32]
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
		 * 类型：string[1,32]
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
		 * 类型：int
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
		 * 类型：int
		 * 描述：
		 *  代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见《代金券或立减优惠》 。
		 *  示例值：100 
		 * </pre>
		 */
		@JsonProperty(value = "refund_amount")
		private Integer refundAmount;

	}

}