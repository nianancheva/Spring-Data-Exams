package softuni.exam.instagraphlite.util;

import org.springframework.stereotype.Component;
import softuni.exam.instagraphlite.models.dtos.PictureDTO;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class DTOValidator {

    public static boolean isValid(PictureDTO pictureDTO) {
        Validator validator;
        validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<Object>> errors = validator.validate(pictureDTO);

        return errors.isEmpty();
    }

//    private final Validator validator;
//
//    public DTOValidator() {
//        this.validator = Validation
//                .buildDefaultValidatorFactory()
//                .getValidator();
//    }
//
//
//
//    public static boolean isValid(Object dto) {
//        DTOValidator dtoValidator = new DTOValidator();
//
//        Set<ConstraintViolation<Object>> errors = dtoValidator.getValidator().validate(dto);
//
//        return errors.isEmpty();
//    }
//
//    public Validator getValidator() {
//        return validator;
//    }
}
