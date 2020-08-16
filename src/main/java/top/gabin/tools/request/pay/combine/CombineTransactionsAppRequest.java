package top.gabin.tools.request.pay.combine;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;


/**
 * <pre>
 * 使用合单支付接口，用户只输入一次密码，即可完成多个订单的支付。目前最多一次可支持50笔订单进行合单支付。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/combine/chapter3_1.shtml
 * </pre>
 */
@Data
@EqualsAndHashCode
@JsonIgnoreProperties()
public class CombineTransactionsAppRequest {
	/**
	 * <pre>
	 * 字段名：合单商户appid
	 * 变量名：combine_appid
	 * 是否必填：是
	 * 类型：string[1,32]
	 * 描述：
	 *  body 合单发起方的appid。
	 *  示例值：wxd678efh567hg6787 
	 * </pre>
	 */
	@JsonProperty(value = "combine_appid")
	private String combineAppid;

	/**
	 * <pre>
	 * 字段名：合单商户号
	 * 变量名：combine_mchid
	 * 是否必填：是
	 * 类型：string[1,32]
	 * 描述：
	 *  body合单发起方商户号。
	 *  示例值：1900000109 
	 * </pre>
	 */
	@JsonProperty(value = "combine_mchid")
	private String combineMchid;

	/**
	 * <pre>
	 * 字段名：合单商户订单号
	 * 变量名：combine_out_trade_no
	 * 是否必填：是
	 * 类型：string[1,32]
	 * 描述：
	 *  body合单支付总订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一 。
	 *  示例值：P20150806125346 
	 * </pre>
	 */
	@JsonProperty(value = "combine_out_trade_no")
	private String combineOutTradeNo;

	/**
	 * <pre>
	 * 字段名：+场景信息
	 * 变量名：scene_info
	 * 是否必填：否
	 * 类型：object
	 * 描述：body支付场景信息描述 
	 * </pre>
	 */
	@JsonProperty(value = "scene_info")
	private SceneInfo sceneInfo;

	/**
	 * <pre>
	 * 字段名：+子单信息
	 * 变量名：sub_orders
	 * 是否必填：是
	 * 类型：array
	 * 描述：
	 *  body最多支持子单条数：50 
	 *  
	 * </pre>
	 */
	@JsonProperty(value = "sub_orders")
	private List<SubOrders> subOrders;

	/**
	 * <pre>
	 * 字段名：+支付者
	 * 变量名：combine_payer_info
	 * 是否必填：否
	 * 类型：object
	 * 描述：body支付者信息 
	 * </pre>
	 */
	@JsonProperty(value = "combine_payer_info")
	private CombinePayerInfo combinePayerInfo;

	/**
	 * <pre>
	 * 字段名：交易起始时间
	 * 变量名：time_start
	 * 是否必填：否
	 * 类型：string[1,14]
	 * 描述：
	 *  body订单生成时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日 13点29分35秒。
	 *  示例值：2019-12-31T15:59:60+08:00 
	 * </pre>
	 */
	@JsonProperty(value = "time_start")
	private String timeStart;

	/**
	 * <pre>
	 * 字段名：交易结束时间
	 * 变量名：time_expire
	 * 是否必填：否
	 * 类型：string[1,14]
	 * 描述：
	 *  body订单失效时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日 13点29分35秒。
	 *  示例值：2019-12-31T15:59:60+08:00 
	 * </pre>
	 */
	@JsonProperty(value = "time_expire")
	private String timeExpire;

	/**
	 * <pre>
	 * 字段名：通知地址
	 * 变量名：notify_url
	 * 是否必填：是
	 * 类型：string[1,256]
	 * 描述：
	 *  body接收微信支付异步通知回调地址，通知url必须为直接可访问的URL，不能携带参数。 
	 *  格式: URL 
	 *  示例值：https://yourapp.com/notify 
	 * </pre>
	 */
	@JsonProperty(value = "notify_url")
	private String notifyUrl;

	@EqualsAndHashCode
	@Data
	@JsonIgnoreProperties()
	public static class SceneInfo {
		/**
		 * <pre>
		 * 字段名：商户端设备号
		 * 变量名：device_id
		 * 是否必填：否
		 * 类型：string[7,16]
		 * 描述：
		 *  终端设备号（门店号或收银设备ID） 。 
		 *  示例值：POS1:1 
		 * </pre>
		 */
		@JsonProperty(value = "device_id")
		private String deviceId;

		/**
		 * <pre>
		 * 字段名：用户终端IP
		 * 变量名：payer_client_ip
		 * 是否必填：是
		 * 类型：string[1,45]
		 * 描述：
		 *  用户端实际ip 
		 *  格式: ip(ipv4+ipv6) 
		 *  示例值：14.17.22.32 
		 * </pre>
		 */
		@JsonProperty(value = "payer_client_ip")
		private String payerClientIp;

	}

	@EqualsAndHashCode
	@Data
	@JsonIgnoreProperties()
	public static class SubOrders {
		/**
		 * <pre>
		 * 字段名：子单商户号
		 * 变量名：mchid
		 * 是否必填：是
		 * 类型：string[1,32]
		 * 描述：
		 *  子单发起方商户号，必须与发起方appid有绑定关系。 
		 *  示例值：1900000109 
		 * </pre>
		 */
		@JsonProperty(value = "mchid")
		private String mchid;

		/**
		 * <pre>
		 * 字段名：附加数据
		 * 变量名：attach
		 * 是否必填：是
		 * 类型：string[1,128]
		 * 描述：
		 *  附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。 
		 *  示例值：深圳分店 
		 * </pre>
		 */
		@JsonProperty(value = "attach")
		private String attach;

		/**
		 * <pre>
		 * 字段名：+订单金额
		 * 变量名：amount
		 * 是否必填：是
		 * 类型：object
		 * 描述：订单金额信息 
		 * </pre>
		 */
		@JsonProperty(value = "amount")
		private Amount amount;

		/**
		 * <pre>
		 * 字段名：子单商户订单号
		 * 变量名：out_trade_no
		 * 是否必填：是
		 * 类型：string[6,32]
		 * 描述：
		 *  商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。 
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
		 * 类型：string[1,32]
		 * 描述：
		 *  二级商户商户号，由微信支付生成并下发。 
		 *  注意：仅适用于电商平台 服务商
		 *  示例值：1900000109 
		 * </pre>
		 */
		@JsonProperty(value = "sub_mchid")
		private String subMchid;

		/**
		 * <pre>
		 * 字段名：商品描述
		 * 变量名：description
		 * 是否必填：是
		 * 类型：string[1,127]
		 * 描述：
		 *  商品简单描述。需传入应用市场上的APP名字-实际商品名称，例如：天天爱消除-游戏充值。
		 *  示例值：腾讯充值中心-QQ会员充值 
		 * </pre>
		 */
		@JsonProperty(value = "description")
		private String description;

		/**
		 * <pre>
		 * 字段名：+结算信息
		 * 变量名：settle_info
		 * 是否必填：否
		 * 类型：object
		 * 描述：结算信息 
		 * </pre>
		 */
		@JsonProperty(value = "settle_info")
		private SettleInfo settleInfo;

	}

	@EqualsAndHashCode
	@Data
	@JsonIgnoreProperties()
	public static class CombinePayerInfo {
		/**
		 * <pre>
		 * 字段名：用户标识
		 * 变量名：openid
		 * 是否必填：否
		 * 类型：string[1,128]
		 * 描述：
		 *  使用合单appid获取的对应用户openid。是用户在商户appid下的唯一标识。 
		 *  示例值：oUpF8uMuAJO_M2pxb1Q9zNjWeS6o 
		 * </pre>
		 */
		@JsonProperty(value = "openid")
		private String openid;

	}

	@EqualsAndHashCode
	@Data
	@JsonIgnoreProperties()
	public static class Amount {
		/**
		 * <pre>
		 * 字段名：标价金额
		 * 变量名：total_amount
		 * 是否必填：是
		 * 类型：int
		 * 描述：
		 *  子单金额，单位为分 
		 *  境外场景下，标价金额要超过商户结算币种的最小单位金额，例如结算币种为美元，则标价金额必须大于1美分
		 *  示例值：100 
		 * </pre>
		 */
		@JsonProperty(value = "total_amount")
		private Integer totalAmount;

		/**
		 * <pre>
		 * 字段名：标价币种
		 * 变量名：currency
		 * 是否必填：是
		 * 类型：string[1,8]
		 * 描述：
		 *  符合ISO 4217标准的三位字母代码，人民币：CNY 。
		 *  示例值：CNY 
		 * </pre>
		 */
		@JsonProperty(value = "currency")
		private String currency;

	}

	@EqualsAndHashCode
	@Data
	@JsonIgnoreProperties()
	public static class SettleInfo {
		/**
		 * <pre>
		 * 字段名：是否指定分账
		 * 变量名：profit_sharing
		 * 是否必填：否
		 * 类型：bool
		 * 描述：
		 *  是否指定分账，枚举值
		 *  true：是 
		 *  false：否 
		 *  示例值：true 
		 * </pre>
		 */
		@JsonProperty(value = "profit_sharing")
		private Boolean profitSharing;

		/**
		 * <pre>
		 * 字段名：补差金额
		 * 变量名：subsidy_amount
		 * 是否必填：否
		 * 类型：int64
		 * 描述：
		 *  SettleInfo.profit_sharing为true时，该金额才生效。 
		 *  注意：单笔订单最高补差金额为5000元
		 *  示例值：10 
		 * </pre>
		 */
		@JsonProperty(value = "subsidy_amount")
		private Integer subsidyAmount;

	}

}