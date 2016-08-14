package com.kiesoft.test.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import com.kiesoft.controller.user.UserController;
import com.kiesoft.domain.response.GenericResponse;
import com.kiesoft.dto.user.UserDTO;
import com.kiesoft.helper.response.ResponseErrorHelper;
import com.kiesoft.helper.validator.ValidatorHelper;
import com.kiesoft.helperimpl.response.DefaultResponseErrorHelper;
import com.kiesoft.helperimpl.validator.DefaultValidatorHelper;
import com.kiesoft.service.user.UserDTOService;
import com.kiesoft.validator.UserDTOValidator;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	
	private UserController userController;
	
	@Mock
	private UserDTOService userDTOService;
	
	private ResponseErrorHelper responseErrorHelper;
	private ValidatorHelper validatorHelper;
	private UserDTOValidator userDTOValidator;
	
	@Before
	public void setUp() {
		userController=new UserController();
		
		responseErrorHelper=new DefaultResponseErrorHelper();
		validatorHelper=new DefaultValidatorHelper();
		userDTOValidator=new UserDTOValidator();
		
		userController.setUserDTOService(userDTOService);
		userController.setResponseErrorService(responseErrorHelper);
		userController.setValidatorService(validatorHelper);
		userController.setUserDTOValidator(userDTOValidator);
		
		userDTOValidator.setUserDTOService(userDTOService);
	}
	
	@Test
	public void username_Empty() {
		ResponseEntity<GenericResponse> response=userController.register("", "Pedro1");
		Assert.isTrue(response.getStatusCode() == HttpStatus.BAD_REQUEST);
		Assert.isTrue("Validation".equals(response.getBody().getMessage()));
	}
	
	@Test
	public void password_Empty() {
		ResponseEntity<GenericResponse> response=userController.register("pedro1", "");
		Assert.isTrue(response.getStatusCode() == HttpStatus.BAD_REQUEST);
		Assert.isTrue("Validation".equals(response.getBody().getMessage()));
	}
	
	@Test
	public void username_MinimumLength() {
		ResponseEntity<GenericResponse> response=userController.register("pe", "Pedro1");
		Assert.isTrue(response.getStatusCode() == HttpStatus.BAD_REQUEST);
		Assert.isTrue("Validation".equals(response.getBody().getMessage()));
	}
	
	@Test
	public void password_MinimumLength() {
		ResponseEntity<GenericResponse> response=userController.register("pedro1", "ab");
		Assert.isTrue(response.getStatusCode() == HttpStatus.BAD_REQUEST);
		Assert.isTrue("Validation".equals(response.getBody().getMessage()));
	}
	
	@Test
	public void username_Alphanumeric() {
		ResponseEntity<GenericResponse> response=userController.register("pe-dro", "Pedr-o-1");
		Assert.isTrue(response.getStatusCode() == HttpStatus.BAD_REQUEST);
		Assert.isTrue("Validation".equals(response.getBody().getMessage()));
	}
	
	@Test
	public void password_Alphanumeric() {
		ResponseEntity<GenericResponse> response=userController.register("pedro", "Pedr-o-1");
		Assert.isTrue(response.getStatusCode() == HttpStatus.BAD_REQUEST);
		Assert.isTrue("Validation".equals(response.getBody().getMessage()));
	}
	
	@Test
	public void password_AtLeastOneUpperCharacter() {
		ResponseEntity<GenericResponse> response=userController.register("pedro1", "1pedro");
		Assert.isTrue(response.getStatusCode() == HttpStatus.BAD_REQUEST);
		Assert.isTrue("Validation".equals(response.getBody().getMessage()));
	}
	
	@Test
	public void password_AtLeastOneNumber() {
		ResponseEntity<GenericResponse> response=userController.register("pedro1", "Pedro");
		Assert.isTrue(response.getStatusCode() == HttpStatus.BAD_REQUEST);
		Assert.isTrue("Validation".equals(response.getBody().getMessage()));
	}
	
	@Test
	public void username_alreadyRegistered() {
		String username="pedro";
		UserDTO userDTO=new UserDTO();
		userDTO.setUsername(username);

		Mockito.when(userDTOService.findByUsername(username)).thenReturn(userDTO);
		
		ResponseEntity<GenericResponse> response=userController.register(username, "Pedro1");
		Assert.isTrue(response.getStatusCode() == HttpStatus.BAD_REQUEST);
		Assert.isTrue("Validation".equals(response.getBody().getMessage()));
	}
	
	@Test
	public void register() {
		String username="pedro";
		UserDTO userDTO=new UserDTO();
		userDTO.setUsername(username);

		Mockito.when(userDTOService.findByUsername(username)).thenReturn(null);
		Mockito.when(userDTOService.save(Mockito.any(UserDTO.class))).thenReturn(userDTO);
		
		ResponseEntity<GenericResponse> response=userController.register(username, "Pedro1");
		Assert.isTrue(response.getStatusCode() == HttpStatus.OK);
	}
	
}
