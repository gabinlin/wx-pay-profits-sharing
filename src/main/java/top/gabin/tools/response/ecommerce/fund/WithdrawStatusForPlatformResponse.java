package top.gabin.tools.response.ecommerce.fund;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.gabin.tools.response.AbstractResponse;

/**
 * <pre>
 * 电商平台通过该接口查询其提现结果，该查询服务提供两种查询方式（两种查询方式返回结果一致）： 方式1：微信支付提现单号查询； 方式2：商户提现单号查询。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/fund/chapter3_6.shtml
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
public class WithdrawStatusForPlatformResponse extends AbstractResponse {
	/**
	 * <pre>
	 * 字段名：提现单状态
	 * 变量名：status
	 * 是否必填：是
	 * 类型：string[1,16]
	 * 描述：
	 *  枚举值：
	 *  CREATE_SUCCESS：受理成功
	 *  SUCCESS：提现成功
	 *  FAIL：提现失败
	 *  REFUND：提现退票
	 *  CLOSE：关单
	 *  INIT：业务单已创建 
	 *  示例值：CREATE_SUCCESS 
	 * </pre>
	 */
	@JsonProperty(value = "status")
	private String status;

	/**
	 * <pre>
	 * 字段名：微信支付提现单号
	 * 变量名：withdraw_id
	 * 是否必填：是
	 * 类型：string[1, 128]
	 * 描述：
	 *  电商平台提交提现申请后，由微信支付返回的申请单号，作为查询申请状态的唯一标识。 
	 *  示例值：12321937198237912739132791732912793127931279317929791239112123 
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
	 *  示例值：20190611222222222200000000012122 
	 * </pre>
	 */
	@JsonProperty(value = "out_request_no")
	private String outRequestNo;

	/**
	 * <pre>
	 * 字段名：提现金额
	 * 变量名：amount
	 * 是否必填：是
	 * 类型：int
	 * 描述：
	 *  单位：分 
	 *  示例值：1 
	 * </pre>
	 */
	@JsonProperty(value = "amount")
	private Integer amount;

	/**
	 * <pre>
	 * 字段名：发起提现时间
	 * 变量名：create_time
	 * 是否必填：是
	 * 类型：string[29, 29]
	 * 描述：
	 *  遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss:sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss:sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日13点29分35秒。 
	 *  示例值：2015-05-20T13:29:35.120+08:00 
	 * </pre>
	 */
	@JsonProperty(value = "create_time")
	private String createTime;

	/**
	 * <pre>
	 * 字段名：提现状态更新时间
	 * 变量名：update_time
	 * 是否必填：是
	 * 类型：string[29, 29]
	 * 描述：
	 *  遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss:sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss:sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日13点29分35秒。 
	 *  示例值：2015-05-20T13:29:35.120+08:00 
	 * </pre>
	 */
	@JsonProperty(value = "update_time")
	private String updateTime;

	/**
	 * <pre>
	 * 字段名：失败原因
	 * 变量名：reason
	 * 是否必填：是
	 * 类型：string[1, 255]
	 * 描述：
	 *  仅在提现失败、退票、关单时有值 
	 *  示例值：卡号错误 
	 * </pre>
	 */
	@JsonProperty(value = "reason")
	private String reason;

	/**
	 * <pre>
	 * 字段名：提现备注
	 * 变量名：remark
	 * 是否必填：是
	 * 类型：string[1, 56]
	 * 描述：
	 *  商户对提现单的备注，若发起提现时未传入相应值或输入不合法，则该值为空 
	 *  示例值：交易提现 
	 * </pre>
	 */
	@JsonProperty(value = "remark")
	private String remark;

	/**
	 * <pre>
	 * 字段名：银行附言
	 * 变量名：bank_memo
	 * 是否必填：是
	 * 类型：string[1, 32]
	 * 描述：
	 *  展示在收款银行系统中的附言，由数字、字母、汉字组成（能否成功展示依赖银行系统支持）。若发起提现时未传入相应值或输入不合法，则该值为空 
	 *  示例值：微信提现 
	 * </pre>
	 */
	@JsonProperty(value = "bank_memo")
	private String bankMemo;

	/**
	 * <pre>
	 * 字段名：出款账户类型
	 * 变量名：account_type
	 * 是否必填：是
	 * 类型：string[1,16]
	 * 描述：
	 *  BASIC：基本户
	 *  OPERATION：运营账户
	 *  FEES：手续费账户 
	 *  示例值：BASIC 
	 * </pre>
	 */
	@JsonProperty(value = "account_type")
	private String accountType;

	/**
	 * <pre>
	 * 字段名：提现失败解决方案
	 * 变量名：solution
	 * 是否必填：是
	 * 类型：string[1, 255]
	 * 描述：
	 *  仅在提现失败、退票、关单时有值 
	 *  示例值：请修改结算银行卡信息 
	 * </pre>
	 */
	@JsonProperty(value = "solution")
	private String solution;

}