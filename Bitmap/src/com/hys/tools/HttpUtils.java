package com.hys.tools;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpUtils {
	public static byte[] getMapData(String map_path){
		HttpClient httpClient=new DefaultHttpClient();
		HttpPost httpPost=new HttpPost(map_path);
		HttpResponse response;
		try{
			response=httpClient.execute(httpPost);
			if(response.getStatusLine().getStatusCode()==200){
				return EntityUtils.toByteArray(response.getEntity());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
}
}