package com.mace.cloud.api.annotation;

import java.lang.annotation.*;

/**
 * description:
 * <br />
 * Created by mace on 12:07 2018/7/2.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemControllerLog {

    String description()  default "";
}
