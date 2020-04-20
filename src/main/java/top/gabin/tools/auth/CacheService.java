package top.gabin.tools.auth;

public interface CacheService {
    <T> T cache(String cacheKey, T cacheData);
    <T> T get(String cacheKey, Class<T> cacheType);
}
