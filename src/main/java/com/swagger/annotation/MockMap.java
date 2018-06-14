package com.swagger.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 模拟数据标记（集合）
 * @author liguoyang
 * @create 2018-06-14 上午10:27
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MockMap {

    /**
     * Key泛型类型
     */
    Class keyType();

    /**
     * Value泛型类型
     */
    Class valueType();
}
