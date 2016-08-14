package com.kiesoft.helper.response;

import java.util.List;

import org.springframework.validation.BindingResult;

public interface ResponseErrorHelper {
	
	List<String> parseBindingErrors(BindingResult result);

}
