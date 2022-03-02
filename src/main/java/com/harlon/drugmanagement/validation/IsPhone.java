package com.harlon.drugmanagement.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {IsPhoneValidation.class})
public @interface IsPhone {
    String massage() default "手机号格式错误";

    Class<?>[] group() default {};

    Class<? extends Payload>[] payload() default {};

}
