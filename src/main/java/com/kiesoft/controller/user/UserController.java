package com.kiesoft.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kiesoft.domain.response.GenericResponse;
import com.kiesoft.dto.response.ErrorResponseDTO;
import com.kiesoft.dto.response.success.UserCreatedSuccessResponseDTO;
import com.kiesoft.dto.user.UserDTO;
import com.kiesoft.service.note.UserDTOService;
import com.kiesoft.service.response.ResponseErrorService;
import com.kiesoft.validator.UserDTOValidator;

@RestController
public class UserController {
	
	@Autowired
	private UserDTOService userDTOService;
	
	@Autowired
	private UserDTOValidator userDTOValidator;
	
	@Autowired
	private ResponseErrorService responseErrorService;
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public ResponseEntity<Page<UserDTO>> users(@PageableDefault(size=5) Pageable page) {
		return ResponseEntity.ok().body(userDTOService.findAll(page));
	}
	
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public ResponseEntity<GenericResponse> newUser() {
		UserDTO userDTO=new UserDTO();
		DataBinder binder = new DataBinder(userDTO);
		binder.setValidator(userDTOValidator);
		binder.validate();
		BindingResult result = binder.getBindingResult();
		
		if (result.hasErrors()) {
			ErrorResponseDTO errorResponseDTO=new ErrorResponseDTO();
			errorResponseDTO.setDefaultMessage("Validation");
			errorResponseDTO.getMessages().addAll(responseErrorService.parseBindingErrors(result));
		    return ResponseEntity.badRequest().body(errorResponseDTO);
		}
		
		UserDTO savedUserDTO=userDTOService.save(userDTO);
		UserCreatedSuccessResponseDTO userCreatedSuccessResponseDTO=new UserCreatedSuccessResponseDTO();
		userCreatedSuccessResponseDTO.setDefaultMessage("Welcome "+savedUserDTO.getUsername());
		userCreatedSuccessResponseDTO.setUser(savedUserDTO);
		return ResponseEntity.ok().body(userCreatedSuccessResponseDTO);
	}

}
