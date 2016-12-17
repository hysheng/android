package com.hys.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hys.spinner.R;



public class AdapterTools {
	public AdapterTools(){
		
	}
	public static List<String> getStrings(){
		List<String> list=new ArrayList<String>();
		list.add("MacBook Air");
		list.add("MacBook Pro");
		list.add("iMac");
		list.add("iPhone");
		list.add("iPod");
		list.add("iPad");
		return list;
	}
	public static List<Map<String, Object>> getMaps(){
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("Logo", R.drawable.imac);
		map.put("produce", "imac");
		Map<String, Object> map2=new HashMap<String, Object>();
		map2.put("Logo", R.drawable.ipad);
		map2.put("produce", "ipad");
		Map<String, Object> map3=new HashMap<String, Object>();
		map3.put("Logo", R.drawable.iphone);
		map3.put("produce", "iphone");
		Map<String, Object> map4=new HashMap<String, Object>();
		map4.put("Logo", R.drawable.ipod);
		map4.put("produce", "ipod");
		Map<String, Object> map5=new HashMap<String, Object>();
		map5.put("Logo", R.drawable.macbook_air);
		map5.put("produce", "macbook_air");
		Map<String, Object> map6=new HashMap<String, Object>();
		map6.put("Logo", R.drawable.macbook_pro);
		map6.put("produce", "macbook_pro");
		list.add(map);
		list.add(map2);
		list.add(map3);
		list.add(map4);
		list.add(map5);
		list.add(map6);
		return list;
	}
}
