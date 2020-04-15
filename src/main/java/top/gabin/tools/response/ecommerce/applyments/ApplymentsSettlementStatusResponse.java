package top.gabin.tools.response.ecommerce.applyments;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import top.gabin.tools.response.AbstractResponse;


/**
 * <pre>
 * 普通服务商（支付机构、银行不可用），可使用本接口查询其进件、已签约的特约商户-结算账户信息（敏感信息掩码）。 该接口可用于核实是否成功修改结算账户信息、及查询系统汇款验证结果。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_5.shtml
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplymentsSettlementStatusResponse extends AbstractResponse {
	/**
	 * <pre>
	 * 字段名：账户类型
	 * 变量名：account_type
	 * 是否必填：是
	 * 类型：enum
	 * 描述：
	 *  返回特约商户的结算账户类型。 
	 *  枚举值： ACCOUNT_TYPE_BUSINESS：对公银行账户 ACCOUNT_TYPE_PRIVATE：经营者个人银行卡 示例值：ACCOUNT_TYPE_BUSINESS 
	 * </pre>
	 */
	@JsonProperty(value = "account_type")
	private String accountType;

	/**
	 * <pre>
	 * 字段名：开户银行
	 * 变量名：account_bank
	 * 是否必填：是
	 * 类型：string(128)
	 * 描述：
	 *  返回特约商户的结算账户-开户银行全称。 
	 *  示例值：工商银行 
	 * </pre>
	 */
	@JsonProperty(value = "account_bank")
	private String accountBank;

	/**
	 * <pre>
	 * 字段名：开户银行全称（含支行)
	 * 变量名：bank_name
	 * 是否必填：是
	 * 类型：string(128)
	 * 描述：
	 *  返回特约商户的结算账户-开户银行全称（含支行）。 
	 *  示例值：施秉县农村信用合作联社城关信用社 
	 * </pre>
	 */
	@JsonProperty(value = "bank_name")
	private String bankName;

	/**
	 * <pre>
	 * 字段名：开户银行联行号
	 * 变量名：bank_branch_id
	 * 是否必填：是
	 * 类型：string(128)
	 * 描述：
	 *  返回特约商户的结算账户-联行号。 
	 *  示例值：402713354941 
	 * </pre>
	 */
	@JsonProperty(value = "bank_branch_id")
	private String bankBranchId;

	/**
	 * <pre>
	 * 字段名：银行账号
	 * 变量名：account_number
	 * 是否必填：是
	 * 类型：string(128)
	 * 描述：
	 *  返回特约商户的结算账户-银行账号，掩码显示。 
	 *  示例值：62*************78 
	 * </pre>
	 */
	@JsonProperty(value = "account_number")
	private String accountNumber;

	/**
	 * <pre>
	 * 字段名：汇款验证结果
	 * 变量名：verify_result
	 * 是否必填：是
	 * 类型：enum
	 * 描述：
	 *  返回特约商户的结算账户-汇款验证结果。
	 *  VERIFYING：系统汇款验证中，商户可发起提现尝试。 VERIFY_SUCCESS：系统成功汇款，该账户可正常发起提现。 VERIFY_FAIL：系统汇款失败，该账户无法发起提现，请检查修改。 示例值：VERIFY_SUCCESS 
	 * </pre>
	 */
	@JsonProperty(value = "verify_result")
	private String verifyResult;

	public String getAccountType() {
		return this.accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountBank() {
		return this.accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankBranchId() {
		return this.bankBranchId;
	}

	public void setBankBranchId(String bankBranchId) {
		this.bankBranchId = bankBranchId;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getVerifyResult() {
		return this.verifyResult;
	}

	public void setVerifyResult(String verifyResult) {
		this.verifyResult = verifyResult;
	}

}