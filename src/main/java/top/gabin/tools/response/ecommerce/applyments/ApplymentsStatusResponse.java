package top.gabin.tools.response.ecommerce.applyments;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import top.gabin.tools.response.AbstractResponse;

import java.util.List;


/**
 * <pre>
 * 电商平台通过查询申请状态API查询二级商户入驻申请结果。该查询服务提供两种查询方式（两种查询方式返回结果一致）： 方式1：业务申请编号查询申请状态； 方式2：申请单号查询申请状态。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_2.shtml
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
public class ApplymentsStatusResponse extends AbstractResponse {
	/**
	 * <pre>
	 * 字段名：申请状态
	 * 变量名：applyment_state
	 * 是否必填：否
	 * 类型：string(32)
	 * 描述：
	 *  枚举值：
	 *  CHECKING：资料校验中
	 *  ACCOUNT_NEED_VERIFY：待账户验证
	 *  AUDITING：审核中 
	 *  REJECTED：已驳回 
	 *  NEED_SIGN：待签约 
	 *  FINISH：完成 
	 *  FROZEN：已冻结 
	 *  示例值：FINISH 
	 * </pre>
	 */
	@JsonProperty(value = "applyment_state")
	private String applymentState;

	/**
	 * <pre>
	 * 字段名：申请状态描述
	 * 变量名：applyment_state_desc
	 * 是否必填：否
	 * 类型：string(32)
	 * 描述：
	 *  申请状态描述
	 *  示例值：“审核中” 
	 * </pre>
	 */
	@JsonProperty(value = "applyment_state_desc")
	private String applymentStateDesc;

	/**
	 * <pre>
	 * 字段名：签约链接
	 * 变量名：sign_url
	 * 是否必填：否
	 * 类型：string(256)
	 * 描述：
	 *  1、当申请状态为NEED_SIGN时才返回。
	 *  2、建议将链接转为二维码展示，需让申请单-管理者用微信扫码打开，完成签约。 
	 *  示例值：https://pay.weixin.qq.com/public/apply4ec_sign/s?applymentId=2000002126198476&sign=b207b673049a32c858f3aabd7d27c7ec 
	 * </pre>
	 */
	@JsonProperty(value = "sign_url")
	private String signUrl;

	/**
	 * <pre>
	 * 字段名：电商平台二级商户号
	 * 变量名：sub_mchid
	 * 是否必填：否
	 * 类型：string(32)
	 * 描述：
	 *  当申请状态为NEED_SIGN或FINISH时才返回。 
	 *  示例值：1542488631 
	 * </pre>
	 */
	@JsonProperty(value = "sub_mchid")
	private String subMchid;

	/**
	 * <pre>
	 * 字段名：+汇款账户验证信息
	 * 变量名：account_validation
	 * 是否必填：否
	 * 类型：object
	 * 描述：当申请状态为ACCOUNT_NEED_VERIFY 时有返回，可根据指引汇款，完成账户验证。 
	 * </pre>
	 */
	@JsonProperty(value = "account_validation")
	private AccountValidation accountValidation;

	/**
	 * <pre>
	 * 字段名：+驳回原因详情
	 * 变量名：audit_detail
	 * 是否必填：否
	 * 类型：array
	 * 描述：各项资料的审核情况。当申请状态为REJECTED或 FROZEN时才返回。 
	 * </pre>
	 */
	@JsonProperty(value = "audit_detail")
	private List<AuditDetail> auditDetail;

	/**
	 * <pre>
	 * 字段名：法人验证链接
	 * 变量名：legal_validation_url
	 * 是否必填：否
	 * 类型：string(256)
	 * 描述：
	 *  1、当申请状态为 
	 *  ACCOUNT_NEED_VERIFY，且通过系统校验的申请单，将返回链接。 
	 *  2、建议将链接转为二维码展示，让商户法人用微信扫码打开，完成账户验证。 
	 *  示例值： https://pay.weixin.qq.com/public/apply4ec_sign/s?applymentId=2000002126198476&sign=b207b673049a32c858f3aabd7d27c7ec 
	 * </pre>
	 */
	@JsonProperty(value = "legal_validation_url")
	private String legalValidationUrl;

	/**
	 * <pre>
	 * 字段名：业务申请编号
	 * 变量名：out_request_no
	 * 是否必填：是
	 * 类型：string(124)
	 * 描述：
	 *  提交接口填写的业务申请编号。 
	 *  示例值：APPLYMENT_00000000001 
	 * </pre>
	 */
	@JsonProperty(value = "out_request_no")
	private String outRequestNo;

	/**
	 * <pre>
	 * 字段名：微信支付申请单号
	 * 变量名：applyment_id
	 * 是否必填：否
	 * 类型：uint64
	 * 描述：
	 *  微信支付分配的申请单号。 
	 *  示例值：2000002124775691 
	 * </pre>
	 */
	@JsonProperty(value = "applyment_id")
	private String applymentId;

	public String getApplymentState() {
		return this.applymentState;
	}

	public void setApplymentState(String applymentState) {
		this.applymentState = applymentState;
	}

	public String getApplymentStateDesc() {
		return this.applymentStateDesc;
	}

	public void setApplymentStateDesc(String applymentStateDesc) {
		this.applymentStateDesc = applymentStateDesc;
	}

	public String getSignUrl() {
		return this.signUrl;
	}

	public void setSignUrl(String signUrl) {
		this.signUrl = signUrl;
	}

	public String getSubMchid() {
		return this.subMchid;
	}

	public void setSubMchid(String subMchid) {
		this.subMchid = subMchid;
	}

	public AccountValidation getAccountValidation() {
		return this.accountValidation;
	}

	public void setAccountValidation(AccountValidation accountValidation) {
		this.accountValidation = accountValidation;
	}

	public List<AuditDetail> getAuditDetail() {
		return this.auditDetail;
	}

	public void setAuditDetail(List<AuditDetail> auditDetail) {
		this.auditDetail = auditDetail;
	}

	public String getLegalValidationUrl() {
		return this.legalValidationUrl;
	}

	public void setLegalValidationUrl(String legalValidationUrl) {
		this.legalValidationUrl = legalValidationUrl;
	}

	public String getOutRequestNo() {
		return this.outRequestNo;
	}

	public void setOutRequestNo(String outRequestNo) {
		this.outRequestNo = outRequestNo;
	}

	public String getApplymentId() {
		return this.applymentId;
	}

	public void setApplymentId(String applymentId) {
		this.applymentId = applymentId;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class AccountValidation {
		/**
		 * <pre>
		 * 字段名：付款户名
		 * 变量名：account_name
		 * 是否必填：否
		 * 类型：uint64
		 * 描述：
		 *  需商户使用该户名的账户进行汇款。 
		 *  示例值： rDdICA3ZYXshYqeOSslSjSMf+MhhC4oaujiISFzq3AE+as7mAEDJly+DgRuVs74msmKUH8pl+3oA== 
		 * </pre>
		 */
		@JsonProperty(value = "account_name")
		private String accountName;

		/**
		 * <pre>
		 * 字段名：付款卡号
		 * 变量名：account_no
		 * 是否必填：否
		 * 类型：string(128)
		 * 描述：
		 *  结算账户为对私时会返回，商户需使用该付款卡号进行汇款。 
		 *  示例值：9nZYDEvBT4rDdICA3ZYXshYqeOSslSjSauAE+as7mAEDJly+DgRuVs74msmKUH8pl+3oA== 
		 * </pre>
		 */
		@JsonProperty(value = "account_no")
		private String accountNo;

		/**
		 * <pre>
		 * 字段名：汇款金额
		 * 变量名：pay_amount
		 * 是否必填：否
		 * 类型：string(32)
		 * 描述：
		 *  需要汇款的金额(单位：分)。 
		 *  示例值：124 
		 * </pre>
		 */
		@JsonProperty(value = "pay_amount")
		private String payAmount;

		/**
		 * <pre>
		 * 字段名：收款卡号
		 * 变量名：destination_account_number
		 * 是否必填：否
		 * 类型：string(128)
		 * 描述：
		 *  收款账户的卡号 
		 *  示例值：7222223333322332 
		 * </pre>
		 */
		@JsonProperty(value = "destination_account_number")
		private String destinationAccountNumber;

		/**
		 * <pre>
		 * 字段名：收款户名
		 * 变量名：destination_account_name
		 * 是否必填：否
		 * 类型：string(128)
		 * 描述：
		 *  收款账户名 
		 *  示例值：财付通支付科技有限公司 
		 * </pre>
		 */
		@JsonProperty(value = "destination_account_name")
		private String destinationAccountName;

		/**
		 * <pre>
		 * 字段名：开户银行
		 * 变量名：destination_account_bank
		 * 是否必填：否
		 * 类型：string(128)
		 * 描述：
		 *  收款账户的开户银行名称。 
		 *  示例值：招商银行威盛大厦支行 
		 * </pre>
		 */
		@JsonProperty(value = "destination_account_bank")
		private String destinationAccountBank;

		/**
		 * <pre>
		 * 字段名：省市信息
		 * 变量名：city
		 * 是否必填：否
		 * 类型：string(128)
		 * 描述：
		 *  收款账户的省市。 
		 *  示例值：深圳 
		 * </pre>
		 */
		@JsonProperty(value = "city")
		private String city;

		/**
		 * <pre>
		 * 字段名：备注信息
		 * 变量名：remark
		 * 是否必填：否
		 * 类型：string(128)
		 * 描述：
		 *  商户汇款时，需要填写的备注信息。 
		 *  示例值：入驻账户验证 
		 * </pre>
		 */
		@JsonProperty(value = "remark")
		private String remark;

		/**
		 * <pre>
		 * 字段名：汇款截止时间
		 * 变量名：deadline
		 * 是否必填：否
		 * 类型：string(20)
		 * 描述：
		 *  请在此时间前完成汇款。 
		 *  示例值：2018-12-1017:09:01 
		 * </pre>
		 */
		@JsonProperty(value = "deadline")
		private String deadline;

		public String getAccountName() {
			return this.accountName;
		}

		public void setAccountName(String accountName) {
			this.accountName = accountName;
		}

		public String getAccountNo() {
			return this.accountNo;
		}

		public void setAccountNo(String accountNo) {
			this.accountNo = accountNo;
		}

		public String getPayAmount() {
			return this.payAmount;
		}

		public void setPayAmount(String payAmount) {
			this.payAmount = payAmount;
		}

		public String getDestinationAccountNumber() {
			return this.destinationAccountNumber;
		}

		public void setDestinationAccountNumber(String destinationAccountNumber) {
			this.destinationAccountNumber = destinationAccountNumber;
		}

		public String getDestinationAccountName() {
			return this.destinationAccountName;
		}

		public void setDestinationAccountName(String destinationAccountName) {
			this.destinationAccountName = destinationAccountName;
		}

		public String getDestinationAccountBank() {
			return this.destinationAccountBank;
		}

		public void setDestinationAccountBank(String destinationAccountBank) {
			this.destinationAccountBank = destinationAccountBank;
		}

		public String getCity() {
			return this.city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getRemark() {
			return this.remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getDeadline() {
			return this.deadline;
		}

		public void setDeadline(String deadline) {
			this.deadline = deadline;
		}

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class AuditDetail {
		/**
		 * <pre>
		 * 字段名：参数名称
		 * 变量名：param_name
		 * 是否必填：否
		 * 类型：string(32)
		 * 描述：
		 *  提交申请单的资料项名称。 
		 *  示例值：id_card_copy 
		 * </pre>
		 */
		@JsonProperty(value = "param_name")
		private String paramName;

		/**
		 * <pre>
		 * 字段名：驳回原因
		 * 变量名：reject_reason
		 * 是否必填：否
		 * 类型：string(32)
		 * 描述：
		 *  提交资料项被驳回原因。 
		 *  示例值：身份证背面识别失败，请上传更清晰的身份证图片 
		 * </pre>
		 */
		@JsonProperty(value = "reject_reason")
		private String rejectReason;

		public String getParamName() {
			return this.paramName;
		}

		public void setParamName(String paramName) {
			this.paramName = paramName;
		}

		public String getRejectReason() {
			return this.rejectReason;
		}

		public void setRejectReason(String rejectReason) {
			this.rejectReason = rejectReason;
		}

	}

}