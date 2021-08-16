package top.gabin.tools.request.ecommerce.amount;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 * 通过此接口可以查询本商户号指定日期当天24点的账户余额。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/amount/chapter3_4.shtml
 * </pre>
 */
@Data
@EqualsAndHashCode
@JsonIgnoreProperties("accountType")
public class AmountDayEndOfPlatformRequest {
	/**
	 * <pre>
	 * 字段名：账户类型
	 * 变量名：account_type
	 * 是否必填：是
	 * 类型：string [1,16]
	 * 描述：
	 *  path 枚举值： 
	 *  BASIC：基本账户 
	 *  OPERATION：运营账户 
	 *  FEES：手续费账户 
	 *  示例值：BASIC 
	 * </pre>
	 */
	@JsonIgnore
	@JsonProperty(value = "account_type")
	private String accountType;

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