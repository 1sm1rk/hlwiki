package de.homelabs.wiki.configuration;

import java.util.Properties;

import javax.naming.directory.InvalidAttributesException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WikiConfiguration {
	
	private static Logger log = LoggerFactory.getLogger(WikiConfiguration.class);
	
	//general
	private String 		basePath					= "";
	private String 		configFile					= "";
	private int 		serverPort 					= 8080;
	private String 		domains 					= "";
	private String 		domainResolve 				= "";
	private String 		domainResolveField 			= "";
	
	//ssl
	private Boolean 	sslEnable 					= false;
	private String 		sslCertificate 				= "";
	private String 		sslCertificateKey 			= "";
	
	protected WikiConfiguration(Properties props) throws InvalidAttributesException {
		this.checkProperties(props);
	}
	
	private void checkProperties(Properties props) throws InvalidAttributesException {
		//path and config
		if (!props.containsKey("basepath")) {
			log.error("properties: cannot find 'basepath'");
			throw new InvalidAttributesException();
		} else {
			this.basePath = (props.getProperty("basepath"));
		}
		
		if (!props.containsKey("configfile")) {
			log.error("properties: cannot find 'configfile'");
			throw new InvalidAttributesException();
		} else {
			this.configFile = (props.getProperty("configfile"));
		}
		
		//general
		if (!props.containsKey("serverport")) {
			log.error("properties: cannot find 'serverport'");
			throw new InvalidAttributesException();
		} else {
			this.serverPort = Integer.parseInt(props.getProperty("serverport"));
		}
		
		if (!props.containsKey("domains")) {
			log.error("properties: cannot find 'domains'");
			throw new InvalidAttributesException();
		} else {
			this.domains = (props.getProperty("domains"));
		}
		
		if (!props.containsKey("domain_resolve")) {
			log.error("properties: cannot find 'domain_resolve'");
			throw new InvalidAttributesException();
		} else {
			this.domainResolve = (props.getProperty("domain_resolve"));
		}
		
		if (!props.containsKey("domain_resolve_field")) {
			log.error("properties: cannot find 'domain_resolve_field'");
			throw new InvalidAttributesException();
		} else {
			this.domainResolveField = (props.getProperty("domain_resolve_field"));
		}
		
		if (!props.containsKey("ssl_enable")) {
			log.error("properties: cannot find 'ssl_enable'");
			throw new InvalidAttributesException();
		} else {
			this.sslEnable = Boolean.parseBoolean(props.getProperty("ssl_enable"));
		}
		
		if (!props.containsKey("ssl_certificate")) {
			log.error("properties: cannot find 'ssl_certificate'");
			throw new InvalidAttributesException();
		} else {
			this.sslCertificate = (this.basePath+"/ssl/"+props.getProperty("ssl_certificate"));
		}
		
		if (!props.containsKey("ssl_certificate_key")) {
			log.error("properties: cannot find 'ssl_certificate_key'");
			throw new InvalidAttributesException();
		} else {
			this.sslCertificateKey = (this.basePath+"/ssl/"+props.getProperty("ssl_certificate_key"));
		}
		return;
	}

	public String getBasePath() 			{		return basePath;				}
	public String getConfigFile() 			{		return configFile;				}
	
	public int getServerPort() 				{		return serverPort;				}
	public String getDomains() 				{		return domains;					}
	public String getDomainResolve() 		{		return domainResolve;			}
	public String getDomainResolveField() 	{		return domainResolveField;		}
	
	public Boolean getSslEnable() 			{		return sslEnable;				}
	public String getSslCertificate() 		{		return sslCertificate;			}
	public String getSslCertificateKey() 	{		return sslCertificateKey;		}
}
