package dev.kirillzhelt.cliqueify.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class ExpiryDateValidator implements ConstraintValidator<ExpiryDateConstraint, LocalDate> {

    public static LocalDate getMinExpiryDate() {
        return LocalDate.now().plusDays(1);
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate minExpiryDate = ExpiryDateValidator.getMinExpiryDate();
        return localDate != null && (localDate.isEqual(minExpiryDate) || localDate.isAfter(minExpiryDate));
    }

    @Override
    public void initialize(ExpiryDateConstraint constraintAnnotation) {
    }
}
