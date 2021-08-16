package top.gabin.tools.response.ecommerce.applyments;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.gabin.tools.response.AbstractResponse;

/**
 * <pre>
 * 服务商/电商平台（不包括支付机构、银行），可使用本接口，修改其进件且已签约特约商户/二级商户的结算银行账户。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_4.shtml
 * </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties()
public class ApplymentsModifySettlementResponse extends AbstractResponse {
}