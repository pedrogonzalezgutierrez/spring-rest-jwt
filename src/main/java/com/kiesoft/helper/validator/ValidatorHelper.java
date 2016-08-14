package com.kiesoft.helper.validator;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

public interface ValidatorHelper {
	
	BindingResult validateObject(Object object, Validator validator);

}
