package top.gabin.tools.request.pay.bill;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * <pre>
 * 微信支付按天提供微信支付账户的资金流水账单文件，商户可以通过该接口获取账单文件的下载地址。文件内包含该账户资金操作相关的业务单号、收支金额、记账时间等信息，供商户进行核对。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/bill/chapter3_2.shtml
 * </pre>
 */
@Data
@JsonIgnoreProperties()
public class BillOfFundFlowRequest {
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
	 * 字段名：资金账户类型
	 * 变量名：account_type
	 * 是否必填：否
	 * 类型：string[1,32]
	 * 描述：
	 *   不填则默认是BASIC 
	 *  枚举值：
	 *  BASIC：基本账户 
	 *  OPERATION：运营账户 
	 *  FEES：手续费账户 
	 *  示例值：BASIC 
	 * </pre>
	 */
	@JsonProperty(value = "account_type")
	private String accountType;

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