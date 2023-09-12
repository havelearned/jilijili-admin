package top.jilijili.system.common.aspect.annotation;

import top.jilijili.system.common.enums.NotifyType;

import java.lang.annotation.*;

/**
 * 系统通知
 * 使用中间件,发送具体通知的注解
 *
 * @author Amani
 * @date 2023年09月10日 8:40
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface SysNotify {

    /**
     * 通知标题
     *
     * @return
     */
    String title();



    /**
     * 通知类型
     * 默认系统通知
     *
     * @return
     */
    NotifyType notifyType() default NotifyType.SYSTEM_NOTIFY;


}
