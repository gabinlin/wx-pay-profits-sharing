package top.gabin.tools.utils;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.gabin.tools.ProfitsSharingService;
import top.gabin.tools.ProfitsSharingServiceImpl;
import top.gabin.tools.config.ProfitsSharingConfig;
import top.gabin.tools.request.pay.combine.CombineTransactionsJsRequest;

import java.util.Map;
import java.util.Optional;

public class ProfitsSharingServiceTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ProfitsSharingService profitsSharingService;

    @Before
    public void service() {
        profitsSharingService = new ProfitsSharingServiceImpl(new ProfitsSharingConfig());
    }


    @Test
    public void combineTransactionsTest() {
        Optional<String> preId = profitsSharingService.combineTransactions(new CombineTransactionsJsRequest());
        logger.info(preId.orElse(""));
    }

    @Test
    public void getStringStringMapTest() {
        Map<String, String> stringStringMap = profitsSharingService.getJsPayParams("wx201410272009395522657a690389285100", "wx8888888888888888");
        logger.info(JsonUtils.bean2Json(stringStringMap));
    }

}
