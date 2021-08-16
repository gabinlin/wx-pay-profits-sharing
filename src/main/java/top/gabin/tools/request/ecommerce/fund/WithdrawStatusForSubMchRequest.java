package top.gabin.tools.request.ecommerce.fund;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 * 电商平台通过查询提现状态API查询二级商户提现单的提现结果。 该查询服务提供两种查询方式（两种查询方式返回结果一致）： 方式1：微信支付提现单号查询； 方式2：商户提现单号查询。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/fund/chapter3_3.shtml
 * </pre>
 */
@Data
@EqualsAndHashCode
@JsonIgnoreProperties("subMchid")
public class WithdrawStatusForSubMchRequest {
	/**
	 * <pre>
	 * 字段名：二级商户号
	 * 变量名：sub_mchid
	 * 是否必填：是
	 * 类型：string[1, 32]
	 * 描述：
	 *  电商平台二级商户号，由微信支付生成并下发
	 *  示例值：1900000109。 
	 * </pre>
	 */
	@JsonIgnore
	@JsonProperty(value = "sub_mchid")
	private String subMchid;

	/**
	 * <pre>
	 * 字段名：微信支付提现单号
	 * 变量名：withdraw_id
	 * 是否必填：是
	 * 类型：string[1, 128]
	 * 描述：
	 *  path电商平台提交二级商户提现申请后，由微信支付返回的申请单号，作为查询申请状态的唯一标识。 
	 *  示例值：1232193719823791273913279173291279 
	 * </pre>
	 */
	@JsonIgnore
	@JsonProperty(value = "withdraw_id")
	private String withdrawId;

}