package com.gupaoedu.vip.spring.v2.framework.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowrire {
    String value() default "";

}
