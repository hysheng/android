package com.hys.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.*;
public class GsonTools {
	public static <T> T jsonToPerson(String jsonString,Class<T> cls){
		T t=null;
		try {
			Gson gson=new Gson();
			t=gson.fromJson(jsonString,cls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	public static <T> List<T> jsonToListPerson(String jsonString,Class<T> cls){
		List<T> list=new ArrayList<T>();
		try {
			Gson gson=new Gson();
			list=gson.fromJson(jsonString, new TypeToken<List<T>>(){}.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public static List<String> jsonToListString(String jsonString){
		List<String> list=new ArrayList<String>();
		try {
			Gson gson=new Gson();
			list=gson.fromJson(jsonString, new TypeToken<List<String>>(){}.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public static List<Map<String, Object>> jsonToListMap(String jsonString){
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		try {
			Gson gson=new Gson();
			list=gson.fromJson(jsonString, new TypeToken<List<Map<String, Object>>>(){}.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return list;
	}
}
