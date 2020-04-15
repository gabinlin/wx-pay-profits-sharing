package top.gabin.tools.response.ecommerce.fund;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import top.gabin.tools.response.AbstractResponse;


/**
 * <pre>
 * 电商平台通过该接口可将其平台的收入进行提现
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/fund/chapter3_5.shtml
 * 状态码	错误码	描述	解决方案
 * 403	NO_AUTH	无接口权限	请确认是否已经开通相关权限
 * 403	NOT_ENOUGH	商户号账户可用余额不足	请确认商户号账户可用余额是否充足
 * 403	REQUEST_BLOCKED	商户号提现权限被冻结，无法发起提现	商户号提现权限为被冻结状态，无法发起提现
 * 403	CONTRACT_NOT_CONFIRM	商户号提现权限已关闭，无法发起提现	商户号提现权限已关闭，无法发起提现
 * 400	INVALID_REQUEST	当前请求已经超过有效期	当前请求已经超过有效期
 * 403	ACCOUNT_ERROR	商户号没有绑定结算银行卡	请绑定后重试
 * 403	ACCOUNT_NOT_VERIFIED	商户号结算银行卡信息有误	请修改后重试
 * 400	PARAM_ERROR	参数错误	请使用正确的参数重新调用
 * 提交相同商户单号的请求但参数和历史提交的参数不一致			
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WithdrawForPlatformResponse extends AbstractResponse {
	/**
	 * <pre>
	 * 字段名：微信支付提现单号
	 * 变量名：withdraw_id
	 * 是否必填：否
	 * 类型：string（128）
	 * 描述：
	 *  微信支付系统生成的提现单号。 
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
	 * 类型：string（32）
	 * 描述：
	 *  商户提现单号，由商户自定义生成。 
	 *  示例值： 20190611222222222200000000012122 
	 * </pre>
	 */
	@JsonProperty(value = "out_request_no")
	private String outRequestNo;

	public String getWithdrawId() {
		return this.withdrawId;
	}

	public void setWithdrawId(String withdrawId) {
		this.withdrawId = withdrawId;
	}

	public String getOutRequestNo() {
		return this.outRequestNo;
	}

	public void setOutRequestNo(String outRequestNo) {
		this.outRequestNo = outRequestNo;
	}

}