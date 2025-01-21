package de.homelabs.wiki.configuration;

import java.util.Properties;

import javax.naming.directory.InvalidAttributesException;

public class WikiConfigurationFactory {

	public static WikiConfiguration createInstance(Properties props) throws InvalidAttributesException {
		return new WikiConfiguration(props);
	}
}
