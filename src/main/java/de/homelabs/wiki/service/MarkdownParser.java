package de.homelabs.wiki.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MarkdownParser {
	Logger log = LoggerFactory.getLogger(MarkdownParser.class);
	
	//TODO: add a default template and css stylesheet
	//TODO: what about images
	//TODO: make it more 'usable'
	public String renderHtml(String url) {
		String before = "<html><head><title>homelabs.de</title></head><body>";
		String nav = "<div id='nav'>"
				+ "<a href='/'>Home</a>&nbsp;"
				+ "<a href='/test'>Test</a>&nbsp;"
				+ "<a href='/test2'>Test2</a>&nbsp;"
				+ "<a href='/einleitung/seite1'>Einleitung/Seite1</a>&nbsp;"
				+ "<a href='/einleitung/eins/test'>Einleitung/eins/test</a>&nbsp;"
				+ "</div>";
		String middle = "<div id='content'>";
		String after = "</div></body></html>";
		
		byte[] content;
		try {
			content = Files.readAllBytes(Paths.get(url));
		} catch (IOException e) {
			log.error("cannot load page: {}",e.getLocalizedMessage());
			return "That did not work :-(";
		}
		
		Parser parser = Parser.builder().build();
		Node document = parser.parse(new String(content));
		HtmlRenderer renderer = HtmlRenderer.builder().build();
		String page = before + nav + middle + renderer.render(document) + after;
		
		return page;
	}
}
