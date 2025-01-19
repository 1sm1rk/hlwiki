package de.homelabs.wiki;


import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Options;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class HlwikiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HlwikiApplication.class, args);
		
	}

	public static void test() {
		Parser parser = Parser.builder().build();
		Node document = parser.parse("[//]: <> (This is also a comment.)\n"
				+ "# sandbox\n"
				+ "\n"
				+ "add some text ;-)\n"
				+ "This is *Markdown*");
		HtmlRenderer renderer = HtmlRenderer.builder().build();
		String result = renderer.render(document); 
		
		System.out.println(result);
	}
	
	public static void test2() {
		String document = "// this comment line is ignored\n"
				+ "= Landing Page\n"
				+ "Dirk Müller <smirk.mueller@gmail.com>; Dirk Web <mudirk@web.de> \n"
				+ ":revdate: 13.01.2025\n"
				+ ":revnumber: 20.1\n"
				+ ":description: this is just a landing page \n"
				+ ":sectanchors: \n"
				+ ":url-repo: https://my-git-repo.com \n"
				+ "\n"
				+ "{doctitle}\n"
				+ "The document body starts here.\n"
				+ "\n"
				+ "== About {author_1}\n"
				+ "The author {firstname} {lastname} also know as {authorinitials}\n"
				+ "\n"
				+ "== About {author_2}\n"
				+ "The author {firstname_2} {lastname_2} also know as {authorinitials_2}";
		
		Asciidoctor adoctor = Asciidoctor.Factory.create();
		String result = adoctor.convert(document,
				Options.builder()
					.headerFooter(true)
					.inPlace(false)
					.build());
		System.out.println(result);
		
		/* 
		String outfile = adoctor.convert(document, Options.builder().headerFooter(true).backend("pdf").build());
		System.out.println(outfile);
		*/
		
		String result2 = adoctor.convert(document,
				Options.builder()
					.headerFooter(true)
					.inPlace(false)
					.build());
		System.out.println(result2);
		
	}
}
