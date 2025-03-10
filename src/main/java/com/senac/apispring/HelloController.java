package com.senac.apispring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello";
	}
	
	
	
	@PostMapping("/hello")
	public String receiveHello(@RequestBody String message ) {
		return "Você enviou: " + message;
	}
	

}
