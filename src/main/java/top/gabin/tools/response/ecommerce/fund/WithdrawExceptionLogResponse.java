package top.gabin.tools.response.ecommerce.fund;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import top.gabin.tools.response.AbstractResponse;


/**
 * <pre>
 * 电商服务商按日查询并下载提现状态为异常的提现单，提现异常包括提现失败和银行退票。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/fund/chapter3_4.shtml
 * 状态码	错误码	描述	解决方案
 * 403	NO_AUTH	当前商户号没有使用该接口的权限	请确认是否已经开通相关权限
 * 400	INVALID_REQUEST	请求的账单日期已过期	请检查bill_date，并重新调用
 * 400	NO_STATEMENT_EXIST	请求的账单文件不存在	请检查当前商户号请求的微信支付账户在指定日期是否有资金操作
 * 400	STATEMENT_CREATING	请求的账单正在生成中	请先检查当前商户号的微信支付账户在指定日期内是否有资金操作，若有，则在T+1日上午10点后再重新下载
 * 500	SYSTEM_ERROR	系统错误	系统异常，请使用相同参数稍后重新调用
 * 400	PARAM_ERROR	参数错误	请使用正确的参数重新调用
 * </pre>
 */
@Data
@JsonIgnoreProperties()
public class WithdrawExceptionLogResponse extends AbstractResponse {
	/**
	 * <pre>
	 * 字段名：哈希类型
	 * 变量名：hash_type
	 * 是否必填：是
	 * 类型：string[1,32]
	 * 描述：
	 *  从download_url下载的文件的哈希类型，用于校验文件的完整性。 
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
	 * 类型：string[1,1024]
	 * 描述：
	 *  从download_url下载的文件的哈希值，用于校验文件的完整性。 
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
	 * 类型：string[1,2048]
	 * 描述：
	 *  供下一步请求账单文件的下载地址，该地址30s内有效。 
	 *  示例值：https://api.mch.weixin.qq.com/v3/billdownload/file?token=xxx 
	 * </pre>
	 */
	@JsonProperty(value = "download_url")
	private String downloadUrl;

}