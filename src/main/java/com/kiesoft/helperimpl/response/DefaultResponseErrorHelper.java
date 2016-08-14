package com.kiesoft.helperimpl.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.kiesoft.helper.response.ResponseErrorHelper;

public class DefaultResponseErrorHelper implements ResponseErrorHelper {

	@Override
	public List<String> parseBindingErrors(BindingResult result) {
		List<String> ret=new ArrayList<>();
		for (ObjectError objectError : result.getAllErrors()) {
			ret.add(objectError.getCode());
		}
		return ret;
	}

}
