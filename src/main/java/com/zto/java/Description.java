package com.zto.java;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE}) // 表明注解用于什么地方
@Retention(RetentionPolicy.RUNTIME) //表示注解生命周期范围 ， 原代码？还是编译文件， 还是运行时
@Inherited  // 允许子类继承父类中的注解，继承的注解只会继承类注解，而不会继承方法注解
@Documented //在编译成Java Doc 的时候，会进行加载
public @interface Description {

        String desc();//成员是以无参无异常的方式声明的

        String author();

        int age() default 18; // 可以添加默认值

}
