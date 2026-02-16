package br.com.ice.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Rodrigo de Freitas
 * Created as a simple cache for spring projects.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {

    //Optional key for the cache, if not provided, it will use the method name as key
    String key() default "";

    //Time in milliseconds to expire the cache, default is 5 seconds
    long expire() default 5000;
}

