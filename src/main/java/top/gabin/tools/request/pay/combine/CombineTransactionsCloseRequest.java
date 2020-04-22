package top.gabin.tools.request.pay.combine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


/**
 * <pre>
 * 合单支付订单只能使用此合单关单api完成关单。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/combine/chapter3_4.shtml
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CombineTransactionsCloseRequest {
	/**
	 * <pre>
	 * 字段名：合单商户appid
	 * 变量名：combine_appid
	 * 是否必填：是
	 * 类型：string(32)
	 * 描述：
	 *  合单发起方的appid。 
	 *  示例值：wxd678efh567hg6787 
	 * </pre>
	 */
	@JsonProperty(value = "combine_appid")
	private String combineAppid;

	/**
	 * <pre>
	 * 字段名：合单商户订单号
	 * 变量名：combine_out_trade_no
	 * 是否必填：是
	 * 类型：string(32)
	 * 描述：
	 *  path 合单支付总订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。 
	 *  示例值：P20150806125346 
	 * </pre>
	 */
	@JsonIgnore
	@JsonProperty(value = "combine_out_trade_no")
	private String combineOutTradeNo;

	/**
	 * <pre>
	 * 字段名：+子单信息
	 * 变量名：sub_orders
	 * 是否必填：是
	 * 类型：array
	 * 描述：
	 *  最多支持子单条数：50 
	 *  
	 * </pre>
	 */
	@JsonProperty(value = "sub_orders")
	private List<SubOrders> subOrders;

	public String getCombineAppid() {
		return this.combineAppid;
	}

	public void setCombineAppid(String combineAppid) {
		this.combineAppid = combineAppid;
	}

	@JsonIgnore
	public String getCombineOutTradeNo() {
		return this.combineOutTradeNo;
	}

	public void setCombineOutTradeNo(String combineOutTradeNo) {
		this.combineOutTradeNo = combineOutTradeNo;
	}

	public List<SubOrders> getSubOrders() {
		return this.subOrders;
	}

	public void setSubOrders(List<SubOrders> subOrders) {
		this.subOrders = subOrders;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class SubOrders {
		/**
		 * <pre>
		 * 字段名：子单商户号
		 * 变量名：mchid
		 * 是否必填：是
		 * 类型：string(32)
		 * 描述：
		 *  子单发起方商户号，必须与发起方appid有绑定关系。 
		 *  示例值：1900000109 
		 * </pre>
		 */
		@JsonProperty(value = "mchid")
		private String mchid;

		/**
		 * <pre>
		 * 字段名：子单商户订单号
		 * 变量名：out_trade_no
		 * 是否必填：是
		 * 类型：string(32)
		 * 描述：
		 *  商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。 
		 *  特殊规则：最小字符长度为6 
		 *  示例值：20150806125346 
		 * </pre>
		 */
		@JsonProperty(value = "out_trade_no")
		private String outTradeNo;

		/**
		 * <pre>
		 * 字段名：二级商户号
		 * 变量名：sub_mchid
		 * 是否必填：是
		 * 类型：string(32)
		 * 描述：
		 *  二级商户商户号，由微信支付生成并下发。 
		 *  注意：仅适用于电商平台 服务商
		 *  示例值：1900000109 
		 * </pre>
		 */
		@JsonProperty(value = "sub_mchid")
		private String subMchid;

		public String getMchid() {
			return this.mchid;
		}

		public void setMchid(String mchid) {
			this.mchid = mchid;
		}

		public String getOutTradeNo() {
			return this.outTradeNo;
		}

		public void setOutTradeNo(String outTradeNo) {
			this.outTradeNo = outTradeNo;
		}

		public String getSubMchid() {
			return this.subMchid;
		}

		public void setSubMchid(String subMchid) {
			this.subMchid = subMchid;
		}

	}

}