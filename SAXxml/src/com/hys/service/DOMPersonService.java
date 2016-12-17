package com.hys.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



import com.hys.domain.Person;

public class DOMPersonService {
	public static List<Person> readXml(InputStream inputStream) throws Exception{
		List<Person> persons=new ArrayList<Person>();
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		Document document=builder.parse(inputStream);
		Element root=(Element) document.getDocumentElement();
		NodeList nodeList=root.getElementsByTagName("pre:person");
		for(int i=0;i<nodeList.getLength();i++){
			Element persElement=(Element)nodeList.item(i);
			Person person=new Person();
			person.setId(Integer.valueOf(persElement.getAttribute("id")));
			NodeList chiList=persElement.getChildNodes();
			for(int j=0;j<chiList.getLength();j++){
			Node chiNode=chiList.item(j);
			if(chiNode.getNodeType()==Node.ELEMENT_NODE){
				Element chiElement=(Element)chiNode;
				if("name".equals(chiElement.getNodeName())){
					person.setName(chiElement.getFirstChild().getNodeValue());
				}else if(("age".equals(chiElement.getNodeName()))){
					person.setAge(Integer.valueOf(chiElement.getFirstChild().getNodeValue()));
				}
			}
			}
			persons.add(person);
		}
		
		
		return persons;
	}
}
