package com.hys.tools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

public class HttpTools {
	private static final String TAG = "test";
	public HttpTools(){
		
	}
	public static String getJsonContent(final String path){
			String js="";
				try{
				URL url=new URL(path);
				HttpURLConnection connection=(HttpURLConnection) url.openConnection();
				connection.setConnectTimeout(3000);
				connection.setRequestMethod("GET");
				connection.setDoInput(true);
				int flag=connection.getResponseCode();
				if(flag == 200){
					js=changStreamToString(connection.getInputStream());
					Log.v(TAG, "@@@@"+js);
					
				}
			}catch(Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				return js;
	}
	private static String changStreamToString(InputStream inputStream) {
		// TODO Auto-generated method stub
		String jsonString="";
		ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
		int len=0;
		byte[] data=new byte[1024];
		try {
			while((len=inputStream.read(data))!=-1){
				outputStream.write(data, 0, len);
			}
			jsonString=new String(outputStream.toByteArray());
//			jsonString=outputStream.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;
	}
}
