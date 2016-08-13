package com.kiesoft.domain.response.error;

import java.util.List;

import com.kiesoft.domain.response.GenericResponse;

public interface ErrorResponse extends GenericResponse {
	
	List<String> getMessages();

}
