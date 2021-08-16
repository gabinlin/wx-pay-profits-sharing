package top.gabin.tools.response.ecommerce.fund;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.gabin.tools.response.AbstractResponse;

/**
 * <pre>
 * 电商平台通过该接口可将其平台的收入进行提现
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/fund/chapter3_5.shtml
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
@EqualsAndHashCode
@JsonIgnoreProperties()
public class WithdrawForPlatformResponse extends AbstractResponse {
	/**
	 * <pre>
	 * 字段名：微信支付提现单号
	 * 变量名：withdraw_id
	 * 是否必填：是
	 * 类型：string[1, 128]
	 * 描述：
	 *  微信支付系统生成的提现单号。 
	 *  示例值：123219371982379127391327917329791239112123 
	 * </pre>
	 */
	@JsonProperty(value = "withdraw_id")
	private String withdrawId;

	/**
	 * <pre>
	 * 字段名：商户提现单号
	 * 变量名：out_request_no
	 * 是否必填：是
	 * 类型：string[1, 32]
	 * 描述：
	 *  商户提现单号，由商户自定义生成。 
	 *  示例值：2019061122200000000012122 
	 * </pre>
	 */
	@JsonProperty(value = "out_request_no")
	private String outRequestNo;

}