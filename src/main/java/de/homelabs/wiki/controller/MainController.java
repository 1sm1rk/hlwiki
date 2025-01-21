package de.homelabs.wiki.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	public MainController() {
		
	}
	
	@GetMapping(value="/", produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	String getLandingPage() {
		return "Hello World!"; 
	}
}
