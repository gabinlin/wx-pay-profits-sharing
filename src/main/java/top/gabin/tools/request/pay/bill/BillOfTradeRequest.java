package top.gabin.tools.request.pay.bill;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 * 微信支付按天提供交易账单文件，商户可以通过该接口获取账单文件的下载地址。文件内包含交易相关的金额、时间、营销等信息，供商户核对订单、退款、银行到账等情况。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/bill/chapter3_1.shtml
 * </pre>
 */
@Data
@EqualsAndHashCode
@JsonIgnoreProperties()
public class BillOfTradeRequest {
	/**
	 * <pre>
	 * 字段名：账单日期
	 * 变量名：bill_date
	 * 是否必填：是
	 * 类型：string[1,10]
	 * 描述：
	 *   格式YYYY-MM-DD 
	 *  仅支持三个月内的账单下载申请。 
	 *  示例值：2019-06-11 
	 * </pre>
	 */
	@JsonProperty(value = "bill_date")
	private String billDate;

	/**
	 * <pre>
	 * 字段名：账单类型
	 * 变量名：bill_type
	 * 是否必填：否
	 * 类型：string[1,32]
	 * 描述：
	 *   不填则默认是ALL 
	 *  枚举值：
	 *  ALL：返回当日所有订单信息（不含充值退款订单） 
	 *  SUCCESS：返回当日成功支付的订单（不含充值退款订单） 
	 *  REFUND：返回当日退款订单（不含充值退款订单） 
	 *  示例值：ALL 
	 * </pre>
	 */
	@JsonProperty(value = "bill_type")
	private String billType;

	/**
	 * <pre>
	 * 字段名：压缩类型
	 * 变量名：tar_type
	 * 是否必填：否
	 * 类型：string[1,32]
	 * 描述：
	 *   不填则默认是数据流 
	 *  枚举值：
	 *  GZIP：返回格式为.gzip的压缩包账单 
	 *  示例值：GZIP 
	 * </pre>
	 */
	@JsonProperty(value = "tar_type")
	private String tarType;

}