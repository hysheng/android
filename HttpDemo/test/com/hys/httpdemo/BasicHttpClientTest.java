package com.hys.httpdemo;

import android.test.AndroidTestCase;
import android.util.Log;

public class BasicHttpClientTest extends AndroidTestCase {
	
	private static final String TAG="http";
	
	String url="";
	String params="id=1&name=yoona";
	
	public void testHttpGet() {
	BasicHttpClient client=new BasicHttpClient();
	String response=client.httpGet(url+"?"+params);
	Log.i(TAG, response);
		
	}

	public void testHttpPost() {
		BasicHttpClient client=new BasicHttpClient();
		String response=client.httpPost(url, params);
		Log.i(TAG, response);
	}

}
