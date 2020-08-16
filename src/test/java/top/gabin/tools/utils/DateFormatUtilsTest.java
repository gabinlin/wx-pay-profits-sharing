package top.gabin.tools.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Date;

@Slf4j
public class DateFormatUtilsTest {

    @Test
    public void testFormatRFC3339ToString() {
        log.info(DateFormatUtils.formatRFC3339ToString(new Date()));
    }

}
