package top.gabin.tools.request.pay.combine;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * <pre>
 * 电商平台通过合单查询订单API查询订单状态，完成下一步的业务逻辑。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/combine/chapter3_3.shtml
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CombineTransactionsDetailRequest {
	/**
	 * <pre>
	 * 字段名：合单商户订单号
	 * 变量名：combine_out_trade_no
	 * 是否必填：是
	 * 类型：string(32)
	 * 描述：
	 *  path 合单支付总订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一 。
	 *  示例值：P20150806125346 
	 * </pre>
	 */
	@JsonProperty(value = "combine_out_trade_no")
	private String combineOutTradeNo;

	public String getCombineOutTradeNo() {
		return this.combineOutTradeNo;
	}

	public void setCombineOutTradeNo(String combineOutTradeNo) {
		this.combineOutTradeNo = combineOutTradeNo;
	}

}