package top.gabin.tools.response.ecommerce.amount;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.gabin.tools.response.AbstractResponse;

/**
 * <pre>
 * 电商服务商通过此接口可以查询二级商户账户余额信息。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/amount/chapter3_1.shtml
 * 状态码	错误码	描述	解决方案
 * 403	NO_AUTH	你无权查询该商户余额	请确认电商平台商户号和二级商户商户号是否存在受理关系
 * 403	NO_AUTH	你无权调用此接口	请确认是否已经开通相关权限
 * 400	PARAM_ERROR	参数错误	请使用正确的参数重新调用
 * 400	INVALID_REQUEST	输入账户类型未开通	请检查account_type，并重新调用
 * </pre>
 */
@Data
@EqualsAndHashCode
@JsonIgnoreProperties()
public class AmountOnlineOfSubMchResponse extends AbstractResponse {
	/**
	 * <pre>
	 * 字段名：二级商户号
	 * 变量名：sub_mchid
	 * 是否必填：是
	 * 类型：string[1,32]
	 * 描述：
	 *  电商平台二级商户号，由微信支付生成并下发。 
	 *  示例值： 1900000109 
	 * </pre>
	 */
	@JsonProperty(value = "sub_mchid")
	private String subMchid;

	/**
	 * <pre>
	 * 字段名：账户类型
	 * 变量名：account_type
	 * 是否必填：否
	 * 类型：string[1,16]
	 * 描述：
	 *  枚举值：
	 *  BASIC：基本账户
	 *  OPERATION：运营账户
	 *  FEES：手续费账户
	 *  示例值： BASIC 
	 * </pre>
	 */
	@JsonProperty(value = "account_type")
	private String accountType;

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