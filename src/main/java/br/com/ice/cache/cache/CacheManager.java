package br.com.ice.cache.cache;

import br.com.ice.cache.annotation.Cache;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class CacheManager {

    private final Map<String, CacheKey> cacheMap = new HashMap<>();

    public Optional<?> get(String key) {

        CacheKey cacheKey = this.cacheMap.get(key);

        if (cacheKey == null) {
            return Optional.empty();
        }

        if (cacheKey.isExpired()) {
            this.cacheMap.remove(key);
            return Optional.empty();
        }

        return Optional.of(cacheKey.value());
    }

    public void put(Cache cache, Object result) {
        long expiresAt = System.currentTimeMillis() + cache.expire();
        cacheMap.put(cache.key(), new CacheKey(result, expiresAt));
    }
}
