package top.gabin.tools.response.ecommerce.applyments;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * <pre>
 * 普通服务商（支付机构、银行不可用），可使用本接口修改其进件、已签约的特约商户-结算账户信息。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_4.shtml
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplymentsModifySettlementResponse {
}