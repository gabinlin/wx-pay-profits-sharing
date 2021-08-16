package top.gabin.tools.request.ecommerce.profitsharing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 * 商户需要核实回退结果，可调用此接口查询回退结果;如果分账回退接口返回状态为处理中，可调用此接口查询回退结果
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/profitsharing/chapter3_4.shtml
 * </pre>
 */
@Data
@EqualsAndHashCode
@JsonIgnoreProperties()
public class ProfitSharingQueryRefundRequest {
	/**
	 * <pre>
	 * 字段名：二级商户号
	 * 变量名：sub_mchid
	 * 是否必填：是
	 * 类型：string[1,32]
	 * 描述：
	 *   分账回退的接收商户，对应原分账出资的电商平台二级商户，填写微信支付分配的商户号。 
	 *  示例值：1900000109 
	 * </pre>
	 */
	@JsonProperty(value = "sub_mchid")
	private String subMchid;

	/**
	 * <pre>
	 * 字段名：微信分账单号
	 * 变量名：order_id
	 * 是否必填：二选一
	 * 类型：string[1,64]
	 * 描述：
	 *   微信分账单号，微信系统返回的唯一标识。微信分账单号和商户分账单号二选一填写。 
	 *  示例值： 3008450740201411110007820472 
	 * </pre>
	 */
	@JsonProperty(value = "order_id")
	private String orderId;

	/**
	 * <pre>
	 * 字段名：商户分账单号
	 * 变量名：out_order_no
	 * 是否必填： 原发起分账请求时使用的商户系统内部的分账单号。微信分账单号与商户分账单号二选一填写。 示例值：P20150806125346
	 * 类型：string[1,64]
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
	 * 类型：string[1,64]
	 * 描述：
	 *  调用回退接口提供的商户系统内部的回退单号
	 *  示例值：R20190516001 
	 * </pre>
	 */
	@JsonProperty(value = "out_return_no")
	private String outReturnNo;

}