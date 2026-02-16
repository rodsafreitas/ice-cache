package br.com.ice.cache.inteceptor;

import br.com.ice.cache.annotation.Cache;
import br.com.ice.cache.cache.CacheManager;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CacheInteceptor implements MethodInterceptor {

    private final CacheManager cacheManager;

    public CacheInteceptor(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        Cache cache = methodInvocation.getMethod().getAnnotation(Cache.class);

        if (cache == null) {
            return methodInvocation.proceed();
        }

        var key = generateKey(cache, methodInvocation);
        var cachedValue = cacheManager.get(key);

        if (cachedValue.isPresent()) {
            return cachedValue.get();
        }

        var result = methodInvocation.proceed();
        cacheManager.put(cache, result);
        return result;
    }

    private String generateKey(Cache cache, MethodInvocation methodInvocation) {
        if (cache.key() != null && !cache.key().isEmpty()) {
            return cache.key();
        }

        return methodInvocation.getClass().getSimpleName()
                + "-" + methodInvocation.getMethod().getName()
                + "-" + Arrays.toString(methodInvocation.getArguments());

    }
}

