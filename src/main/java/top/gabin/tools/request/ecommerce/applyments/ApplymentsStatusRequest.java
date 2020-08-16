package top.gabin.tools.request.ecommerce.applyments;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 * 电商平台通过查询申请状态API查询二级商户入驻申请结果。该查询服务提供两种查询方式（两种查询方式返回结果一致）： 方式1：业务申请编号查询申请状态； 方式2：申请单号查询申请状态。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_2.shtml
 * </pre>
 */
@Data
@EqualsAndHashCode
@JsonIgnoreProperties("applymentId")
public class ApplymentsStatusRequest {
	/**
	 * <pre>
	 * 字段名：微信支付申请单号
	 * 变量名：applyment_id
	 * 是否必填：是
	 * 类型：uint64
	 * 描述：
	 *  path申请单的主键，唯一定义此资源的标识。
	 *  示例值：2000002124775691 
	 * </pre>
	 */
	@JsonIgnore
	@JsonProperty(value = "applyment_id")
	private String applymentId;

}