package top.gabin.tools.response.ecommerce.applyments;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.gabin.tools.response.AbstractResponse;

/**
 * <pre>
 * 服务商/电商平台（不包括支付机构、银行），可使用本接口，查询其进件且已签约特约商户/二级商户的结算账户信息（敏感信息掩码）。 该接口可用于核实是否成功修改结算账户信息、及查询系统汇款验证结果。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_5.shtml
 * </pre>
 */
@Data
@EqualsAndHashCode
@JsonIgnoreProperties()
public class ApplymentsSettlementStatusResponse extends AbstractResponse {
	/**
	 * <pre>
	 * 字段名：账户类型
	 * 变量名：account_type
	 * 是否必填：是
	 * 类型：string[1,32]
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
	 * 类型：string[1,128]
	 * 描述：
	 *  返回特约商户的结算账户-开户银行全称。 
	 *  示例值：工商银行 
	 * </pre>
	 */
	@JsonProperty(value = "account_bank")
	private String accountBank;

	/**
	 * <pre>
	 * 字段名：开户银行全称（含支行）
	 * 变量名：bank_name
	 * 是否必填：否
	 * 类型：string[1,128]
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
	 * 是否必填：否
	 * 类型：string[1,128]
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
	 * 类型：string[1,128]
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
	 * 类型：string[1,32]
	 * 描述：
	 *  返回特约商户的结算账户-汇款验证结果。
	 *  VERIFYING：系统汇款验证中，商户可发起提现尝试。
	 *  VERIFY_SUCCESS：系统成功汇款，该账户可正常发起提现。
	 *  VERIFY_FAIL：系统汇款失败，该账户无法发起提现，请检查修改。
	 *  注：该字段，入驻后若没有修改过银行卡，除非汇款失败，否则是不返回的
	 *  示例值：VERIFY_SUCCESS 
	 * </pre>
	 */
	@JsonProperty(value = "verify_result")
	private String verifyResult;

	/**
	 * <pre>
	 * 字段名：汇款验证失败原因
	 * 变量名：verify_fail_reason
	 * 是否必填：否
	 * 类型：string[1, 1024]
	 * 描述：
	 *  如果汇款验证成功为空，汇款验证失败为具体原因。
	 *  示例值：用户姓名/证件/手机不匹配，请核对后重试 
	 * </pre>
	 */
	@JsonProperty(value = "verify_fail_reason")
	private String verifyFailReason;

}