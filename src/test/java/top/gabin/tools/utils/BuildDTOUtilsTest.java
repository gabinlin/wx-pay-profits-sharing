package top.gabin.tools.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BuildDTOUtilsTest {

    private BuilderDTOUtils builderDTOUtils;
    private String path = "src/main/java/top/gabin/tools/";

    @Before
    public void init() {
        builderDTOUtils = new BuilderDTOUtils();;
    }

    @Data
    @AllArgsConstructor
    class Params {
        private String url;
        private String className;
    }

    @Test
    public void buildEntity() throws IOException {
        List<Params> params = new ArrayList<>();
        // 进件-二级分销商
        params.add(new Params("ecommerce/applyments/chapter3_1.shtml", "Applyments"));
        params.add(new Params("ecommerce/applyments/chapter3_2.shtml", "ApplymentsStatus"));
//        params.add(new Params("ecommerce/applyments/chapter3_3.shtml", "ApplymentsDownCertificates"));
        params.add(new Params("ecommerce/applyments/chapter3_4.shtml", "ApplymentsModifySettlement"));
        params.add(new Params("ecommerce/applyments/chapter3_5.shtml", "ApplymentsSettlementStatus"));


        // 合单
        params.add(new Params("pay/combine/chapter3_1.shtml", "CombineTransactionsApp"));
        params.add(new Params("pay/combine/chapter3_2.shtml", "CombineTransactionsJs"));
        params.add(new Params("pay/combine/chapter3_3.shtml", "CombineTransactionsStatus"));
        params.add(new Params("pay/combine/chapter3_4.shtml", "CombineTransactionsClose"));
        params.add(new Params("pay/combine/chapter3_5.shtml", "CombineTransactionsPayForApp"));
        params.add(new Params("pay/combine/chapter3_6.shtml", "CombineTransactionsPayForJs"));
        params.add(new Params("pay/combine/chapter3_8.shtml", "CombineTransactionsPayForSmallApp"));
        params.add(new Params("pay/combine/chapter3_7.shtml", "CombineTransactionsNotify"));

        // 补差
        params.add(new Params("ecommerce/subsidies/chapter3_1.shtml", "SubsidiesCreate"));
        params.add(new Params("ecommerce/subsidies/chapter3_2.shtml", "SubsidiesRefund"));
        params.add(new Params("ecommerce/subsidies/chapter3_3.shtml", "SubsidiesCancel"));

        // 分账
        params.add(new Params("ecommerce/profitsharing/chapter3_1.shtml", "ProfitSharingApply"));
        params.add(new Params("ecommerce/profitsharing/chapter3_2.shtml", "ProfitSharingQueryApply"));
        params.add(new Params("ecommerce/profitsharing/chapter3_3.shtml", "ProfitSharingRefund"));
        params.add(new Params("ecommerce/profitsharing/chapter3_4.shtml", "ProfitSharingQueryRefund"));
        params.add(new Params("ecommerce/profitsharing/chapter3_5.shtml", "ProfitSharingFinish"));
        params.add(new Params("ecommerce/profitsharing/chapter3_7.shtml", "ProfitSharingAddReceiver"));
        params.add(new Params("ecommerce/profitsharing/chapter3_8.shtml", "ProfitSharingRemoveReceiver"));
        params.add(new Params("ecommerce/profitsharing/chapter3_6.shtml", "ProfitSharingNotify"));

        // 退款
        params.add(new Params("ecommerce/refunds/chapter3_1.shtml", "RefundApply"));
        params.add(new Params("ecommerce/refunds/chapter3_2.shtml", "RefundQueryStatus"));
        params.add(new Params("ecommerce/refunds/chapter3_3.shtml", "RefundNotify"));

        // 余额查询
        params.add(new Params("ecommerce/amount/chapter3_1.shtml", "AmountOnlineOfSubMch"));
        params.add(new Params("ecommerce/amount/chapter3_2.shtml", "AmountDayEndOfSubMch"));
        params.add(new Params("ecommerce/amount/chapter3_3.shtml", "AmountOnlineOfPlatform"));
        params.add(new Params("ecommerce/amount/chapter3_4.shtml", "AmountDayEndOfPlatform"));

        // 提现
        params.add(new Params("ecommerce/fund/chapter3_2.shtml", "WithdrawForSubMch"));
        params.add(new Params("ecommerce/fund/chapter3_3.shtml", "WithdrawStatusForSubMch"));
        params.add(new Params("ecommerce/fund/chapter3_5.shtml", "WithdrawForPlatform"));
        params.add(new Params("ecommerce/fund/chapter3_6.shtml", "WithdrawStatusForPlatform"));
        params.add(new Params("ecommerce/fund/chapter3_4.shtml", "WithdrawExceptionLog"));

        // 账单
        params.add(new Params("pay/bill/chapter3_1.shtml", "BillOfTrade"));
        params.add(new Params("pay/bill/chapter3_2.shtml", "BillOfFundFlow"));
        params.add(new Params("pay/bill/chapter3_3.shtml", "BillDownload"));

        // 其他接口
        params.add(new Params("tool/chapter3_1.shtml", "ImageUpload"));


        for (Params param : params) {
            builderDTOUtils.builder("https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/" + param.getUrl(), path, param.getClassName());
        }
    }



}
