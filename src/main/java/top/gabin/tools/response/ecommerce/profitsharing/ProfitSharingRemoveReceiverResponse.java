package top.gabin.tools.response.ecommerce.profitsharing;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import top.gabin.tools.response.AbstractResponse;


/**
 * <pre>
 * 电商平台发起删除分账接收方请求。删除后，不支持将电商平台下二级商户结算后的资金，分到该分账接收方。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/profitsharing/chapter3_8.shtml
 * 状态码	错误码	描述	解决方案
 * 500	SYSTEM_ERROR	系统错误	系统异常，请使用相同参数稍后重新调用
 * 400	PARAM_ERROR	请求参数不符合参数格式	请使用正确的参数重新调用
 * 400	INVALID_REQUEST	无效请求	请确认分账接收方是否存在
 * 403	NO_AUTH	商户无权限	请开通商户号分账权限
 * 429	FREQUENCY_LIMITED	删除接收方频率过高	请降低频率后重试
 * </pre>
 */
@Data
@JsonIgnoreProperties()
public class ProfitSharingRemoveReceiverResponse extends AbstractResponse {
	/**
	 * <pre>
	 * 字段名：接收方类型
	 * 变量名：type
	 * 是否必填：是
	 * 类型：string[1,32]
	 * 描述：
	 *  分账接收方的类型，枚举值： 
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
	 * 类型：string[1,64]
	 * 描述：
	 *  分账接收方的账号
	 *  类型是MERCHANT_ID时，是商户号
	 *  类型是PERSONAL_OPENID时，是个人openid 
	 *  示例值：190001001 
	 * </pre>
	 */
	@JsonProperty(value = "account")
	private String account;

}