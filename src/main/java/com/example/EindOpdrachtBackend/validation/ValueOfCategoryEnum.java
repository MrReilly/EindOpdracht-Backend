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
@Constraint(validatedBy = ValueOfCategoryEnumValidator.class)
public @interface ValueOfCategoryEnum {
    Class<? extends Enum<?>> enumClass();
    String message() default "must be one of: \n\n[FESTIVAL, ART, CONCERT, KIDS, FAIR, NATURE, MARKET, THEATER, CONFERENCE, CIRCUS, SPORTS, OTHER]";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

