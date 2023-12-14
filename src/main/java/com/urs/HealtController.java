package com.urs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealtController {

	// Should avoid White label using localhost:port
	@GetMapping("/")
	public String home() {
		return "OK! This is home";
	}
}
