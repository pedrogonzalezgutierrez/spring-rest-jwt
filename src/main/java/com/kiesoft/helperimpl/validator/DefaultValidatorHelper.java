package com.kiesoft.helperimpl.validator;

import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import com.kiesoft.helper.validator.ValidatorHelper;

public class DefaultValidatorHelper implements ValidatorHelper {

	@Override
	public BindingResult validateObject(Object object, org.springframework.validation.Validator validator) {
		DataBinder binder = new DataBinder(object);
		binder.setValidator(validator);
		binder.validate();
		return binder.getBindingResult();
	}

}
