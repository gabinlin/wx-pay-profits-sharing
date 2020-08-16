package top.gabin.tools.response.ecommerce.refunds;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import top.gabin.tools.response.AbstractResponse;


/**
 * <pre>
 * 退款状态改变后，微信会把相关退款结果发送给商户。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/refunds/chapter3_3.shtml
 * </pre>
 */
@Data
@JsonIgnoreProperties()
public class RefundNotifyResponse extends AbstractResponse {
	/**
	 * <pre>
	 * 字段名：返回状态码
	 * 变量名：code
	 * 是否必填：是
	 * 类型：string[1,32]
	 * 描述：
	 *  错误码，SUCCESS为接收成功，其他错误码为失败 
	 *  示例值：SUCCESS 
	 * </pre>
	 */
	@JsonProperty(value = "code")
	private String code;

	/**
	 * <pre>
	 * 字段名：返回信息
	 * 变量名：message
	 * 是否必填：否
	 * 类型：string[1,256]
	 * 描述：
	 *  返回信息，如非空，为错误原因 
	 *  示例值：系统错误 
	 * </pre>
	 */
	@JsonProperty(value = "message")
	private String message;

}