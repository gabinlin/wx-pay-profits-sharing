package top.gabin.tools.request.ecommerce.fund;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * <pre>
 * 电商平台通过该接口查询其提现结果
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/fund/chapter3_6.shtml
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WithdrawStatusForPlatformRequest {
	/**
	 * <pre>
	 * 字段名：商户提现单号
	 * 变量名：out_request_no
	 * 是否必填：是
	 * 类型：string（32）
	 * 描述：
	 *  path 商户提现单号，由商户自定义生成。 
	 *  示例值：20190611222222222200000000012122 
	 * </pre>
	 */
	@JsonProperty(value = "out_request_no")
	private String outRequestNo;

	public String getOutRequestNo() {
		return this.outRequestNo;
	}

	public void setOutRequestNo(String outRequestNo) {
		this.outRequestNo = outRequestNo;
	}

}