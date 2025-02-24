package de.homelabs.wiki.service;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import de.homelabs.hlfileserver.entity.FileserverProperties;
import de.homelabs.hlfileserver.service.FileserverFactory;
import de.homelabs.hlfileserver.service.FileserverService;

@Service
public class MarkdownParser {
	Logger log = LoggerFactory.getLogger(this.getClass());
	PropertiesService propService;
	FileserverService fsService;
	
	public MarkdownParser(PropertiesService propService) {
		this.propService = propService;
		
		//TODO: data context from wiki base props
		FileserverProperties props = new FileserverProperties(
				this.propService.wikiConfiguration.getBasePath() + "/data/homelabs_de"
				, 100);
		this.fsService = FileserverFactory.createInstance(props);
	}
	
	//TODO: add a default template and css stylesheet
	//TODO: what about images
	//TODO: make it more 'usable'
	public String renderHtml(String url) {	
		String content = getContent(url);
		if (content.equalsIgnoreCase("")) {
			content = getError();
		}
		return getHeader() + getMenu() + content + getFooter();
	}
	
	private String getHeader() {
		byte[] data = fsService.getFileContent("/style.css");
		return "<html>"
				+ "<head>"
				+ "<title>homelabs.de</title>"
				+ "<style type='text/css'>"+data+"</style>"
				+ "</head>"
				+ "<body>";
	}
	
	private String getFooter() {
		return "</body></html>";
	}
	
	private String getMenu() {
		return "<div id='nav'>"
				+ "<a href='/'>Home</a>&nbsp;"
				+ "<a href='/test'>Test</a>&nbsp;"
				+ "<a href='/test2'>Test2</a>&nbsp;"
				+ "<a href='/einleitung/seite1'>Einleitung/Seite1</a>&nbsp;"
				+ "<a href='/einleitung/eins/test'>Einleitung/eins/test</a>&nbsp;"
				+ "</div>";
	}
	
	private String getContent(String url) {
		String preContent 	= "<div id='content'>";
		String postContent 	= "</div>";
		byte[] data = fsService.getFileContent(url);
		if (data.length <= 0)
			return "";
		Parser parser = Parser.builder().build();
		Node document = parser.parse(new String(data));
		HtmlRenderer renderer = HtmlRenderer.builder().build();
		return preContent + renderer.render(document) + postContent;
	}
	
	private String getError() {
		return "that did not work :-(";
	}
}
