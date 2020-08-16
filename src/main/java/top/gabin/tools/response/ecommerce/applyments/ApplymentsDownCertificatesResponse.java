package top.gabin.tools.response.ecommerce.applyments;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import top.gabin.tools.response.AbstractResponse;

import java.util.List;


/**
 * <pre>
 * 由于证书有效期限制和交易安全的原因，微信支付会不定期的更换平台证书。微信支付提供了一系列接口，帮助商户后台系统实现平滑的证书更换。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_3.shtml
 * 状态码	错误码	描述	解决方案
 * 500	SYSTEMERROR	系统错误	系统异常，请使用相同参数稍后重新调用
 * 400	PARAM_ERROR	参数错误	请使用正确的参数重新调用
 * 400	RESOURCE_ALREADY_EXISTS	存在流程进行中的申请单，请检查是否重入	可通过查询申请状态查看此申请单的申请状态，或更换out_request_no提交新的申请单
 * 403	NO_AUTH	商户权限异常	请确认是否已经开通相关权限
 * 429	RATE_LIMITED	频率限制	请降低调用频率
 * 404	RESOURCE_NOT_EXISTS	申请单不存在	确认入参，传入正确的申请单编号
 * </pre>
 */
@Data
public class ApplymentsDownCertificatesResponse extends AbstractResponse {
	@JsonProperty(value = "data")
	private List<Data> data;

	@lombok.Data
	public static class Data {
		/**
		 * <pre>
		 * 字段名：序列号
		 * 变量名：serial_no
		 * 是否必填：是
		 * 类型：string(32)
		 * 描述：证书的序列号
		 * </pre>
		 */
		@JsonProperty(value = "serial_no")
		private String serialNo;

		/**
		 * <pre>
		 * 字段名：证书
		 * 变量名：encrypt_certificate
		 * 是否必填：是
		 * 类型：string(4096)
		 * 描述：证书内容
		 * </pre>
		 */
		@JsonProperty(value = "encrypt_certificate")
		private EncryptCertificate encryptCertificate;
	}

	@lombok.Data
	public static class EncryptCertificate {
		private String algorithm;
		private String nonce;
		private String associated_data;
		private String ciphertext;
	}
}