package cn.idevtools.common.annotation;


import cn.idevtools.common.commonEnum.ManageHistoryActionType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 添加该注解的方法将会被添加一条管理历史记录.
 * 可以指明1、操作类型
 *        2、操作描述
 *
 * @author 王沁宽
 * @date 2019/2/27
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AddManageHistory {
    ManageHistoryActionType ACTION_TYPE() default ManageHistoryActionType.NOMORL;
    String ACTION_DESC() default "";
}
