package top.gabin.tools.request.ecommerce.profitsharing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;


/**
 * <pre>
 * 1、此功能仅针对分账接收方。 2、分账动账金额变动后，微信会把相关变动结果发送给需要实时关注的分账接收方。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/profitsharing/chapter3_6.shtml
 * </pre>
 */
@Data
@EqualsAndHashCode
@JsonIgnoreProperties()
public class ProfitSharingNotifyRequest1 {
	/**
	 * <pre>
	 * 字段名：直连商户号
	 * 变量名：mchid
	 * 是否必填：否
	 * 类型：string[1,32]
	 * 描述：
	 *  直连模式分账发起和出资商户。 
	 *  示例值：1900000100 
	 * </pre>
	 */
	@JsonProperty(value = "mchid")
	private String mchid;

	/**
	 * <pre>
	 * 字段名：服务商商户号
	 * 变量名：sp_mchid
	 * 是否必填：否
	 * 类型：string[1,32]
	 * 描述：
	 *  服务商模式分账发起商户。 
	 *  示例值：1900000100 
	 * </pre>
	 */
	@JsonProperty(value = "sp_mchid")
	private String spMchid;

	/**
	 * <pre>
	 * 字段名：子商户号
	 * 变量名：sub_mchid
	 * 是否必填：否
	 * 类型：string[1,32]
	 * 描述：
	 *  服务商模式分账出资商户。 
	 *  示例值：1900000100 
	 * </pre>
	 */
	@JsonProperty(value = "sub_mchid")
	private String subMchid;

	/**
	 * <pre>
	 * 字段名：微信订单号
	 * 变量名：transaction_id
	 * 是否必填：是
	 * 类型：string[1,32]
	 * 描述：
	 *  微信支付订单号。 
	 *  示例值： 4200000000000000000000000000 
	 * </pre>
	 */
	@JsonProperty(value = "transaction_id")
	private String transactionId;

	/**
	 * <pre>
	 * 字段名：微信分账/回退单号
	 * 变量名：order_id
	 * 是否必填：是
	 * 类型：string[1,64]
	 * 描述：
	 *  微信分账/回退单号。 
	 *  示例值： 1217752501201407033233368018 
	 * </pre>
	 */
	@JsonProperty(value = "order_id")
	private String orderId;

	/**
	 * <pre>
	 * 字段名：商户分账/回退单号
	 * 变量名：out_order_no
	 * 是否必填：是
	 * 类型：string[1,64]
	 * 描述：
	 *  分账方系统内部的分账/回退单号。 
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
	 * 描述：分账接收方对象 
	 * </pre>
	 */
	@JsonProperty(value = "receivers")
	private List<Receivers> receivers;

	/**
	 * <pre>
	 * 字段名：成功时间
	 * 变量名：success_time
	 * 是否必填：是
	 * 类型：string[1,32]
	 * 描述：
	 *  成功时间，遵循rfc3339标准 
	 *  格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日 13点29分35秒。
	 *  示例值：2018-06-08T10:34:56+08:00 
	 * </pre>
	 */
	@JsonProperty(value = "success_time")
	private String successTime;

	@EqualsAndHashCode
	@Data
	@JsonIgnoreProperties()
	public static class Receivers {
		/**
		 * <pre>
		 * 字段名：分账接收方类型
		 * 变量名：type
		 * 是否必填：是
		 * 类型：string[1,32]
		 * 描述：
		 *  分账接收方的类型，枚举值： 
		 *  MERCHANT_ID：商户
		 *  PERSONAL_OPENID：个人 
		 *  示例值：MERCHANT_ID 
		 * </pre>
		 */
		@JsonProperty(value = "type")
		private String type;

		/**
		 * <pre>
		 * 字段名：分账接收方帐号
		 * 变量名：account
		 * 是否必填：是
		 * 类型：string[1,64]
		 * 描述：
		 *  分账接收方的账号
		 *  类型是MERCHANT_ID时，是商户号
		 *  类型是PERSONAL_OPENID时，是个人openid 
		 *  示例值：190001001 
		 * </pre>
		 */
		@JsonProperty(value = "account")
		private String account;

		/**
		 * <pre>
		 * 字段名：分账动账金额
		 * 变量名：amount
		 * 是否必填：是
		 * 类型：int
		 * 描述：
		 *  分账动账金额，单位为分，只能为整数。 
		 *  示例值：888 
		 * </pre>
		 */
		@JsonProperty(value = "amount")
		private Integer amount;

		/**
		 * <pre>
		 * 字段名：分账/回退描述
		 * 变量名：description
		 * 是否必填：是
		 * 类型：string[1,80]
		 * 描述：
		 *  分账/回退描述 
		 *  示例值：运费/交易分账/及时奖励 
		 * </pre>
		 */
		@JsonProperty(value = "description")
		private String description;

	}

}