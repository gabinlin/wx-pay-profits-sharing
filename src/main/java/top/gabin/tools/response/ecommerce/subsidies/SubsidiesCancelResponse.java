package top.gabin.tools.response.ecommerce.subsidies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * <pre>
 * 对带有补差标识的订单，如果不需要补差，可在发起发起分账前，可调用这个接口进行取消补差。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/subsidies/chapter3_3.shtml
 * 状态码	错误码	描述	解决方案
 * 500	SYSTEM_ERROR	系统错误	系统异常，请使用相同参数稍后重新调用
 * 400	PARAM_ERROR	参数错误	请使用正确的参数重新调用
 * 400	INVALID_REQUEST	参数错误	请使用正确的参数重新调用
 * 429	FREQUENCY_LIMITED	频率限制	请降低频率后重试
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubsidiesCancelResponse {
	/**
	 * <pre>
	 * 字段名：二级商户号
	 * 变量名：sub_mchid
	 * 是否必填：是
	 * 类型：string(32)
	 * 描述：
	 *  补差的电商平台二级商户，填写微信支付分配的商户号。 
	 *  示例值：1900013401 
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
	 * 字段名：微信退款单号
	 * 变量名：refund_id
	 * 是否必填：否
	 * 类型：string(64)
	 * 描述：
	 *  微信退款单号，微信系统退款返回的唯一标识。 
	 *  示例值：3008450740201411110007820472 
	 * </pre>
	 */
	@JsonProperty(value = "refund_id")
	private String refundId;

	/**
	 * <pre>
	 * 字段名：取消补差结果
	 * 变量名：result
	 * 是否必填：是
	 * 类型：enum
	 * 描述：
	 *  取消补差结果，枚举值： 
	 *  SUCCESS：成功 
	 *  FAIL：失败 
	 *  示例值：SUCCESS 
	 * </pre>
	 */
	@JsonProperty(value = "result")
	private String result;

	/**
	 * <pre>
	 * 字段名：取消补差描述
	 * 变量名：description
	 * 是否必填：是
	 * 类型：string(80)
	 * 描述：
	 *  取消补差描述 
	 *  示例值：订单退款 
	 * </pre>
	 */
	@JsonProperty(value = "description")
	private String description;

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

	public String getRefundId() {
		return this.refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}