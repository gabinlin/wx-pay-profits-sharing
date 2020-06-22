package top.gabin.tools.response.pay.bill;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import top.gabin.tools.response.AbstractResponse;


/**
 * <pre>
 * 微信支付按天提供交易账单文件，商户可以通过该接口获取账单文件的下载地址。文件内包含交易相关的金额、时间、营销等信息，供商户核对订单、退款、银行到账等情况。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/bill/chapter3_1.shtml
 * 状态码	错误码	描述	解决方案
 * 500	SYSTEM_ERROR	系统错误	系统异常，请使用相同参数稍后重新调用
 * 400	PARAM_ERROR	参数错误	请使用正确的参数重新调用
 * 400	INVALID_REQUEST	参数错误	请检查bill_date，并重新调用
 * 参数错误	请检查sub_mchid，并重新调用		
 * 403	NO_AUTH	权限异常（无该子商户账单的权限）	请使用正确的sub_mchid再重新调用
 * 权限异常，小微商户不单独提供对账单下载	可以不传sub_mch_id，以获取服务商下全量电商二级商户（包括小微商户和非小微商户）的对账单		
 * 400	NO_STATEMENT_EXIST	账单文件不存在	请检查当前商户号是否在指定日期有交易或退款发生
 * 400	STATEMENT_CREATING	账单生成中	请先检查当前商户号在指定日期内是否有成功的交易或退款，若有，则在T+1日上午8点后再重新下载
 * 400	NO_STATEMENT_EXIST	账单文件不存在	请检查当前商户号请求的微信支付账户在指定日期是否有资金操作
 * 400	STATEMENT_CREATING	账单生成中	请先检查当前商户号的微信支付账户在指定日期内是否有资金操作，若有，则在T+1日上午10点后再重新下载
 * 400	INVALID_REQUEST	参数错误	请按第一步申请账单的API指引，重新获取账单地址后再请求
 * 403	NO_AUTH	权限异常	请检查本次请求的商户是否与第一步申请账单API的请求商户一致
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BillOfTradeResponse extends AbstractResponse {
	/**
	 * <pre>
	 * 字段名：哈希类型
	 * 变量名：hash_type
	 * 是否必填：是
	 * 类型：string(32)
	 * 描述：
	 *  原始账单（gzip需要解压缩）的摘要值，用于校验文件的完整性。 
	 *  示例值：SHA1 
	 * </pre>
	 */
	@JsonProperty(value = "hash_type")
	private String hashType;

	/**
	 * <pre>
	 * 字段名：哈希值
	 * 变量名：hash_value
	 * 是否必填：是
	 * 类型：string(1024)
	 * 描述：
	 *  原始账单（gzip需要解压缩）的摘要值，用于校验文件的完整性。 
	 *  示例值：79bb0f45fc4c42234a918000b2668d689e2bde04 
	 * </pre>
	 */
	@JsonProperty(value = "hash_value")
	private String hashValue;

	/**
	 * <pre>
	 * 字段名：账单下载地址
	 * 变量名：download_url
	 * 是否必填：是
	 * 类型：string(2048)
	 * 描述：
	 *  供下一步请求账单文件的下载地址，该地址30s内有效。 
	 *  示例值：https://api.mch.weixin.qq.com/v3/billdownload/file?token=xxx 
	 * </pre>
	 */
	@JsonProperty(value = "download_url")
	private String downloadUrl;

	public String getHashType() {
		return this.hashType;
	}

	public void setHashType(String hashType) {
		this.hashType = hashType;
	}

	public String getHashValue() {
		return this.hashValue;
	}

	public void setHashValue(String hashValue) {
		this.hashValue = hashValue;
	}

	public String getDownloadUrl() {
		return this.downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

}