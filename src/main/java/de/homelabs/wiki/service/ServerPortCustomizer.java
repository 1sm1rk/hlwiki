package de.homelabs.wiki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class ServerPortCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

	@Autowired
	TestService testService;
	
	@Override
	public void customize(ConfigurableWebServerFactory factory) {
		String port = testService.test().getProperty("serverport", "8080");
		factory.setPort(Integer.parseInt(port));
		
	}

}
