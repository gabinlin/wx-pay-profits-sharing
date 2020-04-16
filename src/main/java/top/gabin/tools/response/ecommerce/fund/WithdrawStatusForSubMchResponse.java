package top.gabin.tools.response.ecommerce.fund;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import top.gabin.tools.response.AbstractResponse;


/**
 * <pre>
 * 电商平台通过查询提现状态API查询二级商户提现单的提现结果。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/fund/chapter3_3.shtml
 * 状态码	错误码	描述	解决方案
 * 403	NO_AUTH	电商特约商户不存在	请确认电商平台商户号和二级商户商户号是否存在受理关系
 * 电商服务商非电商类型	当前商户号没有使用该接口的权限，请确认是否已开通相关权限		
 * 电商特约商户非电商类型	当前商户号没有使用该接口的权限，请确认是否已开通相关权限		
 * 电商服务商和电商特约商户受理关系不存在	请确认电商平台商户号和二级商户商户号是否存在受理关系		
 * 404	ORDER_NOT_EXISTS	该提现单号不存在	请检查订单号是否正确
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WithdrawStatusForSubMchResponse extends AbstractResponse {
	/**
	 * <pre>
	 * 字段名：二级商户号
	 * 变量名：sub_mchid
	 * 是否必填：否
	 * 类型：string(32)
	 * 描述：
	 *  电商平台二级商户号，由微信支付生成并下发。 
	 *  示例值： 1900000109 
	 * </pre>
	 */
	@JsonProperty(value = "sub_mchid")
	private String subMchid;

	/**
	 * <pre>
	 * 字段名：电商平台商户号
	 * 变量名：sp_mchid
	 * 是否必填：是
	 * 类型：string(32)
	 * 描述：
	 *  电商平台商户号 
	 *  示例值： 1800000123 
	 * </pre>
	 */
	@JsonProperty(value = "sp_mchid")
	private String spMchid;

	/**
	 * <pre>
	 * 字段名：提现单状态
	 * 变量名：status
	 * 是否必填：是
	 * 类型：enum
	 * 描述：
	 *  提现状态，枚举值： 
	 *  CREATE_SUCCESS：受理成功
	 *  SUCCESS：提现成功 
	 *  FAIL：提现失败 
	 *  REFUND：提现退票 
	 *  CLOSE：关单 
	 *  示例值： SUCCESS 
	 * </pre>
	 */
	@JsonProperty(value = "status")
	private String status;

	/**
	 * <pre>
	 * 字段名：微信支付提现单号
	 * 变量名：withdraw_id
	 * 是否必填：是
	 * 类型：string(128)
	 * 描述：
	 *  电商平台提交二级商户提现申请后，由微信支付返回的申请单号，作为查询申请状态的唯一标识。 
	 *  示例值： 12321937198237912739132791732912793127931279317929791239112123 
	 * </pre>
	 */
	@JsonProperty(value = "withdraw_id")
	private String withdrawId;

	/**
	 * <pre>
	 * 字段名：商户提现单号
	 * 变量名：out_request_no
	 * 是否必填：是
	 * 类型：string(32)
	 * 描述：
	 *  商户提现单号 
	 *  示例值： 1217752501201407033233368018 
	 * </pre>
	 */
	@JsonProperty(value = "out_request_no")
	private String outRequestNo;

	/**
	 * <pre>
	 * 字段名：提现金额
	 * 变量名：amount
	 * 是否必填：是
	 * 类型：int64
	 * 描述：
	 *  提现金额单位(分)
	 *  示例值：100 
	 * </pre>
	 */
	@JsonProperty(value = "amount")
	private Integer amount;

	/**
	 * <pre>
	 * 字段名：发起提现时间
	 * 变量名：create_time
	 * 是否必填：否
	 * 类型：string(32)
	 * 描述：
	 *  发起提现时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss:sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss:sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日13点29分35秒。 
	 *  示例值： 2015-05-20T13:29:35.120+08:00 
	 * </pre>
	 */
	@JsonProperty(value = "create_time")
	private String createTime;

	/**
	 * <pre>
	 * 字段名：提现状态更新时间
	 * 变量名：update_time
	 * 是否必填：否
	 * 类型：string(32)
	 * 描述：
	 *  提现状态更新时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss:sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss:sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日13点29分35秒。 
	 *  示例值： 2015-05-20T13:29:35.120+08:00 
	 * </pre>
	 */
	@JsonProperty(value = "update_time")
	private String updateTime;

	/**
	 * <pre>
	 * 字段名：失败原因
	 * 变量名：reason
	 * 是否必填：否
	 * 类型：（255)
	 * 描述：
	 *  提现失败原因 
	 *  示例值：卡号错误 
	 * </pre>
	 */
	@JsonProperty(value = "reason")
	private String reason;

	/**
	 * <pre>
	 * 字段名：提现备注
	 * 变量名：remark
	 * 是否必填：否
	 * 类型：string(56)
	 * 描述：
	 *  商户对提现单的备注。 
	 *  示例值：交易提现 
	 * </pre>
	 */
	@JsonProperty(value = "remark")
	private String remark;

	/**
	 * <pre>
	 * 字段名：银行备注
	 * 变量名：bank_memo
	 * 是否必填：否
	 * 类型：string(32)
	 * 描述：
	 *  展示在收款银行系统中的附言，由数字、字母、汉字组成（能否成功展示依赖银行系统支持）。 
	 *  示例值：微信提现 
	 * </pre>
	 */
	@JsonProperty(value = "bank_memo")
	private String bankMemo;

	public String getSubMchid() {
		return this.subMchid;
	}

	public void setSubMchid(String subMchid) {
		this.subMchid = subMchid;
	}

	public String getSpMchid() {
		return this.spMchid;
	}

	public void setSpMchid(String spMchid) {
		this.spMchid = spMchid;
	}

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

}