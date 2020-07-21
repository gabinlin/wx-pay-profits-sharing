package top.gabin.tools.response.ecommerce.profitsharing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import top.gabin.tools.response.AbstractResponse;


/**
 * <pre>
 * 订单已经分账，在退款时，可以先调此接口，将已分账的资金从分账接收方的账户回退给分账方，再发起退款。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/profitsharing/chapter3_3.shtml
 * 状态码	错误码	描述	解决方案
 * 500	SYSTEM_ERROR	系统错误	系统异常，请使用相同参数稍后重新调用
 * 403	NOT_ENOUGH	剩余可回退的金额不足	调整回退金额
 * 400	PARAM_ERROR	订单号格式不正确	请使用正确的参数重新调用
 * 400	INVALID_REQUEST	回退方不存在	请根据返回的错误信息确认违反的业务规则
 * 429	FREQUENCY_LIMITED	商户发起分账回退的频率过高	请降低频率后重试
 * 403	NO_AUTH	回退方未开通分账回退功能	请先让回退方开通分账回退功能
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfitSharingRefundResponse extends AbstractResponse {
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
	 * 字段名：微信分账单号
	 * 变量名：order_id
	 * 是否必填：是
	 * 类型：string[1,64]
	 * 描述：
	 *  原发起分账请求时，微信返回的微信分账单号，与商户分账单号一一对应。 微信分账单号与商户分账单号二选一填写。 
	 *  示例值： 3008450740201411110007820472 
	 * </pre>
	 */
	@JsonProperty(value = "order_id")
	private String orderId;

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
	 * 字段名：商户回退单号
	 * 变量名：out_return_no
	 * 是否必填：是
	 * 类型：string[1,64]
	 * 描述：
	 *  此回退单号是商户在自己后台生成的一个新的回退单号，在商户后台唯一 只能是数字、大小写字母_-*@ ，同一回退单号多次请求等同一次。
	 *  示例值：R20190516001 
	 * </pre>
	 */
	@JsonProperty(value = "out_return_no")
	private String outReturnNo;

	/**
	 * <pre>
	 * 字段名：回退商户号
	 * 变量名：return_mchid
	 * 是否必填：是
	 * 类型：string[1,32]
	 * 描述：
	 *  只能对原分账请求中成功分给商户接收方进行回退。 
	 *  示例值：86693852 
	 * </pre>
	 */
	@JsonProperty(value = "return_mchid")
	private String returnMchid;

	/**
	 * <pre>
	 * 字段名：回退金额
	 * 变量名：amount
	 * 是否必填：是
	 * 类型：int
	 * 描述：
	 *  需要从分账接收方回退的金额，单位为分，只能为整数，不能超过原始分账单分出给该接收方的金额。 
	 *  示例值:10 
	 * </pre>
	 */
	@JsonProperty(value = "amount")
	private Integer amount;

	/**
	 * <pre>
	 * 字段名：微信回退单号
	 * 变量名：return_no
	 * 是否必填：是
	 * 类型：string[1,64]
	 * 描述：
	 *  微信分账回退单号，微信系统返回的唯一标识。 
	 *  示例值： 3008450740201411110007820472 
	 * </pre>
	 */
	@JsonProperty(value = "return_no")
	private String returnNo;

	/**
	 * <pre>
	 * 字段名：回退结果
	 * 变量名：result
	 * 是否必填：是
	 * 类型：string[1,32]
	 * 描述：
	 *  如果请求返回为处理中，则商户可以通过调用回退结果查询接口获取请求的最终处理结果，枚举值： 
	 *  PROCESSING：处理中 
	 *  SUCCESS：已成功 
	 *  FAIL：已失败 
	 *  
	 *  注意：如果返回为处理中，请勿变更商户回退单号，使用相同的参数再次发起分账回退，否则会出现资金风险 在处理中状态的回退单如果5天没有成功，会因为超时被设置为已失败
	 *  示例值：SUCCESS 
	 * </pre>
	 */
	@JsonProperty(value = "result")
	private String result;

	/**
	 * <pre>
	 * 字段名：失败原因
	 * 变量名：fail_reason
	 * 是否必填：否
	 * 类型：string[1,32]
	 * 描述：
	 *  回退失败的原因，此字段仅回退结果为FAIL时存在，枚举值： 
	 *  ACCOUNT_ABNORMAL：分账接收方账户异常 
	 *  TIME_OUT_CLOSED:：超时关单 
	 *  示例值：TIME_OUT_CLOSED 
	 * </pre>
	 */
	@JsonProperty(value = "fail_reason")
	private String failReason;

	/**
	 * <pre>
	 * 字段名：完成时间
	 * 变量名：finish_time
	 * 是否必填：是
	 * 类型：string[1,64]
	 * 描述：
	 *  分账回退完成时间，遵循rfc3339标准格式 
	 * 格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.120+08:00表示，北京时间2015年5月20日 13点29分35秒。
	 *  示例值：2015-05-20T13:29:35.120+08:00 
	 * </pre>
	 */
	@JsonProperty(value = "finish_time")
	private String finishTime;

	public String getSubMchid() {
		return this.subMchid;
	}

	public void setSubMchid(String subMchid) {
		this.subMchid = subMchid;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOutOrderNo() {
		return this.outOrderNo;
	}

	public void setOutOrderNo(String outOrderNo) {
		this.outOrderNo = outOrderNo;
	}

	public String getOutReturnNo() {
		return this.outReturnNo;
	}

	public void setOutReturnNo(String outReturnNo) {
		this.outReturnNo = outReturnNo;
	}

	public String getReturnMchid() {
		return this.returnMchid;
	}

	public void setReturnMchid(String returnMchid) {
		this.returnMchid = returnMchid;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getReturnNo() {
		return this.returnNo;
	}

	public void setReturnNo(String returnNo) {
		this.returnNo = returnNo;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getFailReason() {
		return this.failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public String getFinishTime() {
		return this.finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

}