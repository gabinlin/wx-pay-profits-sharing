package top.gabin.tools.request.ecommerce.profitsharing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 * 1. 电商平台可通过此接口添加分账接收方，建立分账接收方列表。后续通过发起分账请求，将电商平台下的二级商户结算后的资金，分给分账接收方列表中具体的分账接收方。 2. 添加的分账接收方统一都在电商平台维度进行管理，其他二级商户，均可向该分账接收方列表中的接收方进行分账，避免在二级商户维度重复维护。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/profitsharing/chapter3_7.shtml
 * </pre>
 */
@Data
@EqualsAndHashCode
@JsonIgnoreProperties()
public class ProfitSharingAddReceiverRequest {
	/**
	 * <pre>
	 * 字段名：公众账号ID
	 * 变量名：appid
	 * 是否必填：是
	 * 类型：string[1,32]
	 * 描述：
	 *  body 电商平台的appid（公众号APPID或者小程序APPID） 
	 *  示例值：wx8888888888888888 
	 * </pre>
	 */
	@JsonProperty(value = "appid")
	private String appid;

	/**
	 * <pre>
	 * 字段名：接收方类型
	 * 变量名：type
	 * 是否必填：是
	 * 类型：string[1,32]
	 * 描述：
	 *  body 分账接收方的类型，枚举值： 
	 *  MERCHANT_ID：商户
	 *  PERSONAL_OPENID：个人 
	 *  示例值：MERCHANT_ID 
	 * </pre>
	 */
	@JsonProperty(value = "type")
	private String type;

	/**
	 * <pre>
	 * 字段名：接收方账号
	 * 变量名：account
	 * 是否必填：是
	 * 类型：string[1,64]
	 * 描述：
	 *  body 分账接收方的账号
	 *  类型是MERCHANT_ID时，是商户号
	 *  类型是PERSONAL_OPENID时，是个人openid，openid获取方法 
	 *  示例值：190001001 
	 * </pre>
	 */
	@JsonProperty(value = "account")
	private String account;

	/**
	 * <pre>
	 * 字段名：接收方名称
	 * 变量名：name
	 * 是否必填：条件选填
	 * 类型：string[1,256]
	 * 描述：
	 *  body 分账接收方的名称，当type为MERCHANT_ID时，接收方名称是商户全称。 
	 *  示例值：张三网络公司 
	 * </pre>
	 */
	@JsonProperty(value = "name")
	private String name;

	/**
	 * <pre>
	 * 字段名：接收方名称的密文
	 * 变量名：encrypted_name
	 * 是否必填：否
	 * 类型：string[1,10240]
	 * 描述：
	 *  body 1、分账接收方类型是PERSONAL_OPENID时，是个人姓名的密文（选传，传则校验）
	 *  此字段的加密的方式为：
	 *  2、使用微信支付平台证书中的公钥
	 *  3、使用RSAES-OAEP算法进行加密
	 *  4、将请求中HTTP头部的Wechatpay-Serial设置为证书序列号 
	 *  字段加密: 使用APIv3定义的方式加密
	 *  示例值：hu89ohu89ohu89o 
	 * </pre>
	 */
	@JsonProperty(value = "encrypted_name")
	private String encryptedName;

	/**
	 * <pre>
	 * 字段名：与分账方的关系类型
	 * 变量名：relation_type
	 * 是否必填：是
	 * 类型：string[1,32]
	 * 描述：
	 *  body 子商户与接收方的关系。 
	 *  枚举值： 
	 *  SUPPLIER：供应商 
	 *  DISTRIBUTOR：分销商 
	 *  SERVICE_PROVIDER：服务商 
	 *  PLATFORM：平台 
	 *  OTHERS：其他 
	 *  示例值：SUPPLIER 
	 * </pre>
	 */
	@JsonProperty(value = "relation_type")
	private String relationType;

}