package com.hys.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import android.util.Xml;

import com.hys.domain.Person;

public class PullPersonService {
	
	public static List<Person> readXml(InputStream inputStream) throws Exception{
		List<Person> persons=null;
		XmlPullParser parser=Xml.newPullParser();
		parser.setInput(inputStream, "UTF-8");
		int eventCode=parser.getEventType();
		Person person=null;
		while(eventCode!=XmlPullParser.END_DOCUMENT){
			switch (eventCode) {
			case XmlPullParser.START_DOCUMENT:
				persons=new ArrayList<Person>();
				break;
				case XmlPullParser.START_TAG:
					if("person".equals(parser.getName())){
						person=new Person();
						person.setId(Integer.valueOf(parser.getAttributeValue(0)));
						
					}else if ("name".equals(parser.getName())) {
						person.setName(parser.nextText());
					}else if("age".equals(parser.getName())){
						person.setAge(Integer.valueOf(parser.nextText()));
					}
					break;
				case XmlPullParser.END_TAG:
					if("person".equals(parser.getName())&&person!=null){
						persons.add(person);
						person=null;
					}
					break;
			}
				eventCode=parser.next();
				
			}
		return persons;
		}
	}

