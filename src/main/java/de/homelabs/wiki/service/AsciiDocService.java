package de.homelabs.wiki.service;

import java.io.File;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Attributes;
import org.asciidoctor.AttributesBuilder;
import org.asciidoctor.Options;
import org.springframework.stereotype.Service;

@Service
public class AsciiDocService {

	private final Asciidoctor adoctor = Asciidoctor.Factory.create();
	private final AttributesBuilder attributes = Attributes.builder()
            .backend("html")
            .icons(Attributes.FONT_ICONS);
	
	private final Options adoctorOptions = 
			Options
			.builder()
			.headerFooter(true)
			.inPlace(false)
			.toFile(false)
			.templateDirs(new File("/home/sm1rk/workbench/wiki/template/html5"))
			.attributes(attributes)
			.build();
	private final String rootPath="/home/sm1rk/workbench/wiki/";
	
	public AsciiDocService() {
		System.out.println("adoctor version: " + adoctor.asciidoctorVersion());
	}

	public String getPage(String name) {
		return adoctor.convertFile(new File(rootPath+name), adoctorOptions);
	}
	
}
