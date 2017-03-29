package athletics.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import athletics.model.PasswordChangeForm;

public class PasswordChangeValidator implements Validator {

	@Override
	public boolean supports(Class<?> targetClass) {
		return PasswordChangeForm.class.isAssignableFrom(targetClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "oldPassword", "valid.Oldpassword");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword", "valid.newPassword");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPasswordConfirm", "valid.newPasswordConfirm");
		PasswordChangeForm passwordChange = (PasswordChangeForm)obj;
		if(!passwordChange.getNewPassword().equals(passwordChange.getNewPasswordConfirm())){
			errors.rejectValue("newPassword", "valid.differentPasswords", "Passwords are different!");
		}

	}

}
