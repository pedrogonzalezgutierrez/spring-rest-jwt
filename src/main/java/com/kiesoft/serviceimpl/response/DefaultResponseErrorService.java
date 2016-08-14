package com.kiesoft.serviceimpl.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.kiesoft.service.response.ResponseErrorService;

public class DefaultResponseErrorService implements ResponseErrorService {

	@Override
	public List<String> parseBindingErrors(BindingResult result) {
		List<String> ret=new ArrayList<>();
		for (ObjectError objectError : result.getAllErrors()) {
			ret.add(objectError.getCode());
		}
		return ret;
	}

}
