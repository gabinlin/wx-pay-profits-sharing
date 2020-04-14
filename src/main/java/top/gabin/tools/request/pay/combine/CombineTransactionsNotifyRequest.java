package top.gabin.tools.request.pay.combine;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * <pre>
 * 微信支付通过支付通知接口将用户支付成功消息通知给商户
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/combine/chapter3_7.shtml
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CombineTransactionsNotifyRequest {
	/**
	 * <pre>
	 * 字段名：通知ID
	 * 变量名：id
	 * 是否必填：是
	 * 类型：string(32)
	 * 描述：
	 *  通知的唯一ID 
	 *  示例值：EV-2018022511223320873 
	 * </pre>
	 */
	@JsonProperty(value = "id")
	private String id;

	/**
	 * <pre>
	 * 字段名：通知创建时间
	 * 变量名：create_time
	 * 是否必填：是
	 * 类型：string(16)
	 * 描述：
	 *  通知创建的时间，格式为yyyyMMddHHmmss 
	 *  示例值：20180225112233 
	 * </pre>
	 */
	@JsonProperty(value = "create_time")
	private String createTime;

	/**
	 * <pre>
	 * 字段名：通知类型
	 * 变量名：event_type
	 * 是否必填：是
	 * 类型：string(32)
	 * 描述：
	 *  通知的类型，支付成功通知的类型为TRANSACTION.SUCCESS 
	 *  示例值：TRANSACTION.SUCCESS 
	 * </pre>
	 */
	@JsonProperty(value = "event_type")
	private String eventType;

	/**
	 * <pre>
	 * 字段名：通知数据类型
	 * 变量名：resource_type
	 * 是否必填：是
	 * 类型：string(32)
	 * 描述：
	 *  通知的资源数据类型，支付成功通知为encrypt-resource 
	 *  示例值：encrypt-resource 
	 * </pre>
	 */
	@JsonProperty(value = "resource_type")
	private String resourceType;

	/**
	 * <pre>
	 * 字段名：+通知数据
	 * 变量名：resource
	 * 是否必填：是
	 * 类型：object
	 * 描述：
	 *  通知资源数据
	 *  json格式，见示例 
	 * </pre>
	 */
	@JsonProperty(value = "resource")
	private Resource resource;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getEventType() {
		return this.eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getResourceType() {
		return this.resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public Resource getResource() {
		return this.resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Resource {
		/**
		 * <pre>
		 * 字段名：加密算法类型
		 * 变量名：algorithm
		 * 是否必填：是
		 * 类型：string(32)
		 * 描述：
		 *  对开启结果数据进行加密的加密算法，目前只支持AEAD_AES_256_GCM 
		 *  示例值：AEAD_AES_256_GCM 
		 * </pre>
		 */
		@JsonProperty(value = "algorithm")
		private String algorithm;

		/**
		 * <pre>
		 * 字段名：数据密文
		 * 变量名：ciphertext
		 * 是否必填：是
		 * 类型：string(1048576)
		 * 描述：
		 *  Base64编码后的开启/停用结果数据密文
		 *  示例值：sadsadsadsad 
		 * </pre>
		 */
		@JsonProperty(value = "ciphertext")
		private String ciphertext;

		/**
		 * <pre>
		 * 字段名：附加数据
		 * 变量名：associated_data
		 * 是否必填：否
		 * 类型：string(16)
		 * 描述：
		 *  附加数据 
		 *  示例值：fdasfwqewlkja484w 
		 * </pre>
		 */
		@JsonProperty(value = "associated_data")
		private String associatedData;

		/**
		 * <pre>
		 * 字段名：随机串
		 * 变量名：nonce
		 * 是否必填：是
		 * 类型：string(16)
		 * 描述：
		 *  加密使用的随机串 
		 *  示例值：fdasflkja484w 
		 * </pre>
		 */
		@JsonProperty(value = "nonce")
		private String nonce;

		public String getAlgorithm() {
			return this.algorithm;
		}

		public void setAlgorithm(String algorithm) {
			this.algorithm = algorithm;
		}

		public String getCiphertext() {
			return this.ciphertext;
		}

		public void setCiphertext(String ciphertext) {
			this.ciphertext = ciphertext;
		}

		public String getAssociatedData() {
			return this.associatedData;
		}

		public void setAssociatedData(String associatedData) {
			this.associatedData = associatedData;
		}

		public String getNonce() {
			return this.nonce;
		}

		public void setNonce(String nonce) {
			this.nonce = nonce;
		}

	}

}