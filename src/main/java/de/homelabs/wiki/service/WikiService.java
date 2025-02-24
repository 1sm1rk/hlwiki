package de.homelabs.wiki.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WikiService {

	Logger log = LoggerFactory.getLogger(this.getClass());
	private MarkdownParser parser;
	
	public WikiService(MarkdownParser parser) {
		this.parser = parser;
	}
	
	public String getPage(String url) {
		return parser.renderHtml(url);
	}
}
