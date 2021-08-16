package top.gabin.tools.response.ecommerce.subsidies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.gabin.tools.response.AbstractResponse;

/**
 * <pre>
 * 订单发送退款的时候，可以对补贴成功的补差单发起回退。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/subsidies/chapter3_2.shtml
 * 状态码	错误码	描述	解决方案
 * 500	SYSTEM_ERROR	系统错误	系统异常，请使用相同参数稍后重新调用
 * 400	PARAM_ERROR	参数错误	请使用正确的参数重新调用
 * 400	INVALID_REQUEST	参数错误	请使用正确的参数重新调用
 * 429	FREQUENCY_LIMITED	频率限制	请降低频率后重试
 * </pre>
 */
@Data
@EqualsAndHashCode
@JsonIgnoreProperties()
public class SubsidiesRefundResponse extends AbstractResponse {
	/**
	 * <pre>
	 * 字段名：二级商户号
	 * 变量名：sub_mchid
	 * 是否必填：是
	 * 类型：string[1,32]
	 * 描述：
	 *  补差的电商平台二级商户，填写微信支付分配的商户号。 
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
	 * 类型：string[1,64]
	 * 描述：
	 *  微信支付订单号。 
	 *  示例值： 4208450740201411110007820472 
	 * </pre>
	 */
	@JsonProperty(value = "transaction_id")
	private String transactionId;

	/**
	 * <pre>
	 * 字段名：微信补差回退单号
	 * 变量名：subsidy_refund_id
	 * 是否必填：是
	 * 类型：string[1,64]
	 * 描述：
	 *  微信补差回退单号，微信补差回退系统返回的唯一标识。 
	 *  示例值： 3008450740201411110007820472 
	 * </pre>
	 */
	@JsonProperty(value = "subsidy_refund_id")
	private String subsidyRefundId;

	/**
	 * <pre>
	 * 字段名：微信退款单号
	 * 变量名：refund_id
	 * 是否必填：是
	 * 类型：string[1,64]
	 * 描述：
	 *  微信退款单号，微信系统退款返回的唯一标识。 
	 *  示例值： 3008450740201411110007820472 
	 * </pre>
	 */
	@JsonProperty(value = "refund_id")
	private String refundId;

	/**
	 * <pre>
	 * 字段名：商户补差回退单号
	 * 变量名：out_order_no
	 * 是否必填：是
	 * 类型：string[1,64]
	 * 描述：
	 *  商户系统内部的补差回退单号，在商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一补差回退单号多次请求等同一次。
	 *  示例值：P20150806125346 
	 * </pre>
	 */
	@JsonProperty(value = "out_order_no")
	private String outOrderNo;

	/**
	 * <pre>
	 * 字段名：补差回退金额
	 * 变量名：amount
	 * 是否必填：是
	 * 类型：int
	 * 描述：
	 *  补差回退金额 
	 *  示例值：10 
	 * </pre>
	 */
	@JsonProperty(value = "amount")
	private Integer amount;

	/**
	 * <pre>
	 * 字段名：补差回退描述
	 * 变量名：description
	 * 是否必填：是
	 * 类型：string[1,80]
	 * 描述：
	 *  补差回退描述 
	 *  示例值：测试备注 
	 * </pre>
	 */
	@JsonProperty(value = "description")
	private String description;

	/**
	 * <pre>
	 * 字段名：补差回退结果
	 * 变量名：result
	 * 是否必填：是
	 * 类型：string[1,16]
	 * 描述：
	 *  补差回退结果，枚举值： 
	 *  SUCCESS：成功 
	 *  FAIL：失败 
	 *  示例值：SUCCESS 
	 * </pre>
	 */
	@JsonProperty(value = "result")
	private String result;

	/**
	 * <pre>
	 * 字段名：补差回退完成时间
	 * 变量名：success_time
	 * 是否必填：是
	 * 类型：string[1,32]
	 * 描述：
	 *  补差回退完成时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日13点29分35秒。 
	 *  示例值：2015-05-20T13:29:35.120+08:00 
	 * </pre>
	 */
	@JsonProperty(value = "success_time")
	private String successTime;

}