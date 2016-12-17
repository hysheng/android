package com.hys.camera;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class HttpPostUtils {
	public static void sendFormByPost(final String path, final byte[] fileBody,
			final String fileName) {
		new Thread(new Runnable() {	
			@Override
			public void run() {
				Log.v("test", "$$$$$$$$$");
				// TODO Auto-generated method stub
				try {
					HttpClient httpClient=new DefaultHttpClient();
					HttpPost httpPost=new HttpPost(path);
					MultipartEntity entity=new MultipartEntity();
					entity.addPart("myfile", new ByteArrayBody(fileBody, fileName));
					httpPost.setEntity(entity);
					httpClient.execute(httpPost);
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}
		}).start();
	}
}
