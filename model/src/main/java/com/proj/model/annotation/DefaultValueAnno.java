package com.proj.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 默认值检测并赋值的自定义注解
 *
 * @author dong.ning
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultValueAnno {

    String defaultValue() default "";

    String werkName() default "";

}
