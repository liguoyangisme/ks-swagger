package com.swagger.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 模拟数据标记（列表）
 * @author liguoyang
 * @create 2018-06-14 上午10:27
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MockList {

    /**
     * 泛型类型
     */
    Class type();
}
