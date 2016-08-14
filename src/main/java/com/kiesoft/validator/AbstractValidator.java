package com.kiesoft.validator;

import java.util.regex.Pattern;

import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

public abstract class AbstractValidator {

	public void rejectFieldIfEmptyOrWhitespace(String field, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, field, field+" is required");
	}

	public void rejectFieldIfStringMinMaxLength(String field, String string, int min, int max, Errors errors) {
		if ((string.length() < min) || (string.length() > max)) {
			errors.rejectValue(field, field+": Wrong length");
		}
	}

	public void rejectFieldIfNumberMinMaxRange(String field, Integer number, int min, int max, Errors errors) {
		if ((number < min) || (number > max)) {
			errors.rejectValue(field, field+": Wrong range");
		}
	}
	
	public void rejectFieldIfNotAlphaNumeric(String field, String string, Errors errors) {
		if( Pattern.compile("^[a-zA-Z0-9]*$").matcher(string).matches() == false ) {
			errors.rejectValue(field, field+": Not alphanumeric");
		}
	}
	
	public void rejectFieldIfNotContainsUpperCharacters(String field, String string, Errors errors) {
		if( Pattern.compile("[A-Z]").matcher(string).find() == false ) {
			errors.rejectValue(field, field+": It needs to contains at least one upper case character");
		}
	}
	
	public void rejectFieldIfNotContainsNumbers(String field, String string, Errors errors) {
		if( Pattern.compile("[0-9]").matcher(string).find() == false ) {
			errors.rejectValue(field, field+": It needs to contains at least one number");
		}
	}

	public String sanitizeString(String unTrustedString) {
		// I dont allow any html tag, just raw text
		PolicyFactory sanitizer = new HtmlPolicyBuilder().toFactory();
		return sanitizer.sanitize(unTrustedString);
	}

}
