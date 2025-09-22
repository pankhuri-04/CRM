package com.example.crm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class contoller {
	@GetMapping("/")
	public String home() {
		return "CRM Backend is Running!";
	}

}
