package com.hys.service;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.hys.domain.Person;
import com.hys.handler.XMLContentHandler;

public class SAXPersonService {
	public static List<Person> readXml(InputStream inputStream) throws Exception{
		SAXParserFactory factory=SAXParserFactory.newInstance();
		SAXParser parser=factory.newSAXParser();
		XMLContentHandler handler=new XMLContentHandler();
		parser.parse(inputStream, handler);
		inputStream.close();
		return handler.getPersons();
	}
}
