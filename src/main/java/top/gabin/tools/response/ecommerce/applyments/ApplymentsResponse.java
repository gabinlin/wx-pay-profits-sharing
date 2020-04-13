package top.gabin.tools.response.ecommerce.applyments;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * <pre>
 * 电商平台，可使用该接口，帮助其二级商户进件成为微信支付商户。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_1.shtml
 * 状态码	错误码	描述	解决方案
 * 500	SYSTEMERROR	系统错误	系统异常，请使用相同参数稍后重新调用
 * 400	PARAM_ERROR	参数错误	请使用正确的参数重新调用
 * 400	RESOURCE_ALREADY_EXISTS	存在流程进行中的申请单，请检查是否重入	可通过查询申请状态查看此申请单的申请状态，或更换out_request_no提交新的申请单
 * 403	NO_AUTH	商户权限异常	请确认是否已经开通相关权限
 * 429	RATE_LIMITED	频率限制	请降低调用频率
 * 404	RESOURCE_NOT_EXISTS	申请单不存在	确认入参，传入正确的申请单编号
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplymentsResponse {
	/**
	 * <pre>
	 * 字段名：微信支付申请单号
	 * 变量名：applyment_id
	 * 是否必填：是
	 * 类型：uint64
	 * 描述：
	 *  微信支付分配的申请单号 。 
	 *  示例值：2000002124775691 
	 * </pre>
	 */
	@JsonProperty(value = "applyment_id")
	private String applymentId;

	/**
	 * <pre>
	 * 字段名：业务申请编号
	 * 变量名：out_request_no
	 * 是否必填：是
	 * 类型：string(124)
	 * 描述：
	 *  服务商自定义的商户唯一编号。每个编号对应一个申请单，每个申请单审核通过后会生成一个微信支付商户号。
	 *  示例值：APPLYMENT_00000000001 
	 * </pre>
	 */
	@JsonProperty(value = "out_request_no")
	private String outRequestNo;

	public String getApplymentId() {
		return this.applymentId;
	}

	public void setApplymentId(String applymentId) {
		this.applymentId = applymentId;
	}

	public String getOutRequestNo() {
		return this.outRequestNo;
	}

	public void setOutRequestNo(String outRequestNo) {
		this.outRequestNo = outRequestNo;
	}

}