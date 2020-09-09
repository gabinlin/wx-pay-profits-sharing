package top.gabin.tools.utils;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jmh.annotations.*;

import java.util.Date;

@Slf4j
public class DateFormatUtilsTest {

    @Benchmark // jmh基准测试注解，默认只要有这个注解就可以
    @Warmup(iterations = 1, time = 3)   // 预热，JVM有一些优化策略，需要先预热才能更准确
    @Fork(5)    // 使用多少线程
    @BenchmarkMode(Mode.All) // 模式:如吞吐量
    @Measurement(iterations = 1, time = 3) // 一共测试多少遍，调用这个方法多少次
//    @Test
    public void testFormatRFC3339ToString() {
        log.info(DateFormatUtils.formatRFC3339ToString(new Date()));
    }

}
