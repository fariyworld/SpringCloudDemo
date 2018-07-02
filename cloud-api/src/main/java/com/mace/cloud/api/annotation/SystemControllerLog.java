package com.mace.cloud.api.annotation;

import java.lang.annotation.*;

/**
 * description: 自定义注解类 controller 日志注解
 *
 * @Documented: 指明该注解可以用于生成doc
 * @Inherited： 该注解可以被自动继承
 * @Retention:  指明在什么级别显示该注解：
 *                  RetentionPolicy.SOURCE 注解存在于源代码中，编译时会被抛弃
 *                  RetentionPolicy.CLASS 注解会被编译到 class 文件中，但是 JVM 会忽略
 *                  RetentionPolicy.RUNTIME JVM 会读取注解，同时会保存到 class 文件中
 * @Target:     指明该注解可以注解的程序范围
 *                  ElementType.TYPE 用于描述类、接口(包括注解类型) 或 enum 声明
 *                  ElementType.FIELD 作用于字段，包含枚举值
 *                  ElementType.METHOD 作用于方法，不包含构造方法
 *                  ElementType.PARAMETER 作用于方法的参数
 *                  ElementType.CONSTRUCTOR 作用于构造方法
 *                  ElementType.LOCAL_VARIABLE 作用于本地变量或者catch语句
 *                  ElementType.ANNOTATION_TYPE 作用于注解
 *                  ElementType.PACKAGE 作用于包
 * <br />
 * Created by mace on 12:07 2018/7/2.
 */
@Target({ElementType.TYPE, ElementType.METHOD})//该注解作用于类和字段上
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemControllerLog {

    String description()  default "controller"; //没有指定 defalut 的，需要在注解的时候显式指明
}
