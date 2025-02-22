package de.homelabs.wiki.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class FaviconController {
	Logger log = LoggerFactory.getLogger(FaviconController.class);
	
	public FaviconController() {
			
	}
	
	//@GetMapping(value="*", produces = MediaType.TEXT_HTML_VALUE)
	@GetMapping("favicon.ico")
	@ResponseBody
	void noFavicon(HttpServletRequest request) {
		
		log.debug("favicon controller");
		return;
	}
	
	
}
