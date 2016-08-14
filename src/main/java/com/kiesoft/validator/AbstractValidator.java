package com.kiesoft.validator;

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

	public String sanitizeString(String unTrustedString) {
		// I dont allow any html tag, just raw text
		PolicyFactory sanitizer = new HtmlPolicyBuilder().toFactory();
		return sanitizer.sanitize(unTrustedString);
	}

}
