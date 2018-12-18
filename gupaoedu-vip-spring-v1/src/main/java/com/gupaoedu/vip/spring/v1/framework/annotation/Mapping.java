package com.gupaoedu.vip.spring.v1.framework.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Mapping {
    String value() default "";
}
