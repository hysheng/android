package com.hys.hughsmechanism;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import android.graphics.Bitmap;

public class MemoryCache {
	private static final int MAX_CACHE_CAPACITY=30;
	private HashMap<String, SoftReference<Bitmap>> mCacheMap=
			new LinkedHashMap<String, SoftReference<Bitmap>>(){
	private static final long serialVersionUID=1L;
	protected boolean removeEldestEntry(
		Map.Entry<String, SoftReference<Bitmap>> eldest){
		return size() > MAX_CACHE_CAPACITY;
		};
	};
 public Bitmap get(String id){
	 if(!mCacheMap.containsKey(id))
		 return null;
	 SoftReference<Bitmap> ref=mCacheMap.get(id);
	 return ref.get();
 }
 public void put(String id,Bitmap bitmap){
	 mCacheMap.put(id, new SoftReference<Bitmap>(bitmap));
 }
 public void clear(){
	 try{
		 for(Map.Entry<String , SoftReference<Bitmap>>entry:mCacheMap.entrySet()){
			 SoftReference<Bitmap> sr=entry.getValue();
			 if(null!=sr){
				 Bitmap bitmap=sr.get();
				 if(null!=bitmap)
					 bitmap.recycle();
			 }
		 }
		 mCacheMap.clear();
	 }catch(Exception exception){
		 exception.printStackTrace();
	 }
 }
}