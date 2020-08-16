package top.gabin.tools.auth;

import java.util.List;

public interface CacheService {
    <T> T cache(String cacheKey, T cacheData);
    <T> T get(String cacheKey, Class<T> cacheType);
    <T> List<T> getList(String cacheKey);
}
