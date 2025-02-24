package de.homelabs.wiki.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import de.homelabs.wiki.service.MarkdownParser;
import de.homelabs.wiki.service.WikiService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {
	Logger log = LoggerFactory.getLogger(MainController.class);
	WikiService wService;
	
	public MainController(WikiService wService) {
		this.wService = wService;
	}
	
	@GetMapping(value={"*", "*/**"}, produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	String getLandingPage(HttpServletRequest request) {
		
		log.info(request.getRequestURI());

		//TODO: filter special chars and bad or malicious requests -> URLFilter
		if (!request.getRequestURI().matches("[/A-Za-z0-9]+")) {
			log.error("wrong request, contains special chars : {}", request.getRequestURI());
			return "That did not work :-(";
		}
		
		//Landing Page
		if (request.getRequestURI().equals("/") || (request.getRequestURI().equals("")))
			return wService.getPage("/start.md");
		else {
			return wService.getPage(request.getRequestURI()+".md");
		}
	}
	
	
}
