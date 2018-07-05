package me.douboo.springboot.memcached;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({ MemcachedAutoConfiguration.class })
@Documented
public @interface EnableMemcached {
}
