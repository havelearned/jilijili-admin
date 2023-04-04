package wang.jilijili.common.annotation;

import java.lang.annotation.*;

/**
 * 防止表单重复提交
 *
 * @author Amani
 * @date 2023年04月03日 9:54
 */
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoRepeatSubmit {


    /**
     * 指定时间内不可重复提交，单位 毫秒
     *
     * @return
     */
    long timeout() default 3L;



}
