package com.hys.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.widget.Toast;

public class MyIntentService extends IntentService {
	/*
	 * 1.不需要开启线程
	 * 2.不需要关闭服务，它自己关闭
	 * 3.单线程下载数据
	 * 
	 */
	private static final String URL = "https://www.baidu.com/img/bd_logo1.png";

	public MyIntentService() {
		super("Hugh_IntentService");
		// TODO Auto-generated constructor stub
	}
	

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(URL);
		HttpResponse response = null;
		File file = Environment.getExternalStorageDirectory();
		FileOutputStream outputStream = null;
		try {
			response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				byte[] data = EntityUtils.toByteArray(response.getEntity());
				if (Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					File new_file = new File(file, "hugh_baidu_logo.png");
					outputStream = new FileOutputStream(new_file);
					outputStream.write(data, 0, data.length);
					Toast.makeText(getApplicationContext(), "Downloaded", Toast.LENGTH_LONG)
							.show();

				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(outputStream!=null){
					outputStream.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			httpClient.getConnectionManager().shutdown();

		}

	}

}
