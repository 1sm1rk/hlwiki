package de.homelabs.wiki.configuration;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.Ssl;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

import de.homelabs.wiki.service.PropertiesService;

@Component
public class ServerCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
	Logger log = LoggerFactory.getLogger(ServerCustomizer.class);
	
	PropertiesService propService;
	
	public ServerCustomizer(PropertiesService propService) {
		this.propService = propService;
	}

	@Override
	public void customize(ConfigurableWebServerFactory factory) {
		WikiConfiguration config = propService.getWikiConfigration();
		
		//webserver port
		factory.setPort(config.getServerPort());
		
		//SSL if needed
		if (config.getSslEnable()) {
			log.info("enable SSL");
			if (
					Files.notExists(Paths.get(config.getSslCertificate()))
					|| Files.notExists(Paths.get(config.getSslCertificateKey())) 
							
			   ) {
				log.error("cannot find certificate file '{}' or key '{}'"
						, config.getSslCertificate(), config.getSslCertificateKey());
				
				return;
			}
			
			//create and set server SSL context
			Ssl ssl = new Ssl();
			ssl.setCertificate(config.getSslCertificate());
			ssl.setCertificatePrivateKey(config.getSslCertificateKey());
			ssl.setEnabled(true);
			factory.setSsl(ssl);
		}
		
	}

}
