package top.gabin.tools.request.ecommerce.profitsharing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * <pre>
 * 电商平台发起删除分账接收方请求。删除后，不支持将电商平台下二级商户结算后的资金，分到该分账接收方。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/profitsharing/chapter3_8.shtml
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfitSharingRemoveReceiverRequest {
	/**
	 * <pre>
	 * 字段名：接收方类型
	 * 变量名：type
	 * 是否必填：是
	 * 类型：string（32）
	 * 描述：
	 *   分账接收方的类型，当前仅支持： 
	 *  MERCHANT_ID（商户号），填写微信支付分配的商户号。电商平台系统已默认添加为分账接收方，无需重复添加 
	 *  示例值：3008450740201411110007820472 
	 * </pre>
	 */
	@JsonProperty(value = "type")
	private String type;

	/**
	 * <pre>
	 * 字段名：接收方账号
	 * 变量名：account
	 * 是否必填：是
	 * 类型：string（64）
	 * 描述：
	 *   分账接收方的账号，当type为MERCHANT_ID时，接收方账号是商户号。 
	 *  示例值：190001001 
	 * </pre>
	 */
	@JsonProperty(value = "account")
	private String account;

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

}