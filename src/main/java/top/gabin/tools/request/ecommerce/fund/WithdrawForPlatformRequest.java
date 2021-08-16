package top.gabin.tools.request.ecommerce.fund;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 * 电商平台通过该接口可将其平台的收入进行提现
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/fund/chapter3_5.shtml
 * </pre>
 */
@Data
@EqualsAndHashCode
@JsonIgnoreProperties()
public class WithdrawForPlatformRequest {
	/**
	 * <pre>
	 * 字段名：商户提现单号
	 * 变量名：out_request_no
	 * 是否必填：是
	 * 类型：string[1, 32]
	 * 描述：
	 *  body商户提现单号，由商户自定义生成。 
	 *  示例值：20190611222222222200002122 
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
	 *  body单位：分，金额不能超过8亿元 
	 *  示例值：1 
	 * </pre>
	 */
	@JsonProperty(value = "amount")
	private Integer amount;

	/**
	 * <pre>
	 * 字段名：提现备注
	 * 变量名：remark
	 * 是否必填：否
	 * 类型：string[1, 56]
	 * 描述：
	 *  body商户对提现单的备注。 
	 *  示例值：交易提现 
	 * </pre>
	 */
	@JsonProperty(value = "remark")
	private String remark;

	/**
	 * <pre>
	 * 字段名：银行附言
	 * 变量名：bank_memo
	 * 是否必填：否
	 * 类型：string[1, 32]
	 * 描述：
	 *  body展示在收款银行系统中的附言，由数字、字母、汉字组成（能否成功展示依赖银行系统支持）。 
	 *  示例值：xx平台提现 
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
	 *  body枚举值：
	 *  BASIC：基本户
	 *  OPERATION：运营账户
	 *  FEES：手续费账户 
	 *  示例值：BASIC 
	 * </pre>
	 */
	@JsonProperty(value = "account_type")
	private String accountType;

}