package com.hys.saxxml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import android.util.Log;

import com.hys.domain.Person;
import com.hys.service.PullPersonService;

public class PullPersonServiceTest extends TestCase {

	private static final String TAG = "test";

	public void testReadXml() throws Exception {
		InputStream inputStream=PullPersonServiceTest.class.getClassLoader().getResourceAsStream("person.xml");
		List<Person> persons=new ArrayList<Person>();
		persons=PullPersonService.readXml(inputStream);
		for (Person person : persons) {
			Log.i(TAG, person.getId()+"---"+person.getName()+"---"+person.getAge());
		}
	}

}
