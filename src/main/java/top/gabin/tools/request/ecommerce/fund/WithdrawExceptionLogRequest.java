package top.gabin.tools.request.ecommerce.fund;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * <pre>
 * 电商服务商按日查询并下载提现状态为异常的提现单，提现异常包括提现失败和银行退票。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/fund/chapter3_4.shtml
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WithdrawExceptionLogRequest {
	/**
	 * <pre>
	 * 字段名：账单类型
	 * 变量名：bill_type
	 * 是否必填：是
	 * 类型：string(8)
	 * 描述：
	 *  path NO_SUCC：提现异常账单，包括提现失败和提现退票账单。 
	 *  示例值：NO_SUCC 
	 * </pre>
	 */
	@JsonIgnore
	@JsonProperty(value = "bill_type")
	private String billType;

	/**
	 * <pre>
	 * 字段名：账单日期
	 * 变量名：bill_date
	 * 是否必填：是
	 * 类型：string（10）
	 * 描述：
	 *  path 表示所在日期的提现账单，格式为YYYY-MM-DD。 
	 *  例如：2008-01-01日发起的提现，2008-01-03日银行返回提现失败，则该提现数据将出现在bill_date为2008-01-03日的账单中。 
	 *  示例值：2019-08-17 
	 * </pre>
	 */
	@JsonIgnore
	@JsonProperty(value = "bill_date")
	private String billDate;

	/**
	 * <pre>
	 * 字段名：压缩格式
	 * 变量名：tar_type
	 * 是否必填：否
	 * 类型：enum
	 * 描述：
	 *  path 不填则默认是数据流 
	 *  枚举值： 
	 *  GZIP：返回格式为.gzip的压缩包账单 
	 *  示例值：GZIP 
	 * </pre>
	 */
	@JsonIgnore
	@JsonProperty(value = "tar_type")
	private String tarType;

	public String getBillType() {
		return this.billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getBillDate() {
		return this.billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public String getTarType() {
		return this.tarType;
	}

	public void setTarType(String tarType) {
		this.tarType = tarType;
	}

}