package com.kiesoft.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/secure")
public class JWTController {

	@RequestMapping(value = "/roleadmin", method = RequestMethod.GET)
	public String roleAdmin() {
		return "Hi ROLE_ADMIN. You what mate?";
	}

	@RequestMapping(value = "/roleeditor", method = RequestMethod.GET)
	public String roleEditor() {
		return "Hi ROLE_EDITOR";
	}

	@RequestMapping(value = "/rolestaff", method = RequestMethod.GET)
	public String roleStaff() {
		return "Hi ROLE_STAFF";
	}

}
