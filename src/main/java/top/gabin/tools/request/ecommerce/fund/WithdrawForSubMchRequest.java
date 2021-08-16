package top.gabin.tools.request.ecommerce.fund;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 * 电商平台通过余额提现API帮助二级商户发起账户余额提现申请，完成账户余额提现。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/fund/chapter3_2.shtml
 * </pre>
 */
@Data
@EqualsAndHashCode
@JsonIgnoreProperties()
public class WithdrawForSubMchRequest {
	/**
	 * <pre>
	 * 字段名：二级商户号
	 * 变量名：sub_mchid
	 * 是否必填：是
	 * 类型：string[1, 32]
	 * 描述：
	 *  body电商平台二级商户号，由微信支付生成并下发。 
	 *  示例值：1900000109 
	 * </pre>
	 */
	@JsonProperty(value = "sub_mchid")
	private String subMchid;

	/**
	 * <pre>
	 * 字段名：商户提现单号
	 * 变量名：out_request_no
	 * 是否必填：是
	 * 类型：string[1, 32]
	 * 描述：
	 *  body商户提现单号，由商户自定义生成，必须是字母数字。 
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
	 *  body商户对提现单的备注，商户自定义字段。 
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
	 *  body展示在收款银行系统中的附言，数字、字母最长32个汉字（能否成功展示依赖银行系统支持） 
	 *  示例值：微信支付提现 
	 * </pre>
	 */
	@JsonProperty(value = "bank_memo")
	private String bankMemo;

}