package top.gabin.tools.request.ecommerce.fund;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 * 电商平台通过该接口查询其提现结果，该查询服务提供两种查询方式（两种查询方式返回结果一致）： 方式1：微信支付提现单号查询； 方式2：商户提现单号查询。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/fund/chapter3_6.shtml
 * </pre>
 */
@Data
@EqualsAndHashCode
@JsonIgnoreProperties("withdrawId")
public class WithdrawStatusForPlatformRequest {
	/**
	 * <pre>
	 * 字段名：商户提现单号
	 * 变量名：withdraw_id
	 * 是否必填：是
	 * 类型：string[1,128]
	 * 描述：
	 *  path 微信支付系统生成的提现单号。 
	 *  示例值：20190611222222222200000000012122 
	 * </pre>
	 */
	@JsonIgnore
	@JsonProperty(value = "withdraw_id")
	private String withdrawId;

	/**
	 * <pre>
	 * 字段名：电商平台特约商户号
	 * 变量名：sub_mchid
	 * 是否必填：是
	 * 类型：string[1,32]
	 * 描述：
	 *   电商平台特约商户号，由微信支付生成并下发。 
	 *  示例值：200000000012122 
	 * </pre>
	 */
	@JsonProperty(value = "sub_mchid")
	private String subMchid;

}