package com.hys.httpdemo;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;



public class ApacheHttpClient {
	private HttpClient httpClient=new DefaultHttpClient();
	private HttpResponse httpResponse;
	private String response=null;
	private int statusCode=0;
	public String httpGet(String url){
		HttpGet httpGet=new HttpGet(url);
		try{
			httpResponse=httpClient.execute(httpGet);
			statusCode=httpResponse.getStatusLine().getStatusCode();
			if(statusCode==HttpStatus.SC_OK){
				response=EntityUtils.toString(httpResponse.getEntity());
			}else {
				response="·µ»ØÂë£º"+statusCode;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return response;
	}
	public String httpPost(String url,List<NameValuePair> params){
		HttpPost httpPost=new HttpPost(url);
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
			httpResponse=httpClient.execute(httpPost);
			statusCode=httpResponse.getStatusLine().getStatusCode();
			if(statusCode==HttpStatus.SC_OK){
				response=EntityUtils.toString(httpResponse.getEntity());
			}else{
				response="·µ»ØÂë: "+statusCode;
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
		return response;
	}
}
