package top.gabin.tools.request.ecommerce.amount;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 * 电商服务商通过此接口可以查询二级商户账户余额信息。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/amount/chapter3_1.shtml
 * </pre>
 */
@Data
@EqualsAndHashCode
@JsonIgnoreProperties("subMchid")
public class AmountOnlineOfSubMchRequest {
	/**
	 * <pre>
	 * 字段名：二级商户号
	 * 变量名：sub_mchid
	 * 是否必填：是
	 * 类型：string[1,32]
	 * 描述：
	 *  path电商平台二级商户号，由微信支付生成并下发。 
	 *  示例值： 1900000109 
	 * </pre>
	 */
	@JsonIgnore
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

}