package com.urs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class UserRegistrationHelloController {
	public static final Logger logger = LoggerFactory.getLogger(UserRegistrationHelloController.class);
	

	@GetMapping("/")
	public ResponseEntity<String> sayHello() {
		return new ResponseEntity<>("Hello", HttpStatus.OK);
	}

}
