package softuni.exam.instagraphlite.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class Util {

    /**
    if (Validation.isValid(DTO)) {
        ...
    }
     */

    public Util() {
    }

    private static Validator getValidator() {
        Validator validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        return validator;
    }

    public static boolean isValid(Object dto) {
        Set<ConstraintViolation<Object>> validationErrors = Util
                .getValidator()
                .validate(dto);

        return validationErrors.isEmpty();
    }
}
