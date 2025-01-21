package de.homelabs.wiki.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ApplicationShutdownService {
	
	Logger log = LoggerFactory.getLogger(ApplicationShutdownService.class);
		
	public ApplicationShutdownService() {
	
	}
	
	public void shutDown(String message) {
		log.info("...shutting down: {}",message);
		System.exit(1);
	}
}
