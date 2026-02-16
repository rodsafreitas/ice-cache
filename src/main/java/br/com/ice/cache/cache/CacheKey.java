package br.com.ice.cache.cache;

public class CacheKey {

    private final Object value;
    private final long expiresAt;

    public CacheKey(Object value, long expiresAt) {
        this.value = value;
        this.expiresAt = expiresAt;
    }

    public Object value() {
        return value;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expiresAt;
    }
}
