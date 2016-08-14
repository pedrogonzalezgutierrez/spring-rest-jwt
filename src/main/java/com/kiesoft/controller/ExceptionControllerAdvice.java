package com.kiesoft.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kiesoft.domain.response.GenericResponse;
import com.kiesoft.dto.response.GenericResponseDTO;
import com.kiesoft.exceptions.PersistenceProblemException;

@ControllerAdvice
public class ExceptionControllerAdvice {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

	@ExceptionHandler(PersistenceProblemException.class)
	public ResponseEntity<GenericResponse> handlePersistenceProblemException(PersistenceProblemException e) {
		GenericResponseDTO responseDTO=new GenericResponseDTO();
		responseDTO.setMessage("Persistence Problem");
		return ResponseEntity.badRequest().body(responseDTO);
	}

}