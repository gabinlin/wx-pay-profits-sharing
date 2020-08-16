package top.gabin.tools.response.ecommerce.profitsharing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.gabin.tools.response.AbstractResponse;
import java.util.List;


/**
 * <pre>
 * 发起分账请求后，可调用此接口查询分账结果 ;发起分账完结请求后，可调用此接口查询分账完结的结果
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/profitsharing/chapter3_2.shtml
 * 状态码	错误码	描述	解决方案
 * 500	SYSTEM_ERROR	系统错误	系统异常，请使用相同参数稍后重新调用
 * 400	PARAM_ERROR	商户号未设置	请使用正确的参数重新调用
 * 429	FREQUENCY_LIMITED	频率限制	请降低频率后重试
 * 404	RESOURCE_NOT_EXISTS	记录不存在	请检查请求的单号是否正确
 * </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties()
public class ProfitSharingQueryApplyResponse extends AbstractResponse {
	/**
	 * <pre>
	 * 字段名：二级商户号
	 * 变量名：sub_mchid
	 * 是否必填：是
	 * 类型：string[1,32]
	 * 描述：
	 *  分账出资的电商平台二级商户，填写微信支付分配的商户号。 
	 *  示例值：1900000109 
	 * </pre>
	 */
	@JsonProperty(value = "sub_mchid")
	private String subMchid;

	/**
	 * <pre>
	 * 字段名：微信订单号
	 * 变量名：transaction_id
	 * 是否必填：是
	 * 类型：string[1,32]
	 * 描述：
	 *  微信支付订单号。 
	 *  示例值： 4208450740201411110007820472 
	 * </pre>
	 */
	@JsonProperty(value = "transaction_id")
	private String transactionId;

	/**
	 * <pre>
	 * 字段名：商户分账单号
	 * 变量名：out_order_no
	 * 是否必填：是
	 * 类型：string[1,64]
	 * 描述：
	 *  商户系统内部的分账单号，在商户系统内部唯一（单次分账、多次分账、完结分账应使用不同的商户分账单号），同一分账单号多次请求等同一次。 
	 *  示例值：P20150806125346 
	 * </pre>
	 */
	@JsonProperty(value = "out_order_no")
	private String outOrderNo;

	/**
	 * <pre>
	 * 字段名：微信分账单号
	 * 变量名：order_id
	 * 是否必填：是
	 * 类型：string[1,64]
	 * 描述：
	 *  微信分账单号，微信系统返回的唯一标识 
	 *  示例值： 008450740201411110007820472 
	 * </pre>
	 */
	@JsonProperty(value = "order_id")
	private String orderId;

	/**
	 * <pre>
	 * 字段名：分账单状态
	 * 变量名：status
	 * 是否必填：是
	 * 类型：string[1,32]
	 * 描述：
	 *  分账单状态，枚举值： 
	 *  ACCEPTED：受理成功 
	 *  PROCESSING：处理中 
	 *  FINISHED：分账成功 
	 *  CLOSED：处理失败，已关单 
	 *  示例值：FINISHED 
	 * </pre>
	 */
	@JsonProperty(value = "status")
	private String status;

	/**
	 * <pre>
	 * 字段名：+分账接收方列表
	 * 变量名：receivers
	 * 是否必填：是
	 * 类型：array
	 * 描述：分账接收方列表。 
	 * </pre>
	 */
	@JsonProperty(value = "receivers")
	private List<Receivers> receivers;

	/**
	 * <pre>
	 * 字段名：关单原因
	 * 变量名：close_reason
	 * 是否必填：否
	 * 类型：string[1,32]
	 * 描述：
	 *  关单原因描述，枚举值： 
	 *  NO_AUTH：分账授权已解除 
	 *  示例值：NO_AUTH 
	 * </pre>
	 */
	@JsonProperty(value = "close_reason")
	private String closeReason;

	/**
	 * <pre>
	 * 字段名：分账完结金额
	 * 变量名：finish_amount
	 * 是否必填：否
	 * 类型：int
	 * 描述：
	 *  分账完结的分账金额，单位为分， 仅当查询分账完结的执行结果时，存在本字段。 
	 *  示例值：100 
	 * </pre>
	 */
	@JsonProperty(value = "finish_amount")
	private Integer finishAmount;

	/**
	 * <pre>
	 * 字段名：分账完结描述
	 * 变量名：finish_description
	 * 是否必填：否
	 * 类型：string[1,80]
	 * 描述：
	 *  分账完结的原因描述，仅当查询分账完结的执行结果时，存在本字段。 
	 *  示例值：分账完结 
	 * </pre>
	 */
	@JsonProperty(value = "finish_description")
	private String finishDescription;

	@EqualsAndHashCode
	@Data
	@JsonIgnoreProperties()
	public static class Receivers {
		/**
		 * <pre>
		 * 字段名：分账接收商户号
		 * 变量名：receiver_mchid
		 * 是否必填：是
		 * 类型：string[1,32]
		 * 描述：
		 *  填写微信支付分配的商户号，仅支持通过添加分账接收方接口添加的接收方；电商平台商户已默认添加到分账接收方，无需重复添加。 
		 *  示例值：1900000109 
		 * </pre>
		 */
		@JsonProperty(value = "receiver_mchid")
		private String receiverMchid;

		/**
		 * <pre>
		 * 字段名：分账金额
		 * 变量名：amount
		 * 是否必填：是
		 * 类型：int
		 * 描述：
		 *  分账金额，单位为分，只能为整数，不能超过原订单支付金额及最大分账比例金额。 
		 *  示例值： 4208450740201411110007820472 
		 * </pre>
		 */
		@JsonProperty(value = "amount")
		private Integer amount;

		/**
		 * <pre>
		 * 字段名：分账描述
		 * 变量名：description
		 * 是否必填：是
		 * 类型：string[1,80]
		 * 描述：
		 *  分账的原因描述，分账账单中需要体现。 
		 *  示例值：分帐1900000110 
		 * </pre>
		 */
		@JsonProperty(value = "description")
		private String description;

		/**
		 * <pre>
		 * 字段名：分账结果
		 * 变量名：result
		 * 是否必填：是
		 * 类型：string[1,32]
		 * 描述：
		 *  分账结果，枚举值： 
		 *  PENDING：待分账 
		 *  SUCCESS：分账成功 
		 *  ADJUST：分账失败待调账 
		 *  RETURNED：已转回分账方 
		 *  CLOSED：已关闭 
		 *  示例值：SUCCESS 
		 * </pre>
		 */
		@JsonProperty(value = "result")
		private String result;

		/**
		 * <pre>
		 * 字段名：完成时间
		 * 变量名：finish_time
		 * 是否必填：是
		 * 类型：string[1,64]
		 * 描述：
		 *  分账完成时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.120+08:00表示，北京时间2015年5月20日 13点29分35秒。
		 *  示例值： 2015-05-20T13:29:35.120+08:00 
		 * </pre>
		 */
		@JsonProperty(value = "finish_time")
		private String finishTime;

		/**
		 * <pre>
		 * 字段名：分账失败原因
		 * 变量名：fail_reason
		 * 是否必填：否
		 * 类型：string[1,32]
		 * 描述：
		 *  分账失败原因，枚举值：
		 *  ACCOUNT_ABNORMAL：分账接收账户异常 
		 *  NO_RELATION：分账关系已解除 
		 *  RECEIVER_HIGH_RISK：高风险接收方
		 *  示例值：NO_RELATION 
		 * </pre>
		 */
		@JsonProperty(value = "fail_reason")
		private String failReason;

		/**
		 * <pre>
		 * 字段名：分账接收方类型
		 * 变量名：type
		 * 是否必填：是
		 * 类型：string[1,32]
		 * 描述：
		 *  分账接收方类型，枚举值： 
		 *  MERCHANT_ID：商户
		 *  PERSONAL_OPENID：个人
		 *  示例值：MERCHANT_ID 
		 * </pre>
		 */
		@JsonProperty(value = "type")
		private String type;

		/**
		 * <pre>
		 * 字段名：分账接收方账号
		 * 变量名：receiver_account
		 * 是否必填：是
		 * 类型：string[1,64]
		 * 描述：
		 *  分账接收方账号：
		 *  类型是MERCHANT_ID时，是商户ID
		 *  类型是PERSONAL_OPENID时，是个人openid 
		 *  示例值：1900000109 
		 * </pre>
		 */
		@JsonProperty(value = "receiver_account")
		private String receiverAccount;

	}

}