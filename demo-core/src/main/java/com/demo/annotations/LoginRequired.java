package com.demo.annotations;

import java.lang.annotation.*;

/**
 * Desc:
 * User: sunjz
 * Email: jiuzeng.sun@leappmusic.com
 * Date: 2017/8/24
 * Time: 上午11:02
 * To change this template use File | Settings | File Templates.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginRequired {

    boolean validate() default true;

}
