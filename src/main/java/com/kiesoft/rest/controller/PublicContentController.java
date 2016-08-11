package com.kiesoft.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/public")
public class PublicContentController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String roleStaff() {
		return "Hi. This is public content";
	}
	

}
