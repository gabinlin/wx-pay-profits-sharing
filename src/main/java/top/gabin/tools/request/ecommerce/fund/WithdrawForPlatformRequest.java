package top.gabin.tools.request.ecommerce.fund;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * <pre>
 * 电商平台通过该接口可将其平台的收入进行提现
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/fund/chapter3_5.shtml
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WithdrawForPlatformRequest {
	/**
	 * <pre>
	 * 字段名：商户提现单号
	 * 变量名：out_request_no
	 * 是否必填：是
	 * 类型：string（32）
	 * 描述：
	 *   商户提现单号，由商户自定义生成。 
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
	 *   提现金额，单位：分（RMB） 
	 *  示例值：1 
	 * </pre>
	 */
	@JsonProperty(value = "amount")
	private String amount;

	/**
	 * <pre>
	 * 字段名：备注
	 * 变量名：remark
	 * 是否必填：否
	 * 类型：string（255）
	 * 描述：
	 *   商户对提现单的备注。 
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
	 *   展示在收款银行系统中的附言，由数字、字母、汉字组成（能否成功展示依赖银行系统支持）。
	 *  示例值：xx平台提现 
	 * </pre>
	 */
	@JsonProperty(value = "bank_memo")
	private String bankMemo;

	/**
	 * <pre>
	 * 字段名：账户类型
	 * 变量名：account_type
	 * 是否必填：是
	 * 类型：string
	 * 描述：
	 *   枚举值： 
	 *  BASIC：基本账户 
	 *  OPERATION：运营账户 
	 *  FEES：手续费账户 
	 *  示例值：BASIC 
	 * </pre>
	 */
	@JsonProperty(value = "account_type")
	private String accountType;

	public String getOutRequestNo() {
		return this.outRequestNo;
	}

	public void setOutRequestNo(String outRequestNo) {
		this.outRequestNo = outRequestNo;
	}

	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
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

}