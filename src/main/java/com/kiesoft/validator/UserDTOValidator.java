package com.kiesoft.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.kiesoft.dto.user.UserDTO;

@Component
public class UserDTOValidator extends AbstractValidator implements Validator {

	private final static Integer USERNAME_MIN=3;
	private final static Integer USERNAME_MAX=20;

	@Override
	public boolean supports(Class<?> clazz) {
		return UserDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		UserDTO dto = (UserDTO) target;

		// Nothing can be empty
		rejectFieldIfEmptyOrWhitespace("username", errors);

		if (errors.hasErrors() == true) {
			return;
		}

		if (dto.getUsername()!=null && dto.getUsername().length() < USERNAME_MIN) {
			errors.rejectValue("username", "The username is too short");
		} else if (dto.getUsername()!=null && dto.getUsername().length() > USERNAME_MAX) {
			errors.rejectValue("username", "The username is too big");
		}

	}

}
