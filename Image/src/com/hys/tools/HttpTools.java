package com.hys.tools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpTools {
	public HttpTools(){
		
	}
	public static InputStream getImageInputStream(String urlsString){
		InputStream inputStream=null;
		
		try {
			URL url=new URL(urlsString);
			if(url!=null){
			HttpURLConnection connection=(HttpURLConnection)url.openConnection();
			connection.setConnectTimeout(3000);
			connection.setRequestMethod("GET");
			int response_code=connection.getResponseCode();
			if(response_code==200){
				inputStream=connection.getInputStream();
			}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inputStream;
	}
	public static byte[] getImageByteArray(String urlsString){
		byte[] data=null;
		InputStream inputStream=null;
		ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
		try {
			URL url=new URL(urlsString);
			HttpURLConnection connection=(HttpURLConnection)url.openConnection();
			connection.setConnectTimeout(3000);
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			if(connection.getResponseCode()==200){
			    inputStream=connection.getInputStream();
				byte[] bf=new byte[1024];
				int len;
				while((len=inputStream.read(bf))!=-1){
					outputStream.write(bf, 0, len);
				}
				data=outputStream.toByteArray();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(inputStream!=null){
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return data;
	}
}
