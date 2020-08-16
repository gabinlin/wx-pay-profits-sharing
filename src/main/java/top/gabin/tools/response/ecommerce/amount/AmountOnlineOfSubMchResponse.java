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
 * 500	SYSTEM_ERROR	系统错误	系统异常，请使用相同参数稍后重新调用
 * 403	REQUEST_BLOCKED	二级商户未开启手动提现权限	二级商户号提现权限被冻结，无法发起提现
 * 400	PARAM_ERROR	参数错误	请使用正确的参数重新调用
 * 400	PARAM_ERROR	参数错误	请使用正确的参数重新调用，电商平台提交相同商户单号的请求但参数和历史提交的参数不一致
 * 404	ORDER_NOT_EXIST	提现单号不存在	请检查订单号是否正确
 * 403	NOT_ENOUGH	二级商户号账户可用余额不足	二级商户号账户可用余额不足
 * 403	NO_AUTH	无接口使用权限	请开通商户号相关权限
 * 400	INVALID_REQUEST	二级商户未开启手动提现权限	请确认电商平台商户号和二级商户商户号是否存在受理关系
 * 429	FREQUENCY_LIMITED	频率限制	请降低频率后重试
 * 403	CONTRACT_NOT_CONFIRM	二级商户未开启手动提现权限	二级商户号提现权限已关闭，无法发起提现
 * 403	ACCOUNT_NOT_VERIFIED	二级商户下行打款未成功	二级商户号结算银行卡信息有误，修改后重试
 * 403	ACCOUNT_ERROR	二级商户未绑卡	二级商户号没有绑定结算银行卡，绑定后重试
 * </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
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