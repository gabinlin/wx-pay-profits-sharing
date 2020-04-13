package top.gabin.tools.request.ecommerce.profitsharing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


/**
 * <pre>
 * 微信订单支付成功后，由电商平台发起分账请求，将结算后的资金分给分账接收方。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/profitsharing/chapter3_1.shtml
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfitSharingApplyRequest {
	/**
	 * <pre>
	 * 字段名：二级商户号
	 * 变量名：sub_mchid
	 * 是否必填：是
	 * 类型：string(32)
	 * 描述：
	 *  分账出资的电商平台二级商户，填写微信支付分配的商户号。 
	 *  示例值：1900000109 
	 * </pre>
	 */
	@JsonProperty(value = "sub_mchid")
	private String subMchid;

	/**
	 * <pre>
	 * 字段名：微信订单号
	 * 变量名：transaction_id
	 * 是否必填：是
	 * 类型：string(32)
	 * 描述：
	 *  微信支付订单号。 
	 *  示例值： 4208450740201411110007820472 
	 * </pre>
	 */
	@JsonProperty(value = "transaction_id")
	private String transactionId;

	/**
	 * <pre>
	 * 字段名：商户分账单号
	 * 变量名：out_order_no
	 * 是否必填：是
	 * 类型：string(64)
	 * 描述：
	 *  商户系统内部的分账单号，在商户系统内部唯一（单次分账、多次分账、完结分账应使用不同的商户分账单号），同一分账单号多次请求等同一次。 
	 *  示例值：P20150806125346 
	 * </pre>
	 */
	@JsonProperty(value = "out_order_no")
	private String outOrderNo;

	/**
	 * <pre>
	 * 字段名：+分账接收方列表
	 * 变量名：receivers
	 * 是否必填：是
	 * 类型：array
	 * 描述：分账接收方列表，支持设置出资商户作为分账接收方，单次分账最多可有5个分账接收方 
	 * </pre>
	 */
	@JsonProperty(value = "receivers")
	private List<Receivers> receivers;

	/**
	 * <pre>
	 * 字段名：是否分账完成
	 * 变量名：finish
	 * 是否必填：是
	 * 类型：boolean
	 * 描述：
	 *  是否完成分账
	 *  1、如果为true，该笔订单剩余未分账的金额会解冻回电商平台二级商户；
	 *  2、如果为false，该笔订单剩余未分账的金额不会解冻回电商平台二级商户，可以对该笔订单再次进行分账。
	 *  示例值：true 
	 * </pre>
	 */
	@JsonProperty(value = "finish")
	private String finish;

	public String getSubMchid() {
		return this.subMchid;
	}

	public void setSubMchid(String subMchid) {
		this.subMchid = subMchid;
	}

	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getOutOrderNo() {
		return this.outOrderNo;
	}

	public void setOutOrderNo(String outOrderNo) {
		this.outOrderNo = outOrderNo;
	}

	public List<Receivers> getReceivers() {
		return this.receivers;
	}

	public void setReceivers(List<Receivers> receivers) {
		this.receivers = receivers;
	}

	public String getFinish() {
		return this.finish;
	}

	public void setFinish(String finish) {
		this.finish = finish;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Receivers {
		/**
		 * <pre>
		 * 字段名：分账接受商户号
		 * 变量名：receiver_mchid
		 * 是否必填：是
		 * 类型：string(32)
		 * 描述：
		 *  填写微信支付分配的商户号，仅支持通过添加分账接收方添加的商户号；电商平台商户已默认添加到分账接收方，无需重复添加。
		 *  示例值：1900000109 
		 * </pre>
		 */
		@JsonProperty(value = "receiver_mchid")
		private String receiverMchid;

		/**
		 * <pre>
		 * 字段名：分账金额
		 * 变量名：amount
		 * 是否必填：是
		 * 类型：int
		 * 描述：
		 *  分账金额，单位为分，只能为整数，不能超过原订单支付金额及最大分账比例金额。 
		 *  示例值：190 
		 * </pre>
		 */
		@JsonProperty(value = "amount")
		private String amount;

		/**
		 * <pre>
		 * 字段名：分账描述
		 * 变量名：description
		 * 是否必填：是
		 * 类型：string(80)
		 * 描述：
		 *  分账的原因描述，分账账单中需要体现。 
		 *  示例值：分给商户1900000109 
		 * </pre>
		 */
		@JsonProperty(value = "description")
		private String description;

		public String getReceiverMchid() {
			return this.receiverMchid;
		}

		public void setReceiverMchid(String receiverMchid) {
			this.receiverMchid = receiverMchid;
		}

		public String getAmount() {
			return this.amount;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}

		public String getDescription() {
			return this.description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

	}

}