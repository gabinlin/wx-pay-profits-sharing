package top.gabin.tools.request.ecommerce.profitsharing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * <pre>
 * 1. 电商平台可通过此接口添加分账接收方，建立分账接收方列表。后续通过发起分账请求，将电商平台下的二级商户结算后的资金，分给分账接收方列表中具体的分账接收方。 2. 添加的分账接收方统一都在电商平台维度进行管理，其他二级商户，均可向该分账接收方列表中的接收方进行分账，避免在二级商户维度重复维护。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/profitsharing/chapter3_7.shtml
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfitSharingAddReceiverRequest {
	/**
	 * <pre>
	 * 字段名：接收方类型
	 * 变量名：type
	 * 是否必填：是
	 * 类型：string（32）
	 * 描述：
	 *   分账接收方的类型，当前仅支持： 
	 *  MERCHANT_ID（商户号），填写微信支付分配的商户号。电商平台系统已默认添加为分账接收方，无需重复添加 
	 *  示例值：3008450740201411110007820472 
	 * </pre>
	 */
	@JsonProperty(value = "type")
	private String type;

	/**
	 * <pre>
	 * 字段名：接收方账号
	 * 变量名：account
	 * 是否必填：是
	 * 类型：string（64）
	 * 描述：
	 *   分账接收方的账号，当type为MERCHANT_ID时，接收方账号是商户号。 
	 *  示例值：190001001 
	 * </pre>
	 */
	@JsonProperty(value = "account")
	private String account;

	/**
	 * <pre>
	 * 字段名：接收方名称
	 * 变量名：name
	 * 是否必填：是
	 * 类型：string（256）
	 * 描述：
	 *   分账接收方的名称，当type为MERCHANT_ID时，接收方名称是商户全称。 
	 *  示例值：张三网络公司 
	 * </pre>
	 */
	@JsonProperty(value = "name")
	private String name;

	/**
	 * <pre>
	 * 字段名：与分账方的关系类型
	 * 变量名：relation_type
	 * 是否必填：是
	 * 类型：string（32）
	 * 描述：
	 *   子商户与接收方的关系。 
	 *  枚举值： 
	 *  SUPPLIER：供应商 
	 *  DISTRIBUTOR：分销商 
	 *  SERVICE_PROVIDER：服务商 
	 *  OTHERS：其他 
	 *  示例值：SUPPLIER 
	 * </pre>
	 */
	@JsonProperty(value = "relation_type")
	private String relationType;

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelationType() {
		return this.relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}

}