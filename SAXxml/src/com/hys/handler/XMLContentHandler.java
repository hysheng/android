package com.hys.handler;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

import com.hys.domain.Person;

public class XMLContentHandler extends DefaultHandler {
	private static final String TAG="test";
	private List<Person> persons;
	private Person person;
	private String preTag;
	
	public List<Person> getPersons(){
		return persons;
	}
	@Override
	public void startDocument() throws SAXException {
		persons=new ArrayList<Person>();
		Log.i(TAG, "开始解析文档....");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if("person".equals(localName)){
			person=new Person();
			person.setId(Integer.valueOf(attributes.getValue("id")));
		}
		preTag=localName;
		Log.i(TAG, "解析元素"+localName);
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if(person!=null){
			String data=new String(ch,start,length);
			if("name".equals(preTag)){
				person.setName(data);
			}else if("age".equals(preTag)){
				person.setAge(Integer.valueOf(data));
			}
		}
		Log.i(TAG, "解析内容："+new String(ch,start,length));
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if("person".equals(localName)&&person!=null){
			persons.add(person);
			person=null;
		}
		preTag=null;
		Log.i(TAG, localName+"解析完毕");
	}
	
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		Log.i(TAG, "文档解析完毕");
	}
	
	
	
}
