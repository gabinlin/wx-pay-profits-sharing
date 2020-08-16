package top.gabin.tools.response.ecommerce.profitsharing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.gabin.tools.response.AbstractResponse;

/**
 * <pre>
 * 1、此功能仅针对分账接收方。 2、分账动账金额变动后，微信会把相关变动结果发送给需要实时关注的分账接收方。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/profitsharing/chapter3_6.shtml
 * </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties()
public class ProfitSharingNotifyResponse extends AbstractResponse {
	/**
	 * <pre>
	 * 字段名：返回状态码
	 * 变量名：code
	 * 是否必填：是
	 * 类型：string[1,32]
	 * 描述：
	 *  错误码，SUCCESS为接收成功，其他错误码为失败。 
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
	 *  返回信息，如非空，为错误原因。
	 *  示例值：系统错误 
	 * </pre>
	 */
	@JsonProperty(value = "message")
	private String message;

}