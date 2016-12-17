package com.hys.httpdemo;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BasicHttpClient {
	
	private static final int TIMEOUT = 1000*6;
	private static final String METHOD_GET = "get";
	private static final String METHOD_POST="post";
	private static final int HTTP_OK = 200;
	private URL url=null;
	private HttpURLConnection connection=null;
	private InputStream inputStream=null;
	private String response=null;
	private byte[] data=null;
	private int responseCode=0;

	public String httpGet(String urlStr){
		try {
			url=new URL(urlStr);
			connection=(HttpURLConnection)url.openConnection();
			connection.setDoInput(true);
			connection.setConnectTimeout(TIMEOUT);
			connection.setRequestMethod(METHOD_GET);
			connection.setRequestProperty("accept", "*/*");
			connection.connect(); 
			responseCode=connection.getResponseCode();
			if(responseCode==HTTP_OK){
				inputStream=connection.getInputStream();
				response=getResponse(inputStream);
			}else {
				response="·µ»ØÂë£º"+responseCode;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.disconnect();
		}
		return response;
		
	}
	public String httpPost(String urlStr,String params){
		data=params.getBytes();
		try {
			url=new URL(urlStr);
			connection=(HttpURLConnection)url.openConnection();
			connection.setConnectTimeout(TIMEOUT);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setRequestMethod(METHOD_POST);
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setRequestProperty("Charset", "UTF-8");
			connection.setRequestProperty("Conten-Length", String.valueOf(data.length));
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.connect();
			DataOutputStream dataOutputStream=new DataOutputStream(connection.getOutputStream());
			dataOutputStream.write(data);
			dataOutputStream.flush();
			dataOutputStream.close();
			responseCode=connection.getResponseCode();
			if(responseCode==HTTP_OK){
				inputStream=connection.getInputStream();
				response=getResponse(inputStream);
			}else {
				response="·µ»ØÂë£º"+responseCode;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	private String getResponse(InputStream inputStream) throws IOException{
		ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
		int len=-1;
		byte[] buffer=new byte[1024];
		while((len=inputStream.read(buffer))!=-1){
			outputStream.write(buffer, 0, len);
		}
		data=outputStream.toByteArray();
		return new String(data);
	}
}
