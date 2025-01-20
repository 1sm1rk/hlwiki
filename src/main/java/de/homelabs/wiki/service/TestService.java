package de.homelabs.wiki.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.springframework.stereotype.Service;

@Service
public class TestService {

	public Properties test() {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(new File("/home/sm1rk/projekte/datadir/wiki.properties")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return props;
	}
}
