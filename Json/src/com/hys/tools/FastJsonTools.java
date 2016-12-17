package com.hys.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

public class FastJsonTools {
	public static <T> T jsonToPerson(String jsonString, Class<T> cls) {
		T t = null;
		try {
			t=JSON.parseObject(jsonString, cls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	public static <T> List<T> jsonToListPerson(String jsonString, Class<T> cls) {
		List<T> list = new ArrayList<T>();
		try {
			list=JSON.parseArray(jsonString,cls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public static List<Map<String, Object>> jsonToListMap(String jsonString) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list=JSON.parseObject(jsonString,
					 new TypeReference<List<Map<String, Object>>>(){});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}