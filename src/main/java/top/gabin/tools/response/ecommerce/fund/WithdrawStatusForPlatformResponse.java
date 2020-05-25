package top.gabin.tools.response.ecommerce.fund;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import top.gabin.tools.response.AbstractResponse;


/**
 * <pre>
 * 电商平台通过该接口查询其提现结果
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/fund/chapter3_6.shtml
 * 状态码	错误码	描述	解决方案
 * 403	NO_AUTH	当前商户号没有使用该接口的权限	请确认是否已经开通相关权限
 * 400	PARAM_ERROR	参数错误	请使用正确的参数重新调用
 * 404	ORDER_NOT_EXIST	该提现单号不存在	请检查订单号是否正确
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WithdrawStatusForPlatformResponse extends AbstractResponse {
	/**
	 * <pre>
	 * 字段名：提现单状态
	 * 变量名：status
	 * 是否必填：否
	 * 类型：string
	 * 描述：
	 *  枚举值： 
	 *  CREATE_SUCCESS：受理成功 
	 *  SUCCESS：提现成功 
	 *  FAIL：提现失败 
	 *  REFUND：提现退票 
	 *  CLOSE：关单 
	 *  INIT：业务单已创建（业务单处于刚创建状态，需要重入驱动到受理成功。） 
	 *  示例值：CREATE_SUCCESS 
	 * </pre>
	 */
	@JsonProperty(value = "status")
	private String status;

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
	 *  示例值：20190611222222222200000000012122 
	 * </pre>
	 */
	@JsonProperty(value = "out_request_no")
	private String outRequestNo;

	/**
	 * <pre>
	 * 字段名：提现金额
	 * 变量名：amount
	 * 是否必填：否
	 * 类型：int
	 * 描述：
	 *  提现金额，单位：分（RMB） 
	 *  示例值：1 
	 * </pre>
	 */
	@JsonProperty(value = "amount")
	private Integer amount;

	/**
	 * <pre>
	 * 字段名：发起提现时间
	 * 变量名：create_time
	 * 是否必填：否
	 * 类型：string（32）
	 * 描述：
	 *  时间日期遵循rfc3339所定义的格式，入参不限制时区，出参统一使用北京时间（+08:00）。 
	 *  示例值：2015-05-20T13:29:35.120+08:00 
	 * </pre>
	 */
	@JsonProperty(value = "create_time")
	private String createTime;

	/**
	 * <pre>
	 * 字段名：提现状态更新时间
	 * 变量名：update_time
	 * 是否必填：否
	 * 类型：string（32）
	 * 描述：
	 *  时间日期遵循rfc3339所定义的格式，入参不限制时区，出参统一使用北京时间（+08:00）。 
	 *  示例值：2015-05-20T13:29:35.120+08:00 
	 * </pre>
	 */
	@JsonProperty(value = "update_time")
	private String updateTime;

	/**
	 * <pre>
	 * 字段名：失败原因
	 * 变量名：reason
	 * 是否必填：否
	 * 类型：string（255）
	 * 描述：
	 *  提现失败原因 
	 *  示例值：卡号错误 
	 * </pre>
	 */
	@JsonProperty(value = "reason")
	private String reason;

	/**
	 * <pre>
	 * 字段名：备注
	 * 变量名：remark
	 * 是否必填：否
	 * 类型：string（255）
	 * 描述：
	 *  商户对提现单的备注。 
	 *  示例值：交易体现 
	 * </pre>
	 */
	@JsonProperty(value = "remark")
	private String remark;

	/**
	 * <pre>
	 * 字段名：银行附言
	 * 变量名：bank_memo
	 * 是否必填：否
	 * 类型：string（32）
	 * 描述：
	 *  展示在收款银行系统中的附言，由数字、字母、汉字组成（能否成功展示依赖银行系统支持）。
	 *  示例值：xx平台提现 
	 * </pre>
	 */
	@JsonProperty(value = "bank_memo")
	private String bankMemo;

	/**
	 * <pre>
	 * 字段名：账户类型
	 * 变量名：account_type
	 * 是否必填：否
	 * 类型：string
	 * 描述：
	 *  枚举值： 
	 *  BASIC：基本账户 
	 *  OPERATION：运营账户 
	 *  FEES：手续费账户 
	 *  示例值：BASIC 
	 * </pre>
	 */
	@JsonProperty(value = "account_type")
	private String accountType;

	/**
	 * <pre>
	 * 字段名：解决方案
	 * 变量名：solution
	 * 是否必填：否
	 * 类型：string（255）
	 * 描述：
	 *  出错解决方案指引。 
	 *  示例值：请修改结算银行卡信息 
	 * </pre>
	 */
	@JsonProperty(value = "solution")
	private String solution;

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

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

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBankMemo() {
		return this.bankMemo;
	}

	public void setBankMemo(String bankMemo) {
		this.bankMemo = bankMemo;
	}

	public String getAccountType() {
		return this.accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getSolution() {
		return this.solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

}