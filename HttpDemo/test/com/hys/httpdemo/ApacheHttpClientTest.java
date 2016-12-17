package com.hys.httpdemo;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.test.AndroidTestCase;
import android.util.Log;

public class ApacheHttpClientTest extends AndroidTestCase {

	private static final String TAG="http";
	String response="";
	public void testHttpGet() {
		String url="";
		ApacheHttpClient client=new ApacheHttpClient();
		try {
			response=client.httpGet(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.i(TAG, response);
	}

	public void testHttpPost() {
		String url="";
		ApacheHttpClient client=new ApacheHttpClient();
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", "1"));
		params.add(new BasicNameValuePair("name", "Jessica"));
		try{
			response=client.httpPost(url, params);
		}catch(Exception e){
			e.printStackTrace();
		}
		Log.i(TAG, response);
	}

}
