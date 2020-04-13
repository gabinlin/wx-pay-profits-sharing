package top.gabin.tools.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author GaBin on  2016/12/9
 */
public class Md5Utils {


    /**
     * 按字典排序生成MD5签名
     *
     * @param packageParams
     * @param signKey
     * @return
     */
    public static String createSign(Map<String, String> packageParams, String signKey) {
        TreeMap sortedMap = new TreeMap(packageParams);
        StringBuilder toSign = new StringBuilder();

        for (Object o : sortedMap.keySet()) {
            String key = (String) o;
            String value = packageParams.get(key);
            if (null != value && !"".equals(value) && !"sign".equals(key) && !"key".equals(key)) {
                toSign.append(key).append("=").append(value).append("&");
            }
        }

        toSign.append("key=").append(signKey);
        return DigestUtils.md5Hex(toSign.toString()).toUpperCase();
    }

    /**
     * 参数名Map按字典排序，并将参数名转换成小写，拼接成字符串（如：a=1&b=2&）并接上keyName=keyValue,然后MD5加密，生成大写签名
     *
     * @param packageParams
     * @param keyName
     * @param keyValue
     * @return
     */
    public static String createSign(Map<String, String> packageParams, String keyName, String keyValue) {
        TreeMap sortedMap = new TreeMap(packageParams);
        StringBuilder toSign = new StringBuilder();
        for (Object o : sortedMap.keySet()) {
            String key = (String) o;
            String value = packageParams.get(key);
            if (value != null && StringUtils.isNotBlank(value) && !key.equals("sign") && !key.equals(keyName)) {
                toSign.append(key.toLowerCase()).append("=").append(value).append("&");
            }
        }
        toSign.append(keyName + "=").append(keyValue);
        return DigestUtils.md5Hex(toSign.toString()).toUpperCase();
    }

    /**
     * md5加密
     *
     * @param content
     * @return
     */
    public static String encode(String content) {
        return DigestUtils.md5Hex(content).toUpperCase();
    }

    /**
     * @param contents
     * @return
     */
    public static String encodeLowerCase(Object... contents) {
        StringBuilder sign = new StringBuilder();
        for (Object content : contents) {
            sign.append(content);
        }
        return DigestUtils.md5Hex(sign.toString()).toLowerCase();
    }

}
