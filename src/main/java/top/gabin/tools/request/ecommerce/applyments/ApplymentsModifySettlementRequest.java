package top.gabin.tools.request.ecommerce.applyments;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 * 服务商/电商平台（不包括支付机构、银行），可使用本接口，修改其进件且已签约特约商户/二级商户的结算银行账户。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_4.shtml
 * </pre>
 */
@Data
@EqualsAndHashCode
@JsonIgnoreProperties("subMchid")
public class ApplymentsModifySettlementRequest {
	/**
	 * <pre>
	 * 字段名：特约商户号
	 * 变量名：sub_mchid
	 * 是否必填：是
	 * 类型：string[8,10]
	 * 描述：
	 *  path请填写本服务商负责进件的特约商户号。 
	 *  特殊规则：长度最小8个字节。
	 *  示例值：1511101111 
	 * </pre>
	 */
	@JsonIgnore
	@JsonProperty(value = "sub_mchid")
	private String subMchid;

	/**
	 * <pre>
	 * 字段名：账户类型
	 * 变量名：account_type
	 * 是否必填：是
	 * 类型：string[1,32]
	 * 描述：
	 *  body 根据特约商户号的主体类型，可选择的账户类型如下： 
	 *  1、小微主体：经营者个人银行卡 2、个体工商户主体：经营者个人银行卡/ 对公银行账户 3、企业主体：对公银行账户 4、党政、机关及事业单位主体：对公银行账户 5、其他组织主体：对公银行账户 枚举值： ACCOUNT_TYPE_BUSINESS：对公银行账户 ACCOUNT_TYPE_PRIVATE：经营者个人银行卡 示例值：ACCOUNT_TYPE_BUSINESS 
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
	 *  body 请填写开户银行名称，详细参见开户银行对照表。 
	 *  注：
	 *  17家直连银行，请根据开户银行对照表直接填写银行名 ;
	 *  非17家直连银行，该参数请填写为“其他银行”
	 *  示例值：工商银行 
	 * </pre>
	 */
	@JsonProperty(value = "account_bank")
	private String accountBank;

	/**
	 * <pre>
	 * 字段名：开户银行省市编码
	 * 变量名：bank_address_code
	 * 是否必填：是
	 * 类型：string[1,128]
	 * 描述：
	 *  body 需至少精确到市，详细参见省市区编号对照表。
	 *  示例值：110000 
	 * </pre>
	 */
	@JsonProperty(value = "bank_address_code")
	private String bankAddressCode;

	/**
	 * <pre>
	 * 字段名：开户银行全称（含支行）
	 * 变量名：bank_name
	 * 是否必填：否
	 * 类型：string[1,128]
	 * 描述：body 若开户银行为“其他银行”，则需二选一填写“开户银行全称（含支行）”或“开户银行联行号”。 填写银行全称，如"深圳农村商业银行XXX支行" ，详细参见开户银行全称（含支行）对照表。 示例值：施秉县农村信用合作联社城关信用社 
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
	 * 描述：body 若开户银行为“其他银行”，则需二选一填写“开户银行全称（含支行）”或“开户银行联行号”。 填写银行联行号，详细参见开户银行全称（含支行）对照表。 示例值：402713354941 
	 * </pre>
	 */
	@JsonProperty(value = "bank_branch_id")
	private String bankBranchId;

	/**
	 * <pre>
	 * 字段名：银行账号
	 * 变量名：account_number
	 * 是否必填：是
	 * 类型：string[1,1024]
	 * 描述：
	 *  body 1、数字，长度遵循系统支持的开户银行对照表中对公/对私卡号长度要求
	 *  2、该字段需进行加密处理，加密方法详见敏感信息加密说明。(提醒：必须在HTTP头中上送Wechatpay-Serial) 
	 *  示例值：d+xT+MQCvrLHUVDWv/8MR/dB7TkXM2YYZlokmXzFsWs35NXUot7C0NcxIrUF5FnxqCJHkNgKtxa6RxEYyba1+VBRLnqKG2fSy/Y5qDN08Ej9zHCwJjq52Wg1VG8MRugli9YMI1fI83KGBxhuXyemgS/hqFKsfYGiOkJqjTUpgY5VqjtL2N4l4z11T0ECB/aSyVXUysOFGLVfSrUxMPZy6jWWYGvT1+4P633f+R+ki1gT4WF/2KxZOYmli385ZgVhcR30mr4/G3HBcxi13zp7FnEeOsLlvBmI1PHN4C7Rsu3WL8sPndjXTd75kPkyjqnoMRrEEaYQE8ZRGYoeorwC+w== 
	 * </pre>
	 */
	@JsonProperty(value = "account_number")
	private String accountNumber;

}