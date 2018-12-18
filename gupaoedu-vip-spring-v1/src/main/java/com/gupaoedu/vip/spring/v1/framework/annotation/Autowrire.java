package com.gupaoedu.vip.spring.v1.framework.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowrire {
    String value() default "";

}
