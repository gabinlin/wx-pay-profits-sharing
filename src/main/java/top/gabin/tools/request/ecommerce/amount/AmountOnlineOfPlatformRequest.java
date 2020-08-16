package top.gabin.tools.request.ecommerce.amount;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * <pre>
 * 电商平台可通过此接口可以查询本商户号的账号余额情况。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/amount/chapter3_3.shtml
 * </pre>
 */
@Data
@JsonIgnoreProperties("accountType")
public class AmountOnlineOfPlatformRequest {
	/**
	 * <pre>
	 * 字段名：账户类型
	 * 变量名：account_type
	 * 是否必填：是
	 * 类型：string[1,16]
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

}