package com.kiesoft.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.kiesoft.dto.user.UserDTO;
import com.kiesoft.service.user.UserDTOService;

@Component
public class UserDTOValidator extends AbstractValidator implements Validator {

	private final static Integer USERNAME_MIN=3;
	private final static Integer USERNAME_MAX=20;
	
	private final static Integer PASSWORD_MIN=3;
	private final static Integer PASSWORD_MAX=20;
	
	@Autowired
	private UserDTOService userDTOService; 

	@Override
	public boolean supports(Class<?> clazz) {
		return UserDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		UserDTO dto = (UserDTO) target;
		// Avoid XSS injections
		dto.setUsername(sanitizeString(dto.getUsername()));
		dto.setPassword(sanitizeString(dto.getPassword()));

		// Nothing can be empty
		rejectFieldIfEmptyOrWhitespace("username", errors);
		rejectFieldIfEmptyOrWhitespace("password", errors);

		if (errors.hasErrors() == true) {
			return;
		}

		// Length
		rejectFieldIfStringMinMaxLength("username", dto.getUsername(), USERNAME_MIN, USERNAME_MAX, errors);
		rejectFieldIfStringMinMaxLength("password", dto.getPassword(), PASSWORD_MIN, PASSWORD_MAX, errors);

		if (errors.hasErrors() == true) {
			return;
		}
		
		rejectFieldIfNotAlphaNumeric("username", dto.getUsername(), errors);
		rejectFieldIfNotAlphaNumeric("password", dto.getPassword(), errors);
		rejectFieldIfNotContainsNumbers("password", dto.getPassword(), errors);
		rejectFieldIfNotContainsUpperCharacters("password", dto.getPassword(), errors);

		if( userDTOService.findByUsername(dto.getUsername()) != null ) {
			errors.rejectValue("username", "The username already exists");
		}

	}

	public void setUserDTOService(UserDTOService userDTOService) {
		this.userDTOService = userDTOService;
	}

}
