package com.univ.reflection.annotatedType;

/**
 * @author univ
 * date 2023/9/7
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 要能作用于泛型，必须有ElementType.TYPE_USE
 */
@Target({ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface M1 {
    String value() default "aaa";
}
