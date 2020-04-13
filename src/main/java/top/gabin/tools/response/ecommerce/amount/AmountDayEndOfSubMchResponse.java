package top.gabin.tools.response.ecommerce.amount;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * <pre>
 * 电商服务商通过该接口可以查询二级商户指定日期当天24点的账户余额。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/amount/chapter3_2.shtml
 * 状态码	错误码	描述	解决方案
 * 403	NO_AUTH	无接口权限	请确认是否已经开通相关权限
 * 400	PARAM_ERROR	参数错误	请使用正确的参数重新调用
 * 400	INVALID_REQUEST	该账户在指定时间不存在	请检查商户号是否正确
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AmountDayEndOfSubMchResponse {
	/**
	 * <pre>
	 * 字段名：二级商户号
	 * 变量名：sub_mchid
	 * 是否必填：是
	 * 类型：string(32)
	 * 描述：
	 *  电商平台二级商户号，由微信支付生成并下发。 
	 *  示例值： 1900000109 
	 * </pre>
	 */
	@JsonProperty(value = "sub_mchid")
	private String subMchid;

	/**
	 * <pre>
	 * 字段名：可用余额
	 * 变量名：available_amount
	 * 是否必填：是
	 * 类型：int64
	 * 描述：
	 *  可用余额（单位：分），此余额可做提现操作。 
	 *  示例值： 100 
	 * </pre>
	 */
	@JsonProperty(value = "available_amount")
	private String availableAmount;

	/**
	 * <pre>
	 * 字段名：不可用余额
	 * 变量名：pending_amount
	 * 是否必填：否
	 * 类型：int64
	 * 描述：
	 *  不可用余额（单位：分）。 
	 *  示例值： 100 
	 * </pre>
	 */
	@JsonProperty(value = "pending_amount")
	private String pendingAmount;

	public String getSubMchid() {
		return this.subMchid;
	}

	public void setSubMchid(String subMchid) {
		this.subMchid = subMchid;
	}

	public String getAvailableAmount() {
		return this.availableAmount;
	}

	public void setAvailableAmount(String availableAmount) {
		this.availableAmount = availableAmount;
	}

	public String getPendingAmount() {
		return this.pendingAmount;
	}

	public void setPendingAmount(String pendingAmount) {
		this.pendingAmount = pendingAmount;
	}

}