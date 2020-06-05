package dev.kirillzhelt.cliqueify.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExpiryDateValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExpiryDateConstraint {
    String message() default "min expiry date is tomorrow";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
