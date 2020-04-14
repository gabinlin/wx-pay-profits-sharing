package top.gabin.tools.response.pay.combine;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


/**
 * <pre>
 * 电商平台通过合单查询订单API查询订单状态，完成下一步的业务逻辑。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/combine/chapter3_3.shtml
 * 状态码	错误码	描述	解决方案
 * 202	USERPAYING	用户支付中，需要输入密码	等待5秒，然后调用被扫订单结果查询API，查询当前订单的不同状态，决定下一步的操作
 * 403	TRADE_ERROR	交易错误	因业务原因交易失败，请查看接口返回的详细信息
 * 500	SYSTEMERROR	系统错误	系统异常，请用相同参数重新调用
 * 401	SIGN_ERROR	签名错误	请检查签名参数和方法是否都符合签名算法要求
 * 403	RULELIMIT	业务规则限制	因业务规则限制请求频率，请查看接口返回的详细信息
 * 400	PARAM_ERROR	参数错误	请根据接口返回的详细信息检查请求参数
 * 403	OUT_TRADE_NO_USED	商户订单号重复	请核实商户订单号是否重复提交
 * 404	ORDERNOTEXIST	订单不存在	请检查订单是否发起过交易
 * 400	ORDER_CLOSED	订单已关闭	当前订单已关闭，请重新下单
 * 500	OPENID_MISMATCH	openid和appid不匹配	请确认openid和appid是否匹配
 * 403	NOTENOUGH	余额不足	用户帐号余额不足，请用户充值或更换支付卡后再支付
 * 403	NOAUTH	商户无权限	请商户前往申请此接口相关权限
 * 400	MCH_NOT_EXISTS	商户号不存在	请检查商户号是否正确
 * 500	INVALID_TRANSACTIONID	订单号非法	请检查微信支付订单号是否正确
 * 400	INVALID_REQUEST	无效请求	请根据接口返回的详细信息检查
 * 429	FREQUENCY_LIMITED	频率超限	请降低请求接口频率
 * 500	BANKERROR	银行系统异常	银行系统异常，请用相同参数重新调用
 * 400	APPID_MCHID_NOT_MATCH	appid和mch_id不匹配	请确认appid和mch_id是否匹配
 * 403	ACCOUNTERROR	账号异常	用户账号异常，无需更多操作
 * 			
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CombineTransactionsDetailResponse {
	/**
	 * <pre>
	 * 字段名：合单商户appid
	 * 变量名：combine_appid
	 * 是否必填：是
	 * 类型：string(32)
	 * 描述：
	 *  合单发起方的appid。 
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
	 * 类型：string(32)
	 * 描述：
	 *  合单发起方商户号。 
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
	 * 类型：string(32)
	 * 描述：
	 *  合单支付总订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
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
	 * 描述：支付场景信息描述 
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
	 *  最多支持子单条数：50 
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
	 * 描述：示例值：见请求示例 
	 * </pre>
	 */
	@JsonProperty(value = "combine_payer_info")
	private CombinePayerInfo combinePayerInfo;

	public String getCombineAppid() {
		return this.combineAppid;
	}

	public void setCombineAppid(String combineAppid) {
		this.combineAppid = combineAppid;
	}

	public String getCombineMchid() {
		return this.combineMchid;
	}

	public void setCombineMchid(String combineMchid) {
		this.combineMchid = combineMchid;
	}

	public String getCombineOutTradeNo() {
		return this.combineOutTradeNo;
	}

	public void setCombineOutTradeNo(String combineOutTradeNo) {
		this.combineOutTradeNo = combineOutTradeNo;
	}

	public SceneInfo getSceneInfo() {
		return this.sceneInfo;
	}

	public void setSceneInfo(SceneInfo sceneInfo) {
		this.sceneInfo = sceneInfo;
	}

	public List<SubOrders> getSubOrders() {
		return this.subOrders;
	}

	public void setSubOrders(List<SubOrders> subOrders) {
		this.subOrders = subOrders;
	}

	public CombinePayerInfo getCombinePayerInfo() {
		return this.combinePayerInfo;
	}

	public void setCombinePayerInfo(CombinePayerInfo combinePayerInfo) {
		this.combinePayerInfo = combinePayerInfo;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class SceneInfo {
		/**
		 * <pre>
		 * 字段名：商户端设备号
		 * 变量名：device_id
		 * 是否必填：否
		 * 类型：string(16)
		 * 描述：
		 *  终端设备号（门店号或收银设备ID） 。
		 *  特殊规则：长度最小7个字节 
		 *  示例值：POS1:1 
		 * </pre>
		 */
		@JsonProperty(value = "device_id")
		private String deviceId;

		public String getDeviceId() {
			return this.deviceId;
		}

		public void setDeviceId(String deviceId) {
			this.deviceId = deviceId;
		}

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class SubOrders {
		/**
		 * <pre>
		 * 字段名：子单商户号
		 * 变量名：mchid
		 * 是否必填：是
		 * 类型：string(32)
		 * 描述：
		 *  子单发起方商户号，必须与发起方Appid有绑定关系。 
		 *  示例值：1900000109 
		 * </pre>
		 */
		@JsonProperty(value = "mchid")
		private String mchid;

		/**
		 * <pre>
		 * 字段名：交易类型
		 * 变量名：trade_type
		 * 是否必填：是
		 * 类型：string(16)
		 * 描述：
		 *  枚举值： 
		 *  NATIVE：扫码支付 
		 *  JSAPI：公众号支付 
		 *  APP：APP支付
		 *  MWEB：H5支付 
		 *  示例值： JSAPI 
		 * </pre>
		 */
		@JsonProperty(value = "trade_type")
		private String tradeType;

		/**
		 * <pre>
		 * 字段名：交易状态
		 * 变量名：trade_state
		 * 是否必填：是
		 * 类型：string(32)
		 * 描述：
		 *  枚举值： 
		 *  SUCCESS：支付成功 
		 *  REFUND：转入退款 
		 *  NOTPAY：未支付 
		 *  CLOSED：已关闭 
		 *  USERPAYING：用户支付中
		 *  PAYERROR：支付失败(其他原因，如银行返回失败) 
		 *  示例值： SUCCESS 
		 * </pre>
		 */
		@JsonProperty(value = "trade_state")
		private String tradeState;

		/**
		 * <pre>
		 * 字段名：付款银行
		 * 变量名：bank_type
		 * 是否必填：否
		 * 类型：string(16)
		 * 描述：
		 *  银行类型，采用字符串类型的银行标识。 
		 *  示例值：CMC 
		 * </pre>
		 */
		@JsonProperty(value = "bank_type")
		private String bankType;

		/**
		 * <pre>
		 * 字段名：附加信息
		 * 变量名：attach
		 * 是否必填：是
		 * 类型：string(128)
		 * 描述：
		 *  附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。 
		 *  示例值：深圳分店 
		 * </pre>
		 */
		@JsonProperty(value = "attach")
		private String attach;

		/**
		 * <pre>
		 * 字段名：支付完成时间
		 * 变量名：success_time
		 * 是否必填：是
		 * 类型：string(32)
		 * 描述：
		 *  订单支付时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss:sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss:sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日13点29分35秒。 
		 *  示例值： 2015-05-20T13:29:35.120+08:00 
		 * </pre>
		 */
		@JsonProperty(value = "success_time")
		private String successTime;

		/**
		 * <pre>
		 * 字段名：微信订单号
		 * 变量名：transaction_id
		 * 是否必填：是
		 * 类型：string(32)
		 * 描述：
		 *  微信支付订单号。 
		 *  示例值：1009660380201506130728806387 
		 * </pre>
		 */
		@JsonProperty(value = "transaction_id")
		private String transactionId;

		/**
		 * <pre>
		 * 字段名：子单商户订单号
		 * 变量名：out_trade_no
		 * 是否必填：是
		 * 类型：string(32)
		 * 描述：
		 *  商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。 
		 *  特殊规则：最小字符长度为6 
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
		 * 类型：string(32)
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
		 * 字段名：+订单金额
		 * 变量名：amount
		 * 是否必填：是
		 * 类型：object
		 * 描述：订单金额信息 
		 * </pre>
		 */
		@JsonProperty(value = "amount")
		private Amount amount;

		public String getMchid() {
			return this.mchid;
		}

		public void setMchid(String mchid) {
			this.mchid = mchid;
		}

		public String getTradeType() {
			return this.tradeType;
		}

		public void setTradeType(String tradeType) {
			this.tradeType = tradeType;
		}

		public String getTradeState() {
			return this.tradeState;
		}

		public void setTradeState(String tradeState) {
			this.tradeState = tradeState;
		}

		public String getBankType() {
			return this.bankType;
		}

		public void setBankType(String bankType) {
			this.bankType = bankType;
		}

		public String getAttach() {
			return this.attach;
		}

		public void setAttach(String attach) {
			this.attach = attach;
		}

		public String getSuccessTime() {
			return this.successTime;
		}

		public void setSuccessTime(String successTime) {
			this.successTime = successTime;
		}

		public String getTransactionId() {
			return this.transactionId;
		}

		public void setTransactionId(String transactionId) {
			this.transactionId = transactionId;
		}

		public String getOutTradeNo() {
			return this.outTradeNo;
		}

		public void setOutTradeNo(String outTradeNo) {
			this.outTradeNo = outTradeNo;
		}

		public String getSubMchid() {
			return this.subMchid;
		}

		public void setSubMchid(String subMchid) {
			this.subMchid = subMchid;
		}

		public Amount getAmount() {
			return this.amount;
		}

		public void setAmount(Amount amount) {
			this.amount = amount;
		}

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class CombinePayerInfo {
		/**
		 * <pre>
		 * 字段名：用户标识
		 * 变量名：openid
		 * 是否必填：是
		 * 类型：string(128)
		 * 描述：
		 *  使用合单appid获取的对应用户openid。是用户在商户appid下的唯一标识。 
		 *  示例值：oUpF8uMuAJO_M2pxb1Q9zNjWeS6o 
		 * </pre>
		 */
		@JsonProperty(value = "openid")
		private String openid;

		public String getOpenid() {
			return this.openid;
		}

		public void setOpenid(String openid) {
			this.openid = openid;
		}

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Amount {
		/**
		 * <pre>
		 * 字段名：标价金额
		 * 变量名：total_amount
		 * 是否必填：是
		 * 类型：int64
		 * 描述：
		 *  子单金额，单位为分。
		 *  示例值：100 
		 * </pre>
		 */
		@JsonProperty(value = "total_amount")
		private String totalAmount;

		/**
		 * <pre>
		 * 字段名：标价币种
		 * 变量名：currency
		 * 是否必填：否
		 * 类型：string(8)
		 * 描述：
		 *  符合ISO 4217标准的三位字母代码，人民币：CNY。 
		 *  示例值：CNY 
		 * </pre>
		 */
		@JsonProperty(value = "currency")
		private String currency;

		/**
		 * <pre>
		 * 字段名：现金支付金额
		 * 变量名：payer_amount
		 * 是否必填：是
		 * 类型：int64
		 * 描述：
		 *  订单现金支付金额。 
		 *  示例值： 10 
		 * </pre>
		 */
		@JsonProperty(value = "payer_amount")
		private String payerAmount;

		/**
		 * <pre>
		 * 字段名：现金支付币种
		 * 变量名：payer_currency
		 * 是否必填：否
		 * 类型：string(8)
		 * 描述：
		 *  货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY。 
		 *  示例值： CNY 
		 * </pre>
		 */
		@JsonProperty(value = "payer_currency")
		private String payerCurrency;

		public String getTotalAmount() {
			return this.totalAmount;
		}

		public void setTotalAmount(String totalAmount) {
			this.totalAmount = totalAmount;
		}

		public String getCurrency() {
			return this.currency;
		}

		public void setCurrency(String currency) {
			this.currency = currency;
		}

		public String getPayerAmount() {
			return this.payerAmount;
		}

		public void setPayerAmount(String payerAmount) {
			this.payerAmount = payerAmount;
		}

		public String getPayerCurrency() {
			return this.payerCurrency;
		}

		public void setPayerCurrency(String payerCurrency) {
			this.payerCurrency = payerCurrency;
		}

	}

}