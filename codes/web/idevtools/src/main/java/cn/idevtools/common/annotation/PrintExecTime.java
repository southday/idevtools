package cn.idevtools.common.annotation;

import java.lang.annotation.*;

/**
 * 该注解作用于方法，用于打印方法运行时间
 *
 * @author 王沁宽
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PrintExecTime {
}
