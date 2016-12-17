package com.hys.saxxml;

import java.io.InputStream;
import java.util.List;

import com.hys.domain.Person;
import com.hys.service.SAXPersonService;

import android.test.AndroidTestCase;
import android.util.Log;

public class SAXPersonServiceTest extends AndroidTestCase {

	private static final String TAG = "test";

	public void testReadXml() throws Exception {
		InputStream inputStream=SAXPersonServiceTest.class.getClassLoader().getResourceAsStream("person.xml");
		List<Person> persons=SAXPersonService.readXml(inputStream);
		for (Person person : persons) {
			Log.i(TAG, person.getId()+"==="+person.getName()+"==="+person.getAge());
		}
	}

}
