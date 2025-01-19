package de.homelabs.wiki.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import de.homelabs.wiki.service.AsciiDocService;

@Controller
public class MainController {

	private AsciiDocService aService;
	
	public MainController(AsciiDocService asciiDocService) {
		this.aService = asciiDocService;
	}
	
	@GetMapping(value="/", produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	String getLandingPage() {
		//return "<html><body>test</body></html>";
		//String content = /home/sm1rk/workbench/wiki
		return aService.getPage("start.adoc");
	}
}
