package top.gabin.tools.request.ecommerce.applyments;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 * 服务商/电商平台（不包括支付机构、银行），可使用本接口，查询其进件且已签约特约商户/二级商户的结算账户信息（敏感信息掩码）。 该接口可用于核实是否成功修改结算账户信息、及查询系统汇款验证结果。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_5.shtml
 * </pre>
 */
@Data
@EqualsAndHashCode
@JsonIgnoreProperties("subMchid")
public class ApplymentsSettlementStatusRequest {
	/**
	 * <pre>
	 * 字段名：特约商户号
	 * 变量名：sub_mchid
	 * 是否必填：是
	 * 类型：string[8,10]
	 * 描述：
	 *  path 请输入本服务商进件、已签约的特约商户号。
	 *  示例值：1900006491 
	 * </pre>
	 */
	@JsonIgnore
	@JsonProperty(value = "sub_mchid")
	private String subMchid;

}