package top.gabin.tools;

import top.gabin.tools.request.pay.combine.CombineTransactionsJsRequest;

import java.util.Map;

public interface ProfitsSharingService {

    /**
     * <pre>
     * 合单下单-JS支付API.
     * 详见https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/combine/chapter3_2.shtml
     * 使用合单支付接口，用户只输入一次密码，即可完成多个订单的支付。目前最多一次可支持50笔订单进行合单支付。
     * 注意：
     * • 订单如果需要进行抽佣等，需要在合单中指定需要进行分账（profit_sharing为true）；指定后，交易资金进入二级商户账户，处于冻结状态，可在后续使用分账接口进行分账，利用分账完结进行资金解冻，实现抽佣和对二级商户的账期。
     * • 合单中同一个二级商户只允许有一笔子订单。
     * 接口地址：https://api.mch.weixin.qq.com/v3/combine-transactions/jsapi
     * </pre>
     *
     * @param request 微信订单号
     * @return String 预支付交易会话标识,数字和字母。微信生成的预支付会话标识，用于后续接口调用使用。
     */
    String combineTransactions(CombineTransactionsJsRequest request);


    /**
     * @param prePayId 预下单ID
     * @param appId    appID
     * @return 用于js调起支付的参数
     */
    Map<String, String> getStringStringMap(String prePayId, String appId);
}
