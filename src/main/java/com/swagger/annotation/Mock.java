package com.swagger.annotation;

import java.lang.annotation.*;

/**
 * 模拟数据标记
 * @author liguoyang
 * @create 2018-06-14 上午10:27
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Mock {

}
