package athletics.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import athletics.dto.CustomerDto;


public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final CustomerDto customerDto = (CustomerDto) obj;
        return customerDto.getPassword().equals(customerDto.getMatchingPassword());
    }

}
