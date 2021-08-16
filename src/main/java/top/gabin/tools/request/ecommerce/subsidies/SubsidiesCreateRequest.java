package top.gabin.tools.request.ecommerce.subsidies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 * 服务商下单的时候带上补差标识，微信订单支付成功并结算完成后，发起分账前，调用该口进行补差。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/subsidies/chapter3_1.shtml
 * </pre>
 */
@Data
@EqualsAndHashCode
@JsonIgnoreProperties()
public class SubsidiesCreateRequest {
	/**
	 * <pre>
	 * 字段名：二级商户号
	 * 变量名：sub_mchid
	 * 是否必填：是
	 * 类型：string[1,32]
	 * 描述：
	 *  body 补差的电商平台二级商户，填写微信支付分配的商户号。 
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
	 *  body 微信支付订单号。 
	 *  示例值： 4208450740201411110007820472 
	 * </pre>
	 */
	@JsonProperty(value = "transaction_id")
	private String transactionId;

	/**
	 * <pre>
	 * 字段名：补差金额
	 * 变量名：amount
	 * 是否必填：是
	 * 类型：int
	 * 描述：
	 *  body 补差金额，单位为分，只能为整数，不能超过下单时候的最大补差金额。 
	 *  注意：单笔订单最高补差金额为5000元
	 *  示例值：10 
	 * </pre>
	 */
	@JsonProperty(value = "amount")
	private Integer amount;

	/**
	 * <pre>
	 * 字段名：补差描述
	 * 变量名：description
	 * 是否必填：是
	 * 类型：string[1,80]
	 * 描述：
	 *  body 补差备注描述，查询的时候原样带回。 
	 *  示例值：测试备注 
	 * </pre>
	 */
	@JsonProperty(value = "description")
	private String description;

	/**
	 * <pre>
	 * 字段名：微信退款单号
	 * 变量名：refund_id
	 * 是否必填：否
	 * 类型：string[1,64]
	 * 描述：
	 *  body 微信退款单号，微信系统退款返回的唯一标识，当补差金额小于下单时候的金额，该字段必填 
	 *  示例值：3008450740201411110007820472 
	 * </pre>
	 */
	@JsonProperty(value = "refund_id")
	private String refundId;

}