package top.gabin.tools.request.ecommerce.fund;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * <pre>
 * 电商平台通过查询提现状态API查询二级商户提现单的提现结果。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/fund/chapter3_3.shtml
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WithdrawStatusForSubMchRequest1 {
	/**
	 * <pre>
	 * 字段名：二级商户号
	 * 变量名：sub_mchid
	 * 是否必填：是
	 * 类型：string(32)
	 * 描述：
	 *  path 电商平台二级商户号，由微信支付生成并下发。 
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
	 * 类型：string（32）
	 * 描述：
	 *  path 商户提现单号，由商户自定义生成。 
	 *  示例值：20190611222222222200000000012122 
	 * </pre>
	 */
	@JsonProperty(value = "out_request_no")
	private String outRequestNo;

	public String getSubMchid() {
		return this.subMchid;
	}

	public void setSubMchid(String subMchid) {
		this.subMchid = subMchid;
	}

	public String getOutRequestNo() {
		return this.outRequestNo;
	}

	public void setOutRequestNo(String outRequestNo) {
		this.outRequestNo = outRequestNo;
	}

}