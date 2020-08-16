package top.gabin.tools.auth;

import java.util.List;
import java.util.Map;

public interface CacheService {
    <T> void cache(String cacheKey, T cacheData);
    <T> T get(String cacheKey, Class<T> cacheType);
    <Key, Val> Map<Key, Val> getMap(String cacheKey);
    <T> List<T> getList(String cacheKey);
}
