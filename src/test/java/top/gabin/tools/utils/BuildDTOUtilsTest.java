package top.gabin.tools.utils;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuildDTOUtilsTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private BuilderDTOUtils builderDTOUtils;
    private String path = "src/main/java/top/gabin/tools/";

    @Before
    public void init() {
        builderDTOUtils = new BuilderDTOUtils();;
    }

    @Test
    public void test1() throws IOException {
        String url = "https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_1.shtml";
        builderDTOUtils.builder(url, "src/main/java/top/gabin/tools/", "Applyments");
    }

    @Test
    public void test2() throws IOException {
        String url = "https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_2.shtml";
        builderDTOUtils.builder(url, "src/main/java/top/gabin/tools/", "ApplymentsDetail");
    }

    class Params {
        private String url;
        private String className;

        public Params(String url, String className) {
            this.url = url;
            this.className = className;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }
    }

    @Test
    public void test3() throws IOException {
        List<Params> params = new ArrayList<>();
//        params.add(new Params("https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_1.shtml", "Applyments"));
//        params.add(new Params("https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_2.shtml", "ApplymentsDetail"));
//        params.add(new Params("https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_3.shtml", "DownCertificates"));
//        params.add(new Params("https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_4.shtml", "ModifySettlement"));
//        params.add(new Params("https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_5.shtml", "SettlementDetail"));


        params.add(new Params("https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/combine/chapter3_1.shtml", "CombineTransactionsApp"));


        for (Params param : params) {
            builderDTOUtils.builder(param.getUrl(), path, param.getClassName());
        }
    }



}
