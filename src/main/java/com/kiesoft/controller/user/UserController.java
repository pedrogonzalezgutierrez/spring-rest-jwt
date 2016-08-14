package com.kiesoft.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kiesoft.domain.response.GenericResponse;
import com.kiesoft.dto.response.GenericResponseDTO;
import com.kiesoft.dto.response.MessagesResponseDTO;
import com.kiesoft.dto.response.PageResponseDTO;
import com.kiesoft.dto.user.UserDTO;
import com.kiesoft.helper.response.ResponseErrorHelper;
import com.kiesoft.helper.validator.ValidatorHelper;
import com.kiesoft.service.user.UserDTOService;
import com.kiesoft.validator.UserDTOValidator;

@RestController
public class UserController {
	
	@Autowired
	private UserDTOService userDTOService;
	
	@Autowired
	private ResponseErrorHelper responseErrorService;
	
	@Autowired
	private ValidatorHelper validatorService;
	
	@Autowired
	private UserDTOValidator userDTOValidator;
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public ResponseEntity<GenericResponse> users(@PageableDefault(size=5) Pageable page) {
		PageResponseDTO responseDTO=new PageResponseDTO();
		responseDTO.setMessage("List users");
		responseDTO.setItems(userDTOService.findAllByUsername(page));
		return ResponseEntity.ok().body(responseDTO);
	}
	
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public ResponseEntity<GenericResponse> register(@RequestParam("username") String username, @RequestParam("password") String password) {
		
		UserDTO userDTO=new UserDTO();
		userDTO.setUsername(username);
		userDTO.setPassword(password);
		
		BindingResult result = validatorService.validateObject(userDTO, userDTOValidator);
		
		if (result.hasErrors()) {
			MessagesResponseDTO responseDTO=new MessagesResponseDTO();
			responseDTO.setMessage("Validation");
			responseDTO.setItems(responseErrorService.parseBindingErrors(result));
		    return ResponseEntity.badRequest().body(responseDTO);
		}
		
		UserDTO savedUserDTO=userDTOService.save(userDTO);
		GenericResponseDTO responseDTO=new GenericResponseDTO();
		responseDTO.setMessage("Welcome "+savedUserDTO.getUsername());
		return ResponseEntity.ok().body(responseDTO);
	}

	
	/*
	 * Setters
	 */
	public void setUserDTOService(UserDTOService userDTOService) {
		this.userDTOService = userDTOService;
	}

	public void setResponseErrorService(ResponseErrorHelper responseErrorService) {
		this.responseErrorService = responseErrorService;
	}

	public void setValidatorService(ValidatorHelper validatorService) {
		this.validatorService = validatorService;
	}

	public void setUserDTOValidator(UserDTOValidator userDTOValidator) {
		this.userDTOValidator = userDTOValidator;
	}

}
