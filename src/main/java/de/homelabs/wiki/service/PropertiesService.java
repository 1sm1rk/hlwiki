package de.homelabs.wiki.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.naming.directory.InvalidAttributesException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import de.homelabs.wiki.configuration.WikiConfiguration;
import de.homelabs.wiki.configuration.WikiConfigurationFactory;

@Service
public class PropertiesService {

	Logger log = LoggerFactory.getLogger(PropertiesService.class);
	
	ApplicationShutdownService ass;
	WikiConfiguration wikiConfiguration;
	Properties props = new Properties();
	Boolean propertiesLoaded = false;

	@Value("${basepath}")
	String basePath;
	
	@Value("${configfile}")
	String configFile;
	
	public PropertiesService(ApplicationShutdownService ass) {
		this.ass = ass;
	}
	
	public WikiConfiguration getWikiConfigration() {
		log.info("basepath: {}",basePath);
		log.info("configuration file: {}",basePath+"/"+configFile);
		
		if (!propertiesLoaded) {
			if (!this.loadProperties()) {
				ass.shutDown("problems loading properties file");
			}
			
			try {
				this.wikiConfiguration = WikiConfigurationFactory.createInstance(props);
			} catch (InvalidAttributesException e) {
				ass.shutDown("cannot parse properties file");
			}
		}
			
		return this.wikiConfiguration;
		
	}
	
	private boolean loadProperties() {
		try {
			this.props.put("basepath", basePath);
			this.props.put("configfile", configFile);
			this.props.load(new FileInputStream(new File(basePath+"/"+configFile)));
			return true;
		} catch (FileNotFoundException e) {
			log.error("cannot find properties file '{}' ", basePath+"/"+configFile);
			return false;
		} catch (IOException e) {
			log.error("cannot read properties file  '{}' {}", basePath+"/"+configFile,e.getLocalizedMessage());
			return false;
		}
	}
	
	
}
