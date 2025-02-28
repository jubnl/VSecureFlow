package ch.jubnl.vsecureflow.backend.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Inherited
public @interface Auditable {
    boolean exclude() default false;
}
