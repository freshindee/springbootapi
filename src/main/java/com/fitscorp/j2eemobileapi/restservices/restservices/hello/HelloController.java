package com.fitscorp.j2eemobileapi.restservices.restservices.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/hello")
	public String HellowWorld() {
		return "Hello Workd";
	}

	
	@GetMapping("/user")
	public UserDetails getUser() {
		return new UserDetails("Indika","Ruwan","Nugegoda");
	}

	
}
