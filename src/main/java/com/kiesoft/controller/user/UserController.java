package com.kiesoft.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kiesoft.domain.response.GenericResponse;
import com.kiesoft.dto.response.GenericResponseDTO;
import com.kiesoft.dto.response.MessagesResponseDTO;
import com.kiesoft.dto.response.PageResponseDTO;
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
	public ResponseEntity<GenericResponse> users(@PageableDefault(size=5) Pageable page) {
		PageResponseDTO responseDTO=new PageResponseDTO();
		responseDTO.setMessage("List users");
		responseDTO.setItems(userDTOService.findAllByUsername(page));
		return ResponseEntity.ok().body(responseDTO);
	}
	
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public ResponseEntity<GenericResponse> newUser(@RequestParam("username") String username, @RequestParam("password") String password) {
		
		UserDTO userDTO=new UserDTO();
		userDTO.setUsername(username);
		userDTO.setPassword(password);
		
		DataBinder binder = new DataBinder(userDTO);
		binder.setValidator(userDTOValidator);
		binder.validate();
		BindingResult result = binder.getBindingResult();
		
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

}
