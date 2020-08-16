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
@JsonIgnoreProperties("outRequestNo")
public class WithdrawStatusForPlatformRequest1 {
	/**
	 * <pre>
	 * 字段名：商户提现单号
	 * 变量名：out_request_no
	 * 是否必填：是
	 * 类型：string[1,32]
	 * 描述：
	 *  path 商户提现单号，由商户自定义生成。 
	 *  示例值：20190611222222222200000000012122 
	 * </pre>
	 */
	@JsonIgnore
	@JsonProperty(value = "out_request_no")
	private String outRequestNo;

}