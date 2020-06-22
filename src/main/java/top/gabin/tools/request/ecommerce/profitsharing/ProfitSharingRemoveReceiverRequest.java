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
	 * 字段名：公众账号ID
	 * 变量名：appid
	 * 是否必填：是
	 * 类型：string（32）
	 * 描述：
	 *   微信分配的公众账号ID 
	 *  示例值：wx8888888888888888 
	 * </pre>
	 */
	@JsonProperty(value = "appid")
	private String appid;

	/**
	 * <pre>
	 * 字段名：接收方类型
	 * 变量名：type
	 * 是否必填：是
	 * 类型：string（32）
	 * 描述：
	 *   分账接收方的类型，枚举值： 
	 *  MERCHANT_ID：商户
	 *  PERSONAL_OPENID：个人 
	 *  示例值：MERCHANT_ID 
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
	 *   分账接收方的账号
	 *  类型是MERCHANT_ID时，是商户号
	 *  类型是PERSONAL_OPENID时，是个人openid，openid获取方法 
	 *  示例值：190001001 
	 * </pre>
	 */
	@JsonProperty(value = "account")
	private String account;

	public String getAppid() {
		return this.appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

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