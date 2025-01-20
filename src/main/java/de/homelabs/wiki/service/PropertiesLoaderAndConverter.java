package de.homelabs.wiki.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import de.homelabs.wiki.entity.WikiConfiguration;

@Service
public class PropertiesLoaderAndConverter {

	Logger log = LoggerFactory.getLogger(PropertiesLoaderAndConverter.class);
	Properties props;
	Boolean propertiesLoaded = false;
	
	//TODO: make customizable
	String propertiesPath = "/home/sm1rk/projekte/datadir/wiki.properties";
	
	public PropertiesLoaderAndConverter() {
		
	}
	
	public WikiConfiguration getWikiConfigration() {
		if (!propertiesLoaded) {
			propertiesLoaded = this.loadProperties();
		}
		
		//TODO: convert properties to object
		return new WikiConfiguration(0);
	}
	
	private boolean loadProperties() {
		//TODO: error handling
		try {
			this.props.load(new FileInputStream(new File(propertiesPath)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
