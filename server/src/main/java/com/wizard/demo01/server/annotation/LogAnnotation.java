package com.wizard.demo01.server.annotation;

import java.lang.annotation.*;

/**
 * @author wizard_0992
 * @date 2020/1/4 16:07
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    String value() default "";

}
