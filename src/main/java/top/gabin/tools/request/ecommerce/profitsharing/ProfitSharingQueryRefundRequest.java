package top.gabin.tools.request.ecommerce.profitsharing;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * <pre>
 * 商户需要核实回退结果，可调用此接口查询回退结果;如果分账回退接口返回状态为处理中，可调用此接口查询回退结果
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/profitsharing/chapter3_4.shtml
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfitSharingQueryRefundRequest {
	/**
	 * <pre>
	 * 字段名：二级商户号
	 * 变量名：sub_mchid
	 * 是否必填：是
	 * 类型：string(32)
	 * 描述：
	 *  path 分账回退的接收商户，对应原分账出资的电商平台二级商户，填写微信支付分配的商户号。 
	 *  示例值：1900000109 
	 * </pre>
	 */
	@JsonIgnore
	@JsonProperty(value = "sub_mchid")
	private String subMchid;

	/**
	 * <pre>
	 * 字段名：微信分账单号
	 * 变量名：order_id
	 * 是否必填：二选一
	 * 类型：string(64)
	 * 描述：
	 *  path 微信分账单号，微信系统返回的唯一标识。微信分账单号和商户分账单号二选一填写。 
	 *  示例值： 3008450740201411110007820472 
	 * </pre>
	 */
	@JsonIgnore
	@JsonProperty(value = "order_id")
	private String orderId;

	/**
	 * <pre>
	 * 字段名：商户分账单号
	 * 变量名：out_order_no
	 * 是否必填：path 原发起分账请求时使用的商户系统内部的分账单号。微信分账单号与商户分账单号二选一填写。 示例值：P20150806125346
	 * 类型：string(64)
	 * 描述： 
	 * </pre>
	 */
	@JsonProperty(value = "out_order_no")
	private String outOrderNo;

	/**
	 * <pre>
	 * 字段名：商户回退单号
	 * 变量名：out_return_no
	 * 是否必填：是
	 * 类型：string(64)
	 * 描述：
	 *  path此回退单号是商户在自己后台生成的一个新的回退单号，在商户后台唯一 。
	 *  示例值：R20190516001 
	 * </pre>
	 */
	@JsonIgnore
	@JsonProperty(value = "out_return_no")
	private String outReturnNo;

	public String getSubMchid() {
		return this.subMchid;
	}

	public void setSubMchid(String subMchid) {
		this.subMchid = subMchid;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOutOrderNo() {
		return this.outOrderNo;
	}

	public void setOutOrderNo(String outOrderNo) {
		this.outOrderNo = outOrderNo;
	}

	public String getOutReturnNo() {
		return this.outReturnNo;
	}

	public void setOutReturnNo(String outReturnNo) {
		this.outReturnNo = outReturnNo;
	}

}