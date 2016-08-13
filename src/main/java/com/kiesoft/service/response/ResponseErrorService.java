package com.kiesoft.service.response;

import java.util.List;

import org.springframework.validation.BindingResult;

public interface ResponseErrorService {
	
	List<String> parseBindingErrors(BindingResult result);

}
