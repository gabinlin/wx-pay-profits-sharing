package top.gabin.tools.request.ecommerce.applyments;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * <pre>
 * 普通服务商（支付机构、银行不可用），可使用本接口查询其进件、已签约的特约商户-结算账户信息（敏感信息掩码）。 该接口可用于核实是否成功修改结算账户信息、及查询系统汇款验证结果。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_5.shtml
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplymentsSettlementStatusRequest {
	/**
	 * <pre>
	 * 字段名：特约商户号
	 * 变量名：sub_mchid
	 * 是否必填：是
	 * 类型：string(10)
	 * 描述：
	 *  path 请输入本服务商进件、已签约的特约商户号。
	 *  特殊规则：长度最小8个字节
	 *  示例值：1900006491 
	 * </pre>
	 */
	@JsonProperty(value = "sub_mchid")
	private String subMchid;

	public String getSubMchid() {
		return this.subMchid;
	}

	public void setSubMchid(String subMchid) {
		this.subMchid = subMchid;
	}

}