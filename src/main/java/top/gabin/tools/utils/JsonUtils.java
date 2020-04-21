package top.gabin.tools.utils;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * json工具类简单封装
 *
 * @author GaBin  on  15/8/29
 */
public class JsonUtils {

    /**
     * <pre>
     *     将对象转换成json字符串
     * </pre>
     *
     * @param object 实例对象
     * @return String
     */
    public static String bean2Json(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * <pre>
     *     将json字符串转化成对象
     * </pre>
     *
     * @param clazz 转化成的对象类型
     * @param json  需要转化的json字符串
     * @param <T>   泛型
     * @return T
     */
    public static <T> T json2Bean(Class<T> clazz, String json) {
        if (json == null) {
            return null;
        }
        if (json.contains("<html>")) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <pre>
     *     将json字符串转化成List对象
     * </pre>
     *
     * @param clazz Class类型
     * @param json  json字符串
     * @param <T>   泛型
     * @return List
     */
    public static <T> List<T> json2List(Class<T> clazz, String json) {
        if (json == null || json.contains("<html>")) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JavaType javaType = getCollectionType(objectMapper, ArrayList.class, clazz);
            return objectMapper.readValue(json, javaType);
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * @param clazz Class类型
     * @param json  json字符串
     * @param <T>   泛型
     * @return T
     */
    public static <T> Map<String, T> json2Map(Class<T> clazz, String json) {
        if (json == null || json.contains("<html>")) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JavaType javaType = getCollectionType(objectMapper, clazz);
            return objectMapper.readValue(json, javaType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JavaType getCollectionType(ObjectMapper objectMapper, Class<?> collectionClass, Class<?>... elementClasses) {
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        return typeFactory.constructParametricType(collectionClass, elementClasses);
    }

    public static JavaType getCollectionType(ObjectMapper objectMapper, Class<?> elementClasse) {
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        return typeFactory.constructMapType(TreeMap.class, String.class, elementClasse);
    }

}
