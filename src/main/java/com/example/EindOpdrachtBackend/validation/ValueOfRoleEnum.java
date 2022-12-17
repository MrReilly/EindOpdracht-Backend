package com.example.EindOpdrachtBackend.validation;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(value = RUNTIME)
@Documented
@Constraint(validatedBy = ValueOfRoleEnumValidator.class)
public @interface ValueOfRoleEnum {

    Class<? extends Enum<?>> enumClass();

    String message() default "must be one of: \n\n[ORGANIZER, VISITOR, ADMIN]";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
