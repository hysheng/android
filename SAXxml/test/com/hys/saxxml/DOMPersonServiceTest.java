package com.hys.saxxml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;



import com.hys.domain.Person;
import com.hys.service.DOMPersonService;

import junit.framework.TestCase;

public class DOMPersonServiceTest extends TestCase {

	private static final String TAG = "test";

	public void testReadXml() throws Exception {
		InputStream inputStream=DOMPersonServiceTest.class.getClassLoader().getResourceAsStream("person.xml");
		List<Person> persons=new ArrayList<Person>();
		persons=DOMPersonService.readXml(inputStream);
		for (Person person : persons) {
			Log.i(TAG, person.getId()+"---"+person.getName()+"---"+person.getAge());
		}
	}

}
