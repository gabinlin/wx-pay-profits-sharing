package top.gabin.tools.request.ecommerce.refunds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * <pre>
 * 提交退款申请后，通过调用该接口查询退款状态。该查询服务提供两种查询方式（两种查询方式返回结果一致）： 方式1：通过微信支付退款单号查询退款； 方式2：通过商户退款单号查询退款。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/refunds/chapter3_2.shtml
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RefundQueryStatusRequest1 {
	/**
	 * <pre>
	 * 字段名：商户退款单号
	 * 变量名：out_refund_no
	 * 是否必填：是
	 * 类型：string(32)
	 * 描述：
	 *  path 商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔。 
	 *  示例值： 1217752501201407033233368018 
	 * </pre>
	 */
	@JsonIgnore
	@JsonProperty(value = "out_refund_no")
	private String outRefundNo;

	/**
	 * <pre>
	 * 字段名：二级商户号
	 * 变量名：sub_mchid
	 * 是否必填：是
	 * 类型：string(32)
	 * 描述：
	 *  path微信支付分配给二级商户的商户号。 
	 *  示例值：1900000109 
	 * </pre>
	 */
	@JsonIgnore
	@JsonProperty(value = "sub_mchid")
	private String subMchid;

	@JsonIgnore
	public String getOutRefundNo() {
		return this.outRefundNo;
	}

	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}

	@JsonIgnore
	public String getSubMchid() {
		return this.subMchid;
	}

	public void setSubMchid(String subMchid) {
		this.subMchid = subMchid;
	}

}