package top.gabin.tools.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatUtils {

    // 使用线程变量，避免出现并发问题，SimpleDateFormat非线程安全
    private static volatile ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.US));

    /**
     * 格式化日期为RFC3339格式，某些接口需要用
     * @param date
     * @return
     */
    public static String formatRFC3339ToString(Date date) {
        SimpleDateFormat simpleDateFormat = simpleDateFormatThreadLocal.get();
        return simpleDateFormat.format(date);
    }

}
