package athletics.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneConstraintValidator implements ConstraintValidator<ValidPhone, String> {
 
    @Override
    public void initialize(ValidPhone phone) { }
 
    @Override
    public boolean isValid(String phoneField, ConstraintValidatorContext cxt) {
        if(phoneField == null) {
            return false;
        }
        return phoneField.matches("^\\+?([0-9]{2})?\\(?[0-9]{3}\\)?[0-9]{3}\\-?[0-9]{2}\\-?[0-9]{2}$");
        //^\s*(?:\+?(\d{1,3}))?[-. (]*(\d{3})[-. )]*(\d{3})[-. ]*(\d{4})(?: *x(\d+))?\s*$
        
    }
}