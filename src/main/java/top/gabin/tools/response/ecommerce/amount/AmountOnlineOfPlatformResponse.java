package top.gabin.tools.response.ecommerce.amount;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import top.gabin.tools.response.AbstractResponse;


/**
 * <pre>
 * 电商平台可通过此接口可以查询本商户号的账号余额情况。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/amount/chapter3_3.shtml
 * 状态码	错误码	描述	解决方案
 * 403	NO_AUTH	当前商户号没有使用该接口的权限	请确认是否已经开通相关权限
 * 400	INVALID_REQUEST	输入账户类型未开通	请检查account_type，并重新调用
 * 400	PARAM_ERROR	参数错误	请使用正确的参数重新调用
 * </pre>
 */
@Data
@JsonIgnoreProperties()
public class AmountOnlineOfPlatformResponse extends AbstractResponse {
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
	private Integer availableAmount;

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
	private Integer pendingAmount;

}