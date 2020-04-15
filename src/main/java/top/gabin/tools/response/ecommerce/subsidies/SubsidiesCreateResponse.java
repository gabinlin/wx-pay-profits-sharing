package top.gabin.tools.response.ecommerce.subsidies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import top.gabin.tools.response.AbstractResponse;


/**
 * <pre>
 * 服务商下单的时候带上补差标识，微信订单支付成功并结算完成后，发起分账前，调用该口进行补差。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/subsidies/chapter3_1.shtml
 * 状态码	错误码	描述	解决方案
 * 500	SYSTEM_ERROR	系统错误	系统异常，请使用相同参数稍后重新调用
 * 400	PARAM_ERROR	参数错误	请使用正确的参数重新调用
 * 400	INVALID_REQUEST	参数错误	请使用正确的参数重新调用
 * 429	FREQUENCY_LIMITED	频率限制	请降低频率后重试
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubsidiesCreateResponse extends AbstractResponse {
	/**
	 * <pre>
	 * 字段名：二级商户号
	 * 变量名：sub_mchid
	 * 是否必填：是
	 * 类型：string(32)
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
	 * 类型：string(64)
	 * 描述：
	 *  微信支付订单号。 
	 *  示例值： 4208450740201411110007820472 
	 * </pre>
	 */
	@JsonProperty(value = "transaction_id")
	private String transactionId;

	/**
	 * <pre>
	 * 字段名：微信补差单号
	 * 变量名：subsidy_id
	 * 是否必填：是
	 * 类型：string(64)
	 * 描述：
	 *  微信补差单号，微信系统返回的唯一标识。 
	 *  示例值： 3008450740201411110007820472 
	 * </pre>
	 */
	@JsonProperty(value = "subsidy_id")
	private String subsidyId;

	/**
	 * <pre>
	 * 字段名：补差描述
	 * 变量名：description
	 * 是否必填：是
	 * 类型：string(80)
	 * 描述：
	 *  补差描述 
	 *  示例值：满减补差活动 
	 * </pre>
	 */
	@JsonProperty(value = "description")
	private String description;

	/**
	 * <pre>
	 * 字段名：补差金额
	 * 变量名：amount
	 * 是否必填：否
	 * 类型：int
	 * 描述：
	 *  补差金额 
	 *  示例值：10 
	 * </pre>
	 */
	@JsonProperty(value = "amount")
	private String amount;

	/**
	 * <pre>
	 * 字段名：补差单结果
	 * 变量名：result
	 * 是否必填：是
	 * 类型：enum
	 * 描述：
	 *  补差单状态，枚举值： 
	 *  SUCCESS：补差成功 
	 *  FAIL：补差失败 
	 *  REFUND：已全额回退 
	 *  示例值：SUCCESS 
	 * </pre>
	 */
	@JsonProperty(value = "result")
	private String result;

	/**
	 * <pre>
	 * 字段名：补差完成时间
	 * 变量名：success_time
	 * 是否必填：是
	 * 类型：string(32)
	 * 描述：
	 *  补贴完成时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss:sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss:sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日13点29分35秒。 
	 *  示例值： 2015-05-20T13:29:35.120+08:00 
	 * </pre>
	 */
	@JsonProperty(value = "success_time")
	private String successTime;

	public String getSubMchid() {
		return this.subMchid;
	}

	public void setSubMchid(String subMchid) {
		this.subMchid = subMchid;
	}

	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getSubsidyId() {
		return this.subsidyId;
	}

	public void setSubsidyId(String subsidyId) {
		this.subsidyId = subsidyId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getSuccessTime() {
		return this.successTime;
	}

	public void setSuccessTime(String successTime) {
		this.successTime = successTime;
	}

}