package br.com.ice.cache.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import(CacheProxyConfiguration.class)
public class IceCacheAutoConfiguration {

}
