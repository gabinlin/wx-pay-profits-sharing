package top.gabin.tools.request.ecommerce.amount;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 * 电商服务商通过该接口可以查询二级商户指定日期当天24点的账户余额。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/amount/chapter3_2.shtml
 * </pre>
 */
@Data
@EqualsAndHashCode
@JsonIgnoreProperties("subMchid")
public class AmountDayEndOfSubMchRequest {
	/**
	 * <pre>
	 * 字段名：二级商户号
	 * 变量名：sub_mchid
	 * 是否必填：是
	 * 类型：string[1,32]
	 * 描述：
	 *  path 电商平台二级商户号，由微信支付生成并下发。 
	 *  示例值：1222212222 
	 * </pre>
	 */
	@JsonIgnore
	@JsonProperty(value = "sub_mchid")
	private String subMchid;

	/**
	 * <pre>
	 * 字段名：日期
	 * 变量名：date
	 * 是否必填：是
	 * 类型：string[1,10]
	 * 描述：
	 *   指定查询商户日终余额的日期 
	 *  示例值：2019-08-17 
	 * </pre>
	 */
	@JsonProperty(value = "date")
	private String date;

}