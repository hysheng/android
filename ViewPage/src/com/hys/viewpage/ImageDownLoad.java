package com.hys.viewpage;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class ImageDownLoad {

	public void loadImage(final String path, final ImageCallBack callBack) {
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				Bitmap bitmap = (Bitmap) msg.obj;
				callBack.getImageContent(bitmap);
			}
		};
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(path);
				HttpResponse response = null;
				try {
					response = httpClient.execute(httpPost);
					Log.v("test","responseCode="+response.getStatusLine().getStatusCode());
					if (response.getStatusLine().getStatusCode() == 200) {
						byte[] data = EntityUtils.toByteArray(response
								.getEntity());
						Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0,
								data.length);
						Log.v("test",""+path);
						Log.v("test",""+data.length);
						Message message = Message.obtain();
						message.obj = bitmap;
						handler.sendMessage(message);
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				} finally {
					httpClient.getConnectionManager().shutdown();
				}
			}
		}).start();
	}

	public interface ImageCallBack {
		public void getImageContent(Bitmap bitmap);
	}
}
