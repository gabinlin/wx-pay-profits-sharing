package top.gabin.tools.utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class BuildDTOUtilsTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Test
    public void test1() throws IOException {
        BuilderDTOUtils builderDTOUtils = new BuilderDTOUtils();
        String url = "https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_1.shtml";
        builderDTOUtils.builder(url, "src/main/java/top/gabin/tools/", "Applyments");
    }



}
