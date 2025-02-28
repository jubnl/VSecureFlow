package ch.jubnl.vsecureflow.backend.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.LOCAL_VARIABLE, ElementType.PARAMETER})
@Inherited
public @interface Secured {
    String readRight() default "";

    String writeRight() default "";
}
