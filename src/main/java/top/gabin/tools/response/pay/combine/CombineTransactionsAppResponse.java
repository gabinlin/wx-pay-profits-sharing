package top.gabin.tools.response.pay.combine;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import top.gabin.tools.response.AbstractResponse;


/**
 * <pre>
 * 使用合单支付接口，用户只输入一次密码，即可完成多个订单的支付。目前最多一次可支持50笔订单进行合单支付。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/combine/chapter3_1.shtml
 * 状态码	错误码	描述	解决方案
 * 202	USERPAYING	用户支付中，需要输入密码	等待5秒，然后调用被扫订单结果查询API，查询当前订单的不同状态，决定下一步的操作
 * 403	TRADE_ERROR	交易错误	因业务原因交易失败，请查看接口返回的详细信息
 * 500	SYSTEMERROR	系统错误	系统异常，请用相同参数重新调用
 * 401	SIGN_ERROR	签名错误	请检查签名参数和方法是否都符合签名算法要求
 * 403	RULELIMIT	业务规则限制	因业务规则限制请求频率，请查看接口返回的详细信息
 * 400	PARAM_ERROR	参数错误	请根据接口返回的详细信息检查请求参数
 * 403	OUT_TRADE_NO_USED	商户订单号重复	请核实商户订单号是否重复提交
 * 404	ORDERNOTEXIST	订单不存在	请检查订单是否发起过交易
 * 400	ORDER_CLOSED	订单已关闭	当前订单已关闭，请重新下单
 * 500	OPENID_MISMATCH	openid和appid不匹配	请确认openid和appid是否匹配
 * 403	NOTENOUGH	余额不足	用户帐号余额不足，请用户充值或更换支付卡后再支付
 * 403	NOAUTH	商户无权限	请商户前往申请此接口相关权限
 * 400	MCH_NOT_EXISTS	商户号不存在	请检查商户号是否正确
 * 500	INVALID_TRANSACTIONID	订单号非法	请检查微信支付订单号是否正确
 * 400	INVALID_REQUEST	无效请求	请根据接口返回的详细信息检查
 * 429	FREQUENCY_LIMITED	频率超限	请降低请求接口频率
 * 500	BANKERROR	银行系统异常	银行系统异常，请用相同参数重新调用
 * 400	APPID_MCHID_NOT_MATCH	appid和mch_id不匹配	请确认appid和mch_id是否匹配
 * 403	ACCOUNTERROR	账号异常	用户账号异常，无需更多操作
 * 			
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CombineTransactionsAppResponse extends AbstractResponse {
	/**
	 * <pre>
	 * 字段名：预支付交易会话标识
	 * 变量名：prepay_id
	 * 是否必填：是
	 * 类型：string(64)
	 * 描述：
	 *  数字和字母。微信生成的预支付会话标识，用于后续接口调用使用。 
	 *  示例值：wx201410272009395522657a690389285100 
	 * </pre>
	 */
	@JsonProperty(value = "prepay_id")
	private String prepayId;

	public String getPrepayId() {
		return this.prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

}