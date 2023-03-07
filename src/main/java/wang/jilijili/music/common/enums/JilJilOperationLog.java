package wang.jilijili.music.common.enums;


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
