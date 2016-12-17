package com.hys.httpdemo;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpConnection;


public class myBasicClient {
	private static final int HTTP_OK=200;
	private static final int TIME_OUT=6000;
	private static final String METHOD_GET="get";
	private static final String METHOD_POST="post";
	private HttpURLConnection  connection;
	private URL url;
	private InputStream inputStream;
	private String response;
	
	public String doGet(String urlString){
		try {
			url=new URL(urlString);
			connection=(HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			connection.disconnect();
		}
		return response;
		
	}
}
