package br.com.ice.cache.config;

import br.com.ice.cache.annotation.Cache;
import br.com.ice.cache.cache.CacheManager;
import br.com.ice.cache.inteceptor.CacheInteceptor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Stream;

@Configuration
public class CacheProxyConfiguration {

    @Bean
    public CacheManager cacheManager() {
        return new CacheManager();
    }

    @Bean
    public CacheInteceptor cacheInteceptor(CacheManager cacheManager) {
        return new CacheInteceptor(cacheManager);
    }

    @Bean
    public BeanPostProcessor cacheableProxyPostProcessor(CacheInteceptor cacheInteceptor) {
        return new BeanPostProcessor() {

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) {
                if (hasCacheableMethod(bean.getClass())) {
                    var proxyFactory = new ProxyFactoryBean();
                    proxyFactory.setTarget(bean);
                    proxyFactory.addAdvice(cacheInteceptor);
                    proxyFactory.setProxyTargetClass(true);
                    return proxyFactory.getObject();
                }
                return bean;
            }

            private boolean hasCacheableMethod(Class<?> clazz) {
                return Stream.concat(Stream.of(clazz.getDeclaredMethods()),
                                Stream.of(clazz.getMethods()))
                        .anyMatch(method -> method.isAnnotationPresent(Cache.class));
            }
        };
    }
}

