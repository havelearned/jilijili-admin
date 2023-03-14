package wang.jilijili.framework.annotation;


import wang.jilijili.common.enums.OperationType;

import java.lang.annotation.*;

/**
 * @author admin
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JilJilOperationLog {

    String moduleName() default "";

    OperationType type();

}
