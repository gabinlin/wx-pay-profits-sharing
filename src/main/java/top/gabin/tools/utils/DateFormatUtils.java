package top.gabin.tools.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatUtils {

    /**
     * 格式化日期为RFC3339格式，某些接口需要用
     * @param date
     * @return
     */
    public static String formatRFC3339ToString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.US).format(date);
    }

}
