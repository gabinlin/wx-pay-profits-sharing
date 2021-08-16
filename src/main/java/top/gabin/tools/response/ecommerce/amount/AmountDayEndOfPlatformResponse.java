package top.gabin.tools.response.ecommerce.amount;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.gabin.tools.response.AbstractResponse;

/**
 * <pre>
 * 通过此接口可以查询本商户号指定日期当天24点的账户余额。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/amount/chapter3_4.shtml
 * 状态码	错误码	描述	解决方案
 * 403	NO_AUTH	当前商户号没有使用该接口的权限	请确认是否已经开通相关权限
 * 400	INVALID_REQUEST	该账户在指定时间不存在	请检查account_type和date输入是否正确，修改后重新调用
 * 400	PARAM_ERROR	参数错误	请使用正确的参数重新调用
 * </pre>
 */
@Data
@EqualsAndHashCode
@JsonIgnoreProperties()
public class AmountDayEndOfPlatformResponse extends AbstractResponse {
	/**
	 * <pre>
	 * 字段名：可用余额
	 * 变量名：available_amount
	 * 是否必填：是
	 * 类型：int
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
	 * 类型：int
	 * 描述：
	 *  不可用余额（单位：分）。 
	 *  示例值： 100 
	 * </pre>
	 */
	@JsonProperty(value = "pending_amount")
	private Integer pendingAmount;

}