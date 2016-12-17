package com.hys.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.hys.domain.Person;

public class JsonTools {
	
	private static final String TAG = "test";
	public static Person toBePerson(String jsonString){
		Person person=new Person();
		try {
			JSONObject jsonObject=new JSONObject(jsonString);
			JSONObject personObject=jsonObject.getJSONObject("person");
			String name=personObject.getString("name");
			String sex=personObject.getString("sex");
			int age=personObject.getInt("age");
			person.setName(name);
			person.setAge(age);
			person.setSex(sex);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.v(TAG, "^^^^^"+e.getMessage());
		}
		
		return person;
	}
	public static List<Person> getPersons(String jsonString){
		List<Person> persons=new ArrayList<Person>();
		Person person;
		try {
			JSONObject jsonObject=new JSONObject(jsonString);
			JSONArray jsonArray=jsonObject.getJSONArray("persons");
			for(int i=0;i<jsonArray.length();i++){
				JSONObject personObject=jsonArray.getJSONObject(i);
				person=new Person(personObject.getString("name"), 
						personObject.getInt("age"),personObject.getString("sex"));
				persons.add(person);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return persons;
	}
	public static List<String> toBeListString(String jsonString){
		List<String> list=new ArrayList<String>();
		try {
			JSONObject jsonObject=new JSONObject(jsonString);
			JSONArray jsonArray=jsonObject.getJSONArray("list_string");
			for(int i=0;i<jsonArray.length();i++){
				String name=jsonArray.getString(i);
				list.add(name);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public static List<Map<String, Object>> toListMap(String jsonString){
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=new HashMap<String, Object>();
		JSONArray jsonArray;
		try {
			JSONObject jsonObject=new JSONObject(jsonString);
			jsonArray = jsonObject.getJSONArray("list_map");
			for(int i=0;i<jsonArray.length();i++){
				JSONObject object=jsonArray.getJSONObject(i);
				Iterator<String> iterator=object.keys();
				while(iterator.hasNext()){
					String json_key=iterator.next();
					Object json_value=object.get(json_key);
					if(json_value==null){
						json_value="";
					}
					map.put(json_key, json_value);
				}
				list.add(map);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
