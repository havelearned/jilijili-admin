package top.jilijili.system.common.aspect.annotation;


import java.lang.annotation.*;

/**
 * 可以标注在类上或者方法上
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RestrictAccess {

    /**
     * key
     * @return
     */
    String value() default "";

}
