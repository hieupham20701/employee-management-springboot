package com.codingclub.springbootdemo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidatorFile.class})
public @interface ValidFile {
    String message() default "Only jpg, png, jpeg, are allowed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
